package com.endava.dao;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.endava.helper.BaseTestCase;

public class UserDaoIntegrationTest extends BaseTestCase{
	
	@Autowired
	protected UserDao userDao;
	
	/*
	@Test
	public void testLoadUsersNames() {

		final List<String> names = userDao.loadUsersNames();
		Assert.assertEquals(names.size(), 1); 
	}
	*/
}
