package com.mms.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping("pass/{link}")
	ResponseEntity<?> activateAccount(@PathVariable String link)
	{

		var response = new ResponseEntity<>(signupService.activate(link),HttpStatus.OK);
		
		return response;
		
	}
	
	
	
	
}
