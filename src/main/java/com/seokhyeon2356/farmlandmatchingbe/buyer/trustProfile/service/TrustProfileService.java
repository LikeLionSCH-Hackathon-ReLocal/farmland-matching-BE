package com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.service;

import com.seokhyeon2356.farmlandmatchingbe.buyer.entitiy.Buyer;
import com.seokhyeon2356.farmlandmatchingbe.buyer.repository.BuyerRepository;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.dto.TrustProfileReq;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.dto.TrustProfileRes;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.dto.TrustProfileUpdateReq;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.entity.TrustProfile;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.repository.TrustProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrustProfileService {

    private final TrustProfileRepository trustProfileRepository;
    private final BuyerRepository buyerRepository;

    public Long createTrustProfile(Long buyerId, TrustProfileReq trustProfileReq) {

        Buyer buyer = buyerRepository.findById(buyerId)
                .orElseThrow(() -> new IllegalArgumentException("Buyer not found: " + buyerId));


        TrustProfile trustProfile = TrustProfile.builder()
                .interestCrop(trustProfileReq.getInterestCrop())
                .equipment(trustProfileReq.getEquipment())
                .wantTrade(trustProfileReq.getWantTrade())
                .rentPeriod(trustProfileReq.getRentPeriod())
                .other(trustProfileReq.getOther())
                .awards(trustProfileReq.getAwards())
                .experience(trustProfileReq.getExperience())
                .oneIntroduction(trustProfileReq.getOneIntroduction())
                .introduction(trustProfileReq.getIntroduction())
                .videoURL(trustProfileReq.getVideoURL())
                .sns(trustProfileReq.getSns())
                .personal(trustProfileReq.getPersonal())
                .buyerTrustProfile(buyer)
                .build();

        trustProfileRepository.save(trustProfile);

        return trustProfile.getTrustId();
    }

    //신뢰프로필 정보 불러오기
    public TrustProfileRes getTrustProfile(Long buyerId) {

        TrustProfile tp = trustProfileRepository.findById(buyerId)
                .orElseThrow(() -> new IllegalArgumentException("해당 프로필을 찾을 수 없습니다."));

        return new TrustProfileRes(tp);
    }

    //신뢰프로필 정보 수정하기
    public void updateTrustProfile(Long buyerId, TrustProfileUpdateReq trustProfileUpdateReq) {

        TrustProfile tp = trustProfileRepository.findById(buyerId)
                .orElseThrow(() -> new IllegalArgumentException("해당 프로필을 찾을 수 없습니다."));

        if (trustProfileUpdateReq.getInterestCrop() != null) {
            tp.setInterestCrop(trustProfileUpdateReq.getInterestCrop());
        }
        if (trustProfileUpdateReq.getEquipment() != null) {
            tp.setEquipment(trustProfileUpdateReq.getEquipment());
        }
        if (trustProfileUpdateReq.getWantTrade() != null) {
            tp.setWantTrade(trustProfileUpdateReq.getWantTrade());
        }
        if (trustProfileUpdateReq.getRentPeriod() != null) {
            tp.setRentPeriod(trustProfileUpdateReq.getRentPeriod());
        }
        if (trustProfileUpdateReq.getOther() != null) {
            tp.setOther(trustProfileUpdateReq.getOther());
        }
        if (trustProfileUpdateReq.getAwards() != null) {
            tp.setAwards(trustProfileUpdateReq.getAwards());
        }
        if (trustProfileUpdateReq.getExperience() != null) {
            tp.setExperience(trustProfileUpdateReq.getExperience());
        }
        if (trustProfileUpdateReq.getOneIntroduction() != null) {
            tp.setOneIntroduction(trustProfileUpdateReq.getOneIntroduction());
        }
        if (trustProfileUpdateReq.getIntroduction() != null) {
            tp.setIntroduction(trustProfileUpdateReq.getIntroduction());
        }
        if (trustProfileUpdateReq.getVideoURL() != null) {
            tp.setVideoURL(trustProfileUpdateReq.getVideoURL());
        }
        if (trustProfileUpdateReq.getSns() != null) {
            tp.setSns(trustProfileUpdateReq.getSns());
        }
        if (trustProfileUpdateReq.getPersonal() != null) {
            tp.setPersonal(trustProfileUpdateReq.getPersonal());
        }
    }
}
