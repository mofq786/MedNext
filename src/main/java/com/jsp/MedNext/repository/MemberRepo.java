package com.jsp.MedNext.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.MedNext.entity.Member;


public interface MemberRepo extends JpaRepository<Member, Integer> {
	
	 Member findByEmail(String email);
	 Member findById(int id);

}
