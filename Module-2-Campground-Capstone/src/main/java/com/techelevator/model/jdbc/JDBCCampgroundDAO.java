package com.techelevator.model.jdbc;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.CampgroundDAO;

public class JDBCCampgroundDAO implements CampgroundDAO{
	private JdbcTemplate jdbcTemplate;

	public JDBCCampgroundDAO(BasicDataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

}
