package com.bajaj.fixeddeposit.validation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

import com.bajaj.fixeddeposit.dao.FixedDepositDao;
import com.bajaj.fixeddeposit.model.IfscCode;
import com.bajaj.fixeddeposit.model.JsonResponse;
import com.bajaj.fixeddeposit.util.Constants;
import com.bajaj.fixeddeposit.util.DbManipulationUtil;
import com.bajaj.fixeddeposit.util.Utility;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newrelic.api.agent.NewRelic;

@Component
public class FormFieldValidation {

	private static final Logger logger = Logger.getLogger(FormFieldValidation.class);

	private FormFieldValidation() {
	}
	@Autowired
	private FixedDepositDao fixedDepositDao;
	
	@Autowired
	private DbManipulationUtil dbManipulation;  
	
	// validating OTP related fields
	public JsonResponse otpFieldsValidation(String reqJson) {

		JSONObject validationError = new JSONObject();
		JSONObject validation = new JSONObject();
		JsonResponse response = new JsonResponse();

		String mobile = "^[56789]\\d{9}$";
		String date = "\\d{2}/\\d{2}/\\d{4}";
		boolean flag = true;
		String userOtp= "\\d{6}";
		String pincode = "\\d{6}";
		
		try {
			@SuppressWarnings("unchecked")
			LinkedHashMap<String, Object> result = new ObjectMapper().readValue(reqJson, LinkedHashMap.class);
			for (Entry<String, Object> results : result.entrySet())
			{

				String key = results.getKey();
				String value = (String) results.getValue();

				logger.info(" == Key === " + key + " == Value == " + value);
				if (key.equals(Constants.MOBILE_NUMBER)) {

					if (!value.isEmpty() && value.matches(mobile) && value.equalsIgnoreCase(HtmlUtils.htmlEscape(value)))
					{
						validation.put(Constants.MOBILE_NUMBER, "Mobile Number Validation Successfull");
					} else 
					{
						flag = false;
						validationError.put(Constants.MOBILE_NUMBER, "Please Enter valid mobile number");
					}
				}
				
				if (key.equals(Constants.DATE_OF_BIRTH)) 
				{

					if (!value.isEmpty() && value.matches(date)) 
					{
						validation.put(Constants.DATE_OF_BIRTH, "Date of Birth Validation Successfull");
						} else {
							flag = false;
							validationError.put(Constants.DATE_OF_BIRTH, "Please Enter valid date of birth");
						}
				}
				
				if (key.equals(Constants.CUSTOMER_ID)) 
				{

					if (!value.isEmpty()) 
					{
						validation.put(Constants.CUSTOMER_ID, "CUSTOMER_ID Validation Successfull");
						} else 
						{
							flag = false;
						validationError.put(Constants.CUSTOMER_ID, "Please Enter valid CUSTOMER_ID");
						}
				}
				
				if (key.equals("fdslf_pincode")) {

					if (!value.isEmpty() && value.matches(pincode)) {

						logger.info("  == pincode validate Successfully === ");
					} else {

						flag = false;
						logger.error(key + "please enter valid pincode");
						validationError.put(key, "please enter valid pincode");
					}
				}
				
				if (key.equals("fdslf_fullname") || key.equals("fdslf_fullname")) {

					if (!value.equalsIgnoreCase(HtmlUtils.htmlEscape(value))) {

						
						flag = false;
						logger.error(key + "please enter valid fullName");
						validationError.put(key, "please enter valid fullName");
					} else {
						logger.info("  == fullName validate Successfully === ");
						
					}
				}

				
				if (key.equals("otpSource") || key.equals("otpSource")) {

					if (!value.equalsIgnoreCase(HtmlUtils.htmlEscape(value))) {

						
						flag = false;
						logger.error(key + "please enter valid otpSource");
						validationError.put(key, "please enter valid otpSource");
					} else {
						logger.info("  == otpSource validate Successfully === ");
						
					}
				}
				
				if (key.equals("utm_source_utmTrue") || key.equals("utm_source_utmTrue")) {

					if (!value.equalsIgnoreCase(HtmlUtils.htmlEscape(value))) {

						
						flag = false;
						logger.error(key + "please enter valid utm_source_utmTrue");
						validationError.put(key, "please enter valid utm_source_utmTrue");
					} else {
						logger.info("  == utm_source_utmTrue validate Successfully === ");
						
					}
				}
				
				
				if (key.equals("utm_medium_utmTrue") || key.equals("utm_medium_utmTrue")) {

					if (!value.equalsIgnoreCase(HtmlUtils.htmlEscape(value))) {

						
						flag = false;
						logger.error(key + "please enter valid utm_medium_utmTrue");
						validationError.put(key, "please enter valid utm_medium_utmTrue");
					} else {
						logger.info("  == utm_medium_utmTrue validate Successfully === ");
						
					}
				}
				
				
				if (key.equals("utm_campaign_utmTrue") || key.equals("utm_campaign_utmTrue")) {

					if (!value.equalsIgnoreCase(HtmlUtils.htmlEscape(value))) {

						
						flag = false;
						logger.error(key + "please enter valid utm_campaign_utmTrue");
						validationError.put(key, "please enter valid utm_campaign_utmTrue");
					} else {
						logger.info("  == utm_campaign_utmTrue validate Successfully === ");
						
					}
				}
				
				if (key.equals("Client_ID") || key.equals("Client_ID")) {

					if (!value.equalsIgnoreCase(HtmlUtils.htmlEscape(value))) {

						
						flag = false;
						logger.error(key + "please enter valid Client_ID");
						validationError.put(key, "please enter valid Client_ID");
					} else {
						logger.info("  == Client_ID validate Successfully === ");
						
					}
				}
				
				if (key.equals("createon") || key.equals("createon")) {

					if (!value.equalsIgnoreCase(HtmlUtils.htmlEscape(value))) {

						
						flag = false;
						logger.error(key + "please enter valid createon");
						validationError.put(key, "please enter valid createon");
					} else {
						logger.info("  == createon validate Successfully === ");
						
					}
				}
				
				
				if (key.equals("device") || key.equals("device")) {

					if (!value.equalsIgnoreCase(HtmlUtils.htmlEscape(value))) {

						
						flag = false;
						logger.error(key + "please enter valid device");
						validationError.put(key, "please enter valid device");
					} else {
						logger.info("  == device validate Successfully === ");
						
					}
				}
				
				if (key.equals("last_clicked_clicktrue") || key.equals("last_clicked_clicktrue")) {

					if (!value.equalsIgnoreCase(HtmlUtils.htmlEscape(value))) {

						
						flag = false;
						logger.error(key + "please enter valid last_clicked_clicktrue");
						validationError.put(key, "please enter valid last_clicked_clicktrue");
					} else {
						logger.info("  == last_clicked_clicktrue validate Successfully === ");
						
					}
				}
				
				if (key.equals("pageName_form") || key.equals("pageName_form")) {

					if (!value.equalsIgnoreCase(HtmlUtils.htmlEscape(value))) {

						
						flag = false;
						logger.error(key + "please enter valid pageName_form");
						validationError.put(key, "please enter valid pageName_form");
					} else {
						logger.info("  == pageName_form validate Successfully === ");
						
					}
				}
				
				if (key.equals("formname_form") || key.equals("formname_form")) {

					if (!value.equalsIgnoreCase(HtmlUtils.htmlEscape(value))) {

						
						flag = false;
						logger.error(key + "please enter valid formname_form");
						validationError.put(key, "please enter valid formname_form");
					} else {
						logger.info("  == formname_form validate Successfully === ");
						
					}
				}
				
				
				if (key.equals("partnerCode") || key.equals("partnerCode")) {

					if (!value.equalsIgnoreCase(HtmlUtils.htmlEscape(value))) {

						
						flag = false;
						logger.error(key + "please enter valid partnerCode");
						validationError.put(key, "please enter valid partnerCode");
					} else {
						logger.info("  == partnerCode validate Successfully === ");
						
					}
				}
				
				
				if (key.equals("partnerName") || key.equals("partnerName")) {

					if (!value.equalsIgnoreCase(HtmlUtils.htmlEscape(value))) {

						
						flag = false;
						logger.error(key + "please enter valid partnerName");
						validationError.put(key, "please enter valid partnerName");
					} else {
						logger.info("  == partnerName validate Successfully === ");
						
					}
				}
				
				
				if (key.equals("form_Id") || key.equals("form_Id")) {

					if (!value.equalsIgnoreCase(HtmlUtils.htmlEscape(value))) {

						
						flag = false;
						logger.error(key + "please enter valid form_Id");
						validationError.put(key, "please enter valid form_Id");
					} else {
						logger.info("  == form_Id validate Successfully === ");
						
					}
				}
				
				if (key.equals("gaID") || key.equals("gaID")) {

					if (!value.equalsIgnoreCase(HtmlUtils.htmlEscape(value))) {

						
						flag = false;
						logger.error(key + "please enter valid gaID");
						validationError.put(key, "please enter valid gaID");
					} else {
						logger.info("  == gaID validate Successfully === ");
						
					}
				}
				
				if (key.equals("userOtp")) 
				{

					if (!value.isEmpty() && value.matches(userOtp)) 
					{
						validation.put("userOtp", "userOtp Validation Successfull");
						} else 
						{
							flag = false;
						validationError.put("userOtp", "Please Enter valid userOtp");
						}
				}
				
			}
			

		} catch (Exception e) {
			logger.error("--- Exception in preApprovedValidation ------", e);
			response.setResult(Constants.STATUS_FAIL);
		} finally {
			if (flag) {
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setResult(validation);
			} else {
				response.setStatus(Constants.STATUS_FAIL);
				response.setResult(validationError);
			}
		}
		return response;

	}

