package com.seokhyeon2356.farmlandmatchingbe.farmland.dto;

import com.seokhyeon2356.farmlandmatchingbe.farmland.entity.Farmland;

public record FarmlandListRes(
        String landName,
        String landCrop,
        String landAddress,
        Integer landArea,
        Integer landPrice
) {
    public static FarmlandListRes from(Farmland f) {
        return new FarmlandListRes(
                f.getLandName(), f.getLandCrop(), f.getLandAddress(), f.getLandArea(), f.getLandPrice()
        );
    }
}
