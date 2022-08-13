package com.example.demo.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.OpeningHoursEntity;
import com.example.demo.entity.PharmaciesEntity;
import com.example.demo.entity.OpeningHoursEntity.DAY;
import com.example.demo.entity.PharmaciesEntity.Mask;
import com.example.demo.service.PharmaciesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class PharmaciesController {

    @Autowired
    private PharmaciesService pharmaciesService;

    @GetMapping(value = "/getOpenPharmacies")
    public List<PharmaciesEntity> getsPharmacies(@RequestParam DAY day,
            @RequestParam(defaultValue = "-1", required = false) int openTime) {

        return pharmaciesService.openPharmacies(day.toString(), openTime);

    }

    @GetMapping(value = "/getPharmaciesSold")
    public List<Mask> getPharmaciesSold(@RequestParam String pharmacyName,
            @RequestParam String sortBy) {

        return pharmaciesService.getPharmacySold(pharmacyName, sortBy);

    }

    @GetMapping(value = "/getPharmaciesSearch")
    public List<PharmaciesEntity> getPharmaciesSearch(@RequestParam int from, @RequestParam int to,
            @RequestParam String search) {

        return pharmaciesService.searchPharmacies(from, to, search);

    }

    @GetMapping(value = "/searchPharmacies")
    public List<PharmaciesEntity> searchPharmacies(@RequestParam String searchInfo, @RequestParam String searchBy) {

        return pharmaciesService.searchPharmaciesInformations(searchInfo, searchBy);

    }
}
