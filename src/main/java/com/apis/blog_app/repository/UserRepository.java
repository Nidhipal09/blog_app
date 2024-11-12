package com.apis.blog_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apis.blog_app.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	public User findByName(String name);
	
}
