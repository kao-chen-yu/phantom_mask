package com.example.demo.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.PharmaciesEntity;
import com.example.demo.entity.SellMaskEntity;
import com.example.dto.response.OpeningHoursResponse;
import com.example.dto.response.PharmaciesResponse;

@Service
public interface PharmaciesService {

    List<String> dayList = Arrays.asList("Mon", "Tue", "Wed", "Thur", "Fri", "Sat", "Sun");

    public List<PharmaciesResponse> openPharmacies(String day, int openTime);

    public List<SellMaskEntity> getSoldPharmacy(String pharmacyName, String sortBy);

    public List<PharmaciesResponse> searchPharmaciesInformations(String searchInfo, String searchBy);

    public List<PharmaciesResponse> searchRangePharmacies(int minPrice, int maxPrice, String search);

    public List<OpeningHoursResponse> setOpenHours(String openHourStr);

    public boolean isDay(String day);

    public PharmaciesResponse covertToPharmaciesResponse(PharmaciesEntity pharmacy);

}
