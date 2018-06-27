package com.techelevator;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.model.jdbc.JDBCCampgroundDAO;
import com.techelevator.view.Menu;

public class CampgroundCLI {
	
	private static final String VIEW_PARKS_OPTION_ACADIA = "Acadia";
	private static final String VIEW_PARKS_OPTION_ARCHES = "Arches";
	private static final String VIEW_PARKS_OPTION_CUYAHOGA = "Cuyahoga Valley";
	private static final String VIEW_PARKS_OPTION_QUIT = "Quit";
	private static final String[] VIEW_PARKS_OPTIONS = { VIEW_PARKS_OPTION_ACADIA, 
														VIEW_PARKS_OPTION_ARCHES,
														VIEW_PARKS_OPTION_CUYAHOGA,
														VIEW_PARKS_OPTION_QUIT };
	
	private static final String PARK_INFO_OPTION_VIEW_CAMPGROUNDS = "View Campgrounds";
	private static final String PARK_INFO_OPTION_SEARCH_FOR_RESERVATION = "Search for Reservation";
	private static final String RETURN_TO_PREVIOUS_SCREEN = "Return to Previous Screen";
	private static final String[] PARK_INFO_OPTIONS = { PARK_INFO_OPTION_VIEW_CAMPGROUNDS,
														PARK_INFO_OPTION_SEARCH_FOR_RESERVATION,
														RETURN_TO_PREVIOUS_SCREEN };
	
	private static final String PARK_CAMPGROUNDS_OPTION_SEARCH_FOR_AVAILABLE_RESERVATION = "Search for Available Reservation";
	private static final String[] PARK_CAMPGROUNDS_OPTIONS = { PARK_CAMPGROUNDS_OPTION_SEARCH_FOR_AVAILABLE_RESERVATION,
																RETURN_TO_PREVIOUS_SCREEN };
														
	
	private static BasicDataSource dataSource = new BasicDataSource();
	private static JDBCCampgroundDAO campgroundDao;
	private Menu menu;

	public static void main(String[] args) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		
		CampgroundCLI application = new CampgroundCLI(dataSource);
		application.run();
	}

	public CampgroundCLI(DataSource datasource) {
		campgroundDao = new JDBCCampgroundDAO(dataSource);
		this.menu = new Menu(System.in, System.out);
	}
	
	public void run() {
		
	}
}
