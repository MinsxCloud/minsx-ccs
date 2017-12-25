package com.minsx.ccs.core.model.request;

import java.io.File;
import java.io.InputStream;

import com.minsx.ccs.core.able.CCSPutObjectRequestable;
import com.minsx.ccs.core.model.model.CCSObjectMetadata;

public class CCSPutObjectRqeuest extends CCSBasePutObjectRequest implements CCSPutObjectRequestable{

	private File file;

	private InputStream inputStream;

	private CCSObjectMetadata metadata;
	
    private long partSize = 1024 * 100;
    
    private int taskNum = 1;
    
    private boolean enableCheckpoint = false;
    
    private String checkpointFile;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public CCSObjectMetadata getMetadata() {
		return metadata;
	}

	public void setMetadata(CCSObjectMetadata metadata) {
		this.metadata = metadata;
	}

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

	public boolean isEnableCheckpoint() {
		return enableCheckpoint;
	}

	public void setEnableCheckpoint(boolean enableCheckpoint) {
		this.enableCheckpoint = enableCheckpoint;
	}

	public String getCheckpointFile() {
		return checkpointFile;
	}

	public void setCheckpointFile(String checkpointFile) {
		this.checkpointFile = checkpointFile;
	}

}
