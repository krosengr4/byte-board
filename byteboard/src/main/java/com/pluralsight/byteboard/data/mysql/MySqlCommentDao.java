package com.pluralsight.byteboard.data.mysql;

import com.pluralsight.byteboard.data.CommentDao;
import com.pluralsight.byteboard.models.Comment;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class MySqlCommentDao extends MySqlDaoBase implements CommentDao {


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
}
