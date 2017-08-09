package com.winds.smartlink.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winds.smartlink.dao.UserLinkDAO;
import com.winds.smartlink.exceptions.BusinessException;
import com.winds.smartlink.exceptions.DataAccessException;
import com.winds.smartlink.models.UserLink;

@Service("userLinkService")
public class UserLinkServiceImpl implements UserLinkService{
	
	@Autowired
	private UserLinkDAO userLinkDAO;

	@Override
	public UserLink save(UserLink userLink) throws BusinessException {
		try {
			return userLinkDAO.save(userLink);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public void update(UserLink userLink) throws BusinessException {
		try {
			userLinkDAO.update(userLink);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public UserLink getUserLinkByUserAndCode(Long userId, Long code) throws BusinessException {
		try {
			return userLinkDAO.getUserLinkByUserAndCode(userId, code);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}

}
