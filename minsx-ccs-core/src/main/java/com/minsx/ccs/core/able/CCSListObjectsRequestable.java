package com.minsx.ccs.core.able;

public interface CCSListObjectsRequestable {

	String getBucketName();
	
	String getPrefix();

	String getMarker();

	Integer getMaxKeys();

	String getDelimiter();

	String getEncodingType();
	
	CCSListObjectsRequestable withBucketName(String bucketName);
	
	CCSListObjectsRequestable withPrefix(String prefix);
	
	CCSListObjectsRequestable withMarker(String marker);
	
	CCSListObjectsRequestable withMaxKeys(Integer maxKeys);
	
	CCSListObjectsRequestable withDelimiter(String delimiter);
	
	CCSListObjectsRequestable withEncodingType(String encodingType);
	
}
