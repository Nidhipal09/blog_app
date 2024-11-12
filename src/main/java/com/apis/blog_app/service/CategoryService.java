package com.apis.blog_app.service;

import com.apis.blog_app.model.Category;
import com.apis.blog_app.model.User;

public interface CategoryService {

	public void create(Category category);
	public void update(Category category);
	public Category get(String name);
	public Category delete(String name);
}
