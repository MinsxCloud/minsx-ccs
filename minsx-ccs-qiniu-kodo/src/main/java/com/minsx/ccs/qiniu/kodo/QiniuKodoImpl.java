package com.minsx.ccs.qiniu.kodo;

import com.minsx.ccs.core.able.*;
import com.minsx.ccs.core.config.QiniuKodoConfig;
import com.minsx.ccs.core.exception.CCSServiceException;
import com.minsx.ccs.core.exception.NativeClientCastException;
import com.minsx.ccs.core.model.base.CCSObjectOperator;
import com.minsx.ccs.core.model.model.CCSBucket;
import com.minsx.ccs.core.model.model.CCSObject;
import com.minsx.ccs.core.model.model.CCSObjectList;
import com.minsx.ccs.core.model.model.CCSObjectMetadata;
import com.minsx.ccs.core.model.request.CCSPutPartRequest;
import com.minsx.ccs.core.model.response.CCSCompleteMultipartPutResponse;
import com.minsx.ccs.core.model.response.CCSInitiateMultipartPutResponse;
import com.minsx.ccs.core.model.response.CCSPutObjectResponse;
import com.minsx.ccs.core.model.response.CCSPutPartResponse;
import com.minsx.ccs.core.service.CCSClient;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Client;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.model.BatchStatus;
import com.qiniu.storage.model.FileListing;
import com.qiniu.util.Auth;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2017/12/7 12:24
 * ccs七牛实现
 */
public class QiniuKodoImpl implements CCSClient {

    /**
     * ccs七牛云配置
     */
    private QiniuKodoConfig qiniuKodoConfig;

    /**
     * 七牛认证信息
     */
    private Auth auth;

    /**
     * 七牛sdk配置
     */
    private Configuration qiniuCfg;

    /**
     * 七牛sdk提供的bucketManager
     */
    private BucketManager bucketManager;

    /**
     * 网络请求客户端，用于请求七牛api
     */
    private Client client;

    public QiniuKodoImpl(QiniuKodoConfig conf) {
        this.qiniuKodoConfig = conf;
        switch (conf.getZone()) {
            case "zone0":
            case "huadong":
                qiniuCfg = new Configuration(Zone.zone0());
                break;
            case "zone1":
            case "huabei":
                qiniuCfg = new Configuration(Zone.zone1());
                break;
            case "zone2":
            case "huanan":
                qiniuCfg = new Configuration(Zone.zone2());
                break;
            case "zoneNa0":
            case "beimei":
                qiniuCfg = new Configuration(Zone.zoneNa0());
                break;
            default:
                qiniuCfg = new Configuration(Zone.autoZone());
                break;
        }
        auth = Auth.create(conf.getAccessKey(), conf.getSecretKey());
        bucketManager = new BucketManager(auth, qiniuCfg);
        client = new Client(qiniuCfg);
    }

    //-----------------------------分割线，以下都是实现的接口

    @Override
    public void createBucket(String bucketName) {
        try {
            bucketManager.createBucket(bucketName, qiniuCfg.zone.getRegion());
        } catch (QiniuException e) {
            throw new CCSServiceException(e.getMessage());
        }
    }

    @Override
    public void deleteBucket(String bucketName) {
        try {
            bucketManager.deleteBucket(bucketName);
        } catch (QiniuException e) {
            throw new CCSServiceException(e.getMessage());
        }
    }

    @Override
    public Boolean doesBucketExist(String bucketName) {
        return listFiles(bucketName, "", 1, null, null) != null;
    }

    @Override
    public List<CCSBucket> listCCSBuckets() {
        String[] names;
        try {
            names = bucketManager.buckets();
        } catch (QiniuException e) {
            throw new CCSServiceException(e.getMessage());
        }
        return Arrays.stream(names)
                .map(bucketName -> QiniuKodoParseUtil.parseToCCSBucket(bucketName))
                .collect(Collectors.toList());
    }

    @Override
    public void createFolder(String bucketName, String folderName) {
        throw new CCSServiceException("Could not create folder, no folder notion.");
    }

    @Override
    public CCSObjectList listObjects(String bucketName, String prefix) {
        return listFiles(bucketName, prefix, 1000, null, null);
    }

    @Override
    public CCSObjectList listObjects(CCSListObjectsRequestable listRequest) {
        return listFiles(listRequest.getBucketName(),
                listRequest.getPrefix(),
                listRequest.getMaxKeys(),
                null,
                listRequest.getDelimiter()
        );
    }

    @Override
    public CCSObjectList listObjects(CCSPageObjectsRequestable pageRequest) {
        String nextMarker = null;
        if (pageRequest.getPageIndex() > 0) {
            CCSObjectList tmp = listFiles(
                    pageRequest.getBucketName(),
                    pageRequest.getPrefix(),
                    pageRequest.getPageSize() * pageRequest.getPageIndex(),
                    null,
                    null
            );
            nextMarker = tmp.getNextMarker();
        }

        return listFiles(
                pageRequest.getBucketName(),
                pageRequest.getPrefix(),
                pageRequest.getPageSize(),
                nextMarker,
                null
        );
    }

