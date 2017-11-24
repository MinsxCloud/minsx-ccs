package com.minsx.ccs.baidu.bos;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;

public class BosTest {

	private BosClient bosClient;
	
	@Before
	public void initial() {
		BosClientConfiguration config = new BosClientConfiguration();
		config.setEndpoint("http://su.bcebos.com");
	    config.setCredentials(new DefaultBceCredentials("c93dd9d3411740419a0ff0f16cfce2fc", "c7244fc343a8474682663f0a31d4d798"));
	    bosClient = new BosClient(config);
	}
	
	@After
	public void end() {
		bosClient.shutdown();
	}
	
	@Test
	public void getObjectMetadata() {
		bosClient.getObjectMetadata("minsx", "A.docx");
		bosClient.shutdown();
	}
	
	public void name() {
	}
	
}
