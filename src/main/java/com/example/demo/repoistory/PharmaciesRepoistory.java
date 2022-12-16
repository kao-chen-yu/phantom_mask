package com.example.demo.repoistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.PharmaciesEntity;

@Repository
public interface PharmaciesRepoistory extends CrudRepository<PharmaciesEntity, Long> {

    PharmaciesEntity findByName(String name);
}
