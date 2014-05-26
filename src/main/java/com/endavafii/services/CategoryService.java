package com.endavafii.services;

import java.util.List;

import com.endavafii.dto.CategoryDto;
import com.endavafii.entities.Category;

public interface CategoryService {
	List<CategoryDto> getCategories();
	
	List<CategoryDto> getSubCategories(long parentId);
	
	Long getCategoryIdByName(String name);
}
