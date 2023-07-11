package com.stg.buyback.service;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stg.buyback.entity.Dealer;
import com.stg.buyback.repository.DealerRepository;
import com.stg.exception.VbbsException;

@Service
public class DealerServiceImpl implements DealerService {

	@Autowired
	private DealerRepository dealerRepository;

	@Override
	public Dealer createDealership(Dealer dealership) {

		return dealerRepository.save(dealership);
	}
	
	@Override
	public Dealer getDealerByVIN(BigInteger vin) {
		
		return dealerRepository.findDealerByVIN(vin);
	}

	@Override
	public Dealer getDealerByCode(int code) {

		Dealer dealer = dealerRepository.findByDealerCode(code);
		if (dealer != null) {
			return dealer;
		} else {
			throw new VbbsException("No Dealer Found on code " + code);
		}
	}

	@Override
	public Dealer updateDealer(Dealer dealer, int dealerId) {
		Optional<Dealer> dealer2 = dealerRepository.findById(dealerId);
		if (dealer2.isPresent()) {
			dealer.setDealerId(dealerId);
		} else {
			throw new VbbsException("No Dealer Found on " + dealerId);
		}
		return dealerRepository.save(dealer);
	}

	@Override
	public Dealer getDealerById(int dealerId) {

		if (dealerRepository.existsById(dealerId)) {
			Dealer dealertemp = dealerRepository.findById(dealerId).get();
			return dealertemp;
		} else {
			throw new VbbsException("No dealer found on Id " + dealerId);
		}

	}
}
