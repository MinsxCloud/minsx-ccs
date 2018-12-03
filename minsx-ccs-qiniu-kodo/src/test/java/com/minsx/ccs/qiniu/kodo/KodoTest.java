package com.minsx.ccs.qiniu.kodo;

import com.minsx.ccs.core.able.CCSListObjectsRequestable;
import com.minsx.ccs.core.able.CCSPageObjectsRequestable;
import com.minsx.ccs.core.config.QiniuKodoConfig;
import com.minsx.ccs.core.model.model.CCSObjectList;
import com.minsx.ccs.core.model.request.CCSListObjectsRequest;
import com.minsx.ccs.core.model.request.CCSPageObjectsRequest;
import com.minsx.ccs.core.service.CCSClient;
import com.qiniu.http.Client;
import com.qiniu.storage.BucketManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

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
        config.setAccessKey("FnobzListYUq7J-Knyg08RXssVx51xJCfa-eVe6D");
        config.setSecretKey("BP8upcETcjZ6SwPlgOsD4INArNEGXHh45mHf0xE9");
        config.setBucket("static");
        config.setZone("zone0");
        config.setHost("https://static.axboy.cn/");
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
        Boolean boo = ccsClient.doesBucketExist("qiniu_ccs_test1");
        System.out.println("Result: " + boo);
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
        CCSObjectList list = ccsClient.listObjects("static", "");
        System.out.println("size: " + list.getCcsObjectSummaries().size());
    }

    @Test
    public void listObjects2() {
        CCSListObjectsRequestable listRequest = new CCSListObjectsRequest("static");
        listRequest.withMaxKeys(5);
        CCSObjectList list = ccsClient.listObjects(listRequest);
        list.getCcsObjectSummaries().forEach(it -> {
            System.out.println(it.getCcsPath());
        });
    }

    @Test
    public void listObjects3() {
        CCSPageObjectsRequestable req = new CCSPageObjectsRequest("static", "", 0, 5);
        CCSObjectList list = ccsClient.listObjects(req);
        list.getCcsObjectSummaries().forEach(it -> {
            System.out.println(it.getCcsPath());
        });
    }

    @Test
    public void doesObjectExist() {
        Boolean boo = ccsClient.doesObjectExist("static", "index.html");
        System.out.println("Result: " + boo);
    }

    @Test
    public void getObject() {
        ccsClient.getObject("static", "index.html");
    }

    @Test
    public void downloadObject() {
        File file = new File("/Users/zcw/Desktop/index.html");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ccsClient.downloadObject("static", "index.html", file);
    }

    @Test
    public void deleteObject() {
        ccsClient.deleteObject("static", "test.txt");
    }

    @Test
    public void shutdown() {
        ccsClient.shutdown();
    }

    @Test
    public void getNativeClient() {
        ccsClient.getNativeClient(Client.class);
    }
}
