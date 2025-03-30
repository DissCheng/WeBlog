package com.disscheng.weblog.controller;

import com.disscheng.weblog.exception.FileUploadException;
import com.disscheng.weblog.result.Result;
import com.disscheng.weblog.utils.MinioUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.disscheng.weblog.entity.File;

@RestController
@RequestMapping("/users/file")
@Slf4j
public class FileController {
    @Autowired
    private MinioUtil minioUtil;


    @PostMapping("/upload")
    public Result<File> upload(@RequestParam("file") MultipartFile file) {
        log.info("==> 接收到上传文件请求");
        try {
            log.info("==> 开始上传文件至 Minio");
            File fileEntity = new File();
            fileEntity.setUrl(minioUtil.uploadFile(file));
            return Result.success(fileEntity);
        }catch (Exception e) {
            throw new FileUploadException("上传文件失败");
        }
    }
}
