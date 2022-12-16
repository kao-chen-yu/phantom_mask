package com.example.dto.response;

import java.util.List;

import com.example.demo.entity.SellMaskEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PharmaciesResponse implements Comparable<PharmaciesResponse> {

    private String name;
    private double cashBalance;
    private String openingHours;

    private List<SellMaskEntity> masks;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Mask implements Comparable<Mask> {
        private String name;
        private double price;

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return this.price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        @Override
        public int compareTo(Mask o) {
            // TODO Auto-generated method stub
            return this.name.compareTo(o.getName());
        }

    }

    @Override
    public int compareTo(PharmaciesResponse o) {
        // TODO Auto-generated method stub
        return this.name.compareTo(o.getName());
    }
}
