package com.bajaj.fixeddeposit.controller;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bajaj.fixeddeposit.api.OtpIntegration;
import com.bajaj.fixeddeposit.dao.FixedDepositDao;
import com.bajaj.fixeddeposit.model.FixedDepositData;
import com.bajaj.fixeddeposit.model.OtpResponse;
import com.bajaj.fixeddeposit.service.OtpService;
import com.bajaj.fixeddeposit.util.Constants;
import com.bajaj.fixeddeposit.util.DbManipulationUtil;
import com.bajaj.fixeddeposit.util.Encryption;
import com.bajaj.fixeddeposit.util.Utility;
import com.bajaj.fixeddeposit.validation.FormFieldValidation;
import com.newrelic.api.agent.Trace;


@RestController
public class OtpController {
	
	private static final Logger logger = Logger.getLogger(OtpController.class);
	
	@Autowired
	private OtpService otpService; 
	
	@Autowired
	private FormFieldValidation formFieldValidation;
	
	@Autowired
	private OtpIntegration otpIntegration;  
	
	@Autowired
	private DbManipulationUtil dbManipulation;
	
	@Autowired
	private FixedDepositDao fixedDepositDao;
	
	
	// Generating Token
	@GetMapping( value = {"/getajaxFdGenerateToken", "/generateToken"})
	public String tokenGenerator(HttpServletRequest request, HttpServletResponse response) {

		String sessionToken = "";
		try {
			HttpSession httpSession = request.getSession(true);
			logger.info(" === httpSession in tokenGenerator == " + httpSession);

			httpSession.invalidate();
			logger.info(" ==== Old Session Destroyed in tokenGenerator ==  ");
			httpSession = request.getSession(true);
			logger.info(" ==== New Session Created in tokenGenerator ==  " + httpSession.getId());

			long timeMilli = new Date().getTime();

			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Max-Age", 0);
			response.setHeader("SET-COOKIE", "JSESSIONID=" + httpSession.getId() + "; HttpOnly");

			httpSession.setMaxInactiveInterval(30 * 60);

			sessionToken = UUID.randomUUID().toString();
			httpSession.setAttribute(Constants.TOKEN, sessionToken);
			logger.info(" === Generated Session Token === " + sessionToken);

			Cookie tokenCookie = new Cookie(Constants.FD_TOKEN_VERIFY, sessionToken + ":" + timeMilli);
			tokenCookie.setPath("/");
			response.addCookie(tokenCookie);
			httpSession.setAttribute(Constants.FD_TOKEN_UNIQUE, sessionToken);

			return sessionToken;
		}
		catch (Exception e) {
			Utility.loadNewRelicException(e.toString(), "tokenGenerator", "NA", "NA");
			logger.info("=======exception in tokenGenerator=======");
		}
		return sessionToken;
	}
	
