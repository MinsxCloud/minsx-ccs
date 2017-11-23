package com.minsx.ccs.aliyun.oss;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CompleteMultipartUploadRequest;
import com.aliyun.oss.model.InitiateMultipartUploadRequest;
import com.aliyun.oss.model.InitiateMultipartUploadResult;
import com.aliyun.oss.model.PartETag;
import com.aliyun.oss.model.UploadPartRequest;
import com.aliyun.oss.model.UploadPartResult;

public class OSSTest {
	
	
	public static void main(String[] args) throws FileNotFoundException {
		OSSClient client = new OSSClient("xxxxx", "xxxxx", "xxxxx");
		/*UploadFileRequest request = new UploadFileRequest("rtc-hospital", "key");
		client.uploadFile(request);
		client.getObject(null).getObjectMetadata();
		client.listObjects("").getObjectSummaries().get(0).getOwner();
		client.shutdown();
		new ListObjectsRequest();
		new OSSException("");
		client.listObjects(new ListObjectsRequest("<bucketName>"));*/
		
		
		/*final int maxKeys = 200;
		String nextMarker = null;
		ObjectListing objectListing=null;
		do {
		    objectListing = ossClient.listObjects(new ListObjectsRequest("<bucketName>").withMarker(nextMarker).withMaxKeys(maxKeys));
		    List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
		    for (OSSObjectSummary s : sums) {
		        System.out.println("\t" + s.getKey());
		    }
		    nextMarker = objectListing.getNextMarker();
		} while (objectListing.isTruncated());*/
		
		
		InitiateMultipartUploadRequest request = new InitiateMultipartUploadRequest("rtc-hospital", "A.docx");
		InitiateMultipartUploadResult result = client.initiateMultipartUpload(request);
		String uploadId = result.getUploadId();
		
		List<PartETag> partETags = new ArrayList<PartETag>();
		
		File file = new File("E:\\Temp\\A.docx");
		for (int i = 0; i < file.length(); i+=100 * 1024) {
			InputStream instream = new FileInputStream(file);
			UploadPartRequest uploadPartRequest = new UploadPartRequest();
			uploadPartRequest.setBucketName("rtc-hospital");
			uploadPartRequest.setKey("A.docx");
			uploadPartRequest.setUploadId(uploadId);
			uploadPartRequest.setInputStream(instream);
			uploadPartRequest.setPartSize(100 * 1024);
			uploadPartRequest.setPartNumber(i/(100 * 1024)+1);
			UploadPartResult uploadPartResult = client.uploadPart(uploadPartRequest);
			partETags.add(uploadPartResult.getPartETag());
		}
	
		Collections.sort(partETags, new Comparator<PartETag>() {
		    @Override
		    public int compare(PartETag p1, PartETag p2) {
		        return p1.getPartNumber() - p2.getPartNumber();
		    }
		});
		CompleteMultipartUploadRequest completeMultipartUploadRequest = 
		        new CompleteMultipartUploadRequest("rtc-hospital", "A.docx", uploadId, partETags);
		client.completeMultipartUpload(completeMultipartUploadRequest);
		
		
		
		
		
	}
	
	
}
