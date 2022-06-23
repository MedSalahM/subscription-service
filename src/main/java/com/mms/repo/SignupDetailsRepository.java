package com.mms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mms.domain.SignUpDetails;

public interface SignupDetailsRepository extends JpaRepository<SignUpDetails, Long> {

	
	List<SignUpDetails> findByActivationLink(String activationLink);
	List<SignUpDetails> findByEmail(String email);
	
	
	
}
