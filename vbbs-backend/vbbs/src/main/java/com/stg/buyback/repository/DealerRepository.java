package com.stg.buyback.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.stg.buyback.entity.Dealer;

@Repository
public interface DealerRepository extends JpaRepository<Dealer, Integer> {
	
	@Query(value = "select * from dealer where code=?", nativeQuery = true)
	public abstract Dealer findByDealerCode(int code);
	
	@Query(value = "select * from dealer INNER JOIN vehicle where vehicle.vin=?", nativeQuery = true)
	public abstract Dealer findDealerByVIN(BigInteger vin);

	public abstract Dealer existsByCode(int code);
}
