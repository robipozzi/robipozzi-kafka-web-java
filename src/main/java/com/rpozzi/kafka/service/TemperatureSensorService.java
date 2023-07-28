package com.rpozzi.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rpozzi.kafka.dto.TemperatureSensor;

@Service
public class TemperatureSensorService {
	private static final Logger logger = LoggerFactory.getLogger(TemperatureSensorService.class);
	@Value(value = "${kafka.topic.temperatures}")
	private String temperaturesKafkaTopic;

	public TemperatureSensor consume(String in) {
		logger.debug("===> running consume(String in) method ...");
		logger.info("Reading from '" + temperaturesKafkaTopic + "' Kafka topic ...");
		TemperatureSensor temperatureSensor = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			logger.debug("Message read : " + in);
			temperatureSensor = mapper.readValue(in, TemperatureSensor.class);
			logger.info("Temperature = " + temperatureSensor.getTemperature() + " - Humidity = " + temperatureSensor.getHumidity());
		} catch (JsonMappingException e) {
			logger.error(e.getLocalizedMessage());
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			logger.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
		return temperatureSensor;
	}
}