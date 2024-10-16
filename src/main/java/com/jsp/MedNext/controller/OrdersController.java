package com.jsp.MedNext.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.MedNext.service.OrdersService;
import com.jsp.MedNext.utils.SuccessResponse;

@RestController
@RequestMapping("/user/orders")
public class OrdersController {
	
	@Autowired
	private OrdersService ordersService;
	
	@PostMapping("/placeOrder")
	public ResponseEntity<SuccessResponse> placeOrder(@RequestParam int memberId,@RequestBody List<String> drugs)
	{
		return ordersService.addOrder(memberId,drugs);
	}
	
	
}
