package com.winds.smartlink.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winds.smartlink.dao.SmartlinkDAO;
import com.winds.smartlink.exceptions.BusinessException;
import com.winds.smartlink.exceptions.DataAccessException;
import com.winds.smartlink.models.SmartlinkUser;

@Service("smartlinkService")
public class SmartlinkServiceImpl implements SmartlinkService{
	
	@Autowired
	private SmartlinkDAO smartlinkDAO;

	@Override
	public String findSmartlinkByUser(Long userId) throws BusinessException {
		try {
			return smartlinkDAO.findSmartlinkByUser(userId);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public SmartlinkUser findSmartlinkUser(Long userId) throws BusinessException {
		try {
			return smartlinkDAO.findSmartlinkUser(userId);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public SmartlinkUser findSmartlinkUserEmail(String email) throws BusinessException {
		try {
			return smartlinkDAO.findSmartlinkUserEmail(email);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public void update(SmartlinkUser userSmartlink) throws BusinessException {
		try {
			smartlinkDAO.update(userSmartlink);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}
}
