package com.seokhyeon2356.farmlandmatchingbe.farmland.service;

import com.seokhyeon2356.farmlandmatchingbe.commonEntity.BaseEntity;
import com.seokhyeon2356.farmlandmatchingbe.farmland.dto.*;
import com.seokhyeon2356.farmlandmatchingbe.farmland.entity.Farmland;
import com.seokhyeon2356.farmlandmatchingbe.farmland.repository.FarmlandRepository;
import com.seokhyeon2356.farmlandmatchingbe.farmland.dto.MatchStatus;
import com.seokhyeon2356.farmlandmatchingbe.farmland.entity.MatchingInfo;
import com.seokhyeon2356.farmlandmatchingbe.farmland.repository.MatchingInfoRepository;
import com.seokhyeon2356.farmlandmatchingbe.seller.entity.Seller;
import com.seokhyeon2356.farmlandmatchingbe.seller.repository.SellerRepository;
import com.seokhyeon2356.farmlandmatchingbe.supabase.service.SupabaseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FarmlandService extends BaseEntity {

    private final SupabaseService supabaseService;
    private final FarmlandRepository farmlandRepository;
    private final SellerRepository sellerRepository;
    private final MatchingInfoRepository matchingInfoRepository;

    public Long saveFarmland(Long sellerId, FarmlandRequestDto dto) throws IOException {

        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new IllegalArgumentException("판매자를 찾을 수 없습니다: " + sellerId));
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
                .sellerFarmland(seller)
                .build();

        // 3. DB 저장
        farmlandRepository.save(farmland);

        return farmland.getLandId();
    }

    //농지 정보 수정
    @Transactional
    public void updateFarmland(Long sellerId, Long landId, FarmlandUpdateReq req) {
        Farmland f = farmlandRepository.findById(landId)
                .orElseThrow(() -> new IllegalArgumentException("농지를 찾을 수 없습니다. landId=" + landId));
        ensureOwner(f, sellerId);

        // 예시로 몇 개만 반영 (네 DTO에 맞춰 추가)
        if (req.landName() != null) f.setLandName(req.landName());
        if (req.landAddress() != null) f.setLandAddress(req.landAddress());
        if (req.landCrop() != null) f.setLandCrop(req.landCrop());
        if (req.landArea() != null) f.setLandArea(req.landArea());
        // ... 기타 필드들
    }

    //농지 삭제
    @Transactional
    public void deleteFarmland(Long sellerId, Long landId) {
        // 1) 해당 landId의 농지 엔티티 조회
        Farmland f = farmlandRepository.findById(landId)
                .orElseThrow(() -> new IllegalArgumentException("농지를 찾을 수 없습니다. landId=" + landId));

        // 2) 삭제하려는 사람이 소유자인지 검증
        ensureOwner(f, sellerId);

        // 3) 엔티티 삭제
        farmlandRepository.delete(f);
    }

    //신청 수락
    @Transactional
    public void acceptApplicant(Long sellerId, Long landId, Long buyerId) {
        Farmland f = farmlandRepository.findById(landId)
                .orElseThrow(() -> new IllegalArgumentException("농지를 찾을 수 없습니다. landId=" + landId));
        ensureOwner(f, sellerId);

        MatchingInfo mine = matchingInfoRepository
                .findDetailBuyerInfoAndTrustAndLicensesAndSuggests(landId, buyerId)
                .orElseThrow(() -> new IllegalArgumentException("신청 정보를 찾을 수 없습니다."));

        mine.setMatchStatus(MatchStatus.IN_PROGRESS);
    }

    //신청 거절
    @Transactional
    public void rejectApplicant(Long sellerId, Long landId, Long buyerId) {
        Farmland f = farmlandRepository.findById(landId)
                .orElseThrow(() -> new IllegalArgumentException("농지를 찾을 수 없습니다. landId=" + landId));
        ensureOwner(f, sellerId);

        MatchingInfo mi = matchingInfoRepository
                .findDetailBuyerInfoAndTrustAndLicensesAndSuggests(landId, buyerId)
                .orElseThrow(() -> new IllegalArgumentException("신청 정보를 찾을 수 없습니다."));

        mi.setMatchStatus(MatchStatus.REJECTED);
    }

    //농지 상세 보기 (신청자 목록까지 보이기)
    @Transactional
    public List<FarmlandListRes> getFarmlandsBySeller(Long sellerId) {
        return farmlandRepository.findAllFarmlandByseller(sellerId);
    }

    public FarmlandDetailRes getFarmlandDetail(Long sellerId, Long landId) {
        Farmland f = farmlandRepository.findById(landId)
                .orElseThrow(() -> new IllegalArgumentException("농지를 찾을 수 없습니다. landId=" + landId));

        // (선택) 소유자 검증
        ensureOwner(f, sellerId);

        List<MatchingInfo> list = matchingInfoRepository.findAllBuyerByLandId(landId);

        // 화면 상단에 보여줄 대표 상태 선택 로직
        MatchStatus topStatus =
                list.stream().anyMatch(mi -> mi.getMatchStatus() == MatchStatus.IN_PROGRESS)
                        ? MatchStatus.IN_PROGRESS
                        : MatchStatus.WAITING;                       // 없으면 디폴트

        return FarmlandDetailRes.of(f, topStatus, list);
    }

    //신청자 상세보기
    public BuyerDetailRes getApplicantDetail(Long sellerId, Long landId, Long buyerId) {
        // (선택) landId 소유 검증을 위해 우선 Farmland만 가볍게 읽어 확인해도 됨
        Farmland f = farmlandRepository.findById(landId)
                .orElseThrow(() -> new IllegalArgumentException("농지를 찾을 수 없습니다. landId=" + landId));
        ensureOwner(f, sellerId);

        MatchingInfo mi = matchingInfoRepository
                .findDetailBuyerInfoAndTrustAndLicensesAndSuggests(landId, buyerId)
                .orElseThrow(() -> new IllegalArgumentException("신청 정보를 찾을 수 없습니다."));

        return BuyerDetailRes.of(mi);
    }

    //소유자 인증?
    private void ensureOwner(Farmland f, Long sellerId) {
        if (f.getSellerFarmland() == null || !f.getSellerFarmland().getSellerId().equals(sellerId)) {
            throw new IllegalArgumentException("해당 농지의 소유자가 아닙니다.");
        }
    }
}


