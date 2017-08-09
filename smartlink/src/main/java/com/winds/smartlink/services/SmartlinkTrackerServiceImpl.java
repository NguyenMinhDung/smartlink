package com.winds.smartlink.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winds.smartlink.dao.SmartlinkTrackerDAO;
import com.winds.smartlink.dtos.SearchSmartlinkTracker;
import com.winds.smartlink.exceptions.BusinessException;
import com.winds.smartlink.exceptions.DataAccessException;
import com.winds.smartlink.models.SmartlinkTracker;

@Service("smartlinkTrackerService")
public class SmartlinkTrackerServiceImpl implements SmartlinkTrackerService{
	
	@Autowired
	private SmartlinkTrackerDAO smartlinkTrackerDAO;

	@Override
	public SmartlinkTracker save(SmartlinkTracker smartlinkTracker) throws BusinessException {
		try {
			return smartlinkTrackerDAO.save(smartlinkTracker);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public SmartlinkTracker searchOne(SearchSmartlinkTracker input) throws BusinessException {
		try {
			return smartlinkTrackerDAO.searchOne(input);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public void update(SmartlinkTracker smartlinkTracker) throws BusinessException {
		try {
			smartlinkTrackerDAO.update(smartlinkTracker);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public List<SmartlinkTracker> search(SearchSmartlinkTracker input) throws BusinessException {
		try {
			return smartlinkTrackerDAO.search(input);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}

}
