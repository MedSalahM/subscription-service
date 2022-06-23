package com.mms.exeption;

public class ActivationLinkExpired extends RuntimeException {

	public ActivationLinkExpired(String message) {
		
		
		super(message+ " est expiré");
	}
}
