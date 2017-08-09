package com.winds.smartlink.services;

import java.util.List;

import com.winds.smartlink.dtos.SearchSmartlinkTracker;
import com.winds.smartlink.exceptions.BusinessException;
import com.winds.smartlink.models.SmartlinkTracker;

public interface SmartlinkTrackerService {

	SmartlinkTracker save(SmartlinkTracker smartlinkTracker) throws BusinessException;
	SmartlinkTracker searchOne(SearchSmartlinkTracker input) throws BusinessException;
	void update(SmartlinkTracker smartlinkTracker) throws BusinessException;
	List<SmartlinkTracker> search(SearchSmartlinkTracker input) throws BusinessException;
}
