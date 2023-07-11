package com.stg.buyback.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
public class Attachment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int attachmentId;

	private String fileName;

	private String fileType;

	@Lob
	@Column(nullable = false, columnDefinition = "MEDIUMBLOB")
	private byte[] file;

	@ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
	@JoinColumn(name = "buyback_id", referencedColumnName = "buybackId", nullable = false)
	@JsonBackReference(value = "buybackAttachment")
	private Buyback buyback;

	public Attachment(String fileName, String fileType, byte[] file) {
		this.fileName = fileName;
		this.fileType = fileType;
		this.file = file;
	}

	
}
