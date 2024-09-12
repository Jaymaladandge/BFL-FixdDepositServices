package com.bajaj.fixeddeposit.api;

import java.net.URI;
import java.util.logging.Level;
import java.util.zip.CRC32;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;

import com.bajaj.fixeddeposit.dao.FixedDepositDao;
import com.bajaj.fixeddeposit.model.BankNameDetails;
import com.bajaj.fixeddeposit.model.FixedDepositData;
import com.bajaj.fixeddeposit.util.Constants;
import com.bajaj.fixeddeposit.util.DbManipulationUtil;
import com.bajaj.fixeddeposit.util.Utility;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;

@Component
public class PaymentIntegration {
	
	@Autowired
	private FixedDepositDao fixedDepositDao;
	
	@Autowired
	private DbManipulationUtil dbManipulation;
	
	private static final Logger logger = Logger.getLogger(PaymentIntegration.class);
	
	static final int TIMEOUT_MILLIS =40000;
	static final int TOKEN_TIMEOUT_MILLIS =40000;
	
	
	public String getTokenForCustId(String customerId,String contextCalled){
		
		logger.info("====getTokenForCustId customerId====="+customerId+"===contextCalled==="+contextCalled);
		String authToken = null;
		String tokentype= null;
		String authorizationURL=null;
		String res="";
		long start = 0;
		long end = 0;
		String statusCode = "";
		start = System.currentTimeMillis();
		try (CloseableHttpClient httpclient = HttpClients.createDefault()){

			String grantType = Utility.getPropertyFileValue("cKYCgrantType").trim();
			String clientId = Utility.getPropertyFileValue("cKYCclientId").trim();
			String clientSecret = Utility.getPropertyFileValue("cKYCclientSecret").trim();
			 authorizationURL = Utility.getPropertyFileValue("cKYCauthorizationURL").trim();
			String resource = Utility.getPropertyFileValue("cKYCresource").trim();
			String username = Utility.getPropertyFileValue("cKYCusername").trim();
			String apiRequestVal = Utility.getPropertyFileValue("cKYCpassword").trim();   //password Changed to apiRequestVal
			StringBuilder tokenAPIRequest = new StringBuilder(); 
			tokenAPIRequest.append("grant_type=");
			tokenAPIRequest.append(grantType);
			tokenAPIRequest.append("&client_id=");
			tokenAPIRequest.append(clientId);
			tokenAPIRequest.append("&client_secret=");
			tokenAPIRequest.append(clientSecret);
			tokenAPIRequest.append("&resource=");
			tokenAPIRequest.append(resource);
			tokenAPIRequest.append("&username");
			tokenAPIRequest.append(username);
			tokenAPIRequest.append("&password");
			tokenAPIRequest.append(apiRequestVal);

			logger.info("jsonReq== "+tokenAPIRequest.toString() +" authorizationURL== "+authorizationURL+" TOKEN_TIMEOUT_MILLIS===="+TOKEN_TIMEOUT_MILLIS);

			URIBuilder builder = new URIBuilder(authorizationURL);
			URI uri = builder.build();
			HttpPost req = new HttpPost(uri);
			req.setHeader(Constants.CONTENT_TYPE_KEY, "application/x-www-form-urlencoded");
			RequestConfig requestConfig = RequestConfig.custom()
					.setSocketTimeout(TOKEN_TIMEOUT_MILLIS)
					.setConnectTimeout(TOKEN_TIMEOUT_MILLIS)
					.setConnectionRequestTimeout(TOKEN_TIMEOUT_MILLIS)
					.build();
			req.setConfig(requestConfig);
			StringEntity reqEntity = new StringEntity(tokenAPIRequest.toString());
			req.setEntity(reqEntity);
			start = System.currentTimeMillis();

			try(CloseableHttpResponse response = httpclient.execute(req)){
				org.apache.http.HttpEntity entity = response.getEntity();
				statusCode = String.valueOf(response.getStatusLine().getStatusCode());
				end = System.currentTimeMillis();
				if (entity != null) {
					 res = EntityUtils.toString(entity);
					logger.info("=====API Response=====" + res);
					JSONObject tokenAPIResponse = new JSONObject(res); 
					if(tokenAPIResponse.has(Constants.ACCESS_TOKEN_KEY)){
						authToken = tokenAPIResponse.getString(Constants.ACCESS_TOKEN_KEY);
						tokentype = tokenAPIResponse.getString("token_type");
						authToken = tokentype.concat(" ").concat(authToken);
						logger.info("==access token=== " + authToken);
						Utility.loadNewRelicCustomEvent(authorizationURL, String.valueOf(end-start), statusCode, "",contextCalled,customerId);
					}else if(tokenAPIResponse.has(Constants.ERROR_KEY)){
						authToken = tokenAPIResponse.getString(Constants.ERROR_KEY);
						logger.info("access token has errors " + authToken);
						Utility.loadNewRelicCustomEvent(authorizationURL, String.valueOf(end-start), statusCode, "FAIL",contextCalled,customerId);
					}else{
						logger.info("access_token has errors ELSE " + authToken);
						Utility.loadNewRelicCustomEvent(authorizationURL, String.valueOf(end-start), statusCode, "FAIL",contextCalled,customerId);
					}
					
				}
				else{
					Utility.loadNewRelicCustomEvent(authorizationURL, String.valueOf(end-start), statusCode, "FAIL",contextCalled,customerId);
				}
			}
			
			logger.info("==================== TIME TAKEN FOR TOKEN : API ==================== " + (System.currentTimeMillis() - start) + " millis");

			return authToken;
		}catch (Exception ex){
			logger.error("================= APIUtil : Error============== ",ex);
			Utility.loadNewRelicCustomExceptionEvent(authorizationURL, String.valueOf(System.currentTimeMillis()-start), statusCode, "FAIL", ex.toString(),contextCalled,customerId);
			return authToken;
		}
	}
	
	
	
	
	public String calculateChecksum(String inputValues) {

	
		Digest digest = new SHA256Digest();

		HMac hMac = new HMac(digest);
		hMac.init(new KeyParameter("T8weTFOVfrlb".getBytes()));

		byte[] hmacIn = inputValues.getBytes();
		hMac.update(hmacIn, 0, hmacIn.length);
		byte[] hmacOut = new byte[hMac.getMacSize()];

		hMac.doFinal(hmacOut, 0);
		String checksum = bytesToHex(hmacOut);

		logger.info("===========checksum====" + checksum);

		
		return checksum;
	}
	
	
	public static String bytesToHex(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte hashByte : bytes) {
			int intVal = 0xff & hashByte;
			if (intVal < 0x10) {
				sb.append('0');
			}
			sb.append(Integer.toHexString(intVal));
		}
		return sb.toString();
	}	

	
	public String generateChecksum(FixedDepositData depositData, String applicationId,String customerId){
		
		logger.info(" === paymentRequestJson in getPaymentRequest === " + depositData);
		logger.info(" === applicationId in getPaymentRequest === " + applicationId);
		String paymentRequest = "";
		try {
			
			String merchantId = "BAJAJ";

			if("SCHEMEDETAILS".equalsIgnoreCase(depositData.getJourneyStepName())|| "PAYMENTFAIL".equalsIgnoreCase(depositData.getJourneyStepName()))
			{
				
			String paymentAmount =depositData.getDepositAmount()==null?"":depositData.getDepositAmount().replaceAll(",", "");
			String mobileNo = depositData.getMobileNumber();
		
			logger.info("------Original Payment Amount Before Bypass------- \t "+paymentAmount); 
			boolean paymentRupeeBypass=Utility.PaymentBypassRupee(mobileNo);
			paymentAmount=paymentRupeeBypass?"1":paymentAmount;
			logger.info("------ Payment Amount after Bypass------- \t "+paymentAmount); 
			
			customerId = depositData.getCustomerId();
			String fullName = depositData.getFullName();
			String investmentType = "FD";
			String accountNumber = depositData.getBankAccountNumber();
			String bankName = depositData.getBankName();
			String paymentOption=depositData.getPaymentChoice();
			String additionalinfoFour = "website";
			
			String ifscCode=depositData.getIfscCode();
			String bankId="";
			if(bankName!="" && bankName!="NA" && bankName!=null)
			{
				logger.info("====Bank Name====="+bankName);
				BankNameDetails nameDetails = fixedDepositDao.getBankDetailsforPayment(bankName);
				bankId = (nameDetails != null)? nameDetails.getBankId() : "NA";
				//bankId=nameDetails.getBankId();
				logger.info(" === Bank Id === "+bankId);
			}
			
			String paymentprocess =  Utility.getPropertyFileValue("paymentprocess").trim();
			
			if(paymentOption.equalsIgnoreCase("UPI")){
				String accountNumberUPI = AccountNumberValidationAsPerNPCI(accountNumber ,bankName );
				if(accountNumberUPI != "noSuchMatchFound"){
					accountNumber = accountNumberUPI;
					logger.info(" === AccountNumberValidating For UPI === "+accountNumber);
				}
				else{
				accountNumber = accountNumber;
				logger.info(" === AccountNumber For UPI === "+accountNumber);
				}
				}
		
			if("Billdesk".equalsIgnoreCase(paymentprocess)){
				
				BankNameDetails bankNameDetails = fixedDepositDao.getBankDetailsforPayment(bankName);
				
				String bankStatus = bankNameDetails.getBanktype()== null ? "CCAvenue" : bankNameDetails.getBanktype();
				
				if("Both Bank".equalsIgnoreCase(bankStatus)){
					
					 bankName = bankNameDetails.getBilldeskbankname();
					 additionalinfoFour = "NA";
					
					if("UPI".equalsIgnoreCase(paymentOption))
					{
						bankName= "HD4";
						investmentType="DIRECT";
					}
				}else{
					paymentprocess="CCAvenue";
				}

				
			} if("CCAvenue".equalsIgnoreCase(paymentprocess)){

				
				 bankName = depositData.getBankName();
				 additionalinfoFour = "website";
				
				
				if("UPI".equalsIgnoreCase(paymentOption))
				{
					bankName= "CCUPI";
					
				}
				

				switch (bankName) {
				case "ALB":
					additionalinfoFour = "NA";
					
					break;
				case "IDS":
					additionalinfoFour = "NA";
					break;
				
				case "SIB":
					additionalinfoFour = "NA";
					break;
					
				case "IDB":
					additionalinfoFour = "NA";
					break;
				
				case "IDN":
					additionalinfoFour = "NA";
					break;
				
				case "162":
					additionalinfoFour = "NA";
					break;
					

				default:
					break;
				}
			}
			
			if("PayU".equalsIgnoreCase(paymentprocess)){
				
				 bankName = depositData.getBankName();
				 additionalinfoFour = "website";
				 
				if("UPI".equalsIgnoreCase(paymentOption))
				{
					bankId= "FD050";
					logger.info(" === PAYU_UPI Bank Id === "+bankId);
				}
				
			}
			
			String returnUrl = Utility.getPropertyFileValue("digireturnUrl").trim();
			logger.info(" === Generated Application Id in generateChecksum ==== " + applicationId);
			
			String checksumRequest = merchantId+"|"+applicationId+"|"+paymentAmount+"|"+customerId+"|"+fullName+"|"+investmentType+"|"+additionalinfoFour+"|"+bankId+"|"+accountNumber+"|"+ifscCode+"|"+returnUrl;
			logger.info(" ===== checksumRequest in getPaymentRequest ==== " + checksumRequest);
			
			String checksumResponse = calculateChecksum(checksumRequest);
			logger.info(" === checksumResponse in getPaymentRequest ==== " + checksumResponse);
			
			paymentRequest = checksumRequest+"|"+checksumResponse;
			logger.info(" ==== paymentRequest in getPaymentRequest === " + paymentRequest);
			}else
			{
				paymentRequest="noCalculatorStageFound";
			}
		} catch (Exception e) {
			logger.error(" === Exception in getPaymentRequest === ",e);
			if(!(customerId==null && customerId.isEmpty())){
				dbManipulation.recordExeption("PaymentRequest","API issue", customerId);
			}
			NewRelic.getAgent().getLogger().log(Level.SEVERE,"Exception in https://prodapitm.bajajfinserv.in/InsuranceSmartDebitWS/getBankAccountDetails");
			NewRelic.noticeError(e);
			Utility.loadNewRelicException(e.toString(), "generateChecksum", "", customerId);
		}
		
		return paymentRequest;
		
	}
	
	
	public String generateIMPS(FixedDepositData depositData, String applicationId,String customerId){

		logger.info("=======customerId========"+customerId);
		String impsService = Utility.getPropertyFileValue("impsService");
		String impsServiceSubscription = Utility.getPropertyFileValue("impsServiceSubscriptionKey");
		
		String entityResponse = null;

		String requestID =fixedDepositDao.getDealID();
		String beneIFSCCode =depositData.getIfscCode();
		String beneAccNumber =depositData.getBankAccountNumber();
		String baneSource ="Website";
		String baneAmount ="1.0";
		String baneRemitterName =depositData.getFullName();
		String baneRemitterMobile =depositData.getMobileNumber();
		String impscheck=depositData.getImpsCount();
		String IMPSRes="";
		long start = 0;
		long end = 0;
		String statusCode = "";
		
		if(!("impsCheck".equalsIgnoreCase(impscheck))){
		JSONObject beneIMPS = new JSONObject();
		
		beneIMPS.put("requestId", requestID);
		beneIMPS.put("beneIFSCCode", beneIFSCCode);
		beneIMPS.put("beneAccNumber", beneAccNumber);
		beneIMPS.put("source", baneSource);
		beneIMPS.put("amount", baneAmount);
		beneIMPS.put("remitterName", baneRemitterName);
		beneIMPS.put("remitterMobile", baneRemitterMobile);
		start = System.currentTimeMillis();
		try {

			RequestConfig config = RequestConfig.custom()
					.setConnectTimeout(30000)
					.setConnectionRequestTimeout(30000)
					.setSocketTimeout(30000)
					.build();

			try(CloseableHttpClient closeableHttpClient1 =HttpClients.createDefault();){

				String impsReqString = beneIMPS.toString();

				String token = getTokenForCustId(customerId,Constants.PAYMENTRESPONSE);
				logger.info("---------token---------"+token);

				URIBuilder builder1 = new URIBuilder(impsService);
				logger.info("DedupeService......"+impsService);
				java.net.URI uri1 = builder1.build();
				HttpPost req1 = new HttpPost(uri1);

				logger.info("--------IMPS API end-point --------" + builder1);	

				req1.setConfig(config);
				req1.setHeader("Content-Type", "application/json");
				req1.setHeader("Authorization", token);
				req1.setHeader("Ocp-Apim-Subscription-Key", impsServiceSubscription);


				logger.info("=========reqString ======= "+impsReqString);

				StringEntity reqEntity1 = new StringEntity(impsReqString);
				req1.setEntity(reqEntity1);
				
				start = System.currentTimeMillis();
				try(CloseableHttpResponse closeableHttpResponse1 = closeableHttpClient1.execute(req1)){
					end = System.currentTimeMillis();
					org.apache.http.HttpEntity entity1 = closeableHttpResponse1.getEntity();   
					entityResponse = EntityUtils.toString(entity1);
					logger.info("=========IMPSEntityResponse ======= "+entityResponse);

					IMPSRes=entityResponse;
					
					if(entityResponse !=null){
						Utility.loadNewRelicCustomEvent(impsService, String.valueOf(end-start), statusCode, "",Constants.PAYMENTRESPONSE,customerId);
					}else{
						Utility.loadNewRelicCustomEvent(impsService, String.valueOf(end-start), statusCode, "FAIL",Constants.PAYMENTRESPONSE,customerId);
					}
				}
			}

		} catch (Exception e) {
			logger.error("===========Exception in IMPS Service==========",e);
			if(!(customerId==null && customerId.isEmpty())){
				dbManipulation.recordExeption("IMPS","API issue", customerId);
			}
			IMPSRes = new JSONObject().put("respCode", "R009").toString();
			Utility.loadNewRelicCustomExceptionEvent(impsService, String.valueOf(System.currentTimeMillis()-start), statusCode, "FAIL", e.toString(),Constants.PAYMENTRESPONSE,customerId);
		}
	}else{
		IMPSRes = new JSONObject().put("respCode", "R009").toString();
	}
		return IMPSRes;

	
}

	@Trace
	public JSONObject getBankDeatils(String customerId,String FDcustId) throws JSONException
	{
		logger.info("==========getBankDeatils customerId=========="+FDcustId);
		String apiUrl = Utility.getPropertyFileValue("bankdetailsapiurl");
		String subScriptionKey = Utility.getPropertyFileValue("bankdetailssubkey");
		String entityResponse = null;
		long start = 0;
		long end = 0;
		String statusCode = "";

		JSONObject bankdetails = new JSONObject();
		start = System.currentTimeMillis();
		try {

			RequestConfig config = RequestConfig.custom()
					.setConnectTimeout(TIMEOUT_MILLIS)
					.setConnectionRequestTimeout(TIMEOUT_MILLIS)
					.setSocketTimeout(TIMEOUT_MILLIS)
					.build();

			try(CloseableHttpClient closeableHttpClient1 =HttpClients.createDefault();){

				String requestJson ="{\"CUSTID\":\""+customerId+"\"}";

				String token = getTokenForCustId(FDcustId,Constants.ADDUSERDATA);
				logger.info("---------token---------"+token);

				URIBuilder builder1 = new URIBuilder(apiUrl);
				logger.info("getBankDeatils........."+apiUrl);
				java.net.URI uri1 = builder1.build();
				HttpPost req1 = new HttpPost(uri1);

				logger.info("--------getBankDeatils API end-point --------" + builder1);	

				req1.setConfig(config);
				req1.setHeader("Content-Type", "application/json");
				req1.setHeader("Authorization", token);
				req1.setHeader("Ocp-Apim-Subscription-Key", subScriptionKey);


				logger.info("=========reqString ======= "+requestJson);

				StringEntity reqEntity1 = new StringEntity(requestJson);
				req1.setEntity(reqEntity1);

				start = System.currentTimeMillis();
				try(CloseableHttpResponse closeableHttpResponse1 = closeableHttpClient1.execute(req1)){
					
					statusCode = String.valueOf(closeableHttpResponse1.getStatusLine().getStatusCode());
					org.apache.http.HttpEntity entity1 = closeableHttpResponse1.getEntity(); 
					end = System.currentTimeMillis();
					entityResponse = EntityUtils.toString(entity1);
					logger.info("=========getBankDeatils EntityResponse ======= "+entityResponse);
					if(entityResponse!=null)
					{
						JSONObject responseJson = new JSONObject(entityResponse);
						if (responseJson.getString("status").equals("success"))
						{

							JSONArray jsonArray=responseJson.getJSONArray("BankDetails");
							if(!jsonArray.isEmpty() && jsonArray.length()>0)
							{
								JSONObject bankDetailsResJson = jsonArray.getJSONObject(0);
								JSONObject bankdetailsVal = new JSONObject();
								bankdetails.put(Constants.BANK_STATUS, Constants.STATUS_SUCCESS);

								bankdetailsVal.put("agreementNo", bankDetailsResJson.has("")? bankDetailsResJson.get(""):"NA");
								bankdetailsVal.put("bankName",bankDetailsResJson.has("BANK_NAME")? bankDetailsResJson.get("BANK_NAME"):"NA");
								bankdetailsVal.put("accountNo",bankDetailsResJson.has("ACCOUNT_NO")? bankDetailsResJson.get("ACCOUNT_NO"):"NA");
								bankdetailsVal.put("micrCode",bankDetailsResJson.has("MICR_CODE")? bankDetailsResJson.get("MICR_CODE"):"NA");
								bankdetailsVal.put("customerId",bankDetailsResJson.has("CUSTOMER_ID")? bankDetailsResJson.get("CUSTOMER_ID"):"NA");
								bankdetailsVal.put("bankId",bankDetailsResJson.has("BANK_ID")? bankDetailsResJson.get("BANK_ID"):"NA");
								bankdetailsVal.put("mandateRefNo",bankDetailsResJson.has("MANDATE_REF_NO")? bankDetailsResJson.get("MANDATE_REF_NO"):"NA");
								bankdetailsVal.put("IFSC_CODE",bankDetailsResJson.has("IFSC_CODE")? bankDetailsResJson.get("IFSC_CODE"):"NA");
								
								bankdetails.put(Constants.BANK_DETAILS, bankdetailsVal);
								
								Utility.loadNewRelicCustomEvent(apiUrl, String.valueOf(end-start), statusCode, "",Constants.ADDUSERDATA,FDcustId);
							}else
							{
								bankdetails.put(Constants.BANK_STATUS, Constants.STATUS_FAIL);
								Utility.loadNewRelicCustomEvent(apiUrl, String.valueOf(end-start), statusCode, "FAIL",Constants.ADDUSERDATA,FDcustId);
							}
							
						}else
						{
							bankdetails.put(Constants.BANK_STATUS, Constants.STATUS_FAIL);
							Utility.loadNewRelicCustomEvent(apiUrl, String.valueOf(end-start), statusCode, "FAIL",Constants.ADDUSERDATA,FDcustId);
						}
						
					}else
					{
						bankdetails.put(Constants.BANK_STATUS, Constants.STATUS_FAIL);
						Utility.loadNewRelicCustomEvent(apiUrl, String.valueOf(end-start), statusCode, "FAIL",Constants.ADDUSERDATA,FDcustId);
					}

					logger.info(" == getBankDeatils == " + bankdetails);
				}
			}

		} catch (Exception e) {
			logger.error("===========Exception in getBankDeatils==========",e);
			if(!(FDcustId==null && FDcustId.isEmpty())){
				dbManipulation.recordExeption("BANK DETAILS","API issue", FDcustId);
			}
			bankdetails.put(Constants.BANK_STATUS, Constants.STATUS_FAIL);
			Utility.loadNewRelicCustomExceptionEvent(apiUrl, String.valueOf(System.currentTimeMillis()-start), statusCode, "FAIL", e.toString(),Constants.ADDUSERDATA,FDcustId);
		}
		return bankdetails;

	}
	
	public String AccountNumberValidationAsPerNPCI(String accountNumberUpi , String bankNameUpi){
		String accNumber="noSuchMatchFound";
		    if(bankNameUpi.equalsIgnoreCase("YES BANK") && accountNumberUpi.length() < 15){
			for (int i = accountNumberUpi.length();i<15;i++){
				accountNumberUpi = "0".concat(accountNumberUpi);
			}
			accNumber = accountNumberUpi;
			}

			if(bankNameUpi.equalsIgnoreCase("ICICI BANK LIMITED") && accountNumberUpi.length() < 12){
			for (int i = accountNumberUpi.length();i<12;i++){
				accountNumberUpi = "0".concat(accountNumberUpi);
			}
			accNumber = accountNumberUpi;
			}

			if(bankNameUpi.equalsIgnoreCase("ALLAHABAD BANK") && accountNumberUpi.length() < 11){
			for (int i = accountNumberUpi.length();i<11;i++){
				accountNumberUpi = "0".concat(accountNumberUpi);
			}
			accNumber = accountNumberUpi;
			}
			if(bankNameUpi.equalsIgnoreCase("BANK OF BARODA") && accountNumberUpi.length() < 14){
			for (int i = accountNumberUpi.length();i<14;i++){
				accountNumberUpi = "0".concat(accountNumberUpi);
			}
			accNumber = accountNumberUpi;
			}

			if(bankNameUpi.equalsIgnoreCase("BANK OF INDIA") && accountNumberUpi.length() < 15){
			for (int i = accountNumberUpi.length();i<15;i++){
				accountNumberUpi = "0".concat(accountNumberUpi);
			}
			accNumber = accountNumberUpi;
			}

			if(bankNameUpi.equalsIgnoreCase("CENTRAL BANK OF INDIA") && accountNumberUpi.length() < 17){
			for (int i = accountNumberUpi.length();i<17;i++){
				accountNumberUpi = "0".concat(accountNumberUpi);
			}
			accNumber = accountNumberUpi;
			}

			if(bankNameUpi.equalsIgnoreCase("DHANALAKSHMI BANK") && accountNumberUpi.length() < 15){
			for (int i = accountNumberUpi.length();i<15;i++){
				accountNumberUpi = "0".concat(accountNumberUpi);
			}
			accNumber = accountNumberUpi;
			}

			if(bankNameUpi.equalsIgnoreCase("FEDERAL BANK") && accountNumberUpi.length() < 14){
			for (int i = accountNumberUpi.length();i<14;i++){
				accountNumberUpi = "0".concat(accountNumberUpi);
			}
			accNumber = accountNumberUpi;
			}

			if(bankNameUpi.equalsIgnoreCase("HDFC Bank") && accountNumberUpi.length() < 14){
			for (int i = accountNumberUpi.length();i<14;i++){
				accountNumberUpi = "0".concat(accountNumberUpi);
			}
			accNumber = accountNumberUpi;
			}

			if(bankNameUpi.equalsIgnoreCase("KARNATAKA BANK LIMITED") && accountNumberUpi.length() < 16){
			for (int i = accountNumberUpi.length();i<16;i++){
				accountNumberUpi = "0".concat(accountNumberUpi);
			}
			accNumber = accountNumberUpi;
			}

			if(bankNameUpi.equalsIgnoreCase("KARNATAKA VIKAS GRAMEENA BANK") && accountNumberUpi.length() < 17){
			for (int i = accountNumberUpi.length();i<17;i++){
				accountNumberUpi = "0".concat(accountNumberUpi);
			}
			accNumber = accountNumberUpi;
			}

			if(bankNameUpi.equalsIgnoreCase("BANK OF MAHARASHTRA") && accountNumberUpi.length() < 11){
			for (int i = accountNumberUpi.length();i<11;i++){
				accountNumberUpi = "0".concat(accountNumberUpi);
			}
			accNumber = accountNumberUpi;
			}

			if(bankNameUpi.equalsIgnoreCase("Maharashtra Gramin Bank") && accountNumberUpi.length() < 17){
			for (int i = accountNumberUpi.length();i<17;i++){
				accountNumberUpi = "0".concat(accountNumberUpi);
			}
			accNumber = accountNumberUpi;
			}

			if(bankNameUpi.equalsIgnoreCase("The Muslim Co-operative Bank Ltd") && accountNumberUpi.length() < 17){
			for (int i = accountNumberUpi.length();i<17;i++){
				accountNumberUpi = "0".concat(accountNumberUpi);
			}
			accNumber = accountNumberUpi;
			}

			if(bankNameUpi.equalsIgnoreCase("ORIENTAL BANK OF COMMERCE") && accountNumberUpi.length() < 14){
			for (int i = accountNumberUpi.length();i<14;i++){
				accountNumberUpi = "0".concat(accountNumberUpi);
			}
			accNumber = accountNumberUpi;
			}

			if(bankNameUpi.equalsIgnoreCase("RAJASTHAN MARUDHARA GRAMIN BANK") && accountNumberUpi.length() < 17){
			for (int i = accountNumberUpi.length();i<17;i++){
				accountNumberUpi = "0".concat(accountNumberUpi);
			}
			accNumber = accountNumberUpi;
			}

			if(bankNameUpi.equalsIgnoreCase("STATE BANK OF INDIA") && accountNumberUpi.length() < 17){
			for (int i = accountNumberUpi.length();i<17;i++){
				accountNumberUpi = "0".concat(accountNumberUpi);
			}
			accNumber = accountNumberUpi;
			}

			if(bankNameUpi.equalsIgnoreCase("SYNDICATE BANK") && accountNumberUpi.length() < 14){
			for (int i = accountNumberUpi.length();i<14;i++){
				accountNumberUpi = "0".concat(accountNumberUpi);
			}
			accNumber = accountNumberUpi;
			}

			if(bankNameUpi.equalsIgnoreCase("UCO BANK") && accountNumberUpi.length() < 14){
			for (int i = accountNumberUpi.length();i<14;i++){
				accountNumberUpi = "0".concat(accountNumberUpi);
			}
			accNumber = accountNumberUpi;
			}
            return accNumber;
	}
	
}
