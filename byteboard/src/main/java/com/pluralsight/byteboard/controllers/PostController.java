package com.pluralsight.byteboard.controllers;

import com.pluralsight.byteboard.data.PostDao;
import com.pluralsight.byteboard.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
