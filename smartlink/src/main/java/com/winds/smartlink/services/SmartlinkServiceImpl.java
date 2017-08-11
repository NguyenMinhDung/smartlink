package com.winds.smartlink.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winds.smartlink.authen.dao.UserDAO;
import com.winds.smartlink.authen.exceptions.UserExistsException;
import com.winds.smartlink.authen.model.User;
import com.winds.smartlink.authen.model.UserProfile;
import com.winds.smartlink.dao.SmartlinkDAO;
import com.winds.smartlink.dtos.AddUserInput;
import com.winds.smartlink.dtos.SmartlinkCondition;
import com.winds.smartlink.exceptions.BusinessException;
import com.winds.smartlink.exceptions.DataAccessException;
import com.winds.smartlink.models.Smartlink;
import com.winds.smartlink.models.SmartlinkUser;

@Service("smartlinkService")
public class SmartlinkServiceImpl implements SmartlinkService{
	
	@Autowired
	private SmartlinkDAO smartlinkDAO;
	
	@Autowired
	private UserDAO userDAO;

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
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void addUserAndSmartlink(AddUserInput input) throws BusinessException, UserExistsException {
		try {
			User userExists = userDAO.findByUsername(input.getEmail());
			if(userExists != null) {
				throw new UserExistsException("User exists");
			}
			
			User user = createUserAndProfile(input);
			Smartlink smartlink = createSmartlink(input);
			createUserSmartlink(user, smartlink);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}

	private void createUserSmartlink(User user, Smartlink smartlink) throws DataAccessException {
		SmartlinkUser smartlinkUser = new SmartlinkUser();
		smartlinkUser.setUserId(user.getUserId());
		smartlinkUser.setSmartlinkId(smartlink.getSmartlinkId());
		smartlinkUser.setStatus(1);
		smartlinkDAO.save(smartlinkUser);
	}

	private Smartlink createSmartlink(AddUserInput input) throws DataAccessException {
		Smartlink smartlink = new Smartlink();
		smartlink.setNetwork(input.getNetwork());
		smartlink.setLink(input.getSmartlink());
		smartlink.setStatus(1);
		
		smartlink = smartlinkDAO.save(smartlink);
		return smartlink;
	}

	private User createUserAndProfile(AddUserInput input) throws DataAccessException {
		User user = new User();
		user.setEmail(input.getEmail());
		user.setUsername(input.getEmail());
		user.setStatus(1);
		
		BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
		user.setPassword(encode.encode("P@ssword"));
		
		user = userDAO.save(user);
		
		UserProfile userProfile = new UserProfile();
		userProfile.setUserId(user.getUserId());
		userProfile.setProfileId(2L); // User Role
		
		userDAO.saveProfile(userProfile);
		
		return user;
	}
	
	@Override
	public SmartlinkUser autoChooseSmartlink(SmartlinkCondition condition) throws BusinessException {
		try {
			return smartlinkDAO.autoChooseSmartlink(condition);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}
}
