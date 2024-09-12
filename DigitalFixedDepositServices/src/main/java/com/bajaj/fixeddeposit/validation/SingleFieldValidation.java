package com.bajaj.fixeddeposit.validation;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;

@Component
public class SingleFieldValidation {

	private static final Logger logger = Logger.getLogger(SingleFieldValidation.class);

	private static final String OTP_PINCODE_PATTERN = "\\d{6}$";
	private static final String MOBILEPATTERN="^[6789]\\d{9}$";
	private static final String EMAIL_ID_PATTERN= "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	private static final String DATE_PATTERN = "\\d{2}/\\d{2}/\\d{4}";

	public boolean mobileNumberValidation(String mobileNumber){

		logger.info("----- Mobile Number Validation ----");
		if(mobileNumber != null && !mobileNumber.isEmpty() && mobileNumber.matches(MOBILEPATTERN))
			return true;
		else
			return false;

	}




	public boolean pinCodeValidation(String pin){
		if(pin!=null && !pin.isEmpty() && pin.matches(OTP_PINCODE_PATTERN))
			return true;
		else
			return false; 

	}

	public boolean dateValidation(String date){
		if(date!=null && !date.isEmpty() && date.matches(DATE_PATTERN))
			return true;
		else
			return false; 

	}
	public boolean emailidValidation(String emailid){
		logger.info("Email "+emailid);
		if(emailid!=null && !emailid.isEmpty() && emailid.matches(EMAIL_ID_PATTERN))
			return true;
		else
			return false;

	} 

	public boolean textValidation(String text){
		if(text!=null && !text.isEmpty() && text.equalsIgnoreCase(HtmlUtils.htmlEscape(text)))
			return true;
		else
			return false; 

	}
}
