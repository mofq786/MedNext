package com.jsp.MedNext.dao;

import java.util.List;

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
		Member mem = memberRepo.findById(member.getId());
		
		if(mem != null)
		{
			if(member.getAddress() == null)
			{
				member.setAddress(mem.getAddress());
			}
			if(member.getDob() == null)
			{
				member.setDob(mem.getDob());
			}
			if(member.getEmail() == null)
			{
				member.setEmail(mem.getEmail());
			}
			if(member.getMobile() == 0)
			{
				member.setMobile(mem.getMobile());
			}
			if(member.getGender() == null)
			{
				member.setGender(mem.getGender());
			}
			if(member.getName() == null)
			{
				member.setName(mem.getName());
			}
			if(member.getPassword() == null)
			{
				member.setPassword(mem.getPassword());
			}
			
			return memberRepo.save(member);
		}
		
		return null;
	}

	public List<Member> getAllMembers() {
		return memberRepo.findAll();
	}

}
