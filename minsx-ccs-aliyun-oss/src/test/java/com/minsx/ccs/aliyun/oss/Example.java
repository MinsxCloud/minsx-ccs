package com.minsx.ccs.aliyun.oss;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.minsx.ccs.core.config.AliyunOSSConfig;
import com.minsx.ccs.core.model.model.CCSBucket;
import com.minsx.ccs.core.model.model.CCSObject;
import com.minsx.ccs.core.model.model.CCSObjectList;
import com.minsx.ccs.core.model.model.CCSObjectMetadata;
import com.minsx.ccs.core.model.model.CCSObjectSummary;
import com.minsx.ccs.core.model.model.CCSPartETag;
import com.minsx.ccs.core.model.request.CCSCompleteMultipartPutRequest;
import com.minsx.ccs.core.model.request.CCSInitiateMultipartPutRequest;
import com.minsx.ccs.core.model.request.CCSListObjectsRequest;
import com.minsx.ccs.core.model.request.CCSPageObjectsRequest;
import com.minsx.ccs.core.model.request.CCSPutObjectRqeuest;
import com.minsx.ccs.core.model.request.CCSPutPartRequest;
import com.minsx.ccs.core.model.response.CCSCompleteMultipartPutResponse;
import com.minsx.ccs.core.model.response.CCSInitiateMultipartPutResponse;
import com.minsx.ccs.core.model.response.CCSPutObjectResponse;
import com.minsx.ccs.core.model.response.CCSPutPartResponse;
import com.minsx.ccs.core.service.CCSClient;

public class Example {

	private CCSClient ccsClient;

	@Before
	public void initial() {
		AliyunOSSConfig aliyunOSSConfig = new AliyunOSSConfig();
		aliyunOSSConfig.setEndPoint("http://oss-cn-hangzhou.aliyuncs.com");
		aliyunOSSConfig.setAccessKeyId("LTAI94azdAVJieZJ");
		aliyunOSSConfig.setAccessKeySecret("SaFDFCHSGCljmleQtRdVuZHmeedvty");
		ccsClient = new AliyunOSSImpl(aliyunOSSConfig);
	}
	
	@After
	public void end() {
		ccsClient.shutdown();
	}

	@Test
	public void getObjectMetaData() {
		ccsClient.getObjectMetadata("minsx-bucket", "A.docx").getUserMetaData().forEach((key,value)->{
			System.out.println(key);
			System.out.println(value);
		});
	}
	
	@Test
	public void getObject() {
		CCSObject ccsObject = ccsClient.getObject("minsx-bucket", "A.docx");
		System.out.println(ccsObject);
	}
	
	@Test
	public void deleteObject() {
		ccsClient.deleteObject("minsx-bucket", "A.docx");
	}
	
	@Test
	public void putObjectByFilePath() {
		CCSPutObjectResponse ccsPutObjectResponse = ccsClient.putObject("minsx-bucket", "A.docx","E:\\Temp\\A.docx");
		System.out.println(JSON.toJSONString(ccsPutObjectResponse));
	}
	
	@Test
	public void putObjectByFile() {
		CCSPutObjectResponse ccsPutObjectResponse = ccsClient.putObject("minsx-bucket", "A.docx",new File("E:\\Temp\\A.docx"));
		System.out.println(JSON.toJSONString(ccsPutObjectResponse));
	}
	
	@Test
	public void listObjects() {
		CCSObjectList ccsObjectList = ccsClient.listObjects("minsx-bucket", "");
		List<CCSObjectSummary> ccsObjectSummaries =  ccsObjectList.getCcsObjectSummaries();
		ccsObjectSummaries.forEach(ccsObjectSummary->{
			System.out.println(ccsObjectSummary.getCcsPath());
		});
	}
	
	@Test
	public void listAllObjects() {
		final int maxKeys = 10;
		String nextMarker = null;
		CCSObjectList objectListing=null;
		int sum=0;
		do {
		    objectListing = ccsClient.listObjects(new CCSListObjectsRequest("minsx-bucket").withPrefix("").withMarker(nextMarker).withMaxKeys(maxKeys));
		    List<CCSObjectSummary> sums = objectListing.getCcsObjectSummaries();
		    for (CCSObjectSummary s : sums) {
		    	System.out.println(s.getCcsPath());
		    }
		    nextMarker = objectListing.getNextMarker();
		    sum++;
		} while (objectListing.isTruncated());
		 System.out.println(sum);
	}
	
