package com.example.demo.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.chrono.BuddhistChronology;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import com.example.dto.request.BuyMaskRequest;
import com.example.dto.response.PurchaseInfoResponse;

@RestController
public class UserController {

        @Autowired
        private UserService userService;

        @GetMapping(value = "/getTopUsers")
        public List<UserEntity> getTopUsers(@RequestParam String from, @RequestParam String to,
                        @RequestParam int top)
                        throws ParseException, java.text.ParseException {

                SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");

                return userService.topUsers(dateParser.parse(from),
                                dateParser.parse(to), top);

        }

        @GetMapping(value = "/searcHistory")
        public List<PurchaseInfoResponse> searcHistory(@RequestParam String from, @RequestParam String to)
                        throws ParseException, java.text.ParseException {

                SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");

                return userService.UsersPurchadseInformation(dateParser.parse(from),
                                dateParser.parse(to));

        }

        @PostMapping(value = "/buyMask")
        public List<UserEntity> buyMask(@RequestBody BuyMaskRequest maskInfomation) {

                return userService.buyMask(maskInfomation.getUserName(),
                                maskInfomation.getPharamacy(), maskInfomation.getMaskName());

        }
}
