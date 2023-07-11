package com.stg.buyback.service;

import java.math.BigInteger;

import com.stg.buyback.dto.BuybackAll;

public interface MainService {
	
	public abstract BuybackAll createAllDetails(BuybackAll buybackAll);
	
	public abstract BuybackAll updateAllDetails(BuybackAll buybackAll);
	
	public abstract BuybackAll getBuybackRelatedDetails(BigInteger vin, int dealerCode);
}
