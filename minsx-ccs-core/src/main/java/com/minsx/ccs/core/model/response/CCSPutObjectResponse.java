package com.minsx.ccs.core.model.response;

import java.io.InputStream;

public class CCSPutObjectResponse extends CCSBaseResponse{

	private String eTag;

	private InputStream responseBody;
	
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
}
