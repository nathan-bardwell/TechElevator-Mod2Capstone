package com.techelevator.model.jdbc;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;

import javax.print.attribute.ResolutionSyntax;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.util.WeakReferenceMonitor.ReleaseListener;

import com.techelevator.Campground;
import com.techelevator.Site;
import com.techelevator.SiteDAO;

public class JDBCSiteDAO implements SiteDAO{
	
	private static final TemporalUnit DAYS= ChronoUnit.DAYS;
	private JdbcTemplate jdbcTemplate;

	public JDBCSiteDAO(BasicDataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void displayOpenSites(Campground campground, LocalDate arrivalDate, LocalDate departDate) {
		String sqlOpenSites = "SELECT site.*, camp.* " +
							 "FROM site " +
				             "JOIN campground camp " +
				             "ON camp.campground_id = site.campground_id " +
				             "WHERE camp.campground_id = ? AND site.site_id NOT IN (SELECT site_id " +
				                                                                   "FROM reservation " +
				                                                                   "WHERE ? <= to_date " +
				                                                                   "AND ? >= from_date) " +
				             "LIMIT 5";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlOpenSites, campground.getCampgroundId(), arrivalDate, departDate);
		System.out.println("Here are the top 5 available campsites for your requested dates:");
		System.out.println("Site No. Max Occupancy Accessible? Max RV Length Utility Cost");
		while (results.next()) {
		String accessibleStr;
		String utilityStr;
		int siteNumber = results.getInt("site_number");
		int maxOccupancy = results.getInt("max_occupancy");
		int rvLength = results.getInt("max_rv_length");
		boolean accessible = results.getBoolean("accessible");
		boolean utilities =  results.getBoolean("utilities");
		double cost = results.getDouble("daily_fee") * (arrivalDate.until(departDate, DAYS));
		if (accessible) {
			accessibleStr = "Yes";
		} else {
			accessibleStr = "No";
		}
		if (utilities) {
			utilityStr = "Yes";
		} else {
			utilityStr = "N/A";
		}
		System.out.println(siteNumber + " " + maxOccupancy + " " + accessibleStr + " " + rvLength + " " + utilityStr + " $" + cost );
		}
		
		
		
	}
	
	

}
