package com.minsx.ccs.core.able;

import java.io.File;
import java.io.InputStream;

import com.minsx.ccs.core.model.base.CCSObjectMetadata;

public interface CCSPutObjectRequestable {
	
	public String getBucketName();
	
	public String getCcsObjectPath();
	
	public File getFile();
	
	public InputStream getInputStream();
	
	public CCSObjectMetadata getMetadata();

}
