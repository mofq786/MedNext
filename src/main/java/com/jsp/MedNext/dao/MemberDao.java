package com.jsp.MedNext.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.MedNext.entity.Member;
import com.jsp.MedNext.repository.MemberRepo;

@Repository
public class MemberDao {
	
	@Autowired
	private MemberRepo memberRepo;
	
	public Member saveMember(Member member)
	{
		return memberRepo.save(member);
	}
	
	public Member getMemberByEmail(String email)
	{
		return memberRepo.findByEmail(email);
	}
	
	public Member deleteMemberById(int id)
	{
		Member member = memberRepo.findById(id);
		if(member != null)
		{
			memberRepo.deleteById(id);
			return member;
		}
		
		return null;
	}
	
	public Member getMemberById(int id)
	{
		return memberRepo.findById(id);
	}
	
	public Member updateMember(Member member)
	{
		
	}

}
