package com.winds.smartlink.services;

import com.winds.smartlink.authen.exceptions.UserExistsException;
import com.winds.smartlink.dtos.AddUserInput;
import com.winds.smartlink.dtos.SmartlinkCondition;
import com.winds.smartlink.exceptions.BusinessException;
import com.winds.smartlink.models.SmartlinkUser;

public interface SmartlinkService {

	String findSmartlinkByUser(Long userId) throws BusinessException;

	SmartlinkUser findSmartlinkUser(Long userId) throws BusinessException;

	SmartlinkUser findSmartlinkUserEmail(String email) throws BusinessException;

	void update(SmartlinkUser userSmartlink) throws BusinessException;

	void addUserAndSmartlink(AddUserInput input) throws BusinessException, UserExistsException;

	SmartlinkUser autoChooseSmartlink(SmartlinkCondition condition) throws BusinessException;
}
