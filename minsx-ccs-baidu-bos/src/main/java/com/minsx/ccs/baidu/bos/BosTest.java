package com.minsx.ccs.baidu.bos;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.bos.model.ObjectMetadata;

public class BosTest {

	public void test() {
		BosClientConfiguration config = new BosClientConfiguration();
	    config.setCredentials(new DefaultBceCredentials("", ""));
	    BosClient client = new BosClient(config);
	    client.getObjectMetadata("", "");
	    client.getObject(null);
	    new ObjectMetadata();
	    client.listObjects("").getContents().get(0);
	    client.shutdown();
	    
	}
}
