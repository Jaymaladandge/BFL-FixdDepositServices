package com.bajaj.fixeddeposit.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bajaj.fixeddeposit.api.OtpIntegration;
import com.bajaj.fixeddeposit.dao.FixedDepositDao;
import com.bajaj.fixeddeposit.model.FixedDepositData;
import com.bajaj.fixeddeposit.model.JsonResponse;
import com.bajaj.fixeddeposit.model.OtpResponse;
import com.bajaj.fixeddeposit.util.Constants;
import com.bajaj.fixeddeposit.util.DbManipulationUtil;
import com.bajaj.fixeddeposit.util.Utility;
import com.bajaj.fixeddeposit.validation.FormFieldValidation;

@Service("resumeJourneyService")
public class ResumeJourneyServiceImpl implements ResumeJourneyService
{

	private static final Logger logger = Logger.getLogger(ResumeJourneyServiceImpl.class);

	@Autowired
	private OtpIntegration apiIntegration;

	@Autowired
	private FormFieldValidation formFieldValidation;

	@Autowired
	private FixedDepositDao fixedDepositDao;  

	@Autowired
	private OtpService otpService; 

	@Autowired
	private DbManipulationUtil dbManipulation;  
	
	@Override
	public String resumeJourneygetOTP(String requestJson,HttpServletRequest request, HttpSession httpSession) {

		String validationStatus = "";
		try {
			JsonResponse fieldValidationRes = formFieldValidation.otpFieldsValidation(requestJson);
			logger.info(" ====customerId Field's Validation Res == " + fieldValidationRes);
			if (Constants.STATUS_SUCCESS.equalsIgnoreCase(fieldValidationRes.getStatus()))
			{
				JSONObject generateOtpReqJson = new JSONObject(requestJson);

				String mobileNumber = generateOtpReqJson.getString(Constants.MOBILE_NUMBER);
				String resumeOtpTriggerdTime = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(Calendar.getInstance().getTime()) ;
				
				String investmentType = generateOtpReqJson.has("investmentType")? generateOtpReqJson.get("investmentType").toString():"NA";

				String generateOtpRes = apiIntegration.generateOtpApi(mobileNumber, request,"",Constants.RESUMEJOURNEYGETOTP);
				logger.info(" ==== generateOtpRes in resumeJourneygetOTP === " + generateOtpRes);
				if (generateOtpRes != null) 
				{

					OtpResponse otpResponse=otpService.setOtpResponseService(generateOtpRes);
					String requestId = otpResponse.getRequestId() == null?"NA":otpResponse.getRequestId();
					logger.info(" ==== Request Id from Send otp API === " + requestId);
					JSONObject generateOtpResJson = new JSONObject(generateOtpRes);
					if (generateOtpResJson.has(Constants.ERROR_CODE)) {
						String errorCode = generateOtpResJson.get(Constants.ERROR_CODE) == null ? "NA": generateOtpResJson.get(Constants.ERROR_CODE).toString();
						String errorMsg = generateOtpResJson.get(Constants.ERROR_MSG) == null ? "NA": generateOtpResJson.get(Constants.ERROR_MSG).toString();
						logger.info(" === Error Code From API === " + errorCode + " === Error Msg From API == " + errorMsg);
						int generatedOtpCount = httpSession.getAttribute(Constants.RESUME_J_OTP_GENERATE_COUNT)!=null?(int) httpSession.getAttribute(Constants.RESUME_J_OTP_GENERATE_COUNT):0;
						logger.info(" ==== generatedOtpCount in resumeJourneygetOTP for resend otp ==== " + generatedOtpCount);
						if ("00".equals(errorCode)) {
							httpSession.setAttribute(Constants.RESUME_J_OTP_COUNT, 0);
							validationStatus = Constants.STATUS_SUCCESS;
							httpSession.setAttribute(Constants.RESUME_J_OTP_CREATED_TIME, System.currentTimeMillis());
							httpSession.setAttribute(Constants.RESUME_J_OTP_GENERATE_COUNT, generatedOtpCount + 1);
						} else {
							validationStatus = Constants.STATUS_FAIL;
							Utility.loadNewRelicException("", "generateOtp failure", "", "NA");
						}
						httpSession.setAttribute(Constants.RESUME_J_ERROR_CODE, errorCode);
						httpSession.setAttribute(Constants.RESUME_J_ERROR_MSG, errorMsg);
						httpSession.setAttribute(Constants.RESUME_J_OTP_RESPONSE, otpResponse);
						httpSession.setAttribute(Constants.RESUME_J_OTP_REQUEST_ID, requestId);
						httpSession.setAttribute(Constants.MOBILE_NUMBER, mobileNumber);

					}else {
						validationStatus = Constants.STATUS_FAIL;
						logger.info(" === status=== "+validationStatus);
					}
				}
				else {
					validationStatus = Constants.STATUS_FAIL;
					logger.info(" === status=== "+validationStatus);
				}
				
				logger.info(" === validationStatus=== "+validationStatus);
                if(Constants.STATUS_SUCCESS.equalsIgnoreCase(validationStatus)){
				FixedDepositData depositData=fixedDepositDao.getFixedDepositDataByMobile(mobileNumber);
				if(depositData!=null){

					logger.info(" == depositData in resumeJourneygetOTP ==== " + depositData);

					String createdOn=depositData.getCreatedOn()==null?"":depositData.getCreatedOn();
					logger.info(" ====userLast JourneyStep Date === "+createdOn);
					SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
					SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");

					Date date1 = myFormat.parse(createdOn);
					Date date2 = formatter.parse(new Date().toString());
					long diff = date2.getTime() - date1.getTime();
					long journeyDays=TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);


					if(journeyDays<10)
					{
						logger.info(" == Resume JourneyFlag depositData in resumeJourneygetOTP ==== " + depositData.getJourneyStepName());
						String lastStep=depositData.getJourneyStepName()==null?"NA":"null".equalsIgnoreCase(depositData.getJourneyStepName())?"NA":depositData.getJourneyStepName();
						if("NA".equalsIgnoreCase(lastStep) )
						{
							httpSession.setAttribute(Constants.RESUME_J_CUSTOMER_ID, "stepNotFound");
							httpSession.setAttribute("customerId", "stepNotFound");
							validationStatus = Constants.STATUS_SUCCESS;
							logger.info(" == resume Jounery step Not fount=== " );
						}
						else{
							logger.info(" == =====Transction status in resumeJourneygetOTP ==== " + depositData.getTransactionStatus());
							if(!("SUCCESS".equalsIgnoreCase(depositData.getTransactionStatus())))
							{

								logger.info(" === Mobile Number in resumeJourneygetOTP === " + mobileNumber);

								String customerId=depositData.getCustomerId()==null?"":depositData.getCustomerId();
								String dateOfBirth = depositData.getDateOfBirth()==null?"":depositData.getDateOfBirth();
								logger.info(" === dateOfBirth in resumeJourneygetOTP === " + dateOfBirth);
								httpSession.setAttribute(Constants.RESUME_J_DATE_OF_BIRTH, dateOfBirth);
								httpSession.setAttribute(Constants.RESUME_J_CUSTOMER_ID, customerId);
								httpSession.setAttribute("customerId", customerId);
								logger.info(" == fromSubmissionDate Updated in resumeJourney ===" );
								String fromSubmissionDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime()) ;
								depositData.setCreatedOn(fromSubmissionDate);

								depositData.setOtpTriggeredTime(resumeOtpTriggerdTime);
								depositData.setResumeJourneyStatus("RESUMEOTP");
								depositData.setTimeOfLogging(resumeOtpTriggerdTime);
								logger.info(" == investmentType before Update in resumeJourney ==="+depositData.getInvestmentType()+"===to Update=="+investmentType);
								if(!"NA".equalsIgnoreCase(investmentType)){depositData.setInvestmentType(investmentType);}
								depositData.setUtmMedium(generateOtpReqJson.has("utm_medium_utmTrue") ? generateOtpReqJson.get("utm_medium_utmTrue").toString():"NA");
								depositData.setUtmCampaign(generateOtpReqJson.has("utm_campaign_utmTrue") ? generateOtpReqJson.get("utm_campaign_utmTrue").toString():"NA");
								depositData.setUtmSource(generateOtpReqJson.has("utm_source_utmTrue") ? generateOtpReqJson.get("utm_source_utmTrue").toString():"NA");
								depositData.setDevice(generateOtpReqJson.has("device") ? generateOtpReqJson.get("device").toString():"NA");
								String updateStatus = fixedDepositDao.updateFixedDeposit(depositData);
								logger.info(" == Updation status resumeJourneyValidateOtp === " + updateStatus);

							}
							else{
								httpSession.setAttribute(Constants.RESUME_J_CUSTOMER_ID, Constants.STATUS_TRXDONE);
								httpSession.setAttribute("customerId", Constants.STATUS_TRXDONE);
								validationStatus = Constants.STATUS_SUCCESS;
								logger.info(" === validationStatus in resume transaction success=== "+validationStatus);
							}
						}
					}else
					{
						httpSession.setAttribute(Constants.RESUME_J_CUSTOMER_ID, "beforeTenDaysJourneyFound");
						httpSession.setAttribute("customerId", "beforeTenDaysJourneyFound");
						validationStatus = Constants.STATUS_SUCCESS;
						logger.info(" == resume JounerybeforeTenDaysJourneyFound=== " );
					}


				}else{
					httpSession.setAttribute(Constants.RESUME_J_CUSTOMER_ID, "noDataFoundUser");
					httpSession.setAttribute("customerId", "noDataFoundUser");
					validationStatus = Constants.STATUS_SUCCESS;
					logger.info(" == resume noDataFoundUser=== " );
				}
				}

			}else{
				validationStatus = Constants.STATUS_FAIL;
				logger.info(" === Field Validation failed === ");
				Utility.loadNewRelicValidation("Field validation fail in resumeJourneygetOTP service"+fieldValidationRes.toString(), "Field validation fail in resumeJourneygetOTP ", "NA","resumeJourneygetOTP");
			}
			httpSession.setAttribute(Constants.RESUME_COUNT, 0);
		} catch (Exception e) {
			validationStatus = Constants.STATUS_FAIL;
			logger.error(" === Exception in resumeJourneygetOTP === ", e);
			Utility.loadNewRelicException(e.toString(), "resumeJourneygetOTP service", "", "NA");
		}
		logger.info(" === return validationStatus=== "+validationStatus);
		return validationStatus;
	}

	@Override
	public String resumeJourneyValidateOtp(String validateOtpReqJson, HttpSession httpSession,HttpServletRequest request,String contextCalled) {
		String validateOtpStatus = "";
		JSONObject userDetails = new JSONObject();
		String requestCustId="";
		String customerId="";
		try{
			logger.info(" ==== validateOtpReqJson in resumeJourneyValidateOtp === " + validateOtpReqJson);
			logger.info(" ==== httpSession id in resumeJourneyValidateOtp === " + httpSession.getId());
			JSONObject validateOtpJson = new JSONObject(validateOtpReqJson);
			String otpResponseCode = "";
			String otpResponseMessage = "";
			customerId = validateOtpJson.optString("fdcNumber")!=null?validateOtpJson.optString("fdcNumber"):"NA";
			JsonResponse fieldValidationRes = formFieldValidation.otpFieldsValidation(validateOtpReqJson);
			logger.info(" ====customerId Field's Validation Res == " + fieldValidationRes);
			
			if (Constants.STATUS_SUCCESS.equalsIgnoreCase(fieldValidationRes.getStatus())) {
			String userOtp = validateOtpJson.get(Constants.USER_OTP) == null?"NA": validateOtpJson.get(Constants.USER_OTP).toString();
			String requestId = (String) httpSession.getAttribute(Constants.RESUME_J_OTP_REQUEST_ID)==null?"NA":(String) httpSession.getAttribute(Constants.RESUME_J_OTP_REQUEST_ID);
			if(!"NA".equalsIgnoreCase(requestId)){
			String userMobileNumber = validateOtpJson.get(Constants.USER_MOBILE_NUMBER) == null?"NA": validateOtpJson.get(Constants.USER_MOBILE_NUMBER).toString();
			String userMobileNumberSession= (String) httpSession.getAttribute(Constants.MOBILE_NUMBER);
			String otpSubmittedTime = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(Calendar.getInstance().getTime()) ;
			requestCustId = validateOtpJson.has("fdcNumber") ?validateOtpJson.get("fdcNumber").toString():"";
			logger.info(" === userMobileNumber in resumeJourneyValidateOtp from session === " + userMobileNumberSession);
			logger.info(" === requestCustId validateOtpRes in resumeJourneyValidateOtp === " + requestCustId);
			
			if(userMobileNumber.equalsIgnoreCase(userMobileNumberSession))
			{
				String customerIdSession= (String) httpSession.getAttribute(Constants.RESUME_J_CUSTOMER_ID);
				logger.info(" === Customer Id in resumeJourneyValidateOtp from session === " + customerIdSession);
				logger.info(" === Mobile Number in resumeJourneyValidateOtp === " + userMobileNumber);
				String validateOtpRes = apiIntegration.validateOtpApi(userOtp, userMobileNumber, requestId,requestCustId,request,contextCalled);
				logger.info(" === validateOtpRes in resumeJourneyValidateOtp === " + validateOtpRes);
				JSONObject validateOtpResJson = new JSONObject(validateOtpRes);
				if(validateOtpResJson.has("status") && validateOtpResJson.has("message")){
					
					otpResponseCode = validateOtpResJson.get("status")==null?"":validateOtpResJson.get("status").toString();
					otpResponseMessage = validateOtpResJson.get("message")==null?"":validateOtpResJson.get("message").toString();
					int validateOtpCount = Integer.parseInt(httpSession.getAttribute(Constants.RESUME_J_OTP_COUNT).toString())!=0 ?Integer.parseInt(httpSession.getAttribute(Constants.RESUME_J_OTP_COUNT).toString()):0;
					logger.info(" ==== validateOtpCount in resumeJourneyValidateOtp === " + validateOtpCount);
					httpSession.setAttribute(Constants.RESUME_J_OTP_COUNT, validateOtpCount);
					logger.info(" ===== resumeJourneyValidateOtp otpResponseCode ==== " + otpResponseCode + " === otpResponseMessage ==== " + otpResponseMessage);
					if("success".equalsIgnoreCase(otpResponseCode) && "Done".equalsIgnoreCase(otpResponseMessage))
					{
						
						if("noDataFoundUser".equalsIgnoreCase(customerIdSession))
						{
							validateOtpStatus ="noDataFoundForuser";
						}else if(Constants.STATUS_TRXDONE.equalsIgnoreCase(customerIdSession) )
						{
							validateOtpStatus =Constants.STATUS_TRXDONE;
						}else if("beforeTenDaysJourneyFound".equalsIgnoreCase(customerIdSession) )
						{
							validateOtpStatus ="beforeTenDaysJourneyFound";
						}
						else if("stepNotFound".equalsIgnoreCase(customerIdSession) )
						{
							validateOtpStatus ="stepNotFound";
						}
						else{
							
							logger.info(" ==== Otp Validation Successful resumeJourneyValidateOtp === ");
							httpSession.removeAttribute(Constants.RESUME_J_OTP_REQUEST_ID);
							logger.info(" ==== Removed Request Id from session after validating successfully resumeJourneyValidateOtp=== ");
							validateOtpStatus = Constants.STATUS_SUCCESS;
							FixedDepositData depositData  = fixedDepositDao.getFixedDepositData(customerIdSession);
							logger.info(" == depositData in resumeJourneyValidateOtp ==== " + depositData);
							String userLastJourneyStep=depositData.getJourneyStepName()==null?"":depositData.getJourneyStepName();
							logger.info(" ====userLastJourneyStep === "+userLastJourneyStep);
							String kycVerifyStatus=depositData.getKycVerifyStatus()==null?"":depositData.getKycVerifyStatus();
							logger.info(" ====userLastJourneyStep kycVerifyStatus === "+kycVerifyStatus);
							userLastJourneyStep="DEDUPE_SUCCESS".equalsIgnoreCase(kycVerifyStatus) || "CKYC_SUCCESS".equalsIgnoreCase(kycVerifyStatus) || "OKYC_SUCCESS".equalsIgnoreCase(kycVerifyStatus) ?"PERSONALDETAILS":userLastJourneyStep;
							logger.info(" ====userLastJourneyStep based on kycVerifyStatus=== "+userLastJourneyStep);
							if("VALIDATEOTP".equalsIgnoreCase(userLastJourneyStep) || "GETOTP".equalsIgnoreCase(userLastJourneyStep))
							{
								userDetails.put(Constants.RESUME_STEP, "step1");
								userDetails.put(Constants.CUST_TYPE, depositData.getRemarkCustType()==null?"NA":depositData.getRemarkCustType());
							}
							else if("CKYCVERIFY".equalsIgnoreCase(userLastJourneyStep) || "NSDL failure".equalsIgnoreCase(userLastJourneyStep) || "OKYC Success".equalsIgnoreCase(userLastJourneyStep)  || "OKYC failure".equalsIgnoreCase(userLastJourneyStep)  )
							{
								userDetails.put(Constants.RESUME_STEP, "step2");
								userDetails.put(Constants.CUST_TYPE, depositData.getRemarkCustType()==null?"NA":depositData.getRemarkCustType());
							}
							else if("PERSONALDETAILS".equalsIgnoreCase(userLastJourneyStep) || "DOCUMENTUPLOAD".equalsIgnoreCase(userLastJourneyStep)|| "PAYMENTFAIL".equalsIgnoreCase(userLastJourneyStep) || "SCHEMEDETAILS".equalsIgnoreCase(userLastJourneyStep) || "PAYMENTREQUEST".equalsIgnoreCase(userLastJourneyStep))
							{
								String createdOn=depositData.getOriginalcreatedOn()==null?"":depositData.getOriginalcreatedOn();
								logger.info(" ====userLastJourneyStep Date === "+createdOn);
								SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
								SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
								
								Date date1 = myFormat.parse(createdOn);
							    Date date2 = formatter.parse(new Date().toString());
							    long diff = date2.getTime() - date1.getTime();
							    long journeyDays=TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
							    
							    
							    if(journeyDays<10){
								userDetails.put(Constants.RESUME_STEP, "step3");
							    userDetails.put(Constants.ADDRESS,depositData.getAddress()==null?"NA":depositData.getAddress());
								userDetails.put(Constants.EXISTING_CUST_ID, depositData.getExistingCustId()==null?"NA":depositData.getExistingCustId());
								userDetails.put(Constants.CITY, depositData.getCity()==null?"NA":depositData.getCity());
								userDetails.put(Constants.EMAIL,depositData.getEmailAddress()==null?"NA":depositData.getEmailAddress());
								userDetails.put("gender", depositData.getGender()==null?"NA":depositData.getGender());
							    userDetails.put(Constants.USER_MOBILE_NUMBER, userMobileNumber);
							   
							    userDetails.put("nomineeName", depositData.getNomineeName()==null ?"NA":depositData.getNomineeName());
							    userDetails.put("nomineeDob", depositData.getNomineeDateOfBirth()==null ?"NA":depositData.getNomineeDateOfBirth());
							    userDetails.put("relationWithNominee", depositData.getRelationshipWithNominee()==null ?"NA":depositData.getRelationshipWithNominee());
							    userDetails.put("nomineePincode", depositData.getNomineePincode()==null ?"NA":depositData.getNomineePincode());
							    userDetails.put("nomineeAddres", depositData.getNomineeAddress()==null ?"NA":depositData.getNomineeAddress());
							    userDetails.put("gaurdianName", depositData.getNomineeGuardianName()==null ?"NA":depositData.getNomineeGuardianName());
							    userDetails.put("gaurdianAge", depositData.getNomineeGuardianAge()==null ?"NA":depositData.getNomineeGuardianAge());
							    userDetails.put("gaurdianAddress", depositData.getNomineeGuardianAddress()==null ?"NA":depositData.getNomineeGuardianAddress());
							    userDetails.put("gaurdianRealtion", depositData.getNomineeGuardianRelation()==null ?"NA":depositData.getNomineeGuardianRelation());
							    userDetails.put("gaurdianPincode", depositData.getNomineeGuardianPincode()==null ?"NA":depositData.getNomineeGuardianPincode());
							    userDetails.put("isNomineeAvaiable", depositData.getNomineeCheck()==null ?"NA":depositData.getNomineeCheck());
							    userDetails.put("isNomineeAddressAvailable", depositData.getNomineeAddressCheck()==null ?"NA":depositData.getNomineeAddressCheck());
							    userDetails.put("isGaurdiunAvailable", depositData.getGaurdianCheck()==null ?"NA":depositData.getGaurdianCheck());
							    userDetails.put("nomineeSalutation", depositData.getNomineeSalutation()==null ?"NA":depositData.getNomineeSalutation());
							    userDetails.put("mainSalutation", depositData.getSalutation()==null ?"NA":depositData.getSalutation());
							    userDetails.put("etbFlag", depositData.getPanedited()==null ?"NA":depositData.getPanedited());
							    
								String remarkcustType=depositData.getRemarkCustType()==null?"NA":depositData.getRemarkCustType();
								logger.info("==========User type====="+remarkcustType);
								
								SimpleDateFormat formatterDob = new SimpleDateFormat("dd/MM/yyyy");
								SimpleDateFormat formatter1DDob = new SimpleDateFormat("dd-MM-yyyy");
								
								String custType=depositData.getCustomerType()==null?"NA":depositData.getCustomerType();
								Date dateDOB = formatterDob.parse(depositData.getDateOfBirth()==null?"NA":depositData.getDateOfBirth());

								logger.info(formatterDob.format(dateDOB));

								String dobFormatednew=formatter1DDob.format(dateDOB);
								LocalDate currentDate = LocalDate.now();
								LocalDate birthDateFromBrowser = LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(dobFormatednew))) ;
								int ageFromBrowser=Period.between(birthDateFromBrowser, currentDate).getYears();
								
								if("Senior Citizen".equalsIgnoreCase(custType) || ageFromBrowser >= 60)
								{
									userDetails.put(Constants.CUST_TYPE, "STB");
								}else if("Existing Customer".equalsIgnoreCase(custType))
								{
									userDetails.put(Constants.CUST_TYPE, "ETB");
								}else
								{
								userDetails.put(Constants.CUST_TYPE, remarkcustType);
								}
								
								
							    }else
							    {
									userDetails.put(Constants.RESUME_STEP, "step2");
									userDetails.put(Constants.CUST_TYPE, depositData.getRemarkCustType()==null?"NA":depositData.getRemarkCustType());
							    }
							}else
							{
								userDetails.put(Constants.RESUME_STEP, "step1");
								userDetails.put(Constants.CUST_TYPE, depositData.getRemarkCustType()==null?"NA":depositData.getRemarkCustType());
							}
							
							userDetails.put(Constants.EXISTING_CUST_ID, depositData.getExistingCustId()==null?"NA":depositData.getExistingCustId());
							userDetails.put(Constants.USER_MOBILE_NUMBER, userMobileNumber);
							userDetails.put(Constants.DATE_OF_BIRTH, depositData.getDateOfBirth()==null?"NA":depositData.getDateOfBirth());
							userDetails.put(Constants.FULL_NAME,depositData.getFullName()==null?depositData.getNsdlFullName()==null?"NA":depositData.getNsdlFullName():depositData.getFullName());
							userDetails.put(Constants.PIN,depositData.getPinCode()==null?"NA":depositData.getPinCode());
							userDetails.put(Constants.PAN, depositData.getPanCard()==null?"NA":depositData.getPanCard());
							
							userDetails.put(Constants.INVESTMENT_TYPE, depositData.getInvestmentType()==null?"FD":depositData.getInvestmentType());
							depositData.setResumeJourneyStatus("RESUMEVALIDATEOTP");
							depositData.setOtpSubmittedTime(otpSubmittedTime);
							
							String updateStatus = fixedDepositDao.updateFixedDeposit(depositData);
							logger.info(" == Updation status resumeJourneyValidateOtp === " + updateStatus);	
						}
						
					}
					else{
						validateOtpStatus = Constants.STATUS_FAIL;
						validateOtpCount = Integer.parseInt(httpSession.getAttribute(Constants.RESUME_J_OTP_COUNT).toString());
						httpSession.setAttribute(Constants.RESUME_J_OTP_COUNT, validateOtpCount + 1);
						logger.info(" === validateOtpCount in resumeJourneyValidateOtp === " + validateOtpCount);
					}
				}
				else{
					logger.info(" ==== Otp Validation Fail in resumeJourneyValidateOtp=== ");
					validateOtpStatus = Constants.STATUS_FAIL;
					otpResponseCode = validateOtpResJson.has("responseCode")?validateOtpResJson.get("responseCode").toString():"93";
					otpResponseMessage = validateOtpResJson.has("responseMessage")?validateOtpResJson.get("responseMessage").toString():"FAILED : OTP is not valid";
					int validateOtpCount = Integer.parseInt(httpSession.getAttribute(Constants.RESUME_J_OTP_COUNT).toString())!=0 ?Integer.parseInt(httpSession.getAttribute(Constants.RESUME_J_OTP_COUNT).toString()):0;
					logger.info(" ==== validateOtpCount in resumeJourneyValidateOtp === " + validateOtpCount);
					httpSession.setAttribute(Constants.RESUME_J_OTP_COUNT, validateOtpCount + 1);
					logger.info(" === validateOtpCount in resumeJourneyValidateOtp === " + validateOtpCount);
					logger.info(" ===== resumeJourneyValidateOtp otpResponseCode ==== " + otpResponseCode + " === otpResponseMessage ==== " + otpResponseMessage);
				}
			}else
			{
				validateOtpStatus = Constants.STATUS_FAIL;
				Utility.loadNewRelicValidation("Mobile number validation fail in resumeJourneyValidateOtp "+fieldValidationRes, "mobile number fail in resumeJourneyValidateOtp ", customerId,"resumeJourneyValidateOtp");
			}
			logger.info(" === User Data to prefill in resumeJourneyValidateOtp === " + userDetails);
			httpSession.setAttribute("ResumeUserDetails", userDetails.toString());
			httpSession.setAttribute(Constants.RESUME_J_ERROR_CODE, otpResponseCode);
			httpSession.setAttribute(Constants.RESUME_J_ERROR_MSG, otpResponseMessage);
			}else{
				validateOtpStatus = Constants.STATUS_FAIL;
				logger.info(" === Request Id Blank  === ");
			}
			}else{
				validateOtpStatus = Constants.STATUS_FAIL;
				logger.info(" === Field Validation failed === ");
				Utility.loadNewRelicValidation("Field validation fail in resumeJourneyValidateOtp "+fieldValidationRes, "Field validation fail in resumeJourneyValidateOtp ", customerId,"resumeJourneyValidateOtp");
			}
		}catch(Exception e){
			if(!( requestCustId.isEmpty()))
			{
				dbManipulation.recordExeption("RESUME_VALIDATEOTP_SERVICE", "Exception due to internal call",requestCustId);
			}
			validateOtpStatus = Constants.STATUS_FAIL;
			logger.error(" ==== Exception in resumeJourneyValidateOtp ==== ", e);
			Utility.loadNewRelicException(e.toString(), "resumeJourneyValidateOtp service", "", customerId);
		}
		logger.info(" ==== Validate Otp Status in resumeJourneyValidateOtp === " + validateOtpStatus);
		return validateOtpStatus;
	}
}
