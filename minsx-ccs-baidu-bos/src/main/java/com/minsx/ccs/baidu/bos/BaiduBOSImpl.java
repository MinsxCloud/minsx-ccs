package com.minsx.ccs.baidu.bos;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.minsx.ccs.core.able.CCSListObjectsRequestable;
import com.minsx.ccs.core.able.CCSCompleteMultipartPutRequestable;
import com.minsx.ccs.core.able.CCSGetObjectRequestable;
import com.minsx.ccs.core.able.CCSInitiateMultipartPutRequestable;
import com.minsx.ccs.core.able.CCSPageObjectsRequestable;
import com.minsx.ccs.core.able.CCSPutObjectRequestable;
import com.minsx.ccs.core.config.BaiduBOSConfig;
import com.minsx.ccs.core.exception.NativeClientCastException;
import com.minsx.ccs.core.model.model.CCSBucket;
import com.minsx.ccs.core.model.model.CCSObject;
import com.minsx.ccs.core.model.model.CCSObjectList;
import com.minsx.ccs.core.model.model.CCSObjectMetadata;
import com.minsx.ccs.core.model.request.CCSPutPartRequest;
import com.minsx.ccs.core.model.response.CCSCompleteMultipartPutResponse;
import com.minsx.ccs.core.model.response.CCSInitiateMultipartPutResponse;
import com.minsx.ccs.core.model.response.CCSPutObjectResponse;
import com.minsx.ccs.core.model.response.CCSPutPartResponse;
import com.minsx.ccs.core.service.CCSClient;

public class BaiduBOSImpl implements CCSClient {

	private BaiduBOSConfig baiduBOSConfig;
	private BosClient bosClient;

	public BaiduBOSImpl(BaiduBOSConfig baiduBOSConfig) {
		this.baiduBOSConfig = baiduBOSConfig;
		BosClientConfiguration config = new BosClientConfiguration();
		config.setCredentials(
				new DefaultBceCredentials(baiduBOSConfig.getAccessKeyId(), baiduBOSConfig.getAccessKeySecret()));
		config.setEndpoint(baiduBOSConfig.getEndPoint());
		bosClient = new BosClient(config);
	}

	@Override
	public void createBucket(String bucketName) {
		bosClient.createBucket(bucketName);
	}

	@Override
	public void deleteBucket(String bucketName) {
		bosClient.deleteBucket(bucketName);
	}

	@Override
	public Boolean doesBucketExist(String bucketName) {
		return bosClient.doesBucketExist(bucketName);
	}

	@Override
	public Boolean doesObjectExist(String bucketName, String ccsObjectPath) {
		Boolean isExist = true;
		try {
			bosClient.getObjectMetadata(bucketName, ccsObjectPath);
		} catch (Exception e) {
			isExist = false;
		}
		return isExist;
	}

	@Override
	public CCSObjectList listObjects(String bucketName, String ccsFolderPath) {
		return BaiduBOSParseUtil.parseToCCSObjectList(bosClient.listObjects(bucketName, ccsFolderPath));
	}

	@Override
	public CCSObject getObject(String bucketName, String ccsObjectPath) {
		return BaiduBOSParseUtil.parseToCCSObject(bosClient.getObject(bucketName, ccsObjectPath));
	}

	@Override
	public CCSObjectMetadata getObjectMetadata(String bucketName, String ccsObjectPath) {
		return BaiduBOSParseUtil.parseToCCSObjectMetadata(bosClient.getObjectMetadata(bucketName, ccsObjectPath));
	}

	@Override
	public void copyObject(String sourceBucketName, String sourceObjectPath, String destinationBucketName,
			String destinationObjectPath) {
		bosClient.copyObject(sourceBucketName, sourceObjectPath, destinationBucketName, destinationObjectPath);
	}

	@Override
	public CCSPutObjectResponse putObject(String bucketName, String ccsObjectPath, String sourceFilePath) {
		bosClient.putObject(bucketName, ccsObjectPath, new File(sourceFilePath));
		return null;
	}

	@Override
	public void deleteObject(String bucketName, String ccsObjectPath) {
		bosClient.deleteObject(bucketName, ccsObjectPath);
	}

	@Override
	public void shutdown() {
		bosClient.shutdown();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getNativeClient(Class<T> nativeClientClass) {
		if (!nativeClientClass.isInstance(BosClient.class)) {
			throw new NativeClientCastException(bosClient.getClass(), nativeClientClass);
		}
		return (T) bosClient;
	}

	public BaiduBOSConfig getBaiduBOSConfig() {
		return baiduBOSConfig;
	}

	public void setBaiduBOSConfig(BaiduBOSConfig baiduBOSConfig) {
		this.baiduBOSConfig = baiduBOSConfig;
	}

	@Override
	public CCSObjectList listObjects(CCSPageObjectsRequestable pageable) {
		return null;
	}

	@Override
	public CCSObjectList listObjects(CCSListObjectsRequestable listObjectsRequestable) {
		return null;
	}

	@Override
	public CCSObject getObject(CCSGetObjectRequestable objectRequestable) {
		return null;
	}

	@Override
	public List<CCSBucket> listCCSBuckets() {
		List<CCSBucket> ccsBuckets =bosClient.listBuckets().getBuckets().stream().map(ossBucket->BaiduBOSParseUtil.parseToCCSBucket(ossBucket)).collect(Collectors.toList());
		return ccsBuckets;
	}

	@Override
	public CCSPutObjectResponse putObject(CCSPutObjectRequestable putObjectRequestable) {
		return null;
	}

	@Override
	public CCSPutObjectResponse putObject(String bucketName, String ccsObjectPath, InputStream inputStream) {
		bosClient.putObject(bucketName, ccsObjectPath, inputStream);
		return null;
	}

	@Override
	public CCSPutObjectResponse putObject(String bucketName, String ccsObjectPath, File sourceFile) {
		bosClient.putObject(bucketName, ccsObjectPath, sourceFile);
		return null;
	}

	@Override
	public CCSPutObjectResponse putObject(String bucketName, String ccsObjectPath, File sourceFile,
			CCSObjectMetadata ccsObjectMetadata) {
		return null;
	}

	@Override
	public CCSPutObjectResponse putObject(String bucketName, String ccsObjectPath, InputStream inputStream,
			CCSObjectMetadata ccsObjectMetadata) {
		return null;
	}

	@Override
	public CCSInitiateMultipartPutResponse initiateMultipartPut(
			CCSInitiateMultipartPutRequestable ccsInitiateMultipartPutRequestable) {
		return null;
	}

	@Override
	public CCSPutPartResponse putPart(CCSPutPartRequest ccsPutObjectRequestable) {
		return null;
	}

	@Override
	public CCSCompleteMultipartPutResponse completeMultipartUpload(
			CCSCompleteMultipartPutRequestable ccsCompleteMultipartPutRequestable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createFolder(String bucketName, String folderName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CCSObjectMetadata downloadObject(String bucketName, String ccsObjectPath, File localFile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CCSPutObjectResponse putObject(String bucketName, String ccsObjectPath, byte[] bytes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void moveObject(String sourceBucketName, String sourceObjectPath, String destinationBucketName,
			String destinationObjectPath) {
		// TODO Auto-generated method stub
		
	}

}
