package com.sgen.breakmedown.breakmedown.controller.appusersubscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sgen.breakmedown.breakmedown.model.AppUser;
import com.sgen.breakmedown.breakmedown.service.AppUserService;
import com.sgen.breakmedown.breakmedown.service.SubscriptionService;

@RestController
@RequestMapping(value = "/management/api/v1")
public class AppUserSubscriptionController {
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private SubscriptionService subscriptionService;
	
	@PostMapping(value = "/assign/{appUserId}/sub/{subsId}")
	public ResponseEntity<String> assignSubscription(@PathVariable Integer appUserId, @PathVariable Integer subsId){
		
		this.appUserService.findAppUserById(appUserId);
		return null;
	}

}
