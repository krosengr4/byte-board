package com.pluralsight.byteboard.data.mysql;

import com.pluralsight.byteboard.data.PostDao;
import com.pluralsight.byteboard.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
		String query = "SELECT * FROM posts;";

		try(Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);

			ResultSet results = statement.executeQuery();
			while(results.next()) {
				Post post = mapRow(results);
				postList.add(post);
			}

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}

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

	private Post mapRow(ResultSet result) throws SQLException {
		int postId = result.getInt("post_id");
		int user_id = result.getInt("user_id");
		String title = result.getString("title");
		String content = result.getString("content");
		String author = result.getString("author");
		LocalDateTime datePosted = result.getTimestamp("date_posted").toLocalDateTime();

		return new Post(postId, user_id, title, content, author, datePosted);
	}
}
