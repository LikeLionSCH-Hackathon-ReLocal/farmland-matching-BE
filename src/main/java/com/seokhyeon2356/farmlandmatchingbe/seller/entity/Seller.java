package com.seokhyeon2356.farmlandmatchingbe.seller.entity;

import com.seokhyeon2356.farmlandmatchingbe.commonEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "seller",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"seller_name", "seller_year"})
        }
)
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seller_id")
    private Long sellerId;

    @Column(name = "seller_name", nullable = false)
    private String sellerName;
    @Column(name = "seller_year", nullable = false)
    private int sellerYear;
    @Column(name = "seller_number", nullable = false)
    private String sellerNumber;
    @Column(name = "seller_address", nullable = false)
    private String sellerAddress;
    @Column(name = "seller_land", nullable = false)
    private int sellerLand;
}
