package com.winds.smartlink.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.winds.smartlink.exceptions.DataAccessException;
import com.winds.smartlink.models.SmartlinkUser;
import com.winds.smartlink.repo.IRepository;

@Repository("smartlinkDAO")
public class SmartlinkDAOImpl implements SmartlinkDAO{

	@Autowired
	private IRepository repo;

	@Override
	public String findSmartlinkByUser(Long userId) throws DataAccessException {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		
		sql.append("select sl.link from smartlink_user slu ");
		sql.append(" join smartlink sl on sl.smartlinkId = slu.smartlinkId ");
		sql.append(" where slu.userId = ?");
		params.add(userId);
		
		return (String) repo.getObjectByQuery(sql.toString(), params);
	}

	@Override
	public SmartlinkUser findSmartlinkUser(Long userId) throws DataAccessException {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		
		sql.append("select * from smartlink_user slu ");
		sql.append(" join smartlink sl on sl.smartlinkId = slu.smartlinkId ");
		sql.append(" where slu.userId = ?");
		params.add(userId);
		sql.append(" order by slu.smartlinkUserId");
		
		return repo.getEntityBySQL(SmartlinkUser.class, sql.toString(), params);
	}

	@Override
	public SmartlinkUser findSmartlinkUserEmail(String email) throws DataAccessException {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		
		sql.append("select * from smartlink_user slu ");
		sql.append(" join users us on us.userId = slu.userId ");
		sql.append(" join smartlink sl on sl.smartlinkId = slu.smartlinkId ");
		sql.append(" where us.username = ?");
		params.add(email);
		sql.append(" order by slu.smartlinkUserId");
		
		return repo.getEntityBySQL(SmartlinkUser.class, sql.toString(), params);
	}
	
	@Override
	public void update(SmartlinkUser userSmartlink) throws DataAccessException {
		repo.update(userSmartlink);
	}
	
}
