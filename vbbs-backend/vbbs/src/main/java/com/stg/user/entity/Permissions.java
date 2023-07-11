package com.stg.user.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "permissions")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Permissions {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int permissionId;
	
	@Column(length = 20)
	@NotBlank(message = "Enter permission type")
	private String permissionType;
	
	@JoinColumn(name = "role_id", nullable = false, referencedColumnName = "roleId")
	@JsonBackReference(value = "rolePermission")
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private Roles role;
}
