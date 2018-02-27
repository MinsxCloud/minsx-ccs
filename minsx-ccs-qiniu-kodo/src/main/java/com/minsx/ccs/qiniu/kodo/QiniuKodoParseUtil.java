package com.minsx.ccs.qiniu.kodo;

import com.minsx.ccs.core.model.model.CCSBucket;
import com.minsx.ccs.core.model.model.CCSObjectList;
import com.minsx.ccs.core.model.model.CCSObjectSummary;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.storage.model.FileListing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2017/12/7 15:14
 */
public class QiniuKodoParseUtil {

    /**
     * 七牛云FileListing -> CCSObjectList
     */
    public static CCSObjectList parseToCCSObjectList(String bucketName, String prefix, String delimiter, String marker, FileListing fileListing) {
        if (fileListing == null) {
            return null;
        }
        CCSObjectList target = new CCSObjectList();
        target.setBucketName(bucketName);
        target.setMarker(marker);
        target.setNextMarker(fileListing.marker);
        target.setTruncated(fileListing.isEOF());
        target.setDelimiter(delimiter);
        target.setPrefix(prefix);
        target.setEncodingType(null);
        target.setMaxKeys(-1);
        target.setCcsObjectSummaries(new ArrayList<>(fileListing.items.length));
        for (FileInfo fileInfo : fileListing.items) {
            target.getCcsObjectSummaries().add(parseToCCSObjectSummary(fileInfo, bucketName));
        }
        return target;
    }

    /**
     * @param bucketName;
     */
    public static CCSBucket parseToCCSBucket(String bucketName) {
        CCSBucket ccsBucket = new CCSBucket();
        ccsBucket.setName(bucketName);
        return ccsBucket;
    }

    /**
     * qiNiu fileInfo --> ccsObjectSummary
     */
    public static CCSObjectSummary parseToCCSObjectSummary(FileInfo fileInfo, String bucketName) {
        CCSObjectSummary target = new CCSObjectSummary();
        target.setBucketName(bucketName);
        target.setCcsPath(fileInfo.key);
        target.setETag(fileInfo.hash);
        target.setLastModified(new Date(fileInfo.putTime));
        target.setStorgeClass("unknow");
        target.setSize(-1L);
        return target;
    }
}
