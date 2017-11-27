package com.minsx.ccs.core.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import com.minsx.ccs.core.able.CCSCompleteMultipartPutRequestable;
import com.minsx.ccs.core.able.CCSGetObjectRequestable;
import com.minsx.ccs.core.able.CCSInitiateMultipartPutRequestable;
import com.minsx.ccs.core.able.CCSListObjectsRequestable;
import com.minsx.ccs.core.able.CCSPageObjectsRequestable;
import com.minsx.ccs.core.able.CCSPutObjectRequestable;
import com.minsx.ccs.core.model.model.CCSBucket;
import com.minsx.ccs.core.model.model.CCSObject;
import com.minsx.ccs.core.model.model.CCSObjectList;
import com.minsx.ccs.core.model.model.CCSObjectMetadata;
import com.minsx.ccs.core.model.request.CCSPutPartRequest;
import com.minsx.ccs.core.model.response.CCSCompleteMultipartPutResponse;
import com.minsx.ccs.core.model.response.CCSInitiateMultipartPutResponse;
import com.minsx.ccs.core.model.response.CCSPutObjectResponse;
import com.minsx.ccs.core.model.response.CCSPutPartResponse;

/**
 * CCSClient created by Joker on 2017年11月4日
 */
public interface CCSClient {

	/**
	 * Create the bucket by bucket's name
	 * @param bucketName bucket's name
	 */
	public void createBucket(String bucketName);

	/**
	 * Delete the bucket by bucket's name
	 * @param bucketName
	 */
	public void deleteBucket(String bucketName);

	/**
	 * Check if there is such a bucket by name
	 * @param bucketName bucket's name
	 * @return whether exist
	 */
	public Boolean doesBucketExist(String bucketName);

	/**
	 * List All buckets
	 * @return buckets' list
	 */
	public List<CCSBucket> listCCSBuckets();
	
	/**
	 * Create Folder by bucket's name and folder's name
	 * @param bucketName bucket's name
	 * @param folderName folder's name
	 */
	public void createFolder(String bucketName, String folderName);
	
	/**
	 * List top 100 objects in the bucket by bucket's name and object's prefix
	 * @param bucketName bucket's name
	 * @param prefix object's prefix
	 * @return top 100 objects list
	 */
	public CCSObjectList listObjects(String bucketName, String prefix);
	
	/**
	 * List objects of custom number in the bucket by CCSListObjectsRequestable
	 * @param ccslistObjectsRequestable list objects request
	 * @return objects list of custom number
	 */
	public CCSObjectList listObjects(CCSListObjectsRequestable ccslistObjectsRequestable);
	
	/**
	 * List specified page's objects in the bucket  by CCSPageObjectsRequestable
	 * @param ccsPageObjectsRequestable page objects request
	 * @return specified page's objects
	 */
	public CCSObjectList listObjects(CCSPageObjectsRequestable ccsPageObjectsRequestable);

	/**
	 * Check if there is such a object by bucket's name and object's path(key)
	 * @param bucketName bucket's name
	 * @param ccsObjectPath object's path(key)
	 * @return whether exist
	 */
	public Boolean doesObjectExist(String bucketName, String ccsObjectPath);
	
	/**
	 * Get object by bucket's name and object's path(key)
	 * @param bucketName bucket's name
	 * @param ccsObjectPath object's path(key)
	 * @return specified object
	 */
	public CCSObject getObject(String bucketName, String ccsObjectPath);
	
	/**
	 * Download object by bucket's name,object's path(key) and destination file
	 * @param bucketName bucket's name
	 * @param ccsObjectPath object's path(key) 
	 * @param localFile which file to download
	 * @return object's meta data
	 */
	public CCSObjectMetadata downloadObject(String bucketName, String ccsObjectPath,File localFile);
	
	/**
	 * Get specified object by CCSGetObjectRequestable
	 * @param objectRequestable get object request
	 * @return specified object
	 */
	public CCSObject getObject(CCSGetObjectRequestable ccsGetObjectRequestable);

	/**
	 * Get specified object's meta data by bucket's name and object's path(key)
	 * @param bucketName bucket's name
	 * @param ccsObjectPath object's path(key)
	 * @return specified object's meta data
	 */
	public CCSObjectMetadata getObjectMetadata(String bucketName, String ccsObjectPath);

	/**
	 * Put object by bucket's name , object's path(key) and source file path
	 * @param bucketName bucket's name
	 * @param ccsObjectPath object's path(key)
	 * @param sourceFilePath source file path
	 * @return response of putting object 
	 */
	public CCSPutObjectResponse putObject(String bucketName, String ccsObjectPath,String sourceFilePath);
	
