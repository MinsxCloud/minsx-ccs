package com.minsx.ccs.core.model.response;

public class CCSInitiateMultipartPutResponse extends CCSBaseResponse{

	private String uploadId;
	
	public CCSInitiateMultipartPutResponse() {
	}
	
	public CCSInitiateMultipartPutResponse(String bucketName, String ccsObjectPath, String uploadId) {
		super(bucketName,ccsObjectPath);
		this.uploadId = uploadId;
	}
	
	public String getUploadId() {
		return uploadId;
	}

	public void setUploadId(String uploadId) {
		this.uploadId = uploadId;
	}

}
