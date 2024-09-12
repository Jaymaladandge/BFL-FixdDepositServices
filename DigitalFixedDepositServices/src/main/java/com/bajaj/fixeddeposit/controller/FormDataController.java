package com.bajaj.fixeddeposit.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.Range;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bajaj.fixeddeposit.api.PaymentIntegration;
import com.bajaj.fixeddeposit.dao.FixedDepositDao;
import com.bajaj.fixeddeposit.model.FixedDepositData;
import com.bajaj.fixeddeposit.service.FormDataService;
import com.bajaj.fixeddeposit.util.Constants;
import com.bajaj.fixeddeposit.util.DataEncryption;
import com.bajaj.fixeddeposit.util.DbManipulationUtil;
import com.bajaj.fixeddeposit.util.Encryption;
import com.bajaj.fixeddeposit.util.Utility;
import com.bajaj.fixeddeposit.validation.FormFieldValidation;
import com.newrelic.api.agent.Trace;

@RestController
public class FormDataController {

	private static final Logger logger = Logger.getLogger(FormDataController.class);

	@Autowired
	PaymentIntegration paymentIntegration;

	@Autowired
	FormDataService formDataService;

	@Autowired
	FormFieldValidation formFieldValidation;

	@Autowired
	private FixedDepositDao fixedDepositDao;

	@Autowired
	private DbManipulationUtil dbManipulation;  

	@PostMapping("/addNtbUserData")
	public String addNtbUserData(@RequestBody String ntbData, HttpServletRequest request){
		JSONObject responseJson = new JSONObject();
		String encryptRes = "";
		String customerId="";
		String requestCustId="";
		int panexceedCount=0;
		try {

			logger.info(" === request in ntbRequest==  " + ntbData);
			JSONObject requestJson = new JSONObject(ntbData);
			requestCustId = requestJson.has("fdcNumber") ?requestJson.get("fdcNumber").toString():"";
			String panCard = requestJson.get("panCard") == null ?"NA" : requestJson.get("panCard").toString();
			String requestId = requestJson.get("requestId") == null ?"NA" : requestJson.get("requestId").toString();
			
			
			responseJson.put("panCard", panCard);
			responseJson.put("requestId", requestId);
			logger.info(" ========== requestCustId  ========= " + requestCustId);
			HttpSession httpSession = request.getSession(false);

			if (httpSession == null) {
				responseJson.put("apiStatus", "sessionExpired");
			}
			else {
				logger.info(" ===== Session id in ntbRequest ==== " + httpSession.getId());

				panexceedCount = httpSession.getAttribute("panvalicationcont") == null ? 0 :Integer.parseInt(httpSession.getAttribute("panvalicationcont").toString());
				logger.info(" ==== PanexceedCount in addNtbUserData === " + panexceedCount);

				if (panexceedCount > 1) {		
					httpSession.removeAttribute(Constants.OTP_COUNT);
					logger.info(" === Removed pan exceed Count from session ==== ");
					
					String requesstJsonObj = Utility.getReqEncryptedString(requestCustId);
					responseJson.put("requestString", requesstJsonObj);
					responseJson.put("customerId", requestCustId);
					responseJson.put(Constants.VALIDATION_STATUS, "PanValidationexceed");
					responseJson.put("panValicationCount",httpSession.getAttribute("panvalicationcont"));
					
					logger.info("========= responseJson in panexceedCount ========= "+responseJson);
				}else{

					JSONObject fieldValidationStatus = formFieldValidation.formFieldValidation(ntbData);
					logger.info(" == fieldValidationStatus in addNtbData === " + fieldValidationStatus);
					JSONObject validationMsg = (JSONObject) fieldValidationStatus.get("validationMsg");
					String validationStatus = fieldValidationStatus.get(Constants.VALIDATION_STATUS) == null
							? Constants.STATUS_FAIL : fieldValidationStatus.get(Constants.VALIDATION_STATUS).toString();
					if (Constants.STATUS_SUCCESS.equalsIgnoreCase(validationStatus)) {
						customerId = (String) httpSession.getAttribute(Constants.CUSTOMER_ID);
						logger.info(" === customerId in ntbRequest ==== " + customerId);
						synchronized(this){
						String updateStatus = formDataService.addNtbUserDataService(ntbData, customerId, httpSession);
						logger.info(" === updateStatus in addUserData === " + updateStatus);
						httpSession.setAttribute("custType", "NTB");
						responseJson.put(Constants.VALIDATION_STATUS, Constants.STATUS_SUCCESS);
						responseJson.put("userDetails", updateStatus);

						}
						
						String eventHubaddNtbUserData = formDataService.eventHubdataCall(customerId,Constants.ADDNTBUSERDATA);
						logger.info("============event Hub add Ntb User Data=========="+eventHubaddNtbUserData);

					} else {
						responseJson.put(Constants.FIELD_VALIDATION, Constants.STATUS_FAIL);
						responseJson.put("validationMsg", validationMsg);
						Utility.loadNewRelicValidation("Exception in field Validation in addNtbUserData "+" "+validationMsg.toString(), "field Validation in addNtbUserData",requestCustId,Constants.ADDNTBUSERDATA);

					}
				}

			}
		} catch (Exception e) {
			responseJson.put(Constants.VALIDATION_STATUS, Constants.STATUS_FAIL);
			Utility.loadNewRelicException(e.toString(), Constants.ADDNTBUSERDATA, "", requestCustId);
			logger.info(" == Exception in addNtbUserData === ", e);
			if(!(requestCustId.isEmpty())){
				dbManipulation.recordExeption("CKYCVERIFY","Exception due to internal call", requestCustId);
			}
		}
		logger.info(" === responseJson in ==== " + responseJson);
		encryptRes = Encryption.getEncryptedVal(responseJson);
		return encryptRes;
	}

	@GetMapping(value ="/thank-you-page")        
	public ModelAndView fdThankyouPage(HttpServletRequest request,HttpServletResponse response){  
		logger.info(" ======================= thank-you-for-applying-fixed-deposit ============ ");
		try {

			HttpSession session = request.getSession(false);
			logger.info(" ::: session id in thank you page:::  " + session);

			if(session !=null) {
				logger.info(":::  Old session in thank you page url ::::  ");

				return new ModelAndView("fd-sdp-thank-you-page");	
			}

		}
		catch(Exception e) {
			logger.error("Exception in thank-you-for-applying-fixed-deposit : " ,e);
		}
		return null;
	}


	@GetMapping(value ="/online")        
	public ModelAndView mainFormPage(HttpServletRequest request,HttpServletResponse response){  
		logger.info(" =======================applying-fixed-deposit online form ============ ");
		try {

			HttpSession session = request.getSession(false);
			logger.info(" ::: session id in mainFormPage:::  " + session);

			if(session !=null) {
				logger.info(":::  Old session in mainFormPage page url ::::  ");

				return new ModelAndView("fd-sdp-application-form-old");	
			}

		}
		catch(Exception e) {
			logger.error("Exception in mainFormPage-for-applying-fixed-deposit : " ,e);
		}
		return new ModelAndView("fd-sdp-application-form-old");	
	}

