package com.tcs.timeseries.service;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeseriesServiceController {
	
	private static Logger log = Logger.getLogger(TimeseriesServiceController.class);
	
	@RequestMapping(value = "/png/tags", method = RequestMethod.GET)
	public String retrieveTags() {

		log.info("TimeseriesServiceController: retrieveTags ");

//		String[] oauthClient  = restConfig.getOauthClientId().split(":");
		
		

//		log.info("TimeseriesServiceController: retrieveTags " + oauthClient);

		return "hello";
	}

}
