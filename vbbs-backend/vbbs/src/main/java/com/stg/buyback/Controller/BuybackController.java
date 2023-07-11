package com.stg.buyback.Controller;

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

import com.stg.buyback.entity.Buyback;
import com.stg.buyback.service.BuybackService;

@CrossOrigin("*")
@RestController
@RequestMapping("buyback")
public class BuybackController {

	@Autowired
	private BuybackService buybackService;

	@PostMapping(value = "/buyback", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Buyback> createBuyback(@RequestBody Buyback buyback) {
		return new ResponseEntity<Buyback>(buybackService.createBuyback(buyback), HttpStatus.OK);
	}

	@GetMapping(value = "/buyback/{id}")
	public ResponseEntity<Buyback> getBuyBackById(@PathVariable int id) {
		return new ResponseEntity<Buyback>(buybackService.getBuybackById(id), HttpStatus.OK);
	}

	@PutMapping(value = "/buyback/update/{id}")
	public ResponseEntity<Buyback> updateBuybackById(@PathVariable int id, @RequestBody Buyback buyback) {
		return new ResponseEntity<Buyback>(this.buybackService.updateBuybackById(id, buyback), HttpStatus.OK);
	}
	
	@PutMapping(value = "/update")
	public ResponseEntity<Buyback> updateBuybackStatus(@RequestBody Buyback buyback) {
		return new ResponseEntity<Buyback>(this.buybackService.updateBuybackStatusById(buyback), HttpStatus.OK);
	}

}
