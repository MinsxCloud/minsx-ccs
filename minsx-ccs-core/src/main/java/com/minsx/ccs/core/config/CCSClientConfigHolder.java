package com.minsx.ccs.core.config;

public class CCSClientConfigHolder {

	private AliyunOSSConfig aliyunOSSConfig;

	private TencentCOSConfig tencentCOSConfig;

	public AliyunOSSConfig getAliyunOSSConfig() {
		return aliyunOSSConfig;
	}

	public void setAliyunOSSConfig(AliyunOSSConfig aliyunOSSConfig) {
		this.aliyunOSSConfig = aliyunOSSConfig;
	}

	public TencentCOSConfig getTencentCOSConfig() {
		return tencentCOSConfig;
	}

	public void setTencentCOSConfig(TencentCOSConfig tencentCOSConfig) {
		this.tencentCOSConfig = tencentCOSConfig;
	}

}
