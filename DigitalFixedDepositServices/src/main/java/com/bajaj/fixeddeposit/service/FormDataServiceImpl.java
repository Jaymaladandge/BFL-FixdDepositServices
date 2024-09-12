package com.bajaj.fixeddeposit.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;
import java.util.UUID;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

import com.bajaj.fixeddeposit.api.CustIdIntegration;
import com.bajaj.fixeddeposit.api.PaymentIntegration;
import com.bajaj.fixeddeposit.api.ResponsysIntegration;
import com.bajaj.fixeddeposit.api.SfdcIntegration;
import com.bajaj.fixeddeposit.dao.FixedDepositDao;
import com.bajaj.fixeddeposit.model.AssistedPartnerCode;
import com.bajaj.fixeddeposit.model.BankNameDetails;
import com.bajaj.fixeddeposit.model.FDPincodeMaster;
import com.bajaj.fixeddeposit.model.FixedDepositData;
import com.bajaj.fixeddeposit.model.IMPSFixedDepositData;
import com.bajaj.fixeddeposit.model.IfscCode;
import com.bajaj.fixeddeposit.model.JsonResponse;
import com.bajaj.fixeddeposit.model.PartnerCode;
import com.bajaj.fixeddeposit.model.sfdc.AdressDetailsList;
import com.bajaj.fixeddeposit.model.sfdc.FDRecordsObjList;
import com.bajaj.fixeddeposit.model.sfdc.ObjApplDetail;
import com.bajaj.fixeddeposit.model.sfdc.ObjFDDetails;
import com.bajaj.fixeddeposit.model.sfdc.ObjInvestmentAccDetails;
import com.bajaj.fixeddeposit.model.sfdc.ObjKycDoc;
import com.bajaj.fixeddeposit.model.sfdc.ObjMaturityAccDetails;
import com.bajaj.fixeddeposit.model.sfdc.RecWrapperFD;
import com.bajaj.fixeddeposit.model.sfdc.SfdcRequest;
import com.bajaj.fixeddeposit.model.sfdc.SingleSignOn;
import com.bajaj.fixeddeposit.util.Constants;
import com.bajaj.fixeddeposit.util.DbManipulationUtil;
import com.bajaj.fixeddeposit.util.Utility;
import com.bajaj.fixeddeposit.util.kycEncryDecryp;
import com.bajaj.fixeddeposit.validation.FormFieldValidation;
import com.bajaj.fixeddeposit.validation.InterestRateValidation;
import com.bajaj.fixeddeposit.validation.SingleFieldValidation;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newrelic.api.agent.Trace;


@Service("fromDataService")
public class FormDataServiceImpl implements FormDataService {

	private static final Logger logger = Logger.getLogger(FormDataServiceImpl.class);

	private static String UPLOAD_LOCATION="/softs/clover/DigitalFixedDepositServices/CustomerDocuments/"+new SimpleDateFormat("dd-MM-yyyy").format(new Date())+"/";

	@Autowired
	private FixedDepositDao fixedDepositDao;

	@Autowired
	private InterestRateValidation interestRateValidation;

	@Autowired
	private PaymentIntegration paymentIntegration;

	@Autowired
	private ResponsysIntegration responsysIntegration; 

	@Autowired
	private SfdcIntegration sfdcIntegration;

	@Autowired
	private CustIdIntegration custIdIntegration;

	@Autowired
	private DataDownloadService dataDownloadService;

	@Autowired
	private DbManipulationUtil dbManipulation;

	private String schemeCode;

	@Autowired
	private SingleFieldValidation validation;

	@Autowired
	private OtpServiceImpl otpServiceImpl;
	
	@Autowired
	FormFieldValidation formFieldValidation;


	@Trace
	public String addNtbUserDataService(String requestJson, String customerId, HttpSession httpSession) throws JSONException {

		logger.info(" == requestJson in addNtbUserDataService === " + requestJson);
		logger.info(" === Customer id in addNtbUserDataService === " + customerId);

		String updateStatus = "";
		String state ="";
		String city ="";
		String ntbFlag= "";
		int validatePANCount;
		String requestCustId="";
		JSONObject userDetails = new JSONObject();
		JSONObject ntbDeatilJson = new JSONObject();
		String requestTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime()) ;
		logger.info(" === requestTime in addNtbUserDataService === " + requestTime);
		try {

			JSONObject userData = new JSONObject(requestJson);

			String fullName = userData.get("fullname") == null ?"NA" : userData.get("fullname").toString();
			String panCard = userData.get("panCard") == null ?"NA" : userData.get("panCard").toString();
			requestCustId = userData.has("fdcNumber") ?userData.get("fdcNumber").toString():"";
			String pincode = userData.has("pincode") ? userData.get("pincode").toString():"NA";

			FixedDepositData depositData  = fixedDepositDao.getFixedDepositData(customerId);
			logger.info(" == depositData in addNtbUserDataService ==== " + depositData);



			depositData.setPanCard(panCard);
			depositData.setFullName(fullName);
			depositData.setCkcyVerifyTime(requestTime);
			if(!"NA".equalsIgnoreCase(pincode))
			{

				int reqPin = Integer.parseInt(pincode);
				FDPincodeMaster fdPiMast = fixedDepositDao.getcitystatename(reqPin);

				if(fdPiMast != null){
					city = fdPiMast.getCity();
					state = fdPiMast.getState();

				}else{
					city ="NA";
					state = "NA";
				}
				depositData.setPinCode(pincode);
				depositData.setCity(city);
				depositData.setState(state);
			}
			
			userDetails.put("customerId", customerId);

			String cKycsearch = custIdIntegration.CkycSearchApiIntegrationNew(panCard, customerId);
		    JSONObject cKycsearchApiRespons = new JSONObject(cKycsearch);
			String cKycsearchRespons =cKycsearchApiRespons.has("response")? cKycsearchApiRespons.get("response").toString() : "searchFail";
			String cKycsearchException =cKycsearchApiRespons.has("Exception")? cKycsearchApiRespons.get("Exception").toString() : "";
			String cKycsearchRejectionCode =cKycsearchApiRespons.has("RejectedCode")? cKycsearchApiRespons.get("RejectedCode").toString() : "";
			logger.info("===========cKycsearchRespons==========="+cKycsearchRespons);
			logger.info("========cKycsearchException=============="+cKycsearchException);
			logger.info("========cKycsearchRejectionCode=============="+cKycsearchRejectionCode);
			if((cKycsearchException.equalsIgnoreCase("java.net.SocketTimeoutException: Read timed out") || cKycsearchRejectionCode.equalsIgnoreCase("EC2143") || cKycsearchRejectionCode.equalsIgnoreCase("EEC2147")) && cKycsearchRespons.equalsIgnoreCase("searchFail"))
			{
				Thread.sleep(15000);
				String cKycRetriggersearch = custIdIntegration.CkycSearchApiIntegrationNew(panCard,customerId);
				JSONObject cKycsearchRetriggerApiRespons = new JSONObject(cKycRetriggersearch);
				 cKycsearchRespons =cKycsearchRetriggerApiRespons.has("response")? cKycsearchRetriggerApiRespons.get("response").toString() : "searchFail";
				 cKycsearchException =cKycsearchRetriggerApiRespons.has("Exception")? cKycsearchRetriggerApiRespons.get("Exception").toString() : "";
				 cKycsearchRejectionCode =cKycsearchRetriggerApiRespons.has("RejectedCode")? cKycsearchRetriggerApiRespons.get("RejectedCode").toString() : "";
				logger.info("===========cKycsearchRespons 1==========="+cKycsearchRespons);
				logger.info("========cKycsearchException 1=============="+cKycsearchException);
				logger.info("========cKycsearchRejectionCode 1=============="+cKycsearchRejectionCode);
				if((cKycsearchException.equalsIgnoreCase("java.net.SocketTimeoutException: Read timed out") || cKycsearchRejectionCode.equalsIgnoreCase("EC2143") || cKycsearchRejectionCode.equalsIgnoreCase("EEC2147")) && cKycsearchRespons.equalsIgnoreCase("searchFail"))
				{
					Thread.sleep(15000);
					String cKycRetrigsearch = custIdIntegration.CkycSearchApiIntegrationNew(panCard,customerId);
					JSONObject cKycsearchRetrigApiRespons = new JSONObject(cKycRetrigsearch);
					 cKycsearchRespons =cKycsearchRetrigApiRespons.has("response")? cKycsearchRetrigApiRespons.get("response").toString() : "searchFail";
					 cKycsearchException =cKycsearchRetrigApiRespons.has("Exception")? cKycsearchRetrigApiRespons.get("Exception").toString() : "";
					 cKycsearchRejectionCode =cKycsearchRetrigApiRespons.has("RejectedCode")? cKycsearchRetrigApiRespons.get("RejectedCode").toString() : "";
					logger.info("===========cKycsearchRespons 2==========="+cKycsearchRespons);
					logger.info("========cKycsearchException 2=============="+cKycsearchException);
					logger.info("========cKycsearchRejectionCode 2=============="+cKycsearchRejectionCode);
					
				}
			}
			logger.info(" ===  cKycsearchRespons in addNtbUserDataService == " + cKycsearchRespons);
			String ckcyRequestTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime()) ;
			logger.info(" ===  ckcyRequestTime in addNtbUserDataService == " + ckcyRequestTime);

