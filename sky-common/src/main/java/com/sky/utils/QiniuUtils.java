package com.sky.utils;

import com.alibaba.fastjson.JSON;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.sky.properties.QiniuProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author AkazaAkari
 * @version 1.0
 * @className QiniuUtils
 * @description TODO
 * @date 2022/09/12 17:45
 */
@Component
public class QiniuUtils {

    public static final String url = "http://s6pqkr1gf.hd-bkt.clouddn.com/";

    @Autowired
    private QiniuProperties qiniuProperties;


    public boolean upload(MultipartFile file, String fileName) {

        // 构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.huadong());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传，修改上传名称为自己创立空间的空间名称（是你自己的）
        // String bucket = "bankemperor";
        // 默认不指定key的情况下，以文件内容的hash值作为文件名
        try {
            if (file.isEmpty()) {
                return false;
            }
            // 上传文件流 File
            byte[] uploadBytes = file.getBytes();
            // 密钥配置
            Auth auth = Auth.create(qiniuProperties.getAccessKey(), qiniuProperties.getAccessSecretKey());
            // 简单上传，使用默认策略，只需要设置上传的空间名
            String upToken = auth.uploadToken(qiniuProperties.getBucket());
            // 上传
            Response response = uploadManager.put(uploadBytes, fileName, upToken);
            // 解析上传成功的结果
            DefaultPutRet putRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);
            if (putRet.key != null || putRet.hash != null)
                return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}


