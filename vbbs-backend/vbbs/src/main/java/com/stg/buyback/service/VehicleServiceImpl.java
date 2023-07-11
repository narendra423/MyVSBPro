package com.stg.buyback.service;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stg.buyback.entity.Vehicle;
import com.stg.buyback.repository.VehicleRepository;
import com.stg.exception.VbbsException;

@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;

	@Override
	public Vehicle createVehicle(Vehicle vehicle) {

		return vehicleRepository.save(vehicle);
	}
	
	@Override
	public Vehicle findByVin(BigInteger vin) {
		Vehicle vehicle = vehicleRepository.findByVin(vin);
		if (vehicle != null) {
			return vehicle;
		} else {
			throw new VbbsException("VIN not found,Please check the number you entered!");
		}

	}

	@Override
	public Vehicle vehicleDetails(int vehicleId) {
		Optional<Vehicle> vehicle = this.vehicleRepository.findById(vehicleId);
		if (vehicle.isPresent()) {
			return vehicle.get();
		} else {
			throw new VbbsException("No data with this Id");
		}
	}

	@Override
	public Vehicle updateVehicle(int vehicleId, Vehicle vehicle) {
		Optional<Vehicle> vehiDetails = vehicleRepository.findById(vehicleId);
		if (vehiDetails.isPresent()) {
			vehicle.setVehicleId(vehicleId);
			
		} else {

			throw new VbbsException("No data with this Id");
		}

		return vehicleRepository.save(vehicle);
	}

	

}
