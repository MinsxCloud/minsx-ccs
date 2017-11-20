package com.minsx.ccs.core.able;

public interface ListObjectsRequestable {

	String getBucketName();
	
	String getPrefix();

	String getMarker();

	Integer getMaxKeys();

	String getDelimiter();

	String getEncodingType();
	
	ListObjectsRequestable withBucketName(String bucketName);
	
	ListObjectsRequestable withPrefix(String prefix);
	
	ListObjectsRequestable withMarker(String marker);
	
	ListObjectsRequestable withMaxKeys(Integer maxKeys);
	
	ListObjectsRequestable withDelimiter(String delimiter);
	
	ListObjectsRequestable withEncodingType(String encodingType);
	
}
