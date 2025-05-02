package com.bank.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionHandlerController {
	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<?> resourceNotFound(ResourceNotFound ex){
		ErrorResponse error = new ErrorResponse(
				HttpStatus.NOT_FOUND.value(), 
				ex.getMessage(), 
				LocalDateTime.now());
		return ResponseEntity.status(404).body(error);
	}
	@ExceptionHandler(BadRequestParam.class)
	public ResponseEntity<?> badRequestParam(BadRequestParam ex){
		ErrorResponse error = new ErrorResponse(
				HttpStatus.BAD_REQUEST.value(), 
				ex.getMessage(), 
				LocalDateTime.now());
		return ResponseEntity.status(400).body(error);
	}
}
