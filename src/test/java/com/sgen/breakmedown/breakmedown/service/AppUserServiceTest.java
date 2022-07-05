package com.sgen.breakmedown.breakmedown.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

import java.util.List;
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
	void testDeleteAppUserById_PASS() {
		AppUser appUser = 
				new AppUser("Hello", "World", "hello@gmail.com", "password");
		appUser.setId(2);
		
//		Mockito.when(this.appUserRepo.findById(2)).thenReturn(Optional.of(appUser));
		
		BDDMockito.given(this.appUserRepo.findById(2)).willReturn(Optional.of(appUser));
		
		boolean wasAppUserDeleted = this.appUserService.deleteAppUserById(2);
		
		Mockito.verify(this.appUserRepo, times(1)).deleteById(2);
		
		assertTrue(wasAppUserDeleted);	
		
	}
	
	@Test
	void testDeleteAppUserById_FAILURE() {
		AppUser appUser = 
				new AppUser("Hello", "World", "hello@gmail.com", "password");
		appUser.setId(2);
		
//		Mockito.when(this.appUserRepo.findById(2)).thenReturn(Optional.of(appUser));
		
		BDDMockito.given(this.appUserRepo.findById(2)).willReturn(Optional.ofNullable(null));
		
		boolean wasAppUserDeleted = this.appUserService.deleteAppUserById(2);
		
		Mockito.verify(this.appUserRepo, times(0)).deleteById(2);
		
		assertFalse(wasAppUserDeleted);	
		
	}

	@Test
	void testFindAllAppUsers() {
		
		List<AppUser> appUsers = List.of(new AppUser("Franck", "Sgen", "sgen@gmail.com", "password"));
		Mockito.when(this.appUserRepo.findAll()).thenReturn(appUsers);
		
		List<AppUser> fetchedAppUsesrs = this.appUserService.findAllAppUsers();
		
		assertThat(fetchedAppUsesrs.equals(appUsers));
	}

	@Test
	void testUpdateAppUser() {
//		fail("Not yet implemented");
	}

}
