
package com.bajaj.fixeddeposit.model.sfdc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "CustID", "ApplicantType", "Salutation", "FName", "MName", "LName", "DOB", "Gender", "Mobile",
		"Email", "PAN", "Form60_61", "Occupation_EmplType", "IdentityDocType", "IdentityDocNo", "IdentityDocExpiryDate",
		"CKYCNo", "MaritalStatus", "Father_Spouse", "Father_SpouseSalutation", "Father_SpouseFName",
		"Father_SpouseMName", "Father_SpouseLName", "MotherFName", "MotherMName", "MotherLName", "DateofIncorporation",
		"AnnualIncome", "STDCode", "Landline", "CustTdsType", "ResidentType", "TIN", "NRIOtherDocID",
		"CountryofForeignResi", "CountryofTaxResi", "NRIRemarks", "VisaType", "VisaPermit", "VisaPermitNo",
		"VisaIssueDate", "PlaceOfIssue", "ValidUpto", "PassportNo", "PassportExpiryDate", "PassPlaceofIssue",
		"TaxResOutInd", "AdressDetailsList", "NomineeRelationship", "OKYCRequestId", "AuthCode", "AadharRefId",
		"OKYCMobile", "OKYCEmail", "OKYCStatus", "OKYCIpAddr", "OKYCCompletionDT", "OKYCInitiationDT",
		"PANNSDLResponse", "DemogUpdate", "GuardianAge", "GuardianAddr1", "GuardianAddr2", "GuardianAddr3",
		"GuardianCity", "GuardianPincode", "GuardianState", "GuardianName", "GuardianSalutation", "GuardianCountry",
		"CustomerCategory","AcceptanceTimeStamp","IPAddress","isPANEdited"

})
public class ObjApplDetail {

	@JsonProperty("CustID")
	private String custID;
	@JsonProperty("ApplicantType")
	private String applicantType;
	@JsonProperty("Salutation")
	private String salutation;
	@JsonProperty("FName")
	private String fName;
	@JsonProperty("MName")
	private String mName;
	@JsonProperty("LName")
	private String lName;
	@JsonProperty("DOB")
	private String dateBirthValue;
	@JsonProperty("Gender")
	private String gender;
	@JsonProperty("Mobile")
	private String mobile;
	@JsonProperty("Email")
	private String email;
	@JsonProperty("PAN")
	private String pAN;
	@JsonProperty("Form60_61")
	private String form6061;
	@JsonProperty("Occupation_EmplType")
	private String occupationEmplType;
	@JsonProperty("IdentityDocType")
	private String identityDocType;
	@JsonProperty("IdentityDocNo")
	private String identityDocNo;
	@JsonProperty("IdentityDocExpiryDate")
	private String identityDocExpiryDate;
	@JsonProperty("CKYCNo")
	private String cKYCNo;
	@JsonProperty("MaritalStatus")
	private String maritalStatus;
	@JsonProperty("Father_Spouse")
	private String fatherSpouse;
	@JsonProperty("Father_SpouseSalutation")
	private String fatherSpouseSalutation;
	@JsonProperty("Father_SpouseFName")
	private String fatherSpouseFName;
	@JsonProperty("Father_SpouseMName")
	private String fatherSpouseMName;
	@JsonProperty("Father_SpouseLName")
	private String fatherSpouseLName;
	@JsonProperty("MotherFName")
	private String motherFName;
	@JsonProperty("MotherMName")
	private String motherMName;
	@JsonProperty("MotherLName")
	private String motherLName;
	@JsonProperty("DateofIncorporation")
	private String dateofIncorporation;
	@JsonProperty("AnnualIncome")
	private String annualIncome;
	@JsonProperty("STDCode")
	private String sTDCode;
	@JsonProperty("Landline")
	private String landline;
	@JsonProperty("CustTdsType")
	private String custTdsType;
	@JsonProperty("ResidentType")
	private String residentType;
	@JsonProperty("TIN")
	private String tIN;
	@JsonProperty("NRIOtherDocID")
	private String nRIOtherDocID;
	@JsonProperty("CountryofForeignResi")
	private String countryofForeignResi;
	@JsonProperty("CountryofTaxResi")
	private String countryofTaxResi;
	@JsonProperty("NRIRemarks")
	private String nRIRemarks;
	@JsonProperty("VisaType")
	private String visaType;
	@JsonProperty("VisaPermit")
	private String visaPermit;
	@JsonProperty("VisaPermitNo")
	private String visaPermitNo;
	@JsonProperty("VisaIssueDate")
	private String visaIssueDate;
	@JsonProperty("PlaceOfIssue")
	private String placeOfIssue;
	@JsonProperty("ValidUpto")
	private String validUpto;
	@JsonProperty("PassportNo")
	private String passportNo;
	@JsonProperty("PassportExpiryDate")
	private String passportExpiryDate;
	@JsonProperty("PassPlaceofIssue")
	private String PlaceofIssue;
	@JsonProperty("TaxResOutInd")
	private String taxResOutInd;
	@JsonProperty("AdressDetailsList")
	private List<AdressDetailsList> adressDetailsList = null;
	@JsonProperty("NomineeRelationship")
	private String nomineeRelationship; 
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<>();

