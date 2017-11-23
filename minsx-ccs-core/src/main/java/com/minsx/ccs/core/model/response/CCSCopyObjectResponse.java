package com.minsx.ccs.core.model.response;

import java.util.Date;

public class CCSCopyObjectResponse {

	private String eTag;

	private Date lastModified;

	public String geteTag() {
		return eTag;
	}

	public void seteTag(String eTag) {
		this.eTag = eTag;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

}
