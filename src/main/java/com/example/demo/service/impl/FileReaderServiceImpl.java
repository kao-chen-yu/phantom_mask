package com.example.demo.service.impl;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.OpeningHoursEntity;
import com.example.demo.entity.PharmaciesEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.entity.OpeningHoursEntity.DAY;
import com.example.demo.service.FileReaderServie;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class FileReaderServiceImpl implements FileReaderServie {

    @Override
    public void init() {
        // TODO Auto-generated method stub

        Object ob, ob2;
        try {
            ob = new JSONParser().parse(new FileReader("C:/Users/User/Documents/phantom_mask/data/pharmacies.json"));

            ob2 = new JSONParser().parse(new FileReader("C:/Users/User/Documents/phantom_mask/data/users.json"));
            // typecasting ob to JSONObject
            JSONArray pharmaciesInformations = (JSONArray) ob;
            readPharamacies(pharmaciesInformations);

            JSONArray users = (JSONArray) ob2;
            readUsers(users);

        } catch (IOException | ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // System.out.println(this.userList.toString());
    }

    @Override
    public void readUsers(JSONArray users) {

        List<UserEntity> userEntities = new ArrayList<>();

        users.forEach(user -> {

            UserEntity userEntity = new UserEntity();

            try {
                userEntity = objectMapper.readValue(user.toString(),
                        UserEntity.class);
            } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            this.userList.add(userEntity);
        });

    }

    @Override
    public void readPharamacies(JSONArray pharmaciesInformations) {

        List<PharmaciesEntity> pharmaciesEntities = new ArrayList<>();

        pharmaciesInformations.forEach(pharmaciesInformation -> {

            PharmaciesEntity pharmaciesEntity = new PharmaciesEntity();
            try {
                pharmaciesEntity = objectMapper.readValue(pharmaciesInformation.toString(),
                        PharmaciesEntity.class);
                pharmaciesEntity.setOpeningHoursList(setOpenHours(pharmaciesEntity.getOpeningHours()));
            } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            this.pharmaciesList.add(pharmaciesEntity);
        });

    }

    @Override
    public List<OpeningHoursEntity> setOpenHours(String openHourStr) {

        List<OpeningHoursEntity> openingHoursList = new ArrayList<>();

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
            OpeningHoursEntity openHour = new OpeningHoursEntity(DAY.valueOf(day), hours.get(0), hours.get(1));
            openingHoursList.add(openHour);
        });

        return openingHoursList;
    }

    public boolean isDay(String day) {
        return dayList.contains(day);
    }
}
