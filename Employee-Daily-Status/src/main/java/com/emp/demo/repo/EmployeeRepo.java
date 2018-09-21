package com.emp.demo.repo;

import org.springframework.data.repository.CrudRepository;

import com.emp.demo.model.Employee;

public interface EmployeeRepo  extends CrudRepository<Employee, Long> {
	public Employee getByEmail(String email);
}