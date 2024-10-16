package com.jsp.MedNext.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.MedNext.entity.Drug;
import com.jsp.MedNext.service.DrugService;
import com.jsp.MedNext.utils.SuccessResponse;

@RestController
@RequestMapping("/admin/drug")
public class DrugController {
	
	@Autowired
	private DrugService drugService;
	
	@PostMapping("/add")
	public ResponseEntity<SuccessResponse> addDrug(@RequestParam int adminId,@RequestBody Drug drug)
	{
		return drugService.addDrug(adminId, drug);
	}
	
	@PutMapping("/update")
	public ResponseEntity<SuccessResponse> editDrug(@RequestParam int adminId,@RequestBody Drug drug)
	{
		return drugService.editDrug(adminId, drug);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<SuccessResponse> deleteDrug(@RequestParam int adminId,@RequestParam int drugId)
	{
		return drugService.deleteDrug(adminId, drugId);
	}
	
	@GetMapping("byId")
	public ResponseEntity<SuccessResponse> fetchDrugByUsingId(@RequestParam int drugId)
	{
		return drugService.fetchDrugById(drugId);
	}
	
	@GetMapping("byName")
	public ResponseEntity<SuccessResponse> fetchDrugByUsingName(@RequestParam String drugName)
	{
		return drugService.fetchDrugByName(drugName);
	}
	
	@GetMapping("allDrugs")
	public ResponseEntity<SuccessResponse> fetchAllTheDrugs()
	{
		return drugService.fetchAllDrugs();
	}

}
