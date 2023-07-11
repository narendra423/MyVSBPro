package com.stg.buyback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stg.buyback.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	// public Customer findByPhone1(String phone1);

	public Customer findByEmailId(String emailId);
}
