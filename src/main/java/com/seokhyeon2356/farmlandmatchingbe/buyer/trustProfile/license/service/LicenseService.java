package com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.license.service;

import com.seokhyeon2356.farmlandmatchingbe.buyer.entity.Buyer;
import com.seokhyeon2356.farmlandmatchingbe.buyer.repository.BuyerRepository;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.entity.TrustProfile;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.license.dto.LicenseRes;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.license.repo.LicenseRepository;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.license.dto.LicenseReq;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.license.entity.License;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.repository.TrustProfileRepository;
import com.seokhyeon2356.farmlandmatchingbe.supabase.service.SupabaseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LicenseService {

    private final LicenseRepository licenseRepository;
    private final SupabaseService supabaseService;
    private final BuyerRepository buyerRepository;

    @Transactional
    public List<LicenseRes> saveLicense(Long buyerId, List<LicenseReq> requestList) throws IOException {

        Buyer buyer = buyerRepository.findById(buyerId)
                .orElseThrow(() -> new IllegalArgumentException("Buyer not found: " + buyerId));

        // 1) 기존 DB 상태
        List<License> existing = licenseRepository.findByBuyerLicense_BuyerId(buyerId);
        Map<Long, License> existingMap = existing.stream()
                .collect(Collectors.toMap(License::getLicenseId, l -> l));

        // 2) 요청 기준으로 upsert
        List<License> toPersist = new ArrayList<>();
        Set<Long> seenIds = new HashSet<>();

        for (LicenseReq req : requestList) {
            if (req.getLicenseId() == null) {
                // 신규 추가
                String fileUrl = null;
                if (req.getLicenseFile() != null && !req.getLicenseFile().isEmpty()) {
                    fileUrl = supabaseService.uploadFile(req.getLicenseFile());
                }
                License created = License.builder()
                        .licenseName(req.getLicenseName())
                        .licenseFile(fileUrl)           // 파일 없으면 null 가능(필요 시 validation)
                        .buyerLicense(buyer)
                        .build();
                toPersist.add(created);

            } else {
                // 수정 (있던 것 찾아 업데이트)
                License target = existingMap.get(req.getLicenseId());
                if (target == null) {
                    // 방어 코드: 요청이 잘못된 경우
                    throw new IllegalArgumentException("License not found: id=" + req.getLicenseId());
                }
                seenIds.add(req.getLicenseId());

                target.setLicenseName(req.getLicenseName());

                // 파일이 새로 올라온 경우에만 교체
                if (req.getLicenseFile() != null && !req.getLicenseFile().isEmpty()) {
                    String newUrl = supabaseService.uploadFile(req.getLicenseFile());
                    target.setLicenseFile(newUrl);
                }
                // 파일 미첨부 → 기존 URL 유지
                toPersist.add(target);
            }
        }
        // 3) 삭제: DB에는 있는데 요청에는 없는 항목
        List<License> toDelete = existing.stream()
                .filter(l -> l.getLicenseId() != null && !seenIds.contains(l.getLicenseId()))
                .collect(Collectors.toList());

        if (!toDelete.isEmpty()) {
            licenseRepository.deleteAllInBatch(toDelete);
        }
        // 4) 저장 (신규 + 수정)
        List<License> saved = licenseRepository.saveAll(toPersist);
        // 5) 응답 DTO로 변환
        return saved.stream().map(LicenseRes::new).toList();
    }

    public List<LicenseRes> getLicenses(Long buyerId) {

        return licenseRepository.findByBuyerLicense_BuyerId(buyerId).stream()
                .map(LicenseRes::new)
                .toList();
    }
}
