package com.bajaj.fixeddeposit.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

import com.bajaj.fixeddeposit.dao.FixedDepositDao;
import com.bajaj.fixeddeposit.model.FixedDepositData;
import com.bajaj.fixeddeposit.service.FormDataService;
import com.bajaj.fixeddeposit.service.OtpService;
import com.bajaj.fixeddeposit.validation.FormFieldValidation;
@Controller
@EnableScheduling
public class SchduleController {
	
	private static final Logger logger = Logger.getLogger(SchduleController.class);
	@Autowired
	FixedDepositDao fixedDepositDao;
	
	@Autowired
	FormDataService formDataService;
	
	@Autowired
	OtpService otpService;
	
	@Autowired
	FormFieldValidation formFieldValidation;

	

	
	@Scheduled(cron = "0 0 23 * * *")
	public  void getBillDeskRequeryStatus() throws IOException {
		logger.info("------------ Scheduler Started -------------"+new Date());
		

		InetAddress localhost = InetAddress.getLocalHost();
		logger.info("======Server IP========"+localhost.getHostAddress());
		
		String localhostIP = localhost.getHostAddress();
		
		if("10.172.2.18".equalsIgnoreCase(localhostIP)){
		
		ArrayList<FixedDepositData> custList = null;
		
		try {
			logger.info("========inside get billdesk===========");
			
		 custList = (ArrayList<FixedDepositData>)fixedDepositDao.custListforgetBillDeskRequery("night");
			logger.info("===============custList========"+custList.size());
		 
			if(!(custList.isEmpty())){
				
				for (FixedDepositData fixedDepositData : custList) {
					
					String getBillDeskRequery = formDataService.getRePaymentStatus(fixedDepositData);
					
					logger.info("=========get Bill Desk Requery Status======"+getBillDeskRequery);
				}
			}
			
		} catch (Exception e) {
			logger.error(" ===== Exception in getBillDeskRequeryData === ", e);
		}
		
		}else{
			logger.info("====Scheduler not run on ======"+localhost.getHostAddress());
		}
		
	}
	
	@Scheduled(cron = "0 0 */1 * * *")
	public  void getBillDeskRequeryHourStatus() throws IOException {
		logger.info("------------ Scheduler Started -------------"+new Date());
		

		InetAddress localhost = InetAddress.getLocalHost();
		logger.info("======Server IP========"+localhost.getHostAddress());
		
		String localhostIP = localhost.getHostAddress();
		
		if("10.172.2.18".equalsIgnoreCase(localhostIP)){
		
		ArrayList<FixedDepositData> custList = null;
		
		try {
			
			logger.info("========inside get billdesk===========");
			
		 custList = (ArrayList<FixedDepositData>)fixedDepositDao.custListforgetBillDeskHourRequery();
			logger.info("===============custList size in getBillDeskRequeryHourStatus========"+custList.size());
		 
			if(!(custList.isEmpty())){
				
				for (FixedDepositData fixedDepositData : custList) {
					
					String getBillDeskRequery = formDataService.getRePaymentStatus(fixedDepositData);
					
					logger.info("=========get Bill Desk Requery Status======"+getBillDeskRequery);
				}
			}
			
			
		} catch (Exception e) {
			logger.error(" ===== Exception in getBillDeskRequeryData === ", e);
		}
		
		}else{
			logger.info("====Scheduler not run on ======"+localhost.getHostAddress());
		}
		
	}
	
	
	
