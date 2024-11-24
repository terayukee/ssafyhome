package com.ssafy.home.realestate.model.service;

import com.ssafy.home.realestate.model.RealEstateInfoDto;

import java.util.List;

public interface RealEstateService {
    List<RealEstateInfoDto> fetchRealEstateInfos(String space, String dealCategory, String aptSeq, String houseType);
}