package com.sgen.breakmedown.breakmedown.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgen.breakmedown.breakmedown.model.AppUser;
import com.sgen.breakmedown.breakmedown.model.Subscription;
import com.sgen.breakmedown.breakmedown.privilege.role.Role;

@Service
public class AppUserSubscriptionService {
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private SubscriptionService subscriptionService;
	
	public boolean checkAppUserEligibility(Optional<AppUser> appUser, Optional<Subscription> subscription) {
		
		if(appUser.isPresent() && subscription.isPresent()) {
			AppUser fetchedAppUser = appUser.get();
			
			if(fetchedAppUser.getRole().equals(Role.ADMIN)) {
				String exceptionMsg = "The AppUser with Role %s is not eligible for subscription";
				throw new RuntimeException(String.format(exceptionMsg, fetchedAppUser.getRole()));
			}else {
				fetchedAppUser.getSubscriptions().add(subscription.get());
				return true;
			}
		}else {
			String exceptionMsg = "The AppUser or The Subscription type is not available";
			throw new RuntimeException(exceptionMsg);
		}
	}
	
	

}
