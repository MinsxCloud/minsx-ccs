package com.minsx.ccs.core.model;

public class CCSBaseObjectRequest {
	
	private String bucketName;
	
	private String ccsPath;
	
	public CCSBaseObjectRequest(String bucketName,String ccsPath) {
		this.bucketName=bucketName;
		this.ccsPath=ccsPath;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getCcsPath() {
		return ccsPath;
	}

	public void setCcsPath(String ccsPath) {
		this.ccsPath = ccsPath;
	}

}
