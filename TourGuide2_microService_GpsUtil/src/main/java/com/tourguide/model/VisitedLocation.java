package com.tourguide.model;

import java.util.Date;
import java.util.UUID;

public class VisitedLocation {

	public final UUID userId;
	public final Location location;
	public final Date timeVisited;
	public int point;

	public VisitedLocation(UUID userId, Location location, Date timeVisited, int point) {
		this.userId = userId;
		this.location = location;
		this.timeVisited = timeVisited;
		this.point = point;
	}

	@Override
	public String toString() {
		return "VisitedLocation [userId=" + userId + ", location=" + location + ", timeVisited=" + timeVisited + "]";
	}

}
