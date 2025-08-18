package com.seokhyeon2356.farmlandmatchingbe.farmland.dto;

import com.seokhyeon2356.farmlandmatchingbe.farmland.entity.Farmland;

public record FarmlandListRes(
        Long landId,
        String landName,
        String landCrop,
        String landAddress,
        Integer landArea,
        Integer landPrice,
        Double landLat,
        Double landLng
) {
    public static FarmlandListRes from(Farmland f) {
        return new FarmlandListRes(
                f.getLandId(), f.getLandName(), f.getLandCrop(), f.getLandAddress(), f.getLandArea(), f.getLandPrice(), f.getLandLat(), f.getLandLng()
        );
    }
}
