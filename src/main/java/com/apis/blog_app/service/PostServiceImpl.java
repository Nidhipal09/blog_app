package com.apis.blog_app.service;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.apis.blog_app.exception.ResourceAlreadyExists;
import com.apis.blog_app.exception.ResourceNotFound;
import com.apis.blog_app.model.Category;
import com.apis.blog_app.model.Post;
import com.apis.blog_app.model.User;
import com.apis.blog_app.repository.CategoryRepository;
import com.apis.blog_app.repository.PostRepository;
import com.apis.blog_app.repository.UserRepository;
import com.apis.blog_app.utils.PostResponse;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepository postRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Value("${image.file.path}")
	private String imageFilePath;

	@Override
	public void create(Post post, int user_id, int category_id) {
		User user = userRepository.findById(user_id).get();
		Category category  = categoryRepository.findById(category_id).get();

		post.setUser(user);
		post.setCategory(category);
		
		
		
		postRepository.save(post);
	}
	
	@Override
	public void create(MultipartFile image) {
		String imageName = image.getOriginalFilename();
		String fullImagePath = imageFilePath +File.separator+imageName;
		File file = new File(imageFilePath);
		if(!file.exists()) file.mkdir();
		try {
			Files.copy(image.getInputStream(), Paths.get(fullImagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Post post, int id) {
		Post post_found = postRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Post", String.valueOf(id)));
		post_found.setTitle(post.getTitle());
		post_found.setContent(post.getContent());
		post_found.setImageName(post.getImageName());
		post_found.setAddedDate(post.getAddedDate());
		
		postRepository.save(post_found);
	}
	
	@Override
	public Post delete(int id) {
		Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Post", String.valueOf(id)));
		postRepository.delete(post);
		return post;
	}

	@Override
	public List<Post> getPostsByUser(int user_id) {
		User user = userRepository.findById(user_id).get();
		return postRepository.findByUser(user);
		
	}

	@Override
	public List<Post> getPostsByCategory(int category_id) {
		Category category = categoryRepository.findById(category_id).get();
		return postRepository.findByCategory(category);
	}

	@Override
	public PostResponse getAllPosts(int pageNumber,int pageSize, String sortBy,  String sortDir) {
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc")) {
			 sort = Sort.by(sortBy).ascending();
		}else {
			 sort = Sort.by(sortBy).descending();
		}
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> page = postRepository.findAll(pageable);
		return new PostResponse(page.getContent(), page.getNumber(), page.getSize(), page.getTotalElements(), page.getTotalPages(), page.isLast());
		
	}

	@Override
	public Post getPost(int post_id) {
		return  postRepository.findById(post_id).orElseThrow(()->new ResourceNotFound("Post", String.valueOf(post_id) ));
	}

	@Override
	public List<Post> searchPostByTitle(String keyword) {
		return postRepository.findByTitleContaining(keyword);
	}
	
	

}
