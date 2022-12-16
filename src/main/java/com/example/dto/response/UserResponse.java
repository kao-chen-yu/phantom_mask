package com.example.dto.response;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import com.example.demo.entity.BuyHistoryEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserResponse {

    private String name;
    private double cashBalance;

    private List<BuyHistoryEntity> purchaseHistories;

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
