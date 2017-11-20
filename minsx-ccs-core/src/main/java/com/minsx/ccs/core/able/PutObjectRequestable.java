package com.minsx.ccs.core.able;

import java.io.File;
import java.io.InputStream;

import com.minsx.ccs.core.model.CCSObjectMetadata;

public interface PutObjectRequestable {
	
	public String getBucketName();
	
	public String getCcsObjectPath();
	
	public File getFile();
	
	public InputStream getInputStream();
	
	public CCSObjectMetadata getMetadata();
	
	public CallBack getCallBack();

}
