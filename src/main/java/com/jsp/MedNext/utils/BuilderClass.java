package com.jsp.MedNext.utils;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BuilderClass {
	
	public static ResponseEntity<SuccessResponse> builderHelp(HttpStatus status, String message, Object data)
	{
		SuccessResponse successResponse = SuccessResponse
												.builder()
												.statusCode(status.value())
												.msg(message)
												.data(data)
												.dateTime(LocalDateTime.now())
												.build();
		return new ResponseEntity<SuccessResponse>(successResponse, status);
	}
}
