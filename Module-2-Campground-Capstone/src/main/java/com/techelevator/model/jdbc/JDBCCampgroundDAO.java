package com.techelevator.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;
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
	public List<Campground> getParkCampgrounds(int parkId) {
		List<Campground> campgrounds = new ArrayList<>();
		String sqlGetParkCampgrounds = "SELECT campground.* " +
				                       "FROM campground " +
				                       "JOIN park " +
				                       "ON campground.park_id = park.park_id " +
				                       "WHERE park.park_id = ? ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetParkCampgrounds, parkId);
		while (results.next()) {
			Campground campResult = mapRowToCampground(results);
			campgrounds.add(campResult);
		}

		return campgrounds;
	}
	
	private Campground mapRowToCampground(SqlRowSet results) {
		Campground campground = new Campground();
		campground.setCampgroundId(results.getLong("campground_id"));
		campground.setName(results.getString("name"));
		campground.setOpenFrom(results.getString("open_from"));
		campground.setOpenTo(results.getString("open_to"));
		campground.setDailyFee(results.getDouble("daily_fee"));
		return campground;
	}

}
