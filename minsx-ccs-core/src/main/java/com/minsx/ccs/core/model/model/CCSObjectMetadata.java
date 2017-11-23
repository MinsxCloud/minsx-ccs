package com.minsx.ccs.core.model.model;
/**
 * CcsObjectMetadata 用于存储云存储对象的元数据
 * created by Joker on 2017年11月6日
 */

import java.util.Date;
import java.util.Map;

public class CCSObjectMetadata {

	private Date lastModified;

	private Long contentLength;

	private String contentType;

	private String contentEncoding;

	private String contentMD5;
	
	private String ETag;
	
	private String storgeClass;
	
	private String objectType;

	private Map<String, String> userMetaData;

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public Long getContentLength() {
		return contentLength;
	}

	public void setContentLength(Long contentLength) {
		this.contentLength = contentLength;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getContentEncoding() {
		return contentEncoding;
	}

	public void setContentEncoding(String contentEncoding) {
		this.contentEncoding = contentEncoding;
	}

	public String getContentMD5() {
		return contentMD5;
	}

	public void setContentMD5(String contentMD5) {
		this.contentMD5 = contentMD5;
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

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public Map<String, String> getUserMetaData() {
		return userMetaData;
	}
	
	public String getUserMetaDataOf(String key) {
		return userMetaData.get(key);
	}

	public void setUserMetaData(Map<String, String> userMetaData) {
		this.userMetaData = userMetaData;
	}

}
