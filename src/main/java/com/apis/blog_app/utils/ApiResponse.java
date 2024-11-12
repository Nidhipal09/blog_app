package com.apis.blog_app.utils;

public class ApiResponse {

	private String message;
	private Object object;
	
	public ApiResponse(String message) {
		super();
		this.message = message;
	}
	public ApiResponse(String message, Object object) {
		super();
		this.message = message;
		this.object = object;
	}
	public ApiResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
