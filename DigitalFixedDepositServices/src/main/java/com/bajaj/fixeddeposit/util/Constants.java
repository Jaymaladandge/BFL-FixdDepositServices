package com.bajaj.fixeddeposit.util;

public class Constants {


	private Constants() {
	}

	// API Status
	public static final String STATUS_FAIL="fail";
	public static final String STATUS_SUCCESS="success";
	public static final String ERROR_CODE="errorCode";
	public static final String ERROR_MSG="errorMsg";
	public static final String TOKEN="token";
	public static final String API_STATUS="apiStatus";
	public static final String STATUS="status";
	public static final String MESSAGE = "message";
	public static final String DONE = "done";

	// OTP 
	public static final String OTP_COUNT="validateOtpCount";
	public static final String OTP_GENERATE_COUNT="generatedOtpCount";
	public static final String OTP_RESPONSE="otpResponse";
	public static final String OTP_CREATED_TIME="generatedOtpCreatedTime";
	public static final String OTP_REQUEST_ID="requestId";
	public static final String OTP_RESPONSE_CODE="responseCode";
	public static final String OTP_RESPONSE_MESSAGE="responseMessage";
	public static final String OTP_RESPONSE_MESSAGE_STATUS="SUCCESS";
	public static final String OTP_VALIDATION_STATUS="otpValidateStatus";
	public static final String OTP_RESEND_TIME="otpResendTime";
	public static final String USER_OTP="userOtp";
	public static final String USER_MOBILE_NUMBER="mobileNumber";
	public static final String FULL_NAME="fullName";
	public static final String DATE_OF_BIRTH="dateOfBirth";
	public static final String CUSTOMER_TYPE="customerType";
	public static final String PARTNER_CODE="partnerCode";
	public static final String PARTNER_NAME="partnerName";

	// Token
	public static final String FD_TOKEN_VERIFY="FDVerification";
	public static final String FD_TOKEN_UNIQUE="FDUniqueIDToken";
	public static final String INVALID_TOKEN="Invalid Token";
	public static final String ACCESS_TOKEN_STATUS="Access Token Not Found";
	public static final String ACCESS_TOKEN="access_token";
	public static final String TOKEN_TYPE="token_type";
	public static final String TOKEN_TYPE_STATUS="Token Type Not Found";

	// Field Validation
	public static final String MOBILE_NUMBER="mobileNumber";
	public static final String VALIDATION_STATUS = "validationStatus";
	public static final String CUSTOMER_ID = "customerId";
	public static final String FORM_ID = "formId";
	public static final String VALIDATION_MESSAGE = "validationMsg";

	// dedupe
	public static final String DEDUPE_NTB = "NTB";
	public static final String DEDUPE_ETB = "ETB";
	public static final String DEDUPE_STB = "STB";
	public static final String DEDUPE_CUST_TYPE = "dedupeCustType";
	public static final String CUST_TYPE = "custType";
	public static final String ADDRESS = "address";
	public static final String EXISTING_CUST_ID = "existingCustId";
	public static final String PIN = "pin";
	public static final String CITY = "city";
	public static final String EMAIL = "email";
	public static final String PAN = "pan";

	// Bank API
	public static final String CUST_ID_STATUS = "Not Found";
	public static final String BANK_STATUS_MSG = "Bank Details Not Found";
	public static final String STATUS_CODE = "statusCode";
	public static final String BANK_DETAILS = "bankDetails";
	public static final String API_MESSAGE = "message";
	public static final String BANK_NAME = "bankName";
	public static final String BANK_ID = "bankId";
	public static final String MICR_CODE = "micrCode";
	public static final String AGREEMENT_NO = "agreementNo";
	public static final String MANDATE_REF_NO = "mandateRefNo";
	public static final String BANK_BRANCH_NAME = "bankBranchName";
	public static final String ACCOUNT_NO = "accountNo";
	public static final String BANK_STATUS = "bankStatus";
	public static final String CUST_STATUS = "custIdStatus";
	public static final String CUST_DATA_STATUS = "custDataStatus";
	public static final String CUST_BANK_DETAILS = "custBankDetails";
	public static final String INVESTMENT_TYPE = "investmentType";

