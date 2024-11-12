package com.apis.blog_app.exception;

public class ResourceAlreadyExists extends RuntimeException {

	public ResourceAlreadyExists(String resource, String name) {
		super(resource+" "+name+" already exists.");
	}
}
