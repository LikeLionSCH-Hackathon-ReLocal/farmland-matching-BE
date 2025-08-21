package com.seokhyeon2356.farmlandmatchingbe.ai.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class TrustProfileDto {
    List<String> equipment = List.of();
    List<String> interestCrop = List.of();
    List<String> wantTrade = List.of();
    Integer budget;
    String wantPeriod;
}
