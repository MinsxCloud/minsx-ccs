package com.minsx.ccs.core.model.response;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public abstract class CCSHttpMesssage {

	private Map<String, String> headers = new HashMap<String, String>();
	private InputStream content;
	private long contentLength;

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public InputStream getContent() {
		return content;
	}

	public void setContent(InputStream content) {
		this.content = content;
	}

	public long getContentLength() {
		return contentLength;
	}

	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
	}

}
