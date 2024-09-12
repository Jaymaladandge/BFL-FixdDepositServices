package com.bajaj.fixeddeposit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bajaj.fixeddeposit.api.OtpIntegration;
import com.bajaj.fixeddeposit.dao.FixedDepositDao;
import com.bajaj.fixeddeposit.model.FixedDepositData;
import com.bajaj.fixeddeposit.model.OtpResponse;
import com.bajaj.fixeddeposit.service.OtpService;
import com.bajaj.fixeddeposit.service.ResumeJourneyService;
import com.bajaj.fixeddeposit.util.Constants;
import com.bajaj.fixeddeposit.util.DbManipulationUtil;
import com.bajaj.fixeddeposit.util.Encryption;
import com.bajaj.fixeddeposit.util.Utility;
import com.bajaj.fixeddeposit.validation.FormFieldValidation;
@RestController
public class ResumeJourneyController {
	private static final Logger logger = Logger.getLogger(ResumeJourneyController.class);

	@Autowired
	private FormFieldValidation formFieldValidation;

	@Autowired
	ResumeJourneyService ResumeJourneyService;

	@Autowired
	private OtpIntegration otpIntegration;  
	
	@Autowired
	private OtpService otpService; 
	
	@Autowired
	private DbManipulationUtil dbManipulation; 
	
	@Autowired
	private FixedDepositDao fixedDepositDao;
    
	
	@PostMapping("/resumeFdcJourneygetOTP")
	public String resumeJourney(@RequestBody String jsonRequest, @RequestHeader("X-CSRF-Token") String csrfToken, HttpServletRequest request){

		JSONObject responseJson = new JSONObject();
		String encryptedVal = "";
		try {
			HttpSession httpSession = request.getSession(false);
			if (httpSession == null) {
                responseJson.put("apiStatus", "sessionExpired");
            }
            else {
			logger.info(" ===== Session id in resumeJourney ==== " + httpSession.getId());
			logger.info(" ==== Token from Front End ==== " + csrfToken);
			logger.info(" == ntbJson in resumeJourney  == " + jsonRequest);
			String tokenValidationRes = formFieldValidation.tokenValidation(csrfToken, httpSession);
			logger.info(" ==== Token Validation Response in optGenerator == " + tokenValidationRes);
			if(Constants.STATUS_SUCCESS.equalsIgnoreCase(tokenValidationRes))
			{
				httpSession.removeAttribute(Constants.TOKEN);
				logger.info(" === Token Validated Successfully in resumeJourney and removed token from session === ");
				String generatorOtpResponse = ResumeJourneyService.resumeJourneygetOTP(jsonRequest,request,httpSession);
				logger.info(" ==== resumeJourney in optGenerator ==== " + generatorOtpResponse);
				if(Constants.STATUS_SUCCESS.equalsIgnoreCase(generatorOtpResponse)){
					logger.info(" === Otp Send Successfully ==== ");
					String mobileNumber = httpSession.getAttribute(Constants.MOBILE_NUMBER)==null?"":httpSession.getAttribute(Constants.MOBILE_NUMBER).toString();
					String customerIdSession= (String) httpSession.getAttribute(Constants.RESUME_J_CUSTOMER_ID)==null? "":(String) httpSession.getAttribute(Constants.RESUME_J_CUSTOMER_ID);
					responseJson.put(Constants.API_STATUS, Constants.STATUS_SUCCESS);
					responseJson.put(Constants.MOBILE_NUMBER, mobileNumber);
					responseJson.put(Constants.CUSTOMER_ID, customerIdSession);
					
				}else{
						responseJson.put(Constants.API_STATUS, Constants.STATUS_FAIL);	
				}
				String errorCode = httpSession.getAttribute(Constants.RESUME_J_ERROR_CODE)==null?"":httpSession.getAttribute(Constants.RESUME_J_ERROR_CODE).toString();
				String errorMsg = httpSession.getAttribute(Constants.RESUME_J_ERROR_MSG)==null?"":httpSession.getAttribute(Constants.RESUME_J_ERROR_MSG).toString();
				responseJson.put(Constants.ERROR_CODE, errorCode);
				responseJson.put(Constants.ERROR_MSG, errorMsg);
			}
			else{
				httpSession.removeAttribute(Constants.TOKEN);
				responseJson.put(Constants.API_STATUS, Constants.STATUS_FAIL);
				responseJson.put("tokenStatus", Constants.INVALID_TOKEN);
				// need to send flag to refresh page
				Utility.loadNewRelicValidation("Token validation fail in resumeJourney GenerateOTP ", "Token validation fail in resumeJourney", "NA",Constants.RESUMEJOURNEYGETOTP);
			}
		}
		}catch (Exception e)
		{
			responseJson.put(Constants.VALIDATION_STATUS, Constants.STATUS_FAIL);
			logger.error(" === Exception in resumeJourney === ", e);
			Utility.loadNewRelicException(e.toString(), Constants.RESUMEJOURNEYGETOTP, "", "NA");
		}
		logger.info(" == Return Response in resumeJourney === " + responseJson);
		encryptedVal = Encryption.getEncryptedVal(responseJson);
		return encryptedVal;
	}