	// Interest Rate
	public static final String CUMULATIVE = "Cumulative";
	public static final String NON_CUMULATIVE = "Non Cumulative";
	public static final String MONTHLY = "Monthly";
	public static final String QUARTERLY = "Quarterly";
	public static final String HALF_YEARLY = "HalfYearly";
	public static final String YEARLY = "Annually";
	public static final String INTEREST_RATE_STATUS = "InterestRateStatus";
	public static final String INTEREST_RATE = "interestRate";
	public static final String TENOR = "tenor";

	// Transaction 
	public static final String TRANSACTION_STATUS = "transactionStatus";
	public static final String TRANSACTION_SUCCESS = "SUCCESS";
	public static final String DEPOSIT_AMOUNT = "depositAmount";

	// PDF
	public static final String PDF_STATUS = "pdfStatus";
	public static final String PDF_PATH = "pdfPath";

	//Api Header
	public static final String CONTENT_TYPE_KEY ="Content-Type";
	public static final String ACCESS_TOKEN_KEY ="access_token";     
	public static final String ERROR_KEY ="error";
	public static final String OTP_SUBMITTED_TIME = "otpSubmittedTime";

	//Demog
	public static final String CUSTIDARRAY = "custIdArray";
	public static final String PRODUCT_CUSTID_ARRAY = "produtCustIdArray";
	public static final String PRODUCT = "Product";
	public static final String FD = "FD";
	public static final String FD_BLANK = "FD_BLANK";
	public static final String CUSTOMERID = "CUSTOMERID";
	public static final String CUSTOMER_DEATILS = "CUSTOMER_DEATILS";
	public static final String ADDRESS_1 = "address";
	public static final String PIDATA_API = "PIDATAAPI";
	public static final String PRE_FILLED_ID = "prefilledCustomerId";
	public static final String DOCUMENT_FILE="documentFile";


	//Resume Journey
	public static final String RESUME_STEP="resumestep";
	public static final String RESUME_J_OTP_COUNT="resumeJourneyvalidateOtpCount";
	public static final String RESUME_J_CUSTOMER_ID="resumeJourneyCustomerId";
	public static final String RESUME_J_DATE_OF_BIRTH="resumeJourneydateOfBirth";
	public static final String RESUME_J_OTP_GENERATE_COUNT="resumeJourneygeneratedOtpCount";
	public static final String RESUME_J_OTP_RESPONSE="resumeJourneyotpResponse";
	public static final String RESUME_J_OTP_CREATED_TIME="resumeJourneygeneratedOtpCreatedTime";
	public static final String RESUME_J_OTP_REQUEST_ID="resumeJourneyrequestId";
	public static final String RESUME_J_OTP_RESPONSE_CODE="resumeJourneyresponseCode";
	public static final String RESUME_J_OTP_RESPONSE_MESSAGE="resumeJourneyresponseMessage";
	public static final String RESUME_J_OTP_RESPONSE_MESSAGE_STATUS="SUCCESS";
	public static final String RESUME_J_OTP_VALIDATION_STATUS="resumeJourneyotpValidateStatus";
	public static final String RESUME_J_OTP_RESEND_TIME="resumeJourneyotpResendTime";
	public static final String RESUME_J_ERROR_CODE="resumeJourneyerrorCode";
	public static final String RESUME_J_ERROR_MSG="resumeJourneyerrorMsg";
	public static final String STATUS_TRXDONE="transactionSuccess";
	public static final String RESUME_COUNT="resumeCount";
	public static final String PAST_TEN_DAYS="beforeTenDaysJourneyFound";


