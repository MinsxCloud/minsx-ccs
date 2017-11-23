package com.minsx.ccs.core.model.model;

import java.util.Date;

/**
 * CcsObjectSummary 公共云存储对象概要信息
 * created by Joker on 2017年11月7日
 */
public class CCSObjectSummary {
	
	private String bucketName;
	
	private String ccsPath;
	
	private Date lastModified;

	private Long size;

	private String ETag;
	
	private String storgeClass;
	
	private CCSOwner ccsOwner;

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

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getETag() {
		return ETag;
	}

	public void setETag(String eTag) {
		ETag = eTag;
	}

	public String getStorgeClass() {
		return storgeClass;
	}

	public void setStorgeClass(String storgeClass) {
		this.storgeClass = storgeClass;
	}

	public CCSOwner getCCSOwner() {
		return ccsOwner;
	}

	public void setCCSOwner(CCSOwner owner) {
		this.ccsOwner = owner;
	}

}
