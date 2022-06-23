package com.mms.exeption;

public class ActivationLinkNotFound extends RuntimeException {

	public ActivationLinkNotFound(String message) {
		
		
		super( "Lien introuvable !");
	}
}
