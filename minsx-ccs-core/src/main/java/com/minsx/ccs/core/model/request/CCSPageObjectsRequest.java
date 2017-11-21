package com.minsx.ccs.core.model.request;

import com.minsx.ccs.core.able.CCSPageObjectsRequestable;

/**
 * Paged access object created by Joker on 2017年11月10日
 */
public class CCSPageObjectsRequest implements CCSPageObjectsRequestable {

	private final static Integer DEFAULT_PAGE_INDEX = 10;
	private final static Integer DEFAULT_PAGE_SIZE = 10;

	private String bucketName;
	private String prefix;
	private Integer pageIndex;
	private Integer pageSize;

	public CCSPageObjectsRequest(String bucketName, String ccsPath, Integer pageIndex, Integer pageSize) {
		this.bucketName = bucketName;
		this.prefix = ccsPath;
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
	}

	public CCSPageObjectsRequest(String bucketName, String ccsPath) {
		this(bucketName, ccsPath, DEFAULT_PAGE_INDEX, DEFAULT_PAGE_SIZE);
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public Integer getPageIndex() {
		return pageIndex;
	}

	@Override
	public Integer getPageSize() {
		return pageSize;
	}

	@Override
	public String getPrefix() {
		return prefix;
	}

	@Override
	public String getBucketName() {
		return bucketName;
	}

	@Override
	public CCSPageObjectsRequestable next() {
		pageIndex++;
		return this;
	}

	@Override
	public CCSPageObjectsRequestable previous() {
		pageIndex--;
		return this;
	}

}
