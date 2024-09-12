package com.bajaj.fixeddeposit.api;

import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.logging.Level;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HttpsURLConnection;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.bajaj.fixeddeposit.util.Constants;
import com.bajaj.fixeddeposit.util.DbManipulationUtil;
import com.bajaj.fixeddeposit.util.Utility;
import com.newrelic.api.agent.NewRelic;

@Component
public class SfdcIntegration {
	
	private static final Logger logger = Logger.getLogger(SfdcIntegration.class);
	
	static final int TIMEOUT_MILLIS =60000;
	static final int TOKEN_TIMEOUT_MILLIS =60000;

	@Autowired
	private DbManipulationUtil dbManipulation;  
	
	public StringBuilder getSfdcAccessToken(String cusstomerId,String contextCalled){
		
		logger.info("========getSfdcAccessToken cusstomerId========="+cusstomerId+"====contextCalled==="+contextCalled);
		String accessToken = "";
		String tokenType = "";
		StringBuilder typeToken = new StringBuilder();
		String sfdcTokenUrl=null;
		JSONObject newjson = null;
		long start = 0;
		long end = 0;
		String statusCode = "";
		
	    start = System.currentTimeMillis();
		HttpComponentsClientHttpRequestFactory httpRequestFactory=null;
		try {
			RequestConfig config = RequestConfig.custom()
					.setConnectTimeout(TIMEOUT_MILLIS)
					.setConnectionRequestTimeout(TIMEOUT_MILLIS)
					.setSocketTimeout(TIMEOUT_MILLIS)
					.build();

			try(CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();){
				httpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
				
			 sfdcTokenUrl  =Utility.getPropertyFileValue("SFDC_TOKEN_ENDPOINT").trim();
			RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			params.add("grant_type",Utility.getPropertyFileValue("SFDC_TOKEN_GRANTTYPE").trim());
			params.add("client_id",  Utility.getPropertyFileValue("SFDC_CLIENT_ID").trim());
			params.add("client_secret",Utility.getPropertyFileValue("SFDC_CLIENT_SECRET").trim());
			params.add("username",  Utility.getPropertyFileValue("SFDC_USERNAME").trim());
			params.add("password",Utility.getPropertyFileValue("SFDC_PASSWORD").trim());
			
			logger.info("=================params==================="+params);
			
			HttpEntity<?> entity = new HttpEntity<>(params,headers);
			
			start = System.currentTimeMillis();
			ResponseEntity<String> accessTokenResponse = restTemplate.exchange(sfdcTokenUrl, HttpMethod.POST, entity,
					String.class);
			logger.info("=================accessTokenResponse==================="+accessTokenResponse);
			end = System.currentTimeMillis();
			
			statusCode = String.valueOf(accessTokenResponse.getStatusCode());
			String accessTokenBody = accessTokenResponse.getBody();
			newjson = XML.toJSONObject(accessTokenBody);
			accessTokenBody = newjson.get("OAuth").toString();
			
			if (!accessTokenBody.isEmpty()) {
				JSONObject accessTokenBodyJson = new JSONObject(accessTokenBody);
				accessToken = accessTokenBodyJson.has(Constants.ACCESS_TOKEN) ? accessTokenBodyJson.getString(Constants.ACCESS_TOKEN): "";
				tokenType = accessTokenBodyJson.has(Constants.TOKEN_TYPE) ? accessTokenBodyJson.getString(Constants.TOKEN_TYPE): "";

				if (!accessToken.isEmpty()) {
					accessToken = "null".equals(accessToken) ? Constants.ACCESS_TOKEN_STATUS : accessTokenBodyJson.getString(Constants.ACCESS_TOKEN);
					tokenType = "null".equals(tokenType) ? Constants.TOKEN_TYPE_STATUS : accessTokenBodyJson.getString(Constants.TOKEN_TYPE);
					Utility.loadNewRelicCustomEvent(sfdcTokenUrl, String.valueOf(end-start), statusCode, "",contextCalled,cusstomerId);
				} else {
					accessToken = Constants.ACCESS_TOKEN_STATUS;
					tokenType = Constants.TOKEN_TYPE_STATUS;
					Utility.loadNewRelicCustomEvent(sfdcTokenUrl, String.valueOf(end-start), statusCode, "FAIL",contextCalled,cusstomerId);
				}

				
			} else {
				accessToken = Constants.ACCESS_TOKEN_STATUS;
				Utility.loadNewRelicCustomEvent(sfdcTokenUrl, String.valueOf(end-start), statusCode, "FAIL",contextCalled,cusstomerId);
			}
			
			logger.info(" === Token Type == "+ tokenType +" === accessToken == " + accessToken);
			typeToken.append(tokenType).append(" ").append(accessToken);
			logger.info(" === Token == " + typeToken);
			}
		} catch (Exception e) {
			logger.error(" ==== Exception in getSfdcAccessToken  === ", e);
			Utility.loadNewRelicCustomExceptionEvent(sfdcTokenUrl, String.valueOf(System.currentTimeMillis()-start), statusCode, "FAIL", e.toString(),contextCalled,cusstomerId);
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
		
		return typeToken;
		
	}
	
	public synchronized JSONObject partialSfdc(String sfdcRequestJsonStr,String customerId,String contextCalled){
		
		logger.info("=======partialSfdc customerId======="+customerId+"====contextCalled===="+contextCalled);
		JSONObject apiResJson = new JSONObject();
		String entityResponse = null;
		String sfdcApi=null;
		long start = 0;
		long end = 0;
		String statusCode = "";
		start = System.currentTimeMillis();
		try {
			RequestConfig config = RequestConfig.custom()
					.setConnectTimeout(TIMEOUT_MILLIS)
					.setConnectionRequestTimeout(TIMEOUT_MILLIS)
					.setSocketTimeout(TIMEOUT_MILLIS)
					.build();

			try(CloseableHttpClient closeableHttpClient1 = HttpClients.createDefault()){
				
			 sfdcApi =Utility.getPropertyFileValue("SFDC_API").trim();

			
			URIBuilder builder1 = new URIBuilder(sfdcApi);
			logger.info("===== Prod End Point SFDCAPI === " + sfdcApi);
			java.net.URI uri1 = builder1.build();
			HttpPost req1 = new HttpPost(uri1);
			
			req1.setConfig(config);
			req1.setHeader("Method", "POST");
			req1.setHeader("Content-Type", "application/json");
			req1.setHeader("Authorization", getSfdcAccessToken(customerId,contextCalled).toString());
			req1.setHeader("Ocp-Apim-Subscription-Key",Utility.getPropertyFileValue("SFDC_SUBKEY").trim());

			logger.info("===== SFDC Request Value "+sfdcRequestJsonStr.toString());

			StringEntity reqEntity1 = new StringEntity(sfdcRequestJsonStr.toString());
			req1.setEntity(reqEntity1);
			
				start = System.currentTimeMillis();
				try (CloseableHttpResponse closeableHttpResponse1 = closeableHttpClient1.execute(req1)) {

					statusCode = String.valueOf(closeableHttpResponse1.getStatusLine().getStatusCode());
					org.apache.http.HttpEntity entity1 = closeableHttpResponse1.getEntity();
					logger.info("===== SFDC Response Value===== " + entity1);
					end = System.currentTimeMillis();
					
					if (entity1 != null) {
						entityResponse = EntityUtils.toString(entity1);
						logger.info("===== SFDC Response Value===== " + entityResponse);
						
						JSONObject sfdcPartialApiResponseJson  = new JSONObject(entityResponse);
						if(sfdcPartialApiResponseJson.has("JSNRESP")){
							apiResJson = sfdcPartialApiResponseJson.getJSONObject("JSNRESP");
							String finalSfdcStatus = apiResJson.has("IsSuccess")  ?  apiResJson.get("IsSuccess").toString():"NA";
							if("False".equalsIgnoreCase(finalSfdcStatus))
							{
								Utility.loadNewRelicCustomEvent(sfdcApi, String.valueOf(end-start), statusCode, "",contextCalled,customerId);
							}
						}else{
							apiResJson.put("IsSuccess", "fail");
							apiResJson.put("FdSfdcId", "fail");
							Utility.loadNewRelicCustomEvent(sfdcApi, String.valueOf(end-start), statusCode, "FAIL",contextCalled,customerId);
						}
					}else{
						Utility.loadNewRelicCustomEvent(sfdcApi, String.valueOf(end-start), statusCode, "FAIL",contextCalled,customerId);
					}
				}
			}	
		} catch (Exception e) {
			logger.error(" ==== Exception in Partial SFDC ==== ",e);
			if(!(customerId==null && customerId.isEmpty())){
				dbManipulation.recordExeption("SFDC API","API issue", customerId);
			}
			apiResJson.put("IsSuccess", "fail");
			apiResJson.put("FdSfdcId", "fail");
			Utility.loadNewRelicCustomExceptionEvent(sfdcApi, String.valueOf(System.currentTimeMillis()-start), statusCode, "FAIL", e.toString(),contextCalled,customerId);
		}
		
		return apiResJson;
		
	}
	
	public String eventHubApi(String eventHubjson,String contextCalled,String customerId){
		logger.info("=============eventHubApi============="+customerId+"=========="+contextCalled);
		String res = "Failed";
		String 	eventhuburl=null;
		long start = 0;
		long end = 0;
		int statusCode = 0;
		start = System.currentTimeMillis();
		try {
			
		
			String keyName =Utility.getPropertyFileValue("FDKeyName").trim();
			String	key = Utility.getPropertyFileValue("FDKeyValue").trim();
			 	eventhuburl= Utility.getPropertyFileValue("FDEventHubUrl").trim();
			
			logger.info("----Eventhub keyname----" + keyName);
			logger.info("----Eventhub keyValue----" + key);
			logger.info("----Eventhub Url----" + eventhuburl);
		
			String token = getSASToken(eventhuburl, keyName, key);
			
			logger.info("-------Eventhub token--------" + token);

			start = System.currentTimeMillis();
			
			URL url = new URL(eventhuburl);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/atom+xml;type=entry;charset=utf-8");
			con.setRequestProperty("Authorization", token);
			con.setReadTimeout(10000);
			

			OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
			writer.write(eventHubjson);
			writer.flush();
			end = System.currentTimeMillis();
			
			logger.info("-----Event hub response-----" + con.getResponseMessage() +" -- "+con.getResponseCode());
			statusCode=con.getResponseCode();
			
				if (statusCode==201) {
					res = "Success";
					Utility.loadNewRelicCustomEvent(eventhuburl, String.valueOf(end-start), String.valueOf(statusCode), "",contextCalled,customerId);
				}else
				{
					Utility.loadNewRelicCustomEvent(eventhuburl, String.valueOf(end-start), String.valueOf(statusCode), "FAIL",contextCalled,customerId);
				}
			
			
			logger.info("-----Event hub response-----" + res);
			
		} catch (Exception e) {
			logger.error("-----Exception in calleventhubAPI-------",e);
			Utility.loadNewRelicCustomExceptionEvent(eventhuburl, String.valueOf(System.currentTimeMillis()-start), "", "FAIL", e.toString(),contextCalled,customerId);
		}

		return res;
	
		}
	

	private static String getSASToken(String resourceUri, String keyName, String key)
	{
		long epoch = System.currentTimeMillis()/1000L;
		int week = 60*60*24*7;
		String expiry = Long.toString(epoch + week);
		String sasToken = null;
		try {
			String stringToSign = URLEncoder.encode(resourceUri, "UTF-8") + "\n" + expiry;
			String signature = getHMAC256(key, stringToSign);
			sasToken = "SharedAccessSignature sr=" + URLEncoder.encode(resourceUri, "UTF-8") +"&sig=" +
					URLEncoder.encode(signature, "UTF-8") + "&se=" + expiry + "&skn=" + keyName;
		} catch (Exception e) {
			logger.info("==== Exception in eventHubApi ===",e);
		}

		return sasToken;
	}	
	
	private static String getHMAC256(String key, String input) {
		Mac hMac = null;
		String hash = null;
		try {
			hMac = Mac.getInstance("HmacSHA256");
			SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "HmacSHA256");
			hMac.init(secretKey);
			Encoder encoder = Base64.getEncoder();
			hash = new String(encoder.encode(hMac.doFinal(input.getBytes("UTF-8"))));
			
			logger.info("=====Hmac256 algo==========="+hash);

		} catch(Exception e) {
			logger.info(" ==== Exception in eventHubApi ===  ",e);
		}
		return hash;
	}