	//OTP
	public static final String ERROR_CODE_99 = "{\"errorCode\":\"99\",\"errorMsg\":\"API Failure ~ OTP sercive is not available\",\"requestID\":\"WEBSITE\",\"status\":\"500\"}";
	public static final String ERROR_CODE_00 = "{\"errorCode\":\"00\",\"errorMsg\":\"OTP Sent Successfully\",\"mobile_No__c\":\"8601517835\",\"requestID\":\"WEBSITE\",\"status\":\"200\"}";
	public static final String ERROR_CODE_92 = "{\"errorCode\":\"92\",\"errorMsg\":\"API Failure ~ Mobile no is not valid.\",\"requestID\":\"WEBSITE\",\"status\":\"400\"}";
	public static final String ERROR_CODE_91 = "{\"errorCode\":\"91\",\"errorMsg\":\"API Failure ~ OTP is Null or Empty..\",\"requestID\":\"WEBSITE\",\"status\":\"200\"}";
	public static final String ERROR_CODE400_OTP="{\"status\":\"fail\",\"errorMsg\":\"BAD REQUEST\",\"responseCode\":\"400\"}";
	public static final String ERROR_CODE500_OTP="{\"status\":\"fail\",\"errorMsg\":\"Internal Server Error \",\"responseCode\":\"500\"}";
	public static final String VALIDATE_ERROR_CODE_99="{\"responseCode\":99,\"responseMessage\":\"FAILED :  ~ Validate OTP sercive is not available\",\"status\":\"500\"}";
	public static final String VALIDATE_ERROR_CODE_92="{\"responseCode\":92,\"responseMessage\":\"FAILED :  ~ Mobile Number is not valid\",\"status\":\"400\"}";
	public static final String VALIDATE_ERROR_CODE_93="{\"responseCode\":93,\"responseMessage\":\"FAILED :  ~ OTP is not valid\",\"status\":\"400\"}";
	public static final String VALIDATE_ERROR_CODE_91="{\"responseCode\":91,\"responseMessage\":\"FAILED :  ~ Mandatory parameter OTP should not blankd\",\"status\":\"400\"}";
	public static final String ACCESSTOKEN_ERROR="{\"status\":\"fail\",\"errorMsg\":\"Token not Found \",\"errorCode\":\"500\"}";

	//For SSO
	public static final String SSOPRODUCTMAPPING="Please enter Valid product.No mapping found";
	public static final String SSOHEADMAPPING="Please enter Valid App Key.";
	public static final String SSOREQUESTMAPPING="Please enter valid request Data";
	public static final String RETRIVE_EXCEPTION="API Failure";
	public static final String SSOSTATUSCODE_91="91";
	public static final String SSOSTATUSCODE_92="92";
	public static final String SSOSTATUSCODE_00="00";
	public static final String SSOSTATUSCODE_93="93";
	public static final String SSOSTATUSCODE_94="94";
	public static final String SSOSTATUSCODE_95="95";
	public static final String SSOSTATUSCODE_96="96";
	public static final String STATUS_NA="NA";
	public static final String RETURNURL="returnUrl";
	
	public static final String FORMAPPNUMBER = "formAppNumber";
	public static final String FINALSFDCID = "finalSfdcId";
	
	public static final String VALIDATEOTP = "validateOtp";
	public static final String ADDNTBUSERDATA = "addNtbUserData";
	public static final String ADDUSERDATA= "addUserData";
	public static final String PAYMENTRESPONSE="paymentResponse";
	public static final String REPAYMENTSTATUS="getRePaymentStatus";
	public static final String OKYCRESPONSE="okycResponse";
	public static final String RESUMEJOURNEYGETOTP= "resumeFdcJourneygetOTP";
	public static final String RESUMEREGENERATEOTP = "resumeRegenerateOTP";
	public static final String CREATESINGLESIGNON="createsinglesignon";
	public static final String SINGLESIGNON="singleSignOn";
	public static final String REGENERTEOTP="regenerateOtp";
	public static final String GENERATEOTP = "generateOtp";
	public static final String IFSCCODE = "ifscCodeVerification";
	public static final String CALCULATORREQUEST = "calculatorRequest";
	public static final String PAYMENTREQUEST = "paymentRequest";
	public static final String DOCUPLOAD = "documetUploadNTB";
	public static final String PINCODE = "pincodeContext";
	public static final String RESUMEJOURNEYVALIDATEOTP= "resumeFdcJourneyValidateOtp";
	
	public static final String FIELD_VALIDATION = "fieldValidation";
}
