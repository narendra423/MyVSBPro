package com.stg.buyback.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Dealer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int dealerId;

	@Column(unique = true,nullable=false)
	private int code;

	@Column(nullable = false)
	private String dealershipName;

	@Column(unique = true, nullable = false)
	@Email(message = "Enter valid email ID")
	private String emailId;

	@Column(nullable = false)
	private String contact;

	@JsonManagedReference(value = "vehicleDealer")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dealer")
	private List<Vehicle> vehicles;


}
