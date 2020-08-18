package com.example.springbootmybtisplus.util;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.example.springbootmybtisplus.config.FileTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 *
 * @author zengrenshang
 * @since 2020/8/18 19:25
 */
@Slf4j
@Component
public class AliyunOssUtils {


    /**
     * oss上bucket的名称
     */
    @Value("${oss.bucketName:bucket-shang}")
    private String bucketName;


    /**
     * 阿里对应的访问id
     */
    @Value("${oss.accessKeyId:LTAI4GHjRJA2es7rcMMb3ERw}")
    private String accessKeyId;

    /**
     * 阿里对应的密钥
     */
    @Value("${oss.accessKeySecret:ruraII1FGBwKgy4uMY9j26RSrhri8K}")
    private String accessKeySecret;

    /**
     * oss对应的区域节点
     */
    @Value("${oss.endpoint:https://oss-cn-shenzhen.aliyuncs.com}")
    private String endpoint;

    /**
     * 上传文件到阿里云 OSS 服务器
     * @author zengrenshang
     * @param files 文件
     * @param fileTypeEnum 文件类型
     * @return List<String>
     */
    public List<String> uploadFile(MultipartFile[] files, String storagePath, FileTypeEnum fileTypeEnum) {

        // 创建OSSClient实例
        final OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        final List<String> fileIds = new ArrayList<>();
        try {
            for (final MultipartFile file : files) {
                // 创建一个唯一的文件名，类似于id，就是保存在OSS服务器上文件的文件名(自定义文件名)
                String fileName = UUID.randomUUID().toString().replaceAll("-","");
                final InputStream inputStream = file.getInputStream();
                // 设置对象
                final ObjectMetadata objectMetadata = new ObjectMetadata();
                // 设置数据流里有多少个字节可以读取
                objectMetadata.setContentLength(inputStream.available());
                objectMetadata.setCacheControl("no-cache");
                objectMetadata.setHeader("Pragma", "no-cache");
                objectMetadata.setContentType(file.getContentType());
                objectMetadata.setContentDisposition("inline;filename=" + fileName);
                fileName = storagePath + "/" + fileName;
                // 上传文件
                final PutObjectResult result = ossClient.putObject(bucketName, fileName, inputStream, objectMetadata);
                log.info("Aliyun OSS AliyunOSSUtil.uploadFileToAliyunOSS,result:{}", result);
                fileIds.add(fileName);
            }
        }
        catch (final IOException e) {
            log.error("Aliyun OSS AliyunOSSUtil.uploadFileToAliyunOSS fail,reason:{}", e);
        }
        finally {
            ossClient.shutdown();
        }
        return fileIds;
    }

    /**
     * 下载文件
     * @author zengrenshang
     * @param fileName
     * @return
     */
    public  InputStream downloadFile(String fileName){
        boolean objectExist = doesObjectExist(fileName);
        if (objectExist){
            final OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            OSSObject object = ossClient.getObject(bucketName, fileName);
            InputStream inputStream = object.getObjectContent();
            return inputStream;
        }
        return null;
    }

    /**
     * 删除文件
     * @author zengrenshang
     * @param fileName void
     */
    public void deleteFile(String fileName) {
        final OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            ossClient.deleteObject(bucketName, fileName);
        }
        catch (final Exception e) {
            log.error("{}", e.fillInStackTrace());
        }
        finally {
            ossClient.shutdown();
        }
    }

    /**
     * 判断文件是否存在
     * @author zengrenshang
     * @param fileName 文件名称
     * @return boolean
     */
    public boolean doesObjectExist(String fileName) {
        final OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            if (StringUtils.isEmpty(fileName)) {
                log.error("文件名不能为空");
                return false;
            } else {
                final boolean found = ossClient.doesObjectExist(bucketName, fileName);
                return found;
            }
        }
        finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}
