package com.tcs.predix.beta.time.series.ingestion.handler;

import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.ge.predix.solsvc.bootstrap.ams.dto.AssetMeter;
import com.ge.predix.solsvc.bootstrap.tbs.entity.InjectionBody;
import com.ge.predix.solsvc.bootstrap.tbs.entity.InjectionMetric;
import com.ge.predix.solsvc.bootstrap.tbs.entity.InjectionMetricBuilder;
import com.ge.predix.solsvc.bootstrap.tsb.client.TimeseriesWSConfig;
import com.ge.predix.solsvc.bootstrap.tsb.factories.TimeseriesFactory;
import com.google.gson.Gson;
import com.tcs.predix.beta.time.series.ingestion.HttpWrapper;
import com.tcs.predix.beta.time.series.ingestion.api.Constants;
import com.tcs.predix.beta.time.series.ingestion.type.LocomotiveGatewayType;
import com.tcs.predix.beta.time.series.ingestion.type.CargoShelfLife;
import com.tcs.predix.beta.time.series.ingestion.websocket.WebSocketClient;
import com.tcs.predix.beta.time.series.ingestion.websocket.WebSocketConfig;

/**
 * 
 * @author predix - puru
 */
@Component
public class TimeSeriesDataIngestionHandler extends BaseFactoryIT
{
    private static Logger              log = Logger.getLogger(TimeSeriesDataIngestionHandler.class);
    @Autowired
    private TimeseriesFactory timeSeriesFactory;

    @Autowired
    private AssetDataHandler  assetDataHandler;
    
    
	@Autowired
	private TimeseriesWSConfig tsInjectionWSConfig;

	@Autowired
	private WebSocketConfig wsConfig;
	
	@Autowired
	private WebSocketClient wsClient;
    /**
     *  -
     */
    @SuppressWarnings("nls")
    @PostConstruct
    public void intilizeDataIngestionHandler()
    {
        log.info("*******************TimeSeriesDataIngestionHandler Initialization complete*********************");
    }

