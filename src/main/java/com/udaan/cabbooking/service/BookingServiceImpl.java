package com.udaan.cabbooking.service;

import com.udaan.cabbooking.model.Driver;
import com.udaan.cabbooking.model.Location;
import com.udaan.cabbooking.model.Rider;
import com.udaan.cabbooking.model.UserStatus;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/** The type Booking service. */
public class BookingServiceImpl {

	private static final float LIMIT = 5F;

	@NonNull
	private UserServiceImpl userService;

	/**
	 * Constructor
	 * @param userService
	 */
	public BookingServiceImpl(@NonNull UserServiceImpl userService) {
		this.userService = userService;
	}

	/**
   * Get ride driver.
   *
   * @param rider the rider
   * @return the driver
   */
  public Driver getRide(Rider rider) {
		List<Driver> list = new ArrayList<>();
		for (Map.Entry<String, Driver> m: userService.getDriverMap().entrySet()){
			// Get only free Drivers
			if(m.getValue().getStatus().equals(UserStatus.FREE)){
				if(getDriverDistance(m.getValue(), rider) < LIMIT)
					list.add(m.getValue());
			}
		}
		return list.get(0);
	}

	/**
	 *
	 * @param driver
	 * @param rider
	 * @return
	 */
	private float getDriverDistance(Driver driver, Rider rider) {
		Location driverLoc = driver.getLocation();
		Location riderLoc = rider.getLocation();

		float distance = (float) Math.sqrt(Math.pow(driverLoc.getX() - riderLoc.getX(), 2) + Math.pow(driverLoc.getY() - riderLoc.getY(), 2));
		return distance;
	}

}
