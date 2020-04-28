package com.udaan.cabbooking.service;

import com.udaan.cabbooking.model.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/** The type Trip service impl test. */
class TripServiceImplTest {

  private static final Rider RIDER1 = new Rider("rider1");
  private static final Rider RIDER2 = new Rider("rider2");
  private static final Rider RIDER3 = new Rider("rider3");
  private static final Rider RIDER4 = new Rider("rider4");
  private static final Rider RIDER5 = new Rider("rider5");

  private static final Driver DRIVER1 = new Driver("driver1");
  private static final Driver DRIVER2 = new Driver("driver2");
  private static final Driver DRIVER3 = new Driver("driver3");

  /** The User service. */
  UserServiceImpl userService;

  /** The Trip service. */
  TripServiceImpl tripService;

  /** The Booking service. */
  BookingServiceImpl bookingService;

  /** Sets up. */
  @BeforeEach
  void setUp() {
    // Init Services
    userService = new UserServiceImpl();
    bookingService = new BookingServiceImpl(userService);
    tripService = new TripServiceImpl(bookingService);

    RIDER1.setLocation(new Location(3F, 3F));
    RIDER2.setLocation(new Location(3F, 3F));
    RIDER3.setLocation(new Location(3F, 3F));
    RIDER4.setLocation(new Location(3F, 3F));
    RIDER5.setLocation(new Location(3F, 3F));

    // save riders
    userService.registerRider(RIDER1);
    userService.registerRider(RIDER2);
    userService.registerRider(RIDER3);
    userService.registerRider(RIDER4);
    userService.registerRider(RIDER5);

    // save drivers
    userService.registerDriver(DRIVER1);
    DRIVER1.setLocation(new Location(3F, 3F));

    userService.registerDriver(DRIVER2);
    DRIVER2.setLocation(new Location(4F, 5F));

    userService.registerDriver(DRIVER3);
    DRIVER3.setLocation(new Location(5F, 4F));
  }

  /** Start trip. */
  @Test
  void should_give_two_trips_for_rider1() {
  	Trip trip = tripService.startTrip(RIDER1);

  	tripService.updateTrip(trip);
  	tripService.updateTrip(trip);
  	tripService.updateTrip(trip);
  	tripService.endTrip(trip);


    Trip trip2 = tripService.startTrip(RIDER1);
    tripService.endTrip(trip2);

  	List<Trip> trips = tripService.fetchHistory(RIDER1);
    Assertions.assertThat(trips).as("Should Return 2 for Rider1").hasSize(2);
  }

  /** Start trip. */
  @Test
  void should_return_null_for_Rider2() {
    List<Trip> trips = tripService.fetchHistory(RIDER1);
    Assertions.assertThat(trips).as("Should Return 0 for Rider2").isNull();
  }
}