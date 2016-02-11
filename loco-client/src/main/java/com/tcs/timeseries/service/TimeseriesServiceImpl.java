package com.tcs.timeseries.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.http.Header;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.ge.predix.solsvc.restclient.impl.RestClient;


@Component
public class TimeseriesServiceImpl {
	
	private static Logger log = Logger.getLogger(TimeseriesServiceImpl.class);
	
	 @PostConstruct
	    private void init()
	    {
		  System.out.println("Test");
	    }
	 @PreDestroy
	 private void destroy()
	    {
		  System.out.println("Test");
	    }
	
	@Autowired
	@Qualifier("restClient")
	public  RestClient rest;
	
	
	public String timeseries(String tag) {
		
		
		boolean oauthClientIdEncode = true;
		String oauthPort = "80";
		String oauthGrantType = "client_credentials";
		String oauthResource = "/oauth/token";
		String proxyHost = null;
		String proxyPort = null;
		String oauthClientId = "client:client";
		String oauthHost = "328ea004-f3d2-464b-bbf8-8acbd5fa4575.predix-uaa-training.run.aws-usw02-pr.ice.predix.io";

		List<Header> headers = this.rest.getOauthHttpHeaders(oauthClientId, oauthClientIdEncode);
		String tokenString = this.rest.requestToken(headers, oauthResource, oauthHost, oauthPort, oauthGrantType,
				proxyHost, proxyPort);

		log.debug("TOKEN = " + tokenString);
		
		JSONObject token = new JSONObject(tokenString);

        
        String authorization = "Bearer " +token.getString("access_token");
        
		if (tag.equalsIgnoreCase("tags")) {
			String tags = retrieveTags(authorization);
			log.info("TimeseriesServiceImpl :: retrieveTags : response - " + tags);
			return tags;
			
		} else if (tag.equalsIgnoreCase("data")) {
			String dataPoints = retrieveDataPoints(authorization);
			//log.info("TimeseriesServiceImpl :: retrieveDataPoints : response - " + dataPoints);

			try {
				JSONObject dataPointsObject = new JSONObject(dataPoints);

				JSONArray tagsArray = dataPointsObject.getJSONArray("tags");
				System.out.println("tagsArray: " + tagsArray);

				for (int i = 0; i < tagsArray.length(); i++) {
					JSONObject jsonTagsObject = tagsArray.getJSONObject(i);

					System.out.println("jsonTagsObject: " + jsonTagsObject.getJSONArray("results"));

					JSONArray resultsArray = jsonTagsObject.getJSONArray("results");
					for (int j = 0; j < resultsArray.length(); j++) {
						JSONObject jsonValueObject = resultsArray.getJSONObject(j);

						System.out.println("jsonValueObject: " + jsonValueObject.getJSONArray("values"));

						for (int k = 0; k < jsonValueObject.getJSONArray("values").length(); k++) {
							JSONArray jsonArray = jsonValueObject.getJSONArray("values").getJSONArray(k);

							System.out.println("value - 1 : " + jsonArray.get(0) + "value - 2: " + jsonArray.get(1)
									+ "value - 3: " + jsonArray.get(2));
						}
					}
				}
				
				return dataPointsObject.toString();
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        
        return "No DATA FOUND";
	}
	
	private String retrieveDataPoints(String authorization) {
		
		String timeSeriesUri = "https://time-series-store-predix.run.aws-usw02-pr.ice.predix.io/v1/datapoints";
		
		RestTemplate restTemplate = new RestTemplate();
		
        HttpHeaders headers = new HttpHeaders();
        headers.add("Predix-Zone-Id","34d2ece8-5faa-40ac-ae89-3a614aa00b6e");
        headers.add("Authorization", authorization);
        headers.add("Content-Type", "application/json");
        
//	    JSONObject jsonString = buildJson();
        String body ="{"+

         		" 'start': '1d-ago'" + "," +

    			" 'tags': [ "+

        		" {"+

            		" 'name': ['LOCOMOTIVE_1_location', 'LOCOMOTIVE_1_rpm', 'LOCOMOTIVE_1_torque'],"+

            		" 'limit': 100"+

            	" }]}";
		MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<String, String>();
		postParameters.add("content", body);
		
		try {
			
			//HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(postParameters, headers);
			HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
			HttpEntity<String> response = restTemplate.exchange(timeSeriesUri, HttpMethod.POST, requestEntity, String.class);
			
			log.info("TimeseriesServiceImpl :: retrieveDataPoints : response ==============================> " +  response.getBody());
			
			return response.getBody();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private  String retrieveTags(String authorization) {
		String timeSeriesUri = "https://time-series-store-predix.run.aws-usw02-pr.ice.predix.io/v1/tags";
        
        
        RestTemplate restTemplate = new RestTemplate();
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Predix-Zone-Id","34d2ece8-5faa-40ac-ae89-3a614aa00b6e");
        headers.add("Authorization", authorization);
        headers.add("Content-Type", "application/json");
        log.info("TimeseriesServiceImpl :: retrieveTags : headers - " +  headers);
	    try{

	    	HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
	    	HttpEntity<String> response = restTemplate.exchange(timeSeriesUri, HttpMethod.GET, entity, String.class);
	    	
	    	log.info("TimeseriesServiceImpl :: retrieveTags : response ----------->>>>>>>>>>>>>>>>> " +  response.getBody());
    	
	    	return response.getBody();
	    	
	    }catch (Exception e){
	    	e.printStackTrace();
	    }
	    
	    return null;
	}
	
//	private static JSONObject buildJson() {
//		JSONObject item = new JSONObject();
//		
//		JSONArray array = new JSONArray();
//		JSONObject tags = new JSONObject();
//		try {
//			item.append("start", "4d-ago");
//			tags.put("name", "9d8c62f3_currentamp");
//			tags.put("limit", 1000);
//			//array.put(item);
//			
//			item.append("tags", tags);
//		} catch (JSONException e) {
//			log.info("Error occurred while building json object");
//		}		
//		
//		return item;
//		
//	}
	


}
