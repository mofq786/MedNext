package com.jsp.MedNext.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.MedNext.entity.Address;
import com.jsp.MedNext.repository.AddressRepo;

@Repository
public class AddressDao {
	
	@Autowired
	private AddressRepo addressRepo;
	
	public Address saveAddress(Address address)
	{
		return addressRepo.save(address);
	}
}
