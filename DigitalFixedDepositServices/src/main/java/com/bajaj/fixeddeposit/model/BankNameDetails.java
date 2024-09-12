package com.bajaj.fixeddeposit.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "FD_BANK_CHECK_CODE")
public class BankNameDetails implements Serializable {
	
	private static final long serialVersionUID = -5797370179955823560L;

	@Id
	@NotNull
	@Column(name = "ID", unique = true)
	private int id;
	
	@Column(name = "BANK_NAME")
	private String bankName;
	
	@Column(name = "CC_AVENUE_BANK_NAME")
	private String ccavenuebankname;
	
	@Column(name = "BILLDESK_BANK_NAME")
	private String billdeskbankname;
	
	@Column(name = "BANK_TYPE")
	private String banktype;
	
	@Column(name = "BANK_ID")
	private String bankId;


	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCcavenuebankname() {
		return ccavenuebankname;
	}

	public void setCcavenuebankname(String ccavenuebankname) {
		this.ccavenuebankname = ccavenuebankname;
	}

	public String getBilldeskbankname() {
		return billdeskbankname;
	}

	public void setBilldeskbankname(String billdeskbankname) {
		this.billdeskbankname = billdeskbankname;
	}

	public String getBanktype() {
		return banktype;
	}

	public void setBanktype(String banktype) {
		this.banktype = banktype;
	}

	@Override
	public String toString() {
		return "BankNameDetails [id=" + id + ", bankName=" + bankName + ", ccavenuebankname=" + ccavenuebankname
				+ ", billdeskbankname=" + billdeskbankname + ", banktype=" + banktype + bankId + "]";
	}

	public BankNameDetails() {
		super();
	}

	public BankNameDetails(int id, String bankName, String ccavenuebankname, String billdeskbankname, String banktype,String bankId ) {
		super();
		this.id = id;
		this.bankName = bankName;
		this.ccavenuebankname = ccavenuebankname;
		this.billdeskbankname = billdeskbankname;
		this.banktype = banktype;
		this.bankId=bankId;
	}
	
	
	
}
