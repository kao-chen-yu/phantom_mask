package com.example.demo.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserEntity {

    private String name;
    private int cashBalance;

    private List<PurchaseHistory> purchaseHistories;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PurchaseHistory {
        private String pharmacyName;
        private String maskName;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date transactionDate;
        private double transactionAmount;
    }

}
