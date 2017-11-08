package com.minsx.ccs.core.exception;

public class NativeClientTypeException extends Exception {

	private static final long serialVersionUID = 8669725914467072169L;

	public NativeClientTypeException() {
		super();
	}

	public NativeClientTypeException(Class<?> nativeClientClass, Class<?> paramClass) {
		super(String.format("native client type error：%s is not instance of %s", nativeClientClass.getName(),
				paramClass.getName()));
	}

	public NativeClientTypeException(Class<?> nativeClientClass, Class<?> paramClass, Throwable cause) {
		super(String.format("native client type error：%s is not instance of %s", nativeClientClass.getName(),
				paramClass.getName()), cause);
	}

	protected NativeClientTypeException(Class<?> nativeClientClass, Class<?> paramClass, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(String.format("native client type error：%s is not instance of %s", nativeClientClass.getName(),
				paramClass.getName()), cause, enableSuppression, writableStackTrace);
	}

}
