package com.bajaj.fixeddeposit.model.dedupe;
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
@JsonPropertyOrder({
"MATCHED_ID",
"MATCHED_PERCNTG",
"Dedupe_Source__c",
"Source_Or_Target__c",
"Loan_Application__c",
"Lead__c",
"Lead_Applicants__c",
"TERR_MATCHED_ID__c",
"Customer_Status__c",
"LAN__c",
"Product__c",
"Loan_Status__c",
"FDD__c",
"Current_Bucket1__c",
"Balance_Amount__c",
"EMI_Amount__c",
"Bank_Account_No__c",
"DPD_String__c",
"Tenure__c",
"Month_On_Book__c",
"Loan_Amount__c",
"custDGDetails",
"custAddressDetails",
"custEmailDetails",
"custContactDetails",
"custLoanDetails",
"reportDetails",
"matchedDetails"
})
public class DemographicDetail {

@JsonProperty("MATCHED_ID")
private String mATCHEDID;
@JsonProperty("MATCHED_PERCNTG")
private String mATCHEDPERCNTG;
@JsonProperty("Dedupe_Source__c")
private String dedupeSourceC;
@JsonProperty("Source_Or_Target__c")
private String sourceOrTargetC;
@JsonProperty("Loan_Application__c")
private Object loanApplicationC;
@JsonProperty("Lead__c")
private Object leadC;
@JsonProperty("Lead_Applicants__c")
private Object leadApplicantsC;
@JsonProperty("TERR_MATCHED_ID__c")
private Object tERRMATCHEDIDC;
@JsonProperty("Customer_Status__c")
private Object customerStatusC;
@JsonProperty("LAN__c")
private Object lANC;
@JsonProperty("Product__c")
private Object productC;
@JsonProperty("Loan_Status__c")
private Object loanStatusC;
@JsonProperty("FDD__c")
private Object fDDC;
@JsonProperty("Current_Bucket1__c")
private Object currentBucket1C;
@JsonProperty("Balance_Amount__c")
private Object balanceAmountC;
@JsonProperty("EMI_Amount__c")
private Object eMIAmountC;
@JsonProperty("Bank_Account_No__c")
private Object bankAccountNoC;
@JsonProperty("DPD_String__c")
private Object dPDStringC;
@JsonProperty("Tenure__c")
private Object tenureC;
@JsonProperty("Month_On_Book__c")
private Object monthOnBookC;
@JsonProperty("Loan_Amount__c")
private Object loanAmountC;
@JsonProperty("custDGDetails")
private List<CustDGDetail> custDGDetails = null;
@JsonProperty("custAddressDetails")
private List<CustAddressDetail> custAddressDetails = null;
@JsonProperty("custEmailDetails")
private List<CustEmailDetail> custEmailDetails = null;
@JsonProperty("custContactDetails")
private List<CustContactDetail> custContactDetails = null;
@JsonProperty("custLoanDetails")
private List<CustLoanDetail> custLoanDetails = null;
@JsonProperty("reportDetails")
private ReportDetails reportDetails;
@JsonProperty("matchedDetails")
private List<MatchedDetail> matchedDetails = null;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<>();

@JsonProperty("MATCHED_ID")
public String getMATCHEDID() {
return mATCHEDID;
}

@JsonProperty("MATCHED_ID")
public void setMATCHEDID(String mATCHEDID) {
this.mATCHEDID = mATCHEDID;
}

@JsonProperty("MATCHED_PERCNTG")
public String getMATCHEDPERCNTG() {
return mATCHEDPERCNTG;
}

@JsonProperty("MATCHED_PERCNTG")
public void setMATCHEDPERCNTG(String mATCHEDPERCNTG) {
this.mATCHEDPERCNTG = mATCHEDPERCNTG;
}

@JsonProperty("Dedupe_Source__c")
public String getDedupeSourceC() {
return dedupeSourceC;
}

@JsonProperty("Dedupe_Source__c")
public void setDedupeSourceC(String dedupeSourceC) {
this.dedupeSourceC = dedupeSourceC;
}

@JsonProperty("Source_Or_Target__c")
public String getSourceOrTargetC() {
return sourceOrTargetC;
}

@JsonProperty("Source_Or_Target__c")
public void setSourceOrTargetC(String sourceOrTargetC) {
this.sourceOrTargetC = sourceOrTargetC;
}

@JsonProperty("Loan_Application__c")
public Object getLoanApplicationC() {
return loanApplicationC;
}

@JsonProperty("Loan_Application__c")
public void setLoanApplicationC(Object loanApplicationC) {
this.loanApplicationC = loanApplicationC;
}

@JsonProperty("Lead__c")
public Object getLeadC() {
return leadC;
}

@JsonProperty("Lead__c")
public void setLeadC(Object leadC) {
this.leadC = leadC;
}

@JsonProperty("Lead_Applicants__c")
public Object getLeadApplicantsC() {
return leadApplicantsC;
}

@JsonProperty("Lead_Applicants__c")
public void setLeadApplicantsC(Object leadApplicantsC) {
this.leadApplicantsC = leadApplicantsC;
}

@JsonProperty("TERR_MATCHED_ID__c")
public Object getTERRMATCHEDIDC() {
return tERRMATCHEDIDC;
}

@JsonProperty("TERR_MATCHED_ID__c")
public void setTERRMATCHEDIDC(Object tERRMATCHEDIDC) {
this.tERRMATCHEDIDC = tERRMATCHEDIDC;
}

@JsonProperty("Customer_Status__c")
public Object getCustomerStatusC() {
return customerStatusC;
}

@JsonProperty("Customer_Status__c")
public void setCustomerStatusC(Object customerStatusC) {
this.customerStatusC = customerStatusC;
}

@JsonProperty("LAN__c")
public Object getLANC() {
return lANC;
}

@JsonProperty("LAN__c")
public void setLANC(Object lANC) {
this.lANC = lANC;
}

@JsonProperty("Product__c")
public Object getProductC() {
return productC;
}

@JsonProperty("Product__c")
public void setProductC(Object productC) {
this.productC = productC;
}

@JsonProperty("Loan_Status__c")
public Object getLoanStatusC() {
return loanStatusC;
}

@JsonProperty("Loan_Status__c")
public void setLoanStatusC(Object loanStatusC) {
this.loanStatusC = loanStatusC;
}

@JsonProperty("FDD__c")
public Object getFDDC() {
return fDDC;
}

@JsonProperty("FDD__c")
public void setFDDC(Object fDDC) {
this.fDDC = fDDC;
}

@JsonProperty("Current_Bucket1__c")
public Object getCurrentBucket1C() {
return currentBucket1C;
}

@JsonProperty("Current_Bucket1__c")
public void setCurrentBucket1C(Object currentBucket1C) {
this.currentBucket1C = currentBucket1C;
}

@JsonProperty("Balance_Amount__c")
public Object getBalanceAmountC() {
return balanceAmountC;
}

@JsonProperty("Balance_Amount__c")
public void setBalanceAmountC(Object balanceAmountC) {
this.balanceAmountC = balanceAmountC;
}

@JsonProperty("EMI_Amount__c")
public Object getEMIAmountC() {
return eMIAmountC;
}

@JsonProperty("EMI_Amount__c")
public void setEMIAmountC(Object eMIAmountC) {
this.eMIAmountC = eMIAmountC;
}

@JsonProperty("Bank_Account_No__c")
public Object getBankAccountNoC() {
return bankAccountNoC;
}

@JsonProperty("Bank_Account_No__c")
public void setBankAccountNoC(Object bankAccountNoC) {
this.bankAccountNoC = bankAccountNoC;
}

@JsonProperty("DPD_String__c")
public Object getDPDStringC() {
return dPDStringC;
}

@JsonProperty("DPD_String__c")
public void setDPDStringC(Object dPDStringC) {
this.dPDStringC = dPDStringC;
}

@JsonProperty("Tenure__c")
public Object getTenureC() {
return tenureC;
}

@JsonProperty("Tenure__c")
public void setTenureC(Object tenureC) {
this.tenureC = tenureC;
}

@JsonProperty("Month_On_Book__c")
public Object getMonthOnBookC() {
return monthOnBookC;
}

@JsonProperty("Month_On_Book__c")
public void setMonthOnBookC(Object monthOnBookC) {
this.monthOnBookC = monthOnBookC;
}

@JsonProperty("Loan_Amount__c")
public Object getLoanAmountC() {
return loanAmountC;
}

@JsonProperty("Loan_Amount__c")
public void setLoanAmountC(Object loanAmountC) {
this.loanAmountC = loanAmountC;
}

@JsonProperty("custDGDetails")
public List<CustDGDetail> getCustDGDetails() {
return custDGDetails;
}

@JsonProperty("custDGDetails")
public void setCustDGDetails(List<CustDGDetail> custDGDetails) {
this.custDGDetails = custDGDetails;
}

@JsonProperty("custAddressDetails")
public List<CustAddressDetail> getCustAddressDetails() {
return custAddressDetails;
}

@JsonProperty("custAddressDetails")
public void setCustAddressDetails(List<CustAddressDetail> custAddressDetails) {
this.custAddressDetails = custAddressDetails;
}

@JsonProperty("custEmailDetails")
public List<CustEmailDetail> getCustEmailDetails() {
return custEmailDetails;
}

@JsonProperty("custEmailDetails")
public void setCustEmailDetails(List<CustEmailDetail> custEmailDetails) {
this.custEmailDetails = custEmailDetails;
}

@JsonProperty("custContactDetails")
public List<CustContactDetail> getCustContactDetails() {
return custContactDetails;
}

@JsonProperty("custContactDetails")
public void setCustContactDetails(List<CustContactDetail> custContactDetails) {
this.custContactDetails = custContactDetails;
}

@JsonProperty("custLoanDetails")
public List<CustLoanDetail> getCustLoanDetails() {
return custLoanDetails;
}

@JsonProperty("custLoanDetails")
public void setCustLoanDetails(List<CustLoanDetail> custLoanDetails) {
this.custLoanDetails = custLoanDetails;
}

@JsonProperty("reportDetails")
public ReportDetails getReportDetails() {
return reportDetails;
}

@JsonProperty("reportDetails")
public void setReportDetails(ReportDetails reportDetails) {
this.reportDetails = reportDetails;
}

@JsonProperty("matchedDetails")
public List<MatchedDetail> getMatchedDetails() {
return matchedDetails;
}

@JsonProperty("matchedDetails")
public void setMatchedDetails(List<MatchedDetail> matchedDetails) {
this.matchedDetails = matchedDetails;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

@Override
public String toString() {
	return "DemographicDetail [mATCHEDID=" + mATCHEDID + ", mATCHEDPERCNTG=" + mATCHEDPERCNTG + ", dedupeSourceC="
			+ dedupeSourceC + ", sourceOrTargetC=" + sourceOrTargetC + ", loanApplicationC=" + loanApplicationC
			+ ", leadC=" + leadC + ", leadApplicantsC=" + leadApplicantsC + ", tERRMATCHEDIDC=" + tERRMATCHEDIDC
			+ ", customerStatusC=" + customerStatusC + ", lANC=" + lANC + ", productC=" + productC + ", loanStatusC="
			+ loanStatusC + ", fDDC=" + fDDC + ", currentBucket1C=" + currentBucket1C + ", balanceAmountC="
			+ balanceAmountC + ", eMIAmountC=" + eMIAmountC + ", bankAccountNoC=" + bankAccountNoC + ", dPDStringC="
			+ dPDStringC + ", tenureC=" + tenureC + ", monthOnBookC=" + monthOnBookC + ", loanAmountC=" + loanAmountC
			+ ", custDGDetails=" + custDGDetails + ", custAddressDetails=" + custAddressDetails + ", custEmailDetails="
			+ custEmailDetails + ", custContactDetails=" + custContactDetails + ", custLoanDetails=" + custLoanDetails
			+ ", reportDetails=" + reportDetails + ", matchedDetails=" + matchedDetails + ", additionalProperties="
			+ additionalProperties + "]";
}



}