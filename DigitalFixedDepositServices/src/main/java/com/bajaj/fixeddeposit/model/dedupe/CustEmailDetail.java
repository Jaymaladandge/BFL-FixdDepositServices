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
"emailType",
"emailId"
})
public class CustEmailDetail {

@JsonProperty("emailType")
private String emailType;
@JsonProperty("emailId")
private String emailId;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<>();

@JsonProperty("emailType")
public String getEmailType() {
return emailType;
}

@JsonProperty("emailType")
public void setEmailType(String emailType) {
this.emailType = emailType;
}

@JsonProperty("emailId")
public String getEmailId() {
return emailId;
}

@JsonProperty("emailId")
public void setEmailId(String emailId) {
this.emailId = emailId;
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
	return "CustEmailDetail [emailType=" + emailType + ", emailId=" + emailId + ", additionalProperties="
			+ additionalProperties + "]";
}



}