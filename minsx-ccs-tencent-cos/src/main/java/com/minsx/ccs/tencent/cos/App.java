package com.minsx.ccs.tencent.cos;

import java.io.File;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;

@SuppressWarnings("unused")
public class App {

	public static void main(String[] args) {
		COSCredentials cred = new BasicCOSCredentials("1251505282", "AKIDCXrebct3IXl0HXrvcHHLYp6UpKwMlFY0", "kaQEEFIMRYhr2OqImHoR5jQbgow7KYn2");
		ClientConfig clientConfig = new ClientConfig(new Region("ap-shanghai"));
		COSClient cosClient = new COSClient(cred, clientConfig);
		String bucketName = "minsx";
		
		File localFile = new File("E:\\document\\编程文档\\Java真题库_电子书.pdf");
		String key = "/腾讯-COS.pdf";
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
		PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
		cosClient.shutdown();
		
		new CosClientException("");
		new CosServiceException("");
		
		
		//String result = cosClient.statFile(new StatFileRequest(bucketName, "/腾讯-COS.pdf"));
		String result = "{\"code\":0,\"message\":\"SUCCESS\",\"request_id\":\"NWEwMTlkMTNfZTlhMDY4NjRfOTRkZl8yMTkwYjA=\",\"data\":{\"access_url\":\"http://minsx-1251505282.file.myqcloud.com/%E8%85%BE%E8%AE%AF-COS.pdf\",\"authority\":\"eInvalid\",\"biz_attr\":\"\",\"ctime\":1509793525,\"custom_headers\":{},\"filelen\":3032065,\"filesize\":3032065,\"forbid\":0,\"mtime\":1509793525,\"sha\":\"8d32e1cc59a978a84556ab38fa9ca7ebbcdd7b04\",\"slicesize\":1048576,\"source_url\":\"http://minsx-1251505282.cossh.myqcloud.com/%E8%85%BE%E8%AE%AF-COS.pdf\"}}\r\n";
		System.out.println(result);
		JSONObject jsonObject = JSON.parseObject(result);
		System.out.println("code = "+jsonObject.get("code"));
		System.out.println("message = "+jsonObject.get("message"));
		System.out.println("access_url = "+jsonObject.getJSONObject("data").get("access_url"));
		System.out.println("authority = "+jsonObject.getJSONObject("data").get("authority"));
		System.out.println("biz_attr = "+jsonObject.getJSONObject("data").get("biz_attr"));
		System.out.println("ctime = "+jsonObject.getJSONObject("data").get("ctime"));
		System.out.println("custom_headers = "+jsonObject.getJSONObject("data").get("custom_headers"));
		System.out.println("filelen = "+jsonObject.getJSONObject("data").get("filelen"));
		System.out.println("filesize = "+jsonObject.getJSONObject("data").get("filesize"));
		System.out.println("forbid = "+jsonObject.getJSONObject("data").get("forbid"));
		System.out.println("mtime = "+jsonObject.getJSONObject("data").get("mtime"));
		System.out.println("sha = "+jsonObject.getJSONObject("data").get("sha"));
		System.out.println("slicesize = "+jsonObject.getJSONObject("data").get("slicesize"));
		System.out.println("source_url = "+jsonObject.getJSONObject("data").get("source_url"));
		
		System.out.println(new Date(1509793525000L));
		
	}

}
