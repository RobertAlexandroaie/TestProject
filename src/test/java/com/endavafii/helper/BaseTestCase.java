package com.endavafii.helper;
import java.sql.Connection;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * An abstract test case, used for configuration.
 */
@ContextConfiguration( { "/spring-config/test-datasource-config.xml",
"/spring-config/test-context-config.xml" })
@TransactionConfiguration(defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public abstract class BaseTestCase {
	
	@Autowired
	protected DataSource dataSource;

	protected Connection connection;

	protected IDatabaseConnection databaseConnection;

	String referenceDataPath = "/spring-config/dataset.xml";

	/**
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		try {
			connection = dataSource.getConnection();
			databaseConnection = IntegrationTestHelper.getConnection(
					connection, true, "PRJ_TEST");
		} catch (final Exception ex) {
			throw new RuntimeException(ex);
		}

		final IDataSet referenceDataSet = IntegrationTestHelper.getDataSet(
				referenceDataPath, true);
		DatabaseOperation.CLEAN_INSERT.execute(databaseConnection,
				referenceDataSet);

	}

	/**
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		databaseConnection.close();
	}

	
}
