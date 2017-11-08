package com.minsx.ccs.core.exception;

/**
 * CCS root Exception
 * created by Joker on 2017年11月8日
 */
public class CCSException extends RuntimeException {

	private static final long serialVersionUID = -3091258328608812304L;

	/**
	 * Create new CCS exception with message
	 * @param message Abnormal error information
	 */
	public CCSException(String message) {
		super(message);
	}

	/**
	 * Create new CCS exception with throwable
	 * @param t The root cause of this exception
	 */
	public CCSException(Throwable t) {
		super(t);
	}

	/**
	 * Create new CCS exception with message and throwable
	 * @param message Abnormal error information
	 * @param t The root cause of this exception
	 */
	public CCSException(String message, Throwable t) {
		super(message, t);
	}
}
