package com.seokhyeon2356.farmlandmatchingbe.seller.service;

import com.seokhyeon2356.farmlandmatchingbe.seller.dto.SellerRequestDto;
import com.seokhyeon2356.farmlandmatchingbe.seller.dto.SellerResponseDto;
import com.seokhyeon2356.farmlandmatchingbe.seller.dto.SellerUpdateRequestDto;
import com.seokhyeon2356.farmlandmatchingbe.seller.entity.Seller;
import com.seokhyeon2356.farmlandmatchingbe.seller.repository.SellerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    private final SellerRepository sellerRepository;

    public SellerService(SellerRepository sellerRepository) {

        this.sellerRepository = sellerRepository;
    }

    public Long createSeller(SellerRequestDto sellerRequestDto) {

        Seller seller = Seller.builder()
                .sellerName(sellerRequestDto.getSellerName())
                .sellerYear(sellerRequestDto.getSellerYear())
                .sellerNumber(sellerRequestDto.getSellerNumber())
                .sellerAddress(sellerRequestDto.getSellerAddress())
                .sellerLand(sellerRequestDto.getSellerLand())
                .build();

        sellerRepository.save(seller);

        return seller.getSellerId();
    }

    public SellerResponseDto getSellerById(Long sellerId) {
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new IllegalArgumentException("해당 판매자를 찾을 수 없습니다."));

        return new SellerResponseDto(seller);
    }

    @Transactional
    public void updateSeller(Long sellerId, SellerUpdateRequestDto dto) {
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new IllegalArgumentException("해당 판매자가 없습니다."));

        seller.setSellerName(dto.getSellerName());
        seller.setSellerYear(dto.getSellerYear());
        seller.setSellerNumber(dto.getSellerNumber());
        seller.setSellerAddress(dto.getSellerAddress());
        seller.setSellerLand(dto.getSellerLand());
    }
}
