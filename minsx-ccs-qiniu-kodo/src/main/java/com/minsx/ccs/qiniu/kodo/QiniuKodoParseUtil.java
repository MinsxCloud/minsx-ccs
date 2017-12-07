package com.minsx.ccs.qiniu.kodo;

import com.minsx.ccs.core.model.model.CCSBucket;
import com.minsx.ccs.core.model.model.CCSObjectList;
import com.qiniu.storage.model.FileListing;

import java.util.Arrays;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2017/12/7 15:14
 */
public class QiniuKodoParseUtil {

    /**
     * 七牛云FileListing -> CCSObjectList
     *
     * @param fileListing;
     * @return CCSObjectList
     */
    public static CCSObjectList parseToCCSObjectList(String bucketName, String prefix, String marker, FileListing fileListing) {
        //Arrays.stream(fileListing.items).map()
        if (fileListing == null) {
            return null;
        }
        CCSObjectList result = new CCSObjectList();
        result.setBucketName(bucketName);
        result.setMarker(marker);
        result.setNextMarker(fileListing.marker);
        result.setTruncated(fileListing.isEOF());
        result.setCommonPrefix(fileListing.commonPrefixes != null ? Arrays.asList(fileListing.commonPrefixes) : null);
        //TODO 其它一些赋值
        return result;
    }

    /**
     * @param bucketName;
     * @return
     */
    public static CCSBucket parseToCCSBucket(String bucketName) {
        CCSBucket ccsBucket = new CCSBucket();
        ccsBucket.setName(bucketName);
        return ccsBucket;
    }
}
