package com.minsx.ccs.core.able;

import java.util.List;

import com.minsx.ccs.core.model.model.CCSPartETag;

public interface CCSCompleteMultipartPutRequestable extends CCSBaseObjectRequest{
	
	List<CCSPartETag> getCCSPartETags();
	
	String getUploadId();

}
