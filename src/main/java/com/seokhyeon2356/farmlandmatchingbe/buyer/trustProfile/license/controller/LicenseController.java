package com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.license.controller;

import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.license.dto.LicenseListReq;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.license.dto.LicenseReq;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.license.dto.LicenseRes;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.license.service.LicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class LicenseController {

    private final LicenseService licenseService;

    @PostMapping("/{buyerId}/license-save")
    public ResponseEntity<String> uploadLicense(@PathVariable Long buyerId,
                                                @ModelAttribute LicenseListReq licenseListReq) throws IOException {

        licenseService.saveLicense(buyerId, licenseListReq.getLicenseList());

        return ResponseEntity.ok("자격증등록완료");
    }

    @GetMapping("/{buyerId}/licenses")
    public List<LicenseRes> getLicenses(@PathVariable Long buyerId) {

        return licenseService.getLicenses(buyerId);
    }
}
