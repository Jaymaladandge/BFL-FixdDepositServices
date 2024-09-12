
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
    "TDSWaiver",
    "PaymentMode",
    "AccHolderName",
    "AccountNo",
    "Bank",
    "ChequeNo",
    "ChequeDate",
    "InvAndMatAccBelongToYou",
    "TaxRate",
    "UTR",
    "FinancialYear",
    "WaiverPeriodEndDt",
    "CheqRecvdAt",
    "DepositBankName",
    "BankGLCode",
    "ECSLimit",
    "ECSStartDate",
    "ECSEndDate",
    "OpenECSFacility",
    "ECSBarcode",
    "BankAccType",
    "MaturityInvestmentAccountAreSame"
})
public class ObjInvestmentAccDetails {

    @JsonProperty("TDSWaiver")
    private String tDSWaiver;
    @JsonProperty("PaymentMode")
    private String paymentMode;
    @JsonProperty("AccHolderName")
    private String accHolderName;
    @JsonProperty("AccountNo")
    private String accountNo;
    @JsonProperty("Bank")
    private String bank;
    @JsonProperty("ChequeNo")
    private String chequeNo;
    @JsonProperty("ChequeDate")
    private String chequeDate;
    @JsonProperty("InvAndMatAccBelongToYou")
    private String invAndMatAccBelongToYou;
    @JsonProperty("TaxRate")
    private String taxRate;
    @JsonProperty("UTR")
    private String uTR;
    @JsonProperty("FinancialYear")
    private String financialYear;
    @JsonProperty("WaiverPeriodEndDt")
    private String waiverPeriodEndDt;
    @JsonProperty("CheqRecvdAt")
    private String cheqRecvdAt;
    @JsonProperty("DepositBankName")
    private String depositBankNm;
    @JsonProperty("BankGLCode")
    private String bankGLCode;
    @JsonProperty("ECSLimit")
    private String eCSLimit;
    @JsonProperty("ECSStartDate")
    private String eCSStartDate;
    @JsonProperty("ECSEndDate")
    private String eCSEndDate;
    @JsonProperty("OpenECSFacility")
    private String openECSFacility;
    @JsonProperty("ECSBarcode")
    private String eCSBarcode;
    @JsonProperty("BankAccType")
    private String bankAccType;
    @JsonProperty("MaturityInvestmentAccountAreSame")
    private String maturityInvestmentAccountAreSame;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();
    
    @JsonProperty("TDSWaiver")
    public String getTDSWaiver() {
        return tDSWaiver;
    }

    @JsonProperty("TDSWaiver")
    public void setTDSWaiver(String tDSWaiver) {
        this.tDSWaiver = tDSWaiver;
    }

    @JsonProperty("PaymentMode")
    public String getPaymentMode() {
        return paymentMode;
    }

    @JsonProperty("PaymentMode")
    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    @JsonProperty("AccHolderName")
    public String getAccHolderName() {
        return accHolderName;
    }

    @JsonProperty("AccHolderName")
    public void setAccHolderName(String accHolderName) {
        this.accHolderName = accHolderName;
    }

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

    @JsonProperty("ChequeNo")
    public String getChequeNo() {
        return chequeNo;
    }

    @JsonProperty("ChequeNo")
    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    @JsonProperty("ChequeDate")
    public String getChequeDate() {
        return chequeDate;
    }

    @JsonProperty("ChequeDate")
    public void setChequeDate(String chequeDate) {
        this.chequeDate = chequeDate;
    }

    @JsonProperty("InvAndMatAccBelongToYou")
    public String getInvAndMatAccBelongToYou() {
        return invAndMatAccBelongToYou;
    }

    @JsonProperty("InvAndMatAccBelongToYou")
    public void setInvAndMatAccBelongToYou(String invAndMatAccBelongToYou) {
        this.invAndMatAccBelongToYou = invAndMatAccBelongToYou;
    }

    @JsonProperty("TaxRate")
    public String getTaxRate() {
        return taxRate;
    }

    @JsonProperty("TaxRate")
    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    @JsonProperty("UTR")
    public String getUTR() {
        return uTR;
    }

    @JsonProperty("UTR")
    public void setUTR(String uTR) {
        this.uTR = uTR;
    }

    @JsonProperty("FinancialYear")
    public String getFinancialYear() {
        return financialYear;
    }

