package com.bajaj.fixeddeposit.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "FIXED_DEPOSIT_DATA")
@SequenceGenerator(name = "fixedDepositSeq", sequenceName = "SEQ_FIXED_DEPOSIT", allocationSize = 1)
public class FixedDepositData implements Serializable {

	private static final long serialVersionUID = -9176448486771675418L;

	@Id
	@NotNull
	@Column(name = "ID", unique = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fixedDepositSeq")
	private int id;

	@NotNull
	@Column(name = "CUSTOMER_ID", unique = true)
	private String customerId;

	@Column(name = "SALUTATION")
	private String salutation;

	@Column(name = "FULL_NAME")
	private String fullName;

	@Column(name = "MOBILE_NUMBER")
	private String mobileNumber;

	@Column(name = "EMAIL_ADDRESS")
	private String emailAddress;

	@Column(name = "PAN_CARD")
	private String panCard;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "CREATED_ON")
	private String createdOn;

	@Column(name = "DATE_OF_BIRTH")
	private String dateOfBirth;

	@Column(name = "PARTNER_NAME")
	private String partnerName;

	@Column(name = "PARTNER_CODE")
	private String partnerCode;

	@Column(name = "PAGE_URL")
	private String pageUrl;

	@Column(name = "DEVICE")
	private String device;

	@Column(name = "CLIENT_ID")
	private String clientId;

	@Column(name = "COOKIE_ID")
	private String cookieId;

	@Column(name = "UNIQUECODE_ID")
	private String uniqueId;

	@Column(name = "UTM_SOURCE")
	private String utmSource;

	@Column(name = "UTM_MEDIUM")
	private String utmMedium;

	@Column(name = "UTM_CAMAPAIGN")
	private String utmCampaign;

	@Column(name = "UTM_KEYWORD")
	private String utmkeyword;

	@Column(name = "UTM_TERM")
	private String utmTerm;

	@Column(name = "UTM_CONTENT")
	private String utmContent;

	@Column(name = "WCM_TIME")
	private String wcmTime;

	@Column(name = "SFDC_REMARKS")
	private String sfdcRemarks;

	@Column(name = "SFDC_RECORD_ID")
	private String sfdcRecordId;

	@Column(name = "FORM_NAME")
	private String formName;

	@Column(name = "FORM_ID")
	private String formId;

	@Column(name = "DB_STATUS")
	private String dbStatus;

	@Column(name = "PIN_CODE")
	private String pinCode;

	@Column(name = "CITY")
	private String city;

	@Column(name = "STATE")
	private String state;

	@Column(name = "CUSTOMER_TYPE")
	private String customerType;

	@Column(name = "DEDUPE_CUSTOMER_TYPE")
	private String dedupeCustomerType;

	@Column(name = "EXISTING_CUST_ID")
	private String existingCustId;

	@Column(name = "BANK_ACCOUNT_NUMBER")
	private String bankAccountNumber;

	@Column(name = "IFSC_CODE")
	private String ifscCode;

	@Column(name = "BANK_NAME")
	private String bankName;

	@Column(name = "ACCOUNT_TYPE")
	private String accountType;

	@Column(name = "INVESTMENT_TYPE")
	private String investmentType;

	@Column(name = "DEPOSIT_AMOUNT")
	private String depositAmount;

	@Column(name = "PAYMENT_TYPE")
	private String paymentType;

	@Column(name = "TENOR")
	private String tenor;

	@Column(name = "INTEREST_PAYOUT")
	private String interestPayout;

	@Column(name = "INTEREST_PAYOUT_TYPE")
	private String interestPayoutType;

	@Column(name = "INTEREST_RATE")
	private String interestRate;

	@Column(name = "NUMBER_OF_DEPOSIT")
	private String numberOfDeposit;

	@Column(name = "DATE_OF_EACH_DEPOSIT")
	private String dateOfEachDeposit;

	@Column(name = "FD_RENEW")
	private String fdRenew;

	@Column(name = "FD_RENEW_TYPE")
	private String fdRenewType;

	@Column(name = "TRANSACTION_AMOUNT")
	private String transactionAmount;

	@Column(name = "TRANSACTION_STATUS")
	private String transactionStatus;

	@Column(name = "TRANSACTION_MESSAGE")
	private String transactionMessage;

	@Column(name = "DIRECTOR_OR_PROMOTER")
	private String directorOrPromoter;

	@Column(name = "RELATIVE_OF_DIRECTOR")
	private String relativeOfDirector;

	@Column(name = "SHARE_HOLDER")
	private String shareholder;

	@Column(name = "FOREIGN_CITIZEN")
	private String foreignCitizen;

	@Column(name = "FOREIGN_TAX_PAYER")
	private String foreignTaxPayer;

	@Column(name = "UTR_NUMBER")
	private String utrNumber;

	@Column(name = "SMS_STATUS")
	private String smsStatus;

	@Column(name = "NOMINEE_CHECK")
	private String nomineeCheck;

	@Column(name = "NOMINEE_NAME")
	private String nomineeName;

	@Column(name = "NOMINEE_DATE_OF_BIRTH")
	private String nomineeDateOfBirth;

	@Column(name = "RELATIONSHIP_WITH_NOMINEE")
	private String relationshipWithNominee;

	@Column(name = "FINAL_SFDC_STATUS")
	private String finalSfdcStatus;

	@Column(name = "FINAL_SFDC_MSG")
	private String finalSfdcMsg;

	@Column(name = "FINAL_SFDC_UNIQUE_REC_ID")
	private String finalSfdcUniqueRecId;

	@Column(name = "FINAL_SFDC_ID")
	private String finalSfdcId;

	@Column(name = "FORM_APP_NUMBER")
	private String formAppNumber;

	@Column(name = "SDP_TOTAL_PRI_AMT")
	private String sdpTotalPriAmnt;

	@Column(name = "SDP_TOTAL_PAYOUT_AMT")
	private String sdptotalPayoutAmnt;

	@Column(name = "FD_MATURITY_DATE")
	private String fdMaturityDate;

	@Column(name = "FD_INTEREST_AMT")
	private String fdInterestAmnt;

	@Column(name = "FD_MATURITY_AMT")
	private String fdMaturityAmnt;

	@Column(name = "RDPL_LAN")
	private String rdpLan;

	@Column(name = "POCKET_AGREEMENTNO")
	private String pocketAgreementno;

	@Column(name = "POCKET_BANKNAME")
	private String pocketBankname;

	@Column(name = "POCKET_ACCOUNTNO")
	private String pocketAccountno;

	@Column(name = "POCKET_MICRCODE")
	private String pocketMicrcode;

	@Column(name = "POCKET_BFLCUSTOMERID")
	private String pocketBflcustomerid;

	@Column(name = "POCKET_BANKID")
	private String pocketBankid;

	@Column(name = "POCKET_MANDATEREFNO")
	private String pocketMandaterefno;

	@Column(name = "POCKET_ACTIVEFLAG")
	private String pocketActiveflag;

	@Column(name = "POCKET_BALANCE")
	private String pocketBalance;

	@Column(name = "CKYC_STATUS")
	private String ckycStatus;

	@Column(name = "GENDER")
	private String gender;

	@Column(name = "LASTCLICK")
	private String lastClick;

	@Column(name = "CHECKSUMRESPONSE")
	private String checksumresponse;

	@Column(name = "REQUERYSTATUS")
	private String requerystatus;

	@Column(name = "IP_ADRESS")
	private String ipAdress;

	@Column(name = "TIME_OF_LOGGING")
	private String timeOfLogging;

	@Column(name = "OTP_TRIGGERED_TIME")
	private String otpTriggeredTime;

	@Column(name = "OTP_SUBMITTED_TIME")
	private String otpSubmittedTime;

	@Column(name = "PAYMENT_MADE_TIME")
	private String paymentMadeTime;

	@Column(name = "OKYC_RETURN_VALUE")
	private String okycReturnValue;

	@Column(name = "GCLID")
	private String gclid;

