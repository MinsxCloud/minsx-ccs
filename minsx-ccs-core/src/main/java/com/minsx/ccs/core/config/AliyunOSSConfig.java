package com.minsx.ccs.core.config;

public class AliyunOSSConfig {

	private String endPoint;
	private String accessKeyId;
	private String accessKeySecret;
	private boolean isSupportCname;

	public AliyunOSSConfig() {
		this.isSupportCname=false;
	}
	
	public AliyunOSSConfig(String endPoint, String accessKeyId, String accessKeySecret, boolean isSupportCname) {
		super();
		this.endPoint = endPoint;
		this.accessKeyId = accessKeyId;
		this.accessKeySecret = accessKeySecret;
		this.isSupportCname = isSupportCname;
	}
	
	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	public String getAccessKeyId() {
		return accessKeyId;
	}

	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}

	public String getAccessKeySecret() {
		return accessKeySecret;
	}

	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}

	public boolean isSupportCname() {
		return isSupportCname;
	}

	public void setSupportCname(boolean isSupportCname) {
		this.isSupportCname = isSupportCname;
	}
}
