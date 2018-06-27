package com.techelevator.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.Park;
import com.techelevator.ParkDAO;

public class JDBCParkDAO implements ParkDAO { 
	
	private JdbcTemplate jdbcTemplate;

	public JDBCParkDAO(BasicDataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Park> getAllParks() {
		List<Park> allParks = new ArrayList<>();
		String sqlGetAllParks = "SELECT  * " +
									 "FROM park " +
									 "ORDER BY name";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllParks);
		while (results.next()) {
			Park parkResult = mapRowToPark(results);
			allParks.add(parkResult);
		}
		return allParks;
	}

	@Override
	public void displayParkInfo(int id) {
		String sqlGetParkInfo = "SELECT * " +
							    "FROM park "+
				                 "WHERE park_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetParkInfo, id);
		Park thePark = getAllParks().get(id - 1);
		
		System.out.println(thePark.getName());
		System.out.println("Location: " + thePark.getLocation());
		System.out.println("Established: " + thePark.getDateCreated());
		System.out.println("Area: " + thePark.getArea());
		System.out.println("Annual Visitors: " + thePark.getVisitorCount());
		System.out.println();
		System.out.println(thePark.getDescription());
		
	}
	
	private Park mapRowToPark(SqlRowSet results) {
		Park park = new Park();
		park.setParkId(results.getLong("park_id"));
		park.setName(results.getString("name"));
		park.setLocation(results.getString("location"));
		park.setDateCreated(results.getString("establish_date"));
		park.setArea(results.getInt("area"));
		park.setVisitorCount(results.getInt("visitors"));
		park.setDescription(results.getString("description"));
		return park;
	}

}
