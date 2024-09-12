
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
    "SplitNumber",
    "DepositOption",
    "InterestPayoutFreq",
    "Scheme",
    "FDAmount",
    "TenorMonth",
    "TenorDays",
    "Interest"
})
public class SplitFDDetail {

    @JsonProperty("SplitNumber")
    private String splitNumber;
    @JsonProperty("DepositOption")
    private String depositOption;
    @JsonProperty("InterestPayoutFreq")
    private String interestPayoutFreq;
    @JsonProperty("Scheme")
    private String scheme;
    @JsonProperty("FDAmount")
    private String fDAmount;
    @JsonProperty("TenorMonth")
    private String tenorMonth;
    @JsonProperty("TenorDays")
    private String tenorDays;
    @JsonProperty("Interest")
    private String interest;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("SplitNumber")
    public String getSplitNumber() {
        return splitNumber;
    }

    @JsonProperty("SplitNumber")
    public void setSplitNumber(String splitNumber) {
        this.splitNumber = splitNumber;
    }

    @JsonProperty("DepositOption")
    public String getDepositOption() {
        return depositOption;
    }

    @JsonProperty("DepositOption")
    public void setDepositOption(String depositOption) {
        this.depositOption = depositOption;
    }

    @JsonProperty("InterestPayoutFreq")
    public String getInterestPayoutFreq() {
        return interestPayoutFreq;
    }

    @JsonProperty("InterestPayoutFreq")
    public void setInterestPayoutFreq(String interestPayoutFreq) {
        this.interestPayoutFreq = interestPayoutFreq;
    }

    @JsonProperty("Scheme")
    public String getScheme() {
        return scheme;
    }

    @JsonProperty("Scheme")
    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    @JsonProperty("FDAmount")
    public String getFDAmount() {
        return fDAmount;
    }

    @JsonProperty("FDAmount")
    public void setFDAmount(String fDAmount) {
        this.fDAmount = fDAmount;
    }

    @JsonProperty("TenorMonth")
    public String getTenorMonth() {
        return tenorMonth;
    }

    @JsonProperty("TenorMonth")
    public void setTenorMonth(String tenorMonth) {
        this.tenorMonth = tenorMonth;
    }

    @JsonProperty("TenorDays")
    public String getTenorDays() {
        return tenorDays;
    }

    @JsonProperty("TenorDays")
    public void setTenorDays(String tenorDays) {
        this.tenorDays = tenorDays;
    }

    @JsonProperty("Interest")
    public String getInterest() {
        return interest;
    }

    @JsonProperty("Interest")
    public void setInterest(String interest) {
        this.interest = interest;
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
		return "SplitFDDetail [splitNumber=" + splitNumber + ", depositOption=" + depositOption
				+ ", interestPayoutFreq=" + interestPayoutFreq + ", scheme=" + scheme + ", fDAmount=" + fDAmount
				+ ", tenorMonth=" + tenorMonth + ", tenorDays=" + tenorDays + ", interest=" + interest
				+ ", additionalProperties=" + additionalProperties + "]";
	}
    
    

}