	@PostMapping(value = "/addUserData", consumes = {"multipart/form-data"})
	public  @ResponseBody String addUserData(@RequestParam(value = "file") List<MultipartFile> files, 
			@RequestParam(value = "fullName") String fullName,
			@RequestParam(value = "mobileNumber") String mobileNumber,
			@RequestParam(value = "dateOfBirth") String dateOfBirth,
			@RequestParam(value = "emailAddress") String emailAddress,
			@RequestParam(value = "panCard") String panCard,
			@RequestParam(value = "address") String address,
			@RequestParam(value = "pincode") String pincode,
			@RequestParam(value = "nomineeCheck") String nomineeCheck,
			@RequestParam(value = "NomineeName") String nomineeName,
			@RequestParam(value = "NomineeDob") String nomineeDob,
			@RequestParam(value = "nomineeRelation") String nomineeRelation,
			@RequestParam(value = "nomineeAddresCheck") String nomineeAddresCheck,
			@RequestParam(value = "nomineeAddress") String nomineeAddress,
			@RequestParam(value = "guardianName") String guardianName,
			@RequestParam(value = "gaurdianAge") String guardianAge,
			@RequestParam(value = "relationshipNomineeGuardian") String relationshipNomineeGuardian,
			@RequestParam(value = "guardianAddress") String guardianAddress,
			@RequestParam(value = "guardinPresent") String guardinPresent,
			@RequestParam(value = "nomineePinCode") String nomineePincode,
			@RequestParam(value = "GuardianpinCode") String guardianPincode,
			@RequestParam(value = "existingCustId") String existingCustId,
			@RequestParam(value = "commCheckbox") String commCheckbox,
			@RequestParam(value = "commAddress") String commAddress,
			@RequestParam(value = "commPincode") String commPincode,
			@RequestParam(value = "gender") String gender,
			@RequestParam(value = "title") String title,
			@RequestParam(value = "titleMain") String titleMain,
			@RequestParam(value = "fdcNumber") String requestCustId,
			@RequestParam(value = "commAddDoc") MultipartFile commAddDoc,
			@RequestParam(value = "commAddDocName") String commAddDocName,
			@RequestParam(value = "etbFlag") String etbFlag,
			HttpServletRequest request, HttpServletResponse response) throws JSONException {


		JSONObject responseJson = new JSONObject();
		String encryptedVal="";
		JSONObject reqJson = new JSONObject();
		
		try {
			logger.info(" ========== requestCustId  ========= " + requestCustId);
			HttpSession httpSession = request.getSession(false);
			if (httpSession == null) {
				responseJson.put("apiStatus", "sessionExpired");
			}
			else {
				logger.info(" === session id in addUserData === " + httpSession.getId());

				String mobileNumberSession = httpSession.getAttribute(Constants.USER_MOBILE_NUMBER) == null ? "NA" : httpSession.getAttribute(Constants.USER_MOBILE_NUMBER).toString();
				String customerId = (String) httpSession.getAttribute(Constants.CUSTOMER_ID);

				logger.info(" === customerId == "+ customerId +" === fullName === " + fullName + " == mobileNumber === " + mobileNumber + " === dateOfBirth  === " + dateOfBirth);
				logger.info(" == mobileNumberSession === " + mobileNumberSession);

				JSONObject sessionFieldValidation = formFieldValidation.sessionFieldValidation( mobileNumberSession, fullName, mobileNumber, dateOfBirth,customerId);
				logger.info(" === sessionFieldValidation in addUserData ==== " + sessionFieldValidation);

				JSONObject sessionFieldValidationMsg = sessionFieldValidation.getJSONObject("validationMsg");

				if(sessionFieldValidation.has("validationStatus") && sessionFieldValidation.get("validationStatus").toString().equals(Constants.STATUS_SUCCESS)){
					responseJson.put(Constants.VALIDATION_STATUS, Constants.STATUS_SUCCESS);


					responseJson.put("sessionFieldValidationMsg", sessionFieldValidationMsg);

					String uploadFileSotreStatus = formDataService.storeCustomerDocuments(new ArrayList<>(files), customerId);
					logger.info(" === uploadFileSotreStatus in addUserData ==== " + uploadFileSotreStatus);

					logger.info(" == addressFile uploaded files in addUserData  === " + files);

					
					reqJson.put("fullName", fullName);
					reqJson.put("mobileNumber", mobileNumber);
					reqJson.put("dateOfBirth", dateOfBirth);
					reqJson.put("panCard", panCard);	
					reqJson.put("emailAddress", emailAddress);
					reqJson.put("address", address);
					reqJson.put("pincode", pincode);
					reqJson.put("nomineeCheck", nomineeCheck);
					reqJson.put("nomineeName", nomineeName);
					reqJson.put("nomineedob", nomineeDob);
					reqJson.put("nomineeRelation", nomineeRelation);
					reqJson.put("nomineeAddresCheck", nomineeAddresCheck);
					reqJson.put("nomineeAddress", nomineeAddress);
					reqJson.put("guardianName", guardianName);
					reqJson.put("guardianAge", guardianAge);
					reqJson.put("relationshipNomineeGuardian", relationshipNomineeGuardian);
					reqJson.put("guardianAddress", guardianAddress);
					reqJson.put("guardinPresent", guardinPresent);
					reqJson.put("nomineePincode", nomineePincode);
					reqJson.put("guardianPincode", guardianPincode);
					reqJson.put("existingCustId", existingCustId);
					reqJson.put("commCheckbox", commCheckbox);
					reqJson.put("commAddress", commAddress);
					reqJson.put("commPincode", commPincode);
					reqJson.put("gender", gender);
					reqJson.put("title", title);
					reqJson.put("titleMain", titleMain);
					reqJson.put("fdcNumber", requestCustId);
					reqJson.put("commAddDocName", commAddDocName);
					reqJson.put("etbFlag", etbFlag);
					logger.info(" === Request Json in addUserData ==== " + reqJson);
										
					JSONObject fieldValidationStatus  = formFieldValidation.formFieldValidation(reqJson.toString());
					logger.info(" == fieldValidationStatus in addUserData === " + fieldValidationStatus);

					JSONObject validationMsg = (JSONObject) fieldValidationStatus.get("validationMsg");

					String validationStatus = fieldValidationStatus.get(Constants.VALIDATION_STATUS) == null?Constants.STATUS_FAIL:fieldValidationStatus.get(Constants.VALIDATION_STATUS).toString();
					logger.info(" === validationStatus in addUserData === " + validationStatus);

					if(Constants.STATUS_SUCCESS.equalsIgnoreCase(validationStatus)){
						
						logger.info(" === Field Validate Successfully == ");
						
						JSONObject userData = new JSONObject(reqJson);
						JSONObject  commAdddocFileValidation=new JSONObject();
						
						String commAddressPresent= userData.has("commCheckbox")?userData.get("commCheckbox").toString():"NA";
						
						if("No".equalsIgnoreCase(commAddressPresent))
						{
							commAdddocFileValidation=formFieldValidation.commAddDocFileValidation(commAddDoc,customerId);
							 validationMsg= commAdddocFileValidation.getJSONObject("validationMsg");
						}else{
							commAdddocFileValidation.put("validationStatus", Constants.STATUS_SUCCESS);
						}
						
						if(commAdddocFileValidation.has("validationStatus") && commAdddocFileValidation.get("validationStatus").toString().equals(Constants.STATUS_SUCCESS))
						{
						String updateStatus = formDataService.addUserDataService(reqJson.toString(), customerId,commAddDoc);
						JSONObject responseVal=new JSONObject(updateStatus);
						logger.info(" ===  updateStatus in addUserData === " + updateStatus);
						
						String status=responseVal.has("storeStatus")?responseVal.get("storeStatus").toString():Constants.STATUS_FAIL;
						if(Constants.STATUS_SUCCESS.equalsIgnoreCase(status))
						{
							responseJson.put(Constants.VALIDATION_STATUS, Constants.STATUS_SUCCESS);
							responseJson.put(Constants.BANK_DETAILS, updateStatus);
						}else{
							responseJson.put(Constants.VALIDATION_STATUS, Constants.STATUS_FAIL);
							responseJson.put(Constants.BANK_DETAILS, updateStatus);
						}
						responseJson.put("FdcNumber", customerId);						
						}
						else
						{
							responseJson.put(Constants.VALIDATION_STATUS, Constants.STATUS_FAIL);
						}
						
					}else{
						responseJson.put(Constants.VALIDATION_STATUS, Constants.STATUS_FAIL);
						Utility.loadNewRelicValidation("field Validation status fail in addUserData "+" "+validationMsg.toString(), "field Validation in addUserData",requestCustId,Constants.ADDUSERDATA);


					}

					responseJson.put("validationMsg", validationMsg);

				}else{

					responseJson.put(Constants.VALIDATION_STATUS, Constants.STATUS_FAIL);
					Utility.loadNewRelicValidation("session field Validation status fail in addUserData "+sessionFieldValidation, "field Validation in addUserData",requestCustId,Constants.ADDUSERDATA);

				}

				String eventHubaddUserData = formDataService.eventHubdataCall(customerId, Constants.ADDUSERDATA);
				logger.info("============event Hub add User Data=========="+eventHubaddUserData);

			}
		} catch (Exception e) {
			logger.error(" === Exception in addUserData === ", e);
			if(!( requestCustId.isEmpty())){
				dbManipulation.recordExeption("PERSONAL_DETAILS", "Exception due to internal call",requestCustId);
			}
			responseJson.put(Constants.VALIDATION_STATUS, Constants.STATUS_FAIL);
			Utility.loadNewRelicException(e.toString(),Constants.ADDUSERDATA, "", requestCustId);
		}


		logger.info(" == Return Response in adduser === " + responseJson);
		encryptedVal = Encryption.getEncryptedVal(responseJson);
		logger.info(" Encrypted Response === " + encryptedVal);

		return encryptedVal;
	}
	@Trace
	@PostMapping("/ifscValidator")
	public String ifscCodeVerification(@RequestBody String ifscCodeJson){

		logger.info(" ==== ifsc code in ifscCodeVerification ==== " + ifscCodeJson);
		String  ifscStatus = "";
		JSONObject ifscStatusJson = new JSONObject();
		String fdcNumber="";
		
		try {
			JSONObject ifscJson = new JSONObject(ifscCodeJson);
			String ifscCode = ifscJson.get("ifscCode").toString();
			fdcNumber = ifscJson.optString("fdcNumber")==null?"":ifscJson.optString("fdcNumber");
			logger.info("====fdcNumber===="+fdcNumber+" == ifscCode === " + ifscCode);

			if(ifscCode != null){
				String ifscCodeStatus = formFieldValidation.ifscValidator(ifscCode);
				logger.info(" === ifscCodeStatus in ifscCodeVerification === " + ifscCodeStatus);

				if(Constants.STATUS_SUCCESS.equalsIgnoreCase(ifscCodeStatus)){

					logger.info(" === ifsc code validation successfully === ");

					ifscStatus = formDataService.ifscValidatorService(ifscCode,fdcNumber);
					ifscStatusJson.put(Constants.STATUS, ifscStatus);
					logger.info(" === ifscStatus in ifscCodeVerification === " + ifscStatus);
				}else{

					ifscStatusJson.put(Constants.STATUS, Constants.STATUS_FAIL);
					logger.info(" === ifsc code validation failed === ");
					Utility.loadNewRelicValidation("field Validation status fail in ifscCodeVerification ", "field Validation in ifscCodeVerification", fdcNumber,Constants.IFSCCODE);
				}
			}else{
				ifscStatusJson.put(Constants.STATUS, Constants.STATUS_FAIL);
				ifscStatusJson.put(Constants.VALIDATION_STATUS, "please enter ifsc code");
				logger.info(" === ifsc code validation failed === ");
				Utility.loadNewRelicValidation("field Validation status fail in ifscCodeVerification ", "field Validation in ifscCodeVerification", fdcNumber,Constants.IFSCCODE);
			}

		} catch (Exception e) {
			logger.error(" ===== Exception in ifscCodeVerification === ", e);
			Utility.loadNewRelicException(e.toString(), Constants.IFSCCODE, "", fdcNumber);
		}

		return ifscStatusJson.toString();
	}

