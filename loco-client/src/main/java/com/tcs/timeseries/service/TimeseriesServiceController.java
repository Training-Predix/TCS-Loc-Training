package com.tcs.timeseries.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeseriesServiceController {
	
	private static Logger log = Logger.getLogger(TimeseriesServiceController.class);
	
	@Autowired
	public TimeseriesServiceImpl tsimpl;
	
	@RequestMapping(value = "/locomotive/tags", method = RequestMethod.GET)
	public String retrieveTags() {

		log.info("TimeseriesServiceController: retrieveTags ");

		String str = tsimpl.timeseries("tags");

		return str;
	}
	

	@RequestMapping(value = "/locomotive/datapoints", method = RequestMethod.GET)
	public String retrieveDatapoints() {

		log.info("TimeseriesServiceController: retrievedatapoints ");

		String str1 = tsimpl.timeseries("data");

		return str1;
	}
	
	
	@RequestMapping(value = "/locomotive/latest", method = RequestMethod.GET)
	public String retrieveLatest() {

		log.info("TimeseriesServiceController: retrieveLatest ");

		String str1 = tsimpl.timeseries("latest");

		return str1;
	}

}
