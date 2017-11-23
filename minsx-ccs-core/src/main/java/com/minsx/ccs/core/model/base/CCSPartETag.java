package com.minsx.ccs.core.model.base;

import java.io.Serializable;

public class CCSPartETag  implements Serializable{
	
	private static final long serialVersionUID = -1033647391929258557L;
	
	private int partNumber;
    private String eTag;
    private long partSize;
    private Long partCRC;
    
    public CCSPartETag() {
    }
    
    public CCSPartETag(int partNumber,String eTag,long partSize,Long partCRC) {
    	this.partNumber=partNumber;
    	this.eTag=eTag;
    	this.partSize=partSize;
    	this.partCRC=partCRC;
    }
    
	public int getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(int partNumber) {
		this.partNumber = partNumber;
	}
	public String geteTag() {
		return eTag;
	}
	public void seteTag(String eTag) {
		this.eTag = eTag;
	}
	public long getPartSize() {
		return partSize;
	}
	public void setPartSize(long partSize) {
		this.partSize = partSize;
	}
	public Long getPartCRC() {
		return partCRC;
	}
	public void setPartCRC(Long partCRC) {
		this.partCRC = partCRC;
	}

}
