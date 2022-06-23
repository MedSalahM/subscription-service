package com.mms.service;

import com.mms.domain.MyAccount;

public interface AccountService {

	
	
	MyAccount add(MyAccount account);
	boolean hasAccount(String email);
	
	
	
}
