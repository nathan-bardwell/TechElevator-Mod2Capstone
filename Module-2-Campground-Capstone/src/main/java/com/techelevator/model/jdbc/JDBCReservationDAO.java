package com.techelevator.model.jdbc;

import java.util.Date;

import com.techelevator.Campground;
import com.techelevator.ReservationDAO;

public class JDBCReservationDAO implements ReservationDAO{

	@Override
	public boolean createReservation(Campground campground, Date arrivalDate, Date departDate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String displayConfirmationId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
