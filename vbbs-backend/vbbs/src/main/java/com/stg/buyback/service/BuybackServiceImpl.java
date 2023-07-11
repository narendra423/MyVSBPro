package com.stg.buyback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stg.buyback.entity.Buyback;
import com.stg.buyback.repository.BuybackRepository;
import com.stg.exception.VbbsException;


@Service
public class BuybackServiceImpl implements BuybackService {

	@Autowired
	private BuybackRepository buybackRepository;

	@Override
	public Buyback createBuyback(Buyback buyback) {
		
    		return buybackRepository.save(buyback);
 
	}

	@Override
	public Buyback getBuybackById(int id) throws VbbsException {
		
		return buybackRepository.findById(id).orElseThrow(()-> new VbbsException("Buyback  is not found:" + id) );
	
		
	}

	@Override
	public Buyback updateBuybackById(int id, Buyback buyback) throws VbbsException {
		Buyback oldBuyback =  this.getBuybackById(id);
		
		if (oldBuyback.getBuybackId() == id) {
			
			buyback.setBuybackId(id);
			buyback.setVehicle(oldBuyback.getVehicle());
			return this.buybackRepository.save(buyback);
			
		}else {
			throw new  VbbsException("Doesn't Updated");
		}
	}

	@Override
	public Buyback updateBuybackStatusById(Buyback buyback) {
		Buyback buybackObj =  buybackRepository.findById(buyback.getBuybackId()).orElseThrow(()-> new VbbsException("Buyback  is not found for id:" + buyback.getBuybackId()) );
		
		if(buybackObj != null) {
			buybackObj.setBuybackStatus(buyback.getBuybackStatus());
		}
		return buybackRepository.save(buybackObj);
	}
	


}
