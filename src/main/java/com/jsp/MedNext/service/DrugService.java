package com.jsp.MedNext.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.MedNext.dao.AdminDao;
import com.jsp.MedNext.dao.DrugDao;
import com.jsp.MedNext.entity.Admin;
import com.jsp.MedNext.entity.Drug;
import com.jsp.MedNext.exceptions.NotFoundException;
import com.jsp.MedNext.utils.BuilderClass;
import com.jsp.MedNext.utils.SuccessResponse;

@Service
public class DrugService {
	
	@Autowired
	private DrugDao drugDao;
	@Autowired
	private AdminDao adminDao;
	
	public ResponseEntity<SuccessResponse> fetchDrugById(int id)
	{
		Drug drug = drugDao.getDrugById(id);
		if(drug != null)
		return	BuilderClass.builderHelp(HttpStatus.FOUND, "The Drug details found", drug);
		
		throw new NotFoundException("The Drug with Id:"+id+" not found.");
	}
	
	public ResponseEntity<SuccessResponse> addDrug(int adminId,Drug drug)
	{
		Admin admin = adminDao.getAdmin(adminId);
		if(admin != null)
		{
			Drug dr = drugDao.addDrug(drug);
			if(dr != null)
			{
				return	BuilderClass.builderHelp(HttpStatus.CREATED, "The Drug Added Successfully", dr);
			}
			
			return	BuilderClass.builderHelp(HttpStatus.NOT_ACCEPTABLE, "The Drug Cannot be Added", dr);
		}
		throw new NotFoundException("Admin with Id:"+adminId+" Not Found.");
	}
	
	public ResponseEntity<SuccessResponse> deleteDrug(int adminId, int drugId)
	{
		Admin admin = adminDao.getAdmin(adminId);
		if(admin != null)
		{
			Drug dr = drugDao.deleteDrugById(drugId);
			if(dr != null)
			{
				return	BuilderClass.builderHelp(HttpStatus.ACCEPTED, "The Drug Deleted Successfully", dr);
			}
			
			throw new NotFoundException("Drug with Id:"+drugId+" Not Found.");
		}
		throw new NotFoundException("Admin with Id:"+adminId+" Not Found.");
	}
	
	public ResponseEntity<SuccessResponse> fetchDrugByName(String drugName)
	{
		Drug drug = drugDao.getDrugByName(drugName);
		if(drug != null)
		{
			return	BuilderClass.builderHelp(HttpStatus.FOUND, "The Drug Found Successfully", drug);
		}
		
		throw new NotFoundException("Drug: "+drugName+" Not Found.");
		
	}
	
	public ResponseEntity<SuccessResponse> editDrug(int adminId, Drug drug)
	{
		Admin admin = adminDao.getAdmin(adminId);
		if(admin != null)
		{
			Drug dr = drugDao.updateDrug(drug);
			if(dr != null)
			{
				return	BuilderClass.builderHelp(HttpStatus.ACCEPTED, "The Drug Updated Successfully", dr);
			}
			
			throw new NotFoundException("Drug with Id:"+drug.getId()+" Not Found.");
		}
		throw new NotFoundException("Admin with Id:"+adminId+" Not Found.");
	}
	
	public ResponseEntity<SuccessResponse> fetchAllDrugs()
	{
		List<Drug> drugs = drugDao.getAllDrugs();
		if(drugs.size() != 0)
		{
			return	BuilderClass.builderHelp(HttpStatus.FOUND, "The Drugs Found Successfully", drugs);
		}
		
		throw new NotFoundException("No Drugs Available.");
		
	}
	
}
