package com.seokhyeon2356.farmlandmatchingbe.ai.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class BuyerDto {
    Long buyerId;

    Double buyerLat;
    Double buyerLng;

    TrustProfileDto trustProfile;
}
