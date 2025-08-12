package com.seokhyeon2356.farmlandmatchingbe.buyer.repository;

import com.seokhyeon2356.farmlandmatchingbe.buyer.entitiy.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerReposiory extends JpaRepository<Buyer, Long> {
}