			if(!("searchFail".equalsIgnoreCase(cKycsearchRespons))){

				JSONObject searchApirequestJson = new JSONObject(cKycsearchRespons);

				String CKYCAvailable = searchApirequestJson.get("CKYCAvailable").toString();

				if("Yes".equalsIgnoreCase(CKYCAvailable)){

					String ckycRequestId = searchApirequestJson.get("CKYCID").toString();
					logger.info("==========ckycRequestId=============="+ ckycRequestId);

					String ckycDOB = depositData.getDateOfBirth();
					String mobNumber = depositData.getMobileNumber();

					SimpleDateFormat formatterDob = new SimpleDateFormat("dd/MM/yyyy");
					SimpleDateFormat formatter1DDob = new SimpleDateFormat("dd-MM-yyyy");

					Date dateDOB = formatterDob.parse(ckycDOB);

					logger.info(formatterDob.format(dateDOB));

					String dobFormatednew=formatter1DDob.format(dateDOB); 

					logger.info("============dob for CKYC================ "+dobFormatednew);
					String downloalKycApiResponse = custIdIntegration.CkycDownloadApiIntegrationNew(ckycRequestId, dobFormatednew, mobNumber, customerId);
		            JSONObject downloalKycApiRespons = new JSONObject(downloalKycApiResponse);
					String downloalKycResponse =downloalKycApiRespons.has("response")? downloalKycApiRespons.get("response").toString() : "searchFail";
					String downloalKycException =downloalKycApiRespons.has("Exception")? downloalKycApiRespons.get("Exception").toString() : "";
					String downloalKycRejectionCode =downloalKycApiRespons.has("RejectedCode")? downloalKycApiRespons.get("RejectedCode").toString() : "";
					logger.info(" ===  downloalKycResponse == " + downloalKycResponse);
					logger.info("========downloalKycException=============="+downloalKycException+"=========downloalKycRejectionCode=========="+downloalKycRejectionCode);
					if((downloalKycException.equalsIgnoreCase("java.net.SocketTimeoutException: Read timed out") || downloalKycRejectionCode.equalsIgnoreCase("EC2143") || downloalKycRejectionCode.equalsIgnoreCase("EEC2147")) && downloalKycResponse.equalsIgnoreCase("searchFail"))
					{
						Thread.sleep(15000);
						String cKycRetrigdownload = custIdIntegration.CkycDownloadApiIntegrationNew(ckycRequestId, dobFormatednew, mobNumber,customerId);
						JSONObject cKycdownloadRetrigApiRespons = new JSONObject(cKycRetrigdownload);
						downloalKycResponse =cKycdownloadRetrigApiRespons.has("response")? cKycdownloadRetrigApiRespons.get("response").toString() : "searchFail";
						downloalKycException =cKycdownloadRetrigApiRespons.has("Exception")? cKycdownloadRetrigApiRespons.get("Exception").toString() : "";
						downloalKycRejectionCode =cKycdownloadRetrigApiRespons.has("RejectedCode")? cKycdownloadRetrigApiRespons.get("RejectedCode").toString() : "";
						logger.info("===========downloalKycResponse 1==========="+downloalKycResponse);
						logger.info("========downloalKycException 1=============="+downloalKycException+"=========downloalKycRejectionCode 1=========="+downloalKycRejectionCode);
						if((downloalKycException.equalsIgnoreCase("java.net.SocketTimeoutException: Read timed out") || downloalKycRejectionCode.equalsIgnoreCase("EC2143") || downloalKycRejectionCode.equalsIgnoreCase("EEC2147")) && downloalKycResponse.equalsIgnoreCase("searchFail"))
						{
							Thread.sleep(15000);
							String cKycRetrigdown = custIdIntegration.CkycDownloadApiIntegrationNew(ckycRequestId, dobFormatednew, mobNumber,customerId);
							JSONObject cKycdownRetrigApiRespons = new JSONObject(cKycRetrigdown);
							downloalKycResponse =cKycdownRetrigApiRespons.has("response")? cKycdownRetrigApiRespons.get("response").toString() : "searchFail";
							downloalKycException =cKycdownRetrigApiRespons.has("Exception")? cKycdownRetrigApiRespons.get("Exception").toString() : "";
							downloalKycRejectionCode =cKycdownRetrigApiRespons.has("RejectedCode")? cKycdownRetrigApiRespons.get("RejectedCode").toString() : "";
							logger.info("===========downloalKycResponse 2==========="+downloalKycResponse);
							logger.info("========downloalKycException 2=============="+downloalKycException+"=========downloalKycRejectionCode 2=========="+downloalKycRejectionCode);
							
						}
					}
		            logger.info(" ===  downloalKycResponse in addNtbUserDataService == " + downloalKycResponse);

					String ckcyResponseTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime()) ;
					logger.info(" ===  ckcyResponseTime in addNtbUserDataService == " + ckcyResponseTime);

					if(!("downloadFail".equalsIgnoreCase(downloalKycResponse))){

						JSONObject downloalKycResponseSting = new JSONObject(downloalKycResponse);

						String downloadStatus = downloalKycResponseSting.has("downloadStatus") ? downloalKycResponseSting.get("downloadStatus").toString() : "failMobDoc";

						if(!("failMobDoc".equalsIgnoreCase(downloadStatus))){

							String dedupeCustType = downloalKycResponseSting.has(Constants.DEDUPE_CUST_TYPE)? downloalKycResponseSting.get(Constants.DEDUPE_CUST_TYPE).toString(): "NTB";
							String existingCustId = ckycRequestId;

							String fullNameCkyc = downloalKycResponseSting.has(Constants.FULL_NAME) ? downloalKycResponseSting.get(Constants.FULL_NAME).toString() : "NA";
							httpSession.setAttribute(Constants.FULL_NAME, fullNameCkyc);
							city = downloalKycResponseSting.has(Constants.CITY)? downloalKycResponseSting.get(Constants.CITY).toString(): "NA";
							String gender = downloalKycResponseSting.has("gender") ? downloalKycResponseSting.get("gender").toString() :"M";
							String ckycPincode = downloalKycResponseSting.has(Constants.PIN)? downloalKycResponseSting.get(Constants.PIN).toString():"NA";
							String filepath= downloalKycResponseSting.has("saveFilePath")? downloalKycResponseSting.get("saveFilePath").toString() : "";


							int reqPin = 0;
							if(!("NA".equalsIgnoreCase(ckycPincode))){

								reqPin = Integer.parseInt(ckycPincode);
								FDPincodeMaster fdPiMast = fixedDepositDao.getcitystatename(reqPin);
								if(fdPiMast != null){
									city = fdPiMast.getCity();
									state = fdPiMast.getState();
								}else{
									city ="NA";
									state = "NA";
								}
								depositData.setCity(city);
								depositData.setState(state);
							}
							depositData.setGender(gender);
							userDetails.put(Constants.FULL_NAME, fullNameCkyc);
							userDetails.put(Constants.ADDRESS,downloalKycResponseSting.has(Constants.ADDRESS) ?  downloalKycResponseSting.get(Constants.ADDRESS).toString():"NA" );
							userDetails.put(Constants.DATE_OF_BIRTH, ckycDOB);
							userDetails.put(Constants.EXISTING_CUST_ID, existingCustId);
							userDetails.put(Constants.PIN,ckycPincode);
							userDetails.put(Constants.CITY, city);
							userDetails.put(Constants.PAN, panCard);
							userDetails.put(Constants.USER_MOBILE_NUMBER, mobNumber);
							userDetails.put(Constants.DEDUPE_CUST_TYPE, dedupeCustType);
							userDetails.put(Constants.CUST_TYPE, downloalKycResponseSting.has(Constants.CUST_TYPE) ?  downloalKycResponseSting.get(Constants.CUST_TYPE).toString():"NA" );
							ntbDeatilJson.put("userdata", userDetails);
							ntbDeatilJson.put("ntbStatus", Constants.STATUS_SUCCESS);

							depositData.setExistingCustId(existingCustId);
							depositData.setDedupeCustomerType(dedupeCustType);
							depositData.setCkycStatus("YES");
							depositData.setRemarkCustType("CKYC");

							depositData.setFullName(fullNameCkyc);
							depositData.setPanCard(panCard);
							depositData.setAddress(downloalKycResponseSting.has(Constants.ADDRESS) ?  downloalKycResponseSting.get(Constants.ADDRESS).toString():"NA" );
							depositData.setKycVerifyStatus("CKYC_SUCCESS");
							depositData.setCkycRequestDate(ckcyRequestTime);
							depositData.setCkycResponseDate(ckcyResponseTime);
							depositData.setCkycDownloadDate(ckcyResponseTime);
							depositData.setServrFilePath(filepath);

						}else{

							ntbFlag = "NTBTrue";
							userDetails.put(Constants.DEDUPE_CUST_TYPE, "NTB");
							ntbDeatilJson.put("userdata", userDetails);
							ntbDeatilJson.put("ntbStatus", Constants.STATUS_SUCCESS);
						}

					}else{


						ntbFlag = "NTBTrue";
						userDetails.put(Constants.DEDUPE_CUST_TYPE, "NTB");
						ntbDeatilJson.put("userdata", userDetails);
						ntbDeatilJson.put("ntbStatus", Constants.STATUS_SUCCESS);
					}


					logger.info("=============downloalKycResponse================="+userDetails.toString());

				}

			}else{


				ntbFlag = "NTBTrue";
				userDetails.put(Constants.DEDUPE_CUST_TYPE, "NTB");
				ntbDeatilJson.put("userdata", userDetails);
				ntbDeatilJson.put("ntbStatus", Constants.STATUS_SUCCESS);
			}
			
			depositData.setJourneyStepName("CKYCVERIFY");

			httpSession.setAttribute("userDetails", userDetails.toString());


			if("NTBTrue".equalsIgnoreCase(ntbFlag)){

				byte[] encryptKey = "1234567890123456".getBytes();
				String iv = "dfghjuytrfgtyhuj";
				SecretKeySpec skeySpec = new SecretKeySpec(encryptKey, "AES");
				byte[] cipherText=kycEncryDecryp.encryptOp(panCard.getBytes(), skeySpec, iv.getBytes());  
				String encriptPan=Base64.getEncoder().encodeToString(cipherText);
				String requesstPAN=encriptPan.replace('+', '~');

				byte[] cipherTextProduct=kycEncryDecryp.encryptOp("FDR".getBytes(), skeySpec, iv.getBytes());  
				String encriptProduct=Base64.getEncoder().encodeToString(cipherTextProduct);
				String requesstProduct=encriptProduct.replace('+', '~');


				logger.info("=======requesstPAN====="+requesstPAN+"=====requesstProduct===="+requesstProduct);

				String panvalidationAPI = NSDLCallForPAN(panCard,customerId,Constants.ADDNTBUSERDATA);

				logger.info("=========panvalidationAPI========="+panvalidationAPI);

				String panVarifyName = "";
				String okycVerifydetails ="";

				JSONObject panJson = new JSONObject(panvalidationAPI);

				String panStatus = panJson.has("nsdlStatus")? panJson.get("nsdlStatus").toString():"fail";
				String decriptResponse = panJson.has("decriptResponse")? panJson.get("decriptResponse").toString():"NSDL Api fail";




				if("sucess".equalsIgnoreCase(panStatus)){

					panVarifyName = panJson.has("panVarifyName")? panJson.get("panVarifyName").toString():"";
					depositData.setNsdlFullName(panVarifyName);
					depositData.setNsdlApiResponse(decriptResponse);

					logger.info("panVarifyName is match with user Enter name");

					ntbDeatilJson.remove("userDetails");

					String requesstJsonobj = Utility.getReqEncryptedString(customerId);

					logger.info("==============final encript json resuest================"+requesstJsonobj);

					userDetails.put(Constants.DEDUPE_CUST_TYPE, "okyc_success");
					userDetails.put("Request", requesstJsonobj);
					ntbDeatilJson.put("userdata", userDetails);
					ntbDeatilJson.put("ntbStatus", Constants.STATUS_SUCCESS);

				}else if("notValidResponse".equalsIgnoreCase(panStatus))
				{
					okycVerifydetails = (panJson.has("details") ? panJson.get("details").toString() : "Please enter valid PAN");
					userDetails.put(Constants.DEDUPE_CUST_TYPE, "panRecordNOtFound");
					userDetails.put("Request", okycVerifydetails);
					ntbDeatilJson.put("ntbStatus", Constants.STATUS_SUCCESS);
					validatePANCount = httpSession.getAttribute("panvalicationcont") !=null ? Integer.parseInt(httpSession.getAttribute("panvalicationcont").toString())+1:1;
					httpSession.setAttribute("panvalicationcont", validatePANCount);
					depositData.setNsdlApiResponse(decriptResponse);
					depositData.setJourneyStepName("NSDL failure");
					logger.info("=======validatePANCount======="+validatePANCount);
				}else{
					userDetails.put(Constants.DEDUPE_CUST_TYPE, "okyc_fail");
					String requesstJsonobj = Utility.getReqEncryptedString(customerId); 	
					logger.info("==============final encript json resuest================"+requesstJsonobj);
					userDetails.put("requestString", requesstJsonobj); 	
					ntbDeatilJson.put("userdata", userDetails);
					ntbDeatilJson.put("ntbStatus", Constants.STATUS_SUCCESS);
					validatePANCount = httpSession.getAttribute("panvalicationcont") !=null ? Integer.parseInt(httpSession.getAttribute("panvalicationcont").toString())+1:1;
					httpSession.setAttribute("panvalicationcont", validatePANCount);
					depositData.setNsdlApiResponse(decriptResponse);
					depositData.setJourneyStepName("NSDL failure");
					logger.info("=======validatePANCount======="+validatePANCount);
				}
			}
			
			String panNameStatus = fixedDepositDao.updateFixedDeposit(depositData);
			logger.info("=================Final DB update Status ========"+panNameStatus);

		} catch (Exception e) {
			Utility.loadNewRelicException(e.toString(), "addNtbUserData service", "", customerId);
			logger.error(" == Exception in addNtbUserDataService === ",e);
			if(!( requestCustId.isEmpty())){
				dbManipulation.recordExeption("CKYC_SERVICE","Exception due to internal call", requestCustId);
			}
		}

		return userDetails.toString();
	}








	@Override
	public String addUserDataService(String requestJson, String customerId,MultipartFile commAddDoc) throws JSONException {

		logger.info(" == requestJson in addUserDataService === " + requestJson);
		logger.info(" === Customer id in addUserDataService === " + customerId);

		String updateStatus = "";
		String nomineeState ="";
		String nomineeCity ="";
		String nomineeGuardianState ="";
		String nomineeGuardianCity ="";
		String ifscStatus = "";
		String storeStatus = "";
		String requestCustId="";		
		
		JSONObject responseVal = new JSONObject();
		try {

			JSONObject userData = new JSONObject(requestJson);
			requestCustId = userData.has("fdcNumber") ?userData.get("fdcNumber").toString():"";
			String custId = userData.has("existingCustId") ? userData.get("existingCustId").toString() : "";
			String requestTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime()) ;
			logger.info(" === requestTime in addNtbUserDataService === " + requestTime);
			String fullName = userData.get("fullName") == null ?"NA" : userData.get("fullName").toString();
			String mobileNumber = userData.get("mobileNumber") == null ?"NA" : userData.get("mobileNumber").toString();
			String emailAddress = userData.get("emailAddress") == null ?"NA" : userData.get("emailAddress").toString();
			String dateOfBirth = userData.get("dateOfBirth") == null ?"NA" : userData.get("dateOfBirth").toString();
			String panCard = userData.get("panCard") == null ?"NA" : userData.get("panCard").toString();
			String address = userData.get("address") == null ?"NA" : userData.get("address").toString();
			String pincode = userData.get("pincode") == null ?"NA" : userData.get("pincode").toString();
			String nomineeCheck = userData.get("nomineeCheck") == null ?"NA" : userData.get("nomineeCheck").toString();
			String nomineeName = userData.get("nomineeName") == null ?"NA" : userData.get("nomineeName").toString();
			String nomineedob = userData.get("nomineedob") == null ?"NA" : userData.get("nomineedob").toString();
			String nomineeRelation = userData.get("nomineeRelation") == null ?"NA" : userData.get("nomineeRelation").toString();
			String nomineeAddressCheck = userData.get("nomineeAddresCheck") == null ?"NA" : userData.get("nomineeAddresCheck").toString();
			String nomineeAddress = userData.get("nomineeAddress") == null ?"NA" : userData.get("nomineeAddress").toString();

			String guardianName = userData.get("guardianName") == null ?"" : userData.get("guardianName").toString();
			String gaurdianAge = userData.get("guardianAge") == null ? "" : userData.get("guardianAge").toString();
			String relationshipNomineeGuardian = userData.get("relationshipNomineeGuardian") == null ?"NA" : userData.get("relationshipNomineeGuardian").toString();
			String guardianAddress = userData.get("guardianAddress") == null ?"NA" : userData.get("guardianAddress").toString();
			String guardinPresent=userData.get("guardinPresent") == null ?"NA" : userData.get("guardinPresent").toString();
			String nomineePincode=userData.get("nomineePincode") == null ?"NA" : userData.get("nomineePincode").toString();
			String guardianPincode=userData.get("guardianPincode") == null ?"NA" : userData.get("guardianPincode").toString();
			String existingCustId=userData.get("existingCustId") == null ?"NA" : userData.get("existingCustId").toString();

			String commCheckbox=userData.get("commCheckbox") == null ?"NA" : userData.get("commCheckbox").toString();
			String commAddress=userData.get("commAddress") == null ?"NA" : userData.get("commAddress").toString();
			String commPincode=userData.get("commPincode") == null ?"NA" : userData.get("commPincode").toString();			
			String gender=userData.get("gender") == null ?"NA" : userData.get("gender").toString();

			String nomineeSatutation = userData.get("title") == null ?"NA" : userData.get("title").toString();
			String titleMainSalutaion =userData.get("titleMain") == null ?"NA" : userData.get("titleMain").toString();
			String commAddDocName = userData.get("commAddDocName") == null ? "NA" : userData.get("commAddDocName").toString();
			String etbFlag = userData.get("etbFlag") == null ? "NA" : userData.get("etbFlag").toString();

			if("No".equalsIgnoreCase(commCheckbox))
			{
				Boolean flag=Utility.storeCommAddDocument(commAddDoc, customerId);
				logger.info(" === communication document created in addUserDataService ==== " + flag);
			}

			FixedDepositData depositData  = fixedDepositDao.getFixedDepositData(customerId);
			logger.info(" == depositData in addUserDataService ==== " + depositData);

			nomineeCheck = "NA".equalsIgnoreCase(nomineeCheck) ? "NO" : nomineeCheck;
			logger.info(" ==== nomineeCheck in addUser ===== " + nomineeCheck);
			nomineeAddressCheck = "NA".equalsIgnoreCase(nomineeAddressCheck) ? "NO" : nomineeAddressCheck;
			logger.info(" ==== nomineeCheck in addUser ===== " + nomineeCheck);

			String existingCustIdDb=depositData.getExistingCustId()==null?"NA":depositData.getExistingCustId();
			String remarkCustType=depositData.getRemarkCustType()==null?"":depositData.getRemarkCustType();

			logger.info(" ====  in addUser existingCustIdDb ===== " + existingCustIdDb+"===remarkCustType== "+remarkCustType);


			if(!"NO".equalsIgnoreCase(nomineeCheck) &&!("NA".equalsIgnoreCase(nomineedob)))
			{


				Date dob = new SimpleDateFormat("dd/MM/yyyy").parse(nomineedob);
				SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
				nomineedob = format.format(dob);

			}

			if("YES".equalsIgnoreCase(nomineeAddressCheck) && "YES".equalsIgnoreCase(nomineeCheck))
			{
				int reqPin=Integer.parseInt(nomineePincode);
				FDPincodeMaster fdPiMast = fixedDepositDao.getcitystatename(reqPin);


				if(fdPiMast != null){
					nomineeCity = fdPiMast.getCity();
					nomineeState = fdPiMast.getState();

				}else{
					nomineeCity ="NA";
					nomineeState = "NA";
				}

			}
			else{
				nomineeCity ="NA";
				nomineeState = "NA";
			}
			if("YES".equalsIgnoreCase(guardinPresent) && "YES".equalsIgnoreCase(nomineeCheck))
			{
				int guardiunPincode=Integer.parseInt(guardianPincode);
				FDPincodeMaster fdPiMastguardiun = fixedDepositDao.getcitystatename(guardiunPincode);


				if(fdPiMastguardiun != null){
					nomineeGuardianCity = fdPiMastguardiun.getCity();
					nomineeGuardianState = fdPiMastguardiun.getState();

				}else{
					nomineeGuardianCity ="";
					nomineeGuardianState = "";
				}

			}else{
				nomineeGuardianCity ="";
				nomineeGuardianState = "";
			}

			/*************************************Bank Details API call****************/
			String pocketBankname = "";
			String pocketAccountno = "";
			String pocketBankid = "";
			String pocketActiveflag = "";
			String pocketBalance = "";
			String bankDeatilsIfscCode="";


			if("DEDUPE".equalsIgnoreCase(remarkCustType))
			{

				try
				{
					logger.info(" == Bank Details Request in addUser =="+custId);
					JSONObject bankDetails = paymentIntegration.getBankDeatils(custId, customerId);
					logger.info(" == Bank Details Response in addUser === " + bankDetails);

					JSONObject bankDetailsResJson = new JSONObject();

					String bankStatus = bankDetails.get(Constants.BANK_STATUS) == null ? Constants.STATUS_FAIL: bankDetails.get(Constants.BANK_STATUS).toString();
					logger.info(" === custDataStatus in addUseraddUser === " + bankStatus);

					if(Constants.STATUS_SUCCESS.equalsIgnoreCase(bankStatus))
					{

						bankDetailsResJson = bankDetails.getJSONObject(Constants.BANK_DETAILS);
						pocketBankname = bankDetailsResJson.has("bankName") ? bankDetailsResJson.optString("bankName") : "NA";
						pocketAccountno = bankDetailsResJson.has("accountNo") ? bankDetailsResJson.optString("accountNo") : "NA";
						bankDeatilsIfscCode = bankDetailsResJson.has("IFSC_CODE") ? bankDetailsResJson.optString("IFSC_CODE") : "NA";
						logger.info(" === bankDetails in addUseraddUser  ==== " + bankDetails);
					}else
					{
						String bankDetailStr = bankDetails.has(Constants.BANK_DETAILS)? bankDetails.get(Constants.BANK_DETAILS).toString() : Constants.BANK_STATUS_MSG;
						logger.info(" == bankDetailStr in addUseraddUser === " + bankDetailStr);
					}


					IfscCode ifsc = fixedDepositDao.ifscCodeValidator(bankDeatilsIfscCode);
					logger.info(" === ifsc in adduser === " + ifsc);

					if(ifsc !=null)
					{
						String bankNameValue = BankNamePreSelectDedupe(ifsc.getBankName());
						ifscStatus = Constants.STATUS_SUCCESS;
						responseVal.put("IfscStatus", ifscStatus);
						responseVal.put(Constants.ACCOUNT_NO, pocketAccountno);
						responseVal.put(Constants.BANK_NAME, bankNameValue);
						responseVal.put("ifscCode", bankDeatilsIfscCode);
						responseVal.put(Constants.CUST_TYPE, remarkCustType);

					}else{
						ifscStatus = Constants.STATUS_FAIL;
						responseVal.put("IfscStatus", ifscStatus);
					}
					logger.info(" === ifsc code status in adduser === " + ifscStatus);
					logger.info(" ===Bank Deatils in adduser === " + responseVal);
				}
				catch(Exception ed)
				{
					ifscStatus = Constants.STATUS_FAIL;
					responseVal.put("IfscStatus", ifscStatus);
					logger.error("============ Exception in adduser===",ed);	
					Utility.loadNewRelicException(ed.toString(), "exception in addUserData for Dedupe", "", customerId);
				}
			}else
			{
				ifscStatus = Constants.STATUS_FAIL;
				responseVal.put("IfscStatus", ifscStatus);	
			}



			String nsdlPanName = depositData.getNsdlFullName() == null ? "":depositData.getNsdlFullName();

			if(nsdlPanName.isEmpty()){
				String panvalidationAPI = NSDLCallForPAN(panCard,customerId,Constants.ADDUSERDATA);

				logger.info("=========panvalidationAPI========="+panvalidationAPI);

				String panVarifyName = "";


				JSONObject panJson = new JSONObject(panvalidationAPI);

				String panStatus = panJson.has("nsdlStatus")? panJson.get("nsdlStatus").toString():"fail";
				String decriptResponse = panJson.has("decriptResponse")? panJson.get("decriptResponse").toString():"NSDL Api fail";


				if("sucess".equalsIgnoreCase(panStatus)){

					panVarifyName = panJson.has("panVarifyName")? panJson.get("panVarifyName").toString():"";
					depositData.setNsdlFullName(panVarifyName);
					depositData.setNsdlApiResponse(decriptResponse);					

				}else{
					depositData.setNsdlApiResponse(decriptResponse);
										
				}
			}
			
			
			depositData.setFullName(fullName);
			depositData.setMobileNumber(mobileNumber);
			depositData.setEmailAddress(emailAddress);
			depositData.setDateOfBirth(dateOfBirth);
			depositData.setPanCard(panCard);
			depositData.setAddress(address);
			depositData.setPinCode(pincode);
			depositData.setCustomerId(customerId);
			depositData.setCustomerId(customerId);
			depositData.setNomineeCheck(nomineeCheck);
			depositData.setNomineeName("NO".equalsIgnoreCase(nomineeCheck) ?"NA":nomineeName);
			depositData.setNomineeDateOfBirth("NO".equalsIgnoreCase(nomineeCheck)?"NA":nomineedob);
			depositData.setRelationshipWithNominee("NO".equalsIgnoreCase(nomineeCheck) && ("NA".equalsIgnoreCase(nomineedob))?"NA":nomineeRelation);
			depositData.setNomineeAddress("NO".equalsIgnoreCase(nomineeCheck) ?"NA":nomineeAddress);
			depositData.setNomineeGuardianName("NO".equalsIgnoreCase(guardinPresent)?"":guardianName);
			depositData.setNomineeGuardianDob("NA");
			depositData.setNomineeGuardianAddress("NO".equalsIgnoreCase(guardinPresent) ?"":guardianAddress);
			depositData.setNomineeGuardianRelation("NO".equalsIgnoreCase(guardinPresent)?"NA":relationshipNomineeGuardian);
			depositData.setNomineeAddressCheck("NO".equalsIgnoreCase(nomineeCheck)?"NA":nomineeAddressCheck);
			depositData.setNomineePincode("NO".equalsIgnoreCase(nomineeAddressCheck)?"NA":nomineePincode);
			depositData.setNomineeGuardianPincode("NO".equalsIgnoreCase(guardinPresent)?"":guardianPincode);
			depositData.setNomineeGuardianAge("NO".equalsIgnoreCase(guardinPresent)?"":gaurdianAge);
			depositData.setGaurdianCheck("NA".equalsIgnoreCase(guardinPresent) ? "NO" : guardinPresent);
			depositData.setNomineeState(nomineeState);
			depositData.setNomineeCity(nomineeCity);
			depositData.setNomineeGuardianCity(nomineeGuardianCity);
			depositData.setNomineeGuardianState(nomineeGuardianState);
			depositData.setExistingCustId("DEDUPE".equalsIgnoreCase(remarkCustType)?existingCustId:existingCustIdDb);

			depositData.setCommCheckbox(commCheckbox);
			depositData.setCommAddress(commAddress);
			depositData.setCommPincode(commPincode);
			depositData.setCommAddDocName(commAddDocName);

			depositData.setPocketActiveflag(pocketActiveflag);
			depositData.setPocketBalance(pocketBalance);
			depositData.setPocketAccountno(pocketAccountno);
			depositData.setPocketBankid(pocketBankid);
			depositData.setPocketBankname(pocketBankname);
			depositData.setGender(depositData.getGender()==null?gender:depositData.getGender());
			depositData.setJourneyStepName("PERSONALDETAILS");
			depositData.setPersonalDetailsTime(requestTime);

			depositData.setNomineeSalutation(nomineeSatutation);
			depositData.setSalutation(titleMainSalutaion);
			depositData.setPanedited(etbFlag);



			updateStatus = fixedDepositDao.updateFixedDeposit(depositData);
			logger.info(" === updateStatus in addUserDataService == " + updateStatus);
			storeStatus =Constants.STATUS_SUCCESS; 
			responseVal.put("storeStatus", storeStatus);
						

		} catch (Exception e) {
			if(!( requestCustId.isEmpty())){
				dbManipulation.recordExeption("PERSONALDETAILS_SERVICE","Exception due to internal call", requestCustId);
			}
			ifscStatus = Constants.STATUS_FAIL;
			storeStatus = Constants.STATUS_FAIL;
			responseVal.put("IfscStatus", ifscStatus);
			responseVal.put("storeStatus", storeStatus);
			logger.error(" == Exception in addUserDataService === ",e);
			Utility.loadNewRelicException(e.toString(), "addUserDataService", "", customerId);
		}

		return responseVal.toString();
	}

	@Override
	public String ifscValidatorService(String ifscCode,String fdcNumber) {

		String ifscStatus = "";
		try {
			logger.info(" == ifsccode in ifscValidatorService === " + ifscCode);

			IfscCode ifsc = fixedDepositDao.ifscCodeValidator(ifscCode);
			logger.info(" === ifsc in ifscValidatorService === " + ifsc);

			if(ifsc !=null){
				ifscStatus = Constants.STATUS_SUCCESS;

			}else{
				ifscStatus = Constants.STATUS_FAIL;
			}
			logger.info(" === ifsc code status in ifscCodeValidator === " + ifscStatus);

			return ifscStatus;
		}catch (Exception e) {
			Utility.loadNewRelicException(e.toString(), "ifscValidatorService", "", fdcNumber);
			logger.info("====== exception in ifscValidatorService =======",e);
		}
		return ifscCode;
	}

	@Override
	public String partnerCodeValidatorService(String partnerCode) {

		logger.info(" == partnerCode in partnerCodeValidatorService === " + partnerCode);
		String partnerName = "";
		try {
			PartnerCode PartnerCode = fixedDepositDao.partnerCodeValidator(partnerCode);
			logger.info(" === Partner in partnerCodeValidatorService === " + PartnerCode);

			if(PartnerCode !=null){
				partnerName = PartnerCode.getPartnerName();

			}else{
				partnerName = Constants.STATUS_FAIL;
			}
			logger.info(" === partner code name in partnerCodeValidatorService === " + partnerName);

			return partnerName;
		}catch (Exception e) {
			logger.info("========exception in partnerCodeValidatorService========");
			partnerName = Constants.STATUS_FAIL;
			Utility.loadNewRelicException(e.toString(), "partnerCodeValidatorService", "", "NA");
		}
		return partnerName;
		
	}


	@Override
	public boolean AssistedPCodeValidatoService(String partnerCode) {
		logger.info(" == partnerCode in AssistedPCodeValidatoService === " + partnerCode);

		try {
			AssistedPartnerCode assistedPartnerCode = fixedDepositDao.AssistedPCodeValidator(partnerCode);
			logger.info(" === Partner in AssistedPCodeValidatoService === " + assistedPartnerCode);

			if(assistedPartnerCode !=null){
				return true;
			}else{
				return false;
			}
		}catch (Exception e) {
			logger.info("========exception in AssistedPCodeValidatoService========");
			Utility.loadNewRelicException(e.toString(), "AssistedPCodeValidatoService", "", "NA");
		}
		return false;
	}


	@Override
	public String storeCustomerDocuments(ArrayList<MultipartFile> files, String customerId) {

		logger.info(" === files in storeCustomerDocuments ==== " + files);
		String storeFileStatus = "";

		try {
			if(!files.isEmpty()){

				for (MultipartFile docFile : files) {

					String fileName = docFile.getOriginalFilename();
					File file = new File(UPLOAD_LOCATION + customerId + "/" + fileName);

					if (!file.getParentFile().exists()){
						file.getParentFile().mkdirs();
					}
					if (!file.exists()){
						boolean fileCreateStatus = file.createNewFile();
						logger.info(" ==== fileCreateStatus in storeCustomerDocuments ==== " + fileCreateStatus);
					}

					FileCopyUtils.copy(docFile.getBytes(), file);
					storeFileStatus = Constants.STATUS_SUCCESS;
					logger.info(" == Store file name === " + fileName);
				}
			}else{
				logger.info(" === File not found ==== ");
			}

			logger.info(" === storeFileStatus in storeCustomerDocuments === " + storeFileStatus);

		} catch (Exception e) {
			logger.error(" === Exception in storeCustomerDocuments === ", e);
		}

		return storeFileStatus;
	}

	@Override
	public String interestRateValidator(String customerType, String tenor, String interestPayout, String interestPayoutType, String interestRate, String investmentType,String requestCustId) {

		String interetsRateStatus = "";
		String userReq=customerType+" "+tenor+" "+interestPayout+" "+interestPayoutType+" "+interestRate+" "+investmentType+" "+requestCustId;
		try {

			Integer tenorMonths = Integer.parseInt(tenor);
			logger.info(" == Tenor in int === " + tenorMonths);

			interetsRateStatus = interestRateValidation.interestRateValidator(customerType, tenorMonths, interestPayout, interestPayoutType, interestRate, investmentType);
			logger.info(" === interetsRateStatus in interestRateValidator ==== " + interetsRateStatus);

		} catch (Exception e) {
			logger.error(" ==== Exception in interestRateValidator === ", e);
			Utility.loadNewRelicException(e.toString(), "interestRateValidator", "", requestCustId);
		}

		return interetsRateStatus;
	}

	@Override
	public String calculatorService(String requestJson, String customerId) {

		String dbUpdateStatus = "";

		try {
			JSONObject interestRateReqJson = new JSONObject(requestJson);
			logger.info(" === cutomerId in calculatorService === " + customerId);
			logger.info(" == requestJson in calculatorService === "  + requestJson);
			String requestTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime()) ;
			logger.info(" === requestTime in addNtbUserDataService === " + requestTime);
			String tenor = interestRateReqJson.get("tenor") == null  ? "NA" : interestRateReqJson.get("tenor").toString();
			String interestPayout = interestRateReqJson.get("fdPayoutType") == null  ? "NA" : interestRateReqJson.get("fdPayoutType").toString();
			String interestRate = interestRateReqJson.get("interestRate") == null  ? "NA" : interestRateReqJson.get("interestRate").toString();
			String depositAmount = interestRateReqJson.get("depositAmount") == null  ? "NA" : interestRateReqJson.get("depositAmount").toString().replaceAll(",", "");
			String numberOfDeposits = interestRateReqJson.get("sdpDepositNO") == null  ? "NA" : interestRateReqJson.get("sdpDepositNO").toString();
			String sdpDepositDate = interestRateReqJson.get("sdpDepositDate") == null  ? "NA" : interestRateReqJson.get("sdpDepositDate").toString();
			String renewType = interestRateReqJson.get("fdRenewalType") == null  ? "NA" : interestRateReqJson.get("fdRenewalType").toString();
			String customerType = interestRateReqJson.get("customerType") == null  ? "NA" : interestRateReqJson.get("customerType").toString();
			String interestPayoutType = interestRateReqJson.get("interestPayoutType") == null  ? "NA" : interestRateReqJson.get("interestPayoutType").toString();
			String investmentType = interestRateReqJson.get("investmentType") == null  ? "NA" : interestRateReqJson.get("investmentType").toString();
			String sdpTotalPriAmnt = interestRateReqJson.get("sdpTotalPriAmnt") == null  ? "NA" : interestRateReqJson.get("sdpTotalPriAmnt").toString();
			String sdptotalPayoutAmnt = interestRateReqJson.get("sdptotalPayoutAmnt") == null  ? "NA" : interestRateReqJson.get("sdptotalPayoutAmnt").toString();
			String fdMaturityDate = interestRateReqJson.get("fdMaturityDate") == null  ? "NA" : interestRateReqJson.get("fdMaturityDate").toString();
			String fdInterestAmnt = interestRateReqJson.get("fdInterestAmnt") == null  ? "NA" : interestRateReqJson.get("fdInterestAmnt").toString();
			String fdMaturityAmnt = interestRateReqJson.get("fdmaturityAmnt") == null  ? "NA" : interestRateReqJson.get("fdmaturityAmnt").toString();
			String paymentOption = interestRateReqJson.get("paymentOption") == null  ? "NA" : interestRateReqJson.get("paymentOption").toString();
			String bankName = interestRateReqJson.get("bankName") == null  ? "NA" : interestRateReqJson.get("bankName").toString();
			String ifscCode = interestRateReqJson.get("ifscCode") == null  ? "NA" : interestRateReqJson.get("ifscCode").toString();
			String accountNumber=interestRateReqJson.get("accountNumber") == null  ? "NA" : interestRateReqJson.get("accountNumber").toString();
			String maturityScheme = interestRateReqJson.has("maturityScheme")? interestRateReqJson.get("maturityScheme").toString():"NA";
			String fullBankName = interestRateReqJson.has("fullBankName")? interestRateReqJson.get("fullBankName").toString():"NA";


			if("Monthly Maturity".equalsIgnoreCase(maturityScheme)){
				maturityScheme = "Monthly Maturity";
			}else if("NA".equalsIgnoreCase(maturityScheme)){
				maturityScheme = "NA";
			}
			else{
				maturityScheme= "Single Maturity";
			}

			String renewStatus = "NA".equalsIgnoreCase(renewType) ? "NA" : "NO";
			logger.info(" ==== renewStatus in calculatorService ===== " + renewStatus);


			FixedDepositData depositData = fixedDepositDao.getFixedDepositData(customerId);


			depositData.setTenor(tenor);
			depositData.setInterestRate(interestRate);
			depositData.setInterestPayout(interestPayout);
			depositData.setFdRenewType(renewType);

			depositData.setFdRenew(renewStatus);
			depositData.setCustomerType(customerType);
			depositData.setInterestPayoutType(interestPayoutType);

			depositData.setDepositAmount(depositAmount);
			depositData.setNumberOfDeposit(numberOfDeposits);
			depositData.setDateOfEachDeposit(sdpDepositDate);
			depositData.setInvestmentType(investmentType);
			depositData.setSdpTotalPriAmnt(sdpTotalPriAmnt);
			depositData.setSdptotalPayoutAmnt(sdptotalPayoutAmnt);
			depositData.setFdMaturityDate(fdMaturityDate);
			depositData.setFdInterestAmnt(fdInterestAmnt);
			depositData.setFdMaturityAmnt(fdMaturityAmnt);
			depositData.setBankAccountNumber(accountNumber);
			depositData.setBankName(bankName);
			depositData.setIfscCode(ifscCode);
			depositData.setPaymentChoice(paymentOption);
			depositData.setMaturityScheme(maturityScheme);
			depositData.setFullBankName(fullBankName);
			depositData.setJourneyStepName("SCHEMEDETAILS");
			depositData.setSchemeDetailsTime(requestTime);
			dbUpdateStatus = fixedDepositDao.updateFixedDeposit(depositData);
			logger.info(" === dbUpdateStatus in calculatorService === " + dbUpdateStatus);


		} catch (Exception e) {
			logger.error(" ==== Exception in calculatorService === ", e);
			if(!( customerId.isEmpty())){
				dbManipulation.recordExeption("SCHEME_SERVICE","Exception due to internal call", customerId);
			}
			Utility.loadNewRelicException(e.toString(), "calculatorService", "", customerId);
		}

		return dbUpdateStatus;
	}

	@Override
	@Trace
	public String paymentRequestService(String customerId, String paymentJson) {

		logger.info(" ==== customerId in paymentRequestService === " + customerId);
		logger.info(" === paymentJson in paymentRequestService ==== " + paymentJson);
		String checksumResponse = "";

		try {

			synchronized (this)
			{
				FixedDepositData depositData = fixedDepositDao.getFixedDepositData(customerId);
				logger.info(" ==== depositData in paymentRequestService ==== " + depositData);

				String applicationId = fixedDepositDao.applicationIdGenerator();
				logger.info(" ==== applicationId in paymentRequestService ===== " + applicationId);

				checksumResponse = paymentIntegration.generateChecksum(depositData, applicationId,customerId);
				logger.info(" ==== generatedChecksum in paymentRequestService ===== " + checksumResponse);

				if(!"noCalculatorStageFound".equalsIgnoreCase(checksumResponse))
				{

					JSONObject paymentReqJson = new JSONObject(paymentJson);
					String requestTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime()) ;
					logger.info(" === requestTime in paymentRequestService === " + requestTime);
					String directorOrPromoter = paymentReqJson.get("directorOrPromoter") == null ? "NA" : paymentReqJson.get("directorOrPromoter").toString();
					String relativeOfDirector = paymentReqJson.get("relativeOfDirector") == null ? "NA" : paymentReqJson.get("relativeOfDirector").toString();
					String shareholder = paymentReqJson.get("shareholder") == null ? "NA" : paymentReqJson.get("shareholder").toString();
					String foreignCitizen = paymentReqJson.get("foreignCitizen") == null ? "NA" : paymentReqJson.get("foreignCitizen").toString();
					String foreignTaxPayer = paymentReqJson.get("foreignTaxPayer") == null ? "NA" : paymentReqJson.get("foreignTaxPayer").toString();

					depositData.setDirectorOrPromoter(directorOrPromoter);
					depositData.setRelativeOfDirector(relativeOfDirector);
					depositData.setShareholder(shareholder);
					depositData.setForeignTaxPayer(foreignTaxPayer);
					depositData.setForeignCitizen(foreignCitizen);
					depositData.setFormAppNumber(applicationId);
					depositData.setChecksumresponse("1");
					depositData.setJourneyStepName("PAYMENTREQUEST");
					depositData.setPayementRequestTime(requestTime);
					String dataUpdateStatus = fixedDepositDao.updateFixedDeposit(depositData);
					logger.info(" === dataUpdateStatus in paymentRequestService ==== " + dataUpdateStatus);
				}
			}

		} catch (Exception e) {
			logger.error(" === Exception in paymentRequestService === ", e);
			if(!( customerId.isEmpty())){
				dbManipulation.recordExeption("PAYREQUEST_SERVICE","Exception due to internal call", customerId);
			}
			Utility.loadNewRelicException(e.toString(), "paymentRequestService", "", customerId);
		}

		return checksumResponse;
	}

	@Override
	@Trace
	public JSONObject paymentResponseService(String [] responseParamsArr, HttpSession httpSession) {

		JSONObject paymentResJson = new JSONObject();
		String customerId="";
		try {
			String transactionStatus = responseParamsArr[3];
			customerId = responseParamsArr[0];
			String utrNumber = responseParamsArr[1];
			String transactionAmount = responseParamsArr[2];
			String transactionMsg = responseParamsArr[4];

			logger.info(" === Transaction Status in paymentResponseService ==== " + transactionStatus);
			String requestTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime()) ;
			logger.info(" === requestTime in paymentResponseService === " + requestTime);

			FixedDepositData fixedDepositData = fixedDepositDao.getFixedDepositData(customerId);
			logger.info(" == fixedDepositData from cust id in paymentResponseService === " + fixedDepositData);


			fixedDepositData.setUtrNumber(utrNumber);
			fixedDepositData.setTransactionAmount(transactionAmount);
			fixedDepositData.setTransactionStatus(transactionStatus);
			fixedDepositData.setTransactionMessage(transactionMsg);
			fixedDepositData.setPaymentResponseTime(requestTime);
			String responseUpdateStatus = fixedDepositDao.updateFixedDeposit(fixedDepositData);
			logger.info(" ==== responseUpdateStatus in paymentResponseService === " + responseUpdateStatus);

			String customerType = fixedDepositData.getDedupeCustomerType() == null ? "NA" : fixedDepositData.getDedupeCustomerType();
			logger.info(" ==== customerType in paymentResponseService === " + customerType);
			String txnAmountForFail = fixedDepositData.getDepositAmount();

			String investmentType = fixedDepositData.getInvestmentType() == null ? "NA" : fixedDepositData.getInvestmentType();
			logger.info(" === investmentType in paymentResponseService ==== " + investmentType);
			String depositAmount = fixedDepositData.getDepositAmount() == null ? "NA": fixedDepositData.getDepositAmount();
			String sdpTotalPriAmnt = fixedDepositData.getSdpTotalPriAmnt() == null ? "NA": fixedDepositData.getSdpTotalPriAmnt();
			logger.info(" === sdpTotalPriAmnt in paymentResponseService ==== " + sdpTotalPriAmnt);
			String paymentMode =fixedDepositData.getPaymentChoice() == null ? "NA": fixedDepositData.getPaymentChoice();
			logger.info(" ===  paymentMode ==== " + paymentMode);
			String partnerCode = fixedDepositData.getPartnerCode()==null?"NA": fixedDepositData.getPartnerCode();
			logger.info(" ===  partnerCode ==== " + partnerCode);

			switch(transactionMsg){
			case "FAILED-NA-Z8":
				transactionMsg = "PER TRANSACTION LIMIT EXCEEDED";
				break;
			case "FAILED-NA-ZH":
				transactionMsg = "INVALID VIRTUAL ADDRESS";
				break;
			case "FAILED-NA-U16":
				transactionMsg = "RISK THRESHOLD EXCEEDED";
				break;
			case "FAILED-NA-FP":
				transactionMsg = "";
				break;
			case "EXPIRED-NA-U69":
				transactionMsg = "COLLECT EXPIRED";
				break;
			case "FAILED-NA-ZE":
				transactionMsg = "TRANSACTION NOT PERMITTED TO";
				break;
			case "FAILED-NA-ZM":
				transactionMsg = "INVALID/INCORRECT MPIN";
				break;
			default:
				transactionMsg = transactionMsg;
			}


			paymentResJson.put(Constants.TRANSACTION_STATUS, transactionStatus);
			paymentResJson.put(Constants.CUSTOMER_ID, customerId);
			paymentResJson.put(Constants.CUSTOMER_TYPE, customerType);
			httpSession.setAttribute("utrNumber", utrNumber);
			httpSession.setAttribute("transactionMsg", transactionMsg);
			httpSession.setAttribute("txnAmountForFail", txnAmountForFail);
			httpSession.setAttribute(Constants.INVESTMENT_TYPE, investmentType);
			httpSession.setAttribute("depositAmount", depositAmount);
			httpSession.setAttribute("sdpTotalPriAmnt", sdpTotalPriAmnt);
			httpSession.setAttribute("paymentMode", paymentMode);
			if("NA".equalsIgnoreCase(partnerCode) || partnerCode.isEmpty() ){
				httpSession.setAttribute("partnercodeFlage","No");
				httpSession.setAttribute("partnerCode","NA");
			}else{
				httpSession.setAttribute("partnerCode",partnerCode);
				httpSession.setAttribute("partnercodeFlage","Yes");

			}


			if(Constants.TRANSACTION_SUCCESS.equalsIgnoreCase(transactionStatus)){

				logger.info(" ==== Transaction did Successfully ==== ");
				fixedDepositData.setPaymentSuccessTime(requestTime);
				String PaymentSuccessStatus = fixedDepositDao.updateFixedDeposit(fixedDepositData);
				logger.info(" ==== responseUpdateStatus in paymentResponseService === " + PaymentSuccessStatus);
				fixedDepositData.setJourneyStepName("PAYMENTSUCESS");
				String mobileNumber = fixedDepositData.getMobileNumber() == null ? "NA" : fixedDepositData.getMobileNumber();
				String interestRate = fixedDepositData.getInterestRate() == null ? "NA" : fixedDepositData.getInterestRate();
				String tenor = fixedDepositData.getTenor() == null ? "NA" : fixedDepositData.getTenor();
				String applicationNumber = fixedDepositData.getFormAppNumber() == null ? "NA" : fixedDepositData.getFormAppNumber();

				String sdpTotalPayoutAmnt = fixedDepositData.getSdptotalPayoutAmnt() == null ? "NA" : fixedDepositData.getSdptotalPayoutAmnt();
				logger.info(" === sdpTotalPayoutAmnt in paymentResponseService ==== " + sdpTotalPayoutAmnt);

				String fdMaturityDate = fixedDepositData.getFdMaturityDate() == null ? "NA" : fixedDepositData.getFdMaturityDate();
				logger.info(" === fdMaturityDate in paymentResponseService ==== " + fdMaturityDate);

				String fdInterestAmnt = fixedDepositData.getFdInterestAmnt() == null ? "NA" : fixedDepositData.getFdInterestAmnt();
				logger.info(" === fdInterestAmnt in paymentResponseService ==== " + fdInterestAmnt);

				String fdMaturityAmnt = fixedDepositData.getFdMaturityAmnt() == null ? "NA" : fixedDepositData.getFdMaturityAmnt();
				logger.info(" === fdMaturityAmnt in paymentResponseService ==== " + fdMaturityAmnt);

				String customerName =  fixedDepositData.getFullName() == null ? "NA" : fixedDepositData.getFullName();
				logger.info(" === customerName in paymentResponseService ==== " + customerName);

				String noOfDeposit = fixedDepositData.getNumberOfDeposit() == null ? "NA" : fixedDepositData.getNumberOfDeposit();
				logger.info(" === noOfDeposit in paymentResponseService ==== " + noOfDeposit);

				String interestPayoutType = fixedDepositData.getInterestPayout() == null ? "NA" : fixedDepositData.getInterestPayout();
				logger.info(" === interestPayoutType in paymentResponseService ==== " + interestPayoutType);

				httpSession.setAttribute(Constants.INVESTMENT_TYPE, investmentType);
				httpSession.setAttribute("totPayoutAmnt", sdpTotalPayoutAmnt);
				httpSession.setAttribute("maturityDate", fdMaturityDate);
				httpSession.setAttribute("interestEarned", fdInterestAmnt);
				httpSession.setAttribute("maturityAmount", fdMaturityAmnt);
				httpSession.setAttribute("customerName", customerName);
				httpSession.setAttribute("interestRate", interestRate);
				httpSession.setAttribute("monthIntrestAmnt", depositAmount);
				httpSession.setAttribute("noOfDeposit", noOfDeposit);
				httpSession.setAttribute("tenorOfDeposit", tenor);
				httpSession.setAttribute("interestPayoutType", interestPayoutType);
				httpSession.setAttribute("applicationNumber", applicationNumber);
				httpSession.setAttribute("pocketActiveflag", fixedDepositData.getPocketActiveflag()== null ? "N" : fixedDepositData.getPocketActiveflag());
				String impsCheck = fixedDepositData.getImpsCount();

				Runnable runnableFirst=()->{

					JSONObject smsRequest = new JSONObject();

					String custId = fixedDepositData.getCustomerId() == null ? "" : fixedDepositData.getCustomerId();
					FixedDepositData fixedDepositDataUpdated = fixedDepositDao.getFixedDepositData(custId);

					String smsStatusCheck=fixedDepositDataUpdated.getSmsStatus()== null?"NA":fixedDepositDataUpdated.getSmsStatus();
					logger.info(" ===== before smsStatus in paymentResponseService ===== " + smsStatusCheck);
					if(!"SUCCESS".equalsIgnoreCase(smsStatusCheck))
					{
						smsRequest.put("form_Id", "1524666902641");
						smsRequest.put("mobile_number_mobileTrue", mobileNumber);
						smsRequest.put("digital_customer_id", custId);
						smsRequest.put("depositamount_amountTrue", depositAmount);
						smsRequest.put("interestRate_interestrateTrue", interestRate);
						smsRequest.put("tenureMonth_numberTrue", tenor);
						smsRequest.put("digital_CERT_Number", applicationNumber);
						logger.info(" === smsRequest in paymentResponseService ==== " + smsRequest);
						String smsStatus = responsysIntegration.sendSms(smsRequest.toString(),custId);
						logger.info(" ===== smsStatus in paymentResponseService ===== " + smsStatus);
						fixedDepositDataUpdated.setSmsStatus(smsStatus);
						String finalSmsDbStatus = fixedDepositDao.updateFixedDeposit(fixedDepositDataUpdated);
						logger.info(" ==== responseUpdateStatus for SMS in paymentResponseService === " + finalSmsDbStatus);
					}
					logger.info(" ==== impsCheck in paymentRequestService ===== " + impsCheck);
					String impsResponse="";
					try {	
						if (!("impsCheck".equalsIgnoreCase(impsCheck))) 
						{
							impsResponse = paymentIntegration.generateIMPS(fixedDepositDataUpdated, applicationNumber,custId);
							logger.info(" ==== IMPS API in IMPS API Response ===== " + impsResponse);
							JSONObject impsRsponsejson = new JSONObject(impsResponse);
							IMPSFixedDepositData impsfixedepositdata = new IMPSFixedDepositData();
							String fromSubmissionDate = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(Calendar.getInstance().getTime());
							String iMPSmessageCode = "";
							String iMPSdateTime = "";
							String iMPStrnsDate = "";
							String iMPSmerchantId = "";
							String iMPStraceNumber = "";
							String iMPSrespDesc = "";
							String iMPSrrn = "";
							String iMPSbankRefNum = "";
							String iMPSbeneName = "";
							String iMPSchecksum = "";
							String iMPStrnBankName = "";
							String respCode = impsRsponsejson.has("respCode") ? impsRsponsejson.get("respCode").toString(): "NA";


							iMPSmessageCode = impsRsponsejson.has("messageCode")? impsRsponsejson.get("messageCode").toString() : "NA";
							iMPSdateTime = impsRsponsejson.has("dateTime") ? impsRsponsejson.get("dateTime").toString(): "NA";
							iMPStrnsDate = impsRsponsejson.has("trnsDate") ? impsRsponsejson.get("trnsDate").toString(): "NA";
							iMPSmerchantId = impsRsponsejson.has("merchantId")? impsRsponsejson.get("merchantId").toString() : "NA";
							iMPStraceNumber = impsRsponsejson.has("traceNumber")? impsRsponsejson.get("traceNumber").toString() : "NA";
							iMPSrespDesc = impsRsponsejson.has("respDesc") ? impsRsponsejson.get("respDesc").toString(): "NA";
							iMPSrrn = impsRsponsejson.has("rrn") ? impsRsponsejson.get("rrn").toString() : "NA";
							iMPSbankRefNum = impsRsponsejson.has("bankRefNum")? impsRsponsejson.get("bankRefNum").toString() : "NA";
							iMPSbeneName = impsRsponsejson.has("beneName") ? impsRsponsejson.get("beneName").toString(): "NA";
							iMPSchecksum = impsRsponsejson.has("checksum") ? impsRsponsejson.get("checksum").toString(): "NA";
							iMPStrnBankName = impsRsponsejson.has("trnBankName")? impsRsponsejson.get("trnBankName").toString() : "NA";
							fixedDepositDataUpdated.setImpsBeneName(iMPSbeneName);
							fixedDepositDataUpdated.setImpsCount("impsCheck");

							impsfixedepositdata.setBflcustomerId(custId);
							impsfixedepositdata.setiMPSbankRefNum(iMPSbankRefNum);
							impsfixedepositdata.setiMPSbeneName(iMPSbeneName);
							impsfixedepositdata.setiMPSchecksum(iMPSchecksum);
							impsfixedepositdata.setiMPSdateTime(iMPSdateTime);
							impsfixedepositdata.setiMPSmerchantId(iMPSmerchantId);
							impsfixedepositdata.setiMPSmessageCode(iMPSmessageCode);
							impsfixedepositdata.setiMPSrespDesc(iMPSrespDesc);
							impsfixedepositdata.setiMPSrrn(iMPSrrn);
							impsfixedepositdata.setiMPStraceNumber(iMPStraceNumber);
							impsfixedepositdata.setMobileno(fixedDepositDataUpdated.getMobileNumber());
							impsfixedepositdata.setiMPStrnsDate(iMPStrnsDate);
							impsfixedepositdata.setiMPStrnBankName(iMPStrnBankName);

							impsfixedepositdata.setCreatedOn(fromSubmissionDate);
							impsfixedepositdata.setImpsResponseCode(respCode);
							String dbSaveStatus = fixedDepositDao.saveIMPSFixedDepositData(impsfixedepositdata);
							logger.info("==================impsfixedepositdata dbSaveStatus============" + dbSaveStatus);
						}
					} catch (Exception e) {
						logger.error(" ==== Exception in IMPS API Response ==== ", e);
						Utility.loadNewRelicException(e.toString(), "exception in paymentResponse for impsCheck","",custId);
					}

					JSONObject sfdcJsonRes = new JSONObject();

					if("FD".equalsIgnoreCase(investmentType)){

						logger.info(" ===  FD SFDC Request ===");
						JSONObject ekycFdSfdcRes = ekycFdSfdcService(fixedDepositDataUpdated,Constants.PAYMENTRESPONSE);
						logger.info(" ==== ekycFdSfdcRes in paymentResponseService ====== " + ekycFdSfdcRes);
						sfdcJsonRes = ekycFdSfdcRes;

					}else if("SDP".equalsIgnoreCase(investmentType)){

						logger.info(" ===  SDP SFDC Request ===");
						JSONObject ekycSdpSfdcRes = ekycSdpSfdcService(fixedDepositDataUpdated,Constants.PAYMENTRESPONSE);
						logger.info(" ==== ekycSdpSfdcRes in paymentResponseService ====== " + ekycSdpSfdcRes);

						sfdcJsonRes = ekycSdpSfdcRes;
					}

					logger.info(" ==== sfdcJsonRes in paymentResponseService ====== " + sfdcJsonRes);
					logger.info(" ==== after sfdcJsonRes in paymentResponseService schemeCode ====== " + schemeCode);

					String finalSfdcStatus = sfdcJsonRes.get("IsSuccess") == null ? "NA" : sfdcJsonRes.get("IsSuccess").toString();
					String finalSfdcMsg = sfdcJsonRes.get("Message") == null ? "NA" : sfdcJsonRes.get("Message").toString();
					String finalSfdcUniqueRecId = sfdcJsonRes.get("UniqueRecId") == null ? "NA" : sfdcJsonRes.get("UniqueRecId").toString();
					String finalSfdcId = sfdcJsonRes.get("FdSfdcId") == null ? "NA" : sfdcJsonRes.get("FdSfdcId").toString();


					fixedDepositDataUpdated.setFinalSfdcStatus(finalSfdcStatus);
					fixedDepositDataUpdated.setFinalSfdcMsg(finalSfdcMsg);
					fixedDepositDataUpdated.setFinalSfdcUniqueRecId(finalSfdcUniqueRecId);
					fixedDepositDataUpdated.setFinalSfdcId(finalSfdcId);
					fixedDepositDataUpdated.setSchemeCode(schemeCode);
					fixedDepositDataUpdated.setJourneyStepName("PAYMENTSUCESS");
					String cutTypeRemark = fixedDepositDataUpdated.getRemarkCustType();
					if("NTBDocumentUpload".equalsIgnoreCase(cutTypeRemark)){
						Runnable runnable=()->{
							sendDocumentstoSfdcService(fixedDepositDataUpdated,Constants.PAYMENTRESPONSE);
						};
						new Thread(runnable).start();
					}
					String finalSfdcDbStatus = fixedDepositDao.updateFixedDeposit(fixedDepositDataUpdated);
					logger.info(" ==== responseUpdateStatus in paymentResponseService === " + finalSfdcDbStatus);

				};

				new Thread(runnableFirst).start();

			}else if("Invalid Bank Response".equalsIgnoreCase(transactionStatus))
			{

				JSONObject smsRequest = new JSONObject();
				smsRequest.put("form_Id", "1524669302273");
				smsRequest.put("mobile_number_mobileTrue", fixedDepositData.getMobileNumber());
				smsRequest.put("digital_customer_id", customerId);
				smsRequest.put("digital_CERT_Number", customerId);
				smsRequest.put("bitlylink__bitlyLinkId","");
				logger.info(" === smsRequest in paymentResponseService for  Invalid Bank Response==== " + smsRequest);
				String invalidTraxResponseSms = responsysIntegration.sendSms(smsRequest.toString(),customerId);
				logger.info(" ===== smsStatus paymentResponseService for  Invalid Bank Response ===== " + invalidTraxResponseSms);
				fixedDepositData.setJourneyStepName("PAYMENTFAIL");
				fixedDepositData.setInvalidTraxResponseSms(invalidTraxResponseSms);
				Utility.loadNewRelicValidation("Invalid Bank response in PaymentResponse", "Bank response fail in PaymentResponse",customerId,Constants.PAYMENTRESPONSE);

			}
			else
			{

				String formUrl= Utility.getPropertyFileValue("formUrl").trim();
				String mainLinkBitly=formUrl+""+fixedDepositData.getInvestmentType();
				String BrokerBitlyLink=formUrl+""+fixedDepositData.getInvestmentType()+"&PartnerCode="+fixedDepositData.getPartnerCode();
				mainLinkBitly= "NA".equalsIgnoreCase(fixedDepositData.getPartnerCode())?mainLinkBitly:BrokerBitlyLink;

				JSONObject smsRequest = new JSONObject();
				smsRequest.put("form_Id", "1501121550380");
				smsRequest.put("mobile_number_mobileTrue", fixedDepositData.getMobileNumber());
				smsRequest.put("digital_customer_id", customerId);
				smsRequest.put("digital_CERT_Number", customerId);
				smsRequest.put("bitlylink__bitlyLinkId",mainLinkBitly);
				logger.info(" === smsRequest in paymentResponseService for  resumeFailJouney==== " + smsRequest);
				String resumeFailJouneysmsStatus = responsysIntegration.sendSms(smsRequest.toString(),customerId);
				logger.info(" ===== smsStatus paymentResponseService for  resumeFailJouney ===== " + resumeFailJouneysmsStatus);
				fixedDepositData.setJourneyStepName("PAYMENTFAIL");
				fixedDepositData.setPaymentFailSmsStatus(resumeFailJouneysmsStatus); 
				Utility.loadNewRelicValidation("Invalid TransactionStatus in PaymentResponse", "Invalid TransactionStatus in PaymentResponse",customerId,Constants.PAYMENTRESPONSE);
			}

			String finalUpdateAftersms = fixedDepositDao.updateFixedDeposit(fixedDepositData);
			logger.info(" ==== final responseUpdateStatus in paymentResponseService === " + finalUpdateAftersms);

		} catch (Exception e) {
			logger.error(" ==== Exception in paymentResponseService ==== ", e);
			if(!( customerId.isEmpty())){
				dbManipulation.recordExeption("PAYRESPONSE_SERVICE","Exception due to internal call", customerId);
			}
			Utility.loadNewRelicException(e.toString(), "paymentResponseService", "", customerId);
		}
		finally{
			Thread.interrupted();
			logger.info("------ Closing Resources Thraed .... !!!!");
		}
		logger.info(" === paymentResJson in paymentResponseService ===== " + paymentResJson);

		return paymentResJson;
	}

	@Override
	public void sendDocumentstoSfdcService(FixedDepositData depositData, String contextCalled)
	{
		try
		{
			JSONObject documentUploadSfdc = ntbDocumentSFDC(depositData,contextCalled);
			logger.info(" ==== documentUploadSfdc in paymentResponseService ====== " + documentUploadSfdc);
			String documentUploadId = documentUploadSfdc.has("documentUploadId")  ?  documentUploadSfdc.get("documentUploadId").toString():"NA" ;
			String docapiStatus = documentUploadSfdc.get("docapiStatus") == null ? "NA" : documentUploadSfdc.get("docapiStatus").toString();
			depositData.setDocumentUploadId(documentUploadId);
			depositData.setDocumentUploadApiResponse(docapiStatus);
			String finalSfdcDbStatus = fixedDepositDao.updateFixedDeposit(depositData);
			logger.info(" ==== responseUpdateStatus in paymentResponseService === " + finalSfdcDbStatus);
		} catch (Exception e) {
			logger.error(" ==== Exception in paymentResponseService ==== ", e);
		}finally{
			Thread.interrupted();
			logger.info("------ Closing Resources Thraed .... !!!!");
		}
	}
	@Trace
	private JSONObject ekycFdSfdcService(FixedDepositData depositData,String contextCall){

		JSONObject sfdcRes = new JSONObject();
		logger.info(" ===== EKYC Final SFDC FD Call in ekycFdSfdcService ==== ");

		String customerId="";
		try {
			customerId=depositData.getCustomerId()==null?"":depositData.getCustomerId();
			String customerSubtype = depositData.getCustomerType() == null ? "NA" : depositData.getCustomerType();
			String existingCustId = depositData.getExistingCustId() == null ? "NA" : depositData.getExistingCustId();
			String depositOption = depositData.getInterestPayoutType() == null ? "NA" : depositData.getInterestPayoutType();
			String interestPayoutFreq = depositData.getInterestPayout() == null ? "NA" : depositData.getInterestPayout();
			String fDAmount = depositData.getDepositAmount() == null ? "NA" : depositData.getDepositAmount();
			String tenorMonth = depositData.getTenor() == null ? "NA" : depositData.getTenor();
			String interest = depositData.getInterestRate() == null ? "NA" : depositData.getInterestRate();
			String utmMedium = depositData.getUtmMedium() == null ? "NA" : depositData.getUtmMedium();
			String utmCampaign = depositData.getUtmCampaign() == null ? "NA" : depositData.getUtmCampaign();
			String utmKeyword = depositData.getUtmkeyword() == null ? "NA" : depositData.getUtmkeyword();
			String pageUrl = depositData.getPageUrl() == null ? "NA" : depositData.getPageUrl();
			String device = depositData.getDevice() == null ? "NA" : depositData.getDevice();
			String clientId = depositData.getClientId() == null ? "NA" : depositData.getClientId();
			String phoneNo = depositData.getMobileNumber() == null ? "NA" : depositData.getMobileNumber();
			String fullName = depositData.getFullName() == null ? "NA" : depositData.getFullName();
			String email = depositData.getEmailAddress() == null ? "NA" : depositData.getEmailAddress();
			String city = depositData.getCity() == null ? "NA" : depositData.getCity();
			String state = depositData.getState() == null ? "NA" : depositData.getState();
			String accountNumber = depositData.getBankAccountNumber() == null ? "NA" : depositData.getBankAccountNumber();
			String ifscCode = depositData.getIfscCode() == null ? "NA" : depositData.getIfscCode();
			String utr = depositData.getUtrNumber() == null ? "NA" : depositData.getUtrNumber();
			String dateOfBirth = depositData.getDateOfBirth() == null ? "NA" : depositData.getDateOfBirth();
			String pan = depositData.getPanCard() == null ? "NA" : depositData.getPanCard();
			String pincode = depositData.getPinCode() == null ? "NA" : depositData.getPinCode();
			String nomineeName = depositData.getNomineeName() == null ? "NA" : depositData.getNomineeName();
			String nomineeDateOfBirth = depositData.getNomineeDateOfBirth() == null ? "NA" : depositData.getNomineeDateOfBirth();
			String nomineeRelation = depositData.getRelationshipWithNominee() == null ? "NA" : depositData.getRelationshipWithNominee();
			String investmentType = depositData.getInvestmentType() == null ? "NA" : depositData.getInvestmentType();
			String partnerCode = "NA".equalsIgnoreCase(depositData.getPartnerCode())  ? "76783" : depositData.getPartnerCode();
			String formAppNumber = depositData.getFormAppNumber() == null ? "NA" : depositData.getFormAppNumber();
			String address = depositData.getAddress() == null ? "" : depositData.getAddress();			
			String sfdcApiRecordId = depositData.getSfdcRecordId() == null ? "" : depositData.getSfdcRecordId();
			String fdrdplan1 = depositData.getRdpLan() == null ? "" : depositData.getRdpLan();
			String productOfferingSrc = depositData.getRdpLan() == null ? "Website Complete" : "RDPL Complete";
			String propertyFileSourcingChannel=Utility.getPropertyFileValue("SOURCE");
			String sourcingChannel = depositData.getRdpLan() == null ? propertyFileSourcingChannel : "RDPL";

			String loanType = depositData.getRdpLan() == null ? "" : "RDPL Loan";
			String gender = depositData.getGender() == null? "M" : depositData.getGender();
			String lastClick = depositData.getLastClick() == null? "" : depositData.getLastClick();
			lastClick = lastClick.length()>=50 ?lastClick.replace(" ", "").substring(0, 50):lastClick;
			pageUrl = pageUrl.length()>=150 ? pageUrl.replace(" ", "").substring(0, 150) : pageUrl;
			String gclld = depositData.getGclid()==null? "" : depositData.getGclid();

			String requeryFlag = depositData.getRequerystatus() == null ? "Fail" : depositData.getRequerystatus();
			String okycStatus=depositData.getOkycReturnValue()==null ?"":depositData.getOkycReturnValue();
			String remarkCustType = depositData.getRemarkCustType() == null ? "" : depositData.getRemarkCustType();
			String cretedOn = depositData.getCreatedOn() == null ? "" : depositData.getCreatedOn();
			String paymentReqTime = depositData.getPayementRequestTime()== null ? "" : depositData.getPayementRequestTime();
			String nomineeAddressCheck=depositData.getNomineeAddressCheck()==null?"":depositData.getNomineeAddressCheck();
			String nomineeAddress=depositData.getNomineeAddress()==null?"":depositData.getNomineeAddress();			
			String nomineeCity=depositData.getNomineeCity()==null?"":depositData.getNomineeCity();
			String nomineeState=depositData.getNomineeState()==null?"":depositData.getNomineeState();
			String nomineePincode=depositData.getNomineePincode()==null?"":depositData.getNomineePincode();
			String paymentMode=depositData.getPaymentChoice()==null?"":depositData.getPaymentChoice();

			String commCheckbox=depositData.getCommCheckbox()==null?"":depositData.getCommCheckbox();
			String commAddress=depositData.getCommAddress()==null?"":depositData.getCommAddress();			
			String commPincode=depositData.getCommPincode()==null?"":depositData.getCommPincode();
			String utmSource = depositData.getUtmSource() == null ? "NA" : depositData.getUtmSource();

			String nomineePresent = depositData.getNomineeCheck() == null ? "NA": depositData.getNomineeCheck();
			String nsdlApiResponse=depositData.getNsdlApiResponse()==null?"NA":depositData.getNsdlApiResponse();

			String nomineeTitle = depositData.getNomineeSalutation() == null? "NA": depositData.getNomineeSalutation();
			String mainSalution = depositData.getSalutation() ==  null? "NA":depositData.getSalutation();

			String guardianPresent=depositData.getGaurdianCheck()== null?"NO":depositData.getGaurdianCheck();
			String guradianAge=depositData.getNomineeGuardianAge()==null?"":depositData.getNomineeGuardianAge();
			String guradianAddreess=depositData.getNomineeGuardianAddress()==null?"":depositData.getNomineeGuardianAddress();			
			String guradianCity=depositData.getNomineeGuardianCity()==null?"":depositData.getNomineeGuardianCity();
			String guradianPincode=depositData.getNomineeGuardianPincode()==null?"":depositData.getNomineeGuardianPincode();
			String guradianName=depositData.getNomineeGuardianName()==null?"":depositData.getNomineeGuardianName();
			String guradianState=depositData.getNomineeGuardianState()==null?"":depositData.getNomineeGuardianState();

			String relativeOrDirector=depositData.getRelativeOfDirector()== null?"NA":depositData.getRelativeOfDirector();
			String directorOrPromotor=depositData.getDirectorOrPromoter()==null?"NA":depositData.getDirectorOrPromoter();
			String shareHolder=depositData.getShareholder()==null?"NA":depositData.getShareholder();
			String ipAddress=depositData.getIpAdress()==null?"NA":depositData.getIpAdress();
			String commAddressPresent=depositData.getCommCheckbox()==null?"":depositData.getCommCheckbox();
						
			String aadharNumber=depositData.getAadharNumber()==null?"NA" : depositData.getAadharNumber();
			String ipv4 = "(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])";
			String ipv6 ="((([0-9a-fA-F]){1,4})\\:){7}([0-9a-fA-F]){1,4}";
		    if(!ipAddress.matches(ipv4) && !ipAddress.matches(ipv6))
		    {
		    	ipAddress = "";
		    }
		    logger.info("::::::::::::ipAddress FD after regex::::::::::"+ipAddress);
		    
		    String panEdited = depositData.getPanedited()==null?"":depositData.getPanedited();
		    String paymentSuccessTime = depositData.getPaymentSuccessTime() == null ? "NA" : depositData.getPaymentSuccessTime();
		    logger.info("::::::::::::paymentSuccessTime FD::::::::::"+paymentSuccessTime);

			String createdon = cretedOn;
			Date date1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(createdon);
			SimpleDateFormat formatter1DDob = new SimpleDateFormat("dd/MM/yyyy;HH:mm:ss");      
			String currentDateFd = formatter1DDob.format(date1);

			String category="";
			if("yes".equalsIgnoreCase(relativeOrDirector))
				category = "Relative of Director";
			if("yes".equalsIgnoreCase(directorOrPromotor))
				category = (category==""? "Director or Promoter of BFL" : category+ "," +"Director or Promoter of BFL");
			if("yes".equalsIgnoreCase(shareHolder))
				category = (category==""? "Shareholder" : category+ "," +"Shareholder");


			if("Mr.".equalsIgnoreCase(mainSalution)){
				gender="MALE";
			}else if("NA".equalsIgnoreCase(mainSalution)){
				gender="Other";
				mainSalution="Mr.";
			}
			else{
				gender="FEMALE";
			}

			String nomineeGender= "";

			if("Mr.".equalsIgnoreCase(nomineeTitle)){
				nomineeGender="MALE";
			}else if("NA".equalsIgnoreCase(nomineeTitle)){
				nomineeGender="Other";
				nomineeTitle="Mr.";
			}
			else{
				nomineeGender="FEMALE";
			}

			String commAdd;
			String commPin;
			String PermAndResddressAreSameheck;
			if("Yes".equalsIgnoreCase(commCheckbox)){

				commAdd=address;
				commPin= pincode;
				PermAndResddressAreSameheck="Yes";
			}else{
				commAdd=commAddress;
				commPin= commPincode;
				PermAndResddressAreSameheck="No";
			}

			if (("NTBDocumentUpload".equalsIgnoreCase(remarkCustType)))
			{
				remarkCustType="Doc_Upload";

			}
			logger.info("====Nominee Adress presendt======"+nomineeAddressCheck+"---Payment Mode---"+paymentMode+" ----utmSource--"+utmSource);
			if("".equalsIgnoreCase(cretedOn)){
				cretedOn = new SimpleDateFormat("yyyyMMdd").format(new Date());
			}else{
				cretedOn = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(cretedOn));
			}

			if("SUCCESS".equalsIgnoreCase(requeryFlag)){
				sfdcApiRecordId = "";	
			}


			if("NA".equalsIgnoreCase(nomineeRelation)){
				nomineeRelation = "Others";
			}

			if("NA".equalsIgnoreCase(nomineeDateOfBirth)){
				nomineeDateOfBirth = "01/01/1900";
			}else{
				nomineeDateOfBirth = Utility.dateFormerter(nomineeDateOfBirth);
			}

			String bankName = depositData.getBankName() == null ? "NA" : depositData.getBankName();

			if("UPI".equalsIgnoreCase(paymentMode)){

				paymentMode= "UPI CC Avenue";
			}else{
				paymentMode = "Netbanking CC Avenue";
			}
			logger.info("===========paymentMode=========="+paymentMode);

			String formSumissionDate =new SimpleDateFormat("MM/dd/yyyy").format(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(paymentSuccessTime));
			logger.info(":::::::::formSumissionDate FD:::::::::"+formSumissionDate);

			if(!("NA".equalsIgnoreCase(dateOfBirth))){
				dateOfBirth = Utility.dateFormerter(dateOfBirth);
			}
			logger.info("====sfdc FD B Date======"+dateOfBirth);

			if("OnMaturity".equalsIgnoreCase(interestPayoutFreq)){
				interestPayoutFreq = "Annual";
			}

			if("HalfYearly".equalsIgnoreCase(interestPayoutFreq)){
				interestPayoutFreq = "Half Yearly";
			}

			logger.info(" == investmentType === " + investmentType + " == customerSubtype === "+ customerSubtype + " === depositOption === "+ depositOption + " === interestPayoutFreq === "+ interestPayoutFreq);
			String locationType ="NA";

			if(!("NA".equalsIgnoreCase(pincode))){
				int reqPin = Integer.parseInt(pincode);
				FDPincodeMaster fdPiMast = fixedDepositDao.getcitystatename(reqPin);
				if(fdPiMast != null){
					locationType = fdPiMast.getLocationType() == null ? "Urban": fdPiMast.getLocationType();
					state = fdPiMast.getState() == null ? "NA" : fdPiMast.getState();
				}else{
					locationType = "Urban";
				}
			}

			String branchcode= "";
			if("Rural".equalsIgnoreCase(locationType)){
				branchcode = "521";
			}else{
				branchcode = "320";
			}

			schemeCode = fixedDepositDao.getSchemeCode(investmentType, customerSubtype, depositOption, interestPayoutFreq, locationType);

			logger.info(" === schemeCode in  ekycFdSfdcService ==== " + schemeCode);

			JSONObject fullNameObje = Utility.fullNameManipulation(fullName);
			String fName =fullNameObje.has("fName")?  fullNameObje.get("fName").toString() : "NA";
			String lName =fullNameObje.has("lName")?  fullNameObje.get("lName").toString() : ".";
			String mName =fullNameObje.has("mName")?  fullNameObje.get("mName").toString() : "";

			String nomineeFname = "";
			String nomineeLname = "";

			if(!("NA".equalsIgnoreCase(nomineeName)))
			{ 
				nomineeFname=nomineeName.contains(" ")?nomineeName.substring(0,nomineeName.indexOf(' ')).trim():nomineeName;
				nomineeLname=nomineeName.contains(" ")?nomineeName.substring(nomineeName.indexOf(' ')+1).trim():".";

			}else{
				nomineeFname ="NA";
				nomineeLname ="NA";
			}



			logger.info(" == fName === " + fName + " === last Name === " + lName);
			logger.info(" === partnerCode === " + partnerCode);

			if("".equalsIgnoreCase(lName) || lName.isEmpty()){
				lName= ".";
			}

			if("Annually".equalsIgnoreCase(interestPayoutFreq)){
				interestPayoutFreq = "Annual";
			}


			String broker ="";
			String brokerCode = "";
			String ckkycDocument ="";
			String ckycDocumentNameFile ="";
			String ckycDocType="";
			String panckkycDocument ="";
			String panckycDocumentNameFile ="";
			String panckycDocType="";
			String ckycNo="";
			String AddrProof="";
			String identityDocType="";

			if(!(partnerCode.isEmpty())){
				broker = "Broker";
				brokerCode = partnerCode;
			}

			logger.info(" === broker in  ekycFdSfdcService ==== " + broker);
			customerId = depositData.getCustomerId();


			String cKStatus = depositData.getCkycStatus() == null ? "NA" : depositData.getCkycStatus();

			if ("YES".equalsIgnoreCase(cKStatus)) {

				ckycNo = existingCustId;

				existingCustId= "";
				String filePath = Utility.getPropertyFileValue("ckycBase64filepath").trim();
				String filePathCreated = filePath + cretedOn + "/Request_"+customerId+ ".txt";

				String filePath1=depositData.getServrFilePath()== null?"NA":depositData.getServrFilePath();
				logger.info(" ===filePath1  ekycFdSfdcService ==== " + filePath1);
				filePath1="NA".equalsIgnoreCase(filePath1)?filePathCreated:filePath1;
				logger.info(" ===filePath1  ekycFdSfdcService updated if not present ==== " + filePath1);

				String DoucmnetData = custIdIntegration.readFile(filePath1);
				if (!DoucmnetData.isEmpty()) {

					JSONObject docJsonData = new org.json.JSONObject(DoucmnetData);
					ckkycDocument = docJsonData.has("CKYCImageData") ? docJsonData.get("CKYCImageData").toString()	: "NA";
					String ckycDocumentType = docJsonData.has("CKYCImageExtension")	? docJsonData.get("CKYCImageExtension").toString() : "NA";
					String ckycDocumentName = docJsonData.has("CKYCImageType") ? docJsonData.get("CKYCImageType").toString()	: "NA";
					logger.info("---------Document from file--------------" + ckycDocumentName + " --" + ckycDocumentType);
					ckycDocumentNameFile = ckycDocumentName + "." + ckycDocumentType;

					if("AadharCard".equalsIgnoreCase(ckycDocumentName)){
						ckycDocType="OVD-Applicant-Aadhar Card";
						AddrProof="OVD- Applicant-Aadhar Card";
						identityDocType="UID/Aadhaar";
					}else if("Passport".equalsIgnoreCase(ckycDocumentName)){
						ckycDocType="OVD-Applicant-Passport";
						AddrProof="OVD- Applicant-Passport";
						identityDocType="Passport";
					}else if("DrivingLicence".equalsIgnoreCase(ckycDocumentName)){
						ckycDocType="OVD-Applicant-Driving licence";
						AddrProof="OVD- Applicant-Driving licence";
						identityDocType="Driving Licence";
					}else if("VoterID".equalsIgnoreCase(ckycDocumentName)){
						ckycDocType="OVD-Applicant-Voter Identity card";
						AddrProof="OVD- Applicant-Voter Identity Card";
						identityDocType="Voter ID";
					}else if("NREGA".equalsIgnoreCase(ckycDocumentName)){
						ckycDocType="OVD-Applicant-NREGA Job Card";
						AddrProof="OVD- Applicant-NREGA Job Card";
						identityDocType="PAN";
					}else if("AadhaarOffline".equalsIgnoreCase(ckycDocumentName)){
						ckycDocType="OVD-Applicant-Aadhar Card";
						AddrProof="DOVD-Applicant-Others";
						identityDocType="PAN";
					}else if("NPR".equalsIgnoreCase(ckycDocumentName)){
						ckycDocType="OVD-Applicant-National population register letter";
						AddrProof="OVD- Applicant-National population register letter";
						identityDocType="PAN";
					}else if("Photograph".equalsIgnoreCase(ckycDocumentName)){
						ckycDocType="Photo-Applicant";
						AddrProof="DOVD- Applicant-Others";
						identityDocType="PAN";
					}else{
						ckycDocType="";
						AddrProof="DOVD- Applicant-Others";
						identityDocType="PAN";
					}

					if (docJsonData.has("panData")) {

						JSONObject panDatadocJsonData = docJsonData.getJSONObject("panData");
						panckkycDocument = panDatadocJsonData.has("CKYCImageData")	? panDatadocJsonData.get("CKYCImageData").toString(): "NA";
						String panckycDocumentType = panDatadocJsonData.has("CKYCImageExtension")	? panDatadocJsonData.get("CKYCImageExtension").toString() : "NA";
						String panckycDocumentName = panDatadocJsonData.has("CKYCImageType")	? panDatadocJsonData.get("CKYCImageType").toString() : "NA";
						logger.info("---------Document from Pan section file--------------" + panckycDocumentName + " --"+ panckycDocumentType);
						panckycDocumentNameFile = panckycDocumentName + "." + panckycDocumentType;

						if("PAN".equalsIgnoreCase(panckycDocumentName) || "PANCard".equalsIgnoreCase(panckycDocumentName)){
							panckycDocType="PAN_Card";
						}else{
							panckycDocType="";
						}

					}

				}
			}

			logger.info("-------remark Cust Type-------------" + remarkCustType);
			if (!(("DEDUPE".equalsIgnoreCase(remarkCustType)) || ("SSODEDUPE".equalsIgnoreCase(remarkCustType)))) {
				existingCustId = "";

			}

			String okycRequestId ="";
			String authCode = "";
			String aadharRefId= "";
			String okycMobile="";
			String okycEmail = "";
			String okycAPIStatus ="";
			String okycIpAddr= "";
			String okycCompletionDT="";
			String initiationDT="";

			if ("OKYC".equalsIgnoreCase(remarkCustType))
			{

				okycRequestId = customerId;
				authCode = depositData.getOkycAPIRequestID();
				aadharRefId= depositData.getOkycAadhaarreferenceId();
				okycMobile=phoneNo;
				okycEmail = email;
				okycAPIStatus ="Completed";
				okycIpAddr= depositData.getOkycIPAddress();
				okycCompletionDT= depositData.getOkycUpdateRecordDT();
				initiationDT= depositData.getOkycInitiationDT();



				String filePath = Utility.getPropertyFileValue("ckycBase64filepath").trim();
				String filePathCreated = filePath + cretedOn + "/Request_"+customerId+ ".txt";
				String filePath1=depositData.getServrFilePath()== null?"NA":depositData.getServrFilePath();
				logger.info(" ===filePath1  ekycFdSfdcService ==== " + filePath1);
				filePath1="NA".equalsIgnoreCase(filePath1)?filePathCreated:filePath1;
				logger.info(" ===filePath1  ekycFdSfdcService updated if not present ==== " + filePath1);

				String DoucmnetData = custIdIntegration.readFile(filePath1);
				if (!DoucmnetData.isEmpty()) {

					JSONObject docJsonData = new org.json.JSONObject(DoucmnetData);
					ckkycDocument = docJsonData.has("CKYCImageData") ? docJsonData.get("CKYCImageData").toString()	: "NA";
					String ckycDocumentType = docJsonData.has("CKYCImageExtension")	? docJsonData.get("CKYCImageExtension").toString() : "NA";
					String ckycDocumentName = docJsonData.has("CKYCImageType") ? docJsonData.get("CKYCImageType").toString()	: "NA";
					logger.info("---------Document from file--------------" + ckycDocumentName + " --" + ckycDocumentType);
					ckycDocumentNameFile = ckycDocumentName + "." + ckycDocumentType;

					if (docJsonData.has("ockycDocPhoto")) {

						panckkycDocument = docJsonData.has("ockycDocPhoto")	? docJsonData.get("ockycDocPhoto").toString(): "NA";

						panckycDocumentNameFile = "ockycDocPhoto.jpeg";
						
						if("ockycDocPhoto.jpeg".equalsIgnoreCase(panckycDocumentNameFile)){
							panckycDocType="Photograph_OKYC";
							logger.info("---------panckycDocType from file--------------"+panckycDocType);
						}else{
							panckycDocType="";
							logger.info("---------panckycDocType from file--------------"+panckycDocType);

						}
					}

				}

				/************************
				 * okyc Pdf Stamping Call
				 ************/
				JSONObject okcyPdf = dataDownloadService.okycPdfManipulation(customerId);

				ckkycDocument = okcyPdf.has("fileEncode") ? okcyPdf.get("fileEncode").toString(): "";


				if (ckkycDocument.isEmpty()) {
					ckycDocumentNameFile = "";
					ckycDocType = "";
				} else {
					ckycDocumentNameFile="OVD-Applicant-Aadhar Card.pdf";
					ckycDocType="OVD-Applicant-Aadhar Card";
				}
			}

			String commAddfileEncode="";
			String commAddfileName ="";
			String commAdddocType ="";

			if("No".equalsIgnoreCase(commAddressPresent))
			{
				String propetyFilepathForcommdocumentUploadPath = Utility.getPropertyFileValue("communicationUploadPath").trim();
				String filledPathcommDocument = propetyFilepathForcommdocumentUploadPath+cretedOn+"/"+customerId+"/";
				logger.info(" === Commmunication Document  path ==== " + filledPathcommDocument);

				String documentName=depositData.getCommAddDocName()==null?"NA":depositData.getCommAddDocName(); 

				if("Aadhaar Card".equalsIgnoreCase(documentName)){
					commAdddocType="OVD-Applicant-Aadhar Card";
				}else if("Valid Passport".equalsIgnoreCase(documentName)){
					commAdddocType="OVD-Applicant-Passport";
				}else if("Valid Driving Licence".equalsIgnoreCase(documentName)){
					commAdddocType="OVD-Applicant-Driving licence";
				}else if("Voter ID Card".equalsIgnoreCase(documentName)){
					commAdddocType="OVD-Applicant-Voter Identity card";
				}else if("NREGA Job Card".equalsIgnoreCase(documentName)){
					commAdddocType="OVD-Applicant-NREGA Job Card";
				}else if("Letter issued by National Population Register".equalsIgnoreCase(documentName)){
					commAdddocType="OVD-Applicant-National population register letter";
				}else{
					commAdddocType="DOVD- Applicant-Others";
				}

				File filePoi = new File(filledPathcommDocument);
				for (File subFile : filePoi.listFiles()) 
				{
					String FilePath = subFile.getAbsolutePath();
					logger.info(" === FilePath To convert Base 64 == " + subFile.getName());
					JSONObject sfdcPdf = dataDownloadService.sfdcDocumentConvertBase64Service(FilePath);

					commAddfileEncode = sfdcPdf.has("fileEncode") ? sfdcPdf.get("fileEncode").toString() : "";
					commAddfileName = sfdcPdf.has("fileName") ? sfdcPdf.get("fileName").toString() : "";
					// commAdddocType = commAddfileName.substring(0, commAddfileName.lastIndexOf('.')).replace('.', ' ').replaceAll("( +)", " ").trim();
				}
			}


			/************************Audit Trail Pdf Stamping Call************/
			JSONObject auditTrailPdf = dataDownloadService.fdAuditTrailPdf(customerId);

			String auditTrailfileEncode = auditTrailPdf.has("fileEncode") ? auditTrailPdf.get("fileEncode").toString() : "";
			String auditTrailfileName = auditTrailPdf.has("fileName") ? auditTrailPdf.get("fileName").toString() : "";
			String auditTrailfileNameDocType = "";
			if(auditTrailfileEncode.isEmpty()){
				auditTrailfileName = "";
				auditTrailfileNameDocType="";
			}else{
				auditTrailfileNameDocType = auditTrailfileName.substring(0, auditTrailfileName.lastIndexOf('.')).replace('.', ' ').replaceAll("( +)"," ").trim();
			}




			logger.info(" === broker in  ekycFdSfdcService ==== " + broker);

			JSONObject addrManipulat = Utility.addresManipulation(formFieldValidation.SFDCAddressValidate(address));
			JSONObject commAddrManipulat = Utility.addresManipulation(formFieldValidation.SFDCAddressValidate(commAdd));

			String addLine1 = addrManipulat.has("add1") ? addrManipulat.get("add1").toString() : ".";
			String addLine2 = addrManipulat.has("add2") ? addrManipulat.get("add2").toString() : ".";
			String addLine3 = addrManipulat.has("add3") ? addrManipulat.get("add3").toString() : ".";
			String addLine4 = addrManipulat.has("add4") ? addrManipulat.get("add4").toString() : ".";

			String comaddLine1 = commAddrManipulat.has("add1") ? commAddrManipulat.get("add1").toString() : ".";
			String comaddLine2 = commAddrManipulat.has("add2") ? commAddrManipulat.get("add2").toString() : ".";
			String comaddLine3 = commAddrManipulat.has("add3") ? commAddrManipulat.get("add3").toString() : ".";
			String comaddLine4 = commAddrManipulat.has("add4") ? commAddrManipulat.get("add4").toString() : ".";

			String nomaddLine1 = ".";
			String nomaddLine2 = ".";
			String nomaddLine3 = ".";
			String nomaddLine4=".";
			if ("YES".equalsIgnoreCase(nomineeAddressCheck)) {
				JSONObject nominAddrManipulat = Utility.addresManipulation(formFieldValidation.SFDCAddressValidate(nomineeAddress));
				nomaddLine1 = nominAddrManipulat.has("add1") ? nominAddrManipulat.get("add1").toString() : ".";
				nomaddLine2 = nominAddrManipulat.has("add2") ? nominAddrManipulat.get("add2").toString() : ".";
				nomaddLine3 = nominAddrManipulat.has("add3") ? nominAddrManipulat.get("add3").toString() : ".";
				nomaddLine4 = nominAddrManipulat.has("add4") ? nominAddrManipulat.get("add4").toString() : ".";
			} else {
				nomaddLine1 = addLine1;
				nomaddLine2 = addLine2;
				nomaddLine3 = addLine3;
				nomaddLine4=addLine4;
			}

			String guaAddLine1 =".";
			String guaAddLine2 =".";
			String guaAddLine3 =".";
			if ("YES".equalsIgnoreCase(guardianPresent)) {
				JSONObject gaurdianAddress = Utility.addresLineThreeManipulation(formFieldValidation.SFDCAddressValidate(guradianAddreess));
				guaAddLine1 = gaurdianAddress.has("add1") ? gaurdianAddress.get("add1").toString() : ".";
				guaAddLine2 = gaurdianAddress.has("add2") ? gaurdianAddress.get("add2").toString() : ".";
				guaAddLine3 = gaurdianAddress.has("add3") ? gaurdianAddress.get("add3").toString() : ".";
			}


			String paymentprocess =  Utility.getPropertyFileValue("paymentprocess").trim();

			if("Billdesk".equalsIgnoreCase(paymentprocess)){
				BankNameDetails bankNameDetails = fixedDepositDao.getBankDetailsforPayment(bankName);
				String bankStatus = bankNameDetails.getBanktype()== null ? "CCAvenue" : bankNameDetails.getBanktype();

				if("Both Bank".equalsIgnoreCase(bankStatus)){

					if ("UPI".equalsIgnoreCase(paymentMode)) {
						paymentMode = "UPI";
					}else{
						paymentMode = "Netbanking";
					}

				}else{
					paymentprocess="CCAvenue";
				}

			}


			/*********IMPS Data from database*******/
			String impsValidationTimestamp="";
			String impsResponseDesc="";
			String impsResponseCode="";
			String impsBeneficiaryName="";
			String impsBankName="";
			String impstraceName="";
			try{
				IMPSFixedDepositData impsFixedDepositData=fixedDepositDao.getImpsDepositData(customerId);
				if(impsFixedDepositData != null){
					impsValidationTimestamp=impsFixedDepositData.getCreatedOn()==null?"NA":impsFixedDepositData.getCreatedOn();
					impsResponseDesc=impsFixedDepositData.getiMPSrespDesc()==null?"NA":impsFixedDepositData.getiMPSrespDesc();
					impsResponseCode=impsFixedDepositData.getImpsResponseCode()==null?"NA":impsFixedDepositData.getImpsResponseCode();
					impsBeneficiaryName=impsFixedDepositData.getiMPSbeneName()==null?"NA":impsFixedDepositData.getiMPSbeneName();
					impsBankName=impsFixedDepositData.getiMPStrnBankName()==null?"NA":impsFixedDepositData.getiMPStrnBankName();
					impstraceName=impsFixedDepositData.getiMPStraceNumber()==null?"NA":impsFixedDepositData.getiMPStraceNumber();
				}
			}catch(Exception e)
			{
				logger.error(" === Exception in getImpsDepositData === ", e);	
			}


			ObjFDDetails objFDDetails = new ObjFDDetails();

			objFDDetails.setAppFormNo(formAppNumber); //
			objFDDetails.setDepositCategory("FD");
			objFDDetails.setCustomerType("Individual");
			objFDDetails.setCustomerSubtype(customerSubtype);
			objFDDetails.setSourceBy(broker);
			objFDDetails.setChannel("Website Direct");
			objFDDetails.setSourcingChannel(sourcingChannel);
			objFDDetails.setDepositPayableTo("First Holder");
			objFDDetails.setBranch(branchcode);
			objFDDetails.setBroker(brokerCode); 
			objFDDetails.setExistingCustID(existingCustId);
			objFDDetails.setDepositOption(depositOption);
			objFDDetails.setInterestPayoutFreq(interestPayoutFreq);
			objFDDetails.setScheme(schemeCode);
			objFDDetails.setTotalFDAmount(fDAmount); 
			objFDDetails.setFDAmount(fDAmount);
			objFDDetails.setTenorDays("0");
			objFDDetails.setTenorMonth(tenorMonth);
			objFDDetails.setInterest(interest);
			objFDDetails.setIsComplete("Y");
			//objFDDetails.setRenewOption(autoRenewOption);
			objFDDetails.setUTMMEDIUM(utmMedium);
			objFDDetails.setUTMCAMAPAIGN(utmCampaign);
			objFDDetails.setUTMKEYWORD(utmKeyword);
			objFDDetails.setUTMcontent(pincode);
			objFDDetails.setPAGEURL(pageUrl);
			objFDDetails.setDEVICE(device);
			objFDDetails.setLASTCLICK(lastClick);
			objFDDetails.setCLIENTID(clientId);
			objFDDetails.setProductOfferingSrc(productOfferingSrc);
			objFDDetails.setPhoneNo(phoneNo);
			objFDDetails.setFullName(fullName);
			objFDDetails.setEmail(email);
			objFDDetails.setCity(city);
			objFDDetails.setIsSDP("N");
			objFDDetails.setRdplLan(fdrdplan1);
			objFDDetails.setLoanType(loanType);
			objFDDetails.setGclld(gclld);
			objFDDetails.setFinnoneCustomerID("");
			objFDDetails.setRemarks(remarkCustType);
			objFDDetails.setUTMSOURCE(utmSource);
			objFDDetails.setAppGeneration("Y");

			ObjInvestmentAccDetails objInvestmentAccDetails = new ObjInvestmentAccDetails();
			objInvestmentAccDetails.setPaymentMode(paymentMode); 
			objInvestmentAccDetails.setAccHolderName(fullName);
			objInvestmentAccDetails.setAccountNo(accountNumber);
			objInvestmentAccDetails.setBank(ifscCode); 
			objInvestmentAccDetails.setChequeDate(formSumissionDate); 
			objInvestmentAccDetails.setInvAndMatAccBelongToYou("Yes");
			objInvestmentAccDetails.setUTR(utr);
			objInvestmentAccDetails.setTDSWaiver("N");
			objInvestmentAccDetails.setBankAccType("Savings");
			objInvestmentAccDetails.setMaturityInvestmentAccountAreSame("Yes");

			if("Billdesk".equalsIgnoreCase(paymentprocess)){
				objInvestmentAccDetails.setDepositBankNm("Urban HDFC FD Coll Acc - 00070350006738 - Urban");
				objInvestmentAccDetails.setBankGLCode("4117");
			}

			if("CCAvenue".equalsIgnoreCase(paymentprocess)){
				if (!("Netbanking".equalsIgnoreCase(paymentMode))) {
					objInvestmentAccDetails.setDepositBankNm("IndusInd CC Avenue FD Coll 201006586448");
					objInvestmentAccDetails.setBankGLCode("80018");
				}
			}
			
			if("PayU".equalsIgnoreCase(paymentprocess)){
				objInvestmentAccDetails.setDepositBankNm("IndusInd CC Avenue FD Coll 201006586448");
				objInvestmentAccDetails.setBankGLCode("80018");
			}

			ObjMaturityAccDetails objMaturityAccDetails = new ObjMaturityAccDetails();
			objMaturityAccDetails.setAccountNo(accountNumber);
			objMaturityAccDetails.setBank(ifscCode);
			objMaturityAccDetails.setAccHolderName(fullName);

			objMaturityAccDetails.setImpsValidationTimeStamp(impsValidationTimestamp);
			objMaturityAccDetails.setImpsResponseDescription(impsResponseDesc);
			objMaturityAccDetails.setImpsResponseCode(impsResponseCode);
			objMaturityAccDetails.setImpsBeneficiaryName(impsBeneficiaryName);
			objMaturityAccDetails.setImpsBankName(impsBankName);
			objMaturityAccDetails.setImpsTraceNo(impstraceName);


			ObjKycDoc objKycDoc3 = new ObjKycDoc();
			objKycDoc3.setDocName(auditTrailfileName);
			objKycDoc3.setDocContent(auditTrailfileEncode);
			objKycDoc3.setDocType(auditTrailfileNameDocType);

			ObjKycDoc objKycDoc1 = new ObjKycDoc();
			objKycDoc1.setDocName(ckycDocumentNameFile);
			objKycDoc1.setDocContent(ckkycDocument);
			objKycDoc1.setDocType(ckycDocType);

			ObjKycDoc objKycDoc2 = new ObjKycDoc();
			objKycDoc2.setDocName(panckycDocumentNameFile);
			objKycDoc2.setDocContent(panckkycDocument);
			objKycDoc2.setDocType(panckycDocType);
			
			ObjKycDoc objKycDoc4 = new ObjKycDoc();
			if("No".equalsIgnoreCase(commAddressPresent)){
				objKycDoc4.setDocName(commAddfileName);
				objKycDoc4.setDocContent(commAddfileEncode);
				objKycDoc4.setDocType(commAdddocType);
			}			

			ObjApplDetail objApplDetail = new ObjApplDetail();
			objApplDetail.setCustID(existingCustId);
			objApplDetail.setApplicantType("Primary Applicant");
			objApplDetail.setFName(fName);
			objApplDetail.setLName(lName);
			objApplDetail.setMName(mName);
			objApplDetail.setDateBirthValue(dateOfBirth);
			objApplDetail.setMobile(phoneNo);
			objApplDetail.setEmail(email);
			objApplDetail.setPAN(pan);
			objApplDetail.setForm6061("");
			objApplDetail.setOccupationEmplType("Private Sector");
			objApplDetail.setIdentityDocType("CKYC".equalsIgnoreCase(remarkCustType)?identityDocType:"PAN");
			objApplDetail.setCKYCNo(ckycNo);
			objApplDetail.setMaritalStatus("Single"); 
			objApplDetail.setFatherSpouse("Father"); 
			objApplDetail.setFatherSpouseSalutation("Mr.");
			objApplDetail.setFatherSpouseFName("NA");
			objApplDetail.setFatherSpouseMName("NA");
			objApplDetail.setFatherSpouseLName("NA");
			objApplDetail.setMotherFName("NA");
			objApplDetail.setMotherMName("NA");
			objApplDetail.setMotherLName("NA");
			objApplDetail.setAnnualIncome("Upto 15Lacs");
			objApplDetail.setLandline("0");
			objApplDetail.setCustTdsType("INDIVIDUAL");
			objApplDetail.setResidentType("Indian");
			objApplDetail.setIdentityDocNo("OKYC".equalsIgnoreCase(remarkCustType) ? aadharNumber: pan);


			objApplDetail.setSalutation(mainSalution);
			objApplDetail.setGender(gender);

			objApplDetail.setOkycRequestId(okycRequestId);
			objApplDetail.setAuthCode(authCode);
			objApplDetail.setAadharRefId(aadharRefId);
			objApplDetail.setOkycMobile(okycMobile);
			objApplDetail.setOkycEmail(okycEmail);
			objApplDetail.setOkycAPIStatus(okycAPIStatus);
			objApplDetail.setOkycIpAddr(okycIpAddr);
			objApplDetail.setOkycCompletionDT(okycCompletionDT);
			objApplDetail.setInitiationDT(initiationDT);
			objApplDetail.setNsdlResponse(nsdlApiResponse);
			objApplDetail.setDemogUpdate("Yes");
			objApplDetail.setCustomerCategory(category);
			objApplDetail.setAcceptanceTimeStamp(currentDateFd);
			objApplDetail.setIPAddress(ipAddress);
			if(panEdited.equalsIgnoreCase("etbDedupeUserPanEdited")) {
			objApplDetail.setIspanedited("Y");
			}else if(panEdited.equalsIgnoreCase("etbDedupeUser")) {
			objApplDetail.setIspanedited("N");
			}

			AdressDetailsList permanentAddress = new AdressDetailsList();
			permanentAddress.setAddrType("Permanent");
			permanentAddress.setAddress1(addLine1); 
			permanentAddress.setAddress2(addLine2); 
			permanentAddress.setAddress3(addLine3); 
			permanentAddress.setAddress4(addLine4); 
			permanentAddress.setCity(city); 
			permanentAddress.setState(state); 
			permanentAddress.setPincode(pincode);
			permanentAddress.setCountry("India");
			permanentAddress.setPermAndResddressAreSame(PermAndResddressAreSameheck);
			permanentAddress.setPrefferedCommAddress("No");
			if("CKYC".equalsIgnoreCase(remarkCustType))
			{
				permanentAddress.setAddrProof(AddrProof);
			}
			else if("OKYC".equalsIgnoreCase(remarkCustType))
			{
				permanentAddress.setAddrProof("OVD-Applicant-Aadhar Card");
			}else
			{
				permanentAddress.setAddrProof("UID/Aadhaar Card");
			}

			AdressDetailsList residenceAddress = new AdressDetailsList();
			residenceAddress.setAddrType("Residence");
			residenceAddress.setAddress1(comaddLine1); 
			residenceAddress.setAddress2(comaddLine2); 
			residenceAddress.setAddress3(comaddLine3); 
			residenceAddress.setAddress4(comaddLine4); 
			residenceAddress.setCity(city); 
			residenceAddress.setState(state); 
			residenceAddress.setPincode(commPin);
			residenceAddress.setCountry("India");
			residenceAddress.setPermAndResddressAreSame(PermAndResddressAreSameheck);
			residenceAddress.setPrefferedCommAddress("Yes");
			if("CKYC".equalsIgnoreCase(remarkCustType))
			{
				residenceAddress.setAddrProof(AddrProof);
			}
			else if("OKYC".equalsIgnoreCase(remarkCustType))
			{
				residenceAddress.setAddrProof("OVD-Applicant-Aadhar Card");
			}else
			{
				residenceAddress.setAddrProof("UID/Aadhaar Card");
			}

			objApplDetail.setAdressDetailsList(Arrays.asList(permanentAddress,residenceAddress));


			AdressDetailsList nomineePermanentAddress = new AdressDetailsList();
			nomineePermanentAddress.setAddrType("Permanent");
			nomineePermanentAddress.setAddress1(nomaddLine1); 
			nomineePermanentAddress.setAddress2(nomaddLine2); 
			nomineePermanentAddress.setAddress3(nomaddLine3); 
			nomineePermanentAddress.setAddress4(nomaddLine4); 
			nomineePermanentAddress.setCity("YES".equalsIgnoreCase(nomineeAddressCheck)?nomineeCity:city); 
			nomineePermanentAddress.setState("YES".equalsIgnoreCase(nomineeAddressCheck)?nomineeState:state); 
			nomineePermanentAddress.setPincode("YES".equalsIgnoreCase(nomineeAddressCheck)?nomineePincode:pincode);
			nomineePermanentAddress.setCountry("India");
			nomineePermanentAddress.setPermAndResddressAreSame("Yes");
			nomineePermanentAddress.setPrefferedCommAddress("No");
			nomineePermanentAddress.setAddrProof("UID/Aadhaar Card");

			AdressDetailsList nomineeResidenceAddress = new AdressDetailsList();
			nomineeResidenceAddress.setAddrType("Residence");
			nomineeResidenceAddress.setAddress1(nomaddLine1); 
			nomineeResidenceAddress.setAddress2(nomaddLine2); 
			nomineeResidenceAddress.setAddress3(nomaddLine3); 
			nomineeResidenceAddress.setAddress4(nomaddLine3); 
			nomineeResidenceAddress.setCity("YES".equalsIgnoreCase(nomineeAddressCheck)?nomineeCity:city); 
			nomineeResidenceAddress.setState("YES".equalsIgnoreCase(nomineeAddressCheck)?nomineeState:state); 
			nomineeResidenceAddress.setPincode("YES".equalsIgnoreCase(nomineeAddressCheck)?nomineePincode:pincode);
			nomineeResidenceAddress.setCountry("India");
			nomineeResidenceAddress.setPermAndResddressAreSame("No");
			nomineeResidenceAddress.setPrefferedCommAddress("Yes");
			nomineeResidenceAddress.setAddrProof("UID/Aadhaar Card");


			ObjApplDetail objApplDetailNominee = new ObjApplDetail();
			objApplDetailNominee.setCustID("");
			objApplDetailNominee.setApplicantType("Nominee");
			objApplDetailNominee.setSalutation(nomineeTitle);
			objApplDetailNominee.setFName(nomineeFname);
			objApplDetailNominee.setMName("");
			objApplDetailNominee.setLName(nomineeLname);
			objApplDetailNominee.setDateBirthValue(nomineeDateOfBirth);
			objApplDetailNominee.setGender(nomineeGender);
			objApplDetailNominee.setMobile("");   
			objApplDetailNominee.setEmail("");     
			objApplDetailNominee.setFatherSpouse("Father"); 
			objApplDetailNominee.setFatherSpouseSalutation("Mr.");
			objApplDetailNominee.setFatherSpouseFName("NA"); 
			objApplDetailNominee.setFatherSpouseMName("NA"); 
			objApplDetailNominee.setFatherSpouseLName("NA"); 
			objApplDetailNominee.setMotherFName("NA");
			objApplDetailNominee.setMotherMName("NA");
			objApplDetailNominee.setMotherLName("NA");
			objApplDetailNominee.setAnnualIncome("Upto 15Lacs");
			objApplDetailNominee.setNomineeRelationship(nomineeRelation);
			objApplDetailNominee.setResidentType("Indian");
			objApplDetailNominee.setGaurdianAge(guradianAge);
			objApplDetailNominee.setGuardianAddress1(guaAddLine1);
			objApplDetailNominee.setGuardianAddress2(guaAddLine2);
			objApplDetailNominee.setGuardianAddress3(guaAddLine3);
			objApplDetailNominee.setGuardianCity(guradianCity);
			objApplDetailNominee.setGuardianPincode("YES".equalsIgnoreCase(guardianPresent)?guradianPincode:"");
			objApplDetailNominee.setGuardianState(guradianState);
			objApplDetailNominee.setGuardianCountry("YES".equalsIgnoreCase(guardianPresent)?"India":"");
			objApplDetailNominee.setGuardianName(guradianName);
			objApplDetailNominee.setGuardianSaluttion("YES".equalsIgnoreCase(guardianPresent)?"Mr.":"");

			objApplDetailNominee.setAdressDetailsList(Arrays.asList(nomineePermanentAddress,nomineeResidenceAddress));
			objApplDetailNominee.setDemogUpdate("Yes");

			FDRecordsObjList fdRecordsObjList = new FDRecordsObjList();
			fdRecordsObjList.setRecUniqueKey("542");
			fdRecordsObjList.setObjFDDetails(objFDDetails);
			fdRecordsObjList.setObjInvestmentAccDetails(objInvestmentAccDetails);
			fdRecordsObjList.setObjMaturityAccDetails(objMaturityAccDetails);
			fdRecordsObjList.setObjKycDoc(Arrays.asList(objKycDoc1, objKycDoc2,objKycDoc3,objKycDoc4));
			fdRecordsObjList.setObjApplDetails("YES".equalsIgnoreCase(nomineePresent)?(Arrays.asList(objApplDetail, objApplDetailNominee)):(Arrays.asList(objApplDetail)));

			RecWrapperFD recWrapperFD = new RecWrapperFD();
			recWrapperFD.setFDRecordsObjList(Arrays.asList(fdRecordsObjList));


			SfdcRequest finalRequest = new SfdcRequest();
			finalRequest.setRecWrapperFD(recWrapperFD);


			ObjectMapper mapper = new ObjectMapper();
			String fdSfdcFinalRequest = mapper.writeValueAsString(finalRequest);


			logger.info(" === final EKYC FD SFDC Request in ekycFdSfdcService ==== " + fdSfdcFinalRequest);

			sfdcRes = sfdcIntegration.partialSfdc(fdSfdcFinalRequest,customerId,contextCall);
			logger.info(" === FD SFDC Response in ekycFdSfdcService ==== " + sfdcRes);


		} catch (Exception e) {
			logger.error(" ==== Exception in ekycFdSfdcService ==== ", e);
			if(!( customerId.isEmpty())){
				dbManipulation.recordExeption("SFDC_FD_SERVICE","Exception due to internal call", customerId);
			}
			Utility.loadNewRelicException(e.toString(), "ekycFdSfdcService", "", customerId);
		}

		return sfdcRes;
	}
	@Trace
	private JSONObject ekycSdpSfdcService(FixedDepositData depositData,String contextCall){

		JSONObject sfdcRes = new JSONObject();
		logger.info(" === SDP EKYC SFDC in ekycSdpSfdcService ==== ");
		String customerId="";
		try {
			customerId=depositData.getCustomerId()==null?"":depositData.getCustomerId();
			String customerSubtype = depositData.getCustomerType() == null ? "NA" : depositData.getCustomerType();
			String existingCustId = depositData.getExistingCustId() == null ? "NA" : depositData.getExistingCustId();
			String depositOption = depositData.getInterestPayoutType() == null ? "NA" : depositData.getInterestPayoutType();
			String interestPayoutFreq = depositData.getInterestPayout() == null ? "NA" : depositData.getInterestPayout();
			String fDAmount = depositData.getDepositAmount() == null ? "NA" : depositData.getDepositAmount();
			String tenorMonth = depositData.getTenor() == null ? "NA" : depositData.getTenor();
			String interest = depositData.getInterestRate() == null ? "NA" : depositData.getInterestRate();
			String utmMedium = depositData.getUtmMedium() == null ? "NA" : depositData.getUtmMedium();
			String utmCampaign = depositData.getUtmCampaign() == null ? "NA" : depositData.getUtmCampaign();
			String utmKeyword = depositData.getUtmkeyword() == null ? "NA" : depositData.getUtmkeyword();
			String pageUrl = depositData.getPageUrl() == null ? "NA" : depositData.getPageUrl();
			String device = depositData.getDevice() == null ? "NA" : depositData.getDevice();
			String clientId = depositData.getClientId() == null ? "NA" : depositData.getClientId();
			String phoneNo = depositData.getMobileNumber() == null ? "NA" : depositData.getMobileNumber();
			String fullName = depositData.getFullName() == null ? "NA" : depositData.getFullName();
			String email = depositData.getEmailAddress() == null ? "NA" : depositData.getEmailAddress();
			String city = depositData.getCity() == null ? "NA" : depositData.getCity();
			String state = depositData.getState() == null ? "NA" : depositData.getState();
			String accountNumber = depositData.getBankAccountNumber() == null ? "NA" : depositData.getBankAccountNumber();
			String ifscCode = depositData.getIfscCode() == null ? "NA" : depositData.getIfscCode();
			String utr = depositData.getUtrNumber() == null ? "NA" : depositData.getUtrNumber();
			String dateOfBirth = depositData.getDateOfBirth() == null ? "NA" : depositData.getDateOfBirth();
			String pan = depositData.getPanCard() == null ? "NA" : depositData.getPanCard();
			String pincode = depositData.getPinCode() == null ? "NA" : depositData.getPinCode();
			String nomineeName = depositData.getNomineeName() == null ? "NA" : depositData.getNomineeName();
			String nomineeDateOfBirth = depositData.getNomineeDateOfBirth() == null ? "NA" : depositData.getNomineeDateOfBirth();
			String nomineeRelation = depositData.getRelationshipWithNominee() == null ? "NA" : depositData.getRelationshipWithNominee();
			String numberOfInstallment = depositData.getNumberOfDeposit() == null ? "NA" : depositData.getNumberOfDeposit();
			String investmentType = depositData.getInvestmentType() == null ? "NA" : depositData.getInvestmentType();
			String partnerCode = "NA".equalsIgnoreCase(depositData.getPartnerCode())  ? "76783" : depositData.getPartnerCode();
			String formAppNumber = depositData.getFormAppNumber() == null ? "NA" : depositData.getFormAppNumber();
			String address = depositData.getAddress() == null ? "" : depositData.getAddress();			
			String sfdcApiRecordId = depositData.getSfdcRecordId() == null ? "" : depositData.getSfdcRecordId();
			Integer totalFDAmount = Integer.parseInt(fDAmount) * Integer.parseInt(numberOfInstallment);
			//String renewOption = depositData.getFdRenew() == null ? "NO" : depositData.getFdRenew();
			String fdrdplan1 = depositData.getRdpLan() == null ? "" : depositData.getRdpLan();
			String productOfferingSrc = depositData.getRdpLan() == null ? "Website Complete" : "RDPL Complete";
			String propertyFileSourcingChannel=Utility.getPropertyFileValue("SOURCE");
			String sourcingChannel = depositData.getRdpLan() == null ? propertyFileSourcingChannel : "RDPL";
			String loanType = depositData.getRdpLan() == null ? "" : "RDPL Loan";
			String DateOfEachDeposit = depositData.getDateOfEachDeposit() == null ? "" : depositData.getDateOfEachDeposit();
			String gender = depositData.getGender() == null? "M" : depositData.getGender();
			String lastClick = depositData.getLastClick() == null? "" : depositData.getLastClick();
			lastClick = lastClick.length()>=50 ?lastClick.replace(" ", "").substring(0, 50):lastClick;
			pageUrl = pageUrl.length()>=150 ? pageUrl.replace(" ", "").substring(0, 150) : pageUrl;
			String requeryFlag = depositData.getRequerystatus() == null ? "Fail" : depositData.getRequerystatus();
			//String okycStatus=depositData.getOkycReturnValue()==null ?"":depositData.getOkycReturnValue();
			String remarkCustType = depositData.getRemarkCustType() == null ? "" : depositData.getRemarkCustType();
			String gclld = depositData.getGclid()==null? "" : depositData.getGclid();
			String cretedOn = depositData.getCreatedOn() == null ? "" : depositData.getCreatedOn();
			String paymentReqTime = depositData.getPayementRequestTime()== null ? "" : depositData.getPayementRequestTime();
			String nomineeAddressCheck=depositData.getNomineeAddressCheck()==null?"":depositData.getNomineeAddressCheck();
			String nomineeAddress=depositData.getNomineeAddress()==null?"":depositData.getNomineeAddress();			
			String nomineeCity=depositData.getNomineeCity()==null?"":depositData.getNomineeCity();
			String nomineeState=depositData.getNomineeState()==null?"":depositData.getNomineeState();
			String nomineePincode=depositData.getNomineePincode()==null?"":depositData.getNomineePincode();
			String paymentMode=depositData.getPaymentChoice()==null?"":depositData.getPaymentChoice();
			String maturityScheme= depositData.getMaturityScheme()==null?"" : depositData.getMaturityScheme();

			String commCheckbox=depositData.getCommCheckbox()==null?"":depositData.getCommCheckbox();
			String commAddress=depositData.getCommAddress()==null?"":depositData.getCommAddress();			
			String commPincode=depositData.getCommPincode()==null?"":depositData.getCommPincode();
			String utmSource = depositData.getUtmSource() == null ? "NA" : depositData.getUtmSource();

			String nomineePresent = depositData.getNomineeCheck() == null ? "NA": depositData.getNomineeCheck();
			String nsdlApiResponse=depositData.getNsdlApiResponse()==null?"NA":depositData.getNsdlApiResponse();

			String nomineeTitle = depositData.getNomineeSalutation() == null? "NA": depositData.getNomineeSalutation();
			String mainSalution = depositData.getSalutation() ==  null? "NA":depositData.getSalutation();

			String guardianPresent=depositData.getGaurdianCheck()== null?"NO":depositData.getGaurdianCheck();
			String gurdianAge=depositData.getNomineeGuardianAge()==null?"":depositData.getNomineeGuardianAge();
			String guardianAddress=depositData.getNomineeGuardianAddress()==null?"":depositData.getNomineeGuardianAddress();			
			String guardianCity=depositData.getNomineeGuardianCity()==null?"":depositData.getNomineeGuardianCity();
			String guardianPincode=depositData.getNomineeGuardianPincode()==null?"":depositData.getNomineeGuardianPincode();
			String guardianState=depositData.getNomineeGuardianState()==null?"":depositData.getNomineeGuardianState();
			String guardianName=depositData.getNomineeGuardianName()==null?"":depositData.getNomineeGuardianName();

			String relativeOrDirector=depositData.getRelativeOfDirector()== null?"":depositData.getRelativeOfDirector();
			String directorOrPromotor=depositData.getDirectorOrPromoter()==null?"":depositData.getDirectorOrPromoter();
			String shareHolder=depositData.getShareholder()==null?"":depositData.getShareholder();
			String ipAddress=depositData.getIpAdress()==null?"NA":depositData.getIpAdress();
			String paymentRequestTime = depositData.getPayementRequestTime()  == null? "NA" :depositData.getPayementRequestTime();
			String commAddressPresent=depositData.getCommCheckbox()==null?"":depositData.getCommCheckbox();
			
			String aadharNumber=depositData.getAadharNumber()==null?"NA" : depositData.getAadharNumber();

			String ipv4 = "(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])";
			String ipv6 ="((([0-9a-fA-F]){1,4})\\:){7}([0-9a-fA-F]){1,4}";
		    if(!ipAddress.matches(ipv4) && !ipAddress.matches(ipv6))
		    {
		    	ipAddress = "";
		    }
		    logger.info("::::::::::::ipAddress FD after regex::::::::::"+ipAddress);
		    String panEdited = depositData.getPanedited()==null?"":depositData.getPanedited();
		    String paymentSuccessTime = depositData.getPaymentSuccessTime() == null ? "NA" : depositData.getPaymentSuccessTime();
		    logger.info("::::::::::::paymentSuccessTime SDP::::::::::"+paymentSuccessTime);
		    
		    
			String createdon = cretedOn;
			Date date2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(createdon);
			SimpleDateFormat formatter1DDob1 = new SimpleDateFormat("dd/MM/yyyy;HH:mm:ss");     
			String currentDateSdp = formatter1DDob1.format(date2);

			String category="";
			if("yes".equalsIgnoreCase(relativeOrDirector))
				category = "Relative of Director";
			if("yes".equalsIgnoreCase(directorOrPromotor))
				category = (category==""? "Director or Promoter of BFL" : category+ "," +"Director or Promoter of BFL");
			if("yes".equalsIgnoreCase(shareHolder))
				category = (category==""? "Shareholder" : category+ "," +"Shareholder");



			if("Mr.".equalsIgnoreCase(mainSalution)){
				gender="MALE";
			}else if("NA".equalsIgnoreCase(mainSalution)){
				gender="Other";
				mainSalution="Mr.";
			}
			else{
				gender="FEMALE";
			}

			String nomineeGender= "";

			if("Mr.".equalsIgnoreCase(nomineeTitle)){
				nomineeGender="MALE";
			}else if("NA".equalsIgnoreCase(nomineeTitle)){
				nomineeGender="Other";
				nomineeTitle="Mr.";
			}
			else{
				nomineeGender="FEMALE";
			}


			String commAdd;
			String commPin;
			String PermAndResddressAreSameheck;
			if("Yes".equalsIgnoreCase(commCheckbox)){

				commAdd=address;
				commPin= pincode;
				PermAndResddressAreSameheck="Yes";
			}else{
				commAdd=commAddress;
				commPin= commPincode;
				PermAndResddressAreSameheck="No";
			}

			logger.info("====Nominee Adress presendt======"+nomineeAddressCheck+"---Payment Mode---"+paymentMode+" --fdrPhysicalyRequired------" + "----utmSource--"+utmSource);
			if("".equalsIgnoreCase(cretedOn)){
				cretedOn = new SimpleDateFormat("yyyyMMdd").format(new Date());
			}else{
				cretedOn = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(cretedOn));
			}


			if (("NTBDocumentUpload".equalsIgnoreCase(remarkCustType)))
			{
				remarkCustType="Doc_Upload";

			}

			if("SUCCESS".equalsIgnoreCase(requeryFlag)){

				sfdcApiRecordId = "";
			}

			if("F".equalsIgnoreCase(gender)){
				gender = "FEMALE";
			}else{
				gender = "MALE";
			}

			if("NA".equalsIgnoreCase(nomineeRelation)){
				nomineeRelation = "Others";
			}

			if(!("NA".equalsIgnoreCase(dateOfBirth))){
				dateOfBirth = Utility.dateFormerter(dateOfBirth);
			}
			logger.info("====sfdc SDP B Date======"+dateOfBirth);


			if("NA".equalsIgnoreCase(nomineeRelation)){
				nomineeRelation = "Others";
			}

			if("NA".equalsIgnoreCase(nomineeDateOfBirth)){
				nomineeDateOfBirth = "01/01/1900";
			}else{
				nomineeDateOfBirth = Utility.dateFormerter(nomineeDateOfBirth);	
			}




			String bankName = depositData.getBankName() == null ? "NA" : depositData.getBankName();

			if("UPI".equalsIgnoreCase(paymentMode)){

				paymentMode= "UPI CC Avenue";
			}else{
				paymentMode = "Netbanking CC Avenue";
			}
			logger.info("==========paymentMode=========="+paymentMode);
			
			String formSumissionDate =new SimpleDateFormat("MM/dd/yyyy").format(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(paymentSuccessTime));
			logger.info(":::::::::formSumissionDate FD:::::::::"+formSumissionDate);

			String isRDP="";
			if("Single Maturity".equalsIgnoreCase(maturityScheme)){
				isRDP="Y";
			}else{
				isRDP="";
			}


			logger.info(" == investmentType === " + investmentType + " == customerSubtype === "+ customerSubtype + " === depositOption === "+ depositOption + " === interestPayoutFreq === "+ interestPayoutFreq);


			String locationType ="NA";

			if(!("NA".equalsIgnoreCase(pincode))){
				int reqPin = Integer.parseInt(pincode);
				FDPincodeMaster fdPiMast = fixedDepositDao.getcitystatename(reqPin);
				if(fdPiMast != null){
					locationType = fdPiMast.getLocationType() == null ? "Urban": fdPiMast.getLocationType();
					state = fdPiMast.getState() == null ? "NA" : fdPiMast.getState();
				}else{
					locationType = "Urban";
				}
			}

			String branchcode= "";
			if("Rural".equalsIgnoreCase(locationType)){
				branchcode = "521";
			}else{
				branchcode = "320";
			}
			investmentType="Single Maturity".equalsIgnoreCase(maturityScheme)?"SDPSingle":"SDPMonthly";
			schemeCode = fixedDepositDao.getSchemeCode(investmentType, customerSubtype, depositOption, interestPayoutFreq, locationType);

			logger.info(" === schemeCode in ekycFdSfdcService ==== " + schemeCode);



			JSONObject fullNameObje = Utility.fullNameManipulation(fullName);
			String fName =fullNameObje.has("fName")?  fullNameObje.get("fName").toString() : "NA";
			String lName =fullNameObje.has("lName")?  fullNameObje.get("lName").toString() : ".";
			String mName =fullNameObje.has("mName")?  fullNameObje.get("mName").toString() : "";





			String nomineeFname = "";
			String nomineeLname = "";

			if(!("NA".equalsIgnoreCase(nomineeName)))
			{ 
				nomineeFname=nomineeName.contains(" ")?nomineeName.substring(0,nomineeName.indexOf(' ')).trim():nomineeName;
				nomineeLname=nomineeName.contains(" ")?nomineeName.substring(nomineeName.indexOf(' ')+1).trim():".";

			}else{
				nomineeFname ="NA";
				nomineeLname ="NA";
			}




			logger.info(" == fName == " + fName + " === lName === " + lName);



			if("".equalsIgnoreCase(lName) || lName.isEmpty()){
				lName= ".";
			}

			if("NA".equalsIgnoreCase(interestPayoutFreq)){
				interestPayoutFreq = "Annual";
			}

			logger.info(" === partnerCode === " + partnerCode);

			String broker ="";
			String brokerCode = "";
			String ckkycDocument ="";
			String ckycDocumentNameFile ="";
			String ckycDocType="";
			String panckkycDocument ="";
			String panckycDocumentNameFile ="";
			String panckycDocType="";
			String ckycNo="";
			String AddrProof="";
			String identityDocType="";

			if(!(partnerCode.isEmpty())){
				broker = "Broker";
				brokerCode = partnerCode;
			}

			logger.info(" === broker in  ekycFdSfdcService ==== " + broker);
			customerId = depositData.getCustomerId();


			String cKStatus = depositData.getCkycStatus() == null ? "NA" : depositData.getCkycStatus();

			if ("YES".equalsIgnoreCase(cKStatus)) {
				ckycNo = existingCustId;
				existingCustId= "";


				String filePath = Utility.getPropertyFileValue("ckycBase64filepath").trim();
				String filePathCreated = filePath + cretedOn + "/Request_"+customerId+ ".txt";

				String filePath1=depositData.getServrFilePath()== null?"NA":depositData.getServrFilePath();
				logger.info(" ===filePath1  ekycFdSfdcService ==== " + filePath1);
				filePath1="NA".equalsIgnoreCase(filePath1)?filePathCreated:filePath1;
				logger.info(" ===filePath1  ekycFdSfdcService updated if not present ==== " + filePath1);

				String DoucmnetData = custIdIntegration.readFile(filePath1);
				if (!DoucmnetData.isEmpty()) {

					JSONObject docJsonData = new org.json.JSONObject(DoucmnetData);
					ckkycDocument = docJsonData.has("CKYCImageData") ? docJsonData.get("CKYCImageData").toString()	: "NA";
					String ckycDocumentType = docJsonData.has("CKYCImageExtension")	? docJsonData.get("CKYCImageExtension").toString() : "NA";
					String ckycDocumentName = docJsonData.has("CKYCImageType") ? docJsonData.get("CKYCImageType").toString()	: "NA";
					logger.info("---------Document from file--------------" + ckycDocumentName + " --" + ckycDocumentType);
					ckycDocumentNameFile = ckycDocumentName + "." + ckycDocumentType;

					if("AadharCard".equalsIgnoreCase(ckycDocumentName)){
						ckycDocType="OVD-Applicant-Aadhar Card";
						AddrProof=ckycDocType;
						identityDocType="UID/Aadhaar";
					}else if("Passport".equalsIgnoreCase(ckycDocumentName)){
						ckycDocType="OVD-Applicant-Passport";
						AddrProof=ckycDocType;
						identityDocType="Passport";
					}else if("DrivingLicence".equalsIgnoreCase(ckycDocumentName)){
						ckycDocType="OVD-Applicant-Driving licence";
						AddrProof=ckycDocType;
						identityDocType="Driving Licence";
					}else if("VoterID".equalsIgnoreCase(ckycDocumentName)){
						ckycDocType="OVD-Applicant-Voter Identity card";
						AddrProof=ckycDocType;
						identityDocType="Voter ID";
					}else if("NREGA".equalsIgnoreCase(ckycDocumentName)){
						ckycDocType="OVD-Applicant-NREGA Job Card";
						AddrProof=ckycDocType;
						identityDocType="PAN";
					}else if("AadhaarOffline".equalsIgnoreCase(ckycDocumentName)){
						ckycDocType="OVD-Applicant-Aadhar Card";
						AddrProof="DOVD-Applicant-Others";
						identityDocType="PAN";
					}else if("NPR".equalsIgnoreCase(ckycDocumentName)){
						ckycDocType="OVD-Applicant-National population register letter";
						AddrProof=ckycDocType;
						identityDocType="PAN";
					}else if("Photograph".equalsIgnoreCase(ckycDocumentName)){
						ckycDocType="Photo-Applicant";
						AddrProof="DOVD- Applicant-Others";
						identityDocType="PAN";
					}else{
						ckycDocType="";
						AddrProof="DOVD- Applicant-Others";
						identityDocType="PAN";
					}

					if (docJsonData.has("panData")) {

						JSONObject panDatadocJsonData = docJsonData.getJSONObject("panData");
						panckkycDocument = panDatadocJsonData.has("CKYCImageData")	? panDatadocJsonData.get("CKYCImageData").toString(): "NA";
						String panckycDocumentType = panDatadocJsonData.has("CKYCImageExtension")	? panDatadocJsonData.get("CKYCImageExtension").toString() : "NA";
						String panckycDocumentName = panDatadocJsonData.has("CKYCImageType")	? panDatadocJsonData.get("CKYCImageType").toString() : "NA";
						logger.info("---------Document from file--------------" + ckycDocumentName + " --"+ ckycDocumentType);
						panckycDocumentNameFile = panckycDocumentName + "." + panckycDocumentType;

						if("PAN".equalsIgnoreCase(panckycDocumentName) || "PANCard".equalsIgnoreCase(panckycDocumentName)){
							panckycDocType="PAN_Card";
						}else{
							panckycDocType="";
						}

					}

				}
			}
			logger.info("-------remark Cust Type-------------" + remarkCustType);
			if (!(("DEDUPE".equalsIgnoreCase(remarkCustType)) || ("SSODEDUPE".equalsIgnoreCase(remarkCustType)))) {
				existingCustId = "";

			}

			String okycRequestId ="";
			String authCode = "";
			String aadharRefId= "";
			String okycMobile="";
			String okycEmail = "";
			String okycAPIStatus ="";
			String okycIpAddr= "";
			String okycCompletionDT="";
			String initiationDT="";

			if ("OKYC".equalsIgnoreCase(remarkCustType))
			{

				okycRequestId = customerId;
				authCode = depositData.getOkycAPIRequestID();
				aadharRefId= depositData.getOkycAadhaarreferenceId();
				okycMobile=phoneNo;
				okycEmail = email;
				okycAPIStatus ="Completed";
				okycIpAddr= depositData.getOkycIPAddress();
				okycCompletionDT= depositData.getOkycUpdateRecordDT();
				initiationDT= depositData.getOkycInitiationDT();


				String filePath = Utility.getPropertyFileValue("ckycBase64filepath").trim();
				String filePathCreated = filePath + cretedOn + "/Request_"+customerId+ ".txt";
				String filePath1=depositData.getServrFilePath()== null?"NA":depositData.getServrFilePath();
				logger.info(" ===filePath1  ekycFdSfdcService ==== " + filePath1);
				filePath1="NA".equalsIgnoreCase(filePath1)?filePathCreated:filePath1;
				logger.info(" ===filePath1  ekycFdSfdcService updated if not present ==== " + filePath1);

				String DoucmnetData = custIdIntegration.readFile(filePath1);
				if (!DoucmnetData.isEmpty()) {

					JSONObject docJsonData = new org.json.JSONObject(DoucmnetData);
					ckkycDocument = docJsonData.has("CKYCImageData") ? docJsonData.get("CKYCImageData").toString()	: "NA";
					String ckycDocumentType = docJsonData.has("CKYCImageExtension")	? docJsonData.get("CKYCImageExtension").toString() : "NA";
					String ckycDocumentName = docJsonData.has("CKYCImageType") ? docJsonData.get("CKYCImageType").toString()	: "NA";
					logger.info("---------Document from file--------------" + ckycDocumentName + " --" + ckycDocumentType);
					ckycDocumentNameFile = ckycDocumentName + "." + ckycDocumentType;

					if (docJsonData.has("ockycDocPhoto")) {

						panckkycDocument = docJsonData.has("ockycDocPhoto")	? docJsonData.get("ockycDocPhoto").toString(): "NA";
						panckycDocumentNameFile = "ockycDocPhoto.jpeg";
						
						if("ockycDocPhoto.jpeg".equalsIgnoreCase(panckycDocumentNameFile)){
							panckycDocType="Photograph_OKYC";
							logger.info("---------panckycDocType from file--------------"+panckycDocType);
						}else{
							panckycDocType="";
							logger.info("---------panckycDocType from file--------------"+panckycDocType);

						}
						
					}
				}

				/************************
				 * okyc Pdf Stamping Call
				 ************/
				JSONObject okcyPdf = dataDownloadService.okycPdfManipulation(customerId);

				ckkycDocument = okcyPdf.has("fileEncode") ? okcyPdf.get("fileEncode").toString(): "";


				if (ckkycDocument.isEmpty()) {
					ckycDocumentNameFile = "";
					ckycDocType = "";
				} else {
					ckycDocumentNameFile="OVD-Applicant-Aadhar Card.pdf";
					ckycDocType="OVD-Applicant-Aadhar Card";
				}
			}

			if ("3rd".equalsIgnoreCase(DateOfEachDeposit)){
				DateOfEachDeposit = "3" ;
			}
			if ("7th".equalsIgnoreCase(DateOfEachDeposit)){
				DateOfEachDeposit = "7" ;
			}
			if ("12th".equalsIgnoreCase(DateOfEachDeposit)){
				DateOfEachDeposit = "12" ;
			}

			/*	String fileEncode = sfdcPdf.has("fileEncode") ? sfdcPdf.get("fileEncode").toString() : "";
			String fileName = sfdcPdf.has("fileName") ? sfdcPdf.get("fileName").toString() : "";

			if(fileEncode.isEmpty()){
				fileName = "";
			}*/
			
			String commAddfileEncode="";
			String commAddfileName ="";
			String commAdddocType ="";
			if("No".equalsIgnoreCase(commAddressPresent))
			{
				String propetyFilepathForcommdocumentUploadPath =Utility.getPropertyFileValue("communicationUploadPath").trim();
				String filledPathcommDocument = propetyFilepathForcommdocumentUploadPath+cretedOn+"/"+customerId+"/";
				logger.info(" === Commmunication Document  path ==== " + filledPathcommDocument);

				String documentName=depositData.getCommAddDocName()==null?"NA":depositData.getCommAddDocName(); 

				if("Aadhaar Card".equalsIgnoreCase(documentName)){
					commAdddocType="OVD-Applicant-Aadhar Card";
				}else if("Valid Passport".equalsIgnoreCase(documentName)){
					commAdddocType="OVD-Applicant-Passport";
				}else if("Valid Driving Licence".equalsIgnoreCase(documentName)){
					commAdddocType="OVD-Applicant-Driving licence";
				}else if("Voter ID Card".equalsIgnoreCase(documentName)){
					commAdddocType="OVD-Applicant-Voter Identity card";
				}else if("NREGA Job Card".equalsIgnoreCase(documentName)){
					commAdddocType="OVD-Applicant-NREGA Job Card";
				}else if("Letter issued by National Population Register".equalsIgnoreCase(documentName)){
					commAdddocType="OVD-Applicant-National population register letter";
				}else{
					commAdddocType="DOVD- Applicant-Others";
				}


				File filePoi = new File(filledPathcommDocument);
				for (File subFile : filePoi.listFiles()) 
				{
					String FilePath = subFile.getAbsolutePath();
					logger.info(" === FilePath To convert Base 64 == " + subFile.getName());
					JSONObject sfdcPdf = dataDownloadService.sfdcDocumentConvertBase64Service(FilePath);

					commAddfileEncode = sfdcPdf.has("fileEncode") ? sfdcPdf.get("fileEncode").toString() : "";
					commAddfileName = sfdcPdf.has("fileName") ? sfdcPdf.get("fileName").toString() : "";
					// commAdddocType = commAddfileName.substring(0, commAddfileName.lastIndexOf('.')).replace('.', ' ').replaceAll("( +)", " ").trim();
				}
			}
			
			
			
			/************************Audit Trail Pdf Stamping Call************/
			JSONObject auditTrailPdf = dataDownloadService.fdAuditTrailPdf(customerId);

			String auditTrailfileEncode = auditTrailPdf.has("fileEncode") ? auditTrailPdf.get("fileEncode").toString() : "";
			String auditTrailfileName = auditTrailPdf.has("fileName") ? auditTrailPdf.get("fileName").toString() : "";
			String auditTrailfileNameDocType = "";
			if(auditTrailfileEncode.isEmpty()){
				auditTrailfileName = "";
				auditTrailfileNameDocType="";
			}else{
				auditTrailfileNameDocType = auditTrailfileName.substring(0, auditTrailfileName.lastIndexOf('.')).replace('.', ' ').replaceAll("( +)"," ").trim();
			}


			JSONObject addrManipulat = Utility.addresManipulation(formFieldValidation.SFDCAddressValidate(address));
			JSONObject commAddrManipulat = Utility.addresManipulation(formFieldValidation.SFDCAddressValidate(commAdd));

			String addLine1 = addrManipulat.has("add1") ? addrManipulat.get("add1").toString() : ".";
			String addLine2 = addrManipulat.has("add2") ? addrManipulat.get("add2").toString() : ".";
			String addLine3 = addrManipulat.has("add3") ? addrManipulat.get("add3").toString() : ".";
			String addLine4 = addrManipulat.has("add4") ? addrManipulat.get("add4").toString() : ".";

			String comaddLine1 = commAddrManipulat.has("add1") ? commAddrManipulat.get("add1").toString() : ".";
			String comaddLine2 = commAddrManipulat.has("add2") ? commAddrManipulat.get("add2").toString() : ".";
			String comaddLine3 = commAddrManipulat.has("add3") ? commAddrManipulat.get("add3").toString() : ".";
			String comaddLine4 = commAddrManipulat.has("add4") ? commAddrManipulat.get("add4").toString() : ".";

			String nomaddLine1 = ".";
			String nomaddLine2 = ".";
			String nomaddLine3 = ".";
			String nomaddLine4 = ".";

			if ("YES".equalsIgnoreCase(nomineeAddressCheck)) {
				JSONObject nominAddrManipulat = Utility.addresManipulation(formFieldValidation.SFDCAddressValidate(nomineeAddress));
				nomaddLine1 = nominAddrManipulat.has("add1") ? nominAddrManipulat.get("add1").toString() : ".";
				nomaddLine2 = nominAddrManipulat.has("add2") ? nominAddrManipulat.get("add2").toString() : ".";
				nomaddLine3 = nominAddrManipulat.has("add3") ? nominAddrManipulat.get("add3").toString() : ".";
				nomaddLine4 = nominAddrManipulat.has("add4") ? nominAddrManipulat.get("add4").toString() : ".";

			} else {
				nomaddLine1 = addLine1;
				nomaddLine2 = addLine2;
				nomaddLine3 = addLine3;
				nomaddLine4=addLine4;
			}

			String guaAddLine1 =".";
			String guaAddLine2 =".";
			String guaAddLine3 =".";

			if ("YES".equalsIgnoreCase(guardianPresent)) {
				JSONObject gaurdianAddress = Utility.addresLineThreeManipulation(formFieldValidation.SFDCAddressValidate(guardianAddress));
				guaAddLine1 = gaurdianAddress.has("add1") ? gaurdianAddress.get("add1").toString() : ".";
				guaAddLine2 = gaurdianAddress.has("add2") ? gaurdianAddress.get("add2").toString() : ".";
				guaAddLine3 = gaurdianAddress.has("add3") ? gaurdianAddress.get("add3").toString() : ".";
			}


			String paymentprocess =  Utility.getPropertyFileValue("paymentprocess").trim();

			if("Billdesk".equalsIgnoreCase(paymentprocess)){
				BankNameDetails bankNameDetails = fixedDepositDao.getBankDetailsforPayment(bankName);
				String bankStatus = bankNameDetails.getBanktype()== null ? "CCAvenue" : bankNameDetails.getBanktype();

				if("Both Bank".equalsIgnoreCase(bankStatus)){

					if ("UPI".equalsIgnoreCase(paymentMode)) {
						paymentMode = "UPI";
					}else{
						paymentMode = "Netbanking";
					}

				}else{
					paymentprocess="CCAvenue";
				}

			}


			String impsValidationTimestamp="";
			String impsResponseDesc="";
			String impsResponseCode="";
			String impsBeneficiaryName="";
			String impsBankName="";
			String impstraceName="";
			try
			{
				IMPSFixedDepositData impsFixedDepositData=fixedDepositDao.getImpsDepositData(customerId);
				if(impsFixedDepositData != null){
					impsValidationTimestamp=impsFixedDepositData.getCreatedOn()==null?"NA":impsFixedDepositData.getCreatedOn();
					impsResponseDesc=impsFixedDepositData.getiMPSrespDesc()==null?"NA":impsFixedDepositData.getiMPSrespDesc();
					impsResponseCode=impsFixedDepositData.getImpsResponseCode()==null?"NA":impsFixedDepositData.getImpsResponseCode();
					impsBeneficiaryName=impsFixedDepositData.getiMPSbeneName()==null?"NA":impsFixedDepositData.getiMPSbeneName();
					impsBankName=impsFixedDepositData.getiMPStrnBankName()==null?"NA":impsFixedDepositData.getiMPStrnBankName();
					impstraceName=impsFixedDepositData.getiMPStraceNumber()==null?"NA":impsFixedDepositData.getiMPStraceNumber();
				}
			}catch(Exception e)
			{
				logger.error(" === Exception in getImpsDepositData === ", e);	
			}


			Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2030");
			SimpleDateFormat formatter1DDob = new SimpleDateFormat("MM/dd/yyyy");
			String endDate = formatter1DDob.format(date1);
			Date requestTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(paymentRequestTime);
			Calendar c = Calendar.getInstance();
			c.setTime(requestTime); 
			c.add(Calendar.DATE, 2);
			String mandateStartDate = formatter1DDob.format(c.getTime());
			logger.info(" ======= Mandate SDP ======= "+endDate +" "+ mandateStartDate+" "+fDAmount);


			ObjFDDetails objFDDetails = new ObjFDDetails();
			objFDDetails.setAppFormNo(formAppNumber);
			objFDDetails.setDepositCategory("FD");
			objFDDetails.setCustomerType("Individual");
			objFDDetails.setCustomerSubtype(customerSubtype);
			objFDDetails.setSourceBy(broker);
			objFDDetails.setChannel("Website Direct");
			objFDDetails.setSourcingChannel(sourcingChannel);
			objFDDetails.setDepositPayableTo("First Holder");
			objFDDetails.setBranch(branchcode);
			objFDDetails.setBroker(brokerCode);
			objFDDetails.setExistingCustID(existingCustId);
			objFDDetails.setDepositOption(depositOption);
			objFDDetails.setInterestPayoutFreq(interestPayoutFreq);
			objFDDetails.setScheme(schemeCode);
			objFDDetails.setTotalFDAmount(String.valueOf(totalFDAmount));
			objFDDetails.setFDAmount(fDAmount);
			objFDDetails.setTenorDays("0");
			objFDDetails.setTenorMonth(tenorMonth);
			objFDDetails.setInterest(interest);
			objFDDetails.setIsComplete("Y");
			//objFDDetails.setRenewOption(renewOption);
			objFDDetails.setUTMMEDIUM(utmMedium);
			objFDDetails.setUTMCAMAPAIGN(utmCampaign);
			objFDDetails.setUTMKEYWORD(utmKeyword);
			objFDDetails.setUTMcontent(pincode);
			objFDDetails.setPAGEURL(pageUrl);
			objFDDetails.setDEVICE(device);
			objFDDetails.setLASTCLICK(lastClick);
			objFDDetails.setCLIENTID(clientId);
			objFDDetails.setProductOfferingSrc(productOfferingSrc);

			objFDDetails.setPhoneNo(phoneNo);
			objFDDetails.setFullName(fullName);
			objFDDetails.setEmail(email);
			objFDDetails.setCity(city);
			objFDDetails.setIsSDP("Y");
			objFDDetails.setNoOfInstallment(numberOfInstallment);
			objFDDetails.setRdplLan(fdrdplan1);
			objFDDetails.setLoanType(loanType);
			objFDDetails.setInstallmentDueDay(DateOfEachDeposit);
			objFDDetails.setGclld(gclld);
			objFDDetails.setFinnoneCustomerID("");
			objFDDetails.setRemarks(remarkCustType);
			objFDDetails.setIsRDP(isRDP);
			objFDDetails.setUTMSOURCE(utmSource);
			objFDDetails.setAppGeneration("Y");


			ObjInvestmentAccDetails objInvestmentAccDetails = new ObjInvestmentAccDetails();
			objInvestmentAccDetails.setPaymentMode(paymentMode);
			objInvestmentAccDetails.setAccHolderName(fullName);
			objInvestmentAccDetails.setAccountNo(accountNumber);
			objInvestmentAccDetails.setBank(ifscCode); 
			objInvestmentAccDetails.setChequeDate(formSumissionDate);
			objInvestmentAccDetails.setInvAndMatAccBelongToYou("Yes");
			objInvestmentAccDetails.setUTR(utr);
			objInvestmentAccDetails.setBankAccType("Savings");
			objInvestmentAccDetails.setMaturityInvestmentAccountAreSame("Yes");
			objInvestmentAccDetails.setTDSWaiver("N");
			objInvestmentAccDetails.setECSStartDate(mandateStartDate);
			objInvestmentAccDetails.setECSEndDate(endDate);
			objInvestmentAccDetails.setECSLimit(fDAmount);


			if("Billdesk".equalsIgnoreCase(paymentprocess)){
				objInvestmentAccDetails.setDepositBankNm("Urban HDFC FD Coll Acc - 00070350006738 - Urban");
				objInvestmentAccDetails.setBankGLCode("4117");
			}

			if("CCAvenue".equalsIgnoreCase(paymentprocess)){
				if (!("Netbanking".equalsIgnoreCase(paymentMode))) {
					objInvestmentAccDetails.setDepositBankNm("IndusInd CC Avenue FD Coll 201006586448");
					objInvestmentAccDetails.setBankGLCode("80018");
				}
			}
			if("PayU".equalsIgnoreCase(paymentprocess)){
					objInvestmentAccDetails.setDepositBankNm("IndusInd CC Avenue FD Coll 201006586448");
					objInvestmentAccDetails.setBankGLCode("80018");
			}

			ObjMaturityAccDetails objMaturityAccDetails = new ObjMaturityAccDetails();
			objMaturityAccDetails.setAccountNo(accountNumber);
			objMaturityAccDetails.setBank(ifscCode);
			objMaturityAccDetails.setAccHolderName(fullName);

			objMaturityAccDetails.setImpsValidationTimeStamp(impsValidationTimestamp);
			objMaturityAccDetails.setImpsResponseDescription(impsResponseDesc);
			objMaturityAccDetails.setImpsResponseCode(impsResponseCode);
			objMaturityAccDetails.setImpsBeneficiaryName(impsBeneficiaryName);
			objMaturityAccDetails.setImpsBankName(impsBankName);
			objMaturityAccDetails.setImpsTraceNo(impstraceName);


			/*ObjKycDoc objKycDoc0 = new ObjKycDoc();
			objKycDoc0.setDocName(fileName);
			objKycDoc0.setDocContent(fileEncode);*/

			ObjKycDoc objKycDoc1 = new ObjKycDoc();
			objKycDoc1.setDocName(ckycDocumentNameFile);
			objKycDoc1.setDocContent(ckkycDocument);
			objKycDoc1.setDocType(ckycDocType);

			ObjKycDoc objKycDoc2 = new ObjKycDoc();
			objKycDoc2.setDocName(panckycDocumentNameFile);
			objKycDoc2.setDocContent(panckkycDocument);
			objKycDoc2.setDocType(panckycDocType);

			ObjKycDoc objKycDoc3 = new ObjKycDoc();
			objKycDoc3.setDocName(auditTrailfileName);
			objKycDoc3.setDocContent(auditTrailfileEncode);
			objKycDoc3.setDocType(auditTrailfileNameDocType);
			
			ObjKycDoc objKycDoc4 = new ObjKycDoc();
			if("No".equalsIgnoreCase(commAddressPresent)){
				objKycDoc4.setDocName(commAddfileName);
				objKycDoc4.setDocContent(commAddfileEncode);
				objKycDoc4.setDocType(commAdddocType);
			}

			ObjApplDetail objApplDetail = new ObjApplDetail();
			objApplDetail.setCustID(existingCustId);
			objApplDetail.setApplicantType("Primary Applicant");
			objApplDetail.setFName(fName);
			objApplDetail.setLName(lName);
			objApplDetail.setMName(mName);
			objApplDetail.setDateBirthValue(dateOfBirth);
			objApplDetail.setMobile(phoneNo);
			objApplDetail.setEmail(email);
			objApplDetail.setPAN(pan);
			objApplDetail.setForm6061("");
			objApplDetail.setOccupationEmplType("Private Sector");
			objApplDetail.setIdentityDocType("CKYC".equalsIgnoreCase(remarkCustType)?identityDocType:"PAN");
			objApplDetail.setCKYCNo(ckycNo);
			objApplDetail.setMaritalStatus("Single"); 
			objApplDetail.setFatherSpouse("Father"); 
			objApplDetail.setFatherSpouseSalutation("Mr.");
			objApplDetail.setFatherSpouseFName("NA"); 
			objApplDetail.setFatherSpouseMName("NA"); 
			objApplDetail.setFatherSpouseLName("NA"); 
			objApplDetail.setMotherFName("NA");
			objApplDetail.setMotherMName("NA");
			objApplDetail.setMotherLName("NA");
			objApplDetail.setAnnualIncome("Upto 15Lacs");
			objApplDetail.setLandline("0");
			objApplDetail.setCustTdsType("INDIVIDUAL");
			objApplDetail.setResidentType("Indian");
			objApplDetail.setIdentityDocNo("OKYC".equalsIgnoreCase(remarkCustType) ? aadharNumber: pan);

			objApplDetail.setSalutation(mainSalution);
			objApplDetail.setGender(gender);

			objApplDetail.setOkycRequestId(okycRequestId);
			objApplDetail.setAuthCode(authCode);
			objApplDetail.setAadharRefId(aadharRefId);
			objApplDetail.setOkycMobile(okycMobile);
			objApplDetail.setOkycEmail(okycEmail);
			objApplDetail.setOkycAPIStatus(okycAPIStatus);
			objApplDetail.setOkycIpAddr(okycIpAddr);
			objApplDetail.setOkycCompletionDT(okycCompletionDT);
			objApplDetail.setInitiationDT(initiationDT);
			objApplDetail.setNsdlResponse(nsdlApiResponse);
			objApplDetail.setDemogUpdate("Yes");
			objApplDetail.setCustomerCategory(category);
			objApplDetail.setAcceptanceTimeStamp(currentDateSdp);
			objApplDetail.setIPAddress(ipAddress);
			if(panEdited.equalsIgnoreCase("etbDedupeUserPanEdited")) {
			objApplDetail.setIspanedited("Y");
			}else if(panEdited.equalsIgnoreCase("etbDedupeUser")) {
			objApplDetail.setIspanedited("N");
			}


			AdressDetailsList permanentAddress = new AdressDetailsList();
			permanentAddress.setAddrType("Permanent");
			permanentAddress.setAddress1(addLine1); 
			permanentAddress.setAddress2(addLine2); 
			permanentAddress.setAddress3(addLine3); 
			permanentAddress.setAddress4(addLine4); 
			permanentAddress.setCity(city); 
			permanentAddress.setState(state); 
			permanentAddress.setPincode(pincode);
			permanentAddress.setCountry("India");
			permanentAddress.setPermAndResddressAreSame(PermAndResddressAreSameheck);
			permanentAddress.setPrefferedCommAddress("No");
			if("CKYC".equalsIgnoreCase(remarkCustType))
			{
				permanentAddress.setAddrProof(AddrProof);
			}
			else if("OKYC".equalsIgnoreCase(remarkCustType))
			{
				permanentAddress.setAddrProof("OVD-Applicant-Aadhar Card");
			}else
			{
				permanentAddress.setAddrProof("UID/Aadhaar Card");
			}

			AdressDetailsList residenceAddress = new AdressDetailsList();
			residenceAddress.setAddrType("Residence");
			residenceAddress.setAddress1(comaddLine1); 
			residenceAddress.setAddress2(comaddLine2); 
			residenceAddress.setAddress3(comaddLine3); 
			residenceAddress.setAddress4(comaddLine4); 
			residenceAddress.setCity(city); 
			residenceAddress.setState(state); 
			residenceAddress.setPincode(commPin);
			residenceAddress.setCountry("India");
			residenceAddress.setPermAndResddressAreSame(PermAndResddressAreSameheck);
			residenceAddress.setPrefferedCommAddress("Yes");
			if("CKYC".equalsIgnoreCase(remarkCustType))
			{
				residenceAddress.setAddrProof(AddrProof);
			}
			else if("OKYC".equalsIgnoreCase(remarkCustType))
			{
				residenceAddress.setAddrProof("OVD-Applicant-Aadhar Card");
			}else
			{
				residenceAddress.setAddrProof("UID/Aadhaar Card");
			}

			objApplDetail.setAdressDetailsList(Arrays.asList(permanentAddress,residenceAddress));

			AdressDetailsList nomineePermanentAddress = new AdressDetailsList();
			nomineePermanentAddress.setAddrType("Permanent");
			nomineePermanentAddress.setAddress1(nomaddLine1); 
			nomineePermanentAddress.setAddress2(nomaddLine2); 
			nomineePermanentAddress.setAddress3(nomaddLine3); 
			nomineePermanentAddress.setAddress4(nomaddLine4); 
			nomineePermanentAddress.setCity("YES".equalsIgnoreCase(nomineeAddressCheck)?nomineeCity:city); 
			nomineePermanentAddress.setState("YES".equalsIgnoreCase(nomineeAddressCheck)?nomineeState:state); 
			nomineePermanentAddress.setPincode("YES".equalsIgnoreCase(nomineeAddressCheck)?nomineePincode:pincode);
			nomineePermanentAddress.setCountry("India");
			nomineePermanentAddress.setPermAndResddressAreSame("Yes");
			nomineePermanentAddress.setPrefferedCommAddress("No");
			nomineePermanentAddress.setAddrProof("UID/Aadhaar Card");

			AdressDetailsList nomineeResidenceAddress = new AdressDetailsList();
			nomineeResidenceAddress.setAddrType("Residence");
			nomineeResidenceAddress.setAddress1(nomaddLine1); 
			nomineeResidenceAddress.setAddress2(nomaddLine2); 
			nomineeResidenceAddress.setAddress3(nomaddLine3);
			nomineeResidenceAddress.setAddress4(nomaddLine4); 
			nomineeResidenceAddress.setCity("YES".equalsIgnoreCase(nomineeAddressCheck)?nomineeCity:city); 
			nomineeResidenceAddress.setState("YES".equalsIgnoreCase(nomineeAddressCheck)?nomineeState:state); 
			nomineeResidenceAddress.setPincode("YES".equalsIgnoreCase(nomineeAddressCheck)?nomineePincode:pincode);
			nomineeResidenceAddress.setCountry("India");
			nomineeResidenceAddress.setPermAndResddressAreSame("No");
			nomineeResidenceAddress.setPrefferedCommAddress("Yes");
			nomineeResidenceAddress.setAddrProof("UID/Aadhaar Card");

			ObjApplDetail objApplDetailNominee = new ObjApplDetail();
			objApplDetailNominee.setCustID("");
			objApplDetailNominee.setApplicantType("Nominee");
			objApplDetailNominee.setSalutation("Mr.");
			objApplDetailNominee.setFName(nomineeFname);
			objApplDetailNominee.setMName("");
			objApplDetailNominee.setLName(nomineeLname);
			objApplDetailNominee.setDateBirthValue(nomineeDateOfBirth);
			objApplDetailNominee.setGender("Others");
			objApplDetailNominee.setMobile("");   //nominee mobNo not captured from front-End
			objApplDetailNominee.setEmail("");     //nominee email not captured from front-End
			objApplDetailNominee.setFatherSpouse("Father"); 
			objApplDetailNominee.setFatherSpouseSalutation("Mr.");
			objApplDetailNominee.setFatherSpouseFName("NA"); 
			objApplDetailNominee.setFatherSpouseMName("NA"); 
			objApplDetailNominee.setFatherSpouseLName("NA"); 
			objApplDetailNominee.setMotherFName("NA");
			objApplDetailNominee.setMotherMName("NA");
			objApplDetailNominee.setMotherLName("NA");
			objApplDetailNominee.setAnnualIncome("Upto 15Lacs");
			objApplDetailNominee.setNomineeRelationship(nomineeRelation);
			objApplDetailNominee.setResidentType("Indian");
			objApplDetailNominee.setDemogUpdate("Yes");
			objApplDetailNominee.setGaurdianAge(gurdianAge);   
			objApplDetailNominee.setGuardianAddress1(guaAddLine1);
			objApplDetailNominee.setGuardianAddress2(guaAddLine2);
			objApplDetailNominee.setGuardianAddress3(guaAddLine3);
			objApplDetailNominee.setGuardianCity(guardianCity);
			objApplDetailNominee.setGuardianPincode("YES".equalsIgnoreCase(guardianPresent)?guardianPincode:"");
			objApplDetailNominee.setGuardianState(guardianState);
			objApplDetailNominee.setGuardianName(guardianName);
			objApplDetailNominee.setGuardianSaluttion("YES".equalsIgnoreCase(guardianPresent)?"Mr.":"");
			objApplDetailNominee.setGuardianCountry("YES".equalsIgnoreCase(guardianPresent)?"India":"");
			objApplDetailNominee.setAdressDetailsList(Arrays.asList(nomineePermanentAddress,nomineeResidenceAddress));

			FDRecordsObjList fdRecordsObjList = new FDRecordsObjList();
			fdRecordsObjList.setRecUniqueKey("521");
			fdRecordsObjList.setObjFDDetails(objFDDetails);
			fdRecordsObjList.setObjInvestmentAccDetails(objInvestmentAccDetails);
			fdRecordsObjList.setObjMaturityAccDetails(objMaturityAccDetails);
			fdRecordsObjList.setObjKycDoc(Arrays.asList(objKycDoc1,objKycDoc2,objKycDoc3,objKycDoc4));
			fdRecordsObjList.setObjApplDetails("YES".equalsIgnoreCase(nomineePresent)?(Arrays.asList(objApplDetail, objApplDetailNominee)):(Arrays.asList(objApplDetail)));
			RecWrapperFD recWrapperFD = new RecWrapperFD();
			recWrapperFD.setFDRecordsObjList(Arrays.asList(fdRecordsObjList));


			SfdcRequest finalRequest = new SfdcRequest();
			finalRequest.setRecWrapperFD(recWrapperFD);

			ObjectMapper mapper = new ObjectMapper();
			String sdpSfdcFinalRequest = mapper.writeValueAsString(finalRequest);

			logger.info(" === final EKYC SDP Sfdc Request in ekycSdpSfdcService ==== " + sdpSfdcFinalRequest);
			sfdcRes = sfdcIntegration.partialSfdc(sdpSfdcFinalRequest,customerId,contextCall);
			logger.info(" === EKYC SDP SFDC Response in ekycSdpSfdcService ==== " + sfdcRes);


		} catch (Exception e) {
			logger.error(" ==== Exception in ekycSdpSfdcService ===  ", e);
			if(!( customerId.isEmpty())){
				dbManipulation.recordExeption("SFDC_SDP_SERVICE","Exception due to internal call", customerId);
			}
			Utility.loadNewRelicException(e.toString(), "ekycSdpSfdcService", "", customerId);
		}

		return sfdcRes;
	}


	@Override
	@Trace
	public JSONObject partialSfdcServiceNTB(FixedDepositData fixedDepositData, String fullName) {

		logger.info(" === fixedDepositData in partialSfdcServiceNTB ==== " + fixedDepositData);
		JSONObject sfdcRes = new JSONObject();

		try {

			String mobileNumber = fixedDepositData.getMobileNumber() == null ? "NA" : fixedDepositData.getMobileNumber(); 
			String dateOfBirth = fixedDepositData.getDateOfBirth() == null ? "NA" : fixedDepositData.getDateOfBirth();
			//String utmContent = fixedDepositData.getUtmContent() == null ? "NA" : fixedDepositData.getUtmContent();
			String utmCampaign = fixedDepositData.getUtmCampaign() == null ? "NA" : fixedDepositData.getUtmCampaign();
			String utmMedium = fixedDepositData.getUtmMedium() == null ? "NA" : fixedDepositData.getUtmMedium();
			String utmKeyword = fixedDepositData.getUtmkeyword() == null ? "NA" : fixedDepositData.getUtmkeyword();
			String utmSource = fixedDepositData.getUtmSource() == null ? "NA" : fixedDepositData.getUtmSource();
			String pageUrl = fixedDepositData.getPageUrl() == null ? "NA" : fixedDepositData.getPageUrl();
			String device = fixedDepositData.getDevice() == null ? "NA" : fixedDepositData.getDevice();
			String clientId = fixedDepositData.getClientId() == null ? "NA" : fixedDepositData.getClientId();
			String rdplan = fixedDepositData.getRdpLan() == null? "":fixedDepositData.getRdpLan();
			String pincode = fixedDepositData.getPinCode() == null ? "" : fixedDepositData.getPinCode();
			String pan = fixedDepositData.getPanCard() == null ? "" : fixedDepositData.getPanCard();
			String brcode = fixedDepositData.getPartnerCode() == null ? "":fixedDepositData.getPartnerCode();
			String sfdcApiRecordId = fixedDepositData.getSfdcRecordId() == null ? "" : fixedDepositData.getSfdcRecordId();
			String city = fixedDepositData.getCity() == null ? "NA" : fixedDepositData.getCity();
			String sourceBy = "Broker";
			String gclld = fixedDepositData.getGclid()==null? "" : fixedDepositData.getGclid();

			pageUrl = pageUrl.length()>=150 ? pageUrl.replace(" ", "").substring(0, 150) : pageUrl;



			String fName;
			String lName;
			if(fullName.contains(" ")){
				fName =  fullName.substring(0, fullName.indexOf(' ')).trim();
				lName = fullName.substring(fullName.indexOf(' ') + 1).trim();
			}else{
				fName=fullName;
				lName=".";
			}


			if(!("NA".equalsIgnoreCase(dateOfBirth))){
				dateOfBirth = Utility.dateFormerter(dateOfBirth);	
			}
			if("NA".equalsIgnoreCase(brcode)){
				brcode = "";
				sourceBy ="Branch";
			}

			AdressDetailsList adressDetailsList  = new  AdressDetailsList();
			adressDetailsList.setPermAndResddressAreSame("No");
			adressDetailsList.setAddrType("Residence");
			adressDetailsList.setAddress1("");
			adressDetailsList.setAddress2("");
			adressDetailsList.setAddress3("");
			adressDetailsList.setAddress4("");
			adressDetailsList.setCity(city);
			adressDetailsList.setState("");
			adressDetailsList.setCountry("");
			adressDetailsList.setPincode(pincode);
			adressDetailsList.setAddrProof("Passport");
			adressDetailsList.setPrefferedCommAddress("Yes");

			ObjApplDetail objApplDetail = new ObjApplDetail();
			objApplDetail.setLandline("");
			objApplDetail.setEmail("");
			objApplDetail.setFatherSpouseSalutation("");
			objApplDetail.setFatherSpouse("");
			objApplDetail.setSTDCode("0");
			objApplDetail.setGender("Male");
			objApplDetail.setIdentityDocType("PAN");
			objApplDetail.setCKYCNo("");
			objApplDetail.setSalutation("Mr.");
			objApplDetail.setFName(fName);
			objApplDetail.setForm6061("");
			objApplDetail.setApplicantType("Primary Applicant");
			objApplDetail.setMotherFName("");
			objApplDetail.setDateBirthValue(dateOfBirth);
			objApplDetail.setOccupationEmplType("Salaried");
			objApplDetail.setMName("");
			objApplDetail.setLName(lName);
			objApplDetail.setMotherLName("");
			objApplDetail.setFatherSpouseFName("");
			objApplDetail.setFatherSpouseMName("");
			objApplDetail.setFatherSpouseLName("");
			objApplDetail.setIdentityDocExpiryDate("");
			objApplDetail.setMobile(mobileNumber);
			objApplDetail.setDateofIncorporation("");
			objApplDetail.setAdressDetailsList(Arrays.asList(adressDetailsList));
			objApplDetail.setMaritalStatus("");
			objApplDetail.setCustID("");
			objApplDetail.setPAN(pan);
			objApplDetail.setIdentityDocNo(pan);
			objApplDetail.setAnnualIncome("");

			ObjFDDetails objFDDetails = new ObjFDDetails(); 
			objFDDetails.setExistingFDRNo("");
			objFDDetails.setDepositCategory("FD");
			objFDDetails.setEmail("");
			objFDDetails.setEndDateAndTime("");
			objFDDetails.setLoanType("");
			objFDDetails.setScheme("");
			objFDDetails.setSourceBy(sourceBy);
			objFDDetails.setStartDateAndTime("");
			objFDDetails.setPAGEURL(pageUrl);
			objFDDetails.setSubbroker("");
			objFDDetails.setSourcingChannel("Online");
			objFDDetails.setInterest("");
			objFDDetails.setDepositPayableTo("");
			objFDDetails.setPhoneNo(mobileNumber);
			objFDDetails.setRemarks("");
			objFDDetails.setBranch("");
			objFDDetails.setUTMSOURCE(utmSource);
			objFDDetails.setInterestPayoutFreq("");
			objFDDetails.setCustomerSubtype("");
			objFDDetails.setFDRPhysicalRequired("");
			objFDDetails.setTenorDays("0");
			objFDDetails.setLASTCLICK("");
			objFDDetails.setPrefTimeToContact("");
			objFDDetails.setOldFDFinnoneDepositid("");
			objFDDetails.setBroker(brcode);
			objFDDetails.setUTMcontent(pincode);
			objFDDetails.setAppFormNo("");
			objFDDetails.setChannel("");
			objFDDetails.setTenorMonth("");
			objFDDetails.setDEVICE(device);
			objFDDetails.setExistingLAN("");
			objFDDetails.setUTMKEYWORD(utmKeyword);
			objFDDetails.setCity(city);
			objFDDetails.setRdplLan(rdplan);
			objFDDetails.setRenewOption("");
			objFDDetails.setCLIENTID(clientId);
			objFDDetails.setCustomerType("Individual");
			objFDDetails.setFDAmount("");
			objFDDetails.setUTMMEDIUM(utmMedium);
			objFDDetails.setFinnoneCustomerID("");
			objFDDetails.setProductOfferingSrc("Website Incomplete");
			objFDDetails.setDepositOption("");
			objFDDetails.setFullName(fullName);
			objFDDetails.setExistingCustID("");
			objFDDetails.setUTMCAMAPAIGN(utmCampaign);
			objFDDetails.setIsComplete("N");
			objFDDetails.setFDRecIdToUpdate(sfdcApiRecordId);
			objFDDetails.setGclld(gclld);

			FDRecordsObjList fdRecordsObjList = new FDRecordsObjList();
			fdRecordsObjList.setObjApplDetails(Arrays.asList(objApplDetail));
			fdRecordsObjList.setRecUniqueKey("522");
			fdRecordsObjList.setObjFDDetails(objFDDetails);

			RecWrapperFD recWrapperFD = new RecWrapperFD();
			recWrapperFD.setFDRecordsObjList(Arrays.asList(fdRecordsObjList));

			SfdcRequest sfdcRequest = new SfdcRequest();
			sfdcRequest.setRecWrapperFD(recWrapperFD);


			ObjectMapper mapper = new ObjectMapper();
			String sfdcRequestJsonStr = mapper.writeValueAsString(sfdcRequest);
			logger.info(" === sfdcRequest in partialSfdcServiceNTB ==== " + sfdcRequestJsonStr);

			sfdcRes = sfdcIntegration.partialSfdc(sfdcRequestJsonStr,fixedDepositData.getCustomerId(),"");
			logger.info(" === sfdcRes in partialSfdcServiceNTB ==== " + sfdcRes);

		} catch (Exception e) {
			logger.error(" ==== Exception in partialSfdcServiceNTB ==== ", e);
		}

		return sfdcRes;
	}

	@Override
	public String getRePaymentStatus(FixedDepositData fixedDepositData) {

		String response= null;
		String responseUpdateStatus =null;
		String requeryStatusFlag=null;
		String finalSfdcDbStatus=null;
		String transacionID="";
		try {

			FixedDepositData fixedDepositDataUpdated=fixedDepositDao.getFixedDepositData(fixedDepositData.getCustomerId());
			transacionID= fixedDepositDataUpdated.getCustomerId();
			String requestTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
			logger.info("====== BillDesk Response PaymentSuccess Time======" +requestTime);
			String transactionStatus = fixedDepositDataUpdated.getTransactionStatus()== null ? "NA" : fixedDepositDataUpdated.getTransactionStatus();
			String requeryStatus = fixedDepositDataUpdated.getRequerystatus() == null ? "NA" : fixedDepositDataUpdated.getRequerystatus();

			if(!("SUCCESS".equalsIgnoreCase(transactionStatus)&&!("SUCCESS".equalsIgnoreCase(requeryStatus))))
			{
				String paymentChannel = "BAJAJ";
				String paySource= "FD";

				StringBuilder billDeskReq = new StringBuilder();
				billDeskReq.append("{\"TRANSACTIONID\": \"");
				billDeskReq.append(transacionID);
				billDeskReq.append("\",\"PAYMENTCHANNEL\": \"");
				billDeskReq.append(paymentChannel);
				billDeskReq.append("\",\"SOURCE\": \"");
				billDeskReq.append(paySource);
				billDeskReq.append("\"}");
				logger.info("======Requery API Request "+transacionID +"======"+billDeskReq);
				response = custIdIntegration.getBillDeskRepaymentStatus(billDeskReq.toString(),transacionID);

				logger.info("====== BillDesk Response "+transacionID +"======"+response);
				JSONObject billReqData = new JSONObject(response); 
				logger.info("=====billReqData======="+billReqData);

				String bilReqStatus = billReqData.has("status") ? billReqData.get("status").toString():"";
				fixedDepositDataUpdated=fixedDepositDao.getFixedDepositData(fixedDepositData.getCustomerId());


				String transactionStatusUpdated = fixedDepositDataUpdated.getTransactionStatus()== null ? "NA" : fixedDepositDataUpdated.getTransactionStatus();
				String requeryStatusUpdated = fixedDepositDataUpdated.getRequerystatus() == null ? "NA" : fixedDepositDataUpdated.getRequerystatus();

				if(!("SUCCESS".equalsIgnoreCase(transactionStatusUpdated)&&!("SUCCESS".equalsIgnoreCase(requeryStatusUpdated))))
				{
					if("SUCCESS".equalsIgnoreCase(bilReqStatus) )
					{

						if(billReqData.has("data")){


							JSONArray dataJsonArray = billReqData.getJSONArray("data");
							JSONObject newData = new JSONObject();

							for(int i = 0; i < dataJsonArray.length(); i++)
							{
								JSONObject newSuccessData = dataJsonArray.getJSONObject(i);
								String paymentapiStatus = newSuccessData.has("payment_gateway_status")? newSuccessData.get("payment_gateway_status").toString() : "Fail" ;
								if("SUCCESS".equalsIgnoreCase(paymentapiStatus) || "SUCCESSFUL".equalsIgnoreCase(paymentapiStatus))
								{
									newData=newSuccessData;
									break;
								}

							}

							String paymentapiStatus = newData.has("payment_gateway_status")? newData.get("payment_gateway_status").toString() : "Fail" ;
							String utrNo =newData.has("txn_reference_no")? newData.get("txn_reference_no").toString():"NA";
							String transAmount=newData.has("tran_amount")?newData.get("tran_amount").toString():"NA";
							String appFormNumber=newData.has("parent_loan_account_number")?newData.get("parent_loan_account_number").toString():"NA";

							logger.info(" ===  paymentapiStatus recieved from Requery ==="+paymentapiStatus);
							logger.info(" ===  utrNo recieved from Requery ==="+utrNo);
							logger.info(" ===  transAmount recieved from Requery ==="+transAmount);
							logger.info(" ===  appFormNumber recieved from Requery ==="+appFormNumber);
							String dbUtrNumber=fixedDepositDataUpdated.getUtrNumber()==null?"NA":fixedDepositDataUpdated.getUtrNumber();
							logger.info(" ===  dbUtrNumber recieved in Requery ==="+dbUtrNumber);
							if("SUCCESS".equalsIgnoreCase(paymentapiStatus) || "SUCCESSFUL".equalsIgnoreCase(paymentapiStatus))
							{
								if(!dbUtrNumber.equalsIgnoreCase(utrNo))
								{
									FixedDepositData fixedDepositDataUpdatedRecent=fixedDepositDao.getFixedDepositData(fixedDepositDataUpdated.getCustomerId());
									logger.info(" ===  fixedDepositData Updated in recieved from Requery ==="+fixedDepositDataUpdatedRecent);
									fixedDepositDataUpdatedRecent.setUtrNumber(utrNo);
									fixedDepositDataUpdatedRecent.setTransactionStatus(paymentapiStatus);
									fixedDepositDataUpdatedRecent.setRequerystatus("SUCCESS");
									fixedDepositDataUpdatedRecent.setTransactionAmount(transAmount);
									fixedDepositDataUpdatedRecent.setFormAppNumber(appFormNumber);
									fixedDepositDataUpdatedRecent.setPaymentSuccessTime(requestTime);

									responseUpdateStatus = fixedDepositDao.updateFixedDeposit(fixedDepositDataUpdatedRecent);

									String investmentType = fixedDepositDataUpdatedRecent.getInvestmentType() == null ? "NA" : fixedDepositDataUpdatedRecent.getInvestmentType();

									JSONObject sfdcJsonRes = new JSONObject();

									if("FD".equalsIgnoreCase(investmentType)){

										logger.info(" ===  FD SFDC Request ===");
										JSONObject ekycFdSfdcRes = ekycFdSfdcService(fixedDepositDataUpdatedRecent,Constants.REPAYMENTSTATUS);
										logger.info(" ==== ekycFdSfdcRes in paymentResponseService ====== " + ekycFdSfdcRes);
										sfdcJsonRes = ekycFdSfdcRes;

									}else if("SDP".equalsIgnoreCase(investmentType)){

										logger.info(" ===  SDP SFDC Request ===");
										JSONObject ekycSdpSfdcRes = ekycSdpSfdcService(fixedDepositDataUpdatedRecent,Constants.REPAYMENTSTATUS);
										logger.info(" ==== ekycSdpSfdcRes in paymentResponseService ====== " + ekycSdpSfdcRes);

										sfdcJsonRes = ekycSdpSfdcRes;
									}

									logger.info(" ==== sfdcJsonRes in paymentResponseService ====== " + sfdcJsonRes);

									String finalSfdcStatus = sfdcJsonRes.get("IsSuccess") == null ? "NA" : sfdcJsonRes.get("IsSuccess").toString();
									String finalSfdcMsg = sfdcJsonRes.get("Message") == null ? "NA" : sfdcJsonRes.get("Message").toString();
									String finalSfdcUniqueRecId = sfdcJsonRes.get("UniqueRecId") == null ? "NA" : sfdcJsonRes.get("UniqueRecId").toString();
									String finalSfdcId = sfdcJsonRes.get("FdSfdcId") == null ? "NA" : sfdcJsonRes.get("FdSfdcId").toString();
									fixedDepositDataUpdatedRecent.setFinalSfdcStatus(finalSfdcStatus);
									fixedDepositDataUpdatedRecent.setFinalSfdcMsg(finalSfdcMsg);
									fixedDepositDataUpdatedRecent.setFinalSfdcUniqueRecId(finalSfdcUniqueRecId);
									fixedDepositDataUpdatedRecent.setFinalSfdcId(finalSfdcId);
									fixedDepositDataUpdatedRecent.setSchemeCode(schemeCode);
									finalSfdcDbStatus = fixedDepositDao.updateFixedDeposit(fixedDepositDataUpdatedRecent);
									logger.info("=========finalSfdcDbStatus============"+finalSfdcDbStatus);
								}else
								{
									logger.info(" ===  dbUtrNumber recieved in Requery matcheed No SFDC===");
									Utility.loadNewRelicValidation("dbUtrNumber recieved in Requery not matched ", "dbUtrNumber recieved in Requery not matched", transacionID,Constants.REPAYMENTSTATUS);
								}

							}else
							{
								requeryStatusFlag = "paymentapiStatus Received from Requery API "+paymentapiStatus;
								fixedDepositDataUpdated.setRequerystatus(requeryStatusFlag);
								finalSfdcDbStatus = fixedDepositDao.updateFixedDeposit(fixedDepositDataUpdated);
								logger.info("=========finalSfdcDbStatus============"+finalSfdcDbStatus);
								Utility.loadNewRelicValidation("payment_gateway_status recieved as fail in Requery ", "payment_gateway_status recieved as fail in Requery", transacionID,Constants.REPAYMENTSTATUS);
							}

						}else
						{
							requeryStatusFlag = "Requery API not have data Json";
							fixedDepositDataUpdated.setRequerystatus(requeryStatusFlag);
							finalSfdcDbStatus = fixedDepositDao.updateFixedDeposit(fixedDepositDataUpdated);
							logger.info("=========finalSfdcDbStatus============"+finalSfdcDbStatus);
							Utility.loadNewRelicValidation("no data recieved in Requery ", "no data recieved in Requery", transacionID,Constants.REPAYMENTSTATUS);
						}

					}else
					{
						fixedDepositDataUpdated=fixedDepositDao.getFixedDepositData(fixedDepositData.getCustomerId());
						requeryStatusFlag = "first Requery API Status"+bilReqStatus;
						fixedDepositDataUpdated.setRequerystatus(requeryStatusFlag);
						finalSfdcDbStatus = fixedDepositDao.updateFixedDeposit(fixedDepositDataUpdated);
						logger.info("=========finalSfdcDbStatus============"+finalSfdcDbStatus);
					}
				}else
				{
					requeryStatusFlag = "first Requery API Status"+bilReqStatus;
					fixedDepositDataUpdated.setRequerystatus(requeryStatusFlag);
					finalSfdcDbStatus = fixedDepositDao.updateFixedDeposit(fixedDepositDataUpdated);
					logger.info("=========finalSfdcDbStatus============"+finalSfdcDbStatus);
					Utility.loadNewRelicValidation("no success status in Requery ", "no success status in Requery ", transacionID,Constants.REPAYMENTSTATUS);
				}

			}
			else
			{
				fixedDepositDataUpdated=fixedDepositDao.getFixedDepositData(fixedDepositData.getCustomerId());
				responseUpdateStatus = "fail";
				requeryStatusFlag = "Transation is alreday Sucess";
				fixedDepositDataUpdated.setRequerystatus(requeryStatusFlag);
				finalSfdcDbStatus = fixedDepositDao.updateFixedDeposit(fixedDepositDataUpdated);
				logger.info("=========finalSfdcDbStatus============"+finalSfdcDbStatus);

			}


		} catch (Exception e) {
			logger.error("------Exception in getRePaymentStatus--------",e);
			Utility.loadNewRelicException(e.toString(), Constants.REPAYMENTSTATUS, "", transacionID);
		}
		return responseUpdateStatus;

	}

	@Override
	public String okycDatamanupation(String requestJson, HttpSession httpSession) {


		JSONObject userDetails = new JSONObject();
		JSONObject ntbDeatilJson = new JSONObject();

		String custValue=null;   //Customer DOB
		int ageFromOkyc=0;
		String customerId="";
		try {

			byte[] encryptKey = "1234567890123456".getBytes();
			String iv = "dfghjuytrfgtyhuj";
			String decryptOkyc = requestJson.toString();
			String decriptResponse = kycEncryDecryp.decryptOP(decryptOkyc.replace('~', '+').replace("_", "/"), encryptKey, iv);

			logger.info("========decriptResponse========"+decriptResponse);
			String requestTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime()) ;
			logger.info(" === requestTime in addNtbUserDataService === " + requestTime);

			JSONObject okycDecrpt = new JSONObject(decriptResponse);
			String userIdokyc = okycDecrpt.get("UserId").toString();
			String okycUserRequestID = okycDecrpt.get("RequestId").toString();
			FixedDepositData depositData=fixedDepositDao.getFixedDepositData(userIdokyc);
			String investmentType = depositData.getInvestmentType();
			String userMob = depositData.getMobileNumber();
			customerId=userIdokyc;
			httpSession.setAttribute(Constants.MOBILE_NUMBER, userMob);
			httpSession.setAttribute("USER_MOBILE_NUMBER", userMob);
			httpSession.setAttribute("fdUserMobileNumber", userMob);
			depositData.setOkycLandLaningTime(requestTime);
			userDetails.put("fdUserMobileNumber", userMob);
			if("success".equalsIgnoreCase(okycDecrpt.get("status").toString())){

				String okycAdhardata = custIdIntegration.oKycresponseData(okycDecrpt,customerId);

				logger.info("===============okycAdhardata=================="+okycAdhardata);

				JSONObject okycAdhatrData = new JSONObject(okycAdhardata);

				String okycAdharStatus = okycAdhatrData.has("status")? okycAdhatrData.get("status").toString():"fail";


				if("success".equalsIgnoreCase(okycAdharStatus)){

					String okycReturnValue = depositData.getOkycReturnValue();

					if(!("OkycFirstUpdated".equalsIgnoreCase(okycReturnValue))){
						JSONArray jsonArray = okycAdhatrData.getJSONArray("aadhaardata");
						JSONObject aadhaardata = jsonArray.getJSONObject(0);
						String adharName = aadhaardata.has("Poi_name")? aadhaardata.get("Poi_name").toString().toUpperCase(): "";
						String adharHouse = aadhaardata.has("Poa_house")? aadhaardata.get("Poa_house").toString(): "";
						String adharStreet = aadhaardata.has("Poa_street")? aadhaardata.get("Poa_street").toString(): "";
						String adharPO = aadhaardata.has("Poa_po")? aadhaardata.get("Poa_po").toString(): "";
						String adharvtc = aadhaardata.has("Poa_vtc")? aadhaardata.get("Poa_vtc").toString(): "";
						String adharReferenceId = aadhaardata.has("Aadhaar_referenceId")? aadhaardata.get("Aadhaar_referenceId").toString(): "";
						String adharPincode = aadhaardata.has("Poa_pc")? aadhaardata.get("Poa_pc").toString(): "";
						String pan = depositData.getPanCard();
						custValue = aadhaardata.has("Poi_dob")? aadhaardata.get("Poi_dob").toString().trim(): "NA";
						String mobNumber = depositData.getMobileNumber();
						String gender = aadhaardata.has("Poi_gender")? aadhaardata.get("Poi_gender").toString(): "";

						String okycDoc = aadhaardata.has("Pht")? aadhaardata.get("Pht").toString(): "NA";
						String okycAadhaarReferenceId = aadhaardata.has("Aadhaar_referenceId")? aadhaardata.get("Aadhaar_referenceId").toString(): "NA";
						String okycInitiationDT = aadhaardata.has("InitiationDT")? aadhaardata.get("InitiationDT").toString(): "NA";
						String okycEntAadhaarSecurityDT = aadhaardata.has("EntAadhaarSecurityDT")? aadhaardata.get("EntAadhaarSecurityDT").toString(): "NA";
						String okycEntOTPShareDT = aadhaardata.has("EntOTPShareDT")? aadhaardata.get("EntOTPShareDT").toString(): "NA";
						String okycReEntShareCodeDT = aadhaardata.has("ReEntShareCodeDT")? aadhaardata.get("ReEntShareCodeDT").toString(): "NA";
						String okycDetailsDataDT = aadhaardata.has("DetailsDataDT")? aadhaardata.get("DetailsDataDT").toString(): "NA";
						String okycIPAddress = aadhaardata.has("IPAddress")? aadhaardata.get("IPAddress").toString(): "NA";
						String okycUpdateRecordDT = aadhaardata.has("UpdateRecordDT")? aadhaardata.get("UpdateRecordDT").toString(): "NA";
						String aadharNumber = aadhaardata.has("AadhaarNo") ? aadhaardata.get("AadhaarNo").toString() : "NA";

						String addharLoc=aadhaardata.has("Poa_loc") ? aadhaardata.get("Poa_loc").toString() : "NA";          
						String landMark=aadhaardata.has("Poa_landmark") ? aadhaardata.get("Poa_landmark").toString() : "NA";   
						String district=aadhaardata.has("Poa_dist") ? aadhaardata.get("Poa_dist").toString() : "NA"; 
						String subDistrict=aadhaardata.has("Poa_subdist") ? aadhaardata.get("Poa_subdist").toString() : "NA";
						String postOffice=aadhaardata.has("Poa_po") ? aadhaardata.get("Poa_po").toString() : "NA";
						String guiD=aadhaardata.has("Guid") ? aadhaardata.get("Guid").toString() : "NA";


						JSONObject objects = new JSONObject();
						objects.put("ockycDocPhoto", okycDoc);
						String filePath = Utility.getPropertyFileValue("ckycBase64filepath").trim();
						String saveFilePath=custIdIntegration.createJSONfile(objects.toString(), userIdokyc, filePath);


						logger.info("=========DOB========"+custValue);

						if(!("NA".equalsIgnoreCase(custValue))){
							Date formatterDob = new SimpleDateFormat("dd-MM-yyyy").parse(custValue);
							SimpleDateFormat formatter1DDob = new SimpleDateFormat("dd/MM/yyyy");
							custValue = formatter1DDob.format(formatterDob);
							LocalDate currentDate = LocalDate.now();
							LocalDate birthDateFromBrowser = LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(custValue))) ;
							ageFromOkyc=Period.between(birthDateFromBrowser, currentDate).getYears();

							logger.info("=======age From OKYC========="+ageFromOkyc);
						}

						logger.info("formated birth as per Aadhar"+custValue);


						String custType= null;

						if(ageFromOkyc >= 60){
							custType= Constants.DEDUPE_STB;
						}else{
							custType =  "OKYCETB";
						}
						logger.info("cust====="+ageFromOkyc+"===cust type==="+custType );

						httpSession.setAttribute(Constants.FULL_NAME, adharName);
						httpSession.setAttribute(Constants.MOBILE_NUMBER, mobNumber);
						httpSession.setAttribute("USER_MOBILE_NUMBER", mobNumber);
						httpSession.setAttribute(Constants.DATE_OF_BIRTH, custValue);

						String city ="";
						String state ="";


						String address = adharHouse +" "+ adharStreet+ " "+ adharPO+ " "+adharvtc;
						address= address.replaceAll("( +)"," ").trim().toUpperCase();
						int reqPin;
						reqPin = Integer.parseInt(adharPincode);
						FDPincodeMaster fdPiMast = fixedDepositDao.getcitystatename(reqPin);

						if(fdPiMast != null){
							city = fdPiMast.getCity();
							state = fdPiMast.getState();

						}else{
							city ="NA";
							state = "NA";
						}

						userDetails.put(Constants.CUST_TYPE, custType);
						userDetails.put(Constants.FULL_NAME, adharName);
						userDetails.put(Constants.ADDRESS,address);
						userDetails.put(Constants.DATE_OF_BIRTH, custValue);
						userDetails.put(Constants.EXISTING_CUST_ID, adharReferenceId);
						userDetails.put(Constants.PIN,adharPincode);
						userDetails.put(Constants.CITY, city);
						userDetails.put(Constants.PAN, pan);
						userDetails.put(Constants.USER_MOBILE_NUMBER, mobNumber);
						userDetails.put("customerId", customerId);  
						userDetails.put(Constants.DEDUPE_CUST_TYPE, "OKYCETB");
						userDetails.put("investmentType", investmentType);
						ntbDeatilJson.put("userdata", userDetails);
						ntbDeatilJson.put("ntbStatus", Constants.STATUS_SUCCESS);
						ntbDeatilJson.put("customerId", userIdokyc);
						ntbDeatilJson.put(Constants.FULL_NAME, adharName);

						httpSession.setAttribute("userDetails", userDetails.toString());
						okycReturnValue = "OkycFirstUpdated";
						depositData.setGender(gender);
						depositData.setState(state);
						depositData.setOkycReturnValue(okycReturnValue);
						depositData.setDedupeCustomerType(Constants.DEDUPE_ETB);
						depositData.setRemarkCustType("OKYC");
						depositData.setOkycAadhaarreferenceId(okycAadhaarReferenceId);
						depositData.setOkycInitiationDT(okycInitiationDT);
						depositData.setOkycEntAadhaarSecurityDT(okycEntAadhaarSecurityDT); 
						depositData.setOkycEntOTPShareDT(okycEntOTPShareDT);
						depositData.setOkycReEntShareCodeDT(okycReEntShareCodeDT);
						depositData.setOkycDetailsDataDT(okycDetailsDataDT);
						depositData.setOkycIPAddress(okycIPAddress);
						depositData.setOkycUpdateRecordDT(okycUpdateRecordDT);
						depositData.setOkycAPIRequestID(okycUserRequestID);
						depositData.setJourneyStepName("OKYC Success");
						depositData.setAadharNumber(aadharNumber);

						depositData.setAadharHouse(adharHouse);
						depositData.setAadharLocation(addharLoc);
						depositData.setAadharStreet(adharStreet);
						depositData.setAadharLandmark(landMark);
						depositData.setAadharVillage(adharvtc);
						depositData.setAadharSubdistrict(subDistrict);
						depositData.setAadharDistrict(district);
						depositData.setAadharPostOffice(postOffice);
						depositData.setAadharGuiId(guiD);
						depositData.setAadharPincode(adharPincode);
						depositData.setFullName(adharName);
						depositData.setPanCard(pan);
						depositData.setAddress(address);
						depositData.setExistingCustId(adharReferenceId);
						depositData.setKycVerifyStatus("OKYC_SUCCESS");
						depositData.setServrFilePath(saveFilePath);

					}else{
						okycReturnValue = "OkycFirstUpdated";
						depositData.setOkycReturnValue(okycReturnValue);
						userDetails.put(Constants.DEDUPE_CUST_TYPE, "NTB");
						userDetails.put("investmentType", investmentType);
						userDetails.put("customerId", userIdokyc);
						ntbDeatilJson.put("userdata", userDetails);
						ntbDeatilJson.put("investmentType", investmentType);
						ntbDeatilJson.put("customerId", userIdokyc);
						ntbDeatilJson.put("userdata", userDetails);
						ntbDeatilJson.put("ntbStatus", "fail");
						httpSession.setAttribute("userDetails", userDetails.toString());
						if(depositData.getJourneyStepName().equalsIgnoreCase("CKYCVERIFY")) {
							depositData.setJourneyStepName("OKYC failure");
						}
					}

				}else{
					userDetails.put(Constants.DEDUPE_CUST_TYPE, "NTB");
					userDetails.put("investmentType", investmentType);
					userDetails.put("customerId", userIdokyc);
					ntbDeatilJson.put("userdata", userDetails);
					ntbDeatilJson.put("investmentType", investmentType);
					ntbDeatilJson.put("customerId", userIdokyc);

					ntbDeatilJson.put("userdata", userDetails);
					ntbDeatilJson.put("ntbStatus", "fail");

					httpSession.setAttribute("userDetails", userDetails.toString());
					if(depositData.getJourneyStepName().equalsIgnoreCase("CKYCVERIFY")) {
						depositData.setJourneyStepName("OKYC failure");
					}
					Utility.loadNewRelicException("Got fail status from OKYC API", "okycDatamanupation", "",userIdokyc);
				}
			}else{
				userDetails.put(Constants.DEDUPE_CUST_TYPE, "NTB");

				userDetails.put("investmentType", investmentType);
				userDetails.put("customerId", userIdokyc);
				ntbDeatilJson.put("customerId", userIdokyc);

				ntbDeatilJson.put("userdata", userDetails);
				ntbDeatilJson.put("ntbStatus", "fail");
				httpSession.setAttribute("userDetails", userDetails.toString());
				if(depositData.getJourneyStepName().equalsIgnoreCase("CKYCVERIFY")) {
					depositData.setJourneyStepName("OKYC failure");
				}
				Utility.loadNewRelicException("Got fail status from OKYC API", "okycDatamanupation", "",userIdokyc);
			}
			String updateStatus = fixedDepositDao.updateFixedDeposit(depositData);
			logger.info("===========Final okyc Return Value status updated=========="+updateStatus);
			return ntbDeatilJson.toString();

		} catch (Exception e) {

			logger.error("============okyc Data manupation Exception================",e);
			if(!(customerId.isEmpty())){
				dbManipulation.recordExeption("OKYC_RETURN_SERVICE", "Exception due to internal call",customerId);
			}
			userDetails.put(Constants.DEDUPE_CUST_TYPE, "NTB");
			userDetails.put("customerId", customerId);
			ntbDeatilJson.put("userdata", userDetails);
			ntbDeatilJson.put("ntbStatus", "exceptionFail");
			httpSession.setAttribute("userDetails", userDetails.toString());
			Utility.loadNewRelicException(e.toString(), "okycDatamanupation", "", customerId);
			return ntbDeatilJson.toString();
			
		}

	}








	@Override
	public String retryPaymentRequestService(String customerId, String paymentJson) {

		logger.info(" ==== customerId in retryPaymentRequestService === " + customerId);
		logger.info(" === paymentJson in retryPaymentRequestService ==== " + paymentJson);
		String checksumResponse = "";

		try {

			JSONObject paymentReqJson = new JSONObject(paymentJson);

			String accountNumBD = paymentReqJson.get("accountNumBD") == null ? "NA" : paymentReqJson.get("accountNumBD").toString();
			String bankNameBD = paymentReqJson.get("bankNameBD") == null ? "NA" : paymentReqJson.get("bankNameBD").toString();
			String ifscCodeBD = paymentReqJson.get("ifscCodeBD") == null ? "NA" : paymentReqJson.get("ifscCodeBD").toString();
			String paymentOption = paymentReqJson.get("paymentOption") == null ? "NA" : paymentReqJson.get("paymentOption").toString();



			FixedDepositData depositData = fixedDepositDao.getFixedDepositData(customerId);

			int paycount = depositData.getRetryPaymentCount() == 0 ? 0 :depositData.getRetryPaymentCount();
			paycount = paycount + 1;
			depositData.setBankAccountNumber(accountNumBD);
			depositData.setBankName(bankNameBD);
			depositData.setIfscCode(ifscCodeBD);

			depositData.setPaymentChoice(paymentOption);
			depositData.setRetryPaymentCount(paycount);


			logger.info(" ==== depositData in retryPaymentRequestService ==== " + depositData);

			String dataUpdateStatus = fixedDepositDao.updateFixedDeposit(depositData);
			logger.info(" === dataUpdateStatus in retryPaymentRequestService ==== " + dataUpdateStatus);



			String applicationId = depositData.getFormAppNumber();
			logger.info(" ==== applicationId in retryPaymentRequestService ===== " + applicationId);

			checksumResponse = paymentIntegration.generateChecksum(depositData, applicationId,customerId);
			logger.info(" ==== generatedChecksum in retryPaymentRequestService ===== " + checksumResponse);



		} catch (Exception e) {
			logger.error(" === Exception in retryPaymentRequestService === ", e);
			if(!( customerId.isEmpty())){
				dbManipulation.recordExeption("RETRY_PAY_SERVICE","Exception due to internal call", customerId);
			}
			Utility.loadNewRelicException(e.toString(), "retryPaymentRequestService", "", customerId);
		}

		return checksumResponse;
	}








	@Override
	public String cutsomPrefiill(String customerId,String custValue,String mobNo,String fdcNumber,String contextCalled)
	{

		logger.info(" ==== request in cutsomPrefiill ===== " + customerId+"===dob==="+custValue);

		JSONObject detailsjson = new JSONObject();

		try
		{

			String customerDetailsAPIResponse = custIdIntegration.customerDetailsAPICall(mobNo, custValue,fdcNumber,contextCalled);
			logger.info("===========customerDetailsAPIResponse data in cutsomPrefiill==========" + customerDetailsAPIResponse);
			JSONObject custIdResponseJson = new JSONObject(customerDetailsAPIResponse);

			String apiStatus = custIdResponseJson.has(Constants.STATUS) ? custIdResponseJson.get(Constants.STATUS).toString() : "Fail";
			logger.info("======customerDetailsAPIResponse status in cutsomPrefiill=====" + apiStatus);


			if (Constants.STATUS_SUCCESS.equalsIgnoreCase(apiStatus))
			{	
				String custDatailsResponse=custIdResponseJson.has(Constants.CUSTOMER_DEATILS)?custIdResponseJson.get(Constants.CUSTOMER_DEATILS).toString():"";
				logger.info("===========customerDetailsAPICall custDatailsResponse data to Manipulate in cutsomPrefiill= =========" + custDatailsResponse);
				JSONObject customerDeatilsAPIResponse = new JSONObject(custDatailsResponse);
				String custDatailsResponseapiStatus = customerDeatilsAPIResponse.has(Constants.STATUS) ? customerDeatilsAPIResponse.get(Constants.STATUS).toString() : "Fail";
				logger.info("======customerDetailsAPICall status in cutsomPrefiill=====" + custDatailsResponseapiStatus);
				if (Constants.STATUS_SUCCESS.equalsIgnoreCase(custDatailsResponseapiStatus))
				{
					JSONArray customerPiProductDetails =customerDeatilsAPIResponse.has("CustomerPiProductDetails")?customerDeatilsAPIResponse.getJSONArray("CustomerPiProductDetails"):new JSONArray();
					logger.info("------cutsomPrefiill cutsomPrefiill length-----" + customerPiProductDetails.length());
					logger.info("------cutsomPrefiill cutsomPrefiill Data-----" + customerPiProductDetails);
					if (!customerPiProductDetails.isEmpty() && customerPiProductDetails.length() > 0) 
					{

						for (int i = 0; i < customerPiProductDetails.length(); i++) 
						{
							JSONObject objects = customerPiProductDetails.getJSONObject(i);
							String custIdFetch = objects.get(Constants.CUSTOMERID).toString();

							if(customerId.equalsIgnoreCase(custIdFetch))
							{
								logger.info("------cutsomPrefiill customerId"+customerId+" matched with-----" + custIdFetch);

								detailsjson.put(Constants.FULL_NAME,objects.has("FNAME")? objects.get("FNAME"):"NA");
								detailsjson.put("first_name", objects.has("first_name")? objects.get("first_name"):"NA");
								detailsjson.put("last_name", objects.has("last_name")? objects.get("last_name"):"NA");
								detailsjson.put(Constants.DATE_OF_BIRTH,  objects.has("DOB")? objects.get("DOB"):"NA");
								detailsjson.put(Constants.EMAIL, objects.has("EMAIL")?objects.get("EMAIL"):"NA");       
								detailsjson.put(Constants.PIN,objects.has("ZIPCODE")? objects.get("ZIPCODE"):"NA");
								detailsjson.put(Constants.CITY, objects.has("CITY_NAME")?objects.get("CITY_NAME"):"NA");
								detailsjson.put("state",  objects.has("STATE_NAME")? objects.get("STATE_NAME"):"NA");
								detailsjson.put("gender",objects.has("GENDER")? objects.get("GENDER"):"NA");
								detailsjson.put(Constants.PAN, objects.has("PANNO")?objects.get("PANNO"):"NA");
								detailsjson.put(Constants.EXISTING_CUST_ID, custIdFetch);
								detailsjson.put(Constants.PIDATA_API,Constants.STATUS_SUCCESS);	
								break;

							}else{
								detailsjson.put(Constants.PIDATA_API,Constants.STATUS_FAIL);

							}

						}
						logger.info("------cutsomPrefiill Prefiill status Before addres Fetch-----" + detailsjson);
						String detailsjsonPrefillStatus = detailsjson.has("PIDATAAPI") ? detailsjson.get("PIDATAAPI").toString() : "fail";
						if ("success".equalsIgnoreCase(detailsjsonPrefillStatus)) {
							String addressVal =custIdIntegration.getDataToPrefill(mobNo, customerId,fdcNumber,contextCalled);
							FormDataServiceImpl.logger.info((Object)(" == address Response in cutsomPrefiill === " + addressVal));
							JSONObject addressJson = new JSONObject(addressVal);
							String addressPrefillStatus = addressJson.has("PIDATAAPI") ? addressJson.get("PIDATAAPI").toString() : "fail";
							String address = "success".equalsIgnoreCase(addressPrefillStatus) ? addressJson.get("address").toString() : "";
							detailsjson.put("address", address);
						}



					}else{detailsjson.put(Constants.PIDATA_API,Constants.STATUS_FAIL);}	

				}else{detailsjson.put(Constants.PIDATA_API,Constants.STATUS_FAIL);}

			}else{detailsjson.put(Constants.PIDATA_API,Constants.STATUS_FAIL);}

		}
		catch(Exception e)
		{
			if(!( customerId.isEmpty())){
				dbManipulation.recordExeption("PREFILL_SERVICE","Exception due to internal call", customerId);
			}
			logger.error(" === Exception in cutsomPrefiill === ", e);
			detailsjson.put(Constants.PIDATA_API,Constants.STATUS_FAIL);
		}
		logger.info(" ==  Response in cutsomPrefiill === " + detailsjson);
		return detailsjson.toString();


	}








	@Override
	public String storeUploadedCustomerDocuments(Map<String,MultipartFile> mapListOfFiles, String customerId,JSONObject reqJson) 
	{

		logger.info(" === files in storeUploadedCustomerDocuments ==== " + mapListOfFiles);
		String storeFileStatus ="";

		try {

			Runnable runnableFirst=()->{
				String fileExtn="";
				Boolean flag=false;
				Map<String,String> nameOfFileList = new HashMap<>();
				try {

					logger.info(" ===reqJson in storeUploadedCustomerDocuments ==== " +reqJson.toString());
					String indentityDocumentSelected = reqJson.has("indentityDocumentSelected") ? reqJson.get("indentityDocumentSelected").toString():"NA";
					String addressDocumentSelected = reqJson.has("addressDocumentSelected") ? reqJson.get("addressDocumentSelected").toString():"NA";
					String signDocumentSelected = reqJson.has("signDocumentSelected") ?reqJson.get("signDocumentSelected").toString():"NA";
					for(Entry<String, MultipartFile> map : mapListOfFiles.entrySet()){
						logger.info(" === files in storeUploadedCustomerDocuments key ==== " + map.getKey());
						logger.info(" === files in storeUploadedCustomerDocuments Value ==== " + map.getValue());
						MultipartFile fileData=map.getValue();
						String filesharedName=map.getKey().trim();
						String documentName="";
						logger.info(" === filesharedName ==== " + filesharedName);
						if("idententiyDocFrontFile".equalsIgnoreCase(filesharedName) || "idententiyDocBackFile".equalsIgnoreCase(filesharedName))
						{
							documentName=indentityDocumentSelected;

						}else if("addressDocFrontFile".equalsIgnoreCase(filesharedName) || "addressDocBackFile".equalsIgnoreCase(filesharedName))
						{
							documentName=addressDocumentSelected;

						}else if("SignDocFile".equalsIgnoreCase(filesharedName))
						{
							documentName=signDocumentSelected;

						}
						else if("PhotoDocFile".equalsIgnoreCase(filesharedName))
						{
							documentName="PHOTOGRAPH";
						}
						logger.info("=================File in list===============" + fileData.getOriginalFilename());

						if (!(fileData.getOriginalFilename().isEmpty()) && (fileData.getBytes().length > 0))
						{
							fileExtn = Utility.getFileExtension(fileData.getOriginalFilename());
							logger.info("=================File  fileExtn===============" + fileExtn);
							String fileNameToStore=allowNameWithConvetion(filesharedName,documentName);
							File fileToCreate=Utility.multipartToFile(fileData,customerId,fileExtn,fileNameToStore,filesharedName);
							fileToCreate.createNewFile();
							logger.info("=================File created===============" + fileNameToStore);
							nameOfFileList.put(fileNameToStore, fileToCreate.getAbsolutePath());
							flag=true;
						}

					}
					if(flag)
					{
						String poiFrontFilePath="";
						String poiBackFilePath="";
						String poaFrontFilePath="";
						String poaBackFilePath="";
						String poiFileName="";
						String poaFileName="";
						for(Entry<String, String> map : nameOfFileList.entrySet())
						{
							logger.info("=================nameOfFileList to merge in Single key===============" + map.getKey());
							logger.info("=================nameOfFileList to merge in Single Value===============" + map.getValue());
							if("POI_OVD_Passport_Front".equalsIgnoreCase(map.getKey()) || "POI_OVD_Driving License_Front".equalsIgnoreCase(map.getKey()) || "POI_OVD_VoterID_Front".equalsIgnoreCase(map.getKey()) || "POI_OVD_Aadhaar_Front".equalsIgnoreCase(map.getKey())  )
							{
								poiFrontFilePath=map.getValue();
								switch(map.getKey())
								{
								case "POI_OVD_Passport_Front":
									poiFileName="OVD-Applicant-Passport";
									break;
								case "POI_OVD_Driving License_Front":
									poiFileName="OVD-Applicant-Driving licence";
									break;
								case "POI_OVD_Aadhaar_Front":
									poiFileName="OVD-Applicant-Aadhar Card";
									break;
								case "POI_OVD_VoterID_Front":
									poiFileName="OVD-Applicant-Voter Identity card";
									break;

								default:
									poiFileName="";
								}

							}
							if("POI_OVD_Passport_Back".equalsIgnoreCase(map.getKey()) || "POI_OVD_Driving License_Back".equalsIgnoreCase(map.getKey()) || "POI_OVD_VoterID_Back".equalsIgnoreCase(map.getKey()) || "POI_OVD_Aadhaar_Back".equalsIgnoreCase(map.getKey())  )
							{	
								poiBackFilePath=map.getValue();
							}
							if("POA_OVD_Passport_Front".equalsIgnoreCase(map.getKey()) || "POA_OVD_Driving License_Front".equalsIgnoreCase(map.getKey()) || "POA_OVD_VoterID_Front".equalsIgnoreCase(map.getKey()) || "POA_OVD_Aadhaar_Front".equalsIgnoreCase(map.getKey()) || "POA_OVD_NPR_Front".equalsIgnoreCase(map.getKey())  )
							{
								poaFrontFilePath=map.getValue();
								switch(map.getKey())
								{
								case "POI_OVD_Passport_Front":
									poaFileName="OVD-Applicant-Passport";
									break;
								case "POA_OVD_Driving License_Front":
									poaFileName="OVD-Applicant-Driving licence";
									break;
								case "POA_OVD_Aadhaar_Front":
									poaFileName="OVD-Applicant-Aadhar Card";
									break;
								case "POA_OVD_VoterID_Front":
									poaFileName="OVD-Applicant-Voter Identity card";
									break;
								case "POA_OVD_NPR_Front":
									poaFileName="OVD- Applicant-National population register letter";
									break;
								default:
									poaFileName="";
								}
							}

							if("POA_OVD_Passport_Back".equalsIgnoreCase(map.getKey()) || "POA_OVD_Driving License_Back".equalsIgnoreCase(map.getKey()) || "POA_OVD_VoterID_Back".equalsIgnoreCase(map.getKey()) || "POA_OVD_Aadhaar_Back".equalsIgnoreCase(map.getKey()) || "POA_OVD_NPR_Back".equalsIgnoreCase(map.getKey())  )
							{	
								poaBackFilePath=map.getValue();
							}


						}

						if(!"NREGA".equalsIgnoreCase(indentityDocumentSelected))
						{
							Utility.mergePDF(poiFrontFilePath, poiBackFilePath, poiFileName);
						}
						if(!"NA".equalsIgnoreCase(addressDocumentSelected))
						{
							if(!"NREGA".equalsIgnoreCase(addressDocumentSelected))
							{
								Utility.mergePDF(poaFrontFilePath, poaBackFilePath, poaFileName);
							}
						}
					}
				}catch (Exception e) {
					logger.error(" === Exception in thread of storeUploadedCustomerDocuments === ", e);
					Utility.loadNewRelicException(e.toString(), "storeUploadedCustomerDocuments", "", customerId);
				}
			};
			new Thread(runnableFirst).start();
			storeFileStatus=Constants.STATUS_SUCCESS;

		} catch (Exception e) {
			logger.error(" === Exception in storeUploadedCustomerDocuments === ", e);
			if(!( customerId.isEmpty())){
				dbManipulation.recordExeption("DOCS_UPLOAD_SERVICE","Exception due to internal call", customerId);
			}
			storeFileStatus =Constants.STATUS_FAIL;
			Utility.loadNewRelicException(e.toString(), "storeUploadedCustomerDocuments", "", customerId);
		}

		return storeFileStatus;
	}



	@Override
	public String uploadDocService(String requestJson, String customerId)
	{

		logger.info(" == requestJson in uploadDocService === " + requestJson);
		logger.info(" === Customer id in uploadDocService === " + customerId);

		JSONObject responseVal = new JSONObject();
		try {
			String requestTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime()) ;
			logger.info(" === requestTime in addNtbUserDataService === " + requestTime);

			JSONObject userData = new JSONObject(requestJson);

			String indentityDocumentSelected = userData.get("indentityDocumentSelected") == null ?"NA" : userData.get("indentityDocumentSelected").toString();
			String addressDocumentSelected = userData.get("addressDocumentSelected") == null ?"NA" : userData.get("addressDocumentSelected").toString();
			String signDocumentSelected = userData.get("signDocumentSelected") == null ?"NA" : userData.get("signDocumentSelected").toString();
			String addprf = userData.get("addprf") == null ?"NA" : userData.get("addprf").toString();
			String signprf = userData.get("signprf") == null ?"NA" : userData.get("signprf").toString();
			String custId = userData.get("custId") == null ?"NA" : userData.get("custId").toString();



			FixedDepositData depositData = fixedDepositDao.getFixedDepositData(custId);

			depositData.setIndentityDocumentSelected(indentityDocumentSelected);
			depositData.setAddressDocumentSelected(addressDocumentSelected);
			depositData.setSignDocumentSelected(signDocumentSelected);
			depositData.setAddressProof(addprf);
			depositData.setSignProof(signprf);
			depositData.setDocumentUploadTime(requestTime);
			depositData.setRemarkCustType("NTBDocumentUpload");
			depositData.setJourneyStepName("DOCUMENTUPLOAD");
			depositData.setServrFilePath(depositData.getCustomerId());
			logger.info(" ==== depositData in uploadDocService ==== " + depositData);
			responseVal.put(Constants.FULL_NAME,depositData.getNsdlFullName()==null? depositData.getFullName(): depositData.getNsdlFullName());
			responseVal.put(Constants.DATE_OF_BIRTH, depositData.getDateOfBirth()==null? "NA":depositData.getDateOfBirth());
			responseVal.put(Constants.PIN,depositData.getPinCode()==null? "NA":depositData.getPinCode());
			responseVal.put(Constants.PAN, depositData.getPanCard()==null?"NA":depositData.getPanCard());
			responseVal.put(Constants.MOBILE_NUMBER, depositData.getMobileNumber()==null?"NA":depositData.getMobileNumber());
			responseVal.put(Constants.STATUS, Constants.STATUS_SUCCESS);


			depositData.setDedupeCustomerType(Constants.DEDUPE_ETB);

			SimpleDateFormat formatterDob = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat formatter1DDob = new SimpleDateFormat("dd-MM-yyyy");

			Date dateDOB = formatterDob.parse(depositData.getDateOfBirth()==null?"NA":depositData.getDateOfBirth());

			logger.info(formatterDob.format(dateDOB));

			String dobFormatednew=formatter1DDob.format(dateDOB);
			LocalDate currentDate = LocalDate.now();
			LocalDate birthDateFromBrowser = LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(dobFormatednew))) ;
			int ageFromBrowser=Period.between(birthDateFromBrowser, currentDate).getYears();

			if(ageFromBrowser >= 60){
				responseVal.put(Constants.CUST_TYPE, Constants.DEDUPE_STB);
			}else{
				responseVal.put(Constants.CUST_TYPE,  "NTBDocumentUpload");
			}
			String dataUpdateStatus = fixedDepositDao.updateFixedDeposit(depositData);
			logger.info(" === dataUpdateStatus in uploadDocService ==== " + dataUpdateStatus);


		}
		catch(Exception e)
		{
			if(!( customerId.isEmpty())){
				dbManipulation.recordExeption("DOCS_UPLOAD_SERVICE2","Exception due to internal call", customerId);
			}
			logger.error("===========ExCeption in uploadDocService==========",e);
			responseVal.put(Constants.CUST_TYPE,  "NTBDocumentUpload");
			responseVal.put(Constants.STATUS, Constants.STATUS_FAIL);
			Utility.loadNewRelicException(e.toString(), "uploadDocService", "", customerId);
		}
		return responseVal.toString();
	}


	public String allowNameWithConvetion(String documentType,String documentName)
	{
		String documentFileName="";
		logger.info("=====in allowNameWithConvetion ====documentType==" +documentType+"============documentName==="+documentName);	
		switch(documentType)
		{
		case "idententiyDocFrontFile":

			if("NREGA".equalsIgnoreCase(documentName))
			{
				documentFileName="OVD-Applicant-NREGA Job Card";
			}else
			{
				documentFileName="POI_OVD_"+documentName+"_Front";	
			}
			break;
		case "idententiyDocBackFile":

			documentFileName="POI_OVD_"+documentName+"_Back";
			break;
		case "addressDocFrontFile":
			if("NREGA".equalsIgnoreCase(documentName))
			{
				documentFileName="OVD-Applicant-NREGA Job Card";
			}else
			{
				documentFileName="POA_OVD_"+documentName+"_Front";
			}

			break;
		case "addressDocBackFile":
			documentFileName="POA_OVD_"+documentName+"_Back";
			break;
		case "SignDocFile":
			documentFileName="Signature";
			break;
		case "PhotoDocFile":
			documentFileName="Photo-Applicant";
			break;
		default:
			documentFileName=documentName;
		}
		logger.info("====allowNameWithConvetion =documentFileName ====" +documentFileName);
		return documentFileName;
	}



	@Trace
	private JSONObject ntbDocumentSFDC(FixedDepositData depositData,String contextCalled){

		JSONObject sfdcRes = new JSONObject();
		logger.info(" === ================SntbDocumentSFDC================ ==== ");
		JSONArray jsonSfdcId=new JSONArray();
		String docapiStatus="";
		String customerId="";
		try {


			String finalSfdcId = depositData.getFinalSfdcId() == null ? "NA" : depositData.getFinalSfdcId();
			customerId = depositData.getServrFilePath()==null?depositData.getCustomerId():depositData.getServrFilePath();
			logger.info(" === ================Cust id for Doc Upload for "+depositData.getCustomerId() +"================ ==== "+customerId);

			String propetyFilepathFordocumentUploadPath=Utility.getPropertyFileValue("documentUploadPath").trim();

			String filledPdfPathDocument=propetyFilepathFordocumentUploadPath+customerId+"/POI/";
			logger.info(" === EKYC ntbDocument  path ==== " + filledPdfPathDocument);
			File filePoi = new File(filledPdfPathDocument);
			for (File subFile : filePoi.listFiles()) 
			{
				String FilePath=subFile.getAbsolutePath();
				logger.info(" === FilePath To convert Base 64 == " +subFile.getName());
				JSONObject sfdcPdf = dataDownloadService.sfdcDocumentConvertBase64Service(FilePath);


				String fileEncode = sfdcPdf.has("fileEncode") ? sfdcPdf.get("fileEncode").toString() : "";
				String fileName = sfdcPdf.has("fileName") ? sfdcPdf.get("fileName").toString() : "";
				String docType= fileName.substring(0, fileName.lastIndexOf('.')).replace('.', ' ').replaceAll("( +)"," ").trim();

				JSONObject ntbDocumentUploadSFDCJson = new JSONObject();

				ntbDocumentUploadSFDCJson.put("ParentId", finalSfdcId);
				ntbDocumentUploadSFDCJson.put("Name", fileName);
				ntbDocumentUploadSFDCJson.put("body", fileEncode);
				ntbDocumentUploadSFDCJson.put("Description", docType);


				logger.info(" === final Document Upload SFDC Json Request in ntbDocumentSFDC ==== " + ntbDocumentUploadSFDCJson);

				sfdcRes = sfdcIntegration.ntbDocumentUploadSFDC(ntbDocumentUploadSFDCJson.toString(),customerId,contextCalled);

				String documentUploadId = sfdcRes.get("documentUploadId") == null ? "NA" : sfdcRes.get("documentUploadId").toString();
				docapiStatus = sfdcRes.get("docapiStatus") == null ? "NA" : sfdcRes.get("docapiStatus").toString();

				jsonSfdcId.put(documentUploadId);

			}
			String addressDocSelected=depositData.getAddressDocumentSelected()==null?"NA":depositData.getAddressDocumentSelected();
			logger.info(" === addressDocSelected ntbDocument   ==== " + addressDocSelected);
			if(!"NA".equalsIgnoreCase(addressDocSelected))
			{
				String filledPdfPathDocumentPoa=propetyFilepathFordocumentUploadPath+customerId+"/POA/";
				logger.info(" === EKYC ntbDocument  path ==== " + filledPdfPathDocumentPoa);
				File filePoa = new File(filledPdfPathDocumentPoa);
				for (File subFile : filePoa.listFiles()) 
				{
					String FilePath=subFile.getAbsolutePath();
					logger.info(" === FilePath To convert Base 64 == " +subFile.getName());
					JSONObject sfdcPdf = dataDownloadService.sfdcDocumentConvertBase64Service(FilePath);


					String fileEncode = sfdcPdf.has("fileEncode") ? sfdcPdf.get("fileEncode").toString() : "";
					String fileName = sfdcPdf.has("fileName") ? sfdcPdf.get("fileName").toString() : "";
					String docType= fileName.substring(0, fileName.lastIndexOf('.')).replace('.', ' ').replaceAll("( +)"," ").trim();

					JSONObject ntbDocumentUploadSFDCJson = new JSONObject();

					ntbDocumentUploadSFDCJson.put("ParentId", finalSfdcId);
					ntbDocumentUploadSFDCJson.put("Name", fileName);
					ntbDocumentUploadSFDCJson.put("body", fileEncode);
					ntbDocumentUploadSFDCJson.put("Description", docType);

					logger.info(" === final Document Upload SFDC Json Request in ntbDocumentSFDC ==== " + ntbDocumentUploadSFDCJson);

					sfdcRes = sfdcIntegration.ntbDocumentUploadSFDC(ntbDocumentUploadSFDCJson.toString(),customerId,contextCalled);

					String documentUploadId = sfdcRes.get("documentUploadId") == null ? "NA" : sfdcRes.get("documentUploadId").toString();
					docapiStatus = sfdcRes.get("docapiStatus") == null ? "NA" : sfdcRes.get("docapiStatus").toString();

					jsonSfdcId.put(documentUploadId);

				}
			}
			String filledPdfPathDocumentDoc=propetyFilepathFordocumentUploadPath+customerId+"/DOC/";
			logger.info(" === EKYC ntbDocument  path ==== " + filledPdfPathDocumentDoc);
			File filedoc= new File(filledPdfPathDocumentDoc);
			for (File subFile : filedoc.listFiles()) 
			{
				String FilePath=subFile.getAbsolutePath();
				logger.info(" === FilePath To convert Base 64 == " +subFile.getName());
				JSONObject sfdcPdf = dataDownloadService.sfdcDocumentConvertBase64Service(FilePath);


				String fileEncode = sfdcPdf.has("fileEncode") ? sfdcPdf.get("fileEncode").toString() : "";
				String fileName = sfdcPdf.has("fileName") ? sfdcPdf.get("fileName").toString() : "";
				String docType= fileName.substring(0, fileName.lastIndexOf('.')).replace('.', ' ').replaceAll("( +)"," ").trim();

				JSONObject ntbDocumentUploadSFDCJson = new JSONObject();

				ntbDocumentUploadSFDCJson.put("ParentId", finalSfdcId);
				ntbDocumentUploadSFDCJson.put("Name", fileName);
				ntbDocumentUploadSFDCJson.put("body", fileEncode);
				ntbDocumentUploadSFDCJson.put("Description", docType);



				logger.info(" === final Document Upload SFDC Json Request in ntbDocumentSFDC ==== " + ntbDocumentUploadSFDCJson);

				sfdcRes = sfdcIntegration.ntbDocumentUploadSFDC(ntbDocumentUploadSFDCJson.toString(),customerId,contextCalled);

				String documentUploadId = sfdcRes.get("documentUploadId") == null ? "NA" : sfdcRes.get("documentUploadId").toString();
				docapiStatus = sfdcRes.get("docapiStatus") == null ? "NA" : sfdcRes.get("docapiStatus").toString();

				jsonSfdcId.put(documentUploadId);

			}
			sfdcRes.put("documentUploadId", jsonSfdcId.toString().replaceAll("\\\\", ""));
			sfdcRes.put("docapiStatus", docapiStatus);
			logger.info(" === EKYC ntbDocumentSFDC API ==== " + sfdcRes);


		} catch (Exception e)
		{
			logger.error(" ==== Exception in ekycSdpSfdcService ===  ", e);
			if(!(customerId.isEmpty())){
				dbManipulation.recordExeption("DOC_API_SERVICE","Exception due to internal call", customerId);
			}
			sfdcRes.put("documentUploadId", jsonSfdcId.toString());
			sfdcRes.put("docapiStatus", "Fail");
			Utility.loadNewRelicException(e.toString(), "ntbDocumentSFDC", "", depositData.getCustomerId());
		}
		finally{
			Thread.interrupted();
			logger.info("------ Closing Resources Thraed .... !!!!");
		}
		return sfdcRes;
	}

	@Override
	public List<FixedDepositData> custListRemoveUploadedDocumets() 
	{
		String documentPath=Utility.getPropertyFileValue("documentUploadPath").trim();
		ArrayList<FixedDepositData> custList=null;
		try
		{
			custList=(ArrayList<FixedDepositData>) fixedDepositDao.custListRemoveUploadedDocumets();
			logger.info("===============custList========"+custList.size());
			File file = new File(documentPath);
			if(!(custList.isEmpty())){

				logger.info("===============List of Folder to Delete========"+custList.toString());
				for (FixedDepositData fixedDepositData : custList) 
				{


					if("SUCCESS".equalsIgnoreCase(fixedDepositData.getTransactionStatus()) && "SUCCESS".equalsIgnoreCase(fixedDepositData.getFinalSfdcStatus()) && "success".equalsIgnoreCase(fixedDepositData.getDocumentUploadApiResponse()))
					{


						for (File subFile : file.listFiles()) 
						{
							if(subFile.getName().equalsIgnoreCase(fixedDepositData.getCustomerId()))
							{
								logger.info("===============Folder to Delete========"+fixedDepositData.getCustomerId());
								logger.info("===============Folder to Delete========"+subFile.getAbsolutePath());
								if(subFile.isDirectory())
								{
									String[]entries = subFile.list();
									for(String s: entries)
									{

										File currentFile = new File(subFile.getPath(),s);
										if (currentFile.isDirectory())
										{
											for (File sub : currentFile.listFiles())
											{
												sub.delete();
												logger.info("=====File deleteed SuccessFully ====" +sub.getName());
											}
										}
										currentFile.delete();
										logger.info("=====File deleteed SuccessFully ====" +currentFile.getName());	
									}
								}

								subFile.delete();
								logger.info("=====File deleteed SuccessFully ====" +subFile.getName());	
							}
						}
					}

				}
			}
		}catch(Exception e)
		{
			logger.error("============Exception in custListRemoveUploadedDocumetsService==========", e);
		}
		return custList;
	}



	@Override
	public List<FixedDepositData> custListRemoveFailUploadedDocumets() {
		String documentPath=Utility.getPropertyFileValue("documentUploadPath").trim();
		ArrayList<FixedDepositData> custList=null;
		try
		{
			custList=(ArrayList<FixedDepositData>) fixedDepositDao.custListRemoveFailUploadedDocumets();
			logger.info("===============custList========"+custList.size());
			File file = new File(documentPath);
			if(!(custList.isEmpty())){

				logger.info("===============List of Folder to Delete========"+custList.toString());
				for (FixedDepositData fixedDepositData : custList) 
				{

					for (File subFile : file.listFiles()) 
					{
						if(subFile.getName().equalsIgnoreCase(fixedDepositData.getCustomerId()))
						{
							logger.info("===============Folder to Delete========"+fixedDepositData.getCustomerId());
							logger.info("===============Folder to Delete========"+subFile.getAbsolutePath());
							if(subFile.isDirectory())
							{
								String[]entries = subFile.list();
								for(String s: entries)
								{

									File currentFile = new File(subFile.getPath(),s);
									if (currentFile.isDirectory())
									{
										for (File sub : currentFile.listFiles())
										{
											sub.delete();
											logger.info("=====File deleteed SuccessFully ====" +sub.getName());
										}
									}
									currentFile.delete();
									logger.info("=====File deleteed SuccessFully ====" +currentFile.getName());	
								}
							}

							subFile.delete();
							logger.info("=====File deleteed SuccessFully ====" +subFile.getName());	
						}
					}


				}
			}
		}catch(Exception e)
		{
			logger.error("============Exception in custListRemoveUploadedDocumetsService==========", e);
		}
		return custList;
	}


	@Override
	public String eventHubService(FixedDepositData fixedDepositData, String stepNoData) {

		JSONObject eventHubJson = new JSONObject();
		String eventhubString="";
		String customerId="";
		try{
			customerId = fixedDepositData.getCustomerId()== null ? 		"NA" : fixedDepositData.getCustomerId();

			logger.info("=======custID======"+customerId+"========Event Hub Json Step NO Data==========="+stepNoData);

			eventHubJson.put("Customer_Id",customerId);
			eventHubJson.put("title_dropdownTrue",fixedDepositData.getSalutation() 		== null ?	"NA" : fixedDepositData.getSalutation());
			eventHubJson.put("fullname_fullnameTrue",fixedDepositData.getFullName() 		== null ? 	"NA" : fixedDepositData.getFullName());
			eventHubJson.put("mobile_number_mobileTrue",fixedDepositData.getMobileNumber() == null ? 	"NA" : fixedDepositData.getMobileNumber());
			eventHubJson.put("personal_emailid_emailidTrue",fixedDepositData.getEmailAddress() == null ? 	"NA" : fixedDepositData.getEmailAddress());
			eventHubJson.put("pan_card_pancardTrue",fixedDepositData.getPanCard() == null ? 			"NA" : fixedDepositData.getPanCard());
			eventHubJson.put("addressline_1_addressTrue",fixedDepositData.getAddress() 	== null ? 	"NA" : fixedDepositData.getAddress());
			eventHubJson.put("createon_createonTrue",fixedDepositData.getCreatedOn() 		== null ? 	"NA" : fixedDepositData.getCreatedOn());
			eventHubJson.put("date_of_birth_dateTrue",fixedDepositData.getDateOfBirth() 	== null ? 	"NA" : fixedDepositData.getDateOfBirth());
			eventHubJson.put("Description_textnumspecialTrue",fixedDepositData.getPartnerName() == null ?		"NA" : fixedDepositData.getPartnerName());
			eventHubJson.put("partnerId",fixedDepositData.getPartnerCode() 				== null ?	"NA" : fixedDepositData.getPartnerCode());
			eventHubJson.put("PageURL_True",fixedDepositData.getPageUrl() 					== null ? 	"NA" : fixedDepositData.getPageUrl());
			eventHubJson.put("device_True",fixedDepositData.getDevice() 					== null ? 	"NA" : fixedDepositData.getDevice());
			eventHubJson.put("Client_ID",fixedDepositData.getGaID()						== null ? 	"NA" : fixedDepositData.getGaID());
			eventHubJson.put("Cookie_ID",fixedDepositData.getCookieId() 					== null ?	"NA" : fixedDepositData.getCookieId());
			eventHubJson.put("unqiuecodeId",fixedDepositData.getUniqueId() 				== null ? 	"NA" : fixedDepositData.getUniqueId());
			eventHubJson.put("utm_source_utmTrue",fixedDepositData.getUtmSource() 			== null ?	"NA" : fixedDepositData.getUtmSource());
			eventHubJson.put("utm_medium_utmTrue",fixedDepositData.getUtmMedium() 			== null ?	"NA" : fixedDepositData.getUtmMedium());
			eventHubJson.put("utm_campaign_utmTrue",fixedDepositData.getUtmCampaign() 		== null ?	"NA" : fixedDepositData.getUtmCampaign());
			eventHubJson.put("utm_keyword_utmTrue",fixedDepositData.getUtmkeyword() 		== null ?	"NA" : fixedDepositData.getUtmkeyword());
			eventHubJson.put("utm_term_utmTrue",fixedDepositData.getUtmTerm() 				== null ? 	"NA" : fixedDepositData.getUtmTerm());
			eventHubJson.put("utm_content_utmTrue",fixedDepositData.getUtmContent() 		== null ? 	"NA" : fixedDepositData.getUtmContent());
			eventHubJson.put("wcmtime",fixedDepositData.getWcmTime() 						== null ?	"NA" : fixedDepositData.getWcmTime());
			eventHubJson.put("sfdcRemark",fixedDepositData.getSfdcRemarks() 				== null ? 	"NA" : fixedDepositData.getSfdcRemarks());
			eventHubJson.put("sfdcID",fixedDepositData.getSfdcRecordId() 					== null ? 	"NA" : fixedDepositData.getSfdcRecordId());
			eventHubJson.put("pageName_form",fixedDepositData.getFormName() 				== null ? 	"NA" : fixedDepositData.getFormName());
			eventHubJson.put("form_Id",fixedDepositData.getFormId() 						== null ? 	"NA" : fixedDepositData.getFormId());
			eventHubJson.put("db_Status",fixedDepositData.getDbStatus() 					== null ? 	"NA" : fixedDepositData.getDbStatus());
			eventHubJson.put("pincode_pincodeTrue",fixedDepositData.getPinCode() 			== null ?	"NA" : fixedDepositData.getPinCode());
			eventHubJson.put("city_textTrue",fixedDepositData.getCity() 					== null ?	"NA" : fixedDepositData.getCity());
			eventHubJson.put("state_dropdownTrue",fixedDepositData.getState()				== null ? 	"NA" : fixedDepositData.getState());
			eventHubJson.put("customerType",fixedDepositData.getCustomerType() 			== null ?	"NA" : fixedDepositData.getCustomerType());
			eventHubJson.put("dedupeCustomerType",fixedDepositData.getDedupeCustomerType() == null ?	"NA" : fixedDepositData.getDedupeCustomerType());
			eventHubJson.put("customerId",fixedDepositData.getExistingCustId() 			== null ? 	"NA" : fixedDepositData.getExistingCustId());
			eventHubJson.put("accNumber",fixedDepositData.getBankAccountNumber()			== null ? 	"NA" : fixedDepositData.getBankAccountNumber());
			eventHubJson.put("ifscCode",fixedDepositData.getIfscCode() 					== null ? 	"NA" : fixedDepositData.getIfscCode());
			eventHubJson.put("bankName",fixedDepositData.getBankName() 					== null ?	"NA" : fixedDepositData.getBankName());
			eventHubJson.put("BsankaccountType",fixedDepositData.getAccountType()			== null ?	"NA" : fixedDepositData.getAccountType());
			eventHubJson.put("investmentType",fixedDepositData.getInvestmentType()			== null ?	"NA" : fixedDepositData.getInvestmentType());
			eventHubJson.put("balanceSession",fixedDepositData.getDepositAmount()			== null ? 	"NA" : fixedDepositData.getDepositAmount());
			eventHubJson.put("txt_status",fixedDepositData.getTransactionStatus()				== null ? 	"NA" : fixedDepositData.getTransactionStatus());
			eventHubJson.put("Tenor",fixedDepositData.getTenor()							== null ? 	"NA" : fixedDepositData.getTenor());
			eventHubJson.put("interestPayout",fixedDepositData.getInterestPayout()			== null ?	"NA" : fixedDepositData.getInterestPayout());
			eventHubJson.put("paymentType",fixedDepositData.getPaymentType()				== null ? 	"NA" : fixedDepositData.getPaymentType());
			eventHubJson.put("interestRate",fixedDepositData.getInterestRate()				== null ?	"NA" : fixedDepositData.getInterestRate());
			eventHubJson.put("numberOfDeposit",fixedDepositData.getNumberOfDeposit()		== null ?	"NA" : fixedDepositData.getNumberOfDeposit());
			eventHubJson.put("dateOfEachDeposit",fixedDepositData.getDateOfEachDeposit()  	== null ?	"NA" : fixedDepositData.getDateOfEachDeposit());
			eventHubJson.put("fdRenew",fixedDepositData.getFdRenew()						== null ? 	"NA" : fixedDepositData.getFdRenew());
			eventHubJson.put("fdRenewType",fixedDepositData.getFdRenewType()				== null ?	"NA" : fixedDepositData.getFdRenewType());
			eventHubJson.put("transaction_amount",fixedDepositData.getTransactionAmount()	== null ?	"NA" : fixedDepositData.getTransactionAmount());
			eventHubJson.put("transaction_status",fixedDepositData.getTransactionStatus()	== null ? 	"NA" : fixedDepositData.getTransactionStatus());
			eventHubJson.put("transaction_description",fixedDepositData.getTransactionMessage()	== null ?	"NA" : fixedDepositData.getTransactionMessage());
			eventHubJson.put("directorOrPromoter",fixedDepositData.getDirectorOrPromoter() == null ?	"NA" : fixedDepositData.getDirectorOrPromoter());
			eventHubJson.put("relativeOfDirector",fixedDepositData.getRelativeOfDirector()	== null ?	"NA" : fixedDepositData.getRelativeOfDirector());
			eventHubJson.put("shareholder",fixedDepositData.getShareholder()				== null ?	"NA" : fixedDepositData.getShareholder());
			eventHubJson.put("foreignCitizen",fixedDepositData.getForeignCitizen()			== null ?	"NA" : fixedDepositData.getForeignCitizen());
			eventHubJson.put("foreignTaxPayer",fixedDepositData.getForeignTaxPayer()		== null ?	"NA" : fixedDepositData.getForeignTaxPayer());
			eventHubJson.put("utrNumber",fixedDepositData.getUtrNumber()					== null ? 	"NA" : fixedDepositData.getUtrNumber());
			eventHubJson.put("smsStatus",fixedDepositData.getSmsStatus()					== null ?	"NA" : fixedDepositData.getSmsStatus());
			eventHubJson.put("nomineeCheck",fixedDepositData.getNomineeCheck()				== null ?	"NA" : fixedDepositData.getNomineeCheck());
			eventHubJson.put("nominee_name",fixedDepositData.getNomineeName()				== null ?	"NA" : fixedDepositData.getNomineeName());
			eventHubJson.put("nominee_date_of_birth",fixedDepositData.getNomineeDateOfBirth()	== null ?	"NA" :fixedDepositData.getNomineeDateOfBirth());
			eventHubJson.put("nominee_relation",fixedDepositData.getRelationshipWithNominee() == null ? "NA":fixedDepositData.getRelationshipWithNominee());
			eventHubJson.put("finalSfdcStatus",fixedDepositData.getFinalSfdcStatus()		== null ? 	"NA" : fixedDepositData.getFinalSfdcStatus());
			eventHubJson.put("finalSfdcMsg",fixedDepositData.getFinalSfdcMsg()				== null ?	"NA" : fixedDepositData.getFinalSfdcMsg());
			eventHubJson.put("finalSfdcUniqueRecId",fixedDepositData.getFinalSfdcUniqueRecId() == null? "NA": fixedDepositData.getFinalSfdcUniqueRecId());
			eventHubJson.put("finalSfdcId",fixedDepositData.getFinalSfdcId()				== null ? 	"NA" : fixedDepositData.getFinalSfdcId());
			eventHubJson.put("formAppNumber",fixedDepositData.getFormAppNumber()			== null ?	"NA" : fixedDepositData.getFormAppNumber());
			eventHubJson.put("sdpTotalPriAmnt",fixedDepositData.getSdpTotalPriAmnt()		== null ?	"NA" : fixedDepositData.getSdpTotalPriAmnt());
			eventHubJson.put("SdptotalPayoutAmnt",fixedDepositData.getSdptotalPayoutAmnt()	== null ?	"NA" : fixedDepositData.getSdptotalPayoutAmnt());
			eventHubJson.put("FdMaturityDate",fixedDepositData.getFdMaturityDate()			== null ? 	"NA" : fixedDepositData.getFdMaturityDate());
			eventHubJson.put("fdInterestAmnt",fixedDepositData.getFdInterestAmnt()			== null ? 	"NA" : fixedDepositData.getFdInterestAmnt());
			eventHubJson.put("fdMaturityAmnt",fixedDepositData.getFdMaturityAmnt()			== null ?	"NA" : fixedDepositData.getFdMaturityAmnt());
			eventHubJson.put("pocketAgreementno",fixedDepositData.getPocketAgreementno()	== null ?	"NA" : fixedDepositData.getPocketBalance());
			eventHubJson.put("pocketBankname",fixedDepositData.getPocketBankname()			== null	?	"NA" : fixedDepositData.getPocketBankname());
			eventHubJson.put("pocketAccountno",fixedDepositData.getPocketAccountno()		== null ?	"NA" : fixedDepositData.getPocketAccountno());
			eventHubJson.put("pocketMicrcode",fixedDepositData.getPocketMicrcode()			== null ?	"NA" : fixedDepositData.getPocketMicrcode());
			eventHubJson.put("pocketBflcustomerid",fixedDepositData.getPocketBflcustomerid() == null ?	"NA" : fixedDepositData.getPocketBflcustomerid());
			eventHubJson.put("pocketBankname",fixedDepositData.getPocketBankname()			== null ? 	"NA" : fixedDepositData.getPocketBankname());
			eventHubJson.put("pocketMandaterefno",fixedDepositData.getPocketMandaterefno()	== null ? 	"NA" : fixedDepositData.getPocketAgreementno());
			eventHubJson.put("pocketActiveflag",fixedDepositData.getPocketActiveflag()		== null ?	"NA" : fixedDepositData.getPocketActiveflag());
			eventHubJson.put("pocketBalance",fixedDepositData.getPocketBalance()			== null ?	"NA" : fixedDepositData.getPocketBalance());
			eventHubJson.put("ckycStatus",fixedDepositData.getCkycStatus()					== null ?	"NA" : fixedDepositData.getCkycStatus());
			eventHubJson.put("gender_dropdownTrue",fixedDepositData.getGender()			== null ?	"NA" : fixedDepositData.getGender());
			eventHubJson.put("last_clicked_clicktrue",fixedDepositData.getLastClick()		== null ?	"NA" : fixedDepositData.getLastClick());
			eventHubJson.put("checksum_response",fixedDepositData.getChecksumresponse()	== null ?	"NA" : fixedDepositData.getChecksumresponse());
			eventHubJson.put("Rrequerystatus",fixedDepositData.getRequerystatus()			== null ?	"NA" : fixedDepositData.getRequerystatus());
			eventHubJson.put("ipAdress",fixedDepositData.getIpAdress()						== null ?	"NA" : fixedDepositData.getIpAdress());
			eventHubJson.put("timeOfLogging",fixedDepositData.getTimeOfLogging()			== null ?	"NA" : fixedDepositData.getTimeOfLogging());
			eventHubJson.put("otpTriggeredTime",fixedDepositData.getOtpTriggeredTime()		== null ?	"NA" : fixedDepositData.getOtpTriggeredTime());
			eventHubJson.put("otpSubmittedTime",fixedDepositData.getOtpSubmittedTime()		== null ?	"NA" : fixedDepositData.getOtpSubmittedTime());
			eventHubJson.put("paymentMadeTime",fixedDepositData.getPaymentMadeTime()		== null ?	"NA" : fixedDepositData.getPaymentMadeTime());
			eventHubJson.put("okycReturnValue",fixedDepositData.getOkycReturnValue()		== null ?	"NA" : fixedDepositData.getOkycReturnValue());
			eventHubJson.put("gclid",fixedDepositData.getGclid()							== null ?	"NA" : fixedDepositData.getGclid());
			eventHubJson.put("nsdlFullName",fixedDepositData.getNsdlFullName()				== null ? 	"NA" : fixedDepositData.getNsdlFullName());
			eventHubJson.put("remarkCustType",fixedDepositData.getRemarkCustType()			== null ? 	"NA" : fixedDepositData.getRemarkCustType());
			eventHubJson.put("maturityScheme",fixedDepositData.getMaturityScheme()			== null ?	"NA" : fixedDepositData.getMaturityScheme());
			eventHubJson.put("impsBeneName",fixedDepositData.getImpsBeneName()				== null ?	"NA" : fixedDepositData.getImpsBeneName());
			eventHubJson.put("fixed_maturity_plan_amountFalse",fixedDepositData.getSchemeCode()					== null ?	"NA" : fixedDepositData.getSchemeCode());
			eventHubJson.put("dealerId",fixedDepositData.getFdExistingCustID()					== null ?	"NA" : fixedDepositData.getFdExistingCustID());
			eventHubJson.put("customerRefno",fixedDepositData.getCustomerId()					== null ?	"NA" : fixedDepositData.getCustomerId());
			eventHubJson.put("created_on",fixedDepositData.getCreatedOn()				== null ?	"NA" : fixedDepositData.getCreatedOn());
			eventHubJson.put("checkbox_checkboxFalse",	fixedDepositData.getInterestRate()				== null ?	"NA" : fixedDepositData.getInterestRate());
			eventHubJson.put("amount",fixedDepositData.getTransactionAmount()				== null ?	"NA" : fixedDepositData.getTransactionAmount());
			eventHubJson.put("Deposit-Amount_amountTrue",fixedDepositData.getDepositAmount()== null ?	"NA" : fixedDepositData.getDepositAmount());
			eventHubJson.put("feedBackResponse",fixedDepositData.getTransactionMessage()		== null ?	"NA" : fixedDepositData.getTransactionMessage());
			eventHubJson.put("Nominee_Name_fullnameTrue",fixedDepositData.getNomineeName()== null ?	"NA" : fixedDepositData.getNomineeName());
			eventHubJson.put("Nominee_dob_dateTrue",fixedDepositData.getNomineeDateOfBirth()				== null ?	"NA" : fixedDepositData.getNomineeDateOfBirth());
			eventHubJson.put("Relationship_with_Nominee_dropdownTrue",fixedDepositData.getRelationshipWithNominee()				== null ?	"NA" : fixedDepositData.getRelationshipWithNominee());
			eventHubJson.put("interest_offers",fixedDepositData.getInterestPayoutType()		== null ?	"NA" : fixedDepositData.getInterestPayoutType());
			eventHubJson.put("registration_number_alphanumericTrue",fixedDepositData.getFormAppNumber()				== null ?	"NA" : fixedDepositData.getFormAppNumber());
			eventHubJson.put("offerStatus",	fixedDepositData.getCkycStatus()				== null ?	"NA" : fixedDepositData.getCkycStatus() );
			eventHubJson.put("gender_radioTrue",fixedDepositData.getGender()			== null ?	"NA" : fixedDepositData.getGender());
			eventHubJson.put("city_dropdownTrue",fixedDepositData.getCity()			== null ?	"NA" : fixedDepositData.getCity());
			eventHubJson.put("CREATEDIP_True",fixedDepositData.getIpAdress()			== null ?	"NA" : fixedDepositData.getIpAdress());
			eventHubJson.put("Preferred-Time-of-Contact_dropdownFalse",fixedDepositData.getOtpSubmittedTime()			== null ?	"NA" : fixedDepositData.getOtpSubmittedTime());
			eventHubJson.put("looking_for_dropdownTrue",	fixedDepositData.getPaymentChoice()			== null ?	"NA" : fixedDepositData.getPaymentChoice());
			eventHubJson.put("count_otp_requested", fixedDepositData.getRetryPaymentCount()			== 0 ?	"NA" : fixedDepositData.getRetryPaymentCount());
			eventHubJson.put("accountNumber",fixedDepositData.getBankAccountNumber()	== null ?	"NA" : fixedDepositData.getBankAccountNumber());
			eventHubJson.put("customerName",fixedDepositData.getFdslfFullName()				== null ?	"NA" : fixedDepositData.getFdslfFullName());
			eventHubJson.put("addressline_2_addressTrue",fixedDepositData.getCommAddress()	== null ?	"NA" : fixedDepositData.getCommAddress());
			eventHubJson.put("addressline_3_addressTrue",fixedDepositData.getCommPincode()	== null ?	"NA" : fixedDepositData.getCommPincode());
			eventHubJson.put("other",fixedDepositData.getJourneyStepName()	== null ?	"NA" : fixedDepositData.getJourneyStepName());




			String eventHubJsonString = eventHubJson.toString();


			logger.info("========eventHub Json String==================="+eventHubJsonString);

			eventhubString = sfdcIntegration.eventHubApi(eventHubJsonString,stepNoData,customerId);
		}catch(Exception e)
		{
			logger.error("============Exception in custListRemoveUploadedDocumetsService==========", e);
			if(!(customerId==null && customerId.isEmpty())){
				dbManipulation.recordExeption("EVENT_HUB_SERVICE","Exception due to internal call", customerId);
			}
			Utility.loadNewRelicException(e.toString(), "eventHubService", "", customerId);
		}
		return eventhubString;
	}

	public String eventHubdataCall(String customerId, String stepNoData) {

		FixedDepositData fixedDepositData = fixedDepositDao.getFixedDepositData(customerId);

		String eventHubString = eventHubService(fixedDepositData, stepNoData);

		logger.info("===============event Hub String================="+eventHubString);

		return null;
	}


	@Override
	public String getSFDCStatus(FixedDepositData fixedDepositData) {

		String responseUpdateStatus = null;
		String finalSfdcDbStatus = null;
		try {

			String investmentType = fixedDepositData.getInvestmentType() == null ? "NA"
					: fixedDepositData.getInvestmentType();
			String transactionStatus = fixedDepositData.getTransactionStatus() == null ? "NA"
					: fixedDepositData.getTransactionStatus();

			logger.info("======get SFDCS tatus against getSFDCStatus is======"+transactionStatus);
			JSONObject sfdcJsonRes = new JSONObject();

			if ("FD".equalsIgnoreCase(investmentType)) {

				logger.info(" ===  FD SFDC Request ===");
				JSONObject ekycFdSfdcRes = ekycFdSfdcService(fixedDepositData,"");
				logger.info(" ==== ekycFdSfdcRes in paymentResponseService ====== " + ekycFdSfdcRes);
				sfdcJsonRes = ekycFdSfdcRes;

			} else if ("SDP".equalsIgnoreCase(investmentType)) {

				logger.info(" ===  SDP SFDC Request ===");
				JSONObject ekycSdpSfdcRes = ekycSdpSfdcService(fixedDepositData,"");
				logger.info(" ==== ekycSdpSfdcRes in paymentResponseService ====== " + ekycSdpSfdcRes);

				sfdcJsonRes = ekycSdpSfdcRes;
			}

			logger.info(" ==== sfdcJsonRes in paymentResponseService ====== " + sfdcJsonRes);

			String finalSfdcStatus = sfdcJsonRes.get("IsSuccess") == null ? "NA"
					: sfdcJsonRes.get("IsSuccess").toString();
			String finalSfdcMsg = sfdcJsonRes.get("Message") == null ? "NA" : sfdcJsonRes.get("Message").toString();
			String finalSfdcUniqueRecId = sfdcJsonRes.get("UniqueRecId") == null ? "NA"
					: sfdcJsonRes.get("UniqueRecId").toString();
			String finalSfdcId = sfdcJsonRes.get("FdSfdcId") == null ? "NA"
					: sfdcJsonRes.get("FdSfdcId").toString();

			fixedDepositData.setFinalSfdcStatus(finalSfdcStatus);
			fixedDepositData.setFinalSfdcMsg(finalSfdcMsg);
			fixedDepositData.setFinalSfdcUniqueRecId(finalSfdcUniqueRecId);
			fixedDepositData.setFinalSfdcId(finalSfdcId);
			fixedDepositData.setSchemeCode(schemeCode);

			finalSfdcDbStatus = fixedDepositDao.updateFixedDeposit(fixedDepositData);

			logger.info("=========finalSfdcDbStatus============" + finalSfdcDbStatus);

			finalSfdcDbStatus = fixedDepositDao.updateFixedDeposit(fixedDepositData);
			logger.info("=========finalSfdcDbStatus============" + finalSfdcDbStatus);


			responseUpdateStatus = sfdcJsonRes.toString();

		} catch (Exception e) {
			logger.error("------Exception in getRePaymentStatus--------", e);
		}
		return responseUpdateStatus;

	}

	@Override
	public String NSDLCallForPAN(String pancard,String customerId,String contextCalled) {


		String nsdlStatus="";
		JSONObject nsdlapiData = new JSONObject();
		try {
			byte[] encryptKey = "1234567890123456".getBytes();
			String iv = "dfghjuytrfgtyhuj";
			SecretKeySpec skeySpec = new SecretKeySpec(encryptKey, "AES");
			byte[] cipherText = kycEncryDecryp.encryptOp(pancard.getBytes(), skeySpec, iv.getBytes());
			String encriptPan = Base64.getEncoder().encodeToString(cipherText);
			String requesstPAN = encriptPan.replace('+', '~');
			byte[] cipherTextProduct = kycEncryDecryp.encryptOp("FDR".getBytes(), skeySpec, iv.getBytes());
			String encriptProduct = Base64.getEncoder().encodeToString(cipherTextProduct);
			String requesstProduct = encriptProduct.replace('+', '~');
			logger.info("=======requesstPAN=====" + requesstPAN + "=====requesstProduct====" + requesstProduct);
			String soapApiPANValidation = custIdIntegration.panVerification(requesstPAN, requesstProduct,customerId,contextCalled);
			logger.info("=========soapApiPANValidation=========" + soapApiPANValidation);
			String panVarifyName = "";
			String okycVerifydetails = "";
			String nsdlDOB = "";
			String nsdlPAN ="";

			if (!("PAN Not Found".equalsIgnoreCase(soapApiPANValidation))) {

				String decriptResponse = kycEncryDecryp.decryptOP(soapApiPANValidation.replace('~', '+'), encryptKey,
						iv);

				decriptResponse = decriptResponse.trim().replaceAll("(\\^)", "@");



				logger.info("========decriptResponse==========" + decriptResponse);
				if(! decriptResponse.contains("Not enough balance"))
				{

					String content = decriptResponse.substring(decriptResponse.indexOf('@') + 1);
					String content2 = content.substring(0, content.lastIndexOf('@') - 1);					
					String content3 = content2.substring(0, content2.lastIndexOf('@'));
					String content4 = content3.substring(0, content3.lastIndexOf('@'));
					panVarifyName = content4.substring(0, content4.lastIndexOf('@')).replace('@', ' ').replaceAll("( +)", " ").trim();
				    nsdlDOB= content4.substring(content4.lastIndexOf('@')).replace('@', ' ').replaceAll("( +)", " ").trim();
					nsdlPAN=decriptResponse.substring(0, decriptResponse.indexOf('@'));

					logger.info(":::::::::::::::P N verify:::::::::" + panVarifyName+"::::::::NSDL DO::::::::"+nsdlDOB+":::::NSDL PA:::::"+nsdlPAN);

					okycVerifydetails = content3.substring(content3.lastIndexOf('@')).replace('@', ' ').replaceAll("( +)", " ").trim();

					logger.info("========okycVerifydetails==========" + okycVerifydetails);
					nsdlapiData.put("decriptResponse", decriptResponse);

					if("Existing and Valid".equalsIgnoreCase(okycVerifydetails)){
						nsdlStatus="sucess";
						nsdlapiData.put("panVarifyName", panVarifyName);
						nsdlapiData.put("nsdlPAN", nsdlPAN);

					}else{
						nsdlStatus="notValidResponse";
					}
				}else
				{
					nsdlStatus="fail";
					nsdlapiData.put("decriptResponse", decriptResponse);
				}
			}else{
				nsdlStatus="fail";
			} 

			nsdlapiData.put("nsdlStatus", nsdlStatus);

		} catch (Exception e) {
			Utility.loadNewRelicException(e.toString(), "NSDLCallForPAN", "", customerId);
			logger.error("=======Exception in NSDL Call For PAN========",e);
			nsdlapiData.put("nsdlStatus", "fail");
			if(!(customerId==null && customerId.isEmpty())){
				dbManipulation.recordExeption("NSDL_SERVICE","Exception due to internal call", customerId);
			}
		}
		return nsdlapiData.toString();
	}

	@Override
	public String manualReflowDataUpdate(String jsonRequest)
	{
		JSONObject response=new JSONObject();
		JSONArray respData=new JSONArray();
		try
		{
			logger.info("=============manualReflowDataUpdate request=========== " + jsonRequest);
			JSONObject requestVal=new JSONObject(jsonRequest);
			JSONArray dataToUpdateArray = requestVal.has("dataToUpdateArray") ? requestVal.getJSONArray("dataToUpdateArray") : new JSONArray();

			if (!dataToUpdateArray.isEmpty() && dataToUpdateArray.length() > 0)
			{

				for (int i = 0; i < dataToUpdateArray.length(); i++) 
				{
					JSONObject updateStatus = new JSONObject();
					JSONObject validationStatus = new JSONObject();
					boolean flag = true;
					String customerId=null;
					try
					{
						JSONObject singleJsonRequestData = dataToUpdateArray.getJSONObject(i);
						customerId=singleJsonRequestData.has("FDCNumber")?singleJsonRequestData.get("FDCNumber").toString():"NA";
						logger.info("=============FDCNumber in manualReflowDataUpdate=========== " + customerId);
						if(!"NA".equalsIgnoreCase(customerId)&& !customerId.isEmpty() && customerId.equalsIgnoreCase(HtmlUtils.htmlEscape(customerId)))
						{
							FixedDepositData fixedDepositData = fixedDepositDao.getFixedDepositData(customerId);
							String reflowStatus=fixedDepositData.getManualReflowStatus()==null?Constants.STATUS_FAIL:fixedDepositData.getManualReflowStatus();
							logger.info("=============reflow Status in manualReflowDataUpdate=========== " + reflowStatus);
							if(!Constants.STATUS_SUCCESS.equalsIgnoreCase(reflowStatus))
							{
								@SuppressWarnings("unchecked")
								LinkedHashMap<String, Object> result = new ObjectMapper().readValue(singleJsonRequestData.toString(), LinkedHashMap.class);
								for (Entry<String, Object> results : result.entrySet())
								{

									String key = results.getKey();
									String value = (String) results.getValue();

									logger.info(" == Key === " + key + " == Value == " + value);

									if (key.equals("ifscCode")) 
									{
										if (!value.isEmpty())
										{
											if(value.matches("^[A-Z]{4}[0][A-Z0-9]{6}$"))
											{
												fixedDepositData.setIfscPrevious(fixedDepositData.getIfscCode());
												fixedDepositData.setIfscCode(value);
												logger.info("  == ifsc Updated Successfully === ");
											}else
											{
												flag = false;
												logger.error(key + "please enter Valid ifsc code");
												validationStatus.put(key, "please enter Valid ifsc code");
											}
										}

									}
									if (key.equals("bankName")) 
									{
										if (!value.isEmpty())
										{
											if(value.matches("^[-a-zA-Z0-9._]+"))
											{
												fixedDepositData.setBanknamePrevious(fixedDepositData.getBankName());
												fixedDepositData.setBankName(value);
												logger.info("  == bankName Updated Successfully === ");
											}else
											{
												flag = false;
												logger.error(key + "please enter Valid bankName");
												validationStatus.put(key, "please enter Valid bankName");
											}

										}
									}
									if (key.equals("formApNumber")) 
									{
										if (!value.isEmpty())
										{
											if(value.equalsIgnoreCase(HtmlUtils.htmlEscape(value)))
											{
												fixedDepositData.setAppFormNumberPrevious(fixedDepositData.getFormAppNumber());
												fixedDepositData.setFormAppNumber(value);
												logger.info("  == formApNumber Updated Successfully === ");
											}
											else
											{
												flag = false;
												logger.error(key + "please enter Valid formApNumber");
												validationStatus.put(key, "please enter Valid formApNumber");
											}

										}
									}
									if (key.equals("utrNumber")) 
									{
										if (!value.isEmpty())
										{
											if(value.equalsIgnoreCase(HtmlUtils.htmlEscape(value)))
											{
												fixedDepositData.setUtrPrevious(fixedDepositData.getUtrNumber());
												fixedDepositData.setUtrNumber(value);
												logger.info("  == utrNumber Updated Successfully === ");
											}else
											{
												flag = false;
												logger.error(key + "please enter Valid utrNumber");
												validationStatus.put(key, "please enter Valid utrNumber");

											}
										}
									}

									if (key.equals("NomineName")) 
									{
										if (!value.isEmpty())
										{
											if(value.equalsIgnoreCase(HtmlUtils.htmlEscape(value)))
											{
												fixedDepositData.setNomineeNamePrevious(fixedDepositData.getNomineeName());
												fixedDepositData.setNomineeName(value);
												logger.info("  == Nominee Name Updated Successfully === ");
											}else
											{
												flag = false;
												logger.error(key + "please enter Valid Nominee Name");
												validationStatus.put(key, "please enter Valid Nominee Name");

											}
										}
									}


									if (key.equals("BankAccountNumber")) 
									{
										if (!value.isEmpty())
										{
											if(value.equalsIgnoreCase(HtmlUtils.htmlEscape(value)))
											{
												fixedDepositData.setBanknamePrevious(fixedDepositData.getBankAccountNumber());
												fixedDepositData.setBankAccountNumber(value);
												logger.info("  == BankAccount Number Updated Successfully === ");
											}else
											{
												flag = false;
												logger.error(key + "please enter Valid BankAccount Number");
												validationStatus.put(key, "please enter Valid BankAccount Number");

											}
										}
									}



									if (key.equals("NomineeDateOfBirth")) 
									{
										if (!value.isEmpty())
										{
											if(value.equalsIgnoreCase(HtmlUtils.htmlEscape(value)))
											{
												fixedDepositData.setNomineeDateOfBirthPrevious(fixedDepositData.getNomineeDateOfBirth());
												Date nomdbo = new SimpleDateFormat("dd/MM/yyyy").parse(value);
												SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
												String nomineedob = format.format(nomdbo);
												fixedDepositData.setNomineeDateOfBirth(nomineedob);
												logger.info("  == Nominee Date Of Birth Updated Successfully === ");
											}else
											{
												flag = false;
												logger.error(key + "please enter Valid  Nominee Date Of Birth");
												validationStatus.put(key, "please enter Valid  Nominee Date Of Birth");

											}
										}
									}



									if (key.equals("nomineeRelation")) 
									{
										if (!value.isEmpty())
										{
											if(value.equalsIgnoreCase(HtmlUtils.htmlEscape(value)))
											{
												fixedDepositData.setRelationshipWithNomineePrevious(fixedDepositData.getRelationshipWithNominee());
												fixedDepositData.setRelationshipWithNominee(value);
												logger.info("  == Relationship With Nominee Updated Successfully === ");
											}else
											{
												flag = false;
												logger.error(key + "please enter Valid   Relationship With Nominee");
												validationStatus.put(key, "please enter Valid   Relationship With Nominee");

											}
										}
									}
								}

								logger.error("============Status of validation Flag before update========="+flag);
								if(flag)
								{
									fixedDepositData.setManualReflowStatus(Constants.STATUS_SUCCESS);
									String dataUpdateStatus = fixedDepositDao.updateFixedDeposit(fixedDepositData);
									logger.info(" === dataUpdateStatus in manualReflowDataUpdate ==== " + dataUpdateStatus);
									updateStatus.put(Constants.STATUS, Constants.STATUS_SUCCESS);
								}else
								{
									updateStatus.put("errorMessage",validationStatus );	
									updateStatus.put(Constants.STATUS, Constants.STATUS_FAIL);
								}
								updateStatus.put("FDCNumber",customerId );
								respData.put(updateStatus);


							}else
							{
								logger.error("==========manual Reflow already updated============");
								updateStatus.put(Constants.STATUS, Constants.STATUS_FAIL);
								updateStatus.put("FDCNumber",customerId );
								updateStatus.put("Reflow Status","Only One time allowd to update data for Relow cases" );
								respData.put(updateStatus);

							}

						}else
						{
							logger.error("=========costomer id not matched===========");
							updateStatus.put(Constants.STATUS, Constants.STATUS_FAIL);
							updateStatus.put("FDCNumber","FDCNumber is Mandatory" );
							respData.put(updateStatus);

						}
					}
					catch(Exception e)
					{
						logger.error("=======Exception in manualReflowDataUpdate Array Loop=======",e);	
						updateStatus.put(Constants.STATUS, Constants.STATUS_FAIL);
						updateStatus.put("FDCNumber",customerId );
						respData.put(updateStatus);

					}
				}



			}else
			{
				response.put(Constants.STATUS , Constants.STATUS_FAIL);	
			}
			response.put(Constants.MESSAGE, respData);

		}
		catch(Exception e)
		{
			logger.error("=======Exception in manualReflowDataUpdate=======",e);
			response.put(Constants.MESSAGE,  e.getMessage());
			response.put(Constants.STATUS , Constants.STATUS_FAIL);
		}
		logger.info("========responseVal in manualReflowDataUpdate==========="+response);
		return response.toString();
	}


	@Override
	public String getDecryptedRequest(String request)
	{
		String decryptedRequest="";
		try
		{
			String SecretKey=Utility.getPropertyFileValue("ssoSecreteKey");
			String ivKey=Utility.getPropertyFileValue("ssoIvkey");
			decryptedRequest=kycEncryDecryp.decryptAES(request, SecretKey, ivKey);
		}
		catch(Exception e)
		{
			logger.error("----Exception while decryption-----",e);
			decryptedRequest="";
		}
		return decryptedRequest;
	}

	@Override
	public  String getEncryptedRequest(String request)
	{
		String encryptedRequest="";
		try
		{
			String SecretKey=Utility.getPropertyFileValue("ssoSecreteKey");
			String ivKey=Utility.getPropertyFileValue("ssoIvkey");
			encryptedRequest=kycEncryDecryp.encryptAES(request, SecretKey, ivKey);
		}
		catch(Exception e)
		{
			logger.error("----Exception while encrypted-----",e);
			encryptedRequest="";
		}
		return encryptedRequest;
	}


	@Override
	public JSONObject createSingleSignOn(SingleSignOn t,HttpServletRequest request) 
	{
		JSONObject responseVal = new JSONObject();
		try
		{
			logger.info(" === createSingleSignOn request  ===== " + t);
			JsonResponse ValidationResult=doValidation(t);
			String customerId = "";
			if(Constants.STATUS_SUCCESS.equalsIgnoreCase(ValidationResult.getStatus()))
			{
				String mobileNumber=t.getMobileNumber()!=null?t.getMobileNumber():Constants.STATUS_NA;
				String dateOfBirth=t.getDob()!=null?t.getDob():Constants.STATUS_NA;
				String demogApiResponse=custIdIntegration.customerCheckEtbNtb(mobileNumber, dateOfBirth, "",Constants.CREATESINGLESIGNON);
				logger.info(" === demogApiResponse in createSingleSignOn ===== " + demogApiResponse);

				JSONObject demogResponseVal = new JSONObject(demogApiResponse);
				String dedupeCustType = demogResponseVal.get(Constants.DEDUPE_CUST_TYPE) == null ?"NTB":demogResponseVal.get(Constants.DEDUPE_CUST_TYPE).toString();
				logger.info(" === dedupeCustType in createSingleSignOn ===== " + dedupeCustType);


				if(Constants.DEDUPE_ETB.equalsIgnoreCase(dedupeCustType))
				{
					String customerIdFdproduct=	demogResponseVal.has(Constants.CUSTOMERID) ?demogResponseVal.get(Constants.CUSTOMERID).toString():"NA";
					logger.info(" === dedupeResponse in customerId Fd product createSingleSignOn===== " + customerIdFdproduct);

					String addressVal =custIdIntegration.getDataToPrefill(mobileNumber, customerIdFdproduct,"",Constants.CREATESINGLESIGNON);
					logger.info(" == address Response in otpValidatorService === " + addressVal);
					JSONObject addressJson = new JSONObject(addressVal);
					String addressPrefillStatus = addressJson.has("PIDATAAPI") ? addressJson.get("PIDATAAPI").toString() : "fail";
					String address = "success".equalsIgnoreCase(addressPrefillStatus) ? addressJson.get(Constants.ADDRESS_1).toString() : "";

					FixedDepositData fixedDepositData = new FixedDepositData();



					JSONObject prefillResponse=demogResponseVal.getJSONObject("userDetails");

					String fullName =prefillResponse.has(Constants.FULL_NAME) ? prefillResponse.get(Constants.FULL_NAME).toString():"NA" ;
					String gender= prefillResponse.has("gender") ? prefillResponse.get("gender").toString():"NA";
					String city = prefillResponse.has(Constants.CITY) ?  prefillResponse.get(Constants.CITY).toString() :"NA";
					String pincode = prefillResponse.has(Constants.PIN)  ? prefillResponse.get(Constants.PIN).toString():"NA";
					String state="";
					String panCard=prefillResponse.has(Constants.PAN) ? prefillResponse.get(Constants.PAN).toString():"NA";   
					String email=prefillResponse.has(Constants.EMAIL)? prefillResponse.get(Constants.EMAIL).toString():"NA";

					int reqPin;
					if(!("NA".equalsIgnoreCase(pincode)))
					{

						reqPin = Integer.parseInt(pincode);
						FDPincodeMaster fdPiMast = fixedDepositDao.getcitystatename(reqPin);

						if(fdPiMast != null){
							city = fdPiMast.getCity();
							state = fdPiMast.getState();

						}else{
							city ="NA";
							state = "NA";
						}
					}

					if("FEMALE".equalsIgnoreCase(gender)){
						gender = "F";
					}else{
						gender = "M";
					}




					synchronized(this)
					{
						customerId = fixedDepositDao.customerIdGenerator();
						logger.info(" === Generator Customer Id in saveSingleSignOnData === " + customerId);

					}
					fixedDepositData.setCustomerId(customerId);
					fixedDepositData.setCity(city);
					fixedDepositData.setPinCode(pincode);
					fixedDepositData.setState(state);
					fixedDepositData.setGender(gender);
					fixedDepositData.setFullName(fullName);
					fixedDepositData.setPanCard(panCard);
					fixedDepositData.setEmailAddress(email);
					fixedDepositData.setMobileNumber(t.getMobileNumber());
					fixedDepositData.setDateOfBirth(t.getDob());
					fixedDepositData.setKycVerifyStatus("SSODEDUPE_SUCCESS"); 
					fixedDepositData.setInvestmentType("FD");
					fixedDepositData.setExistingCustId(customerIdFdproduct);
					String otpTriggeredTime = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(Calendar.getInstance().getTime()) ;
					fixedDepositData.setOtpTriggeredTime(otpTriggeredTime);
					String fromSubmissionDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime()) ;
					fixedDepositData.setCreatedOn(fromSubmissionDate);
					fixedDepositData.setDedupeCustomerType(Constants.DEDUPE_ETB);
					fixedDepositData.setRemarkCustType("SSODEDUPE");
					logger.info(" === before saving data ==== " + fixedDepositData.toString());

					String  token=UUID.randomUUID().toString();
					fixedDepositData.setSsoToken(token);
					String ssoCreatedOn = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime()) ;
					fixedDepositData.setSsoCreatedOn(ssoCreatedOn);
					fixedDepositData.setAddress(address);
					
					String remoteAddr = request.getHeader("True-Client-IP");
					logger.info("---- Request remoteAddr step 1 -- " + remoteAddr);
					if (remoteAddr == null || "".equals(remoteAddr)){
						remoteAddr="NA";	
					}
					logger.info("---- Request remoteAddr step 1 Condition check -- " + remoteAddr);
					fixedDepositData.setIpAdress(remoteAddr);
					String dbSaveStatus = fixedDepositDao.saveFixedDepositData(fixedDepositData);
					logger.info(" === dbSaveStatus in createSingleSignOn == " + dbSaveStatus);

					String tokenParam="&sessionId="+token;
					String encryptedData="source=experialogin&appId="+customerId;
					logger.info(" ===encryptedData in saveSingleSignOnData === " + encryptedData+"===Token==="+tokenParam);
					encryptedData=getEncryptedRequest(encryptedData);
					String propUrl=Utility.getPropertyFileValue("ssoReturnURL");
					logger.info(" ===propUrl in createSingleSignOn == " + propUrl);
					String returnUrl=propUrl+encryptedData+tokenParam+"&return=ssoleads";
					logger.info(" ===returnUrl in saveSingleSignOnData === " + returnUrl);
					
					/**SSO to partial Sfdc***/
					logger.info(" === createSingleSignOn custId === " + customerId);
					FixedDepositData depositDataNew = fixedDepositDao.getFixedDepositData(customerId);
					String fullnamesfdc= depositDataNew.getFdslfFullName() == null ? "Dummy Dummy" : depositDataNew.getFdslfFullName().toString();
					String utmSource1 = fixedDepositData.getUtmSource() == null ? "NA" : fixedDepositData.getUtmSource();
					boolean utmSourcesCheck = Utility.getUtmSources(utmSource1);
					logger.info("=====fullnamesfdc======="+fullnamesfdc + "=====utmSource1======="+utmSource1+"=========utmSourcesCheck========"+utmSourcesCheck);
					if(utmSourcesCheck){
						Runnable runnable = () -> {

							try {
								logger.info("=====inside thread Started=======" + LocalDateTime.now().toString() + "============");

								TimeUnit.MINUTES.sleep(20);
								logger.info("============Thread date for partial SFDC to check=====" + depositDataNew);
								logger.info("====thread Processing===" + LocalDateTime.now().toString()+ "==========================");
								FixedDepositData depositDataUpdated = fixedDepositDao.getFixedDepositData(depositDataNew.getCustomerId());
								JSONObject partialSfdcResponse = otpServiceImpl.partialSfdcService(depositDataUpdated,fullnamesfdc,"STEP1",Constants.CREATESINGLESIGNON);
								logger.info("=========SFDC Response in createSingleSignOn========="+ partialSfdcResponse.toString());
								String sfdcStatus = partialSfdcResponse.get("IsSuccess") == null ? "fail" : partialSfdcResponse.get("IsSuccess").toString();
								String sfdcRecordId	= partialSfdcResponse.get("FdSfdcId") == null ? "fail" : partialSfdcResponse.get("FdSfdcId").toString();
								logger.info(" === createSingleSignOn Partial SFDC Status === " + sfdcStatus + " === createSingleSignOn Partial SFDC Id ==== " + sfdcRecordId);

								depositDataUpdated.setSfdcRemarks(sfdcStatus);
								depositDataUpdated.setSfdcRecordId(sfdcRecordId);
								String updatedStatus = fixedDepositDao.updateFixedDeposit(depositDataUpdated);
								logger.info(" == Updation status in createSingleSignOn === " + updatedStatus);

							} catch (Exception e) {
								logger.error(" ==== exception in 20 min wait for partial SFDC ==== ", e);
							}
						};                       new Thread(runnable).start();
				
					}
					
					/**Return Response Values**/
					responseVal.put(Constants.RETURNURL,returnUrl);
					responseVal.put(Constants.TOKEN,token);
					responseVal.put(Constants.STATUS, Constants.STATUS_SUCCESS);
					responseVal.put(Constants.STATUS_CODE, Constants.SSOSTATUSCODE_00);
				}else
				{
					String  token=UUID.randomUUID().toString();
					String encryptedData="source=experialogin&appId=blank";
					encryptedData=getEncryptedRequest(encryptedData);
					String tokenParam="&sessionId="+token;
					String propUrl=Utility.getPropertyFileValue("ssoReturnURL");
					logger.info(" ===propUrl in createSingleSignOn == " + propUrl);
					String returnUrl=propUrl+encryptedData+tokenParam;
					logger.info(" ===returnUrl in saveSingleSignOnData === " + returnUrl);
					/**Return Response Values**/
					responseVal.put(Constants.RETURNURL,returnUrl);
					responseVal.put(Constants.TOKEN,token);
					responseVal.put(Constants.STATUS_CODE, Constants.SSOSTATUSCODE_92);
					responseVal.put(Constants.STATUS,Constants.STATUS_SUCCESS);
					logger.info(" === dedupeCustType NTB in createSingleSignOn ===== " + dedupeCustType);
				}

			}else
			{
				responseVal.put("validation_Status", ValidationResult);
				responseVal.put(Constants.STATUS_CODE, Constants.SSOSTATUSCODE_96);
				responseVal.put(Constants.STATUS, Constants.STATUS_SUCCESS);
				Utility.loadNewRelicValidation("Field validation fail in createSingleSignOn "+" "+ValidationResult, "field validation fail in createSingleSignOn", "",Constants.CREATESINGLESIGNON);
			}
		}
		catch(Exception e)
		{
			logger.error("----Exception while createSingleSignOn-----",e);
			responseVal.put(Constants.STATUS_CODE, Constants.SSOSTATUSCODE_91);
			responseVal.put(Constants.STATUS, Constants.STATUS_FAIL);
			responseVal.put(Constants.API_MESSAGE, Constants.RETRIVE_EXCEPTION);
			Utility.loadNewRelicException(e.toString(), "createSingleSignOn service", "", "");
		}
		return responseVal;
	}


	@Override
	public JsonResponse doValidation(SingleSignOn t) {
		logger.info("-----SSO Validation ----");
		JsonResponse jsonResponse=new JsonResponse();
		JSONObject validationResult = new JSONObject();

		try
		{ 
			boolean mobileValidity = validation.mobileNumberValidation(t.getMobileNumber());
			boolean dateValidation = validation.dateValidation(t.getDob());
			boolean isPincodeValid = validation.pinCodeValidation(t.getPincode());
			boolean isSourceValid=validation.textValidation(t.getSourceName());
			if(!mobileValidity){
				validationResult.put("Mobile_Number","Please Enter Your Mobile Number"); 
			} 

			if(!dateValidation){
				validationResult.put("dateofbirth","Please Enter valid date of birth"); 
			}

			if (!isPincodeValid) {
				validationResult.put("pincode", "Please Enter valid Pincode");
			}
			if (!isSourceValid) {
				validationResult.put("Source Name", "Please Enter valid Source name");
			}


			if (mobileValidity  && dateValidation && isPincodeValid && isSourceValid)  
			{ 
				jsonResponse.setStatus(Constants.STATUS_SUCCESS);
				logger.info(" ----- Validation Success ---- \t"+t.getMobileNumber());
			} else {
				jsonResponse.setStatus(Constants.STATUS_FAIL);
				jsonResponse.setResult(validationResult);
				logger.info(" ----- Validation Fail ---- \t"+"\n "+jsonResponse);
			}
		} 
		catch(Exception ex)
		{
			logger.error("---- EXception in Validation ---",ex);
			jsonResponse.setStatus(Constants.STATUS_FAIL);

		}
		return jsonResponse; 
	}





	@Override
	public JSONObject singleSignOnGetData(String request,HttpSession httpSession,HttpServletRequest htRequest)
	{
		JSONObject response = new JSONObject();
		JSONObject userDetails = new JSONObject();
		String requestCustId = "";
		logger.info(" === request in singleSignOnGetData ==== " + request);
		try
		{
			JSONObject requestJson = new JSONObject(request);
			requestCustId = requestJson.has("fdcNumber")?requestJson.get("fdcNumber").toString():"";
			String tokenValue= requestJson.has("token")?requestJson.get("token").toString():"";
			String pageURL= requestJson.has("pageURL")?requestJson.get("pageURL").toString():"";
			String formName= requestJson.has("formName")?requestJson.get("formName").toString():"";
			String formID= requestJson.has("formID")?requestJson.get("formID").toString():"";
			String device= requestJson.has("device")?requestJson.get("device").toString():"";
			String ipAddress= requestJson.has("ipAdress")?requestJson.get("ipAdress").toString():"";
			String timeOfLogging = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(Calendar.getInstance().getTime()) ;
			String utmSource = requestJson.has("utm_source") ?requestJson.get("utm_source").toString() : "experia";
			String utmMedium = requestJson.has("utm_medium") ? requestJson.get("utm_medium").toString() : "";
			String utmCampaign = requestJson.has("utm_campaign") ? requestJson.get("utm_campaign").toString() : "";


			if(tokenValue !=null && tokenValue.equalsIgnoreCase(HtmlUtils.htmlEscape(tokenValue)))
			{
				if(requestCustId !=null && requestCustId.equalsIgnoreCase(HtmlUtils.htmlEscape(requestCustId)))
				{
					FixedDepositData depositData = fixedDepositDao.getFixedDepositData(requestCustId);
					logger.info(" ==== depositData in singleSignOnGetData ==== " + depositData);
					if(depositData!=null)
					{
						if(!"True".equalsIgnoreCase(depositData.getSsoReturnFlag()))
						{

							DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
							LocalDateTime now = LocalDateTime.now();
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
							LocalDateTime dateTime2= LocalDateTime.parse(dtf.format(now), formatter);
							LocalDateTime dateTime1= LocalDateTime.parse(depositData.getSsoCreatedOn(), formatter);
							long diffInMinutes = java.time.Duration.between(dateTime1, dateTime2).toMinutes();
							logger.info(" ====Current time singleSignOnGetData ==== " + dtf.format(now));
							logger.info(" ====time taken to return singleSignOnGetData ==== " + diffInMinutes);
							if(depositData.getSsoToken().equalsIgnoreCase(tokenValue))
							{
								if(diffInMinutes<10)
								{
									userDetails.put(Constants.FULL_NAME,depositData.getFullName()==null?depositData.getNsdlFullName()==null?"NA":depositData.getNsdlFullName():depositData.getFullName());
									userDetails.put(Constants.USER_MOBILE_NUMBER, depositData.getMobileNumber());
									userDetails.put(Constants.EMAIL,depositData.getEmailAddress()==null?"NA":depositData.getEmailAddress());
									userDetails.put(Constants.DATE_OF_BIRTH, depositData.getDateOfBirth()==null?"NA":depositData.getDateOfBirth());
									userDetails.put(Constants.PIN,depositData.getPinCode()==null?"NA":depositData.getPinCode());
									userDetails.put(Constants.PAN, depositData.getPanCard()==null?"NA":depositData.getPanCard());
									userDetails.put("gender", depositData.getGender()==null?"NA":depositData.getGender());
									userDetails.put(Constants.ADDRESS,depositData.getAddress()==null?"NA":depositData.getAddress());
									userDetails.put(Constants.EXISTING_CUST_ID, depositData.getExistingCustId()==null?"NA":depositData.getExistingCustId());
									String remarkcustType=depositData.getRemarkCustType()==null?"NA":depositData.getRemarkCustType();
									userDetails.put(Constants.INVESTMENT_TYPE, depositData.getInvestmentType()==null?"":depositData.getInvestmentType());
									userDetails.put(Constants.CUSTOMER_ID, depositData.getCustomerId());
									String address=depositData.getAddress()==null?"NA":depositData.getAddress();
									userDetails.put(Constants.ADDRESS,address.replaceAll(",null", ""));
									userDetails.put(Constants.EMAIL,depositData.getEmailAddress()==null?"NA":depositData.getEmailAddress());
									SimpleDateFormat formatterDob = new SimpleDateFormat("dd/MM/yyyy");
									SimpleDateFormat formatter1DDob = new SimpleDateFormat("dd-MM-yyyy");
									Date dateDOB = formatterDob.parse(depositData.getDateOfBirth()==null?"NA":depositData.getDateOfBirth());

									logger.info(formatterDob.format(dateDOB));

									String dobFormatednew=formatter1DDob.format(dateDOB);
									LocalDate currentDate = LocalDate.now();
									LocalDate birthDateFromBrowser = LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(dobFormatednew))) ;
									int ageFromBrowser=Period.between(birthDateFromBrowser, currentDate).getYears();

									if(ageFromBrowser >= 60){
										userDetails.put(Constants.CUST_TYPE, Constants.DEDUPE_STB);
									}else{
										userDetails.put(Constants.CUST_TYPE,  "SSODEDUPE");
									}


									httpSession.setAttribute(Constants.USER_MOBILE_NUMBER, depositData.getMobileNumber());
									httpSession.setAttribute(Constants.DATE_OF_BIRTH,  depositData.getDateOfBirth());
									httpSession.setAttribute(Constants.FULL_NAME, depositData.getFullName());
									httpSession.setAttribute("customerId", depositData.getCustomerId());

									response.put("UserDetails", userDetails);
									response.put(Constants.STATUS, Constants.STATUS_SUCCESS);	

									depositData.setSsoReturnFlag("True");
									depositData.setDevice(device);
									depositData.setDeviceOriginal(device);
									depositData.setTimeOfLogging(timeOfLogging);
									depositData.setFormName(formName);
									depositData.setFormId(formID);
									depositData.setPageUrl(pageURL);
									depositData.setPartnerCode("NA");
									depositData.setPartnerName("NA");
									depositData.setUtmSource(utmSource);
									depositData.setUtmMedium(utmMedium);
									depositData.setUtmCampaign(utmCampaign);

									String otpSubmittedTime = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(Calendar.getInstance().getTime()) ;
									depositData.setOtpSubmittedTime(otpSubmittedTime);

									String remoteAddr = htRequest.getHeader("True-Client-IP");
									logger.info("---- Request remoteAddr step 2 -- " + remoteAddr);
									if (remoteAddr == null || "".equals(remoteAddr)){
										remoteAddr="NA";	
									}
									logger.info("---- Request remoteAddr step 2 Condition check -- " + remoteAddr);
									depositData.setIpAdress(remoteAddr);
									
									depositData.setJourneyStepName("SINGLESIGNON");
									String updateStatus = fixedDepositDao.updateFixedDeposit(depositData);
									logger.info(" == Updation status in SingleSignOn === " + updateStatus);
								}else
								{
									response.put(Constants.STATUS, Constants.STATUS_FAIL);
									response.put(Constants.API_STATUS, "tokentimeExceeded");
									response.put(Constants.API_MESSAGE, "Token time exceeded.");
									Utility.loadNewRelicValidation("Token time exceeded in singleSignOnGetData ", "Token time exceeded in singleSignOnGetData", requestCustId,Constants.SINGLESIGNON);
								}
							}else
							{
								response.put(Constants.STATUS, Constants.STATUS_FAIL);
								response.put(Constants.API_STATUS, "tokenValidation");
								response.put(Constants.API_MESSAGE, "Please Enter valid token.");
							}
						}else
						{
							response.put(Constants.STATUS, Constants.STATUS_FAIL);
							response.put(Constants.API_STATUS, "fetchOncessoDone");
							response.put(Constants.API_MESSAGE, "You can access data only once.Please try fresh application");
						}
					}else
					{
						response.put(Constants.STATUS, Constants.STATUS_FAIL);
						response.put(Constants.API_MESSAGE, "Data Not present for Request");
						response.put(Constants.API_STATUS, "RequestIdNotmatched");
					}
				}
				else
				{
					response.put(Constants.STATUS, Constants.STATUS_FAIL);
					response.put(Constants.API_MESSAGE, "Please Enter request Id.");
					response.put(Constants.API_STATUS, "norequestId");
					Utility.loadNewRelicValidation("invalid customerId in singleSignOnGetData ", "invalid customerId in singleSignOnGetData", requestCustId,Constants.SINGLESIGNON);
				}
			}
			else
			{
				response.put(Constants.STATUS, Constants.STATUS_FAIL);
				response.put(Constants.API_STATUS, "tokenValidation");
				response.put(Constants.API_MESSAGE, "Please Enter valid token.");
				Utility.loadNewRelicValidation("token validation fail in singleSignOnGetData ", "token validation fail in singleSignOnGetData", requestCustId,Constants.SINGLESIGNON);
			}
		}catch(Exception e)
		{
			logger.error("---- EXception in singleSignOnGetData ---",e);
			response.put(Constants.STATUS, Constants.STATUS_FAIL);
			response.put(Constants.API_MESSAGE, "Exception");
			response.put(Constants.API_STATUS, "Exception");
			Utility.loadNewRelicException(e.toString(), "singleSignOnGetData", "", requestCustId);
		}
		return response;
	}





	@Override
	public JSONObject ManulAuditTrailExecute(String customerIdJsonArray) 
	{

		JSONObject response =new JSONObject();
		JSONArray successResponse = new JSONArray();
		JSONArray failResponse = new JSONArray();
		int successCount=0;
		int FailCount=0;
		try {
			logger.info("========inside ManulAuditTrailExecute service=========="+customerIdJsonArray);
			String[] AppformArray = customerIdJsonArray.split(",");
			for (int i = 0; i < AppformArray.length; i++)
			{
				logger.info("=======formAppNo in =ManulAuditTrailExecute======"+AppformArray[i]);
				String formAppNo=AppformArray[i];
				FixedDepositData fixedDepositData=fixedDepositDao.getFixedDepositDataByAppNumber(formAppNo);
				logger.info("=======fixedDepositData in =ManulAuditTrailExecute======"+fixedDepositData);
				if(fixedDepositData!=null)
				{
					String customerId=fixedDepositData.getCustomerId()==null?"NA":fixedDepositData.getCustomerId();
					String finalSfdcId = fixedDepositData.getFinalSfdcId()==null ? "NA" : fixedDepositData.getFinalSfdcId();

					logger.info("========customerId in ManulAuditTrailExecute======"+customerId);
					if(fixedDepositData.getFinalSfdcId()!=null)
					{

						Boolean flag=docUPloadjsonManipulation(customerId,fixedDepositData,finalSfdcId);
						logger.info("===Document upload API status=="+flag);
						if(Boolean.TRUE.equals(flag) )
						{
							successCount++;
							successResponse.put(formAppNo);

						}else{
							FailCount++;
							failResponse.put(formAppNo);

						}


					}else
					{
						FailCount++;
						failResponse.put(formAppNo);

					}
				}else
				{
					FailCount++;
					failResponse.put(formAppNo);
				}

			}

			response.put("success Form App Numbers", successResponse);
			response.put("Fail Form App Numbers", failResponse);
			response.put("successCount", successCount);
			response.put("FailCount", FailCount);
			logger.info("========ManulAuditTrailExecute======"+response);
		} catch (Exception e) {
			response.put("success Form App Numbers", successResponse);
			response.put("Fail Form App Numbers", failResponse);
			response.put("successCount", successCount);
			response.put("FailCount", FailCount);
			logger.error(" ===== Exception in ManulAuditTrailExecute === ", e);
		}
		logger.info("========ManulAuditTrailExecute======"+response);
		return response;
	}



	@Override
	public JSONObject manualAuditTrailService(MultipartFile file){

		ArrayList<String> appFormNumbersFromFile = new ArrayList<>();
		JSONObject finalResponse = new JSONObject();
		JSONArray successAppNo = new JSONArray();
		JSONArray failedAppNo = new JSONArray();


		String fileExtn = Utility.getFileExtension(file.getOriginalFilename());
		logger.info("=================File  fileExtn===============" + fileExtn);

		if(fileExtn.equalsIgnoreCase("csv")){

			try(Scanner sc = new Scanner(file.getInputStream()).useDelimiter(",");){
				while(sc.hasNext()){
					String formAppNo = sc.next();
					appFormNumbersFromFile.add(formAppNo);

					if(!formAppNo.trim().equals("") && !formAppNo.equalsIgnoreCase("null") && formAppNo.equalsIgnoreCase(HtmlUtils.htmlEscape(formAppNo))){

						FixedDepositData depositData = fixedDepositDao.getFixedDepositDataByAppNumber(formAppNo);

						if(depositData != null){
							String finalSfdcId = depositData.getFinalSfdcId()==null ? "NA" : depositData.getFinalSfdcId();
							String customerId = depositData.getCustomerId()==null ? "" : depositData.getCustomerId();
							logger.info("===== formAppNo ====="+formAppNo+"=== customerId ====="+customerId);

							if(!"NA".equalsIgnoreCase(finalSfdcId)){

								Boolean flag=docUPloadjsonManipulation(customerId,depositData,finalSfdcId);
								logger.info("===Document upload API status=="+flag);
								if(Boolean.TRUE.equals(flag))
								{
									successAppNo.put(formAppNo);

								}else{
									failedAppNo.put(formAppNo);

								}
							}
							else{
								failedAppNo.put(formAppNo);
							}
						}
						else{
							failedAppNo.put(formAppNo);
						}

					}
					else {
						logger.info("============== Invalid formAppNumber ================");
						failedAppNo.put(formAppNo);
					}
					
				}
				logger.info("===== appFormNumbersFromFile ====="+appFormNumbersFromFile);

				finalResponse.put("SuccessAppNumbers", successAppNo);
				finalResponse.put("SucceesCount", successAppNo.length());
				finalResponse.put("FailAppNumbers", failedAppNo);
				finalResponse.put("FailCount", failedAppNo.length());
			}
			catch(IOException e){
				logger.info("Exception in manualAuditTrailService "+e);
				finalResponse.put("SuccessAppNumbers", successAppNo);
				finalResponse.put("SucceesCount", successAppNo.length());
				finalResponse.put("FailAppNumbers", failedAppNo);
				finalResponse.put("FailCount", failedAppNo.length());
			}
		}
		else{
			logger.info("=== Invalid file extension ===");
			finalResponse.put("Format", "InvalidFormat");
		}
		logger.info("======= manualAuditTrailService finalResponse ======="+finalResponse);
		return finalResponse;
	}


	public Boolean docUPloadjsonManipulation(String customerId,FixedDepositData depositData,String finalSfdcId )
	{

		try
		{

			JSONObject auditTrailPdfJson = dataDownloadService.fdAuditTrailPdf(customerId);
			String auditTrailFileEncode = auditTrailPdfJson.has("fileEncode") ? auditTrailPdfJson.get("fileEncode").toString() : "";
			String auditTrailFileName = auditTrailPdfJson.has("fileName") ? auditTrailPdfJson.get("fileName").toString() : "";
			String auditTrailFileNameDocType = "";

			if(auditTrailFileEncode.isEmpty()){
				auditTrailFileName = "";
				auditTrailFileNameDocType="";
			}else{
				auditTrailFileNameDocType = auditTrailFileName.substring(0, auditTrailFileName.lastIndexOf('.')).replace('.', ' ').replaceAll("( +)"," ").trim();
			}

			JSONObject ntbDocumentUploadSFDCJson = new JSONObject();

			ntbDocumentUploadSFDCJson.put("ParentId", finalSfdcId);
			ntbDocumentUploadSFDCJson.put("Name", auditTrailFileName);
			ntbDocumentUploadSFDCJson.put("body", auditTrailFileEncode);
			ntbDocumentUploadSFDCJson.put("Description", auditTrailFileNameDocType);
			logger.info("==========ntbDocumentUploadSFDCJson in manualAuditTrailService=========="+ntbDocumentUploadSFDCJson);
			JSONObject sfdcRes = sfdcIntegration.ntbDocumentUploadSFDC(ntbDocumentUploadSFDCJson.toString(),customerId,"manualAudit");
			logger.info("============sfdcRes============"+sfdcRes);

			String documentUploadId = sfdcRes.get("documentUploadId") == null ? "NA" : sfdcRes.get("documentUploadId").toString();
			String docapiStatus = sfdcRes.get("docapiStatus") == null ? "NA" : sfdcRes.get("docapiStatus").toString();

			depositData.setManualAuditDocUploadId(documentUploadId);
			depositData.setManualDocUploadApiRes(docapiStatus);
			String updateStatus = fixedDepositDao.updateFixedDeposit(depositData);
			logger.info("=======DB updateStatus for manualAuditTrailService ========"+updateStatus);
			if(!docapiStatus.equalsIgnoreCase("fail"))
			{return true;}
			else{
				return false;}


		}catch(Exception e)
		{
			logger.error(" ===== Exception in docUPloadjsonManipulation === ", e);
			return false;
		}

	}

	@Override
	public String ManualAuditTrailExecuteSingleCase(String request) 
	{

		JSONObject response =new JSONObject();
		JSONArray respData=new JSONArray();

		try
		{
			logger.info("=============manualReflowDataUpdate request=========== " + request);
			JSONObject requestVal=new JSONObject(request);
			JSONArray dataToUpdateArray = requestVal.has("dataForAuditTrail") ? requestVal.getJSONArray("dataForAuditTrail") : new JSONArray();

			if (!dataToUpdateArray.isEmpty() && dataToUpdateArray.length() > 0)
			{

				for (int i = 0; i < dataToUpdateArray.length(); i++) 
				{

					JSONObject requestJson = dataToUpdateArray.getJSONObject(i);
					String formAppNUmber = requestJson.has("formAppNUmber") ?requestJson.get("formAppNUmber").toString():"NA";
					String finalSfdcId = requestJson.has("finalSfdcId") ?requestJson.get("finalSfdcId").toString():"NA";

					if(!("NA".equalsIgnoreCase(formAppNUmber) &&  "NA".equalsIgnoreCase(finalSfdcId)))
					{
						FixedDepositData depositData = fixedDepositDao.getFixedDepositDataByAppNumber(formAppNUmber);
						logger.info("=======fixedDepositData in =ManulAuditTrailExecute======"+depositData);
						if(depositData!=null)
						{
							String customerId = depositData.getCustomerId()==null ? "" : depositData.getCustomerId();
							logger.info("========finalSfdcId======"+finalSfdcId+"=======formAppNo====="+formAppNUmber+"=== customerId ====="+customerId);
							Boolean flag=docUPloadjsonManipulation(customerId,depositData,finalSfdcId);
							logger.info("===Document upload API status=="+flag);

							if(Boolean.TRUE.equals(flag))
							{
								response.put("formAppNumber", formAppNUmber);
								response.put(Constants.STATUS,Constants.STATUS_SUCCESS);
								respData.put(response);

							}else
							{
								response.put("formAppNumber", formAppNUmber);
								response.put(Constants.STATUS,Constants.STATUS_FAIL);
								respData.put(response);

							}

						}else
						{
							response.put("formAppNumber", formAppNUmber);
							response.put(Constants.STATUS,Constants.STATUS_FAIL);
							respData.put(response);
						}

					}else
					{
						response.put("formAppNumber", formAppNUmber);
						response.put(Constants.STATUS,Constants.STATUS_FAIL);
						respData.put(response);
					}

				}


			}


		}
		catch(Exception e)
		{
			response.put(Constants.STATUS,Constants.STATUS_FAIL);
			respData.put(response);
			logger.error(" ===== Exception in ManulAuditTrailExecuteSingleCase === ", e);

		}

		return respData.toString();
	}

	@Override
	public String BankNamePreSelectDedupe(String ifscBankName) {
		logger.info(" ===== BankNamePreSelectDedupe === "+ ifscBankName);
		String MultipleBankNameValue = " ";
		switch(ifscBankName){
		case "AXIS BANK":
			ifscBankName="Axis Bank";
			break;
		case "HDFC BANK":
		    ifscBankName="HDFC Bank";
			break;
		case "ICICI BANK LIMITED":
		    ifscBankName="ICICI Bank";
			break;
		case "KOTAK MAHINDRA BANK LIMITED":
		    ifscBankName="162";
			break;
		case "STATE BANK OF INDIA":
		    ifscBankName="State Bank of India";
			break;
		case "ABHYUDAYA COOPERATIVE BANK LIMITED":
		    ifscBankName="ABHYUDAYA COOPERATIVE BANK LIMITED";
			break;
		case "AHMEDABAD MERCANTILE COOPERATIVE BANK":
		    ifscBankName="AHMEDABAD MERCANTILE COOPERATIVE BANK";
			break;
		case "AHMEDNAGAR MERCHANTS CO-OP BANK LTD":
		    ifscBankName="AHMEDNAGAR MERCHANTS CO-OP BANK LTD";
			break;
		case "ALLAHABAD BANK":
		    ifscBankName="ALB";
			break;
		case "ANDHRA BANK":
		    ifscBankName="Union Bank of India";
			break;
		case "APNA SAHAKARI BANK LIMITED":
		    ifscBankName="APNA SAHAKARI BANK LIMITED";
			break;
		case "AU SMALL FINANCE BANK LIMITED":
		    ifscBankName="AU Small Finance Bank";
			break;
		case "BANDHAN BANK LIMITED":
		    ifscBankName="Bandhan Bank";
			break;
		case "BANK OF BARODA":
		    ifscBankName="Bank of Baroda Retail";
			break;
		case "BANK OF INDIA":
		    ifscBankName="Bank Of India";
			break;
		case "CENTRAL BANK OF INDIA":
		    ifscBankName="Central Bank of India";
			break;
		case "BANK OF MAHARASHTRA":
		    ifscBankName="Bank of Maharashtra";
			break;
		case "BHAGINI NIVEDITA SAHAKARI BANK LTD PUNE":
		    ifscBankName="BHAGINI NIVEDITA SAHAKARI BANK LTD PUNE";
			break;
		case "CANARA BANK":
		    ifscBankName="Canara Bank";
			break;
		case "CAPITAL SMALL FINANCE BANK LIMITED":
		    ifscBankName="CAPITAL SMALL FINANCE BANK LIMITED";
			break;
		case "CITI BANK":
		    ifscBankName="CITI BANK";
			break;
		case "CITY UNION BANK LIMITED":
		    ifscBankName="City Union Bank";
			break;
		case "CORPORATION BANK":
		    ifscBankName="Union Bank of India";
			break;
		case "DENA BANK":
		    ifscBankName="Bank of Baroda Retail";
			break;
		case "DEUSTCHE BANK":
		    ifscBankName="DEUSTCHE BANK";
			break;
		case "DHANALAKSHMI BANK":
		    ifscBankName="Dhanlaxmi Bank";
			break;
		case "EQUITAS SMALL FINANCE BANK LIMITED":
		    ifscBankName="Equitas Small finance bank";
			break;
		case "ESAF SMALL FINANCE BANK LIMITED":
		    ifscBankName="ESAF SMALL FINANCE BANK LIMITED";
			break;
		case "FEDERAL BANK":
		    ifscBankName="FEDERAL BANK";
			break;
		case "FINCARE SMALL FINANCE BANK LTD":
		    ifscBankName="FINCARE SMALL FINANCE BANK LTD";
			break;
		case "G P PARSIK BANK":
		    ifscBankName="G P PARSIK BANK";
			break;
		case "HSBC BANK":
		    ifscBankName="HSBC BANK";
			break;
		case "IDBI BANK":
		    ifscBankName="IDB";
			break;
		case "IDFC First Bank Ltd":
		    ifscBankName="IDN";
			break;
		case "INDIAN BANK":
		    ifscBankName="INDIAN BANK";
			break;
		case "INDIAN OVERSEAS BANK":
		    ifscBankName="Indian Overseas Bank";
			break;
		case "INDUSIND BANK":
		    ifscBankName="IDS";
			break;
		case "KALLAPPANNA AWADE ICHALKARANJI JANATA SAHAKARI BANK LIMITED":
		    ifscBankName="KALLAPPANNA AWADE ICHALKARANJI JANATA SAHAKARI BANK LIMITED";
			break;
		case "KALUPUR COMMERCIAL COOPERATIVE BANK":
		    ifscBankName="KALUPUR COMMERCIAL COOPERATIVE BANK";
			break;
		case "KALYAN JANATA SAHAKARI BANK":
		    ifscBankName="KALYAN JANATA SAHAKARI BANK";
			break;
		case "KARNATAKA BANK LIMITED":
		    ifscBankName="KARNATAKA BANK LIMITED";
			break;
		case "KARNATAKA VIKAS GRAMEENA BANK":
		    ifscBankName="KARNATAKA VIKAS GRAMEENA BANK";
			break;
		case "KARUR VYSYA BANK":
		    ifscBankName="Karur Vysya Bank";
			break;
		case "LAXMI VILAS BANK":
		    ifscBankName="Lakshmi Vilas Bank";
			break;
		case "Maharashtra Gramin Bank":
		    ifscBankName="Maharashtra Gramin Bank";
			break;
		case "NKGSB COOPERATIVE BANK LIMITED":
		    ifscBankName="NKGSB Co-op Bank Ltd";
			break;
		case "NUTAN NAGARIK SAHAKARI BANK LIMITED":
		    ifscBankName="NUTAN NAGARIK SAHAKARI BANK LIMITED";
			break;
		case "ORIENTAL BANK OF COMMERCE":
		    ifscBankName="ORIENTAL BANK OF COMMERCE";
			break;
		case "PUNJAB NATIONAL BANK":
		    ifscBankName="Punjab National Bank [Retail]";
			break;
		case "RAJASTHAN MARUDHARA GRAMIN BANK":
		    ifscBankName="RAJASTHAN MARUDHARA GRAMIN BANK";
			break;
		case "RAJKOT NAGRIK SAHAKARI BANK LIMITED":
		    ifscBankName="RAJKOT NAGRIK SAHAKARI BANK LIMITED";
			break;
		case "RBL BANK LIMITED":
		    ifscBankName="RBL Bank Limited";
			break;
		case "SARASWAT COOPERATIVE BANK LIMITED":
		    ifscBankName="SARASWAT COOPERATIVE BANK LIMITED";
			break;
		case "SOUTH INDIAN BANK":
		    ifscBankName="SIB";
			break;
		case "STANDARD CHARTERED BANK":
		    ifscBankName="Standard Chartered Bank";
			break;
		case "SURYODAY SMALL FINANCE BANK LIMITED":
		    ifscBankName="Suryoday Small Finance Bank Ltd";
			break;
		case "SUTEX COOPERATIVE BANK LIMITED":
		    ifscBankName="SUTEX COOPERATIVE BANK LIMITED";
			break;
		case "SYNDICATE BANK":
		    ifscBankName="Canara Bank";
			break;
		case "THE SHAMRAO VITHAL COOPERATIVE BANK":
		    ifscBankName="Shamrao Vithal Bank";
			break;
		case "Shri Veershaiv Co Op Bank Ltd":
		    ifscBankName="Shri Veershaiv Co Op Bank Ltd";
			break;
		case "Suco Souharda Sahakari Bank Ltd":
		    ifscBankName="Suco Souharda Sahakari Bank Ltd";
			break;
		case "THE BARAMATI SAHAKARI BANK LTD":
		    ifscBankName="THE BARAMATI SAHAKARI BANK LTD";
			break;
		case "THE GADCHIROLI DISTRICT CENTRAL COOPERATIVE BANK LIMITED":
		    ifscBankName="THE GADCHIROLI DISTRICT CENTRAL COOPERATIVE BANK LIMITED";
			break;
		case "THE GUJARAT STATE COOPERATIVE BANK LIMITED":
		    ifscBankName="THE GUJARAT STATE COOPERATIVE BANK LIMITED";
			break;
		case "THE HASTI COOP BANK LTD":
		    ifscBankName="THE HASTI COOP BANK LTD";
			break;
		case "THE MEHSANA URBAN COOPERATIVE BANK":
		    ifscBankName="THE MEHSANA URBAN COOPERATIVE BANK";
			break;
		case "THE THANE BHARAT SAHAKARI BANK LIMITED":
		    ifscBankName="THE THANE BHARAT SAHAKARI BANK LIMITED";
			break;
		case "THE UDAIPUR URBAN CO OPERATIVE BANK LTD":
		    ifscBankName="THE UDAIPUR URBAN CO OPERATIVE BANK LTD";
			break;
		case "THE VISHWESHWAR SAHAKARI BANK LIMITED":
		    ifscBankName="THE VISHWESHWAR SAHAKARI BANK LIMITED";
			break;
		case "The Muslim Co-operative Bank Ltd":
		    ifscBankName="The Muslim Co-operative Bank Ltd";
			break;
		case "UCO BANK":
		    ifscBankName="UCO Bank";
			break;
		case "UNION BANK OF INDIA":
		    ifscBankName="Union Bank of India";
			break;
		case "UNITED BANK OF INDIA":
		    ifscBankName= "UNITED BANK OF INDIA";
			break;
		case "Ujjivan Small Finance Bank Limited":
		    ifscBankName= "Ujjivan Small Finance Bank Limited";
			break;
		case "VIJAYA BANK":
		    ifscBankName= "VIJAYA BANK";
			break;
		case "YES BANK":
		    ifscBankName= "YES BANK";
			break;
		default:
			ifscBankName=ifscBankName;
		}
		MultipleBankNameValue=ifscBankName;
		logger.info(" ===== BankNamePreSelectDedupe === "+ MultipleBankNameValue);
		return MultipleBankNameValue;
		
	}

	
	@Override
	public String getcommunicationCityService(String commPincode,String fdcNumber) {
		String city="";
		String state="";
		JSONObject commRes = new JSONObject();
		try {
			int reqPin = Integer.parseInt(commPincode);
			FDPincodeMaster fdPiMast = fixedDepositDao.getcitystatename(reqPin);
	        if(fdPiMast != null)
	        {
	            city = fdPiMast.getCity();
	            state = fdPiMast.getState();
	        }
	        else
	        {
	            city = "NA";
	            state = "NA";
	        }
	        commRes.put(Constants.STATUS, Constants.STATUS_SUCCESS);
	        commRes.put("city", city);
	        commRes.put("state", state);
	        
		}
		catch(Exception ex){
			commRes.put(Constants.STATUS, Constants.STATUS_FAIL);
			logger.info("========exception in getcommunicationCityService========");
			Utility.loadNewRelicException(ex.toString(), "getcommunicationCityService", "", fdcNumber);
			
		}
		
		logger.info("========response in getcommunicationCityService========"+commRes.toString());
		return commRes.toString();
		
	}
}
