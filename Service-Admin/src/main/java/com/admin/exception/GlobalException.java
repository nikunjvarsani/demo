package com.admin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@RestControllerAdvice
public class GlobalException {
	
@ExceptionHandler(HttpServerErrorException.class)	
public ResponseEntity<ExceptionFormate> methodEx(HttpServerErrorException ex){
	
	return new ResponseEntity<ExceptionFormate>(new ExceptionFormate("Invalid Token"),HttpStatus.BAD_REQUEST);
	
}

@ExceptionHandler(HttpClientErrorException.class)	
public ResponseEntity<ExceptionFormate> methodEx(HttpClientErrorException ex){
	
	return new ResponseEntity<ExceptionFormate>(new ExceptionFormate("Please Enter Token"),HttpStatus.BAD_REQUEST);
	
}
}
