package com.seokhyeon2356.farmlandmatchingbe.buyer.trustProfile.license.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class LicenseReq {
    Long licenseId;
    private String licenseName;
    private MultipartFile licenseFile;
}
