package com.seokhyeon2356.farmlandmatchingbe.farmland.repository;

import com.seokhyeon2356.farmlandmatchingbe.farmland.entity.MatchingInfo; // ← 패키지 정확히!
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MatchingInfoRepository extends JpaRepository<MatchingInfo, Long> {

    // 농지 상세 화면의 신청자 목록 (N+1 방지: buyer를 fetch join)
    @Query("select mi from MatchingInfo mi " +
            "join fetch mi.buyerMatch b " +
            "where mi.farmlandMatch.landId = :landId " +
            "order by mi.matchingId desc")
    List<MatchingInfo> findAllBuyerByLandId(@Param("landId") Long landId);

    @Query("select distinct mi from MatchingInfo mi " +
            "join fetch mi.buyerMatch b " +           // ← buyerMatch (네 필드명)
            "left join fetch b.trustProfile tp " +    // ← buyerMatch.trustProfile
            "left join fetch tp.licenses lic " +      // ← trustProfile.licenses (List)
            "left join fetch tp.suggests sug " +
            "where mi.farmlandMatch.landId = :landId and b.buyerId = :buyerId")
    Optional<MatchingInfo> findDetailBuyerInfoAndTrustAndLicensesAndSuggests(
            @Param("landId") Long landId, @Param("buyerId") Long buyerId);

    boolean existsByFarmlandMatch_LandIdAndBuyerMatch_BuyerId(Long landId, Long buyerId);

    Optional<MatchingInfo> findByFarmlandMatch_LandIdAndBuyerMatch_BuyerId(Long landId, Long buyerId);
}
