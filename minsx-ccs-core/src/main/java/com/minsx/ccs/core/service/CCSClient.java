package com.minsx.ccs.core.service;

import com.minsx.ccs.core.model.CCSObject;
import com.minsx.ccs.core.model.CCSObjectList;
import com.minsx.ccs.core.model.CCSObjectMetadata;

/**
 * CCSClient created by web on 2017年11月4日
 */
public interface CCSClient {

	public void createBucket(String bucketName);

	public void deleteBucket(String bucketName);

	public Boolean doesBucketExist(String bucketName);

	public Boolean doesObjectExist(String bucketName, String ccsObjectPath);

	public CCSObjectList getObjectList(String bucketName, String ccsFolderPath);

	public CCSObject getObject(String bucketName, String ccsObjectPath);

	public CCSObjectMetadata getObjectMetadata(String bucketName, String ccsObjectPath);

	public void copyObject(String sourceBucketName, String sourceObjectPath, String destinationBucketName,
			String destinationObjectPath);

	public void putObject(String sourceFilePath, String bucketName, String ccsObjectPath);

	public void deleteObject(String bucketName, String ccsObjectPath);

	public void shutdown();
	
	public <T> T getNativeClient(Class<T> nativeClientClazz);

}
