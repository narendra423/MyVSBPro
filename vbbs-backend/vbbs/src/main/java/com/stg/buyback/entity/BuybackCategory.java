package com.stg.buyback.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BuybackCategory {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int buybackCategoryId;
	
	@Column(nullable = false, length = 256)
    private String primaryCategory;
	
	@Column(nullable = false, length = 256)
    private String problem;
	
	@Column(nullable = false, length = 512)
    private String problemDesc;
	
	@ManyToOne()
    @JoinColumn(name = "buyback_reason_id", referencedColumnName = "buybackReasonId", nullable = false)
	@JsonBackReference(value = "reasonCategory")
 	private BuybackReason buybackReason;
	
	
	
	
}
