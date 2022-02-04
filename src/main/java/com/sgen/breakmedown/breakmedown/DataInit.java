package com.sgen.breakmedown.breakmedown;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sgen.breakmedown.breakmedown.model.AppUser;
import com.sgen.breakmedown.breakmedown.model.Subscription;
import com.sgen.breakmedown.breakmedown.model.account.Account;
import com.sgen.breakmedown.breakmedown.model.account.accountEnum.AccountStatus;
import com.sgen.breakmedown.breakmedown.privilege.role.Role;
import com.sgen.breakmedown.breakmedown.repository.AccountRepository;
import com.sgen.breakmedown.breakmedown.repository.AppUserRepo;
import com.sgen.breakmedown.breakmedown.repository.SubscriptionRepo;
import com.sgen.breakmedown.breakmedown.subscriptionType.SubscriptionType;

@Component
public class DataInit implements CommandLineRunner{
	
	@Autowired
	private AppUserRepo appUserRepo;
	
	@Autowired
	private SubscriptionRepo subscriptionRepo;
	
	@Autowired
	private AccountRepository accountRepository;
	
	private AppUser admin;
	private AppUser james;
	private AppUser john;
	
	private Subscription S2G;
	private Subscription S3G;
	private Subscription S4G;
	
	@Override
	public void run(String... args) throws Exception {
		
		this.admin = new AppUser("Admin", "Admin", "admin@gmail.com", "password");
		this.james = new AppUser("James", "Bond", "james@gmail.com", "password");
		this.john = new AppUser("John", "Anelka", "john@gmail.com", "password");
		
		this.S2G = new Subscription(SubscriptionType.S2G, "This is the slowest internet connection that we have");
		this.S3G = new Subscription(SubscriptionType.S3G, "This is the average internet connection that we have");
		this.S4G = new Subscription(SubscriptionType.S4G, "This is the most powerful internet connection that we have");
		

		Account account = new Account(AccountStatus.LOCKED);
		account.setBalance(0);
		
		Account jamesAcc = new Account(AccountStatus.ACTIVE);
		jamesAcc.setBalance(2500);
		
		Account johnAcc = new Account(AccountStatus.ACTIVE);
		johnAcc.setBalance(3400);
		
		// saving the accounts
		
		this.accountRepository.saveAll(List.of(account, jamesAcc, johnAcc));
		
		this.subscriptionRepo.saveAll(List.of(S2G, S3G, S4G));
		
		this.admin.getSubscriptions().add(S4G);
		this.admin.getSubscriptions().add(S3G);
		
		this.james.getSubscriptions().add(S2G);
		
		this.john.getSubscriptions().add(S2G);
		this.john.getSubscriptions().add(S3G);
		
		this.admin.setRole(Role.ADMIN);
		this.james.setRole(Role.USER);
		this.john.setRole(Role.USER);
		
		admin.setAccount(account);
		james.setAccount(jamesAcc);
		john.setAccount(johnAcc);
		
		this.appUserRepo.saveAllAndFlush(List.of(admin, james, john));
	}

}
