package com.minsx.ccs.core.model;

/**
 * Paged access object
 * created by Joker on 2017年11月10日
 */
public class PageRequest {
	
	private String bucketName;
	private Integer objectCount;
	private String ccsPath;
	private String nextMarker;
	
	public PageRequest() {
	}
	
	public PageRequest(String bucketName) {
		this.bucketName=bucketName;
	}
	
	public PageRequest withBucketName(String bucketName) {
		this.bucketName=bucketName;
		return this;
	}
	
	public PageRequest withObjectCount(Integer objectCount) {
		this.objectCount=objectCount;
		return this;
	}
	
	public PageRequest withCcsPath(String ccsPath) {
		this.ccsPath=ccsPath;
		return this;
	}
	
	public PageRequest withNextMarker(String nextMarker) {
		this.nextMarker=nextMarker;
		return this;
	}
	

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public Integer getObjectCount() {
		return objectCount;
	}

	public void setObjectCount(Integer objectCount) {
		this.objectCount = objectCount;
	}

	public String getCcsPath() {
		return ccsPath;
	}

	public void setCcsPath(String ccsPath) {
		this.ccsPath = ccsPath;
	}

	public String getNextMarker() {
		return nextMarker;
	}

	public void setNextMarker(String nextMarker) {
		this.nextMarker = nextMarker;
	}

}
