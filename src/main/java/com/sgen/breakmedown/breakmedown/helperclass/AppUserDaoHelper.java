package com.sgen.breakmedown.breakmedown.helperclass;

import java.util.List;
import java.util.Optional;

import com.sgen.breakmedown.breakmedown.model.AppUser;
import com.sgen.breakmedown.breakmedown.requestTemplate.AppUserRegistrationRequest;

public interface AppUserDaoHelper {
	
	public Optional<AppUser> registerAppUser(AppUserRegistrationRequest registrationRequest);
	public Optional<AppUser> findAppUserById(Integer id);
	public boolean deleteAppUserById(Integer id);
	public List<AppUser> findAllAppUsers();
}
