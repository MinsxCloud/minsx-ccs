package com.minsx.ccs.core.exception;

import com.minsx.ccs.core.type.ClientErrorCode;

public class CCSClientException extends CCSException {

	private static final long serialVersionUID = -5833845976115231974L;

	private String errorMessage;
	private String requestId;
	private ClientErrorCode errorCode;
	private String HTTPStatusCode;

	/**
	 * Create new CCS client exception with message
	 * @param message Abnormal error information
	 */
	public CCSClientException(String message) {
		super((String)null);
		this.requestId="Unknown";
		this.errorCode=ClientErrorCode.UNKNOWN;
		this.errorMessage=message;
	}
	
	/**
	 * Create new CCS client exception with throwable
	 * @param t The root cause of this exception
	 */
	public CCSClientException(Throwable t) {
		super(t);
		this.requestId="Unknown";
		this.errorMessage = t.getMessage();
		this.errorCode=ClientErrorCode.UNKNOWN;
	}

	/**
	 * Create new CCS client exception with message and throwable
	 * @param message Abnormal error information
	 * @param t The root cause of this exception
	 */
	public CCSClientException(String message, Throwable t) {
		super((String)null, t);
		this.errorMessage=message;
		this.errorCode=ClientErrorCode.UNKNOWN;
	}
	
	
	/**
	 * Create new CCS client exception with message,requestId and errorCode
	 * @param message Abnormal error information
	 */
	public CCSClientException(String message,String requestId,ClientErrorCode errorCode) {
		super((String)null);
		this.requestId=requestId;
		this.errorCode=errorCode;
		errorMessage=message;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public ClientErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ClientErrorCode errorCode) {
		this.errorCode = errorCode;
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

	public String getHTTPStatusCode() {
		return HTTPStatusCode;
	}

	public void setHTTPStatusCode(String hTTPStatusCode) {
		HTTPStatusCode = hTTPStatusCode;
	}

	@Override
	public String getMessage() {
		return String.format("[errorMessage]: %s,[requestId]: %s,errorCode: %s,HTTPStatusCode: %s", errorMessage,requestId,errorCode.getErrorCodeName(),HTTPStatusCode);
	}
	
	

}
