package com.minsx.ccs.tencent.cos;

import com.minsx.ccs.core.config.TencentCOSConfig;
import com.minsx.ccs.core.service.CCSClient;

public class MainTest {
	
	public static void main(String[] args) {
		TencentCOSConfig tencentCOSConfig = new TencentCOSConfig();
		tencentCOSConfig.setAppId("1251505282");
		tencentCOSConfig.setSecretId("AKIDCXrebct3IXl0HXrvcHHLYp6UpKwMlFY0");
		tencentCOSConfig.setSecretKey("kaQEEFIMRYhr2OqImHoR5jQbgow7KYn2");
		tencentCOSConfig.setRegion("ap-shanghai");
		CCSClient ccsClient = new TencentCOSImpl(tencentCOSConfig);
		
		/*ccsClient.getObjectMetadata("minsx", "advinst汉化.chm").getUserMetaData().forEach((key,value)->{
			System.out.println(key);
			System.out.println(value);
		});*/
		
		/*CCSObject ccsObject = ccsClient.getObject("minsx", "advinst汉化.chm");
		System.out.println(ccsObject.getCcsObjectMetadata().getETag());*/
		
		ccsClient.deleteObject("minsx", "advinst汉化.chm");
		
		//ccsClient.putObject("E:\\document\\编程文档\\advinst汉化.chm", "minsx", "advinst汉化.chm");
		
		//ccsClient.getObjectMetadata("minsx", "advinst汉化.chm").getUserMetaData().put("password", "Ss123456");
		
		ccsClient.shutdown();
		
	}

}
