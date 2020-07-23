package com.tourguide.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourguide.model.Attraction;
import com.tourguide.model.AttractionAndLocation;
import com.tourguide.proxies.MicroServiceRewardProxy;

import gpsUtil.GpsUtil;
import gpsUtil.location.VisitedLocation;

@Service
public class GpsUtilService {

	private final GpsUtil gpsUtil;

	public GpsUtilService(GpsUtil gpsUtil) {
		this.gpsUtil = gpsUtil;
	}

	@Autowired
	MicroServiceRewardProxy microServiceRewardProxy;

	public List<Attraction> getAttractions() {
		List<gpsUtil.location.Attraction> gpsAttraction = gpsUtil.getAttractions();

		// faire stream
		for (gpsUtil.location.Attraction attraction : gpsAttraction) {
			Attraction modelAttraction = new Attraction(attraction.attractionName, null, null, 0, 0);
		}

		return gpsUtil.getAttractions();
	}

	public List<Attraction> getNearByAttractions(VisitedLocation visitedLocation) {
		List<Attraction> nearbyAttractions = new ArrayList<>();

		for (Attraction attraction : gpsUtil.getAttractions()) {
			AttractionAndLocation attractionAndLocation = new AttractionAndLocation();
			attractionAndLocation.setAttraction(attraction);
			attractionAndLocation.setLocation(visitedLocation.location);
			if (microServiceRewardProxy.isWithinAttractionProximity(attractionAndLocation)) {
				nearbyAttractions.add(attraction);
			}
		}
		return nearbyAttractions;
	}

	// created by JB
	public TreeMap<Double, Attraction> get5NearByAttractions(VisitedLocation visitedLocation) {
		TreeMap<Double, Attraction> nearbyAttractions = new TreeMap<>();
		TreeMap<Double, Attraction> orderedNearbyAttractions = new TreeMap<>();
		for (Attraction attraction : gpsUtil.getAttractions()) {
			AttractionAndLocation attractionAndLocation = new AttractionAndLocation();
			attractionAndLocation.setAttraction(attraction);
			attractionAndLocation.setLocation(visitedLocation.location);
			double distance = microServiceRewardProxy.getDistanceMiles(attractionAndLocation);
			nearbyAttractions.put(distance, attraction);
			nearbyAttractions.keySet();
		}
		int i = 0;
		for (Map.Entry<Double, Attraction> entry : nearbyAttractions.entrySet()) {
			orderedNearbyAttractions.put(entry.getKey(), entry.getValue());
			i++;
			if (i == 5) {
				break;
			}
		}
		return orderedNearbyAttractions;
	}

}