	@PostMapping("/calculatorRequest")
	public String calculatorRequest(@RequestBody String requestJson, HttpServletRequest request){

		JSONObject paymentRequestJson = new JSONObject();
		String customerId="";
		String requestCustId="";
		
		try {
			logger.info("--------request in  calculatorRequest interestRateReqJson--------"+requestJson);
			JSONObject interestRateReqJson = new JSONObject(requestJson);
			requestCustId = interestRateReqJson.has("fdcNumber")?interestRateReqJson.get("fdcNumber").toString():"";
			logger.info(" ========== requestCustId  ========= " + requestCustId);
			HttpSession httpSession = request.getSession(false);
			if (httpSession == null) 
			{
				paymentRequestJson.put("apiStatus", "sessionExpired");
				customerId=interestRateReqJson.get("fdcNumber") == null ? "NA" : interestRateReqJson.get("fdcNumber").toString();
			}
			else {
				logger.info(" ===== Session id in paymentRequest ==== " + httpSession.getId());
				logger.info(" == paymentJson in paymentRequest  == " + requestJson);
				customerId = (String) httpSession.getAttribute(Constants.CUSTOMER_ID);
				customerId=customerId==null?requestCustId:customerId;
				
				logger.info(" === customerId calculatorRequest ==== " + customerId);
				String tenor = interestRateReqJson.get("tenor") == null  ? "NA" : interestRateReqJson.get("tenor").toString();
				String interestPayout = interestRateReqJson.get("fdPayoutType") == null  ? "NA" : interestRateReqJson.get("fdPayoutType").toString();
				String interestRate = interestRateReqJson.get("interestRate") == null  ? "NA" : interestRateReqJson.get("interestRate").toString();
				String dedupeCustomerType = interestRateReqJson.get(Constants.CUSTOMER_TYPE) == null  ? "NA" : interestRateReqJson.get(Constants.CUSTOMER_TYPE).toString();
				logger.info(" === tenor === " + tenor + " === interestPayout == " + interestPayout + " == interestRate == " + interestRate + " == dedupeCustomerType == " + dedupeCustomerType);

				String investmentType = interestRateReqJson.get("investmentType") == null  ? "NA" : interestRateReqJson.get("investmentType").toString();
				logger.info(" ==== investmentType in calculatorRequest === " + investmentType);

				Integer depositAmount = Integer.parseInt(interestRateReqJson.get("depositAmount") == null  ? "NA" : interestRateReqJson.get("depositAmount").toString().replaceAll(",", ""));
				logger.info(" ==== depositAmount in calculatorRequest === " + depositAmount);

				String customerType = "";
				String interestPayoutType = "";
				Boolean amountValidator = false;
				String amountRange = "";
				JSONObject validationMsg = new JSONObject(); 

				if(Constants.DEDUPE_ETB.equalsIgnoreCase(dedupeCustomerType)){
					customerType = "Existing Customer";
				}else if(Constants.DEDUPE_STB.equalsIgnoreCase(dedupeCustomerType)){
					customerType = "Senior Citizen";
				}else if("CKYCETB".equalsIgnoreCase(dedupeCustomerType) || "NTBDocumentUpload".equalsIgnoreCase(dedupeCustomerType) || "SSODEDUPE".equalsIgnoreCase(dedupeCustomerType) ){
					customerType = "New Customer";
				}



				logger.info(" === customerType in calculatorRequest ==== " + customerType);

				if ("FD".equalsIgnoreCase(investmentType)) {
					interestPayoutType = "OnMaturity".equalsIgnoreCase(interestPayout) ? Constants.CUMULATIVE : Constants.NON_CUMULATIVE;

					Range<Integer> fdAmountValidator = Range.between(15000, 50000000);
					amountValidator = fdAmountValidator.contains(depositAmount);
					amountRange = amountValidator ? "" : "your deposit amount should be between 15000 to 5 cr";

				} else if ("SDP".equalsIgnoreCase(investmentType)) {
					interestPayoutType = Constants.CUMULATIVE;

					Range<Integer> fdAmountValidator = Range.between(5000, 50000000);
					amountValidator = fdAmountValidator.contains(depositAmount);
					amountRange = amountValidator ? "" : "your deposit amount should be between 5000 to 10 lakh";
				} else {
					interestPayoutType = "NA";
				}

				logger.info(" === interestPayoutType in calculatorRequest ==== " + interestPayoutType);
				logger.info(" === amountValidator in calculatorRequest ==== " + amountValidator);


				if (amountValidator) {

					paymentRequestJson.put(Constants.VALIDATION_STATUS, Constants.STATUS_SUCCESS);
					String interetsRateStatus = formDataService.interestRateValidator(customerType, tenor, interestPayout,
							interestPayoutType, interestRate, investmentType,requestCustId);
					logger.info(" === interetsRateStatus in calculatorRequest === " + interetsRateStatus);

					if (Constants.STATUS_SUCCESS.equalsIgnoreCase(interetsRateStatus))
					{
						JSONObject fieldValidationStatus  = formFieldValidation.formFieldValidation(interestRateReqJson.toString());

						logger.info(" == fieldValidationStatus in calculatorRequest === " + fieldValidationStatus);
						JSONObject FieldValidationMsg = (JSONObject) fieldValidationStatus.get("validationMsg");

						String validationStatus = fieldValidationStatus.get(Constants.VALIDATION_STATUS) == null?Constants.STATUS_FAIL:fieldValidationStatus.get(Constants.VALIDATION_STATUS).toString();
						logger.info(" === field validationStatus in calculatorRequest === " + validationStatus);

						if(Constants.STATUS_SUCCESS.equalsIgnoreCase(validationStatus))
						{
							paymentRequestJson.put(Constants.VALIDATION_STATUS, interetsRateStatus);

							interestRateReqJson.put(Constants.CUSTOMER_TYPE, customerType);
							interestRateReqJson.put("interestPayoutType", interestPayoutType);

							String dbUpdateStatus = formDataService.calculatorService(interestRateReqJson.toString(),customerId);
							logger.info(" === dbUpdateStatus in calculatorRequest == " + dbUpdateStatus);
						}
						else
						{
							validationMsg.put("FieldvalidationMsg", FieldValidationMsg);
							paymentRequestJson.put(Constants.VALIDATION_STATUS, Constants.STATUS_FAIL);
							Utility.loadNewRelicValidation("field validation Fail in calculatorRequest "+" "+FieldValidationMsg.toString(), "field Validation in calculatorRequest",requestCustId,Constants.CALCULATORREQUEST);
						}

					} else {
						validationMsg.put("interestRate", " interest rate not matched");
						paymentRequestJson.put(Constants.VALIDATION_STATUS, Constants.STATUS_FAIL);
						Utility.loadNewRelicValidation("interest Rate validation Fail in calculatorRequest "+" "+interetsRateStatus.toString(), "field Validation in calculatorRequest",requestCustId,Constants.CALCULATORREQUEST);

					}

				}else{
					paymentRequestJson.put(Constants.VALIDATION_STATUS, Constants.STATUS_FAIL);
					Utility.loadNewRelicValidation("amountValidator validation Fail in calculatorRequest "+" "+amountValidator, "amount validation in calculatorRequest",requestCustId,Constants.CALCULATORREQUEST);

				}
				validationMsg.put(Constants.DEPOSIT_AMOUNT, amountRange);
				paymentRequestJson.put("validationMsg", validationMsg);

				logger.info(" ==== paymentRequestJson in calculatorRequest  ==== " + paymentRequestJson);

				String eventHubcalculatorRequest = formDataService.eventHubdataCall(customerId, Constants.CALCULATORREQUEST);
				logger.info("============event Hub calculator Request=========="+eventHubcalculatorRequest);
			}

		} catch (Exception e) {
			logger.error(" === Exception in calculatorRequest === ", e);
			if(!( requestCustId.isEmpty())){
				dbManipulation.recordExeption("CALUCULATION_CTA","Exception due to internal call", requestCustId);
			}
			Utility.loadNewRelicException(e.toString(), Constants.CALCULATORREQUEST, "", requestCustId);
		}

		return paymentRequestJson.toString();

	}

