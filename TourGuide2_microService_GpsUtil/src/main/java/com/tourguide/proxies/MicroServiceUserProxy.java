package com.tourguide.proxies;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.tourguide.model.User;

import gpsUtil.location.VisitedLocation;

@FeignClient(name = "microservice-user")
@RibbonClient(name = "microservice-user")
public interface MicroServiceUserProxy {

	@GetMapping("/getUser/{userName}")
	User getUser(@PathVariable("userName") String userName);

	@GetMapping("/getUserLocation")
	VisitedLocation getUserLocation(@RequestParam String userName);

}
