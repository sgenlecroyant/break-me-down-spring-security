package com.sgen.breakmedown.breakmedown.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgen.breakmedown.breakmedown.model.AppUser;
import com.sgen.breakmedown.breakmedown.model.Subscription;
import com.sgen.breakmedown.breakmedown.repository.SubscriptionRepo;

@Service
public class SubscriptionService {
	
	@Autowired
	private SubscriptionRepo subscriptionRepo;
	
	public List<Subscription> fetchAllSubscriptions(){
		return this.subscriptionRepo.findAll();
	}
	
	public boolean deleteSubscriptionById(Integer id) {
		Optional<Subscription> subscription = this.subscriptionRepo.findById(id);
		
		if(subscription.isPresent()) {
			this.subscriptionRepo.deleteById(id);
			return true;
		}else {
			throw new IllegalStateException("subscription not available");
		}
	}
	
	public Optional<Subscription> addSubcription(Subscription subscription){
		 Subscription addedSubscription = this.subscriptionRepo.save(subscription);
		 return Optional.of(addedSubscription);
	}
	

	public Subscription updateSubscription(Subscription subscription) {
		Optional<Subscription> subscriptionToUpdate = this.subscriptionRepo.findById(subscription.getId());
		if(subscriptionToUpdate.isPresent()) {
			Subscription thisSubscription = subscriptionToUpdate.get();
			
			thisSubscription.setAppUsers(subscription.getAppUsers());
			thisSubscription.setDetails(subscription.getDetails());
			thisSubscription.setSubscriptionType(subscription.getSubscriptionType());
			Subscription updatedSubscription = this.subscriptionRepo.save(subscription);
			return updatedSubscription;
		}else {
			throw new RuntimeException("Unable to update the subscription!");
		}
	}
	
	public Optional<Subscription> fetchSubcriptionById(Integer id){
		Optional<Subscription> subscription = this.subscriptionRepo.findById(id);
		if(subscription.isPresent()) {
			return subscription;
		}else {
			throw new RuntimeException("subscription not available!");
		}
	}
	
}
