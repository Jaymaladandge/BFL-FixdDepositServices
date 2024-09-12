package com.bajaj.fixeddeposit.api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.logging.Level;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;

import com.bajaj.fixeddeposit.dao.FixedDepositDao;
import com.bajaj.fixeddeposit.util.Constants;
import com.bajaj.fixeddeposit.util.DbManipulationUtil;
import com.bajaj.fixeddeposit.util.Utility;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;



@Component
public class CustIdIntegration {

	@Autowired
	private FixedDepositDao fixedDepositDao;

	@Autowired
	private PaymentIntegration payToken;

	private static final Logger logger = Logger.getLogger(CustIdIntegration.class);

	static final int TIMEOUT_MILLIS =50000;
	static final int TOKEN_TIMEOUT_MILLIS =50000;

	static final int TIMEOUT_MILLIS_SEARCH =35000;
	static final int TIMEOUT_MILLIS_DOWNLOAD =35000;

	@Autowired
	private DbManipulationUtil dbManipulation; 
	// Access Token API Integration
	@Trace
	public String getDedupeAccessToekn(String customerId,String contextCalled) throws JSONException {

		logger.info("=====getDedupeAccessToekn ====="+customerId+"====="+contextCalled);
		
		String authToken = null;
		String grantType = null;
		String clientId = null;
		String clientSecret = null;
		String authorizationURL = null;     
		String resource = null;
		String username = null;
		String requestParaFirst = null;    //password to access Token API
		String res="";
		long start = 0;
		long end = 0;
		String statusCode = "";
		start = System.currentTimeMillis();
		try (CloseableHttpClient httpclient = HttpClients.createDefault()){

			grantType = Utility.getPropertyFileValue("cKYCgrantType").trim();
			clientId = Utility.getPropertyFileValue("cKYCclientId").trim();
			clientSecret = Utility.getPropertyFileValue("cKYCclientSecret").trim();
			authorizationURL = Utility.getPropertyFileValue("dedupeTokenURL").trim();     
			resource = Utility.getPropertyFileValue("cKYCresource").trim();
			username = Utility.getPropertyFileValue("cKYCusername").trim();
			requestParaFirst = Utility.getPropertyFileValue("cKYCpassword").trim();
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
			tokenAPIRequest.append(requestParaFirst);

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
				statusCode = String.valueOf(response.getStatusLine().getStatusCode());
				org.apache.http.HttpEntity entity = response.getEntity();
				if (entity != null) {
					res = EntityUtils.toString(entity);
					
					logger.info("=====dedupe token API Response=====" + res);
					JSONObject tokenAPIResponse = new JSONObject(res); 
					if(tokenAPIResponse.has(Constants.ACCESS_TOKEN_KEY)){
						authToken = tokenAPIResponse.getString(Constants.ACCESS_TOKEN_KEY);
						logger.info("==access token=== " + authToken);
						Utility.loadNewRelicCustomEvent(authorizationURL, String.valueOf(System.currentTimeMillis()-start), statusCode, "",contextCalled,customerId);
					}else if(tokenAPIResponse.has(Constants.ERROR_KEY)){
						authToken = tokenAPIResponse.getString(Constants.ERROR_KEY);
						logger.info("access token has errors " + authToken);
						Utility.loadNewRelicCustomEvent(authorizationURL, String.valueOf(System.currentTimeMillis()-start), statusCode, "FAIL",contextCalled,customerId);
					}else{
						logger.info("access_token has errors ELSE " + authToken);
						Utility.loadNewRelicCustomEvent(authorizationURL, String.valueOf(System.currentTimeMillis()-start), statusCode, "FAIL",contextCalled,customerId);
					}
					
				}else{
					Utility.loadNewRelicCustomEvent(authorizationURL, String.valueOf(System.currentTimeMillis()-start), statusCode, "FAIL",contextCalled,customerId);
				}
			}
			end = System.currentTimeMillis();
			logger.info("==================== TIME TAKEN FOR TOKEN : API ==================== " + (end - start) + " millis");

			return authToken;
		}catch (Exception ex){
			logger.error("================= APIUtil : Error============== ",ex);
			Utility.loadNewRelicCustomExceptionEvent(authorizationURL, String.valueOf(System.currentTimeMillis()-start), statusCode, "FAIL", ex.toString(),contextCalled,customerId);
			return authToken;
		}

	}



	public String createJSONfile(String content, String customerId, String filePath){
		BufferedWriter bw = null;
		FileWriter fw = null;
		String filePathReturn="";
		try
		{
			File file = new File(filePath + new SimpleDateFormat("yyyyMMdd").format(new Date()) + "/Request_" + customerId + ".txt");
			logger.error("=============  file creation =========  "+file);
			filePathReturn=file.getAbsolutePath();
			logger.error("=============  filePathReturn=========  "+filePathReturn);
			if (!file.exists()) {
				file.getParentFile().mkdirs();
				file.createNewFile();
			}
			fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);
			bw.write(content);
		} catch (Exception e) {
			logger.error("============= exception in file creation =========  ", e);
		} finally {
			try {
				if (bw != null) {
					bw.close();
					fw.close();
				}
			} catch (Exception e) {
				logger.error("========== exception in file creation in finally ======  ", e);
			}
		}
		return filePathReturn;
	}



	public String readFile(String filepath) {

		logger.info("----inside read method filepath-----"+filepath);
		String content = null;
		BufferedReader reader = null;

		try {

			if(new File(filepath).exists()){
				reader = new BufferedReader(new FileReader(filepath));
				logger.info("----in try reader-----"+reader);
				StringBuilder stringBuilder = new StringBuilder();
				char[] buffer = new char[10];
				while (reader.read(buffer) != -1) {
					stringBuilder.append(new String(buffer));
					buffer = new char[10];
				}

				content = stringBuilder.toString();
			}else{
				content="";
			}


		} catch (Exception e) {
			logger.error("-----Exception in Reading file-----", e);
			content = "";
		} finally {

			try {
				if (reader != null) {
					reader.close();
				}

			} catch (IOException e) {
				logger.error("----Excption in closing reader------", e);
				content= "";
			}
		}

		return content;

	}

	public String getBillDeskRepaymentStatus(String billDeskReq,String customerId) throws ClientProtocolException, IOException, URISyntaxException, JSONException{
		
		logger.info("====== customerIs in getBillDeskRepaymentStatus ======"+customerId);
		String billDeskResponse=null;
		JSONObject responseJson=null;
		String billDeskEndPoint=null;
		long start = 0;
		int statusCode = 0;
		start = System.currentTimeMillis();
		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {

			billDeskEndPoint = Utility.getPropertyFileValue("RePaymentEndPoint").trim();
			String subscription = Utility.getPropertyFileValue("SubscriptionKey").trim();
			String accessToken = payToken.getTokenForCustId(customerId,Constants.REPAYMENTSTATUS);

			if (!"NA".equals(accessToken)) {
				URIBuilder builder = new URIBuilder(billDeskEndPoint);
				URI uri = builder.build();
				HttpPost req = new HttpPost(uri);
				RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(TIMEOUT_MILLIS)
						.setConnectTimeout(TIMEOUT_MILLIS).setConnectionRequestTimeout(TIMEOUT_MILLIS).build();
				req.setConfig(requestConfig);

				req.setHeader("Content-Type", "application/json");
				req.setHeader("Ocp-Apim-Subscription-Key",
						subscription);
				req.setHeader("Authorization", accessToken);

				StringEntity reqEntity = new StringEntity(billDeskReq);
				req.setEntity(reqEntity);
				start = System.currentTimeMillis();
				try (CloseableHttpResponse response = httpclient.execute(req)) {
					statusCode = response.getStatusLine().getStatusCode();
					org.apache.http.HttpEntity entity = response.getEntity();
					billDeskResponse = EntityUtils.toString(entity);
					long end = System.currentTimeMillis();
					responseJson = new JSONObject(billDeskResponse);

					logger.info("==responseJson===="+responseJson);
					if (statusCode == 200 && entity != null) {
						logger.info("========== Validation Response :" + (end - start)+ " millis ===================");
						String status = responseJson.has("status") ? responseJson.getString("status") : "99";
						String statusMsg = responseJson.has("message") ? responseJson.getString("message") : "99";

						logger.info(statusCode + " " + statusMsg + " " );

						if ("SUCCESS".equals(status)) {
							responseJson.put("errorCode","200");
							Utility.loadNewRelicCustomEvent(billDeskEndPoint, String.valueOf(System.currentTimeMillis()-start), String.valueOf(statusCode), "", Constants.REPAYMENTSTATUS, customerId);
						} else {
							responseJson.put("errorCode","404");
							Utility.loadNewRelicCustomEvent(billDeskEndPoint, String.valueOf(System.currentTimeMillis()-start), String.valueOf(statusCode), "FAIL", Constants.REPAYMENTSTATUS, customerId);
						}
					} else {
						responseJson.put("errorCode","500");
						Utility.loadNewRelicCustomEvent(billDeskEndPoint, String.valueOf(System.currentTimeMillis()-start), String.valueOf(statusCode), "FAIL", Constants.REPAYMENTSTATUS, customerId);
					}
				}
			} else {
				responseJson = new JSONObject();
				responseJson.put("errorCode","400");
				Utility.loadNewRelicCustomEvent(billDeskEndPoint, String.valueOf(System.currentTimeMillis()-start), String.valueOf(statusCode), "FAIL", Constants.REPAYMENTSTATUS, customerId);
			}
			return responseJson.toString();
		} catch (Exception ex){
			logger.error("======================== BillDesk Requeru API : ERROR ==============", ex);
			logger.error("BillDesk ERROR Request : " + billDeskReq);
			responseJson = new JSONObject();
			responseJson.put("errorCode","500");
			logger.info(" ------  Final VALIDATE OTP  Respons  \t" + responseJson);
			Utility.loadNewRelicCustomExceptionEvent(billDeskEndPoint, String.valueOf(System.currentTimeMillis()-start), "", "FAIL", ex.toString(), Constants.REPAYMENTSTATUS, customerId);
			return responseJson.toString();
		}
	}


	public String panVerification(String pan, String product,String customerId,String contextCalled) throws JSONException {

		logger.info("======panVerification customerId====="+customerId+"===contextCalled==="+contextCalled);
		String entityResponse = null;
		String panverificationURL=null;
		String res="";
		long start = 0;
		long end = 0;
		int statusCode = 0 ;
		start = System.currentTimeMillis();
		try(CloseableHttpClient  httpclient1=HttpClients.createDefault();){

			panverificationURL= Utility.getPropertyFileValue("panverificationURL").trim();
			String panSubscriptionKey = Utility.getPropertyFileValue("panSubscriptionKey").trim();

			RequestConfig config = RequestConfig.custom()
					.setConnectTimeout(TIMEOUT_MILLIS)
					.setConnectionRequestTimeout(TIMEOUT_MILLIS)
					.setSocketTimeout(TIMEOUT_MILLIS)
					.build();

			URIBuilder builder1 = new URIBuilder(panverificationURL);
			java.net.URI uri1 = builder1.build();
			HttpPost req1 = new HttpPost(uri1);


			String token = getDedupeAccessToekn(customerId, contextCalled);
			JSONObject panRequest = new JSONObject();
			panRequest.put("PAN", pan);
			panRequest.put("Product", product);
			String jsonRequest = panRequest.toString();
			
			req1.setConfig(config);
			req1.setHeader("Content-Type", "application/json");
			req1.setHeader("Authorization", token);
			req1.setHeader("Ocp-Apim-Subscription-Key", panSubscriptionKey);

			StringEntity reqEntity1 = new StringEntity(jsonRequest);
			req1.setEntity(reqEntity1);
			start = System.currentTimeMillis();
			try(CloseableHttpResponse response1 = httpclient1.execute(req1)){
				logger.info("============response1============="+response1);

				statusCode = response1.getStatusLine().getStatusCode();
				org.apache.http.HttpEntity entity1 = response1.getEntity();

				logger.info("============statusCode============="+statusCode);
				
				end = System.currentTimeMillis();
				if (statusCode == 200 && entity1 != null) {
					
					res = EntityUtils.toString(entity1);
					
					logger.info("responseEntity======="+res);
					
					if(res != null){
					
					JSONObject statusBody =new JSONObject(res) ;

					logger.info("statusBody======="+statusBody);
					
					entityResponse = statusBody.has("status") ? statusBody.getString("status"):"PAN Not Found";
				
					Utility.loadNewRelicCustomEvent(panverificationURL,  String.valueOf(end-start), String.valueOf(statusCode), "",contextCalled,customerId);
					}
					else{
					logger.info("====Response is Blank==="+customerId);	
					entityResponse = "PAN Not Found";
					Utility.loadNewRelicCustomEvent(panverificationURL, String.valueOf(end-start), "", "FAIL",contextCalled,customerId);
					}
            

				}else{
					logger.info("====Response status other than 200 in panverificationURL API==="+customerId);
					Utility.loadNewRelicCustomEvent(panverificationURL, String.valueOf(end-start), "", "FAIL",contextCalled,customerId);
					entityResponse ="PAN Not Found";
					
				}

				System.out.println("=======entityResponse======="+entityResponse);
			}
		}catch(Exception e){
			logger.info("----- Exception in panVerification ------ "+ e);
			if(!(customerId.isEmpty())){
				dbManipulation.recordExeption("PAN API","API issue", customerId);
			}
			
			Utility.loadNewRelicCustomExceptionEvent(panverificationURL, String.valueOf(System.currentTimeMillis()-start),"", "FAIL", e.toString(),contextCalled,customerId);
			entityResponse ="PAN Not Found";
		}
		return entityResponse;


	}

	public String oKycresponseData(JSONObject responseJson,String customerId) throws JSONException {

		logger.info("========oKycresponseData customerId========="+customerId);
		String entityResponse = null;
		String okycAdharService =null;
		long start = 0;
		String statusCode = "";
		start = System.currentTimeMillis();
		try(CloseableHttpClient  httpclient1=HttpClients.createDefault();){

			okycAdharService =  Utility.getPropertyFileValue("okycAdharService").trim();
			String oKycDownServiceSubscription =  Utility.getPropertyFileValue("oKycDownServiceSubscription").trim();

			JSONObject requestOkycJson = new JSONObject();

			RequestConfig config = RequestConfig.custom()
					.setConnectTimeout(TIMEOUT_MILLIS)
					.setConnectionRequestTimeout(TIMEOUT_MILLIS)
					.setSocketTimeout(TIMEOUT_MILLIS)
					.build();

			URIBuilder builder1 = new URIBuilder(okycAdharService);
			java.net.URI uri1 = builder1.build();
			HttpPost req1 = new HttpPost(uri1);

			String userIdokyc = responseJson.get("UserId").toString();
			String requestIdokyc = responseJson.get("RequestId").toString();
			requestOkycJson.put("UserId", userIdokyc);
			requestOkycJson.put("RequestId", requestIdokyc);

			String jsonRequest = requestOkycJson.toString();

			req1.setConfig(config);
			req1.setHeader("Content-Type", "application/json");
			req1.setHeader("Ocp-Apim-Subscription-Key", oKycDownServiceSubscription);
			req1.setHeader("Authorization", payToken.getTokenForCustId(customerId,Constants.OKYCRESPONSE));

			StringEntity reqEntity1 = new StringEntity(jsonRequest);
			req1.setEntity(reqEntity1);
			start = System.currentTimeMillis();
			try(CloseableHttpResponse response1 = httpclient1.execute(req1)){
				statusCode = String.valueOf(response1.getStatusLine().getStatusCode());
				logger.info("============response1============="+response1);
				org.apache.http.HttpEntity entity1 = response1.getEntity();
				entityResponse = EntityUtils.toString(entity1);

				if(entityResponse != null){
					logger.info("=======entityResponse======="+entityResponse);
					Utility.loadNewRelicCustomEvent(okycAdharService, String.valueOf(System.currentTimeMillis()-start), statusCode, "",Constants.OKYCRESPONSE,customerId);
					return entityResponse;
				}else{
					Utility.loadNewRelicCustomEvent(okycAdharService, String.valueOf(System.currentTimeMillis()-start), statusCode, "FAIL",Constants.OKYCRESPONSE,customerId);
				}

			}
		}catch(Exception e){
			logger.error("----- Exception in oKycresponseData ------ ", e);
			if(!(customerId.isEmpty())){
				dbManipulation.recordExeption("OKYC API","API issue", customerId);
			}
			entityResponse = new JSONObject().put("status", "fail").toString();
			Utility.loadNewRelicCustomExceptionEvent(okycAdharService, String.valueOf(System.currentTimeMillis()-start), statusCode, "FAIL",e.toString(),Constants.OKYCRESPONSE,customerId);
		}
		return entityResponse;

	}

	/************new CKYC API implementation*************/
	@Trace
	public String CkycSearchApiIntegrationNew(String pan,String customerId) {
		
		logger.info("========CkycSearchApiIntegrationNew customerId========="+customerId);
		String cKycSearchServiceSubscription = Utility.getPropertyFileValue("CKYCSsubscriptionKeyNew");
		String cKycSearchEndPoint = null;
		JSONObject soapDatainJsonObject = null;
		JSONObject cKycSearchApiResponse = new JSONObject();
		long start = 0;
		long end = 0;
		String statusCode = "";
		start = System.currentTimeMillis();
		HttpComponentsClientHttpRequestFactory httpRequestFactory = null;
		try {
			RequestConfig config = RequestConfig.custom()
					.setConnectTimeout(TIMEOUT_MILLIS_SEARCH)
					.setConnectionRequestTimeout(TIMEOUT_MILLIS_SEARCH)
					.setSocketTimeout(TIMEOUT_MILLIS_SEARCH)
					.build();

			try (CloseableHttpClient httpclient = HttpClients.createDefault()) {

				cKycSearchEndPoint=Utility.getPropertyFileValue("SearchInCKYCNew");
				URIBuilder builder = new URIBuilder(cKycSearchEndPoint);
				URI uri = builder.build();
				HttpPost req = new HttpPost(uri);
				req.setConfig(config);
				req.setHeader("Content-Type", "application/json");
				req.setHeader("Ocp-Apim-Subscription-Key", cKycSearchServiceSubscription);
				req.setHeader("Authorization", "Bearer ".concat(getDedupeAccessToekn(customerId,Constants.ADDNTBUSERDATA)));

				String transtionID = fixedDepositDao.getDealID();
				String reqID = "BJFD".concat(fixedDepositDao.getDealID());

				StringBuilder cKycSearchReqString = new StringBuilder();

				cKycSearchReqString.append("<A56SearchInCkycRequest>\n");
				cKycSearchReqString.append("<ApiToken>" + "608a2bb6-5e76-45fb-99d3-efc7cba2e89a" + "</ApiToken>\n");
				cKycSearchReqString.append("<RequestId>" + reqID + "</RequestId>\n");
				cKycSearchReqString.append("<ParentCompany></ParentCompany>\n");
				cKycSearchReqString.append("<SearchInCkycRequestDetails>\n");
				cKycSearchReqString.append("<CkycSearchRequestDetail>\n");
				cKycSearchReqString.append("<TransactionId>" + reqID + "</TransactionId>\n");
				cKycSearchReqString.append("<RecordIdentifier>" + "FD" + "</RecordIdentifier>\n");
				cKycSearchReqString.append("<ApplicationFormNo></ApplicationFormNo>\n");
				cKycSearchReqString.append("<BranchCode></BranchCode>\n");
				cKycSearchReqString.append("<InputIdType>" + "C" + "</InputIdType>\n");
				cKycSearchReqString.append("<InputIdNo>" + pan + "</InputIdNo>\n");
				cKycSearchReqString.append("<SourceSystem>FD_Online</SourceSystem>\n");
				cKycSearchReqString.append("<SourceSystemSegment></SourceSystemSegment>\n");
				cKycSearchReqString.append("<Remarks></Remarks>\n");
				cKycSearchReqString.append("</CkycSearchRequestDetail>\n");

				cKycSearchReqString.append("</SearchInCkycRequestDetails>\n");
				cKycSearchReqString.append("</A56SearchInCkycRequest>\n");

				start = System.currentTimeMillis();

				logger.info("====================jsonreg1 xml ===================" + cKycSearchReqString);

				StringEntity reqEntity = new StringEntity("\"" + cKycSearchReqString.toString() + "\"");
				req.setEntity(reqEntity);
				logger.info("==================== req ckycSearchApiIntegration===================" + req.toString());
				
				try (CloseableHttpResponse response1 = httpclient.execute(req)) {
					
					statusCode = String.valueOf(response1.getStatusLine().getStatusCode());
					if (response1 != null) {
						logger.info("==================== response1 ckycSearchApiIntegration===================" + response1.toString());
						org.apache.http.HttpEntity entity = response1.getEntity();
						logger.info("==================== entity : entity ckycSearchApiIntegration ===================" + entity);

						end = System.currentTimeMillis();
						logger.info("==================== ckycSearchApiIntegration : API ===================" + (end - start) + " millis");


						String res = EntityUtils.toString(entity);
						soapDatainJsonObject = XML.toJSONObject(res);
						logger.info("==================== EntityUtils  ckycSearchApiIntegration===================" + soapDatainJsonObject);

					}
				}

				if(!soapDatainJsonObject.isEmpty())
				{

					JSONObject searchApirequestDetails =soapDatainJsonObject.has("A56SearchInCkycResponse")? soapDatainJsonObject.getJSONObject("A56SearchInCkycResponse"):new JSONObject();

					String requestStatus=searchApirequestDetails.has("RequestStatus")? searchApirequestDetails.get("RequestStatus").toString():"RejectedByTW";

					if(!("RejectedByTW".equalsIgnoreCase(requestStatus)))
					{
						JSONObject searchApirequestDetailsJson =searchApirequestDetails.has("Details")?searchApirequestDetails.getJSONObject("Details"):new JSONObject();
						JSONObject searchInCkycResponseDetails=searchApirequestDetailsJson.has("SearchInCkycResponseDetails")?searchApirequestDetailsJson.getJSONObject("SearchInCkycResponseDetails"):new JSONObject();

						String transactionStatus = searchInCkycResponseDetails.get("TransactionStatus").toString();
						String transactionCode =searchInCkycResponseDetails.has("TransactionRejectionCode")? searchInCkycResponseDetails.get("TransactionRejectionCode").toString() : "";
						if ("CKYCSuccess".equalsIgnoreCase(transactionStatus)) {
							cKycSearchApiResponse.put("response", searchInCkycResponseDetails.toString());

						} else {
							cKycSearchApiResponse.put("response", "searchFail");
							cKycSearchApiResponse.put("RejectedCode", transactionCode);
						}	

					}
					else {
						cKycSearchApiResponse.put("response", "searchFail");
					}
					Utility.loadNewRelicCustomEvent(cKycSearchEndPoint, String.valueOf(end-start), statusCode, "",Constants.ADDNTBUSERDATA,customerId);
				}else {
					cKycSearchApiResponse.put("response", "searchFail");
					Utility.loadNewRelicCustomEvent(cKycSearchEndPoint, String.valueOf(System.currentTimeMillis()-start), statusCode, "FAIL",Constants.ADDNTBUSERDATA,customerId);
				}
			}
		} catch (Exception e) {
			logger.error("=======CkycSearchApiIntegration======", e);
			logger.info("=======CkycSearchApiIntegration Exception ====== "+e.toString());
			if(!(customerId.isEmpty())){
				dbManipulation.recordExeption("CKYC SEARCH API","API issue", customerId);
			}
			cKycSearchApiResponse.put("response", "searchFail");
			cKycSearchApiResponse.put("Exception", e.toString());
			Utility.loadNewRelicCustomExceptionEvent(cKycSearchEndPoint, String.valueOf(System.currentTimeMillis()-start),statusCode,"FAIL", e.toString(),Constants.ADDNTBUSERDATA,customerId);
		} finally {
			if (httpRequestFactory != null) {
				try {
					httpRequestFactory.destroy();
					logger.info("----- Closing Resources.... ----- ");
				} catch (Exception e) {
					cKycSearchApiResponse.put("response", "searchFail");
					logger.error("----- Exception in Closing Resources callRestApi ----- ", e);
				}
			}
		}
		return cKycSearchApiResponse.toString();

	}
	@Trace
	public String CkycDownloadApiIntegrationNew(String ckycRequestId, String RequestVal, String mob, String customerId) {

		logger.info("=======CkycDownloadApiIntegrationNew customerId========"+customerId);
		String cKycDownServiceSubscription =Utility.getPropertyFileValue("CKYCSsubscriptionKeyNew");
		JSONObject cKycDownloadApiResponse = new JSONObject();

		JSONObject soapDatainJsonObject = null;
		String cKycDownloadEndPoint = null;
		long start = 0;
		long end = 0;
		String statusCode = "";
		start = System.currentTimeMillis();
		try {
			RequestConfig config = RequestConfig.custom()
					.setConnectTimeout(TIMEOUT_MILLIS_DOWNLOAD)
					.setConnectionRequestTimeout(TIMEOUT_MILLIS_DOWNLOAD)
					.setSocketTimeout(TIMEOUT_MILLIS_DOWNLOAD)
					.build();

			try (CloseableHttpClient httpclient = HttpClients.createDefault();) {

				cKycDownloadEndPoint= Utility.getPropertyFileValue("DownloadInckycNew");
				URIBuilder builder = new URIBuilder(cKycDownloadEndPoint);
				URI uri = builder.build();
				HttpPost req = new HttpPost(uri);


				req.setConfig(config);
				req.setHeader("Content-Type", "application/json");
				req.setHeader("Ocp-Apim-Subscription-Key", cKycDownServiceSubscription);
				req.setHeader("Authorization", "Bearer ".concat(getDedupeAccessToekn(customerId,Constants.ADDNTBUSERDATA)));

				String transtionID = fixedDepositDao.getDealID();
				String ckycDownloadTxnID = "BJFD".concat(fixedDepositDao.getDealID());

				StringBuilder cKycDownloadReqString = new StringBuilder();

				cKycDownloadReqString.append("<A53DownloadFromCkycRequest>\n");
				cKycDownloadReqString.append("<ApiToken>" + "608a2bb6-5e76-45fb-99d3-efc7cba2e89a" + "</ApiToken>\n");
				cKycDownloadReqString.append("<RequestId>" + ckycDownloadTxnID + "</RequestId>\n");
				cKycDownloadReqString.append("<ParentCompany></ParentCompany>\n");
				cKycDownloadReqString.append("<DownloadFromCkycRequestDetails>\n");
				cKycDownloadReqString.append("<CkycDownloadRequestDetail>\n");
				cKycDownloadReqString.append("<TransactionId>" + ckycDownloadTxnID + "</TransactionId>\n");
				cKycDownloadReqString.append("<RecordIdentifier>" + "FD" + "</RecordIdentifier>\n");
				cKycDownloadReqString.append("<ApplicationFormNo></ApplicationFormNo>\n");
				cKycDownloadReqString.append("<BranchCode></BranchCode>\n");
				cKycDownloadReqString.append("<CKYCNumber>" + ckycRequestId + "</CKYCNumber>\n");
				cKycDownloadReqString.append("<DOB>" + RequestVal + "</DOB>\n");   //RequestVal is DOB of customer
				cKycDownloadReqString.append("<MobileNumber></MobileNumber>\n");
				cKycDownloadReqString.append("<Pincode></Pincode>\n");
				cKycDownloadReqString.append("<BirthYear></BirthYear>\n");
				cKycDownloadReqString.append("<APITags></APITags>\n");
				cKycDownloadReqString.append("<SourceSystem>FD_Online</SourceSystem>\n");
				cKycDownloadReqString.append("<SourceSystemSegment></SourceSystemSegment>\n");
				cKycDownloadReqString.append("<Remarks></Remarks>\n");
				cKycDownloadReqString.append("</CkycDownloadRequestDetail>\n");
				cKycDownloadReqString.append("</DownloadFromCkycRequestDetails>\n");
				cKycDownloadReqString.append("</A53DownloadFromCkycRequest>\n");


				start = System.currentTimeMillis();
				logger.info("====================jsonreg1 xml in ckycDownloadApiIntegration ===================" + cKycDownloadReqString);
				StringEntity reqEntity = new StringEntity("\"" + cKycDownloadReqString.toString() + "\"");
				req.setEntity(reqEntity);
				String res="";
				logger.info("==================== req ===================" + req.toString());
				try (CloseableHttpResponse response1 = httpclient.execute(req)) {

					statusCode = String.valueOf(response1.getStatusLine().getStatusCode());
					if (response1 != null) {
						logger.info("==================== response1 in ckycDownloadApiIntegration ===================" + response1.toString());
						org.apache.http.HttpEntity entity = response1.getEntity();
						logger.info("==================== entity : entity ===================" + entity);

						end = System.currentTimeMillis();
						logger.info("==================== ckycDownloadApiIntegration : API ===================" + (end - start) + " millis");

						res = EntityUtils.toString(entity);
						soapDatainJsonObject = XML.toJSONObject(res);
						logger.info("==================== EntityUtils IN ckycDownloadApiIntegration===================" + soapDatainJsonObject);


					}
				}

				if(!soapDatainJsonObject.isEmpty())
				{

					JSONObject downloadStatus = soapDatainJsonObject.has("A53DownloadCkycResponse")?soapDatainJsonObject.getJSONObject("A53DownloadCkycResponse"):new JSONObject();
					String requestStatus=downloadStatus.has("RequestStatus")? downloadStatus.get("RequestStatus").toString():"NA";

					if(!("RejectedByTW".equalsIgnoreCase(requestStatus)))
					{
						JSONObject downloadFromCkycResponseDetails =downloadStatus.has("DownloadFromCkycResponseDetails")?downloadStatus.getJSONObject("DownloadFromCkycResponseDetails"):new JSONObject();
						JSONObject CkycDownloadResponseDetail =downloadFromCkycResponseDetails.has("CkycDownloadResponseDetail")?downloadFromCkycResponseDetails.getJSONObject("CkycDownloadResponseDetail"):new JSONObject();


						String transactionStatus =CkycDownloadResponseDetail.has("TransactionStatus")? CkycDownloadResponseDetail.get("TransactionStatus").toString():"NA";
						String transactionCode = CkycDownloadResponseDetail.has("TransactionRejectionCode") ? CkycDownloadResponseDetail.get("TransactionRejectionCode").toString() : "NA";

						if ("CKYCSuccess".equalsIgnoreCase(transactionStatus)) {
							String downloadRetriv = retriveCkycResponseNew(CkycDownloadResponseDetail.toString(), RequestVal, mob, customerId);
							if ("failMobDoc".equalsIgnoreCase(downloadRetriv)) {
								cKycDownloadApiResponse.put("response", "downloadFail");
								cKycDownloadApiResponse.put("RejectedCode", transactionCode);
							} else {
								cKycDownloadApiResponse.put("response", downloadRetriv);
							}

						} else {
							cKycDownloadApiResponse.put("response", "downloadFail");
							cKycDownloadApiResponse.put("RejectedCode", transactionCode);
						}	

					}else
					{
						cKycDownloadApiResponse.put("response", "downloadFail");
					}
					Utility.loadNewRelicCustomEvent(cKycDownloadEndPoint, String.valueOf(System.currentTimeMillis()-start), statusCode, "",Constants.ADDNTBUSERDATA,customerId);
				}else
				{
					cKycDownloadApiResponse.put("response", "downloadFail");
					Utility.loadNewRelicCustomEvent(cKycDownloadEndPoint, String.valueOf(System.currentTimeMillis()-start), statusCode, "FAIL",Constants.ADDNTBUSERDATA,customerId);
				}

			}
		} catch (Exception e) {
			logger.error("=======CkycDownloadApiIntegration======", e);
			logger.info("=======CkycDownloadApiIntegration Exception ====== "+e.toString());
			if(!(customerId==null && customerId.isEmpty())){
				dbManipulation.recordExeption("CKYC DOWNLOAD API","API issue", customerId);
			}
			cKycDownloadApiResponse.put("response", "downloadFail");
			cKycDownloadApiResponse.put("Exception", e.toString());
			Utility.loadNewRelicCustomExceptionEvent(cKycDownloadEndPoint, String.valueOf(System.currentTimeMillis()-start),statusCode, "FAIL", e.toString(),Constants.ADDNTBUSERDATA,customerId);
		}

		return cKycDownloadApiResponse.toString();



	}

	public String retriveCkycResponseNew(String ckycDownloadApiRes, String dateOfBirth, String mobileNumber, String customerId) {

		JSONObject dedupeResponseJson = new JSONObject();

		try {
			JSONObject downloadStatus = new JSONObject(ckycDownloadApiRes);


			String transactionStatusDown = downloadStatus.get("TransactionStatus").toString();
			String custAddress = "";
			String custPin = "";
			String custCity = "";
			String fullName = "";

			dedupeResponseJson.put("downloadCKCYAPI", transactionStatusDown);

			if("CKYCSuccess".equalsIgnoreCase(transactionStatusDown))
			{   

				JSONObject CKYCPersonalDetail = downloadStatus.getJSONObject("CKYCPersonalDetail");
				String ckycMobileNumber = !CKYCPersonalDetail.get("CKYCMobileNumber").toString().isEmpty() ? CKYCPersonalDetail.get("CKYCMobileNumber").toString().replaceAll("[{}]", "") : "";
				JSONObject ckycDataCoument = downloadStatus.getJSONObject("CKYCImageDetails");
				JSONArray ckycDocumentArray=ckycDataCoument.getJSONArray("CKYCImageDetails");

				if(ckycMobileNumber.equalsIgnoreCase(mobileNumber) && ckycDocumentArray != null)
				{
					LocalDate currentDate = LocalDate.now();
					LocalDate birthDateFromBrowser = LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(dateOfBirth))) ;
					int ageFromBrowser=Period.between(birthDateFromBrowser, currentDate).getYears();

					String cKYCPerAdd1 =  !(CKYCPersonalDetail.get("CKYCPerAdd1").toString().isEmpty()) ? CKYCPersonalDetail.get("CKYCPerAdd1").toString().replaceAll("( +)"," ").trim().replaceAll("[{}]", "") : "";
					String cKYCPerAdd2 =  !(CKYCPersonalDetail.get("CKYCPerAdd2").toString().isEmpty()) ? CKYCPersonalDetail.get("CKYCPerAdd2").toString().replaceAll("( +)"," ").trim().replaceAll("[{}]", "") : "";
					String cKYCPerAdd3 =  !(CKYCPersonalDetail.get("CKYCPerAdd3").toString().isEmpty()) ? CKYCPersonalDetail.get("CKYCPerAdd3").toString().replaceAll("( +)"," ").trim().replaceAll("[{}]", "") : "";
					custPin = !(CKYCPersonalDetail.get("CKYCPerAddPin").toString().isEmpty()) ? CKYCPersonalDetail.get("CKYCPerAddPin").toString().replaceAll("( +)"," ").trim().replaceAll("[{}]", "") : "";
					custCity = !CKYCPersonalDetail.get("CKYCPerAddCity").toString().isEmpty() ? CKYCPersonalDetail.get("CKYCPerAddCity").toString().replaceAll("( +)"," ").trim().replaceAll("[{}]", "") : "";
					String cKYCFullName = !CKYCPersonalDetail.get("CKYCFullName").toString().isEmpty() ? CKYCPersonalDetail.get("CKYCFullName").toString().replaceAll("[{}]", "").trim() : "";
					String cKYCGender = !CKYCPersonalDetail.get("CKYCGender").toString().isEmpty() ? CKYCPersonalDetail.get("CKYCGender").toString().replaceAll("[{}]", "").trim() : "M";

					String first ="";
					String second ="";

					String titleFullname = cKYCFullName.substring(0, cKYCFullName.indexOf(' ')).replaceAll("( +)"," ").trim().toUpperCase();
					String content = cKYCFullName.substring(cKYCFullName.indexOf(' ') + 1).replaceAll("( +)"," ").trim().toUpperCase();

					switch (titleFullname) {

					case "MR":
						first = content.substring(0,content.indexOf(' ')).trim();
						second = content.substring(content.indexOf(' ') + 1).trim();
						break;

					case "MS":
						first = content.substring(0,content.indexOf(' ')).trim();
						second = content.substring(content.indexOf(' ') + 1).trim();
						break;

					case "MRS":
						first = content.substring(0,content.indexOf(' ')).trim();
						second = content.substring(content.indexOf(' ') + 1).trim();
						break;

					case "MISS":
						first = content.substring(0,content.indexOf(' ')).trim();
						second = content.substring(content.indexOf(' ') + 1).trim();
						break;


					default:
						first = titleFullname;
						second = content;
						break;
					}


					fullName = first +" "+second;

					if("".equalsIgnoreCase(cKYCPerAdd2) && "".equalsIgnoreCase(cKYCPerAdd3)){
						custAddress = cKYCPerAdd1;
					}else if("".equalsIgnoreCase(cKYCPerAdd3)) 
					{
						custAddress = cKYCPerAdd1 + " "+ cKYCPerAdd2;	
					}else{
						custAddress = cKYCPerAdd1 + " "+ cKYCPerAdd2 +" "+ cKYCPerAdd3;
					}


					logger.info("======CKYC Address====="+custAddress);
					logger.info("======CKYC cKYCPerAddCity====="+custCity);

					if(!(custAddress == null || custAddress.isEmpty())){
						if(!(fullName == null || fullName.isEmpty() || " ".equalsIgnoreCase(fullName))){
							if(!(custPin == null || custPin.isEmpty() )){



								dedupeResponseJson.put("address", custAddress);
								dedupeResponseJson.put("city", custCity);
								dedupeResponseJson.put("pin", custPin);
								dedupeResponseJson.put("fullName", fullName);
								dedupeResponseJson.put("downloadStatus", "SucessMobDoc");
								dedupeResponseJson.put("gender", cKYCGender);



								if (ckycDocumentArray != null && (ckycDocumentArray.length() > 0))
								{
									JSONObject objects= new JSONObject();
									JSONObject passPortData= new JSONObject();
									JSONObject adharCardtData= new JSONObject();
									JSONObject voterCardtData= new JSONObject();
									JSONObject drivingLietData= new JSONObject();
									JSONObject panData = new JSONObject();
									for(int i = 0; i < ckycDocumentArray.length(); i++)
									{
										objects = ckycDocumentArray.getJSONObject(i);
										String docName=objects.get("CKYCImageType").toString();
										logger.info("----------------ckycDocumentArray objects------------------"+   objects.getString("CKYCImageType"));	

										switch(docName)
										{
										case "Passport":
											passPortData=objects;
											break;
										case  "AadharCard":
											adharCardtData=objects;
											break;
										case  "Voter ID":
											voterCardtData=objects;
											break;
										case  "Driving License":
											drivingLietData=objects;
											break;
										case "PAN":
											panData=objects;
											break;

										default:
											objects=objects;
											break;

										}
									}





									if(passPortData !=null && passPortData.length() >0)
									{
										objects=passPortData;
									}
									else if(adharCardtData !=null && adharCardtData.length() >0)
									{
										objects=adharCardtData;
									}
									else if(drivingLietData !=null && drivingLietData.length()>0)
									{
										objects=drivingLietData;
									}
									else if(voterCardtData !=null && voterCardtData.length()>0)
									{
										objects=voterCardtData;
									}

									if(panData != null && panData.length()>0){
										objects.put("panData", panData);
									}


									/*if((passPortData !=null && passPortData.length() >0) ||(adharCardtData !=null && adharCardtData.length() >0)
												||(drivingLietData !=null && drivingLietData.length()>0) || (voterCardtData !=null && voterCardtData.length()>0)){
									 *///String filePath = "///softs/clover/DigitalFixedDepositServices/createJsonFile";
									String filePath = Utility.getPropertyFileValue("ckycBase64filepath").trim();

									String saveFilePath=createJSONfile(objects.toString(), customerId, filePath);
									dedupeResponseJson.put("saveFilePath", saveFilePath);

									if(ageFromBrowser >= 60){
										dedupeResponseJson.put(Constants.CUST_TYPE, Constants.DEDUPE_STB);
									}else{
										dedupeResponseJson.put(Constants.CUST_TYPE,  "CKYCETB");
									}

									dedupeResponseJson.put(Constants.DEDUPE_CUST_TYPE, Constants.DEDUPE_ETB);

								}else{
									dedupeResponseJson.put("downloadStatus", "failMobDoc");
									dedupeResponseJson.put(Constants.DEDUPE_CUST_TYPE, Constants.DEDUPE_NTB);
								}

							}else{
								dedupeResponseJson.put("downloadStatus", "failMobDoc");
								dedupeResponseJson.put(Constants.DEDUPE_CUST_TYPE, Constants.DEDUPE_NTB);
							}
						}else{
							dedupeResponseJson.put("downloadStatus", "failMobDoc");
							dedupeResponseJson.put(Constants.DEDUPE_CUST_TYPE, Constants.DEDUPE_NTB);
						}
					}else{
						dedupeResponseJson.put("downloadStatus", "failMobDoc");
						dedupeResponseJson.put(Constants.DEDUPE_CUST_TYPE, Constants.DEDUPE_NTB);
					}



				}else{

					dedupeResponseJson.put("downloadStatus", "failMobDoc");
					dedupeResponseJson.put(Constants.DEDUPE_CUST_TYPE, Constants.DEDUPE_NTB);
				}


			}else{
				dedupeResponseJson.put("downloadStatus", "failMobDoc");
				dedupeResponseJson.put(Constants.DEDUPE_CUST_TYPE, Constants.DEDUPE_NTB);
			}
			
		}catch (StackOverflowError t) {
			dedupeResponseJson.put("downloadStatus", "failMobDoc");
			dedupeResponseJson.put(Constants.DEDUPE_CUST_TYPE, Constants.DEDUPE_NTB);
			logger.error(" === Exception in StackOverflowError === ", t);	

		} catch (Exception e) {
			dedupeResponseJson.put("downloadStatus", "failMobDoc");
			dedupeResponseJson.put(Constants.DEDUPE_CUST_TYPE, Constants.DEDUPE_NTB);
			logger.error(" === Exception in retriveCkycResponse === ", e);
			if(!(customerId==null && customerId.isEmpty())){
				dbManipulation.recordExeption("CKYC","Exception due to internal call", customerId);
			}
		}

		return dedupeResponseJson.toString();
	}

	//GET_CUSTOMER_PI_PRODUCT_DETAILS api calling
	@Trace
	public String customerDetailsAPICall(String mobileNumber, String dateOfBirth,String customerId,String contextCalled) throws JSONException
	{

		String apiUrl =Utility.getPropertyFileValue("demogapiurl");
		String subScriptionKey = Utility.getPropertyFileValue("demog_prefill_subkey");
		String entityResponse = null;
		long start=0;
		long end = 0;
		String statusCode = "";


		String newDateofBirth="";
		JSONObject response=new JSONObject();
		
		try {
			Date custBirthDate = new SimpleDateFormat("dd/MM/yyyy").parse(dateOfBirth);

			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			newDateofBirth = format.format(custBirthDate);
			logger.info("====dob in customerCheckEtbNtb request =="+custBirthDate);

		} catch (ParseException e) {
			logger.error("-----------exception in customerCheckEtbNtb dob formate-------------",e);
		}
		start = System.currentTimeMillis();
		try {

			RequestConfig config = RequestConfig.custom()
					.setConnectTimeout(TIMEOUT_MILLIS)
					.setConnectionRequestTimeout(TIMEOUT_MILLIS)
					.setSocketTimeout(TIMEOUT_MILLIS)
					.build();

			try(CloseableHttpClient closeableHttpClient1 =HttpClients.createDefault();){

				
				String customerCheckEtbNtb ="{\"Mobile\":\""+mobileNumber+"\",\"DOB\":\""+newDateofBirth+"\"}"; 

				String token = "Bearer ".concat(getDedupeAccessToekn(customerId,contextCalled));
				logger.info("---------token---------"+token);

				URIBuilder builder1 = new URIBuilder(apiUrl);
				logger.info("customerCheckEtbNtb........."+apiUrl);
				java.net.URI uri1 = builder1.build();
				HttpPost req1 = new HttpPost(uri1);

				logger.info("--------customerCheckEtbNtb API end-point --------" + builder1);	

				req1.setConfig(config);
				req1.setHeader("Content-Type", "application/json");
				req1.setHeader("Authorization", token);
				req1.setHeader("Ocp-Apim-Subscription-Key", subScriptionKey);


				logger.info("=========reqString ======= "+customerCheckEtbNtb);

				StringEntity reqEntity1 = new StringEntity(customerCheckEtbNtb);
				req1.setEntity(reqEntity1);
				start = System.currentTimeMillis();
				
				try(CloseableHttpResponse closeableHttpResponse1 = closeableHttpClient1.execute(req1))
				{
					statusCode = String.valueOf(closeableHttpResponse1.getStatusLine().getStatusCode());
					end = System.currentTimeMillis();
					org.apache.http.HttpEntity entity1 = closeableHttpResponse1.getEntity();   
					entityResponse = EntityUtils.toString(entity1);
					logger.info("=========customerCheckEtbNtb EntityResponse ======= "+entityResponse);
					if(!entityResponse.isEmpty())
					{
						if(!entityResponse.contains("The service is unavailable")){
						JSONObject customerDetailsResp = new JSONObject(entityResponse);

						String apiStatus = customerDetailsResp.has(Constants.STATUS) ? customerDetailsResp.get(Constants.STATUS).toString() : "Fail";
						logger.info("======retrivecustomerCheckEtbNtbResponse status=====" + apiStatus);
						if (Constants.STATUS_SUCCESS.equalsIgnoreCase(apiStatus))
						{
							response.put(Constants.CUSTOMER_DEATILS, customerDetailsResp);
							response.put(Constants.STATUS, Constants.STATUS_SUCCESS).toString();
							Utility.loadNewRelicCustomEvent(apiUrl, String.valueOf(System.currentTimeMillis() - start), statusCode, "",contextCalled,customerId);
						}
						else
						{
							response.put(Constants.STATUS, Constants.STATUS_FAIL).toString();
							Utility.loadNewRelicCustomEvent(apiUrl, String.valueOf(System.currentTimeMillis() - start), statusCode, "FAIL",contextCalled,customerId);
						}
						
					}else
					{
						response.put(Constants.STATUS, Constants.STATUS_FAIL).toString();
						Utility.loadNewRelicCustomEvent(apiUrl, String.valueOf(System.currentTimeMillis() - start), statusCode, "FAIL",contextCalled,customerId);
					}
					
					}else
					{
						response.put(Constants.STATUS, Constants.STATUS_FAIL).toString();
						Utility.loadNewRelicCustomEvent(apiUrl, String.valueOf(System.currentTimeMillis() - start), statusCode, "FAIL",contextCalled,customerId);
					}

				}
			}

		} catch (Exception e)
		{
			logger.error("===========Exception in customerCheckEtbNtb==========",e);
			if(!(customerId==null && customerId.isEmpty())){
				dbManipulation.recordExeption("DEMOG","API issue", customerId);
			}
			response.put(Constants.STATUS, Constants.STATUS_FAIL).toString();
			Utility.loadNewRelicCustomExceptionEvent(apiUrl, String.valueOf(System.currentTimeMillis()-start), statusCode, "FAIL", e.toString(),contextCalled,customerId);
		}
		return response.toString();

	}

	// to check whether Customer ETB or NTB
	@Trace
	public String customerCheckEtbNtb(String mobileNumber, String dateOfBirth,String customerId,String contextCalled) throws JSONException
	{
		logger.info("=====customerCheckEtbNtb customerId====="+customerId+"===contextCalled==="+contextCalled);
		JSONObject returnVal = new JSONObject();
		JSONObject detailsjson = new JSONObject();
		JSONObject productBlankUserDeatils = new JSONObject();
		JSONObject otherUserDetails = new JSONObject();

		try {
			String apiRes = customerDetailsAPICall(mobileNumber, dateOfBirth,customerId,contextCalled);
			logger.info("===========customerDetailsAPICall data to Manipulate==========" + apiRes);
			JSONObject custIdResponseJson = new JSONObject(apiRes);

			String apiStatus = custIdResponseJson.has(Constants.STATUS) ? custIdResponseJson.get(Constants.STATUS).toString() : Constants.STATUS_FAIL;
			logger.info("======retrivecustomerCheckEtbNtbResponse status=====" + apiStatus);

			LocalDate currentDate = LocalDate.now();
			LocalDate birthDateFromBrowser = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(dateOfBirth)));
			int ageFromBrowser = Period.between(birthDateFromBrowser, currentDate).getYears();
			logger.info("======retrivecustomerCheckEtbNtbResponse age=====" + ageFromBrowser);


			if (Constants.STATUS_SUCCESS.equalsIgnoreCase(apiStatus))
			{
				String custDatailsResponse = custIdResponseJson.has(Constants.CUSTOMER_DEATILS) ? custIdResponseJson.get(Constants.CUSTOMER_DEATILS).toString() : "";
				logger.info("===========customerDetailsAPICall custDatailsResponse data to Manipulate==========" + custDatailsResponse);

				JSONObject customerDeatilsAPIResponse = new JSONObject(custDatailsResponse);
				String custDatailsResponseapiStatus = customerDeatilsAPIResponse.has(Constants.STATUS) ? customerDeatilsAPIResponse.get(Constants.STATUS).toString() : "Fail";
				logger.info("======customerDetailsAPICall status=====" + custDatailsResponseapiStatus);

				if (Constants.STATUS_SUCCESS.equalsIgnoreCase(custDatailsResponseapiStatus))
				{
					JSONArray customerPiProductDetails = customerDeatilsAPIResponse.has("CustomerPiProductDetails") ? customerDeatilsAPIResponse.getJSONArray("CustomerPiProductDetails") : new JSONArray();

					JSONArray custIdArray = new JSONArray();
					JSONArray productArray = new JSONArray();

					Boolean custIdFlag = true;
					String fdCustID = "";
					logger.info("------retrivecustomerCheckEtbNtbResponse CustomerPiProductDetails length-----" + customerPiProductDetails.length());
					if (!customerPiProductDetails.isEmpty() && customerPiProductDetails.length() > 0)
					{
						if (ageFromBrowser >= 60) {
							returnVal.put(Constants.CUST_TYPE, Constants.DEDUPE_STB);
						} else {
							returnVal.put(Constants.CUST_TYPE, Constants.DEDUPE_ETB);
						}
						Boolean productFDcheck = false;
						Boolean productFdBlankcheck = false;
						Boolean productothercheck = false;

						for (int i = 0; i < customerPiProductDetails.length(); i++) 
						{
							JSONObject objects = customerPiProductDetails.getJSONObject(i);
							String custIdFetch = objects.get(Constants.CUSTOMERID).toString();
							logger.info("------retrivecustomerCheckEtbNtbResponse Cust Id"+custIdFetch);
							if (!custIdFetch.isEmpty() && custIdFetch != null) {
								custIdArray.put(custIdFetch);

							} else {
								custIdFlag = false;
							}

							String product =objects.has(Constants.PRODUCT) ?objects.get(Constants.PRODUCT).toString():"NA";
							JSONObject productObject = new JSONObject();
							productObject.put(Constants.PRODUCT, product);
							productObject.put(Constants.CUSTOMERID, custIdFetch);
							logger.info("------retrivecustomerCheckEtbNtbResponse product Cust Id----" + productObject.toString());
							logger.info("------retrivecustomerCheckEtbNtbResponse product Cust productArray----" + productArray.toString());
							productArray.put(productObject);
							logger.info("------retrivecustomerCheckEtbNtbResponse product Cust productArray----" + productArray.toString());


							if (Constants.FD.equalsIgnoreCase(product)) {
								productFDcheck = true;
								fdCustID = custIdFetch;
								detailsjson.put(Constants.FULL_NAME, objects.has("FNAME") ? objects.get("FNAME") : "NA");
								detailsjson.put("first_name", objects.has("first_name") ? objects.get("first_name") : "NA");
								detailsjson.put("last_name", objects.has("last_name") ? objects.get("last_name") : "NA");
								detailsjson.put(Constants.USER_MOBILE_NUMBER, objects.has("MOBILE") ? objects.get("MOBILE") : "NA");
								detailsjson.put(Constants.DATE_OF_BIRTH, objects.has("DOB") ? objects.get("DOB") : "NA");
								detailsjson.put(Constants.EMAIL, objects.has("EMAIL") ? objects.get("EMAIL") : "NA");
								detailsjson.put(Constants.PIN, objects.has("ZIPCODE") ? objects.get("ZIPCODE") : "NA");
								detailsjson.put(Constants.CITY, objects.has("CITY_NAME") ? objects.get("CITY_NAME") : "NA");
								detailsjson.put("state", objects.has("STATE_NAME") ? objects.get("STATE_NAME") : "NA");
								detailsjson.put("gender", objects.has("GENDER") ? objects.get("GENDER") : "NA");
								detailsjson.put(Constants.PAN, objects.has("PANNO") ? objects.get("PANNO") : "NA");
								detailsjson.put("addresstype", objects.has("addresstype") ? objects.get("addresstype") : "NA");
								detailsjson.put(Constants.ADDRESS_1, objects.has("addres_s1") ? objects.get("addres_s1") : "NA");
								detailsjson.put("address2", objects.has("addres_s2") ? objects.get("addres_s2") : "NA");
								detailsjson.put("address3", objects.has("addres_s3") ? objects.get("addres_s3") : "NA");
								detailsjson.put(Constants.EXISTING_CUST_ID, custIdFetch);
								detailsjson.put(Constants.PIDATA_API, Constants.STATUS_SUCCESS);
							}

							if ("".equalsIgnoreCase(product) ) {
								fdCustID = custIdFetch;
								productFdBlankcheck=true;
								productBlankUserDeatils.put(Constants.FULL_NAME, objects.has("FNAME") ? objects.get("FNAME") : "NA");
								productBlankUserDeatils.put("first_name", objects.has("first_name") ? objects.get("first_name") : "NA");
								productBlankUserDeatils.put("last_name", objects.has("last_name") ? objects.get("last_name") : "NA");
								productBlankUserDeatils.put(Constants.USER_MOBILE_NUMBER, objects.has("MOBILE") ? objects.get("MOBILE") : "NA");
								productBlankUserDeatils.put(Constants.DATE_OF_BIRTH, objects.has("DOB") ? objects.get("DOB") : "NA");
								productBlankUserDeatils.put(Constants.EMAIL, objects.has("EMAIL") ? objects.get("EMAIL") : "NA");
								productBlankUserDeatils.put(Constants.PIN, objects.has("ZIPCODE") ? objects.get("ZIPCODE") : "NA");
								productBlankUserDeatils.put(Constants.CITY, objects.has("CITY_NAME") ? objects.get("CITY_NAME") : "NA");
								productBlankUserDeatils.put("state", objects.has("STATE_NAME") ? objects.get("STATE_NAME") : "NA");
								productBlankUserDeatils.put("gender", objects.has("GENDER") ? objects.get("GENDER") : "NA");
								productBlankUserDeatils.put(Constants.PAN, objects.has("PANNO") ? objects.get("PANNO") : "NA");
								productBlankUserDeatils.put(Constants.EXISTING_CUST_ID, custIdFetch);
								productBlankUserDeatils.put(Constants.PIDATA_API, Constants.STATUS_SUCCESS);
							}
							if (!"".equalsIgnoreCase(product) || !Constants.FD.equalsIgnoreCase(product)) 
							{
								fdCustID = custIdFetch;
								productothercheck=true;
								otherUserDetails.put(Constants.FULL_NAME, objects.has("FNAME") ? objects.get("FNAME") : "NA");
								otherUserDetails.put("first_name", objects.has("first_name") ? objects.get("first_name") : "NA");
								otherUserDetails.put("last_name", objects.has("last_name") ? objects.get("last_name") : "NA");
								otherUserDetails.put(Constants.USER_MOBILE_NUMBER, objects.has("MOBILE") ? objects.get("MOBILE") : "NA");
								otherUserDetails.put(Constants.DATE_OF_BIRTH, objects.has("DOB") ? objects.get("DOB") : "NA");
								otherUserDetails.put(Constants.EMAIL, objects.has("EMAIL") ? objects.get("EMAIL") : "NA");
								otherUserDetails.put(Constants.PIN, objects.has("ZIPCODE") ? objects.get("ZIPCODE") : "NA");
								otherUserDetails.put(Constants.CITY, objects.has("CITY_NAME") ? objects.get("CITY_NAME") : "NA");
								otherUserDetails.put("state", objects.has("STATE_NAME") ? objects.get("STATE_NAME") : "NA");
								otherUserDetails.put("gender", objects.has("GENDER") ? objects.get("GENDER") : "NA");
								otherUserDetails.put(Constants.PAN, objects.has("PANNO") ? objects.get("PANNO") : "NA");
								otherUserDetails.put(Constants.EXISTING_CUST_ID, custIdFetch);
								otherUserDetails.put(Constants.PIDATA_API, Constants.STATUS_SUCCESS);
							}
							//need to prefill in response of Validate OTP
						}
						if (custIdFlag) 
						{

							returnVal.put(Constants.CUSTIDARRAY, custIdArray);
							returnVal.put(Constants.PRODUCT_CUSTID_ARRAY, productArray);
							logger.info("------retrivecustomerCheckEtbNtbResponse Flags for prefill---productFDcheck---"+productFDcheck+"----productFdBlankcheck----"+productFdBlankcheck+"------productothercheck-----"+productothercheck);
							if (productFDcheck) {
								returnVal.put(Constants.PRODUCT, Constants.FD);
								returnVal.put(Constants.CUSTOMERID, fdCustID);
								returnVal.put("userDetails", detailsjson);
								returnVal.put(Constants.DEDUPE_CUST_TYPE, Constants.DEDUPE_ETB);
							} else if(productFdBlankcheck){
								returnVal.put(Constants.PRODUCT, Constants.FD_BLANK);
								returnVal.put(Constants.CUSTOMERID, fdCustID);
								returnVal.put("userDetails", productBlankUserDeatils);
								returnVal.put(Constants.DEDUPE_CUST_TYPE, Constants.DEDUPE_ETB);
							}
							else if(productothercheck){
								returnVal.put(Constants.PRODUCT, Constants.FD_BLANK);
								returnVal.put(Constants.CUSTOMERID, fdCustID);
								returnVal.put("userDetails", otherUserDetails);
								returnVal.put(Constants.DEDUPE_CUST_TYPE, Constants.DEDUPE_ETB);
							}else
							{
								returnVal.put(Constants.DEDUPE_CUST_TYPE, Constants.DEDUPE_NTB);	
							}

						} else {
							returnVal.put(Constants.DEDUPE_CUST_TYPE, Constants.DEDUPE_NTB);
						}

					} else {
						returnVal.put(Constants.DEDUPE_CUST_TYPE, Constants.DEDUPE_NTB);
					}
				} else {
					returnVal.put(Constants.DEDUPE_CUST_TYPE, Constants.DEDUPE_NTB);
				}
			} else {
				returnVal.put(Constants.DEDUPE_CUST_TYPE, Constants.DEDUPE_NTB);
			}

		} catch (Exception e) {
			logger.error("===========Exception in customerCheckEtbNtb==========", e);
			if(!(customerId==null && customerId.isEmpty())){
				dbManipulation.recordExeption("DEMOG","Exception due to internal call", customerId);
			}
			returnVal.put(Constants.DEDUPE_CUST_TYPE, Constants.DEDUPE_NTB);
			Utility.loadNewRelicException(e.toString(), "exception in customerCheckEtbNtb", "", customerId);
		}
		return returnVal.toString();
	}



	@Trace
	public String getDataToPrefill(String mobileNumber, String customerId,String fdcNumber,String contextCalled) throws JSONException {

		logger.info("=====getDataToPrefill fdcNumber====="+fdcNumber+"===contextCalled===="+contextCalled);
		String apiUrl =Utility.getPropertyFileValue("prefillApiurl");
		String subScriptionKey = Utility.getPropertyFileValue("demog_prefill_subkey");
		String entityResponse = null;
		long start = 0;
		long end = 0;
		String statusCode = "";

		JSONObject detailsjson = new JSONObject();
		start = System.currentTimeMillis();
		try {

			RequestConfig config = RequestConfig.custom()
					.setConnectTimeout(TIMEOUT_MILLIS)
					.setConnectionRequestTimeout(TIMEOUT_MILLIS)
					.setSocketTimeout(TIMEOUT_MILLIS)
					.build();

			try(CloseableHttpClient closeableHttpClient1 =HttpClients.createDefault();){

				String requestJson ="{\"MOBILE\":\""+mobileNumber+"\",\"CUSTOMERID\":\""+customerId+"\"}";

				String token = "Bearer ".concat(getDedupeAccessToekn(fdcNumber,contextCalled));
				logger.info("---------token---------"+token);

				URIBuilder builder1 = new URIBuilder(apiUrl);
				logger.info("getDataToPrefill........."+apiUrl);
				java.net.URI uri1 = builder1.build();
				HttpPost req1 = new HttpPost(uri1);

				logger.info("--------getDataToPrefill API end-point --------" + builder1);	

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
					end = System.currentTimeMillis();
					org.apache.http.HttpEntity entity1 = closeableHttpResponse1.getEntity();   
					entityResponse = EntityUtils.toString(entity1);
					logger.info("=========getDataToPrefill EntityResponse ======= "+entityResponse);


					JSONArray responseArray= new JSONArray(); 
					JSONObject detailsResult = new JSONObject();

					if(entityResponse!=null && !entityResponse.isEmpty())
					{
						JSONObject responseJson = new JSONObject(entityResponse);
						if (responseJson.getString("status").equals("success")){

							responseArray=responseJson.getJSONArray("data");
							logger.info("======Data ====== "+responseJson.getJSONArray("data")+"======= responseArray "+responseArray);
							if(responseArray!=null){
								if(!responseArray.isNull(0))
								{                  
									detailsResult=responseArray.getJSONObject(0);
									String address1=detailsResult.has("addres_s1")?detailsResult.get("addres_s1").toString():"";
									String address2=detailsResult.has("addres_s2")?detailsResult.get("addres_s2").toString():"";
									String address3=detailsResult.has("addres_s3")?detailsResult.get("addres_s3").toString():"";
									String addressToPrefill="";

									if(address1 == null || "null".equalsIgnoreCase(address1)){
										address1="";
									}
									if(address2 == null || "null".equalsIgnoreCase(address2)){
										address2="";
									}
									if(address3 == null || "null".equalsIgnoreCase(address3)){
										address3="";
									}
									if("".equalsIgnoreCase(address1) && "".equalsIgnoreCase(address2) && "".equalsIgnoreCase(address3)){
										addressToPrefill="";
									}else if ("".equalsIgnoreCase(address2) && "".equalsIgnoreCase(address3)){
										addressToPrefill=address1;
									}else if("".equalsIgnoreCase(address3)){
										addressToPrefill=address1.concat(","+address2);
									}else{
										addressToPrefill=address1.concat(","+address2).concat(","+address3);
									}
									detailsjson.put(Constants.ADDRESS_1, addressToPrefill);

									detailsjson.put(Constants.PIDATA_API,Constants.STATUS_SUCCESS);
									Utility.loadNewRelicCustomEvent(apiUrl, String.valueOf(System.currentTimeMillis()-start), statusCode, "",contextCalled,fdcNumber);	
								}else{
									detailsjson.put(Constants.PIDATA_API,Constants.STATUS_FAIL);
									Utility.loadNewRelicCustomEvent(apiUrl, String.valueOf(System.currentTimeMillis()-start), statusCode, "FAIL",contextCalled,fdcNumber);	
								}
								
							}else{
								detailsjson.put(Constants.PIDATA_API,Constants.STATUS_FAIL);
								Utility.loadNewRelicCustomEvent(apiUrl, String.valueOf(System.currentTimeMillis()-start), statusCode, "FAIL",contextCalled,fdcNumber);
							}
							
						}else{
							detailsjson.put(Constants.PIDATA_API,Constants.STATUS_FAIL);
							Utility.loadNewRelicCustomEvent(apiUrl, String.valueOf(System.currentTimeMillis()-start), statusCode, "FAIL",contextCalled,fdcNumber);
						}
						
					}else{
						detailsjson.put(Constants.PIDATA_API,Constants.STATUS_FAIL);
						Utility.loadNewRelicCustomEvent(apiUrl, String.valueOf(System.currentTimeMillis()-start), statusCode, "FAIL",contextCalled,fdcNumber);
					}

					logger.info(" == getDataToPrefill == " + detailsjson);
				}
			}

		} catch (Exception e) {
			logger.error("===========Exception in getDataToPrefill==========",e);
			if(!(customerId==null && customerId.isEmpty())){
				dbManipulation.recordExeption("PREFILL","API issue", customerId);
			}
			detailsjson.put(Constants.PIDATA_API,Constants.STATUS_FAIL);
			Utility.loadNewRelicCustomExceptionEvent(apiUrl, String.valueOf(System.currentTimeMillis()-start), statusCode, "FAIL", e.toString(),contextCalled,fdcNumber);
		}
		return detailsjson.toString();

	}


}
