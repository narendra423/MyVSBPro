package com.stg.buyback.service;

import java.util.List;

import com.stg.buyback.entity.BuybackReason;

public interface BuybackReasonService {

	public abstract BuybackReason createBuybackReason(BuybackReason buybackReason);
	public abstract BuybackReason getBuybackReasonDetailsById(int buyBackId);
	public abstract List<BuybackReason> getAllBuybackReasonsDetails();
	
	public abstract BuybackReason updateBuybackreason(BuybackReason buybackReason,int buybackReasonId);
}
