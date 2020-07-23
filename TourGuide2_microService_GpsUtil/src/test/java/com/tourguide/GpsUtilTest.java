package com.tourguide;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tourguide.model.User;
import com.tourguide.service.GpsUtilService;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;

@SpringBootTest
public class GpsUtilTest {

	@Autowired
	GpsUtilService gpsUtilService;

	@Test
	public void getNearbyAttractions() {
		GpsUtil gpsUtil = new GpsUtil();

		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
		VisitedLocation visitedLocation = gpsUtilService.trackUserLocation(user);

		List<Attraction> attractions = gpsUtilService.getNearByAttractions(visitedLocation);

		assertEquals(5, attractions.size());
	}

}
