package com.minsx.ccs.aliyun.oss;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.minsx.ccs.core.config.AliyunOSSConfig;
import com.minsx.ccs.core.exception.NativeClientCastException;
import com.minsx.ccs.core.model.base.CCSPartETag;
import com.minsx.ccs.core.model.request.CCSInitiateMultipartPutRequest;
import com.minsx.ccs.core.model.request.CCSPutPartRequest;
import com.minsx.ccs.core.model.response.CCSInitiateMultipartPutResponse;
import com.minsx.ccs.core.model.response.CCSPutPartResponse;
import com.minsx.ccs.core.service.CCSClient;

public class CCSTest {
	
	public static void main(String[] args) throws NativeClientCastException, FileNotFoundException {
		AliyunOSSConfig aliyunOSSConfig = new AliyunOSSConfig();
		aliyunOSSConfig.setEndPoint("xxxxx");
		aliyunOSSConfig.setAccessKeyId("xxxxx");
		aliyunOSSConfig.setAccessKeySecret("xxxxx");
		CCSClient ccsClient = new AliyunOSSImpl(aliyunOSSConfig);
		
		/*ccsClient.getObjectMetadata("rtc-hospital", "advinst汉化.chm").getUserMetaData().forEach((key,value)->{
			System.out.println(key);
			System.out.println(value);
		});*/
		
		/*CCSObject ccsObject = ccsClient.getObject("rtc-hospital", "advinst汉化.chm");
		System.out.println(ccsObject.getCcsObjectMetadata().getETag());*/
		
		//ccsClient.deleteObject("rtc-hospital", "advinst汉化.chm");
		//ccsClient.putObject("E:\\document\\编程文档\\advinst汉化.chm", "rtc-hospital", "advinst汉化.chm");
		//ccsClient.getObjectMetadata("rtc-hospital", "advinst汉化.chm").getUserMetaData().put("password", "Ss123456");
		
		
		/*CCSObjectList ccsObjectList = ccsClient.getObjectList("rtc-hospital", "hospital");
		List<CCSObjectSummary> ccsObjectSummaries =  ccsObjectList.getCcsObjectSummaries();
		ccsObjectSummaries.forEach(ccsObjectSummary->{
			System.out.println(ccsObjectSummary.getCcsPath());
		});*/
		
		
		/*final int maxKeys = 10;
		String nextMarker = null;
		CCSObjectList objectListing=null;
		int sum=0;
		do {
		    objectListing = ccsClient.listObjects(new CCSListObjectsRequest("rtc-hospital").withPrefix("oss-log").withMarker(nextMarker).withMaxKeys(maxKeys));
		    List<CCSObjectSummary> sums = objectListing.getCcsObjectSummaries();
		    for (CCSObjectSummary s : sums) {
		    	System.out.println(s.getCcsPath());
		    }
		    nextMarker = objectListing.getNextMarker();
		    sum++;
		} while (objectListing.isTruncated());
		 System.out.println(sum);*/
		
		
		/*CCSObjectList ccsObjectList = ccsClient.listObjects(new PageRequest("rtc-hospital", "oss-log", 1, 10));
		List<CCSObjectSummary> ccsObjectSummaries =  ccsObjectList.getCcsObjectSummaries();
		ccsObjectSummaries.forEach(ccsObjectSummary->{
			System.out.println(ccsObjectSummary.getCcsPath());
		});
		ccsClient.shutdown();*/
		
		/*List<CCSBucket> ccsBuckets = ccsClient.listCCSBuckets();
		ccsBuckets.forEach(bucket->{
			System.out.println(bucket.getName());
		});*/
		
		/*CCSPutObjectRqeuest ccsPutObjectRqeuest = new CCSPutObjectRqeuest();
		ccsPutObjectRqeuest.setBucketName("rtc-hospital");
		ccsPutObjectRqeuest.setCcsObjectPath("www.txt");
		ccsPutObjectRqeuest.setFile(new File("E:\\Temp\\新建文件夹\\minsx-document\\Java文档\\spring-boot-中文参考指南.pdf"));
		ccsClient.putObject(ccsPutObjectRqeuest);*/
		
		/*CCSPutObjectResponse ccsPutObjectResponse = ccsClient.putObject("rtc-hospital", "www.txt","E:\\Temp\\www.txt");
		System.out.println(JSON.toJSONString(ccsPutObjectResponse));*/
		
		List<CCSPartETag> partETags = new ArrayList<CCSPartETag>();
		
		CCSInitiateMultipartPutRequest ccsInitiateMultipartPutRequest = new CCSInitiateMultipartPutRequest("rtc-hospital", "A.docx");
		CCSInitiateMultipartPutResponse ccsInitiateMultipartPutResponse = ccsClient.initiateMultipartPut(ccsInitiateMultipartPutRequest);
		String uploadId =ccsInitiateMultipartPutResponse.getUploadId();
		
		System.out.println(uploadId);
		
		File file = new File("E:\\Temp\\A.docx");
		for (int i = 0; i < file.length(); i+=100 * 1024) {
			CCSPutPartRequest ccsPutPartRequest = new CCSPutPartRequest();
			ccsPutPartRequest.setBucketName("rtc-hospital");
			ccsPutPartRequest.setCcsObjectPath("A.docx");
			ccsPutPartRequest.setUploadId(uploadId);
			InputStream instream = new FileInputStream(file);
			ccsPutPartRequest.setInputStream(instream);
			ccsPutPartRequest.setPartSize(100 * 1024);
			ccsPutPartRequest.setPartNumber(i/(100 * 1024)+1);
			CCSPutPartResponse ccsPutPartResponse = ccsClient.putPart(ccsPutPartRequest);
			partETags.add(ccsPutPartResponse.getCCSPartETag());
		}
		
		
		
		
	}

}
