package com.jsp.MedNext.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.MedNext.entity.Address;

public interface AddressRepo extends JpaRepository<Address, Integer> {

}
