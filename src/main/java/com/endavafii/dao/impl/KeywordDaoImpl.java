package com.endavafii.dao.impl;

import org.springframework.stereotype.Component;

import com.endavafii.dao.KeywordDao;
import com.endavafii.entities.Keyword;


@Component("keywordDao")
public class KeywordDaoImpl extends GenericDaoImpl<Keyword> implements KeywordDao{

}
