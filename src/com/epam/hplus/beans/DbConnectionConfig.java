package com.epam.hplus.beans;

public class DbConnectionConfig {
	private String url;
	private String user;
	private String password;
	
	public DbConnectionConfig(String url, String user, String password) {
		super();
		this.url = url;
		this.user = user;
		this.password = password;
	}
	
	public String getUrl() {
		return url;
	}
	
	public String getUser() {
		return user;
	}
	
	public String getPassword() {
		return password;
	}
}
