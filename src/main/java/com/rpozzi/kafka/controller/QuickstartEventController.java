package com.rpozzi.kafka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rpozzi.kafka.service.QuickstartEventService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("quickstart/events")
public class QuickstartEventController {
	private static final Logger logger = LoggerFactory.getLogger(QuickstartEventController.class);
	@Autowired
	private QuickstartEventService quickstartEventService;

	@PutMapping
	public void publish(@RequestBody String message) {
		logger.debug("===> running publish(@RequestBody String message) method ...");
		quickstartEventService.publish(message);
	}
	
	@GetMapping
	public void publish() {
		logger.debug("===> running publish method ...");
		//quickstartEventService.publish("Test from Rest Service - GET Method");
	}
}