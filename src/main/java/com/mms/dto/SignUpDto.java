package com.mms.dto;

import java.time.LocalDateTime;

import com.mms.domain.SignUpDetails;

public class SignUpDto {

	
	public static SignUpDetails toSignUpDetails(SignUpRequest request) {
		
		
		return SignUpDetails.builder()
				.email(request.getEmail())
				.dateRequest(LocalDateTime.now().toString())
				.build();
		
		
	}
	

}
