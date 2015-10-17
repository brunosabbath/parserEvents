package com.bruno.modelporra;

import org.springframework.data.annotation.Id;

public class BaseUser {

	@Id
	private String id;
	private String username;
	private String password;
	private String email;
	private String realName;
	
	public BaseUser(String username, String password, String email, String realName) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.realName = realName;
	}
	
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	
	@Override
	public String toString() {
		return "id: " + id + "\tusername: " + username + "\tpassword: " + password + "\temail: " + email + "\tname: " + realName;
	}
	
}
