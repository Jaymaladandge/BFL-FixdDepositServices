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
"phoneType",
"stdCode",
"phoneNumber"
})
public class CustContactDetail {

@JsonProperty("phoneType")
private String phoneType;
@JsonProperty("stdCode")
private String stdCode;
@JsonProperty("phoneNumber")
private String phoneNumber;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<>();

@JsonProperty("phoneType")
public String getPhoneType() {
return phoneType;
}

@JsonProperty("phoneType")
public void setPhoneType(String phoneType) {
this.phoneType = phoneType;
}

@JsonProperty("stdCode")
public String getStdCode() {
return stdCode;
}

@JsonProperty("stdCode")
public void setStdCode(String stdCode) {
this.stdCode = stdCode;
}

@JsonProperty("phoneNumber")
public String getPhoneNumber() {
return phoneNumber;
}

@JsonProperty("phoneNumber")
public void setPhoneNumber(String phoneNumber) {
this.phoneNumber = phoneNumber;
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
	return "CustContactDetail [phoneType=" + phoneType + ", stdCode=" + stdCode + ", phoneNumber=" + phoneNumber
			+ ", additionalProperties=" + additionalProperties + "]";
}



}