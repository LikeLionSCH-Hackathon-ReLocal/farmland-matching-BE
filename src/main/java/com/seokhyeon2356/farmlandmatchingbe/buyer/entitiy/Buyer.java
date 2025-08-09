package com.seokhyeon2356.farmlandmatchingbe.buyer.entitiy;

import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.entity.TrustProfile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "buyer")
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buyer_id")
    private Long buyerId;

    @Column(name = "buyer_name", nullable = false)
    private String buyerName;

    @Column(name = "buyer_age", nullable = false)
    private int buyerAge;

    @Column(name = "buyer_gender", nullable = false)
    private String buyerGender;

    @Column(name = "buyer_address", nullable = false)
    private String buyerAddress;

    @Column(name = "buyer_number", nullable = false)
    private String buyerNumber;

    @Column(name = "buyer_email", nullable = false)
    private String buyerEmail;

    @Column(name = "interest_crop", nullable = false)
    private String interestCrop;

    @Column(name = "buyer_skill")
    private String buyerSkill;

    @Column(name = "buyer_want", nullable = false)
    private String buyerWant;

    @OneToOne(mappedBy = "buyer", cascade = CascadeType.ALL)
    private TrustProfile trustProfile;

}
