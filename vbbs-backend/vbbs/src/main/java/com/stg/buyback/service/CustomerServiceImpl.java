package com.stg.buyback.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stg.buyback.entity.Customer;
import com.stg.buyback.repository.CustomerRepository;
import com.stg.exception.VbbsException;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer createCustomer(Customer customer) throws VbbsException {
		Customer customerEmailId = customerRepository.findByEmailId(customer.getEmailId());
		if (customerEmailId == null) {
			return customerRepository.save(customer);
		} else {
			throw new VbbsException("Customer Details are Not created because Customer EmailId is Already Exist");
		}

	}

	@Override
	public Optional<Customer> readCustomer(int customerId) throws VbbsException {
		Optional<Customer> dumCusId = customerRepository.findById(customerId);
		if (dumCusId.isPresent()) {
			return customerRepository.findById(customerId);
		} else {
			throw new VbbsException("Give it Correct CustomerId");
		}

	}

	@Override

	public Customer updateCustomer(Customer customer) throws VbbsException {
		Optional<Customer> tempId = customerRepository.findById(customer.getCustomerId());
		if (tempId.isPresent()) {
			customer.setVehicle(tempId.get().getVehicle());
			return customerRepository.save(customer);
		} else {
			throw new VbbsException("CustomerId is Not existing");
		}

	}



}
