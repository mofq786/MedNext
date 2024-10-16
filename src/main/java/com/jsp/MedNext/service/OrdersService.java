package com.jsp.MedNext.service;

import java.util.LinkedList;
import java.util.List;

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
import com.jsp.MedNext.exceptions.OutOfStock;
import com.jsp.MedNext.utils.BuilderClass;
import com.jsp.MedNext.utils.SuccessResponse;

@Service
public class OrdersService {
	
	@Autowired
	private OrdersDao ordersDao;
	@Autowired
	private DrugDao drugsDao;
	@Autowired
	private MemberDao memberDao;

	public ResponseEntity<SuccessResponse> addOrder(int memberId, List<String> drugNames) {

		Member member = memberDao.getMemberById(memberId);
		List<Drug> drugsList = new LinkedList<Drug>();
		List<String> notAvailableDrugs = new LinkedList<String>();
		double totalAmount = 0;
		if(member != null)
		{
			if(drugNames.size() != 0)
			{
				for (String drugName : drugNames) 
				{
					Drug drug = drugsDao.getDrugByName(drugName);
					if(drug != null)
					{
						if(drug.getQuantity() > 0)
						{
							totalAmount += drug.getPrice();
							drugsList.add(drug);
						}
						else
							notAvailableDrugs.add(drugName);
					}
					else
						notAvailableDrugs.add(drugName);
				}
				
				Orders order = new Orders();
				order.setDrugs(drugsList);
				order.setMemberid(memberId);
				order.setOrderamount(totalAmount);
				
				Orders ordered = ordersDao.saveOrder(order);
				if(ordered != null)
				{
					return BuilderClass.builderHelp(HttpStatus.CREATED,
							"Order Placed Successfully", 
							ordered+"\nThese Drugs Not Found or Unavailable:"+notAvailableDrugs);
				}
				
			}
			else
				throw new NotFoundException("No Drugs Selected or Names Given");
		}
		
		throw new NotFoundException("Member with Id:"+memberId+" Not Found");
	}
	
	private boolean isMemberExists(int memberId)
	{
		return false;
	}

}
