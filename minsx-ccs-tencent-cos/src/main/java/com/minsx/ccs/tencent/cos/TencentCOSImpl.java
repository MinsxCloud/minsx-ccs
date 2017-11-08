package com.minsx.ccs.tencent.cos;

import java.io.File;

import com.minsx.ccs.core.config.TencentCOSConfig;
import com.minsx.ccs.core.exception.NativeClientTypeException;
import com.minsx.ccs.core.model.CCSObject;
import com.minsx.ccs.core.model.CCSObjectList;
import com.minsx.ccs.core.model.CCSObjectMetadata;
import com.minsx.ccs.core.service.CCSClient;
import com.qcloud.cos.COS;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;

public class TencentCOSImpl implements CCSClient{
	
	private COSClient cosClient;
	private TencentCOSConfig tencentCOSConfig;
	
	public TencentCOSImpl(TencentCOSConfig tencentCOSConfig) {
		this.tencentCOSConfig = tencentCOSConfig;
		COSCredentials cred = new BasicCOSCredentials(tencentCOSConfig.getAppId(), tencentCOSConfig.getSecretId(), tencentCOSConfig.getSecretKey());
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
	public CCSObjectList getObjectList(String bucketName, String ccsFolderPath) {
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
	public void putObject(String sourceFilePath, String bucketName, String ccsObjectPath) {
		cosClient.putObject(bucketName, ccsObjectPath, new File(sourceFilePath));
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
	public <T> T getNativeClient(Class<T> nativeClientClass) throws NativeClientTypeException {
		if (!nativeClientClass.isInstance(COS.class)) {
			throw new NativeClientTypeException(cosClient.getClass(), nativeClientClass);
		}
		return (T)cosClient;
	}

	public TencentCOSConfig getTencentCOSConfig() {
		return tencentCOSConfig;
	}

	public void setTencentCOSConfig(TencentCOSConfig tencentCOSConfig) {
		this.tencentCOSConfig = tencentCOSConfig;
	}
}
