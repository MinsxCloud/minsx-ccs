package com.minsx.ccs.aliyun.oss;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.UploadFileRequest;

public class OSSTest {
	
	public  void test() throws Throwable {
		OSSClient client = new OSSClient("", "", "");
		UploadFileRequest request = new UploadFileRequest("bucketName", "key");
		client.uploadFile(request);
		client.getObject(null).getObjectMetadata();
		client.listObjects("").getObjectSummaries().get(0).getOwner();
		client.shutdown();
		
		new OSSException("");
		
	}
	
	
}
