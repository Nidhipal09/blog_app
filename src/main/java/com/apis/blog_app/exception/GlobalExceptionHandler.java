package com.apis.blog_app.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.apis.blog_app.utils.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceAlreadyExists.class)
	public String userAlreadyExistsExHandler(ResourceAlreadyExists ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(ResourceNotFound.class)
	public String resourceNotFoundExHandler(ResourceNotFound ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgumentNotValidExceptionExHandler(MethodArgumentNotValidException ex) {
		Map<String, String> map = new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach(error->{
			String field = ((FieldError)error).getField();
			String message = error.getDefaultMessage();
			
			map.put(field, message);
		});
		
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.BAD_REQUEST);
	}
}
