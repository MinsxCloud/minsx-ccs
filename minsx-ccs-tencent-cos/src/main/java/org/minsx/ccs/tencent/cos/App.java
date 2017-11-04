package org.minsx.ccs.tencent.cos;

import org.apache.log4j.Logger;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.request.UploadFileRequest;
import com.qcloud.cos.sign.Credentials;

public class App {
	
	private final static Logger LOGGER = Logger.getLogger(App.class);
	
	public static void main(String[] args) {
		long appId = 1251505282;
		String secretId = "AKIDCXrebct3IXl0HXrvcHHLYp6UpKwMlFY0";
		String secretKey = "kaQEEFIMRYhr2OqImHoR5jQbgow7KYn2";
		// 设置要操作的bucket
		String bucketName = "minsx";
		// 初始化秘钥信息
		Credentials cred = new Credentials(appId, secretId, secretKey);
		// 初始化客户端配置
		ClientConfig clientConfig = new ClientConfig();
		// 设置bucket所在的区域，比如华南园区：gz； 华北园区：tj；华东园区：sh ；
		clientConfig.setRegion("sh");
		// 初始化cosClient
		COSClient cosClient = new COSClient(clientConfig, cred);
		UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName,
				"/腾讯-COS.pdf","E:\\Document\\编程文档\\minsx-ccs\\腾讯-COS.pdf");
		String uploadFileRet = cosClient.uploadFile(uploadFileRequest);
		
		/*DelFileRequest delFileRequest = new DelFileRequest(bucketName, "/腾讯-COS.pdf");
		String result = cosClient.delFile(delFileRequest);*/
		LOGGER.info(uploadFileRet);
		cosClient.shutdown();

	}
}
