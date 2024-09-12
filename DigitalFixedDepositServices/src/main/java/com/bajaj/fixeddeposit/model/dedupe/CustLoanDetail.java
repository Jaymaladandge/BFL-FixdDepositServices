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
"segment",
"applicationNo",
"product"
})
public class CustLoanDetail {

@JsonProperty("segment")
private String segment;
@JsonProperty("applicationNo")
private String applicationNo;
@JsonProperty("product")
private String product;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<>();

@JsonProperty("segment")
public String getSegment() {
return segment;
}

@JsonProperty("segment")
public void setSegment(String segment) {
this.segment = segment;
}

@JsonProperty("applicationNo")
public String getApplicationNo() {
return applicationNo;
}

@JsonProperty("applicationNo")
public void setApplicationNo(String applicationNo) {
this.applicationNo = applicationNo;
}

@JsonProperty("product")
public String getProduct() {
return product;
}

@JsonProperty("product")
public void setProduct(String product) {
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
	return "CustLoanDetail [segment=" + segment + ", applicationNo=" + applicationNo + ", product=" + product
			+ ", additionalProperties=" + additionalProperties + "]";
}



}