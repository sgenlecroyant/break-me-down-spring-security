package com.sgen.breakmedown.breakmedown.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.sgen.breakmedown.breakmedown.model.AppUser;

@DataJpaTest(showSql = true)
class AppUserRepoTest {
	
	@Autowired
	private AppUserRepo appUserRepo;
	
	@Test
	void test() {
		
		AppUser appUser = 
				new AppUser("Hello", "World", "hello@gmail.com", "password");
		
		AppUser savedAppUser = this.appUserRepo.save(appUser);
		
		Optional<AppUser> targetAppUser = this.appUserRepo.findByUsername(appUser.getUsername());
		
		assertThat(savedAppUser.getUsername()).isEqualTo(targetAppUser.get().getUsername());
	}

}
