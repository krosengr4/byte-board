package com.pluralsight.byteboard.controllers;

import com.pluralsight.byteboard.data.ProfileDao;
import com.pluralsight.byteboard.data.UserDao;
import com.pluralsight.byteboard.security.jwt.TokenProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AuthentificationController {

	private final TokenProvider tokenProvider;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final UserDao userDao;
	private final ProfileDao profileDao;

	public AuthentificationController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, UserDao userDao, ProfileDao profileDao) {
		this.tokenProvider = tokenProvider;
		this.authenticationManagerBuilder = authenticationManagerBuilder;
		this.userDao = userDao;
		this.profileDao = profileDao;
	}
}
