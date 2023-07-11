package com.stg.buyback.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;

	@Column(unique = true)
	@Email(message = "Enter valid email ID")
	private String emailId;

	@Column(length = 20, nullable = false)
	private String firstName;

	@Column(length = 10, nullable = false)
	private String lastName;

	@Column
	private String middleInitial;

	@Column(length = 10, nullable = false)
	private String phone1;

	@Column(length = 10, nullable = true)
	private String phone2;

	@Column(length = 10, nullable = true)
	private String phone3;

	@Column(length = 20)
	private String contactPreference;

	@Column(length = 20)
	private String bestTimeToCall;
	
	@Column
	private boolean isRental;
	
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "vehicle_id", referencedColumnName = "vehicleId", nullable = false, unique = true)
	@JsonBackReference(value = "customer-vehicle")
	private Vehicle vehicle;
}
