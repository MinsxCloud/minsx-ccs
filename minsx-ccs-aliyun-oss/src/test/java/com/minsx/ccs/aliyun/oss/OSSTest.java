package com.minsx.ccs.aliyun.oss;

import java.io.ByteArrayInputStream;
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
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CompleteMultipartUploadRequest;
import com.aliyun.oss.model.InitiateMultipartUploadRequest;
import com.aliyun.oss.model.InitiateMultipartUploadResult;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.PartETag;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.UploadFileRequest;
import com.aliyun.oss.model.UploadFileResult;
import com.aliyun.oss.model.UploadPartRequest;
import com.aliyun.oss.model.UploadPartResult;

public class OSSTest {
	
	private OSSClient ossClient;
	
	@Before
	public void initial() {
		ossClient = new OSSClient("http://oss-cn-hangzhou.aliyuncs.com", "LTAI94azdAVJieZJ", "SaFDFCHSGCljmleQtRdVuZHmeedvty");
	}
	
	@After
	public void end() {
		ossClient.shutdown();
	}
	
	@Test
	public void uploadFile() throws Throwable {
		UploadFileRequest request = new UploadFileRequest("minsx-bucket", "key");
		UploadFileResult result = ossClient.uploadFile(request);
		System.out.println(JSON.toJSONString(result));
	}
	
	@Test
	public void listAllObjects() {
		final int maxKeys = 200;
		String nextMarker = null;
		ObjectListing objectListing=null;
		do {
		    objectListing = ossClient.listObjects(new ListObjectsRequest("minsx-bucket").withMarker(nextMarker).withMaxKeys(maxKeys));
		    List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
		    for (OSSObjectSummary s : sums) {
		        System.out.println("\t" + s.getKey());
		    }
		    nextMarker = objectListing.getNextMarker();
		} while (objectListing.isTruncated());
	}
	
	@Test
	public  void multipartUpload() throws FileNotFoundException {
		InitiateMultipartUploadRequest request = new InitiateMultipartUploadRequest("minsx-bucket", "A.docx");
		InitiateMultipartUploadResult result = ossClient.initiateMultipartUpload(request);
		String uploadId = result.getUploadId();
		
		List<PartETag> partETags = new ArrayList<PartETag>();
		
		File file = new File("E:\\Temp\\A.docx");
		for (int i = 0; i < file.length(); i+=100 * 1024) {
			InputStream instream = new FileInputStream(file);
			UploadPartRequest uploadPartRequest = new UploadPartRequest();
			uploadPartRequest.setBucketName("minsx-bucket");
			uploadPartRequest.setKey("A.docx");
			uploadPartRequest.setUploadId(uploadId);
			uploadPartRequest.setInputStream(instream);
			uploadPartRequest.setPartSize(100 * 1024);
			uploadPartRequest.setPartNumber(i/(100 * 1024)+1);
			UploadPartResult uploadPartResult = ossClient.uploadPart(uploadPartRequest);
			partETags.add(uploadPartResult.getPartETag());
		}
	
		Collections.sort(partETags, new Comparator<PartETag>() {
		    @Override
		    public int compare(PartETag p1, PartETag p2) {
		        return p1.getPartNumber() - p2.getPartNumber();
		    }
		});
		CompleteMultipartUploadRequest completeMultipartUploadRequest = 
		        new CompleteMultipartUploadRequest("minsx-bucket", "A.docx", uploadId, partETags);
		ossClient.completeMultipartUpload(completeMultipartUploadRequest);
	}
	
	@Test
	public void createFolder() {
		ossClient.putObject("minsx-bucket", "www/", new ByteArrayInputStream("".getBytes()));
	}
	
	@Test
	public void name() throws Throwable {
		// 设置断点续传请求
		UploadFileRequest uploadFileRequest = new UploadFileRequest("<yourBucketName>", "<yourKey>");
		// 指定上传的本地文件
		uploadFileRequest.setUploadFile("<yourLocalFile>");
		// 指定上传并发线程数
		uploadFileRequest.setTaskNum(5);
		// 指定上传的分片大小
		uploadFileRequest.setPartSize(1 * 1024 * 1024);
		// 开启断点续传
		uploadFileRequest.setEnableCheckpoint(true);
		// 断点续传上传
		ossClient.uploadFile(uploadFileRequest);
		
		PutObjectRequest request
		
	}
	
	
}
