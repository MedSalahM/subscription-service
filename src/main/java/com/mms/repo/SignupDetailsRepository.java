package com.mms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mms.domain.SignUpDetails;

public interface SignupDetailsRepository extends JpaRepository<SignUpDetails, Long> {

	
}
