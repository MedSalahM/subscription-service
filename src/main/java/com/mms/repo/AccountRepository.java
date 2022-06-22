package com.mms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mms.domain.MyAccount;

public interface AccountRepository extends JpaRepository<MyAccount, Long>{

	
	List<MyAccount> findByEmail(String email);
	
}
