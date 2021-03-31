package com.springBoot.sample.demo.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springBoot.sample.demo.exception.model.ApiError;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler({BussinessException.class})
	public ResponseEntity<Object> handleBussinessError(BussinessException ex){
		ApiError apiError = new ApiError(new Date(),HttpStatus.SERVICE_UNAVAILABLE, ex.getLocalizedMessage(),ex.getClassName());
		return new ResponseEntity<Object>(apiError, apiError.getStatus());
	}
	
	@ExceptionHandler({ResponseStatusException.class})
	public ResponseEntity<Object> handleResponseStatus(ResponseStatusException ex,WebRequest request){
		ApiError apiError = new ApiError(new Date(),ex.getStatus(), ex.getLocalizedMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(apiError, ex.getResponseHeaders(),apiError.getStatus());
	}
	
	@ExceptionHandler({Exception.class})
	public ResponseEntity<Object> handleAll(Exception ex,WebRequest request){
		ApiError apiError = new ApiError(new Date(),HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(apiError, new HttpHeaders(),apiError.getStatus());
	}
	
	
}
