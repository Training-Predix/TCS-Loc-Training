package com.tcs.timeseries;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.tcs.timeseries.*,"})
public class TimeseriesclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimeseriesclientApplication.class, args);
		
		
	}
}
