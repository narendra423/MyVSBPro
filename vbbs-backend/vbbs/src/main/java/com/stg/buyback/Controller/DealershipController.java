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

import com.stg.buyback.entity.Dealer;
import com.stg.buyback.service.DealerService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "dealer")
public class DealershipController {

	@Autowired
	private DealerService dealershipService;

	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Dealer> createDealership(@RequestBody Dealer dealership) {
		return new ResponseEntity<Dealer>(dealershipService.createDealership(dealership), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/get/{vin}")
	public ResponseEntity<Dealer> getDealerByVin(@PathVariable("vin") BigInteger vin) {
		return new ResponseEntity<Dealer>(dealershipService.getDealerByVIN(vin), HttpStatus.OK);
	}

	@GetMapping(value = "/getByCode/{dealercode}")
	public ResponseEntity<Dealer> getDealerByCode(@PathVariable("dealercode") int code) {
		return new ResponseEntity<Dealer>(dealershipService.getDealerByCode(code), HttpStatus.OK);
	}

	@PutMapping(value = "/update/{id}")
	public ResponseEntity<Dealer> updateDealer(@PathVariable("id") int dealerId, @RequestBody Dealer dealer) {
		return new ResponseEntity<Dealer>(dealershipService.updateDealer(dealer, dealerId), HttpStatus.OK);
	}

	@GetMapping(value = "/getdeal/{dealerId}")
	public ResponseEntity<Dealer> getDealerById(@PathVariable("dealerId") int dealerId) {
		return new ResponseEntity<Dealer>(dealershipService.getDealerById(dealerId), HttpStatus.FOUND);
	}

}
