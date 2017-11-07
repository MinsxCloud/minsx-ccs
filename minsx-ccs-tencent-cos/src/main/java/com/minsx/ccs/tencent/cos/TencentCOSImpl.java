package com.minsx.ccs.tencent.cos;

import com.minsx.ccs.core.config.TencentCOSConfig;
import com.minsx.ccs.core.model.CCSObject;
import com.minsx.ccs.core.model.CCSObjectList;
import com.minsx.ccs.core.model.CCSObjectMetadata;
import com.minsx.ccs.core.service.CCSClient;
import com.qcloud.cos.COS;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.sign.Credentials;

import minsx.ccs.core.exception.NativeClientTypeException;

public class TencentCOSImpl implements CCSClient{
	
	private COSClient cosClient;
	private TencentCOSConfig tencentCOSConfig;
	
	public TencentCOSImpl(TencentCOSConfig tencentCOSConfig) {
		this.tencentCOSConfig = tencentCOSConfig;
        Credentials credentials = new Credentials(tencentCOSConfig.getAppId(), tencentCOSConfig.getSecretId(), tencentCOSConfig.getSecretKey());
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setRegion(tencentCOSConfig.getRegion());
        cosClient = new COSClient(clientConfig, credentials);
	}

	@Override
	public void createBucket(String bucketName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBucket(String bucketName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean doesBucketExist(String bucketName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean doesObjectExist(String bucketName, String ccsObjectPath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CCSObjectList getObjectList(String bucketName, String ccsFolderPath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CCSObject getObject(String bucketName, String ccsObjectPath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CCSObjectMetadata getObjectMetadata(String bucketName, String ccsObjectPath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void copyObject(String sourceBucketName, String sourceObjectPath, String destinationBucketName,
			String destinationObjectPath) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void putObject(String sourceFilePath, String bucketName, String ccsObjectPath) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteObject(String bucketName, String ccsObjectPath) {
		// TODO Auto-generated method stub
		
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