	@Column(name = "NSDL_PAN_FULL_NAME")
	private String nsdlFullName;

	@Column(name = "REMARK_CUST_TYPE")
	private String remarkCustType;

	@Column(name = "NOMINEE_ADDRESS")
	private String nomineeAddress;

	@Column(name = "NOMINEE_GUARDIAN_NAME")
	private String nomineeGuardianName;

	@Column(name = "GUARDIAN_RELATIONSHIP_WITH_NOMINEE")
	private String nomineeGuardianRelation;

	@Column(name = "GUARDIAN_NOMINEE_ADDRESS")
	private String nomineeGuardianAddress;

	@Column(name = "NOMINEE_GAURDIAN_DOB")
	private String nomineeGuardianDob;

	@Column(name = "NOMINEE_ADDRESS_CHECK")
	private String nomineeAddressCheck;

	@Column(name = "PAYMENT_CHOOSE")
	private String paymentChoice;

	@Column(name = "GUARDIAN_PRESENT")
	private String gaurdianCheck;

	@Column(name = "NOMINEE_PINCODE")
	private String nomineePincode;

	@Column(name = "NOMINEE_GAURDIAN_PINCODE")
	private String nomineeGuardianPincode;

	@Column(name = "NOMINEE_GAURDIAN_AGE")
	private String nomineeGuardianAge;

	@Column(name = "NOMINEE_CITY")
	private String nomineeCity;

	@Column(name = "NOMINEE_GUARDIAN_CITY")
	private String nomineeGuardianCity;

	@Column(name = "NOMINEE_STATE")
	private String nomineeState;

	@Column(name = "NOMINEE_GUARDIAN_STATE")
	private String nomineeGuardianState;

	@Column(name = "IMPS_COUNT")
	private String impsCount;

	@Column(name = "IMPS_BENENAME")
	private String impsBeneName;

	@Column(name = "OKYCAADHAARREFERENCEID")
	private String okycAadhaarreferenceId;

	@Column(name = "OKYCINITIATIONDT")
	private String okycInitiationDT;

	@Column(name = "OKYCENTAADHAARSECURITYDT")
	private String okycEntAadhaarSecurityDT;

	@Column(name = "OKYCENTOTPSHAREDT")
	private String okycEntOTPShareDT;

	@Column(name = "OKYCREENTSHARECODEDT")
	private String okycReEntShareCodeDT;

	@Column(name = "OKYCDETAILSDATADT")
	private String okycDetailsDataDT;

	@Column(name = "OKYCIPADDRESS")
	private String okycIPAddress;

	@Column(name = "OKYCUPDATERECORDDT")
	private String okycUpdateRecordDT;

	@Column(name = "MATURITYSCHEME")
	private String maturityScheme;

	@Column(name = "RETRYPAYMENTCOUNT")
	private int retryPaymentCount;

	@Column(name = "SCHEMECODE")
	private String schemeCode;

	@Column(name = "FDR_PHYSCIALLY_REQUIRED")
	private String fdrPhysicalyRequired;

	@Column(name = "FD_EXISTING_CUST_ID")
	private String fdExistingCustID;

	@Column(name = "FDSLF_FULL_NAME")
	private String fdslfFullName;

	@Column(name = "FDSLF_FORM_NAME")
	private String fdslfFormName;

	@Column(name = "FDSLF_FORM_ID")
	private String fdslfFormId;

	@Column(name = "FDSLF_PAGE_NAME")
	private String fdslfPageName;

	@Column(name = "COMM_CHECKBOX")
	private String commCheckbox;

	@Column(name = "COMM_ADDRESS")
	private String commAddress;

	@Column(name = "COMM_PINCODE")
	private String commPincode;

	@Column(name = "OKYCAPIREQUESTID")
	private String okycAPIRequestID;

	@Column(name = "INDENTITYDOCUMENTSELECTED")
	private String indentityDocumentSelected;

	@Column(name = "ADDRESSPROOF")
	private String addressProof;

	@Column(name = "ADDRESSDOCUMENTSELECTED")
	private String addressDocumentSelected;

	@Column(name = "SIGNDOCUMENTSELECTED")
	private String signDocumentSelected;

	@Column(name = "SIGNPROOF")
	private String signProof;

	@Column(name = "DOCUMENTUPLOADID")
	private String documentUploadId;

	@Column(name = "DOCUMENTUPLOADAPISTATUS")
	private String documentUploadApiResponse;

	@Column(name = "JOURNEY_STEP_NAME")
	private String journeyStepName;

	@Column(name = "RESUME_JOURNEY_SMS_STATUS")
	private String resumeJourneySmsStatus;

	@Column(name = "PAYMENT_FAILURE_SMS_STATUS")
	private String paymentFailSmsStatus;

	@Column(name = "RESUME_JOURNEY_STAGE")
	private String resumeJourneyStatus;

	@Column(name = "KYC_VERIFY_STATUS")
	private String kycVerifyStatus;

	@Column(name = "ORIGINAL_OTP_TRIGGERED_TIME")
	private String originalOtpTriggerdTime;

	@Column(name = "ORIGINAL_OTP_SUBMITTED_TIME")
	private String originalValidateOtpSubmitTime;

	@Column(name = "ORIGINAL_CREATED_ON")
	private String OriginalcreatedOn;

	@Column(name = "GAID")
	private String gaID;

	@Column(name = "DEVICE__ORGINAL")
	private String deviceOriginal;

	@Column(name = "UTM_SOURCE_ORGINAL")
	private String utmSourceOriginal;

	@Column(name = "UTM_MEDIUM__ORGINAL")
	private String utmMediumOriginal;

	@Column(name = "UTM_CAMAPAIGN__ORGINAL")
	private String utmCampaignOriginal;

	@Column(name = "INVALID_TRX_RESPONSE_SMS")
	private String invalidTraxResponseSms;

	@Column(name = "CKYCREQUESTDATE")
	private String ckycRequestDate;

	@Column(name = "CKYCRESPONSEDATE")
	private String ckycResponseDate;

	@Column(name = "CKYCDOWNLOADDATE")
	private String ckycDownloadDate;

	@Column(name = "BFLSERVERIPADDRESS")
	private String bflServerIpAddress;

	@Column(name = "FULLBANKNAME")
	private String fullBankName;

	@Column(name = "CKCYVERIFYCTATIME")
	private String ckcyVerifyTime;

	@Column(name = "OKYCLFDANDINGTIME")
	private String okycLandLaningTime;

	@Column(name = "DOCUMENTUPLOADTIME")
	private String documentUploadTime;

	@Column(name = "PERSONALDETAILSTIME")
	private String personalDetailsTime;

	@Column(name = "SCHEMEDETAILSTIME")
	private String schemeDetailsTime;

	@Column(name = "PAYEMENTREQUESTTIME")
	private String payementRequestTime;

	@Column(name = "PAYEMENTRESPONSETIME")
	private String paymentResponseTime;

	@Column(name = "NSDLRESPONSE")
	private String nsdlApiResponse;

	@Column(name = "NOMINEE_SALUTATION")
	private String nomineeSalutation;

	/* Added for SFDC Manual Reflow Updation */
	@Column(name = "IFSC_CODE_OLD")
	private String ifscPrevious;

	@Column(name = "BANK_NAME_OLD")
	private String banknamePrevious;

	@Column(name = "UTR_NUMBER_OLD")
	private String utrPrevious;

	@Column(name = "FORM_APP_NUMBER_OLD")
	private String appFormNumberPrevious;

	@Column(name = "BANK_ACC_NUMBER_OLD")
	private String bankAccountNumberPrev;

	@Column(name = "NOMINEE_NAME_OLD")
	private String nomineeNamePrevious;

	@Column(name = "NOMINEE_DOB_OLD")
	private String nomineeDateOfBirthPrevious;

	@Column(name = "RELATIONSHIP_NOMINEE_OLD")
	private String relationshipWithNomineePrevious;

	@Column(name = "MANUALREFLOWDATASTATUS")
	private String manualReflowStatus;

