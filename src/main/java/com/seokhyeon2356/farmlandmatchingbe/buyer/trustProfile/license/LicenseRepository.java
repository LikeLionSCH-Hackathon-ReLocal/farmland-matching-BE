package com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.license;

import com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.license.entity.License;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicenseRepository extends JpaRepository<License,Long> {
}
