package com.example.demo.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.PharmaciesEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.entity.PharmaciesEntity.Mask;
import com.example.demo.entity.UserEntity.PurchaseHistory;
import com.example.demo.service.FileReaderServie;
import com.example.demo.service.PharmaciesService;
import com.example.demo.service.UserService;
import com.example.dto.response.PurchaseInfoResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserServiceImplement implements UserService {

    @Autowired
    private FileReaderServie fileReaderServie;

    @Override
    public List<UserEntity> topUsers(Date from, Date to, int top) {

        List<UserEntity> userList = fileReaderServie.userList;
        List<UserEntity> userResult = new ArrayList<>();

        Map<Long, UserEntity> map = new HashMap();

        for (UserEntity user : userList) {
            map.put(user.getPurchaseHistories().stream()
                    .filter(predicate -> predicate.getTransactionDate().after(from)
                            && predicate.getTransactionDate().before(to))
                    .count(), user);
        }

        return map.entrySet().stream()
                .sorted((e1, e2) -> e2.getKey().compareTo(e1.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new))
                .values().stream().collect(Collectors.toList()).subList(0, top);

    }

    @Override
    public List<PurchaseInfoResponse> UsersPurchadseInformation(Date from, Date to) {

        List<PurchaseHistory> purchaseHistory = new ArrayList<>();

        List<PurchaseInfoResponse> purchaseResult = new ArrayList<>();

        fileReaderServie.userList.forEach(user -> {
            purchaseHistory.addAll(user.getPurchaseHistories());
        });

        Map<String, List<PurchaseHistory>> purchaseMap = purchaseHistory.stream().filter(
                predicate -> predicate.getTransactionDate().after(from) && predicate.getTransactionDate().before(to))
                .collect(Collectors.groupingBy(PurchaseHistory::getMaskName));

        purchaseMap.forEach((key, purchase) -> {
            PurchaseInfoResponse purchaseResponse = new PurchaseInfoResponse();
            int total = 0;

            purchaseResponse.setName(key);
            purchaseResponse.setNumber(purchase.size());

            for (PurchaseHistory history : purchase)
                total += history.getTransactionAmount();

            purchaseResponse.setTotalPrice(total);

            purchaseResult.add(purchaseResponse);
        });

        // System.out.println(purchaseResult.toString());
        return purchaseResult;
    }

    @Override
    public List<UserEntity> buyMask(String userName, String pharamacy,
            String maskName) {

        List<UserEntity> userList = fileReaderServie.userList;
        UserEntity user = userList.stream().filter(predicate -> predicate.getName().equals(userName)).findAny().get();

        List<PharmaciesEntity> pharmacies = fileReaderServie.pharmaciesList;

        PharmaciesEntity buyPharmacy = pharmacies.stream()
                .filter(predicate -> predicate.getName().equals(pharamacy)
                        && predicate.getMasks().stream().anyMatch(mask -> mask.getName().equals(maskName)))
                .findFirst().get();

        if (buyPharmacy != null) {
            Mask buyMask = buyPharmacy.getMasks().stream().filter(predicate -> predicate.getName().endsWith(maskName))
                    .findAny().get();
            pharmacies.remove(buyPharmacy);
            buyPharmacy.setCashBalance(buyPharmacy.getCashBalance() + buyMask.getPrice());

            pharmacies.add(buyPharmacy);

            Date now = new Date(System.currentTimeMillis());

            PurchaseHistory history = new PurchaseHistory();
            history.setMaskName(maskName);
            history.setPharmacyName(pharamacy);
            history.setTransactionAmount(buyMask.getPrice());
            history.setTransactionDate(now);

            user.getPurchaseHistories().add(history);
        }

        return userList;
    }

}
