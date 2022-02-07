package com.sgen.breakmedown.breakmedown.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sgen.breakmedown.breakmedown.model.account.Account;
import com.sgen.breakmedown.breakmedown.service.AccountService;

@RestController
@RequestMapping(value = "/management/api/v1")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping(value = "/accounts")
	public ResponseEntity<List<Account>> fetchAllAccounts(){
		List<Account> allAccounts = this.accountService.fetchAllAccounts();
		return ResponseEntity.ok(allAccounts);
	}
	

}
