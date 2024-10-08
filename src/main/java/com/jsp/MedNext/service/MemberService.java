package com.jsp.MedNext.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.MedNext.dao.AddressDao;
import com.jsp.MedNext.dao.MemberDao;
import com.jsp.MedNext.entity.Address;
import com.jsp.MedNext.entity.Member;
import com.jsp.MedNext.utils.BuilderClass;
import com.jsp.MedNext.utils.SuccessResponse;

@Service
public class MemberService {
	
	@Autowired
	public MemberDao memberDao;
	@Autowired
	public AddressDao addressDao;
	
	public ResponseEntity<SuccessResponse> saveMemberDetails(Member member)
	{
		Address address = addressDao.saveAddress(member.getAddress());
		member.setAddress(address);
		return BuilderClass.builderHelp(HttpStatus.CREATED, 
				"Member Registered waiting for approval from Admin", memberDao.saveMember(member));
	}
}
