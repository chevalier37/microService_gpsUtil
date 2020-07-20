package com.tourguide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import gpsUtil.GpsUtil;

@SpringBootApplication
@EnableFeignClients("com.tourguide")
public class TourGuide2MicroServiceGpsUtilApplication {

	public static void main(String[] args) {
		SpringApplication.run(TourGuide2MicroServiceGpsUtilApplication.class, args);
	}

	@Bean
	public GpsUtil getGpsUtil() {
		return new GpsUtil();
	}

}
