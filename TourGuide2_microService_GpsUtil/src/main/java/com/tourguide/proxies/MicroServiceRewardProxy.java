package com.tourguide.proxies;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "microservice-reward")
@RibbonClient(name = "microservice-reward")
public interface MicroServiceRewardProxy {

}
