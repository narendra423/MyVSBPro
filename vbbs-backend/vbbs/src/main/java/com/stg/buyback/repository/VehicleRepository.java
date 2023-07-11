package com.stg.buyback.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stg.buyback.entity.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

	public Vehicle findByVehicleId(int vehicleId);
	
	public abstract Vehicle findByVin(BigInteger vin);
}
