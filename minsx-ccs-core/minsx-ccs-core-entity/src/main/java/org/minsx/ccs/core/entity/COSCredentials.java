package org.minsx.ccs.core.entity;

public class COSCredentials extends Credentials{
	
	public COSCredentials(Long appId,String accessKeyId,String secretAccessKey) {
		super();
		this.setAccessKeyId(accessKeyId);
		this.setSecretAccessKey(secretAccessKey);
		this.setAppId(appId);
	}
	
	@Override
	public void setSecurityToken(String securityToken) throws Exception{
		throw new Exception();
	}

}
