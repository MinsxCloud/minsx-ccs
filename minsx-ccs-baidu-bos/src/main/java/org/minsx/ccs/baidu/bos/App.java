package org.minsx.ccs.baidu.bos;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;

public class App {

	public void test() {
		BosClientConfiguration config = new BosClientConfiguration();
	    config.setCredentials(new DefaultBceCredentials("", ""));
	    BosClient client = new BosClient(config);
	    client.shutdown();
	}
}
