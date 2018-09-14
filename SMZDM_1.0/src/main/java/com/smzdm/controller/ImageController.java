package com.smzdm.controller;

import com.aliyun.oss.OSSClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

/**
 * 处理图片的相关操作
 */
@Controller
public class ImageController {

    /**
     * 自动注入service对象
     */

    /**
     * 图片上传
     * @return
     */
    @RequestMapping(value = "/uploadImage/",method = RequestMethod.POST)
    @ResponseBody
    public HashMap uploadImage(MultipartFile file){
        HashMap<String, Object> hashMap = new HashMap<>();

        //获取文件名
        String filename = file.getOriginalFilename();
        //取文件后缀名
        String[] split = filename.split("\\.");
        String s = null;

        if (split != null){
             s = split[split.length - 1];
        }

        UUID uuid = UUID.randomUUID();
        //上传文件保存文件名
        String saveName= uuid.toString()+"."+s;

        // Endpoint地域节点，这里为深圳
        String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限
        String accessKeyId = "LTAIIybl6aBkm8DB";
        String accessKeySecret = "0pBzvabQMcBRF0lbAGMJRoO0LC7J50";
        // 创建存储空间。bucketName是创建bucket时取的名
        String bucketName = "my-news-image-dir";
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 上传文件。

        try {
            ossClient.putObject(bucketName, saveName, new ByteArrayInputStream(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 关闭OSSClient。
        ossClient.shutdown();

        //上传成功。将数据库存储的，以及首页显示的图片压缩100*80
        String imageUrl = "http://" + bucketName + "." + endpoint.split("//")[1] + "/" + saveName + "?x-oss-process=image/resize,m_fixed,h_100,w_80";

        hashMap.put("code",0);
        hashMap.put("msg",imageUrl);

        return hashMap;
    }
}
