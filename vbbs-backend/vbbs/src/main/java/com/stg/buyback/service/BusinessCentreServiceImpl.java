package com.stg.buyback.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.stg.buyback.entity.BusinessCentre;
import com.stg.buyback.repository.BusinessCentreRepository;
import com.stg.exception.VbbsException;

@Service
public class BusinessCentreServiceImpl implements BusinessCentreService {
	@Autowired
	private BusinessCentreRepository businessCentreRepository;

	@Override
	public BusinessCentre createBusinessCentre(BusinessCentre businessCentre) throws VbbsException {
		Optional<BusinessCentre> businessCentre1 = businessCentreRepository.findById(businessCentre.getDecisionMakerId());
		
		if(businessCentre1.isPresent()) {
			throw new VbbsException("Data already exists with the decision maker ID");
		}
		return businessCentreRepository.save(businessCentre);
	}

	@Override
	public BusinessCentre getBusinessCentre(int decisionMakerId) throws VbbsException {
		Optional<BusinessCentre> optional = businessCentreRepository.findByDecisionMakerId(decisionMakerId);
		
		if(optional.isPresent()) {
			return optional.get();
		}else {
			throw new VbbsException("No data with this decision maker ID");
		}
	}

	@Override
	public List<BusinessCentre> getAllBusinessCentres() {
		
		return businessCentreRepository.findAll();
	}

	@Override
	public BusinessCentre updateBusinessCentre(int businessId, BusinessCentre newBusinessCentre) {
		Optional<BusinessCentre> optional = businessCentreRepository.findById(businessId);
		
		if(optional.isPresent()) {
			businessCentreRepository.setForeignConstraints();
			newBusinessCentre.setBusinessId(businessId);
			//return businessCentreRepository.save(newBusinessCentre);
		}else {
			throw new VbbsException("No data with this ID");
		}
		return businessCentreRepository.save(newBusinessCentre);
	}

}
