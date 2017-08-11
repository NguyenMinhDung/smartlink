package com.winds.smartlink.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.winds.smartlink.dtos.SmartlinkCondition;
import com.winds.smartlink.exceptions.DataAccessException;
import com.winds.smartlink.models.Smartlink;
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
	
	@Override
	public Smartlink save(Smartlink smartlink) throws DataAccessException {
		return repo.create(smartlink);
	}
	
	@Override
	public SmartlinkUser save(SmartlinkUser smartlinkUser) throws DataAccessException {
		return repo.create(smartlinkUser);
	}
	
	@Override
	public SmartlinkUser autoChooseSmartlink(SmartlinkCondition condition) throws DataAccessException {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		
		sql.append(" select slu.* from smartlink_user slu ");
		sql.append(" join smartlink sm on sm.smartLinkId = slu.smartLinkId ");
		sql.append(" where slu.userId = ? ");
		
		params.add(condition.getUserId());
		
		return repo.getEntityBySQL(SmartlinkUser.class, sql.toString(), params);
	}
}
