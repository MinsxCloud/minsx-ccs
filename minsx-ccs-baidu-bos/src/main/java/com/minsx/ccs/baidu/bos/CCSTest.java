package com.minsx.ccs.baidu.bos;

import com.minsx.ccs.core.config.BaiduBOSConfig;
import com.minsx.ccs.core.service.CCSClient;


public class CCSTest {
	
	public static void main(String[] args) {
		BaiduBOSConfig baiduBOSConfig = new BaiduBOSConfig();
		baiduBOSConfig.setEndPoint("http://su.bcebos.com");
		baiduBOSConfig.setAccessKeyId("c93dd9d3411740419a0ff0f16cfce2fc");
		baiduBOSConfig.setAccessKeySecret("c7244fc343a8474682663f0a31d4d798");
		CCSClient ccsClient = new BaiduBOSImpl(baiduBOSConfig);
		//ccsClient.putObject("E:\\document\\编程文档\\advinst汉化.chm", "minsx", "advinst汉化.chm");
		//System.out.println(ccsClient.getObjectMetadata("minsx", "advinst汉化.chm").getLastModified());
		//System.out.println(ccsClient.doesObjectExist("minsx", "advinst汉化.chm"));
		
		/*ccsClient.getObjectMetadata("minsx", "advinst汉化.chm").getUserMetaData().forEach((key,value)->{
			System.out.println(key);
			System.out.println(value);
		});*/
		
		ccsClient.deleteObject("minsx", "advinst汉化.chm");
		
		ccsClient.shutdown();
	}

}