	@PostMapping("/paymentRequest")
	public String paymentRequest(@RequestBody String paymentJson, HttpServletRequest request){

		JSONObject paymentReqJson = new JSONObject();
		String customerId="";
		String requestCustId="";
		try {


			logger.info(" == paymentJson in paymentRequest  == " + paymentJson);
			JSONObject paymentRequestJson = new JSONObject(paymentJson);
			logger.info(" == paymentRequestJson in PaymentRequest ==== " + paymentRequestJson);
			requestCustId = paymentRequestJson.has("fdcNumber")?paymentRequestJson.get("fdcNumber").toString():"";
			logger.info(" === requestCustId PaymentRequest ==== " + requestCustId);
			HttpSession httpSession = request.getSession(false);
			if (httpSession == null) {
				paymentReqJson.put("apiStatus", "sessionExpired");
			}
			else {

				logger.info(" ===== Session id in paymentRequest ==== " + httpSession.getId());
				logger.info(" == paymentJson in paymentRequest  == " + paymentJson);
				customerId = (String) httpSession.getAttribute(Constants.CUSTOMER_ID);	

				logger.info(" === customerId PaymentRequest ==== " + customerId);

				String sessionMobileNumber = (String) httpSession.getAttribute(Constants.MOBILE_NUMBER);
				logger.info(" === sessionMobileNumber PaymentRequest ==== " + sessionMobileNumber);



				String mobileNumber = paymentRequestJson.get("mobileNumber") == null ? "NA" : paymentRequestJson.get("mobileNumber").toString();
				logger.info(" === mobileNumber PaymentRequest ==== " + mobileNumber);

				if(sessionMobileNumber.equals(mobileNumber))
				{
					logger.info(" === mobile number matched in paymentRequest === ");
					String checksum = formDataService.paymentRequestService(customerId, paymentJson);
					logger.info(" === checksum in paymentRequest ====== " + checksum);

					paymentReqJson.put(Constants.STATUS, Constants.STATUS_SUCCESS);
					paymentReqJson.put("checksum", checksum);

				}else{

					logger.info(" === mobile number not matched in paymentRequest === ");
					paymentReqJson.put(Constants.STATUS, Constants.STATUS_SUCCESS);
					paymentReqJson.put(Constants.VALIDATION_MESSAGE, "mobile number not matched");
					Utility.loadNewRelicValidation("mobile number not matched fail in paymentRequest ", "field Validation in paymentRequest",requestCustId,Constants.PAYMENTREQUEST);

				}


				logger.info(" === paymentReqJson in ==== " + paymentReqJson);

				String eventHubpaymentRequest = formDataService.eventHubdataCall(customerId, Constants.PAYMENTREQUEST);
				logger.info("============event Hub payment Request=========="+eventHubpaymentRequest);
			}
		} catch (Exception e) {
			logger.info(" == Exception in paymentRequest === ", e);
			if(!( requestCustId.isEmpty())){
				dbManipulation.recordExeption("PAYMENT_CTA","Exception due to internal call", requestCustId);
			}
			Utility.loadNewRelicException(e.toString(), Constants.PAYMENTREQUEST, "", requestCustId);
		}

		return paymentReqJson.toString();

	}

