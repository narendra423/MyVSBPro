package com.stg.buyback.service;

import java.math.BigInteger;

import com.stg.buyback.entity.Vehicle;

public interface VehicleService {

	public abstract Vehicle createVehicle(Vehicle vehicle);
	
	public abstract Vehicle findByVin(BigInteger vin);

	public abstract Vehicle vehicleDetails(int vehicleId);
	  
	public abstract Vehicle updateVehicle(int vehicleId, Vehicle vehicle);
	 
}
