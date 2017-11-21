package com.minsx.ccs.core.model.request;

public class CCSPutBigObjectRequest {
	
	//分片大小
    private long partSize = 1024 * 100;
    //并发线程数
    private int taskNum = 1;
    //源文件路径
    private String sourceFilePath;
    //是否开启断点续传
    private boolean enableCheckpoint = false;
    
	public long getPartSize() {
		return partSize;
	}
	public void setPartSize(long partSize) {
		this.partSize = partSize;
	}
	public int getTaskNum() {
		return taskNum;
	}
	public void setTaskNum(int taskNum) {
		this.taskNum = taskNum;
	}
	public String getSourceFilePath() {
		return sourceFilePath;
	}
	public void setSourceFilePath(String sourceFilePath) {
		this.sourceFilePath = sourceFilePath;
	}
	public boolean isEnableCheckpoint() {
		return enableCheckpoint;
	}
	public void setEnableCheckpoint(boolean enableCheckpoint) {
		this.enableCheckpoint = enableCheckpoint;
	}
}
