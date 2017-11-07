package com.minsx.ccs.tencent.cos;

import com.minsx.ccs.core.config.TencentCOSConfig;
import com.minsx.ccs.core.service.CCSClient;

public class MainTest {
	
	public static void main(String[] args) {
		TencentCOSConfig tencentCOSConfig = new TencentCOSConfig();
		tencentCOSConfig.setAppId(1251505282L);
		tencentCOSConfig.setSecretId("AKIDCXrebct3IXl0HXrvcHHLYp6UpKwMlFY0");
		tencentCOSConfig.setSecretKey("kaQEEFIMRYhr2OqImHoR5jQbgow7KYn2");
		tencentCOSConfig.setRegion("sh");
		CCSClient ccsClient = new TencentCOSImpl(tencentCOSConfig);
		ccsClient.shutdown();
	}

}
