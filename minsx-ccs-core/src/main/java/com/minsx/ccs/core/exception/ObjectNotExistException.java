package com.minsx.ccs.core.exception;
/**
 * CCSObjectNotExistException 公共云存储不存在异常
 * created by Joker on 2017年11月7日
 */
public class ObjectNotExistException extends CCSServiceException{
	
	private String bucketName;
	private String ccsObjectPath;
	private String errorMessage;
	
	private static final long serialVersionUID = 5342978415436948368L;

	public ObjectNotExistException(String message) {
		super((String)null);
		this.errorMessage = message;
	}
	
	public ObjectNotExistException(String bucketName,String ccsObjectPath,String message) {
		super((String)null);
		this.bucketName=bucketName;
		this.ccsObjectPath=ccsObjectPath;
		this.errorMessage = message;
	}
	
	public ObjectNotExistException(Throwable t) {
		super(t);
	}
	
	public ObjectNotExistException(String message,Throwable t) {
		super(t);
		this.errorMessage = message;
	}

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

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String getMessage() {
		return String.format("[errorMessage]: %s, [bucketName]: %s, [ccsObjectPath]: %s ", errorMessage,bucketName,ccsObjectPath);
	}
	

}
