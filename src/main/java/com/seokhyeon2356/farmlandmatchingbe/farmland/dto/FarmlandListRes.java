package com.seokhyeon2356.farmlandmatchingbe.farmland.dto;

import com.seokhyeon2356.farmlandmatchingbe.farmland.entity.Farmland;
import com.seokhyeon2356.farmlandmatchingbe.farmland.entity.MatchingInfo;

public record FarmlandListRes(
        Long landId, String landName, String landAddress, String landCrop, Integer landArea, MatchStatus matchStatus
) {
    public static FarmlandListRes from(Farmland f, MatchStatus status) {
        return new FarmlandListRes(
                f.getLandId(),
                f.getLandName(),
                f.getLandAddress(),
                f.getLandCrop(),
                f.getLandArea(),
                status
        );
    }
}

