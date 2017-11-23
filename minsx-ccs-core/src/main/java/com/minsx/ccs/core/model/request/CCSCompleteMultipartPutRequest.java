package com.minsx.ccs.core.model.request;

import java.util.ArrayList;
import java.util.List;

import com.minsx.ccs.core.able.CCSCompleteMultipartPutRequestable;
import com.minsx.ccs.core.model.model.CCSPartETag;

public class CCSCompleteMultipartPutRequest extends CCSBasePutObjectRequest implements CCSCompleteMultipartPutRequestable{

	private String uploadId;

	private List<CCSPartETag> partETags = new ArrayList<CCSPartETag>();
	
	public CCSCompleteMultipartPutRequest(String bucketName, String ccsObjectPath, String uploadId, List<CCSPartETag> partETags) {
		super(bucketName,ccsObjectPath);
		this.uploadId=uploadId;
		this.partETags=partETags;
	}

	public String getUploadId() {
		return uploadId;
	}

	public void setUploadId(String uploadId) {
		this.uploadId = uploadId;
	}

	public List<CCSPartETag> getCCSPartETags() {
		return partETags;
	}

	public void setPartETags(List<CCSPartETag> partETags) {
		this.partETags = partETags;
	}

}
