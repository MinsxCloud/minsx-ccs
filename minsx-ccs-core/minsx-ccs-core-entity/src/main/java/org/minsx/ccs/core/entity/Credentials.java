package org.minsx.ccs.core.entity;

public interface Credentials {
	/**
	 * Returns the access key ID for this credentials.
	 */
	public String getAccessKeyId();

	/**
	 * Returns the secret access key for this credentials.
	 */
	public String getSecretAccessKey();

	/**
	 * set the secret access key for this credentials.
	 */
	public void setAccessKeyId();

	/**
	 * set the secret access key for this credentials.
	 */
	public String setSecretAccessKey();

}
