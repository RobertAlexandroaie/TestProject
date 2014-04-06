package com.endava.dao;

import java.util.List;

import com.endava.entities.Category;

public interface CategoryDao extends GenericDao<Category>{

	List<Category> getCategories();
	
	List<Category> getSubCategories(long parentId);
	
	Long getCategoryIdByName(String cateogryName);
}
