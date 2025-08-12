package com.seokhyeon2356.farmlandmatchingbe.buyer.controller;

import com.seokhyeon2356.farmlandmatchingbe.buyer.dto.BuyerRequestDto;
import com.seokhyeon2356.farmlandmatchingbe.buyer.service.BuyerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class BuyerController {

    private final BuyerService buyerService;

    @PostMapping("/buyer-upload")
    public ResponseEntity<Map<String,Object>> uploadBuyerProfile(@ModelAttribute BuyerRequestDto buyerRequestDto) throws IOException {

        Long buyerId = buyerService.saveBuyerProfile(buyerRequestDto);

        Map<String,Object> response = new HashMap<>();
        response.put("message","프로필 등록 완료");
        response.put("buyerId",buyerId);

        return ResponseEntity.ok(response);
    }
}
