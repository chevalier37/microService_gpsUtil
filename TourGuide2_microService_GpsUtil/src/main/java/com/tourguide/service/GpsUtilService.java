package com.tourguide.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourguide.proxies.MicroServiceRewardProxy;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;

@Service
public class GpsUtilService {

	@Autowired
	GpsUtil gpsUtil;

	@Autowired
	MicroServiceRewardProxy microServiceRewardProxy;

	public List<Attraction> getAttractions() {
		return gpsUtil.getAttractions();
	}

	public List<Attraction> getNearByAttractions(VisitedLocation visitedLocation) {
		List<Attraction> nearbyAttractions = new ArrayList<>();
		for (Attraction attraction : gpsUtil.getAttractions()) {
			if (microServiceRewardProxy.isWithinAttractionProximity(attraction, visitedLocation.location)) {
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
			double distance = microServiceRewardProxy.getDistanceMiles(attraction, visitedLocation.location);
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
