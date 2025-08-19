package com.pluralsight.byteboard.data.mysql;

import com.pluralsight.byteboard.data.PostDao;
import com.pluralsight.byteboard.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
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
		String query = """
				SELECT * FROM posts
				WHERE post_id = ?;
				""";

		try(Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, postId);

			ResultSet result = statement.executeQuery();
			if(result.next()) {
				return mapRow(result);
			} else {
				System.err.println("ERROR! Cannot find post with that ID!!!");
			}

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}

		return null;
	}

	@Override
	public List<Post> getByUserId(int userId) {
		List<Post> postList = new ArrayList<>();
		String query = """
				SELECT * FROM posts
				WHERE user_id = ?;
				""";

		try(Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, userId);

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
	public Post add(Post post) {
		String query = """
				INSERT INTO posts(user_id, title, content, author, date_posted)
				VALUES (?, ?, ?, ?, ?);
				""";

		try(Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, post.getUserId());
			statement.setString(2, post.getTitle());
			statement.setString(3, post.getContent());
			statement.setString(4, post.getAuthor());
			statement.setTimestamp(5, Timestamp.valueOf(post.getDatePosted()));

			int rows = statement.executeUpdate();
			if(rows > 0) {
				ResultSet key = statement.getGeneratedKeys();

				if(key.next()) {
					int postId = key.getInt(1);
					return getById(postId);
				}
			} else {
				System.err.println("ERROR! Could not add the post!!!");
			}

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}

		return null;
	}

	@Override
	public void update(Post post, int postId) {
		String query = """
				UPDATE posts
				SET title = ?,
				content = ?,
				WHERE postId = ?;
				""";
		try(Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, post.getTitle());
			statement.setString(2, post.getContent());
			statement.setInt(3, postId);

			int rows = statement.executeUpdate();
			if(rows > 0) {
				System.out.println("Success! The post has been updated!");
			} else {
				System.err.println("ERROR! Could not update the post!!!");
			}

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(int postId) {
		String query = """
				DELETE FROM posts
				WHERE post_id = ?;
				""";
		try(Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, postId);

			int rows = statement.executeUpdate();
			if (rows > 0)
				System.out.println("Success the post has been deleted!");
			else
				System.err.println("ERROR! Could not delete the post!!");

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

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
