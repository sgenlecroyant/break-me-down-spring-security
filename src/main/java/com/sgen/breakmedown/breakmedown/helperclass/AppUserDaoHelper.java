package com.sgen.breakmedown.breakmedown.helperclass;

import java.util.List;
import java.util.Optional;

import com.sgen.breakmedown.breakmedown.model.AppUser;

public interface AppUserDaoHelper {
	
	public Optional<AppUser> registerAppUser(AppUser appUser);
	public Optional<AppUser> findAppUserById(Integer id);
	public boolean deleteAppUserById(Integer id);
	public List<AppUser> findAllAppUsers();
}
