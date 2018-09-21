package com.emp.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Employee {

	@Id
	@Getter
	@Setter
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	@Getter
	@Setter
	String name;
	@Column(unique = true)
	@Getter
	@Setter
	String email;
	@Getter
	@Setter
	String address;
	@Getter
	@Setter
	String designation;
	@Getter
	@Setter
	long phoneNum;
	@Getter
	@Setter
	String password;
	@Getter
	@Setter
	String type;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@Getter
	@Setter
	@JoinColumn(name = "adm_id")
	public Admin admin;

}
