package com.minsx.ccs.baidu.bos;

import java.util.ArrayList;
import java.util.List;

import com.baidubce.model.User;
import com.baidubce.services.bos.model.BosObject;
import com.baidubce.services.bos.model.BosObjectSummary;
import com.baidubce.services.bos.model.BucketSummary;
import com.baidubce.services.bos.model.ListObjectsResponse;
import com.baidubce.services.bos.model.ObjectMetadata;
import com.minsx.ccs.core.model.model.CCSBucket;
import com.minsx.ccs.core.model.model.CCSObject;
import com.minsx.ccs.core.model.model.CCSObjectList;
import com.minsx.ccs.core.model.model.CCSObjectMetadata;
import com.minsx.ccs.core.model.model.CCSObjectSummary;
import com.minsx.ccs.core.model.model.CCSOwner;
import com.minsx.ccs.core.type.UnknownType;

public class BaiduBOSParseUtil {
	
	/**
	 * BosObjectSummary到CCSObjectSummary
	 */
	public static CCSObjectSummary parseToCCSObjectSummary(BosObjectSummary bosObjectSummary) {
		CCSObjectSummary ccsObjectSummary = new CCSObjectSummary();
		ccsObjectSummary.setCCSOwner(parseToCCSOwner(bosObjectSummary.getOwner()));
		ccsObjectSummary.setBucketName(bosObjectSummary.getBucketName());
		ccsObjectSummary.setCcsPath(bosObjectSummary.getKey());
		ccsObjectSummary.setETag(bosObjectSummary.getETag());
		ccsObjectSummary.setLastModified(bosObjectSummary.getLastModified());
		ccsObjectSummary.setSize(bosObjectSummary.getSize());
		ccsObjectSummary.setStorgeClass(bosObjectSummary.getStorageClass());
		return ccsObjectSummary;
	}

	/**
	 * BOSObjectMetadata到CCSObjectMetadata
	 */
	public static CCSObjectMetadata parseToCCSObjectMetadata(ObjectMetadata  bosObjectMetadata) {
		CCSObjectMetadata ccsObjectMetadata = new CCSObjectMetadata();
		ccsObjectMetadata.setContentEncoding(bosObjectMetadata.getContentEncoding());
		ccsObjectMetadata.setContentLength(bosObjectMetadata.getContentLength());
		ccsObjectMetadata.setContentMD5(bosObjectMetadata.getContentMd5());
		ccsObjectMetadata.setContentType(bosObjectMetadata.getContentType());
		ccsObjectMetadata.setETag(bosObjectMetadata.getETag());
		ccsObjectMetadata.setLastModified(bosObjectMetadata.getLastModified());
		ccsObjectMetadata.setObjectType(bosObjectMetadata.getObjectType());
		ccsObjectMetadata.setStorgeClass(bosObjectMetadata.getStorageClass());
		ccsObjectMetadata.setUserMetaData(bosObjectMetadata.getUserMetadata());
		return ccsObjectMetadata;
	}

	/**
	 * BOSListObjectsResponse到CCSObjectList
	 */
	public static CCSObjectList parseToCCSObjectList(ListObjectsResponse bosListObjectsResponse) {
		CCSObjectList ccsObjectList = new CCSObjectList();
		ccsObjectList.setBucketName(bosListObjectsResponse.getBucketName());
		ccsObjectList.setMarker(bosListObjectsResponse.getMarker());
		ccsObjectList.setNextMarker(bosListObjectsResponse.getNextMarker());
		ccsObjectList.setDelimiter(bosListObjectsResponse.getDelimiter());
		ccsObjectList.setEncodingType(UnknownType.UNKNOWN_ENCODING_TYPE);
		ccsObjectList.setMaxKeys(bosListObjectsResponse.getMaxKeys());
		ccsObjectList.setTruncated(bosListObjectsResponse.isTruncated());
		ccsObjectList.setPrefix(bosListObjectsResponse.getPrefix());
		ccsObjectList.setCommonPrefix(bosListObjectsResponse.getCommonPrefixes());
		List<CCSObjectSummary> ccsObjectSummaries = new ArrayList<>();
		bosListObjectsResponse.getContents().forEach(ossObjectSummarie -> {
			ccsObjectSummaries.add(parseToCCSObjectSummary(ossObjectSummarie));
		});
		ccsObjectList.setCcsObjectSummaries(ccsObjectSummaries);
		return ccsObjectList;
	}

	/**
	 * BOSOwner到CCSOwner
	 */
	public static CCSOwner parseToCCSOwner(User bosUser) {
		return new CCSOwner(bosUser.getId(), bosUser.getDisplayName());
	}

	/**
	 * BOSObject到CCSObject
	 */
	public static CCSObject parseToCCSObject(BosObject bosObject) {
		CCSObject ccsObject = new CCSObject();
		ccsObject.setBucketName(bosObject.getBucketName());
		ccsObject.setCcsPath(bosObject.getKey());
		ccsObject.setObjectContent(bosObject.getObjectContent());
		ccsObject.setCcsObjectMetadata(parseToCCSObjectMetadata(bosObject.getObjectMetadata()));
		return ccsObject;
	}
	
	/**
	 * OSSBucket 到 CCSBucket
	 */
	public static CCSBucket parseToCCSBucket(BucketSummary ossBucket) {
		CCSBucket ccsBucket = new CCSBucket();
		ccsBucket.setCreationDate(ossBucket.getCreationDate());
		ccsBucket.setExtranetEndpoint(ossBucket.getLocation());
		
		ccsBucket.setLocation(ossBucket.getLocation());
		ccsBucket.setName(ossBucket.getName());
		return ccsBucket;
	}
}
