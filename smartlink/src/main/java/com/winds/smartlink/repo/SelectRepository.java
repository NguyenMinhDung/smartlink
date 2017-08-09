package com.winds.smartlink.repo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.type.Type;

import com.winds.smartlink.exceptions.DataAccessException;
import com.winds.smartlink.repo.vo.KPaging;
import com.winds.smartlink.repo.vo.SpParam;

/**
 * @author Windreams
 */
public interface SelectRepository {
	
	/**
	 * Execute query.
	 * @param sql
	 * @param params
	 * @return number
	 * @throws DataAccessException
	 */
	int executeSQLQuery(String sql, List<Object> params)
			throws DataAccessException;

	/**
	 * execute store procedure.
	 * @param spName
	 * @param inParams
	 * @param outParams
	 * @throws DataAccessException
	 */
	void executeSP(String spName, List<SpParam> inParams,
			List<SpParam> outParams) throws DataAccessException;
	
	/**
	 * Get entity by id.
	 * @param clazz
	 * @param id
	 * @return entity
	 * @throws DataAccessException
	 */
	<T> T getEntityById(Class<T> clazz, Serializable id)
			throws DataAccessException;

	/**
	 * GEt entity by hql.
	 * @param hql
	 * @param params
	 * @return entity
	 * @throws DataAccessException
	 */
	<T> T getEntityByHQL(String hql, List<Object> params)
			throws DataAccessException;

	/**
	 * Get entity by sql and parameters.
	 * @param clazz
	 * @param sql
	 * @param params
	 * @return entity
	 * @throws DataAccessException
	 */
	<T> T getEntityBySQL(Class<T> clazz, String sql, List<Object> params)
			throws DataAccessException;

	/**
	 * Get entity by sql.
	 * @param clazz
	 * @param sql
	 * @param params
	 * @param synchronizedClass
	 * @return entity
	 * @throws DataAccessException
	 */
	<T> T getEntityBySQL(Class<T> clazz, String sql, List<Object> params,
			List<Class<?>> synchronizedClass) throws DataAccessException;

	/**
	 * Get first record by query.
	 * @param clazz
	 * @param sql
	 * @param params
	 * @return the first element in results by query.
	 * @throws DataAccessException
	 */
	<T> T getFirstBySQL(Class<T> clazz, String sql, List<Object> params)
			throws DataAccessException;

	/**
	 * Get first record by query.
	 * @param clazz
	 * @param sql
	 * @param params
	 * @param synchronizedClass
	 * @return first record
	 * @throws DataAccessException
	 */
	<T> T getFirstBySQL(Class<T> clazz, String sql, List<Object> params,
			List<Class<?>> synchronizedClass) throws DataAccessException;

	/**
	 * Get list record by hql.
	 * @param hql
	 * @param params
	 * @return list object
	 * @throws DataAccessException
	 */
	<T> List<T> getListByHQL(String hql, List<Object> params)
			throws DataAccessException;

	/**
	 * Get list paging record by hql.
	 * @param selectHql
	 * @param countHql
	 * @param selectParams
	 * @param countParams
	 * @param paging
	 * @return list record
	 * @throws DataAccessException
	 */
	<T> List<T> getListByHQLPaginated(String selectHql, String countHql,
			List<Object> selectParams, List<Object> countParams,
			KPaging<T> paging) throws DataAccessException;

	/**
	 * Get list paging by hql.
	 * @param hql
	 * @param params
	 * @param paging
	 * @return list record
	 * @throws DataAccessException
	 */
	<T> List<T> getListByHQLPaginated(String hql, List<Object> params,
			KPaging<T> paging) throws DataAccessException;

	/**
	 * Get list by sql.
	 * @param clazz
	 * @param sql
	 * @param params
	 * @return list record
	 * @throws DataAccessException
	 */
	<T> List<T> getListBySQL(Class<T> clazz, String sql, List<Object> params)
			throws DataAccessException;

	/**
	 * Get list by sql.
	 * @param clazz
	 * @param sql
	 * @param params
	 * @return list record
	 * @throws DataAccessException
	 */
	<T> List<T> getListBySQL(Class<T> clazz, StringBuilder sql, List<Object> params)
			throws DataAccessException;
	
	/**
	 * Get list by sql.
	 * @param clazz
	 * @param sql
	 * @param params
	 * @param synchronizedClass
	 * @return list record
	 * @throws DataAccessException
	 */
	<T> List<T> getListBySQL(Class<T> clazz, String sql, List<Object> params,
			List<Class<?>> synchronizedClass) throws DataAccessException;

