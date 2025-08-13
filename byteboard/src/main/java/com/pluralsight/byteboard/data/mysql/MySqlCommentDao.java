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


		return commentList;
	}

	@Override
	public Comment getById(int commentId) {
		return null;
	}

	@Override
	public List<Comment> getByStoryId(int storyId) {
		List<Comment> commentList = new ArrayList<>();


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
