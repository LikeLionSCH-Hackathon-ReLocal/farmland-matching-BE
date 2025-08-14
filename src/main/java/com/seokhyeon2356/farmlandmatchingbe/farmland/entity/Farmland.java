package com.seokhyeon2356.farmlandmatchingbe.farmland.entity;

import com.seokhyeon2356.farmlandmatchingbe.seller.entity.Seller;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "farmland")
public class Farmland {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "land_id", nullable = false)
    private Long landId;

    @Column(name = "land_name")
    private String landName;

    @Column(name = "land_address")
    private String landAddress;

    @Column(name = "land_roadAddress")
    private String landRoadAddress;

    @Column(name = "land_number")
    private String landNumber;

    @Column(name = "land_crop")
    private String landCrop;

    @Column(name = "land_area(m^2)")
    private Integer landArea;

    @Column(nullable = false)
    private String soiltype;

    @Column(nullable = false)
    private String waterSource;

    @Column(name = "owner_name")
    private String ownerName;

    @Column(name = "owner_age")
    private Integer ownerAge;

    @Column(name = "owner_address")
    private String ownerAddress;

    @Column(name = "land_water")
    private String landWater;

    @Column(name = "land_elec")
    private String landElec;

    @Column(name = "land_machine")
    private String landMachine;

    @Column(name = "land_storage")
    private String landStorage;

    @Column(name = "land_house")
    private String landHouse;

    @Column(name = "land_fence")
    private String landFence;

    @Column(name = "land_road")
    private String landRoad;

    @Column(name = "land_wellRoad")
    private String landWellRoad;

    @Column(name = "land_bus")
    private String landBus;

    @Column(name = "land_car")
    private String landCar;

    @Column(name = "land_trade")
    private String landTrade;

    @Column(name = "land_match")
    private String landMatch;

    @Column(name = "land_price")
    private Integer landPrice;

    @Column(name = "land_when")
    private String landWhen;

    @Column(name = "land_why")
    private String landWhy;

    @Column(name = "land_coment")
    private String landComent;

    @CreatedDate
    @Column(name = "land_register_date")
    private LocalDateTime landRegisterDate;

    @Column(name = "land_register")
    private String landRegister;

    @Column(name = "land_cadastre")
    private String landCadastre;

    @Column(name = "land_certification")
    private String landCertification;

    @Column(name = "land_image")
    private String landImage;

    @OneToMany(mappedBy = "farmlandMatch")
    private List<MatchingInfo> matchingInfo =  new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sellerId")
    private Seller sellerFarmland;

}
