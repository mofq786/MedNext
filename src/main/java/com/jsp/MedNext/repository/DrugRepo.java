package com.jsp.MedNext.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.MedNext.entity.Drug;

public interface DrugRepo extends JpaRepository<Drug, Integer> {

}
