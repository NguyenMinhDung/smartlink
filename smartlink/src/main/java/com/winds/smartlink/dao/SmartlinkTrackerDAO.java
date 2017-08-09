package com.winds.smartlink.dao;

import java.util.List;

import com.winds.smartlink.dtos.SearchSmartlinkTracker;
import com.winds.smartlink.exceptions.DataAccessException;
import com.winds.smartlink.models.SmartlinkTracker;

public interface SmartlinkTrackerDAO {

	SmartlinkTracker save(SmartlinkTracker smartlinkTracker) throws DataAccessException;

	SmartlinkTracker searchOne(SearchSmartlinkTracker input) throws DataAccessException;

	void update(SmartlinkTracker smartlinkTracker) throws DataAccessException;

	List<SmartlinkTracker> search(SearchSmartlinkTracker input) throws DataAccessException;

}
