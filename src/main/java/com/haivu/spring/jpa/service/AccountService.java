package com.haivu.spring.jpa.service;

import java.util.List;

import com.haivu.spring.jpa.model.Account;

public interface AccountService {

	/**
	 * get all account
	 * 
	 * @return list account
	 */
	List<Account> getAllAccount();

	/**
	 * get account by id
	 * 
	 * @param id
	 * @return account
	 */
	Account getAccountById(long id);

	/**
	 * get account by email
	 * 
	 * @param email
	 * @return account
	 */
	Account getAccountByEmail(String email);

	/**
	 * add new account
	 * 
	 * @param account
	 * @return account
	 */
	Account addNewAccount(Account account);

	/**
	 * update account
	 * 
	 * @param account
	 * @return account
	 */
	Account updateAccount(Account account);

	/**
	 * delete account by id
	 * 
	 * @param id
	 * @return true if delete account successful or false if delete account fail
	 */
	boolean deleteAccount(long id);

}
