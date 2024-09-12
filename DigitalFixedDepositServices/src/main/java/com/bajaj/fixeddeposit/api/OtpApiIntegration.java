package com.bajaj.fixeddeposit.api;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bajaj.fixeddeposit.util.Constants;
import com.bajaj.fixeddeposit.util.DbManipulationUtil;
import com.bajaj.fixeddeposit.util.Utility;
import com.newrelic.api.agent.NewRelic;

@Component
public class OtpApiIntegration 
{

	static final int TIMEOUT_MILLIS =15000;
	static final int TOKEN_TIMEOUT_MILLIS =30000;
	private static final Logger LOG = Logger.getLogger(OtpApiIntegration.class);

	@Autowired
	CustIdIntegration custId;
	
	@Autowired
	private DbManipulationUtil dbManipulation;  
	
	
	public String sendFDOTP(String mobile,String fdcNumber,String contextCalled) 
	{

		StringBuilder otpReq = new StringBuilder();
		otpReq.append("{\"MOBILE\": \"");
		otpReq.append(mobile);
		otpReq.append( "\",\"EVENT_NAME\": \"");
		otpReq.append("FD_OTP");
		otpReq.append( "\",\"SOURCE\": \"");
		otpReq.append("WEBSITE\"}");

		LOG.info("OTP Request : "+ otpReq);
		return sendOTP(otpReq.toString(),fdcNumber,contextCalled);

	}
	public String getotpvalidation(String mobileNumber, String firstName, String lastName, String otp, String otpSource,
			String requestId,String customerId,String contextCalled) {

		StringBuilder validateOTPReq = new StringBuilder();
		validateOTPReq.append("{\"otp\": \"");
		validateOTPReq.append(otp);
		validateOTPReq.append("\",");
		validateOTPReq.append("\"otpSource\": \"");
		validateOTPReq.append(otpSource);
		validateOTPReq.append("\",");
		validateOTPReq.append("\"requestID\": \"");
		validateOTPReq.append(requestId);
		validateOTPReq.append("\"}");

		StringBuilder offerReq = new StringBuilder();
		offerReq.append("{\"mobileNumber\": \"");
		offerReq.append(mobileNumber);
		offerReq.append("\"," + "\"firstName\": \"");
		offerReq.append("");
		offerReq.append("\",");
		offerReq.append("\"lastName\": \"");
		offerReq.append("");
		offerReq.append("\",");
		offerReq.append("\"sourceId\": \"3\",");
		offerReq.append("\"otp\": \"");
		offerReq.append(otp);
		offerReq.append("\",");
		offerReq.append("\"otpSource\": \"");
		offerReq.append(otpSource);
		offerReq.append("\",");
		offerReq.append("\"bypass\": \"");
		offerReq.append("auth");
		offerReq.append("\",");
		offerReq.append("\"requestID\": \"");
		offerReq.append("null");
		offerReq.append("\"}");


		return getvalidateOTP(validateOTPReq.toString(), offerReq.toString(),customerId,contextCalled);
	} 