	@JsonProperty("OKYCRequestId")
	private String okycRequestId;
	@JsonProperty("AuthCode")
	private String authCode;
	@JsonProperty("AadharRefId")
	private String aadharRefId;
	@JsonProperty("OKYCMobile")
	private String okycMobile;
	@JsonProperty("OKYCEmail")
	private String okycEmail;
	@JsonProperty("OKYCStatus")
	private String okycAPIStatus;
	@JsonProperty("OKYCIpAddr")
	private String okycIpAddr;
	@JsonProperty("OKYCCompletionDT")
	private String okycCompletionDT;
	@JsonProperty("OKYCInitiationDT")
	private String initiationDT;

	@JsonProperty("PANNSDLResponse")
	private String nsdlResponse;

	@JsonProperty("DemogUpdate")
	private String demogUpdate;

	@JsonProperty("GuardianAge")
	private String gaurdianAge;

	@JsonProperty("GuardianAddr1")
	private String guardianAddress1;

	@JsonProperty("GuardianAddr2")
	private String guardianAddress2;

	@JsonProperty("GuardianAddr3")
	private String guardianAddress3;

	@JsonProperty("GuardianCity")
	private String guardianCity;

	@JsonProperty("GuardianPincode")
	private String guardianPincode;

	@JsonProperty("GuardianState")
	private String guardianState;

	@JsonProperty("GuardianName")
	private String guardianName;

	@JsonProperty("GuardianSalutation")
	private String guardianSaluttion;

	@JsonProperty("GuardianCountry")
	private String guardianCountry;

	@JsonProperty("CustomerCategory")
	private String customerCategory;
	
	@JsonProperty("AcceptanceTimeStamp")
	private String acceptanceTimeStamp;
		
	@JsonProperty("IPAddress")
	private String ipAddress;
	
	@JsonProperty("isPANEdited")
	private String ispanedited;

	@JsonProperty("isPANEdited")
	public String getIspanedited() {
		return ispanedited;
	}
	
	@JsonProperty("isPANEdited")	
	public void setIspanedited(String ispanedited) {
		this.ispanedited = ispanedited;
	}

	@JsonProperty("CustID")
	public String getCustID() {
		return custID;
	}

	@JsonProperty("CustID")
	public void setCustID(String custID) {
		this.custID = custID;
	}

	@JsonProperty("ApplicantType")
	public String getApplicantType() {
		return applicantType;
	}

	@JsonProperty("ApplicantType")
	public void setApplicantType(String applicantType) {
		this.applicantType = applicantType;
	}

	@JsonProperty("Salutation")
	public String getSalutation() {
		return salutation;
	}

	@JsonProperty("Salutation")
	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	@JsonProperty("FName")
	public String getFName() {
		return fName;
	}

	@JsonProperty("FName")
	public void setFName(String fName) {
		this.fName = fName;
	}

	@JsonProperty("MName")
	public String getMName() {
		return mName;
	}

	@JsonProperty("MName")
	public void setMName(String mName) {
		this.mName = mName;
	}