	@PostMapping("/resumeFdcJourneyValidateOtp")
	public String resumeFdcJourneyValidateOtp(@RequestBody String validateOtpReq, HttpServletRequest request) throws JSONException {

		JSONObject validateOtpResJson = new JSONObject();
		String encryptRes = "";
		String requestCustId="";
		try {
			JSONObject validateOtpJson = new JSONObject(validateOtpReq);
			
			requestCustId = validateOtpJson.get("fdcNumber") == null?"": validateOtpJson.get("fdcNumber").toString();
			logger.info(" ===== requestCustId ========= " + requestCustId);
			logger.info(" ==== validateOtpReq in resumeFdcJourneyValidateOtp ==== " + validateOtpReq);
			HttpSession httpSession = request.getSession(false);
			if (httpSession == null) {
                validateOtpResJson.put("apiStatus", "sessionExpired");
            }
            else {
			logger.info(" === session id in resumeFdcJourneyValidateOtp  ===== " + httpSession.getId());
			String validateOtpStatus = ResumeJourneyService.resumeJourneyValidateOtp(validateOtpReq, httpSession, request,Constants.RESUMEJOURNEYVALIDATEOTP);
			logger.info(" === Otp Validation in resumeFdcJourneyValidateOtp === " + validateOtpStatus);
			String userOtp = validateOtpJson.get(Constants.USER_OTP) == null?"NA": validateOtpJson.get(Constants.USER_OTP).toString();
			validateOtpResJson.put(Constants.USER_OTP, userOtp);
			logger.info(" ==== userOtp in resumeFdcJourneyValidateOtp === " + userOtp);
			String otpResponseCode = httpSession.getAttribute(Constants.RESUME_J_ERROR_CODE) == null ? "": httpSession.getAttribute(Constants.RESUME_J_ERROR_CODE).toString();
			String otpResponseMessage = httpSession.getAttribute(Constants.RESUME_J_ERROR_MSG) == null ? "": httpSession.getAttribute(Constants.RESUME_J_ERROR_MSG).toString();
			if (Constants.STATUS_SUCCESS.equalsIgnoreCase(validateOtpStatus)) {
				validateOtpResJson.put(Constants.OTP_VALIDATION_STATUS, Constants.STATUS_SUCCESS);
				String ResumeUserDetails = (String) httpSession.getAttribute("ResumeUserDetails");
				logger.info(" == user details in resumeFdcJourneyValidateOtp=== " + ResumeUserDetails);
				validateOtpResJson.put("ResumeUserDetails", ResumeUserDetails);
			}else if("noDataFoundForuser".equalsIgnoreCase(validateOtpStatus)){
				validateOtpResJson.put(Constants.OTP_VALIDATION_STATUS, "noDataFoundForuser");
			} 
			else if(Constants.STATUS_TRXDONE.equalsIgnoreCase(validateOtpStatus)){
				validateOtpResJson.put(Constants.OTP_VALIDATION_STATUS, Constants.STATUS_TRXDONE);
			}
			else if("beforeTenDaysJourneyFound".equalsIgnoreCase(validateOtpStatus)){
				validateOtpResJson.put(Constants.OTP_VALIDATION_STATUS, "beforeTenDaysJourneyFound");
			}else if("stepNotFound".equalsIgnoreCase(validateOtpStatus)){
				validateOtpResJson.put(Constants.OTP_VALIDATION_STATUS, "stepNotFound");
			} else {
				logger.info(" === session id in  resumeFdcJourneyValidateOtp  ===== " + httpSession.getId());
				validateOtpResJson.put(Constants.OTP_VALIDATION_STATUS, Constants.STATUS_FAIL);
				int otpCount = Integer.parseInt(httpSession.getAttribute(Constants.RESUME_J_OTP_COUNT).toString());
				logger.info(" ==== otpCount in resumeFdcJourneyValidateOtp === " + otpCount);
				if (otpCount > 2) {
					httpSession.removeAttribute(Constants.RESUME_J_OTP_COUNT);
					logger.info(" === Removed Otp count from session resumeJourneyValidateOtp ==== ");
					validateOtpResJson.put("otpLimit", "You Have Exceed Your Limit, Please Try After Sometime");
				}
			}
			validateOtpResJson.put(Constants.OTP_RESPONSE_CODE, otpResponseCode);
			validateOtpResJson.put(Constants.OTP_RESPONSE_MESSAGE, otpResponseMessage);
			logger.info(" === session id in otp resumeFdcJourneyValidateOtp ===== " + httpSession.getId());
		
		}
		} catch (Exception e) {
			if(!( requestCustId.isEmpty())){
				dbManipulation.recordExeption("RESUMEJOURNEY_VALIDATE_OTP","Exception due to internal call", requestCustId);
				}
			validateOtpResJson.put(Constants.OTP_VALIDATION_STATUS, Constants.STATUS_FAIL);
			logger.error(" ===== Exception in resumeFdcJourneyValidateOtp ==== ", e);
			Utility.loadNewRelicException(e.toString(), "resumeFdcJourneyValidateOtp", "", requestCustId);
		}
		logger.info(" == validateOtpResJson in resumeFdcJourneyValidateOtp === " + validateOtpResJson);
		encryptRes = Encryption.getEncryptedVal(validateOtpResJson);
		logger.info(" Encrypted Response resumeJourneyValidateOtp=== " + encryptRes);
		return encryptRes;
	}


