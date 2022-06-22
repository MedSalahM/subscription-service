package com.mms.service;

import com.mms.domain.SignUpDetails;
import com.mms.dto.SignUpRequest;
import com.mms.dto.SignUpResponse;

public interface SignUpService {

	SignUpResponse signup(SignUpRequest request);
    SignUpDetails find(Long id);
	
    SignUpResponse response(Long id);
	
	
	
	
	
	
}
