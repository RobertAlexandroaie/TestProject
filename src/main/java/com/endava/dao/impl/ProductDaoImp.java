package com.endava.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.endava.dao.ProductDao;
import com.endava.entities.Product;

@Component("productDao")
public class ProductDaoImp extends GenericDaoImpl<Product> implements ProductDao{

}
