package com.bajaj.fixeddeposit.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "FIXED_DEPOSIT_CUST_ID_GENERATOR")
public class FixedDepositCustIdGenerator implements Serializable {

	private static final long serialVersionUID = 5558448129005071035L;
	
	@Id
	@NotNull
	@Column(name = "ID")
	private int id;
	
	@Column(name = "CUST_ID")
	private Integer customerId;
	
	@Column(name = "STATUS")
	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "FixedDepositCustIdGenerator [id=" + id + ", customerId=" + customerId + ", status=" + status + "]";
	}
	

}
