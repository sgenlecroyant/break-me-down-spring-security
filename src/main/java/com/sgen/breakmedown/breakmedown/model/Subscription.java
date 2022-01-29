package com.sgen.breakmedown.breakmedown.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sgen.breakmedown.breakmedown.subscriptionType.SubscriptionType;

@Entity(name = "subscriptions")
@Table(name = "subscriptions")
public class Subscription {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private SubscriptionType subscriptionType;
	private String details;
	
	@ManyToOne
	private AppUser appUser;
	
	public Subscription() {
		// TODO Auto-generated constructor stub
	}

	public Subscription(SubscriptionType subscriptionType, String details, AppUser appUser) {
		this.subscriptionType = subscriptionType;
		this.details = details;
		this.appUser = appUser;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public SubscriptionType getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(SubscriptionType subscriptionType) {
		this.subscriptionType = subscriptionType;
	}
	
	
}
