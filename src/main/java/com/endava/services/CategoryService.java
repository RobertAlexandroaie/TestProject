package com.endava.services;

import java.util.List;

import com.endava.dto.CategoryDto;
import com.endava.entities.Category;

public interface CategoryService {
	List<CategoryDto> getCategories();
	
	List<CategoryDto> getSubCategories(long parentId);
	
	Long getCategoryIdByName(String name);
}
