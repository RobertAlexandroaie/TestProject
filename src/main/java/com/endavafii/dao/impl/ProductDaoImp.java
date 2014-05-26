package com.endavafii.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.endavafii.dao.ProductDao;
import com.endavafii.entities.Product;

@Component("productDao")
public class ProductDaoImp extends GenericDaoImpl<Product> implements ProductDao{

}
