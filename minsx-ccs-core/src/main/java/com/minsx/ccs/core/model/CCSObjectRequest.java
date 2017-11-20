package com.minsx.ccs.core.model;

import com.minsx.ccs.core.able.ObjectRequestable;

public class CCSObjectRequest extends CCSBaseObjectRequest implements ObjectRequestable{
	
	public CCSObjectRequest(String bucketName,String ccsPath) {
		super(bucketName, ccsPath);
	}

	@Override
	public ObjectRequestable withBucketName(String bucketName) {
		super.setBucketName(bucketName);
		return this;
	}

	@Override
	public ObjectRequestable withCcsPath(String ccsPath) {
		super.setCcsPath(ccsPath);
		return this;
	}

}
