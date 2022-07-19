package com.sgen.breakmedown.breakmedown.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sgen.breakmedown.breakmedown.model.AppUser;
import com.sgen.breakmedown.breakmedown.requestTemplate.AppUserRegistrationRequest;
import com.sgen.breakmedown.breakmedown.requestTemplate.AppUserUpdateRequest;
import com.sgen.breakmedown.breakmedown.requestTemplate.AuthenticationRequest;
import com.sgen.breakmedown.breakmedown.response.AppUserAuthSuccessResponseDTO;
import com.sgen.breakmedown.breakmedown.service.AppUserService;

@RestController
@RequestMapping(value = "/management/api/v1")
public class AppUserController {
	
	@Autowired
	private AppUserService appUserService;
	
	@GetMapping(value = "/appusers")
	public ResponseEntity<List<AppUser>> fetchAllUsers(){
		List<AppUser> allAppUsers = this.appUserService.findAllAppUsers();
		
		allAppUsers.stream()
				.forEach((appUser) -> {
					System.out.println(appUser.getFirstName()+ " is a/an " +appUser.getRole()+
							"with auth: " +appUser.getAuthorities());
				});
		return ResponseEntity.ok().body(allAppUsers);
	}
	
	@PostMapping(value = "/appusers", 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppUser> registerAppUser(@RequestBody AppUserRegistrationRequest registrationRequest){
		Optional<AppUser> registeredAppUser = this.appUserService.registerAppUser(registrationRequest);
		return ResponseEntity.of(registeredAppUser);
	}
	
	@GetMapping(value = "/appusers/{id}")
	public ResponseEntity<AppUser> fetchAppUserById(@PathVariable Integer id){
		Optional<AppUser> appUser = this.appUserService.findAppUserById(id);
		return new ResponseEntity<AppUser>(appUser.get(), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/appusers/{id}")
	public ResponseEntity<Boolean> deleteAppUserById(@PathVariable Integer id){
		@SuppressWarnings(value = "unused")
		boolean wasAppUserDeleted = this.appUserService.deleteAppUserById(id);
			return new ResponseEntity<Boolean>(wasAppUserDeleted, HttpStatus.OK);
	}
	
	@PatchMapping(value = "/appusers")
	public ResponseEntity<AppUser> updateAppUser(@RequestBody AppUserUpdateRequest updateRequest){
		Optional<AppUser> updatedUser = this.appUserService.updateAppUser(updateRequest);
		ResponseEntity.of(updatedUser);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping(value = "/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppUserAuthSuccessResponseDTO> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
		System.out.println("authenticating ...");
		System.out.println(authenticationRequest);
		AppUserAuthSuccessResponseDTO authenticatedAppUser = this.appUserService.authenticate(authenticationRequest);
		return new ResponseEntity<AppUserAuthSuccessResponseDTO>(authenticatedAppUser, HttpStatus.ACCEPTED);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
