package com.minsx.ccs.core.able;

public interface CCSPutBigObjectRequestable {
	
	public String getBucketName();
	
	public String getCcsPath();
	
	public Long getPartSize();
	
	public Integer getTaskNum();
	
	public String getSourceFilePath();
	
	public Boolean isEnableCheckpoint();

}
