package com.jsp.MedNext.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.MedNext.entity.Orders;
import com.jsp.MedNext.repository.OrdersRepo;

@Repository
public class OrdersDao {

	@Autowired
	private OrdersRepo ordersRepo;
	
	public Orders saveOrder(Orders order) {
		return ordersRepo.save(order);
	}

}
