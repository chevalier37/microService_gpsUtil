package com.tourguide.controller;

import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tourguide.model.Attraction;
import com.tourguide.model.VisitedLocation;
import com.tourguide.proxies.MicroServiceUserProxy;
import com.tourguide.service.GpsUtilService;

@RestController
public class GpsUtilController {

	@Autowired
	GpsUtilService gpsUtilService;

	@Autowired
	MicroServiceUserProxy userProxy;

	@GetMapping("/getAttractions")
	public List<Attraction> getAttractions() {
		return gpsUtilService.getAttractions();
	}

	// TODO: Change this method to no longer return a List of Attractions.
	// Instead: Get the closest five tourist attractions to the user - no matter how
	// far away they are.
	// Return a new JSON object that contains:
	// Name of Tourist attraction,
	// Tourist attractions lat/long,
	// The user's location lat/long,
	// The distance in miles between the user's location and each of the
	// attractions.
	// The reward points for visiting each Attraction.
	// Note: Attraction reward points can be gathered from RewardsCentral
	@GetMapping("/getNearbyAttractions")
	public List<Attraction> getNearbyAttractions(@RequestParam String userName) {
		VisitedLocation visitedLocation = userProxy.getUserLocation(userName);
		return gpsUtilService.getNearByAttractions(visitedLocation);
	}

	// created by JB
	@GetMapping("/get5NearbyAttractions")
	public TreeMap<Double, Attraction> get5NearbyAttractions(@RequestParam String userName) {
		VisitedLocation visitedLocation = userProxy.getUserLocation(userName);
		return gpsUtilService.get5NearByAttractions(visitedLocation);
	}

}