	// validating token for send OTP
	public String tokenValidation(String csrfToken, HttpSession httpSession) {

		String sessionToken = httpSession.getAttribute(Constants.TOKEN) == null ? "NA"
				: (String) httpSession.getAttribute(Constants.TOKEN);
		String tokenValidationStatus = "";

		logger.info(" ::: sessionToken ::: " + sessionToken + " === Token From Front End === " + csrfToken);

		if (sessionToken.equalsIgnoreCase(csrfToken)) {
			tokenValidationStatus = Constants.STATUS_SUCCESS;
			logger.info(" === Token Validated Successfully === ");
		} else {
			tokenValidationStatus = Constants.STATUS_FAIL;
			logger.info(" === Token Validation Failed === ");
			
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("attribute1", "Token Validation fail in generateOtp");
			parameters.put("tokeonFront", csrfToken);
			parameters.put("tokeonBack", sessionToken);
			
		}
		logger.info(" === Token Validation Status in tokenValidation === " + tokenValidationStatus);

		return tokenValidationStatus;

	}

	// validating all the fields of form
	public JSONObject formFieldValidation(String requestJson) throws JSONException {

		logger.info(" === requestJson in formFieldValidation === " + requestJson);
		JSONObject validateStatus = new JSONObject();
		JSONObject validation = new JSONObject();

		try {
			String mobile = "\\d{10,15}$";
			String date = "\\d{2}/\\d{2}/\\d{4}";
			String emailId = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w-]+\\.)+[\\w]+[\\w]$";
			String panCard = "[A-Za-z]{3}[pP]{1}[A-Za-z]{1}[0-9]{4}[A-Za-z]{1}";
			String pincode = "\\d{6}";
			String accountNumber = "[0-9]{9,18}";
			String ifscCodeRegex = "^[A-Z]{4}[0][A-Z0-9]{6}$";
			boolean flag = true;

