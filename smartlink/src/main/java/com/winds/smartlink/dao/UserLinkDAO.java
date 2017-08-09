package com.winds.smartlink.dao;

import com.winds.smartlink.exceptions.DataAccessException;
import com.winds.smartlink.models.UserLink;

public interface UserLinkDAO {

	UserLink save(UserLink userLink) throws DataAccessException;

	UserLink getUserLinkByUserAndCode(Long userId, Long code) throws DataAccessException;

	void update(UserLink userLink) throws DataAccessException;

}
