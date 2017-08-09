package com.winds.smartlink.authen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winds.smartlink.authen.dao.UserDAO;
import com.winds.smartlink.authen.model.User;
import com.winds.smartlink.exceptions.BusinessException;
import com.winds.smartlink.exceptions.DataAccessException;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public User findByUsername(String username) throws BusinessException {
		try {
			return userDAO.findByUsername(username);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public User findById(int id) throws BusinessException {
		try {
			return userDAO.findById(id);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}
	
}
