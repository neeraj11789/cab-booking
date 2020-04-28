package com.udaan.cabbooking.service;

import com.udaan.cabbooking.model.*;
import com.udaan.cabbooking.util.Constants;
import lombok.Getter;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

/** The type User service. */
public class UserServiceImpl {

  /** The Rider map. */
  Map<String, Rider> riderMap = new HashMap<>();

  /** The Driver map. */
  @Getter Map<String, Driver> driverMap = new HashMap<>();

  /**
   * Register rider.
   *
   * @param rider the rider @TODO - Have Factory
   */
  public void registerRider(@NonNull Rider rider) {
		if (riderMap.containsKey(rider.getName()))
			throw new IllegalArgumentException(Constants.INVALID_USER);
		riderMap.put(rider.getName(), rider);
	}

  /**
   * Register driver.
   *
   * @param driver the driver @TODO - Have Factory
   */
  public void registerDriver(@NonNull Driver driver) {
		if (driverMap.containsKey(driver.getName()))
			throw new IllegalArgumentException(Constants.INVALID_USER);
		driverMap.put(driver.getName(), driver);
	}

  /**
   * Update status.
   *
   * @param user the user
   * @param status the status
   */
  public void updateStatus(User user, UserStatus status) {
		user.setStatus(status);
	}

  /**
   * Update location.
   *
   * @param user the user
   * @param location the location
   */
  public void updateLocation(User user, Location location) {
		user.setLocation(location);
	}
}
