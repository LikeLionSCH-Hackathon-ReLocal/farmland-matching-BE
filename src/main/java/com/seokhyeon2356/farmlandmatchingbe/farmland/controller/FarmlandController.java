package com.seokhyeon2356.farmlandmatchingbe.farmland.controller;

import com.seokhyeon2356.farmlandmatchingbe.farmland.dto.*;
import com.seokhyeon2356.farmlandmatchingbe.farmland.service.FarmlandService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class FarmlandController {

    private final FarmlandService farmlandService;

    @PostMapping("/{sellerId}/farmland-upload")
    public ResponseEntity<Map<String,Object>> createFarmland(@PathVariable Long sellerId, @ModelAttribute FarmlandRequestDto dto) throws IOException {

        Long landId = farmlandService.saveFarmland(sellerId, dto);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "농지 등록 완료");
        response.put("land_id", landId);

        return ResponseEntity.ok(response);
    }

    //seller의 모든 농지 조회
    @GetMapping("/{sellerId}/farmland")
    public List<FarmlandListRes> listBySeller(@PathVariable Long sellerId) {
        return farmlandService.getFarmlandsBySeller(sellerId);
    }

    //농지 수정
    @PatchMapping("/{sellerId}/farmland-update/{landId}")
    public ResponseEntity<String> update(
            @PathVariable Long sellerId,
            @PathVariable Long landId,
            @RequestBody FarmlandUpdateReq req
    ) {
        farmlandService.updateFarmland(sellerId, landId, req);
        return ResponseEntity.ok("농지 정보 수정 완료");
    }

    //농지 삭제
    @DeleteMapping("/{sellerId}/farmland-delete/{landId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long sellerId,
            @PathVariable Long landId
    ) {
        farmlandService.deleteFarmland(sellerId, landId);
        return ResponseEntity.ok().build();
    }

    //농지 상세보기
    @GetMapping("/{sellerId}/farmland/{landId}")
    public FarmlandDetailRes getDetail(
            @PathVariable Long sellerId,
            @PathVariable Long landId
    ) {
        return farmlandService.getFarmlandDetail(sellerId, landId);
    }

    //신청자 상세보기
    @GetMapping("/{sellerId}/farmland/{landId}/applicants/{buyerId}")
    public BuyerDetailRes getApplicantDetail(
            @PathVariable Long sellerId,
            @PathVariable Long landId,
            @PathVariable Long buyerId
    ) {
        return farmlandService.getApplicantDetail(sellerId, landId, buyerId);
    }

    //신청자 신청 수락
    @PostMapping("/{sellerId}/farmland/{landId}/applicants/{buyerId}/accept")
    public ResponseEntity<Void> accept(
            @PathVariable Long sellerId,
            @PathVariable Long landId,
            @PathVariable Long buyerId
    ) {
        farmlandService.acceptApplicant(sellerId, landId, buyerId);
        return ResponseEntity.ok().build();
    }

    //신청자 신청 거절
    @PostMapping("/{sellerId}/farmland/{landId}/applicants/{buyerId}/reject")
    public ResponseEntity<Void> reject(
            @PathVariable Long sellerId,
            @PathVariable Long landId,
            @PathVariable Long buyerId
    ) {
        farmlandService.rejectApplicant(sellerId, landId, buyerId);
        return ResponseEntity.ok().build();
    }
}