	@JsonProperty("LName")
	public String getLName() {
		return lName;
	}

	@JsonProperty("LName")
	public void setLName(String lName) {
		this.lName = lName;
	}

	@JsonProperty("DOB")
	public String getDateBirthValue() {
		return dateBirthValue;
	}

	@JsonProperty("DOB")
	public void setDateBirthValue(String dateBirthValue) {
		this.dateBirthValue = dateBirthValue;
	}

	@JsonProperty("Gender")
	public String getGender() {
		return gender;
	}

	@JsonProperty("Gender")
	public void setGender(String gender) {
		this.gender = gender;
	}

	@JsonProperty("Mobile")
	public String getMobile() {
		return mobile;
	}

	@JsonProperty("Mobile")
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@JsonProperty("Email")
	public String getEmail() {
		return email;
	}

	@JsonProperty("Email")
	public void setEmail(String email) {
		this.email = email;
	}

	@JsonProperty("PAN")
	public String getPAN() {
		return pAN;
	}

	@JsonProperty("PAN")
	public void setPAN(String pAN) {
		this.pAN = pAN;
	}

	@JsonProperty("Form60_61")
	public String getForm6061() {
		return form6061;
	}

	@JsonProperty("Form60_61")
	public void setForm6061(String form6061) {
		this.form6061 = form6061;
	}

	@JsonProperty("Occupation_EmplType")
	public String getOccupationEmplType() {
		return occupationEmplType;
	}

	@JsonProperty("Occupation_EmplType")
	public void setOccupationEmplType(String occupationEmplType) {
		this.occupationEmplType = occupationEmplType;
	}

	@JsonProperty("IdentityDocType")
	public String getIdentityDocType() {
		return identityDocType;
	}

	@JsonProperty("IdentityDocType")
	public void setIdentityDocType(String identityDocType) {
		this.identityDocType = identityDocType;
	}

	@JsonProperty("IdentityDocNo")
	public String getIdentityDocNo() {
		return identityDocNo;
	}

	@JsonProperty("IdentityDocNo")
	public void setIdentityDocNo(String identityDocNo) {
		this.identityDocNo = identityDocNo;
	}

	@JsonProperty("IdentityDocExpiryDate")
	public String getIdentityDocExpiryDate() {
		return identityDocExpiryDate;
	}

	@JsonProperty("IdentityDocExpiryDate")
	public void setIdentityDocExpiryDate(String identityDocExpiryDate) {
		this.identityDocExpiryDate = identityDocExpiryDate;
	}

	@JsonProperty("CKYCNo")
	public String getCKYCNo() {
		return cKYCNo;
	}

	@JsonProperty("CKYCNo")
	public void setCKYCNo(String cKYCNo) {
		this.cKYCNo = cKYCNo;
	}

	@JsonProperty("MaritalStatus")
	public String getMaritalStatus() {
		return maritalStatus;
	}

	@JsonProperty("MaritalStatus")
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	@JsonProperty("Father_Spouse")
	public String getFatherSpouse() {
		return fatherSpouse;
	}

	@JsonProperty("Father_Spouse")
	public void setFatherSpouse(String fatherSpouse) {
		this.fatherSpouse = fatherSpouse;
	}

	@JsonProperty("Father_SpouseSalutation")
	public String getFatherSpouseSalutation() {
		return fatherSpouseSalutation;
	}

	@JsonProperty("Father_SpouseSalutation")
	public void setFatherSpouseSalutation(String fatherSpouseSalutation) {
		this.fatherSpouseSalutation = fatherSpouseSalutation;
	}

	@JsonProperty("Father_SpouseFName")
	public String getFatherSpouseFName() {
		return fatherSpouseFName;
	}

	@JsonProperty("Father_SpouseFName")
	public void setFatherSpouseFName(String fatherSpouseFName) {
		this.fatherSpouseFName = fatherSpouseFName;
	}

	@JsonProperty("Father_SpouseMName")
	public String getFatherSpouseMName() {
		return fatherSpouseMName;
	}