    @JsonProperty("FinancialYear")
    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }

    @JsonProperty("WaiverPeriodEndDt")
    public String getWaiverPeriodEndDt() {
        return waiverPeriodEndDt;
    }

    @JsonProperty("WaiverPeriodEndDt")
    public void setWaiverPeriodEndDt(String waiverPeriodEndDt) {
        this.waiverPeriodEndDt = waiverPeriodEndDt;
    }

    @JsonProperty("CheqRecvdAt")
    public String getCheqRecvdAt() {
        return cheqRecvdAt;
    }

    @JsonProperty("CheqRecvdAt")
    public void setCheqRecvdAt(String cheqRecvdAt) {
        this.cheqRecvdAt = cheqRecvdAt;
    }

    @JsonProperty("DepositBankName")
    public String getDepositBankNm() {
        return depositBankNm;
    }

    @JsonProperty("DepositBankName")
    public void setDepositBankNm(String depositBankNm) {
        this.depositBankNm = depositBankNm;
    }

    @JsonProperty("BankGLCode")
    public String getBankGLCode() {
        return bankGLCode;
    }

    @JsonProperty("BankGLCode")
    public void setBankGLCode(String bankGLCode) {
        this.bankGLCode = bankGLCode;
    }

    @JsonProperty("ECSLimit")
    public String getECSLimit() {
        return eCSLimit;
    }

    @JsonProperty("ECSLimit")
    public void setECSLimit(String eCSLimit) {
        this.eCSLimit = eCSLimit;
    }

    @JsonProperty("ECSStartDate")
    public String getECSStartDate() {
        return eCSStartDate;
    }

    @JsonProperty("ECSStartDate")
    public void setECSStartDate(String eCSStartDate) {
        this.eCSStartDate = eCSStartDate;
    }

    @JsonProperty("ECSEndDate")
    public String getECSEndDate() {
        return eCSEndDate;
    }

    @JsonProperty("ECSEndDate")
    public void setECSEndDate(String eCSEndDate) {
        this.eCSEndDate = eCSEndDate;
    }

    @JsonProperty("OpenECSFacility")
    public String getOpenECSFacility() {
        return openECSFacility;
    }

    @JsonProperty("OpenECSFacility")
    public void setOpenECSFacility(String openECSFacility) {
        this.openECSFacility = openECSFacility;
    }

    @JsonProperty("ECSBarcode")
    public String getECSBarcode() {
        return eCSBarcode;
    }

    @JsonProperty("ECSBarcode")
    public void setECSBarcode(String eCSBarcode) {
        this.eCSBarcode = eCSBarcode;
    }

    public String getBankAccType() {
		return bankAccType;
	}

	public void setBankAccType(String bankAccType) {
		this.bankAccType = bankAccType;
	}

	public String getMaturityInvestmentAccountAreSame() {
		return maturityInvestmentAccountAreSame;
	}

	public void setMaturityInvestmentAccountAreSame(String maturityInvestmentAccountAreSame) {
		this.maturityInvestmentAccountAreSame = maturityInvestmentAccountAreSame;
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
		return "ObjInvestmentAccDetails [tDSWaiver=" + tDSWaiver + ", paymentMode=" + paymentMode + ", accHolderName="
				+ accHolderName + ", accountNo=" + accountNo + ", bank=" + bank + ", chequeNo=" + chequeNo
				+ ", chequeDate=" + chequeDate + ", invAndMatAccBelongToYou=" + invAndMatAccBelongToYou + ", taxRate="
				+ taxRate + ", uTR=" + uTR + ", financialYear=" + financialYear + ", waiverPeriodEndDt="
				+ waiverPeriodEndDt + ", cheqRecvdAt=" + cheqRecvdAt + ", depositBankNm=" + depositBankNm
				+ ", bankGLCode=" + bankGLCode + ", eCSLimit=" + eCSLimit + ", eCSStartDate=" + eCSStartDate
				+ ", eCSEndDate=" + eCSEndDate + ", openECSFacility=" + openECSFacility + ", eCSBarcode=" + eCSBarcode
				+ ", bankAccType=" + bankAccType + ", maturityInvestmentAccountAreSame="
				+ maturityInvestmentAccountAreSame + ", additionalProperties=" + additionalProperties + "]";
				
				
	}
    
	
    
    
}
