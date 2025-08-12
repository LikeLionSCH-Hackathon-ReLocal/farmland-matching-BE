package com.seokhyeon2356.farmlandmatchingbe.matchingInfo.service;

import com.seokhyeon2356.farmlandmatchingbe.matchingInfo.entity.MatchingInfo;
import com.seokhyeon2356.farmlandmatchingbe.matchingInfo.repository.MatchingInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MatchingInfoService {

    private final MatchingInfoRepository matchingInfoRepository;

    /** 신청자 수락 시 상태 변경 */
    @Transactional
    public void acceptApplicant(Long matchingId) {
        MatchingInfo matchingInfo = matchingInfoRepository.findById(matchingId)
                .orElseThrow(() -> new IllegalArgumentException("매칭 정보를 찾을 수 없습니다."));

        matchingInfo.changeStatusToMatching();
    }

    /** 신청자 추가 시 추천 수 증가 */
    @Transactional
    public void addApplicant(Long matchingId) {
        MatchingInfo matchingInfo = matchingInfoRepository.findById(matchingId)
                .orElseThrow(() -> new IllegalArgumentException("매칭 정보를 찾을 수 없습니다."));

        matchingInfo.increaseRecommendCount();
    }
}
