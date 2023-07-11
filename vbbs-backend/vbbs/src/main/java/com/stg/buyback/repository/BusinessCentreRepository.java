package com.stg.buyback.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stg.buyback.entity.BusinessCentre;

@Repository
public interface BusinessCentreRepository extends JpaRepository<BusinessCentre, Integer> {

	public Optional<BusinessCentre> findByDecisionMakerId(int decisionMakerId);
	
	@Modifying
	@Transactional
	@Query(value = "SET FOREIGN_KEY_CHECKS=0", nativeQuery = true)
	public void setForeignConstraints();

}
