package com.tcs.timeseries;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.tcs.timeseries", "com.ge.predix.solsvc.restclient.impl", "com.ge.predix.solsvc.restclient.config"})
public class TimeseriesclientApplication {
	
	private static final Logger log = LoggerFactory.getLogger(TimeseriesclientApplication.class);

	public static void main(String[] args) {
		 SpringApplication springApplication = new SpringApplication(TimeseriesclientApplication.class);
	        ApplicationContext ctx = springApplication.run(args);
	        
	        log.info("Let's inspect the beans provided by Spring Boot:");
	        String[] beanNames = ctx.getBeanDefinitionNames();
	        Arrays.sort(beanNames);
	        for (String beanName : beanNames)
	        {
	            log.info(beanName);
	        }
		
		
	}
}
