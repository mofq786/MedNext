package com.jsp.MedNext.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.MedNext.service.OrdersService;
import com.jsp.MedNext.utils.SuccessResponse;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/user")
public class OrdersController {
	
	@Autowired
	private OrdersService ordersService;
	
	@PostMapping("/order")
	public ResponseEntity<SuccessResponse> placeOrder(@RequestParam int memberId,@RequestBody Map<String, Integer> drugs) throws MessagingException, IOException
	{
		return ordersService.addOrder(memberId,drugs);
	}
	
	
}
