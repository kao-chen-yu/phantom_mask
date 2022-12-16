package com.example.demo.repoistory;

import java.util.Date;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.BuyHistoryEntity;

@Repository
public interface BuyHistoryRepository extends JpaRepository<BuyHistoryEntity, Long> {

    @Query(value = "SELECT count(*) as cont , sum(transaction_amount) as total FROM test.buy_history where transaction_date between :from and :to", nativeQuery = true)
    Map<String, Object> findAllPharmacies(Date from, Date to);

}
