package com.endava.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.endava.dto.CategoryDto;
import com.endava.services.CategoryService;
import com.endava.util.LoginFormInclude;

@Controller
@RequestMapping("category")
public class CategoryController {
 
	 @Autowired
	    private CategoryService categoryService;
	 
	 @RequestMapping(value = "cat", method = RequestMethod.GET)
	    public String viewList(Model model) {

	        List<CategoryDto> categories = categoryService.getCategories();
	        
	        model.addAttribute("categoriesCount", categories.size());
	        model.addAttribute("categories", categories);
	        LoginFormInclude.addLoginForm(model);
	        return "category/category";
	
	    }
	 
	 @RequestMapping(value = "subcat", method = RequestMethod.GET)
	    public String viewList2(Model model) {

		    long parentId=3;
	        List<CategoryDto> subcategories = categoryService.getSubCategories(parentId);
	        
	        model.addAttribute("categoriesCount", subcategories.size());
	        model.addAttribute("categories", subcategories);
	        LoginFormInclude.addLoginForm(model);
	        return "category/category";
	
	    }
}
