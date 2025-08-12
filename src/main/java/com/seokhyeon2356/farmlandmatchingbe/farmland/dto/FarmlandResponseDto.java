package com.seokhyeon2356.farmlandmatchingbe.farmland.dto;

import com.seokhyeon2356.farmlandmatchingbe.farmland.entity.Farmland;
import lombok.Getter;

@Getter
public class FarmlandResponseDto {

    private Long landId;
    private String landName;
    private String landAddress;
    private Integer landArea;  // m²
    private String status;     // "매칭 대기", "매칭 중" 등

    //public static FarmlandResponseDto from(Farmland f) {
        //return new FarmlandResponseDto(
                //f.getLandId(), f.getLandName(), f.getLandAddress(), f.getLandArea(), f.getStatus()
        //);
    //}
}
