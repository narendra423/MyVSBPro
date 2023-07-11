package com.stg.buyback.Controller;


import java.util.List;

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

import com.stg.buyback.entity.BusinessCentre;
import com.stg.buyback.service.BusinessCentreService;


@CrossOrigin("*")
@RestController
@RequestMapping("business-centre")
public class BusinesscentreController {

	@Autowired
	private BusinessCentreService businessCentreService;

	@PostMapping(value = "/business", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BusinessCentre> businessCentre(@RequestBody BusinessCentre businessCentre) {
		return new ResponseEntity<BusinessCentre>(businessCentreService.createBusinessCentre(businessCentre),
				HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/business/{decisionMakerId}")
	public ResponseEntity<BusinessCentre> businessCentre(@PathVariable int decisionMakerId){
		return new ResponseEntity<BusinessCentre>(businessCentreService.getBusinessCentre(decisionMakerId),HttpStatus.OK);
	}
	
	@GetMapping(value = "/business")
	public ResponseEntity<List<BusinessCentre>> businessCentre(){
		return new ResponseEntity<List<BusinessCentre>>(businessCentreService.getAllBusinessCentres(),HttpStatus.OK);
	}
	
	@PutMapping(value = "/business/{businessId}")
	public ResponseEntity<BusinessCentre> businessCentre(@PathVariable int businessId, @RequestBody BusinessCentre businessCentre) {
		return new ResponseEntity<BusinessCentre>(businessCentreService.updateBusinessCentre(businessId, businessCentre),
				HttpStatus.OK);
	}

}
