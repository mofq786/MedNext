package com.jsp.MedNext.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.MedNext.dao.AdminDao;
import com.jsp.MedNext.dao.MemberDao;
import com.jsp.MedNext.entity.Admin;
import com.jsp.MedNext.entity.Member;
import com.jsp.MedNext.exceptions.NotFoundException;
import com.jsp.MedNext.utils.BuilderClass;
import com.jsp.MedNext.utils.SuccessResponse;


@Service
public class AdminService {
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private MemberDao memberDao;
	
	public ResponseEntity<SuccessResponse> saveAdmin(Admin admin)
	{
		
		return BuilderClass.builderHelp(HttpStatus.CREATED, 
				"Admin Details Saved Successfully", adminDao.saveAdmin(admin));
	}
	
	public ResponseEntity<SuccessResponse> adminLogin(String email, String password)
	{
		Admin admin = adminDao.getAdminByEmail(email);
		
		if(admin != null)
		{
//			admin = adminDao.getAdminByPassword(password);
			if(admin.getPassword().equals(password))
				return BuilderClass.builderHelp(HttpStatus.FOUND, 
						"Admin Login Successful", admin);
			
			throw new NotFoundException("The Given Password:"+password+" is Invalid");
		}
		throw new NotFoundException("Admin with "+email+" does not exist");
	}
	
	public ResponseEntity<SuccessResponse> fetchAdmin(int id)
	{
		Admin admin = adminDao.getAdmin(id);
		
		if(admin != null)
		return BuilderClass.builderHelp(HttpStatus.FOUND, 
				"Admin Found Successful", admin);
		
		throw new NotFoundException("Admin with "+id+" not found");
	}
	
	public ResponseEntity<SuccessResponse> deleteAdminByUsingId(int id)
	{
		Admin admin = adminDao.deleteAdminById(id);
		
		if(admin != null)
		return BuilderClass.builderHelp(HttpStatus.ACCEPTED, 
			"Admin Deleted Successfully", admin);
		
		throw new NotFoundException("Admin with "+id+" not exist");
	}
	
	public ResponseEntity<SuccessResponse> updateAdminDetails(Admin admin)
	{
		Admin adm = adminDao.updateAdminDetails(admin);
		
		if(adm != null)
		return BuilderClass.builderHelp(HttpStatus.ACCEPTED, 
				"Admin Details Updated Successfully", adm);
		
		throw new NotFoundException("Admin with "+admin.getId()+" does not exist");
	}
	
	public ResponseEntity<SuccessResponse> enableMember(int adminId, int memId)
	{
		Admin admin = adminDao.getAdmin(adminId);
		if(admin != null)
		{
			Member member = memberDao.getMemberById(memId);
			if(member != null)
			{
				member.setDisabled(true);
				member = memberDao.updateMember(member);
				return BuilderClass.builderHelp(HttpStatus.ACCEPTED, 
						"Member Enabled Successfully", member);
			}
			
			throw new NotFoundException("Member with Id:"+memId+" not found");
		}
		
		throw new NotFoundException("Admin with Id:"+adminId+" not found");
	}
}
