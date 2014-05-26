package com.endavafii.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.endavafii.dao.CategoryDao;
import com.endavafii.entities.Category;

@Component("categoryDao")
public class CategoryDaoImpl extends GenericDaoImpl<Category> implements CategoryDao  {
	    @Override
		@Transactional
	    public List<Category> getCategories() {
	        return this.findByNamedQuery(Category.FIND_CATEGORIES);
	    }
	    
	    @Override
		@Transactional
	    public List<Category> getSubCategories(long parentId) {
	    	Map<String, Object> params = new HashMap<String, Object>();
	 		params.put("parent", parentId);
	    	
	 		return this.findByNamedQuery(Category.FIND_SUBCATEGORIES, params);
	    }

		@Override
		@Transactional
		public Long getCategoryIdByName(String cateogryName) {

	    	Map<String, Object> params = new HashMap<String, Object>();
	 		params.put("name", cateogryName);
	    	
	 		List<Long> idList = this.findByNamedQuery(Category.FIND_CATEGORYID_BY_NAME, params);
	 		if(idList.size()==1) {
		 		return idList.get(0);
	 		}
	 		return (long) 0;
		}
}
