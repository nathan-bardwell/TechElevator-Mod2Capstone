package com.techelevator;

import java.util.Date;

public interface ReservationDAO {
	
	public boolean createReservation(Campground campground, Date arrivalDate, Date departDate); 
	
	public String displayConfirmationId(int id);

}
