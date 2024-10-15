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

import com.jsp.MedNext.entity.Admin;
import com.jsp.MedNext.service.AdminService;
import com.jsp.MedNext.utils.MedNextMailSender;
import com.jsp.MedNext.utils.SuccessResponse;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminServ;
	
	@PostMapping("/register")
	public ResponseEntity<SuccessResponse> addAdmin(@RequestBody Admin admin)
	{
		return adminServ.saveAdmin(admin);
	}
	
	@GetMapping("/login")
	public ResponseEntity<SuccessResponse> login(@RequestParam String email, @RequestParam String password)
	{
		return adminServ.adminLogin(email, password);
	}
	
	@PutMapping("/update")
	public ResponseEntity<SuccessResponse> update(@RequestBody Admin admin)
	{
		return adminServ.updateAdminDetails(admin);
	}
	
	@DeleteMapping("/delete") 
	public ResponseEntity<SuccessResponse> delete(@RequestParam int id)
	{
		return adminServ.deleteAdminByUsingId(id);
	}
	
	@PutMapping("/enable-member")
	public ResponseEntity<SuccessResponse> enableMember(@RequestParam int adminId, @RequestParam int memId)
	{
		return adminServ.enableMember(adminId, memId);
	}
	
}
