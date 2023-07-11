package com.stg.buyback.service;

import java.util.List;

import com.stg.buyback.entity.BusinessCentre;

public interface BusinessCentreService {
	
	public abstract BusinessCentre createBusinessCentre(BusinessCentre businessCentre);
	
	public abstract BusinessCentre getBusinessCentre(int decisionMakerId);
	
	public abstract List<BusinessCentre> getAllBusinessCentres();
	
	public abstract BusinessCentre updateBusinessCentre(int decisionMakerId, BusinessCentre businessCentre);

}