	/**
	 * Get list paging by sql.
	 * @param clazz
	 * @param selectSql
	 * @param countSql
	 * @param selectParams
	 * @param countParams
	 * @param paging
	 * @return list record
	 * @throws DataAccessException
	 */
	<T> List<T> getListBySQLPaginated(Class<T> clazz, String selectSql,
			String countSql, List<Object> selectParams,
			List<Object> countParams, KPaging<T> paging)
			throws DataAccessException;

	/**
	 * Get list by sql.
	 * @param clazz
	 * @param selectSql
	 * @param countSql
	 * @param selectParams
	 * @param countParams
	 * @param paging
	 * @param synchronizedClass
	 * @return list record
	 * @throws DataAccessException
	 */
	<T> List<T> getListBySQLPaginated(Class<T> clazz, String selectSql,
			String countSql, List<Object> selectParams,
			List<Object> countParams, KPaging<T> paging,
			List<Class<?>> synchronizedClass) throws DataAccessException;

	/**
	 * Get list by sql paging.
	 * @param clazz
	 * @param sql
	 * @param params
	 * @param paging
	 * @return list record
	 * @throws DataAccessException
	 */
	<T> List<T> getListBySQLPaginated(Class<T> clazz, String sql,
			List<Object> params, KPaging<T> paging) throws DataAccessException;

	/**
	 * Get list paging by sql.
	 * @param clazz
	 * @param sql
	 * @param params
	 * @param paging
	 * @param synchronizedClass
	 * @return list record
	 * @throws DataAccessException
	 */
	<T> List<T> getListBySQLPaginated(Class<T> clazz, String sql,
			List<Object> params, KPaging<T> paging,
			List<Class<?>> synchronizedClass) throws DataAccessException;

	/**
	 * Get list by query scalar.
	 * @param clazz
	 * @param fieldNames
	 * @param fieldTypes
	 * @param sql
	 * @param params
	 * @return list record
	 * @throws DataAccessException
	 */
	<T> List<T> getListByQueryAndScalar(Class<T> clazz, String[] fieldNames,
			Type[] fieldTypes, String sql, List<Object> params)
			throws DataAccessException;

	/**
	 * @param clazz
	 * @param fieldNames
	 * @param fieldTypes
	 * @param sql
	 * @param params
	 * @param synchronizedClass
	 * @return list record
	 * @throws DataAccessException
	 */
	<T> List<T> getListByQueryAndScalar(Class<T> clazz, String[] fieldNames,
			Type[] fieldTypes, String sql, List<Object> params,
			List<Class<?>> synchronizedClass) throws DataAccessException;

	/**
	 * @param clazz
	 * @param fieldNames
	 * @param fieldTypes
	 * @param selectSql
	 * @param countSql
	 * @param selectParams
	 * @param countParams
	 * @param paging
	 * @return list record
	 * @throws DataAccessException
	 */
	<T> List<T> getListByQueryAndScalarPaginated(Class<T> clazz,
			String[] fieldNames, Type[] fieldTypes, String selectSql,
			String countSql, List<Object> selectParams,
			List<Object> countParams, KPaging<T> paging)
			throws DataAccessException;

	/**
	 * @param clazz
	 * @param fieldNames
	 * @param fieldTypes
	 * @param selectSql
	 * @param countSql
	 * @param selectParams
	 * @param countParams
	 * @param paging
	 * @param synchronizedClass
	 * @return list record
	 * @throws DataAccessException
	 */
	<T> List<T> getListByQueryAndScalarPaginated(Class<T> clazz,
			String[] fieldNames, Type[] fieldTypes, String selectSql,
			String countSql, List<Object> selectParams,
			List<Object> countParams, KPaging<T> paging,
			List<Class<?>> synchronizedClass) throws DataAccessException;

	/**
	 * @param countHql
	 * @param countParams
	 * @return number record
	 * @throws DataAccessException
	 */
	int countByHQL(String countHql, List<Object> countParams)
			throws DataAccessException;

	/**
	 * @param countSql
	 * @param countParams
	 * @return number of record
	 * @throws DataAccessException
	 */
	int countBySQL(String countSql, List<Object> countParams)
			throws DataAccessException;

	/**
	 * @param countSql
	 * @param countParams
	 * @param synchronizedClass
	 * @return number of record
	 * @throws DataAccessException
	 */
	int countBySQL(String countSql, List<Object> countParams,
			List<Class<?>> synchronizedClass) throws DataAccessException;

	/**
	 * @param countSql
	 * @param countParams
	 * @return number of record
	 * @throws DataAccessException
	 */
	BigDecimal countBySQLReturnBigDecimal(String countSql, List<Object> countParams) throws DataAccessException;
	
	/**
	 * @param namedQuerySQL
	 * @param params
	 * @return list record
	 * @throws DataAccessException
	 */
	<T> List<T> getListByNamedQuery(String namedQuerySQL, List<Object> params)
			throws DataAccessException;

