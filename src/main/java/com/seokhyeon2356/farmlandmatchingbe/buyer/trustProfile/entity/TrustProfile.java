package com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.entity;

import com.seokhyeon2356.farmlandmatchingbe.buyer.entitiy.Buyer;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.license.entity.License;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.suggest.entity.Suggest;
import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

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

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private List<String> awards;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private List<String> interestCrop;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private List<String> wantTrade;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private List<String> equipment;

    private String oneIntroduction;

    private String introduction;

    @Column(name = "videoURL")
    private String videoURL;

    @Column(name = "sns")
    private String sns;

    private String personal;

    @Column(name = "trust_score")
    private String trustScore;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id", nullable = false)
    private Buyer buyerTrustProfile;

    @OneToMany(mappedBy = "trustProfileLicense", cascade = CascadeType.ALL)
    private List<License> licenses = new ArrayList<>();

    @OneToMany(mappedBy = "trustProfileSuggest", cascade = CascadeType.ALL)
    private List<Suggest> suggests = new ArrayList<>();
}
