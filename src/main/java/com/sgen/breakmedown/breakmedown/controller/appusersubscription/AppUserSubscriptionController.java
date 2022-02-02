package com.sgen.breakmedown.breakmedown.controller.appusersubscription;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sgen.breakmedown.breakmedown.model.AppUser;
import com.sgen.breakmedown.breakmedown.model.Subscription;
import com.sgen.breakmedown.breakmedown.service.AppUserService;
import com.sgen.breakmedown.breakmedown.service.AppUserSubscriptionService;
import com.sgen.breakmedown.breakmedown.service.SubscriptionService;

@RestController
@RequestMapping(value = "/management/api/v1")
public class AppUserSubscriptionController {
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private SubscriptionService subscriptionService;
	
	@Autowired
	private AppUserSubscriptionService appUserSubscriptionService;
	
	@GetMapping(value = "/assign/appuser/{appUserId}/sub/{subsId}")
	public ResponseEntity<String> assignSubscription(@PathVariable Integer appUserId, @PathVariable Integer subsId){
		
		Optional<AppUser> appUser = this.appUserService.findAppUserById(appUserId);
		Optional<Subscription> subscription = this.subscriptionService.fetchSubcriptionById(subsId);
		
		boolean isEligibleForSubscription = this.appUserSubscriptionService.checkAppUserEligibility(appUser, subscription);
		
		if(isEligibleForSubscription) {
			System.out.println("The AppUser " +appUser+ " is eligible with auth: " +appUser.get().getAuthorities());
		}else {
			System.out.println("The AppUser " +appUser+ " is not eligible with auth: " +appUser.get().getAuthorities());

		}
		
		return ResponseEntity.ok().body("Eligibility check is Done!");
	}

}
