package com.bajaj.fixeddeposit.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ASSISTED_PARTNER_CODE")
public class AssistedPartnerCode implements Serializable {

	private static final long serialVersionUID = -5587912842476986593L;

	@Id
	@NotNull
	@Column(name = "ID", unique = true)
	private int id;
	
	@Column(name = "PARTNER_CODE")
	private String partnerCode;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPartnerCode() {
		return partnerCode;
	}

	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
