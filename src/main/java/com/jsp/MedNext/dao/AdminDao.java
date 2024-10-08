package com.jsp.MedNext.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.MedNext.entity.Admin;
import com.jsp.MedNext.repository.AdminRepo;

@Repository
public class AdminDao {
	
	@Autowired
	private AdminRepo adminrepo;
	
	public Admin saveAdmin(Admin admin)
	{
		return adminrepo.save(admin);
	}
	
	public Admin getAdminByEmail(String email)
	{
		return adminrepo.findByEmail(email);
	}
	
	public Admin getAdminByPassword(String password)
	{
		return adminrepo.findByPassword(password);
	}
	
	public Admin getAdminByEmailPassword(String email, String password)
	{
		return adminrepo.findByEmailAndPassword(email, password);
	}
	
	public Admin updateAdminDetails(Admin admin)
	{
		Admin adm = getAdmin(admin.getId());
		
		if(adm != null)
		{
			if(admin.getEmail() == null)
			{
				admin.setEmail(adm.getEmail());
			}
			if(admin.getName() == null)
			{
				admin.setName(adm.getName());
			}
			if(admin.getPassword() == null)
			{
				admin.setPassword(adm.getPassword());
			}
			if(admin.getRole() == null)
			{
				admin.setRole(adm.getRole());
			}
			
			return adminrepo.save(admin);
		}
		
		return null;
	}
	
	public Admin deleteAdminById(int id)
	{
		Admin admin = getAdmin(id);
		
		if(admin != null)
		{
			adminrepo.delete(admin);
			return admin;
		}
		
		return null;
	}
	
	public Admin getAdmin(int id)
	{
		Optional<Admin> adm = adminrepo.findById(id);
		if(adm.isPresent())
		{
			Admin admin = adm.get();
			return admin;
		}
		
		return null;
		
	}
	
	
}
