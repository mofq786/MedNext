package com.jsp.MedNext.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.MedNext.dao.AddressDao;
import com.jsp.MedNext.dao.MemberDao;
import com.jsp.MedNext.entity.Member;
import com.jsp.MedNext.exceptions.NotFoundException;
import com.jsp.MedNext.utils.BuilderClass;
import com.jsp.MedNext.utils.MedNextMailSender;
import com.jsp.MedNext.utils.SuccessResponse;

import jakarta.mail.MessagingException;

@Service
public class MemberService {
	
	@Autowired
	public MemberDao memberDao;
	@Autowired
	public AddressDao addressDao;
	
	@Autowired
	private MedNextMailSender mailSender;
	
	public ResponseEntity<SuccessResponse> saveMemberDetails(Member member) throws MessagingException
	{
		Member mem = memberDao.saveMember(member);
		if(mem != null)
		{
			mailSender.enableRequest(mem);
			return BuilderClass.builderHelp(HttpStatus.CREATED, 
					"Member Registered waiting for approval from Admin", mem);
		}
		return null;
	}
	
	public ResponseEntity<SuccessResponse> memberLogin(String email, String password)
	{
		Member member = memberDao.getMemberByEmail(email);
		if(member != null)
		{
			if(member.getPassword().equals(password))
			{
				if(member.isEnabled() == true)
					return BuilderClass.builderHelp(HttpStatus.FOUND, 
						"Member Login Succesful", member);
				
				return BuilderClass.builderHelp(HttpStatus.FOUND, 
						"Member Not Approved yet by the admin", member);
			}
			
			throw new NotFoundException("The Given Password:"+password+" is invalid");
		}
		
		throw new NotFoundException("The Member not found with Email:"+email);
	}
	
	
	public ResponseEntity<SuccessResponse> updateMember(Member member)
	{
		Member mem = memberDao.updateMember(member);
		if(mem != null)
			return BuilderClass.builderHelp(HttpStatus.ACCEPTED, 
				"Member Registered waiting for approval from Admin", mem);
		throw new NotFoundException("Member Id not provided");
	}

	public ResponseEntity<SuccessResponse> deleteMember(int id) {
		
		Member mem = memberDao.deleteMemberById(id);
		if(mem != null)
			return BuilderClass.builderHelp(HttpStatus.ACCEPTED, 
				"Member Deleted Successfully", mem);
		throw new NotFoundException("Member Id:"+id+" is Not Found");
	}

	public ResponseEntity<SuccessResponse> fetchMemberByUsingId(int id) {
		Member mem = memberDao.getMemberById(id);
		if(mem != null)
			return BuilderClass.builderHelp(HttpStatus.FOUND, 
				"Member Details Found", mem);
		throw new NotFoundException("Member Id:"+id+" is Not Found");
	}

	public ResponseEntity<SuccessResponse> fetchAll() {
		List<Member> membersList = memberDao.getAllMembers();
		if(membersList.size() != 0)
			return BuilderClass.builderHelp(HttpStatus.FOUND, 
				"Member Details Found", membersList);
		throw new NotFoundException("No Members Found");
	}

	public ResponseEntity<SuccessResponse> updateProfileInDb(int memberId, MultipartFile file) throws IOException {
		
		Member member = memberDao.getMemberById(memberId);
		
		if(member == null)
		{
			throw new NotFoundException("Member with Id:"+memberId+" Not Found");
		}
		
		member.setImage(file.getBytes());
		
		Member updatedMember = memberDao.updateMember(member);
		
		return BuilderClass.builderHelp(HttpStatus.ACCEPTED, "Image Uploaded Successfully", updatedMember);
		
	}

	public ResponseEntity<byte[]> fetchProfileFromDb(int memberId) {

		Member member = memberDao.getMemberById(memberId);
		
		if(member == null)
		{
			throw new NotFoundException("Member with Id:"+memberId+" Not Found");
		}
		
		byte[] data = member.getImage();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		
		return new ResponseEntity<byte[]>(data,headers,HttpStatus.FOUND);
	}
	
}
