package com.jsp.MedNext.exceptions;

import java.sql.SQLIntegrityConstraintViolationException;

import org.hibernate.TransientPropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.MedNext.utils.BuilderClass;
import com.jsp.MedNext.utils.SuccessResponse;

@RestControllerAdvice
public class MedNextExceptionHandler {
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<SuccessResponse> sqlICVE(SQLIntegrityConstraintViolationException e)
	{
		return BuilderClass.builderHelp(HttpStatus.BAD_REQUEST, 
				"You Cannot Perform this operation", e.getMessage());
	}
	
	@ExceptionHandler(TransientPropertyValueException.class)
	public ResponseEntity<SuccessResponse> transientPVE(TransientPropertyValueException e)
	{
		return BuilderClass.builderHelp(HttpStatus.BAD_REQUEST, 
				"Please follow the principles of data Insertion", e.getMessage());
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<SuccessResponse> notFoundException(NotFoundException e)
	{
		return BuilderClass.builderHelp(HttpStatus.NOT_FOUND, 
				"Operation is Unsuccessful", e.getMsg());
	}
}
