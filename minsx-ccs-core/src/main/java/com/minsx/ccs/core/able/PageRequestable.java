package com.minsx.ccs.core.able;

public interface PageRequestable {

	Integer getPageIndex();

	Integer getPageSize();

	String getPrefix();

	String getBucketName();
	
	PageRequestable next();
	
	PageRequestable previous();
	
}
