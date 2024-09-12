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
"addressType",
"address",
"city",
"pin",
"landmark"
})
public class CustAddressDetail {

@JsonProperty("addressType")
private String addressType;
@JsonProperty("address")
private String address;
@JsonProperty("city")
private String city;
@JsonProperty("pin")
private String pin;
@JsonProperty("landmark")
private Object landmark;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<>();

@JsonProperty("addressType")
public String getAddressType() {
return addressType;
}

@JsonProperty("addressType")
public void setAddressType(String addressType) {
this.addressType = addressType;
}

@JsonProperty("address")
public String getAddress() {
return address;
}

@JsonProperty("address")
public void setAddress(String address) {
this.address = address;
}

@JsonProperty("city")
public String getCity() {
return city;
}

@JsonProperty("city")
public void setCity(String city) {
this.city = city;
}

@JsonProperty("pin")
public String getPin() {
return pin;
}

@JsonProperty("pin")
public void setPin(String pin) {
this.pin = pin;
}

@JsonProperty("landmark")
public Object getLandmark() {
return landmark;
}

@JsonProperty("landmark")
public void setLandmark(Object landmark) {
this.landmark = landmark;
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
	return "CustAddressDetail [addressType=" + addressType + ", address=" + address + ", city=" + city + ", pin=" + pin
			+ ", landmark=" + landmark + ", additionalProperties=" + additionalProperties + "]";
}


}