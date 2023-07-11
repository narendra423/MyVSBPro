package com.stg.buyback.Controller;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stg.buyback.dto.BuybackAll;

import com.stg.buyback.service.MainService;

@CrossOrigin("*")
@RequestMapping("buybackapp")
@RestController
public class mainController {
		
	@Autowired
	private MainService mainService;
	
	@GetMapping(value = "/{vin}/{dealerCode}")
	public ResponseEntity<BuybackAll> buybackDetails(@PathVariable BigInteger vin, @PathVariable int dealerCode) {
		return new ResponseEntity<BuybackAll>(mainService.getBuybackRelatedDetails(vin, dealerCode), HttpStatus.OK);
	}
	
	@PostMapping(value = "/")
	public ResponseEntity<BuybackAll> buybackDetails(@RequestBody BuybackAll BuybackAll ) {
		return new ResponseEntity<BuybackAll>(mainService.createAllDetails(BuybackAll), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/")
	public ResponseEntity<BuybackAll> buyback(@RequestBody BuybackAll BuybackAll ) {
		return new ResponseEntity<BuybackAll>(mainService.updateAllDetails(BuybackAll), HttpStatus.OK);
	}

}
