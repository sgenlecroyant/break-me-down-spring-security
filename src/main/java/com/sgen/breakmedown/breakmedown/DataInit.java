package com.sgen.breakmedown.breakmedown;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sgen.breakmedown.breakmedown.model.AppUser;
import com.sgen.breakmedown.breakmedown.model.Subscription;
import com.sgen.breakmedown.breakmedown.repository.AppUserRepo;
import com.sgen.breakmedown.breakmedown.subscriptionType.SubscriptionType;

@Component
public class DataInit implements CommandLineRunner{
	
	@Autowired
	private AppUserRepo appUserRepo;
	
	@Autowired
	private 
	
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
		
		this.S2G = new Subscription(SubscriptionType.S2G, "This is the slowest internet connection that we have", admin);
		this.S3G = new Subscription(SubscriptionType.S3G, "This is the average internet connection that we have", james);
		this.S3G = new Subscription(SubscriptionType.S4G, "This is the most powerful internet connection that we have", john);
		
//		this.S2G.setAppUser(admin);
		this.appUserRepo.saveAllAndFlush(List.of(admin, james, john));
		this
	}

}
