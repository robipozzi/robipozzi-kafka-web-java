package com.rpozzi.kafka.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rpozzi.kafka.service.QuickstartEventService;

@RestController
@RequiredArgsConstructor
@RequestMapping("quickstart/events")
public class QuickstartEventController {
	@Autowired
	private QuickstartEventService quickstartEventService;

	@PutMapping
	public void publish(@RequestBody String message) {
		quickstartEventService.publish(message);
	}
}