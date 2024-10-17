package com.jsp.MedNext.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.MedNext.entity.Member;
import com.jsp.MedNext.service.MemberService;
import com.jsp.MedNext.utils.MedNextMailSender;
import com.jsp.MedNext.utils.SuccessResponse;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/user")
public class MemberController {
	
	@Autowired
	private MemberService memberServ;
	
	@Autowired
	private MedNextMailSender sender;
	
	@PostMapping("/register")
	public ResponseEntity<SuccessResponse> saveMember(@RequestBody Member member) throws MessagingException
	{
		return memberServ.saveMemberDetails(member);
	}
	
	@GetMapping("/login")
	public ResponseEntity<SuccessResponse> login(@RequestParam String email, @RequestParam String password)
	{
		return memberServ.memberLogin(email, password);
	}
	
	@PutMapping("/update")
	public ResponseEntity<SuccessResponse> update(@RequestBody Member member)
	{
		return memberServ.updateMember(member);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<SuccessResponse> deleteMember(@RequestParam int id)
	{
		return memberServ.deleteMember(id);
	}
	
	@GetMapping("/fetchById")
	public ResponseEntity<SuccessResponse> fetchMemberById(@RequestParam int id)
	{
		return memberServ.fetchMemberByUsingId(id);
	}
	
	@GetMapping("/fetchAll")
	public ResponseEntity<SuccessResponse> fetchAllMembers()
	{
		return memberServ.fetchAll();
	}
	
	@PostMapping("/send-email")
	public void sendMail(@RequestParam String mail) throws MessagingException, IOException
	{
//		model.addAttribute("memberName", "farooq");
		sender.sendOrderEmail(mail, null);
	}
	
}
