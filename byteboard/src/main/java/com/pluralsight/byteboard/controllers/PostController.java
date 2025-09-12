package com.pluralsight.byteboard.controllers;

import com.pluralsight.byteboard.data.PostDao;
import com.pluralsight.byteboard.data.UserDao;
import com.pluralsight.byteboard.models.Post;
import com.pluralsight.byteboard.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
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
	@GetMapping("/{postId}")
	public Post getPostById(@PathVariable int postId) {
		try {
			return postDao.getById(postId);
		} catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops, something went wrong...");
		}
	}

	// todo: add a post
	@PostMapping("")
	public Post addPost(@RequestBody Post post, Principal principal) {
		try {
			// Get the ID of the user that is logged in
			String username = principal.getName();
			User user = userDao.getByUsername(username);
			int userId = user.getId();

			post.setUserId(userId);
			post.setAuthor(username);
			return postDao.add(post);
		} catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops, something went wrong...");
		}
	}

	// todo: update a post
	@PutMapping("/{postId}")
	public void updateStory(@RequestBody Post updatedPost, @PathVariable int postId, Principal principal) {
		try {
			// Get the ID of the user that is logged in
			String username = principal.getName();
			User user = userDao.getByUsername(username);
			int userId = user.getId();

			Post post = postDao.getById(postId);

			// Verify post ID exists and it belongs to user logged in
			if(post != null && userId == post.getUserId()) {
				updatedPost.setUserId(userId);
				updatedPost.setPostId(postId);
				postDao.update(updatedPost, postId);
			} else {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
			}
		} catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops, something went wrong...");
		}
	}

	// todo: delete a post

}
