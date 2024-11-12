package com.apis.blog_app.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.apis.blog_app.model.Category;
import com.apis.blog_app.model.Post;
import com.apis.blog_app.model.User;
import com.apis.blog_app.utils.PostResponse;

public interface PostService {

	public void create(Post post, int user_id, int category_id);
	
	public void create(MultipartFile image);

	public void update(Post post, int post_id);

	public Post delete(int post_id);
	
	public List<Post> getPostsByUser(int user_id);
	
	public List<Post> getPostsByCategory(int category_id);
	
	public PostResponse getAllPosts(int pageNumber, int pageSize, String sortBy, String sortDir);
	
	public Post getPost(int post_id);
	
	public List<Post> searchPostByTitle(String keyword);
}
