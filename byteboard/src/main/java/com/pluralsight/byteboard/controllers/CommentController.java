package com.pluralsight.byteboard.controllers;

import com.pluralsight.byteboard.data.CommentDao;
import com.pluralsight.byteboard.data.UserDao;
import com.pluralsight.byteboard.models.Comment;
import com.pluralsight.byteboard.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("comments")
public class CommentController {

	private final CommentDao commentDao;
	private final UserDao userDao;

	@Autowired
	public CommentController(CommentDao commentDao, UserDao userDao) {
		this.commentDao = commentDao;
		this.userDao = userDao;
	}

	// todo: get all comments
	@GetMapping("")
	public List<Comment> getAll() {
		try {
			return commentDao.getAll();
		} catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops, something went wrong...");
		}
	}

	// todo: get comments on a specific post
	@GetMapping("/post/{postId}")
	public List<Comment> getCommentsForPost(@PathVariable int postId) {
		try {
			return commentDao.getByPostId(postId);
		} catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops, something went wrong...");
		}
	}

	// todo: get one comment by its ID
	@GetMapping("/{commentId}")
	public Comment getCommentById(@PathVariable int commentId) {
		try {
			return commentDao.getById(commentId);
		} catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops, something went wrong...");
		}
	}

	// todo: add a comment
	@PostMapping("/{postId}")
	public Comment addComment(@PathVariable int postId, @RequestBody Comment comment, Principal  principal) {
		try {
			// Get the ID of the user that is logged in
			String username = principal.getName();
			User user = userDao.getByUsername(username);
			int userId = user.getId();

			comment.setUserId(userId);
			comment.setPostId(postId);
			comment.setAuthor(username);

			return commentDao.add(comment);
		} catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops, something went wrong...");
		}
	}

	// todo: update a comment
	@PutMapping("/{commentId}")
	public void updateComment(@PathVariable int commentId, @RequestBody Comment updatedComment, Principal principal) {
		try {
			// Get the ID of the user that is logged in
			User user = userDao.getByUsername(principal.getName());
			int userId = user.getId();

			Comment comment = commentDao.getById(commentId);

			// Verify comment
			if(comment != null && userId == comment.getUserId()) {
				updatedComment.setUserId(userId);
				updatedComment.setCommentId(commentId);
				updatedComment.setPostId(comment.getPostId());
			} else {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
			}
			
		} catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "The server could not get that...");
		}
	}

	// todo: delete a comment
}
