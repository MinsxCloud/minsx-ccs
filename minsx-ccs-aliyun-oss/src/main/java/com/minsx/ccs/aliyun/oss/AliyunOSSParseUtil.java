package com.minsx.ccs.aliyun.oss;

import java.util.ArrayList;
import java.util.List;

import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.Owner;
import com.aliyun.oss.model.PutObjectRequest;
import com.minsx.ccs.core.able.ListObjectsRequestable;
import com.minsx.ccs.core.model.CCSBucket;
import com.minsx.ccs.core.model.CCSObject;
import com.minsx.ccs.core.model.CCSObjectList;
import com.minsx.ccs.core.model.CCSObjectMetadata;
import com.minsx.ccs.core.model.CCSObjectSummary;
import com.minsx.ccs.core.model.CCSOwner;
import com.minsx.ccs.core.model.CCSPutObjectRqeuest;
import com.minsx.ccs.core.type.UnknownType;

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
		ccsObjectList.setNextMarker(ossObjectListing.getNextMarker());
		ccsObjectList.setDelimiter(ossObjectListing.getDelimiter());
		ccsObjectList.setEncodingType(ossObjectListing.getEncodingType());
		ccsObjectList.setMaxKeys(ossObjectListing.getMaxKeys());
		ccsObjectList.setTruncated(ossObjectListing.isTruncated());
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
	
	//---------------------------------------------分隔符----------------------------------------------------------
	/**
	 * CCSObjectMetadata 到 ObjectMetadata
	 */
	public static ObjectMetadata parseToObjectMetadata(CCSObjectMetadata ccsObjectMetadata) {
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setUserMetadata(ccsObjectMetadata.getUserMetaData());
		objectMetadata.setLastModified(ccsObjectMetadata.getLastModified());
		objectMetadata.setContentType(ccsObjectMetadata.getContentType());
		objectMetadata.setContentMD5(ccsObjectMetadata.getContentMD5());
		objectMetadata.setContentLength(ccsObjectMetadata.getContentLength());
		objectMetadata.setContentEncoding(ccsObjectMetadata.getContentEncoding());
		return objectMetadata;
	}
	
	/**
	 * ListObjectsRequestable 到 ListObjectsRequest
	 */
	public static ListObjectsRequest parseToListObjectsRequest(ListObjectsRequestable listObjectsRequestable) {
		ListObjectsRequest listObjectsRequest = new ListObjectsRequest();
		listObjectsRequest.withBucketName(listObjectsRequestable.getBucketName()).withKey(UnknownType.UNKNOWN_KEY);
		listObjectsRequest.setDelimiter(listObjectsRequestable.getDelimiter());
		listObjectsRequest.setEncodingType(listObjectsRequestable.getEncodingType());
		listObjectsRequest.setMarker(listObjectsRequestable.getMarker());
		listObjectsRequest.setMaxKeys(listObjectsRequestable.getMaxKeys());
		listObjectsRequest.setPrefix(listObjectsRequestable.getPrefix());
		return listObjectsRequest;
	}

	/**
	 * OSSBucket 到 CCSBucket
	 */
	public static CCSBucket parseToCCSBucket(Bucket ossBucket) {
		CCSBucket ccsBucket = new CCSBucket();
		ccsBucket.setCreationDate(ossBucket.getCreationDate());
		ccsBucket.setExtranetEndpoint(ossBucket.getExtranetEndpoint());
		ccsBucket.setIntranetEndpoint(ossBucket.getIntranetEndpoint());
		ccsBucket.setLocation(ossBucket.getLocation());
		ccsBucket.setName(ossBucket.getName());
		ccsBucket.setCCSOwner(parseToCCSOwner(ossBucket.getOwner()));
		ccsBucket.setStorageClass(ossBucket.getStorageClass().toString());
		return ccsBucket;
	}
	
	/**
	 * CCSPutObjectRqeuest 到 PutObjectRequest
	 */
	public static PutObjectRequest parseToPutObjectRequest(CCSPutObjectRqeuest ccsPutObjectRqeuest) {
		PutObjectRequest putObjectRequest = null;
		if (ccsPutObjectRqeuest.getFile()!=null) {
			putObjectRequest = new PutObjectRequest(ccsPutObjectRqeuest.getBucketName(),ccsPutObjectRqeuest.getCcsObjectPath(),ccsPutObjectRqeuest.getFile());
		}else if (ccsPutObjectRqeuest.getInputStream()!=null) {
			putObjectRequest = new PutObjectRequest(ccsPutObjectRqeuest.getBucketName(),ccsPutObjectRqeuest.getCcsObjectPath(),ccsPutObjectRqeuest.getInputStream());
		}
		putObjectRequest.setFile(ccsPutObjectRqeuest.getFile());
		putObjectRequest.setInputStream(ccsPutObjectRqeuest.getInputStream());
		putObjectRequest.setKey(ccsPutObjectRqeuest.getCcsObjectPath());
		putObjectRequest.setBucketName(ccsPutObjectRqeuest.getBucketName());
		putObjectRequest.setMetadata(parseToObjectMetadata(ccsPutObjectRqeuest.getMetadata()));
		return putObjectRequest;
	}
	
	
}