	@PostMapping("/paymentResponse" )
	public ModelAndView paymentResponse(HttpServletRequest request, HttpServletResponse response){

		String paymentResponse = request.getParameter("msg");
		logger.info(" ===  requestJson in paymentResponse === " + paymentResponse);
		String customerId="";
		try {

			HttpSession httpSession = request.getSession(true);
			logger.info(" === session id in paymentResponse === " + httpSession.getId());

			paymentResponse = URLDecoder.decode(paymentResponse, "UTF-8");
			String [] paymentResponseArr = paymentResponse.split("\\|");
			logger.info(" === paymentResponseArr in  === " + Arrays.toString(paymentResponseArr));

			String transcationStatus = paymentResponseArr[3];
			logger.info(" === transcationStatus === " + transcationStatus);

			String responseParams = paymentResponse.substring(0, paymentResponse.lastIndexOf('|'));
			logger.info(" ==== responseParams ==== " + responseParams);

			String responseParamsCalculatedChecksum = paymentIntegration.calculateChecksum(responseParams);
			logger.info(" === responseParamsCalculatedChecksum ===== " + responseParamsCalculatedChecksum);

			customerId = paymentResponseArr[0];
			logger.info(" ==== customerId === " + customerId);
			httpSession.setAttribute(Constants.CUSTOMER_ID, customerId);

			String responseChecksum = paymentResponse.substring(paymentResponse.lastIndexOf('|') + 1);
			logger.info(" ==== responseChecksum ==== " + responseChecksum);

			logger.info(" === Calculated Checksum === " + responseParamsCalculatedChecksum + " === responseChecksum === " + responseChecksum);

			if(responseChecksum.equals(responseParamsCalculatedChecksum)){

				logger.info(" === Checksum matched === ");
				JSONObject paymentResJson = formDataService.paymentResponseService(paymentResponseArr, httpSession);
				logger.info(" ===  paymentResJson in paymentResponse === " + paymentResJson);

				String txnStatus = paymentResJson.get(Constants.TRANSACTION_STATUS) == null ? Constants.STATUS_FAIL : paymentResJson.get(Constants.TRANSACTION_STATUS).toString();
				logger.info(" == txnStatus in paymentResponse ===" + txnStatus);

				String customerType = paymentResJson.get(Constants.CUSTOMER_TYPE) == null ? "NA" : paymentResJson.get(Constants.CUSTOMER_TYPE).toString();
				logger.info(" == customerType in paymentResponse ===" + customerType);

				String eventHubpaymentResponse = formDataService.eventHubdataCall(customerId,  Constants.PAYMENTRESPONSE);
				logger.info("==============event Hub payment Response============="+eventHubpaymentResponse);

				httpSession.setAttribute("custType", customerType);

				if("SUCCESS".equalsIgnoreCase(txnStatus)){

					httpSession.setAttribute("transStatus", Constants.STATUS_SUCCESS);
				}else{
					httpSession.setAttribute("transStatus", Constants.STATUS_FAIL);
				}

				logger.info(" === session id in paymentResponse === " + httpSession.getId());

			}else
			{
				Utility.loadNewRelicValidation("Checksum Fail in Payment Response URL", "Checksum Validation fail in PaymentResponse",customerId,Constants.PAYMENTRESPONSE);
			}
		} catch (Exception e) {
			logger.error(" === Exception in paymentResponse === ", e);
			if(!(customerId.isEmpty())){
				dbManipulation.recordExeption("PAYMENT_RESPONSE_CTA","Exception due to internal call", customerId);
			}
			Utility.loadNewRelicException(e.toString(), Constants.PAYMENTRESPONSE, "", customerId);
		}
		return new ModelAndView("fd-sdp-thank-you-page");

	}

	@Trace
	@PostMapping("/getPartnerDetail")
	public String getPartnerDetailVerification(@RequestBody String parnerCodeValue){

		logger.info(" ==== parner Code in getPartnerDetailVerification ==== " + parnerCodeValue);
		String  patnername = "";
		boolean checkPCode=false;
		JSONObject partnercodejson = new JSONObject();

		try {

			JSONObject parnerCodeJson = new JSONObject(parnerCodeValue);
			String partnerCode = parnerCodeJson.get("partnerCode").toString();
			logger.info(" == partnerCode === " + partnerCode);

			if(partnerCode != null){
				String partnerCodeStatus = formFieldValidation.paterCodeValidator(partnerCode);
				logger.info(" === partnerCodeStatus in PartnerCodeVerification === " + partnerCodeStatus);

				if(Constants.STATUS_SUCCESS.equalsIgnoreCase(partnerCodeStatus)){

					logger.info(" === partner code validation successfully === ");

					patnername = formDataService.partnerCodeValidatorService(partnerCode);
					checkPCode= formDataService.AssistedPCodeValidatoService(partnerCode);

					if("fail".equalsIgnoreCase(patnername)){
						partnercodejson.put(Constants.STATUS, Constants.STATUS_FAIL);
						partnercodejson.put("checkPCode", checkPCode);
						logger.info(" === partner code validation failed === ");
					}
					else
					{
						partnercodejson.put(Constants.STATUS, Constants.STATUS_SUCCESS);
						partnercodejson.put("partnerName", patnername);
						partnercodejson.put("checkPCode", checkPCode);
					}

					logger.info(" === patnername in partnerCodeValidateServide === " + patnername);
				}else{
					partnercodejson.put(Constants.STATUS, Constants.STATUS_FAIL);
					logger.info(" === partner code validation failed === ");
					Utility.loadNewRelicValidation("PartnerCode validation fail", "PartnerCode validation fail", "NA","getPartnerDetailVerification");
				}
			}else{
				partnercodejson.put(Constants.STATUS, Constants.STATUS_FAIL);
				partnercodejson.put(Constants.VALIDATION_STATUS, "please enter valid partner code");
				logger.info(" === partner code validation failed === ");
			}

		} catch (Exception e) {
			logger.error(" ===== Exception in getPartnerDetailVerification === ", e);
			Utility.loadNewRelicException(e.toString(), "getPartnerDetailVerification", "", "NA");
		}

		return partnercodejson.toString();
	}

