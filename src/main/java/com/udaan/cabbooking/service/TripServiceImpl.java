package com.udaan.cabbooking.service;

import com.udaan.cabbooking.model.*;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** The type Trip service. */
public class TripServiceImpl {
  @Getter private Map<String, List<Trip>> userTripMap = new HashMap<>();

  @NonNull private BookingServiceImpl bookingService;

  /**
   * Constructor
   *
   * @param bookingService
   */
  public TripServiceImpl(@NonNull BookingServiceImpl bookingService) {
    this.bookingService = bookingService;
  }

  /**
   * Start trip.
   *
   * @param rider the rider
   */
  public Trip startTrip(Rider rider) {
    Driver driver = bookingService.getRide(rider);
    Trip trip = new Trip(rider, driver, driver.getLocation());
    rider.setStatus(UserStatus.ONTRIP);
    driver.setStatus(UserStatus.ONTRIP);
    return trip;
  }

  /**
   * Update trip.
   *
   * @param trip the trip
   */
  void updateTrip(Trip trip) {
    trip.getDriver().getLocation().update();
    trip.getRider().getLocation().update();
  }

  /**
   * End trip.
   *
   * @param trip the trip
   */
  void endTrip(Trip trip) {
    trip.getRider().setStatus(UserStatus.FREE);
    trip.getDriver().setStatus(UserStatus.FREE);
    trip.setEndTime(LocalDateTime.now());
    trip.setEndPoint(trip.getDriver().getLocation());
    saveTrip(trip);
  }

  /**
   * Save trip.
   *
   * @param trip the trip
   */
  void saveTrip(Trip trip) {
    List<Trip> userTrips = userTripMap.get(trip.getRider().getName());
    if (userTrips == null) {
      List<Trip> trips = new ArrayList<>();
      trips.add(trip);
      userTripMap.put(trip.getRider().getName(), trips);
    } else {
      userTrips.add(trip);
      userTripMap.put(trip.getRider().getName(), userTrips);
    }
  }

  public List<Trip> fetchHistory(Rider user) {
    return userTripMap.get(user.getName());
  }
}
