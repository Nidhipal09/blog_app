package com.apis.blog_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apis.blog_app.exception.ResourceAlreadyExists;
import com.apis.blog_app.exception.ResourceNotFound;
import com.apis.blog_app.model.Category;
import com.apis.blog_app.model.Comment;
import com.apis.blog_app.model.Post;
import com.apis.blog_app.model.User;
import com.apis.blog_app.repository.CategoryRepository;
import com.apis.blog_app.repository.CommentRepository;
import com.apis.blog_app.repository.PostRepository;
import com.apis.blog_app.repository.UserRepository;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PostRepository postRepository;

	@Override
	public void create(Comment comment, int user_id, int post_id) {
		User user = userRepository.findById(user_id).get();
		Post post = postRepository.findById(post_id).get();
 
		comment.setUser(user);
		comment.setPost(post);
		
		commentRepository.save(comment);
	}
	
	@Override
	public void update(Comment comment, int comment_id) {
		Comment comment_found = commentRepository.findById(comment_id).get();
 
		comment_found.setContent(comment.getContent());
		
		commentRepository.save(comment_found);
	}

	@Override
	public Comment delete(int comment_id) {
		Comment comment= commentRepository.findById(comment_id).get();
		
		commentRepository.delete(comment);
		
		return comment;
	}

	@Override
	public Comment get(int id) {
		return commentRepository.findById(id).get();
	}

}
