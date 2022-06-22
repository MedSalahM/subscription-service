package com.mms.exeption;

public class SignUpDetailsNotFound extends RuntimeException {

	public SignUpDetailsNotFound(String message) {
		
		
		super(message+ " n existe pas");
	}
}
