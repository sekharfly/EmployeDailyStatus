package com.emp.demo.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Status {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter
	@Getter
	public long id;
	
	
	@Setter
	@Getter
	String status;

	@ManyToOne(cascade = CascadeType.ALL)
	@Getter
	@Setter
	@JoinColumn(name = "emp_id")
	public Employee employee;

	@Getter
	@Setter
	Date date;
}