	@JsonProperty("Father_SpouseMName")
	public void setFatherSpouseMName(String fatherSpouseMName) {
		this.fatherSpouseMName = fatherSpouseMName;
	}

	@JsonProperty("Father_SpouseLName")
	public String getFatherSpouseLName() {
		return fatherSpouseLName;
	}

	@JsonProperty("Father_SpouseLName")
	public void setFatherSpouseLName(String fatherSpouseLName) {
		this.fatherSpouseLName = fatherSpouseLName;
	}

	@JsonProperty("MotherFName")
	public String getMotherFName() {
		return motherFName;
	}

	@JsonProperty("MotherFName")
	public void setMotherFName(String motherFName) {
		this.motherFName = motherFName;
	}

	@JsonProperty("MotherMName")
	public String getMotherMName() {
		return motherMName;
	}

	@JsonProperty("MotherMName")
	public void setMotherMName(String motherMName) {
		this.motherMName = motherMName;
	}

	@JsonProperty("MotherLName")
	public String getMotherLName() {
		return motherLName;
	}

	@JsonProperty("MotherLName")
	public void setMotherLName(String motherLName) {
		this.motherLName = motherLName;
	}

	@JsonProperty("DateofIncorporation")
	public String getDateofIncorporation() {
		return dateofIncorporation;
	}

	@JsonProperty("DateofIncorporation")
	public void setDateofIncorporation(String dateofIncorporation) {
		this.dateofIncorporation = dateofIncorporation;
	}

	@JsonProperty("AnnualIncome")
	public String getAnnualIncome() {
		return annualIncome;
	}

	@JsonProperty("AnnualIncome")
	public void setAnnualIncome(String annualIncome) {
		this.annualIncome = annualIncome;
	}

	@JsonProperty("STDCode")
	public String getSTDCode() {
		return sTDCode;
	}

	@JsonProperty("STDCode")
	public void setSTDCode(String sTDCode) {
		this.sTDCode = sTDCode;
	}

	@JsonProperty("Landline")
	public String getLandline() {
		return landline;
	}

	@JsonProperty("Landline")
	public void setLandline(String landline) {
		this.landline = landline;
	}

	@JsonProperty("CustTdsType")
	public String getCustTdsType() {
		return custTdsType;
	}

	@JsonProperty("CustTdsType")
	public void setCustTdsType(String custTdsType) {
		this.custTdsType = custTdsType;
	}

	@JsonProperty("ResidentType")
	public String getResidentType() {
		return residentType;
	}

	@JsonProperty("ResidentType")
	public void setResidentType(String residentType) {
		this.residentType = residentType;
	}

	@JsonProperty("TIN")
	public String getTIN() {
		return tIN;
	}

	@JsonProperty("TIN")
	public void setTIN(String tIN) {
		this.tIN = tIN;
	}

	@JsonProperty("NRIOtherDocID")
	public String getNRIOtherDocID() {
		return nRIOtherDocID;
	}

	@JsonProperty("NRIOtherDocID")
	public void setNRIOtherDocID(String nRIOtherDocID) {
		this.nRIOtherDocID = nRIOtherDocID;
	}

	@JsonProperty("CountryofForeignResi")
	public String getCountryofForeignResi() {
		return countryofForeignResi;
	}

	@JsonProperty("CountryofForeignResi")
	public void setCountryofForeignResi(String countryofForeignResi) {
		this.countryofForeignResi = countryofForeignResi;
	}

	@JsonProperty("CountryofTaxResi")
	public String getCountryofTaxResi() {
		return countryofTaxResi;
	}

	@JsonProperty("CountryofTaxResi")
	public void setCountryofTaxResi(String countryofTaxResi) {
		this.countryofTaxResi = countryofTaxResi;
	}

	@JsonProperty("NRIRemarks")
	public String getNRIRemarks() {
		return nRIRemarks;
	}

	@JsonProperty("NRIRemarks")
	public void setNRIRemarks(String nRIRemarks) {
		this.nRIRemarks = nRIRemarks;
	}

	@JsonProperty("VisaType")
	public String getVisaType() {
		return visaType;
	}

	@JsonProperty("VisaType")
	public void setVisaType(String visaType) {
		this.visaType = visaType;
	}

