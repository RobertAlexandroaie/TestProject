package com.endavafii.dao;

import java.util.List;
import java.util.Map;


public interface GenericDao<T> {

    T create(T t);

    void delete(Object id);

    T find(Object id);

    T update(T t);   

    void persist(List<T> t);
    
    @SuppressWarnings("rawtypes")
	public List findByNamedQuery(String queryName);

	@SuppressWarnings("rawtypes")
	public List findByNamedQuery(String queryName,
			Map<String, Object> parameters);

	@SuppressWarnings("rawtypes")
	public List findByNamedQuery(String queryName,
			Map<String, Object> parameters, Integer startPosition, Integer maxResult);
}
