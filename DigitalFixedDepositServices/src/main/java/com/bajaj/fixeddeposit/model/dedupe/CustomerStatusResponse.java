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
"dealId",
"areaStatus",
"customerStatus",
"customerID",
"applicationID",
"dedupeLANMatches",
"applicationNumberDisburse",
"elcFlag",
"elcLimit",
"rejectLANMatches",
"fraudLANMatches",
"cobrandLimit",
"cobrandAvailLimit",
"cobrandCount",
"wipLANMatches",
"rblLanMatches",
"amountPaid",
"depositID",
"approvedLANMatches",
"negativeLANMatches",
"customerIdList"
})
public class CustomerStatusResponse {

@JsonProperty("dealId")
private String dealId;
@JsonProperty("areaStatus")
private String areaStatus;
@JsonProperty("customerStatus")
private String customerStatus;
@JsonProperty("customerID")
private String customerID;
@JsonProperty("applicationID")
private String applicationID;
@JsonProperty("dedupeLANMatches")
private String dedupeLANMatches;
@JsonProperty("applicationNumberDisburse")
private String applicationNumberDisburse;
@JsonProperty("elcFlag")
private String elcFlag;
@JsonProperty("elcLimit")
private Integer elcLimit;
@JsonProperty("rejectLANMatches")
private String rejectLANMatches;
@JsonProperty("fraudLANMatches")
private String fraudLANMatches;
@JsonProperty("cobrandLimit")
private Integer cobrandLimit;
@JsonProperty("cobrandAvailLimit")
private Integer cobrandAvailLimit;
@JsonProperty("cobrandCount")
private Integer cobrandCount;
@JsonProperty("wipLANMatches")
private String wipLANMatches;
@JsonProperty("rblLanMatches")
private String rblLanMatches;
@JsonProperty("amountPaid")
private String amountPaid;
@JsonProperty("depositID")
private String depositID;
@JsonProperty("approvedLANMatches")
private String approvedLANMatches;
@JsonProperty("negativeLANMatches")
private String negativeLANMatches;
@JsonProperty("customerIdList")
private String customerIdList;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<>();

@JsonProperty("dealId")
public String getDealId() {
return dealId;
}

@JsonProperty("dealId")
public void setDealId(String dealId) {
this.dealId = dealId;
}

@JsonProperty("areaStatus")
public String getAreaStatus() {
return areaStatus;
}

@JsonProperty("areaStatus")
public void setAreaStatus(String areaStatus) {
this.areaStatus = areaStatus;
}

@JsonProperty("customerStatus")
public String getCustomerStatus() {
return customerStatus;
}

@JsonProperty("customerStatus")
public void setCustomerStatus(String customerStatus) {
this.customerStatus = customerStatus;
}

@JsonProperty("customerID")
public String getCustomerID() {
return customerID;
}

@JsonProperty("customerID")
public void setCustomerID(String customerID) {
this.customerID = customerID;
}

@JsonProperty("applicationID")
public String getApplicationID() {
return applicationID;
}

@JsonProperty("applicationID")
public void setApplicationID(String applicationID) {
this.applicationID = applicationID;
}

@JsonProperty("dedupeLANMatches")
public String getDedupeLANMatches() {
return dedupeLANMatches;
}

@JsonProperty("dedupeLANMatches")
public void setDedupeLANMatches(String dedupeLANMatches) {
this.dedupeLANMatches = dedupeLANMatches;
}

@JsonProperty("applicationNumberDisburse")
public String getApplicationNumberDisburse() {
return applicationNumberDisburse;
}

@JsonProperty("applicationNumberDisburse")
public void setApplicationNumberDisburse(String applicationNumberDisburse) {
this.applicationNumberDisburse = applicationNumberDisburse;
}

@JsonProperty("elcFlag")
public String getElcFlag() {
return elcFlag;
}

@JsonProperty("elcFlag")
public void setElcFlag(String elcFlag) {
this.elcFlag = elcFlag;
}

@JsonProperty("elcLimit")
public Integer getElcLimit() {
return elcLimit;
}

@JsonProperty("elcLimit")
public void setElcLimit(Integer elcLimit) {
this.elcLimit = elcLimit;
}

@JsonProperty("rejectLANMatches")
public String getRejectLANMatches() {
return rejectLANMatches;
}

@JsonProperty("rejectLANMatches")
public void setRejectLANMatches(String rejectLANMatches) {
this.rejectLANMatches = rejectLANMatches;
}

@JsonProperty("fraudLANMatches")
public String getFraudLANMatches() {
return fraudLANMatches;
}

@JsonProperty("fraudLANMatches")
public void setFraudLANMatches(String fraudLANMatches) {
this.fraudLANMatches = fraudLANMatches;
}

@JsonProperty("cobrandLimit")
public Integer getCobrandLimit() {
return cobrandLimit;
}

@JsonProperty("cobrandLimit")
public void setCobrandLimit(Integer cobrandLimit) {
this.cobrandLimit = cobrandLimit;
}

@JsonProperty("cobrandAvailLimit")
public Integer getCobrandAvailLimit() {
return cobrandAvailLimit;
}

@JsonProperty("cobrandAvailLimit")
public void setCobrandAvailLimit(Integer cobrandAvailLimit) {
this.cobrandAvailLimit = cobrandAvailLimit;
}

@JsonProperty("cobrandCount")
public Integer getCobrandCount() {
return cobrandCount;
}

@JsonProperty("cobrandCount")
public void setCobrandCount(Integer cobrandCount) {
this.cobrandCount = cobrandCount;
}

@JsonProperty("wipLANMatches")
public String getWipLANMatches() {
return wipLANMatches;
}

@JsonProperty("wipLANMatches")
public void setWipLANMatches(String wipLANMatches) {
this.wipLANMatches = wipLANMatches;
}

@JsonProperty("rblLanMatches")
public String getRblLanMatches() {
return rblLanMatches;
}

@JsonProperty("rblLanMatches")
public void setRblLanMatches(String rblLanMatches) {
this.rblLanMatches = rblLanMatches;
}

@JsonProperty("amountPaid")
public String getAmountPaid() {
return amountPaid;
}

@JsonProperty("amountPaid")
public void setAmountPaid(String amountPaid) {
this.amountPaid = amountPaid;
}

@JsonProperty("depositID")
public String getDepositID() {
return depositID;
}

@JsonProperty("depositID")
public void setDepositID(String depositID) {
this.depositID = depositID;
}

@JsonProperty("approvedLANMatches")
public String getApprovedLANMatches() {
return approvedLANMatches;
}

@JsonProperty("approvedLANMatches")
public void setApprovedLANMatches(String approvedLANMatches) {
this.approvedLANMatches = approvedLANMatches;
}

@JsonProperty("negativeLANMatches")
public String getNegativeLANMatches() {
return negativeLANMatches;
}

@JsonProperty("negativeLANMatches")
public void setNegativeLANMatches(String negativeLANMatches) {
this.negativeLANMatches = negativeLANMatches;
}

@JsonProperty("customerIdList")
public String getCustomerIdList() {
return customerIdList;
}

@JsonProperty("customerIdList")
public void setCustomerIdList(String customerIdList) {
this.customerIdList = customerIdList;
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
	return "CustomerStatusResponse [dealId=" + dealId + ", areaStatus=" + areaStatus + ", customerStatus="
			+ customerStatus + ", customerID=" + customerID + ", applicationID=" + applicationID + ", dedupeLANMatches="
			+ dedupeLANMatches + ", applicationNumberDisburse=" + applicationNumberDisburse + ", elcFlag=" + elcFlag
			+ ", elcLimit=" + elcLimit + ", rejectLANMatches=" + rejectLANMatches + ", fraudLANMatches="
			+ fraudLANMatches + ", cobrandLimit=" + cobrandLimit + ", cobrandAvailLimit=" + cobrandAvailLimit
			+ ", cobrandCount=" + cobrandCount + ", wipLANMatches=" + wipLANMatches + ", rblLanMatches=" + rblLanMatches
			+ ", amountPaid=" + amountPaid + ", depositID=" + depositID + ", approvedLANMatches=" + approvedLANMatches
			+ ", negativeLANMatches=" + negativeLANMatches + ", customerIdList=" + customerIdList
			+ ", additionalProperties=" + additionalProperties + "]";
}


}