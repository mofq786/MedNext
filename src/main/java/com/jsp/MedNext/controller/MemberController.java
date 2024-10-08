package com.jsp.MedNext.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.MedNext.entity.Member;
import com.jsp.MedNext.service.MemberService;
import com.jsp.MedNext.utils.SuccessResponse;

@RestController
@RequestMapping("/user")
public class MemberController {
	
	@Autowired
	private MemberService memberServ;
	
	@PostMapping("/register")
	public ResponseEntity<SuccessResponse> saveMember(@RequestBody Member member)
	{
		return memberServ.saveMemberDetails(member);
	}
	
}