	@JsonProperty("VisaPermit")
	public String getVisaPermit() {
		return visaPermit;
	}

	@JsonProperty("VisaPermit")
	public void setVisaPermit(String visaPermit) {
		this.visaPermit = visaPermit;
	}

	@JsonProperty("VisaPermitNo")
	public String getVisaPermitNo() {
		return visaPermitNo;
	}

	@JsonProperty("VisaPermitNo")
	public void setVisaPermitNo(String visaPermitNo) {
		this.visaPermitNo = visaPermitNo;
	}

	@JsonProperty("VisaIssueDate")
	public String getVisaIssueDate() {
		return visaIssueDate;
	}

	@JsonProperty("VisaIssueDate")
	public void setVisaIssueDate(String visaIssueDate) {
		this.visaIssueDate = visaIssueDate;
	}

	@JsonProperty("PlaceOfIssue")
	public String getPlaceOfIssue() {
		return placeOfIssue;
	}

	@JsonProperty("PlaceOfIssue")
	public void setPlaceOfIssue(String placeOfIssue) {
		this.placeOfIssue = placeOfIssue;
	}

	@JsonProperty("ValidUpto")
	public String getValidUpto() {
		return validUpto;
	}

	@JsonProperty("ValidUpto")
	public void setValidUpto(String validUpto) {
		this.validUpto = validUpto;
	}

	@JsonProperty("PassportNo")
	public String getPassportNo() {
		return passportNo;
	}

	@JsonProperty("PassportNo")
	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	@JsonProperty("PassportExpiryDate")
	public String getPassportExpiryDate() {
		return passportExpiryDate;
	}

	@JsonProperty("PassportExpiryDate")
	public void setPassportExpiryDate(String passportExpiryDate) {
		this.passportExpiryDate = passportExpiryDate;
	}

	@JsonProperty("PassPlaceofIssue")
	public String getPlaceofIssue() {
		return PlaceofIssue;
	}

	@JsonProperty("PassPlaceofIssue")
	public void setPlaceofIssue(String placeofIssue) {
		PlaceofIssue = placeofIssue;
	}

	@JsonProperty("TaxResOutInd")
	public String getTaxResOutInd() {
		return taxResOutInd;
	}

	@JsonProperty("TaxResOutInd")
	public void setTaxResOutInd(String taxResOutInd) {
		this.taxResOutInd = taxResOutInd;
	}

	@JsonProperty("AdressDetailsList")
	public List<AdressDetailsList> getAdressDetailsList() {
		return adressDetailsList;
	}

	@JsonProperty("AdressDetailsList")
	public void setAdressDetailsList(List<AdressDetailsList> adressDetailsList) {
		this.adressDetailsList = adressDetailsList;
	}

	@JsonProperty("NomineeRelationship")
	public String getNomineeRelationship() {
		return nomineeRelationship;
	}

