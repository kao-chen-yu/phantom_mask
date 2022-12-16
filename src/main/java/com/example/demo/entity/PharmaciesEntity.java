package com.example.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pharmacies")
public class PharmaciesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "cashBalance")
    private double cashBalance;

    @Column(name = "openingHours")
    private String openingHours;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pharmacy")
    @EqualsAndHashCode.Exclude
    private List<SellMaskEntity> masks;
}