    @Override
    public Boolean doesObjectExist(String bucketName, String ccsObjectPath) {
        try {
            this.getObject(bucketName, ccsObjectPath);
        } catch (CCSServiceException e) {
            return false;
        }
        return true;
    }

    @Override
    public CCSObject getObject(String bucketName, String ccsObjectPath) {
        String postfix = auth.privateDownloadUrl(ccsObjectPath);
        Response response;
        try {
            response = client.get(qiniuKodoConfig.getHost() + postfix);
            CCSObject ccsObject = new CCSObject();
            ccsObject.setBucketName(bucketName);
            ccsObject.setCcsPath(ccsObjectPath);
            ccsObject.setObjectContent(response.bodyStream());
            return ccsObject;
        } catch (QiniuException e) {
            throw new CCSServiceException(e.getMessage());
        }
    }

    @Override
    public CCSObjectMetadata downloadObject(String bucketName, String ccsObjectPath, File localFile) {
        CCSObject ccsObject = this.getObject(bucketName, ccsObjectPath);
        try {
            CCSObjectOperator.CCSObjectToFile(ccsObject, localFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ccsObject.getCcsObjectMetadata();
    }

    @Override
    public CCSObject getObject(CCSGetObjectRequestable ccsGetObjectRequestable) {
        return this.getObject(ccsGetObjectRequestable.getBucketName(), ccsGetObjectRequestable.getCcsObjectPath());
    }

    @Override
    public CCSObjectMetadata getObjectMetadata(String bucketName, String ccsObjectPath) {
        return this.getObject(bucketName, ccsObjectPath).getCcsObjectMetadata();
    }

    @Override
    public CCSPutObjectResponse putObject(String bucketName, String ccsObjectPath, String sourceFilePath) {
        return null;
    }

    @Override
    public CCSPutObjectResponse putObject(String bucketName, String ccsObjectPath, File sourceFile) {
        return null;
    }

    @Override
    public CCSPutObjectResponse putObject(String bucketName, String ccsObjectPath, File sourceFile, CCSObjectMetadata ccsObjectMetadata) {
        return null;
    }

    @Override
    public CCSPutObjectResponse putObject(String bucketName, String ccsObjectPath, InputStream inputStream) {
        return null;
    }

    @Override
    public CCSPutObjectResponse putObject(String bucketName, String ccsObjectPath, byte[] bytes) {
        return null;
    }

    @Override
    public CCSPutObjectResponse putObject(String bucketName, String ccsObjectPath, InputStream inputStream, CCSObjectMetadata ccsObjectMetadata) {
        return null;
    }

    @Override
    public CCSPutObjectResponse putObject(CCSPutObjectRequestable putObjectRequestable) {
        return null;
    }

    @Override
    public CCSInitiateMultipartPutResponse initiateMultipartPut(CCSInitiateMultipartPutRequestable ccsInitiateMultipartPutRequestable) {
        return null;
    }

    @Override
    public CCSPutPartResponse putPart(CCSPutPartRequest ccsPutPartRequest) {
        return null;
    }

    @Override
    public CCSCompleteMultipartPutResponse completeMultipartUpload(CCSCompleteMultipartPutRequestable ccsCompleteMultipartPutRequestable) {
        return null;
    }

    @Override
    public void copyObject(String sourceBucketName, String sourceObjectPath, String destinationBucketName, String destinationObjectPath) {

    }

    @Override
    public void moveObject(String sourceBucketName, String sourceObjectPath, String destinationBucketName, String destinationObjectPath) {

    }

    @Override
    public void deleteObject(String bucketName, String ccsObjectPath) {
        BucketManager.BatchOperations batchOperations = new BucketManager.BatchOperations();
        batchOperations.addDeleteOp(bucketName, ccsObjectPath);
        try {
            Response response = bucketManager.batch(batchOperations);
            BatchStatus[] batchStatusList = response.jsonToObject(BatchStatus[].class);
            if (batchStatusList.length == 1 && batchStatusList[0].code != 200) {
                throw new CCSServiceException("delete source object error,try roll back the operation");
            }
        } catch (QiniuException e) {
            throw new CCSServiceException(e.getMessage());
        }
    }

    @Override
    public void shutdown() {
        auth = null;
        client = null;
        bucketManager = null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getNativeClient(Class<T> nativeClientClazz) {
        if (nativeClientClazz.isInstance(bucketManager.getClass())) {
            throw new NativeClientCastException(bucketManager.getClass(), nativeClientClazz);
        }
        return (T) bucketManager;
    }

    //-----------------------------private method

    /**
     * get file list
     *
     * @param bucketName;
     * @param prefix;
     * @param limit;
     * @param curMarker;
     * @param delimiter;路径分隔符
     * @return CCSObjectList
     */
    private CCSObjectList listFiles(String bucketName, String prefix, int limit, String curMarker, String delimiter) {
        FileListing fileListing;
        try {
            fileListing = bucketManager.listFiles(bucketName, prefix, curMarker, limit, delimiter);
        } catch (QiniuException e) {
            System.err.println(e.getMessage());
            fileListing = null;
        }
        return QiniuKodoParseUtil.parseToCCSObjectList(bucketName, prefix, delimiter, curMarker, fileListing);
    }
}
