package com.minsx.ccs.core.model;

import java.io.File;
import java.io.InputStream;

import com.minsx.ccs.core.able.CallBack;
import com.minsx.ccs.core.able.PutObjectRequestable;

public class CCSPutObjectRqeuest implements PutObjectRequestable{

	private String bucketName;

	private String ccsObjectPath;

	private File file;

	private InputStream inputStream;

	private CCSObjectMetadata metadata;

	private CallBack callBack;

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getCcsObjectPath() {
		return ccsObjectPath;
	}

	public void setCcsObjectPath(String ccsObjectPath) {
		this.ccsObjectPath = ccsObjectPath;
	}

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

	public CallBack getCallBack() {
		return callBack;
	}

	public void setCallBack(CallBack callBack) {
		this.callBack = callBack;
	}
	

}
