package com.pluralsight.byteboard.data.mysql;

import com.pluralsight.byteboard.data.ProfileDao;
import com.pluralsight.byteboard.models.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDate;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

@Component
public class MySqlProfileDao extends MySqlDaoBase implements ProfileDao {

	@Autowired
	public MySqlProfileDao(DataSource datasource) {
		super(datasource);
	}

	@Override
	public Profile getByUserId(int userId) {
		String query = """
				SELECT * FROM profiles
				WHERE user_id = ?;
				""";

		try(Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, userId);

			ResultSet result = statement.executeQuery();
			if(result.next()) {
				return mapRow(result);
			} else {
				System.err.println("Could not find user with that ID!!!");
			}

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}

		return null;
	}

	@Override
	public Profile create(Profile profile) {
		return null;
	}

	@Override
	public void update(Profile profile) {

	}

	private Profile mapRow(ResultSet result) throws SQLException {
		int userId = result.getInt("user_id");
		String firstName = result.getString("first_name");
		String lastName = result.getString("last_name");
		String email = result.getString("email");
		String githubLink = result.getString("github_link");
		String city = result.getString("city");
		String state = result.getString("state");
		LocalDate dateRegistered = result.getTimestamp("date_registered").toLocalDateTime().toLocalDate();

		return new Profile(userId, firstName, lastName, email, githubLink, city, state, dateRegistered);
	}

}
