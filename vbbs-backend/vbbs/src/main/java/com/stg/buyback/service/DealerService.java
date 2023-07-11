package com.stg.buyback.service;

import java.math.BigInteger;

import com.stg.buyback.entity.Dealer;

public interface DealerService {

	public abstract Dealer createDealership(Dealer dealership);
	
	public abstract Dealer getDealerByVIN(BigInteger vin);

	public abstract Dealer getDealerByCode(int code);

	public abstract Dealer updateDealer(Dealer dealer,int dealerId);

	 public abstract Dealer getDealerById(int dealerId);

}
