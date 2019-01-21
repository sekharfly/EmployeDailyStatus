package com.swagger.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class EmployeeModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	@Setter
	Integer id;

	@Getter
	@Setter
	String name;

	@Getter
	@Setter
	String email;

	@Getter
	@Setter
	String address;

	@Getter
	@Setter
	String age;
	
	@Getter
	@Setter
	long apikey;
	

}
