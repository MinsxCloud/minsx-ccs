package com.minsx.ccs.core.able;

public interface CCSGetObjectRequestable extends CCSBaseObjectRequest{
	
	CCSGetObjectRequestable withBucketName(String bucketName);
	
	CCSGetObjectRequestable withCcsPath(String ccsPath);

}
