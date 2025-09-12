package com.pluralsight.byteboard.controllers;

import com.pluralsight.byteboard.data.CommentDao;
import com.pluralsight.byteboard.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
