package com.seokhyeon2356.farmlandmatchingbe.farmland.dto;

import lombok.Getter;
import lombok.Setter;

public record FarmlandUpdateReq(
        String landName,
        String landAddress,
        String landCrop,
        Integer landArea
        // ... 필요시 추가
) {}
