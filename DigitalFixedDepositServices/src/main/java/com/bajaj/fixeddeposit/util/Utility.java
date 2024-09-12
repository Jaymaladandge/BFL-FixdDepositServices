package com.bajaj.fixeddeposit.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.newrelic.api.agent.NewRelic;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class Utility {

	private Utility() {
	}

	private static final Logger logger = Logger.getLogger(Utility.class);
	private static final long EXPIRATION_TIME = 3600000;
	public static final String TOKEN_SECRET = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI5MzA3NzM5NzU4IiwiaWF0IjoxNjE3NjAyNjkzLCJleHAiOjE2MTc2MDQ0OTN9.eomFfMr3eVNZgVFrbSRwI2t4Bv4peKHR3mv0JZGs-E8";

	public static String getPropertyFileValue(String key){
		String value = "";
		File propFile=new File("/softs/clover/DigitalFixedDepositServices/fixedDeposit.properties");
		logger.info(" === Property file Key ==== " + key);
		try (FileReader fileReader =  new FileReader(propFile);){

			Properties prop	= new Properties();
			prop.load(fileReader);
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			long propModified = propFile.lastModified();
			logger.info(" === Property file updated time ==== " + dateFormat.format(propModified));
			value = prop.getProperty(key);
			logger.info(" === Property file value ==== " + value);
		} catch (Exception e) {
			logger.error(" === Exception in getPropertyFileValue ==== ", e);
			value="NA";
		}

		return value;

	}

	public static String dateFormerter(String Date){

		try {
			Date formatterDob = new SimpleDateFormat("dd/MM/yyyy").parse(Date);
			SimpleDateFormat formatter1DDob = new SimpleDateFormat("MM/dd/yyyy");
			Date = formatter1DDob.format(formatterDob);
		} catch (Exception e) {
			logger.error("==========exception in dateFormerter===========",e);
		}
		return Date;
	}

	public static JSONObject addresManipulation(String address)
	{
		String addLine1=".";
		String addLine2=".";
		String addLine3 =".";
		String addLine4 =".";
		JSONObject addressJson = new JSONObject();
		
		int c= 0;
		try
		{
			int len = address.length();
		      for (int i = 0; i < len; i++) {
		    	  char ch=address.charAt(i);
		            if(ch==' ')
		            c++;
		         }
		      
		      System.out.println("space "+c);
			if (c != 0){

			if(address.length()>45 && address.length()<90 ){

				String addTrimbefore40=address.substring(0,45);
				addLine1=addTrimbefore40.substring(0,addTrimbefore40.lastIndexOf(' '));

				addLine2=address.substring(addLine1.length(),address.length());

				addLine3=".";
				addLine4 =".";
			}
			else if(address.length()>90 && address.length()<135){
				String addTrimbefore40=address.substring(0,45);
				addLine1=addTrimbefore40.substring(0,addTrimbefore40.lastIndexOf(' '));

				addLine2=address.substring(addLine1.length(),address.length()-1);

				String add2Trimbefore99=addLine2.substring(0,45);
				addLine2=add2Trimbefore99.substring(0,add2Trimbefore99.lastIndexOf(' '));

				String add3marg = addLine1+addLine2;
				addLine3=address.substring(add3marg.length(),address.length());
				addLine4 =".";
			} 
			else if(address.length()>135 && address.length()<180)
			{
				String addTrimbefore40=address.substring(0,45);
				addLine1=addTrimbefore40.substring(0,addTrimbefore40.lastIndexOf(' '));

				addLine2=address.substring(addLine1.length(),address.length()-1);

				String add2Trimbefore99=addLine2.substring(0,45);
				addLine2=add2Trimbefore99.substring(0,add2Trimbefore99.lastIndexOf(' '));

				String add3marg = addLine1+addLine2;
				addLine3=address.substring(add3marg.length(),address.length()-1);
				
				String addressLine3TrimeBefore=addLine3.substring(0,45);
				addLine3=addressLine3TrimeBefore.substring(0,addressLine3TrimeBefore.lastIndexOf(' '));
				
				String add4len=addLine1+addLine2+addLine3;
				addLine4 =address.substring(add4len.length(),address.length());
			}
			else
			{
				addLine1=address.substring(0,address.length());
				addLine2=".";
				addLine3=".";
				addLine4 =".";
				
			}
			} else {
				if (address.length() > 45 && address.length() < 90) {
					String addTrimbefore40 = address.substring(0, 45);
					addLine1 = addTrimbefore40.substring(0, 40);
					addLine2 = address.substring(addLine1.length(), address.length());
					addLine3 = ".";
					addLine4 = ".";
				}
				else if (address.length() > 90 && address.length() < 135) {
					String addTrimbefore40 = address.substring(0, 45);
					addLine1 = addTrimbefore40.substring(0, 40);
					addLine2 = address.substring(addLine1.length(), address.length() - 1);
					String add2Trimbefore99 = addLine2.substring(0, 45);
					addLine2 = add2Trimbefore99.substring(0, 40);
					String add3marg = addLine1 + addLine2;
					addLine3 = address.substring(add3marg.length(), address.length());
				}
				else if (address.length() > 135 && address.length() < 180) {
					String addTrimbefore40 = address.substring(0, 45);
					addLine1 = addTrimbefore40.substring(0, 40);
					addLine2 = address.substring(addLine1.length(), address.length() - 1);
					String add2Trimbefore99 = addLine2.substring(0, 45);
					addLine2 = add2Trimbefore99.substring(0, 40);
					String add3marg = addLine1 + addLine2;
					addLine3 = address.substring(add3marg.length(), address.length() - 1);
					String addressLine3TrimeBefore = addLine3.substring(0, 45);
					addLine3 = addressLine3TrimeBefore.substring(0, 40);
					String add4len = addLine1 + addLine2 + addLine3;
					addLine4 = address.substring(add4len.length(), address.length());
				}else {
					addLine1 = address.substring(0, address.length());
					addLine2 = ".";
					addLine3 = ".";
					addLine4 = ".";
				}

			}
		}
		catch(Exception e)
		{
			logger.error("==============Exception in addr manipulation====",e);
		}
		
		addressJson.put("add1", addLine1);
		addressJson.put("add2", addLine2);
		addressJson.put("add3", addLine3);
		addressJson.put("add4", addLine4);
		return addressJson;
	}

	public static JSONObject addresManipulationForAuditTrail(String address)
	{
		String addLine1=".";
		String addLine2=".";
		String addLine3 =".";
		String addLine4=".";
		JSONObject addressJson = new JSONObject();

		try
		{
			addLine1=address.substring(0,address.length()>22?22:address.length());
			addLine2=address.length()>44?address.substring(addLine1.length(),addLine1.length()+22):address.substring(addLine1.length(),address.length());
			addLine3=address.length()>66?address.substring(44,66):address.substring((addLine2.length()+addLine1.length()),address.length());
			addLine4=address.length()>67?address.substring((addLine1.length()+addLine2.length()+addLine3.length()),address.length()):"";

		}
		catch(Exception e)
		{
			logger.error("==============Exception in addr manipulation====",e);
			addLine1=address;
		}

		addressJson.put("add1", addLine1);
		addressJson.put("add2", addLine2);
		addressJson.put("add3", addLine3);
		addressJson.put("add4", addLine4);

		return addressJson;
	}
    
	public static JSONObject addresLineThreeManipulation(String address)
	{
		String addLine1=".";
		String addLine2=".";
		String addLine3 =".";
		JSONObject addressJson = new JSONObject();

		try
		{


			if(address.length()>45 && address.length()<90 ){

				String addTrimbefore99=address.substring(0,45);
				addLine1=addTrimbefore99.substring(0,addTrimbefore99.lastIndexOf(' '));

				addLine2=address.substring(addLine1.length(),address.length());

				addLine3=".";

			}
			else if(address.length()>90){
				String addTrimbefore99=address.substring(0,45);
				addLine1=addTrimbefore99.substring(0,addTrimbefore99.lastIndexOf(' '));

				addLine2=address.substring(addLine1.length(),address.length()-1);

				String add2Trimbefore99=addLine2.substring(0,45);
				addLine2=add2Trimbefore99.substring(0,add2Trimbefore99.lastIndexOf(' '));

				String add3marg = addLine1+addLine2;
				addLine3=address.substring(add3marg.length(),address.length()-1);
			} 
			else
			{
				addLine1=address.substring(0,address.length());
				addLine2=".";
				addLine3=".";

			}
		}
		catch(Exception e)
		{
			logger.error("==============Exception in addr manipulation====",e);
		}

		addressJson.put("add1", addLine1);
		addressJson.put("add2", addLine2);
		addressJson.put("add3", addLine3);

		return addressJson;
	}


	public static JSONObject fullNameManipulation(String fullname){

		String fName;
		String lName;
		String mName;
		String second;

		JSONObject newJsonFull = new JSONObject();

		try {
			if (fullname.contains(" ")) {
				fName = fullname.substring(0, fullname.indexOf(' ')).trim();
				second = fullname.substring(fullname.indexOf(' ') + 1).trim();

				if (second.contains(" ")) {
					mName = second.substring(0, second.indexOf(' ')).trim();
					lName = second.substring(second.indexOf(' ') + 1).trim();
				} else {
					lName = second;
					mName = "";
				}

			} else {
				fName = fullname;
				lName = ".";
				mName = "";
			}
			newJsonFull.put("fName", fName);
			newJsonFull.put("lName", lName);
			newJsonFull.put("mName", mName);
		} catch (Exception e) {
			logger.error("========fullNameManipulation======",e);

			newJsonFull.put("fName", "NA");
			newJsonFull.put("lName", ".");
			newJsonFull.put("mName", "");
		}
		return newJsonFull;



	}

	public static String getFileExtension(String fileName)
	{
		logger.info("=======Inside getFileExtension Method fileName======================" + fileName);
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		else
			return "";
	}

	public static File multipartToFile(MultipartFile multipart, String fdCustID,String fileExtn,String fileName,String filesharedName) 
	{

		File convFile = null;
		File createFolder = null;
		boolean flag =false;
		try{

			String custIdFromSession= fdCustID;
			logger.info("========multipartToFile custIdFromSession =========="+custIdFromSession);
			String propetyFilepathFordocumentUploadPath=Utility.getPropertyFileValue("documentUploadPath").trim();
			String path="";
			if("idententiyDocFrontFile".equalsIgnoreCase(filesharedName) || "idententiyDocBackFile".equalsIgnoreCase(filesharedName))
			{
				path=propetyFilepathFordocumentUploadPath+custIdFromSession +"/POI/";
			}else if("addressDocFrontFile".equalsIgnoreCase(filesharedName) || "addressDocBackFile".equalsIgnoreCase(filesharedName))
			{
				path=propetyFilepathFordocumentUploadPath+custIdFromSession +"/POA/";
			}else if("SignDocFile".equalsIgnoreCase(filesharedName))
			{
				path=propetyFilepathFordocumentUploadPath+custIdFromSession +"/DOC/";
			}
			else if("PhotoDocFile".equalsIgnoreCase(filesharedName))
			{
				path=propetyFilepathFordocumentUploadPath+custIdFromSession +"/DOC/";
			}

			logger.info("========onvFile path =========="+path);
			createFolder = new File(path);
			logger.info("========convFile path =========="+createFolder);

			logger.info("========fileExtn =========="+fileExtn);

			if(!(createFolder.exists())){
				flag = createFolder.mkdirs();
			}else{
				flag=true; 
			}

			if(flag){
				String documentUploadPath=path +"/"+fileName+"."+fileExtn;
				logger.info("========documentUploadPath =========="+documentUploadPath);
				convFile = new File(documentUploadPath);
				multipart.transferTo(convFile);
			}
		}catch(Exception e){
			logger.info("======Exception while file copy on server== "+e.getMessage());
		}

		return convFile;
	}

	public static boolean validBOTIP(HttpServletRequest request) throws IOException{

		String remoteAddr = "";
		String url = getPropertyFileValue("externalip").trim();
		logger.info("------ String IP from  property ------- \t " +url); 
		String[] validIP=url.split(",");   
		if (request != null) {         
			//True-Client-IP
			logger.info("---- Request All Headers -- "+request.getHeaderNames());
			remoteAddr = request.getHeader("True-Client-IP");
			if (remoteAddr == null || "".equals(remoteAddr)) 
				remoteAddr = request.getRemoteAddr();
			if(validIP!=null && validIP.length>0){    
				Arrays.sort(validIP); 
				logger.info("------ Client IP ------- \t " +remoteAddr);   
				if(-1 < Arrays.binarySearch(validIP, remoteAddr)){
					logger.info("------Remote IP condition matched------- \t "); 
					return true;
				}
				else{
					logger.info("------Remote IP condition  not matched------- \t "); 
					return false;
				}}   
			else{
				return false;    
			}       


		} else{
			return false;
		}

	}


	public static String convertToPDF(String path,String Filename,String imgImagefile)
	{

		Document convertJpgToPdf=new Document();
		try{
			logger.info("-------path to create PDF inside converttoPDF-----------"+path);
			logger.info("-------Filename to create PDF inside converttoPDF-----------"+Filename);
			String convertedFilePath=path+"/"+Filename+".pdf";
			logger.info("-------convertedFilePath inside converttoPDF-----------"+convertedFilePath);

			//Create Document Object

			//Create PdfWriter for Document to hold physical file

			FileOutputStream fos= new FileOutputStream(convertedFilePath);
			PdfWriter.getInstance(convertJpgToPdf, fos);
			convertJpgToPdf.open();
			//Get the input image to Convert to PDF
			Image convertJpg=Image.getInstance(imgImagefile);


			convertJpg.scaleToFit(PageSize.A5.getWidth(), PageSize.A5.getHeight());

			//Add image to Document
			convertJpgToPdf.add(convertJpg);


			if(convertJpgToPdf.isOpen())
			{
				convertJpgToPdf.close();
				if(fos !=null)

				{
					fos.close(); 
				}
			}
			//Close Document

			File file1 = new File(imgImagefile);
			file1.delete();
			logger.info("---------Successfully Converted JPG to PDF in iText--------");

			return convertedFilePath;
		}
		catch (Exception e){
			logger.error("----Exception while converting jpg to pdf------",e);

			return null;
		}


	}


	public static void mergePDF(String filePath1, String filePath2,String fileName) {

		String path=filePath1.substring(0,filePath1.lastIndexOf('/'));

		File createFolder = null;
		// Instantiating PDFMergerUtility class
		PDFMergerUtility pdfMerger = new PDFMergerUtility();
		try {
			createFolder = new File(path);
			logger.info("========createFolder ==========" + createFolder);
			logger.info("==filePath1 in mergePDF====== "+filePath1);
			logger.info("==filePath2 in mergePDF====== "+filePath2);
			// Loading an existing PDF document
			File file1 = new File(filePath1);
			File file2 = new File(filePath2);

			logger.info("--------Name of filepath1====== "+file1.getName().substring(0,file1.getName().lastIndexOf('.')));
			if (filePath1.contains("jpg") || filePath1.contains("jpeg") || filePath1.contains("JPG")
					|| filePath1.contains("JPEG")) {
				filePath1=convertToPDF( filePath1.substring(0,filePath1.lastIndexOf('/')),file1.getName().substring(0,file1.getName().lastIndexOf('.')),filePath1);
				file1 = new File(filePath1);
			}

			if (filePath2.contains("jpg") || filePath2.contains("jpeg") || filePath2.contains("JPG")
					|| filePath2.contains("JPEG")) {
				filePath2=convertToPDF( filePath2.substring(0,filePath2.lastIndexOf('/')),file2.getName().substring(0,file2.getName().lastIndexOf('.')),filePath2);
				file2 = new File(filePath2);
			}


			PDDocument doc1 = PDDocument.load(file1);
			PDDocument doc2 = PDDocument.load(file2);

			// Setting the destination file
			pdfMerger.setDestinationFileName(path +"/"+fileName+".pdf");

			// adding the source files
			pdfMerger.addSource(file1);
			pdfMerger.addSource(file2);

			// Merging the two documents
			pdfMerger.mergeDocuments(null);
			logger.info("=====Documents merged=====");
			// Closing the documents
			doc1.close();
			doc2.close();

			file1.delete();
			file2.delete();

		} catch (Exception e) {
			logger.error("------ Exception in Megere ----",e);
		}
	}




	public static boolean PaymentBypassRupee(String mobNo)
	{

		try{
			String url = getPropertyFileValue("PaymentBypassRupee").trim();
			logger.info("------ String Mobile from  property File ------- \t " +url); 
			String[] MobileProp=url.split(",");   
			if (mobNo != null) {         

				if(MobileProp!=null && MobileProp.length>0)
				{    
					Arrays.sort(MobileProp); 
					logger.info("------ Customer Mobile Number ------- \t " +mobNo);   
					if(-1 < Arrays.binarySearch(MobileProp, mobNo)){
						logger.info("------Mobile Number condition matched------- \t "); 
						return true;
					}
					else{
						logger.info("------Mobile Number condition  not matched------- \t "); 
						return false;
					}
				}   
				else{
					return false;    
				}       


			} else{
				return false;
			}

		} catch (Exception e) {
			logger.error("------ Exception in PaymentBypassRupee ----",e);
			return false;
		}
	}
	public static void loadNewRelicException(String exceptionType, String context, String userReq,String customerId) {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("attribute1", exceptionType); 
		parameters.put("attribute2", customerId);		
		parameters.put("attribute3", userReq);  
		parameters.put("attribute4", context);	
		NewRelic.noticeError(exceptionType,parameters);
	}

	public static void loadNewRelicValidation( String firstAttribute,  String exceptionType,String customerId,String context) {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("attribute1", firstAttribute);
		parameters.put("attribute2", customerId);
		parameters.put("attribute4", context);
		NewRelic.noticeError(exceptionType,parameters);
	}

	public static void loadNewRelicCustomEvent(String apiName, String timeTaken, String apiStatus, String apiResponse) {
		Map<String, String> eventAttributes = new HashMap<String, String>();
		eventAttributes.put("type", "API_Call");
		eventAttributes.put("api", apiName);		//url endPoint
		eventAttributes.put("APITIMETAKEN", timeTaken);
		eventAttributes.put("STATUS", apiStatus);
		eventAttributes.put("RESPONSE", apiResponse);
		NewRelic.getAgent().getInsights().recordCustomEvent("FDCustomEvent", eventAttributes);
	}
	
	public static void loadNewRelicCustomExceptionEvent(String apiName, String timeTaken, String apiStatus, String apiResponse, String errorMsg) {
		Map<String, String> eventAttributes = new HashMap<String, String>();
		eventAttributes.put("type", "API_Call");
		eventAttributes.put("api", apiName);		//url endPoint
		eventAttributes.put("APITIMETAKEN", timeTaken);
		eventAttributes.put("STATUS", apiStatus);
		eventAttributes.put("RESPONSE", apiResponse);
		eventAttributes.put("ErrorMessage", errorMsg);
		NewRelic.getAgent().getInsights().recordCustomEvent("FDCustomEvent", eventAttributes);
	}
	

	public static void loadNewRelicCustomEvent(String apiName, String timeTaken, String apiStatus, String apiResponse,String contextCalled,String customerId) {
		Map<String, String> eventAttributes = new HashMap<String, String>();
		eventAttributes.put("type", "API_Call");
		eventAttributes.put("api", apiName); // url endPoint
		eventAttributes.put("ContextCalled", contextCalled);
		eventAttributes.put("FDCNumber", customerId);
		eventAttributes.put("APITIMETAKEN", timeTaken);
		eventAttributes.put("STATUS", apiStatus);
		eventAttributes.put("RESPONSE", apiResponse);
		NewRelic.getAgent().getInsights().recordCustomEvent("FDCustomEvent", eventAttributes);
	}

	public static void loadNewRelicCustomExceptionEvent(String apiName, String timeTaken, String apiStatus,
			String apiResponse, String errorMsg,String contextCalled,String customerId) {
		Map<String, String> eventAttributes = new HashMap<String, String>();
		eventAttributes.put("type", "API_Call");
		eventAttributes.put("api", apiName); // url endPoint
		eventAttributes.put("ContextCalled", contextCalled);
		eventAttributes.put("FDCNumber", customerId);
		eventAttributes.put("APITIMETAKEN", timeTaken);
		eventAttributes.put("STATUS", apiStatus);
		eventAttributes.put("RESPONSE", apiResponse);
		eventAttributes.put("ErrorMessage", errorMsg);
		NewRelic.getAgent().getInsights().recordCustomEvent("FDCustomEvent", eventAttributes);
	}
	
	public static String generateJWTToken(String mobileNo) throws UnsupportedEncodingException {

		String token = Jwts.builder().setSubject(mobileNo)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS256, TOKEN_SECRET.getBytes("UTF-8")).compact();

		return token;
	}
	
	
	public static String getReqEncryptedString(String userId) {

		String requesstJsonobj = "";
		String subKey = Utility.getPropertyFileValue("okycSubKey");
		byte[] encryptKey = "1234567890123456".getBytes();
		String iv = "dfghjuytrfgtyhuj";
		SecretKeySpec skeySpec = new SecretKeySpec(encryptKey, "AES");

		JSONObject reuestobj = new JSONObject();

		reuestobj.put("UserId", userId);
		reuestobj.put("SubKey", subKey);

		String newReeuestString = reuestobj.toString();

		logger.info("============== OKYC Reeuest String============" + newReeuestString);
		try {
			byte[] newcipherTextProduct = kycEncryDecryp.encryptOp(newReeuestString.getBytes(), skeySpec,
					iv.getBytes());
			String encriptReqJson = Base64.getEncoder().encodeToString(newcipherTextProduct);
			logger.info("========encript Req Json befour removing +============" + encriptReqJson);

			requesstJsonobj = encriptReqJson.replace('+', '~');

		} catch (Exception e) {
			e.printStackTrace();
		}
		return requesstJsonobj;
	}
	
	
	public static boolean getUtmSources(String utmsource) {
		try {
			String url = getPropertyFileValue("getUtmSources").trim();
			logger.info("------ String UTMSOURCE from  property File ------- \t " + url);
			String[] utmSourceProp = url.split(",");
			if (utmsource != null) {

				if (utmSourceProp != null && utmSourceProp.length > 0) {
					Arrays.sort(utmSourceProp);
					logger.info("------ Utm Source ------- \t " + utmsource);
					if (-1 < Arrays.binarySearch(utmSourceProp, utmsource)) {
						logger.info("------utm source found------- \t ");
						return false;
					} else {
						logger.info("------utm sources not found------- \t ");
						return true;
					}
				} else {
					return true;
				}

			} else {
				return true;
			}

		} catch (Exception e) {
			logger.error("------ Exception in PaymentBypassRupee ----", e);
			return true;
		}
	}
	
	public static Boolean storeCommAddDocument(MultipartFile file, String customerId) {
		Boolean flag = false;
		boolean directoryflag = false;
		try {

			String propetyFilepathFordocumentUploadPath = Utility.getPropertyFileValue("communicationUploadPath").trim();
			String path = propetyFilepathFordocumentUploadPath + new SimpleDateFormat("yyyyMMdd").format(new Date())
					+ "/" + customerId;
			String fileExtn = Utility.getFileExtension(file.getOriginalFilename());

			logger.info("========onvFile path ==========" + path);
			File createFolder = new File(path);
			logger.info("========convFile path ==========" + createFolder);

			logger.info("========fileExtn ==========" + fileExtn);

			if (!(createFolder.exists())) {
				directoryflag = createFolder.mkdirs();
			} else {
				directoryflag = true;
			}

			if (directoryflag) {
				String documentUploadPath = path + "/communicationDoc_" + customerId + "." + fileExtn;
				logger.info("========documentUploadPath ==========" + documentUploadPath);
				File convFile = new File(documentUploadPath);
				file.transferTo(convFile);
				convFile.createNewFile();
				flag = true;
			}
		} catch (Exception e) {
			logger.error("------ Exception in saveCommDocument ----", e);
			return false;
		}
		return flag;
	}
	
}
