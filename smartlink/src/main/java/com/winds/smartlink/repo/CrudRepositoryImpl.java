package com.winds.smartlink.repo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.winds.smartlink.exceptions.DataAccessException;



/**
 * @author Windreams
 *
 */
public class CrudRepositoryImpl extends AbstractRepository implements CrudRepository {
	
	/** The session factory. */
	@Autowired
	@Qualifier(value="sessionFactory")
	private SessionFactory insertSessionFactory;
	
	/**
	 * Default constructor.
	 * @param insertSessionFactory
	 */
	public CrudRepositoryImpl(SessionFactory insertSessionFactory) {
		this.insertSessionFactory = insertSessionFactory;
	}
	
	@Override
	public <T> T create(T object) throws DataAccessException {
		try {
			Session session = insertSessionFactory.getCurrentSession();
			session.persist(object);
			return object;
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}
	
	@Override
	public <T> List<T> create(List<T> lstObject) throws DataAccessException  {
		try {
			Session session = insertSessionFactory.getCurrentSession();
			int i = 0;
			for (T obj: lstObject) {
				i++;
				session.save(obj);
	            if (i%50 == 0)
	            {
	                session.flush();
	                session.clear();
	            }
				if (obj != null)
					session.persist(obj);
			}
			return lstObject;
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}
	
	@Override
	public <T> List<T> update(List<T> lstObject) throws DataAccessException  {
		try {
			Session session = insertSessionFactory.getCurrentSession();
			int i = 0;
			for (T obj: lstObject) {
				i++;
				session.update(obj);
	            if (i%50 == 0)
	            {
	                session.flush();
	                session.clear();
	            }
			}
			return lstObject;
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	@Override
	public void update(Object object) throws DataAccessException {
		try {
			Session session = insertSessionFactory.getCurrentSession();
			session.merge(object);
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	@Override
	public void delete(Object object) throws DataAccessException {
		try {
			Session session = insertSessionFactory.getCurrentSession();
			session.delete(object);
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}
}
