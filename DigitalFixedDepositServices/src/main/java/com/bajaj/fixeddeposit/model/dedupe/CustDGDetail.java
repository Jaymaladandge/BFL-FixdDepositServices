package com.bajaj.fixeddeposit.model.dedupe;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"Customer_ID__c",
"Customer_Type__c",
"Name",
"Fathers_Husband_s_Name__c",
"DOB__c",
"PAN__c",
"Voterid__c",
"Cin__c",
"Din__c",
"AadhaarNo",
"Gender_Flag",
"Marital_Status_Flag",
"Source_System",
"creationDate",
"product"
})
public class CustDGDetail {

@JsonProperty("Customer_ID__c")
private String customerIDC;
@JsonProperty("Customer_Type__c")
private Object customerTypeC;
@JsonProperty("Name")
private String name;
@JsonProperty("Fathers_Husband_s_Name__c")
private Object fathersHusbandSNameC;
@JsonProperty("DOB__c")
private String dOBC;
@JsonProperty("PAN__c")
private String pANC;
@JsonProperty("Voterid__c")
private Object voteridC;
@JsonProperty("Cin__c")
private Object cinC;
@JsonProperty("Din__c")
private Object dinC;
@JsonProperty("AadhaarNo")
private Object aadhaarNo;
@JsonProperty("Gender_Flag")
private Object genderFlag;
@JsonProperty("Marital_Status_Flag")
private Object maritalStatusFlag;
@JsonProperty("Source_System")
private String sourceSystem;
@JsonProperty("creationDate")
private String creationDate;
@JsonProperty("product")
private Object product;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<>();

@JsonProperty("Customer_ID__c")
public String getCustomerIDC() {
return customerIDC;
}

@JsonProperty("Customer_ID__c")
public void setCustomerIDC(String customerIDC) {
this.customerIDC = customerIDC;
}

@JsonProperty("Customer_Type__c")
public Object getCustomerTypeC() {
return customerTypeC;
}

@JsonProperty("Customer_Type__c")
public void setCustomerTypeC(Object customerTypeC) {
this.customerTypeC = customerTypeC;
}

@JsonProperty("Name")
public String getName() {
return name;
}

@JsonProperty("Name")
public void setName(String name) {
this.name = name;
}

@JsonProperty("Fathers_Husband_s_Name__c")
public Object getFathersHusbandSNameC() {
return fathersHusbandSNameC;
}

@JsonProperty("Fathers_Husband_s_Name__c")
public void setFathersHusbandSNameC(Object fathersHusbandSNameC) {
this.fathersHusbandSNameC = fathersHusbandSNameC;
}

@JsonProperty("DOB__c")
public String getDOBC() {
return dOBC;
}

@JsonProperty("DOB__c")
public void setDOBC(String dOBC) {
this.dOBC = dOBC;
}

@JsonProperty("PAN__c")
public String getPANC() {
return pANC;
}

@JsonProperty("PAN__c")
public void setPANC(String pANC) {
this.pANC = pANC;
}

@JsonProperty("Voterid__c")
public Object getVoteridC() {
return voteridC;
}

@JsonProperty("Voterid__c")
public void setVoteridC(Object voteridC) {
this.voteridC = voteridC;
}

@JsonProperty("Cin__c")
public Object getCinC() {
return cinC;
}

@JsonProperty("Cin__c")
public void setCinC(Object cinC) {
this.cinC = cinC;
}

@JsonProperty("Din__c")
public Object getDinC() {
return dinC;
}

@JsonProperty("Din__c")
public void setDinC(Object dinC) {
this.dinC = dinC;
}

@JsonProperty("AadhaarNo")
public Object getAadhaarNo() {
return aadhaarNo;
}

@JsonProperty("AadhaarNo")
public void setAadhaarNo(Object aadhaarNo) {
this.aadhaarNo = aadhaarNo;
}

@JsonProperty("Gender_Flag")
public Object getGenderFlag() {
return genderFlag;
}

@JsonProperty("Gender_Flag")
public void setGenderFlag(Object genderFlag) {
this.genderFlag = genderFlag;
}

@JsonProperty("Marital_Status_Flag")
public Object getMaritalStatusFlag() {
return maritalStatusFlag;
}

@JsonProperty("Marital_Status_Flag")
public void setMaritalStatusFlag(Object maritalStatusFlag) {
this.maritalStatusFlag = maritalStatusFlag;
}

@JsonProperty("Source_System")
public String getSourceSystem() {
return sourceSystem;
}

@JsonProperty("Source_System")
public void setSourceSystem(String sourceSystem) {
this.sourceSystem = sourceSystem;
}

@JsonProperty("creationDate")
public String getCreationDate() {
return creationDate;
}

@JsonProperty("creationDate")
public void setCreationDate(String creationDate) {
this.creationDate = creationDate;
}

@JsonProperty("product")
public Object getProduct() {
return product;
}

@JsonProperty("product")
public void setProduct(Object product) {
this.product = product;
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
	return "CustDGDetail [customerIDC=" + customerIDC + ", customerTypeC=" + customerTypeC + ", name=" + name
			+ ", fathersHusbandSNameC=" + fathersHusbandSNameC + ", dOBC=" + dOBC + ", pANC=" + pANC + ", voteridC="
			+ voteridC + ", cinC=" + cinC + ", dinC=" + dinC + ", aadhaarNo=" + aadhaarNo + ", genderFlag=" + genderFlag
			+ ", maritalStatusFlag=" + maritalStatusFlag + ", sourceSystem=" + sourceSystem + ", creationDate="
			+ creationDate + ", product=" + product + ", additionalProperties=" + additionalProperties + "]";
}



}