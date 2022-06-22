package com.mms.exeption;

public class AccountAlreadyExistsException extends RuntimeException {

	public AccountAlreadyExistsException(String email) {
		
		
		super("Il existe un compte avec cet email : "+email);
		
	}
	  
	
	
}
