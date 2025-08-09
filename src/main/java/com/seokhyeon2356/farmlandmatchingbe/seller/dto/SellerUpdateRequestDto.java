package com.seokhyeon2356.farmlandmatchingbe.seller.dto;

import lombok.Getter;

@Getter
public class SellerUpdateRequestDto {

    private String sellerName;
    private int sellerYear;
    private String sellerNumber;
    private String sellerAddress;
    private int sellerLand;
}
