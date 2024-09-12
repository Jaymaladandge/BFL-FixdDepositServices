package com.bajaj.fixeddeposit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface ResumeJourneyService {

	
	String resumeJourneygetOTP(String requestJson, HttpServletRequest request,HttpSession httpSession);
	
	String resumeJourneyValidateOtp(String validateOtpReqJson, HttpSession httpSession,HttpServletRequest request,String contextCalled);
}
