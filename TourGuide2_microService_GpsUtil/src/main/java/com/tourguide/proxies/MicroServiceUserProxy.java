package com.tourguide.proxies;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.tourguide.model.Provider;
import com.tourguide.model.User;
import com.tourguide.model.VisitedLocation;

@FeignClient(name = "microservice-user")
@RibbonClient(name = "microservice-user")
public interface MicroServiceUserProxy {

	@GetMapping("user/getUser/{userName}")
	User getUser(@PathVariable("userName") String userName);

	@GetMapping("user/getUserLocation")
	VisitedLocation getUserLocation(@RequestParam String userName);

	@GetMapping("user/getTripDeals/{userName}")
	List<Provider> getTripDeals(@PathVariable("userName") String userName);

}
