package com.winds.smartlink.repo;

import java.util.List;

import com.winds.smartlink.exceptions.DataAccessException;


/**
 * Interface for create, update, delete.
 * @author Windreams
 *
 */
public interface CrudRepository {
	/**
	 * Create object.
	 * @param object
	 * @return object created have id.
	 * @throws DataAccessException
	 */
	<T> T create(T object) throws DataAccessException;
	
	/**
	 * Create list object.
	 * @param lstObject
	 * @return list object created by id.
	 * @throws DataAccessException
	 */
	<T> List<T> create(List<T> lstObject) throws DataAccessException;

	/**
	 * Update object.
	 * @param object
	 * @throws DataAccessException
	 */
	void update(Object object) throws DataAccessException;
	
	/**
	 * Update list object.
	 * @param lstObject
	 * @return list object
	 * @throws DataAccessException
	 */
	<T> List<T> update(List<T> lstObject) throws DataAccessException;

	/**
	 * Delete object.
	 * @param object
	 * @throws DataAccessException
	 */
	void delete(Object object) throws DataAccessException;
	
}
