package com.minsx.ccs.core.model.response;

import java.io.InputStream;

public class CCSPutObjectResponse {

	private String eTag;

	private InputStream responseBody;
	
	private CCSResponseMessage responseMessage;

	public String geteTag() {
		return eTag;
	}

	public void seteTag(String eTag) {
		this.eTag = eTag;
	}

	public InputStream getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(InputStream responseBody) {
		this.responseBody = responseBody;
	}

	public CCSResponseMessage getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(CCSResponseMessage responseMessage) {
		this.responseMessage = responseMessage;
	}
	
}