    @Override
    @SuppressWarnings("nls")
    public void handleData(String tenentId, String controllerId, String data, String authorization) 
    {
        log.info(data);// Store Token Manually
        if (StringUtils.isEmpty(authorization)) {
        	log.info("Refreshing token locally =====================>>> > token should be coming from machine --> post MVP !!! :( !!!");
        	String[] oauthClient  = restConfig.getOauthClientId().split(":");
        	authorization = "Bearer "+getRestTemplate(oauthClient[0],oauthClient[1]).getAccessToken().getValue();
        	//authorization = "Bearer "+"eyJhbGciOiJSUzI1NiJ9.eyJqdGkiOiJiOTljNGFmMS05MDkxLTRjNDItYTJjNS00NWIxOTg5YmRmYmQiLCJzdWIiOiJiZXRhIiwic2NvcGUiOlsiY2xpZW50cy5yZWFkIiwidGltZXNlcmllcy56b25lcy4wNzU4NDFjYi1kN2ZhLTQ4OTAtODRlYS1mZGQ3ZDdjNjViNjUuaW5nZXN0IiwiY2xpZW50cy5zZWNyZXQiLCJ1YWEucmVzb3VyY2UiLCJjbGllbnRzLmFkbWluIiwidGltZXNlcmllcy56b25lcy4wNzU4NDFjYi1kN2ZhLTQ4OTAtODRlYS1mZGQ3ZDdjNjViNjUudXNlciIsInRpbWVzZXJpZXMuem9uZXMuOTU5OWIyNTEtODYwNy00Y2Y2LTk5ZWYtZTI1Y2JlNTEyY2JjLnVzZXIiLCJzY2ltLnJlYWQiLCJwcmVkaXgtYXNzZXQuem9uZXMuN2MzOWY0YjEtMTQyYy00NWJlLWFjZjAtN2Q4ZGNjMTNmZGZkLnVzZXIiLCJ1YWEudXNlciIsInRpbWVzZXJpZXMuem9uZXMuOTU5OWIyNTEtODYwNy00Y2Y2LTk5ZWYtZTI1Y2JlNTEyY2JjLnF1ZXJ5IiwidGltZXNlcmllcy56b25lcy4wNzU4NDFjYi1kN2ZhLTQ4OTAtODRlYS1mZGQ3ZDdjNjViNjUucXVlcnkiLCJjbGllbnRzLndyaXRlIiwidGltZXNlcmllcy56b25lcy45NTk5YjI1MS04NjA3LTRjZjYtOTllZi1lMjVjYmU1MTJjYmMuaW5nZXN0Iiwiem9uZXMuYWU4YWM0ZmItZjhiZC00OThmLThkZDAtNTI5NDYzMjVkZGZjLmFkbWluIiwiaWRwcy5yZWFkIiwic2NpbS53cml0ZSJdLCJjbGllbnRfaWQiOiJiZXRhIiwiY2lkIjoiYmV0YSIsImF6cCI6ImJldGEiLCJncmFudF90eXBlIjoiY2xpZW50X2NyZWRlbnRpYWxzIiwicmV2X3NpZyI6ImQ1Y2M1YjUxIiwiaWF0IjoxNDU0MTAwMzkzLCJleHAiOjE0NTQxNDM1OTMsImlzcyI6Imh0dHBzOi8vYWVmMDBkYzktYzE4Ny00YmEyLWFiODAtYTg4M2M5NGRlYzMzLnByZWRpeC11YWEucnVuLmF3cy11c3cwMi1wci5pY2UucHJlZGl4LmlvL29hdXRoL3Rva2VuIiwiemlkIjoiYWVmMDBkYzktYzE4Ny00YmEyLWFiODAtYTg4M2M5NGRlYzMzIiwiYXVkIjpbImJldGEiLCJjbGllbnRzIiwidGltZXNlcmllcy56b25lcy4wNzU4NDFjYi1kN2ZhLTQ4OTAtODRlYS1mZGQ3ZDdjNjViNjUiLCJ1YWEiLCJ0aW1lc2VyaWVzLnpvbmVzLjk1OTliMjUxLTg2MDctNGNmNi05OWVmLWUyNWNiZTUxMmNiYyIsInNjaW0iLCJwcmVkaXgtYXNzZXQuem9uZXMuN2MzOWY0YjEtMTQyYy00NWJlLWFjZjAtN2Q4ZGNjMTNmZGZkIiwiem9uZXMuYWU4YWM0ZmItZjhiZC00OThmLThkZDAtNTI5NDYzMjVkZGZjIiwiaWRwcyJdfQ.pXfpXkKCNUvj78-XLgzLdqGJg0u3s-NRsS2gZaMp5Bd-u_MzZA4lrq-IvHbYtbtYJOtBkrdTQZrkY8y7ozAtGBGmWbPvjPS6z-70fDl7aBv8X2mMPQuxAI_Q3PH09FtxzFZmtc4TDhX5ObuO5ddP8_KX-e5vy63YQq-PVgVY8psPPwYKsUZDD0k9s43AWGfEGm1g1yYD-Z7OS56LhjPC5k6CsZP2zs6ZbiFU3p5OiEOp-Ht_YLn2nO8-98H4Rdx4dKfuBOJ9nAQraxVSbZb6HsTSkaHw25Zp71oFWQ5zzd4Ck5JV5K7pxzFOoxG7wM8CZTpbPkuJGU-88xS-Piq0Bg";
        	log.info(oauthClient[0]);
        	log.info(oauthClient[1]);
        	log.info(authorization);
        	log.info("End of data set =====================>>> >");
        }
        try
        {
        	    	
        	
        	LocomotiveGatewayType json = new Gson().fromJson(data, LocomotiveGatewayType.class);

        	log.info("TimeSeries URL ::::::::::>> " + this.tsInjectionWSConfig.getInjectionUri() );
        	log.info("ZONE HEADERNAME>>>>>>>>>>>>>>> : " + this.tsInjectionWSConfig.getPredixZoneIdHeaderName() );
        	log.info("ZONE ID>>>>>>>>>>>>>>> : " + this.tsInjectionWSConfig.getZoneId() );
        	
        	
  
        	
		    // ---> pump Temprature Data
		    this.timeSeriesFactory.create(torqueBuilder(json));     
		     // ----> pump Humidity Data
		    this.timeSeriesFactory.create(rpmBuilder(json));
		     //--> pump long data
		    this.timeSeriesFactory.create(longBuilder(json));
		     // --> pump lat data
		    this.timeSeriesFactory.create(latBuilder(json));
		    

            
            
          
        }
       finally{
    	   
       }
    }
    
    
    public static InjectionMetricBuilder createInjectionBody(TimeSeriesData timeseriesData)
    {
        InjectionMetricBuilder builder = InjectionMetricBuilder.getInstance();
        InjectionMetric metric = new InjectionMetric(Long.valueOf(timeseriesData.getMessageId()));
        InjectionBody body = new InjectionBody(timeseriesData.getTagName());
        body.addAttributes("sourceTagId", timeseriesData.getTagName());
        body.addAttributes("assetId", timeseriesData.getAssetId() );
        body.addDataPoint(timeseriesData.getTimeStamp(), timeseriesData.getMeasureValue());
        metric.getBody().add(body);
        builder.addMetrics(metric);  
        return builder;
    }
    
    
    public InjectionMetricBuilder latBuilder(LocomotiveGatewayType data){
    	Long latitude_Message_number=new Long(System.currentTimeMillis());
    	InjectionMetricBuilder builder = InjectionMetricBuilder.getInstance();
        InjectionMetric metric = new InjectionMetric(latitude_Message_number);
        InjectionBody body = new InjectionBody( data.getLocdata().getName()); // this should come from asset services once We are able to integrate the same
        body.addAttributes("LATITUDE", "Latitude");// attribute should pulled from Asset Store
        Double convertedValue =  Double.parseDouble(data.getLocdata().getLatitude());
        body.addDataPoint(converLocalTimeToUtcTime(data.getCurrentTime()),convertedValue);
        metric.getBody().add(body);
        builder.addMetrics(metric);   
        
        return builder;
    }
    public InjectionMetricBuilder longBuilder(LocomotiveGatewayType data){
    	Long longitude_Message_number=new Long(System.currentTimeMillis());
    	InjectionMetricBuilder builder = InjectionMetricBuilder.getInstance();
        InjectionMetric metric = new InjectionMetric(longitude_Message_number);
        InjectionBody body = new InjectionBody( data.getLocdata().getName()); // this should come from asset services once We are able to integrate the same
        body.addAttributes("LONGITUDE", "Longitude");// attribute should pulled from Asset Store
        Double convertedValue =  Double.parseDouble(data.getLocdata().getLongitude());
        body.addDataPoint(converLocalTimeToUtcTime(data.getCurrentTime()),convertedValue);
        metric.getBody().add(body);
        builder.addMetrics(metric);   
        
        return builder;
    }
    public InjectionMetricBuilder rpmBuilder(LocomotiveGatewayType data){
    	Long rpm_Message_number=new Long(System.currentTimeMillis());
    	InjectionMetricBuilder builder = InjectionMetricBuilder.getInstance();
        InjectionMetric metric = new InjectionMetric(rpm_Message_number);
        InjectionBody body = new InjectionBody( data.getRpmData().getName()); // this should come from asset services once We are able to integrate the same
        body.addAttributes("RPM DATA", "rpm");// attribute should pulled from Asset Store
        Double convertedValue =  data.getRpmData().getRpm();
        body.addDataPoint(converLocalTimeToUtcTime(data.getCurrentTime()),convertedValue);
        metric.getBody().add(body);
        builder.addMetrics(metric);  
        
        return builder;
    }
    public InjectionMetricBuilder torqueBuilder(LocomotiveGatewayType data){
    	Long temprature_Message_number=new Long(System.currentTimeMillis());
    	InjectionMetricBuilder builder = InjectionMetricBuilder.getInstance();
        InjectionMetric metric = new InjectionMetric(temprature_Message_number);
        log.info(">>>>>>>>>>>>>DATA>>>>>>>>>>>>>>>>"+data);
        log.info(">>>>>>>>>>>>>INSIDE TORQUE DATA>>>>>>>>>>>>>>>>"+data.getTorquedata());
        log.info(">>>>>>>>>>INSIDE ALL DATA >>>>>>>>>>>>TIME>>>>>>>"+data.getCurrentTime()+ "++LocDATA++"+data.getLocdata()+"+++++RPM DATA+++"+data.getRpmData()+"++++++++++++");                
        InjectionBody body = new InjectionBody( data.getTorquedata().getName()); // this should come from asset services once We are able to integrate the same
        body.addAttributes("TORQUE", "Torque");// attribute should pulled from Asset Store
        Double convertedValue =  data.getTorquedata().getTorque();
        body.addDataPoint(converLocalTimeToUtcTime(data.getCurrentTime()),convertedValue);
        metric.getBody().add(body);
        builder.addMetrics(metric);   
        
        return builder;
    }