	@PostMapping("/getBillDeskRequery")
	public void getBillDeskRequeryData(){
		
		ArrayList<FixedDepositData> custList = null;
		
		try {
			logger.info("========inside get billdesk===========");
			
		 custList = (ArrayList<FixedDepositData>)fixedDepositDao.custListforgetBillDeskRequery("night");
			logger.info("===============custList========"+custList.size());
		 
			if(!(custList.isEmpty())){
				
				for (FixedDepositData fixedDepositData : custList) {
					
					String getBillDeskRequery = formDataService.getRePaymentStatus(fixedDepositData);
					
					logger.info("=========get Bill Desk Requery Status======"+getBillDeskRequery);
					
				}
				
				
			}
			
			
		} catch (Exception e) {
			logger.error(" ===== Exception in getBillDeskRequeryData === ", e);
		}
		
	}
	
	
	@PostMapping("/getBillDeskRequeryManually")
	@ResponseBody
	public String getBillDeskRequeryDataManually(@RequestBody String customerIdJson, HttpServletRequest request){
		
		String getBillDeskRequery = null;
		try {
			logger.info("========inside get billdesk===========");
			
			JSONObject userData = new JSONObject(customerIdJson);
			
			String customerId = userData.has("customerId") ? userData.get("customerId").toString():"NA";
			
			if(!("NA".equalsIgnoreCase(customerId))){
				FixedDepositData depositData  = fixedDepositDao.getFixedDepositData(customerId);	
				getBillDeskRequery = formDataService.getRePaymentStatus(depositData);
				logger.info("=========get Bill Desk Requery Status======"+getBillDeskRequery);
			}
			
			
			
		} catch (Exception e) {
			logger.error(" ===== Exception in getBillDeskRequeryData === ", e);
		}
		return getBillDeskRequery;
	}
	
	@Scheduled(cron = "0 0 23 * * *")
	public void removeUploadedDocumentsForSuccessFullTransations()
	{
		logger.info("========schedular called for removeUploadedDocuments==== "+new Date());
		

		try
		{
		formDataService.custListRemoveUploadedDocumets();
			logger.info("======Documentes Deleted SuccessFully ");
		}catch(Exception e)
		{
			logger.error("============Exeption in removeUploadedDocuments====",e);
		}
	}
	@Scheduled(cron = "0 0 23 * * *")
	public void removeUploadedDocumentsForFailTransations()
	{
		logger.info("========schedular called for  Fail removeUploadedDocuments==== "+new Date());
		

		try
		{
		formDataService.custListRemoveFailUploadedDocumets();
			logger.info("===== Failed =Documentes Deleted SuccessFully ");
		}catch(Exception e)
		{
			logger.error("============Exeption in Fail removeUploadedDocuments====",e);
		}
	}
	
	
	@PostMapping("/getSFDCResponseManually")
	@ResponseBody
	public String getSFDCResponseManually(@RequestBody String customerIdJson, HttpServletRequest request){
		
		String getBillDeskRequery = null;
		try {
			logger.info("========inside get billdesk===========");
			
			JSONObject userData = new JSONObject(customerIdJson);
			
			String customerId = userData.has("customerId") ? userData.get("customerId").toString():"NA";
			
			if(!("NA".equalsIgnoreCase(customerId)) && customerId.equalsIgnoreCase(HtmlUtils.htmlEscape(customerId)) ){
				FixedDepositData depositData  = fixedDepositDao.getFixedDepositData(customerId);	
				getBillDeskRequery = formDataService.getSFDCStatus(depositData);
				logger.info("=========get Bill Desk Requery Status======"+getBillDeskRequery);
				
				String cutTypeRemark = depositData.getRemarkCustType();
				if("NTBDocumentUpload".equalsIgnoreCase(cutTypeRemark)){
					Runnable runnable=()->{
						formDataService.sendDocumentstoSfdcService(depositData,"getSFDCResponseManually");
					};
					new Thread(runnable).start();
				}
			}
			
			
			
		} catch (Exception e) {
			logger.error(" ===== Exception in getBillDeskRequeryData === ", e);
		}
		return getBillDeskRequery;
	}

	@PostMapping("/sfdcAuditTrailManualDataUpdate")
	@ResponseBody
	public String sfdcAuditTrailManual(@RequestBody String requestArray, HttpServletRequest request)
	{
		
		String responseVal = null;
		try {
			logger.info("========inside get sfdcAuditTrailManual===========");
			responseVal=formDataService.manualReflowDataUpdate(requestArray);
			logger.info("========responseVal in sfdcAuditTrailManual==========="+responseVal);
		} catch (Exception e) {
			logger.error(" ===== Exception in sfdcAuditTrailManual === ", e);
		}
		return responseVal;
	}
	
	
	//maunalAudit Trail
		@PostMapping("/ManulAuditTrailExecute")
		@ResponseBody
		public String ManulAuditTrailExecute(@RequestBody String customerIdJsonArray){

			try {
				logger.info("========inside ManulAuditTrailExecute=========="+new Date());
				return formDataService.ManulAuditTrailExecute(customerIdJsonArray).toString();

			} catch (Exception e) {
				logger.error(" ===== Exception in ManulAuditTrailExecute === ", e);
			}
			return "";
		}
		
