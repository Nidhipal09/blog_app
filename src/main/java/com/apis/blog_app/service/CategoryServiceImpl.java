package com.apis.blog_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apis.blog_app.exception.ResourceAlreadyExists;
import com.apis.blog_app.exception.ResourceNotFound;
import com.apis.blog_app.model.Category;
import com.apis.blog_app.model.User;
import com.apis.blog_app.repository.CategoryRepository;
import com.apis.blog_app.repository.UserRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public void create(Category category) {
		Category category_found = categoryRepository.findByName(category.getName());
		
		if (category_found != null) {
			throw new ResourceAlreadyExists("Category", category.getName());
		}
		categoryRepository.save(category);

	}
	
	@Override
	public void update(Category category) {
		Category category_found = categoryRepository.findByName(category.getName());
		if (category_found == null) {
			throw new ResourceNotFound("Category", category.getName());
		}
		category_found.setName(category.getName());
		category_found.setDescription(category.getDescription());
		categoryRepository.save(category_found);

	}

	@Override
	public Category get(String name) {
		Category category_found = categoryRepository.findByName(name);
		if (category_found == null) {
			throw new ResourceNotFound("Category", name);
		}
		return category_found;
	}

	@Override
	public Category delete(String name) {
		Category category_found = categoryRepository.findByName(name);
		if (category_found == null) {
			throw new ResourceNotFound("Category", name);
		}
		categoryRepository.delete(category_found);
		return category_found;
	}

}
