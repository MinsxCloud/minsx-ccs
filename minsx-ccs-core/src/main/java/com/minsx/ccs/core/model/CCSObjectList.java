package com.minsx.ccs.core.model;

import java.util.List;

/**
 * CcsObjectList 公共云存储对象列表
 * created by Joker on 2017年11月7日
 */
public class CCSObjectList {
	
	private String bucketName;
	
    private Integer maxKeys;
    
    private String marker;
	
	private String prefix;
	
	private List<String> commonPrefix;
	
	private List<CCSObjectMetadata> ccsObjectMetadatas;
	
	private List<CCSObjectSummary> ccsObjectSummaries;

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public Integer getMaxKeys() {
		return maxKeys;
	}

	public void setMaxKeys(Integer maxKeys) {
		this.maxKeys = maxKeys;
	}

	public String getMarker() {
		return marker;
	}

	public void setMarker(String marker) {
		this.marker = marker;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public List<String> getCommonPrefix() {
		return commonPrefix;
	}

	public void setCommonPrefix(List<String> commonPrefix) {
		this.commonPrefix = commonPrefix;
	}

	public List<CCSObjectMetadata> getCcsObjectMetadatas() {
		return ccsObjectMetadatas;
	}

	public void setCcsObjectMetadatas(List<CCSObjectMetadata> ccsObjectMetadatas) {
		this.ccsObjectMetadatas = ccsObjectMetadatas;
	}

	public List<CCSObjectSummary> getCcsObjectSummaries() {
		return ccsObjectSummaries;
	}

	public void setCcsObjectSummaries(List<CCSObjectSummary> ccsObjectSummaries) {
		this.ccsObjectSummaries = ccsObjectSummaries;
	}
	
}
