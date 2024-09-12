package com.bajaj.fixeddeposit.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bajaj.fixeddeposit.filter.XSSUtils;
import com.bajaj.fixeddeposit.service.DataDownloadService;
import com.bajaj.fixeddeposit.util.Constants;

@RestController
public class DataDownloadController {
	
	private static final Logger logger = Logger.getLogger(DataDownloadController.class);
	
	@Autowired
	private DataDownloadService dataDownloadService;
	
	
	public @ResponseBody String fdPdfDownloader(@RequestParam(value = "investmentType") String investmentType,
			@RequestParam(value = "customerId") String  customerId, HttpServletRequest request, HttpServletResponse response) throws JSONException{
		
		customerId = XSSUtils.stripXSS(customerId);
		investmentType = XSSUtils.stripXSS(investmentType);
		JSONObject pdfResponseJson = new JSONObject();
		logger.info(" ==== customerId  in fdPdfDownloader === " + customerId);
		logger.info(" ==== investmentType  in fdPdfDownloader === " + investmentType);
		
		try {
			HttpSession httpSession = request.getSession(true);
			logger.info(" === session id in fdPdfDownloader === " + httpSession.getId());
			
			String sessionCustomerId = (String) httpSession.getAttribute(Constants.CUSTOMER_ID);
			logger.info(" ==== sessionCustomerId from session === " + sessionCustomerId);
			
			String sessionInvestmentType = (String) httpSession.getAttribute(Constants.INVESTMENT_TYPE);
			logger.info(" ==== sessionInvestmentType from session === " + sessionInvestmentType);
			
						
			JSONObject  pdfServiceRes = new JSONObject();
			
			if (sessionCustomerId.equalsIgnoreCase(customerId)) {
				logger.info("=== json and session customer id matched === ");

					if ("FD".equalsIgnoreCase(investmentType)) {

						pdfServiceRes = dataDownloadService.fdPdfDownloadService(customerId);
						logger.info(" === fdPdfServcieRes in fdPdfDownloader ==== " + pdfServiceRes);
						pdfResponseJson.put(Constants.PDF_STATUS, Constants.STATUS_SUCCESS);
						
					} else if ("SDP".equalsIgnoreCase(investmentType)) {
						pdfServiceRes =  dataDownloadService.sdpPdfDownloadService(customerId);
						logger.info(" === sdpPdfServcieRes in fdPdfDownloader ==== " + pdfServiceRes);
						
						
						pdfResponseJson.put(Constants.PDF_STATUS, Constants.STATUS_SUCCESS);
					} else {
						logger.info(" == PDF not available for this customer  id == " + customerId);
						pdfResponseJson.put(Constants.PDF_STATUS, Constants.STATUS_FAIL);
					}
					
					String pdfStatus = pdfServiceRes.get(Constants.PDF_STATUS) == null ? "NA" : pdfServiceRes.get(Constants.PDF_STATUS).toString();
					logger.info(" === pdfStatus in fdPdfDownloader === " + pdfStatus);
					
					if(Constants.STATUS_SUCCESS.equalsIgnoreCase(pdfStatus)){
						pdfResponseJson.put(Constants.PDF_STATUS, Constants.STATUS_SUCCESS);
						String pdfPath = pdfServiceRes.get(Constants.PDF_PATH) == null ? "NA" : pdfServiceRes.get(Constants.PDF_PATH).toString();
						logger.info(" === pdfPath in fdPdfDownloader === " + pdfPath);
						
						String fileName = pdfPath.substring(pdfPath.lastIndexOf('/') + 1);
						logger.info(" === fileName in fdPdfDownloader === " + fileName);
						
						Path filePath = Paths.get(pdfPath);
						logger.info(" === filePath in fdPdfDownloader === " + filePath);
						
						if(filePath.toFile().exists()){
							
							response.setContentType("application/pdf");
							response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
							Files.copy(filePath, response.getOutputStream());
							response.getOutputStream().flush();
							pdfResponseJson.put(Constants.PDF_PATH, pdfPath);
							
						}else{
							
							logger.info(" == PDF not available for this customer id === " + customerId);
							pdfResponseJson.put(Constants.PDF_STATUS, Constants.STATUS_FAIL);
						}
						
					}else{
						logger.info(" == PDF not available for this customer id ==== " + customerId);
						pdfResponseJson.put(Constants.PDF_STATUS, Constants.STATUS_FAIL);
					}

			}else{
				logger.info(" == json and session customer id not matched === ");
				pdfResponseJson.put(Constants.PDF_STATUS, Constants.STATUS_FAIL);
				
			}
			
			logger.info(" === return response from fdPdfDownloader == "  + pdfResponseJson);
			
			logger.info(" === Customer Journey Completed === ");
			httpSession.invalidate();
			logger.info(" === Session Invalidated here ==== ");
			
			
			
		} catch (Exception e) {
			pdfResponseJson.put(Constants.PDF_STATUS, Constants.STATUS_FAIL);
			logger.error(" === Exception in fdPdfDownloader ==== ", e);
		}
		
		return pdfResponseJson.toString();
		
	}
	
}
