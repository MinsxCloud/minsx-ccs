# Minsx-CCS

###### (Instruction : 如果说JPA是用于统一各数据库或ORM的框架,那么CCS即是统一各云存储的框架)
#### 主要用于统一阿里云、百度云、腾讯云、七牛云等云存储及本地存储，提供一套简单通用的调用标准

### 项目说明
+ 软件名称：Minsx-ccs (全称：minsx-common-cloud-storge 即公共云存储)
+ 版本号：1.0.0
+ 开发者：www.minsx.com
+ 语言：Java
+ 功能：统一各云平台对象存储的调用,只需要简单的配置即可完成各云平台对象存储的调用管理,其中包括：
	+ 阿里云 OSS
	+ 百度云 BOS
	+ 腾讯云 COS
	+ 七牛 KODO
	+ 本地存储(操作系统文件形式的存储,非对象存储)
+ 优点：开箱即用/配置简单/通用/可扩展/为混合对象存储而生
+ 缺点：部分功能被阉割(注：您仍可以获取原生客户端进行更细致的操作)
+ 开源协议：Apache License Version 2.0 http://www.apache.org/licenses/
				
### 适用场景
+ 数据托管在以上多家云平台对象存储的企业
+ 需要具备扩展性业务需求(将来会使用以上云平台对象存储)
+ 基础语言为JAVA(如:Spring Boot)的系统

### 使用示例
	
```java
private CCSClient ccsClient;

	@Before
	public void initial() {
		AliyunOSSConfig aliyunOSSConfig = new AliyunOSSConfig();
		aliyunOSSConfig.setEndPoint("http://oss-cn-hangzhou.aliyuncs.com");
		aliyunOSSConfig.setAccessKeyId("LTAI94azdAVJieZJ");
		aliyunOSSConfig.setAccessKeySecret("SaFDFCHSGCljmleQtRdVuZHmeedvty");
		ccsClient = new AliyunOSSImpl(aliyunOSSConfig);
	}
	
	@After
	public void end() {
		ccsClient.shutdown();
	}

	@Test
	public void getObjectMetaData() {
		ccsClient.getObjectMetadata("minsx-bucket", "A.docx").getUserMetaData().forEach((key,value)->{
			System.out.println(key);
			System.out.println(value);
		});
	}
	
	@Test
	public void getObject() {
		CCSObject ccsObject = ccsClient.getObject("minsx-bucket", "A.docx");
		System.out.println(ccsObject);
	}
	
	@Test
	public void deleteObject() {
		ccsClient.deleteObject("minsx-bucket", "A.docx");
	}
	
	@Test
	public void putObject() {
		CCSPutObjectResponse ccsPutObjectResponse = ccsClient.putObject("minsx-bucket", "A.docx","E:\\Temp\\A.docx");
		System.out.println(JSON.toJSONString(ccsPutObjectResponse));
	}
	
	@Test
	public void listObjects() {
		CCSObjectList ccsObjectList = ccsClient.listObjects("minsx-bucket", "hospital");
		List<CCSObjectSummary> ccsObjectSummaries =  ccsObjectList.getCcsObjectSummaries();
		ccsObjectSummaries.forEach(ccsObjectSummary->{
			System.out.println(ccsObjectSummary.getCcsPath());
		});
	}
	
	@Test
	public void listAllObjects() {
		final int maxKeys = 10;
		String nextMarker = null;
		CCSObjectList objectListing=null;
		int sum=0;
		do {
		    objectListing = ccsClient.listObjects(new CCSListObjectsRequest("minsx-bucket").withPrefix("oss-log").withMarker(nextMarker).withMaxKeys(maxKeys));
		    List<CCSObjectSummary> sums = objectListing.getCcsObjectSummaries();
		    for (CCSObjectSummary s : sums) {
		    	System.out.println(s.getCcsPath());
		    }
		    nextMarker = objectListing.getNextMarker();
		    sum++;
		} while (objectListing.isTruncated());
		 System.out.println(sum);
	}
	
	@Test
	public void listObjectsByPage() {
		CCSObjectList ccsObjectList = ccsClient.listObjects(new CCSPageObjectsRequest("minsx-bucket", "oss-log", 1, 10));
		List<CCSObjectSummary> ccsObjectSummaries =  ccsObjectList.getCcsObjectSummaries();
		ccsObjectSummaries.forEach(ccsObjectSummary->{
			System.out.println(ccsObjectSummary.getCcsPath());
		});
		ccsClient.shutdown();
	}
	
	@Test
	public void listCCSBuckets() {
		List<CCSBucket> ccsBuckets = ccsClient.listCCSBuckets();
		ccsBuckets.forEach(bucket->{
			System.out.println(bucket.getName());
		});
	}
	
	@Test
	public void putObjectByCCSPutObjectRqeuest() {
		CCSPutObjectRqeuest ccsPutObjectRqeuest = new CCSPutObjectRqeuest();
		ccsPutObjectRqeuest.setBucketName("minsx-bucket");
		ccsPutObjectRqeuest.setCcsObjectPath("A.docx");
		ccsPutObjectRqeuest.setFile(new File("E:\\Temp\\A.docx"));
		ccsClient.putObject(ccsPutObjectRqeuest);
	}
```
+ 注：我们对原生Key做了重命名为ccsObjectPath (实际对象存储中并不存在路径及文件夹等概念)
+ 在语法上我们尽量靠近各原生SDK以降低学习成本

	
