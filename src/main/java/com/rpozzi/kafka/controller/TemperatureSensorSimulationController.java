package com.rpozzi.kafka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rpozzi.kafka.service.TemperatureSensorSimulationService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("sensor/temperature")
public class TemperatureSensorSimulationController {
	private static final Logger logger = LoggerFactory.getLogger(TemperatureSensorSimulationController.class);
	@Autowired
	private TemperatureSensorSimulationService temperatureSensorSimulationSrv;

	@GetMapping
	public void publish() {
		logger.info("Calling Sensor Simulation Service to publish temperature and humidity ...");
		temperatureSensorSimulationSrv.publish();
	}

}