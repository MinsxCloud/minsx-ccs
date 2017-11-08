package com.minsx.ccs.core.exception;

import java.util.HashMap;
import java.util.Map;

import com.minsx.ccs.core.util.MapUtil;

public class CCSServiceException extends CCSException{

	private static final long serialVersionUID = -1610517394222943322L;
	
	private String errorMessage;

	private Map<String, String> additionalDetails;
	
	public CCSServiceException(String message) {
		super((String)null);
		this.errorMessage=message;
	}
	
	public CCSServiceException(String message,String... additionalDetailsKeyAndValues ) {
		super((String)null);
		this.errorMessage=message;
		this.additionalDetails = new HashMap<>();
		for (int i = 0; i < additionalDetailsKeyAndValues.length-1; i+=2) {
			this.additionalDetails.put(additionalDetailsKeyAndValues[i], additionalDetailsKeyAndValues[i+1]);
		}
	}
	
	public CCSServiceException(String message, Map<String, String> additionalDetails) {
		super((String)null);
		this.errorMessage=message;
		this.additionalDetails=additionalDetails;
	}
	
	public CCSServiceException(Throwable t) {
		super(t);
	}
	
	/**
	 * Create new CCS exception with message and throwable
	 * @param message Abnormal error information
	 * @param t The root cause of this exception
	 */
	public CCSServiceException(String message, Throwable t) {
		super(t);
		this.errorMessage=message;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Map<String, String> getAdditionalDetails() {
		return additionalDetails;
	}

	public void setAdditionalDetails(Map<String, String> additionalDetails) {
		this.additionalDetails = additionalDetails;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String getMessage() {
		return String.format("[errorMessage]: %s, [additionalDetails]: %s", errorMessage,MapUtil.mapToString(additionalDetails));
	}
	

}