	@GetMapping(value = {"/okycResponse", "/getajaxokycResponse"})
	public ModelAndView okycResponsepost(@RequestParam(name="Response") String okycData, HttpServletRequest request, HttpServletResponse response){

		JSONObject responseJson = new JSONObject();
		String encryptRes = "";
		String investmentType = "";
		String customerId = "";
		try {
			HttpSession httpSession = request.getSession(false);
			if (httpSession != null) {
				httpSession.invalidate();
				logger.info("==Session id before invalidate=======" + httpSession.getId());
				httpSession = request.getSession();

			} else {
				httpSession = request.getSession();
			}
			
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Max-Age", 0);
			response.setHeader("SET-COOKIE", "JSESSIONID=" + httpSession.getId() + "; HttpOnly");
			
			logger.info(" === Session id in okycResponsepost === " + httpSession.getId());
			httpSession.setAttribute("custType", "NTB");
			String Responseokcy = formDataService.okycDatamanupation(okycData, httpSession);
			logger.info("=====okyc Response Processed date=============" + Responseokcy);
			JSONObject responsJson = new JSONObject(Responseokcy);

			JSONObject newobj =responsJson.has("userdata")? responsJson.getJSONObject("userdata"):new JSONObject();

			investmentType = newobj.has("investmentType")? newobj.get("investmentType").toString():"FD";
			String mobileNum=newobj.has("fdUserMobileNumber")? newobj.get("fdUserMobileNumber").toString():"NA";
			customerId = responsJson.get("customerId").toString();
			if("exceptionFail".equalsIgnoreCase(responsJson.get("ntbStatus").toString())){
				return new ModelAndView("fd-sdp-thank-you-page");
			}else if ("success".equalsIgnoreCase(responsJson.get("ntbStatus").toString())) {



				httpSession.setAttribute("customerId", customerId);
				httpSession.setAttribute("investmentType", investmentType);
				httpSession.setAttribute("okycstatus", "okycstatusSucess");
				httpSession.setAttribute("okycstatus", "okycstatusSucess");
				httpSession.setAttribute("fdslID", "fdslFail");

				logger.info("requestname===============" + responsJson.get(Constants.FULL_NAME)
				+ "============session name ===============" + httpSession.getAttribute(Constants.FULL_NAME));

				responseJson.put(Constants.VALIDATION_STATUS, Constants.STATUS_SUCCESS);
				responseJson.put("userDetails", Responseokcy);
				logger.info("responseJson===================" + responseJson);

				encryptRes = Encryption.getEncryptedVal(responseJson);

				logger.info("========Encryption=============" + encryptRes);

				String eventHubpaymentResponse = formDataService.eventHubdataCall(customerId,Constants.OKYCRESPONSE);
				logger.info("==============event Hub okyc Response============="+eventHubpaymentResponse);

				Cookie responseCookie = new Cookie("thankYouData", encryptRes);
				responseCookie.setPath("/");
				responseCookie.setSecure(true);

				response.addCookie(responseCookie);

				return new ModelAndView("fd-sdp-application-form");

			} else {
				
				responseJson.put("fdUserMobileNumber", mobileNum);
				responseJson.put("fdExistingCustId", customerId);
				responseJson.put("investmentType", investmentType);
				responseJson.put("okycstatus", "okycstatusFail");
				responseJson.put("fdslID", "fdslFail");
				responseJson.put("customerId", customerId);
				responseJson.put(Constants.VALIDATION_STATUS, Constants.STATUS_FAIL);
				responseJson.put("validationMsg", "OKYC Fail");
				
				httpSession.setAttribute("fdExistingCustId", customerId);
				httpSession.setAttribute("investmentType", investmentType);
				httpSession.setAttribute("okycstatus", "okycstatusFail");
				httpSession.setAttribute("fdslID", "fdslFail");
				httpSession.setAttribute("customerId", customerId);
				logger.info("responseJson===================" + responseJson);
				encryptRes = Encryption.getEncryptedVal(responseJson);
				logger.info("========Encryption=============" + encryptRes);
				Cookie responseCookie = new Cookie("thankYouData", encryptRes);
				responseCookie.setPath("/");
				responseCookie.setSecure(true);
				response.addCookie(responseCookie);

				return new ModelAndView("fd-sdp-application-form");

			} 
		} catch (Exception e) {
			logger.error("okyc Response get Exception===========",e);
			responseJson.put(Constants.VALIDATION_STATUS, Constants.STATUS_FAIL);
			responseJson.put("validationMsg", "OKYC Fail");
			if(!(customerId.isEmpty())){
				dbManipulation.recordExeption("OKYC_RETURN","Exception due to internal call", customerId);
			}
			Utility.loadNewRelicException(e.toString(), Constants.OKYCRESPONSE, "", customerId);
			return new ModelAndView("fd-sdp-thank-you-page");
		}

	}


	@PostMapping("/retryPay")
	public String paymentRetryPay(@RequestBody String paymentJson, HttpServletRequest request){


		JSONObject paymentReqJson = new JSONObject();
		String encryptRes = "";
		String customerId="";
		try {
			logger.info(" == paymentJson in paymentRetryPay  == " + paymentJson);

			paymentJson = DataEncryption.decrypt(paymentJson);
			byte[] bytes = Hex.decodeHex(paymentJson.toCharArray());
			logger.info("------------json data------------ " + bytes);
			paymentJson = new String(bytes, "UTF-8");

			logger.info("------------paymentJson in paymentRetryPay------------" + paymentJson);

			JSONObject paymentRequestJson = new JSONObject(paymentJson);

			logger.info(" == paymentRequestJson in paymentRetryPay ==== " + paymentRequestJson);

			customerId =  paymentRequestJson.has("customerId")?  paymentRequestJson.get("customerId").toString():"NA" ;
			logger.info(" === customerId paymentRetryPay ==== " + customerId);

			HttpSession httpSession = request.getSession(false);

			FixedDepositData depositData = fixedDepositDao.getFixedDepositData(customerId);

			int retryPayCount = depositData.getRetryPaymentCount() == 0 ? 0 : depositData.getRetryPaymentCount() ;
			if(retryPayCount<2){

				String checksum = formDataService.retryPaymentRequestService(customerId, paymentJson);
				logger.info(" === checksum in paymentRetryPay ====== " + checksum);

				paymentReqJson.put(Constants.STATUS, Constants.STATUS_SUCCESS);
				paymentReqJson.put("checksum", checksum);
				paymentReqJson.put("tryCount", retryPayCount);
			}else{

				paymentReqJson.put(Constants.STATUS, Constants.STATUS_SUCCESS);
				paymentReqJson.put("paylimit","retry payment limit exceed" );
				paymentReqJson.put("tryCount", retryPayCount);
				Utility.loadNewRelicValidation("retry payment limit exceed validation", "retry payment limit exceed ",customerId,"paymentRetryPay");
			}


		} catch (Exception e) {
			logger.info(" == Exception in paymentRequest === ", e);
			if(!(customerId.isEmpty() && "NA".equalsIgnoreCase(customerId))){
				dbManipulation.recordExeption("RETRY_PAYMENT","Exception due to internal call", customerId);
			}
			paymentReqJson.put(Constants.STATUS, Constants.STATUS_FAIL);
			Utility.loadNewRelicException(e.toString(), "paymentRetryPay", "", customerId);
		}

		logger.info(" === paymentReqJson in  paymentRetryPay ==== " + paymentReqJson);
		encryptRes = Encryption.getEncryptedVal(paymentReqJson);
		logger.info(" Encrypted Response === " + encryptRes);

		return encryptRes;


	}

