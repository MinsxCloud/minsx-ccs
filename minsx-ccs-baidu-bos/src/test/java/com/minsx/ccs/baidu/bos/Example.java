package com.minsx.ccs.baidu.bos;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.minsx.ccs.core.config.BaiduBOSConfig;
import com.minsx.ccs.core.service.CCSClient;


public class Example {
	
	private CCSClient ccsClient;

	@Before
	public void initial() {
		BaiduBOSConfig baiduBOSConfig = new BaiduBOSConfig();
		baiduBOSConfig.setEndPoint("http://su.bcebos.com");
		baiduBOSConfig.setAccessKeyId("c93dd9d3411740419a0ff0f16cfce2fc");
		baiduBOSConfig.setAccessKeySecret("c7244fc343a8474682663f0a31d4d798");
		ccsClient = new BaiduBOSImpl(baiduBOSConfig);
	}
	
	@After
	public void end() {
		ccsClient.shutdown();
	}
	
	@Test
	public void putObject() {
		ccsClient.putObject("E:\\Temp\\A.docx", "minsx", "A.docx");
	}
	
	@Test
	public void deleteObject() {
		ccsClient.deleteObject("minsx", "A.docx");
	}
	
	@Test
	public void doesObjectExist() {
		System.out.println(ccsClient.doesObjectExist("minsx", "A.docx"));
	}
	
	@Test
	public void getObjectMetadata() {
		ccsClient.getObjectMetadata("minsx", "A.docx").getUserMetaData().forEach((key,value)->{
			System.out.println(key);
			System.out.println(value);
		});
	}

}
