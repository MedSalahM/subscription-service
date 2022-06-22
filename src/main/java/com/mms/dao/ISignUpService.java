package com.mms.dao;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mms.domain.SignUpDetails;
import com.mms.dto.SignUpDto;
import com.mms.dto.SignUpRequest;
import com.mms.dto.SignUpResponse;
import com.mms.exeption.AccountAlreadyExistsException;
import com.mms.exeption.SignUpDetailsNotFound;
import com.mms.repo.SignupDetailsRepository;
import com.mms.service.AccountService;
import com.mms.service.SignUpService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ISignUpService implements SignUpService {

	
	private final SignupDetailsRepository repo;
	
	@Autowired
	private AccountService accountService;
	
	@Override
	@Transactional
	public SignUpResponse signup(SignUpRequest request) {
		
		
		
		
		String email = request.getEmail();
		
		boolean hasAccount = false;
		
		hasAccount=accountService.hasAccount(email);
		
	
		
		if (hasAccount) {
			
			throw new AccountAlreadyExistsException(email);
			
		}
		
		
	
		SignUpDetails signup = SignUpDto.toSignUpDetails(request);
		
		
	
		Long id = repo.save(signup).getId();
		
		return this.response(id);

		
		
				
			
		
	}

	@Override
	public SignUpDetails find(Long id) {
		
		
		SignUpDetails details = repo.findById(id)
				                .orElseThrow(()->new SignUpDetailsNotFound(""+id));
		
		return details;
	}

	@Override
	public  SignUpResponse response(Long id) {
		
		

		
		String activationLink = "activate?activationLink="+String.valueOf(id);
		
	
	     	
		
		 var latest = this.find(id);
		 
		 latest.setActivationLink(activationLink);
		 
		 repo.save(latest);
		
		
	
				
			return 	SignUpResponse.builder()
	              .email(latest.getEmail())
	              .activationLink(activationLink )
	              .build();
	
		
		
		
	}
	
	


	
}
