package com.pluralsight.byteboard.controllers;

import com.pluralsight.byteboard.data.PostDao;
import com.pluralsight.byteboard.data.UserDao;
import com.pluralsight.byteboard.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("posts")
public class PostController {

	private final PostDao postDao;
	private final UserDao userDao;

	@Autowired
	public PostController(PostDao postDao, UserDao userDao) {
		this.postDao = postDao;
		this.userDao = userDao;
	}

	// todo: get all posts
	@GetMapping("")
	public List<Post> getAllPosts() {
		try {
			return postDao.getAll();
		} catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops, something went wrong...");
		}
	}
	// todo: get all posts from a user with user ID
	@GetMapping("/user/{userId}")
	public List<Post> getAllPostsFromUser(@PathVariable int userId) {
		try {
			return postDao.getByUserId(userId);
		} catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops, something went wrong...");
		}
	}


	// todo: get one post by post ID

	// todo: add a post

	// todo: update a post

	// todo: delete a post

}
