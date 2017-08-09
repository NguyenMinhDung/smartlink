package com.winds.smartlink.services;

import com.winds.smartlink.exceptions.BusinessException;
import com.winds.smartlink.models.SmartlinkUser;

public interface SmartlinkService {

	String findSmartlinkByUser(Long userId) throws BusinessException;

	SmartlinkUser findSmartlinkUser(Long userId) throws BusinessException;

	SmartlinkUser findSmartlinkUserEmail(String email) throws BusinessException;

	void update(SmartlinkUser userSmartlink) throws BusinessException;
}
