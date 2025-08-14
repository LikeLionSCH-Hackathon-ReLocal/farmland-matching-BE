package com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.controller;

import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.dto.TrustProfileReq;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.entity.TrustProfile;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.service.TrustProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TrustProfileController {

    private final TrustProfileService trustProfileService;

    @PostMapping("/buyer-trustProfile-upload/{buyerId}")
    public ResponseEntity<Long> trustProfileUpload(@PathVariable Long buyerId,
                                                   @RequestBody TrustProfileReq trustProfileReq) {

        Long trust = trustProfileService.createTrustProfile(trustProfileReq);

        return ResponseEntity.ok(trust);
    }
}
