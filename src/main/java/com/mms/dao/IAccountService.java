package com.mms.dao;

import org.springframework.stereotype.Service;

import com.mms.domain.MyAccount;
import com.mms.repo.AccountRepository;
import com.mms.service.AccountService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IAccountService implements AccountService{
   
	private final AccountRepository accountRepository;
	
	@Override
    public boolean hasAccount(String email) {
	
	 
		 email = email.trim();
		
		if( accountRepository.findByEmail(email)
		                   .size()>0)
		{
			
			return true;
			
		}
		                  
		
		
		
		
		
	    return false;
     }

	@Override
	public MyAccount add(MyAccount account) {
		// TODO Auto-generated method stub
		return accountRepository.save(account);
	}
}
