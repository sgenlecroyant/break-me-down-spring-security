package com.sgen.breakmedown.breakmedown.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgen.breakmedown.breakmedown.model.account.Account;
import com.sgen.breakmedown.breakmedown.repository.AccountRepository;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	public Account registerAccount(Account account){
		Account registeredAccount = this.accountRepository.save(account);
		return registeredAccount;
	}
	
	public List<Account> fetchAllAccounts(){
		return this.accountRepository.findAll();
	}
	
	public Optional<Account> fetchAccountById(Integer id){
		return this.accountRepository.findById(id);
	}
	

}
