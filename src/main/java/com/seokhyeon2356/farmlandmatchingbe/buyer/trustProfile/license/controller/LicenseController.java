package com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.license.controller;

import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.license.dto.LicenseReq;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.license.service.LicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class LicenseController {

    private final LicenseService licenseService;

    @PostMapping("/{buyerId}/license-upload")
    public ResponseEntity<Map<String, Object>> uploadLicense(@PathVariable Long trustId,
                                                             @ModelAttribute LicenseReq licenseReq) throws IOException {

        Long licenseId = licenseService.saveLicense(trustId, licenseReq);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "자격증 등록 완료");
        response.put("licenseId", licenseId);

        return ResponseEntity.ok(response);
    }
}
