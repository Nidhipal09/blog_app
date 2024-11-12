package com.apis.blog_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apis.blog_app.model.Category;
import com.apis.blog_app.model.Post;
import com.apis.blog_app.model.User;

public interface PostRepository extends JpaRepository<Post, Integer>{

 	public List<Post> findByUser(User user);
 	public List<Post> findByCategory(Category category);
 	
 	public List<Post> findByTitleContaining(String keyword);
}