	public JSONObject ntbDocumentUploadSFDC(String sfdcRequestJsonStr,String customerId,String contextCalled) throws JSONException
	{
		logger.info("=====ntbDocumentUploadSFDC customerId======"+customerId+"=====contextCalled====="+contextCalled);
		String sfdcApi = Utility.getPropertyFileValue("SFDC_DOCUPLOAD").trim();
		JSONObject apiResJson = new JSONObject();
		String entityResponse = null;
		String statusCode = "";
		long start = 0;
		long end = 0;
		start = System.currentTimeMillis();
		try {
			
			RequestConfig config = RequestConfig.custom()
					.setConnectTimeout(TIMEOUT_MILLIS)
					.setConnectionRequestTimeout(TIMEOUT_MILLIS)
					.setSocketTimeout(TIMEOUT_MILLIS)
					.build();

			try(CloseableHttpClient closeableHttpClient1 = HttpClients.createDefault()){
				
			

			
			String docToken =  getSfdcAccessToken(customerId,contextCalled).toString();
			
			String documentToken = "OAuth ".concat( docToken.substring(docToken.indexOf(' ') + 1).trim());
			
			URIBuilder builder1 = new URIBuilder(sfdcApi);
			logger.info("===== Prod End Point SFDCAPI === " + sfdcApi);
			java.net.URI uri1 = builder1.build();
			HttpPost req1 = new HttpPost(uri1);
			
			req1.setConfig(config);
			req1.setHeader("Method", "POST");
			req1.setHeader("Content-Type", "application/json");
			req1.setHeader("Authorization", documentToken);
			req1.setHeader("Ocp-Apim-Subscription-Key", Utility.getPropertyFileValue("SFDC_SUBKEY").trim());

			logger.info("===== ntbDocumentUploadSFDC Request Value "+sfdcRequestJsonStr.toString());

			StringEntity reqEntity1 = new StringEntity(sfdcRequestJsonStr.toString());
			req1.setEntity(reqEntity1);
			
				start = System.currentTimeMillis();
				try (CloseableHttpResponse closeableHttpResponse1 = closeableHttpClient1.execute(req1)) {

					statusCode = String.valueOf(closeableHttpResponse1.getStatusLine().getStatusCode());
					org.apache.http.HttpEntity entity1 = closeableHttpResponse1.getEntity();
					logger.info("===== ntbDocumentUploadSFDC Response Value===== " + entity1);
					end = System.currentTimeMillis();
					
					if (entity1 != null) {
						entityResponse = EntityUtils.toString(entity1);
						logger.info("===== ntbDocumentUploadSFDC Response Value===== " + entityResponse);
						
						JSONObject sfdcPartialApiResponseJson  = new JSONObject(entityResponse);
						String documentUploadId = sfdcPartialApiResponseJson.has("id")  ?   sfdcPartialApiResponseJson.get("id").toString():"NA";
						String docapiStatus = sfdcPartialApiResponseJson.has("success")  ?  sfdcPartialApiResponseJson.get("success").toString():"NA";
					
						if("true".equalsIgnoreCase(docapiStatus))
						{
							apiResJson.put("documentUploadId", documentUploadId);
							apiResJson.put("docapiStatus", "success");	
							Utility.loadNewRelicCustomEvent(sfdcApi, String.valueOf(end-start), statusCode, "",contextCalled,customerId);
						}
						else
						{
							apiResJson.put("documentUploadId", "fail");
							apiResJson.put("docapiStatus", "fail");
							Utility.loadNewRelicCustomEvent(sfdcApi, String.valueOf(end-start), statusCode, "FAIL",contextCalled,customerId);
						}
					}else{
						Utility.loadNewRelicCustomEvent(sfdcApi, String.valueOf(end-start), statusCode, "FAIL",contextCalled,customerId);
					}
				}
			}	
		} catch (Exception e) {
			logger.error(" ==== Exception in Partial SFDC ==== ",e);
			if(!(customerId==null && customerId.isEmpty())){
				dbManipulation.recordExeption("DOCUMENT API","API issue", customerId);
			}
			apiResJson.put("documentUploadId", "fail");
			apiResJson.put("docapiStatus", "fail");
			
			Utility.loadNewRelicCustomExceptionEvent(sfdcApi, String.valueOf(System.currentTimeMillis()-start), statusCode, "FAIL", e.toString(),contextCalled,customerId);
		}finally{
			Thread.interrupted();
			logger.info("------ Closing Resources Thraed .... !!!!");
		}
		
		return apiResJson;
		
	}
	
}
