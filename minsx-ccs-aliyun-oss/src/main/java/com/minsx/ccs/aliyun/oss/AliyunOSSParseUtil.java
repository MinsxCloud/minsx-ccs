package com.minsx.ccs.aliyun.oss;

import java.util.ArrayList;
import java.util.List;

import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.Owner;
import com.minsx.ccs.core.model.CCSObject;
import com.minsx.ccs.core.model.CCSObjectList;
import com.minsx.ccs.core.model.CCSObjectMetadata;
import com.minsx.ccs.core.model.CCSObjectSummary;
import com.minsx.ccs.core.model.CCSOwner;

public class AliyunOSSParseUtil {

	/**
	 * OSSObjectSummary到CCSObjectSummary
	 */
	public static CCSObjectSummary parseToCCSObjectSummary(OSSObjectSummary ossObjectSummary) {
		CCSObjectSummary ccsObjectSummary = new CCSObjectSummary();
		ccsObjectSummary.setCCSOwner(parseToCCSOwner(ossObjectSummary.getOwner()));
		ccsObjectSummary.setBucketName(ossObjectSummary.getBucketName());
		ccsObjectSummary.setCcsPath(ossObjectSummary.getKey());
		ccsObjectSummary.setETag(ossObjectSummary.getETag());
		ccsObjectSummary.setLastModified(ossObjectSummary.getLastModified());
		ccsObjectSummary.setSize(ossObjectSummary.getSize());
		ccsObjectSummary.setStorgeClass(ossObjectSummary.getStorageClass());
		return ccsObjectSummary;
	}

	/**
	 * OSSObjectMetadata到CCSObjectMetadata
	 */
	public static CCSObjectMetadata parseToCCSObjectMetadata(ObjectMetadata ossObjectMetadata) {
		CCSObjectMetadata ccsObjectMetadata = new CCSObjectMetadata();
		ccsObjectMetadata.setContentEncoding(ossObjectMetadata.getContentEncoding());
		ccsObjectMetadata.setContentLength(ossObjectMetadata.getContentLength());
		ccsObjectMetadata.setContentMD5(ossObjectMetadata.getContentMD5());
		ccsObjectMetadata.setContentType(ossObjectMetadata.getContentType());
		ccsObjectMetadata.setETag(ossObjectMetadata.getETag());
		ccsObjectMetadata.setLastModified(ossObjectMetadata.getLastModified());
		ccsObjectMetadata.setObjectType(ossObjectMetadata.getObjectType());
		ccsObjectMetadata.setStorgeClass(ossObjectMetadata.getObjectStorageClass().name());
		ccsObjectMetadata.setUserMetaData(ossObjectMetadata.getUserMetadata());
		return ccsObjectMetadata;
	}

	/**
	 * OSSObjectListing到CCSObjectList
	 */
	public static CCSObjectList parseToCCSObjectList(ObjectListing ossObjectListing) {
		CCSObjectList ccsObjectList = new CCSObjectList();
		ccsObjectList.setBucketName(ossObjectListing.getBucketName());
		ccsObjectList.setMarker(ossObjectListing.getMarker());
		ccsObjectList.setMaxKeys(ossObjectListing.getMaxKeys());
		ccsObjectList.setPrefix(ossObjectListing.getPrefix());
		ccsObjectList.setCommonPrefix(ossObjectListing.getCommonPrefixes());
		List<CCSObjectSummary> ccsObjectSummaries = new ArrayList<>();
		ossObjectListing.getObjectSummaries().forEach(ossObjectSummarie -> {
			ccsObjectSummaries.add(parseToCCSObjectSummary(ossObjectSummarie));
		});
		ccsObjectList.setCcsObjectSummaries(ccsObjectSummaries);
		return ccsObjectList;
	}

	/**
	 * OSSOwner到CCSOwner
	 */
	public static CCSOwner parseToCCSOwner(Owner ossOwner) {
		return new CCSOwner(ossOwner.getId(), ossOwner.getDisplayName());
	}

	/**
	 * OSSObject到CCSObject
	 */
	public static CCSObject parseToCCSObject(OSSObject ossObject) {
		CCSObject ccsObject = new CCSObject();
		ccsObject.setBucketName(ossObject.getBucketName());
		ccsObject.setCcsPath(ossObject.getKey());
		ccsObject.setObjectContent(ossObject.getObjectContent());
		ccsObject.setCcsObjectMetadata(parseToCCSObjectMetadata(ossObject.getObjectMetadata()));
		return ccsObject;
	}

}
