package com.bajaj.fixeddeposit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import com.bajaj.fixeddeposit.model.FixedDepositData;
import com.bajaj.fixeddeposit.model.JsonResponse;
import com.bajaj.fixeddeposit.model.sfdc.SingleSignOn;

public interface FormDataService {

	String addNtbUserDataService(String requestJson, String customerId, HttpSession httpSession) throws JSONException;

	String addUserDataService(String requestJson, String customerId,MultipartFile commAddDoc) throws JSONException;

	String ifscValidatorService(String ifscCode,String fdcNumber);

	String partnerCodeValidatorService(String partnerCode);
	
	boolean AssistedPCodeValidatoService (String partnerCode);

	String storeCustomerDocuments(ArrayList<MultipartFile> files, String customerId);

	String interestRateValidator(String customerType, String tenor, String interestPayout, String interestPayoutType, String interestRate, String investmentType,String customerId);

	String calculatorService(String requestJson, String customerId);

	String paymentRequestService(String customerId, String paymentJson);

	JSONObject paymentResponseService(String[] paymentResponseArr, HttpSession httpSession);

	public JSONObject partialSfdcServiceNTB(FixedDepositData fixedDepositData, String fullName);

	String okycDatamanupation(String requestJson, HttpSession httpSession);

	public String getRePaymentStatus(FixedDepositData fixedDepositData);

	String retryPaymentRequestService(String customerId, String paymentJson);

	String cutsomPrefiill(String customerId,String fdCustId,String mobNo,String fdcNumber,String contextCalled);

	String storeUploadedCustomerDocuments(Map<String,MultipartFile> mapListOfFiles, String customerId,JSONObject val);

	String uploadDocService(String requestJson, String customerId) throws JSONException;

	public List<FixedDepositData> custListRemoveUploadedDocumets() ;

	public List<FixedDepositData> custListRemoveFailUploadedDocumets();

	String eventHubService(FixedDepositData fixedDepositData, String stepNoData);

	public String eventHubdataCall(String customerId, String stepNoData);

	public String getSFDCStatus(FixedDepositData fixedDepositData);

	void sendDocumentstoSfdcService(FixedDepositData depositData,String contextCalled);

	String NSDLCallForPAN(String pancard,String customerId,String contextCalled);

	String manualReflowDataUpdate(String jsonRequest);

	public String getDecryptedRequest(String request);

	public JSONObject createSingleSignOn(SingleSignOn t, HttpServletRequest request);

	public JsonResponse doValidation(SingleSignOn t);

	JSONObject singleSignOnGetData(String request,HttpSession httpSession,HttpServletRequest htRequest);

	public String getEncryptedRequest(String request);
	
	public JSONObject ManulAuditTrailExecute(String customerIdJsonArray);

	public JSONObject manualAuditTrailService(MultipartFile file);
	
	public String ManualAuditTrailExecuteSingleCase(String request);
	
	public String BankNamePreSelectDedupe(String ifscBankName);
	
	public String getcommunicationCityService(String commPincode,String fdcNumber);
}
