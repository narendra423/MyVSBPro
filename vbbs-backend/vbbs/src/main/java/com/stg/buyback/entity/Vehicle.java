package com.stg.buyback.entity;

import java.math.BigInteger;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int vehicleId;

	@Column(nullable = false, unique = true)
	private BigInteger vin;

	@Column(length = 15)
	private String modelName;

	@Column(length = 15)
	private String make;

	@Column
	@Min(1900)
	@Max(2022)
	private int modelYear;

	@Column(nullable = false)
	private float currentMileage;


	@OneToOne(mappedBy = "vehicle")
	@JsonManagedReference(value = "buyback-vehcile")
	private Buyback buyback;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "dealer_id", referencedColumnName = "dealerId", nullable = false)
	@JsonBackReference(value = "vehicleDealer")
	private Dealer dealer;

	@OneToOne(mappedBy = "vehicle")
	@JsonManagedReference(value = "customer-vehicle")
	private Customer customer;

}
