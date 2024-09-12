package com.bajaj.fixeddeposit.service;

import org.json.JSONException;
import org.json.JSONObject;

public interface DataDownloadService {
	
	JSONObject fdPdfDownloadService(String customerId) throws JSONException;
	
	JSONObject sdpPdfDownloadService(String customerId) throws JSONException;

	JSONObject sfdcPdfGenerationService(String customerId, String schemeCode) throws JSONException;

	JSONObject sfdcDocumentConvertBase64Service(String customerId) throws JSONException;
	
	JSONObject fdAuditTrailPdf(String customerId) throws JSONException;

	JSONObject okycPdfManipulation(String customerId);
	
}

