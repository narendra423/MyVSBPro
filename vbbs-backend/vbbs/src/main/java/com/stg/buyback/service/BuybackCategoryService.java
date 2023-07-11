package com.stg.buyback.service;

import java.util.List;

import com.stg.buyback.entity.BuybackCategory;
import com.stg.exception.VbbsException;

public interface BuybackCategoryService {

	public abstract BuybackCategory createBuybackCategory(BuybackCategory buybackCategory,int buybackreasonId);
	
	public abstract BuybackCategory getBuyId(int buybackCategotyId);
	
	public abstract List<BuybackCategory> readAll();
	
	public abstract BuybackCategory updateById(int buybackCategotyId,BuybackCategory buybackCategory) throws VbbsException;
}
