package com.udaan.cabbooking.model;

import lombok.Data;

/** The type Location. */
@Data
public class Location {
  /** The X. */
  float x;

  /** The Y. */
  float y;

	public Location(float lat, float lon) {
		x = lat;
		y = lon;
	}

	/** Update. */
  public void update() {
		x = x+1;
		y = y+1;
	}
}
