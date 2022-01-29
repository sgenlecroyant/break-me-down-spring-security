package com.sgen.breakmedown.breakmedown.controller.appusersubscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sgen.breakmedown.breakmedown.service.AppUserService;
import com.sgen.breakmedown.breakmedown.service.SubscriptionService;

@RestController
@RequestMapping(value = "/management/api/v1")
public class AppUserSubscriptionController {
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private SubscriptionService subscriptionService;
	
	public ResponseEntity<String> 

}
