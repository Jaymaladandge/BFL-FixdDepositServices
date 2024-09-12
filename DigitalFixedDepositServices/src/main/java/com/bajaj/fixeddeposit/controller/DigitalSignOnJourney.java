package com.bajaj.fixeddeposit.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import com.bajaj.fixeddeposit.model.sfdc.SSORequest;
import com.bajaj.fixeddeposit.model.sfdc.SingleSignOn;
import com.bajaj.fixeddeposit.service.FormDataService;
import com.bajaj.fixeddeposit.util.Constants;
import com.bajaj.fixeddeposit.util.Encryption;
import com.bajaj.fixeddeposit.util.Utility;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class DigitalSignOnJourney {


	@Autowired
	FormDataService formDataService;

	private static final Logger logger = Logger.getLogger(DigitalSignOnJourney.class);


	@PostMapping(value="/createsinglesignon", consumes="application/json")
	@ResponseBody
	public String singleSignOn(@RequestBody SSORequest ssoRequest, @RequestHeader("X-APP-KEY") String headerAppKeyValue,HttpServletRequest request) throws JsonParseException, JsonMappingException, IOException
	{
		JSONObject response = new JSONObject();
		try  
		{
			logger.info("------ headerAppKeyValue---"+headerAppKeyValue);
			String staticPrivateKey=Utility.getPropertyFileValue("ssoAppKey");
			if(staticPrivateKey.equalsIgnoreCase(headerAppKeyValue) && headerAppKeyValue.equalsIgnoreCase(HtmlUtils.htmlEscape(headerAppKeyValue)))
			{
				String requestData=ssoRequest.getSourceRequest();
				String decryptData=formDataService.getDecryptedRequest(requestData);
				logger.info("-------singleSignOn request------------" + decryptData);
				if(!"".equalsIgnoreCase( decryptData)){
					ObjectMapper mapper = new ObjectMapper();    
					SingleSignOn t  = mapper.readValue(decryptData,SingleSignOn.class);
					String productName=t.getProductName()!=null?t.getProductName():"NA";
					logger.info("------productName in singleSignOn Request ---"+productName);
					if(Constants.FD.equalsIgnoreCase(productName) && productName.equalsIgnoreCase(HtmlUtils.htmlEscape(productName)))
					{
						response=formDataService.createSingleSignOn(t,request);
					}else
					{
						response.put(Constants.STATUS_CODE, Constants.SSOSTATUSCODE_95);
						response.put(Constants.STATUS, Constants.STATUS_FAIL);
						response.put(Constants.API_MESSAGE, Constants.SSOPRODUCTMAPPING);
					}

				}else
				{
					response.put(Constants.STATUS_CODE, Constants.SSOSTATUSCODE_94);
					response.put(Constants.STATUS, Constants.STATUS_FAIL);
					response.put(Constants.API_MESSAGE, Constants.SSOREQUESTMAPPING);
				}
			}else
			{
				response.put(Constants.STATUS_CODE, Constants.SSOSTATUSCODE_93);
				response.put(Constants.STATUS, Constants.STATUS_FAIL);
				response.put(Constants.API_MESSAGE, Constants.SSOHEADMAPPING);
			}
		}
		catch(Exception e)
		{
			logger.error("--------EXception in  singleSignOn----",e);
			response.put(Constants.STATUS_CODE, Constants.SSOSTATUSCODE_91);
			response.put(Constants.STATUS, Constants.STATUS_FAIL);
			response.put(Constants.API_MESSAGE, Constants.RETRIVE_EXCEPTION);
			Utility.loadNewRelicException(e.toString(), Constants.CREATESINGLESIGNON, "", "NA");
		}
		logger.error("--------response before Encryption------"+response.toString());
		return formDataService.getEncryptedRequest(response.toString());

	}


	@PostMapping("/getdatasinglesign")
	public String singleSignOnGetData(@RequestBody String requestJson, HttpServletRequest request, HttpServletResponse response)
	{
		JSONObject ssoResponse = new JSONObject();
		logger.info(" ====== Request for  singleSignOnGetData =======" + requestJson);
		String encryptRes="";
		String customerId="";
		try
		{
			HttpSession httpSession = request.getSession(true);
			logger.info(" === httpSession in singleSignOnGetData == " + httpSession);

			httpSession.invalidate();
			logger.info(" ==== Old Session Destroyed in singleSignOnGetData ==  ");
			httpSession = request.getSession(true);
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Max-Age", 0);
			response.setHeader("SET-COOKIE", "JSESSIONID=" + httpSession.getId() + "; HttpOnly");
			httpSession.setMaxInactiveInterval(30 * 60);

			ssoResponse=formDataService.singleSignOnGetData(requestJson,httpSession,request);
			
			JSONObject reqJson = new JSONObject(requestJson);
			customerId = reqJson.has("fdcNumber")? reqJson.optString("fdcNumber") : "";
			logger.info("============== customerId in singleSignOnGetData ================"+customerId);
			
			String eventHubSingleSignOn = formDataService.eventHubdataCall(customerId, Constants.SINGLESIGNON);
			logger.info("============event Hub singleSignOnGetData=========="+eventHubSingleSignOn);
			
		}
		catch(Exception e)
		{
			logger.error("---- EXception in  singleSignOnGetData---",e);	
			ssoResponse.put(Constants.STATUS, Constants.STATUS_FAIL);
			ssoResponse.put(Constants.API_MESSAGE, "Exception");
			Utility.loadNewRelicException(e.toString(), Constants.SINGLESIGNON, "", customerId);
		}

		logger.error("---- response before Encryption---"+ssoResponse.toString());
		encryptRes = Encryption.getEncryptedVal(ssoResponse);
		logger.info(" Encrypted Response  === " + encryptRes);
		return encryptRes;
	}

}
