package com.sgen.breakmedown.breakmedown.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgen.breakmedown.breakmedown.model.AppUser;
import com.sgen.breakmedown.breakmedown.model.Subscription;
import com.sgen.breakmedown.breakmedown.model.account.Account;
import com.sgen.breakmedown.breakmedown.privilege.role.Role;
import com.sgen.breakmedown.breakmedown.repository.AppUserRepo;

@Service
public class AppUserSubscriptionService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private AppUserRepo appUserRepo;
	
	public boolean checkAppUserEligibility(Optional<AppUser> appUser, Optional<Subscription> subscription) {
		
		if(appUser.isPresent() && subscription.isPresent()) {
			AppUser fetchedAppUser = appUser.get();
			
			if(fetchedAppUser.getRole().equals(Role.ADMIN)) {
				String exceptionMsg = "The AppUser with Role %s is not eligible for subscription";
				throw new RuntimeException(String.format(exceptionMsg, fetchedAppUser.getRole()));
			}else {
				Account appUserAccount = fetchedAppUser.getAccount();
				if(appUserAccount.getBalance() > subscription.get().getFees()) {
					Optional<Subscription> existingSub = fetchedAppUser.getSubscriptions().stream()
								.filter((sub) -> sub.getSubscriptionType().equals(subscription.get().getSubscriptionType()))
								.findFirst();
					if(existingSub.isEmpty()) {
						fetchedAppUser.getSubscriptions().add(subscription.get());
						logger.info("Subscribing {} to AppUser {}", subscription.get().getSubscriptionType(), fetchedAppUser.getFirstName());
						this.appUserRepo.save(fetchedAppUser);
						return true;
					}else {
						throw new RuntimeException("The Subscription is already taken");
					}
					
				}else {
					throw new RuntimeException("Insufficient balance for subscription " +subscription.get().getSubscriptionType());
				}
			}
		}else {
			String exceptionMsg = "The AppUser or The Subscription type is not available";
			throw new RuntimeException(exceptionMsg);
		}
	}
	
	

}
