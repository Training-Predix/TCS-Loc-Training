package com.ge.simulator.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.ge.simulator.*"})
public class LocomotivesimulatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocomotivesimulatorApplication.class, args);
	}
}
