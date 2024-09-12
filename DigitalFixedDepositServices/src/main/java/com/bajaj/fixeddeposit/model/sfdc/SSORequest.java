package com.bajaj.fixeddeposit.model.sfdc;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SSORequest implements Serializable 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 
	@JsonProperty(value = "source_request",required=true)
	private String sourceRequest;

	public String getSourceRequest() {
		return sourceRequest;
	}

	public void setSourceRequest(String sourceRequest) {
		this.sourceRequest = sourceRequest;
	}

	@Override
	public String toString() {
		return "SSORequest [sourceRequest=" + sourceRequest + "]";
	}
	
	
	
}
