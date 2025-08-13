package com.pluralsight.byteboard.data.mysql;

import com.pluralsight.byteboard.data.CommentDao;
import com.pluralsight.byteboard.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlCommentDao extends MySqlDaoBase implements CommentDao {

	@Autowired
	public MySqlCommentDao(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public List<Comment> getAll() {
		List<Comment> commentList = new ArrayList<>();
		String query = "SELECT * FROM comments;";

		try(Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);

			ResultSet results = statement.executeQuery();
			while(results.next()) {
				Comment comment = mapRow(results);
				commentList.add(comment);
			}

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}

		return commentList;
	}

	@Override
	public Comment getById(int commentId) {
		String query = """
				SELECT * FROM comments
				WHERE comment_id = ?;
				""";

		try(Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, commentId);

			ResultSet result = statement.executeQuery();
			if(result.next()) {
				return mapRow(result);
			} else {
				System.err.println("No comment found with that ID!!!");
			}

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public List<Comment> getByPostId(int postId) {
		List<Comment> commentList = new ArrayList<>();
		String query = """
				SELECT * FROM comments
				WHERE post_id = ?;
				""";

		try(Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, postId);

			ResultSet results = statement.executeQuery();
			while(results.next()) {
				Comment comment = mapRow(results);
				commentList.add(comment);
			}

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}

		return commentList;
	}

	@Override
	public Comment add(Comment comment) {
		String query = """
				INSERT INTO comments (user_id, post_id, content, author, date_posted)
				VALUES (?, ?, ?, ?, ?);
				""";

		try(Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, comment.getUserId());
			statement.setInt(2, comment.getPostId());
			statement.setString(3, comment.getContent());
			statement.setString(4, comment.getAuthor());
			statement.setTimestamp(5, Timestamp.valueOf(comment.getDatePosted()));

			int rows = statement.executeUpdate();
			if(rows > 0) {
				ResultSet key = statement.getGeneratedKeys();

				if(key.next()) {
					int commentId = key.getInt(1);
					return getById(commentId);
				}
			} else {
				System.err.println("Could not create new comment!!!");
			}

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public void update(Comment comment, int commentId) {
		String query = """
				UPDATE comments
				SET user_id = ?,
				post_id = ?,
				content = ?,
				author = ?,
				date_posted = ?
				WHERE comment_id = ?;
				""";
		try(Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, comment.getUserId());
			statement.setInt(2, comment.getPostId());
			statement.setString(3, comment.getContent());
			statement.setString(4, comment.getAuthor());
			statement.setTimestamp(5, Timestamp.valueOf(comment.getDatePosted()));
			statement.setInt(6, commentId);

			int rows = statement.executeUpdate();
			if(rows > 0)
				System.out.println("Success! Comment was updated!");
			else
				System.err.println("ERROR! Could not update the comment!!!");

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(int commentId) {

	}

	private Comment mapRow(ResultSet result) throws SQLException {
		int commentId = result.getInt("comment_id");
		int userId = result.getInt("user_id");
		int postId = result.getInt("post_id");
		String content = result.getString("content");
		String author = result.getString("author");
		LocalDateTime datePosted = result.getTimestamp("date_posted").toLocalDateTime();

		return new Comment(commentId, userId, postId, content, author, datePosted);
	}
}
