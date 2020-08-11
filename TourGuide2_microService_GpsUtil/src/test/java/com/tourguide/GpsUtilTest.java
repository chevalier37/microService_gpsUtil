package com.tourguide;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tourguide.model.Attraction;
import com.tourguide.model.Location;
import com.tourguide.model.User;
import com.tourguide.model.VisitedLocation;
import com.tourguide.service.GpsUtilService;

@SpringBootTest
public class GpsUtilTest {

	@Autowired
	GpsUtilService gpsUtilService;

	@Test
	public void get5NearByAttractionsTest() {
		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
		VisitedLocation visitedLocation = new VisitedLocation(user.getUserId(), new Location(20.00, 200.00), new Date(),
				0);
		Map<Double, Attraction> attractions = gpsUtilService.get5NearByAttractions(visitedLocation, "jon");
		assertEquals(5, attractions.size());
	}

	@Test
	public void getAttractionsTest() {
		List<Attraction> attractions = gpsUtilService.getAttractions();
		assertEquals(26, attractions.size());
	}

	@Test
	public void isWithinAttractionProximityTest() {
		Attraction attraction = new Attraction("name", "city", "state", 20.00, 20.00);
		Location location = new Location(20.00, 20.00);
		assertEquals(true, gpsUtilService.isWithinAttractionProximity(attraction, location));
	}

	@Test
	public void getDistanceTest() {
		Location loc1 = new Location(20.00, 20.00);
		Location loc2 = new Location(20.00, 20.00);
		Double distance = gpsUtilService.getDistance(loc1, loc2);
		assertEquals(0.0, distance);
	}

	@Test
	public void getDistanceMileTest() {
		Attraction attraction = new Attraction("name", "city", "state", 20.00, 20.00);
		Location loc2 = new Location(20.00, 20.00);
		Double distance = gpsUtilService.getDistanceMiles(attraction, loc2);
		assertEquals(0.0, distance);
	}

}
