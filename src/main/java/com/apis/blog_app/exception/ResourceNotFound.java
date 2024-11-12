package com.apis.blog_app.exception;

public class ResourceNotFound extends RuntimeException {

	public ResourceNotFound(String resource, String name) {
		super(resource+" "+name+" doesn't exist.");
	}
}
