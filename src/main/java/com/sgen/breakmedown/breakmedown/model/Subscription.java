package com.sgen.breakmedown.breakmedown.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
	
	@ManyToMany(mappedBy = "subscriptions")
	private List<AppUser> appUsers = new ArrayList<>();
	
	public Subscription() {
		// TODO Auto-generated constructor stub
	}

	public Subscription(SubscriptionType subscriptionType, String details) {
		this.subscriptionType = subscriptionType;
		this.details = details;
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

	public List<AppUser> getAppUsers() {
		return appUsers;
	}

	public void setAppUsers(List<AppUser> appUsers) {
		this.appUsers = appUsers;
	}

	public SubscriptionType getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(SubscriptionType subscriptionType) {
		this.subscriptionType = subscriptionType;
	}
	
	
}
