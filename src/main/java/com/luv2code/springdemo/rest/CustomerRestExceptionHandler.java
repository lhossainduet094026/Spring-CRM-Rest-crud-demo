package com.luv2code.springdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler {

	//add an exception handler for customerNotFoundException
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException exc){
		
		CustomerErrorResponse response  = new CustomerErrorResponse();
		response.setStatus(HttpStatus.NOT_FOUND.value());
		response.setMessage(exc.getMessage());
		response.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<CustomerErrorResponse>(response,HttpStatus.NOT_FOUND);//here respose is body and other is status code
	} 
	
	//add another exception handler.....to catch any exception(catch all)
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(Exception exc){
		
		CustomerErrorResponse response  = new CustomerErrorResponse();
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		response.setMessage(exc.getMessage());
		response.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<CustomerErrorResponse>(response,HttpStatus.BAD_REQUEST);//here respose is body and other is status code
	} 
	
}
