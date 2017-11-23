package com.minsx.ccs.core.model.response;

public class CCSInitiateMultipartPutResponse extends CCSBaseResponse{

	private String bucketName;

	private String key;

	private String uploadId;
	
	public CCSInitiateMultipartPutResponse() {
	}
	
	public CCSInitiateMultipartPutResponse(String bucketName, String key, String uploadId) {
		super();
		this.bucketName = bucketName;
		this.key = key;
		this.uploadId = uploadId;
	}
	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUploadId() {
		return uploadId;
	}

	public void setUploadId(String uploadId) {
		this.uploadId = uploadId;
	}

}
