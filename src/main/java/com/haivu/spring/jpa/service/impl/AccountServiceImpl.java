package com.haivu.spring.jpa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.haivu.spring.jpa.model.Account;
import com.haivu.spring.jpa.repository.AccountRepository;
import com.haivu.spring.jpa.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public List<Account> getAllAccount() {
		List<Account> accounts = accountRepository.findAll();
		accounts.forEach(account -> account.setPwd(""));
		return accounts;
	}

	@Override
	public Account getAccountById(long id) {
		Account account = accountRepository.findOne(id);
		account.setPwd("");
		return account;
	}

	@Override
	public Account getAccountByEmail(String email) {
		Account account = accountRepository.findByEmail(email);
		account.setPwd("");
		return account;
	}

	@Override
	@Transactional
	public Account addNewAccount(Account account) {
		String pwd = passwordEncoder.encode(account.getPwd());
		Account newAccount = new Account(0, account.getEmail(), pwd, account.getFullName());
		return accountRepository.save(newAccount);
	}

	@Override
	@Transactional
	public Account updateAccount(Account account) {
		Account tempAccount = getAccountById(account.getId());
		Account newAccount = new Account(account.getId(), account.getEmail(), tempAccount.getPwd(),
				account.getFullName());
		return accountRepository.save(newAccount);
	}

	@Override
	@Transactional
	public boolean deleteAccount(long id) {
		Account account = getAccountById(id);
		if (account != null) {
			accountRepository.delete(account);
			return true;
		}
		return false;
	}

}