			@SuppressWarnings("unchecked")
			LinkedHashMap<String, Object> result = new ObjectMapper().readValue(requestJson, LinkedHashMap.class);

			for (Entry<String, Object> results : result.entrySet()) {

				String key = results.getKey();
				String value = (String) results.getValue();

				logger.info(" == Key === " + key + " == Value == " + value);

				if (key.equals(Constants.MOBILE_NUMBER)) {

					if (!value.isEmpty() && value.matches(mobile)) {

						logger.info("  == mobileNumber validate Successfully === ");
					} else {

						flag = false;
						logger.error(key + "please enter valid mobile number");
						validateStatus.put(key, "please enter valid mobile number");
					}
				}

				if (key.equals("emailAddress")) {

					if (!value.isEmpty() && value.matches(emailId)) {

						logger.info("  == emailAddress validate Successfully === ");
					} else {

						flag = false;
						logger.error(key + "please enter valid email address");
						validateStatus.put(key, "please enter valid email address");
					}
				}

				if (key.equals(Constants.DATE_OF_BIRTH)) {

					if (!value.isEmpty() && value.matches(date)) {

						logger.info("  == dateOfBirth validate Successfully === ");
					} else {

						flag = false;
						logger.error(key + "please enter your date of birth");
						validateStatus.put(key, "please enter your date of birth");
					}
				}

				if (key.equals("panCard")) {

					if (!value.isEmpty() && value.matches(panCard)) {

						logger.info("  == panCard validate Successfully === ");
					} else {

						flag = false;
						logger.error(key + "please enter valid pan card number");
						validateStatus.put(key, "please enter valid pan card number");
					}
				}

				if (key.equals("pincode")) {

					if (!value.isEmpty() && value.matches(pincode)) {

						logger.info("  == pincode validate Successfully === ");
					} else {

						flag = false;
						logger.error(key + "please enter valid pincode");
						validateStatus.put(key, "please enter valid pincode");
					}
				}

				if (key.equals("accountNumber")) {

					if (!value.isEmpty() && value.matches(accountNumber)) {

						logger.info("  == accountNumber validate Successfully === ");
					} else {

						flag = false;
						logger.error(key + "please enter valid account number");
						validateStatus.put(key, "please enter valid account number");
					}
				}

				
				if (key.equals("nomineeName")) {

					if (!value.equalsIgnoreCase(HtmlUtils.htmlEscape(value))) {

						
						flag = false;
						logger.error(key + "please enter valid NomineeName");
						validateStatus.put(key, "please enter valid NomineeName");
					} else {
						logger.info("  == NomineeName validate Successfully === ");
						
					}
				}
				
				
				if (key.equals("guardianName")) {

					if (!value.equalsIgnoreCase(HtmlUtils.htmlEscape(value))) {

						
						flag = false;
						logger.error(key + "please enter valid guardianName");
						validateStatus.put(key, "please enter valid guardianName");
					} else {
						logger.info("  == guardianName validate Successfully === ");
						
					}
				}
				
				if (key.equals("fullName") || key.equals("fullname")) {

					if (!value.equalsIgnoreCase(HtmlUtils.htmlEscape(value))) {

						
						flag = false;
						logger.error(key + "please enter valid fullName");
						validateStatus.put(key, "please enter valid fullName");
					} else {
						logger.info("  == fullName validate Successfully === ");
						
					}
				}
				if (key.equals("nomineeAddress")) {

					if (!value.equalsIgnoreCase(HtmlUtils.htmlEscape(value))) {

						
						flag = false;
						logger.error(key + "please enter valid nominee Address");
						validateStatus.put(key, "please enter valid nominee Address");
					} else {
						logger.info("  == nomineeAddress validate Successfully === ");
						
					}
				}
				if (key.equals("guardianAddress")) {

					if (!value.equalsIgnoreCase(HtmlUtils.htmlEscape(value))) {

						
						flag = false;
						logger.error(key + "please enter valid guardian Address");
						validateStatus.put(key, "please enter valid guardian Address");
					} else {
						logger.info("  == guardianAddress validate Successfully === ");
						
					}
				}
				if (key.equals("address")) {

					if (value.contains("<") || value.contains(">")) {
						flag = false;
						logger.error(key + " please enter valid address");
						validateStatus.put(key, "please enter valid address");
					} else {
						logger.info("  == address validate Successfully === ");
						
					}
				}
				
				if (key.equals("relationshipNomineeGuardian")) {

					if (!value.equalsIgnoreCase(HtmlUtils.htmlEscape(value))) {

						
						flag = false;
						logger.error(key + "please enter valid relationshipNomineeGuardian");
						validateStatus.put(key, "please enter valid relationshipNomineeGuardian");
					} else {
						logger.info("  == relationshipNomineeGuardian validate Successfully === ");
						
					}
				}
				
				
				if (key.equals("nomineeRelation")) {

					if (!value.equalsIgnoreCase(HtmlUtils.htmlEscape(value))) {

						
						flag = false;
						logger.error(key + "please enter valid nomineeRelation");
						validateStatus.put(key, "please enter valid nomineeRelation");
					} else {
						logger.info("  == nomineeRelation validate Successfully === ");
						
					}
				}
				
				if (key.equals("nomineeAddresCheck")) {

					if (!value.equalsIgnoreCase(HtmlUtils.htmlEscape(value))) {

						
						flag = false;
						logger.error(key + "please enter valid nomineeAddresCheck");
						validateStatus.put(key, "please enter valid nomineeAddresCheck");
					} else {
						logger.info("  == nomineeAddresCheck validate Successfully === ");
						
					}
				}
				
				if (key.equals("nomineeCheck")) {

					if (!value.equalsIgnoreCase(HtmlUtils.htmlEscape(value))) {

						
						flag = false;
						logger.error(key + "please enter valid nomineeCheck");
						validateStatus.put(key, "please enter valid nomineeCheck");
					} else {
						logger.info("  == nomineeCheck validate Successfully === ");
						
					}
				}
				
				if (key.equals("nomineePincode")) {

					if (!value.equalsIgnoreCase(HtmlUtils.htmlEscape(value))) {

						
						flag = false;
						logger.error(key + "please enter valid nomineePincode");
						validateStatus.put(key, "please enter valid nomineePincode");
					} else {
						logger.info("  == nomineePincode validate Successfully === ");
						
					}
				}
				
				if (key.equals("guardianPincode")) {

					if (!value.equalsIgnoreCase(HtmlUtils.htmlEscape(value))) {

						
						flag = false;
						logger.error(key + "please enter valid guardianPincode");
						validateStatus.put(key, "please enter valid guardianPincode");
					} else {
						logger.info("  == guardianPincode validate Successfully === ");
						
					}
				}
				
				if (key.equals("guardinPresent")) {

					if (!value.equalsIgnoreCase(HtmlUtils.htmlEscape(value))) {

						
						flag = false;
						logger.error(key + "please enter valid guardinPresent");
						validateStatus.put(key, "please enter valid guardinPresent");
					} else {
						logger.info("  == guardinPresent validate Successfully === ");
						
					}
				}
				
				if (key.equals("nomineeAddresCheck")) {

					if (!value.equalsIgnoreCase(HtmlUtils.htmlEscape(value))) {

						
						flag = false;
						logger.error(key + "please enter valid nomineeAddresCheck");
						validateStatus.put(key, "please enter valid nomineeAddresCheck");
					} else {
						logger.info("  == nomineeAddresCheck validate Successfully === ");
						
					}
				}
				
				if (key.equals("investmentType")) {

					if (!value.equalsIgnoreCase(HtmlUtils.htmlEscape(value))) {

						
						flag = false;
						logger.error(key + "please enter valid investmentType");
						validateStatus.put(key, "please enter valid investmentType");
					} else {
						logger.info("  == investmentType validate Successfully === ");
						
					}
				}
				if (key.equals("existingCustId")) {

					if (!value.equalsIgnoreCase(HtmlUtils.htmlEscape(value))) {

						
						flag = false;
						logger.error(key + "please enter valid existingCustId");
						validateStatus.put(key, "please enter valid existingCustId");
					} else {
						logger.info("  == existingCustId validate Successfully === ");
						
					}
				}
				if (key.equals("custCkycID")) {

					if (!value.equalsIgnoreCase(HtmlUtils.htmlEscape(value))) {

						
						flag = false;
						logger.error(key + "please enter valid custCkycID");
						validateStatus.put(key, "please enter valid custCkycID");
					} else {
						logger.info("  == custCkycID validate Successfully === ");
						
					}
				}
				
				if (key.equals("costomerId")) {

					if (!value.equalsIgnoreCase(HtmlUtils.htmlEscape(value))) {

						
						flag = false;
						logger.error(key + "please enter valid costomerId");
						validateStatus.put(key, "please enter valid costomerId");
					} else {
						logger.info("  == costomerId validate Successfully === ");
						
					}
				}
				
				if (key.equals("nomineedob")) 
				{
                    if(!value.isEmpty() && !"NA".equalsIgnoreCase(value))
                    {
                    	if (!value.isEmpty() && value.matches(date))
                    	{

    						logger.info("  ==nomineedob dateOfBirth validate Successfully === ");
    					} else {

    						flag = false;
    						logger.error(key + "please enter nomineedob date of birth");
    						validateStatus.put(key, "please enter nomineedob date of birth");
    					}	
                    }
					
				}

				if (key.equals("fullBankName")) 
				{
                    if(!value.isEmpty() && !"NA".equalsIgnoreCase(value))
                    {
                    	if (!value.isEmpty() && value.equalsIgnoreCase(HtmlUtils.htmlEscape(value)))
                    	{

    						logger.info("==fullBankName validate Successfully === ");
    					} else {

    						flag = false;
    						logger.error(key + "please select valid Bank Name");
    						validateStatus.put(key, "please select valid Bank Name ");
    					}	
                    }
					
				}
				if (key.equals("ifscCode")) 
				{
                    
                    if (!value.isEmpty() && value.matches(ifscCodeRegex)) 
                    {
            			
            			IfscCode ifsc = fixedDepositDao.ifscCodeValidator(value);
            			if(ifsc !=null)
            			{
            				logger.info("  == ifscCodeRegex validate Successfully === ");
            			}else
            			{
                			flag = false;
    						logger.error(key + "please enter ifsc code");
    						validateStatus.put(key, "please enter ifsc code");

            			}
            			
            		} else 
            		{
            			logger.info(" == ifscCodeRegex validate Failed ===");
            			flag = false;
						logger.error(key + "please enter ifsc code");
						validateStatus.put(key, "please enter ifsc code");

            		}
				}
			}

