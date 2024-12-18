package com.ssafy.home.realestate.controller;

import com.ssafy.home.realestate.model.RealEstateInfoDto;
import com.ssafy.home.realestate.model.service.RealEstateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/realestate")
@CrossOrigin("*")
public class RealEstateController {

    private static final Logger logger = LoggerFactory.getLogger(RealEstateController.class);
    private final RealEstateService realEstateService;

    public RealEstateController(RealEstateService realEstateService) {
        this.realEstateService = realEstateService;
    }

    @GetMapping("/listby-space-dealtype")
    public List<RealEstateInfoDto> fetchRealEstateInfos(
            @RequestParam("aptSeq") String aptSeq,
            @RequestParam("space") String space,
            @RequestParam("dealCategory") String dealCategory,
            @RequestParam("houseType") String houseType) {

        // Debug log for incoming parameters
        logger.info("Received request with aptSeq: {}, space: {}, dealCategory: {}", aptSeq, space, dealCategory);

        // Check if parameters are null or empty
        if (aptSeq == null || aptSeq.isEmpty()) {
            logger.warn("aptSeq parameter is null or empty.");
        }
        if (space == null || space.isEmpty()) {
            logger.warn("space parameter is null or empty.");
        }
        if (dealCategory == null || dealCategory.isEmpty()) {
            logger.warn("dealCategory parameter is null or empty.");
        }

        // Fetch real estate infos
        List<RealEstateInfoDto> realestateList = realEstateService.fetchRealEstateInfos(space, dealCategory, aptSeq, houseType);

        // Debug log for the response
        if (realestateList == null || realestateList.isEmpty()) {
            logger.info("매물 조회 결과 없음 ");
        } else {            
            realestateList.forEach(realestate -> {
            	logger.debug("부동산 매물 : {}", realestate.toString());
            });
        }

        return realestateList;
    }
    
    @GetMapping("/getbyid")
    public RealEstateInfoDto getRealEstateById(@RequestParam("realestateId") String realestateId) {
        logger.info("Fetching real estate info for ID: {}", realestateId);

        // Fetch the real estate info by ID
        RealEstateInfoDto realEstateInfo = realEstateService.getRealEstateById(realestateId);

        if (realEstateInfo == null) {
            logger.warn("No real estate info found for ID: {}", realestateId);
        } else {
            logger.debug("Fetched Real Estate Info: {}", realEstateInfo);
        }

        return realEstateInfo;
    }

    @GetMapping("/getbyuserno")
    public List<RealEstateInfoDto> getRealEstateByUserNo(@RequestParam("registerUserNo") String registerUserNo) {
        logger.info("Fetching real estate info for userNo: {}", registerUserNo);

        List<RealEstateInfoDto> realestateList = realEstateService.getRealEstateByUserNo(registerUserNo);

        if (realestateList == null || realestateList.isEmpty()) {
            logger.warn("No real estate info found for userNo: {}", registerUserNo);
        } else {
            realestateList.forEach(realestate -> logger.info("Real estate for userNo {}: {}", registerUserNo, realestate.toString()));
        }

        return realestateList;
    }
    
    @PostMapping("/register")
    public String registerRealEstate(@RequestBody RealEstateInfoDto realEstateInfoDto) {
        logger.info("Registering new real estate: {}", realEstateInfoDto);

        try {
            realEstateService.registerRealEstate(realEstateInfoDto);
            logger.info("Real estate registered successfully.");
            return "매물이 성공적으로 등록되었습니다.";
        } catch (Exception e) {
            logger.error("Error occurred while registering real estate: ", e);
            throw new RuntimeException("매물 등록 중 오류가 발생했습니다.");
        }
    }

}
