package com.minsx.ccs.core.exception;

/**
 * 
 * created by Joker on 2017年11月9日
 */
public class NativeClientCastException extends ClassCastException {

	private static final long serialVersionUID = 8669725914467072169L;
	
	private Class<?> nativeClientClass;
	private Class<?> destinationClass;
	private String errorMessage;

	public NativeClientCastException() {
		super();
	}
	
	public NativeClientCastException(Class<?> nativeClientClass,Class<?> destinationClass) {
		super((String)null);
		this.nativeClientClass=nativeClientClass;
		this.destinationClass=destinationClass;
		this.errorMessage=String.format("native client type error：%s can't cast to %s", nativeClientClass.getName(),
				destinationClass.getName());
	}

	public Class<?> getNativeClientClass() {
		return nativeClientClass;
	}

	public void setNativeClientClass(Class<?> nativeClientClass) {
		this.nativeClientClass = nativeClientClass;
	}

	public Class<?> getDestinationClass() {
		return destinationClass;
	}

	public void setDestinationClass(Class<?> destinationClass) {
		this.destinationClass = destinationClass;
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
		return this.errorMessage;
	}

}
