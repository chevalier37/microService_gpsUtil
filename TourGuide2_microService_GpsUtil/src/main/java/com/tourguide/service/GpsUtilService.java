package com.tourguide.service;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourguide.model.Attraction;
import com.tourguide.model.Location;
import com.tourguide.model.VisitedLocation;
import com.tourguide.proxies.MicroServiceRewardProxy;

import gpsUtil.GpsUtil;

@Service
public class GpsUtilService {

	private final GpsUtil gpsUtil;
	private int attractionProximityRange = 200;
	private static final double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;

	public GpsUtilService(GpsUtil gpsUtil) {
		this.gpsUtil = gpsUtil;
	}

	@Autowired
	MicroServiceRewardProxy rewardProxy;

	public List<Attraction> getAttractions() {
		List<gpsUtil.location.Attraction> gpsAttraction = gpsUtil.getAttractions();

		return gpsAttraction.stream().map(attraction -> new Attraction(attraction.attractionName, attraction.city,
				attraction.state, attraction.latitude, attraction.longitude)).collect(toList());

	}

	public boolean isWithinAttractionProximity(Attraction attraction, Location location) {
		return getDistance(attraction, location) > attractionProximityRange ? false : true;
	}

	public double getDistance(Location loc1, Location loc2) {
		double lat1 = Math.toRadians(loc1.latitude);
		double lon1 = Math.toRadians(loc1.longitude);
		double lat2 = Math.toRadians(loc2.latitude);
		double lon2 = Math.toRadians(loc2.longitude);

		double angle = Math
				.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));

		double nauticalMiles = 60 * Math.toDegrees(angle);
		double statuteMiles = STATUTE_MILES_PER_NAUTICAL_MILE * nauticalMiles;
		return statuteMiles;
	}

	public double getDistanceMiles(Attraction attraction, Location location) {
		return getDistance(attraction, location);
	}

	public TreeMap<Double, Attraction> get5NearByAttractions(VisitedLocation visitedLocation, String userName) {
		TreeMap<Double, Attraction> nearbyAttractions = new TreeMap<>();
		TreeMap<Double, Attraction> orderedNearbyAttractions = new TreeMap<>();
		for (Attraction attraction : getAttractions()) {
			int point = rewardProxy.getRewardPoints(userName, attraction);
			attraction.point = point;
			double distance = getDistanceMiles(attraction, visitedLocation.location);
			nearbyAttractions.put((double) Math.round(distance / 1000), attraction);
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