	@PostMapping("/generateOtp")
	public String optGenerator(@RequestBody String generatorOtpReq, @RequestHeader("X-CSRF-Token") String csrfToken, HttpServletRequest request) throws JSONException{
		
		JSONObject validationStatus = new JSONObject();
		String encryptRes = "";
		String customerId="";
		try {
			HttpSession session = request.getSession(false);
			if (session == null) {
                validationStatus.put("apiStatus", "sessionExpired");
            }
            else {
			logger.info(" ==== old session in optGenerator === " + session.getId());
			
			logger.info(" ==== Token from Front End ==== " + csrfToken);
			logger.info(" ==== Generate Otp Request ==== " + generatorOtpReq);
			
			String tokenValidationRes = formFieldValidation.tokenValidation(csrfToken, session);
			logger.info(" ==== Token Validation Response in optGenerator == " + tokenValidationRes);
			
			if(Constants.STATUS_SUCCESS.equalsIgnoreCase(tokenValidationRes)){
				session.removeAttribute(Constants.TOKEN);
				logger.info(" === Token Validated Successfully in optGenerator and removed token from session === ");
				
				String generatorOtpResponse = otpService.otpGeneratorService(generatorOtpReq, request, session);
				logger.info(" ==== generatorOtpResponse in optGenerator ==== " + generatorOtpResponse);
				
				if(Constants.STATUS_SUCCESS.equalsIgnoreCase(generatorOtpResponse)){
					logger.info(" === Otp Send Successfully ==== ");
					validationStatus.put(Constants.API_STATUS, Constants.STATUS_SUCCESS);
					
					JSONObject object=new JSONObject(generatorOtpReq);
					String mobileNo =object.has("mobileNumber")? object.optString("mobileNumber") : "NA";
					String whatsAppCheck=object.has("whatsAppCheck")? object.optString("whatsAppCheck") : "NA";
					
					if(whatsAppCheck.equalsIgnoreCase("Yes") && !"NA".equalsIgnoreCase(mobileNo))
					{validationStatus.put("jwtToken", Utility.generateJWTToken(mobileNo));}

					
					synchronized(this){
					customerId = otpService.savePartialDataService(generatorOtpReq);
					logger.info(" == customerId in OtpController == " + customerId);
					}
					
					session.setAttribute("customerId", customerId);
					validationStatus.put(Constants.CUSTOMER_ID, customerId);
					String eventHubStatus = otpService.eventHubOtpCall(customerId, Constants.GENERATEOTP);
					logger.info(" === eventHubStatus in optGenerator === " + eventHubStatus);
					
				}else{
					logger.info(" ==== OTP Sending fail === ");
					validationStatus.put(Constants.API_STATUS, Constants.STATUS_FAIL);
					
				}
				
				String errorCode = session.getAttribute(Constants.ERROR_CODE)==null?"":session.getAttribute(Constants.ERROR_CODE).toString();
				String errorMsg = session.getAttribute(Constants.ERROR_MSG)==null?"":session.getAttribute(Constants.ERROR_MSG).toString();
				validationStatus.put(Constants.ERROR_CODE, errorCode);
				validationStatus.put(Constants.ERROR_MSG, errorMsg);
				
			}
			else{
				session.removeAttribute(Constants.TOKEN);
				validationStatus.put(Constants.API_STATUS, Constants.STATUS_FAIL);
				validationStatus.put("tokenStatus", Constants.INVALID_TOKEN);
				// need to send flag to refresh page
				Utility.loadNewRelicValidation("Token Validation fail in generateOtp", "Token Validation in GenerateOTP","NA",Constants.GENERATEOTP);
				
			}
			
			logger.info(" ==== validationStatus in optGenerator ==== " + validationStatus);
			
		}} catch (Exception e) {
			if(!(customerId.isEmpty())){
				dbManipulation.recordExeption("GETOTP","Exception due to internal call" ,customerId);
				}
			validationStatus.put(Constants.API_STATUS, Constants.STATUS_FAIL);
			Utility.loadNewRelicException(e.toString(), Constants.GENERATEOTP, "", "NA");
			logger.error(" ==== Exception in optGenerator ==== ", e);
		}
		logger.info("  Response  === " + validationStatus);
		encryptRes = Encryption.getEncryptedVal(validationStatus);
		logger.info(" Encrypted Response  === " + encryptRes);
		
		return encryptRes;
	}
	
