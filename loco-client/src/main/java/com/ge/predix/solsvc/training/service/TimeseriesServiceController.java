package com.ge.predix.solsvc.training.service;

import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.Header;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import com.ge.predix.solsvc.restclient.impl.RestClient;

@RestController
public class TimeseriesServiceController {
	
	private static Logger log = Logger.getLogger(TimeseriesServiceController.class);
	
	@Autowired
	public TimeseriesServiceImpl tsimpl;
	
	@Autowired
	@Qualifier("restClient")
	public  RestClient rest;
		
	static String authToken ;
	
	@RequestMapping(value = "/locomotive/tags", method = RequestMethod.GET)
	public String retrieveTags() {

		log.info("TimeseriesServiceController: retrieveTags ");

		String str = tsimpl.timeseries("tags", null);

		return str;
	}
	

	@RequestMapping(value = "/locomotive/datapoints", method = RequestMethod.GET)
	public String retrieveDatapoints() {

		log.info("TimeseriesServiceController: retrievedatapoints ");

		String str1 = tsimpl.timeseries("data", null);

		return str1;
	}
	
	
	@RequestMapping(value = "/locomotive/acslatest", method = { RequestMethod.GET, RequestMethod.POST })
	public String retrieveLatest(@RequestParam("id") String id,
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password
			)
			throws RestClientException, URISyntaxException {

		log.info("TimeseriesServiceController: retrieveACSLatest Inside Locomotive/latest >>>>>>>>> ");	
	
		
		
		String str1 = tsimpl.acsretrieveLatestPoints(username, password, id );

		return str1;
	}
	
	@RequestMapping(value = "/locomotive/latest", method = { RequestMethod.GET, RequestMethod.POST })
	public String retrieveLatest(@RequestParam("id") String id,
			@RequestParam(value = "username", required = true) String username
			
			)
			throws RestClientException, URISyntaxException {

		log.info("TimeseriesServiceController: <<<<<<<retrieveONLYLatest Inside Locomotive/latest >>>>>>>>> ");	
	
		
		String str1 = tsimpl.timeseries("latest", id );

		return str1;
	}
	
	
	//------------------------------------------ACS -----------------------------------------
	
	@SuppressWarnings("nls")
    @RequestMapping(value = "/validateuser", method = { RequestMethod.GET, RequestMethod.POST })
    public @ResponseBody String validateUser() throws Exception
 {
		
			// Get token based on the client_credentials to access Asset and
			// timeseries
			log.info("getting token based on the client_credentials");
			String auth = null;
			try {
				boolean oauthClientIdEncode = true;
				String oauthPort = "80";
				String oauthGrantType = "client_credentials";
				String oauthResource = "/oauth/token";
				String proxyHost = null;
				String proxyPort = null;
				String oauthClientId = "client:client";
				String oauthHost = "328ea004-f3d2-464b-bbf8-8acbd5fa4575.predix-uaa-training.run.aws-usw02-pr.ice.predix.io";

				List<Header> headers = this.rest.getOauthHttpHeaders(oauthClientId, oauthClientIdEncode);
				String tokenString = this.rest.requestToken(headers, oauthResource, oauthHost, oauthPort,
						oauthGrantType, proxyHost, proxyPort);

				log.debug("TOKEN = " + tokenString);

				JSONObject token = new JSONObject(tokenString);

				auth = "Bearer " + token.getString("access_token");

			} catch (HttpClientErrorException hce) {
				throw new Exception(hce);
			}
			return auth;
		}

	
	
	
	

	

}
