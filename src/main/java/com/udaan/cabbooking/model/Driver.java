package com.udaan.cabbooking.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/** The type Driver. */
@Setter @Getter
public class Driver extends User {
  /** The Cab. */
  Cab cab;

  public Driver(@NonNull String name) {
    super(name);
  }
}
