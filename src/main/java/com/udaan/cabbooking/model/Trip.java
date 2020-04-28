package com.udaan.cabbooking.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/** The type Trip. */
@Data
@RequiredArgsConstructor
public class Trip {

	@NonNull
	private User rider;

	@NonNull
	private User driver;

	private LocalDateTime startTime = LocalDateTime.now();

	private LocalDateTime endTime;

	@NonNull
	private Location startPoint;

	private Location endPoint;

}
