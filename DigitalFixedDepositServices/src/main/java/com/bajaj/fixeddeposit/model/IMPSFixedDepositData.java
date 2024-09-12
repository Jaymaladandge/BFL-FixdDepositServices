package com.bajaj.fixeddeposit.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "IMPS_FIXED_DEPOSIT_DATA")
@SequenceGenerator(name = "impsfixedDepositSeq", sequenceName = "SEQ_IMPS_FIXED_DEPOSIT", allocationSize = 1)
public class IMPSFixedDepositData implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@Column(name = "ID", unique = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "impsfixedDepositSeq")
	private int id;
	
	
	@Column(name = "MOBILENO")
	private String mobileno;

	@Column(name = "BFLCUSTOMERID")
	private String bflcustomerId;
	
	@Column(name = "IMPSMESSAGECODE")
    private String iMPSmessageCode;

    @Column(name = "IMPSDATETIME")
    private String iMPSdateTime;
    
    @Column(name = "IMPSTRNSDATE")
    private String iMPStrnsDate;
    
    @Column(name = "IMPSMERCHANTID")
    private String iMPSmerchantId;
    
    @Column(name = "IMPSTRACENUMBER")
    private String iMPStraceNumber;
    
    @Column(name = "IMPSRESPDESC")
    private String iMPSrespDesc;
    
    @Column(name = "IMPSRRN")
    private String  iMPSrrn;
    
    @Column(name = "IMPSBANKREFNUM")
    private String iMPSbankRefNum;
    
    @Column(name = "IMPSBENENAME")
    private String iMPSbeneName;

    @Column(name = "IMPSCHECKSUM")
	private String iMPSchecksum;
    
    @Column(name = "IMPSTRNBANKNAME")
	private String iMPStrnBankName;
    
	@Column(name = "IMPSCREATEDON")
	private Date iMPSCreatedON;
	
	@Column(name = "CREATED_ON")
    private String createdOn;

	@Column(name="IMPSRESPONSECODE")
	private String impsResponseCode;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getBflcustomerId() {
		return bflcustomerId;
	}

	public void setBflcustomerId(String bflcustomerId) {
		this.bflcustomerId = bflcustomerId;
	}

	public String getiMPSmessageCode() {
		return iMPSmessageCode;
	}

	public void setiMPSmessageCode(String iMPSmessageCode) {
		this.iMPSmessageCode = iMPSmessageCode;
	}

	public String getiMPSdateTime() {
		return iMPSdateTime;
	}

	public void setiMPSdateTime(String iMPSdateTime) {
		this.iMPSdateTime = iMPSdateTime;
	}

	public String getiMPStrnsDate() {
		return iMPStrnsDate;
	}

	public void setiMPStrnsDate(String iMPStrnsDate) {
		this.iMPStrnsDate = iMPStrnsDate;
	}

	public String getiMPSmerchantId() {
		return iMPSmerchantId;
	}

	public void setiMPSmerchantId(String iMPSmerchantId) {
		this.iMPSmerchantId = iMPSmerchantId;
	}

	public String getiMPStraceNumber() {
		return iMPStraceNumber;
	}

	public void setiMPStraceNumber(String iMPStraceNumber) {
		this.iMPStraceNumber = iMPStraceNumber;
	}

	public String getiMPSrespDesc() {
		return iMPSrespDesc;
	}

	public void setiMPSrespDesc(String iMPSrespDesc) {
		this.iMPSrespDesc = iMPSrespDesc;
	}

	public String getiMPSrrn() {
		return iMPSrrn;
	}

	public void setiMPSrrn(String iMPSrrn) {
		this.iMPSrrn = iMPSrrn;
	}

	public String getiMPSbankRefNum() {
		return iMPSbankRefNum;
	}

	public void setiMPSbankRefNum(String iMPSbankRefNum) {
		this.iMPSbankRefNum = iMPSbankRefNum;
	}

	public String getiMPSbeneName() {
		return iMPSbeneName;
	}

	public void setiMPSbeneName(String iMPSbeneName) {
		this.iMPSbeneName = iMPSbeneName;
	}

	public String getiMPSchecksum() {
		return iMPSchecksum;
	}

	public void setiMPSchecksum(String iMPSchecksum) {
		this.iMPSchecksum = iMPSchecksum;
	}

	public String getiMPStrnBankName() {
		return iMPStrnBankName;
	}

	public void setiMPStrnBankName(String iMPStrnBankName) {
		this.iMPStrnBankName = iMPStrnBankName;
	}

	public Date getiMPSCreatedON() {
		return iMPSCreatedON;
	}

	public void setiMPSCreatedON(Date iMPSCreatedON) {
		this.iMPSCreatedON = iMPSCreatedON;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getImpsResponseCode() {
		return impsResponseCode;
	}

	public void setImpsResponseCode(String impsResponseCode) {
		this.impsResponseCode = impsResponseCode;
	}

	@Override
	public String toString() {
		return "IMPSFixedDepositData [id=" + id + ", mobileno=" + mobileno + ", bflcustomerId=" + bflcustomerId
				+ ", iMPSmessageCode=" + iMPSmessageCode + ", iMPSdateTime=" + iMPSdateTime + ", iMPStrnsDate="
				+ iMPStrnsDate + ", iMPSmerchantId=" + iMPSmerchantId + ", iMPStraceNumber=" + iMPStraceNumber
				+ ", iMPSrespDesc=" + iMPSrespDesc + ", iMPSrrn=" + iMPSrrn + ", iMPSbankRefNum=" + iMPSbankRefNum
				+ ", iMPSbeneName=" + iMPSbeneName + ", iMPSchecksum=" + iMPSchecksum + ", iMPStrnBankName="
				+ iMPStrnBankName + ", iMPSCreatedON=" + iMPSCreatedON + ", createdOn=" + createdOn
				+ ", impsResponseCode=" + impsResponseCode + "]";
	}

		
}
