package com.bajaj.fixeddeposit.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "BANKS_IFSC_CODE")
public class IfscCode implements Serializable{

	private static final long serialVersionUID = -158315013990153352L;
	

	@Id
	@NotNull
	@Column(name = "ID", unique = true)
	private int id;
	
	@Column(name = "IFSC_CODE")
	private String ifsc;
	
	@Column(name = "BANK_NAME")
	private String bankName;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIfscCode() {
		return ifsc;
	}

	public void setIfscCode(String ifsc) {
		this.ifsc = ifsc;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@Override
	public String toString() {
		return "IfscCode [id=" + id + ", ifsc=" + ifsc + ", bankName=" + bankName + "]";
	}
	
}
