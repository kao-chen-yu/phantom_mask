package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.UserEntity;
import com.example.dto.response.BuyMaskResponse;
import com.example.dto.response.UserPurchaseInformationResponse;
import com.example.dto.response.UserResponse;

@Service
public interface UserService {

    public List<UserResponse> topUsers(Date from, Date to, int top);

    public UserPurchaseInformationResponse UsersPurchadseInformation(Date from, Date to);

    public BuyMaskResponse buyMask(String userName, String pharamacy,
            String maskName);

    public UserResponse covertToUserResponse(UserEntity userEntity);
}
