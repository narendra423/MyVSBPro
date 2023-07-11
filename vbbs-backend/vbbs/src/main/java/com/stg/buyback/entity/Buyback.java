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
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
public class Buyback {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int buybackId;

	@Column(nullable = false)
	private Boolean isArbitrationFilled;

	public enum buyBackType {
		REPLACEMENT, REFUND
	}
	
	public enum aftermarketAccessories {
		no,yes, unknown
	}
	
	public enum status {
		DRAFT, APPROVALPENDING, APPROVED, CANCELLED
	}
	
	private status buybackStatus;

	@Column
	private buyBackType typeOfBuyback;

	@Column(nullable = false)
	private Boolean isStericycleNegotiate;

	@Column(nullable = false)
	private Boolean isMileageOffset;

	@Column
	private String aftermarketAccessoriesList;
	
	@Column
	private aftermarketAccessories isAftermarketAccessories;

	
	@Column
	private Boolean isFlatUsageFee;
	
	@Column
	private float flatUsageFee;

	@Column
	private Boolean isStraightMilage;

	@Column
	private float straightMilageMiles;

	@Column
	private float straightMilageCostPerMile;


	@OneToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", referencedColumnName = "vehicleId", nullable = false, unique = true )
	@JsonBackReference(value="buyback-vehcile")
    private Vehicle vehicle;

	@OneToOne(mappedBy = "buyback", fetch = FetchType.LAZY)
	@JsonManagedReference(value="buyback-reason")
	private BuybackReason buybackReason;
	
	@OneToOne(mappedBy = "buyback", fetch = FetchType.LAZY)
	@JsonManagedReference(value="business-centre")
	private BusinessCentre businessCentre;

	@JsonManagedReference(value = "buybackAttachment")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "buyback")
	private List<Attachment> attachments;
}
