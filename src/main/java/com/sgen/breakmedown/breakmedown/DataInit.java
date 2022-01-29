package com.sgen.breakmedown.breakmedown;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sgen.breakmedown.breakmedown.model.AppUser;
import com.sgen.breakmedown.breakmedown.model.Subscription;
import com.sgen.breakmedown.breakmedown.repository.AppUserRepo;

@Component
public class DataInit implements CommandLineRunner{
	
	@Autowired
	private AppUserRepo appUserRepo;
	
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
		
//		this.S2G = new Subscription(null, admin)
		
		this.appUserRepo.saveAllAndFlush(List.of(admin, james, john));
	}

}
