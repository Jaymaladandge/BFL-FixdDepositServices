
package com.bajaj.fixeddeposit.model.sfdc;

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
    "AddrType",
    "Address1",
    "Address2",
    "Address3",
    "Address4",
    "City",
    "State",
    "Pincode",
    "Country",
    "PermAndResddressAreSame",
    "AddrProof",
    "PrefferedCommAddress",
    "ResidenceType"
})
public class AdressDetailsList {

    @JsonProperty("AddrType")
    private String addrType;
    @JsonProperty("Address1")
    private String address1;
    @JsonProperty("Address2")
    private String address2;
    @JsonProperty("Address3")
    private String address3;
    @JsonProperty("Address4")
    private String address4;
    @JsonProperty("City")
    private String city;
    @JsonProperty("State")
    private String state;
    @JsonProperty("Pincode")
    private String pincode;
    @JsonProperty("Country")
    private String country;
    @JsonProperty("PermAndResddressAreSame")
    private String permAndResddressAreSame;
    @JsonProperty("AddrProof")
    private String addrProof;
    @JsonProperty("PrefferedCommAddress")
    private String prefferedCommAddress;
    @JsonProperty("ResidenceType")
    private String residenceType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("AddrType")
    public String getAddrType() {
        return addrType;
    }

    @JsonProperty("AddrType")
    public void setAddrType(String addrType) {
        this.addrType = addrType;
    }

    @JsonProperty("Address1")
    public String getAddress1() {
        return address1;
    }

    @JsonProperty("Address1")
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    @JsonProperty("Address2")
    public String getAddress2() {
        return address2;
    }

    @JsonProperty("Address2")
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    @JsonProperty("Address3")
    public String getAddress3() {
        return address3;
    }

    @JsonProperty("Address3")
    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    @JsonProperty("Address4")
    public String getAddress4() {
        return address4;
    }

    @JsonProperty("Address4")
    public void setAddress4(String address4) {
        this.address4 = address4;
    }

    @JsonProperty("City")
    public String getCity() {
        return city;
    }

    @JsonProperty("City")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("State")
    public String getState() {
        return state;
    }

    @JsonProperty("State")
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("Pincode")
    public String getPincode() {
        return pincode;
    }

    @JsonProperty("Pincode")
    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    @JsonProperty("Country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("Country")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("PermAndResddressAreSame")
    public String getPermAndResddressAreSame() {
        return permAndResddressAreSame;
    }

    @JsonProperty("PermAndResddressAreSame")
    public void setPermAndResddressAreSame(String permAndResddressAreSame) {
        this.permAndResddressAreSame = permAndResddressAreSame;
    }

    @JsonProperty("AddrProof")
    public String getAddrProof() {
        return addrProof;
    }

    @JsonProperty("AddrProof")
    public void setAddrProof(String addrProof) {
        this.addrProof = addrProof;
    }

    @JsonProperty("PrefferedCommAddress")
    public String getPrefferedCommAddress() {
        return prefferedCommAddress;
    }

    @JsonProperty("PrefferedCommAddress")
    public void setPrefferedCommAddress(String prefferedCommAddress) {
        this.prefferedCommAddress = prefferedCommAddress;
    }

    @JsonProperty("ResidenceType")
    public String getResidenceType() {
        return residenceType;
    }

    @JsonProperty("ResidenceType")
    public void setResidenceType(String residenceType) {
        this.residenceType = residenceType;
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
		return "AdressDetailsList [addrType=" + addrType + ", address1=" + address1 + ", address2=" + address2
				+ ", address3=" + address3 + ", address4=" + address4 + ", city=" + city + ", state=" + state
				+ ", pincode=" + pincode + ", country=" + country + ", permAndResddressAreSame="
				+ permAndResddressAreSame + ", addrProof=" + addrProof + ", prefferedCommAddress="
				+ prefferedCommAddress + ", residenceType=" + residenceType + ", additionalProperties="
				+ additionalProperties + "]";
	}
    
    

}
