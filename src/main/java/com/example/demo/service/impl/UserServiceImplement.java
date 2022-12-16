package com.example.demo.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.BuyHistoryEntity;
import com.example.demo.entity.PharmaciesEntity;
import com.example.demo.entity.SellMaskEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repoistory.BuyHistoryRepository;
import com.example.demo.repoistory.PharmaciesRepoistory;
import com.example.demo.repoistory.SellMaskRepository;
import com.example.demo.repoistory.UserRepository;
import com.example.demo.service.UserService;
import com.example.dto.response.BuyMaskResponse;
import com.example.dto.response.UserPurchaseInformationResponse;
import com.example.dto.response.UserResponse;

@Service
public class UserServiceImplement implements UserService {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private BuyHistoryRepository buyHistoryRepository;

        @Autowired
        private PharmaciesRepoistory pharmaciesRepoistory;

        @Autowired
        private SellMaskRepository sellMaskRepository;

        @Override
        public List<UserResponse> topUsers(Date from, Date to, int top) {

                List<UserEntity> userList = userRepository.findAll();

                Map<Long, UserResponse> map = new HashMap();

                for (UserEntity user : userList) {
                        map.put(user.getPurchaseHistories().stream()
                                        .filter(predicate -> predicate.getTransactionDate().after(from)
                                                        && predicate.getTransactionDate().before(to))
                                        .count(), covertToUserResponse(user));
                }

                return map.entrySet().stream()
                                .sorted((e1, e2) -> e2.getKey().compareTo(e1.getKey()))
                                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1,
                                                LinkedHashMap::new))
                                .values().stream().collect(Collectors.toList()).subList(0, top);

        }

        @Override
        public UserPurchaseInformationResponse UsersPurchadseInformation(Date from, Date to) {

                Map<String, Object> result = buyHistoryRepository.findAllPharmacies(from, to);

                return new UserPurchaseInformationResponse(Integer.valueOf(result.get("cont").toString()),
                                Double.valueOf(result.get("total").toString()));

        }

        @Override
        public BuyMaskResponse buyMask(String userName, String pharamacy,
                        String maskName) {

                PharmaciesEntity pharmacyEntity = pharmaciesRepoistory.findByName(pharamacy);

                UserEntity user = userRepository.findByName(userName);

                SellMaskEntity sellMaskEntity = pharmacyEntity.getMasks().stream()
                                .filter(predicate -> predicate.getMaskName().equals(maskName)).findFirst().get();

                BuyMaskResponse response = new BuyMaskResponse();

                if (sellMaskEntity.getNumber() > 0) {

                        sellMaskEntity.setNumber(sellMaskEntity.getNumber() - 1);

                        user.setCashBalance(user.getCashBalance() - sellMaskEntity.getPrice());
                        pharmacyEntity.setCashBalance(pharmacyEntity.getCashBalance() +
                                        sellMaskEntity.getPrice());

                        BuyHistoryEntity history = BuyHistoryEntity.builder().maskName(maskName).pharmacyName(maskName)
                                        .transactionAmount(sellMaskEntity.getPrice()).user(user)
                                        .transactionDate(new Date()).build();

                        user.getPurchaseHistories().add(history);

                        userRepository.save(user);
                        sellMaskRepository.save(sellMaskEntity);
                        pharmaciesRepoistory.save(pharmacyEntity);
                        buyHistoryRepository.save(history);

                        response.setBuyResult("success");
                        response.setName(user.getName());
                        response.setCashBalance(user.getCashBalance());

                } else {

                        response.setBuyResult("fail");
                        response.setName(user.getName());
                        response.setCashBalance(user.getCashBalance());
                }

                return response;

        }

        @Override
        public UserResponse covertToUserResponse(UserEntity userEntity) {
                return UserResponse.builder().name(userEntity.getName())
                                .cashBalance(userEntity.getCashBalance())
                                .purchaseHistories(userEntity.getPurchaseHistories()).build();
        }

}
