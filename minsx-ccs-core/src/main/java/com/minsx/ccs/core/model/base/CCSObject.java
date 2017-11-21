package com.minsx.ccs.core.model.base;

import java.io.InputStream;

public class CCSObject {

	/**
	 * 对于云存储来说该项即为对象的KEY
	 */
	private String ccsPath;
	
	private String bucketName;
	
	private CCSObjectMetadata ccsObjectMetadata;
	
	private InputStream objectContent;

	public String getCcsPath() {
		return ccsPath;
	}

	public void setCcsPath(String ccsPath) {
		this.ccsPath = ccsPath;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public CCSObjectMetadata getCcsObjectMetadata() {
		return ccsObjectMetadata;
	}

	public void setCcsObjectMetadata(CCSObjectMetadata ccsObjectMetadata) {
		this.ccsObjectMetadata = ccsObjectMetadata;
	}

	public InputStream getObjectContent() {
		return objectContent;
	}

	public void setObjectContent(InputStream objectContent) {
		this.objectContent = objectContent;
	}
	
}
