package com.stg.buyback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stg.buyback.entity.BuybackReason;
import com.stg.buyback.repository.BuybackReasonRepository;
import com.stg.exception.VbbsException;


@Service
public class BuybackReasonServiceImpl implements BuybackReasonService {
	
	@Autowired
	private BuybackReasonRepository buybackReasonRepository;
	
	@Override
	public BuybackReason createBuybackReason(BuybackReason buybackReason) {

		return buybackReasonRepository.save(buybackReason);
	}

	@Override
	public BuybackReason getBuybackReasonDetailsById(int buybackReasonId) throws VbbsException {
		BuybackReason buybackReason=buybackReasonRepository.findById(buybackReasonId).get();
		if(buybackReason!=null) {
			return buybackReason;
		}
		else {
			throw new VbbsException("No BuyBackReason Found with the given Id");
		}
	}

	@Override
	public List<BuybackReason> getAllBuybackReasonsDetails() {
		return buybackReasonRepository.findAll();
	}

	@Override
	public BuybackReason updateBuybackreason(BuybackReason buybackReason, int buybackReasonId) {
		BuybackReason buybackReason1=buybackReasonRepository.findById(buybackReasonId).get();
		if(buybackReason1!=null) {
			buybackReason.setBuybackReasonId(buybackReasonId);
		}
		else {
			throw new VbbsException("No BuyBackReason Found with the given Id");
		}
		
		return buybackReasonRepository.save(buybackReason);
		
	}

}