		@PostMapping("/ManulAuditTrailExecuteSingleCase")
		@ResponseBody
		public String ManualAuditTrailExecuteSingleCase(@RequestBody String requestJson){

			try {
				logger.info("========inside ManulAuditTrailExecuteSingleCase=========="+new Date());
				
				JSONObject jsonObject = formFieldValidation.manualReflowFieldValidation(requestJson);
				if(jsonObject.isEmpty()) {
					return formDataService.ManualAuditTrailExecuteSingleCase(requestJson);
				}else {
					logger.info("======= request validation error in ManualAuditTrailExecuteSingleCase =======");
				}

			} catch (Exception e) {
				logger.error(" ===== Exception in ManulAuditTrailExecuteSingleCase === ", e);
			}
			return "";
		}
		
		@PostMapping(value = "/manualAuditTrailProcess", consumes = {"multipart/form-data"})
		@ResponseBody
		public String manualAuditTrailProcess(@RequestParam("file") MultipartFile file){
			
			JSONObject response = new JSONObject();
			try {
				if(file.getBytes().length > 0){
					logger.info("========== csv file in manualAuditTrailProcess ==========="+file.getOriginalFilename()+"==="+file.getContentType());
					response = formDataService.manualAuditTrailService(file);
				}
				else{
					logger.info("======= file is empty =======");
				}
			} catch (Exception e) {
				logger.error(" ===== Exception in manualAuditTrailProcess === ", e);
			}
			return response.toString();
		}
		
		

		@PostMapping("/ManualpartialSfdc")
		@ResponseBody
		public String ManualpartialSfdc(@RequestBody String custId){

			try {
				logger.info("========inside ManualpartialSfdc=========="+new Date());
				return otpService.manualpartialSfdc(custId);

			} catch (Exception e) {
				logger.error(" ===== Exception in ManualpartialSfdc === ", e);
			}
			return "";
		}


		@PostMapping("/ManualpartialSfdcMultiple")
		@ResponseBody
		public String ManualpartialSfdcMulitple(@RequestBody String custIdArray){

			try {
				logger.info("========inside ManualpartialSfdcMultiple=========="+new Date());
				return otpService.manualpartialSfdcMulitple(custIdArray).toString();

			} catch (Exception e) {
				logger.error(" ===== Exception in ManualpartialSfdcMultiple === ", e);
			}
			return "";
		}

		@Scheduled(cron = "0 0 8 * * *")
		public  void getBillDeskRequeryStatusFistHalf(){
			logger.info("------------ Scheduler Started getBillDeskRequeryStatusFistHalf-------------"+new Date());
			
			ArrayList<FixedDepositData> custList = null;
			try {
				InetAddress localhost = InetAddress.getLocalHost();
				String localhostIP = localhost.getHostAddress();
				logger.info("======Server IP========"+localhostIP);
				
				if("10.172.2.18".equalsIgnoreCase(localhostIP)) {
					
					logger.info("========inside get billdesk============");
					
					custList = (ArrayList<FixedDepositData>)fixedDepositDao.custListforgetBillDeskRequery("morning");
					logger.info("===============custList========"+custList.size());
					
					if(!custList.isEmpty()) {
						
						for(FixedDepositData depositData : custList) {
							
							String getBillDeskRequery = formDataService.getRePaymentStatus(depositData);
							logger.info("=========get Bill Desk Requery Status======"+getBillDeskRequery);
						}
					}
				}else {
					logger.info("===========Schedular not run on==========="+localhostIP);
				}
				
			} catch (Exception e) {
					logger.error(" ===== Exception in getBillDeskRequeryStatusFistHalf === ", e);
			}
		}
		
}