	/************** Comments for Code Functionality *****************
	 * Statuscode - 200 and status eq 'Success' Will send ERROR_CODE_00 OTP send Successfully
	 * Statuscode  200  but status not eq 'Success' will Send ERROR_CODE_91 OTP my null
	 * Statuscode  400  will Send ERROR_CODE_92 API Failure ~ Mobile no is not valid
	 * Statuscode  500  will Send ERROR_CODE_99 OTP sercive is not available
	 * 
	 */
	public String sendOTP(String otpReq,String fdcNumber,String contextCalled)
	{
		LOG.info("==contextCalled==="+contextCalled+"=====fdcNumber===="+fdcNumber);
		String res = null;
		String otpendPoint ="";
		Integer statusCode = 0;
		String timeTaken = "";
		long start = 0;
		long end = 0;
		start = System.currentTimeMillis();
		try (CloseableHttpClient httpclient = HttpClients.createDefault()){

			/**** Property File Reading *****/
			 otpendPoint = Utility.getPropertyFileValue("SendOTP").trim();
			String accessToken =custId.getDedupeAccessToekn(fdcNumber,contextCalled);
			LOG.info("  Token in Validate OTP "+accessToken);
			LOG.info(" ========== accessToken==== :" + accessToken);
			if( ! "NA".equals(accessToken)){
				URIBuilder builder = new URIBuilder(otpendPoint);
				URI uri = builder.build();
				HttpPost req = new HttpPost(uri);
				RequestConfig requestConfig = RequestConfig.custom()
						.setSocketTimeout(TIMEOUT_MILLIS)
						.setConnectTimeout(TIMEOUT_MILLIS)
						.setConnectionRequestTimeout(TIMEOUT_MILLIS)
						.build();
				req.setConfig(requestConfig);

				req.setHeader(Constants.CONTENT_TYPE_KEY, "application/json");
				req.setHeader("Ocp-Apim-Subscription-Key", Utility.getPropertyFileValue("OTPApimSubscriptionKey").trim());
				req.setHeader("Authorization", "Bearer " + accessToken);
				LOG.info("  Send OTP API Request :  \t "+otpReq);
				StringEntity reqEntity = new StringEntity(otpReq);
				req.setEntity(reqEntity);
				start = System.currentTimeMillis();	

				try(CloseableHttpResponse response1 = httpclient.execute(req)){
					statusCode = response1.getStatusLine().getStatusCode();
					LOG.info("  API Status Code \t " +statusCode);
					HttpEntity entity = response1.getEntity();
					end = System.currentTimeMillis();
					res = EntityUtils.toString(entity);
					LOG.info("  Send OTP API Response : \t "+res);
					if (statusCode == 200 && entity != null) {

						timeTaken = String.valueOf(end-start);
						LOG.info(" ========== SENDOTP Response :" + timeTaken + " millis");

						if(res.contains("success"))
						{
							JSONObject responseJson = new JSONObject(res);

							String errorCode =responseJson.has("status")?responseJson.getString("status"):"91";
							String requestID = responseJson.has("data")?((JSONObject)((JSONArray)responseJson.get("data")).get(0)).getString("request_id"):"WEBSITE";

							if("success".equals(errorCode))
							{
								res=Constants.ERROR_CODE_00;
								JSONObject request=new JSONObject(otpReq);
								responseJson=new JSONObject(res);
								responseJson.put("mobile_No__c", request.getString("MOBILE"));
								responseJson.put("requestID", requestID);

								res=responseJson.toString();
								Utility.loadNewRelicCustomEvent(otpendPoint, timeTaken, String.valueOf(statusCode), "",contextCalled,fdcNumber);
							}else
							{	
								res=Constants.ERROR_CODE_91;
								Utility.loadNewRelicCustomEvent(otpendPoint, timeTaken, String.valueOf(statusCode), "FAIL",contextCalled,fdcNumber);
							}
							
						}
					}
					else if(statusCode==400)
					{
						/**** BAD Request ****/
						Utility.loadNewRelicCustomEvent(otpendPoint, String.valueOf(System.currentTimeMillis() - start), String.valueOf(statusCode), "FAIL",contextCalled,fdcNumber);
						res=Constants.ERROR_CODE_92;
						
					}else if (statusCode == 500) {
						Utility.loadNewRelicCustomEvent(otpendPoint, String.valueOf(System.currentTimeMillis() - start), String.valueOf(statusCode), "FAIL",contextCalled,fdcNumber);
						res=Constants.ERROR_CODE_99;
					}
					else{
						/*** Internal Server Error ****/
						res=Constants.ERROR_CODE_99;
						Utility.loadNewRelicCustomEvent(otpendPoint, String.valueOf(System.currentTimeMillis() - start), String.valueOf(statusCode), "FAIL",contextCalled,fdcNumber);
					}
					
				}
			}
			else{
				Utility.loadNewRelicCustomEvent(otpendPoint, String.valueOf(System.currentTimeMillis() - start), "", "FAIL",contextCalled,fdcNumber);
				res=Constants.ERROR_CODE_99;
			}
		}
		catch (Exception ex){
			LOG.error("============= SEND OTP : ERROR=========== \n", ex);
			LOG.info("  Send OTP API Response : \t "+res);
			LOG.error(res);
			res=Constants.ERROR_CODE_99;
			Utility.loadNewRelicCustomExceptionEvent(otpendPoint, String.valueOf(System.currentTimeMillis() - start), String.valueOf(statusCode), "FAIL", ex.toString(),contextCalled,fdcNumber);
			
		}
		LOG.info(" ------  Final SEND OTP  Respons  \t"+res);
		return res;
	}

