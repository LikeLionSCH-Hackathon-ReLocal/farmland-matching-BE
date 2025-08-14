package com.seokhyeon2356.farmlandmatchingbe.buyer.service;

import com.seokhyeon2356.farmlandmatchingbe.buyer.dto.BuyerRequestDto;
import com.seokhyeon2356.farmlandmatchingbe.buyer.dto.BuyerResDto;
import com.seokhyeon2356.farmlandmatchingbe.buyer.entitiy.Buyer;
import com.seokhyeon2356.farmlandmatchingbe.buyer.repository.BuyerRepository;
import com.seokhyeon2356.farmlandmatchingbe.supabase.service.SupabaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class BuyerService {

    private final SupabaseService supabaseService;
    private final BuyerRepository buyerRepository;

    public Long saveBuyerProfile(BuyerRequestDto buyerRequestDto) throws IOException {

        String buyerImageUrl = supabaseService.uploadFile(buyerRequestDto.getBuyerImage());

        Buyer buyer = Buyer.builder()
                .buyerName(buyerRequestDto.getBuyerName())
                .buyerAge(buyerRequestDto.getBuyerAge())
                .buyerGender(buyerRequestDto.getBuyerGender())
                .buyerAddress(buyerRequestDto.getBuyerAddress())
                .buyerNumber(buyerRequestDto.getBuyerNumber())
                .buyerEmail(buyerRequestDto.getBuyerEmail())
                .buyerImage(buyerImageUrl)
                .build();

        buyerRepository.save(buyer);

        return buyer.getBuyerId();
    }

    public BuyerResDto getBuyerProfile(Long buyerId) {

        Buyer buyer = buyerRepository.findById(buyerId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다."));

        return new BuyerResDto(buyer);
    }
}
