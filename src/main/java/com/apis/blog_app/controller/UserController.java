package com.apis.blog_app.controller;

import java.util.Map;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.apis.blog_app.model.User;
import com.apis.blog_app.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
@Tag(name = "User APIs")
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<User> create(@Valid @RequestBody User user) {
		userService.create(user);
		return new ResponseEntity<User>(userService.get(user.getName()), HttpStatus.CREATED);
	}
	
//	@PutMapping("/")
//	public ResponseEntity<User> update(@Valid @RequestBody User user) {
//		userService.update(user);
//		return new ResponseEntity<User>(userService.get(user.getName()), HttpStatus.ACCEPTED);
//	}
	
	@PutMapping("/")
	public User update(@Valid @RequestBody User user) {
		userService.update(user);
		return user;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/")
	public ResponseEntity<User> delete(@Valid @RequestParam String name) {
		User user = userService.delete(name);
		return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/")
	public ResponseEntity<User> get(@Valid @RequestParam String name) {
		User user = userService.get(name);
		return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
	}
}
