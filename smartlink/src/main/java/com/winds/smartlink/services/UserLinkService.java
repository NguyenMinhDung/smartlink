package com.winds.smartlink.services;

import com.winds.smartlink.exceptions.BusinessException;
import com.winds.smartlink.models.UserLink;

public interface UserLinkService {

	UserLink save(UserLink userLink) throws BusinessException;
	
	void update(UserLink userLink) throws BusinessException;

	UserLink getUserLinkByUserAndCode(Long id_mem, Long mahoa) throws BusinessException;

}
