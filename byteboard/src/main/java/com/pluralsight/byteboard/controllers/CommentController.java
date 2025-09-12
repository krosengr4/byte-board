package com.pluralsight.byteboard.controllers;

import com.pluralsight.byteboard.data.CommentDao;
import com.pluralsight.byteboard.data.UserDao;
import com.pluralsight.byteboard.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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

	// todo: get one comment by its ID

	// todo: add a comment

	// todo: update a comment

	// todo: delete a comment

}
