package com.stg.buyback.entity;

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
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BusinessCentre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int businessId;
	
	@Column(nullable = false, unique = true)
    private int decisionMakerId;
	
	@Column(nullable = false, length = 20, updatable = false)
	private String decitionMaker;
	
	@Column(nullable = false)
	@Size(min = 10,max = 10, message = "phone no length must be 10")
	private String phone;
	
	@Column(nullable = false, length = 20)
	@Email
    private String emailId;
	
	@Column(nullable = false,unique = true, length = 20)
    private String businessCentreIdentity;
	
	@Column(nullable = false, length = 20)
	private String district;
	
	@Column(nullable = false)
	private boolean isCauseForBuybackByDealer;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyback_id", referencedColumnName = "buybackId", nullable = false, unique = true)
	@JsonBackReference(value="business-centre")
	private Buyback buyback;
	
}
