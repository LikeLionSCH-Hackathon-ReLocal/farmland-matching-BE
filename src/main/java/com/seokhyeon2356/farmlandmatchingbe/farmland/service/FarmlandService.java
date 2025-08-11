package com.seokhyeon2356.farmlandmatchingbe.farmland.service;

import com.seokhyeon2356.farmlandmatchingbe.commonEntity.BaseEntity;
import com.seokhyeon2356.farmlandmatchingbe.farmland.dto.FarmlandRequestDto;
import com.seokhyeon2356.farmlandmatchingbe.farmland.entity.Farmland;
import com.seokhyeon2356.farmlandmatchingbe.farmland.repository.FarmlandRepository;
import com.seokhyeon2356.farmlandmatchingbe.supabase.service.SupabaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FarmlandService extends BaseEntity {

    private final SupabaseService supabaseService;
    private final FarmlandRepository farmlandRepository;

    public Long saveFarmland(FarmlandRequestDto dto) throws IOException {
        // 1. Supabase에 파일 업로드
        String landRegisterUrl = supabaseService.uploadFile(dto.getLandRegister());
        String landCadastreUrl = supabaseService.uploadFile(dto.getLandCadastre());
        String landCertificationUrl = supabaseService.uploadFile(dto.getLandCertification());
        String landImageUrl = supabaseService.uploadFile(dto.getLandImage());

        // 2. DB 저장용 엔티티 변환
        Farmland farmland = Farmland.builder()
                .landName(dto.getLandName())
                .landAddress(dto.getLandAddress())
                .landRoadAddress(dto.getLandLoadAddress())
                .landNumber(dto.getLandNumber())
                .landCrop(dto.getLandCrop())
                .landArea(dto.getLandArea())
                .soiltype(dto.getSoiltype())
                .waterSource(dto.getWaterSource())
                .ownerName(dto.getOwnerName())
                .ownerAge(dto.getOwnerAge())
                .ownerAddress(dto.getOwnerAddress())
                .landWater(dto.getLandWater())
                .landElec(dto.getLandElec())
                .landMachine(dto.getLandMachine())
                .landStorage(dto.getLandStorage())
                .landHouse(dto.getLandHouse())
                .landFence(dto.getLandFence())
                .landRoad(dto.getLandRoad())
                .landWellRoad(dto.getLandWellRoad())
                .landBus(dto.getLandBus())
                .landCar(dto.getLandCar())
                .landTrade(dto.getLandTrade())
                .landMatch(dto.getLandMatch())
                .landPrice(dto.getLandPrice())
                .landWhen(dto.getLandWhen())
                .landWhy(dto.getLandWhy())
                .landComent(dto.getLandComent())
                .landRegister(landRegisterUrl)
                .landCadastre(landCadastreUrl)
                .landCertification(landCertificationUrl)
                .landImage(landImageUrl)
                .build();

        // 3. DB 저장
        farmlandRepository.save(farmland);

        return farmland.getLandId();
    }
}


