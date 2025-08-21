package com.seokhyeon2356.farmlandmatchingbe.ai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MatchRequestDto {
    BuyerDto buyerDto;
    List<FarmlandFeatureDto> farmlands;
}
