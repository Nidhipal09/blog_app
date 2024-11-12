package com.apis.blog_app.controller;

import java.util.Map;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apis.blog_app.model.Category;
import com.apis.blog_app.model.User;
import com.apis.blog_app.service.CategoryService;
import com.apis.blog_app.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
@Tag(name = "Category APIs")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<Category> create(@Valid @RequestBody Category category) {
		categoryService.create(category);
		return new ResponseEntity<Category>(categoryService.get(category.getName()), HttpStatus.CREATED);
	}
	
	@PutMapping("/")
	public ResponseEntity<Category> update(@Valid @RequestBody Category category) {
		categoryService.update(category);
		return new ResponseEntity<Category>(categoryService.get(category.getName()), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/")
	public ResponseEntity<Category> delete(@Valid @RequestParam String name) {
		Category category = categoryService.delete(name);
		return new ResponseEntity<Category>(category, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/")
	public ResponseEntity<Category> get(@Valid @RequestParam String name) {
		Category category = categoryService.get(name);
		return new ResponseEntity<Category>(category, HttpStatus.ACCEPTED);
	}
}
