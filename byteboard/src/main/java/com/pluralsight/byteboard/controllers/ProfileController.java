package com.pluralsight.byteboard.controllers;

import com.pluralsight.byteboard.data.ProfileDao;
import com.pluralsight.byteboard.data.UserDao;
import com.pluralsight.byteboard.models.Profile;
import com.pluralsight.byteboard.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RestController
@CrossOrigin
@RequestMapping("profiles")
public class ProfileController {

	private final ProfileDao profileDao;
	private final UserDao userDao;

	@Autowired
	public ProfileController(ProfileDao profileDao, UserDao userDao) {
		this.profileDao = profileDao;
		this.userDao = userDao;
	}

	// todo: get a profile by user ID
	@GetMapping("")
	public Profile getByUserId(Principal principal) {
		try {
			// Get user ID of the user that is logged in
			User user = userDao.getByUsername(principal.getName());
			int userId = user.getId();

			return profileDao.getByUserId(userId);
		} catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops, something went wrong...");
		}
	}

	// todo: Update profile method
}
