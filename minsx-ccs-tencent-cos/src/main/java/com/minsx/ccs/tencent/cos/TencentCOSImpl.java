package com.minsx.ccs.tencent.cos;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import com.minsx.ccs.core.able.CCSCompleteMultipartPutRequestable;
import com.minsx.ccs.core.able.CCSGetObjectRequestable;
import com.minsx.ccs.core.able.CCSInitiateMultipartPutRequestable;
import com.minsx.ccs.core.able.CCSListObjectsRequestable;
import com.minsx.ccs.core.able.CCSPageObjectsRequestable;
import com.minsx.ccs.core.able.CCSPutObjectRequestable;
import com.minsx.ccs.core.config.TencentCOSConfig;
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
import com.qcloud.cos.COS;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;

public class TencentCOSImpl implements CCSClient {

	private COSClient cosClient;
	private TencentCOSConfig tencentCOSConfig;

	public TencentCOSImpl(TencentCOSConfig tencentCOSConfig) {
		this.tencentCOSConfig = tencentCOSConfig;
		COSCredentials cred = new BasicCOSCredentials(tencentCOSConfig.getAppId(), tencentCOSConfig.getSecretId(),
				tencentCOSConfig.getSecretKey());
		ClientConfig clientConfig = new ClientConfig(new Region(tencentCOSConfig.getRegion()));
		cosClient = new COSClient(cred, clientConfig);
	}

	@Override
	public void createBucket(String bucketName) {
		cosClient.createBucket(bucketName);
	}

	@Override
	public void deleteBucket(String bucketName) {
		cosClient.deleteBucket(bucketName);
	}

	@Override
	public Boolean doesBucketExist(String bucketName) {
		return cosClient.doesBucketExist(bucketName);
	}

	@Override
	public Boolean doesObjectExist(String bucketName, String ccsObjectPath) {
		return cosClient.doesObjectExist(bucketName, ccsObjectPath);
	}

	@Override
	public CCSObjectList listObjects(String bucketName, String ccsFolderPath) {
		return COSParseUtil.parseToCCSObjectList(cosClient.listObjects(bucketName, ccsFolderPath));
	}

	@Override
	public CCSObject getObject(String bucketName, String ccsObjectPath) {
		return COSParseUtil.parseToCCSObject(cosClient.getObject(bucketName, ccsObjectPath));
	}

	@Override
	public CCSObjectMetadata getObjectMetadata(String bucketName, String ccsObjectPath) {
		return COSParseUtil.parseToCCSObjectMetadata(cosClient.getObjectMetadata(bucketName, ccsObjectPath));
	}

	@Override
	public void copyObject(String sourceBucketName, String sourceObjectPath, String destinationBucketName,
			String destinationObjectPath) {
		cosClient.copyObject(sourceBucketName, sourceObjectPath, destinationBucketName, destinationObjectPath);
	}

	@Override
	public CCSPutObjectResponse putObject(String bucketName, String ccsObjectPath, String sourceFilePath) {
		cosClient.putObject(bucketName, ccsObjectPath, new File(sourceFilePath));
		return null;
	}

	@Override
	public void deleteObject(String bucketName, String ccsObjectPath) {
		cosClient.deleteObject(bucketName, ccsObjectPath);
	}

	@Override
	public void shutdown() {
		cosClient.shutdown();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getNativeClient(Class<T> nativeClientClass) throws NativeClientCastException {
		if (!nativeClientClass.isInstance(COS.class)) {
			throw new NativeClientCastException(cosClient.getClass(), nativeClientClass);
		}
		return (T) cosClient;
	}

	public TencentCOSConfig getTencentCOSConfig() {
		return tencentCOSConfig;
	}

	public void setTencentCOSConfig(TencentCOSConfig tencentCOSConfig) {
		this.tencentCOSConfig = tencentCOSConfig;
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
		return null;
	}

	@Override
	public CCSPutObjectResponse putObject(CCSPutObjectRequestable putObjectRequestable) {
		return null;
	}

	@Override
	public CCSPutObjectResponse putObject(String bucketName, String ccsObjectPath, InputStream inputStream) {
		cosClient.putObject(bucketName, ccsObjectPath, inputStream, null);
		return null;
	}

	@Override
	public CCSPutObjectResponse putObject(String bucketName, String ccsObjectPath, File sourceFile) {
		cosClient.putObject(bucketName, ccsObjectPath, sourceFile);
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CCSCompleteMultipartPutResponse completeMultipartUpload(
			CCSCompleteMultipartPutRequestable ccsCompleteMultipartPutRequestable) {
		// TODO Auto-generated method stub
		return null;
	}
}
