package com.minsx.ccs.core.model.response;

import com.minsx.ccs.core.model.base.CCSPartETag;

public class CCSPutPartResponse extends CCSBaseResponse {

	private int partNumber;
	private long partSize;
	private String eTag;
	
	public CCSPutPartResponse(){
	};
	
	public CCSPutPartResponse(int partNumber, long partSize, String eTag) {
		super();
		this.partNumber = partNumber;
		this.partSize = partSize;
		this.eTag = eTag;
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

	public String geteTag() {
		return eTag;
	}

	public void seteTag(String eTag) {
		this.eTag = eTag;
	}
	
	public CCSPartETag getCCSPartETag() {
		return new CCSPartETag(partNumber, eTag, partSize, null);
	}
	
	

}
