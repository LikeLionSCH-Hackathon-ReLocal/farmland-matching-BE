package com.seokhyeon2356.farmlandmatchingbe.farmland.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class FarmlandRequestDto {

    private String landName;
    private String landAddress;
    private String landLoadAddress;
    private Double landLat;
    private Double landLng;
    private String landNumber;
    private String landCrop;
    private Integer landArea;
    private String soiltype;
    private String waterSource;
    private String ownerName;
    private Integer ownerAge;
    private String ownerAddress;
    private String landWater;
    private String landElec;
    private String landMachine;
    private String landStorage;
    private String landHouse;
    private String landFence;
    private String landRoad;
    private String landWellRoad;
    private String landBus;
    private String landCar;
    private String landTrade;
    private String landMatch;
    private Integer landPrice;
    private String landWhen;
    private String landWhy;
    private String landComent;
    private MultipartFile landRegister;
    private MultipartFile landCadastre;
    private MultipartFile landCertification;
    private MultipartFile landImage;
}
