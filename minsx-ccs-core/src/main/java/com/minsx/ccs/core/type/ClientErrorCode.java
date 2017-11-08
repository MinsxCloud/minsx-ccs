package com.minsx.ccs.core.type;

public enum ClientErrorCode {
	
	UNKNOWN(1,"Unknown"),
	UNKNOWN_HOST(2,"UnknownHost"),
	CONNECTION_TIMEOUT(3,"ConnectionTimeout"),
	SOCKET_TIMEOUT(4,"SocketTimeout"),
	SOCKET_EXCEPTION(5,"SocketException"),
	CONNECTION_REFUSED(6,"ConnectionRefused"),
	NONREPEATABLE_REQUEST(7,"NonRepeatableRequest");
	
	private Integer ErrorCodeNum;
	private String ErrorCodeName;
	
	ClientErrorCode(Integer ErrorCodeNum,String ErrorCodeName) {
		this.ErrorCodeNum = ErrorCodeNum;
		this.ErrorCodeName=ErrorCodeName;
	}

	public Integer getErrorCodeNum() {
		return ErrorCodeNum;
	}

	public void setErrorCodeNum(Integer errorCodeNum) {
		ErrorCodeNum = errorCodeNum;
	}

	public String getErrorCodeName() {
		return ErrorCodeName;
	}

	public void setErrorCodeName(String errorCodeName) {
		ErrorCodeName = errorCodeName;
	}

}
