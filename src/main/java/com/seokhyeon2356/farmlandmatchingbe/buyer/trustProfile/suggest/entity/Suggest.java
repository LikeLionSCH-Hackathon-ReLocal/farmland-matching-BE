package com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.suggest.entity;

import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.entity.TrustProfile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "suggest")
public class Suggest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "suggest_id")
    private Long suggestId;

    @Column(name = "suggest_name", nullable = false)
    private String suggestName;

    @Column(name = "suggest_relationship", nullable = false)
    private String suggestRelationship;

    @Column(name = "suggest_number", nullable = false)
    private String suggestNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trust_id")
    private TrustProfile trustProfile;
}
