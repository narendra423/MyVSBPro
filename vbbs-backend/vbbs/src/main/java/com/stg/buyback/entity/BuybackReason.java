package com.stg.buyback.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
public class BuybackReason {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int buybackReasonId;
	
	@Column(nullable = false, length = 256)
	private String nonConfirmity;
	
	@Column(nullable = false)
	private Boolean isBuybackReasonRepaired;
	
	@Column(length = 256)
	private String reasonForNotRepaired;
	
	@Column(nullable = false)
	private Boolean anyPhysicalDamage;	
	
	@Column(nullable = false)
	private Boolean vehicleInDealership;
	
	@Column(nullable = false, length = 256)
	private String justification;
	
	@Column( length = 512)
	private String additionalInfo;
	
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyback_id", referencedColumnName = "buybackId",nullable = false, unique = true)
	@JsonBackReference(value="buyback-reason")
    private Buyback buyback;

	@JsonManagedReference(value = "reasonCategory")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "buybackReason")
	private List<BuybackCategory> buybackCategories;

}