	@PostMapping("/validateOtp")
	public String otpValidator(@RequestBody String validateOtpReq, HttpServletRequest request) throws JSONException {

		JSONObject validateOtpResJson = new JSONObject();
		String encryptRes = "";
		String customerId ="";
		try {
			logger.info(" ==== validateOtpReq in otpValidator ==== " + validateOtpReq);
			JSONObject validateOtpJson = new JSONObject(validateOtpReq);
			 customerId = validateOtpJson.has("fdcNumber") ? validateOtpJson.get("fdcNumber").toString():"";
			 String mobileNumber = validateOtpJson.has("mobileNumber")?validateOtpJson.optString("mobileNumber") : "NA";
			 logger.info(" ========== requestCustId  ========= " + customerId);
			
			HttpSession httpSession = request.getSession(false);
			if (httpSession == null) {
                validateOtpResJson.put("apiStatus", "sessionExpired");
            }
            else {
			logger.info(" === session id in otp validator  ===== " + httpSession.getId());
			
			customerId = (String) httpSession.getAttribute(Constants.CUSTOMER_ID);
			logger.info(" === customerId in ntbRequest ==== " + customerId);
			
			String validateOtpStatus = otpService.otpValidatorService(validateOtpReq, httpSession, request);
			logger.info(" === Otp Validation in otpValidator === " + validateOtpStatus);
			
		

			String userOtp = validateOtpJson.get(Constants.USER_OTP) == null?"NA": validateOtpJson.get(Constants.USER_OTP).toString();
			validateOtpResJson.put(Constants.USER_OTP, userOtp);
			logger.info(" ==== userOtp in otpValidator === " + userOtp);
			
			String otpResponseCode = httpSession.getAttribute(Constants.ERROR_CODE) == null ? "": httpSession.getAttribute(Constants.ERROR_CODE).toString();
			String otpResponseMessage = httpSession.getAttribute(Constants.ERROR_MSG) == null ? "": httpSession.getAttribute(Constants.ERROR_MSG).toString();

			if (Constants.STATUS_SUCCESS.equalsIgnoreCase(validateOtpStatus)) {

				validateOtpResJson.put(Constants.OTP_VALIDATION_STATUS, Constants.STATUS_SUCCESS);
				String dedupeCustType = httpSession.getAttribute("dedupeCustType") == null ? "NTB" :  httpSession.getAttribute("dedupeCustType").toString();
				logger.info(" ===== Cust Type in OtpValidator === " + dedupeCustType);
				
				if(Constants.DEDUPE_ETB.equalsIgnoreCase(dedupeCustType)){
					String userDetails = (String) httpSession.getAttribute("userDetails");
					logger.info(" =====user details === " + userDetails);
					validateOtpResJson.put("userDetails", userDetails);
					validateOtpResJson.put(Constants.DEDUPE_CUST_TYPE, dedupeCustType);
					
				}else{
					validateOtpResJson.put(Constants.DEDUPE_CUST_TYPE, dedupeCustType);
					validateOtpResJson.put("city", httpSession.getAttribute("city"));
					validateOtpResJson.put("mobileNumber", mobileNumber);
				}
				
				
			} else {
				logger.info(" === session id in otp validator  ===== " + httpSession.getId());
				validateOtpResJson.put(Constants.DEDUPE_CUST_TYPE, Constants.DEDUPE_NTB);
				validateOtpResJson.put(Constants.OTP_VALIDATION_STATUS, Constants.STATUS_FAIL);
				int otpCount = Integer.parseInt(httpSession.getAttribute(Constants.OTP_COUNT)==null? "0":httpSession.getAttribute(Constants.OTP_COUNT).toString());
				logger.info(" ==== otpCount in otpValidator === " + otpCount);
				

				if (otpCount > 2) {
					httpSession.removeAttribute(Constants.OTP_COUNT);
					logger.info(" === Removed Otp count from session ==== ");
					validateOtpResJson.put("otpLimit", "You Have Exceed Your Limit, Please Try After Sometime");
				}

			}

			validateOtpResJson.put(Constants.OTP_RESPONSE_CODE, otpResponseCode);
			validateOtpResJson.put(Constants.OTP_RESPONSE_MESSAGE, otpResponseMessage);

			logger.info(" === session id in otp validator ===== " + httpSession.getId());
			
			String eventHubStatus = otpService.eventHubOtpCall(customerId, Constants.VALIDATEOTP);
			logger.info(" === eventHubStatus in optGenerator === " + eventHubStatus);

		}
		} catch (Exception e) {
			if(!(customerId.isEmpty())){
				dbManipulation.recordExeption("VALIDATEOTP","Exception due to internal call", customerId);
				}
			validateOtpResJson.put(Constants.DEDUPE_CUST_TYPE, Constants.DEDUPE_NTB);
			validateOtpResJson.put(Constants.OTP_VALIDATION_STATUS, Constants.STATUS_FAIL);
			Utility.loadNewRelicException(e.toString(), Constants.VALIDATEOTP, "", customerId);
			logger.error(" ===== Exception in otpValidator ==== ", e);
		}
		
		logger.info(" == validateOtpResJson in otpValidator === " + validateOtpResJson);
		encryptRes = Encryption.getEncryptedVal(validateOtpResJson);
		logger.info(" Encrypted Response === " + encryptRes);
		
		return encryptRes;
	}
	
