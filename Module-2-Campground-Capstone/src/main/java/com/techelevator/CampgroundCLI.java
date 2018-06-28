package com.techelevator;

import java.awt.List;
import java.nio.channels.SelectionKey;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.model.jdbc.JDBCCampgroundDAO;
import com.techelevator.model.jdbc.JDBCParkDAO;
import com.techelevator.model.jdbc.JDBCSiteDAO;
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
	private static JDBCParkDAO parkDao;
	private static JDBCSiteDAO siteDao;
	private Menu menu;

	public static void main(String[] args) throws ParseException {
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		
		CampgroundCLI application = new CampgroundCLI(dataSource);
		application.run();
	}

	public CampgroundCLI(DataSource datasource) {
		campgroundDao = new JDBCCampgroundDAO(dataSource);
		parkDao = new JDBCParkDAO(dataSource);
		siteDao =  new JDBCSiteDAO(dataSource);
		this.menu = new Menu(System.in, System.out);
	}
	
	public void run() throws ParseException {
		Scanner input = new Scanner(System.in);
		
		boolean outerLoop = true;
		
		while(outerLoop) {
			boolean innerLoop = true;
			System.out.println("Select park to view information or make a reservation: ");
			String choice = (String)menu.getChoiceFromOptions(VIEW_PARKS_OPTIONS);
			
			if(choice.equals(VIEW_PARKS_OPTION_QUIT)) {
				outerLoop =false;
				continue;
			}
					
					while (innerLoop) {
						displayParkInfoScreen(choice);
						System.out.println('\n' + "Please make another selection: ");
						String choice2 = (String)menu.getChoiceFromOptions(PARK_INFO_OPTIONS);
					
						if (choice2.equals(PARK_INFO_OPTION_VIEW_CAMPGROUNDS)) {
							showCampgroundInformation(choice);
							System.out.println('\n' + "Please make another selection: ");
							String choice3 = (String)menu.getChoiceFromOptions(PARK_CAMPGROUNDS_OPTIONS);
								
							if (choice3.equals(PARK_CAMPGROUNDS_OPTION_SEARCH_FOR_AVAILABLE_RESERVATION)) {
								showCampgroundInformation(choice);
								java.util.List<Campground> list = campgroundDao.getParkCampgrounds(choice);
								System.out.println("\n\n\nWhich campground would you like:  (enter 0 to cancel)");
								int campgroundSelection = input.nextInt();
								input.nextLine();
								if(campgroundSelection == 0) {
									innerLoop = false;
									continue;
								}
								System.out.println("Enter in an arrival date: (YYYY-MM-DD)");
								String arrivalDateString = input.nextLine();
								System.out.println("Enter in a departure date: (YYYY-MM-DD)");
								String departDateString = input.nextLine();
								
								DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
								LocalDate arrivalDate = LocalDate.parse(arrivalDateString,formatter);
								LocalDate departDate = LocalDate.parse(departDateString,formatter);
								
								
								
								siteDao.displayOpenSites(list.get(campgroundSelection-1), arrivalDate, departDate);
								innerLoop = false;
							}
							
						} else if (choice2.equals(PARK_INFO_OPTION_SEARCH_FOR_RESERVATION)) {
							showCampgroundInformation(choice);
							
							innerLoop = false;
						} else if (choice2.equals(RETURN_TO_PREVIOUS_SCREEN)) {
							innerLoop = false;
						}
					} 

			
		
			
		}
		
	}
	
	public void displayParkInfoScreen(String choice) {
		JDBCParkDAO park = new JDBCParkDAO(dataSource);
		if(choice.equals(VIEW_PARKS_OPTION_ACADIA)) {
			park.displayParkInfo(1);
		} else if (choice.equals(VIEW_PARKS_OPTION_ARCHES)) {
			park.displayParkInfo(2);
		} else if (choice.equals(VIEW_PARKS_OPTION_CUYAHOGA)) {
			park.displayParkInfo(3);
		}
	}
	
	public void showCampgroundInformation(String choice) {
		JDBCCampgroundDAO campgroundDao = new JDBCCampgroundDAO(dataSource);
		campgroundDao.displayCampgroundInfo(choice);
	}
	
}
