package com.rpozzi.kafka;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class KafkaWebApplication {
	private static final Logger logger = LoggerFactory.getLogger(KafkaWebApplication.class);
	@Value(value = "${kafka.topic.quickstartevents}")
	private String quickstartEventsKafkaTopic;

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(KafkaWebApplication.class, args);
		logger.info("Application " + ctx.getId() + " started !!!");
	}
	
	/*****************************************************/
	/****** Kafka Listeners methods - Section START ******/
	/*****************************************************/

	// Kafka Listener for Quickstart Events (see Apache Kafka Get started https://kafka.apache.org/quickstart)
	@KafkaListener(groupId = "quickstart", topics = "quickstart-events")
	public void consumeQuickstartEvents(String in) {
		logger.info("Reading from '" + quickstartEventsKafkaTopic + "' Kafka topic ...");
		logger.info("Message read : " + in);
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