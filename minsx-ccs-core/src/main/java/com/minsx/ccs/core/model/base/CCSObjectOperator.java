package com.minsx.ccs.core.model.base;

import java.io.File;
import java.io.IOException;

import com.minsx.ccs.core.model.model.CCSObject;
import com.minsx.ccs.core.util.IOUtil;

public class CCSObjectOperator {
	
	public static void CCSObjectToFile(CCSObject ccsObject,File file) throws IOException {
		IOUtil.InputStreamToFile(ccsObject.getObjectContent(), file);
	}
	
	
	

}
