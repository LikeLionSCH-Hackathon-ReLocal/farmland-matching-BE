package com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.dto;

public record TrustScore(
        int total,
        ItemRow license,
        ItemRow suggest,
        ItemRow sns,
        ItemRow awards,
        ItemRow oneIntroduction,
        ItemRow introduction
) {
}
