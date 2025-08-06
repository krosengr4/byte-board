package com.pluralsight.byteboard.models;

import java.time.LocalDateTime;

public class Comment {

	int commentId;
	int userId;
	int postId;
	String content;
	String author;
	LocalDateTime datePosted;

	public Comment(int commentId, int userId, int postId, String content, String author, LocalDateTime datePosted) {
		this.commentId = commentId;
		this.userId = userId;
		this.postId = postId;
		this.content = content;
		this.author = author;
		this.datePosted = datePosted;
	}

	//region Setters and Getters
	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
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
