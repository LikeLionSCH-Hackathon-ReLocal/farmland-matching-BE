package com.seokhyeon2356.farmlandmatchingbe.farmland.controller;

import com.seokhyeon2356.farmlandmatchingbe.farmland.dto.FarmlandRequestDto;
import com.seokhyeon2356.farmlandmatchingbe.farmland.service.FarmlandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class FarmlandController {

    private final FarmlandService farmlandService;

    @PostMapping("/farmland-upload")
    public ResponseEntity<Map<String,Object>> createFarmland(@ModelAttribute FarmlandRequestDto dto) throws IOException {

        Long landId = farmlandService.saveFarmland(dto);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "농지 등록 완료");
        response.put("land_id", landId);

        return ResponseEntity.ok(response);
    }

}
