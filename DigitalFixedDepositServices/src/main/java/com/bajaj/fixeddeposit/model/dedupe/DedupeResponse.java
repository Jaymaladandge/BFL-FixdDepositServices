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
"RESPCODE",
"customerStatusResponse",
"appscore",
"errorDescription",
"demographicDetails"
})
public class DedupeResponse {

@JsonProperty("RESPCODE")
private Object rESPCODE;
@JsonProperty("customerStatusResponse")
private CustomerStatusResponse customerStatusResponse;
@JsonProperty("appscore")
private Object appscore;
@JsonProperty("errorDescription")
private Object errorDescription;
@JsonProperty("demographicDetails")
private List<DemographicDetail> demographicDetails = null;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<>();

@JsonProperty("RESPCODE")
public Object getRESPCODE() {
return rESPCODE;
}

@JsonProperty("RESPCODE")
public void setRESPCODE(Object rESPCODE) {
this.rESPCODE = rESPCODE;
}

@JsonProperty("customerStatusResponse")
public CustomerStatusResponse getCustomerStatusResponse() {
return customerStatusResponse;
}

@JsonProperty("customerStatusResponse")
public void setCustomerStatusResponse(CustomerStatusResponse customerStatusResponse) {
this.customerStatusResponse = customerStatusResponse;
}

@JsonProperty("appscore")
public Object getAppscore() {
return appscore;
}

@JsonProperty("appscore")
public void setAppscore(Object appscore) {
this.appscore = appscore;
}

@JsonProperty("errorDescription")
public Object getErrorDescription() {
return errorDescription;
}

@JsonProperty("errorDescription")
public void setErrorDescription(Object errorDescription) {
this.errorDescription = errorDescription;
}

@JsonProperty("demographicDetails")
public List<DemographicDetail> getDemographicDetails() {
return demographicDetails;
}

@JsonProperty("demographicDetails")
public void setDemographicDetails(List<DemographicDetail> demographicDetails) {
this.demographicDetails = demographicDetails;
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
	return "DedupeResponse [rESPCODE=" + rESPCODE + ", customerStatusResponse=" + customerStatusResponse + ", appscore="
			+ appscore + ", errorDescription=" + errorDescription + ", demographicDetails=" + demographicDetails
			+ ", additionalProperties=" + additionalProperties + "]";
}



}