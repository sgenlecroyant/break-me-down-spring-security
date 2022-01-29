package com.sgen.breakmedown.breakmedown.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sgen.breakmedown.breakmedown.model.Subscription;
import com.sgen.breakmedown.breakmedown.service.SubscriptionService;

@RestController
@RequestMapping(value = "/management/api/v1")
public class SubscriptionController {
	
	@Autowired
	private SubscriptionService subscriptionService;
	
	@GetMapping(value = "/subscriptions/{id}")
	public ResponseEntity<Subscription> fetchSubscriptionById(@PathVariable Integer id){
		Optional<Subscription> subscription = this.subscriptionService.fetchSubcriptionById(id);
		return new ResponseEntity<Subscription>(subscription.get(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/subscriptions")
	public ResponseEntity<List<Subscription>> fetchAllSubscriptions(){
		 List<Subscription> allSubscriptions = this.subscriptionService.fetchAllSubscriptions();
		 return new ResponseEntity<List<Subscription>>(allSubscriptions, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/subscriptions/{id}")
	public ResponseEntity<String> deleteSubscriptionById(@PathVariable Integer id){
		boolean wasSubscriptionDeleted = this.subscriptionService.deleteSubscriptionById(id);
		if(wasSubscriptionDeleted) {
			return new ResponseEntity<String>("Subscription Deleted", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Subscription Delettion Failed", HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PatchMapping(value = "/subscriptions")
	public ResponseEntity<String> updateSubscription(@RequestBody Subscription subscription){
		Subscription updatedSubscription = this.subscriptionService.updateSubscription(subscription);
		return new ResponseEntity<String>("subscription updated successfully!", HttpStatus.OK);
	}
	
	@PostMapping(value = "/subscriptions")
	public ResponseEntity<Subscription> addSubscription(@RequestBody Subscription subscription){
		Optional<Subscription> addedSubscription = this.subscriptionService.addSubcription(subscription);
		return ResponseEntity.of(addedSubscription);
	}
}
