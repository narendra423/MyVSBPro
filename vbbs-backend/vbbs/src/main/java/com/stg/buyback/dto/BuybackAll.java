package com.stg.buyback.dto;

import com.stg.buyback.entity.BusinessCentre;
import com.stg.buyback.entity.Buyback;
import com.stg.buyback.entity.BuybackReason;
import com.stg.buyback.entity.Customer;
import com.stg.buyback.entity.Dealer;
import com.stg.buyback.entity.Vehicle;

public class BuybackAll {
	
	private String status;
	private Dealer dealer;
	private Vehicle vehicle;
	private Customer customer;
	private Buyback buyback;
	private BuybackReason buybackReason;
	private BusinessCentre businessCentre;
	
	public Dealer getDealer() {
		return dealer;
	}
	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Buyback getBuyback() {
		return buyback;
	}
	public void setBuyback(Buyback buyback) {
		this.buyback = buyback;
	}
	public BuybackReason getBuybackReason() {
		return buybackReason;
	}
	public void setBuybackReason(BuybackReason buybackReason) {
		this.buybackReason = buybackReason;
	}
	public BusinessCentre getBusinessCentre() {
		return businessCentre;
	}
	public void setBusinessCentre(BusinessCentre businessCentre) {
		this.businessCentre = businessCentre;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
