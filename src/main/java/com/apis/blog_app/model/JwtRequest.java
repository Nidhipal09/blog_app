package com.apis.blog_app.model;

public class JwtRequest {

	String name;
	String password;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public JwtRequest(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	public JwtRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
