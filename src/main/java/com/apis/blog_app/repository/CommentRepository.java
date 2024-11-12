package com.apis.blog_app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apis.blog_app.model.Category;
import com.apis.blog_app.model.Comment;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{


	
}
