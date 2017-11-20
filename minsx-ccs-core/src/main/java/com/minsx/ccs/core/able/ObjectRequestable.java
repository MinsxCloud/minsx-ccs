package com.minsx.ccs.core.able;

public interface ObjectRequestable {
	
	String getBucketName();
	
	String getCcsPath();
	
	ObjectRequestable withBucketName(String bucketName);
	
	ObjectRequestable withCcsPath(String ccsPath);

}
