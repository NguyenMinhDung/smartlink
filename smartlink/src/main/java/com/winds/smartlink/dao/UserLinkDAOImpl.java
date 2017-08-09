package com.winds.smartlink.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.winds.smartlink.exceptions.DataAccessException;
import com.winds.smartlink.models.UserLink;
import com.winds.smartlink.repo.IRepository;

@Repository("userLinkDAO")
public class UserLinkDAOImpl implements UserLinkDAO{

	@Autowired
	private IRepository repo;
	
	@Override
	public UserLink save(UserLink userLink) throws DataAccessException {
		return repo.create(userLink);
	}

	@Override
	public UserLink getUserLinkByUserAndCode(Long userId, Long code) throws DataAccessException {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		
		sql.append("select * from user_links where 1 = 1 ");
		
		if(userId != null) {
			sql.append(" and userId = ? ");
			params.add(userId);
		}
		
		if(code != null) {
			sql.append(" and code = ?");
			params.add(code);
		}
		
		return repo.getEntityBySQL(UserLink.class, sql.toString(), params);
	}

	@Override
	public void update(UserLink userLink) throws DataAccessException {
		repo.update(userLink);
	}

}
