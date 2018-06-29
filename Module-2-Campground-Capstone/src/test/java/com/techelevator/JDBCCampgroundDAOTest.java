package com.techelevator;

import static org.junit.Assert.*;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.model.jdbc.JDBCCampgroundDAO;
import com.techelevator.model.jdbc.JDBCParkDAO;

public class JDBCCampgroundDAOTest  {
	/* Using this particular implementation of DataSource so that
	 * every database interaction is part of the same database
	 * session and hence the same database transaction */
	private static SingleConnectionDataSource dataSource;
	private static JDBCCampgroundDAO campgroundDAO;
	
	/* Before any tests are run, this method initializes the datasource for testing. */
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		/* The following line disables autocommit for connections 
		 * returned by this DataSource. This allows us to rollback
		 * any changes after each test */
		dataSource.setAutoCommit(false);
		campgroundDAO = new JDBCCampgroundDAO(dataSource);
	}
	
	/* After all tests have finished running, this method will close the DataSource */
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}

	/* After each test, we rollback any changes that were made to the database so that
	 * everything is clean for the next test */
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	/* This method provides access to the DataSource for subclasses so that 
	 * they can instantiate a DAO for testing */
	protected DataSource getDataSource() {
		return dataSource;
	}

	@Test
	public void testGetParkCampgrounds() {
		assertEquals("Blackwoods", campgroundDAO.getParkCampgrounds("Acadia").get(0).getName());
		assertEquals("Seawall", campgroundDAO.getParkCampgrounds("Acadia").get(1).getName());
		assertEquals("Schoodic Woods", campgroundDAO.getParkCampgrounds("Acadia").get(2).getName());
		assertEquals("Devil's Garden", campgroundDAO.getParkCampgrounds("Arches").get(0).getName());
		assertEquals("Canyon Wren Group Site", campgroundDAO.getParkCampgrounds("Arches").get(1).getName());
		assertEquals("Juniper Group Site", campgroundDAO.getParkCampgrounds("Arches").get(2).getName());
		assertEquals("The Unnamed Primitive Campsites", campgroundDAO.getParkCampgrounds("Cuyahoga Valley").get(0).getName());
	}

}
