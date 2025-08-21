package com.seokhyeon2356.farmlandmatchingbe.ai.dto;

import lombok.Data;

import java.util.List;

@Data
public class AiScoreResponse {
    List<AiScoreItem> results;
}
