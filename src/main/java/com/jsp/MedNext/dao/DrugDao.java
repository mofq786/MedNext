package com.jsp.MedNext.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.MedNext.entity.Drug;
import com.jsp.MedNext.repository.DrugRepo;

@Repository
public class DrugDao {
	
	@Autowired
	private DrugRepo drugRepo;
	
	public Drug getDrugById(int id)
	{
		Optional<Drug> dr = drugRepo.findById(id);
		if(dr.isPresent())
		{
			Drug drug = dr.get();
			return drug;
		}
		return null;
	}
	
	public List<Drug> getAllDrugs()
	{
		return drugRepo.findAll();
	}
	
	public Drug getDrugByName(String drugName)
	{
		return drugRepo.findByName(drugName);
	}
	
	public Drug deleteDrugById(int id)
	{
		Drug drug = getDrugById(id);
		if(drug != null)
		{
			drugRepo.deleteById(id);
			return drug;
		}
		
		return null;
	}
	
	public Drug updateDrug(Drug drug)
	{
		Drug dr = getDrugById(drug.getId());
		if(dr != null)
		{
			if(drug.getCompany() == null)
			{
				drug.setCompany(dr.getCompany());
			}
			if(drug.getName() == null)
			{
				drug.setName(dr.getName());
			}
			if(drug.getPrice() == 0)
			{
				drug.setPrice(dr.getPrice());
			}
			if(drug.getQuantity() == 0)
			{
				drug.setQuantity(dr.getQuantity());
			}
			if(drug.getRating() == 0)
			{
				drug.setRating(dr.getRating());
			}
			if(drug.getType() == null)
			{
				drug.setType(dr.getType());;
			}
			if(drug.isBanned() == false)
			{
				drug.setBanned(dr.isBanned());
			}
			
			return drugRepo.save(drug);
			
		}
		
		return null;
	}
	
	public Drug addDrug(Drug drug)
	{
		return drugRepo.save(drug);
	}
	
}
