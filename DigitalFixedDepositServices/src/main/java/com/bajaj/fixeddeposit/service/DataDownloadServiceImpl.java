package com.bajaj.fixeddeposit.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bajaj.fixeddeposit.dao.FixedDepositDao;
import com.bajaj.fixeddeposit.model.FixedDepositData;
import com.bajaj.fixeddeposit.util.Constants;
import com.bajaj.fixeddeposit.util.DbManipulationUtil;
import com.bajaj.fixeddeposit.util.Utility;
import com.itextpdf.text.Annotation;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

@Service
public class DataDownloadServiceImpl implements DataDownloadService {


	private static final Logger logger = Logger.getLogger(DataDownloadServiceImpl.class);

	@Autowired
	private FixedDepositDao fixedDepositDao;

	@Autowired
	private DbManipulationUtil dbManipulation;  
	
	
	private void pdfImagePrinter(int pageNum,PdfStamper stamper,Image image,float x, float y) throws DocumentException{

		PdfContentByte contentByte=stamper.getOverContent(pageNum);

		image.setAbsolutePosition(x,y);
		image.setAnnotation(new Annotation(0, 0, 0, 0, 3));
		contentByte.addImage(image);
	}

	private void pdfDataPrinter(int pageNum,PdfStamper stamper,String text,float x, float y) throws IOException, DocumentException{


		BaseFont baseFont=BaseFont.createFont("///softs/clover/DigitalFixedDepositServices/OriginalPdf/PDF_Stuff/Dax_ExtraBold.ttf",BaseFont.CP1252,BaseFont.NOT_EMBEDDED);

		PdfContentByte contentByte=stamper.getOverContent(pageNum);

		contentByte.beginText();
		contentByte.setFontAndSize(baseFont, 8);
		contentByte.setTextMatrix(x,y);
		contentByte.setColorFill(BaseColor.BLACK);
		contentByte.showText(text);
		contentByte.endText();


	}

	private void pdfDataPrinter(int pageNum,PdfStamper stamper,String text,float x, float y,int fontSize) throws IOException, DocumentException{


		BaseFont baseFont=BaseFont.createFont("///softs/clover/DigitalFixedDepositServices/OriginalPdf/PDF_Stuff/Dax_ExtraBold.ttf",BaseFont.CP1252,BaseFont.NOT_EMBEDDED);

		PdfContentByte contentByte=stamper.getOverContent(pageNum);

		contentByte.beginText();
		contentByte.setFontAndSize(baseFont,fontSize);
		contentByte.setTextMatrix(x,y);
		contentByte.setColorFill(BaseColor.BLACK);
		contentByte.showText(text);
		contentByte.endText();


	}
	
