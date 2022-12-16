package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.SellMaskEntity;
import com.example.demo.service.PharmaciesService;
import com.example.dto.response.PharmaciesResponse;
import com.example.dto.response.OpeningHoursResponse.DAY;

@RestController
public class PharmaciesController {

    @Autowired
    private PharmaciesService pharmaciesService;

    @GetMapping(value = "/openPharmacies")
    public List<PharmaciesResponse> getsPharmacies(@RequestParam DAY day,
            @RequestParam(defaultValue = "-1", required = false) int openTime) {

        return pharmaciesService.openPharmacies(day.toString(), openTime);

    }

    @GetMapping(value = "/soldPharmacies")
    public List<SellMaskEntity> getSoldPharmacies(@RequestParam String pharmacyName,
            @RequestParam String sortBy) {

        return pharmaciesService.getSoldPharmacy(pharmacyName, sortBy);

    }

    @GetMapping(value = "/searchRangePharmacies")
    public List<PharmaciesResponse> SearchRangePharmacies(@RequestParam int minPrice, @RequestParam int maxPrice,
            @RequestParam String search) {

        return pharmaciesService.searchRangePharmacies(minPrice, maxPrice, search);

    }

    @GetMapping(value = "/searchPharmacies")
    public List<PharmaciesResponse> searchPharmacies(@RequestParam String searchInfo, @RequestParam String searchBy) {

        return pharmaciesService.searchPharmaciesInformations(searchInfo, searchBy);

    }

}
