package com.emp.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter
	@Getter
	long id;
	@Setter
	@Getter
	String name;
	@Column(unique = true)
	@Setter
	@Getter
	String email;
	@Setter
	@Getter
	String address;
	@Setter
	@Getter
	String password;
	@Setter
	@Getter
	String designation;
	@Transient
	@Getter
	@Setter
	String cppassword;
	@Transient
	@Getter
	@Setter
	String oldpassword;
	@Transient
	@Getter
	@Setter
	String newpassword;
	@Getter
	@Setter
	String type;

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", email=" + email + ", address=" + address + ", password="
				+ password + "]";
	}
}
