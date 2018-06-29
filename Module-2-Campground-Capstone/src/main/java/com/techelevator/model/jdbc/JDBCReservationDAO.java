package com.techelevator.model.jdbc;

import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

import javax.sql.DataSource;

import org.mockito.cglib.core.Local;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.Campground;
import com.techelevator.ReservationDAO;

public class JDBCReservationDAO implements ReservationDAO{

	private JdbcTemplate jdbcTemplate;

	public JDBCReservationDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void createReservation(Campground campground, LocalDate arrivalDate, LocalDate departDate) {
		Scanner input = new Scanner(System.in);
		System.out.println("Which site should be reserved (Enter 0 to cancel)");
		int siteSelection = input.nextInt();
		input.nextLine();
		if(siteSelection == 0) {
			return;
		}
		
		System.out.println("What name should the reservation be made under? ");
		String name = input.nextLine();
		
		String sqlSiteNumber = "SELECT site.* FROM site"+
							  " JOIN campground ON site.campground_id = campground.campground_id "+
							  "WHERE site_number = ?  AND campground.campground_id = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSiteNumber,siteSelection ,campground.getCampgroundId());
		results.next();
		int siteID = results.getInt("site_id");
		
		String sqlInsertReservation = "INSERT INTO reservation (site_id,name,from_date,to_date,create_date) "+
									  "VALUES (?,?,?,?,current_date) RETURNING reservation_id";
		
		int reservationId = jdbcTemplate.queryForObject(sqlInsertReservation , Integer.class , siteID , name , arrivalDate , departDate);
		
		
 		System.out.println("The reservation for " + name + " has been made. Your confirmation ID is " + reservationId );
		
		
		
		
	}
	
	public  void searchReservationByDate(LocalDate arrivalDate, LocalDate departDate) {
		Scanner input = new Scanner(System.in);
		System.out.println("Which site should be reserved (Enter 0 to cancel)");
		int siteSelection = input.nextInt();
		input.nextLine();
		if(siteSelection == 0) {
			return;
		}
		
		System.out.println("What name should the reservation be made under? ");
		String name = input.nextLine();
		
		String sqlSiteNumber = "SELECT site.* FROM site"+
							  " JOIN campground ON site.campground_id = campground.campground_id "+
							  "WHERE site_number = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSiteNumber,siteSelection);
		results.next();
		int siteID = results.getInt("site_id");
		
		String sqlInsertReservation = "INSERT INTO reservation (site_id,name,from_date,to_date,create_date) "+
									  "VALUES (?,?,?,?,current_date) RETURNING reservation_id";
		
		int reservationId = jdbcTemplate.queryForObject(sqlInsertReservation , Integer.class , siteID , name , arrivalDate , departDate);
		
		
 		System.out.println("The reservation for " + name + " has been made. Your confirmation ID is " + reservationId );
	}

	@Override
	public String displayConfirmationId(int d) {
		
		return null;
	}

}
