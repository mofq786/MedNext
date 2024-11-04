package com.jsp.MedNext.utils;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.jsp.MedNext.entity.Member;
import com.jsp.MedNext.entity.Orders;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class MedNextMailSender {
	
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private TemplateEngine templateEngine;
	
	public void enableRequest(Member member) throws MessagingException
	{ 
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo("mailmeasradha@gmail.com");
		helper.setFrom("mofq9786@gmail.com");
		helper.setSubject("Testing the mail");
//		FileSystemResource file = new FileSystemResource(new File("/MedNext/src/main/resources/templates/Medicine.jpg"));
//		helper.addAttachment("Medicine.jpg", file);
		helper.setText("<h4>Hi, Radha</h4>"
				+ "<p>Just Now a new member registered on the platform. Here below, are the Member Details</p>"
				+ "<center>"
				+ "<table border='1' style='border-collapse: collapse;'>"
				+ "<tr><th colspan='2'>New Member Details</th></tr>"
				+ "<tr><td>ID</td><td>"+member.getId()+"</td></tr>"
				+ "<tr><td>Name</td><td>"+member.getName()+"</td></tr>"
				+ "<tr><td>Email</td><td>"+member.getEmail()+"</td></tr>"
				+ "<tr><td>Mobile</td><td>"+member.getMobile()+"</td></tr>"
				+ "<tr><td>Gender</td><td>"+member.getGender()+"</td></tr>"
				+ "<tr><td>DOB</td><td>"+member.getDob()+"</td></tr>"
				+ "<tr><td>Address</td><td>"+member.getAddress().getStreet()+", "
											+member.getAddress().getCity()+", "
											+member.getAddress().getState()+", "
											+member.getAddress().getCountry()+" - "
											+member.getAddress().getPincode()+". "
				+"</td></tr>"
				+ "</table></center>", true);
		
		javaMailSender.send(message);
	
		
	}
	
	public void sendOrderEmail(String memberEmail,Orders order) throws MessagingException, IOException
	{
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom("mofq9786@gmail.com");
		helper.setTo(memberEmail);
		helper.setSubject("MedNext Order Placed Successfully");
		
		Context context = new Context();
		context.setVariable("memberName", "Farooq");
		context.setVariable("order", order);
		
		String htmlContent = templateEngine.process("OrderPlaced", context);
		helper.setText(htmlContent,true);
		javaMailSender.send(message);
	}

}