			validation.put(Constants.VALIDATION_MESSAGE, validateStatus);

			if (flag) {
				validation.put(Constants.VALIDATION_STATUS, Constants.STATUS_SUCCESS);
			} else {
				validation.put(Constants.VALIDATION_STATUS, Constants.STATUS_FAIL);
			}

		} catch (Exception e) {
			validation.put(Constants.VALIDATION_STATUS, Constants.STATUS_FAIL);
			logger.error(" === Exception in formFieldValidation === ", e);
		}

		return validation;

	}

	public String ifscValidator(String ifscCode) {

		String ifscCodeRegex = "^[A-Z]{4}[0][A-Z0-9]{6}$";
		String ifscCodeStatus = "";

		if (!ifscCode.isEmpty() && ifscCode.matches(ifscCodeRegex)) {
			ifscCodeStatus = Constants.STATUS_SUCCESS;
			logger.info("  == ifscCodeRegex validate Successfully === ");

		} else {
			ifscCodeStatus = Constants.STATUS_FAIL;
			logger.info(" == ifscCodeRegex validate Failed ===");

		}

		return ifscCodeStatus;
	}

	public String paterCodeValidator(String partnercode) {

		String patnercodeRegex = "[0-9]{1,10}";
		String partnercodeStatus = "";

		if (!partnercode.isEmpty() && partnercode.matches(patnercodeRegex)) {
			partnercodeStatus = Constants.STATUS_SUCCESS;
			logger.info("  == patnercodeRegex validate Successfully === ");

		} else {
			partnercodeStatus = Constants.STATUS_FAIL;
			logger.info(" == patnercodeRegex validate Failed ===");

		}

		return partnercodeStatus;
	}
	
	public JSONObject sessionFieldValidation(String mobileNumberSession,
			 String fullName, String mobileNumber, String dateOfBirth,String customerId) {

		logger.info( " === mobileNumberSession  === "
				+ mobileNumberSession);
		logger.info(" === fullName === " + fullName + " === mobileNumber  === " + mobileNumber
				+ " === dateOfBirth ==== " + dateOfBirth);

		JSONObject validation = new JSONObject();

		try {
			JSONObject validationStatus = new JSONObject();
			JSONObject validationError = new JSONObject();

			boolean flag = true;


			if (mobileNumberSession.equalsIgnoreCase(mobileNumber)) {

				logger.info(" === mobileNumber matched ====");
				validationStatus.put(Constants.MOBILE_NUMBER, "mobileNumber matched");

			} else {
				flag = false;
				logger.info(" === mobileNumber not matched ====");
				validationError.put(Constants.MOBILE_NUMBER, "mobileNumber not matched");
			}


			if (flag) {

				validation.put("validationStatus", Constants.STATUS_SUCCESS);
				validation.put(Constants.VALIDATION_MESSAGE, validationStatus);
			} else {
				validation.put("validationStatus", Constants.STATUS_FAIL);
				validation.put(Constants.VALIDATION_MESSAGE, validationError);
			}

		} catch (Exception e) {
			logger.error(" === Exception in sessionFieldValidation === ", e);
			if(!(customerId.isEmpty())){
				dbManipulation.recordExeption("session Field Validation","Field Validation issue", customerId);
			}
		}

		return validation;

	}

	public String calculatorFieldValidation(String requestJson,String customerId) throws JSONException {

		logger.info(" === requestJson in calculatorFieldValidation === " + requestJson);

		JSONObject validateStatus = new JSONObject();
		JSONObject validation = new JSONObject();

		try {
			@SuppressWarnings("unchecked")
			LinkedHashMap<String, Object> result = new ObjectMapper().readValue(requestJson, LinkedHashMap.class);

			boolean flag = true;
			for (Entry<String, Object> results : result.entrySet()) {

				String key = results.getKey();
				String value = (String) results.getValue();

				if (key.equals("tenor")) {

					if (!value.isEmpty() && value.matches("")) {

						logger.info("  == tenor validate Successfully === ");
					} else {

						flag = false;
						logger.error(key + "please choose valid tenor");
						validateStatus.put(key, "please choose valid tenor");
					}
				}

				if (key.equals("interestRate")) {

					if (!value.isEmpty() && value.matches("")) {

						logger.info("  == interestRate validate Successfully === ");
					} else {

						flag = false;
						logger.error(key + "please choose valid interestRate");
						validateStatus.put(key, "please choose valid interestRate");
					}
				}

				if (key.equals("depositAmount")) {

					if (!value.isEmpty() && value.matches("")) {

						logger.info("  == depositAmount validate Successfully === ");
					} else {

						flag = false;
						logger.error(key + "please enter valid depositAmount");
						validateStatus.put(key, "please enter valid depositAmount");
					}
				}

				if (key.equals("sdpDepositNO")) {

					if (!value.isEmpty() && value.matches("")) {

						logger.info("  == sdpDepositNO validate Successfully === ");
					} else {

						flag = false;
						logger.error(key + "please choose valid sdpDepositNO");
						validateStatus.put(key, "please choose valid sdpDepositNO");
					}
				}

				if (key.equals("nomineeName")) {

					if (!value.isEmpty() && value.matches("")) {

						logger.info("  == nomineeName validate Successfully === ");
					} else {

						flag = false;
						logger.error(key + "please enter valid name");
						validateStatus.put(key, "please enter valid name");
					}
				}

				if (key.equals("nomineeDOB")) {

					if (!value.isEmpty() && value.matches("")) {

						logger.info("  == nomineeDOB validate Successfully === ");
					} else {

						flag = false;
						logger.error(key + "please enter valid nomineeDOB");
						validateStatus.put(key, "please enter valid dob");
					}
				}

				validation.put(Constants.VALIDATION_MESSAGE, validateStatus);

				if (flag) {
					validation.put(Constants.VALIDATION_STATUS, Constants.STATUS_SUCCESS);
				} else {
					validation.put(Constants.VALIDATION_STATUS, Constants.STATUS_FAIL);
				}

			}

		} catch (Exception e) {
			if(!(customerId.isEmpty())){
				dbManipulation.recordExeption("Field Validation","Field Validation issue", customerId);
			}
			validation.put(Constants.VALIDATION_STATUS, Constants.STATUS_FAIL);
			logger.error(" === Exception in calculatorFieldValidation ===== ", e);
		}

		return validation.toString();

	}
	
	public JSONObject documentUploadFileValidation(Map<String,MultipartFile> listOfFiles,String customerId) 
	{
		JSONObject validation = new JSONObject();
		try {
			JSONObject validationStatus = new JSONObject();
			JSONObject validationError = new JSONObject();
			String fileExtn="";
			List<MultipartFile> files = new ArrayList<>(listOfFiles.values());
			boolean flag = true;
			int fileSize=files.size();
			if(fileSize!= 0)
			{
				for (int i = 0; i< files.size(); i++) 
				{
					if (!(files.get(i).getOriginalFilename().isEmpty()) && (files.get(i).getBytes().length > 0)) 
					{
						logger.info("=================File  OriginalFilename===============" + files.get(i).getOriginalFilename());
						String fileContentType=files.get(i).getContentType();
						
						fileExtn = Utility.getFileExtension(files.get(i).getOriginalFilename());
						logger.info("=================File  fileExtn===============" + fileExtn);
						if("jpg".equalsIgnoreCase(fileExtn) || "jpeg".equalsIgnoreCase(fileExtn)|| "JPG".equalsIgnoreCase(fileExtn) || "JPEG".equalsIgnoreCase(fileExtn))
						{
							flag=true;
							validationStatus.put(Constants.DOCUMENT_FILE, "File format validated");
						}
						else if("pdf".equalsIgnoreCase(fileExtn)||"PDF".equalsIgnoreCase(fileExtn) ||"Pdf".equalsIgnoreCase(fileExtn))
						{
							flag=true;
							validationStatus.put(Constants.DOCUMENT_FILE, "File format validated");
						} else
						{
							flag=false;
							validationError.put(Constants.DOCUMENT_FILE, "File Format not validated");
							break;
						}
						logger.info("=================files.get(i).getContentType();===============" +fileContentType);
						if("image/jpeg".equalsIgnoreCase(fileContentType) || "image/jpg".equalsIgnoreCase(fileContentType) || "application/pdf".equalsIgnoreCase(fileContentType))
						{
							flag=true;
							validationStatus.put(Constants.DOCUMENT_FILE, "File Content Type validated");
						}else
						{
							flag=false;
							validationError.put(Constants.DOCUMENT_FILE, "File Content Type not validated");
							break;
						}
				}
				}
				if (flag) {

					validation.put("validationStatus", Constants.STATUS_SUCCESS);
					validation.put(Constants.VALIDATION_MESSAGE, validationStatus);
				} else {
					validation.put("validationStatus", Constants.STATUS_FAIL);
					validation.put(Constants.VALIDATION_MESSAGE, validationError);
				}
		} 
		}
		catch (Exception e) {
			if(!( customerId.isEmpty())){
				dbManipulation.recordExeption("documents Validation","Field Validation issue", customerId);
			}
			logger.error(" === Exception in documentUploadFileValidation === ", e);
			validation.put("validationStatus", Constants.STATUS_FAIL);
			
		}

		return validation;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject manualReflowFieldValidation(String requestJson) {
		
		JSONObject validationError = new JSONObject(); 
		LinkedHashMap<String, Object> entries;
		try {
			entries = new ObjectMapper().readValue(requestJson, LinkedHashMap.class);
			
			for (Entry<String, Object> entry : entries.entrySet())
			{
				String key = entry.getKey();
				String value = (String) entry.getValue();
				
				if(key.equalsIgnoreCase(Constants.FINALSFDCID)) {
					if(!value.isEmpty() && value.equalsIgnoreCase(HtmlUtils.htmlEscape(value))) {
						logger.info("============ finalSfdcId validated successfully =============");
					}else {
						validationError.put(Constants.FINALSFDCID, "Invalid finalSfdcId");
					}
				}
				
				if(key.equalsIgnoreCase(Constants.FORMAPPNUMBER)) {
					if(!value.isEmpty() && value.equalsIgnoreCase(HtmlUtils.htmlEscape(value))){
						logger.info("========== formAppNumber validated successfully ==========");
					}else {
						validationError.put(Constants.FORMAPPNUMBER, "Invalid formAppNumber");
					}
				}
			}
			logger.info("===== validationError in manualReflowFieldValidation ======"+validationError);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return validationError;
	}
	
	
	public String SFDCAddressValidate(String address)
	{
		String newAddress = "";
		try{
			if (address!="")
			{
		 newAddress=address.replaceAll("[^a-zA-Z0-9 /.,-]+","");
		 logger.info("Address after Processing : "+newAddress);
			}
		}
		catch(Exception e)
		{
			logger.error("======Exception in SFDCAddressValidate========="+e);
		}
		
		return newAddress;
		
	}
	
	public JSONObject commAddDocFileValidation(MultipartFile file,String customerId) 
    {
	JSONObject validation = new JSONObject();
	try {
		JSONObject validationStatus = new JSONObject();
		JSONObject validationError = new JSONObject();
		String fileExtn="";
		boolean flag = true;
		
			
				if (!(file.getOriginalFilename().isEmpty()) && (file.getBytes().length > 0)) 
				{
					logger.info("=================File  OriginalFilename===============" + file.getOriginalFilename());
					String fileContentType=file.getContentType();
					
					fileExtn = Utility.getFileExtension(file.getOriginalFilename());
					logger.info("=================File  fileExtn===============" + fileExtn);
					if("jpg".equalsIgnoreCase(fileExtn) || "jpeg".equalsIgnoreCase(fileExtn)|| "JPG".equalsIgnoreCase(fileExtn) || "JPEG".equalsIgnoreCase(fileExtn))
					{
						flag=true;
						validationStatus.put(Constants.DOCUMENT_FILE, "File format validated");
					}
					else if("pdf".equalsIgnoreCase(fileExtn)||"PDF".equalsIgnoreCase(fileExtn) ||"Pdf".equalsIgnoreCase(fileExtn))
					{
						flag=true;
						validationStatus.put(Constants.DOCUMENT_FILE, "File format validated");
					} else
					{
						flag=false;
						validationError.put(Constants.DOCUMENT_FILE, "File Format not validated");
					}
					logger.info("=================files.get(i).getContentType();===============" +fileContentType);
					if("image/jpeg".equalsIgnoreCase(fileContentType) || "image/jpg".equalsIgnoreCase(fileContentType) || "application/pdf".equalsIgnoreCase(fileContentType))
					{
						flag=true;
						validationStatus.put(Constants.DOCUMENT_FILE, "File Content Type validated");
					}else
					{
						flag=false;
						validationError.put(Constants.DOCUMENT_FILE, "File Content Type not validated");
					}
			}
			
			if (flag) {

				validation.put("validationStatus", Constants.STATUS_SUCCESS);
				validation.put(Constants.VALIDATION_MESSAGE, validationStatus);
			} else {
				validation.put("validationStatus", Constants.STATUS_FAIL);
				validation.put(Constants.VALIDATION_MESSAGE, validationError);
			}
	
	}
	catch (Exception e) {
		
		logger.error(" === Exception in communication AddDoc File Validation === ", e);
		validation.put("validationStatus", Constants.STATUS_FAIL);
		
	}

	return validation;
	
}

	
	public String pincodeValidator(String pincode) {
		String pinCodeRegex = "^[0-9]{6}$";
		String pinCodeStatus = "";

		if (!pincode.isEmpty() && pincode.matches(pinCodeRegex)) {
			pinCodeStatus = Constants.STATUS_SUCCESS;
			logger.info("  == pinodeRegex validate Successfully === ");

		} else {
			pinCodeStatus = Constants.STATUS_FAIL;
			logger.info(" == pinCodeRegex validate Failed ===");

		}

		return pinCodeStatus;
	}
}
