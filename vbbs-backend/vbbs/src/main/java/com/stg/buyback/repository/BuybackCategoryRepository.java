package com.stg.buyback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stg.buyback.entity.BuybackCategory;



@Repository
public interface BuybackCategoryRepository extends JpaRepository<BuybackCategory, Integer> {
	

}
