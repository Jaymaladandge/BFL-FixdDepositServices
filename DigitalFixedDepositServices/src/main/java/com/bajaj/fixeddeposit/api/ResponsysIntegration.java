package com.bajaj.fixeddeposit.api;

import java.io.IOException;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.bajaj.fixeddeposit.util.Constants;
import com.bajaj.fixeddeposit.util.DbManipulationUtil;
import com.bajaj.fixeddeposit.util.Utility;

@Component
public class ResponsysIntegration {
	
	static final int TIMEOUT_MILLIS =60000;
	static final int TOKEN_TIMEOUT_MILLIS =60000;
	
	private static final Logger logger = Logger.getLogger(ResponsysIntegration.class);
	
	@Autowired
	private DbManipulationUtil dbManipulation; 
	
	public String sendSms(String smsRequest,String customerId){
		
		logger.info(" == smsRequest in sendSms ==== " + smsRequest);
		
		String smsResponse ="";
		String url=null;
		long start = 0;
		long end = 0;
		
		start = System.currentTimeMillis();
		HttpComponentsClientHttpRequestFactory httpRequestFactory=null;
		url = Utility.getPropertyFileValue("CampaignProcessingURL").trim();
		try {
			RequestConfig config = RequestConfig.custom()
					.setConnectTimeout(TIMEOUT_MILLIS)
					.setConnectionRequestTimeout(TIMEOUT_MILLIS)
					.setSocketTimeout(TIMEOUT_MILLIS)
					.build();

			start = System.currentTimeMillis();
			
			
			try(CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();){
				httpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
			//String url = "http://10.162.6.4:7887/Responsys/api/CampaignProcessing";
			
			//String url ="http://10.172.64.10:7887/Responsys/api/CampaignProcessing";
			
			RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
			
			MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
			headers.add("Content-Type", "application/json");
			
			HttpEntity<?> entity = new HttpEntity<>(smsRequest, headers);
			
			start = System.currentTimeMillis();
			smsResponse = restTemplate.postForObject(url, entity, String.class);
			end = System.currentTimeMillis();
			String smsRes = smsResponse;
			
			logger.info(" == smsResponse in sendSms === " + smsResponse);
			
			JSONObject smsobject = new JSONObject(smsResponse);
			
			smsResponse = smsobject.has("status") ? smsobject.get("status").toString() : "NA" ;
			logger.info(" == smsResponse in smsobject status === " + smsResponse);
			
			if(smsResponse != null){
				Utility.loadNewRelicCustomEvent(url, String.valueOf(end-start), "", "",Constants.PAYMENTRESPONSE,customerId);
			}else{
				Utility.loadNewRelicCustomEvent(url, String.valueOf(end-start), "", "FAIL",Constants.PAYMENTRESPONSE,customerId);
			}
			}	
		} catch (RestClientException e) {
			smsResponse = Constants.STATUS_FAIL;
			logger.error(" === Exception in sendSms ==== ",e);
			if(!(customerId==null && customerId.isEmpty())){
				dbManipulation.recordExeption("SMS","API issue", customerId);
			}
			Utility.loadNewRelicCustomExceptionEvent(url, String.valueOf(System.currentTimeMillis()), "", "FAIL", e.toString(),Constants.PAYMENTRESPONSE,customerId);
		} catch (JSONException |IOException e) {
			logger.error("==========JSONException in sendSms========",e);
			if(!(customerId==null && customerId.isEmpty())){
				dbManipulation.recordExeption("SMS","API issue", customerId);
			}
			Utility.loadNewRelicCustomExceptionEvent(url, String.valueOf(System.currentTimeMillis()), "", "FAIL", e.toString(),Constants.PAYMENTRESPONSE,customerId);
		}finally{
			if(httpRequestFactory!=null)
			{
				try {
					httpRequestFactory.destroy();
					logger.info("----- Closing Resources.... ----- ");
				} catch (Exception e) {
					logger.error("----- Exception in Closing Resources callRestApi ----- ",e);
				}
			}
		}
		
		return smsResponse;
		
	}
	

}
