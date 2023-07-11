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

import com.stg.buyback.entity.BuybackCategory;
import com.stg.buyback.service.BuybackCategoryService;

@CrossOrigin("*")
@RestController
@RequestMapping("buyback-category")
public class BuybackCategoryController {

	@Autowired
	private BuybackCategoryService buybackCategoryService;

	@PostMapping(value = "/category/{buybackreasonId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BuybackCategory> createBuybackCategory(@RequestBody BuybackCategory buybackCategory,@PathVariable int buybackreasonId) {
		return new ResponseEntity<BuybackCategory>(buybackCategoryService.createBuybackCategory(buybackCategory,buybackreasonId),
				HttpStatus.CREATED);
	}
	
	@GetMapping(value="readbyId/{buybackCategotyId}")
	public ResponseEntity<BuybackCategory> getById(@PathVariable int buybackCategotyId){
		return new ResponseEntity<BuybackCategory>(buybackCategoryService.getBuyId(buybackCategotyId),HttpStatus.OK);
	}
	
	@GetMapping(value="readAll")
	public ResponseEntity<List<BuybackCategory>> readAll(){
		return new ResponseEntity<List<BuybackCategory>>(buybackCategoryService.readAll(),HttpStatus.OK);
	}
	
	@PutMapping(value="updateById/{buybackCategotyId}")
	public ResponseEntity<BuybackCategory> updateById(@PathVariable int buybackCategotyId,@RequestBody BuybackCategory buybackCategory){
		return new ResponseEntity<BuybackCategory>(buybackCategoryService.updateById(buybackCategotyId, buybackCategory),HttpStatus.OK);
	}

}
