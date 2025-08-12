package com.seokhyeon2356.farmlandmatchingbe.farmland.repository;

import com.seokhyeon2356.farmlandmatchingbe.farmland.entity.Farmland;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FarmlandRepository extends JpaRepository<Farmland, Long> {

    List<Farmland> findBySellerFarmland_SellerId(Long sellerId);
}
