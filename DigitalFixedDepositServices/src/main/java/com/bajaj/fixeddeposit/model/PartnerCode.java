package com.bajaj.fixeddeposit.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "FD_PARTNER_CODE")
public class PartnerCode implements Serializable {
	
	private static final long serialVersionUID = -5587912842476986593L;

	@Id
	@NotNull
	@Column(name = "ID", unique = true)
	private int id;
	
	@Column(name = "PARTNER_CODE")
	private String partnerCode;
	
	@Column(name = "PARTNER_NAME")
	private String partnerName;

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

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "PartnerCode [id=" + id + ", partnerCode=" + partnerCode + ", partnerName=" + partnerName + "]";
	}

	

}
