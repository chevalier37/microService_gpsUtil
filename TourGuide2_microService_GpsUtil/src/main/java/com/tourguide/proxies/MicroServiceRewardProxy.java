package com.tourguide.proxies;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tourguide.model.Attraction;

@FeignClient(name = "microservice-reward")
@RibbonClient(name = "microservice-reward")
public interface MicroServiceRewardProxy {

	@PostMapping("rewards/getRewardPoints/{userName}")
	int getRewardPoints(@PathVariable("userName") String userName, @RequestBody Attraction attraction);

}
