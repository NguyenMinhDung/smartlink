package com.winds.smartlink.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.winds.smartlink.dtos.SearchSmartlinkTracker;
import com.winds.smartlink.exceptions.DataAccessException;
import com.winds.smartlink.models.SmartlinkTracker;
import com.winds.smartlink.repo.IRepository;
import com.winds.smartlink.utils.DateUtils;

@Repository("smartlinkTrackerDAO")
public class SmartlinkTrackerDAOImpl implements SmartlinkTrackerDAO{

	@Autowired
	private IRepository repo;

	@Override
	public SmartlinkTracker save(SmartlinkTracker smartlinkTracker) throws DataAccessException {
		return repo.create(smartlinkTracker);
	}

	@Override
	public SmartlinkTracker searchOne(SearchSmartlinkTracker input) throws DataAccessException {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		
		sql.append("select * from smartlink_tracker where 1 = 1 ");
		
		if(input.getSmartlinkUserId() != null) {
			sql.append(" and smartlinkUserId = ? ");
			params.add(input.getSmartlinkUserId());
		}
		
		if(input.getCountryCode() != null) {
			sql.append(" and countryCode = ? ");
			params.add(input.getCountryCode());
		}
		
		if(input.getTrackerDate() != null) {
			sql.append(" and DATE(trackerDate) =  ?");
			params.add(DateUtils.format(input.getTrackerDate(), DateUtils.YYYYMMDD));
		}
		
		return repo.getEntityBySQL(SmartlinkTracker.class, sql.toString(), params);
	}

	@Override
	public void update(SmartlinkTracker smartlinkTracker) throws DataAccessException {
		repo.update(smartlinkTracker);
	}

	@Override
	public List<SmartlinkTracker> search(SearchSmartlinkTracker input) throws DataAccessException {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		
		sql.append(" select st.* from smartlink_tracker st ");
		sql.append(" join smartlink_user su on su.smartlinkUserId = st.smartlinkUserId ");
		sql.append(" where 1 = 1 ");
		
		if(input.getUserId() != null) {
			sql.append(" and su.userId = ? ");
			params.add(input.getUserId());
		}
		
		if(input.getSmartlinkUserId() != null) {
			sql.append(" and st.smartlinkUserId = ? ");
			params.add(input.getSmartlinkUserId());
		}
		
		if(input.getCountryCode() != null) {
			sql.append(" and st.countryCode = ? ");
			params.add(input.getCountryCode());
		}
		
		if(input.getTrackerDate() != null) {
			sql.append(" and DATE(st.trackerDate) =  ?");
			params.add(DateUtils.format(input.getTrackerDate(), DateUtils.YYYYMMDD));
		}
		
		return repo.getListBySQL(SmartlinkTracker.class, sql.toString(), params);
	}
	
}
