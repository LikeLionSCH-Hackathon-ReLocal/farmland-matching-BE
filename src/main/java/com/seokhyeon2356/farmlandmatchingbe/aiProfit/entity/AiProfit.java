package com.seokhyeon2356.farmlandmatchingbe.aiProfit.entity;

import com.seokhyeon2356.farmlandmatchingbe.farmland.entity.Farmland;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "aiProfit")
public class AiProfit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profit_id")
    private Long profitId;

    @Column(name = "yearly_profit", nullable = false)
    private String yearlyProfit;

    @Column(name = "yield", nullable = false)
    private String yield;

    @Column(name = "unit_price", nullable = false)
    private String unitPrice;

    @Column(name = "material", nullable = false)
    private String material;

    @Column(name = "labor", nullable = false)
    private String labor;

    @Column(name = "machine", nullable = false)
    private String machine;

    @Column(name = "net_profit", nullable = false)
    private String netProfit;
}
