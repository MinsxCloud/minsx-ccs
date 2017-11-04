package org.minsx.ccs.aliyun.oss;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.UploadFileRequest;

public class App {
	
	public  void test() throws Throwable {
		OSSClient client = new OSSClient("", "", "");
		UploadFileRequest request = new UploadFileRequest("bucketName", "key");
		client.uploadFile(request);
		client.shutdown();
	}
	
	
}
