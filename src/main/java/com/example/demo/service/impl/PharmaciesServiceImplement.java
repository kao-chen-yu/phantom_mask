package com.example.demo.service.impl;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.OpeningHoursEntity;
import com.example.demo.entity.PharmaciesEntity;
import com.example.demo.entity.PharmaciesEntity.Mask;
import com.example.demo.entity.OpeningHoursEntity.DAY;
import com.example.demo.service.FileReaderServie;
import com.example.demo.service.PharmaciesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PharmaciesServiceImplement implements PharmaciesService {

    @Autowired
    private FileReaderServie fileReaderServie;

    @Override
    public List<PharmaciesEntity> openPharmacies(String day, int openTime) {

        List<PharmaciesEntity> pharmacies = fileReaderServie.pharmaciesList;

        List<PharmaciesEntity> openPharmacies = pharmacies.stream()
                .filter(pharmacy -> pharmacy.getOpeningHours().contains(day)).collect(Collectors.toList());

        List<PharmaciesEntity> openPharmaciesResult = new ArrayList<>();

        if (openTime != -1) {

            for (PharmaciesEntity open : openPharmacies) {
                OpeningHoursEntity openHour = open.getOpeningHoursList().stream()
                        .filter(predicate -> predicate.day.toString().equals(day)).collect(Collectors.toList()).get(0);

                if (openHour.isOpen(openTime))
                    openPharmaciesResult.add(open);

            }
        } else {
            openPharmaciesResult = openPharmacies;
        }
        return openPharmaciesResult;
    }

    @Override
    public List<Mask> getPharmacySold(String pharmacyName, String sortBy) {

        List<PharmaciesEntity> pharmacies = fileReaderServie.pharmaciesList;
        List<Mask> maskResult = new ArrayList<>();

        if (sortBy.equals("name"))
            maskResult = pharmacies.stream().filter(pharmacy -> pharmacy.getName().equals(pharmacyName))
                    .collect(Collectors.toList()).get(0).getMasks().stream()
                    .sorted((p1, p2) -> p1.getName().compareTo(p2.getName())).collect(Collectors.toList());
        else if (sortBy.equals("price"))
            maskResult = pharmacies.stream().filter(pharmacy -> pharmacy.getName().equals(pharmacyName))
                    .collect(Collectors.toList()).get(0).getMasks().stream()
                    .sorted((p1, p2) -> Double.valueOf(p1.getPrice()).compareTo(Double.valueOf(p2.getPrice())))
                    .collect(Collectors.toList());
        return maskResult;
    }

    @Override
    public List<PharmaciesEntity> searchPharmacies(int minPrice, int maxPrice, String search) {

        List<PharmaciesEntity> pharmacies = fileReaderServie.pharmaciesList;

        List<PharmaciesEntity> pharmaciesResult = new ArrayList<>();

        for (PharmaciesEntity pharmacy : pharmacies) {

            String[] searchInformation = search.split(":");

            Long count = pharmacy.getMasks().stream()
                    .filter(predicate -> predicate.getPrice() > minPrice && predicate.getPrice() < maxPrice).count();

            if (searchInformation[0].equals("less")) {
                if (count < Long.valueOf(searchInformation[1]))
                    pharmaciesResult.add(pharmacy);

            } else if (searchInformation[0].equals("greater")) {
                if (count > Long.valueOf(searchInformation[1]))
                    pharmaciesResult.add(pharmacy);
            }
        }
        return pharmaciesResult;
    }

    @Override
    public List<PharmaciesEntity> searchPharmaciesInformations(String searchInfo,
            String searchBy) {

        List<PharmaciesEntity> pharmacies = fileReaderServie.pharmaciesList;

        List<PharmaciesEntity> pharmciesResult = new ArrayList<>();

        if (searchBy.equals("pharamcies")) {
            pharmciesResult = pharmacies.stream().filter(predicate -> predicate.getName().contains(searchInfo))
                    .collect(Collectors.toList());
        } else if (searchBy.equals("mask")) {
            for (PharmaciesEntity pharmacy : pharmacies) {
                if (pharmacy.getMasks().stream().anyMatch(predicate -> predicate.getName().contains(searchInfo))) {
                    pharmciesResult.add(pharmacy);
                }
            }
        }

        return pharmciesResult;
    }

    @Override
    public List<PharmaciesEntity> showAllPharmacies() {
        // TODO Auto-generated method stub
        return fileReaderServie.pharmaciesList;
    }

}
