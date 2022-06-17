package visa.center.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import visa.center.dao.BaseDao;

public class BaseDaoImpl<T, ID extends Serializable> implements BaseDao<T, ID> {

	private Class<T> entityClass;

	private SessionFactory sessionFactory;

	public BaseDaoImpl(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	@Autowired
	public BaseDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@SuppressWarnings("unchecked")
	public ID save(T entity) {
		ID id = (ID) getCurrentSession().save(entity);
		return id;
	}

	@Override
	public void update(T entity) {
		getCurrentSession().update(entity);

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		String getAll = "from " + entityClass.getName();
		List<T> entities = (List<T>) getCurrentSession().createQuery(getAll);
		return entities;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T getById(ID id) {
		T entity = (T) getCurrentSession().get(entityClass.getName(), id);
		return entity;
	}

	@Override
	public void delete(ID id) {
		getCurrentSession().delete(entityClass.getName());

	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

}
