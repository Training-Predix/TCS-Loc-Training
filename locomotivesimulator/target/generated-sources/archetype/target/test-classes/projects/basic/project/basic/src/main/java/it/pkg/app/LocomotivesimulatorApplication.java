package it.pkg.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAutoConfiguration
@ComponentScan(basePackages={"it.pkg.*"})
public class LocomotivesimulatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocomotivesimulatorApplication.class, args);
	}
}
