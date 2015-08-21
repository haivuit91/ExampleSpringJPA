package com.haivu.spring.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haivu.spring.jpa.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	/**
	 * find account by email
	 * 
	 * @param email
	 * @return account
	 */
	Account findByEmail(String email);

}
