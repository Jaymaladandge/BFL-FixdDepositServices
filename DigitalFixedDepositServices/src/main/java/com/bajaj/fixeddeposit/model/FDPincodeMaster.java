package com.bajaj.fixeddeposit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "FIXED_DEPOSIT_PINCODE_MASTER")
public class FDPincodeMaster {
	
	
	@Id
	@NotNull
	@Column(name = "ID", unique = true)
	private int id;
	
	@Column(name="PINCODE")
	private int pincode;
	
	@Column(name="CITY")
	private String city;
	
	@Column(name="STATE")
	private String state;

	@Column(name="LOCATION_TYPE")
	private String locationType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	@Override
	public String toString() {
		return "FDPincodeMaster [id=" + id + ", pincode=" + pincode + ", city=" + city + ", state=" + state
				+ ", locationType=" + locationType + "]";
	}
	

}