	/************** Comments for Code Functionality *****************
	 * Statuscode - 200 and status eq 'Success' and flag=1 Will call GETOFFER API
	 * Statuscode  200  and flag=0  will Send VALIDATE_ERROR_CODE_92 Mobile Number or OTP not valid
	 * Statuscode  400  will Send VALIDATE_ERROR_CODE_92 Mobile Number or OTP not valid
	 * Statuscode  500  will Send VALIDATE_ERROR_CODE_99 Validate OTP sercive is not available
	 * 
	 */
	public String getvalidateOTP(String validateOTPReq,String offerReq,String customerId,String contextCalled)
	{
		LOG.info("=========getvalidateOTP customerId=========="+customerId+"======="+contextCalled);
		String res= "";
		String url ="";
		Integer statusCode = 0;
		String timeTaken="";
		long start=0;
		long end = 0;
		start = System.currentTimeMillis();
		try (CloseableHttpClient httpclient = HttpClients.createDefault()){
			 url = Utility.getPropertyFileValue("ValidateOTP").trim();
			String key = Utility.getPropertyFileValue("OTPApimSubscriptionKey").trim();  

			String accessToken =custId.getDedupeAccessToekn(customerId,contextCalled);
			LOG.info("  Token in Validate OTP "+accessToken);
			if(accessToken!=null)
			{
				URIBuilder builder = new URIBuilder(url);
				URI uri = builder.build();
				HttpPost req = new HttpPost(uri);    
				req.setHeader(Constants.CONTENT_TYPE_KEY, "application/json");
				req.setHeader("Ocp-Apim-Subscription-Key", key);
				req.setHeader("Authorization", "Bearer " + accessToken);

				StringEntity reqEntity = new StringEntity(validateOTPReq);
				req.setEntity(reqEntity);

				RequestConfig requestConfig = RequestConfig.custom()
						.setSocketTimeout(TIMEOUT_MILLIS)
						.setConnectTimeout(TIMEOUT_MILLIS)
						.setConnectionRequestTimeout(TIMEOUT_MILLIS)
						.build();
				req.setConfig(requestConfig);
				start = System.currentTimeMillis();

				try(CloseableHttpResponse response1 = httpclient.execute(req)){
					end = System.currentTimeMillis();
					LOG.info("  Validate OTP API Request :  \t "+validateOTPReq);
					statusCode = response1.getStatusLine().getStatusCode();
					LOG.info("  Validate OTP API Status Code \t " +statusCode);
					HttpEntity entity = response1.getEntity();
					res=EntityUtils.toString(entity);  
					LOG.info("  Validate OTP API Response : \t "+res);
					if (statusCode == 200 && entity != null) {
						timeTaken = String.valueOf(end-start);
						LOG.info("==================== Validation Response :"+ timeTaken + " millis ===================");

						if(res.contains("success")){

							JSONObject responseJson = new JSONObject(res);

							String errorCode =responseJson.has("status")?responseJson.getString("status"):"99";
							String errorMsg =responseJson.has("message")?responseJson.getString("message"):"99";
							Integer flag = responseJson.has("data")?(int) ((JSONObject)((JSONArray)responseJson.get("data")).get(0)).get("flag"):0;
							LOG.info(errorCode +" "+errorMsg+" "+flag);

							if("success".equals(errorCode) && flag==1 ){
								/*** Validate Offer API ****/
								Utility.loadNewRelicCustomEvent(url, timeTaken, String.valueOf(statusCode), "",contextCalled,customerId);
								return res; 
							}else{
								res=Constants.VALIDATE_ERROR_CODE_93;
								Utility.loadNewRelicCustomEvent(url, timeTaken, String.valueOf(statusCode), "FAIL",contextCalled,customerId);
							}
							
						
						}else{
							Utility.loadNewRelicCustomEvent(url, timeTaken, String.valueOf(statusCode), "FAIL",contextCalled,customerId);
							res=Constants.VALIDATE_ERROR_CODE_92;
						}
					}
					else if(statusCode == 400){
						/*** BAD Request ***/
						
						Utility.loadNewRelicCustomEvent(url, String.valueOf(System.currentTimeMillis()-start), String.valueOf(statusCode), "FAIL",contextCalled,customerId);
						res=Constants.VALIDATE_ERROR_CODE_92;
					}
					else{
						/*** Internal Server Error ***/
						Utility.loadNewRelicCustomEvent(url, String.valueOf(System.currentTimeMillis()-start), String.valueOf(statusCode), "FAIL",contextCalled,customerId);
						res=Constants.VALIDATE_ERROR_CODE_99;
					}
					
				}
			}
			else
			{
				LOG.error(res);
				Utility.loadNewRelicCustomEvent(url, String.valueOf(System.currentTimeMillis()-start),"", "FAIL",contextCalled,customerId);
				res=Constants.ACCESSTOKEN_ERROR;
			}
			LOG.info(" ------  Final VALIDATE OTP  Respons  \t"+res);
			return res;
		} 
		catch (Exception ex){
			LOG.error("======================== OTP Validation : ERROR ==============", ex);
			if(!(customerId==null && customerId.isEmpty())){
				dbManipulation.recordExeption("VALIDATE OTP API","API issue", customerId);
			}
			LOG.error(res);
			LOG.info("  Validate OTP API Response : \t "+res);
			LOG.info(" ------  Final VALIDATE OTP  Respons  \t"+res);
			Utility.loadNewRelicCustomExceptionEvent(url, String.valueOf(System.currentTimeMillis() - start), "", "FAIL", ex.toString(),Constants.VALIDATEOTP,customerId);
			res=Constants.VALIDATE_ERROR_CODE_99;
			return res;

		}	
	}

	
}