	@PostMapping("/cutsomPrefill")
	public String cutsomPrefiill(@RequestBody String requestJnso, HttpServletRequest request)
	{
		JSONObject response=new JSONObject();
		String encryptRes="";
		String requestCustId="";
		try{

			JSONObject reqVal=new JSONObject(requestJnso);
			String mobileNumber=reqVal.has("mobileNumber")?reqVal.get("mobileNumber").toString():"NA";
			requestCustId = reqVal.get("fdcNumber") == null?"": reqVal.get("fdcNumber").toString();
			logger.info(" ========== requestCustId  ========= " + requestCustId);
			HttpSession httpSession = request.getSession(false);
			logger.info(" ===== Session id in cutsomPrefiill ==== " + httpSession.getId());


			String mobileNumberSession = httpSession.getAttribute(Constants.USER_MOBILE_NUMBER) == null ? "NA" : httpSession.getAttribute(Constants.USER_MOBILE_NUMBER).toString();
			String customerId = (String) httpSession.getAttribute(Constants.CUSTOMER_ID);


			logger.info(" === customerId == "+ customerId );
			logger.info(" == mobileNumberSession === " + mobileNumberSession);

			JSONObject sessionFieldValidation = formFieldValidation.sessionFieldValidation( mobileNumberSession, "", mobileNumber, "",customerId);
			logger.info(" === sessionFieldValidation in cutsomPrefiill ==== " + sessionFieldValidation);

			JSONObject sessionFieldValidationMsg = sessionFieldValidation.getJSONObject("validationMsg");
			if(sessionFieldValidation.has("validationStatus") && sessionFieldValidation.get("validationStatus").toString().equals(Constants.STATUS_SUCCESS))
			{
				String prefillCustId=reqVal.has("prefillCustID")?reqVal.get("prefillCustID").toString():"NA";
				String prefillDob=reqVal.has("dob")?reqVal.get("dob").toString():"NA";
				String userDetails=formDataService.cutsomPrefiill(prefillCustId, prefillDob,mobileNumber,customerId,"cutsomPrefill");
				JSONObject customPrefillresponse=new JSONObject(userDetails);
				String prefillstatus=customPrefillresponse.has(Constants.PIDATA_API)?customPrefillresponse.get(Constants.PIDATA_API).toString():Constants.STATUS_FAIL;
				if(Constants.STATUS_SUCCESS.equalsIgnoreCase(prefillstatus))
				{

					response.put("userDetails", userDetails);
					response.put(Constants.STATUS,Constants.STATUS_SUCCESS);
				}
				else
				{
					response.put(Constants.STATUS,Constants.STATUS_FAIL);

				}
			}else
			{

				response.put("sessionFieldValidationMsg", sessionFieldValidationMsg);
				response.put(Constants.STATUS,Constants.STATUS_FAIL);
			}
		}catch(Exception e)
		{
			logger.error(" === Exception in cutsomPrefiill === ", e);
			if(!( requestCustId.isEmpty())){
				dbManipulation.recordExeption("DEDUPE_PREFILL","Exception due to internal call", requestCustId);
			}
			response.put(Constants.STATUS,Constants.STATUS_FAIL);
		}
		logger.info(" === Return in cutsomPrefiill ==== " + response);
		encryptRes = Encryption.getEncryptedVal(response);
		logger.info(" Encrypted Response === " + encryptRes);
		return encryptRes;

	}


