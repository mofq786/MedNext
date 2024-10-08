package com.jsp.MedNext.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.MedNext.entity.Admin;

public interface AdminRepo extends JpaRepository<Admin, Integer> {
	
	Admin findByEmailAndPassword(String email, String password);
	Admin findByEmail(String email);
	Admin findByPassword(String password);

}
