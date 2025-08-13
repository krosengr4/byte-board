package com.pluralsight.byteboard.data;

import com.pluralsight.byteboard.models.Post;

import java.util.List;

public interface PostDao {

	List<Post> getAll();

	Post getById(int postId);

	List<Post> getByUserId(int userId);

	Post add(Post post);

	void update(Post post);

	void delete(int postId);

}
