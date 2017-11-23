package com.minsx.ccs.core.model.request;

import com.minsx.ccs.core.able.CCSInitiateMultipartPutRequestable;

public class CCSInitiateMultipartPutRequest extends CCSBasePutObjectRequest implements CCSInitiateMultipartPutRequestable{
	
	public CCSInitiateMultipartPutRequest(String bucketName,String ccsObjectPath) {
		super(bucketName,ccsObjectPath);
	}

}
