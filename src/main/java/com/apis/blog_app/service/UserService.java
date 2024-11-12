package com.apis.blog_app.service;

import com.apis.blog_app.model.User;

public interface UserService {

	public void create(User user);
	public void update(User user);
	public User get(String name);
	public User delete(String name);
}
