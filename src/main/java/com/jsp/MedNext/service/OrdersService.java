package com.jsp.MedNext.service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.MedNext.dao.DrugDao;
import com.jsp.MedNext.dao.MemberDao;
import com.jsp.MedNext.dao.OrdersDao;
import com.jsp.MedNext.entity.Drug;
import com.jsp.MedNext.entity.Member;
import com.jsp.MedNext.entity.Orders;
import com.jsp.MedNext.exceptions.NotFoundException;
import com.jsp.MedNext.utils.BuilderClass;
import com.jsp.MedNext.utils.MedNextMailSender;
import com.jsp.MedNext.utils.SuccessResponse;

import jakarta.mail.MessagingException;

@Service
public class OrdersService {
	
	@Autowired
	private OrdersDao ordersDao;
	@Autowired
	private DrugDao drugsDao;
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private MedNextMailSender mailSender;
	
	private List<Drug> drugsList;
	private List<String> notAvailableDrugs;
	private double totalAmount = 0;

	public ResponseEntity<SuccessResponse> addOrder(int memberId, Map<String,Integer> drugNames) throws MessagingException, IOException {

		Member member = memberDao.getMemberById(memberId);
		
		if(member == null){
			throw new NotFoundException("Member with Id:"+memberId+" Not Found");
		}
		
		if(drugNames.size() == 0){
			throw new NotFoundException("No Drugs Selected or Names Given");
		}
		
		//Adding drugs into the Lists
		addDrugsIntoLists(drugNames);
		
		//Placing The Order
		return placeTheOrderUsingMember(member);
		
	}
	
	private void addDrugsIntoLists(Map<String,Integer> drugNames)
	{
		drugsList = new LinkedList<Drug>();
		notAvailableDrugs = new LinkedList<String>();
		
		for(Map.Entry<String,Integer> dr : drugNames.entrySet())
		{
			String drugName = dr.getKey();
			int quantity = dr.getValue();				
			Drug drug = drugsDao.getDrugByName(drugName);
			
			if(drug != null){
				
				int quantDb = drug.getQuantity();
				if(quantDb > 0 && quantDb >= quantity){
					
					totalAmount += drug.getPrice()*quantity;
					drug.setQuantity(quantity);
					drugsList.add(drug);
					
					drug.setQuantity(quantDb - quantity);
					drugsDao.updateDrug(drug);
					
				}
				else
					notAvailableDrugs.add(drugName);
			}
			else
				notAvailableDrugs.add(drugName);
		}
	}
	
	private ResponseEntity<SuccessResponse> placeTheOrderUsingMember(Member member) throws MessagingException, IOException
	{
		if(drugsList.size() != 0){
			
			Orders order = new Orders();
			order.setDrugs(drugsList);
			order.setMemberid(member.getId());
			order.setOrderamount(totalAmount);
					
			Orders ordered = ordersDao.saveOrder(order);
			
			mailSender.sendOrderEmail(member.getEmail(), ordered);
			return BuilderClass.builderHelp(HttpStatus.CREATED,
					"Order Placed Successfully", 
					ordered+"\nThese Drugs Not Found or Unavailable:"+notAvailableDrugs);
			
		}
		else
			throw new NotFoundException("No Drugs Available and Order Failed");
	}

}
