package com.pluralsight.byteboard.models;

import java.time.LocalDateTime;

public class Post {

	int postId;
	int userId;
	String title;
	String content;
	String author;
	LocalDateTime datePosted;

	public Post(int postId, int userId, String title, String content, String author, LocalDateTime datePosted) {
		this.postId = postId;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.author = author;
		this.datePosted = datePosted;
	}

	//region Setters and Getters
	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public LocalDateTime getDatePosted() {
		return datePosted;
	}

	public void setDatePosted(LocalDateTime datePosted) {
		this.datePosted = datePosted;
	}
	//endregion

}
