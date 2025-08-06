package com.pluralsight.byteboard.models;

import java.time.LocalDate;

public class Profile {

	int userId;
	String firstName = "";
	String lastName = "";
	String email = "";
	String link = "";
	String city = "";
	String state = "";
	LocalDate dateRegistered;

	public Profile(int userId, String firstName, String lastName, String email, String link, String city, String state, LocalDate dateRegistered) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.link = link;
		this.city = city;
		this.state = state;
		this.dateRegistered = dateRegistered;
	}

	//region Setters and Getters
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public LocalDate getDateRegistered() {
		return dateRegistered;
	}

	public void setDateRegistered(LocalDate dateRegistered) {
		this.dateRegistered = dateRegistered;
	}
	//endregion

}
