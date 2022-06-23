package com.mms.exeption;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {

	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		
		BindingResult bindingResult = e.getBindingResult();
		
		  final List<FieldError> fieldErrors = bindingResult.getFieldErrors();

		       List<String> errors = fieldErrors.stream()
		          .map(ef->ef.getField()+" "+ef.getDefaultMessage())
                  .collect(Collectors.toList());
                  
		      var status =HttpStatus.BAD_REQUEST;
		  
             ApiError apiError = new ApiError(e.getLocalizedMessage(),status, errors);

		   return new ResponseEntity<ApiError>(apiError, status);
		  
	}
	

	@ExceptionHandler(AccountAlreadyExistsException.class)
	public ResponseEntity<ApiError> handleAcountalreadyExists(AccountAlreadyExistsException e) {
		
		
		      var status =HttpStatus.CONFLICT;
		      
		      var errors = new ArrayList<String>();
		      
		      errors.add(e.getLocalizedMessage());
		  
             ApiError apiError = new ApiError(e.getLocalizedMessage(),status, errors);

		   return new ResponseEntity<ApiError>(apiError, status);
		  
	}
	
	@ExceptionHandler(ActivationLinkNotFound.class)
	public ResponseEntity<ApiError> handleLinkNotFound(ActivationLinkNotFound e) {
		
		
		      var status =HttpStatus.NOT_FOUND;
		      
		      var errors = new ArrayList<String>();
		      
		      errors.add(e.getLocalizedMessage());
		  
             ApiError apiError = new ApiError(e.getLocalizedMessage(),status, errors);

		   return new ResponseEntity<ApiError>(apiError, status);
		  
	}
	
	@ExceptionHandler(ActivationLinkExpired.class)
	public ResponseEntity<ApiError> handleLinkExpiredException(ActivationLinkExpired e) {
		
		
		      var status =HttpStatus.BAD_REQUEST;
		      
		      var errors = new ArrayList<String>();
		      
		      errors.add(e.getLocalizedMessage());
		  
             ApiError apiError = new ApiError(e.getLocalizedMessage(),status, errors);

		   return new ResponseEntity<ApiError>(apiError, status);
		  
	}
}
