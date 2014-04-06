package com.endava.dao.impl;

import org.springframework.stereotype.Component;

import com.endava.dao.KeywordDao;
import com.endava.entities.Keyword;


@Component("keywordDao")
public class KeywordDaoImpl extends GenericDaoImpl<Keyword> implements KeywordDao{

}
