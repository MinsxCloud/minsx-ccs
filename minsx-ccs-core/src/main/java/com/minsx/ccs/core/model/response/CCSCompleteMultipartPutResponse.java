package com.minsx.ccs.core.model.response;

public class CCSCompleteMultipartPutResponse extends CCSBaseResponse{
	
	private  String eTag;
	
	public CCSCompleteMultipartPutResponse() {
		super();
	}

	public String getETag() {
		return eTag;
	}

	public void setETag(String eTag) {
		this.eTag = eTag;
	}
	
	

}
