package com.stg.buyback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stg.buyback.entity.BuybackCategory;
import com.stg.buyback.entity.BuybackReason;



@Repository
public interface BuybackReasonRepository extends JpaRepository<BuybackReason, Integer> {

	BuybackCategory save(BuybackCategory buybackCategory);

}