    /**
     * @param nodeName -
     * @param value -
     * @return -
     */
    @SuppressWarnings("nls")
    public Double getConvertedValue(String nodeName, Double value)
    {
        Double convValue = null;
        switch (nodeName.toLowerCase())
        {
            case Constants.COMPRESSION_RATIO:
                convValue = value * 9.0 / 65535.0 + 1;
                break;
            case Constants.DISCHG_PRESSURE:
                convValue = value * 100.0 / 65535.0;
                break;
            case Constants.SUCT_PRESSURE:
                convValue = value * 100.0 / 65535.0;
                break;
            case Constants.MAX_PRESSURE:
                convValue = value * 100.0 / 65535.0;
                break;
            case Constants.MIN_PRESSURE:
                convValue = value * 100.0 / 65535.0;
                break;
            case Constants.VELOCITY:
                convValue = value * 0.5 / 65535.0;
                break;
            case Constants.TEMPERATURE:
                convValue = value * 200.0 / 65535.0;
                break;
            default:
                throw new UnsupportedOperationException("nameName=" + nodeName + " not found");
        }
        return convValue;
    }

    private long converLocalTimeToUtcTime(long timeSinceLocalEpoch)
    {
        return timeSinceLocalEpoch + getLocalToUtcDelta();
    }

