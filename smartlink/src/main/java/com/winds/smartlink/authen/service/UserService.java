package com.winds.smartlink.authen.service;

import com.winds.smartlink.authen.model.User;
import com.winds.smartlink.exceptions.BusinessException;

public interface UserService {

	User findById(int id) throws BusinessException;
	User findByUsername(String username) throws BusinessException;

}
