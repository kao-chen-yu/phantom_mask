package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;
import org.springframework.stereotype.Service;

import com.example.demo.entity.OpeningHoursEntity;
import com.example.demo.entity.PharmaciesEntity;
import com.example.demo.entity.UserEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public interface FileReaderServie {

    public List<UserEntity> userList = new ArrayList<>();
    public List<PharmaciesEntity> pharmaciesList = new ArrayList<>();
    List<String> dayList = Arrays.asList("Mon", "Tue", "Wed", "Thur", "Fri", "Sat", "Sun");
    ObjectMapper objectMapper = new ObjectMapper();

    public void init();

    public void readUsers(JSONArray users);

    public void readPharamacies(JSONArray pharmaciesInformations);

    public List<OpeningHoursEntity> setOpenHours(String openHourStr);

}
