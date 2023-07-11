package com.stg.buyback.service;

import com.stg.buyback.entity.Buyback;
import com.stg.exception.VbbsException;

public interface BuybackService {
	
	public abstract Buyback createBuyback(Buyback buyback) throws VbbsException;
	
	public abstract Buyback getBuybackById(int id) throws VbbsException;
	
	public abstract Buyback updateBuybackById(int id,Buyback buyback) throws VbbsException;
	
	public abstract Buyback updateBuybackStatusById(Buyback buyback);

}
