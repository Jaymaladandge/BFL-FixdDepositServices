
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
    "AppFormNo",
    "DepositCategory",
    "CustomerType",
    "CustomerSubtype",
    "SourceBy",
    "Channel",
    "SourcingChannel",
    "DepositPayableTo",
    "Branch",
    "FDRPhysicalRequired",
    "Broker",
    "Subbroker",
    "ExistingLAN",
    "ExistingFDRNo",
    "ExistingCustID",
    "FinnoneCustomerID",
    "DepositOption",
    "InterestPayoutFreq",
    "Scheme",
    "TotalFDAmount",
    "FDAmount",
    "TenorDays",
    "TenorMonth",
    "Interest",
    "StartDateAndTime",
    "EndDateAndTime",
    "LoanType",
    "RdplLan",
    "UTMSOURCE",
    "isComplete",
    "RenewOption",
    "OldFDFinnoneDepositid",
    "UTMMEDIUM",
    "UTMCAMAPAIGN",
    "UTMKEYWORD",
    "UTMCONTENT",
    "PAGEURL",
    "DEVICE",
    "LASTCLICK",
    "CLIENTID",
    "PrefTimeToContact",
    "Remarks",
    "ProductOfferingSrc",
    "FDRecIdToUpdate",
    "PhoneNo",
    "FullName",
    "Email",
    "City",
    "SpecialCategory",
    "TaxResDeclrtn",
    "TaxResDeclrtnAccount",
    "TCAgrmntDeclrtn",
    "NoOfInstallment",
    "InstallmentDueDay",
    "IsSDP",
    "AutoRenew",
    "gcl_ld",
    "IsRDP",
    "AppGeneration"
})
public class ObjFDDetails {

    @JsonProperty("AppFormNo")
    private String appFormNo;
    @JsonProperty("DepositCategory")
    private String depositCategory;
    @JsonProperty("CustomerType")
    private String customerType;
    @JsonProperty("CustomerSubtype")
    private String customerSubtype;
    @JsonProperty("SourceBy")
    private String sourceBy;
    @JsonProperty("Channel")
    private String channel;
    @JsonProperty("SourcingChannel")
    private String sourcingChannel;
    @JsonProperty("DepositPayableTo")
    private String depositPayableTo;
    @JsonProperty("Branch")
    private String branch;
    @JsonProperty("FDRPhysicalRequired")
    private String fDRPhysicalRequired;
    @JsonProperty("Broker")
    private String broker;
    @JsonProperty("Subbroker")
    private String subbroker;
    @JsonProperty("ExistingLAN")
    private String existingLAN;
    @JsonProperty("ExistingFDRNo")
    private String existingFDRNo;
    @JsonProperty("ExistingCustID")
    private String existingCustID;
    @JsonProperty("FinnoneCustomerID")
    private String finnoneCustomerID;
    @JsonProperty("DepositOption")
    private String depositOption;
    @JsonProperty("InterestPayoutFreq")
    private String interestPayoutFreq;
    @JsonProperty("Scheme")
    private String scheme;
    @JsonProperty("TotalFDAmount")
    private String totalFDAmount;
    @JsonProperty("FDAmount")
    private String fDAmount;
    @JsonProperty("TenorDays")
    private String tenorDays;
    @JsonProperty("TenorMonth")
    private String tenorMonth;
    @JsonProperty("Interest")
    private String interest;
    @JsonProperty("StartDateAndTime")
    private String startDateAndTime;
    @JsonProperty("EndDateAndTime")
    private String endDateAndTime;
    @JsonProperty("LoanType")
    private String loanType;
    @JsonProperty("RdplLan")
    private String rdplLan;
    @JsonProperty("UTMSOURCE")
    private String uTMSOURCE;
    @JsonProperty("isComplete")
    private String isComplete;
    @JsonProperty("RenewOption")
    private String renewOption;
    @JsonProperty("OldFDFinnoneDepositid")
    private String oldFDFinnoneDepositid;
    @JsonProperty("UTMMEDIUM")
    private String uTMMEDIUM;
    @JsonProperty("UTMCAMAPAIGN")
    private String uTMCAMAPAIGN;
    @JsonProperty("UTMKEYWORD")
    private String uTMKEYWORD;
    @JsonProperty("UTMCONTENT")
    private String uTMcontent;
    @JsonProperty("PAGEURL")
    private String pAGEURL;
    @JsonProperty("DEVICE")
    private String dEVICE;
    @JsonProperty("LASTCLICK")
    private String lASTCLICK;
    @JsonProperty("CLIENTID")
    private String cLIENTID;
    @JsonProperty("PrefTimeToContact")
    private String prefTimeToContact;
    @JsonProperty("Remarks")
    private String remarks;
    @JsonProperty("ProductOfferingSrc")
    private String productOfferingSrc;
    @JsonProperty("FDRecIdToUpdate")
    private String fDRecIdToUpdate;
    @JsonProperty("PhoneNo")
    private String phoneNo;
    @JsonProperty("FullName")
    private String fullName;
    @JsonProperty("Email")
    private String email;
    @JsonProperty("City")
    private String city;
    @JsonProperty("SpecialCategory")
    private String specialCategory;
    @JsonProperty("TaxResDeclrtn")
    private String taxResDeclrtn;
    @JsonProperty("TaxResDeclrtnAccount")
    private String taxResDeclrtnAccount;
    @JsonProperty("TCAgrmntDeclrtn")
    private String tCAgrmntDeclrtn;
    @JsonProperty("NoOfInstallment")
    private String noOfInstallment;
    @JsonProperty("InstallmentDueDay")
    private String installmentDueDay;
    @JsonProperty("IsSDP")
    private String isSDP;
    @JsonProperty("AutoRenew")
    private String autoRenew;
    @JsonProperty("gcl_ld")
    private String gclld;
    @JsonProperty("IsRDP")
    private String isRDP;
    @JsonProperty("AppGeneration")
    private String appGeneration;
    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("AppFormNo")
    public String getAppFormNo() {
        return appFormNo;
    }

