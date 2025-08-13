package com.pluralsight.byteboard.data.mysql;

import com.pluralsight.byteboard.data.CommentDao;
import com.pluralsight.byteboard.models.Comment;
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
		return null;
	}

	@Override
	public void update(Comment comment) {

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
