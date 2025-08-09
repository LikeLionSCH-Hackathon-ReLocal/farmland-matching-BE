package com.seokhyeon2356.farmlandmatchingbe.farmlands.farmlandImage.entity;

import com.seokhyeon2356.farmlandmatchingbe.farmlands.entity.Farmland;
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
@Table(name = "farmlandImage")
public class FarmlandImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id", nullable = false)
    private Long farmlandImageId;

    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farmland_id")
    private Farmland farmland;
}