    private long getLocalToUtcDelta()
    {
        Calendar local = Calendar.getInstance();
        local.clear();
        local.set(1970, Calendar.JANUARY, 1, 0, 0, 0);
        return local.getTimeInMillis();
    }

    private AssetMeter getAssetMeter(LinkedHashMap<String, AssetMeter> meters,String nodeName)
    {
    	AssetMeter ret = null;
		if ( meters != null ) {
            for (Entry<String, AssetMeter> entry : meters.entrySet())
            {
                AssetMeter assetMeter = entry.getValue();
                //MeterDatasource dataSource = assetMeter.getMeterDatasource();
                if ( assetMeter != null && !assetMeter.getSourceTagId().isEmpty() && nodeName !=null
                        && nodeName.toLowerCase().contains(assetMeter.getSourceTagId().toLowerCase()))
                {
                    ret = assetMeter;
                    return ret;
                }
            }
		}else {
			log.warn("2. asset has no assetmeters with matching nodeName"+ nodeName);
        }
        return ret;
    }
    @SuppressWarnings("nls")
    private OAuth2RestTemplate getRestTemplate(String clientId, String clientSecret)
    {
        // get token here based on username password;
       // ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
        ClientCredentialsResourceDetails clientDetails = new ClientCredentialsResourceDetails();
        clientDetails.setClientId(clientId);
        clientDetails.setClientSecret(clientSecret);
        String url = this.restConfig.getOauthResourceProtocol() + "://" + this.restConfig.getOauthRestHost()
                + this.restConfig.getOauthResource();
        clientDetails.setAccessTokenUri(url);
        clientDetails.setGrantType("client_credentials");
       
        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(clientDetails);

        return restTemplate;
    }
}
