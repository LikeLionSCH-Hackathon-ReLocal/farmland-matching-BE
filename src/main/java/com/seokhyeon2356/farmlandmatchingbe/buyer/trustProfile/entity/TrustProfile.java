package com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.entity;

import com.seokhyeon2356.farmlandmatchingbe.buyer.entity.Buyer;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.license.entity.License;
import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.suggest.entity.Suggest;
import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trustProfile")
@Builder
public class TrustProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trustId;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private List<String> awards;

    private String experience;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private List<String> interestCrop;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private List<String> wantTrade;

    private String rentPeriod;
    private String other;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private List<String> equipment;

    private String oneIntroduction;
    private String introduction;
    private String videoURL;
    private String sns;
    private String personal;
    private String trustScore;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id")
    private Buyer buyerTrustProfile;
}
