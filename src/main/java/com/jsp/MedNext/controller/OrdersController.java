package com.jsp.MedNext.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.MedNext.service.OrdersService;

@RestController
@RequestMapping("/user/orders")
public class OrdersController {
	
	@Autowired
	private OrdersService ordersService;
	
	
}
