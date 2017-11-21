package com.minsx.ccs.core.model.request;

import com.minsx.ccs.core.able.CCSGetObjectRequestable;

public class CCSGetObjectRequest extends CCSBaseGetObjectRequest implements CCSGetObjectRequestable{
	
	public CCSGetObjectRequest(String bucketName,String ccsPath) {
		super(bucketName, ccsPath);
	}

	@Override
	public CCSGetObjectRequestable withBucketName(String bucketName) {
		super.setBucketName(bucketName);
		return this;
	}

	@Override
	public CCSGetObjectRequestable withCcsPath(String ccsPath) {
		super.setCcsPath(ccsPath);
		return this;
	}

}
