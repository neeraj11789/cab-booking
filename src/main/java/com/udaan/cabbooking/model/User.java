package com.udaan.cabbooking.model;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/** The type User. */
@Setter @Getter
public abstract class User {
  /** The Name. */
  @NonNull
  String name;

  /** The Location. */
  Location location;

  /** The Status. */
  UserStatus status = UserStatus.FREE;

	public User(@NonNull String name) {
		this.name = name;
	}
}
