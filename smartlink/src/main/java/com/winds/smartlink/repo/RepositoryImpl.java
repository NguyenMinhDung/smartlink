package com.winds.smartlink.repo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;

import com.winds.smartlink.exceptions.DataAccessException;
import com.winds.smartlink.repo.vo.KPaging;
import com.winds.smartlink.repo.vo.SpParam;

/**
 * @author Windreams
 *
 */
public class RepositoryImpl implements IRepository {

	@Autowired
	private CrudRepository insertRepo;
	
	@Autowired
	private SelectRepository selectRepo;

	@Override
	public <T> T getEntityById(Class<T> clazz, Serializable id)
			throws DataAccessException {
		return selectRepo.getEntityById(clazz, id);
	}

	@Override
	public <T> T getEntityById(Class<T> clazz, Serializable id,
			boolean oneSession) throws DataAccessException {
		if (oneSession) {
			return getEntityById(clazz, id);
		} else {
			return selectRepo.getEntityById(clazz, id);
		}
	}

	@Override
	public <T> T getEntityByHQL(String hql, List<Object> params)
			throws DataAccessException {
		return selectRepo.getEntityByHQL(hql, params);
	}

	@Override
	public <T> T getEntityBySQL(Class<T> clazz, String sql, List<Object> params)
			throws DataAccessException {
		return selectRepo.getEntityBySQL(clazz, sql, params);
	}

	@Override
	public <T> T getEntityBySQL(Class<T> clazz, String sql,
			List<Object> params, List<Class<?>> synchronizedClass)
			throws DataAccessException {
		return selectRepo.getEntityBySQL(clazz, sql, params, synchronizedClass);
	}

	@Override
	public <T> List<T> getListByHQL(String hql, List<Object> params)
			throws DataAccessException {
		return selectRepo.getListByHQL(hql, params);
	}

	@Override
	public <T> List<T> getListByHQLPaginated(String selectHql, String countHql,
			List<Object> selectParams, List<Object> countParams,
			KPaging<T> paging) throws DataAccessException {
		return selectRepo.getListByHQLPaginated(selectHql, countHql,
				selectParams, countParams, paging);
	}

	@Override
	public <T> List<T> getListByHQLPaginated(String hql, List<Object> params,
			KPaging<T> paging) throws DataAccessException {
		return selectRepo.getListByHQLPaginated(hql, params, paging);
	}

	@Override
	public <T> List<T> getListBySQL(Class<T> clazz, String sql,
			List<Object> params) throws DataAccessException {
		return selectRepo.getListBySQL(clazz, sql, params);
	}

	@Override
	public <T> List<T> getListBySQL(Class<T> clazz, StringBuilder sql,
			List<Object> params) throws DataAccessException {
		return selectRepo.getListBySQL(clazz, sql, params);
	}
	
	@Override
	public <T> List<T> getListBySQL(Class<T> clazz, String sql,
			List<Object> params, List<Class<?>> synchronizedClass)
			throws DataAccessException {
		return selectRepo.getListBySQL(clazz, sql, params, synchronizedClass);
	}

	@Override
	public <T> List<T> getListBySQLPaginated(Class<T> clazz, String selectSql,
			String countSql, List<Object> selectParams,
			List<Object> countParams, KPaging<T> paging)
			throws DataAccessException {
		return selectRepo.getListBySQLPaginated(clazz, selectSql, countSql,
				selectParams, countParams, paging);
	}

	@Override
	public <T> List<T> getListBySQLPaginated(Class<T> clazz, String selectSql,
			String countSql, List<Object> selectParams,
			List<Object> countParams, KPaging<T> paging,
			List<Class<?>> synchronizedClass) throws DataAccessException {
		return selectRepo.getListBySQLPaginated(clazz, selectSql, countSql,
				selectParams, countParams, paging, synchronizedClass);
	}

	@Override
	public <T> List<T> getListBySQLPaginated(Class<T> clazz, String sql,
			List<Object> params, KPaging<T> paging) throws DataAccessException {
		return selectRepo.getListBySQLPaginated(clazz, sql, params, paging);
	}

