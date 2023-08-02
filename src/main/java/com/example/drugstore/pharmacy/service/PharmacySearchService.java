package com.example.drugstore.pharmacy.service;

import com.example.drugstore.pharmacy.cache.PharmacyRedisTemplateService;
import com.example.drugstore.pharmacy.dto.PharmacyDto;
import com.example.drugstore.pharmacy.entity.Pharmacy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PharmacySearchService {


    private final PharmacyRepositoryService pharmacyRepositoryService;
    private final PharmacyRedisTemplateService pharmacyRedisTemplateService;
    public List<PharmacyDto> searchPharmacyDtoList(){
        //redis
        List<PharmacyDto> pharmacyDtoList = pharmacyRedisTemplateService.findAll();
        if(!pharmacyDtoList.isEmpty()) return pharmacyDtoList;


        //db
        return pharmacyRepositoryService.findAll()
                .stream()
                .map(entity -> converToPharmacyDto(entity))
                .collect(Collectors.toList());
    }

    private PharmacyDto converToPharmacyDto(Pharmacy pharmacy){
        return PharmacyDto.builder()
                .id(pharmacy.getId())
                .pharmacyAddress(pharmacy.getPharmacyAddress())
                .pharmacyName(pharmacy.getPharmacyAddress())
                .latitude(pharmacy.getLatitude())
                .longitude(pharmacy.getLongitude())
                .build();
    }
}