	@PostMapping("/regenerateOtp")
	public String regenerateOtp(@RequestBody String resendOtpJson,HttpServletRequest request,HttpSession httpSession) throws JSONException {

		JSONObject resendOtpRes = new JSONObject();
		String encryptRes = "";
		String requestCustId ="";
		try {
			JSONObject resendOtp = new JSONObject(resendOtpJson);
			requestCustId = resendOtp.has("fdcNumber")?resendOtp.get("fdcNumber").toString():"";
			logger.info(" ===== requestCustId ========= " + requestCustId);
			logger.info(" === resendOtpJson in regenerateOtp ==  " + resendOtpJson);
			
			if (httpSession == null) {
				resendOtpRes.put("apiStatus", "sessionExpired");
            }
            else {
			logger.info(" === session id in otp validator ===== " + httpSession.getId());
			logger.info(" === resendOtpJson in regenerateOtp ==  " + resendOtpJson);
			long sessionTimeDiff = (System.currentTimeMillis()- Long.parseLong(httpSession.getAttribute(Constants.OTP_CREATED_TIME).toString())) / 1000;
			logger.info(" ===== Time Difference between send and resend otp ===== " + sessionTimeDiff);
			
			String otpResponseCode = "";
			String otpResponseMessage = "";
			
			String mobileNumber = resendOtp.get(Constants.MOBILE_NUMBER) == null ? "" : resendOtp.get(Constants.MOBILE_NUMBER).toString();
			FixedDepositData depositData = fixedDepositDao.getFixedDepositData(requestCustId);
			String depositDataMobile = depositData.getMobileNumber() == null ? "NA" : depositData.getMobileNumber();
			
			if(depositDataMobile.equalsIgnoreCase(mobileNumber)){
			logger.info(" === mobileNumber in regenerateOtp ==== " + mobileNumber);
			
			if (sessionTimeDiff > 60) {

				int generatedOtpCount = (int) httpSession.getAttribute(Constants.OTP_GENERATE_COUNT);
				logger.info(" ===== Cust resend otp attempt in resend otp ===== " + generatedOtpCount);

				if (generatedOtpCount < 4) {

					String resendOtpResponse = otpIntegration.generateOtpApi(mobileNumber, request,requestCustId,Constants.REGENERTEOTP);
				
					logger.info(" ==== generatorOtpResponse in optGenerator ==== " + resendOtpResponse);
					
					JSONObject resendOtpResponseJson = new JSONObject(resendOtpResponse);
					OtpResponse otpResponse = otpService.setOtpResponseService(resendOtpResponse);
					String requestId = otpResponse.getRequestId() == null?"NA":otpResponse.getRequestId();
					
					if(resendOtpResponseJson.has(Constants.ERROR_CODE) && resendOtpResponseJson.has(Constants.ERROR_MSG)){
						
						otpResponseCode = resendOtpResponseJson.get(Constants.ERROR_CODE)==null?"":resendOtpResponseJson.get(Constants.ERROR_CODE).toString();
						otpResponseMessage = resendOtpResponseJson.get(Constants.ERROR_MSG)==null?"":resendOtpResponseJson.get(Constants.ERROR_MSG).toString();
						logger.info(" =====  otpResponseCode ==== " + otpResponseCode + " === otpResponseMessage ==== " + otpResponseMessage);
						
						if(otpResponseCode.equalsIgnoreCase("00")){
							logger.info(" === Otp send Successfully === ");
							
							resendOtpRes.put(Constants.API_STATUS, Constants.STATUS_SUCCESS);
							httpSession.setAttribute(Constants.OTP_CREATED_TIME, System.currentTimeMillis());
							httpSession.setAttribute(Constants.OTP_GENERATE_COUNT, generatedOtpCount + 1);
						}else{
							logger.info(" === Otp send Failed === ");
							resendOtpRes.put(Constants.API_STATUS, Constants.STATUS_FAIL);
							Utility.loadNewRelicException("", "generateOtp failure", "", requestCustId);
						}
								
					}
					
					resendOtpRes.put(Constants.ERROR_CODE, otpResponseCode);
					resendOtpRes.put(Constants.ERROR_MSG, otpResponseMessage);
					httpSession.setAttribute(Constants.OTP_REQUEST_ID, requestId);
				} else {
					resendOtpRes.put(Constants.OTP_RESEND_TIME,
							"You have click Resent OTP for 3 consecutive times. Redirecting to Application form");
					Utility.loadNewRelicValidation("regenerateOtp count limit exceeds in regenerateOtp ", "regenerateOtp count limit exceeds in regenerateOtp ", requestCustId,Constants.REGENERTEOTP);
				}
			} else {
				resendOtpRes.put(Constants.OTP_RESEND_TIME, "Wait for 60 sec");
			}
			}else{
				logger.info(" === Otp send failed due to invalide mobile no === ");
				resendOtpRes.put(Constants.API_STATUS, Constants.STATUS_FAIL);
				Utility.loadNewRelicValidation("Mobile number not match for regenerateOtp ", "mobile number not match for regenerateOtp", requestCustId,Constants.REGENERTEOTP);
			}
			logger.info(" ==== resendOtpRes in regenerateOtp ==== " + resendOtpRes);
            }} catch (Exception e) {
            	if(!(requestCustId.isEmpty())){
    				dbManipulation.recordExeption("RESENDOTP","Exception due to internal call", requestCustId);
    				}
			resendOtpRes.put(Constants.API_STATUS, Constants.STATUS_FAIL);
			logger.error(" ===== Exception in regenerateOtp ==== ", e);
			Utility.loadNewRelicException(e.toString(), Constants.REGENERTEOTP, "", requestCustId);
		}

		encryptRes = Encryption.getEncryptedVal(resendOtpRes);
		logger.info(" Encrypted Response === " + encryptRes);
		
		return encryptRes;

	}
	