	@PostMapping(value = "/documetUploadNTB", consumes = {"multipart/form-data"})
	public  @ResponseBody String documetUploadNTB(

			@RequestParam(value = "idententiyDocFrontFile") MultipartFile idententiyDocFrontFile,
			@RequestParam(value = "idententiyDocBackFile") MultipartFile idententiyDocBackFile,
			@RequestParam(value = "addressDocFrontFile") MultipartFile addressDocFrontFile,
			@RequestParam(value = "addressDocBackFile") MultipartFile addressDocBackFile,
			@RequestParam(value = "SignDocFile") MultipartFile SignDocFile,
			@RequestParam(value = "PhotoDocFile") MultipartFile PhotoDocFile,
			@RequestParam(value = "indentityDocumentSelected") String indentityDocumentSelected,
			@RequestParam(value = "addressDocumentSelected") String addressDocumentSelected,
			@RequestParam(value = "signDocumentSelected") String signDocumentSelected,
			@RequestParam(value = "addprf") String addprf,
			@RequestParam(value = "signprf") String signprf,
			@RequestParam(value = "mobileNumber") String mobileNumber,
			@RequestParam(value = "custId") String custId,
			HttpServletRequest request, HttpServletResponse response){


		JSONObject responseJson = new JSONObject();
		String encryptedVal="";
		String customerId="";
		try {

			HttpSession httpSession = request.getSession(false);
			if (httpSession == null) {
				responseJson.put("apiStatus", (Object)"sessionExpired");
			}
			else {
				logger.info(" === session id in documetUploadNTB === " + httpSession.getId());

				String mobileNumberSession = httpSession.getAttribute(Constants.USER_MOBILE_NUMBER) == null ? "NA" : httpSession.getAttribute(Constants.USER_MOBILE_NUMBER).toString();
				customerId = (String) httpSession.getAttribute(Constants.CUSTOMER_ID);

				logger.info(" === customerId == "+ customerId +" == mobileNumber === " + mobileNumber );
				logger.info(" == mobileNumberSession === " + mobileNumberSession);

				if(mobileNumberSession.equalsIgnoreCase(mobileNumber) && customerId.equalsIgnoreCase(custId))
				{
					JSONObject reqJson = new JSONObject();
					reqJson.put("indentityDocumentSelected", indentityDocumentSelected);
					reqJson.put("addressDocumentSelected", addressDocumentSelected);
					reqJson.put("signDocumentSelected", signDocumentSelected);
					reqJson.put("addprf", addprf);
					reqJson.put("signprf", signprf);
					reqJson.put("mobileNumber", mobileNumber);
					reqJson.put("custId", custId);

					Map<String,MultipartFile> listOfFiles = new HashMap<String,MultipartFile>();

					listOfFiles.put("idententiyDocFrontFile", idententiyDocFrontFile);
					listOfFiles.put("idententiyDocBackFile", idententiyDocBackFile);
					listOfFiles.put("addressDocFrontFile", addressDocFrontFile);
					listOfFiles.put("addressDocBackFile", addressDocBackFile);
					listOfFiles.put("SignDocFile", SignDocFile);
					listOfFiles.put("PhotoDocFile", PhotoDocFile);

					logger.info(" === Request Json in documetUploadNTB ==== " + reqJson);
					JSONObject fieldValidationStatus  = formFieldValidation.formFieldValidation(reqJson.toString());
					logger.info(" == fieldValidationStatus in documetUploadNTB === " + fieldValidationStatus);

					JSONObject validationMsg = (JSONObject) fieldValidationStatus.get("validationMsg");

					String validationStatus = fieldValidationStatus.get(Constants.VALIDATION_STATUS) == null?Constants.STATUS_FAIL:fieldValidationStatus.get(Constants.VALIDATION_STATUS).toString();
					logger.info(" === validationStatus in documetUploadNTB === " + validationStatus);

					if(Constants.STATUS_SUCCESS.equalsIgnoreCase(validationStatus))
					{
						logger.info(" === Field Validate Successfully == ");


						JSONObject docFileValidation = formFieldValidation.documentUploadFileValidation(listOfFiles,custId);
						logger.info(" === sessionFieldValidation in addUserData ==== " + docFileValidation);

						JSONObject sessionFieldValidationMsg = docFileValidation.getJSONObject("validationMsg");

						if(docFileValidation.has("validationStatus") && docFileValidation.get("validationStatus").toString().equals(Constants.STATUS_SUCCESS))
						{
							responseJson.put(Constants.VALIDATION_STATUS, Constants.STATUS_SUCCESS);
							String uploadFileSotreStatus = formDataService.storeUploadedCustomerDocuments(listOfFiles, customerId,reqJson);
							logger.info(" === uploadFileSotreStatus in documetUploadNTB ==== " + uploadFileSotreStatus);

							logger.info(" == File uploaded files in documetUploadNTB  === " + listOfFiles);
							if(Constants.STATUS_SUCCESS.equalsIgnoreCase(uploadFileSotreStatus))
							{
								String dbStatus=formDataService.uploadDocService(reqJson.toString(), customerId);
								responseJson.put(Constants.VALIDATION_STATUS, Constants.STATUS_SUCCESS);
								responseJson.put("userDetails",dbStatus);
							}else
							{
								responseJson.put(Constants.VALIDATION_STATUS, Constants.STATUS_FAIL);
							}
						}
						else
						{
							responseJson.put(Constants.VALIDATION_STATUS, Constants.STATUS_FAIL);
							responseJson.put("validationMsg", sessionFieldValidationMsg);
							Utility.loadNewRelicValidation("document Validation fail in document Upload", "document validation in Document Upload",custId,Constants.DOCUPLOAD);

						}

					}else
					{
						responseJson.put(Constants.VALIDATION_STATUS, Constants.STATUS_FAIL);
						responseJson.put("validationMsg", validationMsg);
						Utility.loadNewRelicValidation("Field Validation fail in document Upload", "field validation in Document Upload",custId,Constants.DOCUPLOAD);

					}
				}
				else
				{
					logger.info(" === mobile number not matched in documetUploadNTB === ");
					responseJson.put(Constants.VALIDATION_STATUS, Constants.STATUS_FAIL);
					responseJson.put(Constants.VALIDATION_MESSAGE, "mobile number not matched");
					Utility.loadNewRelicValidation("Front end mobile number does not matched with session mobile number in document Upload", "field validation in Document Upload",custId,Constants.DOCUPLOAD);
				}

			}
		} catch (Exception e)
		{
			if(!( custId.isEmpty()))
			{
				dbManipulation.recordExeption("DOCUMENTUPLOAD","Exception due to internal call", custId);
			}
			responseJson.put(Constants.VALIDATION_STATUS, Constants.STATUS_FAIL);
			logger.error(" === Exception in documetUploadNTB === ", e);
			Utility.loadNewRelicException(e.toString(), Constants.DOCUPLOAD, "", custId);
		}
		logger.info(" == Return Response in documetUploadNTB === " + responseJson);
		encryptedVal = Encryption.getEncryptedVal(responseJson);
		return encryptedVal;
	}
	
	@Trace
	@PostMapping("/getPrepopulateCityChangeOnPincode")
	public String getPrepopulateCityChangeOnPincode(@RequestBody String pinCodeJson){

		logger.info(" ==== REQ in getPrepopulateCityChangeOnPincode ==== " + pinCodeJson);
		String  commRes = "";
		JSONObject ResJson = new JSONObject();
		JSONObject pincodeJson = new JSONObject(pinCodeJson);
		String fdcNumber = pincodeJson.get("fdcNumber").toString();
		String eventFor ="";
		
		try {
			eventFor =(pincodeJson.has("evnt"))? pincodeJson.get("evnt").toString():"NA";
			String commPincode = pincodeJson.get("pinCodePD").toString();
			logger.info(" == pincode === " + commPincode);

			if(commPincode != null){
				String commpinStatus = formFieldValidation.pincodeValidator(commPincode);
				logger.info(" === pincodeStatus in pincodeVerification === " + commpinStatus);

				if(Constants.STATUS_SUCCESS.equalsIgnoreCase(commpinStatus)){

					logger.info(" === pincode field validation successfully === ");

					commRes = formDataService.getcommunicationCityService(commPincode,fdcNumber);
					JSONObject res = new JSONObject(commRes);
					logger.info(" === pinStatus in pincodeVerification === " + res);
					String status = res.has("status") ? res.get("status").toString() : "NA";
					if(Constants.STATUS_SUCCESS.equalsIgnoreCase(status)) {
					String city = res.has("city") ? res.get("city").toString() : "NA";
					String state = res.has("state") ? res.get("state").toString() : "NA";
						if(!("NA".equalsIgnoreCase(city))) 
							{
								ResJson.put(Constants.STATUS, Constants.STATUS_SUCCESS);
								ResJson.put(Constants.CITY,city);
								ResJson.put("state",state);
							}else{
								ResJson.put(Constants.STATUS, Constants.STATUS_FAIL);
								logger.info(" === Pincode not matched === ");
								Utility.loadNewRelicValidation("Pincode not matched "+eventFor, "Pincode not matched", fdcNumber,"getPrepopulateCityChangeOnPincode");
							}
						}
						else {
							ResJson.put(Constants.STATUS, Constants.STATUS_FAIL);
							logger.info(" === service exception === ");
							Utility.loadNewRelicValidation("Pincode service failed "+eventFor, "Pincode service failed", fdcNumber,"getPrepopulateCityChangeOnPincode");
						}
				
					}else{
						ResJson.put(Constants.STATUS, Constants.STATUS_FAIL);
						logger.info(" === pincode validation failed === ");
						Utility.loadNewRelicValidation("Pincode field validation failed "+eventFor, "Pincode field validation failed", fdcNumber,"getPrepopulateCityChangeOnPincode");
					}
				}else{
					ResJson.put(Constants.STATUS, Constants.STATUS_FAIL);
					ResJson.put(Constants.VALIDATION_STATUS, "please enter pincode");
					logger.info(" === pincode validation failed === ");
				} 

		} catch (Exception e) {
			logger.error(" ===== Exception in getPrepopulateCityChangeOnPincode === ", e);
			ResJson.put(Constants.STATUS, Constants.STATUS_FAIL);
			Utility.loadNewRelicException(e.toString(), "getPrepopulateCityChangeOnPincode "+eventFor, fdcNumber, "NA");
		}
		
		logger.info(" ==== REQ in getPrepopulateCityChangeOnPincode ==== " +ResJson.toString());
		return ResJson.toString();
	}
	
	
	
	
	
}
