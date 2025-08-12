package com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.license.entity;

import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.entity.TrustProfile;
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
@Table(name = "license")
public class License {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "license_id")
    private Long licenseId;

    @Column(name = "license_name")
    private String licenseName;

    @Column(name = "license_file") //s3 저장소에서 URL로 저장받아올거임
    private String licenseFile;

    //추후 추가 예정
    //@Column(name = "acquired_date")
    //private String acquiredDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trust_id")
    private TrustProfile trustProfileLicense;
}
