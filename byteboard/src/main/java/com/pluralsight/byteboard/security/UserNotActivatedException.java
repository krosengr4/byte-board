package com.pluralsight.byteboard.security;

public class UserNotActivatedException extends RuntimeException {
	public UserNotActivatedException(String message) {
		super(message);
	}
}
