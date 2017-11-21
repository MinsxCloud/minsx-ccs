package com.minsx.ccs.core.able;

public interface CCSGetObjectRequestable {
	
	String getBucketName();
	
	String getCcsPath();
	
	CCSGetObjectRequestable withBucketName(String bucketName);
	
	CCSGetObjectRequestable withCcsPath(String ccsPath);

}
