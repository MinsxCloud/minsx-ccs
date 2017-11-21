package com.minsx.ccs.core.model.request;

import java.io.File;
import java.io.InputStream;

import com.minsx.ccs.core.able.CCSPutObjectRequestable;
import com.minsx.ccs.core.model.base.CCSObjectMetadata;

public class CCSPutObjectRqeuest extends CCSBasePutObjectRequest implements CCSPutObjectRequestable{

	private File file;

	private InputStream inputStream;

	private CCSObjectMetadata metadata;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public CCSObjectMetadata getMetadata() {
		return metadata;
	}

	public void setMetadata(CCSObjectMetadata metadata) {
		this.metadata = metadata;
	}

}
