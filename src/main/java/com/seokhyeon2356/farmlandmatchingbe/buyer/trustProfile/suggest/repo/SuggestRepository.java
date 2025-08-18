package com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.suggest.repo;

import com.seokhyeon2356.farmlandmatchingbe.buyer.entity.Buyer;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.suggest.entity.Suggest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SuggestRepository extends JpaRepository<Suggest, Long> {

    List<Suggest> findByBuyerSuggest_BuyerId(Long buyerId);
}
