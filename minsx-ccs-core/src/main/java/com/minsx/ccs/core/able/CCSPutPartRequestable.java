package com.minsx.ccs.core.able;

import java.io.InputStream;

public interface CCSPutPartRequestable {
	
	public String getBucketName();
	
	public String getCcsObjectPath();
	
	public String getUploadId();
	
	public int getPartNumber();
	
	public long getPartSize();
	
	public String getMd5Digest();
	
	public InputStream getInputStream();

}
