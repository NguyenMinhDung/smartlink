package com.winds.smartlink.repo;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.type.Type;

/**
 * @author Windreams
 *
 */
public abstract class AbstractRepository {
	
	/**
	 * @param query
	 * @param params
	 */
	protected void addParameters(Query query, List<Object> params) {
		if (params != null) {
			int i = 0;
			for (Object p : params) {
				query.setParameter(i, p);
				i++;
			}
		}
	}

	/**
	 * @param query
	 * @param fieldNames
	 * @param fieldTypes
	 */
	protected void addScalar(SQLQuery query, String[] fieldNames,
			Type[] fieldTypes) {
		for (int i = 0; i < fieldNames.length; i++) {
			query.addScalar(fieldNames[i], fieldTypes[i]);
		}
	}

	/**
	 * @param query
	 * @param synchronizedClass
	 */
	protected void addSynchronizedClass(SQLQuery query,
			List<Class<?>> synchronizedClass) {
		if (synchronizedClass != null) {
			for (Class<?> clazz : synchronizedClass) {
				query.addSynchronizedEntityClass(clazz);
			}
		}
	}
}
