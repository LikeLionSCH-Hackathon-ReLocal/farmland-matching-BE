package com.seokhyeon2356.farmlandmatchingbe.matchingInfo.repository;

import com.seokhyeon2356.farmlandmatchingbe.farmland.entity.Farmland;
import com.seokhyeon2356.farmlandmatchingbe.matchingInfo.entity.MatchingInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MatchingInfoRepository extends JpaRepository<MatchingInfo, Long> {

    Optional<MatchingInfo> findByFarmlandMatchingInfo(Farmland farmland);
}
