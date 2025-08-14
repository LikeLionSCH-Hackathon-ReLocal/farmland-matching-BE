package com.seokhyeon2356.farmlandmatchingbe.buyer.dto;

import com.seokhyeon2356.farmlandmatchingbe.buyer.entitiy.Buyer;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class BuyerResDto {

    private Long buyerId;
    private String buyerName;
    private Integer buyerAge;
    private String buyerGender;
    private String buyerAddress;
    private String buyerNumber;
    private String buyerEmail;
    private String buyerImageURL;

    public BuyerResDto(Buyer buyer) {

        this.buyerId = buyer.getBuyerId();
        this.buyerName = buyer.getBuyerName();
        this.buyerAge = buyer.getBuyerAge();
        this.buyerGender = buyer.getBuyerGender();
        this.buyerAddress = buyer.getBuyerAddress();
        this.buyerNumber = buyer.getBuyerNumber();
        this.buyerEmail = buyer.getBuyerEmail();
        buyerImageURL = buyer.getBuyerImage();
    }
}
