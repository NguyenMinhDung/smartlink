package com.winds.smartlink.repo;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.AbstractStandardBasicType;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.winds.smartlink.exceptions.DataAccessException;
import com.winds.smartlink.repo.vo.DynamicVO;
import com.winds.smartlink.repo.vo.KPaging;
import com.winds.smartlink.repo.vo.SpParam;
import com.winds.smartlink.utils.GenericUtil;
import com.winds.smartlink.utils.StringUtils;



/**
 * @author Windreams
 *
 */
public class SelectRepositoryImpl extends AbstractRepository implements
		SelectRepository {
	/** The session factory. */
	@Autowired
	@Qualifier(value="sessionFactory")
	private SessionFactory selectSessionFactory;

	/**
	 * @param selectSessionFactory
	 */
	public SelectRepositoryImpl(SessionFactory selectSessionFactory) {
		this.selectSessionFactory = selectSessionFactory;
	}
	
	@Override
	public int executeSQLQuery(String sql, List<Object> params)
			throws DataAccessException {
		try {
			Session session = selectSessionFactory.getCurrentSession();
			SQLQuery query = session.createSQLQuery(sql);
			addParameters(query, params);
			return query.executeUpdate();
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}
	
	@Override
	public void executeSP(final String spName, final List<SpParam> inParams,
			final List<SpParam> outParams) throws DataAccessException {
		try {
			int numParam = inParams.size() + outParams.size();

			final StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("{call ");
			stringBuilder.append(spName);

			if (numParam > 0) {
				stringBuilder.append("(?");
				for (int i = 1; i < numParam; i++) {
					stringBuilder.append(", ?");
				}
				stringBuilder.append(")");
			}
			stringBuilder.append("}");

			// TODO: Kiem tra lai cho nay
			/*insertSessionFactory.getCurrentSession().doReturningWork(
				new ReturningWork<Boolean>() {
					public Boolean execute(Connection con)
							throws SQLException {
						try {
							CallableStatement st = con
									.prepareCall(stringBuilder.toString());
							if (inParams.size() > 0) {
								for (SpParam param : inParams) {
									setParam(st, param.getParamIndex(),
											param.getSqlType(),
											param.getValue());
								}
							}

							if (outParams.size() > 0) {
								for (SpParam param : outParams) {
									st.registerOutParameter(
											param.getParamIndex(),
											param.getSqlType());
								}
							}
							st.executeUpdate();

							for (SpParam outParam : outParams) {
								outParam.setValue(getValue(st,
										outParam.getParamIndex(),
										outParam.getSqlType()));
							}
						} catch (Exception e) {
							return false;
						}
						return true;
					}
				});*/

		} catch (HibernateException e) {
			throw new DataAccessException(e);
		}
	}

	@Override
	public <T> T getEntityById(Class<T> clazz, Serializable id)
			throws DataAccessException {
		try {
			Session session = selectSessionFactory.getCurrentSession();
			return clazz.cast(session.get(clazz, id));
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getEntityByHQL(String hql, List<Object> params)
			throws DataAccessException {
		try {
			
			Session session = selectSessionFactory.getCurrentSession();
			Query query = session.createQuery(hql);
			addParameters(query, params);
			query.setCacheable(true);

			query.setMaxResults(1);
			return (T) query.uniqueResult();
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	@Override
	public <T> T getEntityBySQL(Class<T> clazz, String sql, List<Object> params)
			throws DataAccessException {
		return getEntityBySQL(clazz, sql, params, null);
	}

	@Override
	public <T> T getEntityBySQL(Class<T> clazz, String sql,
			List<Object> params, List<Class<?>> synchronizedClass)
			throws DataAccessException {
		try {
			Session session = selectSessionFactory.getCurrentSession();
			SQLQuery query = session.createSQLQuery(sql);
			addParameters(query, params);
			addSynchronizedClass(query, synchronizedClass);
			query.setCacheable(true);
			query.addEntity(clazz);

			query.setMaxResults(1);
			return clazz.cast(query.uniqueResult());
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getListByHQL(String hql, List<Object> params)
			throws DataAccessException {
		try {
			Session session = selectSessionFactory.getCurrentSession();
			Query query = session.createQuery(hql);
			addParameters(query, params);
			query.setCacheable(true);
			return query.list();
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getListByHQLPaginated(String selectHql, String countHql,
			List<Object> selectParams, List<Object> countParams,
			KPaging<T> paging) throws DataAccessException {
		try {
			Session session = selectSessionFactory.getCurrentSession();

			Query selectQuery = session.createQuery(selectHql);
			addParameters(selectQuery, selectParams);
			selectQuery.setCacheable(true);
			selectQuery.setFirstResult(paging.getPage() * paging.getPageSize());
			selectQuery.setMaxResults(paging.getPageSize());
			paging.setList(selectQuery.list());

			int totalRows = countByHQL(countHql, countParams);
			paging.setTotalRows(totalRows);

			return paging.getList();
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	@Override
	public <T> List<T> getListByHQLPaginated(String hql, List<Object> params,
			KPaging<T> paging) throws DataAccessException {
		try {
			String countHql = hql.toLowerCase().trim();
			if (countHql.startsWith("select")) {
				countHql = hql.substring(countHql.indexOf("from"));
			} else {
				countHql = hql;
			}
			countHql = "select count(*) " + countHql;
			return getListByHQLPaginated(hql, countHql, params, params, paging);
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	@Override
	public <T> List<T> getListBySQL(Class<T> clazz, String sql,
			List<Object> params) throws DataAccessException {
		return getListBySQL(clazz, sql, params, null);
	}
	
	@Override
	public <T> List<T> getListBySQL(Class<T> clazz, StringBuilder sql,
			List<Object> params) throws DataAccessException {
		if (sql == null){
			sql = new StringBuilder();
			sql.append("");
		}
		return getListBySQL(clazz, sql.toString(), params, null);
	}

	@Override
	public <T> List<T> getListBySQL(Class<T> clazz, String sql,
			List<Object> params, int maxResult) throws DataAccessException {
		return getListBySQL(clazz, sql, params, null, maxResult);
	}

	@Override
	public <T> List<T> getListBySQL(Class<T> clazz, String sql,
			List<Object> params, List<Class<?>> synchronizedClass)
			throws DataAccessException {
		return getListBySQL(clazz, sql, params, synchronizedClass, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getListBySQL(Class<T> clazz, String sql,
			List<Object> params, List<Class<?>> synchronizedClass,
			Integer maxResult) throws DataAccessException {
		try {
			Session session = selectSessionFactory.getCurrentSession();
			SQLQuery query = session.createSQLQuery(sql);
			addParameters(query, params);
			addSynchronizedClass(query, synchronizedClass);
			query.setCacheable(true);
			query.addEntity(clazz);

			if (maxResult != null && maxResult > 0) {
				query.setMaxResults(maxResult);
			}

			return query.list();
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	@Override
	public <T> List<T> getListBySQLPaginated(Class<T> clazz, String selectSql,
			String countSql, List<Object> selectParams,
			List<Object> countParams, KPaging<T> paging)
			throws DataAccessException {
		return getListBySQLPaginated(clazz, selectSql, countSql, selectParams,
				countParams, paging, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getListBySQLPaginated(Class<T> clazz, String selectSql,
			String countSql, List<Object> selectParams,
			List<Object> countParams, KPaging<T> paging,
			List<Class<?>> synchronizedClass) throws DataAccessException {
		try {
			Session session = selectSessionFactory.getCurrentSession();

			SQLQuery selectQuery = session.createSQLQuery(selectSql);
			addParameters(selectQuery, selectParams);
			addSynchronizedClass(selectQuery, synchronizedClass);
			selectQuery.setCacheable(true);
			selectQuery.addEntity(clazz);
			selectQuery.setFirstResult(paging.getPage() * paging.getPageSize());
			selectQuery.setMaxResults(paging.getPageSize());
			paging.setList(selectQuery.list());

			int totalRows = countBySQL(countSql, countParams, synchronizedClass);
			paging.setTotalRows(totalRows);

			return paging.getList();
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	@Override
	public <T> List<T> getListBySQLPaginated(Class<T> clazz, String sql,
			List<Object> params, KPaging<T> paging) throws DataAccessException {
		return getListBySQLPaginated(clazz, sql, params, paging, null);
	}

	@Override
	public <T> List<T> getListBySQLPaginated(Class<T> clazz, String sql,
			List<Object> params, KPaging<T> paging,
			List<Class<?>> synchronizedClass) throws DataAccessException {
		try {
			String countSql = sql.toLowerCase().trim();

			countSql = "select count(*) AS count from (" + sql + ")";

			return getListBySQLPaginated(clazz, sql, countSql, params, params,
					paging, synchronizedClass);
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	@Override
	public <T> List<T> getListByQueryAndScalar(Class<T> clazz,
			String[] fieldNames, Type[] fieldTypes, String sql,
			List<Object> params) throws DataAccessException {
		return getListByQueryAndScalar(clazz, fieldNames, fieldTypes, sql,
				params, null, null);
	}

	@Override
	public <T> List<T> getListByQueryAndScalar(Class<T> clazz,
			String[] fieldNames, Type[] fieldTypes, String sql,
			List<Object> params, Integer maxResult) throws DataAccessException {
		return getListByQueryAndScalar(clazz, fieldNames, fieldTypes, sql,
				params, null, maxResult);
	}

	@Override
	public <T> List<T> getListByQueryAndScalar(Class<T> clazz,
			String[] fieldNames, Type[] fieldTypes, String sql,
			List<Object> params, List<Class<?>> synchronizedClass)
			throws DataAccessException {
		return getListByQueryAndScalar(clazz, fieldNames, fieldTypes, sql,
				params, synchronizedClass, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getListByQueryAndScalar(Class<T> clazz,
			String[] fieldNames, Type[] fieldTypes, String sql,
			List<Object> params, List<Class<?>> synchronizedClass,
			Integer maxResult) throws DataAccessException {
		try {
			Session session = selectSessionFactory.getCurrentSession();
			SQLQuery query = session.createSQLQuery(sql);
			addParameters(query, params);
			addScalar(query, fieldNames, fieldTypes);
			addSynchronizedClass(query, synchronizedClass);
			query.setResultTransformer(Transformers.aliasToBean(clazz));

			if (maxResult != null && maxResult > 0) {
				query.setMaxResults(maxResult);
			}

			return query.list();
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	@Override
	public <T> List<T> getListByQueryAndScalarPaginated(Class<T> clazz,
			String[] fieldNames, Type[] fieldTypes, String selectSql,
			String countSql, List<Object> selectParams,
			List<Object> countParams, KPaging<T> paging)
			throws DataAccessException {
		return getListByQueryAndScalarPaginated(clazz, fieldNames, fieldTypes,
				selectSql, countSql, selectParams, countParams, paging, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getListByQueryAndScalarPaginated(Class<T> clazz,
			String[] fieldNames, Type[] fieldTypes, String selectSql,
			String countSql, List<Object> selectParams,
			List<Object> countParams, KPaging<T> paging,
			List<Class<?>> synchronizedClass) throws DataAccessException {
		try {
			Session session = selectSessionFactory.getCurrentSession();
			SQLQuery selectQuery = session.createSQLQuery(selectSql);
			addParameters(selectQuery, selectParams);
			addScalar(selectQuery, fieldNames, fieldTypes);
			addSynchronizedClass(selectQuery, synchronizedClass);

			selectQuery.setResultTransformer(Transformers.aliasToBean(clazz));
			selectQuery.setFirstResult(paging.getPage() * paging.getPageSize());
			selectQuery.setMaxResults(paging.getPageSize());

			paging.setList(selectQuery.list());

			int totalRows = countBySQL(countSql, countParams, synchronizedClass);
			paging.setTotalRows(totalRows);

			return paging.getList();
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	@Override
	public int countByHQL(String countHql, List<Object> countParams)
			throws DataAccessException {
		try {
			Session session = selectSessionFactory.getCurrentSession();
			Query countQuery = session.createQuery(countHql);
			addParameters(countQuery, countParams);
			countQuery.setCacheable(true);
			return ((Number) countQuery.uniqueResult()).intValue();
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	@Override
	public int countBySQL(String countSql, List<Object> countParams)
			throws DataAccessException {
		return countBySQL(countSql, countParams, null);
	}

	@Override
	public int countBySQL(String countSql, List<Object> countParams,
			List<Class<?>> synchronizedClass) throws DataAccessException {
		try {
			Session session = selectSessionFactory.getCurrentSession();
			SQLQuery countQuery = session.createSQLQuery(countSql);
			addParameters(countQuery, countParams);
			addSynchronizedClass(countQuery, synchronizedClass);
			countQuery.addScalar("count", StandardBasicTypes.BIG_DECIMAL);
			countQuery.setCacheable(true);
			return ((Number) countQuery.uniqueResult()).intValue();
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getListByNamedQuery(String namedQuerySQL,
			List<Object> params) throws DataAccessException {
		try {
			Session session = selectSessionFactory.getCurrentSession();
			Query namedQuery = session.getNamedQuery(namedQuerySQL);
			addParameters(namedQuery, params);

			namedQuery.setCacheable(true);
			return namedQuery.list();
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getListByNamedQuery(final Class<T> clazz,
			final String namedQuerySQL, final List<Object> params)
			throws DataAccessException {
		try {
			final Session session = selectSessionFactory.getCurrentSession();
			final Query namedQuery = session.getNamedQuery(namedQuerySQL);

			namedQuery.setResultTransformer(Transformers.aliasToBean(clazz));

			for (int i = 0; i < params.size(); i += 3) {
				namedQuery.setParameter(params.get(i).toString(),
						params.get(i + 1),
						(AbstractStandardBasicType) params.get(i + 2));
			}
			return namedQuery.list();

		} catch (final HibernateException e) {
			throw new DataAccessException(
					"Failed to getTopByNamedNativeQuery: " + e.getMessage(), e);
		}
	}

	@Override
	public Object getObjectByQuery(String sql, List<Object> params)
			throws DataAccessException {
		return getObjectByQuery(sql, params, null);
	}

	@Override
	public Object getObjectByQuery(String sql, List<Object> params,
			List<Class<?>> synchronizedClass) throws DataAccessException {
		try {
			Session sess = selectSessionFactory.getCurrentSession();
			SQLQuery qSelect = sess.createSQLQuery(sql);
			addParameters(qSelect, params);
			addSynchronizedClass(qSelect, synchronizedClass);

			qSelect.setMaxResults(1);
			return qSelect.uniqueResult();

		} catch (final HibernateException e) {
			throw new DataAccessException("Failed to getObjectByQuery: "
					+ e.getMessage(), e);
		}
	}

	@Override
	public SessionFactory getSessionFactory() {
		return selectSessionFactory;
	}

	@Override
	public <T> T getFirstBySQL(Class<T> clazz, String sql, List<Object> params)
			throws DataAccessException {
		return getFirstBySQL(clazz, sql, params, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getFirstBySQL(Class<T> clazz, String sql, List<Object> params,
			List<Class<?>> synchronizedClass) throws DataAccessException {
		try {
			Session session = selectSessionFactory.getCurrentSession();
			SQLQuery query = session.createSQLQuery(sql);
			addParameters(query, params);
			addSynchronizedClass(query, synchronizedClass);
			query.setCacheable(true);
			query.addEntity(clazz);
			query.setMaxResults(1);

			List<T> list = query.list();

			if (list.isEmpty()) {
				return list.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	@Override
	public List<Boolean> checkExistBySQL(List<String> lstSql,
			List<List<Object>> lstParams) throws DataAccessException {

		List<Boolean> rs = new ArrayList<Boolean>();
		Session session = selectSessionFactory.getCurrentSession();
		for (int i = 0; i < lstSql.size(); i++) {
			String sql = lstSql.get(i);
			sql = "select 1 as count " + sql;
			List<Object> params = lstParams.get(i);

			SQLQuery query = session.createSQLQuery(sql);
			addParameters(query, params);
			query.setCacheable(true);

			query.addScalar("count", StandardBasicTypes.BIG_DECIMAL);
			Object c = query.uniqueResult();
			rs.add(c == null ? false : true);
		}
		return rs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getDataToListPaginated(final String sql,
			final List<Object> params, final int fetchSize,
			final int firstResult) throws DataAccessException {
		try {

			final Session session = selectSessionFactory.getCurrentSession();
			SQLQuery query = session.createSQLQuery(sql);

			addParameters(query, params);

			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

			if (fetchSize > 0) {
				query.setMaxResults(fetchSize);
			}

			if (firstResult > 0) {
				query.setFirstResult(firstResult);
			}

			List<Object> data = query.list();

			return data;
		} catch (Exception ex) {
			throw new DataAccessException(ex);
		}
	}

	@Override
	public BigDecimal countBySQLReturnBigDecimal(String countSql,
			List<Object> countParams) throws DataAccessException {
		Session session = selectSessionFactory.getCurrentSession();
		SQLQuery countQuery = session.createSQLQuery(countSql);
		addParameters(countQuery, countParams);
		addSynchronizedClass(countQuery, null);
		countQuery.addScalar("count", StandardBasicTypes.BIG_DECIMAL);
		countQuery.setCacheable(true);
		return (BigDecimal)countQuery.uniqueResult();
	}
	
	@Override
	public void callProcedure(String sql, List<Object> params)
				throws DataAccessException{
		try{
			Session session = selectSessionFactory.getCurrentSession();
			/*Connection connection = ((SessionImpl)session).connection();*/
			Connection connection = null;
		    CallableStatement callable = connection.prepareCall(sql);
		    callable.setLong(1, (Long)params.get(0));
//		    for (int i = 0; i < params.size(); i += 3) {
//		    	Long tmpName = (Long) params.get(i);
//		    	Object tmpValue = params.get(i + 1);
//		    	Long tmpTypes = (Long) params.get(i + 2);
//		    	
//		    	callable.setObject(tmpName,tmpValue, tmpTypes);
//			}
		    
//		    callable.registerOutParameter(1, OracleTypes.CURSOR);
		    callable.execute();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public <T> List<T> getListByQueryDynamicFromPackage(Class<T> clazz,
			String sql, List<Object> params, Integer maxResult)
			throws DataAccessException {
		try {
			List<Object> listTmp;
			Session session = selectSessionFactory.getCurrentSession();
			/*Connection connection = ((SessionImpl)session).connection();*/
			Connection connection = null;
		    CallableStatement callable = connection.prepareCall(sql);
		    for (int i = 0; i < params.size(); i += 3) {
		    	Integer tmpName = (Integer) params.get(i);
		    	Object tmpValue = params.get(i + 1);
		    	Integer tmpTypes = (Integer) params.get(i + 2);
		    	
		    	callable.setObject(tmpName,tmpValue, tmpTypes);
			}
		    
		    // TODO: Kiem tra lai cho nay
		    //callable.registerOutParameter(1, OracleTypes.CURSOR);
		    callable.execute();
		    
		    ResultSet rs = null;
		    rs = (ResultSet) callable.getObject(1);
		    
		    ResultSetMetaData metaData = rs.getMetaData();
		    int columns = metaData.getColumnCount();

		    listTmp = new ArrayList<Object>();

		    while (rs.next()) {
		        LinkedHashMap<String, Object> listItem = new LinkedHashMap<String, Object>();
		        for (int i = 1; i <= columns; i++) {
		            Object value = rs.getObject(i);
		            // TODO: kiem tra lai cho nay
		            // String key = StringUtility.dropUnderlined(metaData.getColumnName(i));
		            //listItem.put(key, value);
		        }
		        listTmp.add(listItem);
		    }
			
			List<T> listResults = convert2ClassVO(clazz, listTmp);

			return listResults;
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}
	
	/**
	 * Convert class to VO.
	 * @param clazz
	 * @param listTmp
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws NoSuchFieldException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 */
	private <T> List<T> convert2ClassVO(Class<T> clazz, List<Object> listTmp)
			throws InstantiationException, IllegalAccessException,
			NoSuchFieldException, NoSuchMethodException,
			InvocationTargetException {
		List<T> listResults = new ArrayList<T>();

		Field[] fields = clazz.getDeclaredFields();
		if (listTmp.isEmpty()) {

			for (Object object : listTmp) {
				T subClazz = clazz.newInstance();

				Map<?,?> row = (Map<?,?>) object;
				Set<?> keySet = row.keySet();
				
				List<DynamicVO> tmpDynamicVOs = new ArrayList<>();
				
				for (Object key : keySet) {
					String tmp = GenericUtil.hasInFields(key, fields);
					if (tmp != null) {
						Object tmpKey = row.get(key);
						if (tmpKey == null) {
							GenericUtil.applyValue2Property(subClazz, tmp, "");
						} else {
							GenericUtil.applyValue2Property(subClazz, 
									tmp, row.get(key).toString());
						}
					} else {
						DynamicVO tmpDynamicVO = new DynamicVO();
						tmpDynamicVO.setKey(key.toString());
						tmpDynamicVO.setValue(row.get(key));
						
						tmpDynamicVOs.add(tmpDynamicVO);
					}
				}
				
				for (Field f : fields) {
					java.lang.reflect.Type type = f.getGenericType();
					
					// Tim thuoc tinh kieu List<DynamicVO> trong Class
					if (type instanceof ParameterizedType) {
						ParameterizedType stringListType = (ParameterizedType) f
								.getGenericType();
						Class<?> stringListClass = (Class<?>) stringListType
								.getActualTypeArguments()[0];
						
						if(stringListClass.equals(DynamicVO.class))
						{
							//Thuoc tinh kieu List<DynamicVO>
							String proName = f.getName();
							Class<?> clz = subClazz.getClass().getDeclaredField(proName)
									.getType();
							String proNameUpperFirst = proName.substring(0, 1).toUpperCase()
									+ proName.substring(1);
							Method setMethod = subClazz.getClass().getMethod(
									"set" + proNameUpperFirst, clz);
							setMethod.invoke(subClazz, tmpDynamicVOs);
							
							break;
						}
						
					}
				}

				listResults.add(subClazz);
			}
		}
		return listResults;
	}

	@Override
	public  LinkedHashMap <String, Object>  getListByQueryDynamicFromPackageManyResult(List<String> lstName,
			String sql, List<Object> params, Integer maxResult, Class<?> ... clazz
			) throws DataAccessException {
		try {
			List<Object> listTmp;
			LinkedHashMap<String,Object> hashMapResults = new LinkedHashMap<String,Object>();
			
			Session session = selectSessionFactory.getCurrentSession();
			/*Connection connection = ((SessionImpl)session).connection();*/
			Connection connection = null;
		    CallableStatement callable = connection.prepareCall(sql);
		    for (int i = 0; i < params.size(); i += 3) {
		    	Integer tmpName = (Integer) params.get(i);
		    	Object tmpValue = params.get(i + 1);
		    	Integer tmpTypes = (Integer) params.get(i + 2);
		    	callable.setObject(tmpName,tmpValue, tmpTypes);
			}
			for (int k = 0; k < lstName.size(); ++k) {
		    	// TODO: Kiem tra lai cho nay
		    	//callable.registerOutParameter(k +1, OracleTypes.CURSOR);
				throw new UnsupportedOperationException();
		    }
		    callable.execute();
		    ResultSet rs = null;
			for (int k = 0; k < lstName.size(); ++k) {
				rs = (ResultSet) callable.getObject(k + 1);
			    ResultSetMetaData metaData = rs.getMetaData();
			    int columns = metaData.getColumnCount();
			    listTmp = new ArrayList<Object>();
			    while (rs.next()) {
			        LinkedHashMap<String, Object> listItem = new LinkedHashMap<String, Object>();
			        for (int i = 1; i <= columns; i++) {
			            Object value = rs.getObject(i);
			            String key = StringUtils.dropUnderlined(metaData.getColumnName(i));
			            listItem.put(key, value);
			        }
			        listTmp.add(listItem);
			    }
			    hashMapResults.put(lstName.get(k),convert2ClassVO(clazz[k], listTmp));
				if (k == lstName.size() - 1) {
					break;
				}
		    }
			return hashMapResults;
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}
	
	@Override
	public  LinkedHashMap <String, Object>  getListByQueryDynamicFromPackageManyResultWithColumnUnderlined(
			List<String> lstName, String sql, List<Object> params, Integer maxResult, Class<?>... clazz) 
					throws DataAccessException {
		try {
			List<Object> listTmp;
			LinkedHashMap<String,Object> hashMapResults = new LinkedHashMap<String,Object>();
			
			Session session = selectSessionFactory.getCurrentSession();
			/*Connection connection = ((SessionImpl)session).connection();*/
			Connection connection = null;
		    CallableStatement callable = connection.prepareCall(sql);
		    for (int i = 0; i < params.size(); i += 3) {
		    	Integer tmpName = (Integer) params.get(i);
		    	Object tmpValue = params.get(i + 1);
		    	Integer tmpTypes = (Integer) params.get(i + 2);
		    	callable.setObject(tmpName,tmpValue, tmpTypes);
			}
		    for (int k = 0; k < lstName.size(); ++k) {
		    	// TODO: Kiem tra lai cho nay
		    	/*callable.registerOutParameter(k +1, OracleTypes.CURSOR);*/
		    	throw new UnsupportedOperationException();
		    }
		    callable.execute();
		    ResultSet rs = null;
		    for (int k = 0; k < lstName.size(); ++k) {
				rs = (ResultSet) callable.getObject(k + 1);
			    ResultSetMetaData metaData = rs.getMetaData();
			    int columns = metaData.getColumnCount();
			    listTmp = new ArrayList<Object>();
			    while (rs.next()) {
			        LinkedHashMap<String, Object> listItem = new LinkedHashMap<String, Object>();
			        for (int i = 1; i <= columns; i++) {
			            Object value = rs.getObject(i);
			            String key = metaData.getColumnName(i);
			            listItem.put(key, value);
			        }
			        listTmp.add(listItem);
			    }
			    hashMapResults.put(lstName.get(k),convert2ClassVO(clazz[k], listTmp));
				if (k == lstName.size() - 1) break;
		    }
			return hashMapResults;
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}


}