	@PostMapping("/validateOtpForFDSLF")
	public String otpValidatorFDSLF(@RequestBody String validateOtpReq, HttpServletRequest request) throws JSONException {

		JSONObject validateOtpResJson = new JSONObject();
		String encryptRes = "";
		String customerId ="";
		String requestCustId="";
		
		try {
			logger.info(" ==== validateOtpReq in otpValidator ==== " + validateOtpReq);
			JSONObject validateOtpJson = new JSONObject(validateOtpReq);
			requestCustId = validateOtpJson.has("fdcNumber") ? validateOtpJson.get("fdcNumber").toString():"";
			logger.info(" ===== requestCustId ========= " + requestCustId);
			HttpSession httpSession = request.getSession(false);
			if (httpSession == null) {
                validateOtpResJson.put("apiStatus", "sessionExpired");
            }
            else {
			logger.info(" === session id in otp validator  ===== " + httpSession.getId());
			
			customerId = (String) httpSession.getAttribute(Constants.CUSTOMER_ID);
			logger.info(" === customerId in ntbRequest ==== " + customerId);
			
			String validateOtpStatus = otpService.otpValidatorServiceFDSLF(validateOtpReq, httpSession, request);
			logger.info(" === Otp Validation in otpValidator === " + validateOtpStatus);
			
			String userOtp = validateOtpJson.get(Constants.USER_OTP) == null?"NA": validateOtpJson.get(Constants.USER_OTP).toString();
			validateOtpResJson.put(Constants.USER_OTP, userOtp);
			logger.info(" ==== userOtp in otpValidator === " + userOtp);
			
			String otpResponseCode = httpSession.getAttribute(Constants.ERROR_CODE) == null ? ""
					: httpSession.getAttribute(Constants.ERROR_CODE).toString();
			String otpResponseMessage = httpSession.getAttribute(Constants.ERROR_MSG) == null ? ""
					: httpSession.getAttribute(Constants.ERROR_MSG).toString();

			if (Constants.STATUS_SUCCESS.equalsIgnoreCase(validateOtpStatus)) {
				
				httpSession.setAttribute("fdslID", "fdslSucess");
				httpSession.setAttribute("investmentType", "FD");
				httpSession.setAttribute("okycstatus", "okycstatusFailForFDSL");

				validateOtpResJson.put(Constants.OTP_VALIDATION_STATUS, Constants.STATUS_SUCCESS);
				String dedupeCustType = httpSession.getAttribute("dedupeCustType") == null ? "NTB" :  httpSession.getAttribute("dedupeCustType").toString();
				httpSession.setAttribute("fdDedupeCustType", dedupeCustType);
				
				logger.info(" ===== Cust Type in OtpValidator === " + dedupeCustType);
				
				if(Constants.DEDUPE_ETB.equalsIgnoreCase(dedupeCustType)){
					
					validateOtpResJson.put(Constants.DEDUPE_CUST_TYPE, dedupeCustType);
					String userDetails = (String) httpSession.getAttribute("userDetails");
					logger.info(" == Cust is ETB and user details === " + userDetails);
					validateOtpResJson.put("userDetails", userDetails);
					
				}else{
					validateOtpResJson.put(Constants.DEDUPE_CUST_TYPE, dedupeCustType);
				}
				
			} else {
				logger.info(" === session id in otp validator  ===== " + httpSession.getId());
				validateOtpResJson.put(Constants.DEDUPE_CUST_TYPE, Constants.DEDUPE_NTB);
				validateOtpResJson.put(Constants.OTP_VALIDATION_STATUS, Constants.STATUS_FAIL);
				int otpCount = Integer.parseInt(httpSession.getAttribute(Constants.OTP_COUNT).toString());
				logger.info(" ==== otpCount in otpValidator === " + otpCount);
				

				if (otpCount > 2) {
					httpSession.removeAttribute(Constants.OTP_COUNT);
					logger.info(" === Removed Otp count from session ==== ");
					validateOtpResJson.put("otpLimit", "You Have Exceed Your Limit, Please Try After Sometime");
				}

			}

			validateOtpResJson.put(Constants.OTP_RESPONSE_CODE, otpResponseCode);
			validateOtpResJson.put(Constants.OTP_RESPONSE_MESSAGE, otpResponseMessage);

			logger.info(" === session id in otp validator ===== " + httpSession.getId());
			
			String eventHubStatus = otpService.eventHubOtpCall(customerId, "ValidateOtp");
			logger.info(" === eventHubStatus in optGenerator === " + eventHubStatus);
		}
		
		} catch (Exception e) {
			if(!( requestCustId.isEmpty())){
				dbManipulation.recordExeption("FDSLF-VALIDATE","Exception due to internal call", requestCustId);
				}
			validateOtpResJson.put(Constants.DEDUPE_CUST_TYPE, Constants.DEDUPE_NTB);
			validateOtpResJson.put(Constants.OTP_VALIDATION_STATUS, Constants.STATUS_FAIL);
			logger.error(" ===== Exception in otpValidator ==== ", e);
		}
		
		logger.info(" == validateOtpResJson in otpValidator === " + validateOtpResJson);
		encryptRes = Encryption.getEncryptedVal(validateOtpResJson);
		logger.info(" Encrypted Response === " + encryptRes);
		
		return encryptRes;
	}
	
