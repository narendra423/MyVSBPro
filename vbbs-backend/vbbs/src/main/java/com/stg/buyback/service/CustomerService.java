package com.stg.buyback.service;

import java.util.Optional;

import com.stg.buyback.entity.Customer;
import com.stg.exception.VbbsException;

public interface CustomerService {

	public abstract Customer createCustomer(Customer customer) throws VbbsException;

	public abstract Optional<Customer> readCustomer(int customerId) throws VbbsException;

	public abstract Customer updateCustomer(Customer customer) throws VbbsException;
}
