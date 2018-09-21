package com.emp.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.emp.demo.model.Employee;
import com.emp.demo.model.Status;

public interface StatusRepo extends CrudRepository<Status, Long> {
	
	public List<Status> findByEmployee(Employee emp);

	@Query(value = "SELECT t FROM Status t where t.date between date and edate")
	public List<Status> findByStartDateBetween(long date,long edate);
}
