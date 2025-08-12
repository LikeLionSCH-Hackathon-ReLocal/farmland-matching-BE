package com.seokhyeon2356.farmlandmatchingbe.buyer.entitiy;

import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.entity.TrustProfile;
import com.seokhyeon2356.farmlandmatchingbe.farmland.entity.Farmland;
import jakarta.persistence.*;
import lombok.*;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "land_id")
    Farmland farmland;
}
