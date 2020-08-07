package com.tourguide.controller;

import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tourguide.model.Attraction;
import com.tourguide.model.VisitedLocation;
import com.tourguide.proxies.MicroServiceUserProxy;
import com.tourguide.service.GpsUtilService;

@RestController
@RequestMapping("/gpsutil")
public class GpsUtilController {

	@Autowired
	GpsUtilService gpsUtilService;

	@Autowired
	MicroServiceUserProxy userProxy;

	@GetMapping("/getAttractions")
	public List<Attraction> getAttractions() {
		return gpsUtilService.getAttractions();
	}

	@GetMapping("/get5NearbyAttractions")
	public TreeMap<Double, Attraction> get5NearbyAttractions(@RequestParam String userName) {
		VisitedLocation visitedLocation = userProxy.getUserLocation(userName);
		return gpsUtilService.get5NearByAttractions(visitedLocation);
	}

}