    @JsonProperty("AppFormNo")
    public void setAppFormNo(String appFormNo) {
        this.appFormNo = appFormNo;
    }

    @JsonProperty("DepositCategory")
    public String getDepositCategory() {
        return depositCategory;
    }

    @JsonProperty("DepositCategory")
    public void setDepositCategory(String depositCategory) {
        this.depositCategory = depositCategory;
    }

    @JsonProperty("CustomerType")
    public String getCustomerType() {
        return customerType;
    }

    @JsonProperty("CustomerType")
    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    @JsonProperty("CustomerSubtype")
    public String getCustomerSubtype() {
        return customerSubtype;
    }

    @JsonProperty("CustomerSubtype")
    public void setCustomerSubtype(String customerSubtype) {
        this.customerSubtype = customerSubtype;
    }

    @JsonProperty("SourceBy")
    public String getSourceBy() {
        return sourceBy;
    }

    @JsonProperty("SourceBy")
    public void setSourceBy(String sourceBy) {
        this.sourceBy = sourceBy;
    }

    @JsonProperty("Channel")
    public String getChannel() {
        return channel;
    }

    @JsonProperty("Channel")
    public void setChannel(String channel) {
        this.channel = channel;
    }

    @JsonProperty("SourcingChannel")
    public String getSourcingChannel() {
        return sourcingChannel;
    }

    @JsonProperty("SourcingChannel")
    public void setSourcingChannel(String sourcingChannel) {
        this.sourcingChannel = sourcingChannel;
    }

    @JsonProperty("DepositPayableTo")
    public String getDepositPayableTo() {
        return depositPayableTo;
    }

    @JsonProperty("DepositPayableTo")
    public void setDepositPayableTo(String depositPayableTo) {
        this.depositPayableTo = depositPayableTo;
    }

    @JsonProperty("Branch")
    public String getBranch() {
        return branch;
    }

    @JsonProperty("Branch")
    public void setBranch(String branch) {
        this.branch = branch;
    }

    @JsonProperty("FDRPhysicalRequired")
    public String getFDRPhysicalRequired() {
        return fDRPhysicalRequired;
    }

    @JsonProperty("FDRPhysicalRequired")
    public void setFDRPhysicalRequired(String fDRPhysicalRequired) {
        this.fDRPhysicalRequired = fDRPhysicalRequired;
    }

    @JsonProperty("Broker")
    public String getBroker() {
        return broker;
    }

    @JsonProperty("Broker")
    public void setBroker(String broker) {
        this.broker = broker;
    }

    @JsonProperty("Subbroker")
    public String getSubbroker() {
        return subbroker;
    }

    @JsonProperty("Subbroker")
    public void setSubbroker(String subbroker) {
        this.subbroker = subbroker;
    }

    @JsonProperty("ExistingLAN")
    public String getExistingLAN() {
        return existingLAN;
    }

    @JsonProperty("ExistingLAN")
    public void setExistingLAN(String existingLAN) {
        this.existingLAN = existingLAN;
    }

    @JsonProperty("ExistingFDRNo")
    public String getExistingFDRNo() {
        return existingFDRNo;
    }

