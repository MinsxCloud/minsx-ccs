package com.minsx.ccs.aliyun.oss;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.UploadFileRequest;

public class OSSTest {
	
	public  void test() throws Throwable {
		OSSClient client = new OSSClient("", "", "");
		UploadFileRequest request = new UploadFileRequest("bucketName", "key");
		client.uploadFile(request);
		client.getObject(null).getObjectMetadata();
		client.listObjects("").getObjectSummaries().get(0).getOwner();
		client.shutdown();
		new ListObjectsRequest();
		new OSSException("");
		client.listObjects(new ListObjectsRequest("<bucketName>"));
		
		
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
		
		
	}
	
	
}
