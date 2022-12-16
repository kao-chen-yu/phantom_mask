package com.example.demo.controller;

import java.text.SimpleDateFormat;

import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.UserService;
import com.example.dto.request.BuyMaskRequest;
import com.example.dto.response.BuyMaskResponse;

import com.example.dto.response.UserPurchaseInformationResponse;
import com.example.dto.response.UserResponse;

@RestController
public class UserController {

        @Autowired
        private UserService userService;

        @GetMapping(value = "/topUsers")
        public List<UserResponse> getTopUsers(@RequestParam String from, @RequestParam String to,
                        @RequestParam int top)
                        throws ParseException, java.text.ParseException {

                SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");

                return userService.topUsers(dateParser.parse(from),
                                dateParser.parse(to), top);

        }

        @GetMapping(value = "/searcHistory")
        public UserPurchaseInformationResponse searcHistory(@RequestParam String from, @RequestParam String to)
                        throws ParseException, java.text.ParseException {

                SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");

                return userService.UsersPurchadseInformation(dateParser.parse(from),
                                dateParser.parse(to));

        }

        @PostMapping(value = "/buyMask")
        public BuyMaskResponse buyMask(@RequestBody BuyMaskRequest maskInfomation) {

                return userService.buyMask(maskInfomation.getUserName(),
                                maskInfomation.getPharamacy(), maskInfomation.getMaskName());

        }

}
