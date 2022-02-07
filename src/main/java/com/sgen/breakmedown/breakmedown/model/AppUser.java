package com.sgen.breakmedown.breakmedown.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sgen.breakmedown.breakmedown.model.account.Account;
import com.sgen.breakmedown.breakmedown.privilege.permission.Permission;
import com.sgen.breakmedown.breakmedown.privilege.role.Role;

@Entity(name = "AppUsers")
@Table(name = "AppUsers")
public class AppUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String firstName;
	private String lastName;
	@Column(unique = true, updatable = true, insertable = true, nullable = false)
	private String username;
	private String password; // still using the raw passwords cuz we have not started yet anything related to security
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@ManyToMany(fetch = FetchType.EAGER, targetEntity = Subscription.class)
	@JsonIgnore(value = true)
	private Set<Subscription> subscriptions = new HashSet<>();
	
	@OneToOne
	private Account account;
	public AppUser() {
		// default constructor for database mapping
	}

	public AppUser(String firstName, String lastName, String username, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(Set<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}
	
	public Set<? extends Permission> getAuthorities(){
		return this.role.getPermissions();
	}
	
	public Role getRole() {
		return this.role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	public void setAccount(Account account) {
		this.account = account;
	}
	public Account getAccount() {
		return account;
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, id, lastName, password, role, subscriptions, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppUser other = (AppUser) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(id, other.id)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(password, other.password)
				&& role == other.role && Objects.equals(subscriptions, other.subscriptions)
				&& Objects.equals(username, other.username);
	}
	
	
	
	
	
}
