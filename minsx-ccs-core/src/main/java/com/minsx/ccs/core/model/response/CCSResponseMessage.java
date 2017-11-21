package com.minsx.ccs.core.model.response;

public class CCSResponseMessage extends CCSHttpMesssage {

	private static final int HTTP_SUCCESS_STATUS_CODE = 200;

	private String uri;
	private Integer statusCode;

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public static int getHttpSuccessStatusCode() {
		return HTTP_SUCCESS_STATUS_CODE;
	}

}
