package com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TrustProfileUpdateReq {

    private List<String> interestCrop;
    private List<String> equipment;
    private List<String> wantTrade;
    private List<String> awards;
    private String experience;
    private String rentPeriod;
    private String other;
    private String oneIntroduction;
    private String introduction;
    private String videoURL;
    private String sns;
    private String personal;
}