	@Trace
	@PostMapping("/pincodeValidator")
	public String pincodeVerification(@RequestBody String pinCodeJson){

		logger.info(" ==== pincode in pincodeVerification ==== " + pinCodeJson);
		String  pinStatus = "";
		JSONObject pincodeStatusJson = new JSONObject();

		try {

			JSONObject pincodeJson = new JSONObject(pinCodeJson);
			String pincode = pincodeJson.get("pincode").toString();
			logger.info(" == pincode === " + pincode);

			if(pincode != null){
				String pincodeStatus = formFieldValidation.pincodeValidator(pincode);
				logger.info(" === pincodeStatus in pincodeVerification === " + pincodeStatus);

				if(Constants.STATUS_SUCCESS.equalsIgnoreCase(pincodeStatus)){

					logger.info(" === pincode field validation successfully === ");

					pinStatus = otpService.pincodeValidatorService(pincode);
					pincodeStatusJson.put(Constants.STATUS, pinStatus);
					logger.info(" === pinStatus in pincodeVerification === " + pinStatus);
				}else{
					pincodeStatusJson.put(Constants.STATUS, Constants.STATUS_FAIL);
					logger.info(" === pincode validation failed === ");
					Utility.loadNewRelicValidation("Pincode field validation failed ", "Pincode field validation failed", "NA",Constants.PINCODE);
				}
			}else{
				pincodeStatusJson.put(Constants.STATUS, Constants.STATUS_FAIL);
				pincodeStatusJson.put(Constants.VALIDATION_STATUS, "please enter pincode");
				logger.info(" === pincode validation failed === ");
			}

		} catch (Exception e) {
			logger.error(" ===== Exception in ifscCodeVerification === ", e);
			Utility.loadNewRelicException(e.toString(), Constants.PINCODE, "", "NA");
		}

		return pincodeStatusJson.toString();
	}

}
