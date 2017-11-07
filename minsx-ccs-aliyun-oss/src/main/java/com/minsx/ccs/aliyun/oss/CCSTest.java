package com.minsx.ccs.aliyun.oss;

import com.minsx.ccs.core.config.AliyunOSSConfig;
import com.minsx.ccs.core.service.CCSClient;

import minsx.ccs.core.exception.NativeClientTypeException;

public class CCSTest {
	
	public static void main(String[] args) throws NativeClientTypeException {
		AliyunOSSConfig aliyunOSSConfig = new AliyunOSSConfig();
		aliyunOSSConfig.setEndPoint("http://oss-cn-shanghai.aliyuncs.com");
		aliyunOSSConfig.setAccessKeyId("LTAIczLFMZV7mdDU");
		aliyunOSSConfig.setAccessKeySecret("9z04n82aNQpphfTXFeLWgPJI2T9wlq");
		CCSClient ccsClient = new AliyunOSSImpl(aliyunOSSConfig);
		
		ccsClient.getObjectMetadata("rtc-hospital", "advinst汉化.chm").getUserMetaData().forEach((key,value)->{
			System.out.println(key);
			System.out.println(value);
		});
		
		/*CCSObject ccsObject = ccsClient.getObject("rtc-hospital", "advinst汉化.chm");
		System.out.println(ccsObject.getCcsObjectMetadata().getETag());*/
		
		//ccsClient.deleteObject("rtc-hospital", "advinst汉化.chm");
		
		//ccsClient.putObject("E:\\document\\编程文档\\advinst汉化.chm", "rtc-hospital", "advinst汉化.chm");
		
		//ccsClient.getObjectMetadata("rtc-hospital", "advinst汉化.chm").getUserMetaData().put("password", "Ss123456");
		
		ccsClient.shutdown();
		
		
	}

}
