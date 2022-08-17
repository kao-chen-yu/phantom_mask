package com.example.demo.service;

import java.util.List;

import org.json.simple.JSONArray;
import org.springframework.stereotype.Service;

import com.example.demo.entity.OpeningHoursEntity;
import com.example.demo.entity.PharmaciesEntity;
import com.example.demo.entity.PharmaciesEntity.Mask;

@Service
public interface PharmaciesService {

    public List<PharmaciesEntity> openPharmacies(String day, int openTime);

    public List<PharmaciesEntity> showAllPharmacies();

    public List<Mask> getPharmacySold(String pharmacyName, String sortBy);

    public List<PharmaciesEntity> searchPharmaciesInformations(String searchInfo, String searchBy);

    public List<PharmaciesEntity> searchPharmacies(int minPrice, int maxPrice, String search);

}
