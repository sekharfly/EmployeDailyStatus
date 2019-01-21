package com.swagger.demo.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import com.swagger.demo.model.EmployeeModel;

public interface EmployeeRepo extends CrudRepository<EmployeeModel, Integer> {

	public List<EmployeeModel> findByApikey(long apikey);
}
