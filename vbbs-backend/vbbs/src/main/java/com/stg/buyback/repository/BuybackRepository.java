package com.stg.buyback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stg.buyback.entity.Buyback;



@Repository
public interface BuybackRepository extends JpaRepository<Buyback, Integer> {

}
