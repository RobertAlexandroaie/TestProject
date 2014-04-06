package com.endava.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Null;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@NamedQueries({
		@NamedQuery(name = Category.FIND_CATEGORIES, query = "select c from Category c where c.parentCategory=null order by c.name"),
		@NamedQuery(name = Category.FIND_SUBCATEGORIES, query = "select s from Category s where s.parentCategory.id=:parent order by s.name"),
		@NamedQuery(name = Category.FIND_CATEGORYID_BY_NAME, query = "select s.id from Category s where s.name = :name") })
@Entity
@Table(name = "categories")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_CATEGORIES = "Category.findCategories";
	public static final String FIND_SUBCATEGORIES = "Category.findSubCategories";
	public static final String FIND_CATEGORYID_BY_NAME = "Category.FIND_CATEGORYID_BY_NAME";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne
	@JsonBackReference
	private Category parentCategory;

	private String name;

	@OneToMany(mappedBy = "parentCategory")
	@JsonManagedReference
	private List<Category> subcategories;

	private String commission;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Category getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Category> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(List<Category> subcategories) {
		this.subcategories = subcategories;
	}

	public String getCommission() {
		return commission;
	}

	public void setCommission(String commission) {
		this.commission = commission;
	}

}
