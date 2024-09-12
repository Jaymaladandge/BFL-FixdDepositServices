package com.bajaj.fixeddeposit.model.sfdc;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SingleSignOn implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7649226476845797051L;

	@JsonProperty(value = "mobile_number_mobileTrue",required=true)
	private String mobileNumber;
	
	@JsonProperty(value = "date_of_birth_dateTrue",required=true)
	private String dob;
	
	@JsonProperty(value = "ProductName",required=true)
	private String productName;
	
	@JsonProperty(value = "SourceName",required=true)
	private String sourceName;
	
	@JsonProperty(value = "pincode_pincodeTrue",required=true)
	private String pincode;

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "SingleSignOn [mobileNumber=" + mobileNumber + ", dob=" + dob + ", productName=" + productName
				+ ", sourceName=" + sourceName + ", pincode=" + pincode + "]";
	}

	

}
