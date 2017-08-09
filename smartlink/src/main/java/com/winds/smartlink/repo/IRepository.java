package com.winds.smartlink.repo;

import java.io.Serializable;

import com.winds.smartlink.exceptions.DataAccessException;


/**
 * Interface repository where contain all method to working with database.
 * @author Windreams
 *
 */
public interface IRepository extends SelectRepository, CrudRepository {
	/**
	 * Get object in one session.
	 * @param clazz
	 * @param id
	 * @param oneSession
	 * @return one record
	 * @throws DataAccessException
	 */
	<T> T getEntityById(Class<T> clazz, Serializable id,
			boolean oneSession) throws DataAccessException;
}
