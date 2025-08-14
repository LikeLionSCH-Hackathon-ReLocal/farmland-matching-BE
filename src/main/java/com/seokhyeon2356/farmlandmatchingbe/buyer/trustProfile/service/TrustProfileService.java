package com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.service;

import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.dto.TrustProfileReq;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.entity.TrustProfile;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.repository.TrustProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrustProfileService {

    private final TrustProfileRepository trustProfileRepository;

    public Long createTrustProfile(TrustProfileReq trustProfileReq) {

        TrustProfile trustProfile = TrustProfile.builder()
                .interestCrop(trustProfileReq.getInterestCrop())
                .equipment(trustProfileReq.getEquipment())
                .wantTrade(trustProfileReq.getWantTrade())
                .awards(trustProfileReq.getAwards())
                .experience(trustProfileReq.getExperience())
                .oneIntroduction(trustProfileReq.getOneIntroduction())
                .introduction(trustProfileReq.getIntroduction())
                .videoURL(trustProfileReq.getVideoURL())
                .sns(trustProfileReq.getSns())
                .personal(trustProfileReq.getPersonal())
                .build();

        trustProfileRepository.save(trustProfile);

        return trustProfile.getTrustId();
    }
}
