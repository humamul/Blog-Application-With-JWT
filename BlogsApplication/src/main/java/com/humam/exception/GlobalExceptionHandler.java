package com.humam.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> OtherExceptionHandler(Exception exp, WebRequest req){
		ErrorDetails authEx = new ErrorDetails();
		authEx.setTimestamp(LocalDateTime.now());
		authEx.setMessage(exp.getMessage());
		authEx.setDescription(req.getDescription(false));

		return new ResponseEntity<>(authEx,HttpStatus.BAD_REQUEST);
	}


	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDetails> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exp) {

		ErrorDetails authEx = new ErrorDetails();
		authEx.setTimestamp(LocalDateTime.now());
		authEx.setMessage("Validation Error");
		authEx.setDescription(exp.getBindingResult().getFieldError().getDefaultMessage());

		return new ResponseEntity<>(authEx,HttpStatus.BAD_REQUEST);
	}



	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> InvalidTicketExceptionHandler(ResourceNotFoundException exp, WebRequest req){
		ErrorDetails authEx = new ErrorDetails();
		authEx.setTimestamp(LocalDateTime.now());
		authEx.setMessage(exp.getMessage());

		authEx.setDescription(req.getDescription(false));

		return new ResponseEntity<>(authEx,HttpStatus.CONFLICT);
	}



}
