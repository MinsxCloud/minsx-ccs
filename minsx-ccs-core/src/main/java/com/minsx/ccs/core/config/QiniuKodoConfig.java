package com.minsx.ccs.core.config;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2017/12/7 15:06
 */
public class QiniuKodoConfig {

    private String accessKey;

    private String secretKey;

    private String bucket;

    private String host;

    /**
     * 地区</br>
     * 可取值<code>zone0,huadong,zone1,huabei,zone2,huanan,zoneNa0,beimei</code>
     */
    private String zone;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
