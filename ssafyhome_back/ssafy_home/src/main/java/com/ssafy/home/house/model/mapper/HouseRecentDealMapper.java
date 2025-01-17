package com.ssafy.home.house.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.home.house.model.HouseRecentDealDto;

@Mapper
public interface HouseRecentDealMapper {
    
    // 매매 또는 전세 데이터를 가져오는 공통 메서드
    List<HouseRecentDealDto> getHousesInBounds(
            @Param("swLat") double swLat,
            @Param("swLng") double swLng,
            @Param("neLat") double neLat,
            @Param("neLng") double neLng,
            @Param("dealCategory") String dealCategory, String roomSize, 
            String approvalDate,
            String infoTableName,
            String targetTableName
    );

    // 월세 데이터를 가져오는 공통 메서드
    List<HouseRecentDealDto> getMonthlyHousesInBounds(
            @Param("swLat") double swLat,
            @Param("swLng") double swLng,
            @Param("neLat") double neLat,
            @Param("neLng") double neLng,
            String roomSize, 
            String approvalDate,
            String infoTableName,
            String targetTableName
    );
}
