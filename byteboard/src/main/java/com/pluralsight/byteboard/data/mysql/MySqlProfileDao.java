package com.pluralsight.byteboard.data.mysql;

import com.pluralsight.byteboard.data.ProfileDao;
import com.pluralsight.byteboard.models.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class MySqlProfileDao extends MySqlDaoBase implements ProfileDao {

	@Autowired
	public MySqlProfileDao(DataSource datasource) {
		super(datasource);
	}

	@Override
	public Profile getByUserId(int userId) {
		return null;
	}

	@Override
	public Profile create(Profile profile) {
		return null;
	}

	@Override
	public void update(Profile profile) {

	}

}
