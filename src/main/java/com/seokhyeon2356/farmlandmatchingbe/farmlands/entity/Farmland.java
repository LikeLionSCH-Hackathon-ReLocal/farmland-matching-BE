package com.seokhyeon2356.farmlandmatchingbe.farmlands.entity;

import com.seokhyeon2356.farmlandmatchingbe.commonEntity.BaseEntity;
import com.seokhyeon2356.farmlandmatchingbe.farmlands.aiProfit.entity.AiProfit;
import com.seokhyeon2356.farmlandmatchingbe.farmlands.farmlandImage.entity.FarmlandImage;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "farmland")
public class Farmland extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "land_id", nullable = false)
    private Long landId;

    @Column(name = "land_name", nullable = false)
    private String landName;

    @Column(name = "land_address", nullable = false)
    private String landAddress;

    @Column(name = "land_roadAddress", nullable = true)
    private String landRoadAddress;

    @Column(name = "land_number", nullable = false)
    private String landNumber;

    @Column(name = "land_crop", nullable = false)
    private String landCrop;

    @Column(name = "land_area(m^2)", nullable = false)
    private String landArea;

    @Column(nullable = false)
    private String soiltype;

    @Column(nullable = false)
    private String waterSource;

    @Column(name = "owner_name", nullable = false)
    private String ownerName;

    @Column(name = "owner_age", nullable = false)
    private int ownerAge;

    @Column(name = "owner_address", nullable = false)
    private String ownerAddress;

    @Column(name = "land_water", nullable = false)
    private String landWater;

    @Column(name = "land_elec", nullable = false)
    private String landElec;

    @Column(name = "land_machine", nullable = false)
    private String landMachine;

    @Column(name = "land_storage")
    private String landStorage;

    @Column(name = "land_house")
    private String landHouse;

    @Column(name = "land_fence")
    private String landFence;

    @Column(name = "land_road", nullable = false)
    private String landRoad;

    @Column(name = "land_wellRoad", nullable = false)
    private String landWellRoad;

    @Column(name = "land_bus", nullable = false)
    private String landBus;

    @Column(name = "land_car", nullable = false)
    private String landCar;

    @Column(name = "land_trade", nullable = false)
    private String landTrade;

    @Column(name = "land_match")
    private String landMatch;

    @Column(name = "land_price")
    private int landPrice;

    @Column(name = "land_when")
    private String landWhen;

    @Column(name = "land_why")
    private String landWhy;

    @Column(name = "land_coment")
    private String landComent;

    @Column(name = "land_register", nullable = false)
    private String landRegister;

    @Column(name = "land_cadastre", nullable = false)
    private String landCadastre;

    @Column(name = "land_certification", nullable = false)
    private String landCertification;

    @OneToMany(mappedBy = "farmland", cascade = CascadeType.ALL)
    private List<FarmlandImage> farmlandImages = new ArrayList<>();

    @OneToOne(mappedBy = "farmland", cascade = CascadeType.ALL)
    private AiProfit aiProfit;


}
