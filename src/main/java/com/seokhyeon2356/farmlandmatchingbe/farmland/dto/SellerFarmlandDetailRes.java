package com.seokhyeon2356.farmlandmatchingbe.farmland.dto;

import com.seokhyeon2356.farmlandmatchingbe.farmland.entity.Farmland;
import com.seokhyeon2356.farmlandmatchingbe.farmland.entity.MatchingInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record SellerFarmlandDetailRes(
        Long landId,
        String landName,
        String landAddress,
        String landLoadAddress,
        String landNumber,
        Double landLat,
        Double landLng,
        String landCrop,
        Integer landArea,
        String soiltype,
        String waterSource,
        String ownerName,
        Integer ownerAge,
        String ownerAddress,
        String landWater,
        String landElec,
        String landMachine,
        String landStorage,
        String landHouse,
        String landFence,
        String landRoad,
        String landWellRoad,
        String landBus,
        String landCar,
        String landTrade,
        String landMatch,
        Integer landPrice,
        String landWhen,
        String landWhy,
        String landComent,
        String landImage,
        String landRegister,
        String landCadastre,
        String landCertification,
        List<ApplicantsSummary> applicants
) {

    public static SellerFarmlandDetailRes of(Farmland f, List<MatchingInfo> list) {

        List<ApplicantsSummary> applicants = list.stream()
                .map(ApplicantsSummary::from)
                .collect(Collectors.toCollection(ArrayList::new));

        return new SellerFarmlandDetailRes(
                f.getLandId(),
                f.getLandName(),
                f.getLandAddress(),
                f.getLandRoadAddress(),
                f.getLandNumber(),
                f.getLandLat(),
                f.getLandLng(),
                f.getLandCrop(),
                f.getLandArea(),
                f.getSoiltype(),
                f.getWaterSource(),
                f.getOwnerName(),
                f.getOwnerAge(),
                f.getOwnerAddress(),
                f.getLandWater(),
                f.getLandElec(),
                f.getLandMachine(),
                f.getLandStorage(),
                f.getLandHouse(),
                f.getLandFence(),
                f.getLandRoad(),
                f.getLandWellRoad(),
                f.getLandBus(),
                f.getLandCar(),
                f.getLandTrade(),
                f.getLandMatch(),
                f.getLandPrice(),
                f.getLandWhen(),
                f.getLandWhy(),
                f.getLandComent(),
                f.getLandImage(),
                f.getLandRegister(),
                f.getLandCadastre(),
                f.getLandCertification(),
                applicants
        );
    }
}
