package com.stg.buyback.Controller;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stg.buyback.entity.Vehicle;
import com.stg.buyback.service.VehicleService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "vehicle")
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;

	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle) {
		return new ResponseEntity<Vehicle>(vehicleService.createVehicle(vehicle), HttpStatus.OK);
	}

//	@GetMapping(value = "/get/{vehicleId}")
//	public ResponseEntity<Vehicle> vehicleDetails(@PathVariable int vehicleId) {
//		return new ResponseEntity<Vehicle>(vehicleService.vehicleDetails(vehicleId), HttpStatus.OK);
//	}
	
	@GetMapping(value = "/get/{vin}")
	public ResponseEntity<Vehicle> vehicleDetails(@PathVariable BigInteger vin) {
		return new ResponseEntity<Vehicle>(vehicleService.findByVin(vin), HttpStatus.OK);
	}

	@PutMapping(value = "/update/{vehicleId}")
	public ResponseEntity<Vehicle> updateVehicle(@PathVariable int vehicleId, @RequestBody Vehicle vehicle) {
		return new ResponseEntity<Vehicle>(vehicleService.updateVehicle(vehicleId, vehicle), HttpStatus.OK);
	}


}