	@JsonProperty("NomineeRelationship")
	public void setNomineeRelationship(String nomineeRelationship) {
		this.nomineeRelationship = nomineeRelationship;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@JsonProperty("OKYCRequestId")
	public String getOkycRequestId() {
		return okycRequestId;
	}

	@JsonProperty("OKYCRequestId")
	public void setOkycRequestId(String okycRequestId) {
		this.okycRequestId = okycRequestId;
	}

	@JsonProperty("AuthCode")
	public String getAuthCode() {
		return authCode;
	}

	@JsonProperty("AuthCode")
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	@JsonProperty("AadharRefId")
	public String getAadharRefId() {
		return aadharRefId;
	}

	@JsonProperty("AadharRefId")
	public void setAadharRefId(String aadharRefId) {
		this.aadharRefId = aadharRefId;
	}

	@JsonProperty("OKYCMobile")
	public String getOkycMobile() {
		return okycMobile;
	}

	@JsonProperty("OKYCMobile")
	public void setOkycMobile(String okycMobile) {
		this.okycMobile = okycMobile;
	}

	@JsonProperty("OKYCEmail")
	public String getOkycEmail() {
		return okycEmail;
	}

	@JsonProperty("OKYCEmail")
	public void setOkycEmail(String okycEmail) {
		this.okycEmail = okycEmail;
	}

	@JsonProperty("OKYCStatus")
	public String getOkycAPIStatus() {
		return okycAPIStatus;
	}

	@JsonProperty("OKYCStatus")
	public void setOkycAPIStatus(String okycAPIStatus) {
		this.okycAPIStatus = okycAPIStatus;
	}

	@JsonProperty("OKYCIpAddr")
	public String getOkycIpAddr() {
		return okycIpAddr;
	}

	@JsonProperty("OKYCIpAddr")
	public void setOkycIpAddr(String okycIpAddr) {
		this.okycIpAddr = okycIpAddr;
	}

	@JsonProperty("OKYCCompletionDT")
	public String getOkycCompletionDT() {
		return okycCompletionDT;
	}

	@JsonProperty("OKYCCompletionDT")
	public void setOkycCompletionDT(String okycCompletionDT) {
		this.okycCompletionDT = okycCompletionDT;
	}

	@JsonProperty("OKYCInitiationDT")
	public String getInitiationDT() {
		return initiationDT;
	}

	@JsonProperty("OKYCInitiationDT")
	public void setInitiationDT(String initiationDT) {
		this.initiationDT = initiationDT;
	}

	@JsonProperty("PANNSDLResponse")
	public String getNsdlResponse() {
		return nsdlResponse;
	}

	@JsonProperty("PANNSDLResponse")
	public void setNsdlResponse(String nsdlResponse) {
		this.nsdlResponse = nsdlResponse;
	}

	public String getDemogUpdate() {
		return demogUpdate;
	}

	@JsonProperty("DemogUpdate")
	public void setDemogUpdate(String demogUpdate) {
		this.demogUpdate = demogUpdate;
	}

	public String getGaurdianAge() {
		return gaurdianAge;
	}

	@JsonProperty("GuardianAge")
	public void setGaurdianAge(String gaurdianAge) {
		this.gaurdianAge = gaurdianAge;
	}

	public String getGuardianCity() {
		return guardianCity;
	}

	@JsonProperty("GuardianCity")
	public void setGuardianCity(String guardianCity) {
		this.guardianCity = guardianCity;
	}

	public String getGuardianPincode() {
		return guardianPincode;
	}

	@JsonProperty("GuardianPincode")
	public void setGuardianPincode(String guardianPincode) {
		this.guardianPincode = guardianPincode;
	}

	public String getGuardianState() {
		return guardianState;
	}

	@JsonProperty("GuardianState")
	public void setGuardianState(String guardianState) {
		this.guardianState = guardianState;
	}

	public String getGuardianName() {
		return guardianName;
	}

	@JsonProperty("GuardianName")
	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}

	public String getGuardianAddress1() {
		return guardianAddress1;
	}

	@JsonProperty("GuardianAddr1")
	public void setGuardianAddress1(String guardianAddress1) {
		this.guardianAddress1 = guardianAddress1;
	}

	public String getGuardianAddress2() {
		return guardianAddress2;
	}

	@JsonProperty("GuardianAddr2")
	public void setGuardianAddress2(String guardianAddress2) {
		this.guardianAddress2 = guardianAddress2;
	}

	public String getGuardianAddress3() {
		return guardianAddress3;
	}

	@JsonProperty("GuardianAddr3")
	public void setGuardianAddress3(String guardianAddress3) {
		this.guardianAddress3 = guardianAddress3;
	}

	public String getGuardianSaluttion() {
		return guardianSaluttion;
	}

	@JsonProperty("GuardianSalutation")
	public void setGuardianSaluttion(String guardianSaluttion) {
		this.guardianSaluttion = guardianSaluttion;
	}

	public String getGuardianCountry() {
		return guardianCountry;
	}

	@JsonProperty("GuardianCountry")
	public void setGuardianCountry(String guardianCountry) {
		this.guardianCountry = guardianCountry;
	}

	public String getCustomerCategory() {
		return customerCategory;
	}

	public void setCustomerCategory(String customerCategory) {
		this.customerCategory = customerCategory;
	}
	
