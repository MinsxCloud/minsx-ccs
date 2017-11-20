package com.minsx.ccs.aliyun.oss;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.ObjectListing;
import com.minsx.ccs.core.able.ListObjectsRequestable;
import com.minsx.ccs.core.able.ObjectRequestable;
import com.minsx.ccs.core.able.PageRequestable;
import com.minsx.ccs.core.able.PutObjectRequestable;
import com.minsx.ccs.core.config.AliyunOSSConfig;
import com.minsx.ccs.core.exception.NativeClientCastException;
import com.minsx.ccs.core.model.CCSBucket;
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
	public CCSObjectList listObjects(String bucketName, String ccsFolderPath) {
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

	@Override
	public CCSObjectList listObjects(PageRequestable pageable) {
		String nextMarker = null;
		CCSObjectList ccsObjectListing=null;
		ObjectListing objectListing = null;
		Integer pageIndex=0;
		do {
		    objectListing = ossClient.listObjects(new ListObjectsRequest(pageable.getBucketName()).withPrefix(pageable.getPrefix()).withMarker(nextMarker).withMaxKeys(pageable.getPageSize()));
		    ccsObjectListing= AliyunOSSParseUtil.parseToCCSObjectList(objectListing);
		    if (pageIndex==pageable.getPageIndex()) {
				break;
			}
		    nextMarker = objectListing.getNextMarker();
		    pageIndex++;
		} while (objectListing.isTruncated());
		return ccsObjectListing;
	}

	@Override
	public CCSObjectList listObjects(ListObjectsRequestable listObjectsRequestable) {
		ObjectListing objectListing = ossClient.listObjects(AliyunOSSParseUtil.parseToListObjectsRequest(listObjectsRequestable));
		return AliyunOSSParseUtil.parseToCCSObjectList(objectListing);
	}

	@Override
	public CCSObject getObject(ObjectRequestable objectRequestable) {
		return this.getObject(objectRequestable.getBucketName(), objectRequestable.getCcsPath());
	}

	@Override
	public List<CCSBucket> listCCSBuckets() {
		List<CCSBucket> ccsBuckets =ossClient.listBuckets().stream().map(ossBucket->AliyunOSSParseUtil.parseToCCSBucket(ossBucket)).collect(Collectors.toList());
		return ccsBuckets;
	}

	@Override
	public void putObject(PutObjectRequestable putObjectRequestable) {
	}
	

}
