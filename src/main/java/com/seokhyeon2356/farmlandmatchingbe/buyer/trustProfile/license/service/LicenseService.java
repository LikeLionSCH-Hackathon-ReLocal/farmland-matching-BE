package com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.license.service;

import com.seokhyeon2356.farmlandmatchingbe.buyer.entity.Buyer;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.entity.TrustProfile;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.license.LicenseRepository;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.license.dto.LicenseReq;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.license.entity.License;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.repository.TrustProfileRepository;
import com.seokhyeon2356.farmlandmatchingbe.supabase.service.SupabaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class LicenseService {

    private final LicenseRepository licenseRepository;
    private final SupabaseService supabaseService;
    private final TrustProfileRepository trustProfileRepository;

    public Long saveLicense(Long trustId, LicenseReq licenseReq) throws IOException {

        TrustProfile tp = trustProfileRepository.findById(trustId)
                .orElseThrow(() -> new IllegalArgumentException("프로필을 찾을 수 없습니다."));

        String licenseFileURL = supabaseService.uploadFile(licenseReq.getLicenseFile());

        License license = License.builder()
                .licenseName(licenseReq.getLicenseName())
                .licenseFile(licenseFileURL)
                .build();

        licenseRepository.save(license);

        return license.getLicenseId();
    }
}
