package com.techelevator;

import java.time.LocalDate;
import java.util.Date;

public interface ReservationDAO {
	
	public void createReservation(Campground campground, LocalDate arrivalDate, LocalDate departDate); 
	
	public String displayConfirmationId(int id);

}
