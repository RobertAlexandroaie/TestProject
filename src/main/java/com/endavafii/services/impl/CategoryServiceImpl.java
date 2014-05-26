package com.endavafii.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.endavafii.dao.CategoryDao;
import com.endavafii.dto.CategoryDto;
import com.endavafii.entities.Category;
import com.endavafii.services.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService{
	 
	@Autowired
    private CategoryDao categoryRepository;

    private static CategoryDto categoryToCategoryDto(Category cat) {

    	CategoryDto catDto = new CategoryDto();

    	catDto.setId(cat.getId());
        catDto.setName(cat.getName());
        catDto.setParentCategory(cat.getParentCategory());
        catDto.setSubcategories(cat.getSubcategories());
        catDto.setCommission(cat.getCommission());

        return catDto;
    }


    public List<CategoryDto> getCategories() {

        List<Category> categories = categoryRepository.getCategories();
        List<CategoryDto> categoriesDtoList = new ArrayList<CategoryDto>();

        for (int i = 0; i < categories.size(); i++) {
        	categoriesDtoList.add(categoryToCategoryDto(categories.get(i)));
        }

        return categoriesDtoList;
    }
    
    public List<CategoryDto> getSubCategories(long parentId) {

        List<Category> subcategories = categoryRepository.getSubCategories(parentId);
        List<CategoryDto> subcategoriesDtoList = new ArrayList<CategoryDto>();

        for (int i = 0; i < subcategories.size(); i++) {
        	subcategoriesDtoList.add(categoryToCategoryDto(subcategories.get(i)));
        }

        return subcategoriesDtoList;
    }


	@Override
	public Long getCategoryIdByName(String name) {
		return categoryRepository.getCategoryIdByName(name);
	}

   
}
