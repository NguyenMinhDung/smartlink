package com.winds.smartlink.authen.dao;

import com.winds.smartlink.authen.model.User;
import com.winds.smartlink.authen.model.UserProfile;
import com.winds.smartlink.exceptions.DataAccessException;

/**
 * @author Windreams
 *
 */
public interface UserDAO {
	User findById(int id) throws DataAccessException;
    User findByUsername(String username) throws DataAccessException;
	User save(User user) throws DataAccessException;
	UserProfile saveProfile(UserProfile userProfile) throws DataAccessException;
}
