package com.techelevator.model.jdbc;

import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

import com.techelevator.Campground;
import com.techelevator.ReservationDAO;

public class JDBCReservationDAO implements ReservationDAO{

	@Override
	public boolean createReservation(Campground campground, LocalDate arrivalDate, LocalDate departDate) {
		Scanner input = new Scanner(System.in);
		System.out.println("Which site should be reserved (Enter 0 to cancel)");
		int siteSelection = input.nextInt();
		input.nextLine();
		if(siteSelection == 0) {
			return false;
		}
		
		
		return false;
		
		
	}

	@Override
	public String displayConfirmationId(int id) {
		
		return null;
	}

}
