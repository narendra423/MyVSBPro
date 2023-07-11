package com.stg.buyback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stg.buyback.entity.BuybackCategory;
import com.stg.buyback.entity.BuybackReason;
import com.stg.buyback.repository.BuybackCategoryRepository;
import com.stg.buyback.repository.BuybackReasonRepository;
import com.stg.exception.VbbsException;


@Service
public class BuybackCategoryServiceImpl implements BuybackCategoryService {

	@Autowired
	private BuybackCategoryRepository buybackCategoryRepository;
	
	@Autowired
	private BuybackReasonRepository buybackReasonRepository;

	@Override
	public BuybackCategory createBuybackCategory(BuybackCategory buybackCategory,int buybackreasonId) {
		BuybackReason buybackReason= buybackReasonRepository.findById(buybackreasonId).get();
		
		buybackCategory.setBuybackReason(buybackReason);
		
		return buybackCategoryRepository.save(buybackCategory);
	}

	@Override
	public BuybackCategory getBuyId(int buybackCategotyId) {
		System.out.println(buybackCategotyId + " -------------------------");
		return  buybackCategoryRepository.findById(buybackCategotyId).orElseThrow(()->new VbbsException("buybackCategotyId not found"));
		
		
	}

	@Override
	public List<BuybackCategory> readAll() {
		
		return buybackCategoryRepository.findAll();
	}

	@Override
	public BuybackCategory updateById (int buybackCategotyId, BuybackCategory buybackCategory) throws VbbsException {
		/*buybackCategory.setBuybackCategoryId(buybackCategotyId);
		return buybackCategoryRepository.save(buybackCategory);*/
		if(this.getBuyId(buybackCategotyId)!=null ) {
			buybackCategory.setBuybackCategoryId(buybackCategotyId);
			return buybackCategoryRepository.save(buybackCategory);
		}
		else {
			throw new VbbsException("buybackCategotyId is not avaliable");
		}
		
	}

}
