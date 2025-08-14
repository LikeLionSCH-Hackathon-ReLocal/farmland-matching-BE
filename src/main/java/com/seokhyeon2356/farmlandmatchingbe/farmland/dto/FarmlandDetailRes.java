package com.seokhyeon2356.farmlandmatchingbe.farmland.dto;

import com.seokhyeon2356.farmlandmatchingbe.farmland.entity.Farmland;
import com.seokhyeon2356.farmlandmatchingbe.farmland.entity.MatchingInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record FarmlandDetailRes(
        Long landId,
        String landName,
        String landAddress,
        String landCrop,
        Integer landArea,
        String matchStatus,
        List<ApplicantsSummary> applicants
) {
    public static FarmlandDetailRes of(Farmland f, MatchStatus status, List<MatchingInfo> list) {

        List<ApplicantsSummary> applicants = list.stream()
                .map(ApplicantsSummary::from)
                .collect(Collectors.toCollection(ArrayList::new));

        return new FarmlandDetailRes(
                f.getLandId(),
                f.getLandName(),
                f.getLandAddress(),
                f.getLandCrop(),
                f.getLandArea(),
                status.label(),
                applicants
        );
    }
}
