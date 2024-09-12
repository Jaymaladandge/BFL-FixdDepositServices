package com.bajaj.fixeddeposit.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity 
@Table(name = "FD_DEAL_ID_GENERATOR")
public class FDDealIdGenerator implements Serializable{
	
	@Id
	@NotNull
	@Column(name = "ID")
	private int id;
	
	@Column(name = "CHECK_ID")
	private String checkID;
	
	@Column(name = "DEAL_ID")
	private String dealID;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCheckID() {
		return checkID;
	}

	public void setCheckID(String checkID) {
		this.checkID = checkID;
	}

	public String getDealID() {
		return dealID;
	}

	public void setDealID(String dealID) {
		this.dealID = dealID;
	}

	
	
}