package com.tcs.timeseries.service;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Component
public class TimeseriesServiceImpl {
	
	private static Logger log = Logger.getLogger(TimeseriesServiceImpl.class);
	
	public void timeseries() {
/*		ClientCredentialsResourceDetails clientDetails = new ClientCredentialsResourceDetails();
		
		clientDetails.setClientId("client");
		clientDetails.setClientSecret("client");
		
		String accessUrl = "https://13a56c35-3065-41e3-9e8f-51e5b7b43234.predix-uaa.run.aws-usw02-pr.ice.predix.io/oauth/token";
		
        clientDetails.setAccessTokenUri(accessUrl);
        clientDetails.setGrantType("client_credentials");
       
        OAuth2RestTemplate oauth2RestTemplate = new OAuth2RestTemplate(clientDetails);
        
        log.info("TimeseriesServiceImpl :: Access Token - " +  oauth2RestTemplate.getAccessToken().getValue());*/
        
        String authorization = "Bearer " +  oauth2RestTemplate.getAccessToken().getValue();
        
        
        
        String tags = retrieveTags(authorization);
        log.info("TimeseriesServiceImpl :: retrieveTags : response - " + tags);
        
        String dataPoints = retrieveDataPoints(authorization);
        log.info("TimeseriesServiceImpl :: retrieveDataPoints : response - " + dataPoints);
        
        try {
			JSONObject dataPointsObject = new JSONObject(dataPoints);
			
			JSONArray tagsArray = dataPointsObject.getJSONArray("tags");
			System.out.println("tagsArray: " + tagsArray);
			
			for (int i=0; i < tagsArray.length(); i++) {
				JSONObject jsonTagsObject = tagsArray.getJSONObject(i);
				
				System.out.println("jsonTagsObject: " + jsonTagsObject.getJSONArray("results"));
				
				JSONArray resultsArray = jsonTagsObject.getJSONArray("results");
				for (int j=0; j < resultsArray.length(); j++) {
					JSONObject jsonValueObject = resultsArray.getJSONObject(j);
					
					System.out.println("jsonValueObject: " + jsonValueObject.getJSONArray("values"));
					
					for (int k = 0; k < jsonValueObject.getJSONArray("values").length(); k++) {
						JSONArray jsonArray = jsonValueObject.getJSONArray("values").getJSONArray(k);
						
						System.out.println("value - 1 : " + jsonArray.get(0) + "value - 2: " + jsonArray.get(1) + "value - 3: " + jsonArray.get(2));
					}
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
	}
	
	private static String retrieveDataPoints(String authorization) {
		
		String timeSeriesUri = "https://time-series-store-predix.run.aws-usw02-pr.ice.predix.io/v1/datapoints";
		
		RestTemplate restTemplate = new RestTemplate();
		
        HttpHeaders headers = new HttpHeaders();
        headers.add("Predix-Zone-Id","d486a10a-44e2-43a2-939e-d2852a02c9ef");
        headers.add("Authorization", authorization);
        headers.add("Content-Type", "application/json");
        
//	    JSONObject jsonString = buildJson();
        String body ="{"+

         		" 'start': '6d-ago'" + "," +

    			" 'tags': [ "+

        		" {"+

            		" 'name': '9d8c62f3_cutter',"+

            		" 'limit': 3"+

            	" }]}";
		MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<String, String>();
		postParameters.add("content", body);
		
		try {
			
			//HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(postParameters, headers);
			HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
			HttpEntity<String> response = restTemplate.exchange(timeSeriesUri, HttpMethod.POST, requestEntity, String.class);
			
			log.info("TimeseriesServiceImpl :: retrieveDataPoints : response - " +  response.getBody());
			
			return response.getBody();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private static String retrieveTags(String authorization) {
		String timeSeriesUri = "https://time-series-store-predix.run.aws-usw02-pr.ice.predix.io/v1/tags";
        
        
        RestTemplate restTemplate = new RestTemplate();
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Predix-Zone-Id","d486a10a-44e2-43a2-939e-d2852a02c9ef");
        headers.add("Authorization", authorization);
        headers.add("Content-Type", "application/json");
        log.info("TimeseriesServiceImpl :: retrieveTags : headers - " +  headers);
	    try{

	    	HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

	    	HttpEntity<String> response = restTemplate.exchange(timeSeriesUri, HttpMethod.GET, entity, String.class);
//	    	log.info("TimeseriesServiceImpl :: retrieveTags : response - " +  response.getBody());
	    	
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
