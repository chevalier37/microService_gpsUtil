package com.tourguide.proxies;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tourguide.model.AttractionAndLocation;

@FeignClient(name = "microservice-reward")
@RibbonClient(name = "microservice-reward")
public interface MicroServiceRewardProxy {

	@GetMapping("/isWithinAttractionProximity")
	boolean isWithinAttractionProximity(@RequestBody AttractionAndLocation request);

	@GetMapping("/getDistanceMiles")
	double getDistanceMiles(@RequestBody AttractionAndLocation request);

}
