package com.sgen.breakmedown.breakmedown.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sgen.breakmedown.breakmedown.model.AppUser;
import com.sgen.breakmedown.breakmedown.model.account.Account;
import com.sgen.breakmedown.breakmedown.service.AccountService;
import com.sgen.breakmedown.breakmedown.service.AppUserService;

@RestController
@RequestMapping(value = "/management/api/v1")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AppUserService appUserService;
	
	@GetMapping(value = "/accounts")
	public ResponseEntity<List<Account>> fetchAllAccounts(){
		List<Account> allAccounts = this.accountService.fetchAllAccounts();
		return ResponseEntity.ok(allAccounts);
	}
	
	@PostMapping(value = "/accounts/{id}")
	public String topUp(@PathVariable(value = "id", required = true) Integer appUserId, @RequestParam Integer amount) {
		Optional<AppUser> appUser = this.appUserService.findAppUserById(appUserId);
		if(appUser.isPresent()) {
			if(amount >= 500) {
				AppUser thisAppUser = appUser.get();
				thisAppUser.getAccount().setBalance(thisAppUser.getAccount().getBalance() + amount);
				this.accountService.registerAccount(thisAppUser.getAccount());
			}else {
				throw new RuntimeException("Invalid amount: "+amount);
			}
		}
		return "Account Topped With Success";
	}
	

}
