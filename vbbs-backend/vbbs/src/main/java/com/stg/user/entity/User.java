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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@Email(message = "Enter valid Email")
	private String emailId;
	
	@Column(length = 20)
	@NotEmpty(message = "Enter Firstname")
	private String firstName;
	
	@Column(length = 20)
	@NotEmpty(message = "Enter Lastname")
	private String lastName;
	
	
	@Column(length = 20)
	@NotEmpty(message = "Enter middle Initial")
	private String  middleInitial;
	
	@Size(min = 5, max = 20, message = "length of the password must be between 5 and 20")
	@NotEmpty(message = "Enter password")
	private String password;
	
	
	
	@JoinColumn(name = "role_id", nullable = false, referencedColumnName = "roleId")
	@JsonBackReference(value = "userRole")
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private Roles role;
	
}
