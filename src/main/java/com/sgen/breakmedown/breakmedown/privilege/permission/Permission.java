package com.sgen.breakmedown.breakmedown.privilege.permission;

public enum Permission {
	SERVICE_READ("SERVICE::READ"),
	SERVICE_WRITE("SERVICE::WRITE");
	
	String permission;
	
	private Permission(String permission) {
		this.permission = permission;
	}
	
	public void setPermission(String permission) {
		this.permission = permission;
	}
	
	public String getPermission() {
		return permission;
	}
}
