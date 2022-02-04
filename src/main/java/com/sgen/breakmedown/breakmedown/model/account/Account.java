package com.sgen.breakmedown.breakmedown.model.account;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sgen.breakmedown.breakmedown.model.AppUser;
import com.sgen.breakmedown.breakmedown.model.account.accountEnum.AccountStatus;

@Entity(name = "accounts")
@Table(name = "accounts")
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private double balance;
	
	@Enumerated(EnumType.STRING)
	private AccountStatus accountStatus;
	
	@OneToOne(mappedBy = "account")
	private AppUser appUser;
	
	public Account() {
		// TODO Auto-generated constructor stub
	}
	
	public Account(AccountStatus accountStatus) {
		this.accountStatus = accountStatus;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public void setAccountStatus(AccountStatus accountStatus) {
		this.accountStatus = accountStatus;
	}
	
	public AccountStatus getAccountStatus() {
		return accountStatus;
	}
	
	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}
	
	public AppUser getAppUser() {
		return appUser;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getBalance() {
		return balance;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountStatus, appUser, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return accountStatus == other.accountStatus && Objects.equals(appUser, other.appUser)
				&& Objects.equals(id, other.id);
	}
	
	

}
