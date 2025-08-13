package com.pluralsight.byteboard.data;

import com.pluralsight.byteboard.models.Profile;

public interface ProfileDao {

	Profile getByUserId(int userId);

	Profile create(Profile profile);

	void update(Profile profile);

}
