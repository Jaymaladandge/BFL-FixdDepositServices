package com.bajaj.fixeddeposit.service;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.bajaj.fixeddeposit.api.CustIdIntegration;
import com.bajaj.fixeddeposit.api.OtpIntegration;
import com.bajaj.fixeddeposit.api.ResponsysIntegration;
import com.bajaj.fixeddeposit.api.SfdcIntegration;
import com.bajaj.fixeddeposit.dao.FixedDepositDao;
import com.bajaj.fixeddeposit.model.FDPincodeMaster;
import com.bajaj.fixeddeposit.model.FixedDepositData;
import com.bajaj.fixeddeposit.model.JsonResponse;
import com.bajaj.fixeddeposit.model.OtpResponse;
import com.bajaj.fixeddeposit.model.sfdc.AdressDetailsList;
import com.bajaj.fixeddeposit.model.sfdc.FDRecordsObjList;
import com.bajaj.fixeddeposit.model.sfdc.ObjApplDetail;
import com.bajaj.fixeddeposit.model.sfdc.ObjFDDetails;
import com.bajaj.fixeddeposit.model.sfdc.RecWrapperFD;
import com.bajaj.fixeddeposit.model.sfdc.SfdcRequest;
import com.bajaj.fixeddeposit.util.Constants;
import com.bajaj.fixeddeposit.util.DbManipulationUtil;
import com.bajaj.fixeddeposit.util.Utility;
import com.bajaj.fixeddeposit.validation.FormFieldValidation;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OtpServiceImpl implements OtpService {

	private static final Logger logger = Logger.getLogger(OtpServiceImpl.class);

	@Autowired
	private OtpIntegration apiIntegration;

	@Autowired
	private FormFieldValidation formFieldValidation;

	@Autowired
	private CustIdIntegration custIdIntegration;

	@Autowired
	private FixedDepositDao fixedDepositDao;

	@Autowired
	private SfdcIntegration sfdcIntegration;

	@Autowired
	FormDataService formDataService;

	@Autowired
	private DbManipulationUtil dbManipulation;

	@Override
	public String otpGeneratorService(String generateOtpReq, HttpServletRequest request, HttpSession httpSession) {

		String validationStatus = "";

		try {
			JsonResponse fieldValidationRes = formFieldValidation.otpFieldsValidation(generateOtpReq);
			logger.info(" ==== Generate Otp Field's Validation Res == " + fieldValidationRes);

			if (Constants.STATUS_SUCCESS.equalsIgnoreCase(fieldValidationRes.getStatus())) {

				JSONObject generateOtpReqJson = new JSONObject(generateOtpReq);
				String mobileNumber = generateOtpReqJson.getString(Constants.MOBILE_NUMBER);
				logger.info(" === Mobile Number in otpGeneratorService === " + mobileNumber);

				httpSession.setAttribute(Constants.MOBILE_NUMBER, mobileNumber);

				String dateOfBirth = generateOtpReqJson.getString(Constants.DATE_OF_BIRTH);
				logger.info(" === dateOfBirth in otpGeneratorService === " + dateOfBirth);

				String partnerCode = generateOtpReqJson.get(Constants.PARTNER_CODE) == null ? "NA"
						: generateOtpReqJson.get(Constants.PARTNER_CODE).toString();
				String partnerName = generateOtpReqJson.get(Constants.PARTNER_NAME) == null ? "NA"
						: generateOtpReqJson.get(Constants.PARTNER_NAME).toString();

				logger.info(" === partnerCode ==== " + partnerCode + " == partnerName === " + partnerName);

				String generateOtpRes = apiIntegration.generateOtpApi(mobileNumber, request,"",Constants.GENERATEOTP);
				logger.info(" ==== generateOtpRes in otpGeneratorService === " + generateOtpRes);

				if (generateOtpRes != null) {

					OtpResponse otpResponse = setOtpResponseService(generateOtpRes);
					String requestId = otpResponse.getRequestId() == null ? "NA" : otpResponse.getRequestId();
					logger.info(" ==== Request Id from Send otp API === " + requestId);

					JSONObject generateOtpResJson = new JSONObject(generateOtpRes);

					if (generateOtpResJson.has(Constants.ERROR_CODE)) {
						String errorCode = generateOtpResJson.get(Constants.ERROR_CODE) == null ? "NA"
								: generateOtpResJson.get(Constants.ERROR_CODE).toString();
						String errorMsg = generateOtpResJson.get(Constants.ERROR_MSG) == null ? "NA"
								: generateOtpResJson.get(Constants.ERROR_MSG).toString();
						logger.info(
								" === Error Code From API === " + errorCode + " === Error Msg From API == " + errorMsg);

						int generatedOtpCount = httpSession.getAttribute(Constants.OTP_GENERATE_COUNT) != null
								? (int) httpSession.getAttribute(Constants.OTP_GENERATE_COUNT) : 0;
						logger.info(" ==== generatedOtpCount in otpGeneratorService for resend otp ==== "
								+ generatedOtpCount);

						if ("00".equals(errorCode)) {

							httpSession.setAttribute(Constants.OTP_COUNT, 0);

							validationStatus = Constants.STATUS_SUCCESS;
							httpSession.setAttribute(Constants.OTP_CREATED_TIME, System.currentTimeMillis());
							httpSession.setAttribute(Constants.OTP_GENERATE_COUNT, generatedOtpCount + 1);
						} else {
							validationStatus = Constants.STATUS_FAIL;
							Utility.loadNewRelicException("", Constants.GENERATEOTP, "", "NA");
						}

						httpSession.setAttribute(Constants.ERROR_CODE, errorCode);
						httpSession.setAttribute(Constants.ERROR_MSG, errorMsg);
						httpSession.setAttribute(Constants.OTP_RESPONSE, otpResponse);
						httpSession.setAttribute(Constants.OTP_REQUEST_ID, requestId);
						httpSession.setAttribute(Constants.DATE_OF_BIRTH, dateOfBirth);
					}
				}
			} else {
				validationStatus = Constants.STATUS_FAIL;
				logger.info(" === Field Validation failed === ");
				Utility.loadNewRelicValidation("backend Field Validation fail in generateOtp"+ " " + fieldValidationRes.toString(), "Field validation", "NA",Constants.GENERATEOTP);
			}
		} catch (JSONException e) {
			validationStatus = Constants.STATUS_FAIL;
			Utility.loadNewRelicException(e.toString(), "generateOtp service", "", "NA");
			logger.error(" === Exception in otpGeneratorService === ", e);
		}

		return validationStatus;
	}

	@Override
	public String otpValidatorService(String validateOtpReqJson, HttpSession httpSession, HttpServletRequest request) {

		String validateOtpStatus = "";
		String requestCustId = "";

		try {

			logger.info(" ==== validateOtpReqJson in otpValidatorService === " + validateOtpReqJson);
			logger.info(" ==== httpSession id in otpValidatorService === " + httpSession.getId());

			JsonResponse fieldValidationRes = formFieldValidation.otpFieldsValidation(validateOtpReqJson);
			JSONObject validateOtpJson = new JSONObject(validateOtpReqJson);
			requestCustId = validateOtpJson.has("fdcNumber") ? validateOtpJson.get("fdcNumber").toString() : "";

			if (Constants.STATUS_SUCCESS.equalsIgnoreCase(fieldValidationRes.getStatus())) {

				String otpResponseCode = "";
				String otpResponseMessage = "";
				String userOtp = validateOtpJson.get(Constants.USER_OTP) == null ? "NA": validateOtpJson.get(Constants.USER_OTP).toString();
				String userMobileNumber = validateOtpJson.get(Constants.USER_MOBILE_NUMBER) == null ? "NA": validateOtpJson.get(Constants.USER_MOBILE_NUMBER).toString();
				String requestId = (String) httpSession.getAttribute(Constants.OTP_REQUEST_ID) == null ? "NA": (String) httpSession.getAttribute(Constants.OTP_REQUEST_ID);
				String dateOfBirth = httpSession.getAttribute(Constants.DATE_OF_BIRTH) == null ? "": (String) httpSession.getAttribute(Constants.DATE_OF_BIRTH);
				String otpSubmittedTime = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
				String ipAddress = validateOtpJson.has("ipAdress") ? validateOtpJson.get("ipAdress").toString() : "NA";

				if (!"NA".equalsIgnoreCase(requestId)) {
					String validateOtpRes = apiIntegration.validateOtpApi(userOtp, userMobileNumber, requestId,requestCustId, request,Constants.VALIDATEOTP);
					logger.info(" === validateOtpRes in otpValidatorService === " + validateOtpRes);

					JSONObject validateOtpResJson = new JSONObject(validateOtpRes);
					JSONObject userDetails = new JSONObject();

					if (validateOtpResJson.has("status") && validateOtpResJson.has("message")) {

						otpResponseCode = validateOtpResJson.get("status") == null ? "": validateOtpResJson.get("status").toString();
						otpResponseMessage = validateOtpResJson.get("message") == null ? "": validateOtpResJson.get("message").toString();

						int validateOtpCount = Integer.parseInt(httpSession.getAttribute(Constants.OTP_COUNT).toString()) != 0? Integer.parseInt(httpSession.getAttribute(Constants.OTP_COUNT).toString()): 0;
						logger.info(" ==== validateOtpCount in otpValidator === " + validateOtpCount);

						httpSession.setAttribute(Constants.OTP_COUNT, validateOtpCount);

						logger.info(" =====  otpResponseCode ==== " + otpResponseCode + " === otpResponseMessage ==== "
								+ otpResponseMessage);

						if ("success".equalsIgnoreCase(otpResponseCode)
								&& "Done".equalsIgnoreCase(otpResponseMessage)) {
							logger.info(" ==== Otp Validation Successful === ");
							httpSession.removeAttribute(Constants.OTP_REQUEST_ID);
							logger.info(" ==== Removed Request Id from session after validating successfully === ");
							validateOtpStatus = Constants.STATUS_SUCCESS;

							logger.info(" ==== Dedupe Request in otpValidatorService ==== ");

							String customerId = (String) httpSession.getAttribute("customerId");
							logger.info(" === Customer Id in otpValidator from session === " + customerId);

							String dedupeResponse = custIdIntegration.customerCheckEtbNtb(userMobileNumber, dateOfBirth,customerId,"validateOtp");
							logger.info(" === dedupeResponse in otpValidatorService ===== " + dedupeResponse);

							JSONObject dedupeResponseJson = new JSONObject(dedupeResponse);
							String dedupeCustType = dedupeResponseJson.get(Constants.DEDUPE_CUST_TYPE) == null ? "NTB"
									: dedupeResponseJson.get(Constants.DEDUPE_CUST_TYPE).toString();

							httpSession.setAttribute("dedupeCustType", dedupeCustType);
							httpSession.setAttribute(Constants.USER_MOBILE_NUMBER, userMobileNumber);
							httpSession.setAttribute(Constants.DATE_OF_BIRTH, dateOfBirth);

							httpSession.setAttribute("fdUserMobileNumber", userMobileNumber);
							httpSession.setAttribute("fdDateOfBirth", dateOfBirth);

							FixedDepositData fixedDepositData = fixedDepositDao.getFixedDepositData(customerId);

							String customerIdFdproduct = dedupeResponseJson.has(Constants.CUSTOMERID)
									? dedupeResponseJson.get(Constants.CUSTOMERID).toString() : "NA";
							logger.info(" === dedupeResponse in customerId Fd product ===== " + customerIdFdproduct);

							if (Constants.DEDUPE_ETB.equalsIgnoreCase(dedupeCustType)) {
								String productFD = dedupeResponseJson.has(Constants.PRODUCT)
										? dedupeResponseJson.get(Constants.PRODUCT).toString() : "NA";
								logger.info(" == product  in otpValidatorService === " + productFD);
								userDetails.put(Constants.CUSTIDARRAY,
										dedupeResponseJson.getJSONArray(Constants.CUSTIDARRAY));
								userDetails.put(Constants.PRODUCT, productFD);
								userDetails.put(Constants.DATE_OF_BIRTH, dateOfBirth);
								userDetails.put(Constants.USER_MOBILE_NUMBER, userMobileNumber);

								if (Constants.FD.equalsIgnoreCase(productFD) || Constants.FD_BLANK.equalsIgnoreCase(productFD)) {
									String addressVal = custIdIntegration.getDataToPrefill(userMobileNumber,customerIdFdproduct,customerId,"validateOtp");
									logger.info(" == address Response in otpValidatorService === " + addressVal);
									JSONObject addressJson = new JSONObject(addressVal);
									String addressPrefillStatus = addressJson.has("PIDATAAPI")
											? addressJson.get("PIDATAAPI").toString() : "fail";
									String address = "success".equalsIgnoreCase(addressPrefillStatus)
											? addressJson.get(Constants.ADDRESS_1).toString() : "";

									JSONObject prefillResponse = dedupeResponseJson.getJSONObject("userDetails");
									logger.info(" === prefillData in otpValidatorService === " + prefillResponse);

									String prefillDataStatus = prefillResponse.has(Constants.PIDATA_API)
											? prefillResponse.get(Constants.PIDATA_API).toString()
											: Constants.STATUS_FAIL;
									logger.info(
											" === prefillDataStatus in otpValidatorService === " + prefillDataStatus);

									String fullName = prefillResponse.has(Constants.FULL_NAME)
											? prefillResponse.get(Constants.FULL_NAME).toString() : "NA";
									httpSession.setAttribute(Constants.FULL_NAME, fullName);
									String gender = prefillResponse.has("gender")
											? prefillResponse.get("gender").toString() : "NA";

									String city = prefillResponse.has(Constants.CITY)
											? prefillResponse.get(Constants.CITY).toString() : "NA";
									String pincode = prefillResponse.has(Constants.PIN)
											? prefillResponse.get(Constants.PIN).toString() : "NA";
									String state = "";
									String panCard = prefillResponse.has(Constants.PAN)
											? prefillResponse.get(Constants.PAN).toString() : "NA";
									String email = prefillResponse.has(Constants.EMAIL)
											? prefillResponse.get(Constants.EMAIL).toString() : "NA";

									userDetails.put(Constants.FULL_NAME, fullName);
									userDetails.put(Constants.ADDRESS, address.replaceAll(",null", ""));
									userDetails.put(Constants.EXISTING_CUST_ID, customerIdFdproduct);
									userDetails.put(Constants.PIN, pincode);
									userDetails.put(Constants.CITY, city);
									userDetails.put(Constants.EMAIL, email);
									userDetails.put(Constants.PAN, panCard);
									userDetails.put(Constants.DEDUPE_CUST_TYPE, dedupeCustType);
									userDetails.put(Constants.CUST_TYPE, dedupeResponseJson.has(Constants.CUST_TYPE)
											? dedupeResponseJson.get(Constants.CUST_TYPE) : "NA");

									int reqPin;

									if (!("NA".equalsIgnoreCase(pincode))) {

										reqPin = Integer.parseInt(pincode);
										FDPincodeMaster fdPiMast = fixedDepositDao.getcitystatename(reqPin);

										if (fdPiMast != null) {
											city = fdPiMast.getCity();
											state = fdPiMast.getState();

										} else {
											city = "NA";
											state = "NA";
										}
									}

									if ("FEMALE".equalsIgnoreCase(gender)) {
										gender = "F";
									} else {
										gender = "M";
									}

									fixedDepositData.setCity(city);
									fixedDepositData.setPinCode(pincode);
									fixedDepositData.setState(state);
									fixedDepositData.setGender(gender);

									fixedDepositData.setFullName(fullName);
									fixedDepositData.setPanCard(panCard);
									fixedDepositData.setEmailAddress(email);
									fixedDepositData.setAddress(address.replaceAll(",null", ""));
									logger.info(" ===SET data in db setExistingCustId === " + customerIdFdproduct);
									fixedDepositData.setExistingCustId(customerIdFdproduct);
									fixedDepositData.setKycVerifyStatus("DEDUPE_SUCCESS");

								}

								fixedDepositData.setRemarkCustType("DEDUPE");
								fixedDepositData.setFdExistingCustID(
										Constants.FD.equalsIgnoreCase(productFD) ? customerIdFdproduct : "NA");
							} else {

								httpSession.setAttribute("fdFullname", fixedDepositData.getFdslfFullName());
								httpSession.setAttribute("fdPincode", fixedDepositData.getPinCode());
								httpSession.setAttribute("city", fixedDepositData.getCity());
							}

							fixedDepositData.setDedupeCustomerType(dedupeCustType);
							fixedDepositData.setOtpSubmittedTime(otpSubmittedTime);
							fixedDepositData.setOriginalValidateOtpSubmitTime(otpSubmittedTime);
							fixedDepositData.setJourneyStepName("VALIDATEOTP");
							if (!ipAddress.equals("")) {
								try {

									int length = ipAddress.indexOf("=");
									logger.info(" === ipAdress  OtpValidateServiceImpl === "
											+ ipAddress.substring(length + 1));
									fixedDepositData.setIpAdress(fixedDepositData.getIpAdress() == null
											|| "NA".equalsIgnoreCase(fixedDepositData.getIpAdress())
													? ipAddress.substring(length + 1)
													: fixedDepositData.getIpAdress());

								} catch (Exception e) {
									logger.info("----Exception in ip save --------");

								}
							}

							String updateStatus = fixedDepositDao.updateFixedDeposit(fixedDepositData);
							logger.info(" == Updation status of cust type and existing cust id === " + updateStatus);

						} else {
							validateOtpStatus = Constants.STATUS_FAIL;
							validateOtpCount = Integer
									.parseInt(httpSession.getAttribute(Constants.OTP_COUNT).toString());
							httpSession.setAttribute(Constants.OTP_COUNT, validateOtpCount + 1);
							logger.info(" === validateOtpCount in otpValidatorService === " + validateOtpCount);
						}
					} else {
						logger.info(" ==== Otp Validation Fail === ");
						validateOtpStatus = Constants.STATUS_FAIL;

						otpResponseCode = validateOtpResJson.has("responseCode")
								? validateOtpResJson.get("responseCode").toString() : "93";
						otpResponseMessage = validateOtpResJson.has("responseMessage")
								? validateOtpResJson.get("responseMessage").toString() : "FAILED : OTP is not valid";
						int validateOtpCount = Integer
								.parseInt(httpSession.getAttribute(Constants.OTP_COUNT).toString()) != 0
										? Integer.parseInt(httpSession.getAttribute(Constants.OTP_COUNT).toString())
										: 0;
						logger.info(" ==== validateOtpCount in otpValidator === " + validateOtpCount);

						httpSession.setAttribute(Constants.OTP_COUNT, validateOtpCount + 1);
						logger.info(" === validateOtpCount in otpValidatorService === " + validateOtpCount);

						logger.info(" =====  otpResponseCode ==== " + otpResponseCode + " === otpResponseMessage ==== "
								+ otpResponseMessage);

					}

					logger.info(" === User Data to prefill === " + userDetails);
					httpSession.setAttribute("userDetails", userDetails.toString());
					httpSession.setAttribute(Constants.ERROR_CODE, otpResponseCode);
					httpSession.setAttribute(Constants.ERROR_MSG, otpResponseMessage);
				} else {

					validateOtpStatus = Constants.STATUS_FAIL;
					logger.info(" === Request Id Blank  === ");

				}

			} else {

				validateOtpStatus = Constants.STATUS_FAIL;
				logger.info(" === Field Validation failed === ");
				Utility.loadNewRelicValidation("Field Validation in valdiateOTP " +" "+ fieldValidationRes.toString(), "Field validation valdiateOTP", requestCustId,Constants.VALIDATEOTP);

			}

		} catch (Exception e) {
			if (!(requestCustId.isEmpty())) {
				dbManipulation.recordExeption("VALIDATE_OTP_SERVICE", "Exception due to internal call", requestCustId);
			}
			validateOtpStatus = Constants.STATUS_FAIL;
			Utility.loadNewRelicException(e.toString(), "validateOtp service", "", requestCustId);
			logger.error(" ==== Exception in otpValidatorService ==== ", e);
		}

		logger.info(" ==== Validate Otp Status in otpValidatorService === " + validateOtpStatus);

		return validateOtpStatus;
	}

	@Override
	public String otpValidatorServiceFDSLF(String validateOtpReqJson, HttpSession httpSession,
			HttpServletRequest request) {

		String validateOtpStatus = "";
		String requestCustId = "";
		try {

			logger.info(" ==== validateOtpReqJson in otpValidatorService === " + validateOtpReqJson);
			logger.info(" ==== httpSession id in otpValidatorService === " + httpSession.getId());

			JsonResponse fieldValidationRes = formFieldValidation.otpFieldsValidation(validateOtpReqJson);
			JSONObject validateOtpJson = new JSONObject(validateOtpReqJson);
			requestCustId = validateOtpJson.has("fdcNumber") ? validateOtpJson.get("fdcNumber").toString() : "";

			if (Constants.STATUS_SUCCESS.equalsIgnoreCase(fieldValidationRes.getStatus())) {

				String otpResponseCode = "";
				String otpResponseMessage = "";
				String fullnamesfdc = "";
				String userOtp = validateOtpJson.get(Constants.USER_OTP) == null ? "NA"
						: validateOtpJson.get(Constants.USER_OTP).toString();
				String userMobileNumber = validateOtpJson.get(Constants.USER_MOBILE_NUMBER) == null ? "NA"
						: validateOtpJson.get(Constants.USER_MOBILE_NUMBER).toString();
				String requestId = (String) httpSession.getAttribute(Constants.OTP_REQUEST_ID) == null ? "NA"
						: (String) httpSession.getAttribute(Constants.OTP_REQUEST_ID);
				String dateOfBirth = httpSession.getAttribute(Constants.DATE_OF_BIRTH) == null ? ""
						: (String) httpSession.getAttribute(Constants.DATE_OF_BIRTH);
				String otpSubmittedTime = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
						.format(Calendar.getInstance().getTime());

				if (!"NA".equalsIgnoreCase(requestId)) {
					String validateOtpRes = apiIntegration.validateOtpApi(userOtp, userMobileNumber, requestId,
							requestCustId, request,"");
					logger.info(" === validateOtpRes in otpValidatorService === " + validateOtpRes);

					JSONObject validateOtpResJson = new JSONObject(validateOtpRes);
					JSONObject userDetails = new JSONObject();

					if (validateOtpResJson.has("status") && validateOtpResJson.has("message")) {

						otpResponseCode = validateOtpResJson.get("status") == null ? ""
								: validateOtpResJson.get("status").toString();
						otpResponseMessage = validateOtpResJson.get("message") == null ? ""
								: validateOtpResJson.get("message").toString();
						int validateOtpCount = Integer
								.parseInt(httpSession.getAttribute(Constants.OTP_COUNT).toString()) != 0
										? Integer.parseInt(httpSession.getAttribute(Constants.OTP_COUNT).toString())
										: 0;
						logger.info(" ==== validateOtpCount in otpValidator === " + validateOtpCount);

						httpSession.setAttribute(Constants.OTP_COUNT, validateOtpCount);

						logger.info(" =====  otpResponseCode ==== " + otpResponseCode + " === otpResponseMessage ==== "
								+ otpResponseMessage);

						if ("success".equalsIgnoreCase(otpResponseCode)
								&& "Done".equalsIgnoreCase(otpResponseMessage)) {
							JSONObject partialSfdcResponse;
							logger.info(" ==== Otp Validation Successful === ");
							httpSession.removeAttribute(Constants.OTP_REQUEST_ID);
							logger.info(" ==== Removed Request Id from session after validating successfully === ");
							validateOtpStatus = Constants.STATUS_SUCCESS;

							logger.info(" ==== Dedupe Request in otpValidatorService ==== ");
							
							String customerId = (String) httpSession.getAttribute("customerId");
							logger.info(" === Customer Id in otpValidator from session === " + customerId);

							String dedupeResponse = custIdIntegration.customerCheckEtbNtb(userMobileNumber, dateOfBirth,"","validateOtpForFDSLF");
							logger.info(" === dedupeResponse in otpValidatorService ===== " + dedupeResponse);

							JSONObject dedupeResponseJson = new JSONObject(dedupeResponse);
							String dedupeCustType = dedupeResponseJson.get(Constants.DEDUPE_CUST_TYPE) == null ? "NTB"
									: dedupeResponseJson.get(Constants.DEDUPE_CUST_TYPE).toString();

							httpSession.setAttribute("dedupeCustType", dedupeCustType);
							httpSession.setAttribute(Constants.USER_MOBILE_NUMBER, userMobileNumber);
							httpSession.setAttribute(Constants.DATE_OF_BIRTH, dateOfBirth);

							httpSession.setAttribute("fdUserMobileNumber", userMobileNumber);
							httpSession.setAttribute("fdDateOfBirth", dateOfBirth);
							httpSession.setAttribute("fdcNumber", customerId);
							FixedDepositData fixedDepositData = fixedDepositDao.getFixedDepositData(customerId);
							String customerIdFdproduct = dedupeResponseJson.has(Constants.CUSTOMERID)
									? dedupeResponseJson.get(Constants.CUSTOMERID).toString() : "NA";
							logger.info(" === dedupeResponse in customerId Fd product ===== " + customerIdFdproduct);
							if (Constants.DEDUPE_ETB.equalsIgnoreCase(dedupeCustType)) {
								String productFD = dedupeResponseJson.has(Constants.PRODUCT)
										? dedupeResponseJson.get(Constants.PRODUCT).toString() : "NA";
								userDetails.put(Constants.CUSTIDARRAY,
										dedupeResponseJson.getJSONArray(Constants.CUSTIDARRAY).toString());

								httpSession.setAttribute("customIDForManuplation",
										dedupeResponseJson.getJSONArray(Constants.CUSTIDARRAY).toString());
								httpSession.setAttribute("fdproductForID", productFD);

								userDetails.put(Constants.PRODUCT, productFD);
								userDetails.put(Constants.DATE_OF_BIRTH, dateOfBirth);
								userDetails.put(Constants.USER_MOBILE_NUMBER, userMobileNumber);

								if (Constants.FD.equalsIgnoreCase(productFD)
										|| Constants.FD_BLANK.equalsIgnoreCase(productFD)) {

									String addressVal = this.custIdIntegration.getDataToPrefill(userMobileNumber,customerIdFdproduct,"","validateOtpForFDSLF");
									logger.info(" == address Response in otpValidatorService === " + addressVal);
									JSONObject addressJson = new JSONObject(addressVal);
									String addressPrefillStatus = addressJson.has("PIDATAAPI")
											? addressJson.get("PIDATAAPI").toString() : "fail";
									String address = "success".equalsIgnoreCase(addressPrefillStatus)
											? addressJson.get(Constants.ADDRESS_1).toString() : "";
									logger.info(" == address Response in otpValidatorService === " + address);

									JSONObject prefillResponse = dedupeResponseJson.getJSONObject("userDetails");

									String prefillDataStatus = prefillResponse.has(Constants.PIDATA_API)
											? prefillResponse.get(Constants.PIDATA_API).toString()
											: Constants.STATUS_FAIL;
									logger.info(
											" === prefillDataStatus in otpValidatorService === " + prefillDataStatus);

									String fullName = prefillResponse.get(Constants.FULL_NAME) == null ? "NA"
											: prefillResponse.get(Constants.FULL_NAME).toString();
									httpSession.setAttribute(Constants.FULL_NAME, fullName);
									fullnamesfdc = fullName;
									String gender = prefillResponse.get("gender") == null ? "NA"
											: prefillResponse.get("gender").toString();

									String city = prefillResponse.get(Constants.CITY) == null ? "NA"
											: prefillResponse.get(Constants.CITY).toString();
									String pincode = prefillResponse.get(Constants.PIN) == null ? "NA"
											: prefillResponse.get(Constants.PIN).toString();
									String state = "";
									String email = prefillResponse.get(Constants.EMAIL) == null ? "NA"
											: prefillResponse.get(Constants.EMAIL).toString();
									String pan = prefillResponse.get(Constants.PAN) == null ? "NA"
											: prefillResponse.get(Constants.PAN).toString();
									String custType = dedupeResponseJson.get(Constants.CUST_TYPE) == null ? "NA"
											: dedupeResponseJson.get(Constants.CUST_TYPE).toString();

									userDetails.put(Constants.FULL_NAME, fullName);
									userDetails.put(Constants.ADDRESS, address);
									userDetails.put(Constants.EXISTING_CUST_ID, customerIdFdproduct);
									userDetails.put(Constants.PIN, pincode);
									userDetails.put(Constants.CITY, city);
									userDetails.put(Constants.EMAIL, prefillResponse.get(Constants.EMAIL) == null ? "NA"
											: prefillResponse.get(Constants.EMAIL));
									userDetails.put(Constants.PAN, prefillResponse.get(Constants.PAN) == null ? "NA"
											: prefillResponse.get(Constants.PAN));

									userDetails.put(Constants.DEDUPE_CUST_TYPE, dedupeCustType);

									userDetails.put(Constants.CUST_TYPE,
											dedupeResponseJson.get(Constants.CUST_TYPE) == null ? "NA"
													: dedupeResponseJson.get(Constants.CUST_TYPE));

									httpSession.setAttribute("fdFullname", fullName);
									httpSession.setAttribute("fdAddress", address);
									httpSession.setAttribute("fdExistingCustId", customerIdFdproduct);
									httpSession.setAttribute("fdPincode", pincode);
									httpSession.setAttribute("fdcity", city);
									httpSession.setAttribute("fdEmail", email);
									httpSession.setAttribute("fdPAN", pan);

									httpSession.setAttribute("fdDedupeCustType", dedupeCustType);
									httpSession.setAttribute("fdCustType", custType);

									int reqPin;

									if (!("NA".equalsIgnoreCase(pincode))) {

										reqPin = Integer.parseInt(pincode);
										FDPincodeMaster fdPiMast = fixedDepositDao.getcitystatename(reqPin);

										if (fdPiMast != null) {
											city = fdPiMast.getCity();
											state = fdPiMast.getState();

										} else {
											city = "NA";
											state = "NA";
										}
									}

									fixedDepositData.setCity(city);
									fixedDepositData.setPinCode(pincode);
									fixedDepositData.setState(state);
									fixedDepositData.setGender(gender);

									fixedDepositData.setFullName(fullName);
									fixedDepositData.setPanCard(pan);
									fixedDepositData.setEmailAddress(email);
									fixedDepositData.setAddress(address.replaceAll(",null", ""));
									logger.info(" ===SET data in db setExistingCustId === " + customerIdFdproduct);
									fixedDepositData.setExistingCustId(customerIdFdproduct);
									fixedDepositData.setKycVerifyStatus("DEDUPE_SUCCESS");
								}

								/*
								 * String bannerFullname =
								 * fixedDepositData.getFdslfFullName() == null ?
								 * "Dummy Dummy" :
								 * fixedDepositData.getFdslfFullName();
								 * 
								 * fullnamesfdc=(Constants.FD.equalsIgnoreCase(
								 * productFD) ||
								 * Constants.FD_BLANK.equalsIgnoreCase(productFD
								 * ))?fullnamesfdc:bannerFullname;
								 * partialSfdcResponse =
								 * partialSfdcService(fixedDepositData,
								 * fullnamesfdc,"STEP2"); logger.
								 * info(" === Partial SFDC Response in otp Controller === "
								 * + partialSfdcResponse);
								 * 
								 * String sfdcStatus =
								 * partialSfdcResponse.get("IsSuccess") == null
								 * ? "fail" :
								 * partialSfdcResponse.get("IsSuccess").toString
								 * (); String sfdcRecordId =
								 * partialSfdcResponse.get("FdSfdcId") == null ?
								 * "fail" :
								 * partialSfdcResponse.get("FdSfdcId").toString(
								 * );
								 * 
								 * logger.info(" ===  Partial SFDC Status === "
								 * + sfdcStatus + " === Partial SFDC Id ==== " +
								 * sfdcRecordId);
								 * 
								 * fixedDepositData.setSfdcRemarks(sfdcStatus);
								 * fixedDepositData.setSfdcRecordId(sfdcRecordId
								 * );
								 */
								fixedDepositData.setRemarkCustType("DEDUPE");

								fixedDepositData.setFdExistingCustID(
										Constants.FD.equalsIgnoreCase(productFD) ? customerIdFdproduct : "NA");

							} else {
								userDetails.put(Constants.DEDUPE_CUST_TYPE, dedupeCustType);
								String fullName = "Dummy Dummy";
								/*
								 * partialSfdcResponse =
								 * partialSfdcService(fixedDepositData,fullName,
								 * "STEP2"); String sfdcStatus =
								 * partialSfdcResponse.get("IsSuccess") == null
								 * ? "fail" :
								 * partialSfdcResponse.get("IsSuccess").toString
								 * (); String sfdcRecordId =
								 * partialSfdcResponse.get("FdSfdcId") == null ?
								 * "fail" :
								 * partialSfdcResponse.get("FdSfdcId").toString(
								 * );
								 * fixedDepositData.setSfdcRemarks(sfdcStatus);
								 * fixedDepositData.setSfdcRecordId(sfdcRecordId
								 * );
								 */

								httpSession.setAttribute("fdFullname", fixedDepositData.getFdslfFullName());
								httpSession.setAttribute("fdPincode", fixedDepositData.getPinCode());
							}

							fixedDepositData.setDedupeCustomerType(dedupeCustType);
							fixedDepositData.setOtpSubmittedTime(otpSubmittedTime);
							fixedDepositData.setOriginalValidateOtpSubmitTime(otpSubmittedTime);
							fixedDepositData.setJourneyStepName("VALIDATEOTP");

							String formUrl = Utility.getPropertyFileValue("formUrl").trim();
							String mainLinkBitly = formUrl + "" + fixedDepositData.getInvestmentType();
							String BrokerBitlyLink = formUrl + "" + fixedDepositData.getInvestmentType()
									+ "&PartnerCode=" + fixedDepositData.getPartnerCode();
							mainLinkBitly = "NA".equalsIgnoreCase(fixedDepositData.getPartnerCode()) ? mainLinkBitly
									: BrokerBitlyLink;

							JSONObject smsRequest = new JSONObject();
							smsRequest.put("form_Id", "1501075838010");
							smsRequest.put("mobile_number_mobileTrue", userMobileNumber);
							smsRequest.put("digital_customer_id", customerId);
							smsRequest.put("digital_CERT_Number", customerId);
							smsRequest.put("bitlylink__bitlyLinkId", mainLinkBitly);

							logger.info(" === smsRequest in otpValidatorService for  resumeJouney==== " + smsRequest);
							// String resumeJouneysmsStatus =
							// responsysIntegration.sendSms(smsRequest.toString());

							// logger.info(" ===== smsStatus in
							// otpValidatorService for resumeJouney ===== " +
							// resumeJouneysmsStatus);
							// fixedDepositData.setResumeJourneySmsStatus(resumeJouneysmsStatus);
							String updateStatus = fixedDepositDao.updateFixedDeposit(fixedDepositData);
							logger.info(" == Updation status of cust type and existing cust id === " + updateStatus);

						} else {
							validateOtpStatus = Constants.STATUS_FAIL;
							validateOtpCount = Integer
									.parseInt(httpSession.getAttribute(Constants.OTP_COUNT).toString());
							httpSession.setAttribute(Constants.OTP_COUNT, validateOtpCount + 1);
							logger.info(" === validateOtpCount in otpValidatorService === " + validateOtpCount);
						}
					} else {
						logger.info(" ==== Otp Validation Fail === ");
						validateOtpStatus = Constants.STATUS_FAIL;

						otpResponseCode = validateOtpResJson.has("responseCode")
								? validateOtpResJson.get("responseCode").toString() : "93";
						otpResponseMessage = validateOtpResJson.has("responseMessage")
								? validateOtpResJson.get("responseMessage").toString() : "FAILED : OTP is not valid";
						int validateOtpCount = Integer
								.parseInt(httpSession.getAttribute(Constants.OTP_COUNT).toString()) != 0
										? Integer.parseInt(httpSession.getAttribute(Constants.OTP_COUNT).toString())
										: 0;
						logger.info(" ==== validateOtpCount in otpValidator === " + validateOtpCount);

						httpSession.setAttribute(Constants.OTP_COUNT, validateOtpCount + 1);
						logger.info(" === validateOtpCount in otpValidatorService === " + validateOtpCount);

						logger.info(" =====  otpResponseCode ==== " + otpResponseCode + " === otpResponseMessage ==== "
								+ otpResponseMessage);

					}

					logger.info(" === User Data to prefill === " + userDetails);
					httpSession.setAttribute("userDetails", userDetails.toString());
					httpSession.setAttribute(Constants.ERROR_CODE, otpResponseCode);
					httpSession.setAttribute(Constants.ERROR_MSG, otpResponseMessage);

				} else {
					validateOtpStatus = Constants.STATUS_FAIL;
					logger.info(" === Request Id Blank  === ");
				}
			} else {

				validateOtpStatus = Constants.STATUS_FAIL;
				logger.info(" === Field Validation failed === ");
				Utility.loadNewRelicValidation("Field Validation in valdiateOTP" + " "
						+ fieldValidationRes.toString(), "Field validation valdiateOTP", requestCustId,"");

			}
		} catch (Exception e) {
			if (!(requestCustId.isEmpty())) {
				dbManipulation.recordExeption("FDSLVALIDATE_OTP_SERVICE", "Exception due to internal call",
						requestCustId);
			}
			validateOtpStatus = Constants.STATUS_FAIL;
			logger.error(" ==== Exception in otpValidatorService ==== ", e);
		}

		logger.info(" ==== Validate Otp Status in otpValidatorService === " + validateOtpStatus);

		return validateOtpStatus;
	}

	@Override
	public OtpResponse setOtpResponseService(String generateOtpRes) {

		OtpResponse response = new OtpResponse();
		try {

			logger.info(" ===== generateOtpRes in setOtpResponseService === " + generateOtpRes);

			@SuppressWarnings("unchecked")
			LinkedHashMap<String, String> otpResponse = new ObjectMapper().readValue(generateOtpRes,
					LinkedHashMap.class);

			for (Entry<String, String> otpRes : otpResponse.entrySet()) {

				String key = otpRes.getKey();
				String value = otpRes.getValue();

				switch (key) {
				case "errorCode":
					if (!value.equals("")) {
						response.setErrorCode(value);
					}
					break;
				case "errorMsg":
					if (!value.equals("")) {
						response.setErrorMsg(value);
					}
					break;
				case "mobile_No__c":
					if (!value.equals("")) {
						response.setMobile(value);
					}
					break;
				case "requestID":
					if (!value.equals("")) {
						response.setRequestId(value);
					}
					break;

				default:
					break;
				}

			}

			logger.info(" === Generated otp Resposne in setOtpResponseService === " + response);

		} catch (Exception e) {
			logger.error(" === Exception in setOtpResponseService == ", e);
		}
		return response;
	}

	@Override
	public String savePartialDataService(String otpRequest) {

		logger.info(" === otpRequest in OtpServiceImpl === " + otpRequest);
		String customerId = "";

		try {
			@SuppressWarnings("unchecked")
			LinkedHashMap<String, String> requestValues = new ObjectMapper().readValue(otpRequest, LinkedHashMap.class);

			FixedDepositData depositData = new FixedDepositData();

			for (Entry<String, String> keys : requestValues.entrySet()) {

				String key = keys.getKey();
				String value = keys.getValue();

				logger.info(" === Key === " + key + " === Value == " + value);

				switch (key) {

				case "mobileNumber":
					if (!value.equals("")) {
						depositData.setMobileNumber(value);
					}
					break;

				case "dateOfBirth":
					if (!value.equals("")) {
						/*
						 * Date dob = new
						 * SimpleDateFormat("dd-MM-yyyy").parse(value);
						 */
						Date dob = new SimpleDateFormat("dd/MM/yyyy").parse(value);
						SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
						String newDate = format.format(dob);
						logger.info("----DOB Set in DB --------" + newDate);
						depositData.setDateOfBirth(newDate);
					}
					break;

				case "pageURL":
					if (!value.equals("")) {
						depositData.setPageUrl(value);
					}
					break;

				case "device":
					if (!value.equals("")) {
						depositData.setDevice(value);
						depositData.setDeviceOriginal(value);
					}
					break;

				case "unqiuecodeId":
					if (!value.equals("")) {
						depositData.setUniqueId(value);
					}
					break;

				case "cookieID":
					if (!value.equals("")) {
						depositData.setCookieId(value);
					}
					break;

				case "clientID":
					if (!value.equals("")) {
						depositData.setClientId(value);
					}
					break;

				case "utm_source_utmTrue":
					if (!value.equals("")) {
						depositData.setUtmSource(value);
						depositData.setUtmSourceOriginal(value);
					}
					break;

				case "utm_campaign_utmTrue":
					if (!value.equals("")) {
						depositData.setUtmCampaign(value);
						depositData.setUtmCampaignOriginal(value);
					}
					break;

				case "utm_medium_utmTrue":
					if (!value.equals("")) {
						depositData.setUtmMedium(value);
						depositData.setUtmMediumOriginal(value);
					}
					break;

				case "utm_keyword_utmTrue":
					if (!value.equals("")) {
						depositData.setUtmkeyword(value);
					}
					break;

				case "utm_content_utmTrue":
					if (!value.equals("")) {
						depositData.setUtmContent(value);
					}
					break;

				case "utm_term":
					if (!value.equals("")) {
						depositData.setUtmTerm(value);
					}
					break;

				case "salutation":
					if (!value.equals("")) {
						depositData.setSalutation(value);
					}
					break;

				case "formName":
					if (!value.equals("")) {
						depositData.setFormName(value);
					}
					break;

				case "formID":
					if (!value.equals("")) {
						depositData.setFormId(value);
					}
					break;

				case "partnerName":
					if (!value.equals("")) {
						depositData.setPartnerName(value);
					}
					break;

				case "partnerCode":
					if (!value.equals("")) {
						depositData.setPartnerCode(value);
					}
					break;

				case "rdplan":
					if (!value.equals("")) {
						depositData.setPartnerCode(value);
					}
					break;

				case "last_clicked_clicktrue":
					if (!value.equals("")) {
						depositData.setLastClick(value);
					}
					break;

				case "timeOfLogging":
					if (!value.equals("")) {
						String logTime = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
								.format(Calendar.getInstance().getTime());
						depositData.setTimeOfLogging(logTime);
					}
					break;

				case "otpTriggeredTime":
					if (!value.equals("")) {
						String otpTriggeredTime = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
								.format(Calendar.getInstance().getTime());
						depositData.setOtpTriggeredTime(otpTriggeredTime);
						depositData.setOriginalOtpTriggerdTime(otpTriggeredTime);
					}
					break;

				case "ipAdress":
					if (!value.equals("")) {
						try {
							int length = value.indexOf("=");
							logger.info(" === ipAdress  OtpServiceImpl === " + value.substring(length + 1));
							depositData.setIpAdress(value.substring(length + 1));

							depositData.setIpAdress(value);
						} catch (Exception e) {
							logger.info("----Exception in ip save --------");
							depositData.setIpAdress("NA");
						}
					}
					break;

				case "gclid":
					if (!value.equals("")) {
						depositData.setGclid(value);
					}
					break;

				case "fdslf_fullname":
					if (!value.equals("")) {
						depositData.setFdslfFullName(value);
					}
					break;

				case "formname_form":
					if (!value.equals("")) {
						depositData.setFdslfFormName(value);
					}
					break;

				case "form_Id":
					if (!value.equals("")) {
						depositData.setFdslfFormId(value);
					}
					break;

				case "pageName_form":
					if (!value.equals("")) {
						depositData.setFdslfPageName(value);
					}
					break;

				case "fdslf_pincode":
					if (!value.equals("")) {
						depositData.setPinCode(value);
						try {
							FDPincodeMaster fdPiMast = fixedDepositDao.getcitystatename(Integer.parseInt(value));
							logger.info("=======Pincode at Get otp Stage===" + value + "====data in FDPincodeMaster===="
									+ fdPiMast);
							if (fdPiMast != null) {
								depositData.setCity(fdPiMast.getCity());
								depositData.setState(fdPiMast.getState());
							}
						} catch (Exception e) {
							logger.error("=====Exception in at Stage 1 FDPincodeMaster===", e);
							depositData.setCity("NA");
							depositData.setState("NA");
						}
					}
					break;

				case "investmentType":
					if (!value.equals("")) {
						depositData.setInvestmentType(value);
					}
					break;

				case "gaID":
					if (!value.equals("")) {
						depositData.setGaID(value);
					}
					break;

				case "whatsAppCheck":
					if (!value.equals("")) {
						depositData.setWhatsAppConsentStatus(value);
					}
					break;

				default:
					break;
				}

			}

			String fromSubmissionDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
					.format(Calendar.getInstance().getTime());
			depositData.setCreatedOn(fromSubmissionDate);
			depositData.setOriginalcreatedOn(fromSubmissionDate);
			InetAddress ip = InetAddress.getLocalHost();

			logger.info(" ===InetAddress in OtpServiceImpl === " + ip.getHostAddress());
			depositData.setBflServerIpAddress(ip.getHostAddress());
			synchronized (this) {
				customerId = fixedDepositDao.customerIdGenerator();
				logger.info(" === Generator Customer Id in OtpServiceImpl === " + customerId);
			}

			depositData.setCustomerId(customerId);

			logger.info(" === before saving data ==== " + depositData.toString());
			depositData.setJourneyStepName("GETOTP");
			String dbSaveStatus = fixedDepositDao.saveFixedDepositData(depositData);
			logger.info(" === dbSaveStatus in OtpServiceImpl == " + dbSaveStatus);

			FixedDepositData fixedDepositData = fixedDepositDao.getFixedDepositData(customerId);
			
			String bob = fixedDepositData.getMobileNumber() == null ? "" : fixedDepositData.getMobileNumber();
			logger.info("=====inside thread Start=======" + LocalDateTime.now().toString() + "============" + bob);

			Runnable runnable = () -> {

				try {

					logger.info("=====inside thread Started=======" + LocalDateTime.now().toString() + "============"
							+ bob);

					TimeUnit.MINUTES.sleep(20);

					String cusID = fixedDepositData.getCustomerId() == null ? "" : fixedDepositData.getCustomerId();

					FixedDepositData fixedDepositDataNew = fixedDepositDao.getFixedDepositData(cusID);
					logger.info("============Thread date for partial SFDC to check=====" + fixedDepositDataNew);
					logger.info("====thread Processing===" + LocalDateTime.now().toString()
							+ "==========================" + bob);
					String fullnamesfdc = fixedDepositDataNew.getFdslfFullName() == null ? "Dummy Dummy"
							: fixedDepositDataNew.getFdslfFullName().toString();

					JSONObject partialSfdcResponse = partialSfdcService(fixedDepositDataNew, fullnamesfdc, "STEP1","generateOtp");
					logger.info("=========SFDC Response in generateOtp Call while save method========="
							+ partialSfdcResponse.toString());

					String sfdcStatus = partialSfdcResponse.get("IsSuccess") == null ? "fail"
							: partialSfdcResponse.get("IsSuccess").toString();
					String sfdcRecordId = partialSfdcResponse.get("FdSfdcId") == null ? "fail"
							: partialSfdcResponse.get("FdSfdcId").toString();

					logger.info(" ===  Partial SFDC Status === " + sfdcStatus + " === Partial SFDC Id ==== "
							+ sfdcRecordId);

					fixedDepositDataNew.setSfdcRemarks(sfdcStatus);
					fixedDepositDataNew.setSfdcRecordId(sfdcRecordId);
					String updatedStatus = fixedDepositDao.updateFixedDeposit(fixedDepositDataNew);
					logger.info(" == Updation status of cust type and existing cust id === " + updatedStatus);

				} catch (InterruptedException e) {
					logger.error(" ==== exception in 30 min wait for partial SFDC ==== ", e);
				}

			};

			new Thread(runnable).start();

		} catch (Exception e) {

			logger.error(" ==== Exception in savePartialDataService ==== ", e);
		}

		return customerId;
	}

	@Override
	public JSONObject partialSfdcService(FixedDepositData fixedDepositData, String fullName, String stepNo,String contextCalled) {

		logger.info(" === fixedDepositData in partialSfdcService ==== " + fixedDepositData);
		JSONObject sfdcRes = new JSONObject();
		String sfdcRequestJsonStr="";
		try {
			String transationDetail = fixedDepositData.getTransactionStatus() == null ? "NA": fixedDepositData.getTransactionStatus();
			if (!(Constants.TRANSACTION_SUCCESS.equalsIgnoreCase(transationDetail))) {

				String fullnameFinal = fixedDepositData.getFullName() == null ? "Dummy Dummy": fixedDepositData.getFullName();

				String mobileNumber = fixedDepositData.getMobileNumber() == null ? "NA": fixedDepositData.getMobileNumber();
				String dateOfBirth = fixedDepositData.getDateOfBirth() == null ? "NA": fixedDepositData.getDateOfBirth();
				String utmCampaign = fixedDepositData.getUtmCampaign() == null ? "NA": fixedDepositData.getUtmCampaign();
				String utmMedium = fixedDepositData.getUtmMedium() == null ? "NA" : fixedDepositData.getUtmMedium();
				String utmKeyword = fixedDepositData.getUtmkeyword() == null ? "NA" : fixedDepositData.getUtmkeyword();
				String utmSource = fixedDepositData.getUtmSource() == null ? "NA" : fixedDepositData.getUtmSource();
				String pageUrl = fixedDepositData.getPageUrl() == null ? "NA" : fixedDepositData.getPageUrl();
				String device = fixedDepositData.getDevice() == null ? "NA" : fixedDepositData.getDevice();
				String clientId = fixedDepositData.getClientId() == null ? "NA" : fixedDepositData.getClientId();
				String rdplan = fixedDepositData.getRdpLan() == null ? "" : fixedDepositData.getRdpLan();
				String brcode = fixedDepositData.getPartnerCode() == null ? "" : fixedDepositData.getPartnerCode();
				String pincode = fixedDepositData.getPinCode() == null ? "" : fixedDepositData.getPinCode();
				String city = fixedDepositData.getCity() == null ? "NA" : fixedDepositData.getCity();
				String gclld = fixedDepositData.getGclid() == null ? "" : fixedDepositData.getGclid();
				String sourceBy = "Broker";
				String sfdcApiRecordId = fixedDepositData.getSfdcRecordId() == null ? "NA": fixedDepositData.getSfdcRecordId();
				pageUrl = pageUrl.length() >= 150 ? pageUrl.replace(" ", "").substring(0, 150) : pageUrl;

				String[] fullNameArr = fullnameFinal.split(" ");
				logger.info(" === fullNameArr in ekycFdSfdcService  ==== " + Arrays.toString(fullNameArr));
				String fName = fullNameArr[0];
				String lName = fullNameArr[fullNameArr.length - 1];

				if (!("NA".equalsIgnoreCase(dateOfBirth))) {
					dateOfBirth = Utility.dateFormerter(dateOfBirth);
				}

				if ("NA".equalsIgnoreCase(brcode)) {
					brcode = "";
					sourceBy = "Branch";
				}
				if ("NA".equalsIgnoreCase(pincode)) {
					pincode = "";
				}

				AdressDetailsList adressDetailsList = new AdressDetailsList();
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
				objApplDetail.setPAN("");
				objApplDetail.setIdentityDocNo("");
				objApplDetail.setAnnualIncome("");
				objApplDetail.setDemogUpdate("No");

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
				objFDDetails.setFullName(fullnameFinal);
				objFDDetails.setExistingCustID("");
				objFDDetails.setUTMCAMAPAIGN(utmCampaign);
				objFDDetails.setIsComplete("N");
				objFDDetails.setGclld(gclld);
				if ("STEP2".equalsIgnoreCase(stepNo)) {
					objFDDetails.setFDRecIdToUpdate(sfdcApiRecordId);
				}

				FDRecordsObjList fdRecordsObjList = new FDRecordsObjList();
				fdRecordsObjList.setObjApplDetails(Arrays.asList(objApplDetail));
				fdRecordsObjList.setRecUniqueKey("522");
				fdRecordsObjList.setObjFDDetails(objFDDetails);

				RecWrapperFD recWrapperFD = new RecWrapperFD();
				recWrapperFD.setFDRecordsObjList(Arrays.asList(fdRecordsObjList));

				SfdcRequest sfdcRequest = new SfdcRequest();
				sfdcRequest.setRecWrapperFD(recWrapperFD);

				ObjectMapper mapper = new ObjectMapper();
				sfdcRequestJsonStr = mapper.writeValueAsString(sfdcRequest);
				logger.info(" === sfdcRequest in partialSfdcService ==== " + sfdcRequestJsonStr);

				sfdcRes = sfdcIntegration.partialSfdc(sfdcRequestJsonStr, fixedDepositData.getCustomerId(),contextCalled);
				logger.info(" === sfdcRes in partialSfdcService ==== " + sfdcRes);

			} else {
				JSONObject apiResJson = new JSONObject();
				apiResJson.put("IsSuccess", "Transation sucess");
				apiResJson.put("FdSfdcId", "CheckFinalSFDC");
				sfdcRes = apiResJson;
			}

		} catch (Exception e) {
			if (!(fixedDepositData.getCustomerId().isEmpty())) {
				dbManipulation.recordExeption("PARTIAL_SFDC_SERVICE", "Exception due to internal call",
						fixedDepositData.getCustomerId());
			}
			Utility.loadNewRelicException(e.toString(), "partialSfdcService", "", fixedDepositData.getCustomerId());
			logger.error(" ==== Exception in partialSfdcService ==== ", e);
		}

		return sfdcRes;
	}

	@Override
	public String eventHubOtpCall(String customerId, String stepNoData) {

		FixedDepositData fixedDepositData = fixedDepositDao.getFixedDepositData(customerId);

		String eventHubString = formDataService.eventHubService(fixedDepositData, stepNoData);

		return eventHubString;
	}

	@Override
	public JSONObject resumeOtpService(String generateOtpReq) {

		JSONObject validationStatus = new JSONObject();
		try {

			logger.info(" == Rquest check if user resumed or not Method==== " + generateOtpReq);
			JSONObject generateOtpReqJson = new JSONObject(generateOtpReq);
			String mobileNumber = generateOtpReqJson.getString(Constants.MOBILE_NUMBER);
			logger.info(" === Mobile Number in resumeService === " + mobileNumber);
			FixedDepositData depositData = fixedDepositDao.getFixedDepositDataByMobile(mobileNumber);
			if (depositData != null) {

				logger.info(" == depositData in resumeOtpService ==== " + depositData);

				String createdOn = depositData.getOriginalcreatedOn() == null ? "" : depositData.getOriginalcreatedOn();
				logger.info(" ====userLast JourneyStep Date === " + createdOn);
				SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
				SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");

				Date date1 = myFormat.parse(createdOn);
				Date date2 = formatter.parse(new Date().toString());
				long diff = date2.getTime() - date1.getTime();
				long journeyDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
				if (journeyDays < 10) {
					logger.info(" == Resume JourneyFlag depositData in resumeOtpService ==== "
							+ depositData.getJourneyStepName());
					String lastStep = depositData.getJourneyStepName() == null ? "NA"
							: "null".equalsIgnoreCase(depositData.getJourneyStepName()) ? "NA"
									: depositData.getJourneyStepName();
					String kycVerifyStatus = depositData.getKycVerifyStatus() == null ? "NA"
							: depositData.getKycVerifyStatus();
					logger.info(" ====userLastJourneyStep kycVerifyStatus === " + kycVerifyStatus);
					String serverDocumentPath = depositData.getServrFilePath() == null ? "notFound"
							: depositData.getServrFilePath();
					logger.info(" ====userLastJourney serverDocumentPath === " + serverDocumentPath);
					String remakCust = depositData.getRemarkCustType() == null ? "NA" : depositData.getRemarkCustType();
					logger.info(" ====userLastJourney remakCust === " + remakCust);
					if ("NA".equalsIgnoreCase(lastStep)) {
						validationStatus.put("validationStatus", "stepNotFound");
						logger.info(" === Field Validation failed  resumeOtpService=== " + validationStatus);
					} else if ("GETOTP".equalsIgnoreCase(lastStep)) {
						validationStatus.put("validationStatus", "noDataFoundForuser");
						logger.info(" === Field Validation failed === " + validationStatus);

					} else if ("VALIDATEOTP".equalsIgnoreCase(lastStep)
							&& !(("DEDUPE_SUCCESS".equalsIgnoreCase(kycVerifyStatus)
									|| "CKYC_SUCCESS".equalsIgnoreCase(kycVerifyStatus)
									|| "OKYC_SUCCESS".equalsIgnoreCase(kycVerifyStatus)))) {
						validationStatus.put("validationStatus", "noDataFoundForuser");
						logger.info(" === Field Validation failed === " + validationStatus);

					} else {
						logger.info(" == =====Transction status in resumeOtpService ==== "
								+ depositData.getTransactionStatus());
						String txrStatus = depositData.getTransactionStatus() == null ? "NA"
								: depositData.getTransactionStatus();
						if ("notFound".equalsIgnoreCase(serverDocumentPath)
								&& !("DEDUPE").equalsIgnoreCase(remakCust)) {
							validationStatus.put("validationStatus", "noDataFoundForuser");
							logger.info(" === Field Validation failed === " + validationStatus);
						} else if (!("SUCCESS".equalsIgnoreCase(txrStatus))) {

							logger.info(" === Mobile Number in resumeJourneygetOTP === " + mobileNumber);
							String customerId = depositData.getCustomerId() == null ? "NA"
									: depositData.getCustomerId();
							String dateOfBirth = depositData.getDateOfBirth() == null ? ""
									: depositData.getDateOfBirth();
							logger.info(" === dateOfBirth in resumeJourneygetOTP === " + dateOfBirth);
							validationStatus.put("validationStatus", "resumeUser");
							validationStatus.put("custId", customerId);
						} else {
							validationStatus.put("validationStatus", Constants.STATUS_TRXDONE);
						}
					}

				} else {

					validationStatus.put("validationStatus", "beforeTenDaysJourneyFound");
				}

			} else {
				validationStatus.put("validationStatus", "noDataFoundForuser");
				logger.info(" === Field Validation failed === " + validationStatus);
			}

		} catch (Exception e) {
			validationStatus.put("validationStatus", Constants.STATUS_FAIL);
			logger.error(" === Exception while check resumed user or not === ", e);
		}
		return validationStatus;
	}

	@Override
	public JSONObject resumeRedirectService(String request, String customerId) {
		JSONObject userDetails = new JSONObject();
		try {
			logger.info(" == Request validate for resume User==== " + request);
			FixedDepositData resumeddepositData = fixedDepositDao.getFixedDepositData(customerId);
			logger.info(" == depositData in resumeJourneyValidateOtp ==== " + resumeddepositData);

			String userLastJourneyStep = resumeddepositData.getJourneyStepName() == null ? ""
					: resumeddepositData.getJourneyStepName();
			logger.info(" ====userLastJourneyStep === " + userLastJourneyStep);
			String kycVerifyStatus = resumeddepositData.getKycVerifyStatus() == null ? ""
					: resumeddepositData.getKycVerifyStatus();
			logger.info(" ====userLastJourneyStep kycVerifyStatus === " + kycVerifyStatus);

			userLastJourneyStep = ("DEDUPE_SUCCESS".equalsIgnoreCase(kycVerifyStatus)
					&& ("VALIDATEOTP".equalsIgnoreCase(userLastJourneyStep))) ? "PERSONALDETAILS" : userLastJourneyStep;
			userLastJourneyStep = ("CKYC_SUCCESS".equalsIgnoreCase(kycVerifyStatus)
					&& ("CKYCVERIFY".equalsIgnoreCase(userLastJourneyStep)
							|| "VALIDATEOTP".equalsIgnoreCase(userLastJourneyStep))) ? "PERSONALDETAILS"
									: userLastJourneyStep;
			userLastJourneyStep = ("OKYC_SUCCESS".equalsIgnoreCase(kycVerifyStatus)) ? "PERSONALDETAILS"
					: userLastJourneyStep;
			logger.info(" ====userLastJourneyStep based on kycVerifyStatus=== " + userLastJourneyStep);

			if ("CKYCVERIFY".equalsIgnoreCase(userLastJourneyStep)
					|| "NSDL failure".equalsIgnoreCase(userLastJourneyStep)
					|| "OKYC Success".equalsIgnoreCase(userLastJourneyStep)
					|| "OKYC failure".equalsIgnoreCase(userLastJourneyStep)) {
				userDetails.put(Constants.RESUME_STEP, "step2");
				userDetails.put(Constants.CUST_TYPE,
						resumeddepositData.getRemarkCustType() == null ? "NA" : resumeddepositData.getRemarkCustType());
			} else if ("PERSONALDETAILS".equalsIgnoreCase(userLastJourneyStep)
					|| "DOCUMENTUPLOAD".equalsIgnoreCase(userLastJourneyStep)) {
				String createdOn = resumeddepositData.getOriginalcreatedOn() == null ? ""
						: resumeddepositData.getOriginalcreatedOn();
				logger.info(" ====userLastJourneyStep Date === " + createdOn);
				SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
				SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");

				Date date1 = myFormat.parse(createdOn);
				Date date2 = formatter.parse(new Date().toString());
				long diff = date2.getTime() - date1.getTime();
				long journeyDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

				if (journeyDays < 10) {
					userDetails.put(Constants.RESUME_STEP, "step3");
					userDetails.put(Constants.ADDRESS,
							resumeddepositData.getAddress() == null ? "NA" : resumeddepositData.getAddress());
					userDetails.put(Constants.EXISTING_CUST_ID, resumeddepositData.getExistingCustId() == null ? "NA"
							: resumeddepositData.getExistingCustId());
					userDetails.put(Constants.CITY,
							resumeddepositData.getCity() == null ? "NA" : resumeddepositData.getCity());
					userDetails.put(Constants.EMAIL,
							resumeddepositData.getEmailAddress() == null ? "NA" : resumeddepositData.getEmailAddress());
					userDetails.put("gender",
							resumeddepositData.getGender() == null ? "NA" : resumeddepositData.getGender());
					userDetails.put(Constants.USER_MOBILE_NUMBER, resumeddepositData.getMobileNumber());

					userDetails.put("nomineeName",
							resumeddepositData.getNomineeName() == null ? "NA" : resumeddepositData.getNomineeName());
					userDetails.put("nomineeDob", resumeddepositData.getNomineeDateOfBirth() == null ? "NA"
							: resumeddepositData.getNomineeDateOfBirth());
					userDetails.put("relationWithNominee", resumeddepositData.getRelationshipWithNominee() == null
							? "NA" : resumeddepositData.getRelationshipWithNominee());
					userDetails.put("nomineePincode", resumeddepositData.getNomineePincode() == null ? "NA"
							: resumeddepositData.getNomineePincode());
					userDetails.put("nomineeAddres", resumeddepositData.getNomineeAddress() == null ? "NA"
							: resumeddepositData.getNomineeAddress());
					userDetails.put("gaurdianName", resumeddepositData.getNomineeGuardianName() == null ? "NA"
							: resumeddepositData.getNomineeGuardianName());
					userDetails.put("gaurdianAge", resumeddepositData.getNomineeGuardianAge() == null ? "NA"
							: resumeddepositData.getNomineeGuardianAge());
					userDetails.put("gaurdianAddress", resumeddepositData.getNomineeGuardianAddress() == null ? "NA"
							: resumeddepositData.getNomineeGuardianAddress());
					userDetails.put("gaurdianRealtion", resumeddepositData.getNomineeGuardianRelation() == null ? "NA"
							: resumeddepositData.getNomineeGuardianRelation());
					userDetails.put("gaurdianPincode", resumeddepositData.getNomineeGuardianPincode() == null ? "NA"
							: resumeddepositData.getNomineeGuardianPincode());
					userDetails.put("isNomineeAvaiable",
							resumeddepositData.getNomineeCheck() == null ? "NA" : resumeddepositData.getNomineeCheck());
					userDetails.put("isNomineeAddressAvailable", resumeddepositData.getNomineeAddressCheck() == null
							? "NA" : resumeddepositData.getNomineeAddressCheck());
					userDetails.put("isGaurdiunAvailable", resumeddepositData.getGaurdianCheck() == null ? "NA"
							: resumeddepositData.getGaurdianCheck());
					userDetails.put("nomineeSalutation", resumeddepositData.getNomineeSalutation() == null ? "NA"
							: resumeddepositData.getNomineeSalutation());
					userDetails.put("mainSalutation",
							resumeddepositData.getSalutation() == null ? "NA" : resumeddepositData.getSalutation());

					String remarkcustType = resumeddepositData.getRemarkCustType() == null ? "NA"
							: resumeddepositData.getRemarkCustType();
					logger.info("==========User type=====" + remarkcustType);

					SimpleDateFormat formatterDob = new SimpleDateFormat("dd/MM/yyyy");
					SimpleDateFormat formatter1DDob = new SimpleDateFormat("dd-MM-yyyy");

					String custType = resumeddepositData.getCustomerType() == null ? "NA"
							: resumeddepositData.getCustomerType();
					Date dateDOB = formatterDob.parse(
							resumeddepositData.getDateOfBirth() == null ? "NA" : resumeddepositData.getDateOfBirth());

					logger.info(formatterDob.format(dateDOB));

					String dobFormatednew = formatter1DDob.format(dateDOB);
					LocalDate currentDate = LocalDate.now();
					LocalDate birthDateFromBrowser = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd")
							.format(new SimpleDateFormat("dd-MM-yyyy").parse(dobFormatednew)));
					int ageFromBrowser = Period.between(birthDateFromBrowser, currentDate).getYears();

					if ("Senior Citizen".equalsIgnoreCase(custType) || ageFromBrowser >= 60) {
						userDetails.put(Constants.CUST_TYPE, "STB");
					} else if ("Existing Customer".equalsIgnoreCase(custType)) {
						userDetails.put(Constants.CUST_TYPE, "ETB");
					} else {
						userDetails.put(Constants.CUST_TYPE, remarkcustType);
					}

				} else {
					userDetails.put(Constants.RESUME_STEP, "step2");
					userDetails.put(Constants.CUST_TYPE, resumeddepositData.getRemarkCustType() == null ? "NA"
							: resumeddepositData.getRemarkCustType());
				}

			} else if ("SCHEMEDETAILS".equalsIgnoreCase(userLastJourneyStep)
					|| "PAYMENTFAIL".equalsIgnoreCase(userLastJourneyStep)
					|| "PAYMENTREQUEST".equalsIgnoreCase(userLastJourneyStep)) {

				String createdOn = resumeddepositData.getOriginalcreatedOn() == null ? ""
						: resumeddepositData.getOriginalcreatedOn();
				logger.info(" ====userLastJourneyStep Date === " + createdOn);
				SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
				SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");

				Date date1 = myFormat.parse(createdOn);
				Date date2 = formatter.parse(new Date().toString());
				long diff = date2.getTime() - date1.getTime();
				long journeyDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

				if (journeyDays < 10) {

					userDetails.put(Constants.RESUME_STEP, "step4");

					userDetails.put("tenor",
							resumeddepositData.getTenor() == null ? "NA" : resumeddepositData.getTenor());
					userDetails.put("interestRate",
							resumeddepositData.getInterestRate() == null ? "NA" : resumeddepositData.getInterestRate());
					userDetails.put("interestPayout", resumeddepositData.getInterestPayout() == null ? "NA"
							: resumeddepositData.getInterestPayout());
					userDetails.put("renewType",
							resumeddepositData.getFdRenewType() == null ? "NA" : resumeddepositData.getFdRenewType());
					userDetails.put("fdRenewStatus",
							resumeddepositData.getFdRenew() == null ? "NA" : resumeddepositData.getFdRenew());
					userDetails.put("customerType",
							resumeddepositData.getCustomerType() == null ? "NA" : resumeddepositData.getCustomerType());
					userDetails.put("interestPayoutType", resumeddepositData.getInterestPayoutType() == null ? "NA"
							: resumeddepositData.getInterestPayoutType());
					userDetails.put("depositAmount", resumeddepositData.getDepositAmount() == null ? "NA"
							: resumeddepositData.getDepositAmount());
					userDetails.put("numberOfDeposit", resumeddepositData.getNumberOfDeposit() == null ? "NA"
							: resumeddepositData.getNumberOfDeposit());
					userDetails.put("dateOfEachDeposit", resumeddepositData.getDateOfEachDeposit() == null ? "NA"
							: resumeddepositData.getDateOfEachDeposit());
					userDetails.put("investmentType", resumeddepositData.getInvestmentType() == null ? "NA"
							: resumeddepositData.getInvestmentType());
					userDetails.put("sdpTotalPriAmnt", resumeddepositData.getSdpTotalPriAmnt() == null ? "NA"
							: resumeddepositData.getSdpTotalPriAmnt());
					userDetails.put("fdMaturityDate", resumeddepositData.getFdMaturityDate() == null ? "NA"
							: resumeddepositData.getFdMaturityDate());
					userDetails.put("fdInterestAmnt", resumeddepositData.getFdInterestAmnt() == null ? "NA"
							: resumeddepositData.getFdInterestAmnt());
					userDetails.put("fdMaturityAmnt", resumeddepositData.getFdMaturityAmnt() == null ? "NA"
							: resumeddepositData.getFdMaturityAmnt());
					userDetails.put("bankAccountNumber", resumeddepositData.getBankAccountNumber() == null ? "NA"
							: resumeddepositData.getBankAccountNumber());
					userDetails.put("bankName",
							resumeddepositData.getBankName() == null ? "NA" : resumeddepositData.getBankName());
					userDetails.put("ifscCode",
							resumeddepositData.getIfscCode() == null ? "NA" : resumeddepositData.getIfscCode());
					userDetails.put("paymentChoice", resumeddepositData.getPaymentChoice() == null ? "NA"
							: resumeddepositData.getPaymentChoice());
					userDetails.put("maturityScheme", resumeddepositData.getMaturityScheme() == null ? "NA"
							: resumeddepositData.getMaturityScheme());
					userDetails.put("fullBankName",
							resumeddepositData.getFullBankName() == null ? "NA" : resumeddepositData.getFullBankName());
					userDetails.put("journeyStepName", resumeddepositData.getJourneyStepName() == null ? "NA"
							: resumeddepositData.getJourneyStepName());
					userDetails.put("schemeDetailsTime", resumeddepositData.getSchemeDetailsTime() == null ? "NA"
							: resumeddepositData.getSchemeDetailsTime());

					String remarkcustType = resumeddepositData.getRemarkCustType() == null ? "NA"
							: resumeddepositData.getRemarkCustType();
					logger.info("==========User type=====" + remarkcustType);

					SimpleDateFormat formatterDob = new SimpleDateFormat("dd/MM/yyyy");
					SimpleDateFormat formatter1DDob = new SimpleDateFormat("dd-MM-yyyy");

					String custType = resumeddepositData.getCustomerType() == null ? "NA"
							: resumeddepositData.getCustomerType();
					Date dateDOB = formatterDob.parse(
							resumeddepositData.getDateOfBirth() == null ? "NA" : resumeddepositData.getDateOfBirth());

					logger.info(formatterDob.format(dateDOB));

					String dobFormatednew = formatter1DDob.format(dateDOB);
					LocalDate currentDate = LocalDate.now();
					LocalDate birthDateFromBrowser = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd")
							.format(new SimpleDateFormat("dd-MM-yyyy").parse(dobFormatednew)));
					int ageFromBrowser = Period.between(birthDateFromBrowser, currentDate).getYears();

					if ("Senior Citizen".equalsIgnoreCase(custType) || ageFromBrowser >= 60) {
						userDetails.put(Constants.CUST_TYPE, "STB");
					} else if ("Existing Customer".equalsIgnoreCase(custType)) {
						userDetails.put(Constants.CUST_TYPE, "ETB");
					} else {
						userDetails.put(Constants.CUST_TYPE, remarkcustType);
					}

				}
			}

			userDetails.put(Constants.EXISTING_CUST_ID,
					resumeddepositData.getExistingCustId() == null ? "NA" : resumeddepositData.getExistingCustId());
			userDetails.put(Constants.USER_MOBILE_NUMBER, resumeddepositData.getMobileNumber());
			userDetails.put(Constants.DATE_OF_BIRTH,
					resumeddepositData.getDateOfBirth() == null ? "NA" : resumeddepositData.getDateOfBirth());
			userDetails.put(Constants.FULL_NAME,
					resumeddepositData.getFullName() == null
							? resumeddepositData.getNsdlFullName() == null ? "NA" : resumeddepositData.getNsdlFullName()
							: resumeddepositData.getFullName());
			userDetails.put(Constants.PIN,
					resumeddepositData.getPinCode() == null ? "NA" : resumeddepositData.getPinCode());
			userDetails.put(Constants.PAN,
					resumeddepositData.getPanCard() == null ? "NA" : resumeddepositData.getPanCard());
			userDetails.put(Constants.INVESTMENT_TYPE,
					resumeddepositData.getInvestmentType() == null ? "FD" : resumeddepositData.getInvestmentType());
			userDetails.put("validationStatus", Constants.STATUS_SUCCESS);

		} catch (Exception e) {
			userDetails.put("validationStatus", Constants.STATUS_FAIL);
			logger.error(" === Exception in getting resume data in Validate OTP === ", e);
		}
		return userDetails;
	}
	
	@Override
	public String manualpartialSfdc(String cusID){
		logger.info(" === ManualpartialSfdc customerID ======= " + cusID);
		JSONObject res = new JSONObject();
		try {
			
			if(!cusID.trim().isEmpty() && cusID.equalsIgnoreCase(HtmlUtils.htmlEscape(cusID))) {
				FixedDepositData fixedDepositData = fixedDepositDao.getFixedDepositData(cusID);
				if(fixedDepositData!=null) 
				{
				String fullnamesfdc= fixedDepositData.getFdslfFullName() == null ? "Dummy Dummy" :fixedDepositData.getFdslfFullName().toString();
				logger.info(" === ManualpartialSfdc fullnamesfdc======= " + fullnamesfdc);
				JSONObject partialSfdcResponse = partialSfdcService(fixedDepositData,fullnamesfdc,"STEP1","manualSFDC");
				logger.info(" === ManualpartialSfdc partialSfdcResponse ======= " + partialSfdcResponse);
				
				if(partialSfdcResponse != null) {
				String sfdcStatus = partialSfdcResponse.has("IsSuccess")?(partialSfdcResponse.get("IsSuccess").toString()==null?"NA":partialSfdcResponse.get("IsSuccess").toString()):"fail";
				String sfdcRecordId	= partialSfdcResponse.has("FdSfdcId") ? (partialSfdcResponse.get("FdSfdcId").toString()==null?"NA":partialSfdcResponse.get("FdSfdcId").toString()):"fail";
				logger.info(" === sfdcRecordId ======= " + sfdcRecordId +"======IsSuccess======="+sfdcStatus);
				fixedDepositData.setManualpartialSfdcApiId(sfdcRecordId);
				fixedDepositData.setManualpartialSfdcApiRes(sfdcStatus);
				String updateStatus = fixedDepositDao.updateFixedDeposit(fixedDepositData);
				logger.info("=======DB updateStatus for ManualpartialSfdc ========"+updateStatus);
				
				String status="true".equalsIgnoreCase(sfdcStatus)?Constants.STATUS_SUCCESS:Constants.STATUS_FAIL;
				res.put("Customer ID", cusID);
				res.put(Constants.STATUS,status);
				}
				else {
					res.put("Customer ID", cusID);
					res.put(Constants.STATUS,Constants.STATUS_FAIL);
				}
				}
				else 
				{
					logger.info(" === ManualpartialSfdc fixedDepositData Empty ======= " + fixedDepositData);
					res.put("Customer ID", cusID);
					res.put(Constants.STATUS,Constants.STATUS_FAIL);
				}

			}
			else {
				logger.info("========== cusID is not valid ========");
				res.put("Customer ID", cusID);
				res.put(Constants.STATUS,Constants.STATUS_FAIL);
			}
			
		}catch(Exception e){
			logger.error(" === Exception in ManualpartialSfdc === ", e);
			res.put("Customer ID", cusID);
			res.put(Constants.STATUS,Constants.STATUS_FAIL);
		}
		logger.info(" === ManualpartialSfdc Response ======= " + res.toString());
		return res.toString();
	}

	@Override
	public JSONObject manualpartialSfdcMulitple(String custIdArray){
		JSONObject response =new JSONObject();
		JSONArray successResponse = new JSONArray();
		JSONArray failResponse = new JSONArray();
		try {
			
			logger.info("========inside ManualpartialSfdcMulitple service=========="+custIdArray);
			String[] customerIdArray = custIdArray.split(",");
			
			for (int i = 0; i < customerIdArray.length; i++) {
				logger.info("=======Customer ID in ManualpartialSfdcMulitple======"+customerIdArray[i]);
				String custID=customerIdArray[i];
				
				if(!custID.trim().isEmpty() && custID.equalsIgnoreCase(HtmlUtils.htmlEscape(custID))) {
					
					FixedDepositData fixedDepositData = fixedDepositDao.getFixedDepositData(custID);
					logger.info("=======fixedDepositData in ManualpartialSfdcMulitple======"+fixedDepositData);
					
					if(fixedDepositData!=null)
					{
						String fullnamesfdc= fixedDepositData.getFdslfFullName() == null ? "Dummy Dummy" :fixedDepositData.getFdslfFullName().toString();
						logger.info(" === ManualpartialSfdcMulitple fullnamesfdc======= " + fullnamesfdc);
						JSONObject partialSfdcResponse = partialSfdcService(fixedDepositData,fullnamesfdc,"STEP1","manualSFDC");
						logger.info(" === ManualpartialSfdcMulitple partialSfdcResponse ======= " + partialSfdcResponse);
						if(partialSfdcResponse!=null) 
						{
							String FdSfdcId = partialSfdcResponse.has("FdSfdcId") ? (partialSfdcResponse.get("FdSfdcId").toString()==null?"NA":partialSfdcResponse.get("FdSfdcId").toString()): "NA";
							String IsSuccess = partialSfdcResponse.has("IsSuccess") ? (partialSfdcResponse.get("IsSuccess").toString()==null?"NA":partialSfdcResponse.get("IsSuccess").toString()) : "NA";
					
							fixedDepositData.setManualpartialSfdcApiId(FdSfdcId);
							fixedDepositData.setManualpartialSfdcApiRes(IsSuccess);
							String updateStatus = fixedDepositDao.updateFixedDeposit(fixedDepositData);
							logger.info("=======DB updateStatus for ManualpartialSfdcMulitple ========"+updateStatus);
							
							if("true".equalsIgnoreCase(IsSuccess)) {
								successResponse.put(custID);
							}else {
								failResponse.put(custID);
							}
						}
						else{
							failResponse.put(custID);
						}
					}
					else{
						failResponse.put(custID);
					}
				}
				else {
					logger.info("================ custId is not valid =================");
					failResponse.put(custID);
				}
				
			}
			response.put("success Customer ID", successResponse);
			response.put("Fail Customer ID", failResponse);
			response.put("successCount", successResponse.length());
			response.put("FailCount", failResponse.length());
			
		}
		catch(Exception e) {
			logger.error(" ===== Exception in ManualpartialSfdcMulitple === ", e);
			response.put("success Customer ID", successResponse);
			response.put("Fail Customer ID", failResponse);
			response.put("successCount", successResponse.length());
			response.put("FailCount", failResponse.length());
		}
		
		logger.info("========ManualpartialSfdcMulitple final response======"+response);
		return response;
	}

	public String pincodeValidatorService(String pincode) {

		logger.info(" == pincode in pincodeValidatorService === " + pincode);
		String pinStatus = "";
		try {
			int reqPin = Integer.parseInt(pincode);
			
			FDPincodeMaster fdPiMast = fixedDepositDao.getcitystatename(reqPin);
			logger.info(" === pin in pincodeValidatorService === " + fdPiMast);

			if(fdPiMast !=null){
				pinStatus = Constants.STATUS_SUCCESS;

			}else{
				pinStatus = Constants.STATUS_FAIL;
				Utility.loadNewRelicValidation("Pincode field validation failed ", "Pincode field validation failed", "NA",Constants.PINCODE);
			}
			logger.info(" === pincode status in pincodeValidator === " + pinStatus);

			return pinStatus;
		}catch(Exception e) {
			logger.info("Exception in pincodeValidatorService ",e);
			Utility.loadNewRelicException(e.toString(), "pincodeValidatorService", "", "NA");
		}
		return pinStatus;
		
	}

}
