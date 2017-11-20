package com.minsx.ccs.core.able;

public interface PutBigObjectRequestable {
	
	public String getBucketName();
	
	public String getCcsPath();
	
	public Long getPartSize();
	
	public Integer getTaskNum();
	
	public String getSourceFilePath();
	
	public Boolean isEnableCheckpoint();
	
	public CallBack getCallBack();

}
