package com.seokhyeon2356.farmlandmatchingbe.ai.dto;

import lombok.Data;

import java.util.Map;

@Data
public class AiScoreItem {
    Long landId;
    Double aiMatchScore;
    Map<String,Object> aiScoreDetail;
}
