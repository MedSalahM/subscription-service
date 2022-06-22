package com.mms.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mms.dto.SignUpRequest;
import com.mms.service.SignUpService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/")


public class SignUpController {

	
	private final SignUpService signupService;
	
	@PostMapping("signup")
	public ResponseEntity<?> signingUp(@RequestBody @Valid SignUpRequest req) {
		
		
		
		var response = new ResponseEntity<>(signupService.signup(req),HttpStatus.CREATED);

		return response;
		
		
	}
	
	
}