	@Test
	public void listObjectsByPage() {
		CCSObjectList ccsObjectList = ccsClient.listObjects(new CCSPageObjectsRequest("minsx-bucket", "oss-log", 1, 10));
		List<CCSObjectSummary> ccsObjectSummaries =  ccsObjectList.getCcsObjectSummaries();
		ccsObjectSummaries.forEach(ccsObjectSummary->{
			System.out.println(ccsObjectSummary.getCcsPath());
		});
		ccsClient.shutdown();
	}
	
	@Test
	public void listCCSBuckets() {
		List<CCSBucket> ccsBuckets = ccsClient.listCCSBuckets();
		ccsBuckets.forEach(bucket->{
			System.out.println(bucket.getName());
		});
	}
	
	@Test
	public void putObjectByCCSPutObjectRqeuest() {
		CCSPutObjectRqeuest ccsPutObjectRqeuest = new CCSPutObjectRqeuest();
		ccsPutObjectRqeuest.setBucketName("minsx-bucket");
		ccsPutObjectRqeuest.setCcsObjectPath("A.docx");
		ccsPutObjectRqeuest.setFile(new File("E:\\Temp\\A.docx"));
		ccsClient.putObject(ccsPutObjectRqeuest);
	}
	
	
	@Test
	public void multipartPut() throws FileNotFoundException {
		List<CCSPartETag> partETags = new ArrayList<CCSPartETag>();

		CCSInitiateMultipartPutRequest ccsInitiateMultipartPutRequest = new CCSInitiateMultipartPutRequest(
				"minsx-bucket", "A.docx");
		CCSInitiateMultipartPutResponse ccsInitiateMultipartPutResponse = ccsClient
				.initiateMultipartPut(ccsInitiateMultipartPutRequest);
		String uploadId = ccsInitiateMultipartPutResponse.getUploadId();

		File file = new File("E:\\Temp\\A.docx");
		for (int i = 0; i < file.length(); i += 100 * 1024) {
			CCSPutPartRequest ccsPutPartRequest = new CCSPutPartRequest();
			ccsPutPartRequest.setBucketName("minsx-bucket");
			ccsPutPartRequest.setCcsObjectPath("A.docx");
			ccsPutPartRequest.setUploadId(uploadId);
			InputStream instream = new FileInputStream(file);
			ccsPutPartRequest.setInputStream(instream);
			ccsPutPartRequest.setPartSize(100 * 1024);
			ccsPutPartRequest.setPartNumber(i / (100 * 1024) + 1);
			CCSPutPartResponse ccsPutPartResponse = ccsClient.putPart(ccsPutPartRequest);
			partETags.add(ccsPutPartResponse.getCCSPartETag());
		}

		Collections.sort(partETags, new Comparator<CCSPartETag>() {
			@Override
			public int compare(CCSPartETag p1, CCSPartETag p2) {
				return p1.getPartNumber() - p2.getPartNumber();
			}
		});

		CCSCompleteMultipartPutRequest request = new CCSCompleteMultipartPutRequest("minsx-bucket", "A.docx", uploadId,
				partETags);
		CCSCompleteMultipartPutResponse response = ccsClient.completeMultipartUpload(request);
		System.out.println(JSON.toJSONString(response));
	}
	
	@Test
	public void createFolder() {
		ccsClient.createFolder("minsx-bucket","test");
	}
	
	@Test
	public void moveObject() {
		ccsClient.moveObject("minsx-test", "A.docx", "minsx-bucket", "A.docx");
	}
	
	@Test
	public void getObjectToFile() {
		CCSObjectMetadata objectMetadata = ccsClient.downloadObject("minsx-bucket", "A.docx", new File("E:\\Temp\\A_bck.docx"));
		System.out.println(JSON.toJSONString(objectMetadata));
	}
	
	@Test
	public void putObjectByBytes() {
		CCSPutObjectResponse ccsPutObjectResponse = ccsClient.putObject("minsx-bucket", "A.txt", "this is txt".getBytes());
		System.out.println(JSON.toJSONString(ccsPutObjectResponse));
	}
	

}
