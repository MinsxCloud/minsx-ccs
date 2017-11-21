package com.minsx.ccs.core.able;

public interface CCSPageObjectsRequestable {

	Integer getPageIndex();

	Integer getPageSize();

	String getPrefix();

	String getBucketName();
	
	CCSPageObjectsRequestable next();
	
	CCSPageObjectsRequestable previous();
	
}
