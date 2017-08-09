package com.winds.smartlink.authen.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.winds.smartlink.authen.model.User;
import com.winds.smartlink.authen.model.UserProfile;
import com.winds.smartlink.exceptions.DataAccessException;
import com.winds.smartlink.repo.IRepository;


@Repository("userDAO")
public class UserDAOImpl implements UserDAO {
	
	private String GET_USER_BY_USERNAME = "from User where username = ?";
	
	@Autowired
	private IRepository repo;

	@Override
	public User findById(int id) throws DataAccessException {
		return repo.getEntityById(User.class, id);
	}

	@Override
	public User findByUsername(String username) throws DataAccessException {
		List<Object> params = new ArrayList<Object>();
		
		params.add(username);
		return repo.getEntityByHQL(GET_USER_BY_USERNAME, params);
	}

	@Override
	public User save(User user) throws DataAccessException {
		return repo.create(user);
	}

	@Override
	public UserProfile saveProfile(UserProfile userProfile) throws DataAccessException {
		return repo.create(userProfile);
	}
}
