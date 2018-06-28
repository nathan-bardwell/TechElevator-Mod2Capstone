package com.techelevator;

import java.time.LocalDate;
import java.util.Date;

public interface SiteDAO {
	
	public void displayOpenSites(Campground campground, LocalDate arrivalDate, LocalDate departDate);

}
