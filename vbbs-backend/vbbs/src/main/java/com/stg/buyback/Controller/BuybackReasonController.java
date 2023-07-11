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

import com.stg.buyback.entity.BuybackReason;
import com.stg.buyback.service.BuybackReasonService;


@CrossOrigin("*")
@RestController
@RequestMapping("buyback-reason")
public class BuybackReasonController {
	@Autowired
	private BuybackReasonService buybackReasonService;

	@PostMapping(value = "buybackReason", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BuybackReason> createBuybackReason(@RequestBody BuybackReason buybackReason) {
		return new ResponseEntity<BuybackReason>(buybackReasonService.createBuybackReason(buybackReason),
				HttpStatus.OK);
	}
	
	@GetMapping(value="getById/{id}")
	public ResponseEntity<BuybackReason> getBuybackReason(@PathVariable int id) {
		return ResponseEntity.status(HttpStatus.OK).body(buybackReasonService.getBuybackReasonDetailsById(id));
	}
	
	@GetMapping(value="getAll")
	public ResponseEntity<List<BuybackReason>> getAllBuybackReasons() {
		return ResponseEntity.status(HttpStatus.OK).body(buybackReasonService.getAllBuybackReasonsDetails());
	}
	
	@PutMapping(value="update/{id}")
	public ResponseEntity<BuybackReason> updateBuybackReason(@RequestBody BuybackReason buybackReason,@PathVariable int id){
		return ResponseEntity.status(HttpStatus.OK).body(buybackReasonService.updateBuybackreason(buybackReason, id));
	}

}