	/**
	 * Put object by bucket's name , object's path(key) and source file
	 * @param bucketName bucket's name
	 * @param ccsObjectPath object's path(key)
	 * @param sourceFile source file
	 * @return response of putting object 
	 */
	public CCSPutObjectResponse putObject(String bucketName, String ccsObjectPath,File sourceFile);
	
	/**
	 * Put object by bucket's name,object's path(key),source file and object meta data
	 * @param bucketName bucket's name
	 * @param ccsObjectPath object's path(key)
	 * @param sourceFile source file
	 * @param ccsObjectMetadata object's meta data
	 * @return response of putting object 
	 */
	public CCSPutObjectResponse putObject(String bucketName, String ccsObjectPath,File sourceFile,CCSObjectMetadata ccsObjectMetadata);
	
	/**
	 * Put object by bucket's name,object's path(key) and source file input stream
	 * @param bucketName bucket's name
	 * @param ccsObjectPath object's path(key)
	 * @param inputStream source file input stream
	 * @return response of putting object 
	 */
	public CCSPutObjectResponse putObject(String bucketName,String ccsObjectPath,InputStream inputStream);
	
	/**
	 * Put object by bucket's name,object's path(key) and bytes
	 * @param bucketName bucket's name
	 * @param ccsObjectPath object's path(key)
	 * @param bytes bytes need put 
	 * @return response of putting object 
	 */
	public CCSPutObjectResponse putObject(String bucketName,String ccsObjectPath,byte[] bytes);
	
	/**
	 * Put object by bucket's name,object's path(key),source file input stream and object meta data
	 * @param bucketName bucket's name
	 * @param ccsObjectPath object's path(key)
	 * @param inputStream source file input stream
	 * @param ccsObjectMetadata object meta data
	 * @return response of putting object 
	 */
	public CCSPutObjectResponse putObject(String bucketName,String ccsObjectPath,InputStream inputStream,CCSObjectMetadata ccsObjectMetadata);
	
	/**
	 * Put object by CCSPutObjectRequestable
	 * @param putObjectRequestable put object request
	 * @return response of putting object 
	 */
	public CCSPutObjectResponse putObject(CCSPutObjectRequestable putObjectRequestable);
	
	/**
	 * Initializing multiple part put request by CCSInitiateMultipartPutRequestable
	 * @param ccsInitiateMultipartPutRequestable initializing multiple put request
	 * @return initializing multiple put request's response
	 */
	public CCSInitiateMultipartPutResponse initiateMultipartPut(CCSInitiateMultipartPutRequestable ccsInitiateMultipartPutRequestable);
	
	/**
	 * Put Object part after initiateMultipartPut operation by CCSPutPartRequest
	 * @param ccsPutPartRequest putting object'part request
	 * @return  putting object'part request's response
	 */
	public CCSPutPartResponse putPart(CCSPutPartRequest ccsPutPartRequest);
	
	/**
	 * Complete multiple part upload's request by CCSCompleteMultipartPutRequestable
	 * @param ccsCompleteMultipartPutRequestable complete multiple part upload's request
	 * @return complete multiple part upload's request's response
	 */
	public CCSCompleteMultipartPutResponse completeMultipartUpload(CCSCompleteMultipartPutRequestable ccsCompleteMultipartPutRequestable);
	
	/**
	 * Copy object by source bucket's name,object's path(key) and destination bucket's name,object's path(key)
	 * @param sourceBucketName source bucket's name 
	 * @param sourceObjectPath source object's path(key)
	 * @param destinationBucketName destination bucket's name,
	 * @param destinationObjectPath destination object's path(key)
	 */
	public void copyObject(String sourceBucketName, String sourceObjectPath, String destinationBucketName,String destinationObjectPath);
	
	/**
	 * Move object by source bucket's name,object's path(key) and destination bucket's name,object's path(key)
	 * @param sourceBucketName source bucket's name 
	 * @param sourceObjectPath source object's path(key)
	 * @param destinationBucketName destination bucket's name,
	 * @param destinationObjectPath destination object's path(key)
	 */
	public void moveObject(String sourceBucketName, String sourceObjectPath, String destinationBucketName,String destinationObjectPath);
			
	/**
	 * Delete object by bucket's name and object's path(key)
	 * @param bucketName bucket's name where is the object
	 * @param ccsObjectPath need be deleted object's path(key)
	 */
	public void deleteObject(String bucketName, String ccsObjectPath);

	/**
	 * Shut down client
	 */
	public void shutdown();

	/**
	 * Get native client's instance by native client's type class
	 * @param nativeClientClazz  native client's class
	 * @return native client's instance
	 */
	public <T> T getNativeClient(Class<T> nativeClientClazz);

}
