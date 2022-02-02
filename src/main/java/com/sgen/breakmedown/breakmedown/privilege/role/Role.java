package com.sgen.breakmedown.breakmedown.privilege.role;

import java.util.Set;

import com.sgen.breakmedown.breakmedown.privilege.permission.Permission;

public enum Role {
	ADMIN(Set.of(Permission.SERVICE_READ, Permission.SERVICE_WRITE)),
	USER(Set.of(Permission.SERVICE_READ));
	
	private Set<Permission> permissions;
	
	private Role(Set<Permission> permissions) {
		this.permissions = permissions;
	}
	
	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}
	public Set<Permission> getPermissions() {
		return permissions;
	}
	
	
}
