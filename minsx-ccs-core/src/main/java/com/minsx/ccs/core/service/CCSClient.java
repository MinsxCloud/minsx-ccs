package com.minsx.ccs.core.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import com.minsx.ccs.core.able.CCSGetObjectRequestable;
import com.minsx.ccs.core.able.CCSInitiateMultipartPutRequestable;
import com.minsx.ccs.core.able.CCSListObjectsRequestable;
import com.minsx.ccs.core.able.CCSPageObjectsRequestable;
import com.minsx.ccs.core.able.CCSPutObjectRequestable;
import com.minsx.ccs.core.model.base.CCSBucket;
import com.minsx.ccs.core.model.base.CCSObject;
import com.minsx.ccs.core.model.base.CCSObjectList;
import com.minsx.ccs.core.model.base.CCSObjectMetadata;
import com.minsx.ccs.core.model.request.CCSPutPartRequest;
import com.minsx.ccs.core.model.response.CCSInitiateMultipartPutResponse;
import com.minsx.ccs.core.model.response.CCSPutObjectResponse;
import com.minsx.ccs.core.model.response.CCSPutPartResponse;

/**
 * CCSClient created by web on 2017年11月4日
 */
public interface CCSClient {

	public void createBucket(String bucketName);

	public void deleteBucket(String bucketName);

	public Boolean doesBucketExist(String bucketName);

	public List<CCSBucket> listCCSBuckets();
	
	public CCSObjectList listObjects(String bucketName, String ccsFolderPath);
	
	public CCSObjectList listObjects(CCSListObjectsRequestable listObjectsRequestable);
	
	public CCSObjectList listObjects(CCSPageObjectsRequestable pageable);

	public Boolean doesObjectExist(String bucketName, String ccsObjectPath);
	
	public CCSObject getObject(String bucketName, String ccsObjectPath);
	
	public CCSObject getObject(CCSGetObjectRequestable objectRequestable);

	public CCSObjectMetadata getObjectMetadata(String bucketName, String ccsObjectPath);

	public void copyObject(String sourceBucketName, String sourceObjectPath, String destinationBucketName,
			String destinationObjectPath);

	public CCSPutObjectResponse putObject(String bucketName, String ccsObjectPath,String sourceFilePath);
	
	public CCSPutObjectResponse putObject(String bucketName, String ccsObjectPath,File sourceFile);
	
	public CCSPutObjectResponse putObject(String bucketName, String ccsObjectPath,File sourceFile,CCSObjectMetadata ccsObjectMetadata);
	
	public CCSPutObjectResponse putObject(String bucketName,String ccsObjectPath,InputStream inputStream);
	
	public CCSPutObjectResponse putObject(String bucketName,String ccsObjectPath,InputStream inputStream,CCSObjectMetadata ccsObjectMetadata);
	
	public CCSPutObjectResponse putObject(CCSPutObjectRequestable putObjectRequestable);
	
	public CCSInitiateMultipartPutResponse initiateMultipartPut(CCSInitiateMultipartPutRequestable ccsInitiateMultipartPutRequestable);
	
	public CCSPutPartResponse putPart(CCSPutPartRequest ccsPutPartRequest);
	
	public void deleteObject(String bucketName, String ccsObjectPath);

	public void shutdown();

	public <T> T getNativeClient(Class<T> nativeClientClazz);

}
