package com.techelevator.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.management.openmbean.OpenDataException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.Campground;
import com.techelevator.CampgroundDAO;
import com.techelevator.Park;

public class JDBCCampgroundDAO implements CampgroundDAO {
	
	private JdbcTemplate jdbcTemplate;

	public JDBCCampgroundDAO(BasicDataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Campground> getParkCampgrounds(String parkName) {
		List<Campground> campgrounds = new ArrayList<>();
		String sqlGetParkCampgrounds = "SELECT campground.* " +
				                       "FROM campground " +
				                       "JOIN park " +
				                       "ON campground.park_id = park.park_id " +
				                       "WHERE park.name = ? ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetParkCampgrounds, parkName);
		while (results.next()) {
			Campground campResult = mapRowToCampground(results);
			campgrounds.add(campResult);
		}

		return campgrounds;
	}
	
	public void displayCampgroundInfo(String choice) {
			String sqlGetAllCampgroundsInPark = "SELECT * FROM campground " +
												"JOIN park on park.park_id = campground.park_id "+ 
												"WHERE park.name = ?;";
			SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllCampgroundsInPark, choice);
	
			System.out.println("\n" + choice + " National Park Campgrounds" + '\n');
			System.out.println(String.format("%-5s%-32s%-10s%-10s%s", " ", "Name", "Open", "Close", "Daily Fee"));
			int campgroundCounter = 1;
			while (results.next()) {
				String parkName = results.getString("name");
				String openDate = results.getString("open_from_mm");
				String closeDate = results.getString("open_to_mm");
				double dailyFee= results.getDouble("daily_fee");
				if(openDate.equals("01")) {
					openDate = "January";
				}else if(openDate.equals("02")) {
					openDate = "Feburary";
				}else if(openDate.equals("03")) {
					openDate = "March";
				}else if(openDate.equals("04")) {
					openDate = "April";
				}else if(openDate.equals("05")) {
					openDate = "May";
				}else if(openDate.equals("06")) {
					openDate = "June";
				}else if(openDate.equals("07")) {
					openDate = "July";
				}else if(openDate.equals("08")) {
					openDate = "August";
				}else if(openDate.equals("09")) {
					openDate = "September";
				}else if(openDate.equals("10")) {
					openDate = "October";
				}else if(openDate.equals("11")) {
					openDate = "Novemeber";
				}else if(openDate.equals("12")) {
					openDate = "December";
				}
				
				if(closeDate.equals("01")) {
					closeDate = "January";
				}else if(closeDate.equals("02")) {
					closeDate = "Feburary";
				}else if(closeDate.equals("03")) {
					closeDate = "March";
				}else if(closeDate.equals("04")) {
					closeDate = "April";
				}else if(closeDate.equals("05")) {
					closeDate = "May";
				}else if(closeDate.equals("06")) {
					closeDate = "June";
				}else if(closeDate.equals("07")) {
					closeDate = "July";
				}else if(closeDate.equals("08")) {
					closeDate = "August";
				}else if(closeDate.equals("09")) {
					closeDate = "September";
				}else if(closeDate.equals("10")) {
					closeDate = "October";
				}else if(closeDate.equals("11")) {
					closeDate = "Novemeber";
				}else if(closeDate.equals("12")) {
					closeDate = "December";
				}
				System.out.println(String.format("#%-4d%-32s%-10s%-10s$%#.2f", campgroundCounter, parkName, openDate, closeDate, dailyFee));
				campgroundCounter++;
			}
	}
			
		
		
		
	
	
	private Campground mapRowToCampground(SqlRowSet results) {
		Campground campground = new Campground();
		campground.setCampgroundId(results.getLong("campground_id"));
		campground.setName(results.getString("name"));
		campground.setOpenFrom(results.getString("open_from_mm"));
		campground.setOpenTo(results.getString("open_to_mm"));
		campground.setDailyFee(results.getDouble("daily_fee"));
		return campground;
	}

}
