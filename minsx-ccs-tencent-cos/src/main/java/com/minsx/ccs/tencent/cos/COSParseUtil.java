package com.minsx.ccs.tencent.cos;

import java.util.ArrayList;
import java.util.List;

import com.minsx.ccs.core.model.model.CCSObject;
import com.minsx.ccs.core.model.model.CCSObjectList;
import com.minsx.ccs.core.model.model.CCSObjectMetadata;
import com.minsx.ccs.core.model.model.CCSObjectSummary;
import com.minsx.ccs.core.model.model.CCSOwner;
import com.qcloud.cos.model.COSObject;
import com.qcloud.cos.model.COSObjectSummary;
import com.qcloud.cos.model.ObjectListing;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.Owner;

/**
 * COSParseUtil created by Joker on 2017年11月7日
 */
public class COSParseUtil {

	/**
	 * COSObjectSummary到CCSObjectSummary
	 */
	public static CCSObjectSummary parseToCCSObjectSummary(COSObjectSummary cosObjectSummary) {
		CCSObjectSummary ccsObjectSummary = new CCSObjectSummary();
		ccsObjectSummary.setCCSOwner(parseToCCSOwner(cosObjectSummary.getOwner()));
		ccsObjectSummary.setBucketName(cosObjectSummary.getBucketName());
		ccsObjectSummary.setCcsPath(cosObjectSummary.getKey());
		ccsObjectSummary.setETag(cosObjectSummary.getETag());
		ccsObjectSummary.setLastModified(cosObjectSummary.getLastModified());
		ccsObjectSummary.setSize(cosObjectSummary.getSize());
		ccsObjectSummary.setStorgeClass(cosObjectSummary.getStorageClass());
		return ccsObjectSummary;
	}

	/**
	 * COSObjectMetadata到CCSObjectMetadata
	 */
	public static CCSObjectMetadata parseToCCSObjectMetadata(ObjectMetadata cosObjectMetadata) {
		CCSObjectMetadata ccsObjectMetadata = new CCSObjectMetadata();
		ccsObjectMetadata.setContentEncoding(cosObjectMetadata.getContentEncoding());
		ccsObjectMetadata.setContentLength(cosObjectMetadata.getContentLength());
		ccsObjectMetadata.setContentMD5(cosObjectMetadata.getContentMD5());
		ccsObjectMetadata.setContentType(cosObjectMetadata.getContentType());
		ccsObjectMetadata.setETag(cosObjectMetadata.getETag());
		ccsObjectMetadata.setLastModified(cosObjectMetadata.getLastModified());
		ccsObjectMetadata.setUserMetaData(cosObjectMetadata.getUserMetadata());
		return ccsObjectMetadata;
	}

	/**
	 * COSObjectListing到CCSObjectList
	 */
	public static CCSObjectList parseToCCSObjectList(ObjectListing  cosObjectListing) {
		CCSObjectList ccsObjectList = new CCSObjectList();
		ccsObjectList.setBucketName(cosObjectListing.getBucketName());
		ccsObjectList.setMarker(cosObjectListing.getMarker());
		ccsObjectList.setNextMarker(cosObjectListing.getNextMarker());
		ccsObjectList.setDelimiter(cosObjectListing.getDelimiter());
		ccsObjectList.setEncodingType(cosObjectListing.getEncodingType());
		ccsObjectList.setMaxKeys(cosObjectListing.getMaxKeys());
		ccsObjectList.setTruncated(cosObjectListing.isTruncated());
		ccsObjectList.setPrefix(cosObjectListing.getPrefix());
		ccsObjectList.setCommonPrefix(cosObjectListing.getCommonPrefixes());
		List<CCSObjectSummary> ccsObjectSummaries = new ArrayList<>();
		cosObjectListing.getObjectSummaries().forEach(cosObjectSummary -> {
			ccsObjectSummaries.add(parseToCCSObjectSummary(cosObjectSummary));
		});
		ccsObjectList.setCcsObjectSummaries(ccsObjectSummaries);
		return ccsObjectList;
	}

	/**
	 * COSOwner到CCSOwner
	 */
	public static CCSOwner parseToCCSOwner(Owner cosOwner) {
		return new CCSOwner(cosOwner.getId(), cosOwner.getDisplayName());
	}

	/**
	 * COSObject到CCSObject
	 */
	public static CCSObject parseToCCSObject(COSObject cosObject) {
		CCSObject ccsObject = new CCSObject();
		ccsObject.setBucketName(cosObject.getBucketName());
		ccsObject.setCcsPath(cosObject.getKey());
		ccsObject.setObjectContent(cosObject.getObjectContent());
		ccsObject.setCcsObjectMetadata(parseToCCSObjectMetadata(cosObject.getObjectMetadata()));
		return ccsObject;
	}

}
