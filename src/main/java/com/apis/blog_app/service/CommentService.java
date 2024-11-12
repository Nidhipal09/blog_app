package com.apis.blog_app.service;

import com.apis.blog_app.model.Category;
import com.apis.blog_app.model.Comment;
import com.apis.blog_app.model.User;

public interface CommentService {

	public void create(Comment comment, int user_id, int post_id);
	public void update(Comment comment, int comment_id);
	public Comment delete(int id);
	public Comment get(int id);
}
