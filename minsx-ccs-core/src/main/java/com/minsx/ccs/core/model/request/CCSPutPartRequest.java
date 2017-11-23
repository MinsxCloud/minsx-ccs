package com.minsx.ccs.core.model.request;

import java.io.InputStream;

import com.minsx.ccs.core.able.CCSPutPartRequestable;

public class CCSPutPartRequest extends CCSBasePutObjectRequest implements CCSPutPartRequestable{
	
	private String uploadId;
	
	private int partNumber;

    private long partSize;
    
    private String md5Digest;

    private InputStream inputStream;

	public String getUploadId() {
		return uploadId;
	}

	public void setUploadId(String uploadId) {
		this.uploadId = uploadId;
	}

	public int getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(int partNumber) {
		this.partNumber = partNumber;
	}

	public long getPartSize() {
		return partSize;
	}

	public void setPartSize(long partSize) {
		this.partSize = partSize;
	}

	public String getMd5Digest() {
		return md5Digest;
	}

	public void setMd5Digest(String md5Digest) {
		this.md5Digest = md5Digest;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

}