    @JsonProperty("ExistingFDRNo")
    public void setExistingFDRNo(String existingFDRNo) {
        this.existingFDRNo = existingFDRNo;
    }

    @JsonProperty("ExistingCustID")
    public String getExistingCustID() {
        return existingCustID;
    }

    @JsonProperty("ExistingCustID")
    public void setExistingCustID(String existingCustID) {
        this.existingCustID = existingCustID;
    }

    @JsonProperty("FinnoneCustomerID")
    public String getFinnoneCustomerID() {
        return finnoneCustomerID;
    }

    @JsonProperty("FinnoneCustomerID")
    public void setFinnoneCustomerID(String finnoneCustomerID) {
        this.finnoneCustomerID = finnoneCustomerID;
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

    @JsonProperty("TotalFDAmount")
    public String getTotalFDAmount() {
        return totalFDAmount;
    }

    @JsonProperty("TotalFDAmount")
    public void setTotalFDAmount(String totalFDAmount) {
        this.totalFDAmount = totalFDAmount;
    }

    @JsonProperty("FDAmount")
    public String getFDAmount() {
        return fDAmount;
    }

    @JsonProperty("FDAmount")
    public void setFDAmount(String fDAmount) {
        this.fDAmount = fDAmount;
    }

    @JsonProperty("TenorDays")
    public String getTenorDays() {
        return tenorDays;
    }

    @JsonProperty("TenorDays")
    public void setTenorDays(String tenorDays) {
        this.tenorDays = tenorDays;
    }

    @JsonProperty("TenorMonth")
    public String getTenorMonth() {
        return tenorMonth;
    }

    @JsonProperty("TenorMonth")
    public void setTenorMonth(String tenorMonth) {
        this.tenorMonth = tenorMonth;
    }

    @JsonProperty("Interest")
    public String getInterest() {
        return interest;
    }

    @JsonProperty("Interest")
    public void setInterest(String interest) {
        this.interest = interest;
    }

    @JsonProperty("StartDateAndTime")
    public String getStartDateAndTime() {
        return startDateAndTime;
    }

    @JsonProperty("StartDateAndTime")
    public void setStartDateAndTime(String startDateAndTime) {
        this.startDateAndTime = startDateAndTime;
    }

    @JsonProperty("EndDateAndTime")
    public String getEndDateAndTime() {
        return endDateAndTime;
    }

    @JsonProperty("EndDateAndTime")
    public void setEndDateAndTime(String endDateAndTime) {
        this.endDateAndTime = endDateAndTime;
    }

    @JsonProperty("LoanType")
    public String getLoanType() {
        return loanType;
    }

    @JsonProperty("LoanType")
    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    @JsonProperty("RdplLan")
    public String getRdplLan() {
        return rdplLan;
    }

    @JsonProperty("RdplLan")
    public void setRdplLan(String rdplLan) {
        this.rdplLan = rdplLan;
    }

    @JsonProperty("UTMSOURCE")
    public String getUTMSOURCE() {
        return uTMSOURCE;
    }

    @JsonProperty("UTMSOURCE")
    public void setUTMSOURCE(String uTMSOURCE) {
        this.uTMSOURCE = uTMSOURCE;
    }

    @JsonProperty("isComplete")
    public String getIsComplete() {
        return isComplete;
    }

    @JsonProperty("isComplete")
    public void setIsComplete(String isComplete) {
        this.isComplete = isComplete;
    }

    @JsonProperty("RenewOption")
    public String getRenewOption() {
        return renewOption;
    }

    @JsonProperty("RenewOption")
    public void setRenewOption(String renewOption) {
        this.renewOption = renewOption;
    }

    @JsonProperty("OldFDFinnoneDepositid")
    public String getOldFDFinnoneDepositid() {
        return oldFDFinnoneDepositid;
    }

    @JsonProperty("OldFDFinnoneDepositid")
    public void setOldFDFinnoneDepositid(String oldFDFinnoneDepositid) {
        this.oldFDFinnoneDepositid = oldFDFinnoneDepositid;
    }

    @JsonProperty("UTMMEDIUM")
    public String getUTMMEDIUM() {
        return uTMMEDIUM;
    }

    @JsonProperty("UTMMEDIUM")
    public void setUTMMEDIUM(String uTMMEDIUM) {
        this.uTMMEDIUM = uTMMEDIUM;
    }

    @JsonProperty("UTMCAMAPAIGN")
    public String getUTMCAMAPAIGN() {
        return uTMCAMAPAIGN;
    }

    @JsonProperty("UTMCAMAPAIGN")
    public void setUTMCAMAPAIGN(String uTMCAMAPAIGN) {
        this.uTMCAMAPAIGN = uTMCAMAPAIGN;
    }

    @JsonProperty("UTMKEYWORD")
    public String getUTMKEYWORD() {
        return uTMKEYWORD;
    }

    @JsonProperty("UTMKEYWORD")
    public void setUTMKEYWORD(String uTMKEYWORD) {
        this.uTMKEYWORD = uTMKEYWORD;
    }

    @JsonProperty("UTMCONTENT")
    public String getUTMcontent() {
        return uTMcontent;
    }

    @JsonProperty("UTMCONTENT")
    public void setUTMcontent(String uTMcontent) {
        this.uTMcontent = uTMcontent;
    }

    @JsonProperty("PAGEURL")
    public String getPAGEURL() {
        return pAGEURL;
    }

    @JsonProperty("PAGEURL")
    public void setPAGEURL(String pAGEURL) {
        this.pAGEURL = pAGEURL;
    }

    @JsonProperty("DEVICE")
    public String getDEVICE() {
        return dEVICE;
    }

    @JsonProperty("DEVICE")
    public void setDEVICE(String dEVICE) {
        this.dEVICE = dEVICE;
    }

    @JsonProperty("LASTCLICK")
    public String getLASTCLICK() {
        return lASTCLICK;
    }

    @JsonProperty("LASTCLICK")
    public void setLASTCLICK(String lASTCLICK) {
        this.lASTCLICK = lASTCLICK;
    }

    @JsonProperty("CLIENTID")
    public String getCLIENTID() {
        return cLIENTID;
    }

    @JsonProperty("CLIENTID")
    public void setCLIENTID(String cLIENTID) {
        this.cLIENTID = cLIENTID;
    }

    @JsonProperty("PrefTimeToContact")
    public String getPrefTimeToContact() {
        return prefTimeToContact;
    }

    @JsonProperty("PrefTimeToContact")
    public void setPrefTimeToContact(String prefTimeToContact) {
        this.prefTimeToContact = prefTimeToContact;
    }

    @JsonProperty("Remarks")
    public String getRemarks() {
        return remarks;
    }

    @JsonProperty("Remarks")
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @JsonProperty("ProductOfferingSrc")
    public String getProductOfferingSrc() {
        return productOfferingSrc;
    }

    @JsonProperty("ProductOfferingSrc")
    public void setProductOfferingSrc(String productOfferingSrc) {
        this.productOfferingSrc = productOfferingSrc;
    }

    @JsonProperty("FDRecIdToUpdate")
    public String getFDRecIdToUpdate() {
        return fDRecIdToUpdate;
    }

    @JsonProperty("FDRecIdToUpdate")
    public void setFDRecIdToUpdate(String fDRecIdToUpdate) {
        this.fDRecIdToUpdate = fDRecIdToUpdate;
    }

    @JsonProperty("PhoneNo")
    public String getPhoneNo() {
        return phoneNo;
    }

    @JsonProperty("PhoneNo")
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @JsonProperty("FullName")
    public String getFullName() {
        return fullName;
    }

    @JsonProperty("FullName")
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @JsonProperty("Email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("Email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("City")
    public String getCity() {
        return city;
    }

    @JsonProperty("City")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("SpecialCategory")
    public String getSpecialCategory() {
        return specialCategory;
    }

    @JsonProperty("SpecialCategory")
    public void setSpecialCategory(String specialCategory) {
        this.specialCategory = specialCategory;
    }

    @JsonProperty("TaxResDeclrtn")
    public String getTaxResDeclrtn() {
        return taxResDeclrtn;
    }

    @JsonProperty("TaxResDeclrtn")
    public void setTaxResDeclrtn(String taxResDeclrtn) {
        this.taxResDeclrtn = taxResDeclrtn;
    }

    @JsonProperty("TaxResDeclrtnAccount")
    public String getTaxResDeclrtnAccount() {
        return taxResDeclrtnAccount;
    }

    @JsonProperty("TaxResDeclrtnAccount")
    public void setTaxResDeclrtnAccount(String taxResDeclrtnAccount) {
        this.taxResDeclrtnAccount = taxResDeclrtnAccount;
    }

    @JsonProperty("TCAgrmntDeclrtn")
    public String getTCAgrmntDeclrtn() {
        return tCAgrmntDeclrtn;
    }

    @JsonProperty("TCAgrmntDeclrtn")
    public void setTCAgrmntDeclrtn(String tCAgrmntDeclrtn) {
        this.tCAgrmntDeclrtn = tCAgrmntDeclrtn;
    }

    @JsonProperty("NoOfInstallment")
    public String getNoOfInstallment() {
        return noOfInstallment;
    }

    @JsonProperty("NoOfInstallment")
    public void setNoOfInstallment(String noOfInstallment) {
        this.noOfInstallment = noOfInstallment;
    }

    @JsonProperty("InstallmentDueDay")
    public String getInstallmentDueDay() {
        return installmentDueDay;
    }

    @JsonProperty("InstallmentDueDay")
    public void setInstallmentDueDay(String installmentDueDay) {
        this.installmentDueDay = installmentDueDay;
    }

    @JsonProperty("IsSDP")
    public String getIsSDP() {
        return isSDP;
    }

    @JsonProperty("IsSDP")
    public void setIsSDP(String isSDP) {
        this.isSDP = isSDP;
    }

    public String getAutoRenew() {
		return autoRenew;
	}

	public void setAutoRenew(String autoRenew) {
		this.autoRenew = autoRenew;
	}

	@JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @JsonProperty("gcl_ld")
    public String getGclld() {
		return gclld;
	}

    @JsonProperty("gcl_ld")
    public void setGclld(String gclld) {
		this.gclld = gclld;
	}

    public String getIsRDP() {
		return isRDP;
	}

	public void setIsRDP(String isRDP) {
		this.isRDP = isRDP;
	}
	
	@JsonProperty("AppGeneration")
	public String getAppGeneration() {
	return appGeneration;
	}

	@JsonProperty("AppGeneration")
	public void setAppGeneration(String appGeneration) {
	this.appGeneration = appGeneration;  
	}
	

	@Override
	public String toString() {
		return "ObjFDDetails [appFormNo=" + appFormNo + ", depositCategory=" + depositCategory + ", customerType="
				+ customerType + ", customerSubtype=" + customerSubtype + ", sourceBy=" + sourceBy + ", channel="
				+ channel + ", sourcingChannel=" + sourcingChannel + ", depositPayableTo=" + depositPayableTo
				+ ", branch=" + branch + ", fDRPhysicalRequired=" + fDRPhysicalRequired + ", broker=" + broker
				+ ", subbroker=" + subbroker + ", existingLAN=" + existingLAN + ", existingFDRNo=" + existingFDRNo
				+ ", existingCustID=" + existingCustID + ", finnoneCustomerID=" + finnoneCustomerID + ", depositOption="
				+ depositOption + ", interestPayoutFreq=" + interestPayoutFreq + ", scheme=" + scheme
				+ ", totalFDAmount=" + totalFDAmount + ", fDAmount=" + fDAmount + ", tenorDays=" + tenorDays
				+ ", tenorMonth=" + tenorMonth + ", interest=" + interest + ", startDateAndTime=" + startDateAndTime
				+ ", endDateAndTime=" + endDateAndTime + ", loanType=" + loanType + ", rdplLan=" + rdplLan
				+ ", uTMSOURCE=" + uTMSOURCE + ", isComplete=" + isComplete + ", renewOption=" + renewOption
				+ ", oldFDFinnoneDepositid=" + oldFDFinnoneDepositid + ", uTMMEDIUM=" + uTMMEDIUM + ", uTMCAMAPAIGN="
				+ uTMCAMAPAIGN + ", uTMKEYWORD=" + uTMKEYWORD + ", uTMcontent=" + uTMcontent + ", pAGEURL=" + pAGEURL
				+ ", dEVICE=" + dEVICE + ", lASTCLICK=" + lASTCLICK + ", cLIENTID=" + cLIENTID + ", prefTimeToContact="
				+ prefTimeToContact + ", remarks=" + remarks + ", productOfferingSrc=" + productOfferingSrc
				+ ", fDRecIdToUpdate=" + fDRecIdToUpdate + ", phoneNo=" + phoneNo + ", fullName=" + fullName
				+ ", email=" + email + ", city=" + city + ", specialCategory=" + specialCategory + ", taxResDeclrtn="
				+ taxResDeclrtn + ", taxResDeclrtnAccount=" + taxResDeclrtnAccount + ", tCAgrmntDeclrtn="
				+ tCAgrmntDeclrtn + ", noOfInstallment=" + noOfInstallment + ", installmentDueDay=" + installmentDueDay
				+ ", isSDP=" + isSDP + ", autoRenew=" + autoRenew + ", gclld=" + gclld + ", isRDP=" + isRDP
				+ ", additionalProperties=" + additionalProperties + ", appGeneration=" + appGeneration + "]";
	}
	
    
    

}
