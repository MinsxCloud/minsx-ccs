package com.minsx.ccs.core.model.request;

public abstract class CCSBaseGetObjectRequest {
	
	private String bucketName;
	
	private String ccsPath;
	
	public CCSBaseGetObjectRequest(String bucketName,String ccsPath) {
		this.bucketName=bucketName;
		this.ccsPath=ccsPath;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getCcsObjectPath() {
		return ccsPath;
	}

	public void setCcsPath(String ccsPath) {
		this.ccsPath = ccsPath;
	}

}