	@JsonProperty("AcceptanceTimeStamp")
	public String getAcceptanceTimeStamp() {
		return acceptanceTimeStamp;
	}

	@JsonProperty("AcceptanceTimeStamp")
	public void setAcceptanceTimeStamp(String acceptanceTimeStamp) {
		this.acceptanceTimeStamp = acceptanceTimeStamp;
	}
	
	@JsonProperty("IPAddress")
	public String getIPAddress() {
		return ipAddress;
	}

	@JsonProperty("IPAddress")
	public void setIPAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Override
	public String toString() {
		return "ObjApplDetail [custID=" + custID + ", applicantType=" + applicantType + ", salutation=" + salutation
				+ ", fName=" + fName + ", mName=" + mName + ", lName=" + lName + ", dateBirthValue=" + dateBirthValue
				+ ", gender=" + gender + ", mobile=" + mobile + ", email=" + email + ", pAN=" + pAN + ", form6061="
				+ form6061 + ", occupationEmplType=" + occupationEmplType + ", identityDocType=" + identityDocType
				+ ", identityDocNo=" + identityDocNo + ", identityDocExpiryDate=" + identityDocExpiryDate + ", cKYCNo="
				+ cKYCNo + ", maritalStatus=" + maritalStatus + ", fatherSpouse=" + fatherSpouse
				+ ", fatherSpouseSalutation=" + fatherSpouseSalutation + ", fatherSpouseFName=" + fatherSpouseFName
				+ ", fatherSpouseMName=" + fatherSpouseMName + ", fatherSpouseLName=" + fatherSpouseLName
				+ ", motherFName=" + motherFName + ", motherMName=" + motherMName + ", motherLName=" + motherLName
				+ ", dateofIncorporation=" + dateofIncorporation + ", annualIncome=" + annualIncome + ", sTDCode="
				+ sTDCode + ", landline=" + landline + ", custTdsType=" + custTdsType + ", residentType=" + residentType
				+ ", tIN=" + tIN + ", nRIOtherDocID=" + nRIOtherDocID + ", countryofForeignResi=" + countryofForeignResi
				+ ", countryofTaxResi=" + countryofTaxResi + ", nRIRemarks=" + nRIRemarks + ", visaType=" + visaType
				+ ", visaPermit=" + visaPermit + ", visaPermitNo=" + visaPermitNo + ", visaIssueDate=" + visaIssueDate
				+ ", placeOfIssue=" + placeOfIssue + ", validUpto=" + validUpto + ", passportNo=" + passportNo
				+ ", passportExpiryDate=" + passportExpiryDate + ", PlaceofIssue=" + PlaceofIssue + ", taxResOutInd="
				+ taxResOutInd + ", adressDetailsList=" + adressDetailsList + ", nomineeRelationship="
				+ nomineeRelationship + ", additionalProperties=" + additionalProperties + ", okycRequestId="
				+ okycRequestId + ", authCode=" + authCode + ", aadharRefId=" + aadharRefId + ", okycMobile="
				+ okycMobile + ", okycEmail=" + okycEmail + ", okycAPIStatus=" + okycAPIStatus + ", okycIpAddr="
				+ okycIpAddr + ", okycCompletionDT=" + okycCompletionDT + ", initiationDT=" + initiationDT
				+ ", nsdlResponse=" + nsdlResponse + ", demogUpdate=" + demogUpdate + ", gaurdianAge=" + gaurdianAge
				+ ", guardianAddress1=" + guardianAddress1 + ", guardianAddress2=" + guardianAddress2
				+ ", guardianAddress3=" + guardianAddress3 + ", guardianCity=" + guardianCity + ", guardianPincode="
				+ guardianPincode + ", guardianState=" + guardianState + ", guardianName=" + guardianName
				+ ", guardianSaluttion=" + guardianSaluttion + ", guardianCountry=" + guardianCountry
				+ ", customerCategory=" + customerCategory + ", acceptanceTimeStamp=" + acceptanceTimeStamp 
				+ ", ipAddress=" + ipAddress + ", ispanedited=" +ispanedited+ "]";
	}

}
