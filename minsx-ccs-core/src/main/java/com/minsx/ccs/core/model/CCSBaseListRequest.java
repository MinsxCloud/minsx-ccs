package com.minsx.ccs.core.model;

public class CCSBaseListRequest {

	private String bucketName;

	private String prefix;

	public CCSBaseListRequest(String bucketName) {
		this.bucketName=bucketName;
	}
	
	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

}
