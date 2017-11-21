package com.minsx.ccs.core.model.request;

public abstract class CCSBasePutObjectRequest {
	
	private String bucketName;

	private String ccsObjectPath;

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getCcsObjectPath() {
		return ccsObjectPath;
	}

	public void setCcsObjectPath(String ccsObjectPath) {
		this.ccsObjectPath = ccsObjectPath;
	}

}
