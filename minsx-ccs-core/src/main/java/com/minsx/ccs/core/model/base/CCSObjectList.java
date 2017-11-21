package com.minsx.ccs.core.model.base;

import java.util.List;

/**
 * CcsObjectList 公共云存储对象列表
 * created by Joker on 2017年11月7日
 */
public class CCSObjectList {
	
	private String bucketName;
	
	private String prefix;
	
	private String marker;
	
	private String nextMarker;
    
    private boolean isTruncated;
    
    private int maxKeys;
    
    private String delimiter;
    
    private String encodingType;
	
	private List<String> commonPrefix;
	
	private List<CCSObjectMetadata> ccsObjectMetadatas;
	
	private List<CCSObjectSummary> ccsObjectSummaries;

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
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

	public String getMarker() {
		return marker;
	}

	public void setMarker(String marker) {
		this.marker = marker;
	}

	public String getNextMarker() {
		return nextMarker;
	}

	public void setNextMarker(String nextMarker) {
		this.nextMarker = nextMarker;
	}

	public boolean isTruncated() {
		return isTruncated;
	}

	public void setTruncated(boolean isTruncated) {
		this.isTruncated = isTruncated;
	}

	public int getMaxKeys() {
		return maxKeys;
	}

	public void setMaxKeys(int maxKeys) {
		this.maxKeys = maxKeys;
	}

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public String getEncodingType() {
		return encodingType;
	}

	public void setEncodingType(String encodingType) {
		this.encodingType = encodingType;
	}
	
}
