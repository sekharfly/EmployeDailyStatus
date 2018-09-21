package com.emp.demo.repo;

import org.springframework.data.repository.CrudRepository;

import com.emp.demo.model.Admin;

public interface AdminRepo extends CrudRepository<Admin, Long> {
	 Admin getByEmail(String email);
}
