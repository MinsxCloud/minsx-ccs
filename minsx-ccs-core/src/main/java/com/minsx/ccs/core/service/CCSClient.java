package com.minsx.ccs.core.service;

import java.util.List;

import com.minsx.ccs.core.able.ListObjectsRequestable;
import com.minsx.ccs.core.able.ObjectRequestable;
import com.minsx.ccs.core.able.PageRequestable;
import com.minsx.ccs.core.able.PutObjectRequestable;
import com.minsx.ccs.core.model.CCSBucket;
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

	public List<CCSBucket> listCCSBuckets();
	
	public CCSObjectList listObjects(String bucketName, String ccsFolderPath);
	
	public CCSObjectList listObjects(ListObjectsRequestable listObjectsRequestable);
	
	public CCSObjectList listObjects(PageRequestable pageable);

	public Boolean doesObjectExist(String bucketName, String ccsObjectPath);
	
	public CCSObject getObject(String bucketName, String ccsObjectPath);
	
	public CCSObject getObject(ObjectRequestable objectRequestable);

	public CCSObjectMetadata getObjectMetadata(String bucketName, String ccsObjectPath);

	public void copyObject(String sourceBucketName, String sourceObjectPath, String destinationBucketName,
			String destinationObjectPath);

	public void putObject(String sourceFilePath, String bucketName, String ccsObjectPath);
	
	public void putObject(PutObjectRequestable putObjectRequestable);

	public void deleteObject(String bucketName, String ccsObjectPath);

	public void shutdown();

	public <T> T getNativeClient(Class<T> nativeClientClazz);

}
