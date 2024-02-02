package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import com.sky.utils.QiniuUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * @author AkazaAkari
 * @version 1.0
 * @Date 2023/12/24 18:33:12
 * @Comment sky-take-out>xuzq
 * @className CommonController
 * @description TODO
 */
@RestController
@Slf4j
@Api(tags = "通用接口")
@RequestMapping("/admin/common")
public class CommonController {

    @Autowired
    private AliOssUtil aliOssUtil;
    @Autowired
    private QiniuUtils qiniuUtils;
/**
     * 文件上传
     * @param file
     * @return
     */
	 
    // @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @PostMapping("/uploads")
    @ApiOperation("文件上传")
    public Result<String> uploads(MultipartFile file) {
        log.info("上传的文件是：{}", file);

        try {
            // 获取源文件名
            String filenameOld = file.getOriginalFilename();
            // 获取源文件的后缀
            String extension = filenameOld.substring(filenameOld.lastIndexOf("."));
            //构造新文件名
            String fileNameNew = UUID.randomUUID().toString() + extension;
            //文件的请求路径
            String filePath = aliOssUtil.upload(file.getBytes(), fileNameNew);
            return Result.success(fileNameNew);
        } catch (IOException e) {
            // throw new RuntimeException(e);
            log.info("文件上传失败:{}",e);
        }
        return Result.error(MessageConstant.UPLOAD_FAILED);
    }

    @PostMapping("/upload")
    // @PostMapping
    public Result<String> upload(@RequestParam("file") MultipartFile file){
        log.info("上传的文件是：{}", file);
        // 获取源文件名
        String filenameOld = file.getOriginalFilename();
        // 获取源文件的后缀
        String extension = filenameOld.substring(filenameOld.lastIndexOf("."));
        //构造新文件名
        String fileNameNew = UUID.randomUUID().toString() + extension;
        log.info("文件上传成功:{}",fileNameNew);
        // String originalFilename = file.getOriginalFilename();
        // String fileName = UUID.randomUUID().toString() + "."+ StringUtils.substringAfterLast(originalFilename, ".");
        //上传文件 上传到哪呢 七牛云 服务器 按量付费 速度快 把图片发放到离用户最近的服务器上
        // 降低我们自身应用服务器的带宽消耗
        boolean upload = qiniuUtils.upload(file, fileNameNew);
        if (upload) {
            return Result.success(qiniuUtils.url+fileNameNew);
        }
        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}
