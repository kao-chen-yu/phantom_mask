package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "sell_masks")
@ToString
public class SellMaskEntity implements Comparable<SellMaskEntity> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private long id;

    @Column(name = "maskName")
    private String maskName;

    @Column(name = "price")
    private double price;

    @Column(name = "number")
    private int number;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "pharmacy_id", referencedColumnName = "id")
    private PharmaciesEntity pharmacy;

    @Override
    public int compareTo(SellMaskEntity o) {

        return this.maskName.compareTo(o.getMaskName());
    }

}
