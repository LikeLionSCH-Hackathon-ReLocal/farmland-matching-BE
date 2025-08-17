package com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.controller;

import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.dto.TrustProfileReq;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.dto.TrustProfileRes;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.dto.TrustProfileUpdateReq;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.entity.TrustProfile;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.service.TrustProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class TrustProfileController {

    private final TrustProfileService trustProfileService;

    @PostMapping("/buyer-trustProfile-upload/{buyerId}")
    public ResponseEntity<Map<String, Object>> trustProfileUpload(@PathVariable Long buyerId,
                                                                   @RequestBody TrustProfileReq trustProfileReq) {

        Long trust = trustProfileService.createTrustProfile(buyerId, trustProfileReq);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "신뢰프로필 등록 완료");
        response.put("trustProfileId", trust);

        return ResponseEntity.ok(response);
    }

    //신뢰프로필 정보 불러오기
    @GetMapping("/buyer-trustProfile/{buyerId}")
    public ResponseEntity<TrustProfileRes> getTrust(@PathVariable Long buyerId) {

        TrustProfileRes tp = trustProfileService.getTrustProfile(buyerId);
        return ResponseEntity.ok(tp);
    }

    //신뢰프로필 정보 수정하기
    @PatchMapping("/buyer-trustProfile-update/{buyerId}")
    public ResponseEntity<String> updateTrust(@PathVariable Long buyerId,
                                                          @RequestBody TrustProfileUpdateReq trustProfileUpdateReq) {

        trustProfileService.updateTrustProfile(buyerId, trustProfileUpdateReq);

        return ResponseEntity.ok("신뢰프로필 수정 완료");
    }
}