	@Override
	public JSONObject fdPdfDownloadService(String customerId) throws JSONException {

		JSONObject pdfResponseJson = new JSONObject();
		PdfReader reader = null;
		PdfStamper stamper  = null;

		try {
			FixedDepositData fixedDepositData = fixedDepositDao.getFixedDepositData(customerId);

			String depositAmount = fixedDepositData.getDepositAmount() == null ? "NA" :fixedDepositData.getDepositAmount();
			String tenor = fixedDepositData.getTenor() == null ? "NA" :fixedDepositData.getTenor();
			String interestPayoutType = fixedDepositData.getInterestPayoutType() == null  ? "NA" : fixedDepositData.getInterestPayoutType().replaceAll("-", " ");
			String interestPayout = fixedDepositData.getInterestPayout() == null  ? "NA" : fixedDepositData.getInterestPayout();
			String customerType = fixedDepositData.getCustomerType() == null  ? "NA" : fixedDepositData.getCustomerType();
			String fullName = fixedDepositData.getFullName() == null ? "NA" : fixedDepositData.getFullName();
			String dateOfBorth = fixedDepositData.getDateOfBirth() == null ? "NA" : fixedDepositData.getDateOfBirth();
			String emailAddress = fixedDepositData.getEmailAddress() == null ? "NA" : fixedDepositData.getEmailAddress();
			String address = fixedDepositData.getAddress() == null ? "NA" : fixedDepositData.getAddress();
			String mobileNumber = fixedDepositData.getMobileNumber() == null ? "NA" : fixedDepositData.getMobileNumber();
			String bankName = fixedDepositData.getBankName() == null ? "NA" : fixedDepositData.getBankName();
			String accountNumber = fixedDepositData.getBankAccountNumber() == null ? "NA" : fixedDepositData.getBankAccountNumber();
			String nomineeName = fixedDepositData.getNomineeName() == null ? "NA" : fixedDepositData.getNomineeName();
			String nomineeRelation = fixedDepositData.getRelationshipWithNominee() == null ? "NA" : fixedDepositData.getRelationshipWithNominee();
			String nomineeDateOfBirth = fixedDepositData.getNomineeDateOfBirth() == null ? "NA" : fixedDepositData.getNomineeDateOfBirth();
			String ifscCode = fixedDepositData.getIfscCode() == null ? "NA" : fixedDepositData.getIfscCode();
			String utrNumber = fixedDepositData.getUtrNumber() == null ? "NA" : fixedDepositData.getUtrNumber();
			String applicationFormNo = fixedDepositData.getFormAppNumber() == null ? "NA" : fixedDepositData.getFormAppNumber();

			String fName = fullName.substring(0, fullName.indexOf(' ')).trim();
			String lName = fullName.substring(fullName.indexOf(' ') + 1).trim();

			String originalFilePath = "///softs/clover/DigitalFixedDepositServices/OriginalPdf/FD/Fixed_Deposit_application_form.pdf";
			String filledPdfPath = "///softs/clover/DigitalFixedDepositServices/DownloadedPdf/FD/"+new SimpleDateFormat("dd-MM-yyyy").format(new Date())+"/"+customerId + "_Fixed_Deposit_application_form_New.pdf";

			reader=new PdfReader(originalFilePath);
			File file = new File(filledPdfPath);


			if (!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			if (!file.exists()){
				boolean fileCreateStatus = file.createNewFile();
				logger.info(" ==== fileCreateStatus in storeCustomerDocuments ==== " + fileCreateStatus);

				if(fileCreateStatus){

					stamper = new PdfStamper(reader, new FileOutputStream(filledPdfPath));
					Image image = Image.getInstance("///softs/clover/DigitalFixedDepositServices/OriginalPdf/PDF_Stuff/tick.png");

					String date = new SimpleDateFormat("dd MM yyyy").format(System.currentTimeMillis());

					String applicationDate = date.substring(0, 1)+" "+date.substring(1, 2)+"  "+date.substring(3, 4)+"  "+date.substring(4, 5)+"   "+
							date.substring(6, 7)+"  "+date.substring(7,8)+"  "+date.substring(8,9)+"  "+date.substring(9,10);

					pdfDataPrinter(1, stamper, applicationDate, 48, 757);

					pdfDataPrinter(1, stamper, applicationFormNo, 520, 751);

					// Investment - No of Depositors
					pdfImagePrinter(1, stamper, image,125, 405);

					// single deposit
					pdfImagePrinter(1, stamper, image,48, 372);


					//  Deposit Payable to
					pdfImagePrinter(1, stamper, image,125, 387);

					// no of fds
					pdfDataPrinter(1,stamper,"1", 50,275);

					//fd amount
					pdfDataPrinter(1, stamper, depositAmount, 70, 275);

					pdfDataPrinter(1, stamper, depositAmount, 195, 275);

					// tenor               
					pdfDataPrinter(1, stamper, tenor, 250, 275);  

					if(interestPayoutType.equalsIgnoreCase("Cumulative"))
					{
						pdfImagePrinter(1, stamper, image,282, 284);
					}else if(interestPayoutType.equalsIgnoreCase("Non cumulative")){
						pdfImagePrinter(1, stamper, image,282, 258);

						switch (interestPayout) {

						case "Monthly":
							pdfImagePrinter(1, stamper, image,338, 265);
							break;

						case "Quarterly":	
							pdfImagePrinter(1, stamper, image,338, 250);
							break;

						case "Half Yearly": 
							pdfImagePrinter(1, stamper, image,383, 265);
							break;

						case "Yearly":
							pdfImagePrinter(1, stamper, image,383, 250);
							break;

						default:
							break;

						}

					}


					// for page 2 

					if(customerType.equals("Senior Citizen"))
					{    
						//senior citizen
						pdfImagePrinter(2, stamper, image,88,750);
					}

					pdfDataPrinter(2,stamper,fName, 185,637);
					pdfDataPrinter(2,stamper,lName, 430,637);


					if (!dateOfBorth.equals("NA")) {

						dateOfBorth = dateOfBorth.replace("/", " ");

						String s2 = new SimpleDateFormat("dd MM yyyy").format(new SimpleDateFormat("dd MM yyyy").parse(dateOfBorth));

						String dob1 = s2.substring(0, 1) + "  " + s2.substring(1, 2) + "  " + s2.substring(3, 4) + "  "
								+ s2.substring(4, 5) + "   " + s2.substring(6, 7) + "  " + s2.substring(7, 8) + "  "
								+ s2.substring(8, 9) + "  " + s2.substring(9, 10);

						pdfDataPrinter(2, stamper, dob1, 96, 602);

					}


					pdfDataPrinter(2, stamper, emailAddress, 400, 602);	
					pdfDataPrinter(2, stamper, mobileNumber, 250, 602);               
					pdfDataPrinter(2, stamper,address, 50, 283);


					/// for page 4 payment page
					//Nominee Details
					pdfDataPrinter(2, stamper, nomineeName, 165, 294);
					pdfDataPrinter(2, stamper, nomineeRelation,370, 280);


					if(!nomineeDateOfBirth.equals("") && !nomineeDateOfBirth.equals("NA"))
					{
						nomineeDateOfBirth = nomineeDateOfBirth.replace("/", " ");
						String nomineeDobFormat = new SimpleDateFormat("dd MM yyyy").format(new SimpleDateFormat("dd MM yyyy").parse(nomineeDateOfBirth));

						String nomineeDobFormatDate=nomineeDobFormat.substring(0, 1)+"  "+nomineeDobFormat.substring(1, 2)+"  "+nomineeDobFormat.substring(3, 4)+"  "+nomineeDobFormat.substring(4, 5)+"   "+
								nomineeDobFormat.substring(6, 7)+"  "+nomineeDobFormat.substring(7,8)+"  "+nomineeDobFormat.substring(8,9)+"  "+nomineeDobFormat.substring(9,10);

						pdfDataPrinter(2, stamper, nomineeDobFormatDate, 254, 265);    

					} 

					// Bank Name
					pdfDataPrinter(3,stamper,bankName, 402,768);
					pdfDataPrinter(3,stamper,bankName, 160,690);

					String txnDate = new SimpleDateFormat("dd MM yyyy").format(System.currentTimeMillis());

					txnDate = txnDate.replace(" ", "/");
					String transactionDate=new SimpleDateFormat("dd MM yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(txnDate));

					pdfDataPrinter(3, stamper, accountNumber, 150, 677);
					pdfDataPrinter(3, stamper, "SAVING", 480, 679);        
					pdfDataPrinter(3, stamper, ifscCode, 150, 663);        

					pdfDataPrinter(3, stamper, utrNumber, 153, 721);        
					pdfDataPrinter(3, stamper, transactionDate, 480, 721);  

					stamper.close();
					reader.close();

					pdfResponseJson.put("pdfPath", filledPdfPath);
					pdfResponseJson.put(Constants.PDF_STATUS, Constants.STATUS_SUCCESS);

				}else{
					logger.info(" === pdf not found in fdPdfDownloadService ====== ");
					pdfResponseJson.put(Constants.PDF_STATUS, Constants.STATUS_FAIL);
				}

			}	else{
				logger.info(" ===== pdf not found in fdPdfDownloadService ==== ");
				pdfResponseJson.put(Constants.PDF_STATUS, Constants.STATUS_FAIL);
			}

		} catch (Exception e) {
			pdfResponseJson.put(Constants.PDF_STATUS, Constants.STATUS_FAIL);
			logger.info(" == Exception in pdfDownloadService === ", e);
		}finally {
			if(reader != null){
				reader.close();
				logger.info("======fd PDF Stamping reader close====");			}
			try {
				if(stamper != null){
					stamper.close();
				}
			} catch (DocumentException | IOException e) {
				logger.error("=== Exception in stampimg close=====",e);
			}
		}



		return pdfResponseJson;
	}


	@Override
	public JSONObject sdpPdfDownloadService(String customerId) throws JSONException{

		JSONObject pdfResponseJson = new JSONObject();
		PdfReader reader= null;
		PdfStamper stamper = null;

		try {
			FixedDepositData fixedDepositData = fixedDepositDao.getFixedDepositData(customerId);

			String depositAmount = fixedDepositData.getDepositAmount() == null ? "NA" :fixedDepositData.getDepositAmount();
			String tenor = fixedDepositData.getTenor() == null ? "NA" :fixedDepositData.getTenor();
			String numberOfDeposit= fixedDepositData.getNumberOfDeposit() == null ? "NA" :fixedDepositData.getNumberOfDeposit();
			String sdpDepositDate = fixedDepositData.getDateOfEachDeposit() == null  ? "NA" : fixedDepositData.getDateOfEachDeposit();
			String customerType = fixedDepositData.getCustomerType() == null  ? "NA" : fixedDepositData.getCustomerType();
			String fullName = fixedDepositData.getFullName() == null ? "NA" : fixedDepositData.getFullName();
			String dateOfBorth = fixedDepositData.getDateOfBirth() == null ? "NA" : fixedDepositData.getDateOfBirth();
			String emailAddress = fixedDepositData.getEmailAddress() == null ? "NA" : fixedDepositData.getEmailAddress();
			String address = fixedDepositData.getAddress() == null ? "NA" : fixedDepositData.getAddress();
			String pancard = fixedDepositData.getPanCard() == null ? "NA" : fixedDepositData.getPanCard();
			String mobileNumber = fixedDepositData.getMobileNumber() == null ? "NA" : fixedDepositData.getMobileNumber();
			String bankName = fixedDepositData.getBankName() == null ? "NA" : fixedDepositData.getBankName();
			String accountNumber = fixedDepositData.getBankAccountNumber() == null ? "NA" : fixedDepositData.getBankAccountNumber();
			String nomineeName = fixedDepositData.getNomineeName() == null ? "NA" : fixedDepositData.getNomineeName();
			String nomineeRelation = fixedDepositData.getRelationshipWithNominee() == null ? "NA" : fixedDepositData.getRelationshipWithNominee();
			String nomineeDateOfBirth = fixedDepositData.getNomineeDateOfBirth() == null ? "NA" : fixedDepositData.getNomineeDateOfBirth();
			String ifscCode = fixedDepositData.getIfscCode() == null ? "NA" : fixedDepositData.getIfscCode();
			String applicationFormNo = fixedDepositData.getFormAppNumber() == null ? "NA" : fixedDepositData.getFormAppNumber();

			String fName = fullName.substring(0, fullName.indexOf(' ')).trim();
			String lName = fullName.substring(fullName.indexOf(' ') + 1).trim();

			if(!("NA".equalsIgnoreCase(numberOfDeposit))){
				int number = Integer.parseInt(numberOfDeposit);
				numberOfDeposit = String.valueOf(number-1);
			}


			String originalFilePath = "///softs/clover/DigitalFixedDepositServices/OriginalPdf/SDP/SDP.pdf";
			String filledPdfPath = "///softs/clover/DigitalFixedDepositServices/DownloadedPdf/SDP/"+new SimpleDateFormat("dd-MM-yyyy").format(new Date())+"/"+customerId + "SDP_New.pdf";

			reader=new PdfReader(originalFilePath);
			File file = new File(filledPdfPath);


			if (!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			if (!file.exists()){
				boolean fileCreateStatus = file.createNewFile();
				logger.info(" ==== fileCreateStatus in storeCustomerDocuments ==== " + fileCreateStatus);

				if(fileCreateStatus){

					stamper = new PdfStamper(reader, new FileOutputStream(filledPdfPath));
					Image image = Image.getInstance("///softs/clover/DigitalFixedDepositServices/OriginalPdf/PDF_Stuff/tick.png");

					String date = new SimpleDateFormat("dd MM yyyy").format(System.currentTimeMillis());

					String applicationDate = date.substring(0, 1)+" "+date.substring(1, 2)+"  "+date.substring(3, 4)+"  "+date.substring(4, 5)+"   "+
							date.substring(6, 7)+"  "+date.substring(7,8)+"  "+date.substring(8,9)+"  "+date.substring(9,10);

					pdfDataPrinter(1, stamper, applicationDate, 48, 765);

					pdfDataPrinter(1, stamper, applicationFormNo, 520, 760);

					//  Deposit Payable to
					pdfImagePrinter(1, stamper, image,116, 549);

					//fd amount
					pdfDataPrinter(1, stamper, depositAmount, 163, 521);

					// deposit date
					if("3rd".equals(sdpDepositDate)){
						pdfImagePrinter(1, stamper, image, 176, 509);

					}else if("7th".equals(sdpDepositDate)){
						pdfImagePrinter(1, stamper, image, 207, 509);

					}else if("12th".equals(sdpDepositDate)){
						pdfImagePrinter(1, stamper, image, 236, 509);

					}

					// fd tenor
					pdfDataPrinter(1, stamper, tenor, 265, 495);

					//no of deposit
					pdfDataPrinter(1, stamper, numberOfDeposit, 235, 480);

					if(customerType.equals("Senior Citizen"))
					{    
						pdfImagePrinter(1, stamper, image,116, 407);
					}else if("Existing Customer".equals(customerType)){
						pdfImagePrinter(1, stamper, image,357, 407);
					}

					pdfDataPrinter(1,stamper,fName, 185,295);
					pdfDataPrinter(1,stamper,lName, 430,295);


					if (!dateOfBorth.equals("NA")) {

						dateOfBorth = dateOfBorth.replace("/", " ");

						String s2 = new SimpleDateFormat("dd MM yyyy").format(new SimpleDateFormat("dd MM yyyy").parse(dateOfBorth));

						String dob1 = s2.substring(0, 1) + "  " + s2.substring(1, 2) + "  " + s2.substring(3, 4) + "  "
								+ s2.substring(4, 5) + "   " + s2.substring(6, 7) + "  " + s2.substring(7, 8) + "  "
								+ s2.substring(8, 9) + "  " + s2.substring(9, 10);
						pdfDataPrinter(1, stamper, dob1, 96, 260);

					}

					pdfDataPrinter(1, stamper, emailAddress,380, 263);	
					pdfDataPrinter(1, stamper, mobileNumber, 250, 260); 
					pdfDataPrinter(1, stamper, pancard, 450, 248); 



					// page 2
					//Nominee Details
					pdfDataPrinter(2, stamper, nomineeName, 165, 768);
					pdfDataPrinter(2, stamper, nomineeRelation,370, 755);


					if(!nomineeDateOfBirth.equals("") && !nomineeDateOfBirth.equals("NA") )
					{
						nomineeDateOfBirth = nomineeDateOfBirth.replace("/", " ");
						String nomineeDobFormat = new SimpleDateFormat("dd MM yyyy").format(new SimpleDateFormat("dd MM yyyy").parse(nomineeDateOfBirth));

						String nomineeDobFormatDate=nomineeDobFormat.substring(0, 1)+"  "+nomineeDobFormat.substring(1, 2)+"  "+nomineeDobFormat.substring(3, 4)+"  "+nomineeDobFormat.substring(4, 5)+"   "+
								nomineeDobFormat.substring(6, 7)+"  "+nomineeDobFormat.substring(7,8)+"  "+nomineeDobFormat.substring(8,9)+"  "+nomineeDobFormat.substring(9,10);

						pdfDataPrinter(2, stamper, nomineeDobFormatDate, 254, 740);    

					} 

					pdfDataPrinter(2, stamper,address, 50, 757);	

					// Bank Name
					pdfDataPrinter(2,stamper,bankName, 150,455);
					pdfDataPrinter(2,stamper,bankName, 380,455);

					String txnDate = new SimpleDateFormat("dd MM yyyy").format(System.currentTimeMillis());

					txnDate = txnDate.replace(" ", "/");
					String transactionDate=new SimpleDateFormat("dd MM yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(txnDate));


					pdfDataPrinter(2, stamper, accountNumber, 150, 440);
					pdfDataPrinter(2, stamper, "SAVING", 480, 440);        
					pdfDataPrinter(2, stamper, ifscCode, 150, 425);        
					pdfDataPrinter(2, stamper, transactionDate, 300, 425);  

					stamper.close();

					pdfResponseJson.put("pdfPath", filledPdfPath);
					pdfResponseJson.put(Constants.PDF_STATUS, Constants.STATUS_SUCCESS);

				}else{
					logger.info(" === pdf not found in fdPdfDownloadService ==== ");
					pdfResponseJson.put(Constants.PDF_STATUS, Constants.STATUS_FAIL);
				}

			}else{
				logger.info(" === pdf not found in fdPdfDownloadService ==== ");
				pdfResponseJson.put(Constants.PDF_STATUS, Constants.STATUS_FAIL);
			}

		} catch (Exception e) {
			pdfResponseJson.put(Constants.PDF_STATUS, Constants.STATUS_FAIL);
			logger.info(" == Exception in pdfDownloadService === ", e);
		}finally {
			if(reader != null){
				reader.close();
				logger.info("======fd PDF Stamping reader close====");			}
			try {
				if(stamper != null){
					stamper.close();
				}
			} catch (DocumentException | IOException e) {
				logger.error("=== Exception in stampimg close=====",e);
			}
		}

		return pdfResponseJson;
	}


	@Override
	public JSONObject sfdcPdfGenerationService(String customerId, String schemeCode) throws JSONException{

		JSONObject pdfResponseJson = new JSONObject();

		try {
			FixedDepositData fixedDepositData = fixedDepositDao.getFixedDepositData(customerId);


			String partnerCode = "NA".equalsIgnoreCase(fixedDepositData.getPartnerCode())  ? "76783" : fixedDepositData.getPartnerCode(); 
			String sourcingChannel = fixedDepositData.getRdpLan() == null ? "Online" : "RDPL";
			String sfdcApiRecordId = fixedDepositData.getSfdcRecordId() == null ? "NA" : fixedDepositData.getSfdcRecordId();
			String applicationFormNo = fixedDepositData.getFormAppNumber() == null ? "NA" : fixedDepositData.getFormAppNumber();
			String depositPayableTo = "";
			String depositOption = fixedDepositData.getInterestPayoutType() == null ? "NA" : fixedDepositData.getInterestPayoutType();
			String schemeCodeRef = schemeCode;
			String maturityInstruction = fixedDepositData.getInterestPayout() == null ? "NA" : fixedDepositData.getInterestPayout();
			String depositAmount = fixedDepositData.getDepositAmount() == null ? "NA" :fixedDepositData.getDepositAmount();
			String tenor = fixedDepositData.getTenor() == null ? "NA" :fixedDepositData.getTenor();
			String specialCategory= "";
			String customerSubType = fixedDepositData.getCustomerType() == null  ? "NA" : fixedDepositData.getCustomerType();
			String existingCustId = fixedDepositData.getExistingCustId() == null ? "NA" : fixedDepositData.getExistingCustId();
			String mobileNumber = fixedDepositData.getMobileNumber() == null ? "NA" : fixedDepositData.getMobileNumber();
			String fullName = fixedDepositData.getFullName() == null ? "NA" : fixedDepositData.getFullName();
			String content = fullName;

			String fName =content.contains(" ")?content.substring(0, content.indexOf(' ')).trim():content;
			String mName = "";
			String lName = content.substring(content.indexOf(' ') + 1).trim();	
			String dateOfBorth = fixedDepositData.getDateOfBirth() == null ? "NA" : fixedDepositData.getDateOfBirth();
			String pancard = fixedDepositData.getPanCard() == null ? "NA" : fixedDepositData.getPanCard();
			String address = fixedDepositData.getAddress() == null ? "NA" : fixedDepositData.getAddress();
			String pincode = fixedDepositData.getPinCode() == null ? "NA" : fixedDepositData.getPinCode();
			String city = fixedDepositData.getCity() == null ? "NA" : fixedDepositData.getCity();
			String state = "";
			String country = "India";
			String nomineeName = fixedDepositData.getNomineeName() == null ? "NA" : fixedDepositData.getNomineeName();
			String nomineeLname = "";
			String nomineeDateOfBirth = fixedDepositData.getNomineeDateOfBirth() == null ? "NA" : fixedDepositData.getNomineeDateOfBirth();
			String nomineeRelation = fixedDepositData.getRelationshipWithNominee() == null ? "NA" : fixedDepositData.getRelationshipWithNominee();
			String guardianName = "";
			String monthlyInvestmentAmount= "";
			String interest = fixedDepositData.getInterestRate() == null ? "NA" : fixedDepositData.getInterestRate();
			String numberOfDeposit= fixedDepositData.getNumberOfDeposit() == null ? "NA" :fixedDepositData.getNumberOfDeposit();
			String tenorMonth = fixedDepositData.getTenor() == null ? "NA" : fixedDepositData.getTenor();
			String sdpDepositDate = fixedDepositData.getDateOfEachDeposit() == null  ? "NA" : fixedDepositData.getDateOfEachDeposit();
			String sccoutType = "Savings";
			String utr = fixedDepositData.getUtrNumber() == null ? "NA" : fixedDepositData.getUtrNumber();

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate localDate = LocalDate.now();
			String dateOfTransaction = dtf.format(localDate);

			String nameOfAccountHolder = fullName;
			String accountNumber = fixedDepositData.getBankAccountNumber() == null ? "NA" : fixedDepositData.getBankAccountNumber();
			String ifscCode = fixedDepositData.getIfscCode() == null ? "NA" : fixedDepositData.getIfscCode();

			String okycAadhaarReferenceId = fixedDepositData.getOkycAadhaarreferenceId() == null ? "NA" : fixedDepositData.getOkycAadhaarreferenceId();
			String okycInitiationDT = fixedDepositData.getOkycInitiationDT() == null ? "NA" : fixedDepositData.getOkycInitiationDT();
			String okycEntAadhaarSecurityDT = fixedDepositData.getOkycEntAadhaarSecurityDT() == null ? "NA" : fixedDepositData.getOkycEntAadhaarSecurityDT();
			String okycEntOTPShareDT = fixedDepositData.getOkycEntOTPShareDT() == null ? "NA" : fixedDepositData.getOkycEntOTPShareDT();
			String okycReEntShareCodeDT = fixedDepositData.getOkycReEntShareCodeDT() == null ? "NA" : fixedDepositData.getOkycReEntShareCodeDT();
			String okycDetailsDataDT = fixedDepositData.getOkycDetailsDataDT() == null ? "NA" : fixedDepositData.getOkycDetailsDataDT();
			String okycIPAddress = fixedDepositData.getOkycIPAddress() == null ? "NA" : fixedDepositData.getOkycIPAddress();
			String okycUpdateRecordDT = fixedDepositData.getOkycUpdateRecordDT() == null ? "NA" : fixedDepositData.getOkycUpdateRecordDT();

			String originalFilePath = "///softs/clover/DigitalFixedDepositServices/OriginalPdf/FD/PDF_Format.pdf";
			String filledPdfPath = "///softs/clover/DigitalFixedDepositServices/DownloadedPdf/PDF_Format/"+new SimpleDateFormat("dd-MM-yyyy").format(new Date())+"/"+customerId + "PDF_New.pdf";

			PdfReader reader=new PdfReader(originalFilePath);
			File file = new File(filledPdfPath);


			if (!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			if (!file.exists()){
				boolean fileCreateStatus = file.createNewFile();
				logger.info(" ==== fileCreateStatus in storeCustomerDocuments ==== " + fileCreateStatus);

				if(fileCreateStatus){

					PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(filledPdfPath));

					pdfDataPrinter(1, stamper, partnerCode, 310, 680);
					pdfDataPrinter(1, stamper, sourcingChannel, 310, 665);
					pdfDataPrinter(1, stamper, sfdcApiRecordId, 310, 650);
					pdfDataPrinter(1, stamper, applicationFormNo, 310, 635);
					pdfDataPrinter(1, stamper, depositPayableTo, 310, 575);
					pdfDataPrinter(1, stamper, depositOption, 310, 560);
					pdfDataPrinter(1, stamper, schemeCodeRef, 310, 545);
					pdfDataPrinter(1, stamper, maturityInstruction, 310, 530);
					pdfDataPrinter(1, stamper, depositAmount, 310, 515);
					pdfDataPrinter(1, stamper, tenor, 310, 500);
					pdfDataPrinter(1, stamper, specialCategory, 310, 485);
					pdfDataPrinter(1, stamper, customerSubType, 310, 424);
					pdfDataPrinter(1, stamper, existingCustId, 310, 409);
					pdfDataPrinter(1, stamper, mobileNumber, 310, 394);
					pdfDataPrinter(1, stamper, fName, 310, 378);
					pdfDataPrinter(1, stamper, mName, 310, 364);
					pdfDataPrinter(1, stamper, lName, 310, 348);
					pdfDataPrinter(1, stamper, dateOfBorth, 310, 334);
					pdfDataPrinter(1, stamper, pancard, 310, 318);
					pdfDataPrinter(1, stamper, address, 310, 273);
					pdfDataPrinter(1, stamper, pincode, 310, 258);
					pdfDataPrinter(1, stamper, city, 310, 243);
					pdfDataPrinter(1, stamper, state, 310, 228);
					pdfDataPrinter(1, stamper, country, 310, 212);
					pdfDataPrinter(1, stamper, nomineeName, 310, 145);
					pdfDataPrinter(1, stamper, nomineeLname, 310, 128);
					pdfDataPrinter(1, stamper, nomineeDateOfBirth, 310, 113);
					pdfDataPrinter(1, stamper, nomineeRelation, 310, 98);
					pdfDataPrinter(1, stamper, guardianName, 310, 83);

					pdfDataPrinter(2, stamper, monthlyInvestmentAmount, 310, 737);
					pdfDataPrinter(2, stamper, interest, 310, 722);
					pdfDataPrinter(2, stamper, numberOfDeposit, 310, 707);
					pdfDataPrinter(2, stamper, tenorMonth, 310, 692);
					pdfDataPrinter(2, stamper, sdpDepositDate, 310, 679);
					pdfDataPrinter(2, stamper, sccoutType, 310, 630);
					pdfDataPrinter(2, stamper, utr, 310, 615);
					pdfDataPrinter(2, stamper, dateOfTransaction, 310, 602);
					pdfDataPrinter(2, stamper, nameOfAccountHolder, 310, 587);
					pdfDataPrinter(2, stamper, accountNumber, 310, 572);
					pdfDataPrinter(2, stamper, ifscCode, 310, 557);

					pdfDataPrinter(2, stamper, okycAadhaarReferenceId, 310, 504);
					pdfDataPrinter(2, stamper, okycInitiationDT, 310, 490);
					pdfDataPrinter(2, stamper, okycEntAadhaarSecurityDT, 310, 475);
					pdfDataPrinter(2, stamper, okycEntOTPShareDT, 310, 460);
					pdfDataPrinter(2, stamper, okycReEntShareCodeDT, 310, 445);
					pdfDataPrinter(2, stamper, okycDetailsDataDT, 310, 429);
					pdfDataPrinter(2, stamper, okycIPAddress, 310, 414);
					pdfDataPrinter(2, stamper, okycUpdateRecordDT, 310, 400);

					stamper.close();
					reader.close();

					pdfResponseJson.put("pdfPath", filledPdfPath);
					pdfResponseJson.put(Constants.PDF_STATUS, Constants.STATUS_SUCCESS);


					FileInputStream fin = null;
					String fileEncode= "";
					String fileName = "";

					try{
						if(file.exists()){
							if(file != null){
								fin = new FileInputStream(file);
								byte fileContent[] = new byte[(int)file.length()];
								//create string from byte array
								logger.info("=====fileContent []====="+fileContent.length);
								fin.read(fileContent);
								String encoded = Base64.getEncoder().encodeToString(fileContent);
								logger.info("filename:= "+file.getName());
								fileEncode =encoded;
								logger.info("===fileEncode==="+fileEncode.length());
								fileName =file.getName();
							}
						}
						else{   
							fileEncode ="";
							fileName ="";
						}

					}
					catch (FileNotFoundException e) {
						logger.error("File not found" , e);
					}
					catch (IOException ioe) {
						logger.error("Exception while reading file " , ioe);
					}
					finally {

						if(reader != null){
							reader.close();
							logger.info("======fd PDF Stamping reader close====");
						}

						try {
							if (fin != null) {
								fin.close();
							}
							if(stamper != null){
								stamper.close();
							}
						}
						catch (DocumentException | IOException ioe) {
							logger.info("Error while closing stream: " + ioe);
						}
					}
					pdfResponseJson.put("fileEncode", fileEncode);
					pdfResponseJson.put("fileName", fileName);


				}else{
					logger.info(" === pdf not found in fdPdfDownloadService ==== ");
					pdfResponseJson.put(Constants.PDF_STATUS, Constants.STATUS_FAIL);
				}

			}else{
				logger.info(" === pdf not found in fdPdfDownloadService ==== ");
				pdfResponseJson.put(Constants.PDF_STATUS, Constants.STATUS_FAIL);
			}

		} catch (Exception e) {
			if(!( customerId.isEmpty())){
				dbManipulation.recordExeption("SFDC_PDF_SERVICE","Exception due to internal call", customerId);
			}
			pdfResponseJson.put(Constants.PDF_STATUS, Constants.STATUS_FAIL);
			logger.info(" == Exception in pdfDownloadService === ", e);
		}

		return pdfResponseJson;
	}




	@Override
	public JSONObject sfdcDocumentConvertBase64Service(String FilePath) throws JSONException{

		JSONObject pdfResponseJson = new JSONObject();
		try {
			File file = new File(FilePath);

			FileInputStream fin = null;
			String fileEncode= "";
			String fileName = "";

			try{
				if(file.exists()){
					if(file != null){
						fin = new FileInputStream(file);
						byte fileContent[] = new byte[(int)file.length()];
						//create string from byte array
						logger.info("=====fileContent []====="+fileContent.length);
						fin.read(fileContent);
						String encoded = Base64.getEncoder().encodeToString(fileContent);
						logger.info("filename:= "+file.getName());
						fileEncode =encoded;
						logger.info("===fileEncode==="+fileEncode.length());
						fileName =file.getName();

					}
				}
				else{   
					fileEncode ="";
					fileName ="";
				}

			}
			catch (FileNotFoundException e) {
				logger.error("File not found" , e);
			}
			catch (IOException ioe) {
				logger.error("Exception while reading file " , ioe);
			}

			pdfResponseJson.put("fileEncode", fileEncode);
			pdfResponseJson.put("fileName", fileName);


		} catch (Exception e) {
			pdfResponseJson.put(Constants.PDF_STATUS, Constants.STATUS_FAIL);
			logger.info(" == Exception in pdfDownloadService === ", e);
		}

		return pdfResponseJson;
	}

	@Override
	public JSONObject fdAuditTrailPdf(String customerId) throws JSONException 
	{

		JSONObject pdfResponseJson = new JSONObject();

		try {
			FixedDepositData fixedDepositData = fixedDepositDao.getFixedDepositData(customerId);

			String createdOnDate=fixedDepositData.getCreatedOn()==null?"NA":fixedDepositData.getCreatedOn();
			String fullName = fixedDepositData.getFullName() == null ? "NA" : fixedDepositData.getFullName();
			String applicationFormNo = fixedDepositData.getFormAppNumber() == null ? "NA" : fixedDepositData.getFormAppNumber();
			String custType=fixedDepositData.getRemarkCustType()==null?"NA":fixedDepositData.getRemarkCustType();
			String investType=fixedDepositData.getInvestmentType()==null?"NA":fixedDepositData.getInvestmentType();
			String maturityScheme=fixedDepositData.getMaturityScheme()==null?"NA":fixedDepositData.getMaturityScheme();
			String depositAmount = fixedDepositData.getDepositAmount() == null ? "NA" :fixedDepositData.getDepositAmount();
			String tenor = fixedDepositData.getTenor() == null ? "NA" :fixedDepositData.getTenor();
			String depositOption = fixedDepositData.getInterestPayoutType() == null ? "NA" : fixedDepositData.getInterestPayoutType();
			String customerSubType = fixedDepositData.getCustomerType() == null  ? "NA" : fixedDepositData.getCustomerType();
			String interest = fixedDepositData.getInterestRate() == null ? "NA" : fixedDepositData.getInterestRate();
			String mobileNumber = fixedDepositData.getMobileNumber() == null ? "NA" : fixedDepositData.getMobileNumber();
			String emailId=fixedDepositData.getEmailAddress()==null?"NA":fixedDepositData.getEmailAddress();
			String nomineeName = fixedDepositData.getNomineeName() == null ? "NA" : fixedDepositData.getNomineeName();
			String nomineeAdressCheck=fixedDepositData.getNomineeAddressCheck()==null?"NO":fixedDepositData.getNomineeAddressCheck();
			String nomineeAddress=fixedDepositData.getNomineeAddress()==null?"":fixedDepositData.getNomineeAddress();
			String nomineeDateOfBirth = fixedDepositData.getNomineeDateOfBirth() == null ? "NA" : fixedDepositData.getNomineeDateOfBirth();
			nomineeDateOfBirth = nomineeDateOfBirth.replace("/", "-");
			String nomineeRelation = fixedDepositData.getRelationshipWithNominee() == null ? "NA" : fixedDepositData.getRelationshipWithNominee();
			String guardianName =fixedDepositData.getNomineeGuardianName()==null?"NA":fixedDepositData.getNomineeGuardianName();
			String accountNumber = fixedDepositData.getBankAccountNumber() == null ? "NA" : fixedDepositData.getBankAccountNumber();
			String ifscCode = fixedDepositData.getIfscCode() == null ? "NA" : fixedDepositData.getIfscCode();
			String bankName=fixedDepositData.getBankName()==null?"NA":fixedDepositData.getBankName();
			String utr = fixedDepositData.getUtrNumber() == null ? "NA" : fixedDepositData.getUtrNumber();
			String ipAddress=fixedDepositData.getIpAdress()==null?"NA":fixedDepositData.getIpAdress();
			String bflIpAddress=fixedDepositData.getBflServerIpAddress()==null?"NA":fixedDepositData.getBflServerIpAddress();
			String pincode = fixedDepositData.getPinCode() == null ? "NA" : fixedDepositData.getPinCode();
			String city = fixedDepositData.getCity() == null ? "NA" : fixedDepositData.getCity();
			String state =fixedDepositData.getState()==null?"NA":fixedDepositData.getState();
			String dateOfBorth = fixedDepositData.getDateOfBirth() == null ? "NA" : fixedDepositData.getDateOfBirth();
			dateOfBorth = dateOfBorth.replace("/", "-");
			String partnerCode = "NA".equalsIgnoreCase(fixedDepositData.getPartnerCode())  ? "NA" : fixedDepositData.getPartnerCode(); 
			String parterName=fixedDepositData.getPartnerName()==null?"NA":fixedDepositData.getPartnerName();
			String pancard = fixedDepositData.getPanCard() == null ? "NA" : fixedDepositData.getPanCard();
			String ckycRequestDate=fixedDepositData.getCkycRequestDate()==null?"NA":fixedDepositData.getCkycRequestDate();
			String ckycResponseDate=fixedDepositData.getCkycResponseDate()==null?"NA":fixedDepositData.getCkycResponseDate();
			String ckycdownloadDate=fixedDepositData.getCkycDownloadDate()==null?"":fixedDepositData.getCkycDownloadDate();
			String okycRequestId=fixedDepositData.getOkycAPIRequestID()==null?"NA":fixedDepositData.getOkycAPIRequestID();
			String okycAadhaarReferenceId = fixedDepositData.getOkycAadhaarreferenceId() == null ? "NA" : fixedDepositData.getOkycAadhaarreferenceId();
			String okycInitiationDT = fixedDepositData.getOkycInitiationDT() == null ? "NA" : fixedDepositData.getOkycInitiationDT();
			String okycEntAadhaarSecurityDT = fixedDepositData.getOkycEntAadhaarSecurityDT() == null ? "NA" : fixedDepositData.getOkycEntAadhaarSecurityDT();
			String okycEntOTPShareDT = fixedDepositData.getOkycEntOTPShareDT() == null ? "NA" : fixedDepositData.getOkycEntOTPShareDT();
			String okycReEntShareCodeDT = fixedDepositData.getOkycReEntShareCodeDT() == null ? "NA" : fixedDepositData.getOkycReEntShareCodeDT();
			String okycDetailsDataDT = fixedDepositData.getOkycDetailsDataDT() == null ? "NA" : fixedDepositData.getOkycDetailsDataDT();
			String okycIPAddress = fixedDepositData.getOkycIPAddress() == null ? "NA" : fixedDepositData.getOkycIPAddress();
			String okycUpdateRecordDT = fixedDepositData.getOkycUpdateRecordDT() == null ? "NA" : fixedDepositData.getOkycUpdateRecordDT();

			String addressProodDoc=fixedDepositData.getAddressDocumentSelected()==null?"NA":fixedDepositData.getAddressDocumentSelected();
			String identityDoc=fixedDepositData.getIndentityDocumentSelected()==null?"NA":fixedDepositData.getIndentityDocumentSelected();
			String signProofDoc=fixedDepositData.getSignDocumentSelected()==null?"NA":fixedDepositData.getSignDocumentSelected();
			String gener=fixedDepositData.getGender()==null?"NA":fixedDepositData.getGender();
			String address = fixedDepositData.getAddress() == null ? "NA" : fixedDepositData.getAddress();
			String commCheckbox=fixedDepositData.getCommCheckbox()==null?"NA":fixedDepositData.getCommCheckbox();
			if("No".equalsIgnoreCase(commCheckbox))
			{
				commCheckbox="Yes";	
			}else if("Yes".equalsIgnoreCase(commCheckbox))
			{
				commCheckbox="No";
			}
			
			String commAddress=fixedDepositData.getCommAddress()==null?"NA":fixedDepositData.getCommAddress();
			String commPincode=fixedDepositData.getCommPincode()==null?"NA":fixedDepositData.getCommPincode();
			String fdrPhysicalyRequired=fixedDepositData.getFdrPhysicalyRequired()==null?"":fixedDepositData.getFdrPhysicalyRequired();
			String guardiunPresent=fixedDepositData.getGaurdianCheck()==null?"":fixedDepositData.getGaurdianCheck();
			String nomineeAddressChek=fixedDepositData.getNomineeAddressCheck()==null?"":fixedDepositData.getNomineeAddressCheck();
			String nomineePincode=fixedDepositData.getNomineePincode()==null?"":fixedDepositData.getNomineePincode();
			String numberOfDeposit= fixedDepositData.getNumberOfDeposit() == null ? "NA" :fixedDepositData.getNumberOfDeposit();
            String nominePresent=fixedDepositData.getNomineeCheck()==null?"NA":fixedDepositData.getNomineeCheck();
			String sdpDepositDate = fixedDepositData.getDateOfEachDeposit() == null  ? "NA" : fixedDepositData.getDateOfEachDeposit();
			String paymentMode=fixedDepositData.getPaymentChoice()==null?"":fixedDepositData.getPaymentChoice();
			String transationStatus=fixedDepositData.getTransactionStatus()==null?"":fixedDepositData.getTransactionStatus();

			String fdMaturityAmt=fixedDepositData.getFdMaturityAmnt()==null?"":fixedDepositData.getFdMaturityAmnt();
			String sdpMaturityAmt=fixedDepositData.getSdptotalPayoutAmnt()==null?"":fixedDepositData.getSdptotalPayoutAmnt();
			String intrestPayout=fixedDepositData.getInterestPayout()==null?"":fixedDepositData.getInterestPayout();

			
			String ckycTime=fixedDepositData.getCkcyVerifyTime()==null?"":fixedDepositData.getCkcyVerifyTime();
			String personalDetailsTime=fixedDepositData.getPersonalDetailsTime()==null?"":fixedDepositData.getPersonalDetailsTime();
			String documentUploadTime=fixedDepositData.getDocumentUploadTime()==null?"":fixedDepositData.getDocumentUploadTime();
			String paymentRequestTime=fixedDepositData.getPayementRequestTime()==null?"":fixedDepositData.getPayementRequestTime();
			String paymentResponseTime=fixedDepositData.getPaymentResponseTime()==null?"":fixedDepositData.getPaymentResponseTime();
			String okycTime=fixedDepositData.getOkycLandLaningTime()==null?"":fixedDepositData.getOkycLandLaningTime();
			String schemeTime=fixedDepositData.getSchemeDetailsTime()==null?"":fixedDepositData.getSchemeDetailsTime();
			
			String logTime=fixedDepositData.getTimeOfLogging()==null?"":fixedDepositData.getTimeOfLogging();
			logTime= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(logTime));
			logger.info("------updated log time in audit trail----"+logTime);
			
			String otpTriggeredTime=fixedDepositData.getOtpTriggeredTime()==null?"":fixedDepositData.getOtpTriggeredTime();
			otpTriggeredTime= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(otpTriggeredTime));
			logger.info("------updated otpTriggeredTime in audit trail----"+otpTriggeredTime);
			
			String otpSubmittedTime=fixedDepositData.getOtpSubmittedTime()==null?"":fixedDepositData.getOtpSubmittedTime();
			otpSubmittedTime= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(otpSubmittedTime));
			
			String content = fullName;

			String fullBankName=fixedDepositData.getFullBankName()==null?"":fixedDepositData.getFullBankName();

			String fName =content.contains(" ")?content.substring(0, content.indexOf(' ')).trim():content;
			String notApplicable="NA";

			String originalFilePath = "///softs/clover/DigitalFixedDepositServices/OriginalPdf/FD/FDAuditTrail.pdf";
			String filledPdfPath = "///softs/clover/DigitalFixedDepositServices/DownloadedPdf/FDAuditTrail/"+new SimpleDateFormat("dd-MM-yyyy").format(new Date())+"/Audit_Trail_Document.pdf";

			PdfReader reader=new PdfReader(originalFilePath);
			File file = new File(filledPdfPath);


			if (!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			logger.info(" ==== check file exists in FDAuditTrail  ==== " + file.exists());
			if (file.exists())
			{
				file.delete();
			}
				boolean fileCreateStatus = file.createNewFile();
				logger.info(" ==== fileCreateStatus in FDAuditTrail  ==== " + fileCreateStatus);

				if(fileCreateStatus){
					String fromSubmissionDate = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime()) ;
					String applicant=customerId;
					String bflPortalName="www.bajajfinserv.in";
					PdfStamper pdfStamper = new PdfStamper(reader, new FileOutputStream(filledPdfPath));
					ipAddress=ipAddress.replace("ip=", "");
					pdfDataPrinter(1, pdfStamper, fromSubmissionDate, 63,758);
					pdfDataPrinter(1, pdfStamper, fullName, 90,735);
					pdfDataPrinter(1, pdfStamper, fullName, 232, 455);	 	
					pdfDataPrinter(1, pdfStamper, applicationFormNo, 232, 440);	
					pdfDataPrinter(1, pdfStamper, createdOnDate, 232, 425);
					pdfDataPrinter(1, pdfStamper, custType, 232, 393);
					pdfDataPrinter(1, pdfStamper, investType, 232, 354);     //Scheme/Product Type
					pdfDataPrinter(1, pdfStamper, maturityScheme, 232, 340);  //Sub Scheme for SDP  
					pdfDataPrinter(1, pdfStamper, depositAmount, 232, 325);
					pdfDataPrinter(1, pdfStamper, tenor, 232, 310);       //FD Tenure
					pdfDataPrinter(1, pdfStamper, depositOption, 232, 295);       //Deposit Option
					pdfDataPrinter(1, pdfStamper,"FD".equalsIgnoreCase(investType)?intrestPayout:maturityScheme, 232, 280);   //Interest Payout
//					pdfDataPrinter(1, pdfStamper, "Senior Citizen".equalsIgnoreCase(customerSubType)?"Senior Citizen FD through BFL FD":"Online FD through BFL website", 232, 265);    //Applicable Special Category Benefit
					pdfDataPrinter(1, pdfStamper, "Senior Citizen".equalsIgnoreCase(customerSubType)?"Additional 0.25% for senior citizens":"-", 232, 265);    //Applicable Special Category Benefit
					
					pdfDataPrinter(1, pdfStamper,interest, 232, 245);  
					pdfDataPrinter(1, pdfStamper,"FD".equalsIgnoreCase(investType)?fdMaturityAmt:sdpMaturityAmt, 232, 230);
					pdfDataPrinter(1, pdfStamper, mobileNumber, 232, 210);
					pdfDataPrinter(1, pdfStamper,  emailId, 232, 195);
					pdfDataPrinter(1, pdfStamper, nomineeName+" Address:"+nomineeAddress, 232, 180);
					pdfDataPrinter(1, pdfStamper, nomineeDateOfBirth+ " Relationship:"+nomineeRelation, 232, 162);   //dob
					pdfDataPrinter(1, pdfStamper, guardianName, 232, 146);     
					pdfDataPrinter(1, pdfStamper, fullBankName, 232, 130);
					pdfDataPrinter(1, pdfStamper, accountNumber, 232, 115);
					pdfDataPrinter(1, pdfStamper, ifscCode, 232, 98);
					pdfDataPrinter(1, pdfStamper, utr, 232, 82);
					pdfDataPrinter(1, pdfStamper, bflIpAddress, 232, 66);
					pdfDataPrinter(2, pdfStamper, ipAddress, 232, 795);
					pdfDataPrinter(2, pdfStamper,city+"/" +state, 232, 780);
					pdfDataPrinter(2, pdfStamper, parterName, 232, 760);
					pdfDataPrinter(2, pdfStamper,partnerCode, 232, 740);

					pdfDataPrinter(2, pdfStamper, logTime, 30, 565);  /*DD:MM:YYYY HH:MM:SS:S  */
					
					pdfDataPrinter(2, pdfStamper, logTime, 30, 465);
					pdfDataPrinter(2, pdfStamper, logTime, 30, 340);
					pdfDataPrinter(3, pdfStamper,logTime, 30, 695);  
					pdfDataPrinter(3, pdfStamper,logTime, 30, 475);
					pdfDataPrinter(3, pdfStamper,otpTriggeredTime, 30, 335);
					pdfDataPrinter(3, pdfStamper,otpTriggeredTime, 30, 215);
					
					pdfDataPrinter(2, pdfStamper, ipAddress, 161, 546);     //accessed
					pdfDataPrinter(2, pdfStamper, ipAddress, 340, 616);     //accessed
					pdfDataPrinter(2, pdfStamper, bflIpAddress, 374 , 558);
					pdfDataPrinter(2, pdfStamper, bflPortalName, 374, 538);
					pdfDataPrinter(2, pdfStamper, ipAddress, 464, 569);   


					pdfDataPrinter(2, pdfStamper, ipAddress ,162, 409);	//<Applicant> clickedl
					pdfDataPrinter(2, pdfStamper, ipAddress, 344, 454);     
					pdfDataPrinter(2, pdfStamper, bflIpAddress, 364, 394);
					pdfDataPrinter(2, pdfStamper, bflPortalName, 364, 374);
					pdfDataPrinter(2, pdfStamper,ipAddress, 461, 406);

					pdfDataPrinter(2, pdfStamper, ipAddress ,162, 280);   
					pdfDataPrinter(2, pdfStamper, ipAddress, 340, 325); 
					pdfDataPrinter(2, pdfStamper, bflIpAddress, 370, 270);
					pdfDataPrinter(2, pdfStamper, bflPortalName, 370, 250);
					pdfDataPrinter(2, pdfStamper, ipAddress, 461, 275);

					pdfDataPrinter(3, pdfStamper,ipAddress, 163, 788); 
					pdfDataPrinter(3, pdfStamper,mobileNumber, 163, 758); 
					pdfDataPrinter(3, pdfStamper,dateOfBorth, 190, 740); 
					pdfDataPrinter(3, pdfStamper,"NA".equalsIgnoreCase(partnerCode)?"NO":"YES", 198, 711);
					pdfDataPrinter(3, pdfStamper,"NA".equalsIgnoreCase(partnerCode)?"NA":partnerCode, 215, 676);
					pdfDataPrinter(3, pdfStamper,"NA".equalsIgnoreCase(parterName)?"NA":parterName, 162, 649);
					pdfDataPrinter(3, pdfStamper, ipAddress, 350, 685);     
					pdfDataPrinter(3, pdfStamper, bflIpAddress, 350, 630);
					pdfDataPrinter(3, pdfStamper, bflPortalName, 350, 610);
					pdfDataPrinter(3, pdfStamper, ipAddress, 470, 640);

					pdfDataPrinter(3, pdfStamper, ipAddress, 168, 434);
					pdfDataPrinter(3, pdfStamper,ipAddress, 345, 470);
					pdfDataPrinter(3, pdfStamper,bflIpAddress, 332, 410);
					pdfDataPrinter(3, pdfStamper,bflPortalName, 332, 380);
					pdfDataPrinter(3, pdfStamper,ipAddress,470, 405);

					pdfDataPrinter(3, pdfStamper,applicant,158, 325);
					pdfDataPrinter(3, pdfStamper,ipAddress, 162, 260);
					pdfDataPrinter(3, pdfStamper,ipAddress, 340, 324);
					pdfDataPrinter(3, pdfStamper, bflIpAddress, 345, 270);
					pdfDataPrinter(3, pdfStamper, bflPortalName, 345, 244);
					pdfDataPrinter(3, pdfStamper, ipAddress, 464, 260);

					pdfDataPrinter(3, pdfStamper,bflIpAddress, 330, 185);
					pdfDataPrinter(3, pdfStamper,bflPortalName, 330, 153);
					pdfDataPrinter(3, pdfStamper,ipAddress, 350, 80);
					//pdfDataPrinter(3, pdfStamper,applicant, 465, 162);



					//pending
					pdfDataPrinter(3, pdfStamper, mobileNumber, 161, 151);
					pdfDataPrinter(3, pdfStamper, bflIpAddress, 161, 121);
					pdfDataPrinter(3, pdfStamper,mobileNumber, 466, 127);
					//pdfDataPrinter(3, pdfStamper,ipAddress, 466, 90);
					pdfDataPrinter(3, pdfStamper,bflIpAddress, 466, 60);
					
					
					
					pdfDataPrinter(4, pdfStamper,ipAddress, 163, 725);  
					pdfDataPrinter(4, pdfStamper,ipAddress, 347, 770);
					pdfDataPrinter(4, pdfStamper, bflIpAddress, 335, 725);
					pdfDataPrinter(4, pdfStamper, bflPortalName, 345, 705);

					pdfDataPrinter(4, pdfStamper,bflIpAddress, 466, 753);
					pdfDataPrinter(4, pdfStamper, ipAddress, 466, 705);

					if("DEDUPE".equalsIgnoreCase(custType))
					{
						pdfDataPrinter(4, pdfStamper, notApplicable, 163, 630);
						pdfDataPrinter(4, pdfStamper, notApplicable, 183, 595);
						pdfDataPrinter(4, pdfStamper, notApplicable, 205, 575);
						pdfDataPrinter(4, pdfStamper, notApplicable, 182, 515);
						
						pdfDataPrinter(4, pdfStamper, notApplicable, 350, 615);
						pdfDataPrinter(4, pdfStamper, notApplicable, 350, 595);
						pdfDataPrinter(4, pdfStamper, notApplicable, 350, 565); //Applicant's Email:
						pdfDataPrinter(4, pdfStamper, notApplicable, 350, 535);

						pdfDataPrinter(4, pdfStamper, notApplicable, 355, 475);
						pdfDataPrinter(4, pdfStamper, notApplicable, 355, 460);
						pdfDataPrinter(4, pdfStamper, notApplicable, 466, 445);
						/***NTB Details End Here**/
						pdfDataPrinter(4, pdfStamper, notApplicable, 350, 385);
						pdfDataPrinter(4, pdfStamper,notApplicable, 350, 360);
						pdfDataPrinter(4, pdfStamper, notApplicable, 350, 335);
						pdfDataPrinter(4, pdfStamper, notApplicable, 350, 310);
						pdfDataPrinter(4, pdfStamper, notApplicable, 350, 285);
						pdfDataPrinter(4, pdfStamper,notApplicable, 350, 260);

						/***OKYC Details start Here**/

						pdfDataPrinter(4, pdfStamper,notApplicable, 165, 195);
						pdfDataPrinter(4, pdfStamper,notApplicable, 350, 200);

						pdfDataPrinter(4, pdfStamper,notApplicable, 350, 165);
						pdfDataPrinter(4, pdfStamper, notApplicable, 350, 125);
						pdfDataPrinter(4, pdfStamper,notApplicable, 350, 85);


						/* page no.args 5 start*/
						pdfDataPrinter(5, pdfStamper, notApplicable, 350,785);                        	  
						pdfDataPrinter(5, pdfStamper,notApplicable, 350, 760);       
						pdfDataPrinter(5, pdfStamper, notApplicable, 350, 670);     
						pdfDataPrinter(5, pdfStamper, notApplicable, 350, 640);                             	
						pdfDataPrinter(5, pdfStamper, notApplicable, 350, 610); 
						pdfDataPrinter(5, pdfStamper, notApplicable, 190, 595); //underline   
						pdfDataPrinter(5, pdfStamper, notApplicable, 350, 580);   
						pdfDataPrinter(5, pdfStamper,notApplicable, 480, 580);      //underline               
						pdfDataPrinter(5, pdfStamper, notApplicable, 350,519);                                     	  
						pdfDataPrinter(5, pdfStamper, notApplicable, 350, 500); 
						pdfDataPrinter(5, pdfStamper, notApplicable, 350, 450);     
						pdfDataPrinter(5, pdfStamper, notApplicable, 350, 410);                             	
						pdfDataPrinter(5, pdfStamper, notApplicable, 350, 380);     
						pdfDataPrinter(5, pdfStamper, notApplicable, 350, 350);           
						pdfDataPrinter(5, pdfStamper, notApplicable, 190, 340);      //underline      
						pdfDataPrinter(5, pdfStamper, notApplicable, 356, 290);   
						pdfDataPrinter(5, pdfStamper, notApplicable, 356, 270);	           
						pdfDataPrinter(5, pdfStamper, notApplicable, 356, 240);  
						pdfDataPrinter(5, pdfStamper, notApplicable, 350, 190);         
						pdfDataPrinter(5, pdfStamper, notApplicable, 350, 160);  
						pdfDataPrinter(5, pdfStamper, notApplicable, 180, 170);    //underline        
						pdfDataPrinter(5, pdfStamper, notApplicable, 350, 130);     
						pdfDataPrinter(5, pdfStamper,notApplicable, 470,115);   //underline                             
						pdfDataPrinter(5, pdfStamper,notApplicable, 350, 90);                    

						/* page no.args 5 end*/

						/* page no.args 6 start*/
						pdfDataPrinter(6, pdfStamper, notApplicable, 347,780);                	  
						pdfDataPrinter(6, pdfStamper, notApplicable, 347, 760);   
						pdfDataPrinter(6, pdfStamper, notApplicable, 350, 680);	 
						pdfDataPrinter(6, pdfStamper, notApplicable, 190, 678);	 //underline                 
						pdfDataPrinter(6, pdfStamper, notApplicable, 349, 640);    
						pdfDataPrinter(6, pdfStamper, notApplicable, 464, 660);       //underline                                
						pdfDataPrinter(6, pdfStamper, notApplicable, 349, 610); 
						pdfDataPrinter(6, pdfStamper, notApplicable, 350, 580);	
						pdfDataPrinter(6, pdfStamper, notApplicable, 349, 550);                 	
						pdfDataPrinter(6, pdfStamper, notApplicable, 349, 520); 
						pdfDataPrinter(6, pdfStamper, notApplicable, 349, 490);          
						pdfDataPrinter(6, pdfStamper, notApplicable, 349, 420);              
						pdfDataPrinter(6, pdfStamper, notApplicable, 349, 390);       
						pdfDataPrinter(6,pdfStamper, notApplicable, 349, 360);    
						pdfDataPrinter(6, pdfStamper,notApplicable, 349, 330); 
						pdfDataPrinter(6, pdfStamper, notApplicable, 349,300);  
						pdfDataPrinter(6,pdfStamper, notApplicable, 349, 270); 
						pdfDataPrinter(6, pdfStamper,notApplicable, 349, 235); 
						pdfDataPrinter(6, pdfStamper, notApplicable, 349,200);   
						pdfDataPrinter(6, pdfStamper, notApplicable, 349,170);          
						/* page no.args 6 end*/
						/***OKYC Details End Here**/
						
						/***NTDOCUMENTUPLOAD Details START Here**/
						/* page no.args 7 start*/
						pdfDataPrinter(7, pdfStamper, notApplicable, 347,770);                         	  
						pdfDataPrinter(7, pdfStamper, notApplicable, 347, 740);   
						pdfDataPrinter(7, pdfStamper, notApplicable, 347,700);    
						pdfDataPrinter(7, pdfStamper,notApplicable, 347, 670);	      
						pdfDataPrinter(7, pdfStamper, notApplicable, 347,620);                   
						pdfDataPrinter(7, pdfStamper, notApplicable, 347, 600);
						/*
						 * underline section
						 */	  
						pdfDataPrinter(7, pdfStamper, notApplicable, 190,787);            
						pdfDataPrinter(7, pdfStamper, notApplicable, 180,705);                      
						pdfDataPrinter(7, pdfStamper, notApplicable, 180,678);             
						pdfDataPrinter(7, pdfStamper, notApplicable, 180,620);    
						pdfDataPrinter(7, pdfStamper, notApplicable, 180,575);    
						pdfDataPrinter(7, pdfStamper, notApplicable, 180,542); 

						pdfDataPrinter(7, pdfStamper, notApplicable, 180,515);   
						pdfDataPrinter(7, pdfStamper, notApplicable, 180,460);
						pdfDataPrinter(7, pdfStamper, notApplicable, 180,435);     
						pdfDataPrinter(7, pdfStamper, notApplicable, 180,381);

						pdfDataPrinter(7, pdfStamper, notApplicable, 180,342);   
						pdfDataPrinter(7, pdfStamper, notApplicable, 180,280); 
						pdfDataPrinter(7, pdfStamper, notApplicable, 180,243);        
						pdfDataPrinter(7, pdfStamper, notApplicable, 180,192);        
						pdfDataPrinter(7, pdfStamper, notApplicable, 180,165);        
						pdfDataPrinter(7, pdfStamper, notApplicable, 180,112);        

						/* page no.args 7 end*/

						/* page no.args 8 start*/
						pdfDataPrinter(8, pdfStamper, notApplicable, 180,794);        
						pdfDataPrinter(8, pdfStamper, notApplicable, 180,777);  
						pdfDataPrinter(8, pdfStamper, notApplicable, 180,740);        
						pdfDataPrinter(8, pdfStamper, notApplicable, 180,658);           
						pdfDataPrinter(8, pdfStamper, notApplicable, 180,548);               
						pdfDataPrinter(8, pdfStamper, notApplicable, 180,450);                           


						/* page no.args 8 end*/
						
						/***NTDOCUMENTUPLOAD Details End Here**/
					}
					if("CKYC".equalsIgnoreCase(custType)){
						/***NTB Details start Here**/
						pdfDataPrinter(4, pdfStamper, fullName, 163, 630);
						pdfDataPrinter(4, pdfStamper, pancard, 183, 595);
						pdfDataPrinter(4, pdfStamper, pincode, 205, 575);
						pdfDataPrinter(4, pdfStamper, ipAddress, 182, 515);

						pdfDataPrinter(4, pdfStamper, fullName, 350, 615);
						pdfDataPrinter(4, pdfStamper, mobileNumber, 350, 595);
						pdfDataPrinter(4, pdfStamper, "NA", 350, 565); //Applicant's Email:
						pdfDataPrinter(4, pdfStamper, ipAddress, 350, 535);

						pdfDataPrinter(4, pdfStamper, bflIpAddress, 355, 475);
						pdfDataPrinter(4, pdfStamper, bflPortalName, 355, 460);
						pdfDataPrinter(4, pdfStamper, ipAddress, 466, 445);
						/***NTB Details End Here**/
						pdfDataPrinter(4, pdfStamper, ckycResponseDate.substring(0,ckycResponseDate.indexOf(' ')), 350, 385);
						pdfDataPrinter(4, pdfStamper, ckycResponseDate.substring(ckycResponseDate.indexOf(' '),(ckycResponseDate.length()-1)), 350, 360);
						pdfDataPrinter(4, pdfStamper, ckycRequestDate.substring(0,ckycRequestDate.indexOf(' ')), 350, 335);
						pdfDataPrinter(4, pdfStamper, ckycRequestDate.substring(ckycRequestDate.indexOf(' '),(ckycRequestDate.length()-1)), 350, 310);
						pdfDataPrinter(4, pdfStamper, ckycdownloadDate.substring(0,ckycdownloadDate.indexOf(' ')), 350, 285);
						pdfDataPrinter(4, pdfStamper, ckycdownloadDate.substring(ckycdownloadDate.indexOf(' '),(ckycdownloadDate.length()-1)), 350, 260);

						/***OKYC Details start Here**/

						pdfDataPrinter(4, pdfStamper,notApplicable, 165, 195);
						pdfDataPrinter(4, pdfStamper,notApplicable, 350, 200);

						pdfDataPrinter(4, pdfStamper,notApplicable, 350, 165);
						pdfDataPrinter(4, pdfStamper, notApplicable, 350, 125);
						pdfDataPrinter(4, pdfStamper,notApplicable, 350, 85);


						/* page no.args 5 start*/
						pdfDataPrinter(5, pdfStamper, notApplicable, 350,785);                        	  
						pdfDataPrinter(5, pdfStamper,notApplicable, 350, 760);       
						pdfDataPrinter(5, pdfStamper, notApplicable, 350, 670);     
						pdfDataPrinter(5, pdfStamper, notApplicable, 350, 640);                             	
						pdfDataPrinter(5, pdfStamper, notApplicable, 350, 610); 
						pdfDataPrinter(5, pdfStamper, notApplicable, 190, 595); //underline   
						pdfDataPrinter(5, pdfStamper, notApplicable, 350, 580);   
						pdfDataPrinter(5, pdfStamper,notApplicable, 480, 580);      //underline               
						pdfDataPrinter(5, pdfStamper, notApplicable, 350,519);                                     	  
						pdfDataPrinter(5, pdfStamper, notApplicable, 350, 500); 
						pdfDataPrinter(5, pdfStamper, notApplicable, 350, 450);     
						pdfDataPrinter(5, pdfStamper, notApplicable, 350, 410);                             	
						pdfDataPrinter(5, pdfStamper, notApplicable, 350, 380);     
						pdfDataPrinter(5, pdfStamper, notApplicable, 350, 350);           
						pdfDataPrinter(5, pdfStamper, notApplicable, 190, 340);      //underline      
						pdfDataPrinter(5, pdfStamper, notApplicable, 356, 290);   
						pdfDataPrinter(5, pdfStamper, notApplicable, 356, 270);	           
						pdfDataPrinter(5, pdfStamper, notApplicable, 356, 240);  
						pdfDataPrinter(5, pdfStamper, notApplicable, 350, 190);         
						pdfDataPrinter(5, pdfStamper, notApplicable, 350, 160);  
						pdfDataPrinter(5, pdfStamper, notApplicable, 180, 170);    //underline        
						pdfDataPrinter(5, pdfStamper, notApplicable, 350, 130);     
						pdfDataPrinter(5, pdfStamper,notApplicable, 470,115);   //underline                             
						pdfDataPrinter(5, pdfStamper,notApplicable, 350, 90);                    

						/* page no.args 5 end*/

						/* page no.args 6 start*/
						pdfDataPrinter(6, pdfStamper, notApplicable, 347,780);                	  
						pdfDataPrinter(6, pdfStamper, notApplicable, 347, 760);   
						pdfDataPrinter(6, pdfStamper, notApplicable, 350, 680);	 
						pdfDataPrinter(6, pdfStamper, notApplicable, 190, 678);	 //underline                 
						pdfDataPrinter(6, pdfStamper, notApplicable, 349, 640);    
						pdfDataPrinter(6, pdfStamper, notApplicable, 464, 660);       //underline                                
						pdfDataPrinter(6, pdfStamper, notApplicable, 349, 610); 
						pdfDataPrinter(6, pdfStamper, notApplicable, 350, 580);	
						pdfDataPrinter(6, pdfStamper, notApplicable, 349, 550);                 	
						pdfDataPrinter(6, pdfStamper, notApplicable, 349, 520); 
						pdfDataPrinter(6, pdfStamper, notApplicable, 349, 490);          
						pdfDataPrinter(6, pdfStamper, notApplicable, 349, 420);              
						pdfDataPrinter(6, pdfStamper, notApplicable, 349, 390);       
						pdfDataPrinter(6,pdfStamper, notApplicable, 349, 360);    
						pdfDataPrinter(6, pdfStamper,notApplicable, 349, 330); 
						pdfDataPrinter(6, pdfStamper, notApplicable, 349,300);  
						pdfDataPrinter(6,pdfStamper, notApplicable, 349, 270); 
						pdfDataPrinter(6, pdfStamper,notApplicable, 349, 235); 
						pdfDataPrinter(6, pdfStamper, notApplicable, 349,200);   
						pdfDataPrinter(6, pdfStamper, notApplicable, 349,170);          
						/* page no.args 6 end*/
						/***OKYC Details End Here**/
						
						/***NTDOCUMENTUPLOAD Details START Here**/
						/* page no.args 7 start*/
						pdfDataPrinter(7, pdfStamper, notApplicable, 347,770);                         	  
						pdfDataPrinter(7, pdfStamper, notApplicable, 347, 740);   
						pdfDataPrinter(7, pdfStamper, notApplicable, 347,700);    
						pdfDataPrinter(7, pdfStamper,notApplicable, 347, 670);	      
						pdfDataPrinter(7, pdfStamper, notApplicable, 347,620);                   
						pdfDataPrinter(7, pdfStamper, notApplicable, 347, 600);
						/*
						 * underline section
						 */	  
						pdfDataPrinter(7, pdfStamper, notApplicable, 190,787);            
						pdfDataPrinter(7, pdfStamper, notApplicable, 180,705);                      
						pdfDataPrinter(7, pdfStamper, notApplicable, 180,678);             
						pdfDataPrinter(7, pdfStamper, notApplicable, 180,620);    
						pdfDataPrinter(7, pdfStamper, notApplicable, 180,575);    
						pdfDataPrinter(7, pdfStamper, notApplicable, 180,542); 

						pdfDataPrinter(7, pdfStamper, notApplicable, 180,515);   
						pdfDataPrinter(7, pdfStamper, notApplicable, 180,460);
						pdfDataPrinter(7, pdfStamper, notApplicable, 180,435);     
						pdfDataPrinter(7, pdfStamper, notApplicable, 180,381);

						pdfDataPrinter(7, pdfStamper, notApplicable, 180,342);   
						pdfDataPrinter(7, pdfStamper, notApplicable, 180,280); 
						pdfDataPrinter(7, pdfStamper, notApplicable, 180,243);        
						pdfDataPrinter(7, pdfStamper, notApplicable, 180,192);        
						pdfDataPrinter(7, pdfStamper, notApplicable, 180,165);        
						pdfDataPrinter(7, pdfStamper, notApplicable, 180,112);        

						/* page no.args 7 end*/

						/* page no.args 8 start*/
						pdfDataPrinter(8, pdfStamper, notApplicable, 180,794);        
						pdfDataPrinter(8, pdfStamper, notApplicable, 180,777);  
						pdfDataPrinter(8, pdfStamper, notApplicable, 180,740);        
						pdfDataPrinter(8, pdfStamper, notApplicable, 180,658);           
						pdfDataPrinter(8, pdfStamper, notApplicable, 180,548);               
						pdfDataPrinter(8, pdfStamper, notApplicable, 180,450);                           


						/* page no.args 8 end*/
						
						/***NTDOCUMENTUPLOAD Details End Here**/
					}
					if("OKYC".equalsIgnoreCase(custType)){
						/***NTB Details start Here**/
						pdfDataPrinter(4, pdfStamper, fullName, 163, 630);
						pdfDataPrinter(4, pdfStamper, pancard, 183, 595);
						pdfDataPrinter(4, pdfStamper, pincode, 205, 575);
						pdfDataPrinter(4, pdfStamper, ipAddress, 182, 515);



						pdfDataPrinter(4, pdfStamper, fullName, 350, 615);
						pdfDataPrinter(4, pdfStamper, mobileNumber, 350, 595);
						pdfDataPrinter(4, pdfStamper, "NA", 350, 565); //Applicant's Email:
						pdfDataPrinter(4, pdfStamper, ipAddress, 350, 535);

						pdfDataPrinter(4, pdfStamper, bflIpAddress, 355, 475);
						pdfDataPrinter(4, pdfStamper, bflPortalName, 355, 460);
						pdfDataPrinter(4, pdfStamper, ipAddress, 466, 445);
						/***NTB Details End Here**/
						
						pdfDataPrinter(4, pdfStamper, ipAddress, 165, 195);
						pdfDataPrinter(4, pdfStamper,fullName, 350, 200);

						pdfDataPrinter(4, pdfStamper,mobileNumber, 350, 165);
						pdfDataPrinter(4, pdfStamper, "NA", 350, 125);
						pdfDataPrinter(4, pdfStamper,ipAddress, 350, 85);


						/* page no.args 5 start*/
						pdfDataPrinter(5, pdfStamper, bflIpAddress, 350,785);                        	  
						pdfDataPrinter(5, pdfStamper, bflPortalName, 350, 760);       
						pdfDataPrinter(5, pdfStamper, fullName, 350, 670);     
						pdfDataPrinter(5, pdfStamper, mobileNumber, 350, 640);                             	
						pdfDataPrinter(5, pdfStamper, "NA", 350, 610); 
						pdfDataPrinter(5, pdfStamper, okycIPAddress, 190, 595); //underline   
						pdfDataPrinter(5, pdfStamper, ipAddress, 350, 580);   
						pdfDataPrinter(5, pdfStamper,okycIPAddress, 480, 580);      //underline               
						pdfDataPrinter(5, pdfStamper, bflIpAddress, 350,519);                                     	  
						pdfDataPrinter(5, pdfStamper, bflPortalName, 350, 500); 
						pdfDataPrinter(5, pdfStamper, fullName, 350, 450);     
						pdfDataPrinter(5, pdfStamper, mobileNumber, 350, 410);                             	
						pdfDataPrinter(5, pdfStamper, "NA", 350, 380);     
						pdfDataPrinter(5, pdfStamper, ipAddress, 350, 350);           
						pdfDataPrinter(5, pdfStamper, bflIpAddress, 190, 340);      //underline      
						pdfDataPrinter(5, pdfStamper, okycIPAddress, 356, 290);   
						pdfDataPrinter(5, pdfStamper, bflPortalName, 356, 270);	           
						pdfDataPrinter(5, pdfStamper, okycIPAddress, 356, 240);  
						pdfDataPrinter(5, pdfStamper, fullName, 350, 190);         
						pdfDataPrinter(5, pdfStamper, mobileNumber, 350, 160);  
						pdfDataPrinter(5, pdfStamper, ipAddress, 180, 170);    //underline        
						pdfDataPrinter(5, pdfStamper, "NA", 350, 130);     
						pdfDataPrinter(5, pdfStamper,ipAddress, 470,115);   //underline                             
						pdfDataPrinter(5, pdfStamper,bflIpAddress, 350, 90);                    

						/* page no.args 5 end*/

						/* page no.args 6 start*/
						pdfDataPrinter(6, pdfStamper, bflIpAddress, 347,780);                	  
						pdfDataPrinter(6, pdfStamper, bflPortalName, 347, 760);   
						pdfDataPrinter(6, pdfStamper, okycIPAddress, 350, 680);	 
						pdfDataPrinter(6, pdfStamper, customerId, 190, 678);	 //underline                 
						pdfDataPrinter(6, pdfStamper, bflPortalName, 349, 640);    
						pdfDataPrinter(6, pdfStamper, customerId, 460, 660);       //underline                                
						pdfDataPrinter(6, pdfStamper, bflIpAddress, 349, 610); 
						pdfDataPrinter(6, pdfStamper, fullName, 350, 580);	
						pdfDataPrinter(6, pdfStamper, mobileNumber, 349, 550);                 	
						pdfDataPrinter(6, pdfStamper, emailId, 349, 520); 
						pdfDataPrinter(6, pdfStamper, ipAddress, 349, 490);          
						pdfDataPrinter(6, pdfStamper, okycRequestId, 349, 420);              
						pdfDataPrinter(6, pdfStamper, "NA", 349, 390);       
						pdfDataPrinter(6,pdfStamper, okycAadhaarReferenceId, 349, 360);    
						pdfDataPrinter(6, pdfStamper,mobileNumber, 349, 330); 
						pdfDataPrinter(6, pdfStamper, emailId, 349,300);  
						pdfDataPrinter(6,pdfStamper, fixedDepositData.getOkycReturnValue()==null?"NA":fixedDepositData.getOkycReturnValue(), 349, 270); 
						pdfDataPrinter(6, pdfStamper,okycIPAddress, 349, 235); 
						pdfDataPrinter(6, pdfStamper, okycUpdateRecordDT, 349,200);   
						pdfDataPrinter(6, pdfStamper, okycInitiationDT, 349,170);          
						/* page no.args 6 end*/
						
						
						
						/*******CKYC Details start here***************************/

						pdfDataPrinter(4, pdfStamper, notApplicable, 350, 385);
						pdfDataPrinter(4, pdfStamper, notApplicable, 350, 360);
						pdfDataPrinter(4, pdfStamper, notApplicable, 350, 335);
						pdfDataPrinter(4, pdfStamper,notApplicable, 350, 310);
						pdfDataPrinter(4, pdfStamper, notApplicable, 350, 285);
						pdfDataPrinter(4, pdfStamper, notApplicable, 350, 260);

						/******CKYC Deatails Start Here**************************/
						
						/***NTDOCUMENTUPLOAD Details START Here**/
						/* page no.args 7 start*/
						pdfDataPrinter(7, pdfStamper, notApplicable, 347,770);                         	  
						pdfDataPrinter(7, pdfStamper, notApplicable, 347, 740);   
						pdfDataPrinter(7, pdfStamper, notApplicable, 347,700);    
						pdfDataPrinter(7, pdfStamper,notApplicable, 347, 670);	      
						pdfDataPrinter(7, pdfStamper, notApplicable, 347,620);                   
						pdfDataPrinter(7, pdfStamper, notApplicable, 347, 600);
						/*
						 * underline section
						 */	  
						pdfDataPrinter(7, pdfStamper, notApplicable, 190,787);            
						pdfDataPrinter(7, pdfStamper, notApplicable, 180,705);                      
						pdfDataPrinter(7, pdfStamper, notApplicable, 180,678);             
						pdfDataPrinter(7, pdfStamper, notApplicable, 180,620);    
						pdfDataPrinter(7, pdfStamper, notApplicable, 180,575);    
						pdfDataPrinter(7, pdfStamper, notApplicable, 180,542); 

						pdfDataPrinter(7, pdfStamper, notApplicable, 180,515);   
						pdfDataPrinter(7, pdfStamper, notApplicable, 180,460);
						pdfDataPrinter(7, pdfStamper, notApplicable, 180,435);     
						pdfDataPrinter(7, pdfStamper, notApplicable, 180,381);

						pdfDataPrinter(7, pdfStamper, notApplicable, 180,342);   
						pdfDataPrinter(7, pdfStamper, notApplicable, 180,280); 
						pdfDataPrinter(7, pdfStamper, notApplicable, 180,243);        
						pdfDataPrinter(7, pdfStamper, notApplicable, 180,192);        
						pdfDataPrinter(7, pdfStamper, notApplicable, 180,165);        
						pdfDataPrinter(7, pdfStamper, notApplicable, 180,112);        

						/* page no.args 7 end*/

						/* page no.args 8 start*/
						pdfDataPrinter(8, pdfStamper, notApplicable, 180,794);        
						pdfDataPrinter(8, pdfStamper, notApplicable, 180,777);  
						pdfDataPrinter(8, pdfStamper, notApplicable, 180,740);        
						pdfDataPrinter(8, pdfStamper, notApplicable, 180,658);           
						pdfDataPrinter(8, pdfStamper, notApplicable, 180,548);               
						pdfDataPrinter(8, pdfStamper, notApplicable, 180,450);                           


						/* page no.args 8 end*/
						
						/***NTDOCUMENTUPLOAD Details End Here**/

					}

					if("NTBDocumentUpload".equalsIgnoreCase(custType)){
						/***NTB Details start Here**/
						pdfDataPrinter(4, pdfStamper, fullName, 163, 630);
						pdfDataPrinter(4, pdfStamper, pancard, 183, 595);
						pdfDataPrinter(4, pdfStamper, pincode, 205, 575);
						pdfDataPrinter(4, pdfStamper, ipAddress, 182, 515);



						pdfDataPrinter(4, pdfStamper, fullName, 350, 615);
						pdfDataPrinter(4, pdfStamper, mobileNumber, 350, 595);
						pdfDataPrinter(4, pdfStamper, "NA", 350, 565); //Applicant's Email:
						pdfDataPrinter(4, pdfStamper, ipAddress, 350, 535);

						pdfDataPrinter(4, pdfStamper, bflIpAddress, 355, 475);
						pdfDataPrinter(4, pdfStamper, bflPortalName, 355, 460);
						pdfDataPrinter(4, pdfStamper, ipAddress, 466, 445);
						/***NTB Details End Here**/
						/* page no.args 7 start*/
						pdfDataPrinter(7, pdfStamper, customerId, 347,770);                         	  
						pdfDataPrinter(7, pdfStamper, mobileNumber, 347, 740);   
						pdfDataPrinter(7, pdfStamper, "NA", 347,700);    
						pdfDataPrinter(7, pdfStamper,ipAddress, 347, 670);	      
						pdfDataPrinter(7, pdfStamper, bflIpAddress, 347,620);                   
						pdfDataPrinter(7, pdfStamper, bflPortalName, 347, 600);
						/*
						 * underline section
						 */	  
						pdfDataPrinter(7, pdfStamper, customerId, 190,787);            
						pdfDataPrinter(7, pdfStamper, ipAddress, 180,705);                      
						pdfDataPrinter(7, pdfStamper, customerId, 180,678);             
						pdfDataPrinter(7, pdfStamper, identityDoc, 180,620);    
						pdfDataPrinter(7, pdfStamper, customerId, 180,575);    
						pdfDataPrinter(7, pdfStamper, identityDoc, 180,542); 

						pdfDataPrinter(7, pdfStamper, customerId, 180,515);   
						pdfDataPrinter(7, pdfStamper, addressProodDoc, 180,460);
						pdfDataPrinter(7, pdfStamper, customerId, 180,435);     
						pdfDataPrinter(7, pdfStamper, signProofDoc, 180,381);

						pdfDataPrinter(7, pdfStamper, customerId, 180,342);   
						pdfDataPrinter(7, pdfStamper, addressProodDoc, 180,280); 
						pdfDataPrinter(7, pdfStamper, customerId, 180,243);        
						pdfDataPrinter(7, pdfStamper, addressProodDoc, 180,192);        
						pdfDataPrinter(7, pdfStamper, customerId, 180,165);        
						pdfDataPrinter(7, pdfStamper, signProofDoc, 180,112);        

						/* page no.args 7 end*/

						/* page no.args 8 start*/
						pdfDataPrinter(8, pdfStamper, customerId, 180,794);        
						pdfDataPrinter(8, pdfStamper, signProofDoc, 180,777);  
						pdfDataPrinter(8, pdfStamper, customerId, 180,740);        
						pdfDataPrinter(8, pdfStamper, customerId, 180,658);           
						pdfDataPrinter(8, pdfStamper, customerId, 180,548);               
						pdfDataPrinter(8, pdfStamper, customerId, 180,450);                           


						/* page no.args 8 end*/
						
						
						/**********OKYC Details start Here*******/

						pdfDataPrinter(4, pdfStamper, notApplicable, 165, 195);
						pdfDataPrinter(4, pdfStamper,notApplicable, 350, 200);

						pdfDataPrinter(4, pdfStamper,notApplicable, 350, 165);
						pdfDataPrinter(4, pdfStamper, notApplicable, 350, 125);
						pdfDataPrinter(4, pdfStamper,notApplicable, 350, 85);


						/* page no.args 5 start*/
						pdfDataPrinter(5, pdfStamper, notApplicable, 350,785);                        	  
						pdfDataPrinter(5, pdfStamper, notApplicable, 350, 760);       
						pdfDataPrinter(5, pdfStamper, notApplicable, 350, 670);     
						pdfDataPrinter(5, pdfStamper, notApplicable, 350, 640);                             	
						pdfDataPrinter(5, pdfStamper, notApplicable, 350, 610); 
						pdfDataPrinter(5, pdfStamper, notApplicable, 190, 595); //underline   
						pdfDataPrinter(5, pdfStamper, notApplicable, 350, 580);   
						pdfDataPrinter(5, pdfStamper,notApplicable, 480, 580);      //underline               
						pdfDataPrinter(5, pdfStamper, notApplicable, 350,519);                                     	  
						pdfDataPrinter(5, pdfStamper, notApplicable, 350, 500); 
						pdfDataPrinter(5, pdfStamper, notApplicable, 350, 450);     
						pdfDataPrinter(5, pdfStamper, notApplicable, 350, 410);                             	
						pdfDataPrinter(5, pdfStamper,notApplicable, 350, 380);     
						pdfDataPrinter(5, pdfStamper, notApplicable, 350, 350);           
						pdfDataPrinter(5, pdfStamper, notApplicable, 190, 340);      //underline      
						pdfDataPrinter(5, pdfStamper, notApplicable, 356, 290);   
						pdfDataPrinter(5, pdfStamper, notApplicable, 356, 270);	           
						pdfDataPrinter(5, pdfStamper, notApplicable, 356, 240);  
						pdfDataPrinter(5, pdfStamper, notApplicable, 350, 190);         
						pdfDataPrinter(5, pdfStamper, notApplicable, 350, 160);  
						pdfDataPrinter(5, pdfStamper, notApplicable, 180, 170);    //underline        
						pdfDataPrinter(5, pdfStamper, notApplicable, 350, 130);     
						pdfDataPrinter(5, pdfStamper,notApplicable, 470,115);   //underline                             
						pdfDataPrinter(5, pdfStamper,notApplicable, 350, 90);                    

						/* page no.args 5 end*/

						/* page no.args 6 start*/
						pdfDataPrinter(6, pdfStamper, notApplicable, 347,780);                	  
						pdfDataPrinter(6, pdfStamper, notApplicable, 347, 760);   
						pdfDataPrinter(6, pdfStamper, notApplicable, 350, 680);	 
						pdfDataPrinter(6, pdfStamper, notApplicable, 190, 678);	 //underline                 
						pdfDataPrinter(6, pdfStamper, notApplicable, 349, 640);    
						pdfDataPrinter(6, pdfStamper, notApplicable, 464, 660);       //underline                                
						pdfDataPrinter(6, pdfStamper, notApplicable, 349, 610); 
						pdfDataPrinter(6, pdfStamper, notApplicable, 350, 580);	
						pdfDataPrinter(6, pdfStamper, notApplicable, 349, 550);                 	
						pdfDataPrinter(6, pdfStamper, notApplicable, 349, 520); 
						pdfDataPrinter(6, pdfStamper, notApplicable, 349, 490);          
						pdfDataPrinter(6, pdfStamper, notApplicable, 349, 420);              
						pdfDataPrinter(6, pdfStamper, notApplicable, 349, 390);       
						pdfDataPrinter(6,pdfStamper, notApplicable, 349, 360);    
						pdfDataPrinter(6, pdfStamper,notApplicable, 349, 330); 
						pdfDataPrinter(6, pdfStamper, notApplicable, 349,300);  
						pdfDataPrinter(6,pdfStamper, notApplicable, 349, 270); 
						pdfDataPrinter(6, pdfStamper,notApplicable, 349, 235); 
						pdfDataPrinter(6, pdfStamper, notApplicable, 349,200);   
						pdfDataPrinter(6, pdfStamper, notApplicable, 349,170);          
						/* page no.args 6 end*/
						
						/**********OKYC Details End Here*********/
						
						/*******CKYC Details start here***************************/

						pdfDataPrinter(4, pdfStamper, notApplicable, 350, 385);
						pdfDataPrinter(4, pdfStamper, notApplicable, 350, 360);
						pdfDataPrinter(4, pdfStamper, notApplicable, 350, 335);
						pdfDataPrinter(4, pdfStamper,notApplicable, 350, 310);
						pdfDataPrinter(4, pdfStamper, notApplicable, 350, 285);
						pdfDataPrinter(4, pdfStamper, notApplicable, 350, 260);

						/******CKYC Deatails Start Here**************************/
						
					}

					/* page no.args 9 start*/
					pdfDataPrinter(9, pdfStamper, custType, 180,788);                                  
					pdfDataPrinter(9, pdfStamper, customerId, 180,740);                                        
					pdfDataPrinter(9, pdfStamper, fullName, 168,640);                                           
					pdfDataPrinter(9, pdfStamper, emailId, 168,610);                                           
					pdfDataPrinter(9, pdfStamper, pancard, 168,580);                                             
					pdfDataPrinter(9, pdfStamper, gener, 168,550);                                             
					pdfDataPrinter(9, pdfStamper, "NA", 168,510);
					pdfDataPrinter(9, pdfStamper, fullName, 350,515);     //applicant      
					pdfDataPrinter(9, pdfStamper, mobileNumber, 350,485);     //applicant      
					pdfDataPrinter(9, pdfStamper, emailId, 350,460);     //applicant  
					pdfDataPrinter(9, pdfStamper, custType, 470,460);     //applicant  fetch from              
					pdfDataPrinter(9, pdfStamper, ipAddress, 470,440);     //applicant  records from ip                 
					pdfDataPrinter(9, pdfStamper, ipAddress, 350,435);     //applicant               
					pdfDataPrinter(9, pdfStamper, bflIpAddress, 350,370);     //BFL               
					pdfDataPrinter(9, pdfStamper, bflPortalName, 350,340);     //BFL           
					pdfDataPrinter(9, pdfStamper, mobileNumber, 168,480);                                                  
					pdfDataPrinter(9, pdfStamper, dateOfBorth, 168,450);    


					JSONObject addrManipulat=Utility.addresManipulationForAuditTrail(address);


					String addLine1=addrManipulat.has("add1") ? addrManipulat.get("add1").toString() : ".";
					String addLine2=addrManipulat.has("add2") ? addrManipulat.get("add2").toString() : ".";
					String addLine3 =addrManipulat.has("add3") ? addrManipulat.get("add3").toString() : ".";
					String addLine4 =addrManipulat.has("add4") ? addrManipulat.get("add4").toString() : ".";

					pdfDataPrinter(9, pdfStamper, addLine1, 168,420);                                               
					pdfDataPrinter(9, pdfStamper, addLine2, 168,390);                                                   
					pdfDataPrinter(9, pdfStamper, addLine3, 168,358);                                               
					pdfDataPrinter(9, pdfStamper, addLine4, 168,328);                                                      
					pdfDataPrinter(9, pdfStamper,city, 168,292);                                                        
					pdfDataPrinter(9, pdfStamper,state, 168,262);                                                      
					pdfDataPrinter(9, pdfStamper,pincode, 168,232);                                                         
					pdfDataPrinter(9, pdfStamper, emailId, 168,160);                                                              
					pdfDataPrinter(9, pdfStamper, pancard, 168,115);                                                                 



					/* page no.args 9 end*/



					/* page no.args 10 start*/
					pdfDataPrinter(10, pdfStamper, ipAddress, 168,755);                                                                 
					pdfDataPrinter(10, pdfStamper,emailId, 217,617);                                                                      
					pdfDataPrinter(10, pdfStamper, ipAddress, 168,575); 
					pdfDataPrinter(10, pdfStamper, emailId, 217,467);                                                                            
					pdfDataPrinter(10, pdfStamper, ipAddress, 168,447);                                                                   
					pdfDataPrinter(10, pdfStamper, emailId, 168,365);                                                                      
					pdfDataPrinter(10, pdfStamper, address, 168,325);
					//pdfDataPrinter(10, pdfStamper, fName, 168,255);    
					//pdfDataPrinter(10, pdfStamper, commCheckbox, 195,210);  
					pdfDataPrinter(10, pdfStamper, customerId, 168,255);
					//pdfDataPrinter(10, pdfStamper, "Y", 195,210);
					pdfDataPrinter(10, pdfStamper, commCheckbox, 168,190);    
					pdfDataPrinter(10, pdfStamper,commAddress, 168,128);          
					pdfDataPrinter(10, pdfStamper, commPincode, 168,53);  
					pdfDataPrinter(10, pdfStamper, fullName, 350,270); //applicant section       
					pdfDataPrinter(10, pdfStamper, mobileNumber, 350,245); //applicant section  
					pdfDataPrinter(10, pdfStamper, emailId, 350,220); //applicant section    
					pdfDataPrinter(10, pdfStamper, ipAddress, 350,185); //applicant section  
					pdfDataPrinter(10, pdfStamper, ipAddress, 475,200);    
					pdfDataPrinter(10, pdfStamper, commAddress, 480,190);              
					pdfDataPrinter(10, pdfStamper,bflIpAddress, 340,120); //BFL section    
					pdfDataPrinter(10, pdfStamper, bflPortalName, 340,90); //BFL section           


					/* page no.args 10 end*/

					//pdfDataPrinter(11, pdfStamper, "Digital/Physical copy of FDR", 170,595);
					
					pdfDataPrinter(11, pdfStamper, customerId, 170,430);
					pdfDataPrinter(11, pdfStamper, ipAddress, 170,388);
					pdfDataPrinter(11, pdfStamper, "YES".equalsIgnoreCase(nominePresent)?nomineeName:"NA", 170,312);
					pdfDataPrinter(11, pdfStamper, "YES".equalsIgnoreCase(nominePresent)?nomineeDateOfBirth:"NA", 185,275);
					pdfDataPrinter(11, pdfStamper,"YES".equalsIgnoreCase(nominePresent)? nomineeRelation:"NA", 180,240);
					pdfDataPrinter(11, pdfStamper,nominePresent, 223,221);
					
					
					//pdfDataPrinter(11, pdfStamper, guardianName, 170,182);
					pdfDataPrinter(11, pdfStamper, customerId, 170,182);
					pdfDataPrinter(11, pdfStamper, guardianName, 170,122);
					pdfDataPrinter(11, pdfStamper, nomineeAddressChek, 185,78);

					pdfDataPrinter(11, pdfStamper, emailId, 170,618);
					pdfDataPrinter(11, pdfStamper, mobileNumber, 200,595);
					pdfDataPrinter(11, pdfStamper, fdrPhysicalyRequired, 200,515);

					pdfDataPrinter(11, pdfStamper, customerId, 480,648);
					pdfDataPrinter(11, pdfStamper, customerId, 500,270);
					pdfDataPrinter(11, pdfStamper, ipAddress, 480,230);

					pdfDataPrinter(11, pdfStamper, ipAddress, 170,758);
					pdfDataPrinter(11, pdfStamper, fullName, 350,680);
					pdfDataPrinter(11, pdfStamper, mobileNumber, 350,645);
					pdfDataPrinter(11, pdfStamper, emailId, 350,615);
					pdfDataPrinter(11, pdfStamper, ipAddress, 350,580);
					pdfDataPrinter(11, pdfStamper, bflIpAddress, 345,520);
					pdfDataPrinter(11, pdfStamper, bflPortalName, 350,490);


					pdfDataPrinter(11, pdfStamper, fullName, 350,350);
					pdfDataPrinter(11, pdfStamper, mobileNumber, 350,320);
					pdfDataPrinter(11, pdfStamper, emailId, 350,290);
					pdfDataPrinter(11, pdfStamper, ipAddress, 350,260);
					pdfDataPrinter(11, pdfStamper, bflIpAddress, 345,205);
					pdfDataPrinter(11, pdfStamper, bflPortalName, 350,175);
					pdfDataPrinter(11, pdfStamper, fdrPhysicalyRequired, 480,600);
					pdfDataPrinter(11, pdfStamper, ipAddress, 480,573);
					
					
					/* page 12 starts here */
					pdfDataPrinter(12, pdfStamper, customerId, 162,765);

					pdfDataPrinter(12, pdfStamper, nomineeAddress, 161,731);
					pdfDataPrinter(12, pdfStamper,nomineePincode, 161,671);

					if("FD".equalsIgnoreCase(investType)){
						pdfDataPrinter(12, pdfStamper, customerId, 162,600);  //FD
						pdfDataPrinter(12, pdfStamper, ipAddress, 161,545);
						pdfDataPrinter(12, pdfStamper, depositAmount, 215,525);
						pdfDataPrinter(12, pdfStamper, tenor, 204,515);
						pdfDataPrinter(12, pdfStamper, depositOption, 209,505);
						pdfDataPrinter(12, pdfStamper, fName, 200,475);
					}
					if("SDP".equalsIgnoreCase(investType)){
						pdfDataPrinter(12, pdfStamper, customerId, 195,373);   //sdp
						pdfDataPrinter(12, pdfStamper, ipAddress, 236,362);
						pdfDataPrinter(12, pdfStamper, depositAmount, 170,343);

						pdfDataPrinter(12, pdfStamper, tenor, 170,305);
						pdfDataPrinter(12, pdfStamper, numberOfDeposit, 170,290);
						pdfDataPrinter(12, pdfStamper, sdpDepositDate, 186,270);
					}

					pdfDataPrinter(12, pdfStamper, fullName, 344,520);
					pdfDataPrinter(12, pdfStamper, mobileNumber, 344,495);
					pdfDataPrinter(12, pdfStamper, emailId, 344,465);
					pdfDataPrinter(12, pdfStamper, ipAddress, 344,435);
					pdfDataPrinter(12, pdfStamper, customerId, 465,438);
					pdfDataPrinter(12, pdfStamper,ipAddress, 465, 400);
					pdfDataPrinter(12, pdfStamper,bflIpAddress, 344,370);
					pdfDataPrinter(12, pdfStamper, bflPortalName, 344,345);

					//pdfDataPrinter(12, pdfStamper, "scheme", 344,520);



					/* page no.args 13 start*/
					pdfDataPrinter(13, pdfStamper, fName, 175,797);
					pdfDataPrinter(13, pdfStamper, ipAddress, 190,742);
					pdfDataPrinter(13, pdfStamper, fullBankName, 190,705);
					pdfDataPrinter(13, pdfStamper,paymentMode, 190,678);
					pdfDataPrinter(13, pdfStamper, accountNumber, 160,658);
					pdfDataPrinter(13, pdfStamper,ifscCode, 220,640);
					//pdfDataPrinter(13, pdfStamper, fName, 170,612);
					pdfDataPrinter(13, pdfStamper, fullBankName, 190,538);
					pdfDataPrinter(13, pdfStamper, paymentMode, 190,511);
					pdfDataPrinter(13, pdfStamper, accountNumber, 160,490);
					pdfDataPrinter(13, pdfStamper, ifscCode, 220,473);                                                                              		 
					pdfDataPrinter(13, pdfStamper, customerId, 170,443);
					pdfDataPrinter(13, pdfStamper, fullBankName, 170,380);
					pdfDataPrinter(13, pdfStamper, accountNumber, 230,368);
					pdfDataPrinter(13, pdfStamper, ifscCode, 200,355);

					pdfDataPrinter(13, pdfStamper, fullName, 350,658);   //applicant
					pdfDataPrinter(13, pdfStamper, mobileNumber, 350,635);   //applicant
					pdfDataPrinter(13, pdfStamper, emailId, 350,592);   //applicant
					pdfDataPrinter(13, pdfStamper, ipAddress,350,575);   //applicant
					pdfDataPrinter(13, pdfStamper, bflIpAddress, 340,511);   //BFL
					pdfDataPrinter(13, pdfStamper, bflPortalName, 340,473);   //BFL
					pdfDataPrinter(13, pdfStamper, fullName, 350,280);   //applicant
					pdfDataPrinter(13, pdfStamper,mobileNumber, 350,250);   //applicant
					pdfDataPrinter(13, pdfStamper, emailId, 350,220);   //applicant
					pdfDataPrinter(13, pdfStamper, ipAddress,350,190);   //applicant

					pdfDataPrinter(13, pdfStamper, customerId, 480,648);
					pdfDataPrinter(13, pdfStamper, ipAddress, 470,585);

					pdfDataPrinter(13, pdfStamper, customerId, 480,238);
					pdfDataPrinter(13, pdfStamper,customerId, 175,260);
					pdfDataPrinter(13, pdfStamper, "", 175,208);
					pdfDataPrinter(13, pdfStamper,transationStatus, 175,155);
					pdfDataPrinter(13, pdfStamper, paymentMode, 495,185);
					/* page no.args 13 end*/

					/* page no.args 14 start*/
					pdfDataPrinter(14, pdfStamper, utr, 380,782);
					pdfDataPrinter(14, pdfStamper, transationStatus, 380,770);
					pdfDataPrinter(14, pdfStamper, "success", 380,752);
					pdfDataPrinter(14, pdfStamper, createdOnDate, 380,738);
					pdfDataPrinter(14, pdfStamper, applicationFormNo, 380,720);
					pdfDataPrinter(14, pdfStamper, fullName, 345,650);
					pdfDataPrinter(14, pdfStamper, mobileNumber, 345,630);
					pdfDataPrinter(14, pdfStamper, emailId, 345,600);
					pdfDataPrinter(14, pdfStamper,customerId, 180,595);
					pdfDataPrinter(14, pdfStamper,customerId, 480,567);
					pdfDataPrinter(14, pdfStamper, ipAddress, 345,570);
					pdfDataPrinter(14, pdfStamper,bflIpAddress, 345,510);
					pdfDataPrinter(14, pdfStamper, bflPortalName, 345,480);
					pdfDataPrinter(14, pdfStamper, fName, 180,370);
					pdfDataPrinter(14, pdfStamper, applicationFormNo, 180,315);
					pdfDataPrinter(14, pdfStamper, customerId, 180,285);
					pdfDataPrinter(14, pdfStamper, mobileNumber, 180,150);
					pdfDataPrinter(14, pdfStamper, emailId, 180,120);
					//pdfDataPrinter(14, pdfStamper, customerId, 480,145);
					pdfDataPrinter(14, pdfStamper, mobileNumber, 480,95);
					pdfDataPrinter(14, pdfStamper, customerId, 345,445);
					pdfDataPrinter(14, pdfStamper, bflIpAddress, 340,380);
					pdfDataPrinter(14, pdfStamper, bflPortalName, 340,350);
					pdfDataPrinter(14, pdfStamper, customerId, 345,305);
					pdfDataPrinter(14, pdfStamper,createdOnDate, 340,250);
					pdfDataPrinter(14, pdfStamper, createdOnDate, 340,230);
					pdfDataPrinter(14, pdfStamper, bflIpAddress, 340,170);
					pdfDataPrinter(14, pdfStamper, bflPortalName, 340,140);
					pdfDataPrinter(14, pdfStamper,bflPortalName, 340,105);
					
					/* page no.args 14 end*/

					/* page no.args 15 start*/
					pdfDataPrinter(15, pdfStamper, fullName, 345,768);
					pdfDataPrinter(15, pdfStamper, mobileNumber, 345,752);
					pdfDataPrinter(15, pdfStamper, emailId, 345,730);
					/* page no.args 15 end*/
					
					
					
					/*Dates Stamping */
					
					pdfDataPrinter(4, pdfStamper,otpSubmittedTime, 30, 735);
					pdfDataPrinter(4, pdfStamper,ckycTime, 30, 595);
					pdfDataPrinter(4, pdfStamper,okycTime, 30, 195);
					pdfDataPrinter(5, pdfStamper,okycTime, 30, 760);
					pdfDataPrinter(5, pdfStamper,okycTime, 30, 670);
					
					pdfDataPrinter(5, pdfStamper,okycTime, 30, 450);
					pdfDataPrinter(5, pdfStamper,okycTime, 30, 190);
					
					pdfDataPrinter(6, pdfStamper,okycTime, 30, 760);
					pdfDataPrinter(6, pdfStamper,okycTime, 30, 678);
					pdfDataPrinter(7, pdfStamper,documentUploadTime, 30, 787);
					
					pdfDataPrinter(8, pdfStamper,documentUploadTime, 30, 777);
					pdfDataPrinter(9, pdfStamper,personalDetailsTime, 30, 788);
					pdfDataPrinter(10, pdfStamper,personalDetailsTime, 30, 755);
					pdfDataPrinter(10, pdfStamper,personalDetailsTime, 30, 255);
					pdfDataPrinter(11, pdfStamper,personalDetailsTime, 30, 758);
					
					
					pdfDataPrinter(11, pdfStamper,personalDetailsTime, 30, 615);
					pdfDataPrinter(11, pdfStamper,personalDetailsTime, 30, 388);
					pdfDataPrinter(12, pdfStamper,personalDetailsTime, 30, 731);
					pdfDataPrinter(12, pdfStamper,schemeTime, 30, 600);
					pdfDataPrinter(13, pdfStamper,schemeTime, 30, 797);
					
					
					
					
					pdfDataPrinter(13, pdfStamper,paymentRequestTime, 30, 260);
					pdfDataPrinter(14, pdfStamper,paymentRequestTime, 30, 782);
					pdfDataPrinter(14, pdfStamper,paymentResponseTime, 30, 595);
					pdfDataPrinter(14, pdfStamper,paymentResponseTime, 30, 370);
					pdfDataPrinter(14, pdfStamper,paymentResponseTime, 30, 145);
					pdfDataPrinter(15, pdfStamper,paymentResponseTime, 30, 768);
					
					String line1="Corporate Office:";
					String line2="6th Floor, Bajaj Finserv Corporate Office, Off Pune-Ahmednagar Road, Viman Nagar, Pune – 411014";
					String line3="Customer Care Phone Number: +91 869801001 | Email – wecare@bajajfinserv.in";
					pdfDataPrinter(15, pdfStamper,line1, 250, 65,6);
					pdfDataPrinter(15, pdfStamper,line2, 250, 55,6);
					pdfDataPrinter(15, pdfStamper,line3, 250, 45,6);
					
					pdfStamper.close();

					pdfResponseJson.put("pdfPath", filledPdfPath);
					pdfResponseJson.put(Constants.PDF_STATUS, Constants.STATUS_SUCCESS);


					FileInputStream fin = null;
					String fileEncode= "";
					String fileName = "";

					try{
						if(file.exists()){
							if(file != null){
								fin = new FileInputStream(file);
								byte fileContent[] = new byte[(int)file.length()];
								//create string from byte array
								logger.info("=====fileContent []====="+fileContent.length);
								fin.read(fileContent);
								String encoded = Base64.getEncoder().encodeToString(fileContent);
								logger.info("filename:= "+file.getName());
								fileEncode =encoded;
								logger.info("===fileEncode==="+fileEncode.length());
								fileName =file.getName();
							}
						}
						else{   
							fileEncode ="";
							fileName ="";
						}

					}
					catch (FileNotFoundException e) {
						logger.error("File not found" , e);
					}
					catch (IOException ioe) {
						logger.error("Exception while reading file " , ioe);
					}
					finally {

						if(reader != null){
							reader.close();
							logger.info("======fd PDF Stamping reader close====");
						}

						try {
							if (fin != null) {
								fin.close();
							}
							if(pdfStamper != null){
								pdfStamper.close();
							}
						}
						catch (DocumentException | IOException ioe) {
							logger.info("Error while closing stream: " + ioe);
						}
					}
					pdfResponseJson.put("fileEncode", fileEncode);
					pdfResponseJson.put("fileName", fileName);


				}else{
					logger.info(" === pdf not found in FDAuditTrailpdf ==== ");
					pdfResponseJson.put(Constants.PDF_STATUS, Constants.STATUS_FAIL);
				}

			

		} catch (Exception e) {
			if(!( customerId.isEmpty())){
				dbManipulation.recordExeption("AUDIT_TRAIL_PDF_SERVICE","Exception due to internal call", customerId);
			}
			pdfResponseJson.put(Constants.PDF_STATUS, Constants.STATUS_FAIL);
			logger.info(" == Exception in FDAuditTrailpdf === ", e);
		}

		return pdfResponseJson;
	}
	
	

	@Override
	public JSONObject okycPdfManipulation(String customerId)
	{

		JSONObject pdfResponseJson = new JSONObject();

		try {
			

			FixedDepositData fixedDepositData = fixedDepositDao.getFixedDepositData(customerId);

			String fullName = fixedDepositData.getFullName() == null ? "NA" : fixedDepositData.getFullName();
			String gender=fixedDepositData.getGender()==null?"NA":fixedDepositData.getGender();
			String dateOfBirth = fixedDepositData.getDateOfBirth() == null ? "NA" : fixedDepositData.getDateOfBirth();
			String address = fixedDepositData.getAddress() == null ? "NA" : fixedDepositData.getAddress();
			String city = fixedDepositData.getCity() == null ? "NA" : fixedDepositData.getCity();
			
			String addharPincode = fixedDepositData.getAadharPincode() == null ? "NA" : fixedDepositData.getAadharPincode();
			
			String state =fixedDepositData.getState()==null?"NA":fixedDepositData.getState();
			String okycRequestId=fixedDepositData.getOkycAPIRequestID()==null?"NA":fixedDepositData.getOkycAPIRequestID();		
			String aadhaarReferenceId = fixedDepositData.getOkycAadhaarreferenceId() == null ? "NA" : fixedDepositData.getOkycAadhaarreferenceId();
			String initiationDate = fixedDepositData.getOkycInitiationDT() == null ? "NA" : fixedDepositData.getOkycInitiationDT();
			String ipAddress=fixedDepositData.getOkycIPAddress()==null?"NA":fixedDepositData.getOkycIPAddress();
			String aadharNumber=fixedDepositData.getAadharNumber()==null?"NA" : fixedDepositData.getAadharNumber();
			String aadharHouse=fixedDepositData.getAadharHouse()==null?"NA":fixedDepositData.getAadharHouse();
			String aadharLocation=fixedDepositData.getAadharLocation()==null?"NA":fixedDepositData.getAadharLocation();
			String aadharStret=fixedDepositData.getAadharStreet()==null?"NA":fixedDepositData.getAadharStreet();
			String aadharLandmark=fixedDepositData.getAadharLandmark()==null?"NA":fixedDepositData.getAadharLandmark();
			String aadharVillage=fixedDepositData.getAadharVillage()==null?"NA":fixedDepositData.getAadharVillage();
			String aadharSubdistrict=fixedDepositData.getAadharSubdistrict()==null?"NA":fixedDepositData.getAadharSubdistrict();
			String disctrict=fixedDepositData.getAadharDistrict()==null?"NA":fixedDepositData.getAadharDistrict();
			String postOffice=fixedDepositData.getAadharPostOffice()==null?"NA":fixedDepositData.getAadharPostOffice();
			String guId=fixedDepositData.getAadharGuiId()==null?"NA":fixedDepositData.getAadharGuiId();
			
			
			
			String originalFilePath = "///softs/clover/DigitalFixedDepositServices/OriginalPdf/adhar.pdf";
			String filledPdfPath = "///softs/clover/DigitalFixedDepositServices/DownloadedPdf/AadharPdf/"+new SimpleDateFormat("dd-MM-yyyy").format(new Date())+"/adhar_Document.pdf";

			PdfReader reader=new PdfReader(originalFilePath);
			File file = new File(filledPdfPath);


			if (!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			logger.info(" ==== check file exists in okycPdfManipulation  ==== " + file.exists());
			if (file.exists())
			{
				file.delete();
			}
				boolean fileCreateStatus = file.createNewFile();
				logger.info(" ==== fileCreateStatus in okycPdfManipulation  ==== " + fileCreateStatus);

				if(fileCreateStatus){
					
					PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(filledPdfPath));
					dateOfBirth= dateOfBirth.replace("/", "-");
					
					
					
					pdfDataPrinter(1, stamper,customerId,220,790);
					pdfDataPrinter(1, stamper,fullName,220,760); 
					pdfDataPrinter(1, stamper,gender,220,730);
					pdfDataPrinter(1, stamper,dateOfBirth,220,700);
					pdfDataPrinter(1, stamper,address,220,670);
					pdfDataPrinter(1, stamper, aadharHouse, 220, 640);
					pdfDataPrinter(1, stamper, aadharLocation, 220, 610);
					pdfDataPrinter(1, stamper, aadharStret, 220, 590);
					pdfDataPrinter(1, stamper, aadharLandmark, 220, 560);
					pdfDataPrinter(1, stamper, aadharVillage, 220, 530);
					pdfDataPrinter(1, stamper, aadharSubdistrict, 220, 500);
					pdfDataPrinter(1, stamper, disctrict, 220, 481);
					pdfDataPrinter(1, stamper, postOffice, 220, 456);
					pdfDataPrinter(1, stamper, addharPincode, 220, 426);
					pdfDataPrinter(1, stamper,state,220,390);
					pdfDataPrinter(1, stamper,guId,220,365);
					pdfDataPrinter(1, stamper,aadhaarReferenceId,220,335);
					pdfDataPrinter(1, stamper,initiationDate,220,305); 
					pdfDataPrinter(1, stamper,ipAddress,220,280);
					pdfDataPrinter(1, stamper,aadharNumber,220,250);
					
					
					stamper.close();

					pdfResponseJson.put("pdfPath", filledPdfPath);
					pdfResponseJson.put(Constants.PDF_STATUS, Constants.STATUS_SUCCESS);


					FileInputStream fin = null;
					String fileEncode= "";
					String fileName = "";

					try{
						if(file.exists()){
							if(file != null){
								fin = new FileInputStream(file);
								byte fileContent[] = new byte[(int)file.length()];
								logger.info("=====fileContent []====="+fileContent.length);
								fin.read(fileContent);
								String encoded = Base64.getEncoder().encodeToString(fileContent);
								logger.info("filename:= "+file.getName());
								fileEncode =encoded;
								logger.info("===fileEncode==="+fileEncode.length());
								fileName =file.getName();
							}
						}
						else{   
							fileEncode ="";
							fileName ="";
						}

					}
					catch (FileNotFoundException e) {
						logger.error("File not found" , e);
					}
					catch (IOException ioe) {
						logger.error("Exception while reading file " , ioe);
					}
					finally {

						if(reader != null){
							reader.close();
							logger.info("======fd PDF Stamping reader close====");
						}

						try {
							if (fin != null) {
								fin.close();
							}
							if(stamper != null){
								stamper.close();
							}
						}
						catch (DocumentException | IOException ioe) {
							logger.info("Error while closing stream: " + ioe);
						}
					}
					pdfResponseJson.put("fileEncode", fileEncode);
					pdfResponseJson.put("fileName", fileName);


				}else{
					logger.info(" === pdf not found in okycPdfManipulation ==== ");
					pdfResponseJson.put(Constants.PDF_STATUS, Constants.STATUS_FAIL);
				}

			

		} catch (Exception e) {
			
			pdfResponseJson.put(Constants.PDF_STATUS, Constants.STATUS_FAIL);
			logger.info(" == Exception in okycPdfManipulation === ", e);
		}

		return pdfResponseJson;
	}
	
	
	
}
