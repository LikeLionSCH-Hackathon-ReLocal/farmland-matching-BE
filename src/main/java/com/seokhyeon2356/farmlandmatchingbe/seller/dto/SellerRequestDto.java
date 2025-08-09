package com.seokhyeon2356.farmlandmatchingbe.seller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SellerRequestDto {

    private String sellerName;
    private int sellerYear;
    private String sellerNumber;
    private String sellerAddress;
    private int sellerLand;
}
