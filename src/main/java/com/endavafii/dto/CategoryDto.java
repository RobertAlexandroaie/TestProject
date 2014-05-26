package com.endavafii.dto;

import java.util.List;

import com.endavafii.entities.Category;

public class CategoryDto {
	private long id;	
	private String name;
	private Category parentCategory;
	private List<Category> subcategories;
	private String commission;
	
	
	public String getCommission() {
		return commission;
	}
	public void setCommission(String commission) {
		this.commission = commission;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Category getParentCategory() {
		return parentCategory;
	}
	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}
	public List<Category> getSubcategories() {
		return subcategories;
	}
	public void setSubcategories(List<Category> subcategories) {
		this.subcategories = subcategories;
	}
	
	

}
