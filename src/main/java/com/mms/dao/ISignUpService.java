package com.mms.dao;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mms.domain.MyAccount;
import com.mms.domain.SignUpDetails;
import com.mms.dto.SignUpDto;
import com.mms.dto.SignUpRequest;
import com.mms.dto.SignUpResponse;
import com.mms.exeption.AccountAlreadyExistsException;
import com.mms.exeption.ActivationLinkExpired;
import com.mms.exeption.ActivationLinkNotFound;
import com.mms.exeption.SignUpDetailsNotFound;
import com.mms.repo.SignupDetailsRepository;
import com.mms.service.AccountService;
import com.mms.service.MailingService;
import com.mms.service.SignUpService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ISignUpService implements SignUpService {

	
	private final SignupDetailsRepository repo;
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private MailingService mailingService;
	
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
		
	
		String activationLink = "user="+String.valueOf(id)+"&key="+String.valueOf(id).hashCode();
		
		signup.setActivationLink(activationLink);
    	signup.setExpriesAt(LocalDateTime.now().plusHours(1));

		
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
		
		

		
	
	     	
		
		  var latest = this.find(id);
	
		  var response = SignUpResponse.builder()
         .email(latest.getEmail())
         .activationLink(latest.getActivationLink() )
         .build();
		 
		  mailingService.sendEmail(response.getActivationLink(), response.getEmail());
		  
			return response;
	
		
	}

	@Override
	public MyAccount activate(String link) {
	
		
		List<SignUpDetails> signups = repo.findByActivationLink(link);
		
		
		if (signups.size()==0) throw new ActivationLinkNotFound(link); 
		
		
		SignUpDetails sd = signups.stream().findFirst().get();
		
		String email = sd.getEmail();
		if (accountService.hasAccount(email)) throw new AccountAlreadyExistsException(email);
		
		
		if (sd.getExpriesAt().isBefore(LocalDateTime.now())) throw new ActivationLinkExpired(link);
		
		MyAccount account = MyAccount.builder()
		         .email(sd.getEmail())
		         .createdAt(LocalDateTime.now().toString())
		         .build();
		                
		
		
		MyAccount createdAccount = accountService.add(account);
		
		this.deleteUnecessaryRequests(email, link);
		
		
		
		return createdAccount;
	}
	
	
	public  void deleteUnecessaryRequests(String email , String link) {
		
		
		List<SignUpDetails> signups = repo.findByEmail(email);
		signups.stream()
		       .filter(e->!e.getActivationLink().equals(link))
		       .forEach(repo::delete);
		       
		       
		
	}
	
 

	
}