	@Override
	public <T> List<T> getListBySQLPaginated(Class<T> clazz, String sql,
			List<Object> params, KPaging<T> paging,
			List<Class<?>> synchronizedClass) throws DataAccessException {
		return selectRepo.getListBySQLPaginated(clazz, sql, params, paging,
				synchronizedClass);
	}

	@Override
	public <T> List<T> getListByQueryAndScalar(Class<T> clazz,
			String[] fieldNames, Type[] fieldTypes, String sql,
			List<Object> params) throws DataAccessException {
		return selectRepo.getListByQueryAndScalar(clazz, fieldNames,
				fieldTypes, sql, params);
	}

	@Override
	public <T> List<T> getListByQueryAndScalar(Class<T> clazz,
			String[] fieldNames, Type[] fieldTypes, String sql,
			List<Object> params, List<Class<?>> synchronizedClass)
			throws DataAccessException {
		return selectRepo.getListByQueryAndScalar(clazz, fieldNames,
				fieldTypes, sql, params, synchronizedClass);
	}

	@Override
	public <T> List<T> getListByQueryAndScalarPaginated(Class<T> clazz,
			String[] fieldNames, Type[] fieldTypes, String selectSql,
			String countSql, List<Object> selectParams,
			List<Object> countParams, KPaging<T> paging)
			throws DataAccessException {
		return selectRepo.getListByQueryAndScalarPaginated(clazz, fieldNames,
				fieldTypes, selectSql, countSql, selectParams, countParams,
				paging);
	}

	@Override
	public <T> List<T> getListByQueryAndScalarPaginated(Class<T> clazz,
			String[] fieldNames, Type[] fieldTypes, String selectSql,
			String countSql, List<Object> selectParams,
			List<Object> countParams, KPaging<T> paging,
			List<Class<?>> synchronizedClass) throws DataAccessException {
		return selectRepo.getListByQueryAndScalarPaginated(clazz, fieldNames,
				fieldTypes, selectSql, countSql, selectParams, countParams,
				paging, synchronizedClass);
	}

	@Override
	public int countByHQL(String countHql, List<Object> countParams)
			throws DataAccessException {
		return selectRepo.countByHQL(countHql, countParams);
	}

	@Override
	public int countBySQL(String countSql, List<Object> countParams)
			throws DataAccessException {
		return selectRepo.countBySQL(countSql, countParams);
	}

	@Override
	public int countBySQL(String countSql, List<Object> countParams,
			List<Class<?>> synchronizedClass) throws DataAccessException {
		return selectRepo.countBySQL(countSql, countParams, synchronizedClass);
	}

	@Override
	public <T> List<T> getListByNamedQuery(String namedQuerySQL,
			List<Object> params) throws DataAccessException {
		return selectRepo.getListByNamedQuery(namedQuerySQL, params);
	}

	@Override
	public <T> List<T> getListByNamedQuery(Class<T> clazz,
			String namedQuerySQL, List<Object> params)
			throws DataAccessException {
		return selectRepo.getListByNamedQuery(clazz, namedQuerySQL, params);
	}

	@Override
	public SessionFactory getSessionFactory() {
		return selectRepo.getSessionFactory();
	}

	@Override
	public <T> T create(T object) throws DataAccessException {
		return insertRepo.create(object);
	}

	@Override
	public void update(Object object) throws DataAccessException {
		insertRepo.update(object);
	}

	@Override
	public int executeSQLQuery(String sql, List<Object> params)
			throws DataAccessException {
		return selectRepo.executeSQLQuery(sql, params);
	}

	@Override
	public void delete(Object object) throws DataAccessException {
		insertRepo.delete(object);
	}

	@Override
	public Object getObjectByQuery(String sql, List<Object> params)
			throws DataAccessException {
		return selectRepo.getObjectByQuery(sql, params);
	}

	@Override
	public <T> T getFirstBySQL(Class<T> clazz, String sql, List<Object> params)
			throws DataAccessException {
		return selectRepo.getFirstBySQL(clazz, sql, params);
	}

