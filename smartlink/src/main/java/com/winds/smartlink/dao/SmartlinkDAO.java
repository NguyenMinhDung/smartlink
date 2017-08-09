package com.winds.smartlink.dao;

import com.winds.smartlink.exceptions.DataAccessException;
import com.winds.smartlink.models.SmartlinkUser;

public interface SmartlinkDAO {

	String findSmartlinkByUser(Long userId) throws DataAccessException;

	SmartlinkUser findSmartlinkUser(Long userId) throws DataAccessException;

	SmartlinkUser findSmartlinkUserEmail(String email) throws DataAccessException;

	void update(SmartlinkUser userSmartlink) throws DataAccessException;

}