	@Column(name = "EXCEPTIONCOUNT")
	private String exceptionCount;

	@Column(name = "EXCEPTIONCTANAME")
	private String exceptionCtaName;

	@Column(name = "EXCEPTIONCATEGORY")
	private String exceptionCategory;

	@Column(name = "SSO_TOKEN")
	private String ssoToken;

	@Column(name = "SSO_CREATEDON")
	private String ssoCreatedOn;

	@Column(name = "SSO_RETURN_FLAG")
	private String ssoReturnFlag;

	@Column(name = "SERVERFILEPATH")
	private String servrFilePath;

	@Column(name = "RESUME_FDC_NUMBER")
	private String resumeFdcNumber;

	@Column(name = "WHATSAPP_CONSENT_STATUS")
	private String whatsAppConsentStatus;

	@Column(name = "AADHARNUMBER")
	private String aadharNumber;

	@Column(name = "AADHAR_HOUSE")
	private String aadharHouse;

	@Column(name = "AADHAR_LOCATION")
	private String aadharLocation;

	@Column(name = "AADHAR_STREET")
	private String aadharStreet;

	@Column(name = "AADHAR_LANDMARK")
	private String aadharLandmark;

	@Column(name = "AADHAR_VILLAGE")
	private String aadharVillage;

	@Column(name = "AADHAR_SUBDISTRICT")
	private String aadharSubdistrict;

	@Column(name = "AADHAR_DISTRICT")
	private String aadharDistrict;

	@Column(name = "AADHAR_POST_OFFICE")
	private String aadharPostOffice;

	@Column(name = "AADHAR_PIN_CODE")
	private String aadharPincode;

	@Column(name = "AADHAR_GUID")
	private String aadharGuiId;

	@Column(name = "MANUAL_AUDIT_DOC_UPLOAD_ID")
	private String manualAuditDocUploadId;

	@Column(name = "MANUAL_DOC_UPLOAD_API_RES")
	private String manualDocUploadApiRes;

	@Column(name = "MANUAL_PARTIAL_SFDC_API_ID")
	private String manualpartialSfdcApiId;

	@Column(name = "MANUAL_PARTIAL_SFDC_API_RES")
	private String manualpartialSfdcApiRes;
	
	@Column(name="COMMUNICATIONDOCNAME")
	private String commAddDocName;
	
	@Column(name="PAN_EDITED")
	private String panedited;
	
	@Column(name = "PAYEMENTSUCCESSTIME")
	private String  paymentSuccessTime;
	
	public String getPaymentSuccessTime() {
		return paymentSuccessTime;
	}

	public void setPaymentSuccessTime(String paymentSuccessTime) {
		this.paymentSuccessTime = paymentSuccessTime;
	}

	public String getPanedited() {
		return panedited;
	}

