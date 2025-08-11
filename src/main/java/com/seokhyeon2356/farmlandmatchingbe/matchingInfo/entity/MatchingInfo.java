package com.seokhyeon2356.farmlandmatchingbe.matchingInfo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "machingInfo")
public class MatchingInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchingId;
}
