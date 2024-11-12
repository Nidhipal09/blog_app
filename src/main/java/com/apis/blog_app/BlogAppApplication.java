package com.apis.blog_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.apis.blog_app.config.AppConstants;
import com.apis.blog_app.model.Role;
import com.apis.blog_app.model.User;
import com.apis.blog_app.repository.RoleRepository;
import com.apis.blog_app.repository.UserRepository;

@SpringBootApplication
public class BlogAppApplication implements CommandLineRunner{

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		User user = new User();
//		user.setName("ADMIN_USER");
//		user.setPassword(passwordEncoder.encode("admin"));
//		user.setAbout("Admin user");
//		user.setEmail("blog_admin@gmail.com");
//		Role adminRole = roleRepository.findById(AppConstants.ADMIN_USER).get();
//		user.getRoles().add(adminRole);
//		userRepository.save(user);
		
	}

}
