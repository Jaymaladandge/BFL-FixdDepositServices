package com.bajaj.fixeddeposit.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "FIXED_DEPOSIT_SCHEME_CODE")
public class FixedDepositSchemeCode implements Serializable {
	
	private static final long serialVersionUID = 8326042007586111873L;

	@Id
	@NotNull
	@Column(name = "ID")
	private int id;
	
	@Column(name="PRODUCT")
	private String product;
	
	@Column(name="BFL_CUSTOMER_TYPE")
	private String bflCustType;
	
	@Column(name = "PAYOUT_TYPE")
	private String payoutType;
	
	@Column(name = "INTEREST_PAYOUT_FREQUENCY")
	private String interestPayoutFrequency;
	
	@Column(name = "SCHEME")
	private String scheme;
	
	@Column(name ="LOCATION_TYPE")
	private String locationType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getBflCustType() {
		return bflCustType;
	}

	public void setBflCustType(String bflCustType) {
		this.bflCustType = bflCustType;
	}

	public String getPayoutType() {
		return payoutType;
	}

	public void setPayoutType(String payoutType) {
		this.payoutType = payoutType;
	}

	public String getInterestPayoutFrequency() {
		return interestPayoutFrequency;
	}

	public void setInterestPayoutFrequency(String interestPayoutFrequency) {
		this.interestPayoutFrequency = interestPayoutFrequency;
	}

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	@Override
	public String toString() {
		return "FixedDepositSchemeCode [id=" + id + ", product=" + product + ", bflCustType=" + bflCustType
				+ ", payoutType=" + payoutType + ", interestPayoutFrequency=" + interestPayoutFrequency + ", scheme="
				+ scheme + ", locationType=" + locationType + "]";
	}
	
	
	
	

}