	/**
	 * @param sql
	 * @param params
	 * @return object
	 * @throws DataAccessException
	 */
	Object getObjectByQuery(String sql, List<Object> params)
			throws DataAccessException;

	/**
	 * @param sql
	 * @param params
	 * @param synchronizedClass
	 * @return object
	 * @throws DataAccessException
	 */
	Object getObjectByQuery(String sql, List<Object> params,
			List<Class<?>> synchronizedClass) throws DataAccessException;

	/**
	 * @return session factory
	 */
	SessionFactory getSessionFactory();

	/**
	 * @param clazz
	 * @param namedQuerySQL
	 * @param params
	 * @return object
	 * @throws DataAccessException
	 */
	<T> List<T> getListByNamedQuery(Class<T> clazz, String namedQuerySQL,
			List<Object> params) throws DataAccessException;

	/**
	 * @param clazz
	 * @param sql
	 * @param params
	 * @param maxResult
	 * @return list record
	 * @throws DataAccessException
	 */
	<T> List<T> getListBySQL(Class<T> clazz, String sql, List<Object> params,
			int maxResult) throws DataAccessException;

	/**
	 * @param clazz
	 * @param sql
	 * @param params
	 * @param synchronizedClass
	 * @param maxResult
	 * @return list record
	 * @throws DataAccessException
	 */
	<T> List<T> getListBySQL(Class<T> clazz, String sql, List<Object> params,
			List<Class<?>> synchronizedClass, Integer maxResult)
			throws DataAccessException;

	/**
	 * @param clazz
	 * @param fieldNames
	 * @param fieldTypes
	 * @param sql
	 * @param params
	 * @param synchronizedClass
	 * @param maxResult
	 * @return list record
	 * @throws DataAccessException
	 */
	<T> List<T> getListByQueryAndScalar(Class<T> clazz, String[] fieldNames,
			Type[] fieldTypes, String sql, List<Object> params,
			List<Class<?>> synchronizedClass, Integer maxResult)
			throws DataAccessException;

	/**
	 * @param clazz
	 * @param fieldNames
	 * @param fieldTypes
	 * @param sql
	 * @param params
	 * @param maxResult
	 * @return list record
	 * @throws DataAccessException
	 */
	<T> List<T> getListByQueryAndScalar(Class<T> clazz, String[] fieldNames,
			Type[] fieldTypes, String sql, List<Object> params,
			Integer maxResult) throws DataAccessException;

	/**
	 * Check exists by sql.
	 * @param lstSql
	 * @param lstParams
	 * @return true if exists else false.
	 * @throws DataAccessException
	 */
	List<Boolean> checkExistBySQL(List<String> lstSql,
			List<List<Object>> lstParams) throws DataAccessException;

	/**
	 * @param sql
	 * @param params
	 * @param fetchSize
	 * @param firstResult
	 * @return list object
	 * @throws DataAccessException
	 */
	List<Object> getDataToListPaginated(String sql, List<Object> params,
			int fetchSize, int firstResult) throws DataAccessException;

	/**
	 * @author nald
	 * @description ham ho tro lay report cot dong tu cau SQL truy van co truoc
	 * @param clazz VO nhan ket qua tra ve. 
	 * VO gom cac thuoc tinh tinh, va 1 list kieu DynamicVO. Tham khao TestDynamicVO.java
	 * @param sql cau truy van SQL
	 * @param maxResult so ket qua toi da tra ve
	 * @return List<VO>
	 */
	<T> List<T> getListByQueryDynamicFromPackage(Class<T> clazz, String sql,
			List<Object> params, Integer maxResult) throws DataAccessException;
	
	/**
	 * Get query dynamic from package many result.
	 * @param lstName
	 * @param sql
	 * @param params
	 * @param maxResult
	 * @param clazz
	 * @return multi list
	 * @throws DataAccessException
	 */
	LinkedHashMap<String, Object> getListByQueryDynamicFromPackageManyResult(List<String> lstName, String sql,
			List<Object> params, Integer maxResult, Class<?>... clazz) throws DataAccessException;
	
	/**
	 * @param lstName
	 * @param sql
	 * @param params
	 * @param maxResult
	 * @param clazz
	 * @return list results
	 * @throws DataAccessException
	 */
	LinkedHashMap<String, Object> getListByQueryDynamicFromPackageManyResultWithColumnUnderlined(
			List<String> lstName, String sql, List<Object> params, 
			Integer maxResult, Class<?>... clazz) throws DataAccessException;

	/**
	 * Call procedure.
	 * @param sql
	 * @param params
	 * @throws DataAccessException
	 */
	void callProcedure(String sql, List<Object> params)
			throws DataAccessException;
}