	public void setPanedited(String panedited) {
		this.panedited = panedited;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPanCard() {
		return panCard;
	}

	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public String getPartnerCode() {
		return partnerCode;
	}

	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getCookieId() {
		return cookieId;
	}

	public void setCookieId(String cookieId) {
		this.cookieId = cookieId;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getUtmSource() {
		return utmSource;
	}

	public void setUtmSource(String utmSource) {
		this.utmSource = utmSource;
	}

	public String getUtmMedium() {
		return utmMedium;
	}

	public void setUtmMedium(String utmMedium) {
		this.utmMedium = utmMedium;
	}

	public String getUtmCampaign() {
		return utmCampaign;
	}

	public void setUtmCampaign(String utmCampaign) {
		this.utmCampaign = utmCampaign;
	}

	public String getUtmkeyword() {
		return utmkeyword;
	}

	public void setUtmkeyword(String utmkeyword) {
		this.utmkeyword = utmkeyword;
	}

	public String getUtmTerm() {
		return utmTerm;
	}

	public void setUtmTerm(String utmTerm) {
		this.utmTerm = utmTerm;
	}

	public String getUtmContent() {
		return utmContent;
	}

	public void setUtmContent(String utmContent) {
		this.utmContent = utmContent;
	}

	public String getWcmTime() {
		return wcmTime;
	}

	public void setWcmTime(String wcmTime) {
		this.wcmTime = wcmTime;
	}

	public String getSfdcRemarks() {
		return sfdcRemarks;
	}

	public void setSfdcRemarks(String sfdcRemarks) {
		this.sfdcRemarks = sfdcRemarks;
	}

	public String getSfdcRecordId() {
		return sfdcRecordId;
	}

	public void setSfdcRecordId(String sfdcRecordId) {
		this.sfdcRecordId = sfdcRecordId;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getDbStatus() {
		return dbStatus;
	}

	public void setDbStatus(String dbStatus) {
		this.dbStatus = dbStatus;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getDedupeCustomerType() {
		return dedupeCustomerType;
	}

	public void setDedupeCustomerType(String dedupeCustomerType) {
		this.dedupeCustomerType = dedupeCustomerType;
	}

	public String getExistingCustId() {
		return existingCustId;
	}

	public void setExistingCustId(String existingCustId) {
		this.existingCustId = existingCustId;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getInvestmentType() {
		return investmentType;
	}

	public void setInvestmentType(String investmentType) {
		this.investmentType = investmentType;
	}

	public String getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(String depositAmount) {
		this.depositAmount = depositAmount;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getTenor() {
		return tenor;
	}

	public void setTenor(String tenor) {
		this.tenor = tenor;
	}

	public String getInterestPayout() {
		return interestPayout;
	}

	public void setInterestPayout(String interestPayout) {
		this.interestPayout = interestPayout;
	}

	public String getInterestPayoutType() {
		return interestPayoutType;
	}

	public void setInterestPayoutType(String interestPayoutType) {
		this.interestPayoutType = interestPayoutType;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	public String getNumberOfDeposit() {
		return numberOfDeposit;
	}

	public void setNumberOfDeposit(String numberOfDeposit) {
		this.numberOfDeposit = numberOfDeposit;
	}

	public String getDateOfEachDeposit() {
		return dateOfEachDeposit;
	}

	public void setDateOfEachDeposit(String dateOfEachDeposit) {
		this.dateOfEachDeposit = dateOfEachDeposit;
	}

	public String getFdRenew() {
		return fdRenew;
	}

	public void setFdRenew(String fdRenew) {
		this.fdRenew = fdRenew;
	}

	public String getFdRenewType() {
		return fdRenewType;
	}

	public void setFdRenewType(String fdRenewType) {
		this.fdRenewType = fdRenewType;
	}

	public String getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public String getTransactionMessage() {
		return transactionMessage;
	}

	public void setTransactionMessage(String transactionMessage) {
		this.transactionMessage = transactionMessage;
	}

	public String getDirectorOrPromoter() {
		return directorOrPromoter;
	}

	public void setDirectorOrPromoter(String directorOrPromoter) {
		this.directorOrPromoter = directorOrPromoter;
	}

	public String getRelativeOfDirector() {
		return relativeOfDirector;
	}

	public void setRelativeOfDirector(String relativeOfDirector) {
		this.relativeOfDirector = relativeOfDirector;
	}

	public String getShareholder() {
		return shareholder;
	}

	public void setShareholder(String shareholder) {
		this.shareholder = shareholder;
	}

	public String getForeignCitizen() {
		return foreignCitizen;
	}

	public void setForeignCitizen(String foreignCitizen) {
		this.foreignCitizen = foreignCitizen;
	}

	public String getForeignTaxPayer() {
		return foreignTaxPayer;
	}

	public void setForeignTaxPayer(String foreignTaxPayer) {
		this.foreignTaxPayer = foreignTaxPayer;
	}

	public String getUtrNumber() {
		return utrNumber;
	}

	public void setUtrNumber(String utrNumber) {
		this.utrNumber = utrNumber;
	}

	public String getSmsStatus() {
		return smsStatus;
	}

	public void setSmsStatus(String smsStatus) {
		this.smsStatus = smsStatus;
	}

	public String getNomineeCheck() {
		return nomineeCheck;
	}

	public void setNomineeCheck(String nomineeCheck) {
		this.nomineeCheck = nomineeCheck;
	}

	public String getNomineeName() {
		return nomineeName;
	}

	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
	}

	public String getNomineeDateOfBirth() {
		return nomineeDateOfBirth;
	}

	public void setNomineeDateOfBirth(String nomineeDateOfBirth) {
		this.nomineeDateOfBirth = nomineeDateOfBirth;
	}

	public String getRelationshipWithNominee() {
		return relationshipWithNominee;
	}

	public void setRelationshipWithNominee(String relationshipWithNominee) {
		this.relationshipWithNominee = relationshipWithNominee;
	}

	public String getFinalSfdcStatus() {
		return finalSfdcStatus;
	}

	public void setFinalSfdcStatus(String finalSfdcStatus) {
		this.finalSfdcStatus = finalSfdcStatus;
	}

	public String getFinalSfdcMsg() {
		return finalSfdcMsg;
	}

	public void setFinalSfdcMsg(String finalSfdcMsg) {
		this.finalSfdcMsg = finalSfdcMsg;
	}

	public String getFinalSfdcUniqueRecId() {
		return finalSfdcUniqueRecId;
	}

	public void setFinalSfdcUniqueRecId(String finalSfdcUniqueRecId) {
		this.finalSfdcUniqueRecId = finalSfdcUniqueRecId;
	}

	public String getFinalSfdcId() {
		return finalSfdcId;
	}

	public void setFinalSfdcId(String finalSfdcId) {
		this.finalSfdcId = finalSfdcId;
	}

	public String getFormAppNumber() {
		return formAppNumber;
	}

	public void setFormAppNumber(String formAppNumber) {
		this.formAppNumber = formAppNumber;
	}

	public String getSdpTotalPriAmnt() {
		return sdpTotalPriAmnt;
	}

	public void setSdpTotalPriAmnt(String sdpTotalPriAmnt) {
		this.sdpTotalPriAmnt = sdpTotalPriAmnt;
	}

	public String getSdptotalPayoutAmnt() {
		return sdptotalPayoutAmnt;
	}

	public void setSdptotalPayoutAmnt(String sdptotalPayoutAmnt) {
		this.sdptotalPayoutAmnt = sdptotalPayoutAmnt;
	}

	public String getFdMaturityDate() {
		return fdMaturityDate;
	}

	public void setFdMaturityDate(String fdMaturityDate) {
		this.fdMaturityDate = fdMaturityDate;
	}

	public String getFdInterestAmnt() {
		return fdInterestAmnt;
	}

	public void setFdInterestAmnt(String fdInterestAmnt) {
		this.fdInterestAmnt = fdInterestAmnt;
	}

	public String getFdMaturityAmnt() {
		return fdMaturityAmnt;
	}

	public void setFdMaturityAmnt(String fdMaturityAmnt) {
		this.fdMaturityAmnt = fdMaturityAmnt;
	}

	public String getRdpLan() {
		return rdpLan;
	}

	public void setRdpLan(String rdpLan) {
		this.rdpLan = rdpLan;
	}

	public String getPocketAgreementno() {
		return pocketAgreementno;
	}

	public void setPocketAgreementno(String pocketAgreementno) {
		this.pocketAgreementno = pocketAgreementno;
	}

	public String getPocketBankname() {
		return pocketBankname;
	}

	public void setPocketBankname(String pocketBankname) {
		this.pocketBankname = pocketBankname;
	}

	public String getPocketAccountno() {
		return pocketAccountno;
	}

	public void setPocketAccountno(String pocketAccountno) {
		this.pocketAccountno = pocketAccountno;
	}

	public String getPocketMicrcode() {
		return pocketMicrcode;
	}

	public void setPocketMicrcode(String pocketMicrcode) {
		this.pocketMicrcode = pocketMicrcode;
	}

	public String getPocketBflcustomerid() {
		return pocketBflcustomerid;
	}

	public void setPocketBflcustomerid(String pocketBflcustomerid) {
		this.pocketBflcustomerid = pocketBflcustomerid;
	}

	public String getPocketBankid() {
		return pocketBankid;
	}

	public void setPocketBankid(String pocketBankid) {
		this.pocketBankid = pocketBankid;
	}

	public String getPocketMandaterefno() {
		return pocketMandaterefno;
	}

	public void setPocketMandaterefno(String pocketMandaterefno) {
		this.pocketMandaterefno = pocketMandaterefno;
	}

	public String getPocketActiveflag() {
		return pocketActiveflag;
	}

	public void setPocketActiveflag(String pocketActiveflag) {
		this.pocketActiveflag = pocketActiveflag;
	}

	public String getPocketBalance() {
		return pocketBalance;
	}

	public void setPocketBalance(String pocketBalance) {
		this.pocketBalance = pocketBalance;
	}

	public String getCkycStatus() {
		return ckycStatus;
	}

	public void setCkycStatus(String ckycStatus) {
		this.ckycStatus = ckycStatus;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLastClick() {
		return lastClick;
	}

	public void setLastClick(String lastClick) {
		this.lastClick = lastClick;
	}

	public String getChecksumresponse() {
		return checksumresponse;
	}

	public void setChecksumresponse(String checksumresponse) {
		this.checksumresponse = checksumresponse;
	}

	public String getRequerystatus() {
		return requerystatus;
	}

	public void setRequerystatus(String requerystatus) {
		this.requerystatus = requerystatus;
	}

	public String getIpAdress() {
		return ipAdress;
	}

	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}

	public String getTimeOfLogging() {
		return timeOfLogging;
	}

	public void setTimeOfLogging(String timeOfLogging) {
		this.timeOfLogging = timeOfLogging;
	}

	public String getOtpTriggeredTime() {
		return otpTriggeredTime;
	}

	public void setOtpTriggeredTime(String otpTriggeredTime) {
		this.otpTriggeredTime = otpTriggeredTime;
	}

	public String getOtpSubmittedTime() {
		return otpSubmittedTime;
	}

	public void setOtpSubmittedTime(String otpSubmittedTime) {
		this.otpSubmittedTime = otpSubmittedTime;
	}

	public String getPaymentMadeTime() {
		return paymentMadeTime;
	}

	public void setPaymentMadeTime(String paymentMadeTime) {
		this.paymentMadeTime = paymentMadeTime;
	}

	public String getOkycReturnValue() {
		return okycReturnValue;
	}

	public void setOkycReturnValue(String okycReturnValue) {
		this.okycReturnValue = okycReturnValue;
	}

	public String getGclid() {
		return gclid;
	}

	public void setGclid(String gclid) {
		this.gclid = gclid;
	}

	public String getNsdlFullName() {
		return nsdlFullName;
	}

	public void setNsdlFullName(String nsdlFullName) {
		this.nsdlFullName = nsdlFullName;
	}

	public String getRemarkCustType() {
		return remarkCustType;
	}

	public void setRemarkCustType(String remarkCustType) {
		this.remarkCustType = remarkCustType;
	}

	public String getNomineeAddress() {
		return nomineeAddress;
	}

	public void setNomineeAddress(String nomineeAddress) {
		this.nomineeAddress = nomineeAddress;
	}

	public String getNomineeGuardianName() {
		return nomineeGuardianName;
	}

	public void setNomineeGuardianName(String nomineeGuardianName) {
		this.nomineeGuardianName = nomineeGuardianName;
	}

	public String getNomineeGuardianRelation() {
		return nomineeGuardianRelation;
	}

	public void setNomineeGuardianRelation(String nomineeGuardianRelation) {
		this.nomineeGuardianRelation = nomineeGuardianRelation;
	}

	public String getNomineeGuardianAddress() {
		return nomineeGuardianAddress;
	}

	public void setNomineeGuardianAddress(String nomineeGuardianAddress) {
		this.nomineeGuardianAddress = nomineeGuardianAddress;
	}

	public String getNomineeGuardianDob() {
		return nomineeGuardianDob;
	}

	public void setNomineeGuardianDob(String nomineeGuardianDob) {
		this.nomineeGuardianDob = nomineeGuardianDob;
	}

	public String getNomineeAddressCheck() {
		return nomineeAddressCheck;
	}

	public void setNomineeAddressCheck(String nomineeAddressCheck) {
		this.nomineeAddressCheck = nomineeAddressCheck;
	}

	public String getPaymentChoice() {
		return paymentChoice;
	}

	public void setPaymentChoice(String paymentChoice) {
		this.paymentChoice = paymentChoice;
	}

	public String getGaurdianCheck() {
		return gaurdianCheck;
	}

	public void setGaurdianCheck(String gaurdianCheck) {
		this.gaurdianCheck = gaurdianCheck;
	}

	public String getNomineePincode() {
		return nomineePincode;
	}

	public void setNomineePincode(String nomineePincode) {
		this.nomineePincode = nomineePincode;
	}

	public String getNomineeGuardianPincode() {
		return nomineeGuardianPincode;
	}

	public void setNomineeGuardianPincode(String nomineeGuardianPincode) {
		this.nomineeGuardianPincode = nomineeGuardianPincode;
	}

	public String getNomineeGuardianAge() {
		return nomineeGuardianAge;
	}

	public void setNomineeGuardianAge(String nomineeGuardianAge) {
		this.nomineeGuardianAge = nomineeGuardianAge;
	}

	public String getNomineeCity() {
		return nomineeCity;
	}

	public void setNomineeCity(String nomineeCity) {
		this.nomineeCity = nomineeCity;
	}

	public String getNomineeGuardianCity() {
		return nomineeGuardianCity;
	}

	public void setNomineeGuardianCity(String nomineeGuardianCity) {
		this.nomineeGuardianCity = nomineeGuardianCity;
	}

	public String getNomineeState() {
		return nomineeState;
	}

	public void setNomineeState(String nomineeState) {
		this.nomineeState = nomineeState;
	}

	public String getNomineeGuardianState() {
		return nomineeGuardianState;
	}

	public void setNomineeGuardianState(String nomineeGuardianState) {
		this.nomineeGuardianState = nomineeGuardianState;
	}

	public String getImpsCount() {
		return impsCount;
	}

	public void setImpsCount(String impsCount) {
		this.impsCount = impsCount;
	}

	public String getImpsBeneName() {
		return impsBeneName;
	}

	public void setImpsBeneName(String impsBeneName) {
		this.impsBeneName = impsBeneName;
	}

	public String getOkycAadhaarreferenceId() {
		return okycAadhaarreferenceId;
	}

	public void setOkycAadhaarreferenceId(String okycAadhaarreferenceId) {
		this.okycAadhaarreferenceId = okycAadhaarreferenceId;
	}

	public String getOkycInitiationDT() {
		return okycInitiationDT;
	}

	public void setOkycInitiationDT(String okycInitiationDT) {
		this.okycInitiationDT = okycInitiationDT;
	}

	public String getOkycEntAadhaarSecurityDT() {
		return okycEntAadhaarSecurityDT;
	}

	public void setOkycEntAadhaarSecurityDT(String okycEntAadhaarSecurityDT) {
		this.okycEntAadhaarSecurityDT = okycEntAadhaarSecurityDT;
	}

	public String getOkycEntOTPShareDT() {
		return okycEntOTPShareDT;
	}

	public void setOkycEntOTPShareDT(String okycEntOTPShareDT) {
		this.okycEntOTPShareDT = okycEntOTPShareDT;
	}

	public String getOkycReEntShareCodeDT() {
		return okycReEntShareCodeDT;
	}

	public void setOkycReEntShareCodeDT(String okycReEntShareCodeDT) {
		this.okycReEntShareCodeDT = okycReEntShareCodeDT;
	}

	public String getOkycDetailsDataDT() {
		return okycDetailsDataDT;
	}

	public void setOkycDetailsDataDT(String okycDetailsDataDT) {
		this.okycDetailsDataDT = okycDetailsDataDT;
	}

	public String getOkycIPAddress() {
		return okycIPAddress;
	}

	public void setOkycIPAddress(String okycIPAddress) {
		this.okycIPAddress = okycIPAddress;
	}

	public String getOkycUpdateRecordDT() {
		return okycUpdateRecordDT;
	}

	public void setOkycUpdateRecordDT(String okycUpdateRecordDT) {
		this.okycUpdateRecordDT = okycUpdateRecordDT;
	}

	public String getMaturityScheme() {
		return maturityScheme;
	}

	public void setMaturityScheme(String maturityScheme) {
		this.maturityScheme = maturityScheme;
	}

	public int getRetryPaymentCount() {
		return retryPaymentCount;
	}

	public void setRetryPaymentCount(int retryPaymentCount) {
		this.retryPaymentCount = retryPaymentCount;
	}

	public String getSchemeCode() {
		return schemeCode;
	}

	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}

	public String getFdrPhysicalyRequired() {
		return fdrPhysicalyRequired;
	}

	public void setFdrPhysicalyRequired(String fdrPhysicalyRequired) {
		this.fdrPhysicalyRequired = fdrPhysicalyRequired;
	}

	public String getFdExistingCustID() {
		return fdExistingCustID;
	}

	public void setFdExistingCustID(String fdExistingCustID) {
		this.fdExistingCustID = fdExistingCustID;
	}

	public String getFdslfFullName() {
		return fdslfFullName;
	}

	public void setFdslfFullName(String fdslfFullName) {
		this.fdslfFullName = fdslfFullName;
	}

	public String getFdslfFormName() {
		return fdslfFormName;
	}

	public void setFdslfFormName(String fdslfFormName) {
		this.fdslfFormName = fdslfFormName;
	}

	public String getFdslfFormId() {
		return fdslfFormId;
	}

	public void setFdslfFormId(String fdslfFormId) {
		this.fdslfFormId = fdslfFormId;
	}

	public String getFdslfPageName() {
		return fdslfPageName;
	}

	public void setFdslfPageName(String fdslfPageName) {
		this.fdslfPageName = fdslfPageName;
	}

	public String getCommCheckbox() {
		return commCheckbox;
	}

	public void setCommCheckbox(String commCheckbox) {
		this.commCheckbox = commCheckbox;
	}

	public String getCommAddress() {
		return commAddress;
	}

	public void setCommAddress(String commAddress) {
		this.commAddress = commAddress;
	}

	public String getCommPincode() {
		return commPincode;
	}

	public void setCommPincode(String commPincode) {
		this.commPincode = commPincode;
	}

	public String getOkycAPIRequestID() {
		return okycAPIRequestID;
	}

	public void setOkycAPIRequestID(String okycAPIRequestID) {
		this.okycAPIRequestID = okycAPIRequestID;
	}

	public String getIndentityDocumentSelected() {
		return indentityDocumentSelected;
	}

	public void setIndentityDocumentSelected(String indentityDocumentSelected) {
		this.indentityDocumentSelected = indentityDocumentSelected;
	}

	public String getAddressProof() {
		return addressProof;
	}

	public void setAddressProof(String addressProof) {
		this.addressProof = addressProof;
	}

	public String getAddressDocumentSelected() {
		return addressDocumentSelected;
	}

	public void setAddressDocumentSelected(String addressDocumentSelected) {
		this.addressDocumentSelected = addressDocumentSelected;
	}

	public String getSignDocumentSelected() {
		return signDocumentSelected;
	}

	public void setSignDocumentSelected(String signDocumentSelected) {
		this.signDocumentSelected = signDocumentSelected;
	}

	public String getSignProof() {
		return signProof;
	}

	public void setSignProof(String signProof) {
		this.signProof = signProof;
	}

	public String getDocumentUploadId() {
		return documentUploadId;
	}

	public void setDocumentUploadId(String documentUploadId) {
		this.documentUploadId = documentUploadId;
	}

	public String getDocumentUploadApiResponse() {
		return documentUploadApiResponse;
	}

	public void setDocumentUploadApiResponse(String documentUploadApiResponse) {
		this.documentUploadApiResponse = documentUploadApiResponse;
	}

	public String getJourneyStepName() {
		return journeyStepName;
	}

	public void setJourneyStepName(String journeyStepName) {
		this.journeyStepName = journeyStepName;
	}

	public String getResumeJourneySmsStatus() {
		return resumeJourneySmsStatus;
	}

	public void setResumeJourneySmsStatus(String resumeJourneySmsStatus) {
		this.resumeJourneySmsStatus = resumeJourneySmsStatus;
	}

	public String getPaymentFailSmsStatus() {
		return paymentFailSmsStatus;
	}

	public void setPaymentFailSmsStatus(String paymentFailSmsStatus) {
		this.paymentFailSmsStatus = paymentFailSmsStatus;
	}

	public String getResumeJourneyStatus() {
		return resumeJourneyStatus;
	}

	public void setResumeJourneyStatus(String resumeJourneyStatus) {
		this.resumeJourneyStatus = resumeJourneyStatus;
	}

	public String getKycVerifyStatus() {
		return kycVerifyStatus;
	}

	public void setKycVerifyStatus(String kycVerifyStatus) {
		this.kycVerifyStatus = kycVerifyStatus;
	}

	public String getOriginalOtpTriggerdTime() {
		return originalOtpTriggerdTime;
	}

	public void setOriginalOtpTriggerdTime(String originalOtpTriggerdTime) {
		this.originalOtpTriggerdTime = originalOtpTriggerdTime;
	}

	public String getOriginalValidateOtpSubmitTime() {
		return originalValidateOtpSubmitTime;
	}

	public void setOriginalValidateOtpSubmitTime(String originalValidateOtpSubmitTime) {
		this.originalValidateOtpSubmitTime = originalValidateOtpSubmitTime;
	}

	public String getOriginalcreatedOn() {
		return OriginalcreatedOn;
	}

	public void setOriginalcreatedOn(String originalcreatedOn) {
		OriginalcreatedOn = originalcreatedOn;
	}

	public String getGaID() {
		return gaID;
	}

	public void setGaID(String gaID) {
		this.gaID = gaID;
	}

	public String getDeviceOriginal() {
		return deviceOriginal;
	}

	public void setDeviceOriginal(String deviceOriginal) {
		this.deviceOriginal = deviceOriginal;
	}

	public String getUtmSourceOriginal() {
		return utmSourceOriginal;
	}

	public void setUtmSourceOriginal(String utmSourceOriginal) {
		this.utmSourceOriginal = utmSourceOriginal;
	}

	public String getUtmMediumOriginal() {
		return utmMediumOriginal;
	}

	public void setUtmMediumOriginal(String utmMediumOriginal) {
		this.utmMediumOriginal = utmMediumOriginal;
	}

	public String getUtmCampaignOriginal() {
		return utmCampaignOriginal;
	}

	public void setUtmCampaignOriginal(String utmCampaignOriginal) {
		this.utmCampaignOriginal = utmCampaignOriginal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInvalidTraxResponseSms() {
		return invalidTraxResponseSms;
	}

	public void setInvalidTraxResponseSms(String invalidTraxResponseSms) {
		this.invalidTraxResponseSms = invalidTraxResponseSms;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCkycRequestDate() {
		return ckycRequestDate;
	}

	public void setCkycRequestDate(String ckycRequestDate) {
		this.ckycRequestDate = ckycRequestDate;
	}

	public String getCkycResponseDate() {
		return ckycResponseDate;
	}

	public void setCkycResponseDate(String ckycResponseDate) {
		this.ckycResponseDate = ckycResponseDate;
	}

	public String getCkycDownloadDate() {
		return ckycDownloadDate;
	}

	public void setCkycDownloadDate(String ckycDownloadDate) {
		this.ckycDownloadDate = ckycDownloadDate;
	}

	public String getBflServerIpAddress() {
		return bflServerIpAddress;
	}

	public void setBflServerIpAddress(String bflServerIpAddress) {
		this.bflServerIpAddress = bflServerIpAddress;
	}

	public String getFullBankName() {
		return fullBankName;
	}

	public void setFullBankName(String fullBankName) {
		this.fullBankName = fullBankName;
	}

	public String getCkcyVerifyTime() {
		return ckcyVerifyTime;
	}

	public void setCkcyVerifyTime(String ckcyVerifyTime) {
		this.ckcyVerifyTime = ckcyVerifyTime;
	}

	public String getOkycLandLaningTime() {
		return okycLandLaningTime;
	}

	public void setOkycLandLaningTime(String okycLandLaningTime) {
		this.okycLandLaningTime = okycLandLaningTime;
	}

	public String getDocumentUploadTime() {
		return documentUploadTime;
	}

	public void setDocumentUploadTime(String documentUploadTime) {
		this.documentUploadTime = documentUploadTime;
	}

	public String getPersonalDetailsTime() {
		return personalDetailsTime;
	}

	public void setPersonalDetailsTime(String personalDetailsTime) {
		this.personalDetailsTime = personalDetailsTime;
	}

	public String getSchemeDetailsTime() {
		return schemeDetailsTime;
	}

	public void setSchemeDetailsTime(String schemeDetailsTime) {
		this.schemeDetailsTime = schemeDetailsTime;
	}

	public String getPayementRequestTime() {
		return payementRequestTime;
	}

	public void setPayementRequestTime(String payementRequestTime) {
		this.payementRequestTime = payementRequestTime;
	}

	public String getPaymentResponseTime() {
		return paymentResponseTime;
	}

	public void setPaymentResponseTime(String paymentResponseTime) {
		this.paymentResponseTime = paymentResponseTime;
	}

	public String getNsdlApiResponse() {
		return nsdlApiResponse;
	}

	public void setNsdlApiResponse(String nsdlApiResponse) {
		this.nsdlApiResponse = nsdlApiResponse;
	}

	public String getNomineeSalutation() {
		return nomineeSalutation;
	}

	public void setNomineeSalutation(String nomineeSalutation) {
		this.nomineeSalutation = nomineeSalutation;
	}

	public String getIfscPrevious() {
		return ifscPrevious;
	}

	public void setIfscPrevious(String ifscPrevious) {
		this.ifscPrevious = ifscPrevious;
	}

	public String getBanknamePrevious() {
		return banknamePrevious;
	}

	public void setBanknamePrevious(String banknamePrevious) {
		this.banknamePrevious = banknamePrevious;
	}

	public String getUtrPrevious() {
		return utrPrevious;
	}

	public void setUtrPrevious(String utrPrevious) {
		this.utrPrevious = utrPrevious;
	}

	public String getAppFormNumberPrevious() {
		return appFormNumberPrevious;
	}

	public void setAppFormNumberPrevious(String appFormNumberPrevious) {
		this.appFormNumberPrevious = appFormNumberPrevious;
	}

	public String getBankAccountNumberPrev() {
		return bankAccountNumberPrev;
	}

	public void setBankAccountNumberPrev(String bankAccountNumberPrev) {
		this.bankAccountNumberPrev = bankAccountNumberPrev;
	}

	public String getNomineeNamePrevious() {
		return nomineeNamePrevious;
	}

	public void setNomineeNamePrevious(String nomineeNamePrevious) {
		this.nomineeNamePrevious = nomineeNamePrevious;
	}

	public String getNomineeDateOfBirthPrevious() {
		return nomineeDateOfBirthPrevious;
	}

	public void setNomineeDateOfBirthPrevious(String nomineeDateOfBirthPrevious) {
		this.nomineeDateOfBirthPrevious = nomineeDateOfBirthPrevious;
	}

	public String getRelationshipWithNomineePrevious() {
		return relationshipWithNomineePrevious;
	}

	public void setRelationshipWithNomineePrevious(String relationshipWithNomineePrevious) {
		this.relationshipWithNomineePrevious = relationshipWithNomineePrevious;
	}

	public String getManualReflowStatus() {
		return manualReflowStatus;
	}

	public void setManualReflowStatus(String manualReflowStatus) {
		this.manualReflowStatus = manualReflowStatus;
	}

	public String getExceptionCount() {
		return exceptionCount;
	}

	public void setExceptionCount(String exceptionCount) {
		this.exceptionCount = exceptionCount;
	}

	public String getExceptionCtaName() {
		return exceptionCtaName;
	}

	public void setExceptionCtaName(String exceptionCtaName) {
		this.exceptionCtaName = exceptionCtaName;
	}

	public String getExceptionCategory() {
		return exceptionCategory;
	}

	public void setExceptionCategory(String exceptionCategory) {
		this.exceptionCategory = exceptionCategory;
	}

	public String getSsoToken() {
		return ssoToken;
	}

	public void setSsoToken(String ssoToken) {
		this.ssoToken = ssoToken;
	}

	public String getSsoCreatedOn() {
		return ssoCreatedOn;
	}

	public void setSsoCreatedOn(String ssoCreatedOn) {
		this.ssoCreatedOn = ssoCreatedOn;
	}

	public String getSsoReturnFlag() {
		return ssoReturnFlag;
	}

	public void setSsoReturnFlag(String ssoReturnFlag) {
		this.ssoReturnFlag = ssoReturnFlag;
	}

	public String getServrFilePath() {
		return servrFilePath;
	}

	public void setServrFilePath(String servrFilePath) {
		this.servrFilePath = servrFilePath;
	}

	public String getResumeFdcNumber() {
		return resumeFdcNumber;
	}

	public void setResumeFdcNumber(String resumeFdcNumber) {
		this.resumeFdcNumber = resumeFdcNumber;
	}

	public String getWhatsAppConsentStatus() {
		return whatsAppConsentStatus;
	}

	public void setWhatsAppConsentStatus(String whatsAppConsentStatus) {
		this.whatsAppConsentStatus = whatsAppConsentStatus;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public String getAadharHouse() {
		return aadharHouse;
	}

	public void setAadharHouse(String aadharHouse) {
		this.aadharHouse = aadharHouse;
	}

	public String getAadharLocation() {
		return aadharLocation;
	}

	public void setAadharLocation(String aadharLocation) {
		this.aadharLocation = aadharLocation;
	}

	public String getAadharStreet() {
		return aadharStreet;
	}

	public void setAadharStreet(String aadharStreet) {
		this.aadharStreet = aadharStreet;
	}

	public String getAadharLandmark() {
		return aadharLandmark;
	}

	public void setAadharLandmark(String aadharLandmark) {
		this.aadharLandmark = aadharLandmark;
	}

	public String getAadharVillage() {
		return aadharVillage;
	}

	public void setAadharVillage(String aadharVillage) {
		this.aadharVillage = aadharVillage;
	}

	public String getAadharSubdistrict() {
		return aadharSubdistrict;
	}

	public void setAadharSubdistrict(String aadharSubdistrict) {
		this.aadharSubdistrict = aadharSubdistrict;
	}

	public String getAadharDistrict() {
		return aadharDistrict;
	}

	public void setAadharDistrict(String aadharDistrict) {
		this.aadharDistrict = aadharDistrict;
	}

	public String getAadharPostOffice() {
		return aadharPostOffice;
	}

	public void setAadharPostOffice(String aadharPostOffice) {
		this.aadharPostOffice = aadharPostOffice;
	}

	public String getAadharPincode() {
		return aadharPincode;
	}

	public void setAadharPincode(String aadharPincode) {
		this.aadharPincode = aadharPincode;
	}

	public String getAadharGuiId() {
		return aadharGuiId;
	}

	public void setAadharGuiId(String aadharGuiId) {
		this.aadharGuiId = aadharGuiId;
	}

	public String getManualAuditDocUploadId() {
		return manualAuditDocUploadId;
	}

	public void setManualAuditDocUploadId(String manualAuditDocUploadId) {
		this.manualAuditDocUploadId = manualAuditDocUploadId;
	}

	public String getManualDocUploadApiRes() {
		return manualDocUploadApiRes;
	}

	public void setManualDocUploadApiRes(String manualDocUploadApiRes) {
		this.manualDocUploadApiRes = manualDocUploadApiRes;
	}

	public String getManualpartialSfdcApiId() {
		return manualpartialSfdcApiId;
	}

	public void setManualpartialSfdcApiId(String manualpartialSfdcApiId) {
		this.manualpartialSfdcApiId = manualpartialSfdcApiId;
	}

	public String getManualpartialSfdcApiRes() {
		return manualpartialSfdcApiRes;
	}

	public void setManualpartialSfdcApiRes(String manualpartialSfdcApiRes) {
		this.manualpartialSfdcApiRes = manualpartialSfdcApiRes;
	}
	
	public String getCommAddDocName() {
		return commAddDocName;
	}

	public void setCommAddDocName(String commAddDocName) {
		this.commAddDocName = commAddDocName;
	}

	@Override
	public String toString() {
		return "FixedDepositData [id=" + id + ", customerId=" + customerId + ", salutation=" + salutation
				+ ", fullName=" + fullName + ", mobileNumber=" + mobileNumber + ", emailAddress=" + emailAddress
				+ ", panCard=" + panCard + ", address=" + address + ", createdOn=" + createdOn + ", dateOfBirth="
				+ dateOfBirth + ", partnerName=" + partnerName + ", partnerCode=" + partnerCode + ", pageUrl=" + pageUrl
				+ ", device=" + device + ", clientId=" + clientId + ", cookieId=" + cookieId + ", uniqueId=" + uniqueId
				+ ", utmSource=" + utmSource + ", utmMedium=" + utmMedium + ", utmCampaign=" + utmCampaign
				+ ", utmkeyword=" + utmkeyword + ", utmTerm=" + utmTerm + ", utmContent=" + utmContent + ", wcmTime="
				+ wcmTime + ", sfdcRemarks=" + sfdcRemarks + ", sfdcRecordId=" + sfdcRecordId + ", formName=" + formName
				+ ", formId=" + formId + ", dbStatus=" + dbStatus + ", pinCode=" + pinCode + ", city=" + city
				+ ", state=" + state + ", customerType=" + customerType + ", dedupeCustomerType=" + dedupeCustomerType
				+ ", existingCustId=" + existingCustId + ", bankAccountNumber=" + bankAccountNumber + ", ifscCode="
				+ ifscCode + ", bankName=" + bankName + ", accountType=" + accountType + ", investmentType="
				+ investmentType + ", depositAmount=" + depositAmount + ", paymentType=" + paymentType + ", tenor="
				+ tenor + ", interestPayout=" + interestPayout + ", interestPayoutType=" + interestPayoutType
				+ ", interestRate=" + interestRate + ", numberOfDeposit=" + numberOfDeposit + ", dateOfEachDeposit="
				+ dateOfEachDeposit + ", fdRenew=" + fdRenew + ", fdRenewType=" + fdRenewType + ", transactionAmount="
				+ transactionAmount + ", transactionStatus=" + transactionStatus + ", transactionMessage="
				+ transactionMessage + ", directorOrPromoter=" + directorOrPromoter + ", relativeOfDirector="
				+ relativeOfDirector + ", shareholder=" + shareholder + ", foreignCitizen=" + foreignCitizen
				+ ", foreignTaxPayer=" + foreignTaxPayer + ", utrNumber=" + utrNumber + ", smsStatus=" + smsStatus
				+ ", nomineeCheck=" + nomineeCheck + ", nomineeName=" + nomineeName + ", nomineeDateOfBirth="
				+ nomineeDateOfBirth + ", relationshipWithNominee=" + relationshipWithNominee + ", finalSfdcStatus="
				+ finalSfdcStatus + ", finalSfdcMsg=" + finalSfdcMsg + ", finalSfdcUniqueRecId=" + finalSfdcUniqueRecId
				+ ", finalSfdcId=" + finalSfdcId + ", formAppNumber=" + formAppNumber + ", sdpTotalPriAmnt="
				+ sdpTotalPriAmnt + ", sdptotalPayoutAmnt=" + sdptotalPayoutAmnt + ", fdMaturityDate=" + fdMaturityDate
				+ ", fdInterestAmnt=" + fdInterestAmnt + ", fdMaturityAmnt=" + fdMaturityAmnt + ", rdpLan=" + rdpLan
				+ ", pocketAgreementno=" + pocketAgreementno + ", pocketBankname=" + pocketBankname
				+ ", pocketAccountno=" + pocketAccountno + ", pocketMicrcode=" + pocketMicrcode
				+ ", pocketBflcustomerid=" + pocketBflcustomerid + ", pocketBankid=" + pocketBankid
				+ ", pocketMandaterefno=" + pocketMandaterefno + ", pocketActiveflag=" + pocketActiveflag
				+ ", pocketBalance=" + pocketBalance + ", ckycStatus=" + ckycStatus + ", gender=" + gender
				+ ", lastClick=" + lastClick + ", checksumresponse=" + checksumresponse + ", requerystatus="
				+ requerystatus + ", ipAdress=" + ipAdress + ", timeOfLogging=" + timeOfLogging + ", otpTriggeredTime="
				+ otpTriggeredTime + ", otpSubmittedTime=" + otpSubmittedTime + ", paymentMadeTime=" + paymentMadeTime
				+ ", okycReturnValue=" + okycReturnValue + ", gclid=" + gclid + ", nsdlFullName=" + nsdlFullName
				+ ", remarkCustType=" + remarkCustType + ", nomineeAddress=" + nomineeAddress + ", nomineeGuardianName="
				+ nomineeGuardianName + ", nomineeGuardianRelation=" + nomineeGuardianRelation
				+ ", nomineeGuardianAddress=" + nomineeGuardianAddress + ", nomineeGuardianDob=" + nomineeGuardianDob
				+ ", nomineeAddressCheck=" + nomineeAddressCheck + ", paymentChoice=" + paymentChoice
				+ ", gaurdianCheck=" + gaurdianCheck + ", nomineePincode=" + nomineePincode
				+ ", nomineeGuardianPincode=" + nomineeGuardianPincode + ", nomineeGuardianAge=" + nomineeGuardianAge
				+ ", nomineeCity=" + nomineeCity + ", nomineeGuardianCity=" + nomineeGuardianCity + ", nomineeState="
				+ nomineeState + ", nomineeGuardianState=" + nomineeGuardianState + ", impsCount=" + impsCount
				+ ", impsBeneName=" + impsBeneName + ", okycAadhaarreferenceId=" + okycAadhaarreferenceId
				+ ", okycInitiationDT=" + okycInitiationDT + ", okycEntAadhaarSecurityDT=" + okycEntAadhaarSecurityDT
				+ ", okycEntOTPShareDT=" + okycEntOTPShareDT + ", okycReEntShareCodeDT=" + okycReEntShareCodeDT
				+ ", okycDetailsDataDT=" + okycDetailsDataDT + ", okycIPAddress=" + okycIPAddress
				+ ", okycUpdateRecordDT=" + okycUpdateRecordDT + ", maturityScheme=" + maturityScheme
				+ ", retryPaymentCount=" + retryPaymentCount + ", schemeCode=" + schemeCode + ", fdrPhysicalyRequired="
				+ fdrPhysicalyRequired + ", fdExistingCustID=" + fdExistingCustID + ", fdslfFullName=" + fdslfFullName
				+ ", fdslfFormName=" + fdslfFormName + ", fdslfFormId=" + fdslfFormId + ", fdslfPageName="
				+ fdslfPageName + ", commCheckbox=" + commCheckbox + ", commAddress=" + commAddress + ", commPincode="
				+ commPincode + ", okycAPIRequestID=" + okycAPIRequestID + ", indentityDocumentSelected="
				+ indentityDocumentSelected + ", addressProof=" + addressProof + ", addressDocumentSelected="
				+ addressDocumentSelected + ", signDocumentSelected=" + signDocumentSelected + ", signProof="
				+ signProof + ", documentUploadId=" + documentUploadId + ", documentUploadApiResponse="
				+ documentUploadApiResponse + ", journeyStepName=" + journeyStepName + ", resumeJourneySmsStatus="
				+ resumeJourneySmsStatus + ", paymentFailSmsStatus=" + paymentFailSmsStatus + ", resumeJourneyStatus="
				+ resumeJourneyStatus + ", kycVerifyStatus=" + kycVerifyStatus + ", originalOtpTriggerdTime="
				+ originalOtpTriggerdTime + ", originalValidateOtpSubmitTime=" + originalValidateOtpSubmitTime
				+ ", OriginalcreatedOn=" + OriginalcreatedOn + ", gaID=" + gaID + ", deviceOriginal=" + deviceOriginal
				+ ", utmSourceOriginal=" + utmSourceOriginal + ", utmMediumOriginal=" + utmMediumOriginal
				+ ", utmCampaignOriginal=" + utmCampaignOriginal + ", invalidTraxResponseSms=" + invalidTraxResponseSms
				+ ", ckycRequestDate=" + ckycRequestDate + ", ckycResponseDate=" + ckycResponseDate
				+ ", ckycDownloadDate=" + ckycDownloadDate + ", bflServerIpAddress=" + bflServerIpAddress
				+ ", fullBankName=" + fullBankName + ", ckcyVerifyTime=" + ckcyVerifyTime + ", okycLandLaningTime="
				+ okycLandLaningTime + ", documentUploadTime=" + documentUploadTime + ", personalDetailsTime="
				+ personalDetailsTime + ", schemeDetailsTime=" + schemeDetailsTime + ", payementRequestTime="
				+ payementRequestTime + ", paymentResponseTime=" + paymentResponseTime + ", nsdlApiResponse="
				+ nsdlApiResponse + ", nomineeSalutation=" + nomineeSalutation + ", ifscPrevious=" + ifscPrevious
				+ ", banknamePrevious=" + banknamePrevious + ", utrPrevious=" + utrPrevious + ", appFormNumberPrevious="
				+ appFormNumberPrevious + ", bankAccountNumberPrev=" + bankAccountNumberPrev + ", nomineeNamePrevious="
				+ nomineeNamePrevious + ", nomineeDateOfBirthPrevious=" + nomineeDateOfBirthPrevious
				+ ", relationshipWithNomineePrevious=" + relationshipWithNomineePrevious + ", manualReflowStatus="
				+ manualReflowStatus + ", exceptionCount=" + exceptionCount + ", exceptionCtaName=" + exceptionCtaName
				+ ", exceptionCategory=" + exceptionCategory + ", ssoToken=" + ssoToken + ", ssoCreatedOn="
				+ ssoCreatedOn + ", ssoReturnFlag=" + ssoReturnFlag + ", servrFilePath=" + servrFilePath
				+ ", resumeFdcNumber=" + resumeFdcNumber + ", whatsAppConsentStatus=" + whatsAppConsentStatus
				+ ", aadharNumber=" + aadharNumber + ", aadharHouse=" + aadharHouse + ", aadharLocation="
				+ aadharLocation + ", aadharStreet=" + aadharStreet + ", aadharLandmark=" + aadharLandmark
				+ ", aadharVillage=" + aadharVillage + ", aadharSubdistrict=" + aadharSubdistrict + ", aadharDistrict="
				+ aadharDistrict + ", aadharPostOffice=" + aadharPostOffice + ", aadharPincode=" + aadharPincode
				+ ", aadharGuiId=" + aadharGuiId + ", manualAuditDocUploadId=" + manualAuditDocUploadId
				+ ", manualDocUploadApiRes=" + manualDocUploadApiRes + ", manualpartialSfdcApiRes="
				+ manualpartialSfdcApiRes + ", manualpartialSfdcApiId=" + manualpartialSfdcApiId 
				+ ", commAddDocName=" + commAddDocName + ", panedited =" +panedited+ ", paymentSuccessTime =" +paymentSuccessTime+ "]";
	}

}
