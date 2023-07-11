package com.stg.buyback.service;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stg.buyback.dto.BuybackAll;
import com.stg.buyback.entity.BusinessCentre;
import com.stg.buyback.entity.Buyback;
import com.stg.buyback.entity.BuybackReason;
import com.stg.buyback.entity.Customer;
import com.stg.buyback.entity.Dealer;
import com.stg.buyback.entity.Vehicle;
import com.stg.buyback.repository.BusinessCentreRepository;
import com.stg.buyback.repository.BuybackReasonRepository;
import com.stg.buyback.repository.BuybackRepository;
import com.stg.buyback.repository.CustomerRepository;
import com.stg.buyback.repository.DealerRepository;
import com.stg.buyback.repository.VehicleRepository;
import com.stg.exception.VbbsException;

@Service
public class MainServiceImpl implements MainService {
	@Autowired
	private DealerRepository dealerRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private VehicleRepository vehicleRepository;

	@Autowired
	private BuybackRepository buybackRepository;

	@Autowired
	private BuybackReasonRepository buybackReasonRepository;

	@Autowired
	private BusinessCentreRepository businessCentreRepository;

	@Override
	public BuybackAll getBuybackRelatedDetails(BigInteger vin, int dealerCode) throws VbbsException {
		Vehicle vehicleTemp = vehicleRepository.findByVin(vin);
		if (vehicleTemp == null) {
			throw new VbbsException("No data exists with the given VIN");
		}

		Dealer dealerTemp = dealerRepository.findByDealerCode(dealerCode);
		if (dealerTemp == null) {
			throw new VbbsException("No data exists with the given dealerCode");
		}

		BuybackAll buybackAll = new BuybackAll();

		buybackAll.setVehicle(vehicleTemp);
		buybackAll.setDealer(dealerTemp);

		return buybackAll;
	}

	@Override
	public BuybackAll createAllDetails(BuybackAll buybackAll) {

		if (buybackAll.getBuyback() != null) {

			Buyback buybackTemp = vehicleRepository.findByVin(buybackAll.getVehicle().getVin()).getBuyback();

			if (buybackTemp == null) {
				buybackAll.setBuyback(buybackRepository.save(buybackAll.getBuyback()));
			} else {
				throw new VbbsException("Buyback already exists for this vehicle");
			}

		} else if (buybackAll.getBuybackReason() != null) {

			BuybackReason buybackReasonTemp = buybackRepository.findById(buybackAll.getBuyback().getBuybackId()).get()
					.getBuybackReason();

			if (buybackReasonTemp == null) {
				buybackAll.setBuybackReason(buybackReasonRepository.save(buybackAll.getBuybackReason()));
			} else {
				throw new VbbsException("Buyback reason already exists for the given buyback");
			}

		} else if (buybackAll.getBusinessCentre() != null) {

			BusinessCentre businessCentreTemp = buybackRepository.findById(buybackAll.getBuyback().getBuybackId()).get()
					.getBusinessCentre();

			if (businessCentreTemp == null) {
				buybackAll.setBusinessCentre(businessCentreRepository.save(buybackAll.getBusinessCentre()));
			} else {
				throw new VbbsException("Business Centre already exists for the given buyback");
			}

		}

		return buybackAll;
	}

	@Override
	public BuybackAll updateAllDetails(BuybackAll buybackAll) {

		if (buybackAll.getCustomer() != null) {

			Optional<Customer> tempId = customerRepository.findById(buybackAll.getCustomer().getCustomerId());

			if (tempId.isPresent()) {
				customerRepository.save(buybackAll.getCustomer());
			} else {
				throw new VbbsException("No Customer with the customerId");
			}

		} else if (buybackAll.getDealer() != null) {

			Optional<Dealer> dealer2 = dealerRepository.findById(buybackAll.getDealer().getDealerId());
			if (dealer2.isPresent()) {
				dealerRepository.save(buybackAll.getDealer());
			} else {
				throw new VbbsException("No Dealer Found with dealerId ");
			}

		} else if (buybackAll.getBuyback() != null) {

			Buyback buybackTemp = buybackRepository.findById(buybackAll.getBuyback().getBuybackId()).get();

			if (buybackTemp != null) {
				buybackRepository.save(buybackAll.getBuyback());
			} else {
				throw new VbbsException("No Buyback exists with the given Id");
			}

		} else if (buybackAll.getBuybackReason() != null) {

			BuybackReason buybackReasonTemp = buybackReasonRepository
					.findById(buybackAll.getBuybackReason().getBuybackReasonId()).get();

			if (buybackReasonTemp != null) {
				buybackReasonRepository.save(buybackAll.getBuybackReason());
			} else {
				throw new VbbsException("No Buyback reason exists for the given Id");
			}

		} else if (buybackAll.getBusinessCentre() != null) {

			BusinessCentre businessCentreTemp = businessCentreRepository
					.findById(buybackAll.getBusinessCentre().getBusinessId()).get();

			if (businessCentreTemp != null) {
				businessCentreRepository.save(buybackAll.getBusinessCentre());
			} else {
				throw new VbbsException("No Business Centre exists for the given Id");
			}

		}

		return buybackAll;
	}

}
