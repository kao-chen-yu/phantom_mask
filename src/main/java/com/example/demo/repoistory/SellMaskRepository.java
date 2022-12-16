package com.example.demo.repoistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.SellMaskEntity;

@Repository
public interface SellMaskRepository extends JpaRepository<SellMaskEntity, Long> {

}
