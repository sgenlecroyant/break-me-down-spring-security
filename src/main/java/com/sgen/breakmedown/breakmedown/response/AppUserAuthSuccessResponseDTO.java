package com.sgen.breakmedown.breakmedown.response;

import com.sgen.breakmedown.breakmedown.model.AppUser;
import com.sgen.breakmedown.breakmedown.model.account.accountEnum.AccountStatus;
import com.sgen.breakmedown.breakmedown.privilege.role.Role;

public class AppUserAuthSuccessResponseDTO {

	private String firstName;
	private String lastName;
	private String username;
	private Role role = Role.USER;
	private double accountBalance;
	private AccountStatus accountStatus;
	
//	private static AppUserAuthSuccessResponseDTO responseDTO;
	
	public AppUserAuthSuccessResponseDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public AccountStatus getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(AccountStatus accountStatus) {
		this.accountStatus = accountStatus;
	}
	
	public static AppUserAuthSuccessResponseDTO builder(AppUser appUser) {
		AppUserAuthSuccessResponseDTO responseDTO = 
				new AppUserAuthSuccessResponseDTO();
		responseDTO.setFirstName(appUser.getFirstName());
		responseDTO.setLastName(appUser.getLastName());
		responseDTO.setUsername(appUser.getUsername());
		responseDTO.setRole(appUser.getRole());
		responseDTO.setAccountBalance(appUser.getAccount().getBalance());
		responseDTO.setAccountStatus(appUser.getAccount().getAccountStatus());
		return responseDTO;
	}
	
	public AppUserAuthSuccessResponseDTO build() {
		return this;
	}

}
