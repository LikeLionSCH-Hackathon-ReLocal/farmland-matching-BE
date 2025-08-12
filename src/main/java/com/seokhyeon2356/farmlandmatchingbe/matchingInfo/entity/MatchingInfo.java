package com.seokhyeon2356.farmlandmatchingbe.matchingInfo.entity;

import com.seokhyeon2356.farmlandmatchingbe.farmland.entity.Farmland;
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

    private String status = "신청대기";

    private Integer recommendCount = 0;

    private String preferences;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "land_id")
    private Farmland farmlandMatchingInfo;

    // 추천수 증가 메서드
    public void increaseRecommendCount() {
        this.recommendCount += 1;
    }

    // 매칭 상태 변경 메서드
    public void changeStatusToMatching() {
        this.status = "매칭 대기중";
    }

    public void decreaseRecommendCount() {

        this.recommendCount -= 1;
    }
}
