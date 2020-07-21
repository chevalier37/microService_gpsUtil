package com.tourguide.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gpsUtil.location.Attraction;
import gpsUtil.location.Location;

@FeignClient(name = "microservice-reward", url = "localhost:9003")
public interface MicroServiceRewardProxy {

	@GetMapping("/isWithinAttractionProximity")
	boolean isWithinAttractionProximity(@RequestParam Attraction attraction, @RequestParam Location location);

	@GetMapping("/getDistanceMiles")
	double getDistanceMiles(@RequestParam Attraction attraction, @RequestParam Location location);

}
