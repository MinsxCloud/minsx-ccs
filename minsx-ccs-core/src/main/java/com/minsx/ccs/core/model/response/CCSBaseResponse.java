package com.minsx.ccs.core.model.response;

public class CCSBaseResponse {

	private String bucketName;

	private String ccsObjectPath;
	
	public CCSBaseResponse() {
	}
	
	public CCSBaseResponse(String bucketName,String ccsObjectPath) {
		this.bucketName=bucketName;
		this.ccsObjectPath=ccsObjectPath;
	}

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
