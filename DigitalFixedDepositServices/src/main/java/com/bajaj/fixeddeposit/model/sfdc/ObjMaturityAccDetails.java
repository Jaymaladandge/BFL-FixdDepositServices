
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
    "AccountNo",
    "Bank",
    "AccHolderName",
    "IMPSValidationTImestamp",
    "IMPSResponseDescription",
    "IMPSResponseCode",
    "IMPSBeneficiaryName",
    "IMPSBankName",
    "IMPSTraceNo",
})
public class ObjMaturityAccDetails {

    @JsonProperty("AccountNo")
    private String accountNo;
    @JsonProperty("Bank")
    private String bank;
    @JsonProperty("AccHolderName")
    private String accHolderName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();
    
    @JsonProperty("IMPSValidationTImestamp")
    private String impsValidationTimeStamp;
    
    @JsonProperty("IMPSResponseDescription")
    private String impsResponseDescription;
    
    @JsonProperty("IMPSResponseCode")
    private String impsResponseCode;
    
    @JsonProperty("IMPSBeneficiaryName")
    private String impsBeneficiaryName;
    
    @JsonProperty("IMPSBankName")
    private String impsBankName;
    
    @JsonProperty("IMPSTraceNo")
    private String impsTraceNo;
    
    
    @JsonProperty("AccountNo")
    public String getAccountNo() {
        return accountNo;
    }

    @JsonProperty("AccountNo")
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    @JsonProperty("Bank")
    public String getBank() {
        return bank;
    }

    @JsonProperty("Bank")
    public void setBank(String bank) {
        this.bank = bank;
    }

    @JsonProperty("AccHolderName")
    public String getAccHolderName() {
        return accHolderName;
    }

    @JsonProperty("AccHolderName")
    public void setAccHolderName(String accHolderName) {
        this.accHolderName = accHolderName;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }


    @JsonProperty("IMPSValidationTImestamp")
	public String getImpsValidationTimeStamp() {
		return impsValidationTimeStamp;
	}
    @JsonProperty("IMPSValidationTImestamp")
	public void setImpsValidationTimeStamp(String impsValidationTimeStamp) {
		this.impsValidationTimeStamp = impsValidationTimeStamp;
	}
    @JsonProperty("IMPSResponseDescription")
	public String getImpsResponseDescription() {
		return impsResponseDescription;
	}
    @JsonProperty("IMPSResponseDescription")
	public void setImpsResponseDescription(String impsResponseDescription) {
		this.impsResponseDescription = impsResponseDescription;
	}
    @JsonProperty("IMPSResponseCode")
	public String getImpsResponseCode() {
		return impsResponseCode;
	}
    @JsonProperty("IMPSResponseCode")
	public void setImpsResponseCode(String impsResponseCode) {
		this.impsResponseCode = impsResponseCode;
	}
    @JsonProperty("IMPSBeneficiaryName")
	public String getImpsBeneficiaryName() {
		return impsBeneficiaryName;
	}
    @JsonProperty("IMPSBeneficiaryName")
	public void setImpsBeneficiaryName(String impsBeneficiaryName) {
		this.impsBeneficiaryName = impsBeneficiaryName;
	}
    @JsonProperty("IMPSBankName")
	public String getImpsBankName() {
		return impsBankName;
	}
    @JsonProperty("IMPSBankName")
	public void setImpsBankName(String impsBankName) {
		this.impsBankName = impsBankName;
	}
    @JsonProperty("IMPSTraceNo")
	public String getImpsTraceNo() {
		return impsTraceNo;
	}
    @JsonProperty("IMPSTraceNo")
	public void setImpsTraceNo(String impsTraceNo) {
		this.impsTraceNo = impsTraceNo;
	}

	@Override
	public String toString() {
		return "ObjMaturityAccDetails [accountNo=" + accountNo + ", bank=" + bank + ", accHolderName=" + accHolderName
				+ ", additionalProperties=" + additionalProperties + ", impsValidationTimeStamp="
				+ impsValidationTimeStamp + ", impsResponseDescription=" + impsResponseDescription
				+ ", impsResponseCode=" + impsResponseCode + ", impsBeneficiaryName=" + impsBeneficiaryName
				+ ", impsBankName=" + impsBankName + ", impsTraceNo=" + impsTraceNo + "]";
	}
    
	
    
    

}
