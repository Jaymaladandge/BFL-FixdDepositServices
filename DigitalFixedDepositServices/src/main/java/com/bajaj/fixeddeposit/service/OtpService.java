package com.bajaj.fixeddeposit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.bajaj.fixeddeposit.model.FixedDepositData;
import com.bajaj.fixeddeposit.model.OtpResponse;

public interface OtpService {
	
	String otpGeneratorService(String mobileNumber, HttpServletRequest request,HttpSession httpSession);
	
	OtpResponse setOtpResponseService(String generateOtpRes);
	
	String otpValidatorService(String validateOtpReqJson, HttpSession httpSession,HttpServletRequest request);
	
	String savePartialDataService(String otpRequest);
	
	JSONObject partialSfdcService(FixedDepositData generatorOtpReq, String fullName,String stepNo,String contextCalled);
	
	String eventHubOtpCall(String customerId, String StepNoString);
	
	String otpValidatorServiceFDSLF(String validateOtpReqJson, HttpSession httpSession,HttpServletRequest request);

	JSONObject resumeOtpService(String request);
	
	JSONObject resumeRedirectService(String request,String customerId);
	
	String manualpartialSfdc(String cusID);

	JSONObject manualpartialSfdcMulitple(String custIdArray);
	
	String pincodeValidatorService(String pincode);
}
