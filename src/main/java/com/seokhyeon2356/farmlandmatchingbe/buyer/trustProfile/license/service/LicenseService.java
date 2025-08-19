package com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.license.service;

import com.seokhyeon2356.farmlandmatchingbe.buyer.entity.Buyer;
import com.seokhyeon2356.farmlandmatchingbe.buyer.repository.BuyerRepository;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.entity.TrustProfile;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.license.dto.LicenseRes;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.license.repo.LicenseRepository;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.license.dto.LicenseReq;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.license.entity.License;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.repository.TrustProfileRepository;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.service.TrustProfileService;
import com.seokhyeon2356.farmlandmatchingbe.supabase.service.SupabaseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LicenseService {

    private final LicenseRepository licenseRepository;
    private final SupabaseService supabaseService;
    private final BuyerRepository buyerRepository;
    private final TrustProfileService trustProfileService;

    @Transactional
    public List<LicenseRes> saveLicense(Long buyerId, List<LicenseReq> requestList) throws IOException {
        Buyer buyer = buyerRepository.findById(buyerId)
                .orElseThrow(() -> new IllegalArgumentException("Buyer not found: " + buyerId));

        List<License> existing = licenseRepository.findByBuyerLicense_BuyerId(buyerId);
        Map<Long, License> existingMap = existing.stream()
                .collect(Collectors.toMap(License::getLicenseId, l -> l));

        List<License> toPersist = new ArrayList<>();
        Set<Long> seenIds = new HashSet<>();

        for (LicenseReq req : requestList) {
            if (req.getLicenseId() == null) {
                // 신규
                String fileUrl = null;
                if (hasRealFile(req.getLicenseFile())) {
                    String uploaded = supabaseService.uploadFile(req.getLicenseFile());
                    if (uploaded != null && !uploaded.isBlank()) fileUrl = uploaded;
                }
                License created = License.builder()
                        .licenseName(req.getLicenseName())
                        .licenseFile(fileUrl)      // 파일 없으면 null(신규 항목이므로 OK)
                        .buyerLicense(buyer)
                        .build();
                toPersist.add(created);

            } else {
                // 수정
                License target = existingMap.get(req.getLicenseId());
                if (target == null) throw new IllegalArgumentException("License not found: id=" + req.getLicenseId());
                seenIds.add(req.getLicenseId());

                target.setLicenseName(req.getLicenseName());

                // ✅ 진짜 새 파일이 온 경우에만 업로드 + 교체
                if (Boolean.TRUE.equals(req.getRemoveFile())) {
                    // 필요하면 실제 스토리지에서도 파일 삭제
                    // supabaseService.deleteFileByUrl(target.getLicenseFile());
                    target.setLicenseFile(null);

                } else if (hasRealFile(req.getLicenseFile())) {
                    // 새 파일 업로드가 있을 때만 교체
                    String newUrl = supabaseService.uploadFile(req.getLicenseFile());
                    if (newUrl != null && !newUrl.isBlank()) {
                        // 필요하면 기존 파일 삭제
                        // supabaseService.deleteFileByUrl(target.getLicenseFile());
                        target.setLicenseFile(newUrl);
                    }
                    // 파일 업로드 실패 → 기존 URL 유지
                }
                // 파일 파트 없음 → 기존 URL 유지

                toPersist.add(target);
            }
        }
        // 요청에 없는 항목 삭제(기존 로직 유지)
        List<License> toDelete = existing.stream()
                .filter(l -> l.getLicenseId() != null && !seenIds.contains(l.getLicenseId()))
                .collect(Collectors.toList());
        if (!toDelete.isEmpty()) licenseRepository.deleteAllInBatch(toDelete);

        List<License> saved = licenseRepository.saveAll(toPersist);

        trustProfileService.recalcAndPersist(buyerId);

        return saved.stream().map(LicenseRes::new).toList();
    }


    private boolean hasRealFile(MultipartFile file) {
        if (file == null || file.isEmpty()) return false;
        try {
            // 일부 프론트가 "null" 문자열을 파일처럼 보낼 수 있음 → 무시
            if (file.getSize() <= 16) {
                String body = new String(file.getBytes(), java.nio.charset.StandardCharsets.UTF_8);
                if ("null".equalsIgnoreCase(body.trim())) return false;
            }
        } catch (IOException ignore) { }
        return true;
    }

    public List<LicenseRes> getLicenses(Long buyerId) {

        return licenseRepository.findByBuyerLicense_BuyerId(buyerId).stream()
                .map(LicenseRes::new)
                .toList();
    }
}
