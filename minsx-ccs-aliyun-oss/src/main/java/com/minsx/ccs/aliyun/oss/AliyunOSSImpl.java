package com.minsx.ccs.aliyun.oss;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CompleteMultipartUploadRequest;
import com.aliyun.oss.model.CompleteMultipartUploadResult;
import com.aliyun.oss.model.InitiateMultipartUploadRequest;
import com.aliyun.oss.model.InitiateMultipartUploadResult;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.PartETag;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyun.oss.model.UploadPartRequest;
import com.aliyun.oss.model.UploadPartResult;
import com.minsx.ccs.core.able.CCSCompleteMultipartPutRequestable;
import com.minsx.ccs.core.able.CCSGetObjectRequestable;
import com.minsx.ccs.core.able.CCSInitiateMultipartPutRequestable;
import com.minsx.ccs.core.able.CCSListObjectsRequestable;
import com.minsx.ccs.core.able.CCSPageObjectsRequestable;
import com.minsx.ccs.core.able.CCSPutObjectRequestable;
import com.minsx.ccs.core.config.AliyunOSSConfig;
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
	public CCSPutObjectResponse putObject( String bucketName, String ccsObjectPath,String sourceFilePath) {
		PutObjectResult result =	ossClient.putObject(bucketName, ccsObjectPath, new File(sourceFilePath));
		return AliyunOSSParseUtil.parseToCCSPutObjectResponse(result);
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
	public CCSObjectList listObjects(CCSPageObjectsRequestable pageable) {
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
	public CCSObjectList listObjects(CCSListObjectsRequestable listObjectsRequestable) {
		ObjectListing objectListing = ossClient.listObjects(AliyunOSSParseUtil.parseToListObjectsRequest(listObjectsRequestable));
		return AliyunOSSParseUtil.parseToCCSObjectList(objectListing);
	}

	@Override
	public CCSObject getObject(CCSGetObjectRequestable objectRequestable) {
		return this.getObject(objectRequestable.getBucketName(), objectRequestable.getCcsObjectPath());
	}

	@Override
	public List<CCSBucket> listCCSBuckets() {
		List<CCSBucket> ccsBuckets =ossClient.listBuckets().stream().map(ossBucket->AliyunOSSParseUtil.parseToCCSBucket(ossBucket)).collect(Collectors.toList());
		return ccsBuckets;
	}

	@Override
	public CCSPutObjectResponse putObject(CCSPutObjectRequestable putObjectRequestable) {
		PutObjectResult result =	ossClient.putObject(AliyunOSSParseUtil.parseToPutObjectRequest(putObjectRequestable));
		return AliyunOSSParseUtil.parseToCCSPutObjectResponse(result);
	}

	@Override
	public CCSPutObjectResponse putObject(String bucketName, String ccsObjectPath, InputStream inputStream) {
		PutObjectResult result =	ossClient.putObject(bucketName, ccsObjectPath, inputStream);
		return AliyunOSSParseUtil.parseToCCSPutObjectResponse(result);
	}

	@Override
	public CCSPutObjectResponse putObject(String bucketName, String ccsObjectPath, File sourceFile) {
		PutObjectResult result =	ossClient.putObject(bucketName, ccsObjectPath, sourceFile);
		return AliyunOSSParseUtil.parseToCCSPutObjectResponse(result);
	}

	@Override
	public CCSPutObjectResponse putObject(String bucketName, String ccsObjectPath, File sourceFile,
			CCSObjectMetadata ccsObjectMetadata) {
		PutObjectResult result =	ossClient.putObject(bucketName, ccsObjectPath, sourceFile, AliyunOSSParseUtil.parseToObjectMetadata(ccsObjectMetadata));
		return AliyunOSSParseUtil.parseToCCSPutObjectResponse(result);
	}

	@Override
	public CCSPutObjectResponse putObject(String bucketName, String ccsObjectPath, InputStream inputStream,
			CCSObjectMetadata ccsObjectMetadata) {
		PutObjectResult result =	ossClient.putObject(bucketName, ccsObjectPath, inputStream, AliyunOSSParseUtil.parseToObjectMetadata(ccsObjectMetadata));
		return AliyunOSSParseUtil.parseToCCSPutObjectResponse(result);
	}

	@Override
	public CCSInitiateMultipartPutResponse initiateMultipartPut(
			CCSInitiateMultipartPutRequestable ccsInitiateMultipartPutRequestable) {
		InitiateMultipartUploadRequest request = new InitiateMultipartUploadRequest(ccsInitiateMultipartPutRequestable.getBucketName(),ccsInitiateMultipartPutRequestable.getCcsObjectPath());
		InitiateMultipartUploadResult result = ossClient.initiateMultipartUpload(request);
		System.out.println(JSON.toJSONString(result));
		return new CCSInitiateMultipartPutResponse(result.getBucketName(),result.getKey(),result.getUploadId());
	}

	@Override
	public CCSPutPartResponse putPart(CCSPutPartRequest ccsPutObjectRequestable) {
		UploadPartRequest uploadPartRequest = new UploadPartRequest();
		uploadPartRequest.setBucketName(ccsPutObjectRequestable.getBucketName());
		uploadPartRequest.setKey(ccsPutObjectRequestable.getCcsObjectPath());
		uploadPartRequest.setUploadId(ccsPutObjectRequestable.getUploadId());
		uploadPartRequest.setInputStream(ccsPutObjectRequestable.getInputStream());
		uploadPartRequest.setPartSize(ccsPutObjectRequestable.getPartSize());
		uploadPartRequest.setPartNumber(ccsPutObjectRequestable.getPartNumber());
		UploadPartResult uploadPartResult = ossClient.uploadPart(uploadPartRequest);
		return AliyunOSSParseUtil.parseToCCSPutPartResponse(uploadPartResult);
	}

	@Override
	public CCSCompleteMultipartPutResponse completeMultipartUpload(
			CCSCompleteMultipartPutRequestable ccsCompleteMultipartPutRequestable) {
		List<PartETag> partETags = ccsCompleteMultipartPutRequestable.getCCSPartETags().stream().map( ccsPartETag->AliyunOSSParseUtil.parseToPartETag(ccsPartETag)).collect(Collectors.toList());
		CompleteMultipartUploadRequest completeMultipartUploadRequest = 
		        new CompleteMultipartUploadRequest(ccsCompleteMultipartPutRequestable.getBucketName(), ccsCompleteMultipartPutRequestable.getCcsObjectPath(), ccsCompleteMultipartPutRequestable.getUploadId(),partETags);
		CompleteMultipartUploadResult ossResponse =  ossClient.completeMultipartUpload(completeMultipartUploadRequest);
		return AliyunOSSParseUtil.parseToCCSCompleteMultipartPutResponse(ossResponse);
	}


}