	@PostMapping("/resumeregenerateOtp")
	public String resumeRegenerateOtp(@RequestBody String resendOtpJson,HttpServletRequest request, HttpSession httpSession) throws JSONException {

		JSONObject resendOtpRes = new JSONObject();
		String encryptRes = "";
		String requestCustId="";
		try {
			logger.info(" === resendOtpJson in resumeRegenerateOtp ==  " + resendOtpJson);
			JSONObject resendOtp = new JSONObject(resendOtpJson);
			requestCustId = resendOtp.get("fdcNumber") == null?"": resendOtp.get("fdcNumber").toString();
			logger.info(" ===== requestCustId ========= " + requestCustId);
			if (httpSession == null) {
				resendOtpRes.put("apiStatus", "sessionExpired");
            }
            else {
			logger.info(" === session id in resumeRegenerateOtp validator ===== " + httpSession.getId());
			logger.info(" === resendOtpJson in resumeRegenerateOtp ==  " + resendOtpJson);
			long sessionTimeDiff = (System.currentTimeMillis()- Long.parseLong(httpSession.getAttribute(Constants.RESUME_J_OTP_CREATED_TIME).toString())) / 1000;
			logger.info(" ===== Time Difference between send and resend otp ===== " + sessionTimeDiff);
			String otpResponseCode = "";
			String otpResponseMessage = "";
			
			
			String mobileNumber = resendOtp.get(Constants.MOBILE_NUMBER) == null ? "" : resendOtp.get(Constants.MOBILE_NUMBER).toString();
			FixedDepositData depositData = fixedDepositDao.getFixedDepositData(requestCustId);
			String depositDataMobile = depositData.getMobileNumber() == null ? "NA" : depositData.getMobileNumber();
			
			
			logger.info(" === mobileNumber in regenerateOtp ==== " + mobileNumber);
			
			if(depositDataMobile.equalsIgnoreCase(mobileNumber)){
			logger.info(" === Mobile Number in resumeJourneygetOTP === " + mobileNumber);
			
			if (sessionTimeDiff > 60) {
				int generatedOtpCount = (int) httpSession.getAttribute(Constants.RESUME_J_OTP_GENERATE_COUNT);
				logger.info(" ===== Cust resend otp attempt in resend otp resumeRegenerateOtp===== " + generatedOtpCount);
				if (generatedOtpCount < 4) {
					String resendOtpResponse = otpIntegration.generateOtpApi(mobileNumber,request,requestCustId,Constants.RESUMEREGENERATEOTP);
					logger.info(" ==== generatorOtpResponse in resumeRegenerateOtp ==== " + resendOtpResponse);
					JSONObject resendOtpResponseJson = new JSONObject(resendOtpResponse);
					OtpResponse otpResponse = otpService.setOtpResponseService(resendOtpResponse);
					String requestId = otpResponse.getRequestId() == null?"NA":otpResponse.getRequestId();
					if(resendOtpResponseJson.has(Constants.ERROR_CODE) && resendOtpResponseJson.has(Constants.ERROR_MSG)){
						otpResponseCode = resendOtpResponseJson.get(Constants.ERROR_CODE)==null?"":resendOtpResponseJson.get(Constants.ERROR_CODE).toString();
						otpResponseMessage = resendOtpResponseJson.get(Constants.ERROR_MSG)==null?"":resendOtpResponseJson.get(Constants.ERROR_MSG).toString();
						logger.info(" ===== resumeRegenerateOtp otpResponseCode ==== " + otpResponseCode + " === otpResponseMessage ==== " + otpResponseMessage);
						if(otpResponseCode.equalsIgnoreCase("00")){
							logger.info(" === Otp send Successfully resumeRegenerateOtp=== ");
							resendOtpRes.put(Constants.MOBILE_NUMBER, mobileNumber);
							resendOtpRes.put(Constants.API_STATUS, Constants.STATUS_SUCCESS);
							httpSession.setAttribute(Constants.RESUME_J_OTP_CREATED_TIME, System.currentTimeMillis());
							httpSession.setAttribute(Constants.RESUME_J_OTP_GENERATE_COUNT, generatedOtpCount + 1);
						}else{
							logger.info(" === Otp send Failed resumeRegenerateOtp === ");
							resendOtpRes.put(Constants.API_STATUS, Constants.STATUS_FAIL);
							Utility.loadNewRelicException("", "generateOTP failure", "", requestCustId);
						}
					}
					resendOtpRes.put(Constants.ERROR_CODE, otpResponseCode);
					resendOtpRes.put(Constants.ERROR_MSG, otpResponseMessage);
					httpSession.setAttribute(Constants.RESUME_J_OTP_REQUEST_ID, requestId);
				} else {
					resendOtpRes.put(Constants.OTP_RESEND_TIME,"You have click Resent OTP for 3 consecutive times. Redirecting to Application form");
					Utility.loadNewRelicValidation("regenerateOtp count limit exceeds in resumeRegenerateOtp ", "regenerateOtp count limit exceeds in resumeRegenerateOtp ", requestCustId,Constants.RESUMEREGENERATEOTP);
				}
			} else {
				resendOtpRes.put(Constants.OTP_RESEND_TIME, "Wait for 60 sec");
			}
			}else{
				logger.info(" === Otp send failed in resumeRegenerateOtp due to invalide mobile no === ");
				resendOtpRes.put(Constants.API_STATUS, Constants.STATUS_FAIL);
				Utility.loadNewRelicValidation("mobile number not match in resumeRegenerateOtp ", "mobile number not match in resumeRegenerateOtp ", requestCustId,Constants.RESUMEREGENERATEOTP);
			}
			logger.info(" ==== resendOtpRes in resumeRegenerateOtp ==== " + resendOtpRes);
		}
		} catch (Exception e) {
			if(!( requestCustId.isEmpty())){
				dbManipulation.recordExeption("RESUMEJOURNEY_RESEND_OTP","Exception due to internal call", requestCustId);
				}
			resendOtpRes.put(Constants.API_STATUS, Constants.STATUS_FAIL);
			logger.error(" ===== Exception in resumeRegenerateOtp ==== ", e);
			Utility.loadNewRelicException(e.toString(), Constants.RESUMEREGENERATEOTP, "", requestCustId);
		}
		encryptRes = Encryption.getEncryptedVal(resendOtpRes);
		logger.info(" Encrypted Response resumeRegenerateOtp=== " + encryptRes);
		return encryptRes;
	}
}
