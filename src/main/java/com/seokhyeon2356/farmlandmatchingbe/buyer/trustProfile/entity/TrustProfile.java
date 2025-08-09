package com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.entity;

import com.seokhyeon2356.farmlandmatchingbe.buyer.entitiy.Buyer;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.license.entity.License;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.suggest.entity.Suggest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trustProfile")
public class TrustProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trust_id")
    private Long trustId;

    @Column(length = 1000, name = "awards")
    private String awards;

    @Column(name = "motivation")
    private String motivation;

    @Column(name = "experience")
    private String experience;

    @Column(name = "want_trade", nullable = false)
    private String wantTrade;

    @Column(name = "presentation")
    private String presentation;

    @Column(name = "videoURL")
    private String videoURL;

    @Column(name = "sns")
    private String sns;

    @Column(name = "trust_score")
    private String trustScore;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id", nullable = false)
    private Buyer buyer;

    @OneToMany(mappedBy = "trustProfile", cascade = CascadeType.ALL)
    private List<License> license = new ArrayList<>();

    @OneToMany(mappedBy = "trustProfile", cascade = CascadeType.ALL)
    private List<Suggest> suggest = new ArrayList<>();
}
