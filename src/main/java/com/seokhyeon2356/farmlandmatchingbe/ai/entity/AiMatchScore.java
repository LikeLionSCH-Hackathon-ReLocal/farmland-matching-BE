package com.seokhyeon2356.farmlandmatchingbe.ai.entity;

import com.seokhyeon2356.farmlandmatchingbe.buyer.entity.Buyer;
import com.seokhyeon2356.farmlandmatchingbe.farmland.entity.Farmland;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(
        name = "aiMatchScore",
        uniqueConstraints = @UniqueConstraint(columnNames = {"landId","buyerId"})
)
public class AiMatchScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aiMatchScoreId;

    private Double aiMatchScore;

    @Column(columnDefinition = "jsonb")
    private String aiScoreDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "landId")
    private Farmland landAiMatchScore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyerId")
    private Buyer buyerAiMatchScore;
}
