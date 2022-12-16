package com.example.demo.service.impl;

import java.util.ArrayList;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.PharmaciesEntity;
import com.example.demo.entity.SellMaskEntity;
import com.example.demo.repoistory.PharmaciesRepoistory;
import com.example.demo.service.PharmaciesService;
import com.example.dto.response.OpeningHoursResponse;
import com.example.dto.response.PharmaciesResponse;
import com.example.dto.response.OpeningHoursResponse.DAY;

@Service
public class PharmaciesServiceImplement implements PharmaciesService {

    @Autowired
    private PharmaciesRepoistory pharmaciesRepoistory;

    @Override
    public List<PharmaciesResponse> openPharmacies(String day, int openTime) {

        List<PharmaciesEntity> pharmacies = (List<PharmaciesEntity>) pharmaciesRepoistory.findAll();

        List<PharmaciesEntity> openPharmacies = pharmacies.stream()
                .filter(pharmacy -> pharmacy.getOpeningHours().contains(day)).collect(Collectors.toList());

        List<PharmaciesResponse> openPharmaciesResult = new ArrayList<>();

        if (openTime != -1) {

            for (PharmaciesEntity open : openPharmacies) {
                OpeningHoursResponse openHour = setOpenHours(open.getOpeningHours()).stream()
                        .filter(predicate -> predicate.day.toString().equals(day)).collect(Collectors.toList()).get(0);

                if (openHour.isOpen(openTime))

                    openPharmaciesResult.add(covertToPharmaciesResponse(open));

            }
        } else {
            for (PharmaciesEntity open : openPharmacies) {
                openPharmaciesResult.add(covertToPharmaciesResponse(open));
            }
        }
        return openPharmaciesResult;

    }

    @Override
    public List<SellMaskEntity> getSoldPharmacy(String pharmacyName, String sortBy) {

        List<PharmaciesEntity> pharmacies = (List<PharmaciesEntity>) pharmaciesRepoistory.findAll();
        List<SellMaskEntity> maskResult = new ArrayList<>();

        if (sortBy.equals("name"))
            maskResult = pharmacies.stream().filter(pharmacy -> pharmacy.getName().equals(pharmacyName))
                    .collect(Collectors.toList()).get(0).getMasks().stream()
                    .sorted((p1, p2) -> p1.getMaskName().compareTo(p2.getMaskName())).collect(Collectors.toList());
        else if (sortBy.equals("price"))
            maskResult = pharmacies.stream().filter(pharmacy -> pharmacy.getName().equals(pharmacyName))
                    .collect(Collectors.toList()).get(0).getMasks().stream()
                    .sorted((p1, p2) -> Double.valueOf(p1.getPrice()).compareTo(Double.valueOf(p2.getPrice())))
                    .collect(Collectors.toList());

        return maskResult;
    }

    @Override
    public List<PharmaciesResponse> searchRangePharmacies(int minPrice, int maxPrice, String search) {

        List<PharmaciesEntity> pharmacies = (List<PharmaciesEntity>) pharmaciesRepoistory.findAll();

        List<PharmaciesResponse> pharmaciesResult = new ArrayList<>();

        for (PharmaciesEntity pharmacy : pharmacies) {

            String[] searchInformation = search.split(":");

            Long count = pharmacy.getMasks().stream()
                    .filter(predicate -> predicate.getPrice() > minPrice && predicate.getPrice() < maxPrice).count();

            if (searchInformation[0].equals("less")) {
                if (count < Long.valueOf(searchInformation[1]))
                    pharmaciesResult.add(covertToPharmaciesResponse(pharmacy));

            } else if (searchInformation[0].equals("greater")) {
                if (count > Long.valueOf(searchInformation[1]))
                    pharmaciesResult.add(covertToPharmaciesResponse(pharmacy));
            }
        }
        return pharmaciesResult;
    }

    @Override
    public List<PharmaciesResponse> searchPharmaciesInformations(String searchInfo,
            String searchBy) {

        List<PharmaciesEntity> pharmacies = (List<PharmaciesEntity>) pharmaciesRepoistory.findAll();

        List<PharmaciesResponse> pharmciesResult = new ArrayList<>();

        if (searchBy.equals("pharamcies")) {
            for (PharmaciesEntity pharmacy : pharmacies) {
                if (pharmacy.getName().contains(searchInfo)) {
                    pharmciesResult.add(covertToPharmaciesResponse(pharmacy));
                }
            }
        } else if (searchBy.equals("mask")) {
            for (PharmaciesEntity pharmacy : pharmacies) {
                if (pharmacy.getMasks().stream().anyMatch(predicate -> predicate.getMaskName().contains(searchInfo))) {
                    pharmciesResult.add(covertToPharmaciesResponse(pharmacy));
                }
            }
        }

        return pharmciesResult;
    }

    @Override
    public List<OpeningHoursResponse> setOpenHours(String openHourStr) {

        List<OpeningHoursResponse> openingHoursList = new ArrayList<>();

        String regex = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";
        Pattern pattern = Pattern.compile(regex);

        List<String> days = new ArrayList<>();
        List<Integer> hours = new ArrayList<>();

        String[] openInformations = openHourStr.replaceAll(",", "").split(" ");

        for (int i = 0; i < openInformations.length; i++) {

            if (isDay(openInformations[i]) && !openInformations[i + 1].equals("-")) {
                // System.out.println(openInformation);
                days.add(openInformations[i]);
            }

            else if (isDay(openInformations[i]) && openInformations[i + 1].equals("-")) {
                boolean cont = false;

                for (String day : dayList) {
                    if (day.equals(openInformations[i]))
                        cont = true;

                    if (cont)
                        days.add(day);

                    if (day.equals(openInformations[i + 2])) {
                        cont = false;
                        break;
                    }

                }

            } else if (pattern.matcher(openInformations[i]).find()) {
                hours.add(Integer.valueOf(openInformations[i].split(":")[0]));
            }
        }

        days.forEach(day -> {
            OpeningHoursResponse openHour = new OpeningHoursResponse(DAY.valueOf(day), hours.get(0), hours.get(1));
            openingHoursList.add(openHour);
        });

        return openingHoursList;
    }

    @Override
    public boolean isDay(String day) {
        return dayList.contains(day);
    }

    @Override
    public PharmaciesResponse covertToPharmaciesResponse(PharmaciesEntity pharmacy) {
        return PharmaciesResponse.builder().name(pharmacy.getName()).openingHours(pharmacy.getOpeningHours())
                .cashBalance(pharmacy.getCashBalance()).masks((pharmacy.getMasks())).build();
    }
}
