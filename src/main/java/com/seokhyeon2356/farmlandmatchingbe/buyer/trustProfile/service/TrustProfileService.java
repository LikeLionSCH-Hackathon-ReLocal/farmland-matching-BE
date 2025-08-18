package com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.service;

import com.seokhyeon2356.farmlandmatchingbe.buyer.entity.Buyer;
import com.seokhyeon2356.farmlandmatchingbe.buyer.repository.BuyerRepository;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.dto.TrustProfileReq;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.dto.TrustProfileRes;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.entity.TrustProfile;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.repository.TrustProfileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrustProfileService {

    private final TrustProfileRepository trustProfileRepository;
    private final BuyerRepository buyerRepository;

    /**
     * 저장(Upsert):
     * - buyerId로 TrustProfile 존재하면 → 전체 필드 덮어쓰기(프론트에서 온 리스트가 정답)
     * - 없으면 → 새로 만들기 + 연관관계 세팅
     */
    @Transactional
    public Long saveTrustProfile(Long buyerId, TrustProfileReq req) {
        Buyer buyer = buyerRepository.findById(buyerId)
                .orElseThrow(() -> new IllegalArgumentException("Buyer not found: " + buyerId));

        TrustProfile tp = trustProfileRepository.findByBuyerId(buyerId)
                .orElseGet(() -> {
                    TrustProfile created = new TrustProfile();
                    // ★ 연관관계 주인(TrustProfile) 쪽 FK 세팅
                    created.setBuyerTrustProfile(buyer);
                    return created;
                });

        // === 프론트에서 보낸 값으로 '통째로' 덮어쓰기 ===
        tp.setInterestCrop(req.getInterestCrop());
        tp.setEquipment(req.getEquipment());
        tp.setWantTrade(req.getWantTrade());
        tp.setRentPeriod(req.getRentPeriod());
        tp.setOther(req.getOther());
        tp.setAwards(req.getAwards());
        tp.setExperience(req.getExperience());
        tp.setOneIntroduction(req.getOneIntroduction());
        tp.setIntroduction(req.getIntroduction());
        tp.setVideoURL(req.getVideoURL());
        tp.setSns(req.getSns());
        tp.setPersonal(req.getPersonal());
        //tp.setTrustScore(req.getTrustScore()); // 쓰는 중이면 포함

        // 새로 생성된 경우에는 persist, 기존이면 merge감지로 update
        TrustProfile saved = trustProfileRepository.save(tp);
        return saved.getTrustId();
    }

    /**
     * 조회: buyerId로 조회하여 화면에 다시 뿌리기
     */
    @Transactional
    public TrustProfileRes getTrustProfile(Long buyerId) {
        TrustProfile tp = trustProfileRepository.findByBuyerId(buyerId)
                .orElseThrow(() -> new IllegalArgumentException("해당 프로필(buyerId=" + buyerId + ")을 찾을 수 없습니다."));
        return new TrustProfileRes(tp);
    }
}
