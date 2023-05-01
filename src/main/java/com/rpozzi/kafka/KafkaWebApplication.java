package com.rpozzi.kafka;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;

import com.rpozzi.kafka.dto.TemperatureSensor;
import com.rpozzi.kafka.service.QuickstartEventService;
import com.rpozzi.kafka.service.TemperatureSensorService;

@SpringBootApplication
public class KafkaWebApplication {
	private static final Logger logger = LoggerFactory.getLogger(KafkaWebApplication.class);
	@Autowired
	private TemperatureSensorService temperatureSensorSrv;
	@Autowired
	private QuickstartEventService quickstartEventSrv;

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(KafkaWebApplication.class, args);
		logger.info("Application " + ctx.getId() + " started !!!");
	}
	
	/*****************************************************/
	/****** Kafka Listeners methods - Section START ******/
	/*****************************************************/

	// Kafka Listener for temperature and humidity sensor
	@KafkaListener(groupId = "robi-temperatures", topics = "temperatures")
	public void consumeTemperature(String in) {
		TemperatureSensor temperatureSensor = temperatureSensorSrv.consume(in);
		if (temperatureSensor.getTemperature() > 30) {
			logger.info("Temperature higher than 30°, publishing alert");
			quickstartEventSrv.publish("High Temperature !!!");
		}
	}
	
	/*****************************************************/
	/****** Kafka Listeners methods - Section END ******/
	/*****************************************************/
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			logger.debug("Let's inspect the beans provided by Spring Boot:");
			logger.debug("************** Spring Boot beans - START **************");
			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				logger.debug(beanName);
			}
			logger.debug("************** Spring Boot beans - END **************");
		};
	}

}