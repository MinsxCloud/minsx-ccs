package com.minsx.ccs.core.model.request;

import com.minsx.ccs.core.able.CCSListObjectsRequestable;

public class CCSListObjectsRequest extends CCSBaseListObjectsRequest implements CCSListObjectsRequestable {

	private String marker;
	private Integer maxKeys;
	private String delimiter;
	private String encodingType;
	
	public CCSListObjectsRequest(String bucketName){
		super(bucketName);
	}

	public void setMarker(String marker) {
		this.marker = marker;
	}

	public void setMaxKeys(Integer maxKeys) {
		this.maxKeys = maxKeys;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public void setEncodingType(String encodingType) {
		this.encodingType = encodingType;
	}

	@Override
	public String getMarker() {
		return marker;
	}

	@Override
	public Integer getMaxKeys() {
		return maxKeys;
	}

	@Override
	public String getDelimiter() {
		return delimiter;
	}

	@Override
	public String getEncodingType() {
		return encodingType;
	}

	@Override
	public CCSListObjectsRequestable withBucketName(String bucketName) {
		setBucketName(bucketName);
		return this;
	}

	@Override
	public CCSListObjectsRequestable withPrefix(String prefix) {
		setPrefix(prefix);
		return this;
	}

	@Override
	public CCSListObjectsRequestable withMarker(String marker) {
		setMarker(marker);
		return this;
	}

	@Override
	public CCSListObjectsRequestable withMaxKeys(Integer maxKeys) {
		setMaxKeys(maxKeys);
		return this;
	}

	@Override
	public CCSListObjectsRequestable withDelimiter(String delimiter) {
		setDelimiter(delimiter);
		return this;
	}

	@Override
	public CCSListObjectsRequestable withEncodingType(String encodingType) {
		setEncodingType(encodingType);
		return this;
	}

}
