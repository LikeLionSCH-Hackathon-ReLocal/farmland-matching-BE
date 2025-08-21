package com.seokhyeon2356.farmlandmatchingbe.ai.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class FarmlandFeatureDto {
    Long landId;
    String landName;
    Double landLat;
    Double landLng;
    Integer ownerAge;
    String ownerAddress;
    String ownerName;
    String landCrop;
    String soiltype;
    String waterSource;
    Boolean landWater;
    Boolean landElec;
    Boolean landMachine;
    Boolean landStorage;
    Boolean landHouse;
    Boolean landFence;
    Boolean landRoad;
    Boolean landWellRoad;
    Boolean landBus;
    Boolean landCar;
    String landTrade;
    Integer landPrice;
    String landMatch;
    String landWhen;
    String landWhy;
    String landComent;
}
