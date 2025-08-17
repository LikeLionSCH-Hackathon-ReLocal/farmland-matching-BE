package com.seokhyeon2356.farmlandmatchingbe.buyer.entity;

import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.entity.TrustProfile;
import com.seokhyeon2356.farmlandmatchingbe.farmland.entity.MatchingInfo;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "buyer")
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buyer_id")
    private Long buyerId;

    @Column(name = "buyer_name", nullable = false)
    private String buyerName;

    @Column(name = "buyer_age", nullable = false)
    private Integer buyerAge;

    @Column(name = "buyer_gender", nullable = false)
    private String buyerGender;

    @Column(name = "buyer_address", nullable = false)
    private String buyerAddress;

    @Column(name = "buyer_number", nullable = false)
    private String buyerNumber;

    @Column(name = "buyer_email", nullable = false)
    private String buyerEmail;

    @Column(name = "buyer_image")
    private String buyerImage;

    @OneToOne(mappedBy = "buyerTrustProfile", cascade = CascadeType.ALL)
    private TrustProfile trustProfile;

    @OneToMany(mappedBy = "buyerMatch")
    private List<MatchingInfo> buyerInfo =  new ArrayList<>();


}
