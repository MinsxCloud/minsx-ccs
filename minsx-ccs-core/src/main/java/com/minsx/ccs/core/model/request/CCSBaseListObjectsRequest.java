package com.minsx.ccs.core.model.request;

public abstract class CCSBaseListObjectsRequest {

	private String bucketName;

	private String prefix;

	public CCSBaseListObjectsRequest(String bucketName) {
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
