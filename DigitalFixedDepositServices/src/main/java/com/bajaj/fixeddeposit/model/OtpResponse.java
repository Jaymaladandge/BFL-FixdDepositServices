package com.bajaj.fixeddeposit.model;

import java.io.Serializable;

public class OtpResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String errorMsg;
	private String mobile;
	private String requestId;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	@Override
	public String toString() {
		return "OTPResponse [errorCode=" + errorCode + ", errorMsg=" + errorMsg + ", mobile=" + mobile + ", requestId="
				+ requestId + "]";
	}

}
