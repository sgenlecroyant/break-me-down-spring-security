package com.sgen.breakmedown.breakmedown.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "subscriptions")
@Table(name = "subscriptions")
public class Subscription {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private StringBuilder details;
	
	@ManyToOne
	private AppUser appUser;
	
	public Subscription() {
		// TODO Auto-generated constructor stub
	}

	public Subscription(StringBuilder details, AppUser appUser) {
		this.details = details;
		this.appUser = appUser;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public StringBuilder getDetails() {
		return details;
	}

	public void setDetails(StringBuilder details) {
		this.details = details;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}
	
	
	
	
	
}
