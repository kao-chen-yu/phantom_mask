package com.example.demo.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.json.simple.JSONArray;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserEntity;
import com.example.demo.entity.UserEntity.PurchaseHistory;
import com.example.dto.response.PurchaseInfoResponse;

@Service
public interface UserService {

    public List<UserEntity> topUsers(Date from, Date to, int top);

    public List<UserEntity> showUser(String user);

    public List<PurchaseInfoResponse> UsersPurchadseInformation(Date from, Date to);

    public List<UserEntity> buyMask(String userName, String pharamacy,
            String maskName);
}
