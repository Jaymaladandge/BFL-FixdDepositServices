package com.bajaj.fixeddeposit.model;
public class JsonResponse {
	private String status = null;
	private Object result = null;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "JsonResponse [status=" + status + ", result=" + result + "]";
	}
	
	
	
}