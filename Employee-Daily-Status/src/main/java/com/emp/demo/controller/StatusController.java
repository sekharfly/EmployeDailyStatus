package com.emp.demo.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.emp.demo.model.Employee;
import com.emp.demo.model.Status;
import com.emp.demo.repo.EmployeeRepo;
import com.emp.demo.repo.StatusRepo;

@Controller
public class StatusController {
	
	@Autowired
	StatusRepo statusRepo;
	
	@Autowired 
	EmployeeRepo empRepo;
	
	
	@RequestMapping("/createEmployeeStatus")
	public String createEmpStatus() {
		return "createEmployeeStatus";
	}
	
	@RequestMapping("/createSatatus/{id}")
	public String CreateStatus(@RequestBody Status status,@PathVariable("id") long id) {
		Optional<Employee> findById = empRepo.findById(id);
		Employee employee = findById.get();
		status.setEmployee(employee);
		status.setDate(new Date());
		Status save = statusRepo.save(status);
		System.out.println(save);
		return "Created"; 
	}
	
	@RequestMapping("/getEmployeeStatus/{empid}")
	public String getEmployeeStatus(@PathVariable("empid") long id ,Map<String , Object>model) {
		Optional<Employee> findById = empRepo.findById(id);
		Employee employee = findById.get();
		List<Status> findByEmployee = statusRepo.findByEmployee(employee);
		model.put("status", findByEmployee);
		return "employeeStatus";
	}
	
	@RequestMapping("/getallstatus")
	public String getAllStatus(Map <String, Object> model) {
		Iterable<Status> findAll = statusRepo.findAll();
		model.put("status", findAll);
		return "employees-Status";
	}
}

