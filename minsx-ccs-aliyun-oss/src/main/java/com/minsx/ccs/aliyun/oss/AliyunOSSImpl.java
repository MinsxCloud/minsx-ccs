package com.minsx.ccs.aliyun.oss;

import java.io.File;

import com.aliyun.oss.OSSClient;
import com.minsx.ccs.core.config.AliyunOSSConfig;
import com.minsx.ccs.core.exception.NativeClientCastException;
import com.minsx.ccs.core.model.CCSObject;
import com.minsx.ccs.core.model.CCSObjectList;
import com.minsx.ccs.core.model.CCSObjectMetadata;
import com.minsx.ccs.core.service.CCSClient;

public class AliyunOSSImpl implements CCSClient {

	private OSSClient ossClient;
	private AliyunOSSConfig aliyunOSSConfig;

	public AliyunOSSImpl(AliyunOSSConfig aliyunOSSConfig) {
		this.aliyunOSSConfig = aliyunOSSConfig;
		this.ossClient = new OSSClient(aliyunOSSConfig.getEndPoint(), aliyunOSSConfig.getAccessKeyId(),
				aliyunOSSConfig.getAccessKeySecret());
	}

	@Override
	public void createBucket(String bucketName) {
		ossClient.createBucket(bucketName);
	}

	@Override
	public void deleteBucket(String bucketName) {
		ossClient.deleteBucket(bucketName);
	}

	@Override
	public Boolean doesBucketExist(String bucketName) {
		return ossClient.doesBucketExist(bucketName);
	}

	@Override
	public Boolean doesObjectExist(String bucketName, String ccsObjectPath) {
		return ossClient.doesObjectExist(bucketName, ccsObjectPath);
	}

	@Override
	public CCSObjectList getObjectList(String bucketName, String ccsFolderPath) {
		return AliyunOSSParseUtil.parseToCCSObjectList(ossClient.listObjects(bucketName, ccsFolderPath));
	}

	@Override
	public CCSObject getObject(String bucketName, String ccsObjectPath) {
		return AliyunOSSParseUtil.parseToCCSObject(ossClient.getObject(bucketName, ccsObjectPath));
	}

	@Override
	public CCSObjectMetadata getObjectMetadata(String bucketName, String ccsObjectPath) {
		return AliyunOSSParseUtil.parseToCCSObjectMetadata(ossClient.getObjectMetadata(bucketName, ccsObjectPath));
	}

	@Override
	public void copyObject(String sourceBucketName, String sourceObjectPath, String destinationBucketName,
			String destinationObjectPath) {
		ossClient.copyObject(sourceBucketName, sourceObjectPath, destinationBucketName, destinationObjectPath);
	}

	@Override
	public void putObject(String sourceFilePath, String bucketName, String ccsObjectPath) {
		ossClient.putObject(bucketName, ccsObjectPath, new File(sourceFilePath));
	}

	@Override
	public void deleteObject(String bucketName, String ccsObjectPath) {
		ossClient.deleteObject(bucketName, ccsObjectPath);
	}

	@Override
	public void shutdown() {
		ossClient.shutdown();
	}

	public AliyunOSSConfig getAliyunOSSConfig() {
		return aliyunOSSConfig;
	}

	public void setAliyunOSSConfig(AliyunOSSConfig aliyunOSSConfig) {
		this.aliyunOSSConfig = aliyunOSSConfig;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getNativeClient(Class<T> nativeClientClass) throws NativeClientCastException {
		if (!nativeClientClass.isInstance(OSSClient.class)) {
			throw new NativeClientCastException(ossClient.getClass(), nativeClientClass);
		}
		return (T)ossClient;
	}

	

}
