package com.minsx.ccs.qiniu.kodo;

import com.minsx.ccs.core.able.CCSListObjectsRequestable;
import com.minsx.ccs.core.able.CCSPageObjectsRequestable;
import com.minsx.ccs.core.config.QiniuKodoConfig;
import com.minsx.ccs.core.model.model.CCSObjectList;
import com.minsx.ccs.core.model.request.CCSListObjectsRequest;
import com.minsx.ccs.core.model.request.CCSPageObjectsRequest;
import com.minsx.ccs.core.service.CCSClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2018/2/27 17:54
 */
public class KodoTest {

    private CCSClient ccsClient;

    @Before
    public void initial() {
        QiniuKodoConfig config = new QiniuKodoConfig();
        config.setAccessKey("todo");
        config.setSecretKey("todo");
        config.setBucket("12306-img");
        config.setZone("zone0");
        CCSPageObjectsRequest req = new CCSPageObjectsRequest("12306-img", "", 0, 5);
        ccsClient = new QiniuKodoImpl(config);
    }

    @After
    public void end() {
        ccsClient.shutdown();
    }

    @Test
    public void createBucket() {
        ccsClient.createBucket("qiniu_ccs_test");
        System.out.println("Bucket created, please see in qiNiu console.");
    }

    @Test
    public void deleteBucket() {
        ccsClient.deleteBucket("qiniu_ccs_test");
        System.out.println("Bucket deleted, please see in qiNiu console.");
    }

    @Test
    public void doesBucketExist() {
        Boolean boo = ccsClient.doesBucketExist("qiniu_ccs_test");
        System.out.println("result: " + boo);
    }

    @Test
    public void listCCSBuckets() {
        ccsClient.listCCSBuckets().forEach(it -> {
            System.out.println(it.getName());
        });
    }

    @Test
    public void createFolder() {
        ccsClient.createFolder("bucketName", "dirName");
    }

    @Test
    public void listObjects1() {
        CCSObjectList list = ccsClient.listObjects("12306-img", "");
        System.out.println("size: " + list.getCcsObjectSummaries().size());
    }

    @Test
    public void listObjects2() {
        CCSListObjectsRequestable listRequest = new CCSListObjectsRequest("12306-img");
        listRequest.withMaxKeys(5);
        CCSObjectList list = ccsClient.listObjects(listRequest);
        list.getCcsObjectSummaries().forEach(it -> {
            System.out.println(it.getCcsPath());
        });
    }

    @Test
    public void listObjects3() {
        CCSPageObjectsRequestable req = new CCSPageObjectsRequest("12306-img", "", 0, 5);
        CCSObjectList list = ccsClient.listObjects(req);
        list.getCcsObjectSummaries().forEach(it -> {
            System.out.println(it.getCcsPath());
        });
    }

    @Test
    public void doesObjectExist() {
        Boolean boo = ccsClient.doesObjectExist("12306-img", "1008f218357ab17ccf3d97b60e3481d8");
        System.out.println("result: " + boo);
    }
}
