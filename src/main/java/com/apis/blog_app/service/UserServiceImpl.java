package com.apis.blog_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.apis.blog_app.config.AppConstants;
import com.apis.blog_app.exception.ResourceAlreadyExists;
import com.apis.blog_app.exception.ResourceNotFound;
import com.apis.blog_app.model.Role;
import com.apis.blog_app.model.User;
import com.apis.blog_app.repository.RoleRepository;
import com.apis.blog_app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void create(User user) {
		User user_found = userRepository.findByName(user.getName());
		System.out.println(user_found+" ------------");
		if (user_found != null) {
			throw new ResourceAlreadyExists("User", user.getName());
		}
		System.out.println(user.toString());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Role defaultRole = roleRepository.findById(AppConstants.NORMAL_USER).get();
		user.getRoles().add(defaultRole);
		userRepository.save(user);

	}
	
	@Override
	public void update(User user) {
		User user_found = userRepository.findByName(user.getName());
		if (user_found == null) {
			System.out.println("not found");
			throw new ResourceNotFound("User", user.getName());
		}
		user_found.setAbout(user.getAbout());
		user_found.setName(user.getName());
		user_found.setPassword(user.getPassword());
		user_found.setEmail(user.getEmail());
		userRepository.save(user_found);

	}

	@Override
	public User get(String name) {
		User user_found = userRepository.findByName(name);
		if (user_found == null) {
			throw new ResourceNotFound("User", name);
		}
		return user_found;
	}

	@Override
	public User delete(String name) {
		User user_found = userRepository.findByName(name);
		if (user_found == null) {
			throw new ResourceNotFound("User", name);
		}
		userRepository.delete(user_found);
		return user_found;
	}

}
