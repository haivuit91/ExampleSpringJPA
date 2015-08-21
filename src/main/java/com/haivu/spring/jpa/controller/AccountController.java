package com.haivu.spring.jpa.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.haivu.spring.jpa.model.Account;
import com.haivu.spring.jpa.service.AccountService;

@Controller
@RequestMapping("/account/")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@RequestMapping("list")
	public String listUsers(ModelMap model) {
		List<Account> accounts = accountService.getAllAccount();
		model.put("listAccounts", accounts);
		return "list";
	}

	@RequestMapping("add")
	public String addNewUser(Account account, ModelMap model) {
		return "add-edit";
	}

	@RequestMapping("edit/{id}")
	public String updateUser(@PathVariable("id") long id, ModelMap model) {
		Account account = accountService.getAccountById(id);
		model.put("account", account);
		return "add-edit";
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String saveUser(@Valid Account account, BindingResult bindingResult, ModelMap model) {
		if (bindingResult.hasErrors()) {
			return "add-edit";
		}
		if (account.getId() == 0) {
			accountService.addNewAccount(account);
			return "redirect:list";
		}
		accountService.updateAccount(account);
		return "redirect:list";

	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	public boolean deleteUser(@PathVariable("id") long id) {
		return accountService.deleteAccount(id);
	}

}
