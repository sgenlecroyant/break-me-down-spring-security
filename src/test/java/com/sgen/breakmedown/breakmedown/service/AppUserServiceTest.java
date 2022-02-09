package com.sgen.breakmedown.breakmedown.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.sgen.breakmedown.breakmedown.model.AppUser;
import com.sgen.breakmedown.breakmedown.model.account.Account;
import com.sgen.breakmedown.breakmedown.repository.AppUserRepo;
import com.sgen.breakmedown.breakmedown.requestTemplate.AppUserRegistrationRequest;

@SpringBootTest
class AppUserServiceTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Mock
	private AppUserRepo appUserRepo;
	
	@Mock
	private AccountService accountService;
	
	@InjectMocks
	private AppUserService appUserService;
	
	private AutoCloseable autoCloseable;
	
	
	@BeforeEach
	public void setUp() {
		logger.info("Setting up the testing environment");
		this.autoCloseable = MockitoAnnotations.openMocks(this);
		this.appUserService = new AppUserService(appUserRepo, accountService);
	}

	@AfterEach
	public void destroy() throws Exception {
		logger.info("Closing connections ...");
		this.autoCloseable.close();
	}

	@Test
	void testRegisterAppUser() {
		
		AppUserRegistrationRequest request =
				new AppUserRegistrationRequest("Hello", "World", "hello@gmail.com", "password");
	
		
		AppUser appUser = 
				new AppUser(request.getFirstName(), request.getLastName(), request.getUsername(), request.getPassword());
		appUser.setId(2);
		
		Mockito.when(this.appUserRepo.save(any(AppUser.class)))
				.thenReturn(appUser);
		
		Optional<AppUser> registerAppUser = this.appUserService.registerAppUser(request);
		
		ArgumentCaptor<AppUser> registrationArgumentCaptor = 
				ArgumentCaptor.forClass(AppUser.class);
		
		Mockito.verify(this.appUserRepo).save(registrationArgumentCaptor.capture());
		
		AppUser capturedAppUser = registrationArgumentCaptor.getValue();
		logger.info("Captured AppUser: "+capturedAppUser);
		assertThat(capturedAppUser).isNotNull();
		
		assertThat(appUser.getLastName()).isEqualTo(registerAppUser.get().getLastName());
		
		Mockito.verify(this.appUserRepo, Mockito.times(1)).save(any());
		
		
	}

	@Test
	void testFindAppUserById_PASS() {
		logger.info("Testing FIndAppUserById");
		
		AppUser appUser = 
				new AppUser("Hello", "World", "hello@gmail.com", "password");
		appUser.setId(2);
		
		Mockito.when(this.appUserRepo.findById(appUser.getId()))
		.thenReturn(Optional.of(appUser));
		
		Optional<AppUser> fetchedAppUser = this.appUserService.findAppUserById(appUser.getId());
		
		assertThat(fetchedAppUser.get().getLastName()).isEqualTo(appUser.getLastName())
		.isInstanceOf(String.class);
	}
	
	@Test
	void testFindAppUserById_FAILURE() {
		logger.info("Testing FIndAppUserById");
		
		AppUser appUser = 
				new AppUser("Hello", "World", "hello@gmail.com", "password");
		appUser.setId(2);
		
		BDDMockito.given(this.appUserRepo.findById(appUser.getId()))
		.willReturn(Optional.empty());
		
		assertThatThrownBy(() -> this.appUserService.findAppUserById(2))
		.hasMessage("the user could not be found!")
		.isExactlyInstanceOf(RuntimeException.class);
	}

	@Test
	void testDeleteAppUserById() {
//		fail("Not yet implemented");
	}

	@Test
	void testFindAllAppUsers() {
//		fail("Not yet implemented");
	}

	@Test
	void testUpdateAppUser() {
//		fail("Not yet implemented");
	}

}
