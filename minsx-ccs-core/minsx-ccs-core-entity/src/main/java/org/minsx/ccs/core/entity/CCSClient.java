package org.minsx.ccs.core.entity;

/**
 * CCSClient created by web on 2017年11月4日
 */
public interface CCSClient {

	public void shutdown();

	public Bucket createBucket(String bucketName) throws Exception;

}
