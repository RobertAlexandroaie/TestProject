package com.endava.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.endava.dao.GenericDao;

public abstract class GenericDaoImpl<T> implements GenericDao<T> {

    @PersistenceContext
    protected EntityManager em;

    private Class<T> type;

    public static final Log LOG = LogFactory.getLog(GenericDaoImpl.class);
    
    public GenericDaoImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    @Override
    public T create(final T t) {
        this.em.persist(t);
        return t;
    }

    @Override
    public void delete(final Object id) {
        this.em.remove(this.em.getReference(type, id));
    }

    @Override
    public T find(final Object id) {
        return (T) this.em.find(type, id);
    }

    @Override
    public T update(final T t) {
        return this.em.merge(t);    
    }
    
    @Transactional(propagation=Propagation.REQUIRES_NEW)
	public void persist(List<T> t){
		for(T item:t){
			em.persist(item);
		}
	}
    
    @SuppressWarnings("rawtypes")
	public List findByNamedQuery(String queryName) {
		return em.createNamedQuery(queryName).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public T findUniqueByNamedQuery(String queryName, Map<String, Object> parameters, Class<T> type) {
		
		Query query = em.createNamedQuery(queryName);

		if (parameters != null) {
			
			Set<Entry<String, Object>> rawParameters = parameters.entrySet();
			for (Entry<String, Object> entry : rawParameters) {
				if (entry.getValue() == null) {
					LOG.warn(String.format(
							"Invalid value. Parameter %s is NULL.",
							entry.getKey()));
					return null;
				}
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		
		try {
			return (T) query.getSingleResult();
		} catch (NoResultException e) {
			LOG.error(e.getMessage());
			return null;
		} catch (NonUniqueResultException e) {
			LOG.error(e.getMessage());
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public List findByNamedQuery(String queryName,
			Map<String, Object> parameters) {
		return findByNamedQueryHelper(queryName, parameters, null, null);
	}

	@SuppressWarnings("rawtypes")
	public List findByNamedQuery(String queryName,
			Map<String, Object> parameters, Integer startPosition, Integer maxResult) {
		
		return findByNamedQueryHelper(queryName, parameters, startPosition, maxResult);
	}
	
	@SuppressWarnings("rawtypes")
	private List findByNamedQueryHelper(String queryName,
			Map<String, Object> parameters, Integer startPosition, Integer maxResult) {
		
		Set<Entry<String, Object>> rawParameters = parameters.entrySet();
		Query query = em.createNamedQuery(queryName);
		
		if(startPosition != null && startPosition > 0) {
			query.setFirstResult(startPosition);
		}
		
		if(maxResult != null && maxResult > 0) {
			query.setMaxResults(maxResult);
		}
		
		for(Entry<String, Object> entry : rawParameters) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		
		return query.getResultList();		
	}
}