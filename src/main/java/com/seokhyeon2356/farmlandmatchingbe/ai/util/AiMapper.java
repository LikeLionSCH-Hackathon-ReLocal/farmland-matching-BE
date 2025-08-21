package com.seokhyeon2356.farmlandmatchingbe.ai.util;

import com.seokhyeon2356.farmlandmatchingbe.ai.dto.BuyerDto;
import com.seokhyeon2356.farmlandmatchingbe.ai.dto.FarmlandFeatureDto;
import com.seokhyeon2356.farmlandmatchingbe.ai.dto.TrustProfileDto;
import com.seokhyeon2356.farmlandmatchingbe.buyer.entity.Buyer;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.entity.TrustProfile;
import com.seokhyeon2356.farmlandmatchingbe.farmland.entity.Farmland;

public class AiMapper {
    public static BuyerDto toBuyerDTO(Buyer b, TrustProfile tp) {
        TrustProfileDto tpDto = new TrustProfileDto();

        tpDto.setInterestCrop(tp.getInterestCrop());
        tpDto.setEquipment(tp.getEquipment());
        tpDto.setWantTrade(tp.getWantTrade());
        tpDto.setBudget(tp.getBudget());
        tpDto.setWantPeriod(tp.getWantPeriod());

        BuyerDto bDto = new BuyerDto();
        bDto.setBuyerId(b.getBuyerId());
        bDto.setBuyerLat(b.getBuyerLat());
        bDto.setBuyerLng(b.getBuyerLng());
        bDto.setTrustProfile(tpDto);

        return bDto;
    }

    public static FarmlandFeatureDto toFeature(Farmland f) {
        FarmlandFeatureDto d = new FarmlandFeatureDto();
        d.setLandId(f.getLandId()); // 실제 PK 사용
        d.setLandName(f.getLandName());
        d.setLandLat(f.getLandLat());
        d.setLandLng(f.getLandLng());
        d.setOwnerAge(f.getOwnerAge());
        d.setOwnerAddress(f.getOwnerAddress());
        d.setOwnerName(f.getOwnerName());
        d.setLandCrop(f.getLandCrop());
        d.setSoiltype(f.getSoiltype());
        d.setWaterSource(f.getWaterSource());
        d.setLandWater(f.getLandWater());
        d.setLandElec(f.getLandElec());
        d.setLandMachine(f.getLandMachine());
        d.setLandStorage(f.getLandStorage());
        d.setLandHouse(f.getLandHouse());
        d.setLandFence(f.getLandFence());
        d.setLandRoad(f.getLandRoad());
        d.setLandWellRoad(f.getLandWellRoad());
        d.setLandBus(f.getLandBus());
        d.setLandCar(f.getLandCar());
        d.setLandTrade(f.getLandTrade());
        d.setLandPrice(f.getLandPrice());
        d.setLandMatch(f.getLandMatch());
        d.setLandWhen(f.getLandWhen());
        d.setLandWhy(f.getLandWhy());
        d.setLandComent(f.getLandComent());
        return d;
    }
}
