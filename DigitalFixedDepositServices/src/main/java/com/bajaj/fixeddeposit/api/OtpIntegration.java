package com.bajaj.fixeddeposit.api;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bajaj.fixeddeposit.util.Utility;

@Component
public class OtpIntegration {

	private static final Logger logger = Logger.getLogger(OtpIntegration.class);
	
	@Autowired
	OtpApiIntegration otpApi;
	
	// Send OTP API 
	public String generateOtpApi(String mobileNumber,HttpServletRequest request,String fdcNumber,String contextCalled) {
		String otpResponse = "";
		logger.info("SEND OTP REMOTE ADD "+request.getRemoteAddr());
		
		try {
			boolean isValidIP=Utility.validBOTIP(request);
			if(isValidIP){
				otpResponse = "{\"requestID\":\"123456\",\"errorCode\":\"00\",\"mobile_No__c\":\"7020713901\",\"errorMsg\":\"OTP Sent Successfully\",\"status\":\"200\"}";
				logger.info("-------- ByPassing OTP API -------");
			} else{
			otpResponse = otpApi.sendFDOTP(mobileNumber,fdcNumber,contextCalled);
			}
			logger.info(" ==== Otp Response form Otp Api Jar in ApiIntegration ===  " + otpResponse);
		} catch (Exception e) {
			otpResponse = "{\"errorCode\":\"99\"}";
			logger.error(" ===== Exception in generateOtpApi ApiIntegration === ", e);
		}
		return otpResponse;
	}
	
	// Validate OTP API
	public String validateOtpApi(String userOtp, String userMobileNumber, String requestId,String customerId,HttpServletRequest request,String contextCalled) {
		String otpVaidationResponse = "";
		try {
			logger.info(" === Validate Otp Request in validateOtpApi ==== userOtp ==  " + userOtp + " == userMobileNumber == "
					+ userMobileNumber + "=== requestId == " + requestId);
			 logger.info("VALIDATE OTP REMOTE ADD "+request.getRemoteAddr());
			boolean isValidIP=Utility.validBOTIP(request);
			String apiResponse=null;       
			if(isValidIP){
				ArrayList<String> byPassList=new ArrayList<String>(9);     
				byPassList.add("9714466454");
				byPassList.add("9773567945");
				byPassList.add("9673914441");
				byPassList.add("9874069850");
				if(byPassList.contains(userMobileNumber))  
				{
					logger.info("-------- ByPassing OTP API mobileNumber-------"+userMobileNumber);  
					otpVaidationResponse="{\"status\":\"success\",\"message\":\"Done\",\"data\":[{\"flag\":1}]}";	
				}
				else
				{
					otpVaidationResponse="{\"status\":\"success\",\"message\":\"Done\",\"data\":[{\"flag\":1}]}";
				}  
				logger.info("-------- ByPassing OTP API -------");  
			} else {  
				otpVaidationResponse = otpApi.getotpvalidation(userMobileNumber, "", "", userOtp, "WEBSITE", requestId,customerId,contextCalled);
			}  
			logger.info(" === Validate Otp Response in validateOtpApi ==== " + otpVaidationResponse);
		} catch (Exception e) {
			otpVaidationResponse = "{\"responseCode\":\"99\"}";
			logger.error(" ==== Exception in validateOtpApi ====== ", e);
		}
		return otpVaidationResponse;
	}

	
}
