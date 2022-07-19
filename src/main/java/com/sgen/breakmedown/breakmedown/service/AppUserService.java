package com.sgen.breakmedown.breakmedown.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgen.breakmedown.breakmedown.helperclass.AppUserDaoHelper;
import com.sgen.breakmedown.breakmedown.model.AppUser;
import com.sgen.breakmedown.breakmedown.model.account.Account;
import com.sgen.breakmedown.breakmedown.repository.AppUserRepo;
import com.sgen.breakmedown.breakmedown.requestTemplate.AppUserRegistrationRequest;
import com.sgen.breakmedown.breakmedown.requestTemplate.AppUserUpdateRequest;
import com.sgen.breakmedown.breakmedown.requestTemplate.AuthenticationRequest;
import com.sgen.breakmedown.breakmedown.staticresponse.StaticAuthResponse;

@Service
public class AppUserService implements AppUserDaoHelper{

	private AppUserRepo appUserRepo;
	
	private AccountService accountService;
	
	
	public AppUserService(AppUserRepo appUserRepo, AccountService accountService) {
		this.appUserRepo = appUserRepo;
		this.accountService = accountService;
	}
	
	@Override
	public Optional<AppUser> registerAppUser(AppUserRegistrationRequest registrationRequest) {
		AppUser appUser = new AppUser(
				registrationRequest.getFirstName(), 
				registrationRequest.getLastName(), 
				registrationRequest.getUsername(), 
				registrationRequest.getPassword());
		Account account = appUser.getAccount();
		this.accountService.registerAccount(account);
				AppUser registeredAppUser = this.appUserRepo.save(appUser);
				
		return Optional.of(registeredAppUser);
	}

	@Override
	public Optional<AppUser> findAppUserById(Integer id) {
		Optional<AppUser> appUser = this.appUserRepo.findById(id);
		if(appUser.isPresent()) {
			return appUser;
		}else {
			throw new RuntimeException("the user could not be found!");
		}
	}

	@Override
	public boolean deleteAppUserById(Integer id) {
		Optional<AppUser> appUser = this.appUserRepo.findById(id);
		if(appUser.isPresent()) {
			this.appUserRepo.deleteById(id);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<AppUser> findAllAppUsers() {
		return this.appUserRepo.findAll();
	}

	@Override
	public Optional<AppUser> updateAppUser(AppUserUpdateRequest updateRequest) {
		Optional<AppUser> appUser = this.appUserRepo.findById(updateRequest.getId());
		if(appUser.isPresent()) {
			AppUser userToUpdate = appUser.get();
			userToUpdate.setFirstName(updateRequest.getFirstName());
			userToUpdate.setLastName(updateRequest.getLastName());
			userToUpdate.setUsername(updateRequest.getUsername());
			userToUpdate.setPassword(updateRequest.getPassword());
			this.appUserRepo.save(userToUpdate);
			return Optional.of(userToUpdate);
		}else {
			throw new RuntimeException("The operation is not allowed!");
		}
	}

	public String authenticate(AuthenticationRequest authenticationRequest) {
		Optional<AppUser> nullableFetchedUser = this.appUserRepo.findByUsername(authenticationRequest.getUsername());
		AppUser fetchedAppUser = nullableFetchedUser.get();
		if(fetchedAppUser.getUsername().equals(authenticationRequest.getUsername())) {
			if(fetchedAppUser.getPassword().equals(authenticationRequest.getPassword())) {
				return StaticAuthResponse.SUCCESS.name();
			}else {
				return StaticAuthResponse.INCORRECT_PASSWORD.name();
			}
		}
		return StaticAuthResponse.INVALID_USERNAME.name();
	}

}
