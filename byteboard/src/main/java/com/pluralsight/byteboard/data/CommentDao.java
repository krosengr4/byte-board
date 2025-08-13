package com.pluralsight.byteboard.data;

import com.pluralsight.byteboard.models.Comment;

import java.util.List;

public interface CommentDao {

	List<Comment> getAll();

	Comment getById(int commentId);

	List<Comment> getByPostId(int postId);

	Comment add(Comment comment);

	void update(Comment comment);

	void delete(int commentId);
}
