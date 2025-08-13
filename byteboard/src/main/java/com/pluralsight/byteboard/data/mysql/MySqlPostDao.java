package com.pluralsight.byteboard.data.mysql;

import com.pluralsight.byteboard.data.PostDao;
import com.pluralsight.byteboard.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlPostDao extends MySqlDaoBase implements PostDao {

	@Autowired
	public MySqlPostDao(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public List<Post> getAll() {
		List<Post> postList = new ArrayList<>();


		return postList;
	}

	@Override
	public Post getById(int postId) {
		return null;
	}

	@Override
	public List<Post> getByUserId(int userId) {
		List<Post> postList = new ArrayList<>();


		return postList;
	}

	@Override
	public Post add(Post post) {
		return null;
	}

	@Override
	public void update(Post post) {

	}

	@Override
	public void delete(int postId) {}

}