	@Override
	public <T> T getFirstBySQL(Class<T> clazz, String sql, List<Object> params,
			List<Class<?>> synchronizedClass) throws DataAccessException {
		return selectRepo.getFirstBySQL(clazz, sql, params, synchronizedClass);
	}

	@Override
	public Object getObjectByQuery(String sql, List<Object> params,
			List<Class<?>> synchronizedClass) throws DataAccessException {
		return selectRepo.getObjectByQuery(sql, params, synchronizedClass);
	}

	@Override
	public <T> List<T> getListBySQL(Class<T> clazz, String sql,
			List<Object> params, int maxResult) throws DataAccessException {
		return selectRepo.getListBySQL(clazz, sql, params, maxResult);
	}

	@Override
	public <T> List<T> getListBySQL(Class<T> clazz, String sql,
			List<Object> params, List<Class<?>> synchronizedClass,
			Integer maxResult) throws DataAccessException {
		return selectRepo.getListBySQL(clazz, sql, params, synchronizedClass, maxResult);
	}

	@Override
	public <T> List<T> getListByQueryAndScalar(Class<T> clazz,
			String[] fieldNames, Type[] fieldTypes, String sql,
			List<Object> params, List<Class<?>> synchronizedClass,
			Integer maxResult) throws DataAccessException {
		return selectRepo.getListByQueryAndScalar(clazz, fieldNames,
				fieldTypes, sql, params, synchronizedClass, maxResult);
	}

	@Override
	public <T> List<T> getListByQueryAndScalar(Class<T> clazz,
			String[] fieldNames, Type[] fieldTypes, String sql,
			List<Object> params, Integer maxResult) throws DataAccessException {
		return selectRepo.getListByQueryAndScalar(clazz, fieldNames,
				fieldTypes, sql, params, null, maxResult);
	}

	@Override
	public <T> List<T> create(List<T> lstObject) throws DataAccessException {
		return insertRepo.create(lstObject);
	}

	@Override
	public List<Boolean> checkExistBySQL(List<String> lstSql,
			List<List<Object>> lstParams) throws DataAccessException {
		return selectRepo.checkExistBySQL(lstSql, lstParams);
	}

	@Override
	public <T> List<T> update(List<T> lstObject) throws DataAccessException {
		return insertRepo.update(lstObject);
	}

	@Override
	public List<Object> getDataToListPaginated(String sql, List<Object> params,
			int fetchSize, int firstResult) throws DataAccessException {
		return selectRepo.getDataToListPaginated(sql, params, fetchSize, firstResult);
	}

	@Override
	public void executeSP(String spName, List<SpParam> inParams,
			List<SpParam> outParams) throws DataAccessException {
		selectRepo.executeSP(spName, inParams, outParams);
	}
	
	@Override
	public BigDecimal countBySQLReturnBigDecimal(String countSql,
			List<Object> countParams) throws DataAccessException {
		
		return selectRepo.countBySQLReturnBigDecimal(countSql, countParams);
	}
	
	@Override
	public <T> List<T> getListByQueryDynamicFromPackage(Class<T> clazz,
			String sql, List<Object> params, Integer maxResult)
			throws DataAccessException {
		return selectRepo.getListByQueryDynamicFromPackage(clazz, sql, params, maxResult);
	}
	
	@Override
	public LinkedHashMap<String, Object> getListByQueryDynamicFromPackageManyResult(List<String> lstName,
			String sql, List<Object> params, Integer maxResult, Class<?>... clazz)
			throws DataAccessException {
		return selectRepo.getListByQueryDynamicFromPackageManyResult( lstName, sql, params, maxResult,clazz);
	}
	
	@Override
	public LinkedHashMap<String, Object> getListByQueryDynamicFromPackageManyResultWithColumnUnderlined(
			List<String> lstName, String sql, List<Object> params, Integer maxResult, Class<?>... clazz)
			throws DataAccessException {
		return selectRepo.getListByQueryDynamicFromPackageManyResultWithColumnUnderlined(lstName, sql, params,
				maxResult, clazz);
	}

	@Override
	public void callProcedure(String sql,
			List<Object> params) throws DataAccessException {
		selectRepo.callProcedure(sql, params);
	}
}
